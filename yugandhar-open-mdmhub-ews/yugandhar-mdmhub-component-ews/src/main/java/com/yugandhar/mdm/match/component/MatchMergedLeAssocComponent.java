package com.yugandhar.mdm.match.component;
/* Generated Oct 27, 2017 5:20:01 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.MatchMergedLeAssocDO;
import com.yugandhar.mdm.extern.dobj.RefMergeReasonDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model MatchMergedLeAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class MatchMergedLeAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	MatchMergedLeAssocComponentRule theMatchMergedLeAssocComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public MatchMergedLeAssocComponent() {
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
	 *             if MatchMergedLeAssocDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */

	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theMatchMergedLeAssocComponentRule.prevalidateMatchMergedLeAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			MatchMergedLeAssocDO reqMatchMergedLeAssocDO = (MatchMergedLeAssocDO) txnTransferObj.getTxnPayload()
					.getMatchMergedLeAssocDO();
			if (null == reqMatchMergedLeAssocDO.getPrimaryKeyDO()
					|| null == reqMatchMergedLeAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqMatchMergedLeAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqMatchMergedLeAssocDO.setIdPk(reqMatchMergedLeAssocDO.getPrimaryKeyDO().getIdPk());
				MatchMergedLeAssocDO dbimageMatchMergedLeAssocDO = entityManager.find(MatchMergedLeAssocDO.class,
						reqMatchMergedLeAssocDO.getIdPk());
				if (null != dbimageMatchMergedLeAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"MatchMergedLeAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theMatchMergedLeAssocComponentRule.preExecuteMatchMergedLeAssocCompPersist(reqMatchMergedLeAssocDO,
					txnTransferObj);
			entityManager.persist(reqMatchMergedLeAssocDO);
			entityManager.flush();
			reqMatchMergedLeAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setMatchMergedLeAssocDO(new MatchMergedLeAssocDO(reqMatchMergedLeAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getMatchMergedLeAssocDO());
			theMatchMergedLeAssocComponentRule.postExecuteMatchMergedLeAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"MatchMergedLeAssocComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"MatchMergedLeAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MatchMergedLeAssocComponent.persist failed unexpectedly");
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
	 *             if MatchMergedLeAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theMatchMergedLeAssocComponentRule.PrevalidateMatchMergedLeAssocCompMerge(txnTransferObj);
			MatchMergedLeAssocDO reqMatchMergedLeAssocDO = (MatchMergedLeAssocDO) txnTransferObj.getTxnPayload()
					.getMatchMergedLeAssocDO();
			MatchMergedLeAssocDO dbimageMatchMergedLeAssocDO = entityManager.find(MatchMergedLeAssocDO.class,
					reqMatchMergedLeAssocDO.getIdPk());
			if (null == dbimageMatchMergedLeAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"MatchMergedLeAssocComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqMatchMergedLeAssocDO);
			BeanUtils.copyProperties(reqMatchMergedLeAssocDO, dbimageMatchMergedLeAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageMatchMergedLeAssocDO);
			theMatchMergedLeAssocComponentRule.preExecuteMatchMergedLeAssocCompMerge(reqMatchMergedLeAssocDO,
					dbimageMatchMergedLeAssocDO, txnTransferObj);
			MatchMergedLeAssocDO mergedMatchMergedLeAssocDO = entityManager.merge(dbimageMatchMergedLeAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setMatchMergedLeAssocDO(new MatchMergedLeAssocDO(mergedMatchMergedLeAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getMatchMergedLeAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theMatchMergedLeAssocComponentRule.postExecuteMatchMergedLeAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in MatchMergedLeAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"MatchMergedLeAssocComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"MatchMergedLeAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MatchMergedLeAssocComponent.merge failed unexpectedly");
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
	 *             if MatchMergedLeAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theMatchMergedLeAssocComponentRule.prevalidateMatchMergedLeAssocCompFindById(txnTransferObj);
			MatchMergedLeAssocDO reqMatchMergedLeAssocDO = (MatchMergedLeAssocDO) txnTransferObj.getTxnPayload()
					.getMatchMergedLeAssocDO();
			MatchMergedLeAssocDO dbimageMatchMergedLeAssocDO = entityManager.find(MatchMergedLeAssocDO.class,
					reqMatchMergedLeAssocDO.getIdPk());
			if (null == dbimageMatchMergedLeAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"MatchMergedLeAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setMatchMergedLeAssocDO(new MatchMergedLeAssocDO(dbimageMatchMergedLeAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getMatchMergedLeAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theMatchMergedLeAssocComponentRule.postExecuteMatchMergedLeAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MatchMergedLeAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting MatchMergedLeAssocDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MatchMergedLeAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MatchMergedLeAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getSurvivorLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getSurvivorLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10106",
					"matchMergedLeAssocDO.survivorLegalentityIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getSurvivorLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getSurvivorLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10107",
					"matchMergedLeAssocDO.mergedLegalentityIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getSurvivorLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getSurvivorLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10108",
					"matchMergedLeAssocDO.mergeReasonRefkey is needed in the request");
		}

		MatchMergedLeAssocDO theMatchMergedLeAssocDO = (MatchMergedLeAssocDO) txnTransferObj.getTxnPayload()
				.getMatchMergedLeAssocDO();
		theMatchMergedLeAssocDO.setCreatedTs(new Date());
		theMatchMergedLeAssocDO.setUpdatedTs(new Date());
		theMatchMergedLeAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theMatchMergedLeAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating MatchMergedLeAssocDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MatchMergedLeAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MatchMergedLeAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"MatchMergedLeAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"MatchMergedLeAssocDO.version should not be null");
		}

		MatchMergedLeAssocDO theMatchMergedLeAssocDO = (MatchMergedLeAssocDO) txnTransferObj.getTxnPayload()
				.getMatchMergedLeAssocDO();
		theMatchMergedLeAssocDO.setUpdatedTs(new Date());
		theMatchMergedLeAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theMatchMergedLeAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that MatchMergedLeAssocDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MatchMergedLeAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MatchMergedLeAssocDO is needed in the request");
		}
		MatchMergedLeAssocDO theMatchMergedLeAssocDO = (MatchMergedLeAssocDO) txnTransferObj.getTxnPayload()
				.getMatchMergedLeAssocDO();
		if (null == theMatchMergedLeAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"MatchMergedLeAssocDO.idpk should not be null");
		}

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			MatchMergedLeAssocDO theMatchMergedLeAssocDO) {

		// mergeReasonRefkey
		if (!(null == theMatchMergedLeAssocDO.getMergeReasonRefkey()
				|| theMatchMergedLeAssocDO.getMergeReasonRefkey().isEmpty())) {
			RefMergeReasonDO theRefMergeReasonDO = referenceTableHelper.getRefMergeReasonValueByKey(requesterLanguage,
					theMatchMergedLeAssocDO.getMergeReasonRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefMergeReasonDO) {
				theMatchMergedLeAssocDO.setMergeReasonRefValue(theRefMergeReasonDO.getValue());
			}
		}

	}

}
