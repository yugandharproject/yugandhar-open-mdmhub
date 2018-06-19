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
import com.yugandhar.mdm.extern.dobj.RefLanguageCodeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefLanguageCodeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefLanguageCodeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefLanguageCodeRepository theRefLanguageCodeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefLanguageCodeComponentRule theRefLanguageCodeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefLanguageCodeComponent() {
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
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefLanguageCodeComponentRule.prevalidateRefLanguageCodeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefLanguageCodeDO reqRefLanguageCodeDO = (RefLanguageCodeDO) txnTransferObj.getTxnPayload()
					.getRefLanguageCodeDO();
			if (null == reqRefLanguageCodeDO.getPrimaryKeyDO()
					|| null == reqRefLanguageCodeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefLanguageCodeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefLanguageCodeDO.setIdPk(reqRefLanguageCodeDO.getPrimaryKeyDO().getIdPk());
				RefLanguageCodeDO dbimageRefLanguageCodeDO = entityManager.find(RefLanguageCodeDO.class,
						reqRefLanguageCodeDO.getIdPk());
				if (null != dbimageRefLanguageCodeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefLanguageCodeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefLanguageCodeComponentRule.preExecuteRefLanguageCodeCompPersist(reqRefLanguageCodeDO, txnTransferObj);
			entityManager.persist(reqRefLanguageCodeDO);
			entityManager.flush();
			reqRefLanguageCodeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefLanguageCodeDO(new RefLanguageCodeDO(reqRefLanguageCodeDO));
			theRefLanguageCodeComponentRule.postExecuteRefLanguageCodeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLanguageCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLanguageCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLanguageCodeComponent.persist failed unexpectedly");
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
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefLanguageCodeComponentRule.PrevalidateRefLanguageCodeCompMerge(txnTransferObj);
			RefLanguageCodeDO reqRefLanguageCodeDO = (RefLanguageCodeDO) txnTransferObj.getTxnPayload()
					.getRefLanguageCodeDO();
			RefLanguageCodeDO dbimageRefLanguageCodeDO = entityManager.find(RefLanguageCodeDO.class,
					reqRefLanguageCodeDO.getIdPk());
			if (null == dbimageRefLanguageCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLanguageCodeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefLanguageCodeDO);
			BeanUtils.copyProperties(reqRefLanguageCodeDO, dbimageRefLanguageCodeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefLanguageCodeDO);
			theRefLanguageCodeComponentRule.preExecuteRefLanguageCodeCompMerge(reqRefLanguageCodeDO,
					dbimageRefLanguageCodeDO, txnTransferObj);
			RefLanguageCodeDO mergedRefLanguageCodeDO = entityManager.merge(dbimageRefLanguageCodeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefLanguageCodeDO(new RefLanguageCodeDO(mergedRefLanguageCodeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLanguageCodeComponentRule.postExecuteRefLanguageCodeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefLanguageCodeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLanguageCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLanguageCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLanguageCodeComponent.merge failed unexpectedly");
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
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefLanguageCodeComponentRule.prevalidateRefLanguageCodeCompFindById(txnTransferObj);
			RefLanguageCodeDO reqRefLanguageCodeDO = (RefLanguageCodeDO) txnTransferObj.getTxnPayload()
					.getRefLanguageCodeDO();
			RefLanguageCodeDO dbimageRefLanguageCodeDO = entityManager.find(RefLanguageCodeDO.class,
					reqRefLanguageCodeDO.getIdPk());
			if (null == dbimageRefLanguageCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLanguageCodeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefLanguageCodeDO(new RefLanguageCodeDO(dbimageRefLanguageCodeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLanguageCodeComponentRule.postExecuteRefLanguageCodeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLanguageCodeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefLanguageCodeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLanguageCodeDO is needed in the request");
		}

		RefLanguageCodeDO theRefLanguageCodeDO = (RefLanguageCodeDO) txnTransferObj.getTxnPayload()
				.getRefLanguageCodeDO();
		if (null == theRefLanguageCodeDO.getKey() || theRefLanguageCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLanguageCodeDO.key should not be null");
		}

		if (null == theRefLanguageCodeDO.getConfigLanguageCodeKey()
				|| theRefLanguageCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLanguageCodeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLanguageCodeDO.getValue() || theRefLanguageCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLanguageCodeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLanguageCodeDO.getConfigLanguageCodeKey()
				|| theRefLanguageCodeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLanguageCodeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLanguageCodeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLanguageCodeDO.setCreatedTs(new Date());
		theRefLanguageCodeDO.setUpdatedTs(new Date());
		theRefLanguageCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLanguageCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefLanguageCodeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLanguageCodeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLanguageCodeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefLanguageCodeDO.version should not be null");
		}

		RefLanguageCodeDO theRefLanguageCodeDO = (RefLanguageCodeDO) txnTransferObj.getTxnPayload()
				.getRefLanguageCodeDO();
		if (null == theRefLanguageCodeDO.getKey() || theRefLanguageCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLanguageCodeDO.key should not be null");
		}

		if (null == theRefLanguageCodeDO.getConfigLanguageCodeKey()
				|| theRefLanguageCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLanguageCodeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLanguageCodeDO.getValue() || theRefLanguageCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLanguageCodeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLanguageCodeDO.getConfigLanguageCodeKey()
				|| theRefLanguageCodeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLanguageCodeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLanguageCodeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLanguageCodeDO.setUpdatedTs(new Date());
		theRefLanguageCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLanguageCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefLanguageCodeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLanguageCodeDO is needed in the request");
		}
		RefLanguageCodeDO theRefLanguageCodeDO = (RefLanguageCodeDO) txnTransferObj.getTxnPayload()
				.getRefLanguageCodeDO();
		if (null == theRefLanguageCodeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLanguageCodeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefLanguageCodeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefLanguageCodeDO> pageRefLanguageCodeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefLanguageCodeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLanguageCode reference list does not have any records in the database");
			}

			if ((pageRefLanguageCodeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefLanguageCode, total number of pages is "
								+ pageRefLanguageCodeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefLanguageCodeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefLanguageCodeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefLanguageCodeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefLanguageCodeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefLanguageCodeDO.getSize());

			List<RefLanguageCodeDO> dbimageRefLanguageCodeDOlist = pageRefLanguageCodeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefLanguageCodeDO> clonedRefLanguageCodeDOList = null;
			if (null != dbimageRefLanguageCodeDOlist && dbimageRefLanguageCodeDOlist.size() > 0) {
				clonedRefLanguageCodeDOList = new ArrayList<RefLanguageCodeDO>();
				Iterator<RefLanguageCodeDO> itr = dbimageRefLanguageCodeDOlist.iterator();
				while (itr.hasNext()) {
					RefLanguageCodeDO theRefLanguageCodeDO = new RefLanguageCodeDO(itr.next());
					clonedRefLanguageCodeDOList.add(theRefLanguageCodeDO);
				}
			}

			if (null == clonedRefLanguageCodeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLanguageCode reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefLanguageCodeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLanguageCode reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefLanguageCodeDOList(clonedRefLanguageCodeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLanguageCodeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFLANGUAGECODE_BUSKEY")
	public Page<RefLanguageCodeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefLanguageCodeDO> pageRefLanguageCodeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefLanguageCodeDO = this.theRefLanguageCodeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefLanguageCodeDO = this.theRefLanguageCodeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefLanguageCodeDO = this.theRefLanguageCodeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefLanguageCodeDO;
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
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefLanguageCodeDO reqRefLanguageCodeDO = (RefLanguageCodeDO) txnTransferObj.getTxnPayload()
					.getRefLanguageCodeDO();
			theRefLanguageCodeComponentRule.preValidateRefLanguageCodefindByBusinessKey(txnTransferObj);
			theRefLanguageCodeComponentRule.preExecuteRefLanguageCodefindByBusinessKey(txnTransferObj);

			RefLanguageCodeDO dbimageRefLanguageCodeDO = executeRepositoryQuery(
					reqRefLanguageCodeDO.getConfigLanguageCodeKey(), reqRefLanguageCodeDO.getKey(),
					reqRefLanguageCodeDO.getInquiryFilter());

			if (null == dbimageRefLanguageCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLanguageCodeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefLanguageCodeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefLanguageCodeDO(new RefLanguageCodeDO(dbimageRefLanguageCodeDO));
			}

			theRefLanguageCodeComponentRule.postExecuteRefLanguageCodefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLanguageCodeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefLanguageCodeDO returns the populated RefLanguageCodeDO object
	 */
	@CacheResult(cacheName = "REFLANGUAGECODE_BUSKEY")
	public RefLanguageCodeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefLanguageCodeDO dbimageRefLanguageCodeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefLanguageCodeDO = this.theRefLanguageCodeRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefLanguageCodeDO = this.theRefLanguageCodeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefLanguageCodeDO = this.theRefLanguageCodeRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefLanguageCodeDO;
	}

	/**
	 * perform the common validation that RefLanguageCodeDO,
	 * RefLanguageCodeDO.configLanguageCodeKey and RefLanguageCodeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLanguageCodeDO is needed in the request");
		}
		RefLanguageCodeDO theRefLanguageCodeDO = (RefLanguageCodeDO) txnTransferObj.getTxnPayload()
				.getRefLanguageCodeDO();
		if (null == theRefLanguageCodeDO.getKey() || theRefLanguageCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLanguageCodeDO.key should not be null");
		}

		if (null == theRefLanguageCodeDO.getConfigLanguageCodeKey()
				|| theRefLanguageCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLanguageCodeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLanguageCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefLanguageCodeDO and
	 * RefLanguageCodeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLanguageCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLanguageCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLanguageCodeDO is needed in the request");
		}
		RefLanguageCodeDO theRefLanguageCodeDO = (RefLanguageCodeDO) txnTransferObj.getTxnPayload()
				.getRefLanguageCodeDO();

		if (null == theRefLanguageCodeDO.getConfigLanguageCodeKey()
				|| theRefLanguageCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLanguageCodeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLanguageCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLanguageCodeDO().getInquiryFilter());
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
