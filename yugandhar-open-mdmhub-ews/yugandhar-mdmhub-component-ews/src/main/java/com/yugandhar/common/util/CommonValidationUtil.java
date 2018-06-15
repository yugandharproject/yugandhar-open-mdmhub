package com.yugandhar.common.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.extern.transferobj.TxnPayload;
import com.yugandhar.common.transobj.TxnHeader;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.mdm.auth.SearchAuthAccessControlService;
import com.yugandhar.mdm.auth.SearchAuthRolesService;
import com.yugandhar.mdm.config.errorcoderegistry.ConfigErrorcodeRegistryComponent;
import com.yugandhar.mdm.extern.dobj.AuthRolesRegistryDO;
import com.yugandhar.mdm.extern.dobj.AuthUserRegistryDO;
import com.yugandhar.mdm.extern.dobj.ConfigErrorcodeRegistryDO;
import com.yugandhar.mdm.extern.dobj.SearchAuthAccessControlDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;
import com.yugandhar.mdm.misc.dobj.CommonValidationResponse;

@Component
public class CommonValidationUtil {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	@Autowired
	protected ConfigErrorcodeRegistryComponent theConfigErrorcodeRegistryComponent;

	@Autowired
	protected SearchAuthAccessControlService theSearchAuthAccessControlService;
	
	@Autowired
	protected SearchAuthRolesService theSearchAuthRolesService;

	public CommonValidationResponse validateHeader(TxnTransferObj txnTransferObj) {
		TxnHeader txnHeader = txnTransferObj.getTxnHeader();
		CommonValidationResponse commonValidationResponse = new CommonValidationResponse();

		if (null == txnHeader) {
			commonValidationResponse.setValidationResult(false);
			commonValidationResponse.setErrorCode("0");// requesterLanguage is
														// Needed in the request
														// txnHeader
			commonValidationResponse.setErrorMessage("txnHeader object is needed in the request");
			commonValidationResponse.setAdditionalErrorMessage("txnHeader object is needed in the request");

			return (commonValidationResponse);
		}

		if (null == txnHeader.getRequesterLanguage() || txnHeader.getRequesterLanguage().isEmpty()) {
			commonValidationResponse.setValidationResult(false);
			commonValidationResponse.setErrorCode("104");// requesterLanguage is
															// Needed in the
															// request txnHeader
			commonValidationResponse.setErrorMessage(getErrorMessageFromErrorCode(txnTransferObj, "104"));
			commonValidationResponse.setAdditionalErrorMessage("requesterLanguage is Needed  in the request txnHeader");

			return (commonValidationResponse);
		}

		if (null == txnHeader.getUserName() || txnHeader.getUserName().isEmpty()) {
			commonValidationResponse.setValidationResult(false);
			commonValidationResponse.setErrorCode("105");// userName is Needed
															// in the request
															// txnHeader
			commonValidationResponse.setErrorMessage(getErrorMessageFromErrorCode(txnTransferObj, "105"));
			commonValidationResponse.setAdditionalErrorMessage("userName is Needed in the request txnHeader");
			return (commonValidationResponse);
		}

		// if (null == txnHeader.getUserRole() ||
		// txnHeader.getUserRole().isEmpty()) {
		// commonValidationResponse.setValidationResult(false);
		// commonValidationResponse.setErrorCode("106"); // userRole is Needed
		// // in the request
		// // txnHeader
		// commonValidationResponse.setErrorMessage(getErrorMessageFromErrorCode(txnTransferObj,
		// "106"));
		// commonValidationResponse.setAdditionalErrorMessage("userRole is
		// Needed in the request txnHeader");
		// return (commonValidationResponse);
		// }

		if (null == txnHeader.getTransactionServiceName() || txnHeader.getTransactionServiceName().isEmpty()) {
			commonValidationResponse.setValidationResult(false);
			commonValidationResponse.setErrorCode("107"); // transactionServiceName
															// is Needed in the
															// request txnHeader
			commonValidationResponse.setErrorMessage(getErrorMessageFromErrorCode(txnTransferObj, "107"));
			commonValidationResponse
					.setAdditionalErrorMessage("transactionServiceName is Needed in the request txnHeader");
			return (commonValidationResponse);
		}

		if (null == txnHeader.getTxnMessageId() || txnHeader.getTxnMessageId().isEmpty()) {
			YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
			txnHeader.setTxnMessageId(yugandharKeygenerator.generateKey());
			logger.debug(
					"Header Validation - txnMessageId not present in the request so generated new for the transaction "
							+ txnHeader.getTxnMessageId());

		}

		commonValidationResponse.setValidationResult(true);
		return commonValidationResponse;

	}

	public void validateHeaderForInternalTxn(TxnTransferObj txnTransferObj) {
		TxnHeader txnHeader = txnTransferObj.getTxnHeader();

		if (null == txnHeader) {
			throw populateValidationErrorResponse(txnTransferObj, "107", "txnHeader object is needed in the request");

		}

		if (null == txnHeader.getRequesterLanguage() || txnHeader.getRequesterLanguage().isEmpty()) {
			throw populateValidationErrorResponse(txnTransferObj, "105",
					"requesterLanguage is Needed  in the request txnHeader");
		}

		if (null == txnHeader.getUserName() || txnHeader.getUserName().isEmpty()) {
			throw populateValidationErrorResponse(txnTransferObj, "105", "userName is Needed in the request txnHeader");
		}

		// if (null == txnHeader.getUserRole() ||
		// txnHeader.getUserRole().isEmpty()) {
		// throw populateValidationErrorResponse(txnTransferObj, "106",
		// "userRole is Needed in the request txnHeader");
		// }

		if (null == txnHeader.getTransactionServiceName() || txnHeader.getTransactionServiceName().isEmpty()) {
			throw populateValidationErrorResponse(txnTransferObj, "107",
					"transactionServiceName is Needed in the request txnHeader");
		}

		if (null == txnHeader.getTxnMessageId() || txnHeader.getTxnMessageId().isEmpty()) {
			YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
			txnHeader.setTxnMessageId(yugandharKeygenerator.generateKey());
			logger.debug(
					"Header Validation - txnMessageId not present in the request so generated new for the transaction "
							+ txnHeader.getTxnMessageId());

		}

	}

	public YugandharCommonException populateErrorResponse(TxnTransferObj txnTransferObj, String errorCode, Exception e,
			String additionalErrorMessage) {
		// write the logic to get error message based on error code.
		YugandharCommonException yugandharCommonException = new YugandharCommonException();
		yugandharCommonException.setResponseCode(yugandharConstants.RESPONSE_CODE_FAIL);
		yugandharCommonException.setErrorCode(errorCode);
		yugandharCommonException.setErrorMessage(getErrorMessageFromErrorCode(txnTransferObj, errorCode));
		yugandharCommonException.setAdditionalErrorMessage(additionalErrorMessage);
		yugandharCommonException.setStackErrorMessage(e.getMessage());
		return yugandharCommonException;
	}

	public String getErrorMessageFromErrorCode(TxnTransferObj txnTransferObj, String errorCode) {
		try {

			TxnTransferObj theTxnTransferObj = new TxnTransferObj();
			theTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			ConfigErrorcodeRegistryDO theConfigErrorcodeRegistryDO = new ConfigErrorcodeRegistryDO();
			theConfigErrorcodeRegistryDO.setErrorCode(errorCode);
			theConfigErrorcodeRegistryDO
					.setConfigLanguageCodeKey(theTxnTransferObj.getTxnHeader().getRequesterLanguage());
			theTxnTransferObj.getTxnPayload().setConfigErrorcodeRegistryDO(theConfigErrorcodeRegistryDO);
			TxnTransferObj respTxnTransferObj = theConfigErrorcodeRegistryComponent
					.findByBusinessKey(theTxnTransferObj);
			return respTxnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getErrorMessage();
		} catch (RuntimeException re) {
			logger.error("getErrorMessageFromErrorCode", re);
			re.printStackTrace();
			return "Failed to retrieve error message for given error code";
		}

	}

	public YugandharCommonException populateValidationErrorResponse(TxnTransferObj txnTransferObj, String errorCode,
			String additionalErrorMessage) {
		// write the logic to get error message based on error code.
		YugandharCommonException yugandharCommonException = new YugandharCommonException();
		yugandharCommonException.setResponseCode(yugandharConstants.RESPONSE_CODE_FAIL);
		yugandharCommonException.setErrorCode(errorCode);
		yugandharCommonException.setErrorMessage(getErrorMessageFromErrorCode(txnTransferObj, errorCode));
		yugandharCommonException.setAdditionalErrorMessage(additionalErrorMessage);
		return yugandharCommonException;

	}

	public CommonValidationResponse createCommonValidationResponseFromException(TxnTransferObj txnErrTransferObj,
			String errorCode, Exception e) {
		CommonValidationResponse commonValidationResponse = new CommonValidationResponse();
		commonValidationResponse.setErrorCode(errorCode);
		commonValidationResponse.setErrorMessage(getErrorMessageFromErrorCode(txnErrTransferObj, errorCode));
		commonValidationResponse.setStackErrorMessage(e.getMessage());
		commonValidationResponse.setAdditionalErrorMessage(e.toString());
		return commonValidationResponse;
	}

	public CommonValidationResponse createCommonValidationResponseFromYugException(TxnTransferObj txnErrTransferObj,
			YugandharCommonException yce) {
		CommonValidationResponse commonValidationResponse = new CommonValidationResponse();
		commonValidationResponse.setErrorCode(yce.getErrorCode());
		commonValidationResponse.setErrorMessage(yce.getErrorMessage());
		commonValidationResponse.setServiceComponent(yce.getServiceComponent());
		commonValidationResponse.setStackErrorMessage(yce.getStackErrorMessage());
		commonValidationResponse.setAdditionalErrorMessage(yce.getAdditionalErrorMessage());
		return commonValidationResponse;
	}

	public void validateFilterValue(TxnTransferObj txnTransferObj, String filter) {

		ArrayList<String> strList = new ArrayList<String>();
		strList.add("ACTIVE");
		strList.add("INACTIVE");
		strList.add("ALL");

		if (!strList.contains(filter.toUpperCase())) {
			throw populateValidationErrorResponse(txnTransferObj, "1008", yugandharConstants.ERROR_FILTER_VALIDATION);
		}

	}

	public YugandharCommonException createErrorResponse(TxnTransferObj txnTransferObj, String errorCode,
			String additionalErrorMessage) {
		// TODO Auto-generated method stub
		YugandharCommonException yugandharCommonException = new YugandharCommonException();
		yugandharCommonException.setResponseCode(yugandharConstants.RESPONSE_CODE_FAIL);
		yugandharCommonException.setErrorCode(errorCode);
		yugandharCommonException.setErrorMessage(getErrorMessageFromErrorCode(txnTransferObj, errorCode));
		yugandharCommonException.setAdditionalErrorMessage(additionalErrorMessage);
		return yugandharCommonException;
	}

	public void copyPaginationProperties(TxnPayload fromTxnPayload, TxnPayload ToTxnPayload) {
		ToTxnPayload.setPaginationIndexOfCurrentSlice(fromTxnPayload.getPaginationIndexOfCurrentSlice());
		ToTxnPayload.setPaginationInfoElementsOnCurrentSlice(fromTxnPayload.getPaginationInfoElementsOnCurrentSlice());
		ToTxnPayload.setPaginationInfoTotalElements(fromTxnPayload.getPaginationInfoTotalElements());
		ToTxnPayload.setPaginationInfoTotalPages(fromTxnPayload.getPaginationInfoTotalPages());
		ToTxnPayload.setPaginationPageSize(fromTxnPayload.getPaginationPageSize());

	}

	public boolean checkIfUserOrRoleIsAuthorizedForRequestedTxn(TxnTransferObj txnTransferObj) {

		if (!isNullOrEmpty(txnTransferObj.getTxnHeader().getUserName())
				&& !isNullOrEmpty(txnTransferObj.getTxnHeader().getUserRole())) {
			
			TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
			TxnPayload transitTxnPayload = new TxnPayload();
			// Clone TxnHeader
			TxnHeader theTxnHeader = new TxnHeader(txnTransferObj.getTxnHeader());
			transitTxnTransferObj.setTxnHeader(theTxnHeader);

			SearchAuthAccessControlDO theSearchAuthAccessControlDO = new SearchAuthAccessControlDO();
			theSearchAuthAccessControlDO.setUserName(txnTransferObj.getTxnHeader().getUserName());
			theSearchAuthAccessControlDO.setRoleName(txnTransferObj.getTxnHeader().getUserRole());
			theSearchAuthAccessControlDO.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
			
			transitTxnPayload.setSearchAuthAccessControlDO(theSearchAuthAccessControlDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			
			//Check if userName and roleName combination is valid
			transitTxnTransferObj = theSearchAuthRolesService.process(transitTxnTransferObj);
			
			List<AuthRolesRegistryDO> theAuthRolesRegistryDOList = transitTxnTransferObj.getTxnPayload()
					.getAuthRolesRegistryDOList();

			
			//if user is part of the given role then check if that role have access to the transaction
			if (null != theAuthRolesRegistryDOList && theAuthRolesRegistryDOList.size() > 0) {


				transitTxnTransferObj = new TxnTransferObj();
				transitTxnPayload = new TxnPayload();
				// Clone TxnHeader
				theTxnHeader = new TxnHeader(txnTransferObj.getTxnHeader());
				transitTxnTransferObj.setTxnHeader(theTxnHeader);

				theSearchAuthAccessControlDO = new SearchAuthAccessControlDO();

				// set the values as recieved from request
				theSearchAuthAccessControlDO.setRoleName(txnTransferObj.getTxnHeader().getUserRole());
				theSearchAuthAccessControlDO.setTxnserviceName(txnTransferObj.getTxnHeader().getTransactionServiceName());
				theSearchAuthAccessControlDO.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);

				transitTxnPayload.setSearchAuthAccessControlDO(theSearchAuthAccessControlDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);

				transitTxnTransferObj = theSearchAuthAccessControlService.process(transitTxnTransferObj);
				List<AuthUserRegistryDO> theAuthUserRegistryDOList = transitTxnTransferObj.getTxnPayload()
						.getAuthUserRegistryDOList();

				if (null != theAuthUserRegistryDOList && theAuthUserRegistryDOList.size() > 0) {
					return true;
				} else {
					return false;
				}
			
			} else {
				YugandharCommonException yce = populateErrorResponse(txnTransferObj, "10084", new Exception(),
						"txnHeader.userName:'" + txnTransferObj.getTxnHeader().getUserName() +"' Provided in the request"
								+ " is not part of role given in roleName: '"+ txnTransferObj.getTxnHeader().getUserRole() +"'");
				throw yce;
			}
			
			
		} else {

			TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
			TxnPayload transitTxnPayload = new TxnPayload();
			// Clone TxnHeader
			TxnHeader theTxnHeader = new TxnHeader(txnTransferObj.getTxnHeader());
			transitTxnTransferObj.setTxnHeader(theTxnHeader);

			SearchAuthAccessControlDO theSearchAuthAccessControlDO = new SearchAuthAccessControlDO();

			// set the values as recieved from request
			theSearchAuthAccessControlDO.setUserName(txnTransferObj.getTxnHeader().getUserName());
			//theSearchAuthAccessControlDO.setRoleName(txnTransferObj.getTxnHeader().getUserRole());
			theSearchAuthAccessControlDO.setTxnserviceName(txnTransferObj.getTxnHeader().getTransactionServiceName());
			theSearchAuthAccessControlDO.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);

			transitTxnPayload.setSearchAuthAccessControlDO(theSearchAuthAccessControlDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);

			transitTxnTransferObj = theSearchAuthAccessControlService.process(transitTxnTransferObj);
			List<AuthUserRegistryDO> theAuthUserRegistryDOList = transitTxnTransferObj.getTxnPayload()
					.getAuthUserRegistryDOList();

			if (null != theAuthUserRegistryDOList && theAuthUserRegistryDOList.size() > 0) {
				return true;
			} else {
				return false;
			}
		}

	}

	public boolean isNullOrEmpty(String strToCheck) {

		if (null == strToCheck || strToCheck.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

}
