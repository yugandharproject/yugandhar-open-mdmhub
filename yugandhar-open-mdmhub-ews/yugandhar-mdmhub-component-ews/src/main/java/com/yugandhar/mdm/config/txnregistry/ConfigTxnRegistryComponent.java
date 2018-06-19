package com.yugandhar.mdm.config.txnregistry;
/* Generated Jun 13, 2017 1:01:46 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;

import javax.cache.annotation.CacheResult;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
import com.yugandhar.mdm.extern.dobj.ConfigTxnRegistryDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model ConfigTxnRegistryDO class
*@author Yugandhar
*@version 1.0
*@since 1.0
 * 
*/

@Component
@Scope(value = "prototype")
public class ConfigTxnRegistryComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	//  entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private ConfigTxnRegistryRepository theConfigTxnRegistryRepository;

	@Autowired
 CommonValidationUtil commonValidationUtil;
	
	@Autowired
	ConfigTxnRegistryComponentRule theConfigTxnRegistryComponentRule;
	

	//default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public ConfigTxnRegistryComponent() {
		
	}

	/**
	*This method creates a record in database. generates the idpk if not provided in the request and 
	*populate the updatedByUser, updatedTxnRefId, createdTsString, updatedTsString attributes.
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with persisted instance 
	*@throws YugandharCommonException if ConfigTxnRegistryDO object is not present in the request or other mandatory attributes not present
	*
	*/
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		logger.debug("Persist Method - starts");
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theConfigTxnRegistryComponentRule.prevalidateConfigTxnRegistryCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			ConfigTxnRegistryDO reqConfigTxnRegistryDO = (ConfigTxnRegistryDO) txnTransferObj.getTxnPayload()
					.getConfigTxnRegistryDO();
			if (null == reqConfigTxnRegistryDO.getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqConfigTxnRegistryDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				ConfigTxnRegistryDO dbimageConfigTxnRegistryDO = entityManager.find(ConfigTxnRegistryDO.class,
						reqConfigTxnRegistryDO.getIdPk());
				if (null != dbimageConfigTxnRegistryDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101","ConfigTxnRegistryComponent.Component.persist failed with Validation Exception"); //Record already present for given Idpk
				}
			}
			theConfigTxnRegistryComponentRule.preExecuteConfigTxnRegistryCompPersist(reqConfigTxnRegistryDO,txnTransferObj);
			entityManager.persist(reqConfigTxnRegistryDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setConfigTxnRegistryDO(reqConfigTxnRegistryDO);
			theConfigTxnRegistryComponentRule.postExecuteConfigTxnRegistryCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re , "ConfigTxnRegistryComponent.persist failed unexpectedly" ); //Transaction Failed due to unknown error, please check statck trace
		}
		logger.debug("Persist Method - Ends");
		return respTxnTransferObj;
	}

	/**This service updates the record in database. populate the updatedByUser, updatedTxnRefId, updatedTsString attributes
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigTxnRegistryDO object is not present in the request or mandatory attributes primary key is not present
	*/

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		logger.debug("merge Method - starts");
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theConfigTxnRegistryComponentRule.PrevalidateConfigTxnRegistryCompMerge(txnTransferObj);
			ConfigTxnRegistryDO reqConfigTxnRegistryDO = (ConfigTxnRegistryDO) txnTransferObj.getTxnPayload()
					.getConfigTxnRegistryDO();
			ConfigTxnRegistryDO dbimageConfigTxnRegistryDO = entityManager.find(ConfigTxnRegistryDO.class,
					reqConfigTxnRegistryDO.getIdPk());
			if (null == dbimageConfigTxnRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102","ConfigTxnRegistryComponent.merge failed with validation Exception"); //Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqConfigTxnRegistryDO);
			BeanUtils.copyProperties(reqConfigTxnRegistryDO, dbimageConfigTxnRegistryDO, strIgnoreProperties);
			entityManager.detach(dbimageConfigTxnRegistryDO);
			theConfigTxnRegistryComponentRule.preExecuteConfigTxnRegistryCompMerge(reqConfigTxnRegistryDO,
					dbimageConfigTxnRegistryDO,txnTransferObj);
			respTxnTransferObj.getTxnPayload().setConfigTxnRegistryDO(entityManager.merge(dbimageConfigTxnRegistryDO));
			entityManager.flush();
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theConfigTxnRegistryComponentRule.postExecuteConfigTxnRegistryCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe, "OptimisticLockException"); //OptimisticLockException- Row was updated or deleted by another transaction
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re , "ConfigTxnRegistryComponent.merge failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
		}
		logger.debug("merge successful");
		return respTxnTransferObj;
	}

	/**
	* This method search the database record based on primary key. 
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigTxnRegistryDO object is not present in the request or mandatory attributes primary key is not present
	*/
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		logger.debug("findById method start");
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			theConfigTxnRegistryComponentRule.prevalidateConfigTxnRegistryCompFindById(txnTransferObj);
			ConfigTxnRegistryDO reqConfigTxnRegistryDO = (ConfigTxnRegistryDO) txnTransferObj.getTxnPayload()
					.getConfigTxnRegistryDO();
			ConfigTxnRegistryDO dbimageConfigTxnRegistryDO = entityManager.find(ConfigTxnRegistryDO.class,
					reqConfigTxnRegistryDO.getIdPk());
			if (null == dbimageConfigTxnRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102","ConfigTxnRegistryComponent.findbyId failed with Validation Exception"); //Record not found
			}
			respTxnTransferObj.getTxnPayload().setConfigTxnRegistryDO(dbimageConfigTxnRegistryDO);
			entityManager.flush();
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theConfigTxnRegistryComponentRule.postExecuteConfigTxnRegistryCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,  "ConfigTxnRegistryComponent.findById failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
		}
		logger.debug("Entity Found for given idpk, findById ends ");
		return respTxnTransferObj;

	}

	/**
	* perform the common validation before persisting ConfigTxnRegistryDO object in the database.
	* populate createdTimestamp, updatedTimestamp, transaction reference Id and user
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigTxnRegistryDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		if (null == txnTransferObj.getTxnPayload().getConfigTxnRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001","ConfigTxnRegistryDO is needed in the request"); 
		}
		ConfigTxnRegistryDO theConfigTxnRegistryDO = (ConfigTxnRegistryDO) txnTransferObj.getTxnPayload()
				.getConfigTxnRegistryDO();
		if (null == theConfigTxnRegistryDO.getTxnserviceName() || theConfigTxnRegistryDO.getTxnserviceName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1014",
					"ConfigTxnRegistryDO.txnserviceName should not be null");
		}

		if (null == theConfigTxnRegistryDO.getTxnserviceClass() || theConfigTxnRegistryDO.getTxnserviceClass().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1015",
					"ConfigTxnRegistryDO.txnserviceClass should not be null");
		}
		
		if (null == theConfigTxnRegistryDO.getTxnserviceClassmethod() || theConfigTxnRegistryDO.getTxnserviceClassmethod().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1016",
					"ConfigTxnRegistryDO.txnserviceClassmethod should not be null");
		}
		
		theConfigTxnRegistryDO.setCreatedTs(new Date());
		theConfigTxnRegistryDO.setUpdatedTs(new Date());
		theConfigTxnRegistryDO.setUpdatedTxnRefId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theConfigTxnRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	* perform the common validation before updating ConfigTxnRegistryDO object in the database.
	* populate createdTimestamp, updatedTimestamp, transaction reference Id and user
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigTxnRegistryDO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		if (null == txnTransferObj.getTxnPayload().getConfigTxnRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001","ConfigTxnRegistryDO is needed in the request"); 
		}
		ConfigTxnRegistryDO theConfigTxnRegistryDO = (ConfigTxnRegistryDO) txnTransferObj.getTxnPayload()
				.getConfigTxnRegistryDO();
		if (null == theConfigTxnRegistryDO.getTxnserviceName() || theConfigTxnRegistryDO.getTxnserviceName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1014",
					"ConfigTxnRegistryDO.txnserviceName should not be null");
		}

		if (null == theConfigTxnRegistryDO.getTxnserviceClass() || theConfigTxnRegistryDO.getTxnserviceClass().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1015",
					"ConfigTxnRegistryDO.txnserviceClass should not be null");
		}
		
		if (null == theConfigTxnRegistryDO.getTxnserviceClassmethod() || theConfigTxnRegistryDO.getTxnserviceClassmethod().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1016",
					"ConfigTxnRegistryDO.txnserviceClassmethod should not be null");
		}
		
		theConfigTxnRegistryDO.setUpdatedTs(new Date());
		theConfigTxnRegistryDO.setUpdatedTxnRefId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theConfigTxnRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

		

	/**
	* This method search the database record based on business key (e.g.LanguageCode and Key)
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ConfigTxnRegistryDO object is not present in the request or mandatory attributes business key is not present
	*/

	@Transactional
	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		
		try {
			ConfigTxnRegistryDO reqConfigTxnRegistryDO = (ConfigTxnRegistryDO) txnTransferObj.getTxnPayload().getConfigTxnRegistryDO();
			theConfigTxnRegistryComponentRule.preValidateConfigTxnRegistryfindByBusinessKey(txnTransferObj);
			theConfigTxnRegistryComponentRule.preExecuteConfigTxnRegistryfindByBusinessKey(txnTransferObj);
			
			//if inquiry filter is null then consider the inquiry is for ACTIVE records
			if (null == reqConfigTxnRegistryDO.getInquiryFilter())
				reqConfigTxnRegistryDO.setInquiryFilter("ACTIVE");

			//ConfigTxnRegistryDO dbimageConfigTxnRegistryDO = null;
			//if (reqConfigTxnRegistryDO.getInquiryFilter().equals("ACTIVE")) {
			//	dbimageConfigTxnRegistryDO =this.theConfigTxnRegistryRepository.findByBusinessKeyActive(
			//			reqConfigTxnRegistryDO.getTxnserviceName());
			//} 
			
			ConfigTxnRegistryDO dbimageConfigTxnRegistryDO = executeRepositoryQuery(
					reqConfigTxnRegistryDO.getTxnserviceName(),reqConfigTxnRegistryDO.getInquiryFilter());
			
			if (null == dbimageConfigTxnRegistryDO) {
				logger.error("ConfigTxnRegistryComponent.findByBusinessKey - dbimageConfigTxnRegistryDO is null after find");
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102","ConfigTxnRegistryComponent.findByBusinessKey search result have no records"); //Record not found
			} else{
				dbimageConfigTxnRegistryDO.setInquiryFilter(txnTransferObj.getTxnPayload()
					.getConfigTxnRegistryDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setConfigTxnRegistryDO(new ConfigTxnRegistryDO(dbimageConfigTxnRegistryDO));
			}

			theConfigTxnRegistryComponentRule.postExecuteConfigTxnRegistryfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			logger.error(yce.getMessage());
			throw yce;
		}
		catch (RuntimeException re) {
			logger.error("ConfigTxnRegistryComponent.findByBusinessKey failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,  "ConfigTxnRegistryComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}
	
	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param txnserviceName
	 * @param filter
	 * @return ConfigTxnRegistryDO returns the populated ConfigTxnRegistryDO object
	 */
	@CacheResult(cacheName = "CONFIGTXNREGISTRY_BUSKEY")
	public ConfigTxnRegistryDO executeRepositoryQuery(String txnserviceName, String filter) {
		ConfigTxnRegistryDO dbimageConfigTxnRegistryDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageConfigTxnRegistryDO = this.theConfigTxnRegistryRepository
					.findByBusinessKeyActive(txnserviceName);
		} 

		return dbimageConfigTxnRegistryDO;
	}

}
