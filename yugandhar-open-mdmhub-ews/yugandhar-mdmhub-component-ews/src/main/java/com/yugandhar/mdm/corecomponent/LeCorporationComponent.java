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
import com.yugandhar.mdm.extern.dobj.LeCorporationDO;
import com.yugandhar.mdm.extern.dobj.RefClassificationCodeDO;
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.extern.dobj.RefIndustryCodeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LeCorporationDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LeCorporationComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeCorporationComponentRule theLeCorporationComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LeCorporationComponent() {
	}

	/**
	 * This method creates a record in database. validates LegalentityIdpk is
	 * present in the LEGALENTITY table populate the updatedByUser,
	 * updatedByTxnId, createdTsString, updatedTsString attributes.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with persisted instance
	 * @throws YugandharCommonException
	 *             if LeCorporationDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLeCorporationComponentRule.prevalidateLeCorporationCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			LeCorporationDO reqLeCorporationDO = (LeCorporationDO) txnTransferObj.getTxnPayload().getLeCorporationDO();
			if (null == reqLeCorporationDO.getPrimaryKeyDO()
					|| null == reqLeCorporationDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLeCorporationDO.setLegalentityIdpk(yugandharKeygenerator.generateKey());
			} else {
				reqLeCorporationDO.setLegalentityIdpk(reqLeCorporationDO.getPrimaryKeyDO().getIdPk());
				LeCorporationDO dbimageLeCorporationDO = entityManager.find(LeCorporationDO.class,
						reqLeCorporationDO.getLegalentityIdpk());
				if (null != dbimageLeCorporationDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LeCorporationComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLeCorporationComponentRule.preExecuteLeCorporationCompPersist(reqLeCorporationDO, txnTransferObj);
			entityManager.persist(reqLeCorporationDO);
			entityManager.flush();
			reqLeCorporationDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setLeCorporationDO(new LeCorporationDO(reqLeCorporationDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeCorporationDO());
			theLeCorporationComponentRule.postExecuteLeCorporationCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeCorporationComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeCorporationComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeCorporationComponent.persist failed unexpectedly");
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
	 *             if LeCorporationDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLeCorporationComponentRule.PrevalidateLeCorporationCompMerge(txnTransferObj);
			LeCorporationDO reqLeCorporationDO = (LeCorporationDO) txnTransferObj.getTxnPayload().getLeCorporationDO();
			LeCorporationDO dbimageLeCorporationDO = entityManager.find(LeCorporationDO.class,
					reqLeCorporationDO.getLegalentityIdpk());
			if (null == dbimageLeCorporationDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeCorporationComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLeCorporationDO);
			BeanUtils.copyProperties(reqLeCorporationDO, dbimageLeCorporationDO, strIgnoreProperties);
			entityManager.detach(dbimageLeCorporationDO);
			theLeCorporationComponentRule.preExecuteLeCorporationCompMerge(reqLeCorporationDO, dbimageLeCorporationDO,
					txnTransferObj);
			LeCorporationDO mergedLeCorporationDO = entityManager.merge(dbimageLeCorporationDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLeCorporationDO(new LeCorporationDO(mergedLeCorporationDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeCorporationDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeCorporationComponentRule.postExecuteLeCorporationCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LeCorporationComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeCorporationComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeCorporationComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeCorporationComponent.merge failed unexpectedly");
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
	 *             if LeCorporationDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLeCorporationComponentRule.prevalidateLeCorporationCompFindById(txnTransferObj);
			LeCorporationDO reqLeCorporationDO = (LeCorporationDO) txnTransferObj.getTxnPayload().getLeCorporationDO();
			LeCorporationDO dbimageLeCorporationDO = entityManager.find(LeCorporationDO.class,
					reqLeCorporationDO.getLegalentityIdpk());
			if (null == dbimageLeCorporationDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeCorporationComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLeCorporationDO(new LeCorporationDO(dbimageLeCorporationDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeCorporationDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeCorporationComponentRule.postExecuteLeCorporationCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeCorporationComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LeCorporationDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeCorporationDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeCorporationDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeCorporationDO is needed in the request");
		}
		LeCorporationDO theLeCorporationDO = (LeCorporationDO) txnTransferObj.getTxnPayload().getLeCorporationDO();
		theLeCorporationDO.setCreatedTs(new Date());
		theLeCorporationDO.setUpdatedTs(new Date());
		theLeCorporationDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeCorporationDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LeCorporationDO object in
	 * the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeCorporationDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeCorporationDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeCorporationDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLeCorporationDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeCorporationDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeCorporationDO.legalentityIdpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLeCorporationDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LeCorporationDO.version should not be null");
		}

		LeCorporationDO theLeCorporationDO = (LeCorporationDO) txnTransferObj.getTxnPayload().getLeCorporationDO();
		theLeCorporationDO.setUpdatedTs(new Date());
		theLeCorporationDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeCorporationDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LeCorporationDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeCorporationDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLeCorporationDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeCorporationDO is needed in the request");
		}
		LeCorporationDO theLeCorporationDO = (LeCorporationDO) txnTransferObj.getTxnPayload().getLeCorporationDO();
		if (null == theLeCorporationDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeCorporationDO.LegalentityIdpk should not be null");
		}

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, LeCorporationDO theLeCorporationDO) {

		// ClassificationCodeRefkey
		if (!(null == theLeCorporationDO.getClassificationCodeRefkey()
				|| theLeCorporationDO.getClassificationCodeRefkey().isEmpty())) {
			RefClassificationCodeDO theRefClassificationCodeDO = referenceTableHelper
					.getRefClassificationCodeValueByKey(requesterLanguage,
							theLeCorporationDO.getClassificationCodeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefClassificationCodeDO) {
				theLeCorporationDO.setClassificationCodeRefValue(theRefClassificationCodeDO.getValue());
			}
		}

		// IndustryCodeRefkey
		if (!(null == theLeCorporationDO.getIndustryCodeRefkey()
				|| theLeCorporationDO.getIndustryCodeRefkey().isEmpty())) {
			RefIndustryCodeDO theRefIndustryCodeDO = referenceTableHelper.getRefIndustryCodeValueByKey(
					requesterLanguage, theLeCorporationDO.getIndustryCodeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefIndustryCodeDO) {
				theLeCorporationDO.setIndustryCodeRefValue(theRefIndustryCodeDO.getValue());
			}
		}

		// CountryRegistrationRefkey (RefCountryIsoDO)
		if (!(null == theLeCorporationDO.getCountryRegistrationRefkey()
				|| theLeCorporationDO.getCountryRegistrationRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryDO = referenceTableHelper.getRefCountryIsoValueByKey(requesterLanguage,
					theLeCorporationDO.getCountryRegistrationRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryDO) {
				theLeCorporationDO.setCountryRegistrationRefValue(theRefCountryDO.getValue());
			}
		}
	}

}
