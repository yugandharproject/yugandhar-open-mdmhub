package com.yugandhar.mdm.match.componentref;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.cache.annotation.CacheKey;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.IgnoreAttributesUtil;
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.config.langcode.ConfigLanguageCodeComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.RefMatchThresholdDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefMatchThresholdDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 */

@Scope(value = "prototype")
@Component
public class RefMatchThresholdComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefMatchThresholdRepository theRefMatchThresholdRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefMatchThresholdComponentRule theRefMatchThresholdComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefMatchThresholdComponent() {
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
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefMatchThresholdComponentRule.prevalidateRefMatchThresholdCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefMatchThresholdDO reqRefMatchThresholdDO = (RefMatchThresholdDO) txnTransferObj.getTxnPayload()
					.getRefMatchThresholdDO();
			if (null == reqRefMatchThresholdDO.getPrimaryKeyDO()
					|| null == reqRefMatchThresholdDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefMatchThresholdDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefMatchThresholdDO.setIdPk(reqRefMatchThresholdDO.getPrimaryKeyDO().getIdPk());
				RefMatchThresholdDO dbimageRefMatchThresholdDO = entityManager.find(RefMatchThresholdDO.class,
						reqRefMatchThresholdDO.getIdPk());
				if (null != dbimageRefMatchThresholdDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefMatchThresholdComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefMatchThresholdComponentRule.preExecuteRefMatchThresholdCompPersist(reqRefMatchThresholdDO,
					txnTransferObj);
			entityManager.persist(reqRefMatchThresholdDO);
			entityManager.flush();
			reqRefMatchThresholdDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefMatchThresholdDO(new RefMatchThresholdDO(reqRefMatchThresholdDO));
			theRefMatchThresholdComponentRule.postExecuteRefMatchThresholdCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchThresholdComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchThresholdComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchThresholdComponent.persist failed unexpectedly");
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
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefMatchThresholdComponentRule.PrevalidateRefMatchThresholdCompMerge(txnTransferObj);
			RefMatchThresholdDO reqRefMatchThresholdDO = (RefMatchThresholdDO) txnTransferObj.getTxnPayload()
					.getRefMatchThresholdDO();
			RefMatchThresholdDO dbimageRefMatchThresholdDO = entityManager.find(RefMatchThresholdDO.class,
					reqRefMatchThresholdDO.getIdPk());
			if (null == dbimageRefMatchThresholdDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchThresholdComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefMatchThresholdDO);
			BeanUtils.copyProperties(reqRefMatchThresholdDO, dbimageRefMatchThresholdDO, strIgnoreProperties);
			entityManager.detach(dbimageRefMatchThresholdDO);
			theRefMatchThresholdComponentRule.preExecuteRefMatchThresholdCompMerge(reqRefMatchThresholdDO,
					dbimageRefMatchThresholdDO, txnTransferObj);
			RefMatchThresholdDO mergedRefMatchThresholdDO = entityManager.merge(dbimageRefMatchThresholdDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefMatchThresholdDO(new RefMatchThresholdDO(mergedRefMatchThresholdDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchThresholdComponentRule.postExecuteRefMatchThresholdCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefMatchThresholdComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchThresholdComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchThresholdComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchThresholdComponent.merge failed unexpectedly");
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
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefMatchThresholdComponentRule.prevalidateRefMatchThresholdCompFindById(txnTransferObj);
			RefMatchThresholdDO reqRefMatchThresholdDO = (RefMatchThresholdDO) txnTransferObj.getTxnPayload()
					.getRefMatchThresholdDO();
			RefMatchThresholdDO dbimageRefMatchThresholdDO = entityManager.find(RefMatchThresholdDO.class,
					reqRefMatchThresholdDO.getIdPk());
			if (null == dbimageRefMatchThresholdDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchThresholdComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefMatchThresholdDO(new RefMatchThresholdDO(dbimageRefMatchThresholdDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchThresholdComponentRule.postExecuteRefMatchThresholdCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchThresholdComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefMatchThresholdDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchThresholdDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchThresholdDO is needed in the request");
		}

		RefMatchThresholdDO theRefMatchThresholdDO = (RefMatchThresholdDO) txnTransferObj.getTxnPayload()
				.getRefMatchThresholdDO();
		if (null == theRefMatchThresholdDO.getAttrBlockName() || theRefMatchThresholdDO.getAttrBlockName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10093",
					"refMatchThresholdDO.attrBlockName is needed in the request");
		}

		if (null == theRefMatchThresholdDO.getMatchThreshold()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10094",
					"refMatchThresholdDO.matchThreshold is needed in the request");
		}

		if (null == theRefMatchThresholdDO.getDecayThresholdInDays()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10095",
					"refMatchThresholdDO.decayThresholdInDays is needed in the request");
		}

		if (null == theRefMatchThresholdDO.getDecayPercentage()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10096",
					"refMatchThresholdDO.decayPercentage is needed in the request");
		}

		if (null == theRefMatchThresholdDO.getMaxDecayPercentage()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10097",
					"refMatchThresholdDO.maxDecayPercentage is needed in the request");
		}

		theRefMatchThresholdDO.setCreatedTs(new Date());
		theRefMatchThresholdDO.setUpdatedTs(new Date());
		theRefMatchThresholdDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchThresholdDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefMatchThresholdDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchThresholdDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchThresholdDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchThresholdDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefMatchThresholdDO.version should not be null");
		}

		RefMatchThresholdDO theRefMatchThresholdDO = (RefMatchThresholdDO) txnTransferObj.getTxnPayload()
				.getRefMatchThresholdDO();
		if (null == theRefMatchThresholdDO.getAttrBlockName() || theRefMatchThresholdDO.getAttrBlockName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10093",
					"refMatchThresholdDO.attrBlockName is needed in the request");
		}

		if (null == theRefMatchThresholdDO.getMatchThreshold()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10094",
					"refMatchThresholdDO.matchThreshold is needed in the request");
		}

		if (null == theRefMatchThresholdDO.getDecayThresholdInDays()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10095",
					"refMatchThresholdDO.decayThresholdInDays is needed in the request");
		}

		if (null == theRefMatchThresholdDO.getDecayPercentage()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10096",
					"refMatchThresholdDO.decayPercentage is needed in the request");
		}

		if (null == theRefMatchThresholdDO.getMaxDecayPercentage()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10097",
					"refMatchThresholdDO.maxDecayPercentage is needed in the request");
		}

		theRefMatchThresholdDO.setUpdatedTs(new Date());
		theRefMatchThresholdDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchThresholdDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefMatchThresholdDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchThresholdDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchThresholdDO is needed in the request");
		}
		RefMatchThresholdDO theRefMatchThresholdDO = (RefMatchThresholdDO) txnTransferObj.getTxnPayload()
				.getRefMatchThresholdDO();
		if (null == theRefMatchThresholdDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchThresholdDO.idpk should not be null");
		}

	}

	/**
	 * This method search the database table records based on business key
	 * (attrBlockName)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefMatchThresholdDO reqRefMatchThresholdDO = (RefMatchThresholdDO) txnTransferObj.getTxnPayload()
					.getRefMatchThresholdDO();
			theRefMatchThresholdComponentRule.preValidateRefMatchThresholdfindByBusinessKey(txnTransferObj);
			theRefMatchThresholdComponentRule.preExecuteRefMatchThresholdfindByBusinessKey(txnTransferObj);

			RefMatchThresholdDO dbimageRefMatchThresholdDO = executeRepositoryQuery(
					reqRefMatchThresholdDO.getAttrBlockName(), reqRefMatchThresholdDO.getInquiryFilter());

			if (null == dbimageRefMatchThresholdDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchThresholdComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefMatchThresholdDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefMatchThresholdDO(new RefMatchThresholdDO(dbimageRefMatchThresholdDO));
			}

			theRefMatchThresholdComponentRule.postExecuteRefMatchThresholdfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchThresholdComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param attrBlockName
	 * @param filter
	 * @return RefMatchThresholdDO returns the populated RefMatchThresholdDO
	 *         object
	 */
	@CacheResult(cacheName = "REFMATCHTHRESHOLD_BUSKEY")
	public RefMatchThresholdDO executeRepositoryQuery(String attrBlockName, String filter) {
		RefMatchThresholdDO dbimageRefMatchThresholdDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefMatchThresholdDO = this.theRefMatchThresholdRepository.findByBusinessKeyActive(attrBlockName);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefMatchThresholdDO = this.theRefMatchThresholdRepository.findByBusinessKeyInactive(attrBlockName);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefMatchThresholdDO = this.theRefMatchThresholdRepository.findByBusinessKeyAll(attrBlockName);

		}

		return dbimageRefMatchThresholdDO;
	}

	/**
	 * perform the common validation that RefMatchThresholdDO,
	 * RefMatchThresholdDO.configLanguageCodeKey and RefMatchThresholdDO.key is
	 * not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchThresholdDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchThresholdDO is needed in the request");
		}
		RefMatchThresholdDO theRefMatchThresholdDO = (RefMatchThresholdDO) txnTransferObj.getTxnPayload()
				.getRefMatchThresholdDO();
		if (null == theRefMatchThresholdDO.getAttrBlockName() || theRefMatchThresholdDO.getAttrBlockName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10093",
					"refMatchThresholdDO.attrBlockName is needed in the request");
		}
		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchThresholdDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getInquiryFilter());
		}
	}

	/**
	 * Returns all the records from the for RefMatchThresholdDO entity. maximum
	 * number of of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecords(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecords(txnTransferObj);
			Page<RefMatchThresholdDO> pageRefMatchThresholdDO = findAllRecordsFromRepository(
					txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefMatchThresholdDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchThreshold reference list does not have any records in the database");
			}

			if ((pageRefMatchThresholdDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefMatchThreshold, total number of pages is "
								+ pageRefMatchThresholdDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefMatchThresholdDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefMatchThresholdDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefMatchThresholdDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefMatchThresholdDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefMatchThresholdDO.getSize());

			List<RefMatchThresholdDO> dbimageRefMatchThresholdDOlist = pageRefMatchThresholdDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefMatchThresholdDO> clonedRefMatchThresholdDOList = null;
			if (null != dbimageRefMatchThresholdDOlist && dbimageRefMatchThresholdDOlist.size() > 0) {
				clonedRefMatchThresholdDOList = new ArrayList<RefMatchThresholdDO>();
				Iterator<RefMatchThresholdDO> itr = dbimageRefMatchThresholdDOlist.iterator();
				while (itr.hasNext()) {
					RefMatchThresholdDO theRefMatchThresholdDO = new RefMatchThresholdDO(itr.next());
					clonedRefMatchThresholdDOList.add(theRefMatchThresholdDO);
				}
			}

			if (null == clonedRefMatchThresholdDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchThreshold reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefMatchThresholdDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchThreshold reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefMatchThresholdDOList(clonedRefMatchThresholdDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchThresholdComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFMATCHTHRESHOLD_BUSKEY")
	public Page<RefMatchThresholdDO> findAllRecordsFromRepository(@CacheKey String filter,
			@CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefMatchThresholdDO> pageRefMatchThresholdDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefMatchThresholdDO = this.theRefMatchThresholdRepository.findAllActive(localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefMatchThresholdDO = this.theRefMatchThresholdRepository.findAllInActive(localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefMatchThresholdDO = this.theRefMatchThresholdRepository.findAll(localPageable);

		}

		return pageRefMatchThresholdDO;
	}

	/**
	 * perform the common validation that RefMatchThresholdDO and
	 * RefMatchThresholdDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchThresholdDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecords(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchThresholdDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchThresholdDO is needed in the request");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchThresholdDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchThresholdDO().getInquiryFilter());
		}

		// set the current page index to zero (0) if page index is not provided
		// in the request or negative value is provided
		if (null == txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice()
				|| txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice() < 0) {
			txnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(0);
		}

		// set default page size as configured in application properties
		if (null == txnTransferObj.getTxnPayload().getPaginationPageSize()
				|| txnTransferObj.getTxnPayload().getPaginationPageSize() <= 0) {
			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_pagination_referencelov_default_pagesize,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			txnTransferObj.getTxnPayload().setPaginationPageSize(Integer.valueOf(theConfigAppPropertiesDO.getValue()));

		}

	}

}
