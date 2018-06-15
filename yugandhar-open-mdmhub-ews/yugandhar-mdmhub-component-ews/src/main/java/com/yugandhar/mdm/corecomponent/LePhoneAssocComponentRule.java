package com.yugandhar.mdm.corecomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.LePhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneTypeDO;

@Scope(value = "prototype")
@Component
public class LePhoneAssocComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for LePhoneAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLePhoneAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LePhoneAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLePhoneAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LePhoneAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLePhoneAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in LePhoneAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLePhoneAssocCompPersist(LePhoneAssocDO reqLePhoneAssocDO, TxnTransferObj txnTransferObj) {

		// PhoneTypeRefkey
		if (!(null == reqLePhoneAssocDO.getPhoneTypeRefkey() || reqLePhoneAssocDO.getPhoneTypeRefkey().isEmpty())) {
			RefPhoneTypeDO theRefPhoneTypeDO = referenceTableHelper.getRefPhoneTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLePhoneAssocDO.getPhoneTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneTypeDO) {

				if (null == reqLePhoneAssocDO.getPhoneTypeRefValue()) {
					reqLePhoneAssocDO.setPhoneTypeRefValue(theRefPhoneTypeDO.getValue());
				} else if (!(reqLePhoneAssocDO.getPhoneTypeRefValue().equals(theRefPhoneTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11045",
							"Validation error : Recieved " + reqLePhoneAssocDO.getPhoneTypeRefkey() + "-"
									+ reqLePhoneAssocDO.getPhoneTypeRefValue()
									+ " as PhoneTypeRefkey- PhoneTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11045",
						"Validation error : Recieved " + reqLePhoneAssocDO.getPhoneTypeRefkey()
								+ " as PhoneTypeRefkey in request which failed validation");
			}
		}

		// PhoneSubtypeRefkey
		if (!(null == reqLePhoneAssocDO.getPhoneSubtypeRefkey()
				|| reqLePhoneAssocDO.getPhoneSubtypeRefkey().isEmpty())) {
			RefPhoneSubtypeDO theRefPhoneSubtypeDO = referenceTableHelper.getRefPhoneSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLePhoneAssocDO.getPhoneSubtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneSubtypeDO) {

				if (null == reqLePhoneAssocDO.getPhoneSubtypeRefValue()) {
					reqLePhoneAssocDO.setPhoneSubtypeRefValue(theRefPhoneSubtypeDO.getValue());
				} else if (!(reqLePhoneAssocDO.getPhoneSubtypeRefValue().equals(theRefPhoneSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11046",
							"Validation error : Recieved " + reqLePhoneAssocDO.getPhoneSubtypeRefkey() + "-"
									+ reqLePhoneAssocDO.getPhoneSubtypeRefValue()
									+ " as PhoneSubtypeRefkey- PhoneSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11046",
						"Validation error : Recieved " + reqLePhoneAssocDO.getPhoneSubtypeRefkey()
								+ " as PhoneSubtypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in LePhoneAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLePhoneAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LePhoneAssocComp This method is modularized
	 * in respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLePhoneAssocCompMerge(LePhoneAssocDO reqLePhoneAssocDO, LePhoneAssocDO dbimageLePhoneAssocDO,
			TxnTransferObj txnTransferObj) {

		if (dbimageLePhoneAssocDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"Recieved empty string for LePhoneAssocDO.LegalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageLePhoneAssocDO.getPhoneTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10010",
					"Recieved empty string for LePhoneAssocDO.phoneTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageLePhoneAssocDO.getPhoneNumber().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10011",
					"Recieved empty string for LePhoneAssocDO.phoneNumber, this attribute cannot be updated to null");
		}

		// PhoneTypeRefkey
		if (!(null == dbimageLePhoneAssocDO.getPhoneTypeRefkey()
				|| dbimageLePhoneAssocDO.getPhoneTypeRefkey().isEmpty())) {
			RefPhoneTypeDO theRefPhoneTypeDO = referenceTableHelper.getRefPhoneTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLePhoneAssocDO.getPhoneTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneTypeDO) {

				if (null == dbimageLePhoneAssocDO.getPhoneTypeRefValue()) {
					dbimageLePhoneAssocDO.setPhoneTypeRefValue(theRefPhoneTypeDO.getValue());
				} else if (!(dbimageLePhoneAssocDO.getPhoneTypeRefValue().equals(theRefPhoneTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11045",
							"Validation error : Recieved " + dbimageLePhoneAssocDO.getPhoneTypeRefkey() + "-"
									+ dbimageLePhoneAssocDO.getPhoneTypeRefValue()
									+ " as PhoneTypeRefkey- PhoneTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11045",
						"Validation error : Recieved " + dbimageLePhoneAssocDO.getPhoneTypeRefkey()
								+ " as PhoneTypeRefkey in request which failed validation");
			}
		}

		// PhoneSubtypeRefkey
		if (!(null == dbimageLePhoneAssocDO.getPhoneSubtypeRefkey()
				|| dbimageLePhoneAssocDO.getPhoneSubtypeRefkey().isEmpty())) {
			RefPhoneSubtypeDO theRefPhoneSubtypeDO = referenceTableHelper.getRefPhoneSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLePhoneAssocDO.getPhoneSubtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneSubtypeDO) {

				if (null == dbimageLePhoneAssocDO.getPhoneSubtypeRefValue()) {
					dbimageLePhoneAssocDO.setPhoneSubtypeRefValue(theRefPhoneSubtypeDO.getValue());
				} else if (!(dbimageLePhoneAssocDO.getPhoneSubtypeRefValue().equals(theRefPhoneSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11046",
							"Validation error : Recieved " + dbimageLePhoneAssocDO.getPhoneSubtypeRefkey() + "-"
									+ dbimageLePhoneAssocDO.getPhoneSubtypeRefValue()
									+ " as PhoneSubtypeRefkey- PhoneSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11046",
						"Validation error : Recieved " + dbimageLePhoneAssocDO.getPhoneSubtypeRefkey()
								+ " as PhoneSubtypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for merge in LePhoneAssocComp This method is modularized
	 * in respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLePhoneAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LePhoneAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLePhoneAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateLePhoneAssocCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		// set the current page index to zero (0) if page index is not provided
		// in the request or negative value is provided
		if (null == txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice()
				|| txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice() < 0) {
			txnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(0);
		}

		// set default page size as configured in application properties
		if (null == txnTransferObj.getTxnPayload().getPaginationPageSize()
				|| txnTransferObj.getTxnPayload().getPaginationPageSize() <= 0) {
			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_pagination_datatables_default_pagesize,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			txnTransferObj.getTxnPayload().setPaginationPageSize(Integer.valueOf(theConfigAppPropertiesDO.getValue()));

		}

	}

	public void postExecuteLePhoneAssocCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
