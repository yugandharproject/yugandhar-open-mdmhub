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
import com.yugandhar.mdm.component.util.YugandharPhoneticHelper;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.CorporationnamesDO;
import com.yugandhar.mdm.extern.dobj.RefCorporationNameTypeDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;

@Scope(value = "prototype")
@Component
public class CorporationnamesComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@Autowired
	YugandharPhoneticHelper theYugandharPhoneticHelper;

	/**
	 * Pre execute persist validation method for CorporationnamesComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateCorporationnamesCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for CorporationnamesComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateCorporationnamesCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for CorporationnamesComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateCorporationnamesCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in CorporationnamesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteCorporationnamesCompPersist(CorporationnamesDO reqCorporationnamesDO,
			TxnTransferObj txnTransferObj) {
		// CorporationNameTypeRefkey
		if (!(null == reqCorporationnamesDO.getCorporationNameTypeRefkey()
				|| reqCorporationnamesDO.getCorporationNameTypeRefkey().isEmpty())) {
			RefCorporationNameTypeDO theRefCorporationNameTypeDO = referenceTableHelper
					.getRefCorporationNameTypeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqCorporationnamesDO.getCorporationNameTypeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCorporationNameTypeDO) {

				if (null == reqCorporationnamesDO.getCorporationNameTypeRefValue()) {
					reqCorporationnamesDO.setCorporationNameTypeRefValue(theRefCorporationNameTypeDO.getValue());
				} else if (!(reqCorporationnamesDO.getCorporationNameTypeRefValue()
						.equals(theRefCorporationNameTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11016",
							"Validation error : Recieved " + reqCorporationnamesDO.getCorporationNameTypeRefkey() + "-"
									+ reqCorporationnamesDO.getCorporationNameTypeRefValue()
									+ " as CorporationNameTypeRefkey- CorporationNameTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11016",
						"Validation error : Recieved " + reqCorporationnamesDO.getCorporationNameTypeRefkey()
								+ " as CorporationNameTypeRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == reqCorporationnamesDO.getSourceSystemRefkey()
				|| reqCorporationnamesDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqCorporationnamesDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == reqCorporationnamesDO.getSourceSystemRefValue()) {
					reqCorporationnamesDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(reqCorporationnamesDO.getSourceSystemRefValue().equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11017",
							"Validation error : Recieved " + reqCorporationnamesDO.getSourceSystemRefkey() + "-"
									+ reqCorporationnamesDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11017",
						"Validation error : Recieved " + reqCorporationnamesDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}

		// Phonetic
		ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_framework_enabled,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		if (theConfigAppPropertiesDO.getValue().equalsIgnoreCase(yugandharConstants.FLAG_true)) {

			if (!(null == reqCorporationnamesDO.getCorporationName()
					|| reqCorporationnamesDO.getCorporationName().isEmpty())) {
				reqCorporationnamesDO.setPhoneticCorporationName(theYugandharPhoneticHelper
						.getPhoneticValue(reqCorporationnamesDO.getCorporationName(), txnTransferObj));
			}

		}
	}

	/**
	 * Pre execute rule for persist in CorporationnamesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteCorporationnamesCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in CorporationnamesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteCorporationnamesCompMerge(CorporationnamesDO reqCorporationnamesDO,
			CorporationnamesDO dbimageCorporationnamesDO, TxnTransferObj txnTransferObj) {
		if (dbimageCorporationnamesDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10005",
					"Recieved empty string for CorporationnamesDO.LegalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageCorporationnamesDO.getCorporationNameTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10006",
					"Recieved empty string for CorporationnamesDO.CorporationNameTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageCorporationnamesDO.getCorporationName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10007",
					"Recieved empty string for CorporationnamesDO.corporationName, this attribute cannot be updated to null");
		}

		// CorporationNameTypeRefkey
		if (!(null == dbimageCorporationnamesDO.getCorporationNameTypeRefkey()
				|| dbimageCorporationnamesDO.getCorporationNameTypeRefkey().isEmpty())) {
			RefCorporationNameTypeDO theRefCorporationNameTypeDO = referenceTableHelper
					.getRefCorporationNameTypeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageCorporationnamesDO.getCorporationNameTypeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCorporationNameTypeDO) {

				if (null == dbimageCorporationnamesDO.getCorporationNameTypeRefValue()) {
					dbimageCorporationnamesDO.setCorporationNameTypeRefValue(theRefCorporationNameTypeDO.getValue());
				} else if (!(dbimageCorporationnamesDO.getCorporationNameTypeRefValue()
						.equals(theRefCorporationNameTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11016",
							"Validation error : Recieved " + dbimageCorporationnamesDO.getCorporationNameTypeRefkey()
									+ "-" + dbimageCorporationnamesDO.getCorporationNameTypeRefValue()
									+ " as CorporationNameTypeRefkey- CorporationNameTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11016",
						"Validation error : Recieved " + dbimageCorporationnamesDO.getCorporationNameTypeRefkey()
								+ " as CorporationNameTypeRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == dbimageCorporationnamesDO.getSourceSystemRefkey()
				|| dbimageCorporationnamesDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageCorporationnamesDO.getSourceSystemRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == dbimageCorporationnamesDO.getSourceSystemRefValue()) {
					dbimageCorporationnamesDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(dbimageCorporationnamesDO.getSourceSystemRefValue()
						.equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11017",
							"Validation error : Recieved " + dbimageCorporationnamesDO.getSourceSystemRefkey() + "-"
									+ dbimageCorporationnamesDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11017",
						"Validation error : Recieved " + dbimageCorporationnamesDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}

		// Phonetic
		ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_framework_enabled,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		if (theConfigAppPropertiesDO.getValue().equalsIgnoreCase(yugandharConstants.FLAG_true)) {

			if (!(null == dbimageCorporationnamesDO.getCorporationName()
					|| dbimageCorporationnamesDO.getCorporationName().isEmpty())) {
				dbimageCorporationnamesDO.setPhoneticCorporationName(theYugandharPhoneticHelper
						.getPhoneticValue(dbimageCorporationnamesDO.getCorporationName(), txnTransferObj));
			}

		}
	}

	/**
	 * Pre execute rule for merge in CorporationnamesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteCorporationnamesCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in CorporationnamesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteCorporationnamesCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateCorporationnamesCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteCorporationnamesCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
