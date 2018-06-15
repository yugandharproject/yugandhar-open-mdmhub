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
import com.yugandhar.mdm.extern.dobj.LePreferencesDO;
import com.yugandhar.mdm.extern.dobj.RefPreferenceTypeDO;

@Scope(value = "prototype")
@Component
public class LePreferencesComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for LePreferencesComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLePreferencesCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LePreferencesComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLePreferencesCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LePreferencesComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLePreferencesCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in LePreferencesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLePreferencesCompPersist(LePreferencesDO reqLePreferencesDO, TxnTransferObj txnTransferObj) {
		// PreferenceTypeRefkey
		if (!(null == reqLePreferencesDO.getPreferenceTypeRefkey()
				|| reqLePreferencesDO.getPreferenceTypeRefkey().isEmpty())) {
			RefPreferenceTypeDO theRefPreferenceTypeDO = referenceTableHelper.getRefPreferenceTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLePreferencesDO.getPreferenceTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPreferenceTypeDO) {

				if (null == reqLePreferencesDO.getPreferenceTypeRefValue()) {
					reqLePreferencesDO.setPreferenceTypeRefValue(theRefPreferenceTypeDO.getValue());
				} else if (!(reqLePreferencesDO.getPreferenceTypeRefValue()
						.equals(theRefPreferenceTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11047",
							"Validation error : Recieved " + reqLePreferencesDO.getPreferenceTypeRefkey() + "-"
									+ reqLePreferencesDO.getPreferenceTypeRefValue()
									+ " as PreferenceTypeRefkey- PreferenceTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11047",
						"Validation error : Recieved " + reqLePreferencesDO.getPreferenceTypeRefkey()
								+ " as PreferenceTypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in LePreferencesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLePreferencesCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LePreferencesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLePreferencesCompMerge(LePreferencesDO reqLePreferencesDO,
			LePreferencesDO dbimageLePreferencesDO, TxnTransferObj txnTransferObj) {

		if (dbimageLePreferencesDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"Recieved empty string for LePreferencesDO.legalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageLePreferencesDO.getPreferenceTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10038",
					"Recieved empty string for LePreferencesDO.PreferenceTypeRefkey, this attribute cannot be updated to null");
		}

		// PreferenceTypeRefkey
		if (!(null == dbimageLePreferencesDO.getPreferenceTypeRefkey()
				|| dbimageLePreferencesDO.getPreferenceTypeRefkey().isEmpty())) {
			RefPreferenceTypeDO theRefPreferenceTypeDO = referenceTableHelper.getRefPreferenceTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLePreferencesDO.getPreferenceTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPreferenceTypeDO) {

				if (null == dbimageLePreferencesDO.getPreferenceTypeRefValue()) {
					dbimageLePreferencesDO.setPreferenceTypeRefValue(theRefPreferenceTypeDO.getValue());
				} else if (!(dbimageLePreferencesDO.getPreferenceTypeRefValue()
						.equals(theRefPreferenceTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11047",
							"Validation error : Recieved " + dbimageLePreferencesDO.getPreferenceTypeRefkey() + "-"
									+ dbimageLePreferencesDO.getPreferenceTypeRefValue()
									+ " as PreferenceTypeRefkey- PreferenceTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11047",
						"Validation error : Recieved " + dbimageLePreferencesDO.getPreferenceTypeRefkey()
								+ " as PreferenceTypeRefkey in request which failed validation");
			}
		}
	}

	/**
	 * Pre execute rule for merge in LePreferencesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLePreferencesCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LePreferencesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLePreferencesCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateLePreferencesCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteLePreferencesCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
