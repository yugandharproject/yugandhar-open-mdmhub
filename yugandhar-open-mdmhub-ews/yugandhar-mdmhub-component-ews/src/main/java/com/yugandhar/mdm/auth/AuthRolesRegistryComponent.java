package com.yugandhar.mdm.auth;
/* Generated Oct 1, 2017 8:58:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.AuthRolesRegistryDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model AuthRolesRegistryDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class AuthRolesRegistryComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private AuthRolesRegistryRepository theAuthRolesRegistryRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AuthRolesRegistryComponentRule theAuthRolesRegistryComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public AuthRolesRegistryComponent() {
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
	 *             if AuthRolesRegistryDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theAuthRolesRegistryComponentRule.prevalidateAuthRolesRegistryCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			AuthRolesRegistryDO reqAuthRolesRegistryDO = (AuthRolesRegistryDO) txnTransferObj.getTxnPayload()
					.getAuthRolesRegistryDO();
			if (null == reqAuthRolesRegistryDO.getPrimaryKeyDO()
					|| null == reqAuthRolesRegistryDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqAuthRolesRegistryDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqAuthRolesRegistryDO.setIdPk(reqAuthRolesRegistryDO.getPrimaryKeyDO().getIdPk());
				AuthRolesRegistryDO dbimageAuthRolesRegistryDO = entityManager.find(AuthRolesRegistryDO.class,
						reqAuthRolesRegistryDO.getIdPk());
				if (null != dbimageAuthRolesRegistryDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"AuthRolesRegistryComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theAuthRolesRegistryComponentRule.preExecuteAuthRolesRegistryCompPersist(reqAuthRolesRegistryDO,
					txnTransferObj);
			entityManager.persist(reqAuthRolesRegistryDO);
			entityManager.flush();
			reqAuthRolesRegistryDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setAuthRolesRegistryDO(new AuthRolesRegistryDO(reqAuthRolesRegistryDO));
			theAuthRolesRegistryComponentRule.postExecuteAuthRolesRegistryCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AuthRolesRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AuthRolesRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthRolesRegistryComponent.persist failed unexpectedly");
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
	 *             if AuthRolesRegistryDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theAuthRolesRegistryComponentRule.PrevalidateAuthRolesRegistryCompMerge(txnTransferObj);
			AuthRolesRegistryDO reqAuthRolesRegistryDO = (AuthRolesRegistryDO) txnTransferObj.getTxnPayload()
					.getAuthRolesRegistryDO();
			AuthRolesRegistryDO dbimageAuthRolesRegistryDO = entityManager.find(AuthRolesRegistryDO.class,
					reqAuthRolesRegistryDO.getIdPk());
			if (null == dbimageAuthRolesRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthRolesRegistryComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqAuthRolesRegistryDO);
			BeanUtils.copyProperties(reqAuthRolesRegistryDO, dbimageAuthRolesRegistryDO, strIgnoreProperties);
			entityManager.detach(dbimageAuthRolesRegistryDO);
			theAuthRolesRegistryComponentRule.preExecuteAuthRolesRegistryCompMerge(reqAuthRolesRegistryDO,
					dbimageAuthRolesRegistryDO, txnTransferObj);
			AuthRolesRegistryDO mergedAuthRolesRegistryDO = entityManager.merge(dbimageAuthRolesRegistryDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setAuthRolesRegistryDO(new AuthRolesRegistryDO(mergedAuthRolesRegistryDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAuthRolesRegistryComponentRule.postExecuteAuthRolesRegistryCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in AuthRolesRegistryComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AuthRolesRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AuthRolesRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthRolesRegistryComponent.merge failed unexpectedly");
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
	 *             if AuthRolesRegistryDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theAuthRolesRegistryComponentRule.prevalidateAuthRolesRegistryCompFindById(txnTransferObj);
			AuthRolesRegistryDO reqAuthRolesRegistryDO = (AuthRolesRegistryDO) txnTransferObj.getTxnPayload()
					.getAuthRolesRegistryDO();
			AuthRolesRegistryDO dbimageAuthRolesRegistryDO = entityManager.find(AuthRolesRegistryDO.class,
					reqAuthRolesRegistryDO.getIdPk());
			if (null == dbimageAuthRolesRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthRolesRegistryComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setAuthRolesRegistryDO(new AuthRolesRegistryDO(dbimageAuthRolesRegistryDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAuthRolesRegistryComponentRule.postExecuteAuthRolesRegistryCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthRolesRegistryComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting AuthRolesRegistryDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthRolesRegistryDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAuthRolesRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthRolesRegistryDO is needed in the request");
		}

		AuthRolesRegistryDO theAuthRolesRegistryDO = (AuthRolesRegistryDO) txnTransferObj.getTxnPayload()
				.getAuthRolesRegistryDO();
		if (null == theAuthRolesRegistryDO.getRoleName() || theAuthRolesRegistryDO.getRoleName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10073",
					"AuthRolesRegistryDO.roleName should not be null");
		} else {
			theAuthRolesRegistryDO.setRoleName(theAuthRolesRegistryDO.getRoleName().toUpperCase());
		}

		theAuthRolesRegistryDO.setCreatedTs(new Date());
		theAuthRolesRegistryDO.setUpdatedTs(new Date());
		theAuthRolesRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAuthRolesRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating AuthRolesRegistryDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthRolesRegistryDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthRolesRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthRolesRegistryDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getAuthRolesRegistryDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAuthRolesRegistryDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AuthRolesRegistryDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getAuthRolesRegistryDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"AuthRolesRegistryDO.version should not be null");
		}

		AuthRolesRegistryDO theAuthRolesRegistryDO = (AuthRolesRegistryDO) txnTransferObj.getTxnPayload()
				.getAuthRolesRegistryDO();
		if (null == theAuthRolesRegistryDO.getRoleName() || theAuthRolesRegistryDO.getRoleName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10073",
					"AuthRolesRegistryDO.roleName should not be null");
		}else {
			theAuthRolesRegistryDO.setRoleName(theAuthRolesRegistryDO.getRoleName().toUpperCase());
		}

		theAuthRolesRegistryDO.setUpdatedTs(new Date());
		theAuthRolesRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAuthRolesRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that AuthRolesRegistryDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthRolesRegistryDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAuthRolesRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthRolesRegistryDO is needed in the request");
		}
		AuthRolesRegistryDO theAuthRolesRegistryDO = (AuthRolesRegistryDO) txnTransferObj.getTxnPayload()
				.getAuthRolesRegistryDO();
		if (null == theAuthRolesRegistryDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AuthRolesRegistryDO.idpk should not be null");
		}

	}

	/**
	 * This method search the database table AUTH_ROLES_REGISTRY records based
	 * on business key (e.g.roleName)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthRolesRegistryDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			AuthRolesRegistryDO reqAuthRolesRegistryDO = (AuthRolesRegistryDO) txnTransferObj.getTxnPayload()
					.getAuthRolesRegistryDO();
			theAuthRolesRegistryComponentRule.preValidateAuthRolesRegistryfindByBusinessKey(txnTransferObj);
			theAuthRolesRegistryComponentRule.preExecuteAuthRolesRegistryfindByBusinessKey(txnTransferObj);

			AuthRolesRegistryDO dbimageAuthRolesRegistryDO = executeRepositoryQuery(
					reqAuthRolesRegistryDO.getRoleName(), reqAuthRolesRegistryDO.getInquiryFilter());

			if (null == dbimageAuthRolesRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthRolesRegistryComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageAuthRolesRegistryDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getAuthRolesRegistryDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setAuthRolesRegistryDO(new AuthRolesRegistryDO(dbimageAuthRolesRegistryDO));
			}

			theAuthRolesRegistryComponentRule.postExecuteAuthRolesRegistryfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthRolesRegistryComponent.findByBusinessKey failed unexpectedly");
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
	 * @return AuthRolesRegistryDO returns the populated AuthRolesRegistryDO
	 *         object
	 */
	@CacheResult(cacheName = "AUTHROLESREGISTRY_BUSKEY")
	public AuthRolesRegistryDO executeRepositoryQuery(String roleName, String filter) {
		AuthRolesRegistryDO dbimageAuthRolesRegistryDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageAuthRolesRegistryDO = this.theAuthRolesRegistryRepository.findByBusinessKeyActive(roleName);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageAuthRolesRegistryDO = this.theAuthRolesRegistryRepository.findByBusinessKeyInactive(roleName);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageAuthRolesRegistryDO = this.theAuthRolesRegistryRepository.findByBusinessKeyAll(roleName);

		}

		return dbimageAuthRolesRegistryDO;
	}

	/**
	 * perform the common validation that AuthRolesRegistryDO,
	 * AuthRolesRegistryDO.configLanguageCodeKey and AuthRolesRegistryDO.key is
	 * not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthRolesRegistryDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthRolesRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthRolesRegistryDO is needed in the request");
		}
		AuthRolesRegistryDO theAuthRolesRegistryDO = (AuthRolesRegistryDO) txnTransferObj.getTxnPayload()
				.getAuthRolesRegistryDO();
		if (null == theAuthRolesRegistryDO.getRoleName() || theAuthRolesRegistryDO.getRoleName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10073",
					"AuthRolesRegistryDO.roleName should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getAuthRolesRegistryDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getAuthRolesRegistryDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getAuthRolesRegistryDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getAuthRolesRegistryDO().getInquiryFilter());
		}
	}

}
