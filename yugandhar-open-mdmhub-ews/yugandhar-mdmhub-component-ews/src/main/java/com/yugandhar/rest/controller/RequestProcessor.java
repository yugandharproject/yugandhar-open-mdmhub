package com.yugandhar.rest.controller;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StopWatch;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.config.txnregistry.ConfigTxnRegistryComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.ConfigTxnRegistryDO;
import com.yugandhar.mdm.misc.dobj.CommonValidationResponse;


/**
 * RequestProcessor is the default entry point for invoking transaction on Yugandhar MDM Application
 * This class invokes the transaction as mentioned in the incoming request and return the response
 *
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */


@Scope(value = "prototype")
@Service
public class RequestProcessor {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	private static final Logger perflogger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_PERFSUMMARY_LOGGER);
	private static final Logger perfErrorlogger = LoggerFactory
			.getLogger(yugandharConstants.YUGANDHAR_ERROR_PERFSUMMARY_LOGGER);

	@Autowired
	private ApplicationContext context;

	@Autowired
	ConfigTxnRegistryComponent theConfigTxnRegistryComponent;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	CommonValidationResponse commonValidationResponse;
	private String ServiceClassName = null;
	private String ServiceClassMethodName = null;

	public RequestProcessor() {
		commonValidationResponse = new CommonValidationResponse();
	}

	
	/**
	 * processMessage method processes the incoming message wherein it invokes the spring bean as per the 
	 * transaction name mentioned in txnTransferObj.txnHeader.transactionServiceName attribute.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with persisted instance
	 * @throws YugandharCommonException
	 *             if transaction fails due to any reason
	 *
	 */
	
	@Transactional
	public TxnTransferObj processMessage(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		commonValidationResponse = commonValidationUtil.validateHeader(txnTransferObj);

		if (commonValidationResponse.getValidationResult() == true) {
			try {

				authorizeRequest(txnTransferObj);

				setServiceClassAndMethodName(txnTransferObj);
				Object serviceobj = context.getBean(this.ServiceClassName);
				Class<?>[] paramTxnTransObj = new Class[1];
				paramTxnTransObj[0] = TxnTransferObj.class;
				Method method = ReflectionUtils.findMethod(serviceobj.getClass(), this.ServiceClassMethodName,
						paramTxnTransObj);
				ReflectionUtils.makeAccessible(method);
				txnTransferObj = (TxnTransferObj) ReflectionUtils.invokeMethod(method, serviceobj, txnTransferObj);

			} catch (Exception e) {
				stopWatch.stop();
				// e.printStackTrace();
				if (e instanceof YugandharCommonException) {
					YugandharCommonException yce = (YugandharCommonException) e;
					logPerformanceErrorSummary(stopWatch, yce, txnTransferObj);
					throw yce;
				}
				// write the logic to get error message based on error code.
				// Error code is hard-coded here
				logger.error("Transaction failed", e);
				YugandharCommonException yce = commonValidationUtil.populateErrorResponse(txnTransferObj, "1", e,
						"RequestProcessor.processMessage failed unexpectedly, either cant find the method or bean class");
				logPerformanceErrorSummary(stopWatch, yce, txnTransferObj);
				throw yce;

			}
		} else {
			txnTransferObj.setResponseCode("FAIL");
			txnTransferObj.getTxnPayload().setErrorResponseObj(commonValidationResponse);
			return txnTransferObj;
		}
		stopWatch.stop();
		txnTransferObj.getTxnHeader().setTotalExecutionTimeMillies(String.valueOf(stopWatch.getTotalTimeMillis()));
		logPerformanceSummary(stopWatch, txnTransferObj);
		return txnTransferObj;

	}
	
	/**
	 * authorizeRequest invokes the authorization framework and check If User Or Role mentioned intxnTransferObj.txnHeader.userName or 
	 * txnTransferObj.txnHeaderuserRole Is Authorized to invoke transaction mentioned in 
	 * txnTransferObj.txnHeader.transactionServiceName attribute.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            txnTransferObj from the request
	 * @return void
	 *
	 */

	private void authorizeRequest(TxnTransferObj txnTransferObj) {

		boolean isAuthorized = false;
		boolean skipAuthorizationFrameworkflag = false;

		try {

			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_authorization_framework_enabled,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (theConfigAppPropertiesDO.getValue().toLowerCase().equals(yugandharConstants.FLAG_true)) {
				// if authorization framework is enabled then perform the
				// authorization

				isAuthorized = commonValidationUtil.checkIfUserOrRoleIsAuthorizedForRequestedTxn(txnTransferObj);
			} else {
				// skip authorization
				skipAuthorizationFrameworkflag = true;
			}

		} catch (YugandharCommonException yce) {
			// write the logic to get error message based on error code.
			logger.error("checkIfUserOrRoleIsAuthorizedForRequestedTxn failed");
			throw yce;
		} catch (RuntimeException re) {
			logger.error("checkIfUserOrRoleIsAuthorizedForRequestedTxn failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"Transaction Failed due to unknown error, please check stack trace");
			// Transaction Failed due to unknown error, please check stack trace
		}

		if (skipAuthorizationFrameworkflag) {
			logger.info(
					"Authorization check is disabled in the configuration properties, application is not protected");
		} else {
			if (isAuthorized) {
				logger.info("user or role is authorized");
			} else {

				logger.error("user or role is not authorized to execute given transaction");
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "10083", new Exception(),
						"User or Role not Authorized");
			}
		}

	}

	/**
	 * Logs the performance summary in below format
	 * 
	 * TransactionServiceName|UserName|UserRole|RequesterSystem|RequestOriginSource|TxnCorrelationId|TxnMessageId|ResponseCode|TotalExecutionTimeMillies
	 * 
	 * @since 1.0
	 * @param stopWatch
	 *            active stopwatch
	 * @param txnTransferObj
	 *            txnTransferObj from the response
	 * @return void
	 *
	 */

	private void logPerformanceSummary(StopWatch stopWatch, TxnTransferObj txnTransferObj) {
		StringBuffer perflogBuffer = new StringBuffer();
		perflogBuffer.append(txnTransferObj.getTxnHeader().getTransactionServiceName());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getUserName());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getUserRole());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getRequesterSystem());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getRequestOriginSource());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getTxnCorrelationId());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getTxnMessageId());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getResponseCode());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getTotalExecutionTimeMillies());

		perflogger.info(perflogBuffer.toString());

	}

	/**
	 * Logs the performance error summary in below format
	 * 
	 * TransactionServiceName|UserName|UserRole|RequesterSystem|RequestOriginSource|TxnCorrelationId|TxnMessageId|ResponseCode|TotalExecutionTimeMillies|ErrorCode|ErrorMessage
	 * 
	 * @since 1.0
	 * @param stopWatch
	 *            active stopwatch
	 * @param txnTransferObj
	 *            txnTransferObj from the response
	 * @return void
	 *
	 */

	private void logPerformanceErrorSummary(StopWatch stopWatch, YugandharCommonException yce,
			TxnTransferObj txnTransferObj) {
		StringBuffer perflogBuffer = new StringBuffer();
		perflogBuffer.append(txnTransferObj.getTxnHeader().getTransactionServiceName());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getUserName());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getUserRole());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getRequesterSystem());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getRequestOriginSource());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getTxnCorrelationId());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getTxnMessageId());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getResponseCode());
		perflogBuffer.append("|");
		perflogBuffer.append(txnTransferObj.getTxnHeader().getTotalExecutionTimeMillies());
		perflogBuffer.append("|");
		perflogBuffer.append(yce.getErrorCode());
		perflogBuffer.append("|");
		perflogBuffer.append(yce.getErrorMessage());
		perfErrorlogger.info(perflogBuffer.toString());

	}
	
	/**
	 * setServiceClassAndMethodName find the service class name and method to be invoked based on the
	 * value of txnTransferObj.txnHeader.transactionServiceName attribute 
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            txnTransferObj from the request
	 * @return void
	 *
	 */
	


	private void setServiceClassAndMethodName(TxnTransferObj txnTransferObj) {
		TxnTransferObj theTxnTransferObj = new TxnTransferObj();
		theTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		ConfigTxnRegistryDO theConfigApppropertiesDO = new ConfigTxnRegistryDO();
		theConfigApppropertiesDO.setTxnserviceName(txnTransferObj.getTxnHeader().getTransactionServiceName());
		theTxnTransferObj.getTxnPayload().setConfigTxnRegistryDO(theConfigApppropertiesDO);
		try {
			theTxnTransferObj = theConfigTxnRegistryComponent.findByBusinessKey(theTxnTransferObj);
		} catch (YugandharCommonException yce) {
			// write the logic to get error message based on error code.
			logger.error("Transaction Service Name not found in configuration");
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1002", new Exception(), null);
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re, null);
			// Transaction Failed due to unknown error, please check statck
			// trace
		}

		if (null != theTxnTransferObj.getTxnPayload().getConfigTxnRegistryDO()) {
			if (null != theTxnTransferObj.getTxnPayload().getConfigTxnRegistryDO().getTxnserviceClass()
					&& null != theTxnTransferObj.getTxnPayload().getConfigTxnRegistryDO().getTxnserviceClassmethod()) {
				this.ServiceClassName = theTxnTransferObj.getTxnPayload().getConfigTxnRegistryDO().getTxnserviceClass()
						.trim();
				this.ServiceClassMethodName = theTxnTransferObj.getTxnPayload().getConfigTxnRegistryDO()
						.getTxnserviceClassmethod().trim();
			} else {
				logger.error("Transaction Service Name is present but not configured properly");
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1003", new Exception(), null);
			}
		} else {
			// write the logic to get error message based on error code.
			logger.error("Transaction Service Name not found in configuration");
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1002", new Exception(), null);
		}

	}
	
	
	/**
	 * processMessage method invokes processMessage method asynchronously.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with persisted instance
	 * @throws YugandharCommonException
	 *             if transaction fails due to any reason
	 *
	 */
	
	@Async
	public TxnTransferObj processMessageAsync(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		logger.info("processMessageAsync method recieved the message");
		return processMessage(txnTransferObj);
		
	}

}
