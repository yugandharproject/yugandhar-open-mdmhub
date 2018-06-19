package com.yugandhar.mdm.corecomponent;
/* Generated Jul 1, 2017 2:56:24 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.PhoneStandardizedDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model PhoneStandardizedDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class PhoneStandardizedComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	PhoneStandardizedComponentRule thePhoneStandardizedComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public PhoneStandardizedComponent() {
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
	 *             if PhoneStandardizedDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			thePhoneStandardizedComponentRule.prevalidatePhoneStandardizedCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			PhoneStandardizedDO reqPhoneStandardizedDO = (PhoneStandardizedDO) txnTransferObj.getTxnPayload()
					.getPhoneStandardizedDO();
			if (null == reqPhoneStandardizedDO.getPrimaryKeyDO()
					|| null == reqPhoneStandardizedDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqPhoneStandardizedDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqPhoneStandardizedDO.setIdPk(reqPhoneStandardizedDO.getPrimaryKeyDO().getIdPk());
				PhoneStandardizedDO dbimagePhoneStandardizedDO = entityManager.find(PhoneStandardizedDO.class,
						reqPhoneStandardizedDO.getIdPk());
				if (null != dbimagePhoneStandardizedDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"PhoneStandardizedComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			thePhoneStandardizedComponentRule.preExecutePhoneStandardizedCompPersist(reqPhoneStandardizedDO,
					txnTransferObj);
			entityManager.persist(reqPhoneStandardizedDO);
			entityManager.flush();
			reqPhoneStandardizedDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setPhoneStandardizedDO(new PhoneStandardizedDO(reqPhoneStandardizedDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getPhoneStandardizedDO());
			thePhoneStandardizedComponentRule.postExecutePhoneStandardizedCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"PhoneStandardizedComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"PhoneStandardizedComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"PhoneStandardizedComponent.persist failed unexpectedly");
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
	 *             if PhoneStandardizedDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			thePhoneStandardizedComponentRule.PrevalidatePhoneStandardizedCompMerge(txnTransferObj);
			PhoneStandardizedDO reqPhoneStandardizedDO = (PhoneStandardizedDO) txnTransferObj.getTxnPayload()
					.getPhoneStandardizedDO();
			PhoneStandardizedDO dbimagePhoneStandardizedDO = entityManager.find(PhoneStandardizedDO.class,
					reqPhoneStandardizedDO.getIdPk());
			if (null == dbimagePhoneStandardizedDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"PhoneStandardizedComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqPhoneStandardizedDO);
			BeanUtils.copyProperties(reqPhoneStandardizedDO, dbimagePhoneStandardizedDO, strIgnoreProperties);
			entityManager.detach(dbimagePhoneStandardizedDO);
			thePhoneStandardizedComponentRule.preExecutePhoneStandardizedCompMerge(reqPhoneStandardizedDO,
					dbimagePhoneStandardizedDO, txnTransferObj);
			PhoneStandardizedDO mergedPhoneStandardizedDO = entityManager.merge(dbimagePhoneStandardizedDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setPhoneStandardizedDO(new PhoneStandardizedDO(mergedPhoneStandardizedDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getPhoneStandardizedDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			thePhoneStandardizedComponentRule.postExecutePhoneStandardizedCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in PhoneStandardizedComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"PhoneStandardizedComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"PhoneStandardizedComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"PhoneStandardizedComponent.merge failed unexpectedly");
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
	 *             if PhoneStandardizedDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			thePhoneStandardizedComponentRule.prevalidatePhoneStandardizedCompFindById(txnTransferObj);
			PhoneStandardizedDO reqPhoneStandardizedDO = (PhoneStandardizedDO) txnTransferObj.getTxnPayload()
					.getPhoneStandardizedDO();
			PhoneStandardizedDO dbimagePhoneStandardizedDO = entityManager.find(PhoneStandardizedDO.class,
					reqPhoneStandardizedDO.getIdPk());
			if (null == dbimagePhoneStandardizedDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"PhoneStandardizedComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setPhoneStandardizedDO(new PhoneStandardizedDO(dbimagePhoneStandardizedDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getPhoneStandardizedDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			thePhoneStandardizedComponentRule.postExecutePhoneStandardizedCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"PhoneStandardizedComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting PhoneStandardizedDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if PhoneStandardizedDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getPhoneStandardizedDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"PhoneStandardizedDO is needed in the request");
		}
		PhoneStandardizedDO thePhoneStandardizedDO = (PhoneStandardizedDO) txnTransferObj.getTxnPayload()
				.getPhoneStandardizedDO();
		thePhoneStandardizedDO.setCreatedTs(new Date());
		thePhoneStandardizedDO.setUpdatedTs(new Date());
		thePhoneStandardizedDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		thePhoneStandardizedDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating PhoneStandardizedDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if PhoneStandardizedDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getPhoneStandardizedDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"PhoneStandardizedDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getPhoneStandardizedDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getPhoneStandardizedDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"PhoneStandardizedDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getPhoneStandardizedDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"PhoneStandardizedDO.version should not be null");
		}

		PhoneStandardizedDO thePhoneStandardizedDO = (PhoneStandardizedDO) txnTransferObj.getTxnPayload()
				.getPhoneStandardizedDO();
		thePhoneStandardizedDO.setUpdatedTs(new Date());
		thePhoneStandardizedDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		thePhoneStandardizedDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that PhoneStandardizedDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if PhoneStandardizedDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getPhoneStandardizedDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"PhoneStandardizedDO is needed in the request");
		}
		PhoneStandardizedDO thePhoneStandardizedDO = (PhoneStandardizedDO) txnTransferObj.getTxnPayload()
				.getPhoneStandardizedDO();
		if (null == thePhoneStandardizedDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"PhoneStandardizedDO.idpk should not be null");
		}

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			PhoneStandardizedDO thePhoneStandardizedDO) {

		/*
		 * No Reference lists in this table
		 */

	}

}
