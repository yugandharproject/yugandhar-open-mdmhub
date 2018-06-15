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
import com.yugandhar.mdm.extern.dobj.LeVehicleAssocDO;
import com.yugandhar.mdm.extern.dobj.RefAgreementTypeDO;
import com.yugandhar.mdm.extern.dobj.RefDeactivationReasonDO;
import com.yugandhar.mdm.extern.dobj.RefLeRoletypeDO;

@Scope(value = "prototype")
@Component
public class LeVehicleAssocComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for LeVehicleAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeVehicleAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LeVehicleAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLeVehicleAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LeVehicleAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeVehicleAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in LeVehicleAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeVehicleAssocCompPersist(LeVehicleAssocDO reqLeVehicleAssocDO,
			TxnTransferObj txnTransferObj) {

		// LeRoletypeRefkey
		if (!(null == reqLeVehicleAssocDO.getLeRoletypeRefkey()
				|| reqLeVehicleAssocDO.getLeRoletypeRefkey().isEmpty())) {
			RefLeRoletypeDO theRefLeRoletypeDO = referenceTableHelper.getRefLeRoletypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLeVehicleAssocDO.getLeRoletypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRoletypeDO) {

				if (null == reqLeVehicleAssocDO.getLeRoletypeRefValue()) {
					reqLeVehicleAssocDO.setLeRoletypeRefValue(theRefLeRoletypeDO.getValue());
				} else if (!(reqLeVehicleAssocDO.getLeRoletypeRefValue().equals(theRefLeRoletypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11053",
							"Validation error : Recieved " + reqLeVehicleAssocDO.getLeRoletypeRefkey() + "-"
									+ reqLeVehicleAssocDO.getLeRoletypeRefValue()
									+ " as LeRoletypeRefkey- LeRoletypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11053",
						"Validation error : Recieved " + reqLeVehicleAssocDO.getLeRoletypeRefkey()
								+ " as LeRoletypeRefkey in request which failed validation");
			}
		}

		// DeactivationReasonRefkey
		if (!(null == reqLeVehicleAssocDO.getDeactivationReasonRefkey()
				|| reqLeVehicleAssocDO.getDeactivationReasonRefkey().isEmpty())) {
			RefDeactivationReasonDO theRefDeactivationReasonDO = referenceTableHelper
					.getRefDeactivationReasonValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqLeVehicleAssocDO.getDeactivationReasonRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefDeactivationReasonDO) {

				if (null == reqLeVehicleAssocDO.getDeactivationReasonRefValue()) {
					reqLeVehicleAssocDO.setDeactivationReasonRefValue(theRefDeactivationReasonDO.getValue());
				} else if (!(reqLeVehicleAssocDO.getDeactivationReasonRefValue()
						.equals(theRefDeactivationReasonDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11054",
							"Validation error : Recieved " + reqLeVehicleAssocDO.getDeactivationReasonRefkey() + "-"
									+ reqLeVehicleAssocDO.getDeactivationReasonRefValue()
									+ " as DeactivationReasonRefkey-  DeactivationReasonRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11054",
						"Validation error : Recieved " + reqLeVehicleAssocDO.getDeactivationReasonRefkey()
								+ " as DeactivationReasonRefkey in request which failed validation");
			}
		}

		// AgreementTypeRefkey
		if (!(null == reqLeVehicleAssocDO.getAgreementTypeRefkey()
				|| reqLeVehicleAssocDO.getAgreementTypeRefkey().isEmpty())) {
			RefAgreementTypeDO theRefAgreementTypeDO = referenceTableHelper.getRefAgreementTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLeVehicleAssocDO.getAgreementTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAgreementTypeDO) {

				if (null == reqLeVehicleAssocDO.getAgreementTypeRefValue()) {
					reqLeVehicleAssocDO.setAgreementTypeRefValue(theRefAgreementTypeDO.getValue());
				} else if (!(reqLeVehicleAssocDO.getAgreementTypeRefValue().equals(theRefAgreementTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11055",
							"Validation error : Recieved " + reqLeVehicleAssocDO.getAgreementTypeRefkey() + "-"
									+ reqLeVehicleAssocDO.getAgreementTypeRefValue()
									+ " as  AgreementTypeRefkey-  AgreementTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11055",
						"Validation error : Recieved " + reqLeVehicleAssocDO.getAgreementTypeRefkey()
								+ " as  AgreementTypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in LeVehicleAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeVehicleAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LeVehicleAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeVehicleAssocCompMerge(LeVehicleAssocDO reqLeVehicleAssocDO,
			LeVehicleAssocDO dbimageLeVehicleAssocDO, TxnTransferObj txnTransferObj) {

		if (dbimageLeVehicleAssocDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"Recieved empty string for LeVehicleAssocDO.LegalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageLeVehicleAssocDO.getLeRoletypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10023",
					"Recieved empty string for LeVehicleAssocDO.leRoletypeRefkey, this attribute cannot be updated to null");
		}

		// LeRoletypeRefkey
		if (!(null == dbimageLeVehicleAssocDO.getLeRoletypeRefkey()
				|| dbimageLeVehicleAssocDO.getLeRoletypeRefkey().isEmpty())) {
			RefLeRoletypeDO theRefLeRoletypeDO = referenceTableHelper.getRefLeRoletypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLeVehicleAssocDO.getLeRoletypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRoletypeDO) {

				if (null == dbimageLeVehicleAssocDO.getLeRoletypeRefValue()) {
					dbimageLeVehicleAssocDO.setLeRoletypeRefValue(theRefLeRoletypeDO.getValue());
				} else if (!(dbimageLeVehicleAssocDO.getLeRoletypeRefValue().equals(theRefLeRoletypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11053",
							"Validation error : Recieved " + dbimageLeVehicleAssocDO.getLeRoletypeRefkey() + "-"
									+ dbimageLeVehicleAssocDO.getLeRoletypeRefValue()
									+ " as LeRoletypeRefkey- LeRoletypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11053",
						"Validation error : Recieved " + dbimageLeVehicleAssocDO.getLeRoletypeRefkey()
								+ " as LeRoletypeRefkey in request which failed validation");
			}
		}

		// DeactivationReasonRefkey
		if (!(null == dbimageLeVehicleAssocDO.getDeactivationReasonRefkey()
				|| dbimageLeVehicleAssocDO.getDeactivationReasonRefkey().isEmpty())) {
			RefDeactivationReasonDO theRefDeactivationReasonDO = referenceTableHelper
					.getRefDeactivationReasonValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageLeVehicleAssocDO.getDeactivationReasonRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefDeactivationReasonDO) {

				if (null == dbimageLeVehicleAssocDO.getDeactivationReasonRefValue()) {
					dbimageLeVehicleAssocDO.setDeactivationReasonRefValue(theRefDeactivationReasonDO.getValue());
				} else if (!(dbimageLeVehicleAssocDO.getDeactivationReasonRefValue()
						.equals(theRefDeactivationReasonDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11054",
							"Validation error : Recieved " + dbimageLeVehicleAssocDO.getDeactivationReasonRefkey() + "-"
									+ dbimageLeVehicleAssocDO.getDeactivationReasonRefValue()
									+ " as DeactivationReasonRefkey-  DeactivationReasonRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11054",
						"Validation error : Recieved " + dbimageLeVehicleAssocDO.getDeactivationReasonRefkey()
								+ " as DeactivationReasonRefkey in request which failed validation");
			}
		}

		// AgreementTypeRefkey
		if (!(null == dbimageLeVehicleAssocDO.getAgreementTypeRefkey()
				|| dbimageLeVehicleAssocDO.getAgreementTypeRefkey().isEmpty())) {
			RefAgreementTypeDO theRefAgreementTypeDO = referenceTableHelper.getRefAgreementTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLeVehicleAssocDO.getAgreementTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAgreementTypeDO) {

				if (null == dbimageLeVehicleAssocDO.getAgreementTypeRefValue()) {
					dbimageLeVehicleAssocDO.setAgreementTypeRefValue(theRefAgreementTypeDO.getValue());
				} else if (!(dbimageLeVehicleAssocDO.getAgreementTypeRefValue()
						.equals(theRefAgreementTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11055",
							"Validation error : Recieved " + dbimageLeVehicleAssocDO.getAgreementTypeRefkey() + "-"
									+ dbimageLeVehicleAssocDO.getAgreementTypeRefValue()
									+ " as  AgreementTypeRefkey-  AgreementTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11055",
						"Validation error : Recieved " + dbimageLeVehicleAssocDO.getAgreementTypeRefkey()
								+ " as  AgreementTypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for merge in LeVehicleAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeVehicleAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LeVehicleAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeVehicleAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateLeVehicleAssocCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteLeVehicleAssocCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
