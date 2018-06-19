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
import com.yugandhar.mdm.extern.dobj.RefLeRatingDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefLeRatingDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefLeRatingComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefLeRatingRepository theRefLeRatingRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefLeRatingComponentRule theRefLeRatingComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefLeRatingComponent() {
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
	 *             if RefLeRatingDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefLeRatingComponentRule.prevalidateRefLeRatingCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefLeRatingDO reqRefLeRatingDO = (RefLeRatingDO) txnTransferObj.getTxnPayload().getRefLeRatingDO();
			if (null == reqRefLeRatingDO.getPrimaryKeyDO() || null == reqRefLeRatingDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefLeRatingDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefLeRatingDO.setIdPk(reqRefLeRatingDO.getPrimaryKeyDO().getIdPk());
				RefLeRatingDO dbimageRefLeRatingDO = entityManager.find(RefLeRatingDO.class,
						reqRefLeRatingDO.getIdPk());
				if (null != dbimageRefLeRatingDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefLeRatingComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefLeRatingComponentRule.preExecuteRefLeRatingCompPersist(reqRefLeRatingDO, txnTransferObj);
			entityManager.persist(reqRefLeRatingDO);
			entityManager.flush();
			reqRefLeRatingDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefLeRatingDO(new RefLeRatingDO(reqRefLeRatingDO));
			theRefLeRatingComponentRule.postExecuteRefLeRatingCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLeRatingComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLeRatingComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRatingComponent.persist failed unexpectedly");
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
	 *             if RefLeRatingDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefLeRatingComponentRule.PrevalidateRefLeRatingCompMerge(txnTransferObj);
			RefLeRatingDO reqRefLeRatingDO = (RefLeRatingDO) txnTransferObj.getTxnPayload().getRefLeRatingDO();
			RefLeRatingDO dbimageRefLeRatingDO = entityManager.find(RefLeRatingDO.class, reqRefLeRatingDO.getIdPk());
			if (null == dbimageRefLeRatingDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLeRatingComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefLeRatingDO);
			BeanUtils.copyProperties(reqRefLeRatingDO, dbimageRefLeRatingDO, strIgnoreProperties);
			entityManager.detach(dbimageRefLeRatingDO);
			theRefLeRatingComponentRule.preExecuteRefLeRatingCompMerge(reqRefLeRatingDO, dbimageRefLeRatingDO,
					txnTransferObj);
			RefLeRatingDO mergedRefLeRatingDO = entityManager.merge(dbimageRefLeRatingDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefLeRatingDO(new RefLeRatingDO(mergedRefLeRatingDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLeRatingComponentRule.postExecuteRefLeRatingCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefLeRatingComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLeRatingComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLeRatingComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRatingComponent.merge failed unexpectedly");
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
	 *             if RefLeRatingDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefLeRatingComponentRule.prevalidateRefLeRatingCompFindById(txnTransferObj);
			RefLeRatingDO reqRefLeRatingDO = (RefLeRatingDO) txnTransferObj.getTxnPayload().getRefLeRatingDO();
			RefLeRatingDO dbimageRefLeRatingDO = entityManager.find(RefLeRatingDO.class, reqRefLeRatingDO.getIdPk());
			if (null == dbimageRefLeRatingDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLeRatingComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefLeRatingDO(new RefLeRatingDO(dbimageRefLeRatingDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLeRatingComponentRule.postExecuteRefLeRatingCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRatingComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefLeRatingDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRatingDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLeRatingDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRatingDO is needed in the request");
		}

		RefLeRatingDO theRefLeRatingDO = (RefLeRatingDO) txnTransferObj.getTxnPayload().getRefLeRatingDO();
		if (null == theRefLeRatingDO.getKey() || theRefLeRatingDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLeRatingDO.key should not be null");
		}

		if (null == theRefLeRatingDO.getConfigLanguageCodeKey()
				|| theRefLeRatingDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRatingDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLeRatingDO.getValue() || theRefLeRatingDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLeRatingDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLeRatingDO.getConfigLanguageCodeKey()
				|| theRefLeRatingDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLeRatingDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLeRatingDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLeRatingDO.setCreatedTs(new Date());
		theRefLeRatingDO.setUpdatedTs(new Date());
		theRefLeRatingDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLeRatingDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefLeRatingDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRatingDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLeRatingDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRatingDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefLeRatingDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefLeRatingDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLeRatingDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefLeRatingDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefLeRatingDO.version should not be null");
		}

		RefLeRatingDO theRefLeRatingDO = (RefLeRatingDO) txnTransferObj.getTxnPayload().getRefLeRatingDO();
		if (null == theRefLeRatingDO.getKey() || theRefLeRatingDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLeRatingDO.key should not be null");
		}

		if (null == theRefLeRatingDO.getConfigLanguageCodeKey()
				|| theRefLeRatingDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRatingDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLeRatingDO.getValue() || theRefLeRatingDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLeRatingDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLeRatingDO.getConfigLanguageCodeKey()
				|| theRefLeRatingDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLeRatingDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLeRatingDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLeRatingDO.setUpdatedTs(new Date());
		theRefLeRatingDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLeRatingDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefLeRatingDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRatingDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLeRatingDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRatingDO is needed in the request");
		}
		RefLeRatingDO theRefLeRatingDO = (RefLeRatingDO) txnTransferObj.getTxnPayload().getRefLeRatingDO();
		if (null == theRefLeRatingDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLeRatingDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefLeRatingDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRatingDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefLeRatingDO> pageRefLeRatingDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefLeRatingDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefLeRatingDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefLeRatingDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLeRating reference list does not have any records in the database");
			}

			if ((pageRefLeRatingDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefLeRating, total number of pages is "
								+ pageRefLeRatingDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefLeRatingDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefLeRatingDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefLeRatingDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefLeRatingDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefLeRatingDO.getSize());

			List<RefLeRatingDO> dbimageRefLeRatingDOlist = pageRefLeRatingDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefLeRatingDO> clonedRefLeRatingDOList = null;
			if (null != dbimageRefLeRatingDOlist && dbimageRefLeRatingDOlist.size() > 0) {
				clonedRefLeRatingDOList = new ArrayList<RefLeRatingDO>();
				Iterator<RefLeRatingDO> itr = dbimageRefLeRatingDOlist.iterator();
				while (itr.hasNext()) {
					RefLeRatingDO theRefLeRatingDO = new RefLeRatingDO(itr.next());
					clonedRefLeRatingDOList.add(theRefLeRatingDO);
				}
			}

			if (null == clonedRefLeRatingDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLeRating reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefLeRatingDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLeRating reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefLeRatingDOList(clonedRefLeRatingDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRatingComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFLERATING_BUSKEY")
	public Page<RefLeRatingDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefLeRatingDO> pageRefLeRatingDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefLeRatingDO = this.theRefLeRatingRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefLeRatingDO = this.theRefLeRatingRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefLeRatingDO = this.theRefLeRatingRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefLeRatingDO;
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
	 *             if RefLeRatingDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefLeRatingDO reqRefLeRatingDO = (RefLeRatingDO) txnTransferObj.getTxnPayload().getRefLeRatingDO();
			theRefLeRatingComponentRule.preValidateRefLeRatingfindByBusinessKey(txnTransferObj);
			theRefLeRatingComponentRule.preExecuteRefLeRatingfindByBusinessKey(txnTransferObj);

			RefLeRatingDO dbimageRefLeRatingDO = executeRepositoryQuery(reqRefLeRatingDO.getConfigLanguageCodeKey(),
					reqRefLeRatingDO.getKey(), reqRefLeRatingDO.getInquiryFilter());

			if (null == dbimageRefLeRatingDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLeRatingComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefLeRatingDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefLeRatingDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefLeRatingDO(new RefLeRatingDO(dbimageRefLeRatingDO));
			}

			theRefLeRatingComponentRule.postExecuteRefLeRatingfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRatingComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefLeRatingDO returns the populated RefLeRatingDO object
	 */
	@CacheResult(cacheName = "REFLERATING_BUSKEY")
	public RefLeRatingDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefLeRatingDO dbimageRefLeRatingDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefLeRatingDO = this.theRefLeRatingRepository.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefLeRatingDO = this.theRefLeRatingRepository.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefLeRatingDO = this.theRefLeRatingRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefLeRatingDO;
	}

	/**
	 * perform the common validation that RefLeRatingDO,
	 * RefLeRatingDO.configLanguageCodeKey and RefLeRatingDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRatingDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLeRatingDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRatingDO is needed in the request");
		}
		RefLeRatingDO theRefLeRatingDO = (RefLeRatingDO) txnTransferObj.getTxnPayload().getRefLeRatingDO();
		if (null == theRefLeRatingDO.getKey() || theRefLeRatingDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLeRatingDO.key should not be null");
		}

		if (null == theRefLeRatingDO.getConfigLanguageCodeKey()
				|| theRefLeRatingDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRatingDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLeRatingDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLeRatingDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLeRatingDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLeRatingDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefLeRatingDO and
	 * RefLeRatingDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRatingDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLeRatingDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRatingDO is needed in the request");
		}
		RefLeRatingDO theRefLeRatingDO = (RefLeRatingDO) txnTransferObj.getTxnPayload().getRefLeRatingDO();

		if (null == theRefLeRatingDO.getConfigLanguageCodeKey()
				|| theRefLeRatingDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRatingDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLeRatingDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLeRatingDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLeRatingDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLeRatingDO().getInquiryFilter());
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
