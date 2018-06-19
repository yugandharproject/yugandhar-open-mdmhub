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
import com.yugandhar.mdm.extern.dobj.RefClassificationCodeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefClassificationCodeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefClassificationCodeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefClassificationCodeRepository theRefClassificationCodeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefClassificationCodeComponentRule theRefClassificationCodeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefClassificationCodeComponent() {
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
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefClassificationCodeComponentRule.prevalidateRefClassificationCodeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefClassificationCodeDO reqRefClassificationCodeDO = (RefClassificationCodeDO) txnTransferObj
					.getTxnPayload().getRefClassificationCodeDO();
			if (null == reqRefClassificationCodeDO.getPrimaryKeyDO()
					|| null == reqRefClassificationCodeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefClassificationCodeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefClassificationCodeDO.setIdPk(reqRefClassificationCodeDO.getPrimaryKeyDO().getIdPk());
				RefClassificationCodeDO dbimageRefClassificationCodeDO = entityManager
						.find(RefClassificationCodeDO.class, reqRefClassificationCodeDO.getIdPk());
				if (null != dbimageRefClassificationCodeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefClassificationCodeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefClassificationCodeComponentRule.preExecuteRefClassificationCodeCompPersist(reqRefClassificationCodeDO,
					txnTransferObj);
			entityManager.persist(reqRefClassificationCodeDO);
			entityManager.flush();
			reqRefClassificationCodeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefClassificationCodeDO(new RefClassificationCodeDO(reqRefClassificationCodeDO));
			theRefClassificationCodeComponentRule.postExecuteRefClassificationCodeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefClassificationCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefClassificationCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefClassificationCodeComponent.persist failed unexpectedly");
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
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefClassificationCodeComponentRule.PrevalidateRefClassificationCodeCompMerge(txnTransferObj);
			RefClassificationCodeDO reqRefClassificationCodeDO = (RefClassificationCodeDO) txnTransferObj
					.getTxnPayload().getRefClassificationCodeDO();
			RefClassificationCodeDO dbimageRefClassificationCodeDO = entityManager.find(RefClassificationCodeDO.class,
					reqRefClassificationCodeDO.getIdPk());
			if (null == dbimageRefClassificationCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefClassificationCodeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefClassificationCodeDO);
			BeanUtils.copyProperties(reqRefClassificationCodeDO, dbimageRefClassificationCodeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefClassificationCodeDO);
			theRefClassificationCodeComponentRule.preExecuteRefClassificationCodeCompMerge(reqRefClassificationCodeDO,
					dbimageRefClassificationCodeDO, txnTransferObj);
			RefClassificationCodeDO mergedRefClassificationCodeDO = entityManager.merge(dbimageRefClassificationCodeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefClassificationCodeDO(new RefClassificationCodeDO(mergedRefClassificationCodeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefClassificationCodeComponentRule.postExecuteRefClassificationCodeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefClassificationCodeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefClassificationCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefClassificationCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefClassificationCodeComponent.merge failed unexpectedly");
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
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefClassificationCodeComponentRule.prevalidateRefClassificationCodeCompFindById(txnTransferObj);
			RefClassificationCodeDO reqRefClassificationCodeDO = (RefClassificationCodeDO) txnTransferObj
					.getTxnPayload().getRefClassificationCodeDO();
			RefClassificationCodeDO dbimageRefClassificationCodeDO = entityManager.find(RefClassificationCodeDO.class,
					reqRefClassificationCodeDO.getIdPk());
			if (null == dbimageRefClassificationCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefClassificationCodeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefClassificationCodeDO(new RefClassificationCodeDO(dbimageRefClassificationCodeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefClassificationCodeComponentRule.postExecuteRefClassificationCodeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefClassificationCodeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefClassificationCodeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefClassificationCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefClassificationCodeDO is needed in the request");
		}

		RefClassificationCodeDO theRefClassificationCodeDO = (RefClassificationCodeDO) txnTransferObj.getTxnPayload()
				.getRefClassificationCodeDO();
		if (null == theRefClassificationCodeDO.getKey() || theRefClassificationCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefClassificationCodeDO.key should not be null");
		}

		if (null == theRefClassificationCodeDO.getConfigLanguageCodeKey()
				|| theRefClassificationCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefClassificationCodeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefClassificationCodeDO.getValue() || theRefClassificationCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefClassificationCodeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefClassificationCodeDO.getConfigLanguageCodeKey()
				|| theRefClassificationCodeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefClassificationCodeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefClassificationCodeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefClassificationCodeDO.setCreatedTs(new Date());
		theRefClassificationCodeDO.setUpdatedTs(new Date());
		theRefClassificationCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefClassificationCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefClassificationCodeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefClassificationCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefClassificationCodeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefClassificationCodeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefClassificationCodeDO.version should not be null");
		}

		RefClassificationCodeDO theRefClassificationCodeDO = (RefClassificationCodeDO) txnTransferObj.getTxnPayload()
				.getRefClassificationCodeDO();
		if (null == theRefClassificationCodeDO.getKey() || theRefClassificationCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefClassificationCodeDO.key should not be null");
		}

		if (null == theRefClassificationCodeDO.getConfigLanguageCodeKey()
				|| theRefClassificationCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefClassificationCodeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefClassificationCodeDO.getValue() || theRefClassificationCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefClassificationCodeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefClassificationCodeDO.getConfigLanguageCodeKey()
				|| theRefClassificationCodeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefClassificationCodeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefClassificationCodeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefClassificationCodeDO.setUpdatedTs(new Date());
		theRefClassificationCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefClassificationCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefClassificationCodeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefClassificationCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefClassificationCodeDO is needed in the request");
		}
		RefClassificationCodeDO theRefClassificationCodeDO = (RefClassificationCodeDO) txnTransferObj.getTxnPayload()
				.getRefClassificationCodeDO();
		if (null == theRefClassificationCodeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefClassificationCodeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefClassificationCodeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefClassificationCodeDO> pageRefClassificationCodeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefClassificationCodeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefClassificationCode reference list does not have any records in the database");
			}

			if ((pageRefClassificationCodeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefClassificationCode, total number of pages is "
								+ pageRefClassificationCodeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefClassificationCodeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefClassificationCodeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefClassificationCodeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefClassificationCodeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefClassificationCodeDO.getSize());

			List<RefClassificationCodeDO> dbimageRefClassificationCodeDOlist = pageRefClassificationCodeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefClassificationCodeDO> clonedRefClassificationCodeDOList = null;
			if (null != dbimageRefClassificationCodeDOlist && dbimageRefClassificationCodeDOlist.size() > 0) {
				clonedRefClassificationCodeDOList = new ArrayList<RefClassificationCodeDO>();
				Iterator<RefClassificationCodeDO> itr = dbimageRefClassificationCodeDOlist.iterator();
				while (itr.hasNext()) {
					RefClassificationCodeDO theRefClassificationCodeDO = new RefClassificationCodeDO(itr.next());
					clonedRefClassificationCodeDOList.add(theRefClassificationCodeDO);
				}
			}

			if (null == clonedRefClassificationCodeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefClassificationCode reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefClassificationCodeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefClassificationCode reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefClassificationCodeDOList(clonedRefClassificationCodeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefClassificationCodeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFCLASSIFICATIONCODE_BUSKEY")
	public Page<RefClassificationCodeDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefClassificationCodeDO> pageRefClassificationCodeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefClassificationCodeDO = this.theRefClassificationCodeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefClassificationCodeDO = this.theRefClassificationCodeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefClassificationCodeDO = this.theRefClassificationCodeRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefClassificationCodeDO;
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
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefClassificationCodeDO reqRefClassificationCodeDO = (RefClassificationCodeDO) txnTransferObj
					.getTxnPayload().getRefClassificationCodeDO();
			theRefClassificationCodeComponentRule.preValidateRefClassificationCodefindByBusinessKey(txnTransferObj);
			theRefClassificationCodeComponentRule.preExecuteRefClassificationCodefindByBusinessKey(txnTransferObj);

			RefClassificationCodeDO dbimageRefClassificationCodeDO = executeRepositoryQuery(
					reqRefClassificationCodeDO.getConfigLanguageCodeKey(), reqRefClassificationCodeDO.getKey(),
					reqRefClassificationCodeDO.getInquiryFilter());

			if (null == dbimageRefClassificationCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefClassificationCodeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefClassificationCodeDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefClassificationCodeDO(new RefClassificationCodeDO(dbimageRefClassificationCodeDO));
			}

			theRefClassificationCodeComponentRule.postExecuteRefClassificationCodefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefClassificationCodeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefClassificationCodeDO returns the populated RefClassificationCodeDO object
	 */
	@CacheResult(cacheName = "REFCLASSIFICATIONCODE_BUSKEY")
	public RefClassificationCodeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefClassificationCodeDO dbimageRefClassificationCodeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefClassificationCodeDO = this.theRefClassificationCodeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefClassificationCodeDO = this.theRefClassificationCodeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefClassificationCodeDO = this.theRefClassificationCodeRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefClassificationCodeDO;
	}

	/**
	 * perform the common validation that RefClassificationCodeDO,
	 * RefClassificationCodeDO.configLanguageCodeKey and RefClassificationCodeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefClassificationCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefClassificationCodeDO is needed in the request");
		}
		RefClassificationCodeDO theRefClassificationCodeDO = (RefClassificationCodeDO) txnTransferObj.getTxnPayload()
				.getRefClassificationCodeDO();
		if (null == theRefClassificationCodeDO.getKey() || theRefClassificationCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefClassificationCodeDO.key should not be null");
		}

		if (null == theRefClassificationCodeDO.getConfigLanguageCodeKey()
				|| theRefClassificationCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefClassificationCodeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefClassificationCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefClassificationCodeDO and
	 * RefClassificationCodeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefClassificationCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefClassificationCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefClassificationCodeDO is needed in the request");
		}
		RefClassificationCodeDO theRefClassificationCodeDO = (RefClassificationCodeDO) txnTransferObj.getTxnPayload()
				.getRefClassificationCodeDO();

		if (null == theRefClassificationCodeDO.getConfigLanguageCodeKey()
				|| theRefClassificationCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefClassificationCodeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefClassificationCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefClassificationCodeDO().getInquiryFilter());
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
