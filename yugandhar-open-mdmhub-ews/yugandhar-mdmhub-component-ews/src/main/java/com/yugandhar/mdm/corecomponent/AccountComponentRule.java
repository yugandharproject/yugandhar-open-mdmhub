package com.yugandhar.mdm.corecomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.AccountDO;
import com.yugandhar.mdm.extern.dobj.RefAccountMdmStatusDO;
import com.yugandhar.mdm.extern.dobj.RefAccountSourceStatusDO;
import com.yugandhar.mdm.extern.dobj.RefBillingModeTypeDO;
import com.yugandhar.mdm.extern.dobj.RefBranchCodeDO;
import com.yugandhar.mdm.extern.dobj.RefCurrencyDO;
import com.yugandhar.mdm.extern.dobj.RefLanguageCodeDO;
import com.yugandhar.mdm.extern.dobj.RefLobtypeDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.extern.dobj.RefTerminationReasonDO;

@Scope(value = "prototype")
@Component
public class AccountComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for AccountComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateAccountCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for AccountComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateAccountCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for AccountComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateAccountCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in AccountComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteAccountCompPersist(AccountDO reqAccountDO, TxnTransferObj txnTransferObj) {

		// ContractSignedLangRefkey
		if (!(null == reqAccountDO.getContractSignedLangRefkey()
				|| reqAccountDO.getContractSignedLangRefkey().isEmpty())) {
			RefLanguageCodeDO theRefLanguageCodeDO = referenceTableHelper.getRefLanguageCodeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqAccountDO.getContractSignedLangRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLanguageCodeDO) {

				if (null == reqAccountDO.getContractSignedLangRefValue()) {
					reqAccountDO.setContractSignedLangRefValue(theRefLanguageCodeDO.getValue());
				} else if (!(reqAccountDO.getContractSignedLangRefValue().equals(theRefLanguageCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11003",
							"Validation error : Recieved " + reqAccountDO.getContractSignedLangRefkey() + "-"
									+ reqAccountDO.getContractSignedLangRefValue()
									+ " as ContractSignedLangRefkey- ContractSignedLangRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11003",
						"Validation error : Recieved " + reqAccountDO.getContractSignedLangRefkey()
								+ " as ContractSignedLangRefkey in request which failed validation");
			}
		}

		// CurrencyRefkey
		if (!(null == reqAccountDO.getCurrencyRefkey() || reqAccountDO.getCurrencyRefkey().isEmpty())) {
			RefCurrencyDO theRefCurrencyDO = referenceTableHelper.getRefCurrencyValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAccountDO.getCurrencyRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCurrencyDO) {

				if (null == reqAccountDO.getCurrencyRefValue()) {
					reqAccountDO.setCurrencyRefValue(theRefCurrencyDO.getValue());
				} else if (!(reqAccountDO.getCurrencyRefValue().equals(theRefCurrencyDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11004",
							"Validation error : Recieved " + reqAccountDO.getCurrencyRefkey() + "-"
									+ reqAccountDO.getCurrencyRefValue()
									+ " as CurrencyRefkey- CurrencyRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11004",
						"Validation error : Recieved " + reqAccountDO.getCurrencyRefkey()
								+ " as CurrencyRefkey in request which failed validation");
			}
		}

		// BillingModeTypeRefkey
		if (!(null == reqAccountDO.getBillingModeTypeRefkey() || reqAccountDO.getBillingModeTypeRefkey().isEmpty())) {
			RefBillingModeTypeDO theRefBillingModeTypeDO = referenceTableHelper.getRefBillingModeTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAccountDO.getBillingModeTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBillingModeTypeDO) {

				if (null == reqAccountDO.getBillingModeTypeRefValue()) {
					reqAccountDO.setBillingModeTypeRefValue(theRefBillingModeTypeDO.getValue());
				} else if (!(reqAccountDO.getBillingModeTypeRefValue().equals(theRefBillingModeTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11005",
							"Validation error : Recieved " + reqAccountDO.getBillingModeTypeRefkey() + "-"
									+ reqAccountDO.getBillingModeTypeRefValue()
									+ " as BillingModeTypeRefkey- BillingModeTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11005",
						"Validation error : Recieved " + reqAccountDO.getBillingModeTypeRefkey()
								+ " as BillingModeTypeRefkey in request which failed validation");
			}
		}

		// LobtypeRefkey
		if (!(null == reqAccountDO.getLobtypeRefkey() || reqAccountDO.getLobtypeRefkey().isEmpty())) {
			RefLobtypeDO theRefLobtypeDO = referenceTableHelper.getRefLobtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAccountDO.getLobtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLobtypeDO) {

				if (null == reqAccountDO.getLobtypeRefValue()) {
					reqAccountDO.setLobtypeRefValue(theRefLobtypeDO.getValue());
				} else if (!(reqAccountDO.getLobtypeRefValue().equals(theRefLobtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11006",
							"Validation error : Recieved " + reqAccountDO.getLobtypeRefkey() + "-"
									+ reqAccountDO.getLobtypeRefValue()
									+ " as LobtypeRefkey- LobtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11006",
						"Validation error : Recieved " + reqAccountDO.getLobtypeRefkey()
								+ " as LobtypeRefkey in request which failed validation");
			}
		}

		// BranchCodeRefkey
		if (!(null == reqAccountDO.getBranchCodeRefkey() || reqAccountDO.getBranchCodeRefkey().isEmpty())) {
			RefBranchCodeDO theRefBranchCodeDO = referenceTableHelper.getRefBranchCodeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAccountDO.getBranchCodeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBranchCodeDO) {

				if (null == reqAccountDO.getBranchCodeRefValue()) {
					reqAccountDO.setBranchCodeRefValue(theRefBranchCodeDO.getValue());
				} else if (!(reqAccountDO.getBranchCodeRefValue().equals(theRefBranchCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11008",
							"Validation error : Recieved " + reqAccountDO.getBranchCodeRefkey() + "-"
									+ reqAccountDO.getBranchCodeRefValue()
									+ " as BranchCodeRefkey- BranchCodeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11008",
						"Validation error : Recieved " + reqAccountDO.getBranchCodeRefkey()
								+ " as BranchCodeRefkey in request which failed validation");
			}
		}

		// AccountSourceStatusRefkey
		if (!(null == reqAccountDO.getAccountSourceStatusRefkey()
				|| reqAccountDO.getAccountSourceStatusRefkey().isEmpty())) {
			RefAccountSourceStatusDO theRefAccountSourceStatusDO = referenceTableHelper
					.getRefAccountSourceStatusValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqAccountDO.getAccountSourceStatusRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAccountSourceStatusDO) {

				if (null == reqAccountDO.getAccountSourceStatusRefValue()) {
					reqAccountDO.setAccountSourceStatusRefValue(theRefAccountSourceStatusDO.getValue());
				} else if (!(reqAccountDO.getAccountSourceStatusRefValue()
						.equals(theRefAccountSourceStatusDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11009",
							"Validation error : Recieved " + reqAccountDO.getAccountSourceStatusRefkey() + "-"
									+ reqAccountDO.getAccountSourceStatusRefValue()
									+ " as AccountSourceStatusRefkey- AccountSourceStatusRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11009",
						"Validation error : Recieved " + reqAccountDO.getAccountSourceStatusRefkey()
								+ " as AccountSourceStatusRefkey in request which failed validation");
			}
		}

		// AccountMdmStatusRefkey
		if (!(null == reqAccountDO.getAccountMdmStatusRefkey() || reqAccountDO.getAccountMdmStatusRefkey().isEmpty())) {
			RefAccountMdmStatusDO theRefAccountMdmStatusDO = referenceTableHelper.getRefAccountMdmStatusValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAccountDO.getAccountMdmStatusRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAccountMdmStatusDO) {

				if (null == reqAccountDO.getAccountMdmStatusRefValue()) {
					reqAccountDO.setAccountMdmStatusRefValue(theRefAccountMdmStatusDO.getValue());
				} else if (!(reqAccountDO.getAccountMdmStatusRefValue().equals(theRefAccountMdmStatusDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11010",
							"Validation error : Recieved " + reqAccountDO.getAccountMdmStatusRefkey() + "-"
									+ reqAccountDO.getAccountMdmStatusRefValue()
									+ " as AccountMdmStatusRefkey- AccountMdmStatusRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11010",
						"Validation error : Recieved " + reqAccountDO.getAccountMdmStatusRefkey()
								+ " as AccountMdmStatusRefkey in request which failed validation");
			}
		}

		// TerminationReasonRefkey
		if (!(null == reqAccountDO.getTerminationReasonRefkey()
				|| reqAccountDO.getTerminationReasonRefkey().isEmpty())) {
			RefTerminationReasonDO theRefTerminationReasonDO = referenceTableHelper.getRefTerminationReasonValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAccountDO.getTerminationReasonRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefTerminationReasonDO) {

				if (null == reqAccountDO.getTerminationReasonRefValue()) {
					reqAccountDO.setTerminationReasonRefValue(theRefTerminationReasonDO.getValue());
				} else if (!(reqAccountDO.getTerminationReasonRefValue()
						.equals(theRefTerminationReasonDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11012",
							"Validation error : Recieved " + reqAccountDO.getTerminationReasonRefkey() + "-"
									+ reqAccountDO.getTerminationReasonRefValue()
									+ " as TerminationReasonRefkey- TerminationReasonRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11012",
						"Validation error : Recieved " + reqAccountDO.getTerminationReasonRefkey()
								+ " as TerminationReasonRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == reqAccountDO.getSourceSystemRefkey() || reqAccountDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAccountDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == reqAccountDO.getSourceSystemRefValue()) {
					reqAccountDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(reqAccountDO.getSourceSystemRefValue().equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11007",
							"Validation error : Recieved " + reqAccountDO.getSourceSystemRefkey() + "-"
									+ reqAccountDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11007",
						"Validation error : Recieved " + reqAccountDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}

	}

	/**
	*Pre execute rule for persist in AccountComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteAccountCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in AccountComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteAccountCompMerge(AccountDO reqAccountDO, AccountDO dbimageAccountDO,
			TxnTransferObj txnTransferObj) {

		if (reqAccountDO.getAccountName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10015",
					"Recieved empty string for AccountDO.accountName, this attribute cannot be updated to null");
		}

		// ContractSignedLangRefkey
		if (!(null == dbimageAccountDO.getContractSignedLangRefkey()
				|| dbimageAccountDO.getContractSignedLangRefkey().isEmpty())) {
			RefLanguageCodeDO theRefLanguageCodeDO = referenceTableHelper.getRefLanguageCodeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageAccountDO.getContractSignedLangRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLanguageCodeDO) {

				if (null == dbimageAccountDO.getContractSignedLangRefValue()) {
					dbimageAccountDO.setContractSignedLangRefValue(theRefLanguageCodeDO.getValue());
				} else if (!(dbimageAccountDO.getContractSignedLangRefValue()
						.equals(theRefLanguageCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11003",
							"Validation error : Recieved " + dbimageAccountDO.getContractSignedLangRefkey() + "-"
									+ dbimageAccountDO.getContractSignedLangRefValue()
									+ " as ContractSignedLangRefkey- ContractSignedLangRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11003",
						"Validation error : Recieved " + dbimageAccountDO.getContractSignedLangRefkey()
								+ " as ContractSignedLangRefkey in request which failed validation");
			}
		}

		// CurrencyRefkey
		if (!(null == dbimageAccountDO.getCurrencyRefkey() || dbimageAccountDO.getCurrencyRefkey().isEmpty())) {
			RefCurrencyDO theRefCurrencyDO = referenceTableHelper.getRefCurrencyValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageAccountDO.getCurrencyRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCurrencyDO) {

				if (null == dbimageAccountDO.getCurrencyRefValue()) {
					dbimageAccountDO.setCurrencyRefValue(theRefCurrencyDO.getValue());
				} else if (!(dbimageAccountDO.getCurrencyRefValue().equals(theRefCurrencyDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11004",
							"Validation error : Recieved " + dbimageAccountDO.getCurrencyRefkey() + "-"
									+ dbimageAccountDO.getCurrencyRefValue()
									+ " as CurrencyRefkey- CurrencyRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11004",
						"Validation error : Recieved " + dbimageAccountDO.getCurrencyRefkey()
								+ " as CurrencyRefkey in request which failed validation");
			}
		}

		// BillingModeTypeRefkey
		if (!(null == dbimageAccountDO.getBillingModeTypeRefkey()
				|| dbimageAccountDO.getBillingModeTypeRefkey().isEmpty())) {
			RefBillingModeTypeDO theRefBillingModeTypeDO = referenceTableHelper.getRefBillingModeTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageAccountDO.getBillingModeTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBillingModeTypeDO) {

				if (null == dbimageAccountDO.getBillingModeTypeRefValue()) {
					dbimageAccountDO.setBillingModeTypeRefValue(theRefBillingModeTypeDO.getValue());
				} else if (!(dbimageAccountDO.getBillingModeTypeRefValue()
						.equals(theRefBillingModeTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11005",
							"Validation error : Recieved " + dbimageAccountDO.getBillingModeTypeRefkey() + "-"
									+ dbimageAccountDO.getBillingModeTypeRefValue()
									+ " as BillingModeTypeRefkey- BillingModeTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11005",
						"Validation error : Recieved " + dbimageAccountDO.getBillingModeTypeRefkey()
								+ " as BillingModeTypeRefkey in request which failed validation");
			}
		}

		// LobtypeRefkey
		if (!(null == dbimageAccountDO.getLobtypeRefkey() || dbimageAccountDO.getLobtypeRefkey().isEmpty())) {
			RefLobtypeDO theRefLobtypeDO = referenceTableHelper.getRefLobtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageAccountDO.getLobtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLobtypeDO) {

				if (null == dbimageAccountDO.getLobtypeRefValue()) {
					dbimageAccountDO.setLobtypeRefValue(theRefLobtypeDO.getValue());
				} else if (!(dbimageAccountDO.getLobtypeRefValue().equals(theRefLobtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11006",
							"Validation error : Recieved " + dbimageAccountDO.getLobtypeRefkey() + "-"
									+ dbimageAccountDO.getLobtypeRefValue()
									+ " as LobtypeRefkey- LobtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11006",
						"Validation error : Recieved " + dbimageAccountDO.getLobtypeRefkey()
								+ " as LobtypeRefkey in request which failed validation");
			}
		}

		// BranchCodeRefkey
		if (!(null == dbimageAccountDO.getBranchCodeRefkey() || dbimageAccountDO.getBranchCodeRefkey().isEmpty())) {
			RefBranchCodeDO theRefBranchCodeDO = referenceTableHelper.getRefBranchCodeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageAccountDO.getBranchCodeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBranchCodeDO) {

				if (null == dbimageAccountDO.getBranchCodeRefValue()) {
					dbimageAccountDO.setBranchCodeRefValue(theRefBranchCodeDO.getValue());
				} else if (!(dbimageAccountDO.getBranchCodeRefValue().equals(theRefBranchCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11008",
							"Validation error : Recieved " + dbimageAccountDO.getBranchCodeRefkey() + "-"
									+ dbimageAccountDO.getBranchCodeRefValue()
									+ " as BranchCodeRefkey- BranchCodeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1100",
						"Validation error : Recieved " + dbimageAccountDO.getBranchCodeRefkey()
								+ " as BranchCodeRefkey in request which failed validation");
			}
		}

		// AccountSourceStatusRefkey
		if (!(null == dbimageAccountDO.getAccountSourceStatusRefkey()
				|| dbimageAccountDO.getAccountSourceStatusRefkey().isEmpty())) {
			RefAccountSourceStatusDO theRefAccountSourceStatusDO = referenceTableHelper
					.getRefAccountSourceStatusValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageAccountDO.getAccountSourceStatusRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAccountSourceStatusDO) {

				if (null == dbimageAccountDO.getAccountSourceStatusRefValue()) {
					dbimageAccountDO.setAccountSourceStatusRefValue(theRefAccountSourceStatusDO.getValue());
				} else if (!(dbimageAccountDO.getAccountSourceStatusRefValue()
						.equals(theRefAccountSourceStatusDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11009",
							"Validation error : Recieved " + dbimageAccountDO.getAccountSourceStatusRefkey() + "-"
									+ dbimageAccountDO.getAccountSourceStatusRefValue()
									+ " as AccountSourceStatusRefkey- AccountSourceStatusRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11009",
						"Validation error : Recieved " + dbimageAccountDO.getAccountSourceStatusRefkey()
								+ " as AccountSourceStatusRefkey in request which failed validation");
			}
		}

		// AccountMdmStatusRefkey
		if (!(null == dbimageAccountDO.getAccountMdmStatusRefkey()
				|| dbimageAccountDO.getAccountMdmStatusRefkey().isEmpty())) {
			RefAccountMdmStatusDO theRefAccountMdmStatusDO = referenceTableHelper.getRefAccountMdmStatusValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageAccountDO.getAccountMdmStatusRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAccountMdmStatusDO) {

				if (null == dbimageAccountDO.getAccountMdmStatusRefValue()) {
					dbimageAccountDO.setAccountMdmStatusRefValue(theRefAccountMdmStatusDO.getValue());
				} else if (!(dbimageAccountDO.getAccountMdmStatusRefValue()
						.equals(theRefAccountMdmStatusDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11010",
							"Validation error : Recieved " + dbimageAccountDO.getAccountMdmStatusRefkey() + "-"
									+ dbimageAccountDO.getAccountMdmStatusRefValue()
									+ " as AccountMdmStatusRefkey- AccountMdmStatusRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11010",
						"Validation error : Recieved " + dbimageAccountDO.getAccountMdmStatusRefkey()
								+ " as AccountMdmStatusRefkey in request which failed validation");
			}
		}

		// TerminationReasonRefkey
		if (!(null == dbimageAccountDO.getTerminationReasonRefkey()
				|| dbimageAccountDO.getTerminationReasonRefkey().isEmpty())) {
			RefTerminationReasonDO theRefTerminationReasonDO = referenceTableHelper.getRefTerminationReasonValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageAccountDO.getTerminationReasonRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefTerminationReasonDO) {

				if (null == dbimageAccountDO.getTerminationReasonRefValue()) {
					dbimageAccountDO.setTerminationReasonRefValue(theRefTerminationReasonDO.getValue());
				} else if (!(dbimageAccountDO.getTerminationReasonRefValue()
						.equals(theRefTerminationReasonDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11012",
							"Validation error : Recieved " + dbimageAccountDO.getTerminationReasonRefkey() + "-"
									+ dbimageAccountDO.getTerminationReasonRefValue()
									+ " as TerminationReasonRefkey- TerminationReasonRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11012",
						"Validation error : Recieved " + dbimageAccountDO.getTerminationReasonRefkey()
								+ " as TerminationReasonRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == dbimageAccountDO.getSourceSystemRefkey() || dbimageAccountDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageAccountDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == dbimageAccountDO.getSourceSystemRefValue()) {
					dbimageAccountDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(dbimageAccountDO.getSourceSystemRefValue().equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11007",
							"Validation error : Recieved " + dbimageAccountDO.getSourceSystemRefkey() + "-"
									+ dbimageAccountDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11007",
						"Validation error : Recieved " + dbimageAccountDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}

	}

	/**
	*Pre execute rule for merge in AccountComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteAccountCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in AccountComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteAccountCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
