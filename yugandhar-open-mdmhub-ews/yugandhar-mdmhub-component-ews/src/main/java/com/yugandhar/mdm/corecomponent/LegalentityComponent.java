package com.yugandhar.mdm.corecomponent;
/* Generated Jun 29, 2017 10:39:54 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.corecomponentref.RefEntityObjectTypeComponent;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.RefClassificationCodeDO;
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;
import com.yugandhar.mdm.extern.dobj.RefImportanceTypeDO;
import com.yugandhar.mdm.extern.dobj.RefLeRatingDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.extern.dobj.RefStatusTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LegalentityDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LegalentityComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;


	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	LegalentityComponentRule theLegalentityComponentRule;
	
	@Autowired
	protected RefEntityObjectTypeComponent theRefEntityObjectTypeComponent;
	
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LegalentityComponent() {
		
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
	 *             if LegalentityDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLegalentityComponentRule.prevalidateLegalentityCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			LegalentityDO reqLegalentityDO = (LegalentityDO) txnTransferObj.getTxnPayload().getLegalentityDO();
			if (null == reqLegalentityDO.getPrimaryKeyDO() || null == reqLegalentityDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLegalentityDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLegalentityDO.setIdPk(reqLegalentityDO.getPrimaryKeyDO().getIdPk());
				LegalentityDO dbimageLegalentityDO = entityManager.find(LegalentityDO.class,
						reqLegalentityDO.getIdPk());
				if (null != dbimageLegalentityDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LegalentityComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLegalentityComponentRule.preExecuteLegalentityCompPersist(reqLegalentityDO, txnTransferObj);
			entityManager.persist(reqLegalentityDO);
			entityManager.flush();
			reqLegalentityDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setLegalentityDO(new LegalentityDO(reqLegalentityDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(), respTxnTransferObj.getTxnPayload().getLegalentityDO());
			theLegalentityComponentRule.postExecuteLegalentityCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LegalentityComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LegalentityComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LegalentityComponent.persist failed unexpectedly");
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
	 *             if LegalentityDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLegalentityComponentRule.PrevalidateLegalentityCompMerge(txnTransferObj);
			LegalentityDO reqLegalentityDO = (LegalentityDO) txnTransferObj.getTxnPayload().getLegalentityDO();
			LegalentityDO dbimageLegalentityDO = entityManager.find(LegalentityDO.class, reqLegalentityDO.getIdPk());
			if (null == dbimageLegalentityDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LegalentityComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLegalentityDO);
			BeanUtils.copyProperties(reqLegalentityDO, dbimageLegalentityDO, strIgnoreProperties);
			entityManager.detach(dbimageLegalentityDO);
			theLegalentityComponentRule.preExecuteLegalentityCompMerge(reqLegalentityDO, dbimageLegalentityDO,
					txnTransferObj);
			LegalentityDO mergedLegalentityDO = entityManager.merge(dbimageLegalentityDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLegalentityDO(new LegalentityDO(mergedLegalentityDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLegalentityDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLegalentityComponentRule.postExecuteLegalentityCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LegalentityComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LegalentityComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LegalentityComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LegalentityComponent.merge failed unexpectedly");
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
	 *             if LegalentityDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLegalentityComponentRule.prevalidateLegalentityCompFindById(txnTransferObj);
			LegalentityDO reqLegalentityDO = (LegalentityDO) txnTransferObj.getTxnPayload().getLegalentityDO();
			LegalentityDO dbimageLegalentityDO = entityManager.find(LegalentityDO.class, reqLegalentityDO.getIdPk());
			if (null == dbimageLegalentityDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LegalentityComponent.findbyId failed with Validation Exception - No Record found for given LegalEntityIdPk"); // Record
																																		// not
																																		// found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLegalentityDO(new LegalentityDO(dbimageLegalentityDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLegalentityDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLegalentityComponentRule.postExecuteLegalentityCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LegalentityComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LegalentityDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LegalentityDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLegalentityDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LegalentityDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLegalentityDO().getDisplayName()
				|| txnTransferObj.getTxnPayload().getLegalentityDO().getDisplayName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10001",
					"LegalentityDO.displayName is required in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLegalentityDO().getEntityObjectTypeRefkey()
				|| txnTransferObj.getTxnPayload().getLegalentityDO().getEntityObjectTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10002",
					"LegalentityDO.entityObjectTypeRefkey is required in the request");
		}

		LegalentityDO theLegalentityDO = (LegalentityDO) txnTransferObj.getTxnPayload().getLegalentityDO();
		theLegalentityDO.setCreatedTs(new Date());
		theLegalentityDO.setUpdatedTs(new Date());
		theLegalentityDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLegalentityDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LegalentityDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LegalentityDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLegalentityDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LegalentityDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLegalentityDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LegalentityDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLegalentityDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LegalentityDO.version should not be null");
		}

		LegalentityDO theLegalentityDO = (LegalentityDO) txnTransferObj.getTxnPayload().getLegalentityDO();
		theLegalentityDO.setUpdatedTs(new Date());
		theLegalentityDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLegalentityDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LegalentityDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LegalentityDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLegalentityDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LegalentityDO is needed in the request");
		}
		LegalentityDO theLegalentityDO = (LegalentityDO) txnTransferObj.getTxnPayload().getLegalentityDO();
		if (null == theLegalentityDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LegalentityDO.idpk should not be null");
		}

	}



	protected void populateReferenceDataAttributes(String requesterLanguage, LegalentityDO theLegalentityDO) {

		// entityObjectTypeRefkey
		if (!(null == theLegalentityDO.getEntityObjectTypeRefkey()
				|| theLegalentityDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					requesterLanguage, theLegalentityDO.getEntityObjectTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {
				theLegalentityDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
			}
		}

		// classificationCodeRefkey
		if (!(null == theLegalentityDO.getClassificationCodeRefkey()
				|| theLegalentityDO.getClassificationCodeRefkey().isEmpty())) {
			RefClassificationCodeDO theRefClassificationCodeDO = referenceTableHelper
					.getRefClassificationCodeValueByKey(requesterLanguage,
							theLegalentityDO.getClassificationCodeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefClassificationCodeDO) {
				theLegalentityDO.setClassificationCodeRefValue(theRefClassificationCodeDO.getValue());
			}
		}

		// importanceTypeRefkey
		if (!(null == theLegalentityDO.getImportanceTypeRefkey()
				|| theLegalentityDO.getImportanceTypeRefkey().isEmpty())) {
			RefImportanceTypeDO theRefImportanceTypeDO = referenceTableHelper.getRefImportanceTypeValueByKey(
					requesterLanguage, theLegalentityDO.getImportanceTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefImportanceTypeDO) {
				theLegalentityDO.setImportanceTypeRefValue(theRefImportanceTypeDO.getValue());
			}
		}

		// leRatingRefkey
		if (!(null == theLegalentityDO.getLeRatingRefkey() || theLegalentityDO.getLeRatingRefkey().isEmpty())) {
			RefLeRatingDO theRefLeRatingDO = referenceTableHelper.getRefLeRatingValueByKey(requesterLanguage,
					theLegalentityDO.getLeRatingRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRatingDO) {
				theLegalentityDO.setLeRatingRefValue(theRefLeRatingDO.getValue());
			}
		}

		// statusTypeRefkey
		if (!(null == theLegalentityDO.getStatusTypeRefkey() || theLegalentityDO.getStatusTypeRefkey().isEmpty())) {
			RefStatusTypeDO theRefStatusTypeDO = referenceTableHelper.getRefStatusTypeValueByKey(requesterLanguage,
					theLegalentityDO.getStatusTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefStatusTypeDO) {
				theLegalentityDO.setStatusTypeRefValue(theRefStatusTypeDO.getValue());
			}
		}

		// sourceSystemRefkey
		if (!(null == theLegalentityDO.getSourceSystemRefkey() || theLegalentityDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					requesterLanguage, theLegalentityDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {
				theLegalentityDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
			}
		}

	}
	
	
	
}
