package com.yugandhar.mdm.config.langcode;
/* Generated Aug 8, 2017 3:00:17 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;
import java.util.List;

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
import com.yugandhar.mdm.extern.dobj.ConfigLanguageCodeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model ConfigLanguageCodeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Component
@Scope(value = "prototype")
public class ConfigLanguageCodeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private ConfigLanguageCodeRepository theConfigLanguageCodeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	ConfigLanguageCodeComponentRule theConfigLanguageCodeComponentRule;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public ConfigLanguageCodeComponent() {
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
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theConfigLanguageCodeComponentRule.prevalidateConfigLanguageCodeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			ConfigLanguageCodeDO reqConfigLanguageCodeDO = (ConfigLanguageCodeDO) txnTransferObj.getTxnPayload()
					.getConfigLanguageCodeDO();
			if (null == reqConfigLanguageCodeDO.getPrimaryKeyDO()
					|| null == reqConfigLanguageCodeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqConfigLanguageCodeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqConfigLanguageCodeDO.setIdPk(reqConfigLanguageCodeDO.getPrimaryKeyDO().getIdPk());
				ConfigLanguageCodeDO dbimageConfigLanguageCodeDO = entityManager.find(ConfigLanguageCodeDO.class,
						reqConfigLanguageCodeDO.getIdPk());
				if (null != dbimageConfigLanguageCodeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"ConfigLanguageCodeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theConfigLanguageCodeComponentRule.preExecuteConfigLanguageCodeCompPersist(reqConfigLanguageCodeDO,
					txnTransferObj);
			entityManager.persist(reqConfigLanguageCodeDO);
			entityManager.flush();
			reqConfigLanguageCodeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setConfigLanguageCodeDO(reqConfigLanguageCodeDO);
			theConfigLanguageCodeComponentRule.postExecuteConfigLanguageCodeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"ConfigLanguageCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"ConfigLanguageCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigLanguageCodeComponent.persist failed unexpectedly");
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
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theConfigLanguageCodeComponentRule.PrevalidateConfigLanguageCodeCompMerge(txnTransferObj);
			ConfigLanguageCodeDO reqConfigLanguageCodeDO = (ConfigLanguageCodeDO) txnTransferObj.getTxnPayload()
					.getConfigLanguageCodeDO();
			ConfigLanguageCodeDO dbimageConfigLanguageCodeDO = entityManager.find(ConfigLanguageCodeDO.class,
					reqConfigLanguageCodeDO.getIdPk());
			if (null == dbimageConfigLanguageCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"ConfigLanguageCodeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqConfigLanguageCodeDO);
			BeanUtils.copyProperties(reqConfigLanguageCodeDO, dbimageConfigLanguageCodeDO, strIgnoreProperties);
			entityManager.detach(dbimageConfigLanguageCodeDO);
			theConfigLanguageCodeComponentRule.preExecuteConfigLanguageCodeCompMerge(reqConfigLanguageCodeDO,
					dbimageConfigLanguageCodeDO, txnTransferObj);
			respTxnTransferObj.getTxnPayload()
					.setConfigLanguageCodeDO(entityManager.merge(dbimageConfigLanguageCodeDO));
			entityManager.flush();
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theConfigLanguageCodeComponentRule.postExecuteConfigLanguageCodeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in ConfigLanguageCodeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"ConfigLanguageCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"ConfigLanguageCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigLanguageCodeComponent.merge failed unexpectedly");
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
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theConfigLanguageCodeComponentRule.prevalidateConfigLanguageCodeCompFindById(txnTransferObj);
			ConfigLanguageCodeDO reqConfigLanguageCodeDO = (ConfigLanguageCodeDO) txnTransferObj.getTxnPayload()
					.getConfigLanguageCodeDO();
			ConfigLanguageCodeDO dbimageConfigLanguageCodeDO = entityManager.find(ConfigLanguageCodeDO.class,
					reqConfigLanguageCodeDO.getIdPk());
			if (null == dbimageConfigLanguageCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"ConfigLanguageCodeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			respTxnTransferObj.getTxnPayload().setConfigLanguageCodeDO(dbimageConfigLanguageCodeDO);
			entityManager.flush();
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theConfigLanguageCodeComponentRule.postExecuteConfigLanguageCodeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigLanguageCodeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting ConfigLanguageCodeDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getConfigLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigLanguageCodeDO is needed in the request");
		}
		ConfigLanguageCodeDO theConfigLanguageCodeDO = (ConfigLanguageCodeDO) txnTransferObj.getTxnPayload()
				.getConfigLanguageCodeDO();
		if (null == theConfigLanguageCodeDO.getKey() || theConfigLanguageCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"ConfigLanguageCodeDO.key should not be null");
		}

		if (null == theConfigLanguageCodeDO.getValue() || theConfigLanguageCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"ConfigLanguageCodeDO.Value should not be null");
		}

		theConfigLanguageCodeDO.setCreatedTs(new Date());
		theConfigLanguageCodeDO.setUpdatedTs(new Date());
		theConfigLanguageCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theConfigLanguageCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating ConfigLanguageCodeDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getConfigLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigLanguageCodeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"ConfigLanguageCodeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"ConfigLanguageCodeDO.version should not be null");
		}
		
		ConfigLanguageCodeDO theConfigLanguageCodeDO = (ConfigLanguageCodeDO) txnTransferObj.getTxnPayload()
				.getConfigLanguageCodeDO();
		if (null == theConfigLanguageCodeDO.getKey() || theConfigLanguageCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"ConfigLanguageCodeDO.key should not be null");
		}

		if (null == theConfigLanguageCodeDO.getValue() || theConfigLanguageCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"ConfigLanguageCodeDO.Value should not be null");
		}
		
		theConfigLanguageCodeDO.setUpdatedTs(new Date());
		theConfigLanguageCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theConfigLanguageCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that ConfigLanguageCodeDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getConfigLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigLanguageCodeDO is needed in the request");
		}
		ConfigLanguageCodeDO theConfigLanguageCodeDO = (ConfigLanguageCodeDO) txnTransferObj.getTxnPayload()
				.getConfigLanguageCodeDO();
		if (null == theConfigLanguageCodeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"ConfigLanguageCodeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for ConfigLanguageCodeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecords(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			List<ConfigLanguageCodeDO> dbimageConfigLanguageCodeDOlist = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getInquiryFilter());

			if (null == dbimageConfigLanguageCodeDOlist) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"ConfigLanguageCode reference list does not have any records in the database");
				// Record not found
			} else if (dbimageConfigLanguageCodeDOlist.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"ConfigLanguageCode reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setConfigLanguageCodeDOList(dbimageConfigLanguageCodeDOlist);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigLanguageCodeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "CONFIGLANGUAGECODE_BUSKEY")
	public List<ConfigLanguageCodeDO> findAllRecordsByLanguageCodeFromRepository(String filter) {

		List<ConfigLanguageCodeDO> dbimageConfigLanguageCodeDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageConfigLanguageCodeDOlist = this.theConfigLanguageCodeRepository.findAllActive();
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageConfigLanguageCodeDOlist = this.theConfigLanguageCodeRepository.findAllInActive();
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageConfigLanguageCodeDOlist = this.theConfigLanguageCodeRepository.findAll();

		}
		return dbimageConfigLanguageCodeDOlist;
	}

	/**
	 * This method search the database table CONFIG_LANGUAGE_CODE records based
	 * on business key (e.g.LanguageCode and Key)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			ConfigLanguageCodeDO reqConfigLanguageCodeDO = (ConfigLanguageCodeDO) txnTransferObj.getTxnPayload()
					.getConfigLanguageCodeDO();
			theConfigLanguageCodeComponentRule.preValidateConfigLanguageCodefindByBusinessKey(txnTransferObj);
			theConfigLanguageCodeComponentRule.preExecuteConfigLanguageCodefindByBusinessKey(txnTransferObj);

			ConfigLanguageCodeDO dbimageConfigLanguageCodeDO = executeRepositoryQuery(reqConfigLanguageCodeDO.getKey(),
					reqConfigLanguageCodeDO.getInquiryFilter());

			if (null == dbimageConfigLanguageCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"ConfigLanguageCodeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageConfigLanguageCodeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setConfigLanguageCodeDO(dbimageConfigLanguageCodeDO);
			}

			theConfigLanguageCodeComponentRule.postExecuteConfigLanguageCodefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigLanguageCodeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	
	public ConfigLanguageCodeDO getConfigLanguageCodeValueByKey(String Refkey, String filterValue) {
		return executeRepositoryQuery(Refkey, filterValue);
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
	 * @return ConfigLanguageCodeDO returns the populated ConfigLanguageCodeDO
	 *         object
	 */
	@CacheResult(cacheName = "CONFIGLANGUAGECODE_BUSKEY")
	public ConfigLanguageCodeDO executeRepositoryQuery(String key, String filter) {
		ConfigLanguageCodeDO dbimageConfigLanguageCodeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageConfigLanguageCodeDO = this.theConfigLanguageCodeRepository.findByBusinessKeyActive(key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageConfigLanguageCodeDO = this.theConfigLanguageCodeRepository.findByBusinessKeyInactive(key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageConfigLanguageCodeDO = this.theConfigLanguageCodeRepository.findByBusinessKeyAll(key);

		}

		return dbimageConfigLanguageCodeDO;
	}

	/**
	 * perform the common validation that ConfigLanguageCodeDO,
	 * ConfigLanguageCodeDO.configLanguageCodeKey and ConfigLanguageCodeDO.key
	 * is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getConfigLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigLanguageCodeDO is needed in the request");
		}
		ConfigLanguageCodeDO theConfigLanguageCodeDO = (ConfigLanguageCodeDO) txnTransferObj.getTxnPayload()
				.getConfigLanguageCodeDO();
		if (null == theConfigLanguageCodeDO.getKey() || theConfigLanguageCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"ConfigLanguageCodeDO.key should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that ConfigLanguageCodeDO and
	 * ConfigLanguageCodeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ConfigLanguageCodeDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getConfigLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigLanguageCodeDO is needed in the request");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getConfigLanguageCodeDO().getInquiryFilter());
		}
	}

}
