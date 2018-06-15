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
import com.yugandhar.mdm.extern.dobj.MiscellaneousInfoDO;
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;

@Scope(value = "prototype")
@Component
public class MiscellaneousInfoComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for MiscellaneousInfoComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateMiscellaneousInfoCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for MiscellaneousInfoComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateMiscellaneousInfoCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for MiscellaneousInfoComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateMiscellaneousInfoCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in MiscellaneousInfoComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteMiscellaneousInfoCompPersist(MiscellaneousInfoDO reqMiscellaneousInfoDO,
			TxnTransferObj txnTransferObj) {
		// EntityObjectTypeRefkey
		if (!(null == reqMiscellaneousInfoDO.getEntityObjectTypeRefkey()
				|| reqMiscellaneousInfoDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqMiscellaneousInfoDO.getEntityObjectTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {

				if (null == reqMiscellaneousInfoDO.getEntityObjectTypeRefValue()) {
					reqMiscellaneousInfoDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
				} else if (!(reqMiscellaneousInfoDO.getEntityObjectTypeRefValue()
						.equals(theRefEntityObjectTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11056",
							"Validation error : Recieved " + reqMiscellaneousInfoDO.getEntityObjectTypeRefkey() + "-"
									+ reqMiscellaneousInfoDO.getEntityObjectTypeRefValue()
									+ " as EntityObjectTypeRefkey- EntityObjectTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11056",
						"Validation error : Recieved " + reqMiscellaneousInfoDO.getEntityObjectTypeRefkey()
								+ " as EntityObjectTypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in MiscellaneousInfoComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteMiscellaneousInfoCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in MiscellaneousInfoComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteMiscellaneousInfoCompMerge(MiscellaneousInfoDO reqMiscellaneousInfoDO,
			MiscellaneousInfoDO dbimageMiscellaneousInfoDO, TxnTransferObj txnTransferObj) {

		if (dbimageMiscellaneousInfoDO.getEntityObjectTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10038",
					"Recieved empty string for MiscellaneousInfoDO.entityObjectTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageMiscellaneousInfoDO.getEntityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10039",
					"Recieved empty string for MiscellaneousInfoDO.entityIdpk, this attribute cannot be updated to null");
		}

		if (dbimageMiscellaneousInfoDO.getName1().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10040",
					"Recieved empty string for MiscellaneousInfoDO.name1, this attribute cannot be updated to null");
		}
		if (dbimageMiscellaneousInfoDO.getValue1().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10041",
					"Recieved empty string for MiscellaneousInfoDO.value1, this attribute cannot be updated to null");
		}

		// EntityObjectTypeRefkey
		if (!(null == dbimageMiscellaneousInfoDO.getEntityObjectTypeRefkey()
				|| dbimageMiscellaneousInfoDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageMiscellaneousInfoDO.getEntityObjectTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {

				if (null == dbimageMiscellaneousInfoDO.getEntityObjectTypeRefValue()) {
					dbimageMiscellaneousInfoDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
				} else if (!(dbimageMiscellaneousInfoDO.getEntityObjectTypeRefValue()
						.equals(theRefEntityObjectTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11056",
							"Validation error : Recieved " + dbimageMiscellaneousInfoDO.getEntityObjectTypeRefkey()
									+ "-" + dbimageMiscellaneousInfoDO.getEntityObjectTypeRefValue()
									+ " as EntityObjectTypeRefkey- EntityObjectTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11056",
						"Validation error : Recieved " + dbimageMiscellaneousInfoDO.getEntityObjectTypeRefkey()
								+ " as EntityObjectTypeRefkey in request which failed validation");
			}
		}
	}

	/**
	 * Pre execute rule for merge in MiscellaneousInfoComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteMiscellaneousInfoCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in MiscellaneousInfoComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteMiscellaneousInfoCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateMiscellaneousInfoCompFindByEntityIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteMiscellaneousInfoCompFindByEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
