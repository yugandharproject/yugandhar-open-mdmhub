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
import com.yugandhar.mdm.extern.dobj.RefAddressTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefAddressTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefAddressTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefAddressTypeRepository theRefAddressTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefAddressTypeComponentRule theRefAddressTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefAddressTypeComponent() {
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
	 *             if RefAddressTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefAddressTypeComponentRule.prevalidateRefAddressTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefAddressTypeDO reqRefAddressTypeDO = (RefAddressTypeDO) txnTransferObj.getTxnPayload()
					.getRefAddressTypeDO();
			if (null == reqRefAddressTypeDO.getPrimaryKeyDO()
					|| null == reqRefAddressTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefAddressTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefAddressTypeDO.setIdPk(reqRefAddressTypeDO.getPrimaryKeyDO().getIdPk());
				RefAddressTypeDO dbimageRefAddressTypeDO = entityManager.find(RefAddressTypeDO.class,
						reqRefAddressTypeDO.getIdPk());
				if (null != dbimageRefAddressTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefAddressTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefAddressTypeComponentRule.preExecuteRefAddressTypeCompPersist(reqRefAddressTypeDO, txnTransferObj);
			entityManager.persist(reqRefAddressTypeDO);
			entityManager.flush();
			reqRefAddressTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefAddressTypeDO(new RefAddressTypeDO(reqRefAddressTypeDO));
			theRefAddressTypeComponentRule.postExecuteRefAddressTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAddressTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAddressTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressTypeComponent.persist failed unexpectedly");
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
	 *             if RefAddressTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefAddressTypeComponentRule.PrevalidateRefAddressTypeCompMerge(txnTransferObj);
			RefAddressTypeDO reqRefAddressTypeDO = (RefAddressTypeDO) txnTransferObj.getTxnPayload()
					.getRefAddressTypeDO();
			RefAddressTypeDO dbimageRefAddressTypeDO = entityManager.find(RefAddressTypeDO.class,
					reqRefAddressTypeDO.getIdPk());
			if (null == dbimageRefAddressTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAddressTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefAddressTypeDO);
			BeanUtils.copyProperties(reqRefAddressTypeDO, dbimageRefAddressTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefAddressTypeDO);
			theRefAddressTypeComponentRule.preExecuteRefAddressTypeCompMerge(reqRefAddressTypeDO,
					dbimageRefAddressTypeDO, txnTransferObj);
			RefAddressTypeDO mergedRefAddressTypeDO = entityManager.merge(dbimageRefAddressTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefAddressTypeDO(new RefAddressTypeDO(mergedRefAddressTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAddressTypeComponentRule.postExecuteRefAddressTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefAddressTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAddressTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAddressTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressTypeComponent.merge failed unexpectedly");
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
	 *             if RefAddressTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefAddressTypeComponentRule.prevalidateRefAddressTypeCompFindById(txnTransferObj);
			RefAddressTypeDO reqRefAddressTypeDO = (RefAddressTypeDO) txnTransferObj.getTxnPayload()
					.getRefAddressTypeDO();
			RefAddressTypeDO dbimageRefAddressTypeDO = entityManager.find(RefAddressTypeDO.class,
					reqRefAddressTypeDO.getIdPk());
			if (null == dbimageRefAddressTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAddressTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefAddressTypeDO(new RefAddressTypeDO(dbimageRefAddressTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAddressTypeComponentRule.postExecuteRefAddressTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefAddressTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAddressTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressTypeDO is needed in the request");
		}

		RefAddressTypeDO theRefAddressTypeDO = (RefAddressTypeDO) txnTransferObj.getTxnPayload().getRefAddressTypeDO();
		if (null == theRefAddressTypeDO.getKey() || theRefAddressTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAddressTypeDO.key should not be null");
		}

		if (null == theRefAddressTypeDO.getConfigLanguageCodeKey()
				|| theRefAddressTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAddressTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAddressTypeDO.getValue() || theRefAddressTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAddressTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAddressTypeDO.getConfigLanguageCodeKey()
				|| theRefAddressTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAddressTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAddressTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAddressTypeDO.setCreatedTs(new Date());
		theRefAddressTypeDO.setUpdatedTs(new Date());
		theRefAddressTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAddressTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefAddressTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAddressTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefAddressTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefAddressTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAddressTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefAddressTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefAddressTypeDO.version should not be null");
		}

		RefAddressTypeDO theRefAddressTypeDO = (RefAddressTypeDO) txnTransferObj.getTxnPayload().getRefAddressTypeDO();
		if (null == theRefAddressTypeDO.getKey() || theRefAddressTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAddressTypeDO.key should not be null");
		}

		if (null == theRefAddressTypeDO.getConfigLanguageCodeKey()
				|| theRefAddressTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAddressTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAddressTypeDO.getValue() || theRefAddressTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAddressTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAddressTypeDO.getConfigLanguageCodeKey()
				|| theRefAddressTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAddressTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAddressTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAddressTypeDO.setUpdatedTs(new Date());
		theRefAddressTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAddressTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefAddressTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAddressTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressTypeDO is needed in the request");
		}
		RefAddressTypeDO theRefAddressTypeDO = (RefAddressTypeDO) txnTransferObj.getTxnPayload().getRefAddressTypeDO();
		if (null == theRefAddressTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAddressTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefAddressTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefAddressTypeDO> pageRefAddressTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefAddressTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefAddressTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefAddressTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAddressType reference list does not have any records in the database");
			}

			if ((pageRefAddressTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefAddressType, total number of pages is "
								+ pageRefAddressTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefAddressTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefAddressTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefAddressTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefAddressTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefAddressTypeDO.getSize());

			List<RefAddressTypeDO> dbimageRefAddressTypeDOlist = pageRefAddressTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefAddressTypeDO> clonedRefAddressTypeDOList = null;
			if (null != dbimageRefAddressTypeDOlist && dbimageRefAddressTypeDOlist.size() > 0) {
				clonedRefAddressTypeDOList = new ArrayList<RefAddressTypeDO>();
				Iterator<RefAddressTypeDO> itr = dbimageRefAddressTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefAddressTypeDO theRefAddressTypeDO = new RefAddressTypeDO(itr.next());
					clonedRefAddressTypeDOList.add(theRefAddressTypeDO);
				}
			}

			if (null == clonedRefAddressTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAddressType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefAddressTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAddressType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefAddressTypeDOList(clonedRefAddressTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFADDRESSTYPE_BUSKEY")
	public Page<RefAddressTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefAddressTypeDO> pageRefAddressTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefAddressTypeDO = this.theRefAddressTypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefAddressTypeDO = this.theRefAddressTypeRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefAddressTypeDO = this.theRefAddressTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefAddressTypeDO;
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
	 *             if RefAddressTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefAddressTypeDO reqRefAddressTypeDO = (RefAddressTypeDO) txnTransferObj.getTxnPayload()
					.getRefAddressTypeDO();
			theRefAddressTypeComponentRule.preValidateRefAddressTypefindByBusinessKey(txnTransferObj);
			theRefAddressTypeComponentRule.preExecuteRefAddressTypefindByBusinessKey(txnTransferObj);

			RefAddressTypeDO dbimageRefAddressTypeDO = executeRepositoryQuery(
					reqRefAddressTypeDO.getConfigLanguageCodeKey(), reqRefAddressTypeDO.getKey(),
					reqRefAddressTypeDO.getInquiryFilter());

			if (null == dbimageRefAddressTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAddressTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefAddressTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefAddressTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefAddressTypeDO(new RefAddressTypeDO(dbimageRefAddressTypeDO));
			}

			theRefAddressTypeComponentRule.postExecuteRefAddressTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefAddressTypeDO returns the populated RefAddressTypeDO object
	 */
	@CacheResult(cacheName = "REFADDRESSTYPE_BUSKEY")
	public RefAddressTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefAddressTypeDO dbimageRefAddressTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefAddressTypeDO = this.theRefAddressTypeRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefAddressTypeDO = this.theRefAddressTypeRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefAddressTypeDO = this.theRefAddressTypeRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefAddressTypeDO;
	}

	/**
	 * perform the common validation that RefAddressTypeDO,
	 * RefAddressTypeDO.configLanguageCodeKey and RefAddressTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAddressTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressTypeDO is needed in the request");
		}
		RefAddressTypeDO theRefAddressTypeDO = (RefAddressTypeDO) txnTransferObj.getTxnPayload().getRefAddressTypeDO();
		if (null == theRefAddressTypeDO.getKey() || theRefAddressTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAddressTypeDO.key should not be null");
		}

		if (null == theRefAddressTypeDO.getConfigLanguageCodeKey()
				|| theRefAddressTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAddressTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAddressTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAddressTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAddressTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAddressTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefAddressTypeDO and
	 * RefAddressTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAddressTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressTypeDO is needed in the request");
		}
		RefAddressTypeDO theRefAddressTypeDO = (RefAddressTypeDO) txnTransferObj.getTxnPayload().getRefAddressTypeDO();

		if (null == theRefAddressTypeDO.getConfigLanguageCodeKey()
				|| theRefAddressTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAddressTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAddressTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAddressTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAddressTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAddressTypeDO().getInquiryFilter());
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
