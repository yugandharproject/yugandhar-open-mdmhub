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
import com.yugandhar.mdm.extern.dobj.LePersonDO;
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.extern.dobj.RefGenderDO;
import com.yugandhar.mdm.extern.dobj.RefHighestEduQualDO;
import com.yugandhar.mdm.extern.dobj.RefLanguageCodeDO;
import com.yugandhar.mdm.extern.dobj.RefPersonTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LePersonDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LePersonComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LePersonComponentRule theLePersonComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LePersonComponent() {
	}

	/**
	 * This method creates a record in database. validates if the
	 * LegalentityIdpk is present in the LEGALENTITY table populate the
	 * updatedByUser, updatedByTxnId, createdTsString, updatedTsString
	 * attributes.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with persisted instance
	 * @throws YugandharCommonException
	 *             if LePersonDO object is not present in the request or other
	 *             mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLePersonComponentRule.prevalidateLePersonCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			LePersonDO reqLePersonDO = (LePersonDO) txnTransferObj.getTxnPayload().getLePersonDO();
			if (null == reqLePersonDO.getPrimaryKeyDO() || null == reqLePersonDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLePersonDO.setLegalentityIdpk(yugandharKeygenerator.generateKey());
			} else {
				reqLePersonDO.setLegalentityIdpk(reqLePersonDO.getPrimaryKeyDO().getIdPk());
				LePersonDO dbimageLePersonDO = entityManager.find(LePersonDO.class, reqLePersonDO.getLegalentityIdpk());
				if (null != dbimageLePersonDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LePersonComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLePersonComponentRule.preExecuteLePersonCompPersist(reqLePersonDO, txnTransferObj);
			entityManager.persist(reqLePersonDO);
			entityManager.flush();
			reqLePersonDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setLePersonDO(new LePersonDO(reqLePersonDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(), respTxnTransferObj.getTxnPayload().getLePersonDO());
			theLePersonComponentRule.postExecuteLePersonCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LePersonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LePersonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePersonComponent.persist failed unexpectedly");
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
	 *             if LePersonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLePersonComponentRule.PrevalidateLePersonCompMerge(txnTransferObj);
			LePersonDO reqLePersonDO = (LePersonDO) txnTransferObj.getTxnPayload().getLePersonDO();
			LePersonDO dbimageLePersonDO = entityManager.find(LePersonDO.class, reqLePersonDO.getLegalentityIdpk());
			if (null == dbimageLePersonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LePersonComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLePersonDO);
			BeanUtils.copyProperties(reqLePersonDO, dbimageLePersonDO, strIgnoreProperties);
			entityManager.detach(dbimageLePersonDO);
			theLePersonComponentRule.preExecuteLePersonCompMerge(reqLePersonDO, dbimageLePersonDO, txnTransferObj);
			LePersonDO mergedLePersonDO = entityManager.merge(dbimageLePersonDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLePersonDO(new LePersonDO(mergedLePersonDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePersonDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePersonComponentRule.postExecuteLePersonCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LePersonComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"PersonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"PersonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePersonComponent.merge failed unexpectedly");
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
	 *             if LePersonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLePersonComponentRule.prevalidateLePersonCompFindById(txnTransferObj);
			LePersonDO reqLePersonDO = (LePersonDO) txnTransferObj.getTxnPayload().getLePersonDO();
			LePersonDO dbimageLePersonDO = entityManager.find(LePersonDO.class, reqLePersonDO.getLegalentityIdpk());
			if (null == dbimageLePersonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LePersonComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLePersonDO(new LePersonDO(dbimageLePersonDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePersonDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePersonComponentRule.postExecuteLePersonCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePersonComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LePersonDO object in the
	 * database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePersonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLePersonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePersonDO is needed in the request");
		}
		
		if (null == txnTransferObj.getTxnPayload().getLePersonDO().getGenderRefkey()
				|| txnTransferObj.getTxnPayload().getLePersonDO().getGenderRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10085",
					"lePersonDO.genderRefkey is needed in the request");
		}
		
		LePersonDO theLePersonDO = (LePersonDO) txnTransferObj.getTxnPayload().getLePersonDO();
		theLePersonDO.setCreatedTs(new Date());
		theLePersonDO.setUpdatedTs(new Date());
		theLePersonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLePersonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LePersonDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePersonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLePersonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePersonDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLePersonDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLePersonDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LePersonDO.LegalentityIdpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLePersonDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LePersonDO.version should not be null");
		}

		LePersonDO theLePersonDO = (LePersonDO) txnTransferObj.getTxnPayload().getLePersonDO();
		theLePersonDO.setUpdatedTs(new Date());
		theLePersonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLePersonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LePersonDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePersonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLePersonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePersonDO is needed in the request");
		}
		LePersonDO theLePersonDO = (LePersonDO) txnTransferObj.getTxnPayload().getLePersonDO();
		if (null == theLePersonDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LePersonDO.LegalentityIdpk should not be null");
		}

	}

	/*
	 * Sample Code
	 * 
	 * @Autowired protected RefEntityObjectTypeComponent
	 * theRefEntityObjectTypeComponent;
	 */

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, LePersonDO theLePersonDO) {

		// PersonTypeRefkey
		if (!(null == theLePersonDO.getPersonTypeRefkey() || theLePersonDO.getPersonTypeRefkey().isEmpty())) {
			RefPersonTypeDO theRefPersonTypeDO = referenceTableHelper.getRefPersonTypeValueByKey(requesterLanguage,
					theLePersonDO.getPersonTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPersonTypeDO) {
				theLePersonDO.setPersonTypeRefValue(theRefPersonTypeDO.getValue());
			}
		}

		// GenderRefkey
		if (!(null == theLePersonDO.getGenderRefkey() || theLePersonDO.getGenderRefkey().isEmpty())) {
			RefGenderDO theRefGenderDO = referenceTableHelper.getRefGenderValueByKey(requesterLanguage,
					theLePersonDO.getGenderRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefGenderDO) {
				theLePersonDO.setGenderRefValue(theRefGenderDO.getValue());
			}
		}

		// CountryOfBirthRefkey (RefCountryIsoDO)
		if (!(null == theLePersonDO.getCountryOfBirthRefkey() || theLePersonDO.getCountryOfBirthRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryDO = referenceTableHelper.getRefCountryIsoValueByKey(requesterLanguage,
					theLePersonDO.getCountryOfBirthRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryDO) {
				theLePersonDO.setCountryOfBirthRefValue(theRefCountryDO.getValue());
			}
		}

		// CountryCitizenshipRefkey (RefCountryIsoDO)
		if (!(null == theLePersonDO.getCountryCitizenshipRefkey()
				|| theLePersonDO.getCountryCitizenshipRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryDO = referenceTableHelper.getRefCountryIsoValueByKey(requesterLanguage,
					theLePersonDO.getCountryCitizenshipRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryDO) {
				theLePersonDO.setCountryCitizenshipRefValue(theRefCountryDO.getValue());
			}
		}

		// CountryOfDomicileRefkey (RefCountryIsoDO)
		if (!(null == theLePersonDO.getCountryOfDomicileRefkey()
				|| theLePersonDO.getCountryOfDomicileRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryDO = referenceTableHelper.getRefCountryIsoValueByKey(requesterLanguage,
					theLePersonDO.getCountryOfDomicileRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryDO) {
				theLePersonDO.setCountryOfDomicileRefValue(theRefCountryDO.getValue());
			}
		}

		// HighestEduQualRefkey
		if (!(null == theLePersonDO.getHighestEduQualRefkey() || theLePersonDO.getHighestEduQualRefkey().isEmpty())) {
			RefHighestEduQualDO theRefHighestEduQualDO = referenceTableHelper.getRefHighestEduQualValueByKey(
					requesterLanguage, theLePersonDO.getHighestEduQualRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefHighestEduQualDO) {
				theLePersonDO.setHighestEduQualRefValue(theRefHighestEduQualDO.getValue());
			}
		}

		// PreferredLanguageRefkey (RefLanguageCodeDO)
		if (!(null == theLePersonDO.getPreferredLanguageRefkey()
				|| theLePersonDO.getPreferredLanguageRefkey().isEmpty())) {
			RefLanguageCodeDO theRefPreferredLanguageDO = referenceTableHelper.getRefLanguageCodeValueByKey(requesterLanguage,
					theLePersonDO.getPreferredLanguageRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPreferredLanguageDO) {
				theLePersonDO.setPreferredLanguageRefValue(theRefPreferredLanguageDO.getValue());
			}
		}

	}

}
