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
import com.yugandhar.mdm.extern.dobj.InactiveLeRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefInactivationReasonDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model InactiveLeRegistryDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class InactiveLeRegistryComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	InactiveLeRegistryComponentRule theInactiveLeRegistryComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public InactiveLeRegistryComponent() {
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
	 *             if InactiveLeRegistryDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */

	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theInactiveLeRegistryComponentRule.prevalidateInactiveLeRegistryCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			InactiveLeRegistryDO reqInactiveLeRegistryDO = (InactiveLeRegistryDO) txnTransferObj.getTxnPayload()
					.getInactiveLeRegistryDO();
			if (null == reqInactiveLeRegistryDO.getPrimaryKeyDO()
					|| null == reqInactiveLeRegistryDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqInactiveLeRegistryDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqInactiveLeRegistryDO.setIdPk(reqInactiveLeRegistryDO.getPrimaryKeyDO().getIdPk());
				InactiveLeRegistryDO dbimageInactiveLeRegistryDO = entityManager.find(InactiveLeRegistryDO.class,
						reqInactiveLeRegistryDO.getIdPk());
				if (null != dbimageInactiveLeRegistryDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"InactiveLeRegistryComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theInactiveLeRegistryComponentRule.preExecuteInactiveLeRegistryCompPersist(reqInactiveLeRegistryDO,
					txnTransferObj);
			entityManager.persist(reqInactiveLeRegistryDO);
			entityManager.flush();
			reqInactiveLeRegistryDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setInactiveLeRegistryDO(new InactiveLeRegistryDO(reqInactiveLeRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getInactiveLeRegistryDO());
			theInactiveLeRegistryComponentRule.postExecuteInactiveLeRegistryCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"InactiveLeRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"InactiveLeRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"InactiveLeRegistryComponent.persist failed unexpectedly");
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
	 *             if InactiveLeRegistryDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theInactiveLeRegistryComponentRule.PrevalidateInactiveLeRegistryCompMerge(txnTransferObj);
			InactiveLeRegistryDO reqInactiveLeRegistryDO = (InactiveLeRegistryDO) txnTransferObj.getTxnPayload()
					.getInactiveLeRegistryDO();
			InactiveLeRegistryDO dbimageInactiveLeRegistryDO = entityManager.find(InactiveLeRegistryDO.class,
					reqInactiveLeRegistryDO.getIdPk());
			if (null == dbimageInactiveLeRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"InactiveLeRegistryComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqInactiveLeRegistryDO);
			BeanUtils.copyProperties(reqInactiveLeRegistryDO, dbimageInactiveLeRegistryDO, strIgnoreProperties);
			entityManager.detach(dbimageInactiveLeRegistryDO);
			theInactiveLeRegistryComponentRule.preExecuteInactiveLeRegistryCompMerge(reqInactiveLeRegistryDO,
					dbimageInactiveLeRegistryDO, txnTransferObj);
			InactiveLeRegistryDO mergedInactiveLeRegistryDO = entityManager.merge(dbimageInactiveLeRegistryDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setInactiveLeRegistryDO(new InactiveLeRegistryDO(mergedInactiveLeRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getInactiveLeRegistryDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theInactiveLeRegistryComponentRule.postExecuteInactiveLeRegistryCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in InactiveLeRegistryComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"InactiveLeRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"InactiveLeRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"InactiveLeRegistryComponent.merge failed unexpectedly");
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
	 *             if InactiveLeRegistryDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theInactiveLeRegistryComponentRule.prevalidateInactiveLeRegistryCompFindById(txnTransferObj);
			InactiveLeRegistryDO reqInactiveLeRegistryDO = (InactiveLeRegistryDO) txnTransferObj.getTxnPayload()
					.getInactiveLeRegistryDO();
			InactiveLeRegistryDO dbimageInactiveLeRegistryDO = entityManager.find(InactiveLeRegistryDO.class,
					reqInactiveLeRegistryDO.getIdPk());
			if (null == dbimageInactiveLeRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"InactiveLeRegistryComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setInactiveLeRegistryDO(new InactiveLeRegistryDO(dbimageInactiveLeRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getInactiveLeRegistryDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theInactiveLeRegistryComponentRule.postExecuteInactiveLeRegistryCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"InactiveLeRegistryComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting InactiveLeRegistryDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if InactiveLeRegistryDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getInactiveLeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"InactiveLeRegistryDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10098",
					"inactiveLeRegistryDO.legalentityIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getInactivatedTs()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10099",
					"inactiveLeRegistryDO.inactivatedTs is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getInactivationReasonRefkey()
				|| txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getInactivationReasonRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10100",
					"inactiveLeRegistryDO.inactivationReasonRefkey is needed in the request");
		}

		InactiveLeRegistryDO theInactiveLeRegistryDO = (InactiveLeRegistryDO) txnTransferObj.getTxnPayload()
				.getInactiveLeRegistryDO();
		theInactiveLeRegistryDO.setCreatedTs(new Date());
		theInactiveLeRegistryDO.setUpdatedTs(new Date());
		theInactiveLeRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theInactiveLeRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating InactiveLeRegistryDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if InactiveLeRegistryDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getInactiveLeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"InactiveLeRegistryDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"InactiveLeRegistryDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"InactiveLeRegistryDO.version should not be null");
		}

		InactiveLeRegistryDO theInactiveLeRegistryDO = (InactiveLeRegistryDO) txnTransferObj.getTxnPayload()
				.getInactiveLeRegistryDO();
		theInactiveLeRegistryDO.setUpdatedTs(new Date());
		theInactiveLeRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theInactiveLeRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that InactiveLeRegistryDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if InactiveLeRegistryDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getInactiveLeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"InactiveLeRegistryDO is needed in the request");
		}
		InactiveLeRegistryDO theInactiveLeRegistryDO = (InactiveLeRegistryDO) txnTransferObj.getTxnPayload()
				.getInactiveLeRegistryDO();
		if (null == theInactiveLeRegistryDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"InactiveLeRegistryDO.idpk should not be null");
		}

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			InactiveLeRegistryDO theInactiveLeRegistryDO) {

		// inactivationReasonRefkey
		if (!(null == theInactiveLeRegistryDO.getInactivationReasonRefkey()
				|| theInactiveLeRegistryDO.getInactivationReasonRefkey().isEmpty())) {
			RefInactivationReasonDO theRefInactivationReasonDO = referenceTableHelper
					.getRefInactivationReasonValueByKey(requesterLanguage,
							theInactiveLeRegistryDO.getInactivationReasonRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefInactivationReasonDO) {
				theInactiveLeRegistryDO.setInactivationReasonRefValue(theRefInactivationReasonDO.getValue());
			}
		}

	}

}
