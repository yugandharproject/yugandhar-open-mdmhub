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
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefEntityObjectTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefEntityObjectTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefEntityObjectTypeRepository theRefEntityObjectTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefEntityObjectTypeComponentRule theRefEntityObjectTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefEntityObjectTypeComponent() {
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
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefEntityObjectTypeComponentRule.prevalidateRefEntityObjectTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefEntityObjectTypeDO reqRefEntityObjectTypeDO = (RefEntityObjectTypeDO) txnTransferObj.getTxnPayload()
					.getRefEntityObjectTypeDO();
			if (null == reqRefEntityObjectTypeDO.getPrimaryKeyDO()
					|| null == reqRefEntityObjectTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefEntityObjectTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefEntityObjectTypeDO.setIdPk(reqRefEntityObjectTypeDO.getPrimaryKeyDO().getIdPk());
				RefEntityObjectTypeDO dbimageRefEntityObjectTypeDO = entityManager.find(RefEntityObjectTypeDO.class,
						reqRefEntityObjectTypeDO.getIdPk());
				if (null != dbimageRefEntityObjectTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefEntityObjectTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefEntityObjectTypeComponentRule.preExecuteRefEntityObjectTypeCompPersist(reqRefEntityObjectTypeDO,
					txnTransferObj);
			entityManager.persist(reqRefEntityObjectTypeDO);
			entityManager.flush();
			reqRefEntityObjectTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefEntityObjectTypeDO(new RefEntityObjectTypeDO(reqRefEntityObjectTypeDO));
			theRefEntityObjectTypeComponentRule.postExecuteRefEntityObjectTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefEntityObjectTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefEntityObjectTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefEntityObjectTypeComponent.persist failed unexpectedly");
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
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefEntityObjectTypeComponentRule.PrevalidateRefEntityObjectTypeCompMerge(txnTransferObj);
			RefEntityObjectTypeDO reqRefEntityObjectTypeDO = (RefEntityObjectTypeDO) txnTransferObj.getTxnPayload()
					.getRefEntityObjectTypeDO();
			RefEntityObjectTypeDO dbimageRefEntityObjectTypeDO = entityManager.find(RefEntityObjectTypeDO.class,
					reqRefEntityObjectTypeDO.getIdPk());
			if (null == dbimageRefEntityObjectTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefEntityObjectTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefEntityObjectTypeDO);
			BeanUtils.copyProperties(reqRefEntityObjectTypeDO, dbimageRefEntityObjectTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefEntityObjectTypeDO);
			theRefEntityObjectTypeComponentRule.preExecuteRefEntityObjectTypeCompMerge(reqRefEntityObjectTypeDO,
					dbimageRefEntityObjectTypeDO, txnTransferObj);
			RefEntityObjectTypeDO mergedRefEntityObjectTypeDO = entityManager.merge(dbimageRefEntityObjectTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefEntityObjectTypeDO(new RefEntityObjectTypeDO(mergedRefEntityObjectTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefEntityObjectTypeComponentRule.postExecuteRefEntityObjectTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefEntityObjectTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefEntityObjectTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefEntityObjectTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefEntityObjectTypeComponent.merge failed unexpectedly");
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
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefEntityObjectTypeComponentRule.prevalidateRefEntityObjectTypeCompFindById(txnTransferObj);
			RefEntityObjectTypeDO reqRefEntityObjectTypeDO = (RefEntityObjectTypeDO) txnTransferObj.getTxnPayload()
					.getRefEntityObjectTypeDO();
			RefEntityObjectTypeDO dbimageRefEntityObjectTypeDO = entityManager.find(RefEntityObjectTypeDO.class,
					reqRefEntityObjectTypeDO.getIdPk());
			if (null == dbimageRefEntityObjectTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefEntityObjectTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefEntityObjectTypeDO(new RefEntityObjectTypeDO(dbimageRefEntityObjectTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefEntityObjectTypeComponentRule.postExecuteRefEntityObjectTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefEntityObjectTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefEntityObjectTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefEntityObjectTypeDO is needed in the request");
		}

		RefEntityObjectTypeDO theRefEntityObjectTypeDO = (RefEntityObjectTypeDO) txnTransferObj.getTxnPayload()
				.getRefEntityObjectTypeDO();
		if (null == theRefEntityObjectTypeDO.getKey() || theRefEntityObjectTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefEntityObjectTypeDO.key should not be null");
		}

		if (null == theRefEntityObjectTypeDO.getConfigLanguageCodeKey()
				|| theRefEntityObjectTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefEntityObjectTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefEntityObjectTypeDO.getValue() || theRefEntityObjectTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefEntityObjectTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefEntityObjectTypeDO.getConfigLanguageCodeKey()
				|| theRefEntityObjectTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefEntityObjectTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefEntityObjectTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefEntityObjectTypeDO.setCreatedTs(new Date());
		theRefEntityObjectTypeDO.setUpdatedTs(new Date());
		theRefEntityObjectTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefEntityObjectTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefEntityObjectTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefEntityObjectTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefEntityObjectTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefEntityObjectTypeDO.version should not be null");
		}

		RefEntityObjectTypeDO theRefEntityObjectTypeDO = (RefEntityObjectTypeDO) txnTransferObj.getTxnPayload()
				.getRefEntityObjectTypeDO();
		if (null == theRefEntityObjectTypeDO.getKey() || theRefEntityObjectTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefEntityObjectTypeDO.key should not be null");
		}

		if (null == theRefEntityObjectTypeDO.getConfigLanguageCodeKey()
				|| theRefEntityObjectTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefEntityObjectTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefEntityObjectTypeDO.getValue() || theRefEntityObjectTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefEntityObjectTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefEntityObjectTypeDO.getConfigLanguageCodeKey()
				|| theRefEntityObjectTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefEntityObjectTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefEntityObjectTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefEntityObjectTypeDO.setUpdatedTs(new Date());
		theRefEntityObjectTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefEntityObjectTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefEntityObjectTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefEntityObjectTypeDO is needed in the request");
		}
		RefEntityObjectTypeDO theRefEntityObjectTypeDO = (RefEntityObjectTypeDO) txnTransferObj.getTxnPayload()
				.getRefEntityObjectTypeDO();
		if (null == theRefEntityObjectTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefEntityObjectTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefEntityObjectTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefEntityObjectTypeDO> pageRefEntityObjectTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefEntityObjectTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefEntityObjectType reference list does not have any records in the database");
			}

			if ((pageRefEntityObjectTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefEntityObjectType, total number of pages is "
								+ pageRefEntityObjectTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefEntityObjectTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefEntityObjectTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefEntityObjectTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefEntityObjectTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefEntityObjectTypeDO.getSize());

			List<RefEntityObjectTypeDO> dbimageRefEntityObjectTypeDOlist = pageRefEntityObjectTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefEntityObjectTypeDO> clonedRefEntityObjectTypeDOList = null;
			if (null != dbimageRefEntityObjectTypeDOlist && dbimageRefEntityObjectTypeDOlist.size() > 0) {
				clonedRefEntityObjectTypeDOList = new ArrayList<RefEntityObjectTypeDO>();
				Iterator<RefEntityObjectTypeDO> itr = dbimageRefEntityObjectTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefEntityObjectTypeDO theRefEntityObjectTypeDO = new RefEntityObjectTypeDO(itr.next());
					clonedRefEntityObjectTypeDOList.add(theRefEntityObjectTypeDO);
				}
			}

			if (null == clonedRefEntityObjectTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefEntityObjectType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefEntityObjectTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefEntityObjectType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefEntityObjectTypeDOList(clonedRefEntityObjectTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefEntityObjectTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFENTITYOBJECTTYPE_BUSKEY")
	public Page<RefEntityObjectTypeDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefEntityObjectTypeDO> pageRefEntityObjectTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefEntityObjectTypeDO = this.theRefEntityObjectTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefEntityObjectTypeDO = this.theRefEntityObjectTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefEntityObjectTypeDO = this.theRefEntityObjectTypeRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefEntityObjectTypeDO;
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
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefEntityObjectTypeDO reqRefEntityObjectTypeDO = (RefEntityObjectTypeDO) txnTransferObj.getTxnPayload()
					.getRefEntityObjectTypeDO();
			theRefEntityObjectTypeComponentRule.preValidateRefEntityObjectTypefindByBusinessKey(txnTransferObj);
			theRefEntityObjectTypeComponentRule.preExecuteRefEntityObjectTypefindByBusinessKey(txnTransferObj);

			RefEntityObjectTypeDO dbimageRefEntityObjectTypeDO = executeRepositoryQuery(
					reqRefEntityObjectTypeDO.getConfigLanguageCodeKey(), reqRefEntityObjectTypeDO.getKey(),
					reqRefEntityObjectTypeDO.getInquiryFilter());

			if (null == dbimageRefEntityObjectTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefEntityObjectTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefEntityObjectTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefEntityObjectTypeDO(new RefEntityObjectTypeDO(dbimageRefEntityObjectTypeDO));
			}

			theRefEntityObjectTypeComponentRule.postExecuteRefEntityObjectTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefEntityObjectTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefEntityObjectTypeDO returns the populated RefEntityObjectTypeDO object
	 */
	@CacheResult(cacheName = "REFENTITYOBJECTTYPE_BUSKEY")
	public RefEntityObjectTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefEntityObjectTypeDO dbimageRefEntityObjectTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefEntityObjectTypeDO = this.theRefEntityObjectTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefEntityObjectTypeDO = this.theRefEntityObjectTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefEntityObjectTypeDO = this.theRefEntityObjectTypeRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefEntityObjectTypeDO;
	}

	/**
	 * perform the common validation that RefEntityObjectTypeDO,
	 * RefEntityObjectTypeDO.configLanguageCodeKey and RefEntityObjectTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefEntityObjectTypeDO is needed in the request");
		}
		RefEntityObjectTypeDO theRefEntityObjectTypeDO = (RefEntityObjectTypeDO) txnTransferObj.getTxnPayload()
				.getRefEntityObjectTypeDO();
		if (null == theRefEntityObjectTypeDO.getKey() || theRefEntityObjectTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefEntityObjectTypeDO.key should not be null");
		}

		if (null == theRefEntityObjectTypeDO.getConfigLanguageCodeKey()
				|| theRefEntityObjectTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefEntityObjectTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefEntityObjectTypeDO and
	 * RefEntityObjectTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefEntityObjectTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefEntityObjectTypeDO is needed in the request");
		}
		RefEntityObjectTypeDO theRefEntityObjectTypeDO = (RefEntityObjectTypeDO) txnTransferObj.getTxnPayload()
				.getRefEntityObjectTypeDO();

		if (null == theRefEntityObjectTypeDO.getConfigLanguageCodeKey()
				|| theRefEntityObjectTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefEntityObjectTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefEntityObjectTypeDO().getInquiryFilter());
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
