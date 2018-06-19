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
import com.yugandhar.mdm.extern.dobj.RefCorporationTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefCorporationTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefCorporationTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefCorporationTypeRepository theRefCorporationTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefCorporationTypeComponentRule theRefCorporationTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefCorporationTypeComponent() {
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
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefCorporationTypeComponentRule.prevalidateRefCorporationTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefCorporationTypeDO reqRefCorporationTypeDO = (RefCorporationTypeDO) txnTransferObj.getTxnPayload()
					.getRefCorporationTypeDO();
			if (null == reqRefCorporationTypeDO.getPrimaryKeyDO()
					|| null == reqRefCorporationTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefCorporationTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefCorporationTypeDO.setIdPk(reqRefCorporationTypeDO.getPrimaryKeyDO().getIdPk());
				RefCorporationTypeDO dbimageRefCorporationTypeDO = entityManager.find(RefCorporationTypeDO.class,
						reqRefCorporationTypeDO.getIdPk());
				if (null != dbimageRefCorporationTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefCorporationTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefCorporationTypeComponentRule.preExecuteRefCorporationTypeCompPersist(reqRefCorporationTypeDO,
					txnTransferObj);
			entityManager.persist(reqRefCorporationTypeDO);
			entityManager.flush();
			reqRefCorporationTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefCorporationTypeDO(new RefCorporationTypeDO(reqRefCorporationTypeDO));
			theRefCorporationTypeComponentRule.postExecuteRefCorporationTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefCorporationTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefCorporationTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationTypeComponent.persist failed unexpectedly");
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
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefCorporationTypeComponentRule.PrevalidateRefCorporationTypeCompMerge(txnTransferObj);
			RefCorporationTypeDO reqRefCorporationTypeDO = (RefCorporationTypeDO) txnTransferObj.getTxnPayload()
					.getRefCorporationTypeDO();
			RefCorporationTypeDO dbimageRefCorporationTypeDO = entityManager.find(RefCorporationTypeDO.class,
					reqRefCorporationTypeDO.getIdPk());
			if (null == dbimageRefCorporationTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCorporationTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefCorporationTypeDO);
			BeanUtils.copyProperties(reqRefCorporationTypeDO, dbimageRefCorporationTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefCorporationTypeDO);
			theRefCorporationTypeComponentRule.preExecuteRefCorporationTypeCompMerge(reqRefCorporationTypeDO,
					dbimageRefCorporationTypeDO, txnTransferObj);
			RefCorporationTypeDO mergedRefCorporationTypeDO = entityManager.merge(dbimageRefCorporationTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefCorporationTypeDO(new RefCorporationTypeDO(mergedRefCorporationTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefCorporationTypeComponentRule.postExecuteRefCorporationTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefCorporationTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefCorporationTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefCorporationTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationTypeComponent.merge failed unexpectedly");
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
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefCorporationTypeComponentRule.prevalidateRefCorporationTypeCompFindById(txnTransferObj);
			RefCorporationTypeDO reqRefCorporationTypeDO = (RefCorporationTypeDO) txnTransferObj.getTxnPayload()
					.getRefCorporationTypeDO();
			RefCorporationTypeDO dbimageRefCorporationTypeDO = entityManager.find(RefCorporationTypeDO.class,
					reqRefCorporationTypeDO.getIdPk());
			if (null == dbimageRefCorporationTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCorporationTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefCorporationTypeDO(new RefCorporationTypeDO(dbimageRefCorporationTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefCorporationTypeComponentRule.postExecuteRefCorporationTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefCorporationTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefCorporationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationTypeDO is needed in the request");
		}

		RefCorporationTypeDO theRefCorporationTypeDO = (RefCorporationTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationTypeDO();
		if (null == theRefCorporationTypeDO.getKey() || theRefCorporationTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCorporationTypeDO.key should not be null");
		}

		if (null == theRefCorporationTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCorporationTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefCorporationTypeDO.getValue() || theRefCorporationTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefCorporationTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefCorporationTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefCorporationTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefCorporationTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefCorporationTypeDO.setCreatedTs(new Date());
		theRefCorporationTypeDO.setUpdatedTs(new Date());
		theRefCorporationTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefCorporationTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefCorporationTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCorporationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefCorporationTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefCorporationTypeDO.version should not be null");
		}

		RefCorporationTypeDO theRefCorporationTypeDO = (RefCorporationTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationTypeDO();
		if (null == theRefCorporationTypeDO.getKey() || theRefCorporationTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCorporationTypeDO.key should not be null");
		}

		if (null == theRefCorporationTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCorporationTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefCorporationTypeDO.getValue() || theRefCorporationTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefCorporationTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefCorporationTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefCorporationTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefCorporationTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefCorporationTypeDO.setUpdatedTs(new Date());
		theRefCorporationTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefCorporationTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefCorporationTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefCorporationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationTypeDO is needed in the request");
		}
		RefCorporationTypeDO theRefCorporationTypeDO = (RefCorporationTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationTypeDO();
		if (null == theRefCorporationTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefCorporationTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefCorporationTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefCorporationTypeDO> pageRefCorporationTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefCorporationTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCorporationType reference list does not have any records in the database");
			}

			if ((pageRefCorporationTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefCorporationType, total number of pages is "
								+ pageRefCorporationTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefCorporationTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefCorporationTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefCorporationTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefCorporationTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefCorporationTypeDO.getSize());

			List<RefCorporationTypeDO> dbimageRefCorporationTypeDOlist = pageRefCorporationTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefCorporationTypeDO> clonedRefCorporationTypeDOList = null;
			if (null != dbimageRefCorporationTypeDOlist && dbimageRefCorporationTypeDOlist.size() > 0) {
				clonedRefCorporationTypeDOList = new ArrayList<RefCorporationTypeDO>();
				Iterator<RefCorporationTypeDO> itr = dbimageRefCorporationTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefCorporationTypeDO theRefCorporationTypeDO = new RefCorporationTypeDO(itr.next());
					clonedRefCorporationTypeDOList.add(theRefCorporationTypeDO);
				}
			}

			if (null == clonedRefCorporationTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCorporationType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefCorporationTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCorporationType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefCorporationTypeDOList(clonedRefCorporationTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFCORPORATIONTYPE_BUSKEY")
	public Page<RefCorporationTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefCorporationTypeDO> pageRefCorporationTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefCorporationTypeDO = this.theRefCorporationTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefCorporationTypeDO = this.theRefCorporationTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefCorporationTypeDO = this.theRefCorporationTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefCorporationTypeDO;
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
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefCorporationTypeDO reqRefCorporationTypeDO = (RefCorporationTypeDO) txnTransferObj.getTxnPayload()
					.getRefCorporationTypeDO();
			theRefCorporationTypeComponentRule.preValidateRefCorporationTypefindByBusinessKey(txnTransferObj);
			theRefCorporationTypeComponentRule.preExecuteRefCorporationTypefindByBusinessKey(txnTransferObj);

			RefCorporationTypeDO dbimageRefCorporationTypeDO = executeRepositoryQuery(
					reqRefCorporationTypeDO.getConfigLanguageCodeKey(), reqRefCorporationTypeDO.getKey(),
					reqRefCorporationTypeDO.getInquiryFilter());

			if (null == dbimageRefCorporationTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCorporationTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefCorporationTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefCorporationTypeDO(new RefCorporationTypeDO(dbimageRefCorporationTypeDO));
			}

			theRefCorporationTypeComponentRule.postExecuteRefCorporationTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefCorporationTypeDO returns the populated RefCorporationTypeDO object
	 */
	@CacheResult(cacheName = "REFCORPORATIONTYPE_BUSKEY")
	public RefCorporationTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefCorporationTypeDO dbimageRefCorporationTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefCorporationTypeDO = this.theRefCorporationTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefCorporationTypeDO = this.theRefCorporationTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefCorporationTypeDO = this.theRefCorporationTypeRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefCorporationTypeDO;
	}

	/**
	 * perform the common validation that RefCorporationTypeDO,
	 * RefCorporationTypeDO.configLanguageCodeKey and RefCorporationTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCorporationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationTypeDO is needed in the request");
		}
		RefCorporationTypeDO theRefCorporationTypeDO = (RefCorporationTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationTypeDO();
		if (null == theRefCorporationTypeDO.getKey() || theRefCorporationTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCorporationTypeDO.key should not be null");
		}

		if (null == theRefCorporationTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCorporationTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefCorporationTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefCorporationTypeDO and
	 * RefCorporationTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCorporationTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationTypeDO is needed in the request");
		}
		RefCorporationTypeDO theRefCorporationTypeDO = (RefCorporationTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationTypeDO();

		if (null == theRefCorporationTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCorporationTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefCorporationTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefCorporationTypeDO().getInquiryFilter());
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
