package com.yugandhar.mdm.composite.service;
/* Generated Jul 3, 2017 2:32:54 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.extern.transferobj.TxnPayload;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.PerformLeMatchRequestDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.PerformLeMatchService")
public class PerformLeMatchService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	private static final Logger matchEngineLogger = LoggerFactory
			.getLogger(yugandharConstants.YUGANDHAR_MATCH_ENGINE_APPENDER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@Autowired
	LegalentityComponent legalentityComponent;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	private ApplicationContext context;

	public PerformLeMatchService() {
		txnTransferObjResponse = new TxnTransferObj();
		respTxnTransferObj = new TxnTransferObj();
		respTxnPayload = new TxnPayload();
		transitTxnTransferObj = new TxnTransferObj();
	}

	@Transactional
	public TxnTransferObj process(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		TxnPayload transitTxnPayload = null;
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {

			performCommonvalidationBeforeExecution(txnTransferObj);

			transitTxnPayload = new TxnPayload();
			transitTxnPayload
					.setLegalentityDO(txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO().getLegalentityDO());
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = legalentityComponent.findById(transitTxnTransferObj);
			LegalentityDO respLegalentityDO = transitTxnTransferObj.getTxnPayload().getLegalentityDO();

			//set the response in the txnTransferObj, this is required to retrieve the entityObjectTypeRefkey of the object
			txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO().setLegalentityDO(respLegalentityDO);
			transitTxnTransferObj = invokeMatchRule(txnTransferObj);

			respTxnTransferObj.setTxnPayload(transitTxnTransferObj.getTxnPayload());

		} catch (YugandharCommonException yce) {
			logger.error("PerformLeMatchService Composite Service failed", yce);
			throw yce;
		} catch (Exception e) {
			// write the logic to get error message based on error code. Error
			// code is hard-coded here
			logger.error("PerformLeMatchService Composite Service failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
					"PerformLeMatchService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

	}

	private TxnTransferObj invokeMatchRule(TxnTransferObj reqTxnTransferObj) {

		PerformLeMatchRequestDO thePerformLeMatchRequestDO = reqTxnTransferObj.getTxnPayload()
				.getPerformLeMatchRequestDO();
		String entityObjectTypeRefkey = thePerformLeMatchRequestDO.getLegalentityDO().getEntityObjectTypeRefkey();
		String className = null;
		String methodName = null;
		// Phonetic
		if (thePerformLeMatchRequestDO.getMatchEngineType()
				.equalsIgnoreCase(yugandharConstants.match_le_engine_type_deterministic)) {

			if (entityObjectTypeRefkey.equalsIgnoreCase("1")) {
				ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
						YugandharConfigurationProperties.com_yugandhar_match_le_Deterministic_LePerson_RuleClass,
						yugandharConstants.FILTER_VALUE_ACTIVE);

				className = theConfigAppPropertiesDO.getValue();

				theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
						YugandharConfigurationProperties.com_yugandhar_match_le_Deterministic_LePerson_RuleClassMethod,
						yugandharConstants.FILTER_VALUE_ACTIVE);
				methodName = theConfigAppPropertiesDO.getValue();

			} else if (entityObjectTypeRefkey.equalsIgnoreCase("2")) {

				ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
						YugandharConfigurationProperties.com_yugandhar_match_le_Deterministic_LeCorporation_RuleClass,
						yugandharConstants.FILTER_VALUE_ACTIVE);

				className = theConfigAppPropertiesDO.getValue();

				theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
						YugandharConfigurationProperties.com_yugandhar_match_le_Deterministic_LeCorporation_RuleClassMethod,
						yugandharConstants.FILTER_VALUE_ACTIVE);
				methodName = theConfigAppPropertiesDO.getValue();
			}

		}

		if (thePerformLeMatchRequestDO.getMatchEngineType()
				.equalsIgnoreCase(yugandharConstants.match_le_engine_type_fuzzy)) {

			if (entityObjectTypeRefkey.equalsIgnoreCase("1")) {
				ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
						YugandharConfigurationProperties.com_yugandhar_match_le_Fuzzy_LePerson_RuleClass,
						yugandharConstants.FILTER_VALUE_ACTIVE);

				className = theConfigAppPropertiesDO.getValue();

				theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
						YugandharConfigurationProperties.com_yugandhar_match_le_Fuzzy_LePerson_RuleClassMethod,
						yugandharConstants.FILTER_VALUE_ACTIVE);
				methodName = theConfigAppPropertiesDO.getValue();

			} else if (entityObjectTypeRefkey.equalsIgnoreCase("2")) {
				ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
						YugandharConfigurationProperties.com_yugandhar_match_le_Fuzzy_LeCorporation_RuleClass,
						yugandharConstants.FILTER_VALUE_ACTIVE);

				className = theConfigAppPropertiesDO.getValue();

				theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
						YugandharConfigurationProperties.com_yugandhar_match_le_Fuzzy_LeCorporation_RuleClassMethod,
						yugandharConstants.FILTER_VALUE_ACTIVE);
				methodName = theConfigAppPropertiesDO.getValue();

			}
		}

		Object matchRule = context.getBean(className);
		Class<?>[] paramTxnTransObj = new Class[1];
		paramTxnTransObj[0] = TxnTransferObj.class;
		Method method = ReflectionUtils.findMethod(matchRule.getClass(), methodName, paramTxnTransObj);
		ReflectionUtils.makeAccessible(method);
		TxnTransferObj respTxnTransferObj = (TxnTransferObj) ReflectionUtils.invokeMethod(method, matchRule,
				reqTxnTransferObj);
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation that LegalentityDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LegalentityDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeExecution(TxnTransferObj txnTransferObj) {

		if (null == txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"performLeMatchRequestDO is needed in the request");
		}

		PerformLeMatchRequestDO thePerformLeMatchRequestDO = txnTransferObj.getTxnPayload()
				.getPerformLeMatchRequestDO();

		if (isNullOrEmpty(thePerformLeMatchRequestDO.getMatchEngineType())) {

			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_match_le_engine_type,
					yugandharConstants.FILTER_VALUE_ACTIVE);

			if (isNullOrEmpty(theConfigAppPropertiesDO.getValue())) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10114",
						"performLeMatchRequestDO.matchEngineType is needed in the request or Configuration property com_yugandhar_match_le_engine_type must be set ");
			} else {
				thePerformLeMatchRequestDO.setMatchEngineType(theConfigAppPropertiesDO.getValue());
				if (matchEngineLogger.isInfoEnabled()) {
					matchEngineLogger
							.info("PerformLeMatchService.performCommonvalidationBeforeExecution performLeMatchRequestDO.matchEngineType not found, setting the value from configuration properties: "
									+ theConfigAppPropertiesDO.getValue());
				}
			}

		} else if (!(thePerformLeMatchRequestDO.getMatchEngineType()
				.equalsIgnoreCase(yugandharConstants.match_le_engine_type_deterministic))) {

			if (!(thePerformLeMatchRequestDO.getMatchEngineType()
					.equalsIgnoreCase(yugandharConstants.match_le_engine_type_fuzzy))) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10115",
						"performLeMatchRequestDO.matchEngineType is not valid");
			}
		}

		if (null == thePerformLeMatchRequestDO.getLegalentityDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10116",
					"performLeMatchRequestDO.legalentityDO is needed in the request");
		}

		/*if (isNullOrEmpty(thePerformLeMatchRequestDO.getLegalentityDO().getEntityObjectTypeRefkey())) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10117",
					"performLeMatchRequestDO.legalentityDO.entityObjectTypeRefkey is needed in the request");
		}*/

	}

	private boolean isNullOrEmpty(String strToCheck) {

		if (null == strToCheck || strToCheck.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
}
