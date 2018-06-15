package com.yugandhar.mdm.match.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.MatchCandidateLeRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefMatchActionstatusDO;
import com.yugandhar.mdm.extern.dobj.RefMatchProposedActionDO;

@Scope(value = "prototype")
@Component
public class MatchCandidateLeRegistryComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	 * Pre execute persist validation method for MatchCandidateLeRegistryComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateMatchCandidateLeRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for MatchCandidateLeRegistryComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateMatchCandidateLeRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for MatchCandidateLeRegistryComp
	 * to validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateMatchCandidateLeRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in MatchCandidateLeRegistryComp This method
	 * is modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteMatchCandidateLeRegistryCompPersist(MatchCandidateLeRegistryDO reqMatchCandidateLeRegistryDO,
			TxnTransferObj txnTransferObj) {

		// matchProposedActionRefkey
		if (!(null == reqMatchCandidateLeRegistryDO.getMatchProposedActionRefkey()
				|| reqMatchCandidateLeRegistryDO.getMatchProposedActionRefkey().isEmpty())) {
			RefMatchProposedActionDO theRefMatchProposedActionDO = referenceTableHelper
					.getRefMatchProposedActionValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqMatchCandidateLeRegistryDO.getMatchProposedActionRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefMatchProposedActionDO) {

				if (null == reqMatchCandidateLeRegistryDO.getMatchProposedActionRefValue()) {
					reqMatchCandidateLeRegistryDO
							.setMatchProposedActionRefValue(theRefMatchProposedActionDO.getValue());
				} else if (!(reqMatchCandidateLeRegistryDO.getMatchProposedActionRefValue()
						.equals(theRefMatchProposedActionDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11063",
							"Validation error : Recieved "
									+ reqMatchCandidateLeRegistryDO.getMatchProposedActionRefkey() + "-"
									+ reqMatchCandidateLeRegistryDO.getMatchProposedActionRefValue()
									+ " as matchProposedActionRefkey- matchProposedActionRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11063",
						"Validation error : Recieved " + reqMatchCandidateLeRegistryDO.getMatchProposedActionRefkey()
								+ " as matchProposedActionRefkey in request which failed validation");
			}
		}

		// matchActionstatusRefkey
		if (!(null == reqMatchCandidateLeRegistryDO.getMatchActionstatusRefkey()
				|| reqMatchCandidateLeRegistryDO.getMatchActionstatusRefkey().isEmpty())) {
			RefMatchActionstatusDO theRefMatchActionstatusDO = referenceTableHelper.getRefMatchActionstatusValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqMatchCandidateLeRegistryDO.getMatchActionstatusRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefMatchActionstatusDO) {

				if (null == reqMatchCandidateLeRegistryDO.getMatchActionstatusRefValue()) {
					reqMatchCandidateLeRegistryDO.setMatchActionstatusRefValue(theRefMatchActionstatusDO.getValue());
				} else if (!(reqMatchCandidateLeRegistryDO.getMatchActionstatusRefValue()
						.equals(theRefMatchActionstatusDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11064",
							"Validation error : Recieved " + reqMatchCandidateLeRegistryDO.getMatchActionstatusRefkey()
									+ "-" + reqMatchCandidateLeRegistryDO.getMatchActionstatusRefValue()
									+ " as MatchActionstatusRefkey- MatchActionstatusRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11064",
						"Validation error : Recieved " + reqMatchCandidateLeRegistryDO.getMatchActionstatusRefkey()
								+ " as MatchActionstatusRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in MatchCandidateLeRegistryComp This method
	 * is modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteMatchCandidateLeRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in MatchCandidateLeRegistryComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteMatchCandidateLeRegistryCompMerge(MatchCandidateLeRegistryDO reqMatchCandidateLeRegistryDO,
			MatchCandidateLeRegistryDO dbimageMatchCandidateLeRegistryDO, TxnTransferObj txnTransferObj) {

		if (null == dbimageMatchCandidateLeRegistryDO.getLegalentityIdpk()
				|| dbimageMatchCandidateLeRegistryDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10101",
					"Recieved empty string for matchCandidateLeRegistryDO.legalentityIdpk, this attribute cannot be updated to null");
		}

		if (null == dbimageMatchCandidateLeRegistryDO.getCandidateLegalentityidpk()
				|| dbimageMatchCandidateLeRegistryDO.getCandidateLegalentityidpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10102",
					"Recieved empty string for matchCandidateLeRegistryDO.candidateLegalentityidpk, this attribute cannot be updated to null");
		}

		if (null == dbimageMatchCandidateLeRegistryDO.getMatchPattern()
				|| dbimageMatchCandidateLeRegistryDO.getMatchPattern().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10103",
					"Recieved empty string for matchCandidateLeRegistryDO.matchPattern, this attribute cannot be updated to null");
		}

		if (null == dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefkey()
				|| dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10104",
					"Recieved empty string for matchCandidateLeRegistryDO.matchProposedActionRefkey, this attribute cannot be updated to null");
		}

		if (null == dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefkey()
				|| dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10105",
					"Recieved empty string for matchCandidateLeRegistryDO.matchActionstatusRefkey, this attribute cannot be updated to null");
		}

		// matchProposedActionRefkey
		if (!(null == dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefkey()
				|| dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefkey().isEmpty())) {
			RefMatchProposedActionDO theRefMatchProposedActionDO = referenceTableHelper
					.getRefMatchProposedActionValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefMatchProposedActionDO) {

				if (null == dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefValue()) {
					dbimageMatchCandidateLeRegistryDO
							.setMatchProposedActionRefValue(theRefMatchProposedActionDO.getValue());
				} else if (!(dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefValue()
						.equals(theRefMatchProposedActionDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11063",
							"Validation error : Recieved "
									+ dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefkey() + "-"
									+ dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefValue()
									+ " as matchProposedActionRefkey- matchProposedActionRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11063",
						"Validation error : Recieved "
								+ dbimageMatchCandidateLeRegistryDO.getMatchProposedActionRefkey()
								+ " as matchProposedActionRefkey in request which failed validation");
			}
		}

		// matchActionstatusRefkey
		if (!(null == dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefkey()
				|| dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefkey().isEmpty())) {
			RefMatchActionstatusDO theRefMatchActionstatusDO = referenceTableHelper.getRefMatchActionstatusValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefMatchActionstatusDO) {

				if (null == dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefValue()) {
					dbimageMatchCandidateLeRegistryDO
							.setMatchActionstatusRefValue(theRefMatchActionstatusDO.getValue());
				} else if (!(dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefValue()
						.equals(theRefMatchActionstatusDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11064",
							"Validation error : Recieved "
									+ dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefkey() + "-"
									+ dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefValue()
									+ " as MatchActionstatusRefkey- MatchActionstatusRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11064",
						"Validation error : Recieved " + dbimageMatchCandidateLeRegistryDO.getMatchActionstatusRefkey()
								+ " as MatchActionstatusRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for merge in MatchCandidateLeRegistryComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteMatchCandidateLeRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in MatchCandidateLeRegistryComp This method
	 * is modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteMatchCandidateLeRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
