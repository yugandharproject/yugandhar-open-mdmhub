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
import com.yugandhar.mdm.extern.dobj.PersonnamesDO;
import com.yugandhar.mdm.extern.dobj.RefPersonnameTypeDO;
import com.yugandhar.mdm.extern.dobj.RefPrefixNameDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.extern.dobj.RefSuffixNameDO;

@Scope(value = "prototype")
@Component
public class PersonnamesComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent theConfigAppPropertiesComponent;

	@Autowired
	YugandharPhoneticHelper theYugandharPhoneticHelper;

	/**
	 * Pre execute persist validation method for PersonnamesComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidatePersonnamesCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for PersonnamesComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidatePersonnamesCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for PersonnamesComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidatePersonnamesCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in PersonnamesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecutePersonnamesCompPersist(PersonnamesDO reqPersonnamesDO, TxnTransferObj txnTransferObj) {

		// PersonnameTypeRefkey
		if (!(null == reqPersonnamesDO.getPersonnameTypeRefkey()
				|| reqPersonnamesDO.getPersonnameTypeRefkey().isEmpty())) {
			RefPersonnameTypeDO theRefPersonnameTypeDO = referenceTableHelper.getRefPersonnameTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqPersonnamesDO.getPersonnameTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPersonnameTypeDO) {

				if (null == reqPersonnamesDO.getPersonnameTypeRefValue()) {
					reqPersonnamesDO.setPersonnameTypeRefValue(theRefPersonnameTypeDO.getValue());
				} else if (!(reqPersonnamesDO.getPersonnameTypeRefValue().equals(theRefPersonnameTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11057",
							"Validation error : Recieved " + reqPersonnamesDO.getPersonnameTypeRefkey() + "-"
									+ reqPersonnamesDO.getPersonnameTypeRefValue()
									+ " as PersonnameTypeRefkey- PersonnameTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11057",
						"Validation error : Recieved " + reqPersonnamesDO.getPersonnameTypeRefkey()
								+ " as PersonnameTypeRefkey in request which failed validation");
			}
		}

		// PrefixNameRefkey
		if (!(null == reqPersonnamesDO.getPrefixNameRefkey() || reqPersonnamesDO.getPrefixNameRefkey().isEmpty())) {
			RefPrefixNameDO theRefPrefixNameDO = referenceTableHelper.getRefPrefixNameValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqPersonnamesDO.getPrefixNameRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPrefixNameDO) {

				if (null == reqPersonnamesDO.getPrefixNameRefValue()) {
					reqPersonnamesDO.setPrefixNameRefValue(theRefPrefixNameDO.getValue());
				} else if (!(reqPersonnamesDO.getPrefixNameRefValue().equals(theRefPrefixNameDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11058",
							"Validation error : Recieved " + reqPersonnamesDO.getPrefixNameRefkey() + "-"
									+ reqPersonnamesDO.getPrefixNameRefValue()
									+ " as PrefixNameRefkey-  PrefixNameRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11058",
						"Validation error : Recieved " + reqPersonnamesDO.getPrefixNameRefkey()
								+ " as PrefixNameRefkey in request which failed validation");
			}
		}

		// SuffixNameRefkey
		if (!(null == reqPersonnamesDO.getSuffixNameRefkey() || reqPersonnamesDO.getSuffixNameRefkey().isEmpty())) {
			RefSuffixNameDO theRefSuffixNameDO = referenceTableHelper.getRefSuffixNameValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqPersonnamesDO.getSuffixNameRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSuffixNameDO) {

				if (null == reqPersonnamesDO.getSuffixNameRefValue()) {
					reqPersonnamesDO.setSuffixNameRefValue(theRefSuffixNameDO.getValue());
				} else if (!(reqPersonnamesDO.getSuffixNameRefValue().equals(theRefSuffixNameDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11059",
							"Validation error : Recieved " + reqPersonnamesDO.getSuffixNameRefkey() + "-"
									+ reqPersonnamesDO.getSuffixNameRefValue()
									+ " as  SuffixNameRefkey-  SuffixNameRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11059",
						"Validation error : Recieved " + reqPersonnamesDO.getSuffixNameRefkey()
								+ " as  SuffixNameRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == reqPersonnamesDO.getSourceSystemRefkey() || reqPersonnamesDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqPersonnamesDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == reqPersonnamesDO.getSourceSystemRefValue()) {
					reqPersonnamesDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(reqPersonnamesDO.getSourceSystemRefValue().equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11060",
							"Validation error : Recieved " + reqPersonnamesDO.getSourceSystemRefkey() + "-"
									+ reqPersonnamesDO.getSourceSystemRefValue()
									+ " as  SourceSystemRefkey-  SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11060",
						"Validation error : Recieved " + reqPersonnamesDO.getSourceSystemRefkey()
								+ " as  SourceSystemRefkey in request which failed validation");
			}
		}

		// Phonetic
		ConfigAppPropertiesDO theConfigAppPropertiesDO = theConfigAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_framework_enabled,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		if (theConfigAppPropertiesDO.getValue().equalsIgnoreCase(yugandharConstants.FLAG_true)) {

			if (!(null == reqPersonnamesDO.getNameOne() || reqPersonnamesDO.getNameOne().isEmpty())) {
				reqPersonnamesDO.setPhoneticNameOne(
						theYugandharPhoneticHelper.getPhoneticValue(reqPersonnamesDO.getNameOne(), txnTransferObj));
			}

			if (!(null == reqPersonnamesDO.getNameTwo() || reqPersonnamesDO.getNameTwo().isEmpty())) {
				reqPersonnamesDO.setPhoneticNameTwo(
						theYugandharPhoneticHelper.getPhoneticValue(reqPersonnamesDO.getNameTwo(), txnTransferObj));
			}

			if (!(null == reqPersonnamesDO.getNameThree() || reqPersonnamesDO.getNameThree().isEmpty())) {
				reqPersonnamesDO.setPhoneticNameThree(
						theYugandharPhoneticHelper.getPhoneticValue(reqPersonnamesDO.getNameThree(), txnTransferObj));
			}

			if (!(null == reqPersonnamesDO.getLastName() || reqPersonnamesDO.getLastName().isEmpty())) {
				reqPersonnamesDO.setPhoneticLastName(
						theYugandharPhoneticHelper.getPhoneticValue(reqPersonnamesDO.getLastName(), txnTransferObj));
			}

		}

	}

	/**
	 * Pre execute rule for persist in PersonnamesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecutePersonnamesCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in PersonnamesComp This method is modularized
	 * in respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecutePersonnamesCompMerge(PersonnamesDO reqPersonnamesDO, PersonnamesDO dbimagePersonnamesDO,
			TxnTransferObj txnTransferObj) {

		if (dbimagePersonnamesDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"Recieved empty string for PersonnamesDO.LegalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimagePersonnamesDO.getPersonnameTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10004",
					"Recieved empty string for PersonnamesDO.personnameTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimagePersonnamesDO.getNameOne().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10012",
					"Recieved empty string for PersonnamesDO.nameOne, this attribute cannot be updated to null");
		}

		// PersonnameTypeRefkey
		if (!(null == dbimagePersonnamesDO.getPersonnameTypeRefkey()
				|| dbimagePersonnamesDO.getPersonnameTypeRefkey().isEmpty())) {
			RefPersonnameTypeDO theRefPersonnameTypeDO = referenceTableHelper.getRefPersonnameTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimagePersonnamesDO.getPersonnameTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPersonnameTypeDO) {

				if (null == dbimagePersonnamesDO.getPersonnameTypeRefValue()) {
					dbimagePersonnamesDO.setPersonnameTypeRefValue(theRefPersonnameTypeDO.getValue());
				} else if (!(dbimagePersonnamesDO.getPersonnameTypeRefValue()
						.equals(theRefPersonnameTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11057",
							"Validation error : Recieved " + dbimagePersonnamesDO.getPersonnameTypeRefkey() + "-"
									+ dbimagePersonnamesDO.getPersonnameTypeRefValue()
									+ " as PersonnameTypeRefkey- PersonnameTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11057",
						"Validation error : Recieved " + dbimagePersonnamesDO.getPersonnameTypeRefkey()
								+ " as PersonnameTypeRefkey in request which failed validation");
			}
		}

		// PrefixNameRefkey
		if (!(null == dbimagePersonnamesDO.getPrefixNameRefkey()
				|| dbimagePersonnamesDO.getPrefixNameRefkey().isEmpty())) {
			RefPrefixNameDO theRefPrefixNameDO = referenceTableHelper.getRefPrefixNameValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimagePersonnamesDO.getPrefixNameRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPrefixNameDO) {

				if (null == dbimagePersonnamesDO.getPrefixNameRefValue()) {
					dbimagePersonnamesDO.setPrefixNameRefValue(theRefPrefixNameDO.getValue());
				} else if (!(dbimagePersonnamesDO.getPrefixNameRefValue().equals(theRefPrefixNameDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11058",
							"Validation error : Recieved " + dbimagePersonnamesDO.getPrefixNameRefkey() + "-"
									+ dbimagePersonnamesDO.getPrefixNameRefValue()
									+ " as PrefixNameRefkey-  PrefixNameRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11058",
						"Validation error : Recieved " + dbimagePersonnamesDO.getPrefixNameRefkey()
								+ " as PrefixNameRefkey in request which failed validation");
			}
		}

		// SuffixNameRefkey
		if (!(null == dbimagePersonnamesDO.getSuffixNameRefkey()
				|| dbimagePersonnamesDO.getSuffixNameRefkey().isEmpty())) {
			RefSuffixNameDO theRefSuffixNameDO = referenceTableHelper.getRefSuffixNameValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimagePersonnamesDO.getSuffixNameRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSuffixNameDO) {

				if (null == dbimagePersonnamesDO.getSuffixNameRefValue()) {
					dbimagePersonnamesDO.setSuffixNameRefValue(theRefSuffixNameDO.getValue());
				} else if (!(dbimagePersonnamesDO.getSuffixNameRefValue().equals(theRefSuffixNameDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11059",
							"Validation error : Recieved " + dbimagePersonnamesDO.getSuffixNameRefkey() + "-"
									+ dbimagePersonnamesDO.getSuffixNameRefValue()
									+ " as  SuffixNameRefkey-  SuffixNameRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11059",
						"Validation error : Recieved " + dbimagePersonnamesDO.getSuffixNameRefkey()
								+ " as  SuffixNameRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == dbimagePersonnamesDO.getSourceSystemRefkey()
				|| dbimagePersonnamesDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimagePersonnamesDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == dbimagePersonnamesDO.getSourceSystemRefValue()) {
					dbimagePersonnamesDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(dbimagePersonnamesDO.getSourceSystemRefValue().equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11060",
							"Validation error : Recieved " + dbimagePersonnamesDO.getSourceSystemRefkey() + "-"
									+ dbimagePersonnamesDO.getSourceSystemRefValue()
									+ " as  SourceSystemRefkey-  SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11060",
						"Validation error : Recieved " + dbimagePersonnamesDO.getSourceSystemRefkey()
								+ " as  SourceSystemRefkey in request which failed validation");
			}
		}

		// Phonetic
		ConfigAppPropertiesDO theConfigAppPropertiesDO = theConfigAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_framework_enabled,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		if (theConfigAppPropertiesDO.getValue().equalsIgnoreCase(yugandharConstants.FLAG_true)) {

			if (!(null == dbimagePersonnamesDO.getNameOne() || dbimagePersonnamesDO.getNameOne().isEmpty())) {
				dbimagePersonnamesDO.setPhoneticNameOne(
						theYugandharPhoneticHelper.getPhoneticValue(dbimagePersonnamesDO.getNameOne(), txnTransferObj));
			}

			if (!(null == dbimagePersonnamesDO.getNameTwo() || dbimagePersonnamesDO.getNameTwo().isEmpty())) {
				dbimagePersonnamesDO.setPhoneticNameTwo(
						theYugandharPhoneticHelper.getPhoneticValue(dbimagePersonnamesDO.getNameTwo(), txnTransferObj));
			}

			if (!(null == dbimagePersonnamesDO.getNameThree() || dbimagePersonnamesDO.getNameThree().isEmpty())) {
				dbimagePersonnamesDO.setPhoneticNameThree(
						theYugandharPhoneticHelper.getPhoneticValue(dbimagePersonnamesDO.getNameThree(), txnTransferObj));
			}

			if (!(null == dbimagePersonnamesDO.getLastName() || dbimagePersonnamesDO.getLastName().isEmpty())) {
				dbimagePersonnamesDO.setPhoneticLastName(
						theYugandharPhoneticHelper.getPhoneticValue(dbimagePersonnamesDO.getLastName(), txnTransferObj));
			}
		}

	}

	/**
	 * Pre execute rule for merge in PersonnamesComp This method is modularized
	 * in respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecutePersonnamesCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in PersonnamesComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecutePersonnamesCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidatePersonnamesCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		// set the current page index to zero (0) if page index is not provided
		// in the request or negative value is provided
		if (null == txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice()
				|| txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice() < 0) {
			txnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(0);
		}

		// set default page size as configured in application properties
		if (null == txnTransferObj.getTxnPayload().getPaginationPageSize()
				|| txnTransferObj.getTxnPayload().getPaginationPageSize() <= 0) {
			ConfigAppPropertiesDO theConfigAppPropertiesDO = theConfigAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_pagination_datatables_default_pagesize,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			txnTransferObj.getTxnPayload().setPaginationPageSize(Integer.valueOf(theConfigAppPropertiesDO.getValue()));

		}

	}

	public void postExecutePersonnamesCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
