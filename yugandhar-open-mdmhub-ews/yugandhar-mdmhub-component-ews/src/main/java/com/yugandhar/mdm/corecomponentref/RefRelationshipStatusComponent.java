package com.yugandhar.mdm.corecomponentref;
/* Generated Sep 8, 2017 3:56:16 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.ConfigLanguageCodeDO;
import com.yugandhar.mdm.extern.dobj.RefRelationshipStatusDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefRelationshipStatusDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefRelationshipStatusComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefRelationshipStatusRepository theRefRelationshipStatusRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefRelationshipStatusComponentRule theRefRelationshipStatusComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefRelationshipStatusComponent() {
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
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefRelationshipStatusComponentRule.prevalidateRefRelationshipStatusCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefRelationshipStatusDO reqRefRelationshipStatusDO = (RefRelationshipStatusDO) txnTransferObj
					.getTxnPayload().getRefRelationshipStatusDO();
			if (null == reqRefRelationshipStatusDO.getPrimaryKeyDO()
					|| null == reqRefRelationshipStatusDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefRelationshipStatusDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefRelationshipStatusDO.setIdPk(reqRefRelationshipStatusDO.getPrimaryKeyDO().getIdPk());
				RefRelationshipStatusDO dbimageRefRelationshipStatusDO = entityManager
						.find(RefRelationshipStatusDO.class, reqRefRelationshipStatusDO.getIdPk());
				if (null != dbimageRefRelationshipStatusDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefRelationshipStatusComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefRelationshipStatusComponentRule.preExecuteRefRelationshipStatusCompPersist(reqRefRelationshipStatusDO,
					txnTransferObj);
			entityManager.persist(reqRefRelationshipStatusDO);
			entityManager.flush();
			reqRefRelationshipStatusDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefRelationshipStatusDO(new RefRelationshipStatusDO(reqRefRelationshipStatusDO));
			theRefRelationshipStatusComponentRule.postExecuteRefRelationshipStatusCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefRelationshipStatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefRelationshipStatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefRelationshipStatusComponent.persist failed unexpectedly");
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
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefRelationshipStatusComponentRule.PrevalidateRefRelationshipStatusCompMerge(txnTransferObj);
			RefRelationshipStatusDO reqRefRelationshipStatusDO = (RefRelationshipStatusDO) txnTransferObj
					.getTxnPayload().getRefRelationshipStatusDO();
			RefRelationshipStatusDO dbimageRefRelationshipStatusDO = entityManager.find(RefRelationshipStatusDO.class,
					reqRefRelationshipStatusDO.getIdPk());
			if (null == dbimageRefRelationshipStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefRelationshipStatusComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefRelationshipStatusDO);
			BeanUtils.copyProperties(reqRefRelationshipStatusDO, dbimageRefRelationshipStatusDO, strIgnoreProperties);
			entityManager.detach(dbimageRefRelationshipStatusDO);
			theRefRelationshipStatusComponentRule.preExecuteRefRelationshipStatusCompMerge(reqRefRelationshipStatusDO,
					dbimageRefRelationshipStatusDO, txnTransferObj);
			RefRelationshipStatusDO mergedRefRelationshipStatusDO = entityManager.merge(dbimageRefRelationshipStatusDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefRelationshipStatusDO(new RefRelationshipStatusDO(mergedRefRelationshipStatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefRelationshipStatusComponentRule.postExecuteRefRelationshipStatusCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefRelationshipStatusComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefRelationshipStatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefRelationshipStatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefRelationshipStatusComponent.merge failed unexpectedly");
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
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefRelationshipStatusComponentRule.prevalidateRefRelationshipStatusCompFindById(txnTransferObj);
			RefRelationshipStatusDO reqRefRelationshipStatusDO = (RefRelationshipStatusDO) txnTransferObj
					.getTxnPayload().getRefRelationshipStatusDO();
			RefRelationshipStatusDO dbimageRefRelationshipStatusDO = entityManager.find(RefRelationshipStatusDO.class,
					reqRefRelationshipStatusDO.getIdPk());
			if (null == dbimageRefRelationshipStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefRelationshipStatusComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefRelationshipStatusDO(new RefRelationshipStatusDO(dbimageRefRelationshipStatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefRelationshipStatusComponentRule.postExecuteRefRelationshipStatusCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefRelationshipStatusComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefRelationshipStatusDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefRelationshipStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefRelationshipStatusDO is needed in the request");
		}

		RefRelationshipStatusDO theRefRelationshipStatusDO = (RefRelationshipStatusDO) txnTransferObj.getTxnPayload()
				.getRefRelationshipStatusDO();
		if (null == theRefRelationshipStatusDO.getKey() || theRefRelationshipStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefRelationshipStatusDO.key should not be null");
		}

		if (null == theRefRelationshipStatusDO.getConfigLanguageCodeKey()
				|| theRefRelationshipStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefRelationshipStatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefRelationshipStatusDO.getValue() || theRefRelationshipStatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefRelationshipStatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefRelationshipStatusDO.getConfigLanguageCodeKey()
				|| theRefRelationshipStatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefRelationshipStatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefRelationshipStatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefRelationshipStatusDO.setCreatedTs(new Date());
		theRefRelationshipStatusDO.setUpdatedTs(new Date());
		theRefRelationshipStatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefRelationshipStatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefRelationshipStatusDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefRelationshipStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefRelationshipStatusDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefRelationshipStatusDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefRelationshipStatusDO.version should not be null");
		}

		RefRelationshipStatusDO theRefRelationshipStatusDO = (RefRelationshipStatusDO) txnTransferObj.getTxnPayload()
				.getRefRelationshipStatusDO();
		if (null == theRefRelationshipStatusDO.getKey() || theRefRelationshipStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefRelationshipStatusDO.key should not be null");
		}

		if (null == theRefRelationshipStatusDO.getConfigLanguageCodeKey()
				|| theRefRelationshipStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefRelationshipStatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefRelationshipStatusDO.getValue() || theRefRelationshipStatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefRelationshipStatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefRelationshipStatusDO.getConfigLanguageCodeKey()
				|| theRefRelationshipStatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefRelationshipStatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefRelationshipStatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefRelationshipStatusDO.setUpdatedTs(new Date());
		theRefRelationshipStatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefRelationshipStatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefRelationshipStatusDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefRelationshipStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefRelationshipStatusDO is needed in the request");
		}
		RefRelationshipStatusDO theRefRelationshipStatusDO = (RefRelationshipStatusDO) txnTransferObj.getTxnPayload()
				.getRefRelationshipStatusDO();
		if (null == theRefRelationshipStatusDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefRelationshipStatusDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefRelationshipStatusDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefRelationshipStatusDO> pageRefRelationshipStatusDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefRelationshipStatusDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefRelationshipStatus reference list does not have any records in the database");
			}

			if ((pageRefRelationshipStatusDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefRelationshipStatus, total number of pages is "
								+ pageRefRelationshipStatusDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefRelationshipStatusDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefRelationshipStatusDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefRelationshipStatusDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefRelationshipStatusDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefRelationshipStatusDO.getSize());

			List<RefRelationshipStatusDO> dbimageRefRelationshipStatusDOlist = pageRefRelationshipStatusDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefRelationshipStatusDO> clonedRefRelationshipStatusDOList = null;
			if (null != dbimageRefRelationshipStatusDOlist && dbimageRefRelationshipStatusDOlist.size() > 0) {
				clonedRefRelationshipStatusDOList = new ArrayList<RefRelationshipStatusDO>();
				Iterator<RefRelationshipStatusDO> itr = dbimageRefRelationshipStatusDOlist.iterator();
				while (itr.hasNext()) {
					RefRelationshipStatusDO theRefRelationshipStatusDO = new RefRelationshipStatusDO(itr.next());
					clonedRefRelationshipStatusDOList.add(theRefRelationshipStatusDO);
				}
			}

			if (null == clonedRefRelationshipStatusDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefRelationshipStatus reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefRelationshipStatusDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefRelationshipStatus reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefRelationshipStatusDOList(clonedRefRelationshipStatusDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefRelationshipStatusComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFRELATIONSHIPSTATUS_BUSKEY")
	public Page<RefRelationshipStatusDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefRelationshipStatusDO> pageRefRelationshipStatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefRelationshipStatusDO = this.theRefRelationshipStatusRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefRelationshipStatusDO = this.theRefRelationshipStatusRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefRelationshipStatusDO = this.theRefRelationshipStatusRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefRelationshipStatusDO;
	}

	/**
	 * This method search the database table REF_CURRENCY records based on
	 * business key (e.g.LanguageCode and Key)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefRelationshipStatusDO reqRefRelationshipStatusDO = (RefRelationshipStatusDO) txnTransferObj
					.getTxnPayload().getRefRelationshipStatusDO();
			theRefRelationshipStatusComponentRule.preValidateRefRelationshipStatusfindByBusinessKey(txnTransferObj);
			theRefRelationshipStatusComponentRule.preExecuteRefRelationshipStatusfindByBusinessKey(txnTransferObj);

			RefRelationshipStatusDO dbimageRefRelationshipStatusDO = executeRepositoryQuery(
					reqRefRelationshipStatusDO.getConfigLanguageCodeKey(), reqRefRelationshipStatusDO.getKey(),
					reqRefRelationshipStatusDO.getInquiryFilter());

			if (null == dbimageRefRelationshipStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefRelationshipStatusComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefRelationshipStatusDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefRelationshipStatusDO(new RefRelationshipStatusDO(dbimageRefRelationshipStatusDO));
			}

			theRefRelationshipStatusComponentRule.postExecuteRefRelationshipStatusfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefRelationshipStatusComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefRelationshipStatusDO returns the populated RefRelationshipStatusDO object
	 */
	@CacheResult(cacheName = "REFRELATIONSHIPSTATUS_BUSKEY")
	public RefRelationshipStatusDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefRelationshipStatusDO dbimageRefRelationshipStatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefRelationshipStatusDO = this.theRefRelationshipStatusRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefRelationshipStatusDO = this.theRefRelationshipStatusRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefRelationshipStatusDO = this.theRefRelationshipStatusRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefRelationshipStatusDO;
	}

	/**
	 * perform the common validation that RefRelationshipStatusDO,
	 * RefRelationshipStatusDO.configLanguageCodeKey and RefRelationshipStatusDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefRelationshipStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefRelationshipStatusDO is needed in the request");
		}
		RefRelationshipStatusDO theRefRelationshipStatusDO = (RefRelationshipStatusDO) txnTransferObj.getTxnPayload()
				.getRefRelationshipStatusDO();
		if (null == theRefRelationshipStatusDO.getKey() || theRefRelationshipStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefRelationshipStatusDO.key should not be null");
		}

		if (null == theRefRelationshipStatusDO.getConfigLanguageCodeKey()
				|| theRefRelationshipStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefRelationshipStatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefRelationshipStatusDO and
	 * RefRelationshipStatusDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefRelationshipStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefRelationshipStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefRelationshipStatusDO is needed in the request");
		}
		RefRelationshipStatusDO theRefRelationshipStatusDO = (RefRelationshipStatusDO) txnTransferObj.getTxnPayload()
				.getRefRelationshipStatusDO();

		if (null == theRefRelationshipStatusDO.getConfigLanguageCodeKey()
				|| theRefRelationshipStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefRelationshipStatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefRelationshipStatusDO().getInquiryFilter());
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
