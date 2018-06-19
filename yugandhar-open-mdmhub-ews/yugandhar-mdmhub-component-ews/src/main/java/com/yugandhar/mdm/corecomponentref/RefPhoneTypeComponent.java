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
import com.yugandhar.mdm.extern.dobj.RefPhoneTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefPhoneTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefPhoneTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefPhoneTypeRepository theRefPhoneTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefPhoneTypeComponentRule theRefPhoneTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefPhoneTypeComponent() {
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
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefPhoneTypeComponentRule.prevalidateRefPhoneTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefPhoneTypeDO reqRefPhoneTypeDO = (RefPhoneTypeDO) txnTransferObj.getTxnPayload().getRefPhoneTypeDO();
			if (null == reqRefPhoneTypeDO.getPrimaryKeyDO() || null == reqRefPhoneTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefPhoneTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefPhoneTypeDO.setIdPk(reqRefPhoneTypeDO.getPrimaryKeyDO().getIdPk());
				RefPhoneTypeDO dbimageRefPhoneTypeDO = entityManager.find(RefPhoneTypeDO.class,
						reqRefPhoneTypeDO.getIdPk());
				if (null != dbimageRefPhoneTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefPhoneTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefPhoneTypeComponentRule.preExecuteRefPhoneTypeCompPersist(reqRefPhoneTypeDO, txnTransferObj);
			entityManager.persist(reqRefPhoneTypeDO);
			entityManager.flush();
			reqRefPhoneTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefPhoneTypeDO(new RefPhoneTypeDO(reqRefPhoneTypeDO));
			theRefPhoneTypeComponentRule.postExecuteRefPhoneTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPhoneTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPhoneTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneTypeComponent.persist failed unexpectedly");
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
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefPhoneTypeComponentRule.PrevalidateRefPhoneTypeCompMerge(txnTransferObj);
			RefPhoneTypeDO reqRefPhoneTypeDO = (RefPhoneTypeDO) txnTransferObj.getTxnPayload().getRefPhoneTypeDO();
			RefPhoneTypeDO dbimageRefPhoneTypeDO = entityManager.find(RefPhoneTypeDO.class,
					reqRefPhoneTypeDO.getIdPk());
			if (null == dbimageRefPhoneTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPhoneTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefPhoneTypeDO);
			BeanUtils.copyProperties(reqRefPhoneTypeDO, dbimageRefPhoneTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefPhoneTypeDO);
			theRefPhoneTypeComponentRule.preExecuteRefPhoneTypeCompMerge(reqRefPhoneTypeDO, dbimageRefPhoneTypeDO,
					txnTransferObj);
			RefPhoneTypeDO mergedRefPhoneTypeDO = entityManager.merge(dbimageRefPhoneTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefPhoneTypeDO(new RefPhoneTypeDO(mergedRefPhoneTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPhoneTypeComponentRule.postExecuteRefPhoneTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefPhoneTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPhoneTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPhoneTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneTypeComponent.merge failed unexpectedly");
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
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefPhoneTypeComponentRule.prevalidateRefPhoneTypeCompFindById(txnTransferObj);
			RefPhoneTypeDO reqRefPhoneTypeDO = (RefPhoneTypeDO) txnTransferObj.getTxnPayload().getRefPhoneTypeDO();
			RefPhoneTypeDO dbimageRefPhoneTypeDO = entityManager.find(RefPhoneTypeDO.class,
					reqRefPhoneTypeDO.getIdPk());
			if (null == dbimageRefPhoneTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPhoneTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefPhoneTypeDO(new RefPhoneTypeDO(dbimageRefPhoneTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPhoneTypeComponentRule.postExecuteRefPhoneTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefPhoneTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPhoneTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneTypeDO is needed in the request");
		}

		RefPhoneTypeDO theRefPhoneTypeDO = (RefPhoneTypeDO) txnTransferObj.getTxnPayload().getRefPhoneTypeDO();
		if (null == theRefPhoneTypeDO.getKey() || theRefPhoneTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPhoneTypeDO.key should not be null");
		}

		if (null == theRefPhoneTypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPhoneTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPhoneTypeDO.getValue() || theRefPhoneTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPhoneTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPhoneTypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPhoneTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPhoneTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPhoneTypeDO.setCreatedTs(new Date());
		theRefPhoneTypeDO.setUpdatedTs(new Date());
		theRefPhoneTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPhoneTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefPhoneTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPhoneTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPhoneTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefPhoneTypeDO.version should not be null");
		}

		RefPhoneTypeDO theRefPhoneTypeDO = (RefPhoneTypeDO) txnTransferObj.getTxnPayload().getRefPhoneTypeDO();
		if (null == theRefPhoneTypeDO.getKey() || theRefPhoneTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPhoneTypeDO.key should not be null");
		}

		if (null == theRefPhoneTypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPhoneTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPhoneTypeDO.getValue() || theRefPhoneTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPhoneTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPhoneTypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPhoneTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPhoneTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPhoneTypeDO.setUpdatedTs(new Date());
		theRefPhoneTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPhoneTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefPhoneTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPhoneTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneTypeDO is needed in the request");
		}
		RefPhoneTypeDO theRefPhoneTypeDO = (RefPhoneTypeDO) txnTransferObj.getTxnPayload().getRefPhoneTypeDO();
		if (null == theRefPhoneTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPhoneTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefPhoneTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefPhoneTypeDO> pageRefPhoneTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefPhoneTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPhoneType reference list does not have any records in the database");
			}

			if ((pageRefPhoneTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefPhoneType, total number of pages is "
								+ pageRefPhoneTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefPhoneTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefPhoneTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefPhoneTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefPhoneTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefPhoneTypeDO.getSize());

			List<RefPhoneTypeDO> dbimageRefPhoneTypeDOlist = pageRefPhoneTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefPhoneTypeDO> clonedRefPhoneTypeDOList = null;
			if (null != dbimageRefPhoneTypeDOlist && dbimageRefPhoneTypeDOlist.size() > 0) {
				clonedRefPhoneTypeDOList = new ArrayList<RefPhoneTypeDO>();
				Iterator<RefPhoneTypeDO> itr = dbimageRefPhoneTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefPhoneTypeDO theRefPhoneTypeDO = new RefPhoneTypeDO(itr.next());
					clonedRefPhoneTypeDOList.add(theRefPhoneTypeDO);
				}
			}

			if (null == clonedRefPhoneTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPhoneType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefPhoneTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPhoneType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefPhoneTypeDOList(clonedRefPhoneTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFPHONETYPE_BUSKEY")
	public Page<RefPhoneTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefPhoneTypeDO> pageRefPhoneTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefPhoneTypeDO = this.theRefPhoneTypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefPhoneTypeDO = this.theRefPhoneTypeRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefPhoneTypeDO = this.theRefPhoneTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefPhoneTypeDO;
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
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefPhoneTypeDO reqRefPhoneTypeDO = (RefPhoneTypeDO) txnTransferObj.getTxnPayload().getRefPhoneTypeDO();
			theRefPhoneTypeComponentRule.preValidateRefPhoneTypefindByBusinessKey(txnTransferObj);
			theRefPhoneTypeComponentRule.preExecuteRefPhoneTypefindByBusinessKey(txnTransferObj);

			RefPhoneTypeDO dbimageRefPhoneTypeDO = executeRepositoryQuery(reqRefPhoneTypeDO.getConfigLanguageCodeKey(),
					reqRefPhoneTypeDO.getKey(), reqRefPhoneTypeDO.getInquiryFilter());

			if (null == dbimageRefPhoneTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPhoneTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefPhoneTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefPhoneTypeDO(new RefPhoneTypeDO(dbimageRefPhoneTypeDO));
			}

			theRefPhoneTypeComponentRule.postExecuteRefPhoneTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefPhoneTypeDO returns the populated RefPhoneTypeDO object
	 */
	@CacheResult(cacheName = "REFPHONETYPE_BUSKEY")
	public RefPhoneTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefPhoneTypeDO dbimageRefPhoneTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefPhoneTypeDO = this.theRefPhoneTypeRepository.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefPhoneTypeDO = this.theRefPhoneTypeRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefPhoneTypeDO = this.theRefPhoneTypeRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefPhoneTypeDO;
	}

	/**
	 * perform the common validation that RefPhoneTypeDO,
	 * RefPhoneTypeDO.configLanguageCodeKey and RefPhoneTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPhoneTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneTypeDO is needed in the request");
		}
		RefPhoneTypeDO theRefPhoneTypeDO = (RefPhoneTypeDO) txnTransferObj.getTxnPayload().getRefPhoneTypeDO();
		if (null == theRefPhoneTypeDO.getKey() || theRefPhoneTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPhoneTypeDO.key should not be null");
		}

		if (null == theRefPhoneTypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPhoneTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPhoneTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefPhoneTypeDO and
	 * RefPhoneTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPhoneTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneTypeDO is needed in the request");
		}
		RefPhoneTypeDO theRefPhoneTypeDO = (RefPhoneTypeDO) txnTransferObj.getTxnPayload().getRefPhoneTypeDO();

		if (null == theRefPhoneTypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPhoneTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPhoneTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPhoneTypeDO().getInquiryFilter());
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
