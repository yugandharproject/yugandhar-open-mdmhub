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
import com.yugandhar.mdm.extern.dobj.RefAssocTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefAssocTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefAssocTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefAssocTypeRepository theRefAssocTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefAssocTypeComponentRule theRefAssocTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefAssocTypeComponent() {
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
	 *             if RefAssocTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefAssocTypeComponentRule.prevalidateRefAssocTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefAssocTypeDO reqRefAssocTypeDO = (RefAssocTypeDO) txnTransferObj.getTxnPayload().getRefAssocTypeDO();
			if (null == reqRefAssocTypeDO.getPrimaryKeyDO() || null == reqRefAssocTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefAssocTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefAssocTypeDO.setIdPk(reqRefAssocTypeDO.getPrimaryKeyDO().getIdPk());
				RefAssocTypeDO dbimageRefAssocTypeDO = entityManager.find(RefAssocTypeDO.class,
						reqRefAssocTypeDO.getIdPk());
				if (null != dbimageRefAssocTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefAssocTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefAssocTypeComponentRule.preExecuteRefAssocTypeCompPersist(reqRefAssocTypeDO, txnTransferObj);
			entityManager.persist(reqRefAssocTypeDO);
			entityManager.flush();
			reqRefAssocTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefAssocTypeDO(new RefAssocTypeDO(reqRefAssocTypeDO));
			theRefAssocTypeComponentRule.postExecuteRefAssocTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAssocTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAssocTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAssocTypeComponent.persist failed unexpectedly");
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
	 *             if RefAssocTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefAssocTypeComponentRule.PrevalidateRefAssocTypeCompMerge(txnTransferObj);
			RefAssocTypeDO reqRefAssocTypeDO = (RefAssocTypeDO) txnTransferObj.getTxnPayload().getRefAssocTypeDO();
			RefAssocTypeDO dbimageRefAssocTypeDO = entityManager.find(RefAssocTypeDO.class,
					reqRefAssocTypeDO.getIdPk());
			if (null == dbimageRefAssocTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAssocTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefAssocTypeDO);
			BeanUtils.copyProperties(reqRefAssocTypeDO, dbimageRefAssocTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefAssocTypeDO);
			theRefAssocTypeComponentRule.preExecuteRefAssocTypeCompMerge(reqRefAssocTypeDO, dbimageRefAssocTypeDO,
					txnTransferObj);
			RefAssocTypeDO mergedRefAssocTypeDO = entityManager.merge(dbimageRefAssocTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefAssocTypeDO(new RefAssocTypeDO(mergedRefAssocTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAssocTypeComponentRule.postExecuteRefAssocTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefAssocTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAssocTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAssocTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAssocTypeComponent.merge failed unexpectedly");
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
	 *             if RefAssocTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefAssocTypeComponentRule.prevalidateRefAssocTypeCompFindById(txnTransferObj);
			RefAssocTypeDO reqRefAssocTypeDO = (RefAssocTypeDO) txnTransferObj.getTxnPayload().getRefAssocTypeDO();
			RefAssocTypeDO dbimageRefAssocTypeDO = entityManager.find(RefAssocTypeDO.class,
					reqRefAssocTypeDO.getIdPk());
			if (null == dbimageRefAssocTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAssocTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefAssocTypeDO(new RefAssocTypeDO(dbimageRefAssocTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAssocTypeComponentRule.postExecuteRefAssocTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAssocTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefAssocTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAssocTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAssocTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAssocTypeDO is needed in the request");
		}

		RefAssocTypeDO theRefAssocTypeDO = (RefAssocTypeDO) txnTransferObj.getTxnPayload().getRefAssocTypeDO();
		if (null == theRefAssocTypeDO.getKey() || theRefAssocTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAssocTypeDO.key should not be null");
		}

		if (null == theRefAssocTypeDO.getConfigLanguageCodeKey()
				|| theRefAssocTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAssocTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAssocTypeDO.getValue() || theRefAssocTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAssocTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAssocTypeDO.getConfigLanguageCodeKey()
				|| theRefAssocTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAssocTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAssocTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAssocTypeDO.setCreatedTs(new Date());
		theRefAssocTypeDO.setUpdatedTs(new Date());
		theRefAssocTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAssocTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefAssocTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAssocTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAssocTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAssocTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefAssocTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefAssocTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAssocTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefAssocTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefAssocTypeDO.version should not be null");
		}

		RefAssocTypeDO theRefAssocTypeDO = (RefAssocTypeDO) txnTransferObj.getTxnPayload().getRefAssocTypeDO();
		if (null == theRefAssocTypeDO.getKey() || theRefAssocTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAssocTypeDO.key should not be null");
		}

		if (null == theRefAssocTypeDO.getConfigLanguageCodeKey()
				|| theRefAssocTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAssocTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAssocTypeDO.getValue() || theRefAssocTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAssocTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAssocTypeDO.getConfigLanguageCodeKey()
				|| theRefAssocTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAssocTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAssocTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAssocTypeDO.setUpdatedTs(new Date());
		theRefAssocTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAssocTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefAssocTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAssocTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAssocTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAssocTypeDO is needed in the request");
		}
		RefAssocTypeDO theRefAssocTypeDO = (RefAssocTypeDO) txnTransferObj.getTxnPayload().getRefAssocTypeDO();
		if (null == theRefAssocTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAssocTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefAssocTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAssocTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefAssocTypeDO> pageRefAssocTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefAssocTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefAssocTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefAssocTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAssocType reference list does not have any records in the database");
			}

			if ((pageRefAssocTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefAssocType, total number of pages is "
								+ pageRefAssocTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefAssocTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefAssocTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefAssocTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefAssocTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefAssocTypeDO.getSize());

			List<RefAssocTypeDO> dbimageRefAssocTypeDOlist = pageRefAssocTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefAssocTypeDO> clonedRefAssocTypeDOList = null;
			if (null != dbimageRefAssocTypeDOlist && dbimageRefAssocTypeDOlist.size() > 0) {
				clonedRefAssocTypeDOList = new ArrayList<RefAssocTypeDO>();
				Iterator<RefAssocTypeDO> itr = dbimageRefAssocTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefAssocTypeDO theRefAssocTypeDO = new RefAssocTypeDO(itr.next());
					clonedRefAssocTypeDOList.add(theRefAssocTypeDO);
				}
			}

			if (null == clonedRefAssocTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAssocType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefAssocTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAssocType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefAssocTypeDOList(clonedRefAssocTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAssocTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFASSOCTYPE_BUSKEY")
	public Page<RefAssocTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefAssocTypeDO> pageRefAssocTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefAssocTypeDO = this.theRefAssocTypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefAssocTypeDO = this.theRefAssocTypeRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefAssocTypeDO = this.theRefAssocTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefAssocTypeDO;
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
	 *             if RefAssocTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefAssocTypeDO reqRefAssocTypeDO = (RefAssocTypeDO) txnTransferObj.getTxnPayload().getRefAssocTypeDO();
			theRefAssocTypeComponentRule.preValidateRefAssocTypefindByBusinessKey(txnTransferObj);
			theRefAssocTypeComponentRule.preExecuteRefAssocTypefindByBusinessKey(txnTransferObj);

			RefAssocTypeDO dbimageRefAssocTypeDO = executeRepositoryQuery(reqRefAssocTypeDO.getConfigLanguageCodeKey(),
					reqRefAssocTypeDO.getKey(), reqRefAssocTypeDO.getInquiryFilter());

			if (null == dbimageRefAssocTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAssocTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefAssocTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefAssocTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefAssocTypeDO(new RefAssocTypeDO(dbimageRefAssocTypeDO));
			}

			theRefAssocTypeComponentRule.postExecuteRefAssocTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAssocTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefAssocTypeDO returns the populated RefAssocTypeDO object
	 */
	@CacheResult(cacheName = "REFASSOCTYPE_BUSKEY")
	public RefAssocTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefAssocTypeDO dbimageRefAssocTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefAssocTypeDO = this.theRefAssocTypeRepository.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefAssocTypeDO = this.theRefAssocTypeRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefAssocTypeDO = this.theRefAssocTypeRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefAssocTypeDO;
	}

	/**
	 * perform the common validation that RefAssocTypeDO,
	 * RefAssocTypeDO.configLanguageCodeKey and RefAssocTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAssocTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAssocTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAssocTypeDO is needed in the request");
		}
		RefAssocTypeDO theRefAssocTypeDO = (RefAssocTypeDO) txnTransferObj.getTxnPayload().getRefAssocTypeDO();
		if (null == theRefAssocTypeDO.getKey() || theRefAssocTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAssocTypeDO.key should not be null");
		}

		if (null == theRefAssocTypeDO.getConfigLanguageCodeKey()
				|| theRefAssocTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAssocTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAssocTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAssocTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAssocTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAssocTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefAssocTypeDO and
	 * RefAssocTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAssocTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAssocTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAssocTypeDO is needed in the request");
		}
		RefAssocTypeDO theRefAssocTypeDO = (RefAssocTypeDO) txnTransferObj.getTxnPayload().getRefAssocTypeDO();

		if (null == theRefAssocTypeDO.getConfigLanguageCodeKey()
				|| theRefAssocTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAssocTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAssocTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAssocTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAssocTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAssocTypeDO().getInquiryFilter());
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
