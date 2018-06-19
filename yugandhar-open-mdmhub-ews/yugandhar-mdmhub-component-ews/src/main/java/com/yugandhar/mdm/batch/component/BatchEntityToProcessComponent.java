package com.yugandhar.mdm.batch.component;
/* Generated Dec 12, 2017 6:24:10 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.IgnoreAttributesUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.BatchEntityToProcessDO;
import com.yugandhar.mdm.extern.dobj.RefBatchActionStatusDO;
import com.yugandhar.mdm.extern.dobj.RefBatchProposedActionDO;
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model BatchEntityToProcessDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class BatchEntityToProcessComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	BatchEntityToProcessComponentRule theBatchEntityToProcessComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public BatchEntityToProcessComponent() {
	}

	/**
	 * This method creates a record in database. generates the idpk if not
	 * provided in the request and populate the updatedByUser, updatedByTxnId,
	 * createdTsString, updatedTsString attributes.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with persisted instance
	 * @throws YugandharCommonException
	 *             if BatchEntityToProcessDO object is not present in the
	 *             request or other mandatory attributes not present
	 *
	 */

	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theBatchEntityToProcessComponentRule.prevalidateBatchEntityToProcessCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			BatchEntityToProcessDO reqBatchEntityToProcessDO = (BatchEntityToProcessDO) txnTransferObj.getTxnPayload()
					.getBatchEntityToProcessDO();
			if (null == reqBatchEntityToProcessDO.getPrimaryKeyDO()
					|| null == reqBatchEntityToProcessDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqBatchEntityToProcessDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqBatchEntityToProcessDO.setIdPk(reqBatchEntityToProcessDO.getPrimaryKeyDO().getIdPk());
				BatchEntityToProcessDO dbimageBatchEntityToProcessDO = entityManager.find(BatchEntityToProcessDO.class,
						reqBatchEntityToProcessDO.getIdPk());
				if (null != dbimageBatchEntityToProcessDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"BatchEntityToProcessComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theBatchEntityToProcessComponentRule.preExecuteBatchEntityToProcessCompPersist(reqBatchEntityToProcessDO,
					txnTransferObj);
			entityManager.persist(reqBatchEntityToProcessDO);
			entityManager.flush();

			reqBatchEntityToProcessDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setBatchEntityToProcessDO(new BatchEntityToProcessDO(reqBatchEntityToProcessDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getBatchEntityToProcessDO());
			theBatchEntityToProcessComponentRule.postExecuteBatchEntityToProcessCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"BatchEntityToProcessComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"BatchEntityToProcessComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"BatchEntityToProcessComponent.persist failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;
	}

	/**
	 * This service updates the record in database. populate the updatedByUser,
	 * updatedByTxnId, updatedTsString attributes
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if BatchEntityToProcessDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theBatchEntityToProcessComponentRule.PrevalidateBatchEntityToProcessCompMerge(txnTransferObj);
			BatchEntityToProcessDO reqBatchEntityToProcessDO = (BatchEntityToProcessDO) txnTransferObj.getTxnPayload()
					.getBatchEntityToProcessDO();
			BatchEntityToProcessDO dbimageBatchEntityToProcessDO = entityManager.find(BatchEntityToProcessDO.class,
					reqBatchEntityToProcessDO.getIdPk());
			if (null == dbimageBatchEntityToProcessDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"BatchEntityToProcessComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqBatchEntityToProcessDO);
			BeanUtils.copyProperties(reqBatchEntityToProcessDO, dbimageBatchEntityToProcessDO, strIgnoreProperties);
			entityManager.detach(dbimageBatchEntityToProcessDO);
			theBatchEntityToProcessComponentRule.preExecuteBatchEntityToProcessCompMerge(reqBatchEntityToProcessDO,
					dbimageBatchEntityToProcessDO, txnTransferObj);
			BatchEntityToProcessDO mergedBatchEntityToProcessDO = entityManager.merge(dbimageBatchEntityToProcessDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setBatchEntityToProcessDO(new BatchEntityToProcessDO(mergedBatchEntityToProcessDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getBatchEntityToProcessDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theBatchEntityToProcessComponentRule.postExecuteBatchEntityToProcessCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in BatchEntityToProcessComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"BatchEntityToProcessComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"BatchEntityToProcessComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"BatchEntityToProcessComponent.merge failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;
	}

	/**
	 * This method search the database record based on primary key.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if BatchEntityToProcessDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theBatchEntityToProcessComponentRule.prevalidateBatchEntityToProcessCompFindById(txnTransferObj);
			BatchEntityToProcessDO reqBatchEntityToProcessDO = (BatchEntityToProcessDO) txnTransferObj.getTxnPayload()
					.getBatchEntityToProcessDO();
			BatchEntityToProcessDO dbimageBatchEntityToProcessDO = entityManager.find(BatchEntityToProcessDO.class,
					reqBatchEntityToProcessDO.getIdPk());
			if (null == dbimageBatchEntityToProcessDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"BatchEntityToProcessComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setBatchEntityToProcessDO(new BatchEntityToProcessDO(dbimageBatchEntityToProcessDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getBatchEntityToProcessDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theBatchEntityToProcessComponentRule.postExecuteBatchEntityToProcessCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"BatchEntityToProcessComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting BatchEntityToProcessDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if BatchEntityToProcessDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"BatchEntityToProcessDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getEntityObjectTypeRefkey()
				|| txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getEntityObjectTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10118",
					"batchEntityToProcessDO.entityObjectTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getEntityIdpk()
				|| txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getEntityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10119",
					"batchEntityToProcessDO.entityIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getBatchProposedActionRefkey()
				|| txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getBatchProposedActionRefkey()
						.isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10120",
					"batchEntityToProcessDO.batchProposedActionRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getBatchActionStatusRefkey()
				|| txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getBatchActionStatusRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10121",
					"batchEntityToProcessDO.batchActionStatusRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getEntryMadeBySubsystemName()
				|| txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getEntryMadeBySubsystemName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10122",
					"batchEntityToProcessDO.entryMadeBySubsystemName is needed in the request");
		}

		BatchEntityToProcessDO theBatchEntityToProcessDO = (BatchEntityToProcessDO) txnTransferObj.getTxnPayload()
				.getBatchEntityToProcessDO();
		theBatchEntityToProcessDO.setCreatedTs(new Date());
		theBatchEntityToProcessDO.setUpdatedTs(new Date());
		theBatchEntityToProcessDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theBatchEntityToProcessDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating BatchEntityToProcessDO
	 * object in the database. populate updatedTimestamp, transaction reference
	 * Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if BatchEntityToProcessDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"BatchEntityToProcessDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"BatchEntityToProcessDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"BatchEntityToProcessDO.version should not be null");
		}

		BatchEntityToProcessDO theBatchEntityToProcessDO = (BatchEntityToProcessDO) txnTransferObj.getTxnPayload()
				.getBatchEntityToProcessDO();
		theBatchEntityToProcessDO.setUpdatedTs(new Date());
		theBatchEntityToProcessDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theBatchEntityToProcessDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that BatchEntityToProcessDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if BatchEntityToProcessDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getBatchEntityToProcessDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"BatchEntityToProcessDO is needed in the request");
		}
		BatchEntityToProcessDO theBatchEntityToProcessDO = (BatchEntityToProcessDO) txnTransferObj.getTxnPayload()
				.getBatchEntityToProcessDO();
		if (null == theBatchEntityToProcessDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"BatchEntityToProcessDO.idpk should not be null");
		}

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			BatchEntityToProcessDO theBatchEntityToProcessDO) {

		//RefEntityObjectType
		if (!(null == theBatchEntityToProcessDO.getEntityObjectTypeRefkey()
				|| theBatchEntityToProcessDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					requesterLanguage, theBatchEntityToProcessDO.getEntityObjectTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {
				theBatchEntityToProcessDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
			}
		}

		//RefBatchProposedAction
		if (!(null == theBatchEntityToProcessDO.getBatchProposedActionRefkey()
				|| theBatchEntityToProcessDO.getBatchProposedActionRefkey().isEmpty())) {
			RefBatchProposedActionDO theRefBatchProposedActionDO = referenceTableHelper.getRefBatchProposedActionValueByKey(
					requesterLanguage, theBatchEntityToProcessDO.getBatchProposedActionRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBatchProposedActionDO) {
				theBatchEntityToProcessDO.setBatchProposedActionRefValue(theRefBatchProposedActionDO.getValue());
			}
		}
		
		//RefBatchActionStatus
		if (!(null == theBatchEntityToProcessDO.getBatchActionStatusRefkey()
				|| theBatchEntityToProcessDO.getBatchActionStatusRefkey().isEmpty())) {
			RefBatchActionStatusDO theRefBatchActionStatusDO = referenceTableHelper.getRefBatchActionStatusValueByKey(
					requesterLanguage, theBatchEntityToProcessDO.getBatchActionStatusRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBatchActionStatusDO) {
				theBatchEntityToProcessDO.setBatchActionStatusRefValue(theRefBatchActionStatusDO.getValue());
			}
		}

	}

}
