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
import com.yugandhar.mdm.extern.dobj.MatchCandidateLeRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefMatchActionstatusDO;
import com.yugandhar.mdm.extern.dobj.RefMatchProposedActionDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model MatchCandidateLeRegistryDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class MatchCandidateLeRegistryComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	MatchCandidateLeRegistryComponentRule theMatchCandidateLeRegistryComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public MatchCandidateLeRegistryComponent() {
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
	 *             if MatchCandidateLeRegistryDO object is not present in the
	 *             request or other mandatory attributes not present
	 *
	 */

	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theMatchCandidateLeRegistryComponentRule.prevalidateMatchCandidateLeRegistryCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			MatchCandidateLeRegistryDO reqMatchCandidateLeRegistryDO = (MatchCandidateLeRegistryDO) txnTransferObj
					.getTxnPayload().getMatchCandidateLeRegistryDO();
			if (null == reqMatchCandidateLeRegistryDO.getPrimaryKeyDO()
					|| null == reqMatchCandidateLeRegistryDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqMatchCandidateLeRegistryDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqMatchCandidateLeRegistryDO.setIdPk(reqMatchCandidateLeRegistryDO.getPrimaryKeyDO().getIdPk());
				MatchCandidateLeRegistryDO dbimageMatchCandidateLeRegistryDO = entityManager
						.find(MatchCandidateLeRegistryDO.class, reqMatchCandidateLeRegistryDO.getIdPk());
				if (null != dbimageMatchCandidateLeRegistryDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"MatchCandidateLeRegistryComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theMatchCandidateLeRegistryComponentRule
					.preExecuteMatchCandidateLeRegistryCompPersist(reqMatchCandidateLeRegistryDO, txnTransferObj);
			entityManager.persist(reqMatchCandidateLeRegistryDO);
			entityManager.flush();
			reqMatchCandidateLeRegistryDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setMatchCandidateLeRegistryDO(new MatchCandidateLeRegistryDO(reqMatchCandidateLeRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO());
			theMatchCandidateLeRegistryComponentRule.postExecuteMatchCandidateLeRegistryCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"MatchCandidateLeRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"MatchCandidateLeRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MatchCandidateLeRegistryComponent.persist failed unexpectedly");
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
	 *             if MatchCandidateLeRegistryDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theMatchCandidateLeRegistryComponentRule.PrevalidateMatchCandidateLeRegistryCompMerge(txnTransferObj);
			MatchCandidateLeRegistryDO reqMatchCandidateLeRegistryDO = (MatchCandidateLeRegistryDO) txnTransferObj
					.getTxnPayload().getMatchCandidateLeRegistryDO();
			MatchCandidateLeRegistryDO dbimageMatchCandidateLeRegistryDO = entityManager
					.find(MatchCandidateLeRegistryDO.class, reqMatchCandidateLeRegistryDO.getIdPk());
			if (null == dbimageMatchCandidateLeRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"MatchCandidateLeRegistryComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqMatchCandidateLeRegistryDO);
			BeanUtils.copyProperties(reqMatchCandidateLeRegistryDO, dbimageMatchCandidateLeRegistryDO,
					strIgnoreProperties);
			entityManager.detach(dbimageMatchCandidateLeRegistryDO);
			theMatchCandidateLeRegistryComponentRule.preExecuteMatchCandidateLeRegistryCompMerge(
					reqMatchCandidateLeRegistryDO, dbimageMatchCandidateLeRegistryDO, txnTransferObj);
			MatchCandidateLeRegistryDO mergedMatchCandidateLeRegistryDO = entityManager
					.merge(dbimageMatchCandidateLeRegistryDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setMatchCandidateLeRegistryDO(new MatchCandidateLeRegistryDO(mergedMatchCandidateLeRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theMatchCandidateLeRegistryComponentRule.postExecuteMatchCandidateLeRegistryCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in MatchCandidateLeRegistryComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"MatchCandidateLeRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"MatchCandidateLeRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MatchCandidateLeRegistryComponent.merge failed unexpectedly");
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
	 *             if MatchCandidateLeRegistryDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theMatchCandidateLeRegistryComponentRule.prevalidateMatchCandidateLeRegistryCompFindById(txnTransferObj);
			MatchCandidateLeRegistryDO reqMatchCandidateLeRegistryDO = (MatchCandidateLeRegistryDO) txnTransferObj
					.getTxnPayload().getMatchCandidateLeRegistryDO();
			MatchCandidateLeRegistryDO dbimageMatchCandidateLeRegistryDO = entityManager
					.find(MatchCandidateLeRegistryDO.class, reqMatchCandidateLeRegistryDO.getIdPk());
			if (null == dbimageMatchCandidateLeRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"MatchCandidateLeRegistryComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setMatchCandidateLeRegistryDO(new MatchCandidateLeRegistryDO(dbimageMatchCandidateLeRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theMatchCandidateLeRegistryComponentRule
					.postExecuteMatchCandidateLeRegistryCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MatchCandidateLeRegistryComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting
	 * MatchCandidateLeRegistryDO object in the database. populate
	 * createdTimestamp, updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MatchCandidateLeRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MatchCandidateLeRegistryDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10101",
					"matchCandidateLeRegistryDO.legalentityIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getCandidateLegalentityidpk()
				|| txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getCandidateLegalentityidpk()
						.isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10102",
					"matchCandidateLeRegistryDO.candidateLegalentityidpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getMatchPattern()
				|| txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getMatchPattern().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10103",
					"matchCandidateLeRegistryDO.matchPattern is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getMatchProposedActionRefkey()
				|| txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getMatchProposedActionRefkey()
						.isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10104",
					"matchCandidateLeRegistryDO.matchProposedActionRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getMatchActionstatusRefkey()
				|| txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getMatchActionstatusRefkey()
						.isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10105",
					"matchCandidateLeRegistryDO.matchActionstatusRefkey is needed in the request");
		}

		MatchCandidateLeRegistryDO theMatchCandidateLeRegistryDO = (MatchCandidateLeRegistryDO) txnTransferObj
				.getTxnPayload().getMatchCandidateLeRegistryDO();
		theMatchCandidateLeRegistryDO.setCreatedTs(new Date());
		theMatchCandidateLeRegistryDO.setUpdatedTs(new Date());
		theMatchCandidateLeRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theMatchCandidateLeRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating MatchCandidateLeRegistryDO
	 * object in the database. populate updatedTimestamp, transaction reference
	 * Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MatchCandidateLeRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MatchCandidateLeRegistryDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"MatchCandidateLeRegistryDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"MatchCandidateLeRegistryDO.version should not be null");
		}

		MatchCandidateLeRegistryDO theMatchCandidateLeRegistryDO = (MatchCandidateLeRegistryDO) txnTransferObj
				.getTxnPayload().getMatchCandidateLeRegistryDO();
		theMatchCandidateLeRegistryDO.setUpdatedTs(new Date());
		theMatchCandidateLeRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theMatchCandidateLeRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that MatchCandidateLeRegistryDO and idpk is
	 * not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MatchCandidateLeRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MatchCandidateLeRegistryDO is needed in the request");
		}
		MatchCandidateLeRegistryDO theMatchCandidateLeRegistryDO = (MatchCandidateLeRegistryDO) txnTransferObj
				.getTxnPayload().getMatchCandidateLeRegistryDO();
		if (null == theMatchCandidateLeRegistryDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"MatchCandidateLeRegistryDO.idpk should not be null");
		}

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			MatchCandidateLeRegistryDO theMatchCandidateLeRegistryDO) {

		// matchProposedActionRefkey
		if (!(null == theMatchCandidateLeRegistryDO.getMatchProposedActionRefkey()
				|| theMatchCandidateLeRegistryDO.getMatchProposedActionRefkey().isEmpty())) {
			RefMatchProposedActionDO theRefMatchProposedActionDO = referenceTableHelper
					.getRefMatchProposedActionValueByKey(requesterLanguage,
							theMatchCandidateLeRegistryDO.getMatchProposedActionRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefMatchProposedActionDO) {
				theMatchCandidateLeRegistryDO.setMatchProposedActionRefValue(theRefMatchProposedActionDO.getValue());
			}
		}

		// matchActionstatusRefkey
		if (!(null == theMatchCandidateLeRegistryDO.getMatchActionstatusRefkey()
				|| theMatchCandidateLeRegistryDO.getMatchActionstatusRefkey().isEmpty())) {
			RefMatchActionstatusDO theRefMatchActionstatusDO = referenceTableHelper.getRefMatchActionstatusValueByKey(
					requesterLanguage, theMatchCandidateLeRegistryDO.getMatchActionstatusRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefMatchActionstatusDO) {
				theMatchCandidateLeRegistryDO.setMatchActionstatusRefValue(theRefMatchActionstatusDO.getValue());
			}
		}

	}

}
