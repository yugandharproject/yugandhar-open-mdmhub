package com.yugandhar.mdm.corecomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.LeCorporationDO;
import com.yugandhar.mdm.extern.dobj.RefClassificationCodeDO;
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.extern.dobj.RefIndustryCodeDO;

@Scope(value = "prototype")
@Component
public class LeCorporationComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for LeCorporationComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateLeCorporationCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for LeCorporationComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateLeCorporationCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for LeCorporationComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateLeCorporationCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in LeCorporationComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteLeCorporationCompPersist(LeCorporationDO reqLeCorporationDO, TxnTransferObj txnTransferObj) {
		// ClassificationCodeRefkey
		if (!(null == reqLeCorporationDO.getClassificationCodeRefkey()
				|| reqLeCorporationDO.getClassificationCodeRefkey().isEmpty())) {
			RefClassificationCodeDO theRefClassificationCodeDO = referenceTableHelper
					.getRefClassificationCodeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqLeCorporationDO.getClassificationCodeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefClassificationCodeDO) {

				if (null == reqLeCorporationDO.getClassificationCodeRefValue()) {
					reqLeCorporationDO.setClassificationCodeRefValue(theRefClassificationCodeDO.getValue());
				} else if (!(reqLeCorporationDO.getClassificationCodeRefValue()
						.equals(theRefClassificationCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11027",
							"Validation error : Recieved " + reqLeCorporationDO.getClassificationCodeRefkey() + "-"
									+ reqLeCorporationDO.getClassificationCodeRefValue()
									+ " as ClassificationCodeRefkey- ClassificationCodeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11027",
						"Validation error : Recieved " + reqLeCorporationDO.getClassificationCodeRefkey()
								+ " as ClassificationCodeRefkey in request which failed validation");
			}
		}

		// IndustryCodeRefkey
		if (!(null == reqLeCorporationDO.getIndustryCodeRefkey()
				|| reqLeCorporationDO.getIndustryCodeRefkey().isEmpty())) {
			RefIndustryCodeDO theRefIndustryCodeDO = referenceTableHelper.getRefIndustryCodeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLeCorporationDO.getIndustryCodeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefIndustryCodeDO) {

				if (null == reqLeCorporationDO.getIndustryCodeRefValue()) {
					reqLeCorporationDO.setIndustryCodeRefValue(theRefIndustryCodeDO.getValue());
				} else if (!(reqLeCorporationDO.getIndustryCodeRefValue().equals(theRefIndustryCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11028",
							"Validation error : Recieved " + reqLeCorporationDO.getIndustryCodeRefkey() + "-"
									+ reqLeCorporationDO.getIndustryCodeRefValue()
									+ " as IndustryCodeRefkey- IndustryCodeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11028",
						"Validation error : Recieved " + reqLeCorporationDO.getIndustryCodeRefkey()
								+ " as IndustryCodeRefkey in request which failed validation");
			}
		}
		// CountryRegistrationRefkey
		if (!(null == reqLeCorporationDO.getCountryRegistrationRefkey()
				|| reqLeCorporationDO.getCountryRegistrationRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryRegistrationDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqLeCorporationDO.getCountryRegistrationRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryRegistrationDO) {

				if (null == reqLeCorporationDO.getCountryRegistrationRefValue()) {
					reqLeCorporationDO.setCountryRegistrationRefValue(theRefCountryRegistrationDO.getValue());
				} else if (!(reqLeCorporationDO.getCountryRegistrationRefValue()
						.equals(theRefCountryRegistrationDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11029",
							"Validation error : Recieved " + reqLeCorporationDO.getCountryRegistrationRefkey() + "-"
									+ reqLeCorporationDO.getCountryRegistrationRefValue()
									+ " as CountryRegistrationRefkey- CountryRegistrationRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11029",
						"Validation error : Recieved " + reqLeCorporationDO.getCountryRegistrationRefkey()
								+ " as CountryRegistrationRefkey in request which failed validation");
			}
		}

	}

	/**
	*Pre execute rule for persist in LeCorporationComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteLeCorporationCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in LeCorporationComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteLeCorporationCompMerge(LeCorporationDO reqLeCorporationDO,
			LeCorporationDO dbimageLeCorporationDO, TxnTransferObj txnTransferObj) {
		// ClassificationCodeRefkey
		if (!(null == dbimageLeCorporationDO.getClassificationCodeRefkey()
				|| dbimageLeCorporationDO.getClassificationCodeRefkey().isEmpty())) {
			RefClassificationCodeDO theRefClassificationCodeDO = referenceTableHelper
					.getRefClassificationCodeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageLeCorporationDO.getClassificationCodeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefClassificationCodeDO) {

				if (null == dbimageLeCorporationDO.getClassificationCodeRefValue()) {
					dbimageLeCorporationDO.setClassificationCodeRefValue(theRefClassificationCodeDO.getValue());
				} else if (!(dbimageLeCorporationDO.getClassificationCodeRefValue()
						.equals(theRefClassificationCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11027",
							"Validation error : Recieved " + dbimageLeCorporationDO.getClassificationCodeRefkey() + "-"
									+ dbimageLeCorporationDO.getClassificationCodeRefValue()
									+ " as ClassificationCodeRefkey- ClassificationCodeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11027",
						"Validation error : Recieved " + dbimageLeCorporationDO.getClassificationCodeRefkey()
								+ " as ClassificationCodeRefkey in request which failed validation");
			}
		}

		// IndustryCodeRefkey
		if (!(null == dbimageLeCorporationDO.getIndustryCodeRefkey()
				|| dbimageLeCorporationDO.getIndustryCodeRefkey().isEmpty())) {
			RefIndustryCodeDO theRefIndustryCodeDO = referenceTableHelper.getRefIndustryCodeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLeCorporationDO.getIndustryCodeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefIndustryCodeDO) {

				if (null == dbimageLeCorporationDO.getIndustryCodeRefValue()) {
					dbimageLeCorporationDO.setIndustryCodeRefValue(theRefIndustryCodeDO.getValue());
				} else if (!(dbimageLeCorporationDO.getIndustryCodeRefValue()
						.equals(theRefIndustryCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11028",
							"Validation error : Recieved " + dbimageLeCorporationDO.getIndustryCodeRefkey() + "-"
									+ dbimageLeCorporationDO.getIndustryCodeRefValue()
									+ " as IndustryCodeRefkey- IndustryCodeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11028",
						"Validation error : Recieved " + dbimageLeCorporationDO.getIndustryCodeRefkey()
								+ " as IndustryCodeRefkey in request which failed validation");
			}
		}
		// CountryRegistrationRefkey
		if (!(null == dbimageLeCorporationDO.getCountryRegistrationRefkey()
				|| dbimageLeCorporationDO.getCountryRegistrationRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryRegistrationDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLeCorporationDO.getCountryRegistrationRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryRegistrationDO) {

				if (null == dbimageLeCorporationDO.getCountryRegistrationRefValue()) {
					dbimageLeCorporationDO.setCountryRegistrationRefValue(theRefCountryRegistrationDO.getValue());
				} else if (!(dbimageLeCorporationDO.getCountryRegistrationRefValue()
						.equals(theRefCountryRegistrationDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11029",
							"Validation error : Recieved " + dbimageLeCorporationDO.getCountryRegistrationRefkey() + "-"
									+ dbimageLeCorporationDO.getCountryRegistrationRefValue()
									+ " as CountryRegistrationRefkey- CountryRegistrationRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11029",
						"Validation error : Recieved " + dbimageLeCorporationDO.getCountryRegistrationRefkey()
								+ " as CountryRegistrationRefkey in request which failed validation");
			}
		}

	}

	/**
	*Pre execute rule for merge in LeCorporationComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteLeCorporationCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in LeCorporationComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteLeCorporationCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
