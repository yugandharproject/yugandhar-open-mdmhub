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
import com.yugandhar.mdm.extern.dobj.LeSystemKeysRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.extern.dobj.RefStatusInSourceDO;

@Scope(value = "prototype")
@Component
public class LeSystemKeysRegistryComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for LeSystemKeysRegistryComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeSystemKeysRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LeSystemKeysRegistryComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLeSystemKeysRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LeSystemKeysRegistryComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeSystemKeysRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in LeSystemKeysRegistryComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeSystemKeysRegistryCompPersist(LeSystemKeysRegistryDO reqLeSystemKeysRegistryDO,
			TxnTransferObj txnTransferObj) {

		// SourceSystemRefkey
		if (!(null == reqLeSystemKeysRegistryDO.getSourceSystemRefkey()
				|| reqLeSystemKeysRegistryDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqLeSystemKeysRegistryDO.getSourceSystemRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == reqLeSystemKeysRegistryDO.getSourceSystemRefValue()) {
					reqLeSystemKeysRegistryDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(reqLeSystemKeysRegistryDO.getSourceSystemRefValue()
						.equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11049",
							"Validation error : Recieved " + reqLeSystemKeysRegistryDO.getSourceSystemRefkey() + "-"
									+ reqLeSystemKeysRegistryDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11049",
						"Validation error : Recieved " + reqLeSystemKeysRegistryDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}

		// StatusInSourceRefkey
		if (!(null == reqLeSystemKeysRegistryDO.getStatusInSourceRefkey()
				|| reqLeSystemKeysRegistryDO.getStatusInSourceRefkey().isEmpty())) {
			RefStatusInSourceDO theRefStatusInSourceDO = referenceTableHelper.getRefStatusInSourceValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqLeSystemKeysRegistryDO.getStatusInSourceRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefStatusInSourceDO) {

				if (null == reqLeSystemKeysRegistryDO.getStatusInSourceRefValue()) {
					reqLeSystemKeysRegistryDO.setStatusInSourceRefValue(theRefStatusInSourceDO.getValue());
				} else if (!(reqLeSystemKeysRegistryDO.getStatusInSourceRefValue()
						.equals(theRefStatusInSourceDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11050",
							"Validation error : Recieved " + reqLeSystemKeysRegistryDO.getStatusInSourceRefkey() + "-"
									+ reqLeSystemKeysRegistryDO.getStatusInSourceRefValue()
									+ " as StatusInSourceRefkey-  StatusInSourceRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11050",
						"Validation error : Recieved " + reqLeSystemKeysRegistryDO.getStatusInSourceRefkey()
								+ " as StatusInSourceRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in LeSystemKeysRegistryComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeSystemKeysRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LeSystemKeysRegistryComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeSystemKeysRegistryCompMerge(LeSystemKeysRegistryDO reqLeSystemKeysRegistryDO,
			LeSystemKeysRegistryDO dbimageLeSystemKeysRegistryDO, TxnTransferObj txnTransferObj) {

		if (dbimageLeSystemKeysRegistryDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"Recieved empty string for LeSystemKeysRegistryDO.LegalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageLeSystemKeysRegistryDO.getSourceSystemRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10034",
					"Recieved empty string for LeSystemKeysRegistryDO.sourceSystemRefkey, this attribute cannot be updated to null");
		}

		if (dbimageLeSystemKeysRegistryDO.getReferenceId().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10035",
					"Recieved empty string for LeSystemKeysRegistryDO.referenceId, this attribute cannot be updated to null");
		}

		// SourceSystemRefkey
		if (!(null == dbimageLeSystemKeysRegistryDO.getSourceSystemRefkey()
				|| dbimageLeSystemKeysRegistryDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLeSystemKeysRegistryDO.getSourceSystemRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == dbimageLeSystemKeysRegistryDO.getSourceSystemRefValue()) {
					dbimageLeSystemKeysRegistryDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(dbimageLeSystemKeysRegistryDO.getSourceSystemRefValue()
						.equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11049",
							"Validation error : Recieved " + dbimageLeSystemKeysRegistryDO.getSourceSystemRefkey() + "-"
									+ dbimageLeSystemKeysRegistryDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11049",
						"Validation error : Recieved " + dbimageLeSystemKeysRegistryDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}

		// StatusInSourceRefkey
		if (!(null == dbimageLeSystemKeysRegistryDO.getStatusInSourceRefkey()
				|| dbimageLeSystemKeysRegistryDO.getStatusInSourceRefkey().isEmpty())) {
			RefStatusInSourceDO theRefStatusInSourceDO = referenceTableHelper.getRefStatusInSourceValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLeSystemKeysRegistryDO.getStatusInSourceRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefStatusInSourceDO) {

				if (null == dbimageLeSystemKeysRegistryDO.getStatusInSourceRefValue()) {
					dbimageLeSystemKeysRegistryDO.setStatusInSourceRefValue(theRefStatusInSourceDO.getValue());
				} else if (!(dbimageLeSystemKeysRegistryDO.getStatusInSourceRefValue()
						.equals(theRefStatusInSourceDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11050",
							"Validation error : Recieved " + dbimageLeSystemKeysRegistryDO.getStatusInSourceRefkey()
									+ "-" + dbimageLeSystemKeysRegistryDO.getStatusInSourceRefValue()
									+ " as StatusInSourceRefkey-  StatusInSourceRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11050",
						"Validation error : Recieved " + dbimageLeSystemKeysRegistryDO.getStatusInSourceRefkey()
								+ " as StatusInSourceRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for merge in LeSystemKeysRegistryComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeSystemKeysRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LeSystemKeysRegistryComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeSystemKeysRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateLeSystemKeysRegistryCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteLeSystemKeysRegistryCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
