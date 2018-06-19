package com.yugandhar.mdm.config.inqlevel;
/* Generated Aug 22, 2017 5:25:47 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.config.langcode.ConfigLanguageCodeComponent;
import com.yugandhar.mdm.extern.dobj.ConfigInquiryLevelsDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model ConfigInquiryLevelsDO class
*@author Yugandhar
*@version 1.0
*@since 1.0
 * 
*/

@Scope(value = "prototype")
@Component
public class ConfigInquiryLevelsComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	//  entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private ConfigInquiryLevelsRepository theConfigInquiryLevelsRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	ConfigInquiryLevelsComponentRule theConfigInquiryLevelsComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	//default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public ConfigInquiryLevelsComponent() {
	}

	/**
	*This method creates a record in database. generates the idpk if not provided in the request and 
	*populate the updatedByUser, updatedByTxnId, createdTsString, updatedTsString attributes.
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with persisted instance 
	*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or other mandatory attributes not present
	*
	*/
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theConfigInquiryLevelsComponentRule.prevalidateConfigInquiryLevelsCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			ConfigInquiryLevelsDO reqConfigInquiryLevelsDO = (ConfigInquiryLevelsDO) txnTransferObj.getTxnPayload()
					.getConfigInquiryLevelsDO();
			if (null == reqConfigInquiryLevelsDO.getPrimaryKeyDO()
					|| null == reqConfigInquiryLevelsDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqConfigInquiryLevelsDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqConfigInquiryLevelsDO.setIdPk(reqConfigInquiryLevelsDO.getPrimaryKeyDO().getIdPk());
				ConfigInquiryLevelsDO dbimageConfigInquiryLevelsDO = entityManager.find(ConfigInquiryLevelsDO.class,
						reqConfigInquiryLevelsDO.getIdPk());
				if (null != dbimageConfigInquiryLevelsDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"ConfigInquiryLevelsComponent.persist failed with Validation Exception");
					//Record already present for given Idpk
				}
			}
			theConfigInquiryLevelsComponentRule.preExecuteConfigInquiryLevelsCompPersist(reqConfigInquiryLevelsDO,
					txnTransferObj);
			entityManager.persist(reqConfigInquiryLevelsDO);
			entityManager.flush();
			reqConfigInquiryLevelsDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setConfigInquiryLevelsDO(reqConfigInquiryLevelsDO);
			theConfigInquiryLevelsComponentRule.postExecuteConfigInquiryLevelsCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"ConfigInquiryLevelsComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"ConfigInquiryLevelsComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigInquiryLevelsComponent.persist failed unexpectedly");
			//Transaction Failed due to unknown error, please check statck trace
		}
		return respTxnTransferObj;
	}

	/**This service updates the record in database. populate the updatedByUser, updatedByTxnId, updatedTsString attributes
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or mandatory attributes primary key is not present
	*/

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theConfigInquiryLevelsComponentRule.PrevalidateConfigInquiryLevelsCompMerge(txnTransferObj);
			ConfigInquiryLevelsDO reqConfigInquiryLevelsDO = (ConfigInquiryLevelsDO) txnTransferObj.getTxnPayload()
					.getConfigInquiryLevelsDO();
			ConfigInquiryLevelsDO dbimageConfigInquiryLevelsDO = entityManager.find(ConfigInquiryLevelsDO.class,
					reqConfigInquiryLevelsDO.getIdPk());
			if (null == dbimageConfigInquiryLevelsDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"ConfigInquiryLevelsComponent.merge failed with Validation Exception");
				//Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqConfigInquiryLevelsDO);
			BeanUtils.copyProperties(reqConfigInquiryLevelsDO, dbimageConfigInquiryLevelsDO, strIgnoreProperties);
			entityManager.detach(dbimageConfigInquiryLevelsDO);
			theConfigInquiryLevelsComponentRule.preExecuteConfigInquiryLevelsCompMerge(reqConfigInquiryLevelsDO,
					dbimageConfigInquiryLevelsDO, txnTransferObj);
			respTxnTransferObj.getTxnPayload()
					.setConfigInquiryLevelsDO(entityManager.merge(dbimageConfigInquiryLevelsDO));
			entityManager.flush();
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theConfigInquiryLevelsComponentRule.postExecuteConfigInquiryLevelsCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in ConfigInquiryLevelsComponent.merge");
			//OptimisticLockException- Row was updated or deleted by another transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"ConfigInquiryLevelsComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"ConfigInquiryLevelsComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigInquiryLevelsComponent.merge failed unexpectedly");
			//Transaction Failed due to unknown error, please check statck trace
		}
		return respTxnTransferObj;
	}

	/**
	* This method search the database record based on primary key. 
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or mandatory attributes primary key is not present
	*/
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theConfigInquiryLevelsComponentRule.prevalidateConfigInquiryLevelsCompFindById(txnTransferObj);
			ConfigInquiryLevelsDO reqConfigInquiryLevelsDO = (ConfigInquiryLevelsDO) txnTransferObj.getTxnPayload()
					.getConfigInquiryLevelsDO();
			ConfigInquiryLevelsDO dbimageConfigInquiryLevelsDO = entityManager.find(ConfigInquiryLevelsDO.class,
					reqConfigInquiryLevelsDO.getIdPk());
			if (null == dbimageConfigInquiryLevelsDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"ConfigInquiryLevelsComponent.findbyId failed with Validation Exception");
				//Record not found
			}
			respTxnTransferObj.getTxnPayload().setConfigInquiryLevelsDO(dbimageConfigInquiryLevelsDO);
			entityManager.flush();
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theConfigInquiryLevelsComponentRule.postExecuteConfigInquiryLevelsCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigInquiryLevelsComponent.findById failed unexpectedly");
			//Transaction Failed due to unknown error, please check statck trace
		}
		return respTxnTransferObj;

	}

	/**
	* perform the common validation before persisting ConfigInquiryLevelsDO object in the database.
	* populate createdTimestamp, updatedTimestamp, transaction reference Id and user
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigInquiryLevelsDO is needed in the request");
		}

		ConfigInquiryLevelsDO theConfigInquiryLevelsDO = (ConfigInquiryLevelsDO) txnTransferObj.getTxnPayload()
				.getConfigInquiryLevelsDO();
		if (null == theConfigInquiryLevelsDO.getInquiryLevel() || theConfigInquiryLevelsDO.getInquiryLevel().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1017",
					"ConfigInquiryLevelsDO.inquiryLevel should not be null");
		}

		if (null == theConfigInquiryLevelsDO.getApplicableDobj()
				|| theConfigInquiryLevelsDO.getApplicableDobj().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1018",
					"ConfigInquiryLevelsDO.applicableDobj should not be null");
		}

		if (null == theConfigInquiryLevelsDO.getChildDobj() || theConfigInquiryLevelsDO.getChildDobj().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1019",
					"ConfigInquiryLevelsDO.childDobj should not be null");
		}

		theConfigInquiryLevelsDO.setCreatedTs(new Date());
		theConfigInquiryLevelsDO.setUpdatedTs(new Date());
		theConfigInquiryLevelsDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theConfigInquiryLevelsDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	* perform the common validation before updating ConfigInquiryLevelsDO object in the database.
	* populate  updatedTimestamp, transaction reference Id and user
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigInquiryLevelsDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"ConfigInquiryLevelsDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"ConfigInquiryLevelsDO.version should not be null");
		}

		ConfigInquiryLevelsDO theConfigInquiryLevelsDO = (ConfigInquiryLevelsDO) txnTransferObj.getTxnPayload()
				.getConfigInquiryLevelsDO();
		if (null == theConfigInquiryLevelsDO.getInquiryLevel() || theConfigInquiryLevelsDO.getInquiryLevel().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1017",
					"ConfigInquiryLevelsDO.inquiryLevel should not be null");
		}

		if (null == theConfigInquiryLevelsDO.getApplicableDobj()
				|| theConfigInquiryLevelsDO.getApplicableDobj().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1018",
					"ConfigInquiryLevelsDO.applicableDobj should not be null");
		}

		if (null == theConfigInquiryLevelsDO.getChildDobj() || theConfigInquiryLevelsDO.getChildDobj().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1019",
					"ConfigInquiryLevelsDO.childDobj should not be null");
		}

		theConfigInquiryLevelsDO.setUpdatedTs(new Date());
		theConfigInquiryLevelsDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theConfigInquiryLevelsDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	* perform the common validation that ConfigInquiryLevelsDO and idpk is not null
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigInquiryLevelsDO is needed in the request");
		}
		ConfigInquiryLevelsDO theConfigInquiryLevelsDO = (ConfigInquiryLevelsDO) txnTransferObj.getTxnPayload()
				.getConfigInquiryLevelsDO();
		if (null == theConfigInquiryLevelsDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"ConfigInquiryLevelsDO.idpk should not be null");
		}

	}

	/**
		* Returns all the records from the for ConfigInquiryLevelsDO entity based on language code and maximum of records as configuration in properties
		*@since 1.0
		*@param  txnTransferObj  Transfer Object TxnTransferObj instance
		*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
		*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or mandatory attributes business key is not present
		*/
	public TxnTransferObj findAllRecords(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecords(txnTransferObj);
			List<ConfigInquiryLevelsDO> dbimageConfigInquiryLevelsDOlist = findAllRecordsFromRepository(
					txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getInquiryFilter());

			if (null == dbimageConfigInquiryLevelsDOlist) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"ConfigInquiryLevels reference list does not have any records in the database");
				//Record not found
			} else if (dbimageConfigInquiryLevelsDOlist.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"ConfigInquiryLevels reference list does not have any records in the database"); //Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setConfigInquiryLevelsDOList(dbimageConfigInquiryLevelsDOlist);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigInquiryLevelsComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "CONFIGINQUIRYLEVELS_BUSKEY")
	public List<ConfigInquiryLevelsDO> findAllRecordsFromRepository(String filter) {

		List<ConfigInquiryLevelsDO> dbimageConfigInquiryLevelsDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageConfigInquiryLevelsDOlist = this.theConfigInquiryLevelsRepository
					.findAllActive();
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageConfigInquiryLevelsDOlist = this.theConfigInquiryLevelsRepository
					.findAllInActive();
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageConfigInquiryLevelsDOlist = this.theConfigInquiryLevelsRepository
					.findAll();

		}
		return dbimageConfigInquiryLevelsDOlist;
	}

	/**
	* This method search the database table CONFIG_INQUIRY_LEVELS records based on business key (inquiryLevel and applicableDobj)
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or mandatory attributes business key is not present
	*/

	public TxnTransferObj findAllByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			ConfigInquiryLevelsDO reqConfigInquiryLevelsDO = (ConfigInquiryLevelsDO) txnTransferObj.getTxnPayload()
					.getConfigInquiryLevelsDO();
			theConfigInquiryLevelsComponentRule.preValidateConfigInquiryLevelsfindAllByBusinessKey(txnTransferObj);
			theConfigInquiryLevelsComponentRule.preExecuteConfigInquiryLevelsfindAllByBusinessKey(txnTransferObj);

			List<ConfigInquiryLevelsDO> dbimageConfigInquiryLevelsDOList = executeRepositoryQuery(
					reqConfigInquiryLevelsDO.getInquiryLevel(), reqConfigInquiryLevelsDO.getApplicableDobj(), 
					reqConfigInquiryLevelsDO.getInquiryFilter());

			if (null == dbimageConfigInquiryLevelsDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"ConfigInquiryLevelsComponent.findByBusinessKey search result have no records");
				//Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setConfigInquiryLevelsDOList(dbimageConfigInquiryLevelsDOList);
			}

			theConfigInquiryLevelsComponentRule.postExecuteConfigInquiryLevelsfindAllByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"ConfigInquiryLevelsComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	* Execute the query using JPA Repository 
	*@since 1.0
	*@param  inquiryLevel
	*@param applicableDobj 
	*@param filter
	*@return  ConfigInquiryLevelsDO returns the populated ConfigInquiryLevelsDO object
	*/
	@CacheResult(cacheName = "CONFIGINQUIRYLEVELS_BUSKEY")
	public List<ConfigInquiryLevelsDO> executeRepositoryQuery(String inquiryLevel, String applicableDobj, String filter) {
		List<ConfigInquiryLevelsDO> dbimageConfigInquiryLevelsDOList = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageConfigInquiryLevelsDOList = this.theConfigInquiryLevelsRepository
					.findAllByBusinessKeyActive(inquiryLevel, applicableDobj);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageConfigInquiryLevelsDOList = this.theConfigInquiryLevelsRepository
					.findAllByBusinessKeyInactive(inquiryLevel, applicableDobj);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageConfigInquiryLevelsDOList = this.theConfigInquiryLevelsRepository
					.findAllByBusinessKeyAll(inquiryLevel, applicableDobj);

		}

		return dbimageConfigInquiryLevelsDOList;
	}

	/**
	* perform the common validation that ConfigInquiryLevelsDO, ConfigInquiryLevelsDO.configLanguageCodeKey and ConfigInquiryLevelsDO.key is not null
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigInquiryLevelsDO is needed in the request");
		}

		ConfigInquiryLevelsDO theConfigInquiryLevelsDO = (ConfigInquiryLevelsDO) txnTransferObj.getTxnPayload()
				.getConfigInquiryLevelsDO();
		if (null == theConfigInquiryLevelsDO.getInquiryLevel() || theConfigInquiryLevelsDO.getInquiryLevel().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1017",
					"ConfigInquiryLevelsDO.inquiryLevel should not be null");
		}

		//if inquiry filter is null then consider the inquiry is for ACTIVE records
		if (null == txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getInquiryFilter());
		}
	}

	/**
	* perform the common validation that ConfigInquiryLevelsDO and ConfigInquiryLevelsDO.configLanguageCodeKey is not null
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigInquiryLevelsDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforefindAllRecords(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"ConfigInquiryLevelsDO is needed in the request");
		}
		
		//if inquiry filter is null then consider the inquiry is for ACTIVE records
		if (null == txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getConfigInquiryLevelsDO().getInquiryFilter());
		}
	}

}
