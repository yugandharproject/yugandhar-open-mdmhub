package com.yugandhar.mdm.corecomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.LePersonDO;
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.extern.dobj.RefGenderDO;
import com.yugandhar.mdm.extern.dobj.RefHighestEduQualDO;
import com.yugandhar.mdm.extern.dobj.RefLanguageCodeDO;
import com.yugandhar.mdm.extern.dobj.RefPersonTypeDO;

@Scope(value = "prototype")
@Component
public class LePersonComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for LePersonComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateLePersonCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for LePersonComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateLePersonCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for LePersonComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateLePersonCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in LePersonComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteLePersonCompPersist(LePersonDO reqLePersonDO, TxnTransferObj txnTransferObj) {
		// PersonTypeRefkey
		if (!(null == reqLePersonDO.getPersonTypeRefkey() || reqLePersonDO.getPersonTypeRefkey().isEmpty())) {
			RefPersonTypeDO theRefPersonTypeDO = referenceTableHelper.getRefPersonTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLePersonDO.getPersonTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPersonTypeDO) {

				if (null == reqLePersonDO.getPersonTypeRefValue()) {
					reqLePersonDO.setPersonTypeRefValue(theRefPersonTypeDO.getValue());
				} else if (!(reqLePersonDO.getPersonTypeRefValue().equals(theRefPersonTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11038",
							"Validation error : Recieved " + reqLePersonDO.getPersonTypeRefkey() + "-"
									+ reqLePersonDO.getPersonTypeRefValue()
									+ " as PersonTypeRefkey- PersonTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11038",
						"Validation error : Recieved " + reqLePersonDO.getPersonTypeRefkey()
								+ " as PersonTypeRefkey in request which failed validation");
			}
		}

		// GenderRefkey
		if (!(null == reqLePersonDO.getGenderRefkey() || reqLePersonDO.getGenderRefkey().isEmpty())) {
			RefGenderDO theRefGenderDO = referenceTableHelper.getRefGenderValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLePersonDO.getGenderRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefGenderDO) {

				if (null == reqLePersonDO.getGenderRefValue()) {
					reqLePersonDO.setGenderRefValue(theRefGenderDO.getValue());
				} else if (!(reqLePersonDO.getGenderRefValue().equals(theRefGenderDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11039",
							"Validation error : Recieved " + reqLePersonDO.getGenderRefkey() + "-"
									+ reqLePersonDO.getGenderRefValue()
									+ " as GenderRefkey- GenderRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11039",
						"Validation error : Recieved " + reqLePersonDO.getGenderRefkey()
								+ " as GenderRefkey in request which failed validation");
			}
		}

		// CountryOfBirthRefkey
		if (!(null == reqLePersonDO.getCountryOfBirthRefkey() || reqLePersonDO.getCountryOfBirthRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryOfBirthDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLePersonDO.getCountryOfBirthRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryOfBirthDO) {

				if (null == reqLePersonDO.getCountryOfBirthRefValue()) {
					reqLePersonDO.setCountryOfBirthRefValue(theRefCountryOfBirthDO.getValue());
				} else if (!(reqLePersonDO.getCountryOfBirthRefValue().equals(theRefCountryOfBirthDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11040",
							"Validation error : Recieved " + reqLePersonDO.getCountryOfBirthRefkey() + "-"
									+ reqLePersonDO.getCountryOfBirthRefValue()
									+ " as CountryOfBirthRefkey- CountryOfBirthRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11040",
						"Validation error : Recieved " + reqLePersonDO.getCountryOfBirthRefkey()
								+ " as CountryOfBirthRefkey in request which failed validation");
			}
		}

		// CountryCitizenshipRefkey
		if (!(null == reqLePersonDO.getCountryCitizenshipRefkey()
				|| reqLePersonDO.getCountryCitizenshipRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryCitizenshipDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLePersonDO.getCountryCitizenshipRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryCitizenshipDO) {

				if (null == reqLePersonDO.getCountryCitizenshipRefValue()) {
					reqLePersonDO.setCountryCitizenshipRefValue(theRefCountryCitizenshipDO.getValue());
				} else if (!(reqLePersonDO.getCountryCitizenshipRefValue()
						.equals(theRefCountryCitizenshipDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11041",
							"Validation error : Recieved " + reqLePersonDO.getCountryCitizenshipRefkey() + "-"
									+ reqLePersonDO.getCountryCitizenshipRefValue()
									+ " as CountryCitizenshipRefkey- CountryCitizenshipRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11041",
						"Validation error : Recieved " + reqLePersonDO.getCountryCitizenshipRefkey()
								+ " as CountryCitizenshipRefkey in request which failed validation");
			}
		}

		// CountryOfDomicileRefkey
		if (!(null == reqLePersonDO.getCountryOfDomicileRefkey()
				|| reqLePersonDO.getCountryOfDomicileRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryOfDomicileDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLePersonDO.getCountryOfDomicileRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryOfDomicileDO) {

				if (null == reqLePersonDO.getCountryOfDomicileRefValue()) {
					reqLePersonDO.setCountryOfDomicileRefValue(theRefCountryOfDomicileDO.getValue());
				} else if (!(reqLePersonDO.getCountryOfDomicileRefValue()
						.equals(theRefCountryOfDomicileDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11042",
							"Validation error : Recieved " + reqLePersonDO.getCountryOfDomicileRefkey() + "-"
									+ reqLePersonDO.getCountryOfDomicileRefValue()
									+ " as CountryOfDomicileRefkey- CountryOfDomicileRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11042",
						"Validation error : Recieved " + reqLePersonDO.getCountryOfDomicileRefkey()
								+ " as CountryOfDomicileRefkey in request which failed validation");
			}
		}

		// HighestEduQualRefkey
		if (!(null == reqLePersonDO.getHighestEduQualRefkey() || reqLePersonDO.getHighestEduQualRefkey().isEmpty())) {
			RefHighestEduQualDO theRefHighestEduQualDO = referenceTableHelper.getRefHighestEduQualValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLePersonDO.getHighestEduQualRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefHighestEduQualDO) {

				if (null == reqLePersonDO.getHighestEduQualRefValue()) {
					reqLePersonDO.setHighestEduQualRefValue(theRefHighestEduQualDO.getValue());
				} else if (!(reqLePersonDO.getHighestEduQualRefValue().equals(theRefHighestEduQualDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11043",
							"Validation error : Recieved " + reqLePersonDO.getHighestEduQualRefkey() + "-"
									+ reqLePersonDO.getHighestEduQualRefValue()
									+ " as  HighestEduQualRefkey-  HighestEduQualRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11043",
						"Validation error : Recieved " + reqLePersonDO.getHighestEduQualRefkey()
								+ " as  HighestEduQualRefkey in request which failed validation");
			}
		}

		// PreferredLanguageRefkey
		if (!(null == reqLePersonDO.getPreferredLanguageRefkey()
				|| reqLePersonDO.getPreferredLanguageRefkey().isEmpty())) {
			RefLanguageCodeDO theRefPrefereedLanguageDO = referenceTableHelper.getRefLanguageCodeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqLePersonDO.getPreferredLanguageRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPrefereedLanguageDO) {

				if (null == reqLePersonDO.getPreferredLanguageRefValue()) {
					reqLePersonDO.setPreferredLanguageRefValue(theRefPrefereedLanguageDO.getValue());
				} else if (!(reqLePersonDO.getPreferredLanguageRefValue()
						.equals(theRefPrefereedLanguageDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11044",
							"Validation error : Recieved " + reqLePersonDO.getPreferredLanguageRefkey() + "-"
									+ reqLePersonDO.getPreferredLanguageRefValue()
									+ " as PreferredLanguageRefkey- PreferredLanguageRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11044",
						"Validation error : Recieved " + reqLePersonDO.getPreferredLanguageRefkey()
								+ " as PreferredLanguageRefkey in request which failed validation");
			}
		}

	}

	/**
	*Pre execute rule for persist in LePersonComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteLePersonCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in LePersonComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteLePersonCompMerge(LePersonDO reqLePersonDO, LePersonDO dbimageLePersonDO,
			TxnTransferObj txnTransferObj) {

		if (dbimageLePersonDO.getGenderRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10086",
					"Recieved empty string for lePersonDO.genderRefkey, this attribute cannot be updated to null");
		}
		
		
		// PersonTypeRefkey
		if (!(null == dbimageLePersonDO.getPersonTypeRefkey() || dbimageLePersonDO.getPersonTypeRefkey().isEmpty())) {
			RefPersonTypeDO theRefPersonTypeDO = referenceTableHelper.getRefPersonTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLePersonDO.getPersonTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPersonTypeDO) {

				if (null == dbimageLePersonDO.getPersonTypeRefValue()) {
					dbimageLePersonDO.setPersonTypeRefValue(theRefPersonTypeDO.getValue());
				} else if (!(dbimageLePersonDO.getPersonTypeRefValue().equals(theRefPersonTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11038",
							"Validation error : Recieved " + dbimageLePersonDO.getPersonTypeRefkey() + "-"
									+ dbimageLePersonDO.getPersonTypeRefValue()
									+ " as PersonTypeRefkey- PersonTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11038",
						"Validation error : Recieved " + dbimageLePersonDO.getPersonTypeRefkey()
								+ " as PersonTypeRefkey in request which failed validation");
			}
		}

		// GenderRefkey
		if (!(null == dbimageLePersonDO.getGenderRefkey() || dbimageLePersonDO.getGenderRefkey().isEmpty())) {
			RefGenderDO theRefGenderDO = referenceTableHelper.getRefGenderValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLePersonDO.getGenderRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefGenderDO) {

				if (null == dbimageLePersonDO.getGenderRefValue()) {
					dbimageLePersonDO.setGenderRefValue(theRefGenderDO.getValue());
				} else if (!(dbimageLePersonDO.getGenderRefValue().equals(theRefGenderDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11039",
							"Validation error : Recieved " + dbimageLePersonDO.getGenderRefkey() + "-"
									+ dbimageLePersonDO.getGenderRefValue()
									+ " as GenderRefkey- GenderRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11039",
						"Validation error : Recieved " + dbimageLePersonDO.getGenderRefkey()
								+ " as GenderRefkey in request which failed validation");
			}
		}

		// CountryOfBirthRefkey
		if (!(null == dbimageLePersonDO.getCountryOfBirthRefkey()
				|| dbimageLePersonDO.getCountryOfBirthRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryOfBirthDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLePersonDO.getCountryOfBirthRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryOfBirthDO) {

				if (null == dbimageLePersonDO.getCountryOfBirthRefValue()) {
					dbimageLePersonDO.setCountryOfBirthRefValue(theRefCountryOfBirthDO.getValue());
				} else if (!(dbimageLePersonDO.getCountryOfBirthRefValue().equals(theRefCountryOfBirthDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11040",
							"Validation error : Recieved " + dbimageLePersonDO.getCountryOfBirthRefkey() + "-"
									+ dbimageLePersonDO.getCountryOfBirthRefValue()
									+ " as CountryOfBirthRefkey- CountryOfBirthRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11040",
						"Validation error : Recieved " + dbimageLePersonDO.getCountryOfBirthRefkey()
								+ " as CountryOfBirthRefkey in request which failed validation");
			}
		}

		// CountryCitizenshipRefkey
		if (!(null == dbimageLePersonDO.getCountryCitizenshipRefkey()
				|| dbimageLePersonDO.getCountryCitizenshipRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryCitizenshipDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLePersonDO.getCountryCitizenshipRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryCitizenshipDO) {

				if (null == dbimageLePersonDO.getCountryCitizenshipRefValue()) {
					dbimageLePersonDO.setCountryCitizenshipRefValue(theRefCountryCitizenshipDO.getValue());
				} else if (!(dbimageLePersonDO.getCountryCitizenshipRefValue()
						.equals(theRefCountryCitizenshipDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11041",
							"Validation error : Recieved " + dbimageLePersonDO.getCountryCitizenshipRefkey() + "-"
									+ dbimageLePersonDO.getCountryCitizenshipRefValue()
									+ " as CountryCitizenshipRefkey- CountryCitizenshipRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11041",
						"Validation error : Recieved " + dbimageLePersonDO.getCountryCitizenshipRefkey()
								+ " as CountryCitizenshipRefkey in request which failed validation");
			}
		}

		// CountryOfDomicileRefkey
		if (!(null == dbimageLePersonDO.getCountryOfDomicileRefkey()
				|| dbimageLePersonDO.getCountryOfDomicileRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryOfDomicileDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLePersonDO.getCountryOfDomicileRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryOfDomicileDO) {

				if (null == dbimageLePersonDO.getCountryOfDomicileRefValue()) {
					dbimageLePersonDO.setCountryOfDomicileRefValue(theRefCountryOfDomicileDO.getValue());
				} else if (!(dbimageLePersonDO.getCountryOfDomicileRefValue()
						.equals(theRefCountryOfDomicileDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11042",
							"Validation error : Recieved " + dbimageLePersonDO.getCountryOfDomicileRefkey() + "-"
									+ dbimageLePersonDO.getCountryOfDomicileRefValue()
									+ " as CountryOfDomicileRefkey- CountryOfDomicileRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11042",
						"Validation error : Recieved " + dbimageLePersonDO.getCountryOfDomicileRefkey()
								+ " as CountryOfDomicileRefkey in request which failed validation");
			}
		}

		// HighestEduQualRefkey
		if (!(null == dbimageLePersonDO.getHighestEduQualRefkey()
				|| dbimageLePersonDO.getHighestEduQualRefkey().isEmpty())) {
			RefHighestEduQualDO theRefHighestEduQualDO = referenceTableHelper.getRefHighestEduQualValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLePersonDO.getHighestEduQualRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefHighestEduQualDO) {

				if (null == dbimageLePersonDO.getHighestEduQualRefValue()) {
					dbimageLePersonDO.setHighestEduQualRefValue(theRefHighestEduQualDO.getValue());
				} else if (!(dbimageLePersonDO.getHighestEduQualRefValue().equals(theRefHighestEduQualDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11043",
							"Validation error : Recieved " + dbimageLePersonDO.getHighestEduQualRefkey() + "-"
									+ dbimageLePersonDO.getHighestEduQualRefValue()
									+ " as  HighestEduQualRefkey-  HighestEduQualRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11043",
						"Validation error : Recieved " + dbimageLePersonDO.getHighestEduQualRefkey()
								+ " as  HighestEduQualRefkey in request which failed validation");
			}
		}

		// PreferredLanguageRefkey
		if (!(null == dbimageLePersonDO.getPreferredLanguageRefkey()
				|| dbimageLePersonDO.getPreferredLanguageRefkey().isEmpty())) {
			RefLanguageCodeDO theRefPrefereedLanguageDO = referenceTableHelper.getRefLanguageCodeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLePersonDO.getPreferredLanguageRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPrefereedLanguageDO) {

				if (null == dbimageLePersonDO.getPreferredLanguageRefValue()) {
					dbimageLePersonDO.setPreferredLanguageRefValue(theRefPrefereedLanguageDO.getValue());
				} else if (!(dbimageLePersonDO.getPreferredLanguageRefValue()
						.equals(theRefPrefereedLanguageDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11044",
							"Validation error : Recieved " + dbimageLePersonDO.getPreferredLanguageRefkey() + "-"
									+ dbimageLePersonDO.getPreferredLanguageRefValue()
									+ " as PreferredLanguageRefkey- PreferredLanguageRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11044",
						"Validation error : Recieved " + dbimageLePersonDO.getPreferredLanguageRefkey()
								+ " as PreferredLanguageRefkey in request which failed validation");
			}
		}

	}

	/**
	*Pre execute rule for merge in LePersonComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteLePersonCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in LePersonComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteLePersonCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
