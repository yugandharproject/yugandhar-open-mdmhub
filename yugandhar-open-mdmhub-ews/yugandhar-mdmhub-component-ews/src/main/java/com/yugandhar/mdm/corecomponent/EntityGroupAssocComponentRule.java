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
import com.yugandhar.mdm.extern.dobj.EntityGroupAssocDO;
import com.yugandhar.mdm.extern.dobj.RefAssocTypeDO;
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;

@Scope(value = "prototype")
@Component
public class EntityGroupAssocComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for EntityGroupAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateEntityGroupAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for EntityGroupAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateEntityGroupAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for EntityGroupAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateEntityGroupAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in EntityGroupAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteEntityGroupAssocCompPersist(EntityGroupAssocDO reqEntityGroupAssocDO,
			TxnTransferObj txnTransferObj) {
		// EntityObjectTypeRefkey
		if (!(null == reqEntityGroupAssocDO.getEntityObjectTypeRefkey()
				|| reqEntityGroupAssocDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqEntityGroupAssocDO.getEntityObjectTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {

				if (null == reqEntityGroupAssocDO.getEntityObjectTypeRefValue()) {
					reqEntityGroupAssocDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
				} else if (!(reqEntityGroupAssocDO.getEntityObjectTypeRefValue()
						.equals(theRefEntityObjectTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11018",
							"Validation error : Recieved " + reqEntityGroupAssocDO.getEntityObjectTypeRefkey() + "-"
									+ reqEntityGroupAssocDO.getEntityObjectTypeRefValue()
									+ " as EntityObjectTypeRefkey- EntityObjectTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11018",
						"Validation error : Recieved " + reqEntityGroupAssocDO.getEntityObjectTypeRefkey()
								+ " as EntityObjectTypeRefkey in request which failed validation");
			}
		}

		// AssocTypeRefkey
		if (!(null == reqEntityGroupAssocDO.getAssocTypeRefkey()
				|| reqEntityGroupAssocDO.getAssocTypeRefkey().isEmpty())) {
			RefAssocTypeDO theRefAssocTypeDO = referenceTableHelper.getRefAssocTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqEntityGroupAssocDO.getAssocTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAssocTypeDO) {

				if (null == reqEntityGroupAssocDO.getAssocTypeRefValue()) {
					reqEntityGroupAssocDO.setAssocTypeRefValue(theRefAssocTypeDO.getValue());
				} else if (!(reqEntityGroupAssocDO.getAssocTypeRefValue().equals(theRefAssocTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11019",
							"Validation error : Recieved " + reqEntityGroupAssocDO.getAssocTypeRefkey() + "-"
									+ reqEntityGroupAssocDO.getAssocTypeRefValue()
									+ " as AssocTypeRefkey- AssocTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11019",
						"Validation error : Recieved " + reqEntityGroupAssocDO.getAssocTypeRefkey()
								+ " as AssocTypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in EntityGroupAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteEntityGroupAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in EntityGroupAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteEntityGroupAssocCompMerge(EntityGroupAssocDO reqEntityGroupAssocDO,
			EntityGroupAssocDO dbimageEntityGroupAssocDO, TxnTransferObj txnTransferObj) {
		// EntityObjectTypeRefkey
		if (!(null == dbimageEntityGroupAssocDO.getEntityObjectTypeRefkey()
				|| dbimageEntityGroupAssocDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageEntityGroupAssocDO.getEntityObjectTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {

				if (null == dbimageEntityGroupAssocDO.getEntityObjectTypeRefValue()) {
					dbimageEntityGroupAssocDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
				} else if (!(dbimageEntityGroupAssocDO.getEntityObjectTypeRefValue()
						.equals(theRefEntityObjectTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11018",
							"Validation error : Recieved " + dbimageEntityGroupAssocDO.getEntityObjectTypeRefkey() + "-"
									+ dbimageEntityGroupAssocDO.getEntityObjectTypeRefValue()
									+ " as EntityObjectTypeRefkey- EntityObjectTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11018",
						"Validation error : Recieved " + dbimageEntityGroupAssocDO.getEntityObjectTypeRefkey()
								+ " as EntityObjectTypeRefkey in request which failed validation");
			}
		}

		// AssocTypeRefkey
		if (!(null == dbimageEntityGroupAssocDO.getAssocTypeRefkey()
				|| dbimageEntityGroupAssocDO.getAssocTypeRefkey().isEmpty())) {
			RefAssocTypeDO theRefAssocTypeDO = referenceTableHelper.getRefAssocTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageEntityGroupAssocDO.getAssocTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAssocTypeDO) {

				if (null == dbimageEntityGroupAssocDO.getAssocTypeRefValue()) {
					dbimageEntityGroupAssocDO.setAssocTypeRefValue(theRefAssocTypeDO.getValue());
				} else if (!(dbimageEntityGroupAssocDO.getAssocTypeRefValue().equals(theRefAssocTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11019",
							"Validation error : Recieved " + dbimageEntityGroupAssocDO.getAssocTypeRefkey() + "-"
									+ dbimageEntityGroupAssocDO.getAssocTypeRefValue()
									+ " as AssocTypeRefkey- AssocTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11019",
						"Validation error : Recieved " + dbimageEntityGroupAssocDO.getAssocTypeRefkey()
								+ " as AssocTypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for merge in EntityGroupAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteEntityGroupAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in EntityGroupAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteEntityGroupAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateEntityGroupAssocCompFindByEntityIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteEntityGroupAssocCompFindByEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateEntityGroupAssocCompFindByEntityGroupIdpk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteEntityGroupAssocCompFindByEntityGroupIdpk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
