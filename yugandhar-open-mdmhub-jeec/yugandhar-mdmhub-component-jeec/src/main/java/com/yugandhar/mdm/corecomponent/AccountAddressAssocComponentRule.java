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
import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.RefAddressSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefAddressTypeDO;

@Scope(value = "prototype")
@Component
public class AccountAddressAssocComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for AccountAddressAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateAccountAddressAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for AccountAddressAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateAccountAddressAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for AccountAddressAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateAccountAddressAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in AccountAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteAccountAddressAssocCompPersist(AccountAddressAssocDO reqAccountAddressAssocDO,
			TxnTransferObj txnTransferObj) {

		// AddressTypeRefkey
		if (!(null == reqAccountAddressAssocDO.getAddressTypeRefkey()
				|| reqAccountAddressAssocDO.getAddressTypeRefkey().isEmpty())) {
			RefAddressTypeDO theRefAddressTypeDO = referenceTableHelper.getRefAddressTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqAccountAddressAssocDO.getAddressTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressTypeDO) {

				if (null == reqAccountAddressAssocDO.getAddressTypeRefValue()) {
					reqAccountAddressAssocDO.setAddressTypeRefValue(theRefAddressTypeDO.getValue());
				} else if (!(reqAccountAddressAssocDO.getAddressTypeRefValue()
						.equals(theRefAddressTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11001",
							"Validation error : Recieved " + reqAccountAddressAssocDO.getAddressTypeRefkey() + "-"
									+ reqAccountAddressAssocDO.getAddressTypeRefValue()
									+ " as AddressTypeRefkey- AddressTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11001",
						"Validation error : Recieved " + reqAccountAddressAssocDO.getAddressTypeRefkey()
								+ " as AddressTypeRefkey in request which failed validation");
			}
		}

		// AddressSubtypeRefkey
		if (!(null == reqAccountAddressAssocDO.getAddressSubtypeRefkey()
				|| reqAccountAddressAssocDO.getAddressSubtypeRefkey().isEmpty())) {
			RefAddressSubtypeDO theRefAddressSubtypeDO = referenceTableHelper.getRefAddressSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqAccountAddressAssocDO.getAddressSubtypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressSubtypeDO) {

				if (null == reqAccountAddressAssocDO.getAddressSubtypeRefValue()) {
					reqAccountAddressAssocDO.setAddressSubtypeRefValue(theRefAddressSubtypeDO.getValue());
				} else if (!(reqAccountAddressAssocDO.getAddressSubtypeRefValue()
						.equals(theRefAddressSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11002",
							"Validation error : Recieved " + reqAccountAddressAssocDO.getAddressSubtypeRefkey() + "-"
									+ reqAccountAddressAssocDO.getAddressSubtypeRefValue()
									+ " as AddressSubtypeRefkey- AddressSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11002",
						"Validation error : Recieved " + reqAccountAddressAssocDO.getAddressSubtypeRefkey()
								+ " as AddressSubtypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in AccountAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteAccountAddressAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in AccountAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteAccountAddressAssocCompMerge(AccountAddressAssocDO reqAccountAddressAssocDO,
			AccountAddressAssocDO dbimageAccountAddressAssocDO, TxnTransferObj txnTransferObj) {

		if (dbimageAccountAddressAssocDO.getAccountIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10014",
					"Recieved empty string for AccountAddressAssocDO.LegalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageAccountAddressAssocDO.getAddressIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10017",
					"Recieved empty string for AccountAddressAssocDO.addressIdpk, this attribute cannot be updated to null");
		}

		if (dbimageAccountAddressAssocDO.getAddressTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10008",
					"Recieved empty string for AccountAddressAssocDO.addressTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageAccountAddressAssocDO.getAddressSubtypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10050",
					"Recieved empty string for AccountAddressAssocDO.addressSubtypeRefkey, this attribute cannot be updated to null");
		}

		// AddressTypeRefkey
		if (!(null == dbimageAccountAddressAssocDO.getAddressTypeRefkey()
				|| dbimageAccountAddressAssocDO.getAddressTypeRefkey().isEmpty())) {
			RefAddressTypeDO theRefAddressTypeDO = referenceTableHelper.getRefAddressTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageAccountAddressAssocDO.getAddressTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressTypeDO) {

				if (null == dbimageAccountAddressAssocDO.getAddressTypeRefValue()) {
					dbimageAccountAddressAssocDO.setAddressTypeRefValue(theRefAddressTypeDO.getValue());
				} else if (!(dbimageAccountAddressAssocDO.getAddressTypeRefValue()
						.equals(theRefAddressTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11001",
							"Validation error : Recieved " + dbimageAccountAddressAssocDO.getAddressTypeRefkey() + "-"
									+ dbimageAccountAddressAssocDO.getAddressTypeRefValue()
									+ " as AddressTypeRefkey- AddressTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11001",
						"Validation error : Recieved " + dbimageAccountAddressAssocDO.getAddressTypeRefkey()
								+ " as AddressTypeRefkey in request which failed validation");
			}
		}

		// AddressSubtypeRefkey
		if (!(null == dbimageAccountAddressAssocDO.getAddressSubtypeRefkey()
				|| dbimageAccountAddressAssocDO.getAddressSubtypeRefkey().isEmpty())) {
			RefAddressSubtypeDO theRefAddressSubtypeDO = referenceTableHelper.getRefAddressSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageAccountAddressAssocDO.getAddressSubtypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressSubtypeDO) {

				if (null == dbimageAccountAddressAssocDO.getAddressSubtypeRefValue()) {
					dbimageAccountAddressAssocDO.setAddressSubtypeRefValue(theRefAddressSubtypeDO.getValue());
				} else if (!(dbimageAccountAddressAssocDO.getAddressSubtypeRefValue()
						.equals(theRefAddressSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11002",
							"Validation error : Recieved " + dbimageAccountAddressAssocDO.getAddressSubtypeRefkey()
									+ "-" + dbimageAccountAddressAssocDO.getAddressSubtypeRefValue()
									+ " as AddressSubtypeRefkey- AddressSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11002",
						"Validation error : Recieved " + dbimageAccountAddressAssocDO.getAddressSubtypeRefkey()
								+ " as AddressSubtypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for merge in AccountAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteAccountAddressAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in AccountAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteAccountAddressAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateAccountAddressAssocCompFindByAccountIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteAccountAddressAssocCompFindByAccountIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
