package com.yugandhar.mdm.match.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.MatchMergedLeAssocDO;
import com.yugandhar.mdm.extern.dobj.RefMergeReasonDO;

@Scope(value = "prototype")
@Component
public class MatchMergedLeAssocComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	 * Pre execute persist validation method for MatchMergedLeAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateMatchMergedLeAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for MatchMergedLeAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateMatchMergedLeAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for MatchMergedLeAssocComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateMatchMergedLeAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in MatchMergedLeAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteMatchMergedLeAssocCompPersist(MatchMergedLeAssocDO reqMatchMergedLeAssocDO,
			TxnTransferObj txnTransferObj) {

		// mergeReasonRefkey
		if (!(null == reqMatchMergedLeAssocDO.getMergeReasonRefkey()
				|| reqMatchMergedLeAssocDO.getMergeReasonRefkey().isEmpty())) {
			RefMergeReasonDO theRefMergeReasonDO = referenceTableHelper.getRefMergeReasonValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqMatchMergedLeAssocDO.getMergeReasonRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefMergeReasonDO) {

				if (null == reqMatchMergedLeAssocDO.getMergeReasonRefValue()) {
					reqMatchMergedLeAssocDO.setMergeReasonRefValue(theRefMergeReasonDO.getValue());
				} else if (!(reqMatchMergedLeAssocDO.getMergeReasonRefValue().equals(theRefMergeReasonDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11065",
							"Validation error : Recieved " + reqMatchMergedLeAssocDO.getMergeReasonRefkey() + "-"
									+ reqMatchMergedLeAssocDO.getMergeReasonRefValue()
									+ " as mergeReasonRefkey- mergeReasonRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11065",
						"Validation error : Recieved " + reqMatchMergedLeAssocDO.getMergeReasonRefkey()
								+ " as mergeReasonRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in MatchMergedLeAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteMatchMergedLeAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in MatchMergedLeAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteMatchMergedLeAssocCompMerge(MatchMergedLeAssocDO reqMatchMergedLeAssocDO,
			MatchMergedLeAssocDO dbimageMatchMergedLeAssocDO, TxnTransferObj txnTransferObj) {

		if (null == dbimageMatchMergedLeAssocDO.getSurvivorLegalentityIdpk()
				|| dbimageMatchMergedLeAssocDO.getSurvivorLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10106",
					"matchMergedLeAssocDO.survivorLegalentityIdpk is needed in the request");
		}

		if (null == dbimageMatchMergedLeAssocDO.getSurvivorLegalentityIdpk()
				|| dbimageMatchMergedLeAssocDO.getSurvivorLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10107",
					"matchMergedLeAssocDO.mergedLegalentityIdpk is needed in the request");
		}

		if (null == dbimageMatchMergedLeAssocDO.getSurvivorLegalentityIdpk()
				|| dbimageMatchMergedLeAssocDO.getSurvivorLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10108",
					"matchMergedLeAssocDO.mergeReasonRefkey is needed in the request");
		}

		// mergeReasonRefkey
		if (!(null == dbimageMatchMergedLeAssocDO.getMergeReasonRefkey()
				|| dbimageMatchMergedLeAssocDO.getMergeReasonRefkey().isEmpty())) {
			RefMergeReasonDO theRefMergeReasonDO = referenceTableHelper.getRefMergeReasonValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageMatchMergedLeAssocDO.getMergeReasonRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefMergeReasonDO) {

				if (null == dbimageMatchMergedLeAssocDO.getMergeReasonRefValue()) {
					dbimageMatchMergedLeAssocDO.setMergeReasonRefValue(theRefMergeReasonDO.getValue());
				} else if (!(dbimageMatchMergedLeAssocDO.getMergeReasonRefValue()
						.equals(theRefMergeReasonDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11065",
							"Validation error : Recieved " + dbimageMatchMergedLeAssocDO.getMergeReasonRefkey() + "-"
									+ dbimageMatchMergedLeAssocDO.getMergeReasonRefValue()
									+ " as mergeReasonRefkey- mergeReasonRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11065",
						"Validation error : Recieved " + dbimageMatchMergedLeAssocDO.getMergeReasonRefkey()
								+ " as mergeReasonRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for merge in MatchMergedLeAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteMatchMergedLeAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in MatchMergedLeAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteMatchMergedLeAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
