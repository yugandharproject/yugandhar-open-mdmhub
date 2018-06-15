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
import com.yugandhar.mdm.extern.dobj.AccountPhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneTypeDO;

@Scope(value = "prototype")
@Component
public class AccountPhoneAssocComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;
	
	/**
	 * Pre execute persist validation method for AccountPhoneAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateAccountPhoneAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for AccountPhoneAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateAccountPhoneAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for AccountPhoneAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateAccountPhoneAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in AccountPhoneAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteAccountPhoneAssocCompPersist(AccountPhoneAssocDO reqAccountPhoneAssocDO,
			TxnTransferObj txnTransferObj) {

		// PhoneTypeRefkey
		if (!(null == reqAccountPhoneAssocDO.getPhoneTypeRefkey()
				|| reqAccountPhoneAssocDO.getPhoneTypeRefkey().isEmpty())) {
			RefPhoneTypeDO theRefPhoneTypeDO = referenceTableHelper.getRefPhoneTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAccountPhoneAssocDO.getPhoneTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneTypeDO) {

				if (null == reqAccountPhoneAssocDO.getPhoneTypeRefValue()) {
					reqAccountPhoneAssocDO.setPhoneTypeRefValue(theRefPhoneTypeDO.getValue());
				} else if (!(reqAccountPhoneAssocDO.getPhoneTypeRefValue().equals(theRefPhoneTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11012",
							"Validation error : Recieved " + reqAccountPhoneAssocDO.getPhoneTypeRefkey() + "-"
									+ reqAccountPhoneAssocDO.getPhoneTypeRefValue()
									+ " as PhoneTypeRefkey- PhoneTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11012",
						"Validation error : Recieved " + reqAccountPhoneAssocDO.getPhoneTypeRefkey()
								+ " as PhoneTypeRefkey in request which failed validation");
			}
		}

		// PhoneSubtypeRefkey
		if (!(null == reqAccountPhoneAssocDO.getPhoneSubtypeRefkey()
				|| reqAccountPhoneAssocDO.getPhoneSubtypeRefkey().isEmpty())) {
			RefPhoneSubtypeDO theRefPhoneSubtypeDO = referenceTableHelper.getRefPhoneSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqAccountPhoneAssocDO.getPhoneSubtypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneSubtypeDO) {

				if (null == reqAccountPhoneAssocDO.getPhoneSubtypeRefValue()) {
					reqAccountPhoneAssocDO.setPhoneSubtypeRefValue(theRefPhoneSubtypeDO.getValue());
				} else if (!(reqAccountPhoneAssocDO.getPhoneSubtypeRefValue()
						.equals(theRefPhoneSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11013",
							"Validation error : Recieved " + reqAccountPhoneAssocDO.getPhoneSubtypeRefkey() + "-"
									+ reqAccountPhoneAssocDO.getPhoneSubtypeRefValue()
									+ " as PhoneSubtypeRefkey- PhoneSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11013",
						"Validation error : Recieved " + reqAccountPhoneAssocDO.getPhoneSubtypeRefkey()
								+ " as PhoneSubtypeRefkey in request which failed validation");
			}
		}
	}

	/**
	 * Pre execute rule for persist in AccountPhoneAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteAccountPhoneAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in AccountPhoneAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteAccountPhoneAssocCompMerge(AccountPhoneAssocDO reqAccountPhoneAssocDO,
			AccountPhoneAssocDO dbimageAccountPhoneAssocDO, TxnTransferObj txnTransferObj) {

		if (dbimageAccountPhoneAssocDO.getAccountIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10014",
					"Recieved empty string for LePhoneAssocDO.accountIdpk, this attribute cannot be updated to null");
		}

		if (dbimageAccountPhoneAssocDO.getPhoneTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10010",
					"Recieved empty string for LePhoneAssocDO.phoneTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageAccountPhoneAssocDO.getPhoneNumber().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10011",
					"Recieved empty string for LePhoneAssocDO.phoneNumber, this attribute cannot be updated to null");
		}

		// PhoneTypeRefkey
		if (!(null == dbimageAccountPhoneAssocDO.getPhoneTypeRefkey()
				|| dbimageAccountPhoneAssocDO.getPhoneTypeRefkey().isEmpty())) {
			RefPhoneTypeDO theRefPhoneTypeDO = referenceTableHelper.getRefPhoneTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageAccountPhoneAssocDO.getPhoneTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneTypeDO) {

				if (null == dbimageAccountPhoneAssocDO.getPhoneTypeRefValue()) {
					dbimageAccountPhoneAssocDO.setPhoneTypeRefValue(theRefPhoneTypeDO.getValue());
				} else if (!(dbimageAccountPhoneAssocDO.getPhoneTypeRefValue().equals(theRefPhoneTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11012",
							"Validation error : Recieved " + dbimageAccountPhoneAssocDO.getPhoneTypeRefkey() + "-"
									+ dbimageAccountPhoneAssocDO.getPhoneTypeRefValue()
									+ " as PhoneTypeRefkey- PhoneTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11012",
						"Validation error : Recieved " + dbimageAccountPhoneAssocDO.getPhoneTypeRefkey()
								+ " as PhoneTypeRefkey in request which failed validation");
			}
		}

		// PhoneSubtypeRefkey
		if (!(null == dbimageAccountPhoneAssocDO.getPhoneSubtypeRefkey()
				|| dbimageAccountPhoneAssocDO.getPhoneSubtypeRefkey().isEmpty())) {
			RefPhoneSubtypeDO theRefPhoneSubtypeDO = referenceTableHelper.getRefPhoneSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageAccountPhoneAssocDO.getPhoneSubtypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneSubtypeDO) {

				if (null == dbimageAccountPhoneAssocDO.getPhoneSubtypeRefValue()) {
					dbimageAccountPhoneAssocDO.setPhoneSubtypeRefValue(theRefPhoneSubtypeDO.getValue());
				} else if (!(dbimageAccountPhoneAssocDO.getPhoneSubtypeRefValue()
						.equals(theRefPhoneSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11013",
							"Validation error : Recieved " + dbimageAccountPhoneAssocDO.getPhoneSubtypeRefkey() + "-"
									+ dbimageAccountPhoneAssocDO.getPhoneSubtypeRefValue()
									+ " as PhoneSubtypeRefkey- PhoneSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11013",
						"Validation error : Recieved " + dbimageAccountPhoneAssocDO.getPhoneSubtypeRefkey()
								+ " as PhoneSubtypeRefkey in request which failed validation");
			}
		}
	}

	/**
	 * Pre execute rule for merge in AccountPhoneAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteAccountPhoneAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in AccountPhoneAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteAccountPhoneAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateAccountPhoneAssocCompFindByAccountIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteAccountPhoneAssocCompFindByAccountIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
