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
import com.yugandhar.mdm.extern.dobj.RefAccountSourceStatusDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefAccountSourceStatusDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefAccountSourceStatusComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefAccountSourceStatusRepository theRefAccountSourceStatusRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefAccountSourceStatusComponentRule theRefAccountSourceStatusComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefAccountSourceStatusComponent() {
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
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefAccountSourceStatusComponentRule.prevalidateRefAccountSourceStatusCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefAccountSourceStatusDO reqRefAccountSourceStatusDO = (RefAccountSourceStatusDO) txnTransferObj
					.getTxnPayload().getRefAccountSourceStatusDO();
			if (null == reqRefAccountSourceStatusDO.getPrimaryKeyDO()
					|| null == reqRefAccountSourceStatusDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefAccountSourceStatusDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefAccountSourceStatusDO.setIdPk(reqRefAccountSourceStatusDO.getPrimaryKeyDO().getIdPk());
				RefAccountSourceStatusDO dbimageRefAccountSourceStatusDO = entityManager
						.find(RefAccountSourceStatusDO.class, reqRefAccountSourceStatusDO.getIdPk());
				if (null != dbimageRefAccountSourceStatusDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefAccountSourceStatusComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefAccountSourceStatusComponentRule
					.preExecuteRefAccountSourceStatusCompPersist(reqRefAccountSourceStatusDO, txnTransferObj);
			entityManager.persist(reqRefAccountSourceStatusDO);
			entityManager.flush();
			reqRefAccountSourceStatusDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefAccountSourceStatusDO(new RefAccountSourceStatusDO(reqRefAccountSourceStatusDO));
			theRefAccountSourceStatusComponentRule.postExecuteRefAccountSourceStatusCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAccountSourceStatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAccountSourceStatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountSourceStatusComponent.persist failed unexpectedly");
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
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefAccountSourceStatusComponentRule.PrevalidateRefAccountSourceStatusCompMerge(txnTransferObj);
			RefAccountSourceStatusDO reqRefAccountSourceStatusDO = (RefAccountSourceStatusDO) txnTransferObj
					.getTxnPayload().getRefAccountSourceStatusDO();
			RefAccountSourceStatusDO dbimageRefAccountSourceStatusDO = entityManager
					.find(RefAccountSourceStatusDO.class, reqRefAccountSourceStatusDO.getIdPk());
			if (null == dbimageRefAccountSourceStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAccountSourceStatusComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefAccountSourceStatusDO);
			BeanUtils.copyProperties(reqRefAccountSourceStatusDO, dbimageRefAccountSourceStatusDO, strIgnoreProperties);
			entityManager.detach(dbimageRefAccountSourceStatusDO);
			theRefAccountSourceStatusComponentRule.preExecuteRefAccountSourceStatusCompMerge(
					reqRefAccountSourceStatusDO, dbimageRefAccountSourceStatusDO, txnTransferObj);
			RefAccountSourceStatusDO mergedRefAccountSourceStatusDO = entityManager
					.merge(dbimageRefAccountSourceStatusDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefAccountSourceStatusDO(new RefAccountSourceStatusDO(mergedRefAccountSourceStatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAccountSourceStatusComponentRule.postExecuteRefAccountSourceStatusCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefAccountSourceStatusComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAccountSourceStatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAccountSourceStatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountSourceStatusComponent.merge failed unexpectedly");
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
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefAccountSourceStatusComponentRule.prevalidateRefAccountSourceStatusCompFindById(txnTransferObj);
			RefAccountSourceStatusDO reqRefAccountSourceStatusDO = (RefAccountSourceStatusDO) txnTransferObj
					.getTxnPayload().getRefAccountSourceStatusDO();
			RefAccountSourceStatusDO dbimageRefAccountSourceStatusDO = entityManager
					.find(RefAccountSourceStatusDO.class, reqRefAccountSourceStatusDO.getIdPk());
			if (null == dbimageRefAccountSourceStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAccountSourceStatusComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefAccountSourceStatusDO(new RefAccountSourceStatusDO(dbimageRefAccountSourceStatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAccountSourceStatusComponentRule.postExecuteRefAccountSourceStatusCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountSourceStatusComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefAccountSourceStatusDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountSourceStatusDO is needed in the request");
		}

		RefAccountSourceStatusDO theRefAccountSourceStatusDO = (RefAccountSourceStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountSourceStatusDO();
		if (null == theRefAccountSourceStatusDO.getKey() || theRefAccountSourceStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAccountSourceStatusDO.key should not be null");
		}

		if (null == theRefAccountSourceStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountSourceStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAccountSourceStatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAccountSourceStatusDO.getValue() || theRefAccountSourceStatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAccountSourceStatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAccountSourceStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountSourceStatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAccountSourceStatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAccountSourceStatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAccountSourceStatusDO.setCreatedTs(new Date());
		theRefAccountSourceStatusDO.setUpdatedTs(new Date());
		theRefAccountSourceStatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAccountSourceStatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefAccountSourceStatusDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountSourceStatusDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAccountSourceStatusDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefAccountSourceStatusDO.version should not be null");
		}

		RefAccountSourceStatusDO theRefAccountSourceStatusDO = (RefAccountSourceStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountSourceStatusDO();
		if (null == theRefAccountSourceStatusDO.getKey() || theRefAccountSourceStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAccountSourceStatusDO.key should not be null");
		}

		if (null == theRefAccountSourceStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountSourceStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAccountSourceStatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAccountSourceStatusDO.getValue() || theRefAccountSourceStatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAccountSourceStatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAccountSourceStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountSourceStatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAccountSourceStatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAccountSourceStatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAccountSourceStatusDO.setUpdatedTs(new Date());
		theRefAccountSourceStatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAccountSourceStatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefAccountSourceStatusDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountSourceStatusDO is needed in the request");
		}
		RefAccountSourceStatusDO theRefAccountSourceStatusDO = (RefAccountSourceStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountSourceStatusDO();
		if (null == theRefAccountSourceStatusDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAccountSourceStatusDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefAccountSourceStatusDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefAccountSourceStatusDO> pageRefAccountSourceStatusDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefAccountSourceStatusDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAccountSourceStatus reference list does not have any records in the database");
			}

			if ((pageRefAccountSourceStatusDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefAccountSourceStatus, total number of pages is "
								+ pageRefAccountSourceStatusDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefAccountSourceStatusDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefAccountSourceStatusDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefAccountSourceStatusDO.getTotalElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalPages(pageRefAccountSourceStatusDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefAccountSourceStatusDO.getSize());

			List<RefAccountSourceStatusDO> dbimageRefAccountSourceStatusDOlist = pageRefAccountSourceStatusDO
					.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefAccountSourceStatusDO> clonedRefAccountSourceStatusDOList = null;
			if (null != dbimageRefAccountSourceStatusDOlist && dbimageRefAccountSourceStatusDOlist.size() > 0) {
				clonedRefAccountSourceStatusDOList = new ArrayList<RefAccountSourceStatusDO>();
				Iterator<RefAccountSourceStatusDO> itr = dbimageRefAccountSourceStatusDOlist.iterator();
				while (itr.hasNext()) {
					RefAccountSourceStatusDO theRefAccountSourceStatusDO = new RefAccountSourceStatusDO(itr.next());
					clonedRefAccountSourceStatusDOList.add(theRefAccountSourceStatusDO);
				}
			}

			if (null == clonedRefAccountSourceStatusDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAccountSourceStatus reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefAccountSourceStatusDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAccountSourceStatus reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefAccountSourceStatusDOList(clonedRefAccountSourceStatusDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountSourceStatusComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFACCOUNTSOURCESTATUS_BUSKEY")
	public Page<RefAccountSourceStatusDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefAccountSourceStatusDO> pageRefAccountSourceStatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefAccountSourceStatusDO = this.theRefAccountSourceStatusRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefAccountSourceStatusDO = this.theRefAccountSourceStatusRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefAccountSourceStatusDO = this.theRefAccountSourceStatusRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefAccountSourceStatusDO;
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
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefAccountSourceStatusDO reqRefAccountSourceStatusDO = (RefAccountSourceStatusDO) txnTransferObj
					.getTxnPayload().getRefAccountSourceStatusDO();
			theRefAccountSourceStatusComponentRule.preValidateRefAccountSourceStatusfindByBusinessKey(txnTransferObj);
			theRefAccountSourceStatusComponentRule.preExecuteRefAccountSourceStatusfindByBusinessKey(txnTransferObj);

			RefAccountSourceStatusDO dbimageRefAccountSourceStatusDO = executeRepositoryQuery(
					reqRefAccountSourceStatusDO.getConfigLanguageCodeKey(), reqRefAccountSourceStatusDO.getKey(),
					reqRefAccountSourceStatusDO.getInquiryFilter());

			if (null == dbimageRefAccountSourceStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAccountSourceStatusComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefAccountSourceStatusDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefAccountSourceStatusDO(new RefAccountSourceStatusDO(dbimageRefAccountSourceStatusDO));
			}

			theRefAccountSourceStatusComponentRule
					.postExecuteRefAccountSourceStatusfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountSourceStatusComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefAccountSourceStatusDO returns the populated RefAccountSourceStatusDO object
	 */
	@CacheResult(cacheName = "REFACCOUNTSOURCESTATUS_BUSKEY")
	public RefAccountSourceStatusDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefAccountSourceStatusDO dbimageRefAccountSourceStatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefAccountSourceStatusDO = this.theRefAccountSourceStatusRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefAccountSourceStatusDO = this.theRefAccountSourceStatusRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefAccountSourceStatusDO = this.theRefAccountSourceStatusRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefAccountSourceStatusDO;
	}

	/**
	 * perform the common validation that RefAccountSourceStatusDO,
	 * RefAccountSourceStatusDO.configLanguageCodeKey and RefAccountSourceStatusDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountSourceStatusDO is needed in the request");
		}
		RefAccountSourceStatusDO theRefAccountSourceStatusDO = (RefAccountSourceStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountSourceStatusDO();
		if (null == theRefAccountSourceStatusDO.getKey() || theRefAccountSourceStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAccountSourceStatusDO.key should not be null");
		}

		if (null == theRefAccountSourceStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountSourceStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAccountSourceStatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefAccountSourceStatusDO and
	 * RefAccountSourceStatusDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountSourceStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountSourceStatusDO is needed in the request");
		}
		RefAccountSourceStatusDO theRefAccountSourceStatusDO = (RefAccountSourceStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountSourceStatusDO();

		if (null == theRefAccountSourceStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountSourceStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAccountSourceStatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAccountSourceStatusDO().getInquiryFilter());
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
