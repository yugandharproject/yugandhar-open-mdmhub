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
import com.yugandhar.mdm.extern.dobj.LePropertyAssocDO;
import com.yugandhar.mdm.extern.dobj.RefPropertyLeReltypeDO;

@Scope(value = "prototype")
@Component
public class LePropertyAssocComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for LePropertyAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLePropertyAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LePropertyAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLePropertyAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LePropertyAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLePropertyAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in LePropertyAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLePropertyAssocCompPersist(LePropertyAssocDO reqLePropertyAssocDO,
			TxnTransferObj txnTransferObj) {
		// PropertyLeReltypeRefkey
		if (!(null == reqLePropertyAssocDO.getPropertyLeReltypeRefkey()
				|| reqLePropertyAssocDO.getPropertyLeReltypeRefkey().isEmpty())) {
			RefPropertyLeReltypeDO theRefPropertyLeReltypeDO = referenceTableHelper.getRefPropertyLeReltypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqLePropertyAssocDO.getPropertyLeReltypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPropertyLeReltypeDO) {

				if (null == reqLePropertyAssocDO.getPropertyLeReltypeRefValue()) {
					reqLePropertyAssocDO.setPropertyLeReltypeRefValue(theRefPropertyLeReltypeDO.getValue());
				} else if (!(reqLePropertyAssocDO.getPropertyLeReltypeRefValue()
						.equals(theRefPropertyLeReltypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11048",
							"Validation error : Recieved " + reqLePropertyAssocDO.getPropertyLeReltypeRefkey() + "-"
									+ reqLePropertyAssocDO.getPropertyLeReltypeRefValue()
									+ " as PropertyLeReltypeRefkey- PropertyLeReltypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11048",
						"Validation error : Recieved " + reqLePropertyAssocDO.getPropertyLeReltypeRefkey()
								+ " as PropertyLeReltypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in LePropertyAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLePropertyAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LePropertyAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLePropertyAssocCompMerge(LePropertyAssocDO reqLePropertyAssocDO,
			LePropertyAssocDO dbimageLePropertyAssocDO, TxnTransferObj txnTransferObj) {

		if (dbimageLePropertyAssocDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"Recieved empty string for LePropertyAssocDO.LegalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageLePropertyAssocDO.getPropertyIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10025",
					"Recieved empty string for LePropertyAssocDO.propertyIdpk, this attribute cannot be updated to null");
		}

		if (dbimageLePropertyAssocDO.getPropertyLeReltypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10027",
					"Recieved empty string for LePropertyAssocDO.propertyLeReltypeRefkey, this attribute cannot be updated to null");
		}

		// PropertyLeReltypeRefkey
		if (!(null == dbimageLePropertyAssocDO.getPropertyLeReltypeRefkey()
				|| dbimageLePropertyAssocDO.getPropertyLeReltypeRefkey().isEmpty())) {
			RefPropertyLeReltypeDO theRefPropertyLeReltypeDO = referenceTableHelper.getRefPropertyLeReltypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLePropertyAssocDO.getPropertyLeReltypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPropertyLeReltypeDO) {

				if (null == dbimageLePropertyAssocDO.getPropertyLeReltypeRefValue()) {
					dbimageLePropertyAssocDO.setPropertyLeReltypeRefValue(theRefPropertyLeReltypeDO.getValue());
				} else if (!(dbimageLePropertyAssocDO.getPropertyLeReltypeRefValue()
						.equals(theRefPropertyLeReltypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11048",
							"Validation error : Recieved " + dbimageLePropertyAssocDO.getPropertyLeReltypeRefkey() + "-"
									+ dbimageLePropertyAssocDO.getPropertyLeReltypeRefValue()
									+ " as PropertyLeReltypeRefkey- PropertyLeReltypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11048",
						"Validation error : Recieved " + dbimageLePropertyAssocDO.getPropertyLeReltypeRefkey()
								+ " as PropertyLeReltypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for merge in LePropertyAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLePropertyAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LePropertyAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLePropertyAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateLePropertyAssocCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteLePropertyAssocCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
