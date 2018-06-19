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
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.IgnoreAttributesUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.EntityGroupDO;
import com.yugandhar.mdm.extern.dobj.RefGroupSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefGroupTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model EntityGroupDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class EntityGroupComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	EntityGroupComponentRule theEntityGroupComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public EntityGroupComponent() {
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
	 *             if EntityGroupDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theEntityGroupComponentRule.prevalidateEntityGroupCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			EntityGroupDO reqEntityGroupDO = (EntityGroupDO) txnTransferObj.getTxnPayload().getEntityGroupDO();
			if (null == reqEntityGroupDO.getPrimaryKeyDO() || null == reqEntityGroupDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqEntityGroupDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				 reqEntityGroupDO.setIdPk( reqEntityGroupDO.getPrimaryKeyDO().getIdPk());
				EntityGroupDO dbimageEntityGroupDO = entityManager.find(EntityGroupDO.class,
						reqEntityGroupDO.getIdPk());
				if (null != dbimageEntityGroupDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"EntityGroupComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theEntityGroupComponentRule.preExecuteEntityGroupCompPersist(reqEntityGroupDO, txnTransferObj);
			entityManager.persist(reqEntityGroupDO);
			entityManager.flush();
			reqEntityGroupDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setEntityGroupDO(new EntityGroupDO(reqEntityGroupDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(), respTxnTransferObj.getTxnPayload().getEntityGroupDO());
			theEntityGroupComponentRule.postExecuteEntityGroupCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"EntityGroupComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"EntityGroupComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"EntityGroupComponent.persist failed unexpectedly");
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
	 *             if EntityGroupDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theEntityGroupComponentRule.PrevalidateEntityGroupCompMerge(txnTransferObj);
			EntityGroupDO reqEntityGroupDO = (EntityGroupDO) txnTransferObj.getTxnPayload().getEntityGroupDO();
			EntityGroupDO dbimageEntityGroupDO = entityManager.find(EntityGroupDO.class, reqEntityGroupDO.getIdPk());
			if (null == dbimageEntityGroupDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"EntityGroupComponent.merge failed with Validation Exception"); 
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqEntityGroupDO);
			BeanUtils.copyProperties(reqEntityGroupDO, dbimageEntityGroupDO, strIgnoreProperties);
			entityManager.detach(dbimageEntityGroupDO);
			theEntityGroupComponentRule.preExecuteEntityGroupCompMerge(reqEntityGroupDO, dbimageEntityGroupDO,
					txnTransferObj);
			EntityGroupDO mergedEntityGroupDO = entityManager.merge(dbimageEntityGroupDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setEntityGroupDO(new EntityGroupDO(mergedEntityGroupDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getEntityGroupDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theEntityGroupComponentRule.postExecuteEntityGroupCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in EntityGroupComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"EntityGroupComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"EntityGroupComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"EntityGroupComponent.merge failed unexpectedly");
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
	 *             if EntityGroupDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theEntityGroupComponentRule.prevalidateEntityGroupCompFindById(txnTransferObj);
			EntityGroupDO reqEntityGroupDO = (EntityGroupDO) txnTransferObj.getTxnPayload().getEntityGroupDO();
			EntityGroupDO dbimageEntityGroupDO = entityManager.find(EntityGroupDO.class, reqEntityGroupDO.getIdPk());
			if (null == dbimageEntityGroupDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"EntityGroupComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setEntityGroupDO(new EntityGroupDO(dbimageEntityGroupDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getEntityGroupDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theEntityGroupComponentRule.postExecuteEntityGroupCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"EntityGroupComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting EntityGroupDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if EntityGroupDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getEntityGroupDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"EntityGroupDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getEntityGroupDO().getGroupTypeRefkey()
				|| txnTransferObj.getTxnPayload().getEntityGroupDO().getGroupTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10043",
					"EntityGroupDO.groupTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getEntityGroupDO().getGroupName()
				|| txnTransferObj.getTxnPayload().getEntityGroupDO().getGroupName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10044",
					"EntityGroupDO.groupName is needed in the request");
		}

		EntityGroupDO theEntityGroupDO = (EntityGroupDO) txnTransferObj.getTxnPayload().getEntityGroupDO();
		theEntityGroupDO.setCreatedTs(new Date());
		theEntityGroupDO.setUpdatedTs(new Date());
		theEntityGroupDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theEntityGroupDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating EntityGroupDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if EntityGroupDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getEntityGroupDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"EntityGroupDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getEntityGroupDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getEntityGroupDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"EntityGroupDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getEntityGroupDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"EntityGroupDO.version should not be null");
		}

		EntityGroupDO theEntityGroupDO = (EntityGroupDO) txnTransferObj.getTxnPayload().getEntityGroupDO();
		theEntityGroupDO.setUpdatedTs(new Date());
		theEntityGroupDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theEntityGroupDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that EntityGroupDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if EntityGroupDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getEntityGroupDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"EntityGroupDO is needed in the request");
		}
		EntityGroupDO theEntityGroupDO = (EntityGroupDO) txnTransferObj.getTxnPayload().getEntityGroupDO();
		if (null == theEntityGroupDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"EntityGroupDO.idpk should not be null");
		}

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, EntityGroupDO theEntityGroupDO) {
		// GroupTypeRefkey
		if (!(null == theEntityGroupDO.getGroupTypeRefkey()
				|| theEntityGroupDO.getGroupTypeRefkey().isEmpty())) {
			RefGroupTypeDO theRefGroupTypeDO = referenceTableHelper.getRefGroupTypeValueByKey(requesterLanguage,
					theEntityGroupDO.getGroupTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefGroupTypeDO) {
				theEntityGroupDO.setGroupTypeRefValue(theRefGroupTypeDO.getValue());
			}
		}
		
		// GroupSubtypeRefkey
		if (!(null == theEntityGroupDO.getGroupSubtypeRefkey()
				|| theEntityGroupDO.getGroupSubtypeRefkey().isEmpty())) {
			RefGroupSubtypeDO theRefGroupSubtypeDO = referenceTableHelper.getRefGroupSubtypeValueByKey(requesterLanguage,
					theEntityGroupDO.getGroupSubtypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefGroupSubtypeDO) {
				theEntityGroupDO.setGroupSubtypeRefValue(theRefGroupSubtypeDO.getValue());
			}
		}

	}

}
