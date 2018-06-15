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
import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;
import com.yugandhar.mdm.extern.dobj.RefAgreementTypeDO;
import com.yugandhar.mdm.extern.dobj.RefDeactivationReasonDO;
import com.yugandhar.mdm.extern.dobj.RefLeRoletypeDO;

@Scope(value = "prototype")
@Component
public class LeAccountAssocComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for LeAccountAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeAccountAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LeAccountAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLeAccountAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LeAccountAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeAccountAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in LeAccountAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeAccountAssocCompPersist(LeAccountAssocDO reqLeAccountAssocDO,
			TxnTransferObj txnTransferObj) {
		// LeRoletypeRefkey
		if (!(null == reqLeAccountAssocDO.getLeRoletypeRefkey()
				|| reqLeAccountAssocDO.getLeRoletypeRefkey().isEmpty())) {
			RefLeRoletypeDO theRefLeRoletypeDO = referenceTableHelper.getRefLeRoletypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLeAccountAssocDO.getLeRoletypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRoletypeDO) {

				if (null == reqLeAccountAssocDO.getLeRoletypeRefValue()) {
					reqLeAccountAssocDO.setLeRoletypeRefValue(theRefLeRoletypeDO.getValue());
				} else if (!(reqLeAccountAssocDO.getLeRoletypeRefValue().equals(theRefLeRoletypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11022",
							"Validation error : Recieved " + reqLeAccountAssocDO.getLeRoletypeRefkey() + "-"
									+ reqLeAccountAssocDO.getLeRoletypeRefValue()
									+ " as LeRoletypeRefkey- LeRoletypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11022",
						"Validation error : Recieved " + reqLeAccountAssocDO.getLeRoletypeRefkey()
								+ " as LeRoletypeRefkey in request which failed validation");
			}
		}

		// DeactivationReasonRefkey
		if (!(null == reqLeAccountAssocDO.getDeactivationReasonRefkey()
				|| reqLeAccountAssocDO.getDeactivationReasonRefkey().isEmpty())) {
			RefDeactivationReasonDO theRefDeactivationReasonDO = referenceTableHelper
					.getRefDeactivationReasonValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqLeAccountAssocDO.getDeactivationReasonRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefDeactivationReasonDO) {

				if (null == reqLeAccountAssocDO.getDeactivationReasonRefValue()) {
					reqLeAccountAssocDO.setDeactivationReasonRefValue(theRefDeactivationReasonDO.getValue());
				} else if (!(reqLeAccountAssocDO.getDeactivationReasonRefValue()
						.equals(theRefDeactivationReasonDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11023",
							"Validation error : Recieved " + reqLeAccountAssocDO.getDeactivationReasonRefkey() + "-"
									+ reqLeAccountAssocDO.getDeactivationReasonRefValue()
									+ " as DeactivationReasonRefkey- DeactivationReasonRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11023",
						"Validation error : Recieved " + reqLeAccountAssocDO.getDeactivationReasonRefkey()
								+ " as DeactivationReasonRefkey in request which failed validation");
			}
		}
		// AgreementTypeRefkey
		if (!(null == reqLeAccountAssocDO.getAgreementTypeRefkey()
				|| reqLeAccountAssocDO.getAgreementTypeRefkey().isEmpty())) {
			RefAgreementTypeDO theRefAgreementTypeDO = referenceTableHelper.getRefAgreementTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLeAccountAssocDO.getAgreementTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAgreementTypeDO) {

				if (null == reqLeAccountAssocDO.getAgreementTypeRefValue()) {
					reqLeAccountAssocDO.setAgreementTypeRefValue(theRefAgreementTypeDO.getValue());
				} else if (!(reqLeAccountAssocDO.getAgreementTypeRefValue().equals(theRefAgreementTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11024",
							"Validation error : Recieved " + reqLeAccountAssocDO.getAgreementTypeRefkey() + "-"
									+ reqLeAccountAssocDO.getAgreementTypeRefValue()
									+ " as AgreementTypeRefkey- AgreementTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11024",
						"Validation error : Recieved " + reqLeAccountAssocDO.getAgreementTypeRefkey()
								+ " as AgreementTypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in LeAccountAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeAccountAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LeAccountAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeAccountAssocCompMerge(LeAccountAssocDO reqLeAccountAssocDO,
			LeAccountAssocDO dbimageLeAccountAssocDO, TxnTransferObj txnTransferObj) {

		if (dbimageLeAccountAssocDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"Recieved empty string for LeAccountAssocDO.legalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageLeAccountAssocDO.getLeRoletypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10013",
					"Recieved empty string for LeAccountAssocDO.roletypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageLeAccountAssocDO.getAccountIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10014",
					"Recieved empty string for LeAccountAssocDO.accountIdpk, this attribute cannot be updated to null");
		}

		// LeRoletypeRefkey
		if (!(null == dbimageLeAccountAssocDO.getLeRoletypeRefkey()
				|| dbimageLeAccountAssocDO.getLeRoletypeRefkey().isEmpty())) {
			RefLeRoletypeDO theRefLeRoletypeDO = referenceTableHelper.getRefLeRoletypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLeAccountAssocDO.getLeRoletypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRoletypeDO) {

				if (null == dbimageLeAccountAssocDO.getLeRoletypeRefValue()) {
					dbimageLeAccountAssocDO.setLeRoletypeRefValue(theRefLeRoletypeDO.getValue());
				} else if (!(dbimageLeAccountAssocDO.getLeRoletypeRefValue().equals(theRefLeRoletypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11022",
							"Validation error : Recieved " + dbimageLeAccountAssocDO.getLeRoletypeRefkey() + "-"
									+ dbimageLeAccountAssocDO.getLeRoletypeRefValue()
									+ " as LeRoletypeRefkey- LeRoletypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11022",
						"Validation error : Recieved " + dbimageLeAccountAssocDO.getLeRoletypeRefkey()
								+ " as LeRoletypeRefkey in request which failed validation");
			}
		}

		// DeactivationReasonRefkey
		if (!(null == dbimageLeAccountAssocDO.getDeactivationReasonRefkey()
				|| dbimageLeAccountAssocDO.getDeactivationReasonRefkey().isEmpty())) {
			RefDeactivationReasonDO theRefDeactivationReasonDO = referenceTableHelper
					.getRefDeactivationReasonValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageLeAccountAssocDO.getDeactivationReasonRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefDeactivationReasonDO) {

				if (null == dbimageLeAccountAssocDO.getDeactivationReasonRefValue()) {
					dbimageLeAccountAssocDO.setDeactivationReasonRefValue(theRefDeactivationReasonDO.getValue());
				} else if (!(dbimageLeAccountAssocDO.getDeactivationReasonRefValue()
						.equals(theRefDeactivationReasonDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11023",
							"Validation error : Recieved " + dbimageLeAccountAssocDO.getDeactivationReasonRefkey() + "-"
									+ dbimageLeAccountAssocDO.getDeactivationReasonRefValue()
									+ " as DeactivationReasonRefkey- DeactivationReasonRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11023",
						"Validation error : Recieved " + dbimageLeAccountAssocDO.getDeactivationReasonRefkey()
								+ " as DeactivationReasonRefkey in request which failed validation");
			}
		}
		// AgreementTypeRefkey
		if (!(null == dbimageLeAccountAssocDO.getAgreementTypeRefkey()
				|| dbimageLeAccountAssocDO.getAgreementTypeRefkey().isEmpty())) {
			RefAgreementTypeDO theRefAgreementTypeDO = referenceTableHelper.getRefAgreementTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLeAccountAssocDO.getAgreementTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAgreementTypeDO) {

				if (null == dbimageLeAccountAssocDO.getAgreementTypeRefValue()) {
					dbimageLeAccountAssocDO.setAgreementTypeRefValue(theRefAgreementTypeDO.getValue());
				} else if (!(dbimageLeAccountAssocDO.getAgreementTypeRefValue()
						.equals(theRefAgreementTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11024",
							"Validation error : Recieved " + dbimageLeAccountAssocDO.getAgreementTypeRefkey() + "-"
									+ dbimageLeAccountAssocDO.getAgreementTypeRefValue()
									+ " as AgreementTypeRefkey- AgreementTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11024",
						"Validation error : Recieved " + dbimageLeAccountAssocDO.getAgreementTypeRefkey()
								+ " as AgreementTypeRefkey in request which failed validation");
			}
		}
	}

	/**
	 * Pre execute rule for merge in LeAccountAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeAccountAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LeAccountAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeAccountAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for FindByLegalEntityIdPk in LeAccountAssocComp This
	 * method is modularized in respective rule class Use Aspect Oriented
	 * Programming (AOP) based Yugandhar Rule to override / extend the default
	 * OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeAccountAssocCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
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

	/**
	 * Post execute rule for FindByLegalEntityIdPk in LeAccountAssocComp This
	 * method is modularized in respective rule class Use Aspect Oriented
	 * Programming (AOP) based Yugandhar Rule to override / extend the default
	 * OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeAccountAssocCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for FindByAccountIdpk in LeAccountAssocComp This method
	 * is modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeAccountAssocCompFindByAccountIdpk(TxnTransferObj txnTransferObj) {
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

	/**
	 * Pre execute rule for FindByAccountIdpk in LeAccountAssocComp This method
	 * is modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeAccountAssocCompFindByAccountIdpk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
