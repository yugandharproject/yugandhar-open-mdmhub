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
import com.yugandhar.mdm.extern.dobj.RefIndustryCodeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefIndustryCodeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefIndustryCodeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefIndustryCodeRepository theRefIndustryCodeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefIndustryCodeComponentRule theRefIndustryCodeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefIndustryCodeComponent() {
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
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefIndustryCodeComponentRule.prevalidateRefIndustryCodeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefIndustryCodeDO reqRefIndustryCodeDO = (RefIndustryCodeDO) txnTransferObj.getTxnPayload()
					.getRefIndustryCodeDO();
			if (null == reqRefIndustryCodeDO.getPrimaryKeyDO()
					|| null == reqRefIndustryCodeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefIndustryCodeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefIndustryCodeDO.setIdPk(reqRefIndustryCodeDO.getPrimaryKeyDO().getIdPk());
				RefIndustryCodeDO dbimageRefIndustryCodeDO = entityManager.find(RefIndustryCodeDO.class,
						reqRefIndustryCodeDO.getIdPk());
				if (null != dbimageRefIndustryCodeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefIndustryCodeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefIndustryCodeComponentRule.preExecuteRefIndustryCodeCompPersist(reqRefIndustryCodeDO, txnTransferObj);
			entityManager.persist(reqRefIndustryCodeDO);
			entityManager.flush();
			reqRefIndustryCodeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefIndustryCodeDO(new RefIndustryCodeDO(reqRefIndustryCodeDO));
			theRefIndustryCodeComponentRule.postExecuteRefIndustryCodeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefIndustryCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefIndustryCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIndustryCodeComponent.persist failed unexpectedly");
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
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefIndustryCodeComponentRule.PrevalidateRefIndustryCodeCompMerge(txnTransferObj);
			RefIndustryCodeDO reqRefIndustryCodeDO = (RefIndustryCodeDO) txnTransferObj.getTxnPayload()
					.getRefIndustryCodeDO();
			RefIndustryCodeDO dbimageRefIndustryCodeDO = entityManager.find(RefIndustryCodeDO.class,
					reqRefIndustryCodeDO.getIdPk());
			if (null == dbimageRefIndustryCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefIndustryCodeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefIndustryCodeDO);
			BeanUtils.copyProperties(reqRefIndustryCodeDO, dbimageRefIndustryCodeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefIndustryCodeDO);
			theRefIndustryCodeComponentRule.preExecuteRefIndustryCodeCompMerge(reqRefIndustryCodeDO,
					dbimageRefIndustryCodeDO, txnTransferObj);
			RefIndustryCodeDO mergedRefIndustryCodeDO = entityManager.merge(dbimageRefIndustryCodeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefIndustryCodeDO(new RefIndustryCodeDO(mergedRefIndustryCodeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefIndustryCodeComponentRule.postExecuteRefIndustryCodeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefIndustryCodeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefIndustryCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefIndustryCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIndustryCodeComponent.merge failed unexpectedly");
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
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefIndustryCodeComponentRule.prevalidateRefIndustryCodeCompFindById(txnTransferObj);
			RefIndustryCodeDO reqRefIndustryCodeDO = (RefIndustryCodeDO) txnTransferObj.getTxnPayload()
					.getRefIndustryCodeDO();
			RefIndustryCodeDO dbimageRefIndustryCodeDO = entityManager.find(RefIndustryCodeDO.class,
					reqRefIndustryCodeDO.getIdPk());
			if (null == dbimageRefIndustryCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefIndustryCodeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefIndustryCodeDO(new RefIndustryCodeDO(dbimageRefIndustryCodeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefIndustryCodeComponentRule.postExecuteRefIndustryCodeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIndustryCodeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefIndustryCodeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefIndustryCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIndustryCodeDO is needed in the request");
		}

		RefIndustryCodeDO theRefIndustryCodeDO = (RefIndustryCodeDO) txnTransferObj.getTxnPayload()
				.getRefIndustryCodeDO();
		if (null == theRefIndustryCodeDO.getKey() || theRefIndustryCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefIndustryCodeDO.key should not be null");
		}

		if (null == theRefIndustryCodeDO.getConfigLanguageCodeKey()
				|| theRefIndustryCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefIndustryCodeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefIndustryCodeDO.getValue() || theRefIndustryCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefIndustryCodeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefIndustryCodeDO.getConfigLanguageCodeKey()
				|| theRefIndustryCodeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefIndustryCodeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefIndustryCodeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefIndustryCodeDO.setCreatedTs(new Date());
		theRefIndustryCodeDO.setUpdatedTs(new Date());
		theRefIndustryCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefIndustryCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefIndustryCodeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefIndustryCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIndustryCodeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefIndustryCodeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefIndustryCodeDO.version should not be null");
		}

		RefIndustryCodeDO theRefIndustryCodeDO = (RefIndustryCodeDO) txnTransferObj.getTxnPayload()
				.getRefIndustryCodeDO();
		if (null == theRefIndustryCodeDO.getKey() || theRefIndustryCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefIndustryCodeDO.key should not be null");
		}

		if (null == theRefIndustryCodeDO.getConfigLanguageCodeKey()
				|| theRefIndustryCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefIndustryCodeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefIndustryCodeDO.getValue() || theRefIndustryCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefIndustryCodeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefIndustryCodeDO.getConfigLanguageCodeKey()
				|| theRefIndustryCodeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefIndustryCodeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefIndustryCodeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefIndustryCodeDO.setUpdatedTs(new Date());
		theRefIndustryCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefIndustryCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefIndustryCodeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefIndustryCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIndustryCodeDO is needed in the request");
		}
		RefIndustryCodeDO theRefIndustryCodeDO = (RefIndustryCodeDO) txnTransferObj.getTxnPayload()
				.getRefIndustryCodeDO();
		if (null == theRefIndustryCodeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefIndustryCodeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefIndustryCodeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefIndustryCodeDO> pageRefIndustryCodeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefIndustryCodeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefIndustryCode reference list does not have any records in the database");
			}

			if ((pageRefIndustryCodeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefIndustryCode, total number of pages is "
								+ pageRefIndustryCodeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefIndustryCodeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefIndustryCodeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefIndustryCodeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefIndustryCodeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefIndustryCodeDO.getSize());

			List<RefIndustryCodeDO> dbimageRefIndustryCodeDOlist = pageRefIndustryCodeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefIndustryCodeDO> clonedRefIndustryCodeDOList = null;
			if (null != dbimageRefIndustryCodeDOlist && dbimageRefIndustryCodeDOlist.size() > 0) {
				clonedRefIndustryCodeDOList = new ArrayList<RefIndustryCodeDO>();
				Iterator<RefIndustryCodeDO> itr = dbimageRefIndustryCodeDOlist.iterator();
				while (itr.hasNext()) {
					RefIndustryCodeDO theRefIndustryCodeDO = new RefIndustryCodeDO(itr.next());
					clonedRefIndustryCodeDOList.add(theRefIndustryCodeDO);
				}
			}

			if (null == clonedRefIndustryCodeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefIndustryCode reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefIndustryCodeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefIndustryCode reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefIndustryCodeDOList(clonedRefIndustryCodeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIndustryCodeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFINDUSTRYCODE_BUSKEY")
	public Page<RefIndustryCodeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefIndustryCodeDO> pageRefIndustryCodeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefIndustryCodeDO = this.theRefIndustryCodeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefIndustryCodeDO = this.theRefIndustryCodeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefIndustryCodeDO = this.theRefIndustryCodeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefIndustryCodeDO;
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
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefIndustryCodeDO reqRefIndustryCodeDO = (RefIndustryCodeDO) txnTransferObj.getTxnPayload()
					.getRefIndustryCodeDO();
			theRefIndustryCodeComponentRule.preValidateRefIndustryCodefindByBusinessKey(txnTransferObj);
			theRefIndustryCodeComponentRule.preExecuteRefIndustryCodefindByBusinessKey(txnTransferObj);

			RefIndustryCodeDO dbimageRefIndustryCodeDO = executeRepositoryQuery(
					reqRefIndustryCodeDO.getConfigLanguageCodeKey(), reqRefIndustryCodeDO.getKey(),
					reqRefIndustryCodeDO.getInquiryFilter());

			if (null == dbimageRefIndustryCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefIndustryCodeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefIndustryCodeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefIndustryCodeDO(new RefIndustryCodeDO(dbimageRefIndustryCodeDO));
			}

			theRefIndustryCodeComponentRule.postExecuteRefIndustryCodefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIndustryCodeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefIndustryCodeDO returns the populated RefIndustryCodeDO object
	 */
	@CacheResult(cacheName = "REFINDUSTRYCODE_BUSKEY")
	public RefIndustryCodeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefIndustryCodeDO dbimageRefIndustryCodeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefIndustryCodeDO = this.theRefIndustryCodeRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefIndustryCodeDO = this.theRefIndustryCodeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefIndustryCodeDO = this.theRefIndustryCodeRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefIndustryCodeDO;
	}

	/**
	 * perform the common validation that RefIndustryCodeDO,
	 * RefIndustryCodeDO.configLanguageCodeKey and RefIndustryCodeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefIndustryCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIndustryCodeDO is needed in the request");
		}
		RefIndustryCodeDO theRefIndustryCodeDO = (RefIndustryCodeDO) txnTransferObj.getTxnPayload()
				.getRefIndustryCodeDO();
		if (null == theRefIndustryCodeDO.getKey() || theRefIndustryCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefIndustryCodeDO.key should not be null");
		}

		if (null == theRefIndustryCodeDO.getConfigLanguageCodeKey()
				|| theRefIndustryCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefIndustryCodeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefIndustryCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefIndustryCodeDO and
	 * RefIndustryCodeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIndustryCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefIndustryCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIndustryCodeDO is needed in the request");
		}
		RefIndustryCodeDO theRefIndustryCodeDO = (RefIndustryCodeDO) txnTransferObj.getTxnPayload()
				.getRefIndustryCodeDO();

		if (null == theRefIndustryCodeDO.getConfigLanguageCodeKey()
				|| theRefIndustryCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefIndustryCodeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefIndustryCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefIndustryCodeDO().getInquiryFilter());
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
