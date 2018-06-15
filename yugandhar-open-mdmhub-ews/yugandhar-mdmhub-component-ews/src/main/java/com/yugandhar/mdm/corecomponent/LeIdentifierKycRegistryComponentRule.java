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
import com.yugandhar.mdm.extern.dobj.LeIdentifierKycRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefIdentificationTypeDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;

@Scope(value = "prototype")
@Component
public class LeIdentifierKycRegistryComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for LeIdentifierKycRegistryComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeIdentifierKycRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LeIdentifierKycRegistryComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLeIdentifierKycRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LeIdentifierKycRegistryComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeIdentifierKycRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in LeIdentifierKycRegistryComp This method
	 * is modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeIdentifierKycRegistryCompPersist(LeIdentifierKycRegistryDO reqLeIdentifierKycRegistryDO,
			TxnTransferObj txnTransferObj) {
		// IdentificationTypeRefkey
		if (!(null == reqLeIdentifierKycRegistryDO.getIdentificationTypeRefkey()
				|| reqLeIdentifierKycRegistryDO.getIdentificationTypeRefkey().isEmpty())) {
			RefIdentificationTypeDO theRefIdentificationTypeDO = referenceTableHelper
					.getRefIdentificationTypeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqLeIdentifierKycRegistryDO.getIdentificationTypeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefIdentificationTypeDO) {

				if (null == reqLeIdentifierKycRegistryDO.getIdentificationTypeRefValue()) {
					reqLeIdentifierKycRegistryDO.setIdentificationTypeRefValue(theRefIdentificationTypeDO.getValue());
				} else if (!(reqLeIdentifierKycRegistryDO.getIdentificationTypeRefValue()
						.equals(theRefIdentificationTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11036",
							"Validation error : Recieved " + reqLeIdentifierKycRegistryDO.getIdentificationTypeRefkey()
									+ "-" + reqLeIdentifierKycRegistryDO.getIdentificationTypeRefValue()
									+ " as IdentificationTypeRefkey- IdentificationTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11036",
						"Validation error : Recieved " + reqLeIdentifierKycRegistryDO.getIdentificationTypeRefkey()
								+ " as IdentificationTypeRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == reqLeIdentifierKycRegistryDO.getSourceSystemRefkey()
				|| reqLeIdentifierKycRegistryDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqLeIdentifierKycRegistryDO.getSourceSystemRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == reqLeIdentifierKycRegistryDO.getSourceSystemRefValue()) {
					reqLeIdentifierKycRegistryDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(reqLeIdentifierKycRegistryDO.getSourceSystemRefValue()
						.equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11037",
							"Validation error : Recieved " + reqLeIdentifierKycRegistryDO.getSourceSystemRefkey() + "-"
									+ reqLeIdentifierKycRegistryDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11037",
						"Validation error : Recieved " + reqLeIdentifierKycRegistryDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in LeIdentifierKycRegistryComp This method
	 * is modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeIdentifierKycRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LeIdentifierKycRegistryComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeIdentifierKycRegistryCompMerge(LeIdentifierKycRegistryDO reqLeIdentifierKycRegistryDO,
			LeIdentifierKycRegistryDO dbimageLeIdentifierKycRegistryDO, TxnTransferObj txnTransferObj) {

		if (dbimageLeIdentifierKycRegistryDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"Recieved empty string for LeIdentifierKycRegistryDO.LegalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageLeIdentifierKycRegistryDO.getIdentificationTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10031",
					"Recieved empty string for LeIdentifierKycRegistryDO.identificationTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageLeIdentifierKycRegistryDO.getIdentificationNumber().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10032",
					"Recieved empty string for LeIdentifierKycRegistryDO.identificationNumber, this attribute cannot be updated to null");
		}

		// IdentificationTypeRefkey
		if (!(null == dbimageLeIdentifierKycRegistryDO.getIdentificationTypeRefkey()
				|| dbimageLeIdentifierKycRegistryDO.getIdentificationTypeRefkey().isEmpty())) {
			RefIdentificationTypeDO theRefIdentificationTypeDO = referenceTableHelper
					.getRefIdentificationTypeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageLeIdentifierKycRegistryDO.getIdentificationTypeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefIdentificationTypeDO) {

				if (null == dbimageLeIdentifierKycRegistryDO.getIdentificationTypeRefValue()) {
					dbimageLeIdentifierKycRegistryDO
							.setIdentificationTypeRefValue(theRefIdentificationTypeDO.getValue());
				} else if (!(dbimageLeIdentifierKycRegistryDO.getIdentificationTypeRefValue()
						.equals(theRefIdentificationTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11036",
							"Validation error : Recieved "
									+ dbimageLeIdentifierKycRegistryDO.getIdentificationTypeRefkey() + "-"
									+ dbimageLeIdentifierKycRegistryDO.getIdentificationTypeRefValue()
									+ " as IdentificationTypeRefkey- IdentificationTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11036",
						"Validation error : Recieved " + dbimageLeIdentifierKycRegistryDO.getIdentificationTypeRefkey()
								+ " as IdentificationTypeRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == dbimageLeIdentifierKycRegistryDO.getSourceSystemRefkey()
				|| dbimageLeIdentifierKycRegistryDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLeIdentifierKycRegistryDO.getSourceSystemRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == dbimageLeIdentifierKycRegistryDO.getSourceSystemRefValue()) {
					dbimageLeIdentifierKycRegistryDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(dbimageLeIdentifierKycRegistryDO.getSourceSystemRefValue()
						.equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11037",
							"Validation error : Recieved " + dbimageLeIdentifierKycRegistryDO.getSourceSystemRefkey()
									+ "-" + dbimageLeIdentifierKycRegistryDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11037",
						"Validation error : Recieved " + dbimageLeIdentifierKycRegistryDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}
	}

	/**
	 * Pre execute rule for merge in LeIdentifierKycRegistryComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeIdentifierKycRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LeIdentifierKycRegistryComp This method
	 * is modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeIdentifierKycRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateLeIdentifierKycRegistryCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteLeIdentifierKycRegistryCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
