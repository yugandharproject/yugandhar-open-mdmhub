package com.yugandhar.mdm.config.errorcoderegistry;
/* Generated Aug 8, 2017 4:29:11 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnHeader;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.IgnoreAttributesUtil;
import com.yugandhar.mdm.config.langcode.ConfigLanguageCodeComponent;
import com.yugandhar.mdm.extern.dobj.ConfigErrorcodeRegistryDO;
import com.yugandhar.mdm.extern.dobj.ConfigLanguageCodeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model ConfigErrorcodeRegistryDO class
*@author Yugandhar
*@version 1.0
*@since 1.0
*/

@Component
@Scope(value = "prototype")
public class ConfigErrorcodeRegistryComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	//  entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private ConfigErrorcodeRegistryRepository theConfigErrorcodeRegistryRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	ConfigErrorcodeRegistryComponentRule theConfigErrorcodeRegistryComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;
	
	//default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public ConfigErrorcodeRegistryComponent() {
	}

	/**
	*This method creates a record in database. generates the idpk if not provided in the request and 
	*populate the updatedByUser, updatedByTxnId, createdTsString, updatedTsString attributes.
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with persisted instance 
	*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or other mandatory attributes not present
	*
	*/
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theConfigErrorcodeRegistryComponentRule.prevalidateConfigErrorcodeRegistryCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			ConfigErrorcodeRegistryDO reqConfigErrorcodeRegistryDO = (ConfigErrorcodeRegistryDO) txnTransferObj
					.getTxnPayload().getConfigErrorcodeRegistryDO();
			if (null == reqConfigErrorcodeRegistryDO.getPrimaryKeyDO()
					|| null == reqConfigErrorcodeRegistryDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqConfigErrorcodeRegistryDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqConfigErrorcodeRegistryDO.setIdPk(reqConfigErrorcodeRegistryDO.getPrimaryKeyDO().getIdPk());
				ConfigErrorcodeRegistryDO dbimageConfigErrorcodeRegistryDO = entityManager
						.find(ConfigErrorcodeRegistryDO.class, reqConfigErrorcodeRegistryDO.getIdPk());
				if (null != dbimageConfigErrorcodeRegistryDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"ConfigErrorcodeRegistryComponent.persist failed with Validation Exception");
					//Record already present for given Idpk
				}
			}
			theConfigErrorcodeRegistryComponentRule.preExecuteConfigErrorcodeRegistryCompPersist(reqConfigErrorcodeRegistryDO,
					txnTransferObj);
			entityManager.persist(reqConfigErrorcodeRegistryDO);
			entityManager.flush();
			reqConfigErrorcodeRegistryDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setConfigErrorcodeRegistryDO(reqConfigErrorcodeRegistryDO);
			theConfigErrorcodeRegistryComponentRule.postExecuteConfigErrorcodeRegistryCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"ConfigErrorcodeRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"ConfigErrorcodeRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigErrorcodeRegistryComponent.persist failed unexpectedly");
			//Transaction Failed due to unknown error, please check statck trace
		}
		return respTxnTransferObj;
	}

	/**This service updates the record in database. populate the updatedByUser, updatedByTxnId, updatedTsString attributes
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or mandatory attributes primary key is not present
	*/

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theConfigErrorcodeRegistryComponentRule.PrevalidateConfigErrorcodeRegistryCompMerge(txnTransferObj);
			ConfigErrorcodeRegistryDO reqConfigErrorcodeRegistryDO = (ConfigErrorcodeRegistryDO) txnTransferObj
					.getTxnPayload().getConfigErrorcodeRegistryDO();
			ConfigErrorcodeRegistryDO dbimageConfigErrorcodeRegistryDO = entityManager
					.find(ConfigErrorcodeRegistryDO.class, reqConfigErrorcodeRegistryDO.getIdPk());
			if (null == dbimageConfigErrorcodeRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"ConfigErrorcodeRegistryComponent.merge failed with Validation Exception");
				//Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqConfigErrorcodeRegistryDO);
			BeanUtils.copyProperties(reqConfigErrorcodeRegistryDO, dbimageConfigErrorcodeRegistryDO,
					strIgnoreProperties);
			entityManager.detach(dbimageConfigErrorcodeRegistryDO);
			theConfigErrorcodeRegistryComponentRule.preExecuteConfigErrorcodeRegistryCompMerge(reqConfigErrorcodeRegistryDO,
					dbimageConfigErrorcodeRegistryDO, txnTransferObj);
			respTxnTransferObj.getTxnPayload()
					.setConfigErrorcodeRegistryDO(entityManager.merge(dbimageConfigErrorcodeRegistryDO));
			entityManager.flush();
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theConfigErrorcodeRegistryComponentRule.postExecuteConfigErrorcodeRegistryCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in ConfigErrorcodeRegistryComponent.merge");
			//OptimisticLockException- Row was updated or deleted by another transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"ConfigErrorcodeRegistryComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"ConfigErrorcodeRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigErrorcodeRegistryComponent.merge failed unexpectedly");
			//Transaction Failed due to unknown error, please check statck trace
		}
		return respTxnTransferObj;
	}

	/**
	* This method search the database record based on primary key. 
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or mandatory attributes primary key is not present
	*/
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theConfigErrorcodeRegistryComponentRule.prevalidateConfigErrorcodeRegistryCompFindById(txnTransferObj);
			ConfigErrorcodeRegistryDO reqConfigErrorcodeRegistryDO = (ConfigErrorcodeRegistryDO) txnTransferObj
					.getTxnPayload().getConfigErrorcodeRegistryDO();
			ConfigErrorcodeRegistryDO dbimageConfigErrorcodeRegistryDO = entityManager
					.find(ConfigErrorcodeRegistryDO.class, reqConfigErrorcodeRegistryDO.getIdPk());
			if (null == dbimageConfigErrorcodeRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"ConfigErrorcodeRegistryComponent.findbyId failed with Validation Exception");
				//Record not found
			}
			respTxnTransferObj.getTxnPayload().setConfigErrorcodeRegistryDO(dbimageConfigErrorcodeRegistryDO);
			entityManager.flush();
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theConfigErrorcodeRegistryComponentRule.postExecuteConfigErrorcodeRegistryCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigErrorcodeRegistryComponent.findById failed unexpectedly");
			//Transaction Failed due to unknown error, please check statck trace
		}
		return respTxnTransferObj;

	}

	/**
	* perform the common validation before persisting ConfigErrorcodeRegistryDO object in the database.
	* populate createdTimestamp, updatedTimestamp, transaction reference Id and user
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigErrorcodeRegistryDO is needed in the request");
		}
		ConfigErrorcodeRegistryDO theConfigErrorcodeRegistryDO = (ConfigErrorcodeRegistryDO) txnTransferObj.getTxnPayload()
				.getConfigErrorcodeRegistryDO();
		if (null == theConfigErrorcodeRegistryDO.getErrorCode() || theConfigErrorcodeRegistryDO.getErrorCode().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1005",
					"ConfigErrorcodeRegistryDO.errorCode should not be null");
		}

		if (null == theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey()
				|| theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"ConfigErrorcodeRegistryDO.configLanguageCodeKey should not be null");
		}

		if (null == theConfigErrorcodeRegistryDO.getErrorMessage() || theConfigErrorcodeRegistryDO.getErrorMessage().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1013",
					"ConfigErrorcodeRegistryDO.errorMessage should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey()
				|| theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent.getConfigLanguageCodeValueByKey(
					theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"ConfigErrorcodeRegistryDO.configLanguageCodeKey is not valid");
			}
		}

		theConfigErrorcodeRegistryDO.setCreatedTs(new Date());
		theConfigErrorcodeRegistryDO.setUpdatedTs(new Date());
		theConfigErrorcodeRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theConfigErrorcodeRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	* perform the common validation before updating ConfigErrorcodeRegistryDO object in the database.
	* populate  updatedTimestamp, transaction reference Id and user
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigErrorcodeRegistryDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"ConfigErrorcodeRegistryDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"ConfigErrorcodeRegistryDO.version should not be null");
		}
		ConfigErrorcodeRegistryDO theConfigErrorcodeRegistryDO = (ConfigErrorcodeRegistryDO) txnTransferObj.getTxnPayload()
				.getConfigErrorcodeRegistryDO();
		if (null == theConfigErrorcodeRegistryDO.getErrorCode() || theConfigErrorcodeRegistryDO.getErrorCode().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1005",
					"ConfigErrorcodeRegistryDO.errorCode should not be null");
		}

		if (null == theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey()
				|| theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"ConfigErrorcodeRegistryDO.configLanguageCodeKey should not be null");
		}

		if (null == theConfigErrorcodeRegistryDO.getErrorMessage() || theConfigErrorcodeRegistryDO.getErrorMessage().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1013",
					"ConfigErrorcodeRegistryDO.errorMessage should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey()
				|| theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent.getConfigLanguageCodeValueByKey(
					theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"ConfigErrorcodeRegistryDO.configLanguageCodeKey is not valid");
			}
		}
		
		theConfigErrorcodeRegistryDO.setUpdatedTs(new Date());
		theConfigErrorcodeRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theConfigErrorcodeRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	* perform the common validation that ConfigErrorcodeRegistryDO and idpk is not null
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigErrorcodeRegistryDO is needed in the request");
		}
		ConfigErrorcodeRegistryDO theConfigErrorcodeRegistryDO = (ConfigErrorcodeRegistryDO) txnTransferObj
				.getTxnPayload().getConfigErrorcodeRegistryDO();
		if (null == theConfigErrorcodeRegistryDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"ConfigErrorcodeRegistryDO.idpk should not be null");
		}

	}

	/**
		* Returns all the records from the for ConfigErrorcodeRegistryDO entity based on language code and maximum of records as configuration in properties
		*@since 1.0
		*@param  txnTransferObj  Transfer Object TxnTransferObj instance
		*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
		*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or mandatory attributes business key is not present
		*/
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			List<ConfigErrorcodeRegistryDO> dbimageConfigErrorcodeRegistryDOlist = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getInquiryFilter());

			if (null == dbimageConfigErrorcodeRegistryDOlist) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"ConfigErrorcodeRegistry reference list does not have any records in the database");
				//Record not found
			} else if (dbimageConfigErrorcodeRegistryDOlist.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"ConfigErrorcodeRegistry reference list does not have any records in the database"); //Record not found
			} else {
				respTxnTransferObj.getTxnPayload()
						.setConfigErrorcodeRegistryDOList(dbimageConfigErrorcodeRegistryDOlist);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigErrorcodeRegistryComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "CONFIGERRORCODEREGISTRY_BUSKEY")
	public List<ConfigErrorcodeRegistryDO> findAllRecordsByLanguageCodeFromRepository(String configLanguageCodeKey,
			String filter) {

		List<ConfigErrorcodeRegistryDO> dbimageConfigErrorcodeRegistryDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageConfigErrorcodeRegistryDOlist = this.theConfigErrorcodeRegistryRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageConfigErrorcodeRegistryDOlist = this.theConfigErrorcodeRegistryRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageConfigErrorcodeRegistryDOlist = this.theConfigErrorcodeRegistryRepository
					.findAllByLanguageCode(configLanguageCodeKey);

		}
		return dbimageConfigErrorcodeRegistryDOlist;
	}

	/**
	* This method search the database table CONFIG_ERRORCODE_REGISTRY records based on business key (e.g.LanguageCode and Key)
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or mandatory attributes business key is not present
	*/
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			ConfigErrorcodeRegistryDO reqConfigErrorcodeRegistryDO = (ConfigErrorcodeRegistryDO) txnTransferObj
					.getTxnPayload().getConfigErrorcodeRegistryDO();

			ConfigErrorcodeRegistryDO dbimageConfigErrorcodeRegistryDO = executeRepositoryQuery(
					reqConfigErrorcodeRegistryDO.getConfigLanguageCodeKey(), reqConfigErrorcodeRegistryDO.getErrorCode(),
					reqConfigErrorcodeRegistryDO.getInquiryFilter());

			if (null == dbimageConfigErrorcodeRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"ConfigErrorcodeRegistryComponent.findByBusinessKey search result have no records");
				//Record not found
			} else {
				dbimageConfigErrorcodeRegistryDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setConfigErrorcodeRegistryDO(dbimageConfigErrorcodeRegistryDO);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigErrorcodeRegistryComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	* Execute the query using JPA Repository 
	*@since 1.0
	*@param  configLanguageCodeKey config language code key
	*@param errorCode error code
	*@param filter filter value ACTIVE, INACTIVE or ALL
	*@return  ConfigErrorcodeRegistryDO returns the populated ConfigErrorcodeRegistryDO object
	*/
	@CacheResult(cacheName = "CONFIGERRORCODEREGISTRY_BUSKEY")
	public ConfigErrorcodeRegistryDO executeRepositoryQuery(String configLanguageCodeKey, String errorCode, String filter) {
		ConfigErrorcodeRegistryDO dbimageConfigErrorcodeRegistryDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageConfigErrorcodeRegistryDO = this.theConfigErrorcodeRegistryRepository
					.findByBusinessKeyActive(configLanguageCodeKey, errorCode);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageConfigErrorcodeRegistryDO = this.theConfigErrorcodeRegistryRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, errorCode);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageConfigErrorcodeRegistryDO = this.theConfigErrorcodeRegistryRepository
					.findByBusinessKeyAll(configLanguageCodeKey, errorCode);

		}

		return dbimageConfigErrorcodeRegistryDO;
	}

	/**
	* perform the common validation that ConfigErrorcodeRegistryDO, ConfigErrorcodeRegistryDO.configLanguageCodeKey and ConfigErrorcodeRegistryDO.key is not null
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		//commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		
		if(null == txnTransferObj.getTxnHeader()) {
			TxnHeader txnHeader=txnTransferObj.getTxnHeader();
			txnHeader.setRequesterLanguage("1");
			txnHeader.setUserName("admin");
			txnTransferObj.setTxnHeader(txnHeader);
		}
		
		if (null == txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigErrorcodeRegistryDO is needed in the request");
		}
		ConfigErrorcodeRegistryDO theConfigErrorcodeRegistryDO = (ConfigErrorcodeRegistryDO) txnTransferObj
				.getTxnPayload().getConfigErrorcodeRegistryDO();
		if (null == theConfigErrorcodeRegistryDO.getErrorCode() || theConfigErrorcodeRegistryDO.getErrorCode().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1005",
					"ConfigErrorcodeRegistryDO.errorCode should not be null");
		}

		if (null == theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey()
				|| theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey().isEmpty()) {
			theConfigErrorcodeRegistryDO.setConfigLanguageCodeKey("1");
		}

		//if inquiry filter is null then consider the inquiry is for ACTIVE records
		if (null == txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getInquiryFilter());
		}
	}

	/**
	* perform the common validation that ConfigErrorcodeRegistryDO and ConfigErrorcodeRegistryDO.configLanguageCodeKey is not null
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigErrorcodeRegistryDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigErrorcodeRegistryDO is needed in the request");
		}
		ConfigErrorcodeRegistryDO theConfigErrorcodeRegistryDO = (ConfigErrorcodeRegistryDO) txnTransferObj
				.getTxnPayload().getConfigErrorcodeRegistryDO();

		if (null == theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey()
				|| theConfigErrorcodeRegistryDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"ConfigErrorcodeRegistryDO.configLanguageCodeKey should not be null");
		}

		//if inquiry filter is null then consider the inquiry is for ACTIVE records
		if (null == txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getConfigErrorcodeRegistryDO().getInquiryFilter());
		}
	}

}
