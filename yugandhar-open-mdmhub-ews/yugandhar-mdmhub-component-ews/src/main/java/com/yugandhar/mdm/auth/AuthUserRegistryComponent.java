package com.yugandhar.mdm.auth;
/* Generated Oct 2, 2017 1:29:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;

import javax.cache.annotation.CacheResult;
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
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.config.langcode.ConfigLanguageCodeComponent;
import com.yugandhar.mdm.extern.dobj.AuthUserRegistryDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model AuthUserRegistryDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class AuthUserRegistryComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private AuthUserRegistryRepository theAuthUserRegistryRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AuthUserRegistryComponentRule theAuthUserRegistryComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public AuthUserRegistryComponent() {
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
	 *             if AuthUserRegistryDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theAuthUserRegistryComponentRule.prevalidateAuthUserRegistryCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			AuthUserRegistryDO reqAuthUserRegistryDO = (AuthUserRegistryDO) txnTransferObj.getTxnPayload()
					.getAuthUserRegistryDO();
			if (null == reqAuthUserRegistryDO.getPrimaryKeyDO()
					|| null == reqAuthUserRegistryDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqAuthUserRegistryDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqAuthUserRegistryDO.setIdPk(reqAuthUserRegistryDO.getPrimaryKeyDO().getIdPk());
				AuthUserRegistryDO dbimageAuthUserRegistryDO = entityManager.find(AuthUserRegistryDO.class,
						reqAuthUserRegistryDO.getIdPk());
				if (null != dbimageAuthUserRegistryDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"AuthUserRegistryComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theAuthUserRegistryComponentRule.preExecuteAuthUserRegistryCompPersist(reqAuthUserRegistryDO,
					txnTransferObj);
			entityManager.persist(reqAuthUserRegistryDO);
			entityManager.flush();
			reqAuthUserRegistryDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setAuthUserRegistryDO(new AuthUserRegistryDO(reqAuthUserRegistryDO));
			theAuthUserRegistryComponentRule.postExecuteAuthUserRegistryCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AuthUserRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AuthUserRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRegistryComponent.persist failed unexpectedly");
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
	 *             if AuthUserRegistryDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theAuthUserRegistryComponentRule.PrevalidateAuthUserRegistryCompMerge(txnTransferObj);
			AuthUserRegistryDO reqAuthUserRegistryDO = (AuthUserRegistryDO) txnTransferObj.getTxnPayload()
					.getAuthUserRegistryDO();
			AuthUserRegistryDO dbimageAuthUserRegistryDO = entityManager.find(AuthUserRegistryDO.class,
					reqAuthUserRegistryDO.getIdPk());
			if (null == dbimageAuthUserRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthUserRegistryComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqAuthUserRegistryDO);
			BeanUtils.copyProperties(reqAuthUserRegistryDO, dbimageAuthUserRegistryDO, strIgnoreProperties);
			entityManager.detach(dbimageAuthUserRegistryDO);
			theAuthUserRegistryComponentRule.preExecuteAuthUserRegistryCompMerge(reqAuthUserRegistryDO,
					dbimageAuthUserRegistryDO, txnTransferObj);
			AuthUserRegistryDO mergedAuthUserRegistryDO = entityManager.merge(dbimageAuthUserRegistryDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setAuthUserRegistryDO(new AuthUserRegistryDO(mergedAuthUserRegistryDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAuthUserRegistryComponentRule.postExecuteAuthUserRegistryCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in AuthUserRegistryComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AuthUserRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AuthUserRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRegistryComponent.merge failed unexpectedly");
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
	 *             if AuthUserRegistryDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theAuthUserRegistryComponentRule.prevalidateAuthUserRegistryCompFindById(txnTransferObj);
			AuthUserRegistryDO reqAuthUserRegistryDO = (AuthUserRegistryDO) txnTransferObj.getTxnPayload()
					.getAuthUserRegistryDO();
			AuthUserRegistryDO dbimageAuthUserRegistryDO = entityManager.find(AuthUserRegistryDO.class,
					reqAuthUserRegistryDO.getIdPk());
			if (null == dbimageAuthUserRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthUserRegistryComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setAuthUserRegistryDO(new AuthUserRegistryDO(dbimageAuthUserRegistryDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAuthUserRegistryComponentRule.postExecuteAuthUserRegistryCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRegistryComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting AuthUserRegistryDO object
	 * in the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRegistryDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAuthUserRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"authUserRegistryDO is needed in the request");
		}

		AuthUserRegistryDO theAuthUserRegistryDO = (AuthUserRegistryDO) txnTransferObj.getTxnPayload()
				.getAuthUserRegistryDO();
		if (null == theAuthUserRegistryDO.getUserName() || theAuthUserRegistryDO.getUserName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10074",
					"authUserRegistryDO.userName should not be null");
		}

		theAuthUserRegistryDO.setCreatedTs(new Date());
		theAuthUserRegistryDO.setUpdatedTs(new Date());
		theAuthUserRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAuthUserRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating AuthUserRegistryDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRegistryDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthUserRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserRegistryDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getAuthUserRegistryDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAuthUserRegistryDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AuthUserRegistryDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getAuthUserRegistryDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"AuthUserRegistryDO.version should not be null");
		}

		AuthUserRegistryDO theAuthUserRegistryDO = (AuthUserRegistryDO) txnTransferObj.getTxnPayload()
				.getAuthUserRegistryDO();
		if (null == theAuthUserRegistryDO.getUserName() || theAuthUserRegistryDO.getUserName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10074",
					"authUserRegistryDO.userName should not be null");
		}

		theAuthUserRegistryDO.setUpdatedTs(new Date());
		theAuthUserRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAuthUserRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that AuthUserRegistryDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRegistryDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAuthUserRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserRegistryDO is needed in the request");
		}
		AuthUserRegistryDO theAuthUserRegistryDO = (AuthUserRegistryDO) txnTransferObj.getTxnPayload()
				.getAuthUserRegistryDO();
		if (null == theAuthUserRegistryDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AuthUserRegistryDO.idpk should not be null");
		}

	}

	/**
	 * This method search the database table records based on business key
	 * (e.g.UserName)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRegistryDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			AuthUserRegistryDO reqAuthUserRegistryDO = (AuthUserRegistryDO) txnTransferObj.getTxnPayload()
					.getAuthUserRegistryDO();
			theAuthUserRegistryComponentRule.preValidateAuthUserRegistryfindByBusinessKey(txnTransferObj);
			theAuthUserRegistryComponentRule.preExecuteAuthUserRegistryfindByBusinessKey(txnTransferObj);

			AuthUserRegistryDO dbimageAuthUserRegistryDO = executeRepositoryQuery(reqAuthUserRegistryDO.getUserName(),
					reqAuthUserRegistryDO.getInquiryFilter());

			if (null == dbimageAuthUserRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthUserRegistryComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageAuthUserRegistryDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getAuthUserRegistryDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setAuthUserRegistryDO(new AuthUserRegistryDO(dbimageAuthUserRegistryDO));
			}

			theAuthUserRegistryComponentRule.postExecuteAuthUserRegistryfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRegistryComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param String
	 *            configLanguageCodeKey
	 * @param String
	 *            key
	 * @param String
	 *            filter
	 * @return AuthUserRegistryDO returns the populated AuthUserRegistryDO
	 *         object
	 */
	@CacheResult(cacheName = "AUTHUSERREGISTRY_BUSKEY")
	public AuthUserRegistryDO executeRepositoryQuery(String userName, String filter) {
		AuthUserRegistryDO dbimageAuthUserRegistryDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageAuthUserRegistryDO = this.theAuthUserRegistryRepository.findByBusinessKeyActive(userName);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageAuthUserRegistryDO = this.theAuthUserRegistryRepository.findByBusinessKeyInactive(userName);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageAuthUserRegistryDO = this.theAuthUserRegistryRepository.findByBusinessKeyAll(userName);

		}

		return dbimageAuthUserRegistryDO;
	}

	/**
	 * perform the common validation that AuthUserRegistryDO,
	 * AuthUserRegistryDO.configLanguageCodeKey and AuthUserRegistryDO.key is
	 * not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRegistryDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthUserRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserRegistryDO is needed in the request");
		}
		AuthUserRegistryDO theAuthUserRegistryDO = (AuthUserRegistryDO) txnTransferObj.getTxnPayload()
				.getAuthUserRegistryDO();
		if (null == theAuthUserRegistryDO.getUserName() || theAuthUserRegistryDO.getUserName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10074",
					"AuthUserRegistryDO.userName should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getAuthUserRegistryDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getAuthUserRegistryDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getAuthUserRegistryDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getAuthUserRegistryDO().getInquiryFilter());
		}
	}

}
