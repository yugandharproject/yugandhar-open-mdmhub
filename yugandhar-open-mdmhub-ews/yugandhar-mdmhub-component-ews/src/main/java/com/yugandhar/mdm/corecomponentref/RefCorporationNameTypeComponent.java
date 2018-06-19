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
import com.yugandhar.mdm.extern.dobj.RefCorporationNameTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefCorporationNameTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefCorporationNameTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefCorporationNameTypeRepository theRefCorporationNameTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefCorporationNameTypeComponentRule theRefCorporationNameTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefCorporationNameTypeComponent() {
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
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefCorporationNameTypeComponentRule.prevalidateRefCorporationNameTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefCorporationNameTypeDO reqRefCorporationNameTypeDO = (RefCorporationNameTypeDO) txnTransferObj
					.getTxnPayload().getRefCorporationNameTypeDO();
			if (null == reqRefCorporationNameTypeDO.getPrimaryKeyDO()
					|| null == reqRefCorporationNameTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefCorporationNameTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefCorporationNameTypeDO.setIdPk(reqRefCorporationNameTypeDO.getPrimaryKeyDO().getIdPk());
				RefCorporationNameTypeDO dbimageRefCorporationNameTypeDO = entityManager
						.find(RefCorporationNameTypeDO.class, reqRefCorporationNameTypeDO.getIdPk());
				if (null != dbimageRefCorporationNameTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefCorporationNameTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefCorporationNameTypeComponentRule
					.preExecuteRefCorporationNameTypeCompPersist(reqRefCorporationNameTypeDO, txnTransferObj);
			entityManager.persist(reqRefCorporationNameTypeDO);
			entityManager.flush();
			reqRefCorporationNameTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefCorporationNameTypeDO(new RefCorporationNameTypeDO(reqRefCorporationNameTypeDO));
			theRefCorporationNameTypeComponentRule.postExecuteRefCorporationNameTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefCorporationNameTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefCorporationNameTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationNameTypeComponent.persist failed unexpectedly");
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
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefCorporationNameTypeComponentRule.PrevalidateRefCorporationNameTypeCompMerge(txnTransferObj);
			RefCorporationNameTypeDO reqRefCorporationNameTypeDO = (RefCorporationNameTypeDO) txnTransferObj
					.getTxnPayload().getRefCorporationNameTypeDO();
			RefCorporationNameTypeDO dbimageRefCorporationNameTypeDO = entityManager
					.find(RefCorporationNameTypeDO.class, reqRefCorporationNameTypeDO.getIdPk());
			if (null == dbimageRefCorporationNameTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCorporationNameTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefCorporationNameTypeDO);
			BeanUtils.copyProperties(reqRefCorporationNameTypeDO, dbimageRefCorporationNameTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefCorporationNameTypeDO);
			theRefCorporationNameTypeComponentRule.preExecuteRefCorporationNameTypeCompMerge(
					reqRefCorporationNameTypeDO, dbimageRefCorporationNameTypeDO, txnTransferObj);
			RefCorporationNameTypeDO mergedRefCorporationNameTypeDO = entityManager
					.merge(dbimageRefCorporationNameTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefCorporationNameTypeDO(new RefCorporationNameTypeDO(mergedRefCorporationNameTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefCorporationNameTypeComponentRule.postExecuteRefCorporationNameTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefCorporationNameTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefCorporationNameTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefCorporationNameTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationNameTypeComponent.merge failed unexpectedly");
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
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefCorporationNameTypeComponentRule.prevalidateRefCorporationNameTypeCompFindById(txnTransferObj);
			RefCorporationNameTypeDO reqRefCorporationNameTypeDO = (RefCorporationNameTypeDO) txnTransferObj
					.getTxnPayload().getRefCorporationNameTypeDO();
			RefCorporationNameTypeDO dbimageRefCorporationNameTypeDO = entityManager
					.find(RefCorporationNameTypeDO.class, reqRefCorporationNameTypeDO.getIdPk());
			if (null == dbimageRefCorporationNameTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCorporationNameTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefCorporationNameTypeDO(new RefCorporationNameTypeDO(dbimageRefCorporationNameTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefCorporationNameTypeComponentRule.postExecuteRefCorporationNameTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationNameTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefCorporationNameTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationNameTypeDO is needed in the request");
		}

		RefCorporationNameTypeDO theRefCorporationNameTypeDO = (RefCorporationNameTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationNameTypeDO();
		if (null == theRefCorporationNameTypeDO.getKey() || theRefCorporationNameTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCorporationNameTypeDO.key should not be null");
		}

		if (null == theRefCorporationNameTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationNameTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCorporationNameTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefCorporationNameTypeDO.getValue() || theRefCorporationNameTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefCorporationNameTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefCorporationNameTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationNameTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefCorporationNameTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefCorporationNameTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefCorporationNameTypeDO.setCreatedTs(new Date());
		theRefCorporationNameTypeDO.setUpdatedTs(new Date());
		theRefCorporationNameTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefCorporationNameTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefCorporationNameTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationNameTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefCorporationNameTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefCorporationNameTypeDO.version should not be null");
		}

		RefCorporationNameTypeDO theRefCorporationNameTypeDO = (RefCorporationNameTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationNameTypeDO();
		if (null == theRefCorporationNameTypeDO.getKey() || theRefCorporationNameTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCorporationNameTypeDO.key should not be null");
		}

		if (null == theRefCorporationNameTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationNameTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCorporationNameTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefCorporationNameTypeDO.getValue() || theRefCorporationNameTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefCorporationNameTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefCorporationNameTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationNameTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefCorporationNameTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefCorporationNameTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefCorporationNameTypeDO.setUpdatedTs(new Date());
		theRefCorporationNameTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefCorporationNameTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefCorporationNameTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationNameTypeDO is needed in the request");
		}
		RefCorporationNameTypeDO theRefCorporationNameTypeDO = (RefCorporationNameTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationNameTypeDO();
		if (null == theRefCorporationNameTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefCorporationNameTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefCorporationNameTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefCorporationNameTypeDO> pageRefCorporationNameTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefCorporationNameTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCorporationNameType reference list does not have any records in the database");
			}

			if ((pageRefCorporationNameTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefCorporationNameType, total number of pages is "
								+ pageRefCorporationNameTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefCorporationNameTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefCorporationNameTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefCorporationNameTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalPages(pageRefCorporationNameTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefCorporationNameTypeDO.getSize());

			List<RefCorporationNameTypeDO> dbimageRefCorporationNameTypeDOlist = pageRefCorporationNameTypeDO
					.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefCorporationNameTypeDO> clonedRefCorporationNameTypeDOList = null;
			if (null != dbimageRefCorporationNameTypeDOlist && dbimageRefCorporationNameTypeDOlist.size() > 0) {
				clonedRefCorporationNameTypeDOList = new ArrayList<RefCorporationNameTypeDO>();
				Iterator<RefCorporationNameTypeDO> itr = dbimageRefCorporationNameTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefCorporationNameTypeDO theRefCorporationNameTypeDO = new RefCorporationNameTypeDO(itr.next());
					clonedRefCorporationNameTypeDOList.add(theRefCorporationNameTypeDO);
				}
			}

			if (null == clonedRefCorporationNameTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCorporationNameType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefCorporationNameTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCorporationNameType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefCorporationNameTypeDOList(clonedRefCorporationNameTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationNameTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFCORPORATIONNAMETYPE_BUSKEY")
	public Page<RefCorporationNameTypeDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefCorporationNameTypeDO> pageRefCorporationNameTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefCorporationNameTypeDO = this.theRefCorporationNameTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefCorporationNameTypeDO = this.theRefCorporationNameTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefCorporationNameTypeDO = this.theRefCorporationNameTypeRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefCorporationNameTypeDO;
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
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefCorporationNameTypeDO reqRefCorporationNameTypeDO = (RefCorporationNameTypeDO) txnTransferObj
					.getTxnPayload().getRefCorporationNameTypeDO();
			theRefCorporationNameTypeComponentRule.preValidateRefCorporationNameTypefindByBusinessKey(txnTransferObj);
			theRefCorporationNameTypeComponentRule.preExecuteRefCorporationNameTypefindByBusinessKey(txnTransferObj);

			RefCorporationNameTypeDO dbimageRefCorporationNameTypeDO = executeRepositoryQuery(
					reqRefCorporationNameTypeDO.getConfigLanguageCodeKey(), reqRefCorporationNameTypeDO.getKey(),
					reqRefCorporationNameTypeDO.getInquiryFilter());

			if (null == dbimageRefCorporationNameTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCorporationNameTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefCorporationNameTypeDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefCorporationNameTypeDO(new RefCorporationNameTypeDO(dbimageRefCorporationNameTypeDO));
			}

			theRefCorporationNameTypeComponentRule
					.postExecuteRefCorporationNameTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCorporationNameTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefCorporationNameTypeDO returns the populated RefCorporationNameTypeDO object
	 */
	@CacheResult(cacheName = "REFCORPORATIONNAMETYPE_BUSKEY")
	public RefCorporationNameTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefCorporationNameTypeDO dbimageRefCorporationNameTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefCorporationNameTypeDO = this.theRefCorporationNameTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefCorporationNameTypeDO = this.theRefCorporationNameTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefCorporationNameTypeDO = this.theRefCorporationNameTypeRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefCorporationNameTypeDO;
	}

	/**
	 * perform the common validation that RefCorporationNameTypeDO,
	 * RefCorporationNameTypeDO.configLanguageCodeKey and RefCorporationNameTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationNameTypeDO is needed in the request");
		}
		RefCorporationNameTypeDO theRefCorporationNameTypeDO = (RefCorporationNameTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationNameTypeDO();
		if (null == theRefCorporationNameTypeDO.getKey() || theRefCorporationNameTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCorporationNameTypeDO.key should not be null");
		}

		if (null == theRefCorporationNameTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationNameTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCorporationNameTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefCorporationNameTypeDO and
	 * RefCorporationNameTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCorporationNameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCorporationNameTypeDO is needed in the request");
		}
		RefCorporationNameTypeDO theRefCorporationNameTypeDO = (RefCorporationNameTypeDO) txnTransferObj.getTxnPayload()
				.getRefCorporationNameTypeDO();

		if (null == theRefCorporationNameTypeDO.getConfigLanguageCodeKey()
				|| theRefCorporationNameTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCorporationNameTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefCorporationNameTypeDO().getInquiryFilter());
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
