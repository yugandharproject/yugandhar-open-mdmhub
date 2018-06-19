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
import com.yugandhar.mdm.extern.dobj.RefIdentificationTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefIdentificationTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefIdentificationTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefIdentificationTypeRepository theRefIdentificationTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefIdentificationTypeComponentRule theRefIdentificationTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefIdentificationTypeComponent() {
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
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefIdentificationTypeComponentRule.prevalidateRefIdentificationTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefIdentificationTypeDO reqRefIdentificationTypeDO = (RefIdentificationTypeDO) txnTransferObj
					.getTxnPayload().getRefIdentificationTypeDO();
			if (null == reqRefIdentificationTypeDO.getPrimaryKeyDO()
					|| null == reqRefIdentificationTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefIdentificationTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefIdentificationTypeDO.setIdPk(reqRefIdentificationTypeDO.getPrimaryKeyDO().getIdPk());
				RefIdentificationTypeDO dbimageRefIdentificationTypeDO = entityManager
						.find(RefIdentificationTypeDO.class, reqRefIdentificationTypeDO.getIdPk());
				if (null != dbimageRefIdentificationTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefIdentificationTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefIdentificationTypeComponentRule.preExecuteRefIdentificationTypeCompPersist(reqRefIdentificationTypeDO,
					txnTransferObj);
			entityManager.persist(reqRefIdentificationTypeDO);
			entityManager.flush();
			reqRefIdentificationTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefIdentificationTypeDO(new RefIdentificationTypeDO(reqRefIdentificationTypeDO));
			theRefIdentificationTypeComponentRule.postExecuteRefIdentificationTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefIdentificationTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefIdentificationTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIdentificationTypeComponent.persist failed unexpectedly");
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
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefIdentificationTypeComponentRule.PrevalidateRefIdentificationTypeCompMerge(txnTransferObj);
			RefIdentificationTypeDO reqRefIdentificationTypeDO = (RefIdentificationTypeDO) txnTransferObj
					.getTxnPayload().getRefIdentificationTypeDO();
			RefIdentificationTypeDO dbimageRefIdentificationTypeDO = entityManager.find(RefIdentificationTypeDO.class,
					reqRefIdentificationTypeDO.getIdPk());
			if (null == dbimageRefIdentificationTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefIdentificationTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefIdentificationTypeDO);
			BeanUtils.copyProperties(reqRefIdentificationTypeDO, dbimageRefIdentificationTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefIdentificationTypeDO);
			theRefIdentificationTypeComponentRule.preExecuteRefIdentificationTypeCompMerge(reqRefIdentificationTypeDO,
					dbimageRefIdentificationTypeDO, txnTransferObj);
			RefIdentificationTypeDO mergedRefIdentificationTypeDO = entityManager.merge(dbimageRefIdentificationTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefIdentificationTypeDO(new RefIdentificationTypeDO(mergedRefIdentificationTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefIdentificationTypeComponentRule.postExecuteRefIdentificationTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefIdentificationTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefIdentificationTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefIdentificationTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIdentificationTypeComponent.merge failed unexpectedly");
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
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefIdentificationTypeComponentRule.prevalidateRefIdentificationTypeCompFindById(txnTransferObj);
			RefIdentificationTypeDO reqRefIdentificationTypeDO = (RefIdentificationTypeDO) txnTransferObj
					.getTxnPayload().getRefIdentificationTypeDO();
			RefIdentificationTypeDO dbimageRefIdentificationTypeDO = entityManager.find(RefIdentificationTypeDO.class,
					reqRefIdentificationTypeDO.getIdPk());
			if (null == dbimageRefIdentificationTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefIdentificationTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefIdentificationTypeDO(new RefIdentificationTypeDO(dbimageRefIdentificationTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefIdentificationTypeComponentRule.postExecuteRefIdentificationTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIdentificationTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefIdentificationTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefIdentificationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIdentificationTypeDO is needed in the request");
		}

		RefIdentificationTypeDO theRefIdentificationTypeDO = (RefIdentificationTypeDO) txnTransferObj.getTxnPayload()
				.getRefIdentificationTypeDO();
		if (null == theRefIdentificationTypeDO.getKey() || theRefIdentificationTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefIdentificationTypeDO.key should not be null");
		}

		if (null == theRefIdentificationTypeDO.getConfigLanguageCodeKey()
				|| theRefIdentificationTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefIdentificationTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefIdentificationTypeDO.getValue() || theRefIdentificationTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefIdentificationTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefIdentificationTypeDO.getConfigLanguageCodeKey()
				|| theRefIdentificationTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefIdentificationTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefIdentificationTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefIdentificationTypeDO.setCreatedTs(new Date());
		theRefIdentificationTypeDO.setUpdatedTs(new Date());
		theRefIdentificationTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefIdentificationTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefIdentificationTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefIdentificationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIdentificationTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefIdentificationTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefIdentificationTypeDO.version should not be null");
		}

		RefIdentificationTypeDO theRefIdentificationTypeDO = (RefIdentificationTypeDO) txnTransferObj.getTxnPayload()
				.getRefIdentificationTypeDO();
		if (null == theRefIdentificationTypeDO.getKey() || theRefIdentificationTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefIdentificationTypeDO.key should not be null");
		}

		if (null == theRefIdentificationTypeDO.getConfigLanguageCodeKey()
				|| theRefIdentificationTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefIdentificationTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefIdentificationTypeDO.getValue() || theRefIdentificationTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefIdentificationTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefIdentificationTypeDO.getConfigLanguageCodeKey()
				|| theRefIdentificationTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefIdentificationTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefIdentificationTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefIdentificationTypeDO.setUpdatedTs(new Date());
		theRefIdentificationTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefIdentificationTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefIdentificationTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefIdentificationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIdentificationTypeDO is needed in the request");
		}
		RefIdentificationTypeDO theRefIdentificationTypeDO = (RefIdentificationTypeDO) txnTransferObj.getTxnPayload()
				.getRefIdentificationTypeDO();
		if (null == theRefIdentificationTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefIdentificationTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefIdentificationTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefIdentificationTypeDO> pageRefIdentificationTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefIdentificationTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefIdentificationType reference list does not have any records in the database");
			}

			if ((pageRefIdentificationTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefIdentificationType, total number of pages is "
								+ pageRefIdentificationTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefIdentificationTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefIdentificationTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefIdentificationTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefIdentificationTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefIdentificationTypeDO.getSize());

			List<RefIdentificationTypeDO> dbimageRefIdentificationTypeDOlist = pageRefIdentificationTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefIdentificationTypeDO> clonedRefIdentificationTypeDOList = null;
			if (null != dbimageRefIdentificationTypeDOlist && dbimageRefIdentificationTypeDOlist.size() > 0) {
				clonedRefIdentificationTypeDOList = new ArrayList<RefIdentificationTypeDO>();
				Iterator<RefIdentificationTypeDO> itr = dbimageRefIdentificationTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefIdentificationTypeDO theRefIdentificationTypeDO = new RefIdentificationTypeDO(itr.next());
					clonedRefIdentificationTypeDOList.add(theRefIdentificationTypeDO);
				}
			}

			if (null == clonedRefIdentificationTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefIdentificationType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefIdentificationTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefIdentificationType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefIdentificationTypeDOList(clonedRefIdentificationTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIdentificationTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFIDENTIFICATIONTYPE_BUSKEY")
	public Page<RefIdentificationTypeDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefIdentificationTypeDO> pageRefIdentificationTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefIdentificationTypeDO = this.theRefIdentificationTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefIdentificationTypeDO = this.theRefIdentificationTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefIdentificationTypeDO = this.theRefIdentificationTypeRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefIdentificationTypeDO;
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
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefIdentificationTypeDO reqRefIdentificationTypeDO = (RefIdentificationTypeDO) txnTransferObj
					.getTxnPayload().getRefIdentificationTypeDO();
			theRefIdentificationTypeComponentRule.preValidateRefIdentificationTypefindByBusinessKey(txnTransferObj);
			theRefIdentificationTypeComponentRule.preExecuteRefIdentificationTypefindByBusinessKey(txnTransferObj);

			RefIdentificationTypeDO dbimageRefIdentificationTypeDO = executeRepositoryQuery(
					reqRefIdentificationTypeDO.getConfigLanguageCodeKey(), reqRefIdentificationTypeDO.getKey(),
					reqRefIdentificationTypeDO.getInquiryFilter());

			if (null == dbimageRefIdentificationTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefIdentificationTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefIdentificationTypeDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefIdentificationTypeDO(new RefIdentificationTypeDO(dbimageRefIdentificationTypeDO));
			}

			theRefIdentificationTypeComponentRule.postExecuteRefIdentificationTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefIdentificationTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefIdentificationTypeDO returns the populated RefIdentificationTypeDO object
	 */
	@CacheResult(cacheName = "REFIDENTIFICATIONTYPE_BUSKEY")
	public RefIdentificationTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefIdentificationTypeDO dbimageRefIdentificationTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefIdentificationTypeDO = this.theRefIdentificationTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefIdentificationTypeDO = this.theRefIdentificationTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefIdentificationTypeDO = this.theRefIdentificationTypeRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefIdentificationTypeDO;
	}

	/**
	 * perform the common validation that RefIdentificationTypeDO,
	 * RefIdentificationTypeDO.configLanguageCodeKey and RefIdentificationTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefIdentificationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIdentificationTypeDO is needed in the request");
		}
		RefIdentificationTypeDO theRefIdentificationTypeDO = (RefIdentificationTypeDO) txnTransferObj.getTxnPayload()
				.getRefIdentificationTypeDO();
		if (null == theRefIdentificationTypeDO.getKey() || theRefIdentificationTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefIdentificationTypeDO.key should not be null");
		}

		if (null == theRefIdentificationTypeDO.getConfigLanguageCodeKey()
				|| theRefIdentificationTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefIdentificationTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefIdentificationTypeDO and
	 * RefIdentificationTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefIdentificationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefIdentificationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefIdentificationTypeDO is needed in the request");
		}
		RefIdentificationTypeDO theRefIdentificationTypeDO = (RefIdentificationTypeDO) txnTransferObj.getTxnPayload()
				.getRefIdentificationTypeDO();

		if (null == theRefIdentificationTypeDO.getConfigLanguageCodeKey()
				|| theRefIdentificationTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefIdentificationTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefIdentificationTypeDO().getInquiryFilter());
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
