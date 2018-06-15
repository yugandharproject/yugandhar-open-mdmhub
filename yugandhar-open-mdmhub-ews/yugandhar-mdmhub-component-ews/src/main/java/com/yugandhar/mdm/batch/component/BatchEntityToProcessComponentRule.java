package com.yugandhar.mdm.batch.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.BatchEntityToProcessDO;
import com.yugandhar.mdm.extern.dobj.RefBatchActionStatusDO;
import com.yugandhar.mdm.extern.dobj.RefBatchProposedActionDO;
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;

@Scope(value = "prototype")
@Component
public class BatchEntityToProcessComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	 * Pre execute persist validation method for BatchEntityToProcessComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateBatchEntityToProcessCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for BatchEntityToProcessComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateBatchEntityToProcessCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for BatchEntityToProcessComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateBatchEntityToProcessCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in BatchEntityToProcessComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteBatchEntityToProcessCompPersist(BatchEntityToProcessDO reqBatchEntityToProcessDO,
			TxnTransferObj txnTransferObj) {

		// EntityObjectTypeRefkey
		if (!(null == reqBatchEntityToProcessDO.getEntityObjectTypeRefkey()
				|| reqBatchEntityToProcessDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqBatchEntityToProcessDO.getEntityObjectTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {

				if (null == reqBatchEntityToProcessDO.getEntityObjectTypeRefValue()) {
					reqBatchEntityToProcessDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
				} else if (!(reqBatchEntityToProcessDO.getEntityObjectTypeRefValue()
						.equals(theRefEntityObjectTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11066",
							"Validation error : Recieved " + reqBatchEntityToProcessDO.getEntityObjectTypeRefkey() + "-"
									+ reqBatchEntityToProcessDO.getEntityObjectTypeRefValue()
									+ " as EntityObjectTypeRefkey- EntityObjectTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11066",
						"Validation error : Recieved " + reqBatchEntityToProcessDO.getEntityObjectTypeRefkey()
								+ " as EntityObjectTypeRefkey in request which failed validation");
			}
		}

		// batchProposedActionRefkey
		if (!(null == reqBatchEntityToProcessDO.getBatchProposedActionRefkey()
				|| reqBatchEntityToProcessDO.getBatchProposedActionRefkey().isEmpty())) {
			RefBatchProposedActionDO theRefBatchProposedActionDO = referenceTableHelper
					.getRefBatchProposedActionValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqBatchEntityToProcessDO.getBatchProposedActionRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBatchProposedActionDO) {

				if (null == reqBatchEntityToProcessDO.getBatchProposedActionRefValue()) {
					reqBatchEntityToProcessDO.setBatchProposedActionRefValue(theRefBatchProposedActionDO.getValue());
				} else if (!(reqBatchEntityToProcessDO.getBatchProposedActionRefValue()
						.equals(theRefBatchProposedActionDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11067",
							"Validation error : Recieved " + reqBatchEntityToProcessDO.getBatchProposedActionRefkey()
									+ "-" + reqBatchEntityToProcessDO.getBatchProposedActionRefValue()
									+ " as batchProposedActionRefkey- batchProposedActionRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11067",
						"Validation error : Recieved " + reqBatchEntityToProcessDO.getBatchProposedActionRefkey()
								+ " as batchProposedActionRefkey in request which failed validation");
			}
		}

		// batchActionStatusRefkey
		if (!(null == reqBatchEntityToProcessDO.getBatchActionStatusRefkey()
				|| reqBatchEntityToProcessDO.getBatchActionStatusRefkey().isEmpty())) {
			RefBatchActionStatusDO theRefBatchActionStatusDO = referenceTableHelper.getRefBatchActionStatusValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					reqBatchEntityToProcessDO.getBatchActionStatusRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBatchActionStatusDO) {

				if (null == reqBatchEntityToProcessDO.getBatchActionStatusRefValue()) {
					reqBatchEntityToProcessDO.setBatchActionStatusRefValue(theRefBatchActionStatusDO.getValue());
				} else if (!(reqBatchEntityToProcessDO.getBatchActionStatusRefValue()
						.equals(theRefBatchActionStatusDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11068",
							"Validation error : Recieved " + reqBatchEntityToProcessDO.getBatchActionStatusRefkey()
									+ "-" + reqBatchEntityToProcessDO.getBatchActionStatusRefValue()
									+ " as batchActionStatusRefkey- batchActionStatusRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11068",
						"Validation error : Recieved " + reqBatchEntityToProcessDO.getBatchActionStatusRefkey()
								+ " as batchActionStatusRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in BatchEntityToProcessComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteBatchEntityToProcessCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in BatchEntityToProcessComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteBatchEntityToProcessCompMerge(BatchEntityToProcessDO reqBatchEntityToProcessDO,
			BatchEntityToProcessDO dbimageBatchEntityToProcessDO, TxnTransferObj txnTransferObj) {
		if (dbimageBatchEntityToProcessDO.getEntityObjectTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10118",
					"Recieved empty string for batchEntityToProcessDO.entityObjectTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageBatchEntityToProcessDO.getEntityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10119",
					"Recieved empty string for batchEntityToProcessDO.entityIdpk, this attribute cannot be updated to null");
		}

		if (dbimageBatchEntityToProcessDO.getBatchProposedActionRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10120",
					"Recieved empty string for batchEntityToProcessDO.batchProposedActionRefkey, this attribute cannot be updated to null");
		}

		if (dbimageBatchEntityToProcessDO.getBatchActionStatusRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10121",
					"Recieved empty string for batchEntityToProcessDO.batchActionStatusRefkey, this attribute cannot be updated to null");
		}

		if (dbimageBatchEntityToProcessDO.getEntryMadeBySubsystemName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10122",
					"Recieved empty string for batchEntityToProcessDO.entryMadeBySubsystemName, this attribute cannot be updated to null");
		}
		
		// EntityObjectTypeRefkey
				if (!(null == reqBatchEntityToProcessDO.getEntityObjectTypeRefkey()
						|| reqBatchEntityToProcessDO.getEntityObjectTypeRefkey().isEmpty())) {
					RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
							txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqBatchEntityToProcessDO.getEntityObjectTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
					if (null != theRefEntityObjectTypeDO) {

						if (null == reqBatchEntityToProcessDO.getEntityObjectTypeRefValue()) {
							reqBatchEntityToProcessDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
						} else if (!(reqBatchEntityToProcessDO.getEntityObjectTypeRefValue()
								.equals(theRefEntityObjectTypeDO.getValue()))) {
							throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11066",
									"Validation error : Recieved " + reqBatchEntityToProcessDO.getEntityObjectTypeRefkey() + "-"
											+ reqBatchEntityToProcessDO.getEntityObjectTypeRefValue()
											+ " as EntityObjectTypeRefkey- EntityObjectTypeRefValue pair in request which failed validation");
						}

					} else {
						throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11066",
								"Validation error : Recieved " + reqBatchEntityToProcessDO.getEntityObjectTypeRefkey()
										+ " as EntityObjectTypeRefkey in request which failed validation");
					}
				}

				// batchProposedActionRefkey
				if (!(null == reqBatchEntityToProcessDO.getBatchProposedActionRefkey()
						|| reqBatchEntityToProcessDO.getBatchProposedActionRefkey().isEmpty())) {
					RefBatchProposedActionDO theRefBatchProposedActionDO = referenceTableHelper
							.getRefBatchProposedActionValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
									reqBatchEntityToProcessDO.getBatchProposedActionRefkey(),
									yugandharConstants.FILTER_VALUE_ACTIVE);
					if (null != theRefBatchProposedActionDO) {

						if (null == reqBatchEntityToProcessDO.getBatchProposedActionRefValue()) {
							reqBatchEntityToProcessDO.setBatchProposedActionRefValue(theRefBatchProposedActionDO.getValue());
						} else if (!(reqBatchEntityToProcessDO.getBatchProposedActionRefValue()
								.equals(theRefBatchProposedActionDO.getValue()))) {
							throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11067",
									"Validation error : Recieved " + reqBatchEntityToProcessDO.getBatchProposedActionRefkey()
											+ "-" + reqBatchEntityToProcessDO.getBatchProposedActionRefValue()
											+ " as batchProposedActionRefkey- batchProposedActionRefValue pair in request which failed validation");
						}

					} else {
						throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11067",
								"Validation error : Recieved " + reqBatchEntityToProcessDO.getBatchProposedActionRefkey()
										+ " as batchProposedActionRefkey in request which failed validation");
					}
				}

				// batchActionStatusRefkey
				if (!(null == reqBatchEntityToProcessDO.getBatchActionStatusRefkey()
						|| reqBatchEntityToProcessDO.getBatchActionStatusRefkey().isEmpty())) {
					RefBatchActionStatusDO theRefBatchActionStatusDO = referenceTableHelper.getRefBatchActionStatusValueByKey(
							txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqBatchEntityToProcessDO.getBatchActionStatusRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
					if (null != theRefBatchActionStatusDO) {

						if (null == reqBatchEntityToProcessDO.getBatchActionStatusRefValue()) {
							reqBatchEntityToProcessDO.setBatchActionStatusRefValue(theRefBatchActionStatusDO.getValue());
						} else if (!(reqBatchEntityToProcessDO.getBatchActionStatusRefValue()
								.equals(theRefBatchActionStatusDO.getValue()))) {
							throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11068",
									"Validation error : Recieved " + reqBatchEntityToProcessDO.getBatchActionStatusRefkey()
											+ "-" + reqBatchEntityToProcessDO.getBatchActionStatusRefValue()
											+ " as batchActionStatusRefkey- batchActionStatusRefValue pair in request which failed validation");
						}

					} else {
						throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11068",
								"Validation error : Recieved " + reqBatchEntityToProcessDO.getBatchActionStatusRefkey()
										+ " as batchActionStatusRefkey in request which failed validation");
					}
				}

	}

	/**
	 * Pre execute rule for merge in BatchEntityToProcessComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteBatchEntityToProcessCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in BatchEntityToProcessComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteBatchEntityToProcessCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
