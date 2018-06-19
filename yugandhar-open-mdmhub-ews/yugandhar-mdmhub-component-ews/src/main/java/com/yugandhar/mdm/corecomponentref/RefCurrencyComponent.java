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
import com.yugandhar.mdm.extern.dobj.RefCurrencyDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefCurrencyDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefCurrencyComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefCurrencyRepository theRefCurrencyRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefCurrencyComponentRule theRefCurrencyComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefCurrencyComponent() {
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
	 *             if RefCurrencyDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefCurrencyComponentRule.prevalidateRefCurrencyCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefCurrencyDO reqRefCurrencyDO = (RefCurrencyDO) txnTransferObj.getTxnPayload().getRefCurrencyDO();
			if (null == reqRefCurrencyDO.getPrimaryKeyDO() || null == reqRefCurrencyDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefCurrencyDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefCurrencyDO.setIdPk(reqRefCurrencyDO.getPrimaryKeyDO().getIdPk());
				RefCurrencyDO dbimageRefCurrencyDO = entityManager.find(RefCurrencyDO.class,
						reqRefCurrencyDO.getIdPk());
				if (null != dbimageRefCurrencyDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefCurrencyComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefCurrencyComponentRule.preExecuteRefCurrencyCompPersist(reqRefCurrencyDO, txnTransferObj);
			entityManager.persist(reqRefCurrencyDO);
			entityManager.flush();
			reqRefCurrencyDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefCurrencyDO(new RefCurrencyDO(reqRefCurrencyDO));
			theRefCurrencyComponentRule.postExecuteRefCurrencyCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefCurrencyComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefCurrencyComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCurrencyComponent.persist failed unexpectedly");
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
	 *             if RefCurrencyDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefCurrencyComponentRule.PrevalidateRefCurrencyCompMerge(txnTransferObj);
			RefCurrencyDO reqRefCurrencyDO = (RefCurrencyDO) txnTransferObj.getTxnPayload().getRefCurrencyDO();
			RefCurrencyDO dbimageRefCurrencyDO = entityManager.find(RefCurrencyDO.class, reqRefCurrencyDO.getIdPk());
			if (null == dbimageRefCurrencyDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCurrencyComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefCurrencyDO);
			BeanUtils.copyProperties(reqRefCurrencyDO, dbimageRefCurrencyDO, strIgnoreProperties);
			entityManager.detach(dbimageRefCurrencyDO);
			theRefCurrencyComponentRule.preExecuteRefCurrencyCompMerge(reqRefCurrencyDO, dbimageRefCurrencyDO,
					txnTransferObj);
			RefCurrencyDO mergedRefCurrencyDO = entityManager.merge(dbimageRefCurrencyDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefCurrencyDO(new RefCurrencyDO(mergedRefCurrencyDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefCurrencyComponentRule.postExecuteRefCurrencyCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefCurrencyComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefCurrencyComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefCurrencyComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCurrencyComponent.merge failed unexpectedly");
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
	 *             if RefCurrencyDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefCurrencyComponentRule.prevalidateRefCurrencyCompFindById(txnTransferObj);
			RefCurrencyDO reqRefCurrencyDO = (RefCurrencyDO) txnTransferObj.getTxnPayload().getRefCurrencyDO();
			RefCurrencyDO dbimageRefCurrencyDO = entityManager.find(RefCurrencyDO.class, reqRefCurrencyDO.getIdPk());
			if (null == dbimageRefCurrencyDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCurrencyComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefCurrencyDO(new RefCurrencyDO(dbimageRefCurrencyDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefCurrencyComponentRule.postExecuteRefCurrencyCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCurrencyComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefCurrencyDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCurrencyDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefCurrencyDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCurrencyDO is needed in the request");
		}

		RefCurrencyDO theRefCurrencyDO = (RefCurrencyDO) txnTransferObj.getTxnPayload().getRefCurrencyDO();
		if (null == theRefCurrencyDO.getKey() || theRefCurrencyDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCurrencyDO.key should not be null");
		}

		if (null == theRefCurrencyDO.getConfigLanguageCodeKey()
				|| theRefCurrencyDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCurrencyDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefCurrencyDO.getValue() || theRefCurrencyDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefCurrencyDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefCurrencyDO.getConfigLanguageCodeKey()
				|| theRefCurrencyDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefCurrencyDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefCurrencyDO.configLanguageCodeKey is not valid");
			}
		}

		theRefCurrencyDO.setCreatedTs(new Date());
		theRefCurrencyDO.setUpdatedTs(new Date());
		theRefCurrencyDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefCurrencyDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefCurrencyDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCurrencyDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCurrencyDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCurrencyDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefCurrencyDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefCurrencyDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefCurrencyDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefCurrencyDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefCurrencyDO.version should not be null");
		}

		RefCurrencyDO theRefCurrencyDO = (RefCurrencyDO) txnTransferObj.getTxnPayload().getRefCurrencyDO();
		if (null == theRefCurrencyDO.getKey() || theRefCurrencyDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCurrencyDO.key should not be null");
		}

		if (null == theRefCurrencyDO.getConfigLanguageCodeKey()
				|| theRefCurrencyDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCurrencyDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefCurrencyDO.getValue() || theRefCurrencyDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefCurrencyDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefCurrencyDO.getConfigLanguageCodeKey()
				|| theRefCurrencyDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefCurrencyDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefCurrencyDO.configLanguageCodeKey is not valid");
			}
		}

		theRefCurrencyDO.setUpdatedTs(new Date());
		theRefCurrencyDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefCurrencyDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefCurrencyDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCurrencyDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefCurrencyDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCurrencyDO is needed in the request");
		}
		RefCurrencyDO theRefCurrencyDO = (RefCurrencyDO) txnTransferObj.getTxnPayload().getRefCurrencyDO();
		if (null == theRefCurrencyDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefCurrencyDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefCurrencyDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCurrencyDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefCurrencyDO> pageRefCurrencyDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefCurrencyDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefCurrencyDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefCurrencyDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCurrency reference list does not have any records in the database");
			}

			if ((pageRefCurrencyDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefCurrency, total number of pages is "
								+ pageRefCurrencyDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefCurrencyDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefCurrencyDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefCurrencyDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefCurrencyDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefCurrencyDO.getSize());

			List<RefCurrencyDO> dbimageRefCurrencyDOlist = pageRefCurrencyDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefCurrencyDO> clonedRefCurrencyDOList = null;
			if (null != dbimageRefCurrencyDOlist && dbimageRefCurrencyDOlist.size() > 0) {
				clonedRefCurrencyDOList = new ArrayList<RefCurrencyDO>();
				Iterator<RefCurrencyDO> itr = dbimageRefCurrencyDOlist.iterator();
				while (itr.hasNext()) {
					RefCurrencyDO theRefCurrencyDO = new RefCurrencyDO(itr.next());
					clonedRefCurrencyDOList.add(theRefCurrencyDO);
				}
			}

			if (null == clonedRefCurrencyDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCurrency reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefCurrencyDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCurrency reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefCurrencyDOList(clonedRefCurrencyDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCurrencyComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFCURRENCY_BUSKEY")
	public Page<RefCurrencyDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefCurrencyDO> pageRefCurrencyDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefCurrencyDO = this.theRefCurrencyRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefCurrencyDO = this.theRefCurrencyRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefCurrencyDO = this.theRefCurrencyRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefCurrencyDO;
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
	 *             if RefCurrencyDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefCurrencyDO reqRefCurrencyDO = (RefCurrencyDO) txnTransferObj.getTxnPayload().getRefCurrencyDO();
			theRefCurrencyComponentRule.preValidateRefCurrencyfindByBusinessKey(txnTransferObj);
			theRefCurrencyComponentRule.preExecuteRefCurrencyfindByBusinessKey(txnTransferObj);

			RefCurrencyDO dbimageRefCurrencyDO = executeRepositoryQuery(reqRefCurrencyDO.getConfigLanguageCodeKey(),
					reqRefCurrencyDO.getKey(), reqRefCurrencyDO.getInquiryFilter());

			if (null == dbimageRefCurrencyDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCurrencyComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefCurrencyDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefCurrencyDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefCurrencyDO(new RefCurrencyDO(dbimageRefCurrencyDO));
			}

			theRefCurrencyComponentRule.postExecuteRefCurrencyfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCurrencyComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefCurrencyDO returns the populated RefCurrencyDO object
	 */
	@CacheResult(cacheName = "REFCURRENCY_BUSKEY")
	public RefCurrencyDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefCurrencyDO dbimageRefCurrencyDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefCurrencyDO = this.theRefCurrencyRepository.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefCurrencyDO = this.theRefCurrencyRepository.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefCurrencyDO = this.theRefCurrencyRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefCurrencyDO;
	}

	/**
	 * perform the common validation that RefCurrencyDO,
	 * RefCurrencyDO.configLanguageCodeKey and RefCurrencyDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCurrencyDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCurrencyDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCurrencyDO is needed in the request");
		}
		RefCurrencyDO theRefCurrencyDO = (RefCurrencyDO) txnTransferObj.getTxnPayload().getRefCurrencyDO();
		if (null == theRefCurrencyDO.getKey() || theRefCurrencyDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCurrencyDO.key should not be null");
		}

		if (null == theRefCurrencyDO.getConfigLanguageCodeKey()
				|| theRefCurrencyDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCurrencyDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefCurrencyDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefCurrencyDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefCurrencyDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefCurrencyDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefCurrencyDO and
	 * RefCurrencyDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCurrencyDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCurrencyDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCurrencyDO is needed in the request");
		}
		RefCurrencyDO theRefCurrencyDO = (RefCurrencyDO) txnTransferObj.getTxnPayload().getRefCurrencyDO();

		if (null == theRefCurrencyDO.getConfigLanguageCodeKey()
				|| theRefCurrencyDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCurrencyDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefCurrencyDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefCurrencyDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefCurrencyDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefCurrencyDO().getInquiryFilter());
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
