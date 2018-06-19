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
import com.yugandhar.mdm.extern.dobj.RefPersonTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefPersonTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefPersonTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefPersonTypeRepository theRefPersonTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefPersonTypeComponentRule theRefPersonTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefPersonTypeComponent() {
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
	 *             if RefPersonTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefPersonTypeComponentRule.prevalidateRefPersonTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefPersonTypeDO reqRefPersonTypeDO = (RefPersonTypeDO) txnTransferObj.getTxnPayload().getRefPersonTypeDO();
			if (null == reqRefPersonTypeDO.getPrimaryKeyDO()
					|| null == reqRefPersonTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefPersonTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefPersonTypeDO.setIdPk(reqRefPersonTypeDO.getPrimaryKeyDO().getIdPk());
				RefPersonTypeDO dbimageRefPersonTypeDO = entityManager.find(RefPersonTypeDO.class,
						reqRefPersonTypeDO.getIdPk());
				if (null != dbimageRefPersonTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefPersonTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefPersonTypeComponentRule.preExecuteRefPersonTypeCompPersist(reqRefPersonTypeDO, txnTransferObj);
			entityManager.persist(reqRefPersonTypeDO);
			entityManager.flush();
			reqRefPersonTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefPersonTypeDO(new RefPersonTypeDO(reqRefPersonTypeDO));
			theRefPersonTypeComponentRule.postExecuteRefPersonTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPersonTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPersonTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonTypeComponent.persist failed unexpectedly");
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
	 *             if RefPersonTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefPersonTypeComponentRule.PrevalidateRefPersonTypeCompMerge(txnTransferObj);
			RefPersonTypeDO reqRefPersonTypeDO = (RefPersonTypeDO) txnTransferObj.getTxnPayload().getRefPersonTypeDO();
			RefPersonTypeDO dbimageRefPersonTypeDO = entityManager.find(RefPersonTypeDO.class,
					reqRefPersonTypeDO.getIdPk());
			if (null == dbimageRefPersonTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPersonTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefPersonTypeDO);
			BeanUtils.copyProperties(reqRefPersonTypeDO, dbimageRefPersonTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefPersonTypeDO);
			theRefPersonTypeComponentRule.preExecuteRefPersonTypeCompMerge(reqRefPersonTypeDO, dbimageRefPersonTypeDO,
					txnTransferObj);
			RefPersonTypeDO mergedRefPersonTypeDO = entityManager.merge(dbimageRefPersonTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefPersonTypeDO(new RefPersonTypeDO(mergedRefPersonTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPersonTypeComponentRule.postExecuteRefPersonTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefPersonTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPersonTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPersonTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonTypeComponent.merge failed unexpectedly");
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
	 *             if RefPersonTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefPersonTypeComponentRule.prevalidateRefPersonTypeCompFindById(txnTransferObj);
			RefPersonTypeDO reqRefPersonTypeDO = (RefPersonTypeDO) txnTransferObj.getTxnPayload().getRefPersonTypeDO();
			RefPersonTypeDO dbimageRefPersonTypeDO = entityManager.find(RefPersonTypeDO.class,
					reqRefPersonTypeDO.getIdPk());
			if (null == dbimageRefPersonTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPersonTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefPersonTypeDO(new RefPersonTypeDO(dbimageRefPersonTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPersonTypeComponentRule.postExecuteRefPersonTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefPersonTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPersonTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonTypeDO is needed in the request");
		}

		RefPersonTypeDO theRefPersonTypeDO = (RefPersonTypeDO) txnTransferObj.getTxnPayload().getRefPersonTypeDO();
		if (null == theRefPersonTypeDO.getKey() || theRefPersonTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPersonTypeDO.key should not be null");
		}

		if (null == theRefPersonTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPersonTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPersonTypeDO.getValue() || theRefPersonTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPersonTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPersonTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPersonTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPersonTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPersonTypeDO.setCreatedTs(new Date());
		theRefPersonTypeDO.setUpdatedTs(new Date());
		theRefPersonTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPersonTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefPersonTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPersonTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefPersonTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefPersonTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPersonTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefPersonTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefPersonTypeDO.version should not be null");
		}

		RefPersonTypeDO theRefPersonTypeDO = (RefPersonTypeDO) txnTransferObj.getTxnPayload().getRefPersonTypeDO();
		if (null == theRefPersonTypeDO.getKey() || theRefPersonTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPersonTypeDO.key should not be null");
		}

		if (null == theRefPersonTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPersonTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPersonTypeDO.getValue() || theRefPersonTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPersonTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPersonTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPersonTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPersonTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPersonTypeDO.setUpdatedTs(new Date());
		theRefPersonTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPersonTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefPersonTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPersonTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonTypeDO is needed in the request");
		}
		RefPersonTypeDO theRefPersonTypeDO = (RefPersonTypeDO) txnTransferObj.getTxnPayload().getRefPersonTypeDO();
		if (null == theRefPersonTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPersonTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefPersonTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefPersonTypeDO> pageRefPersonTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefPersonTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefPersonTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefPersonTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPersonType reference list does not have any records in the database");
			}

			if ((pageRefPersonTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefPersonType, total number of pages is "
								+ pageRefPersonTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefPersonTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefPersonTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefPersonTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefPersonTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefPersonTypeDO.getSize());

			List<RefPersonTypeDO> dbimageRefPersonTypeDOlist = pageRefPersonTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefPersonTypeDO> clonedRefPersonTypeDOList = null;
			if (null != dbimageRefPersonTypeDOlist && dbimageRefPersonTypeDOlist.size() > 0) {
				clonedRefPersonTypeDOList = new ArrayList<RefPersonTypeDO>();
				Iterator<RefPersonTypeDO> itr = dbimageRefPersonTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefPersonTypeDO theRefPersonTypeDO = new RefPersonTypeDO(itr.next());
					clonedRefPersonTypeDOList.add(theRefPersonTypeDO);
				}
			}

			if (null == clonedRefPersonTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPersonType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefPersonTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPersonType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefPersonTypeDOList(clonedRefPersonTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFPERSONTYPE_BUSKEY")
	public Page<RefPersonTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefPersonTypeDO> pageRefPersonTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefPersonTypeDO = this.theRefPersonTypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefPersonTypeDO = this.theRefPersonTypeRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefPersonTypeDO = this.theRefPersonTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefPersonTypeDO;
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
	 *             if RefPersonTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefPersonTypeDO reqRefPersonTypeDO = (RefPersonTypeDO) txnTransferObj.getTxnPayload().getRefPersonTypeDO();
			theRefPersonTypeComponentRule.preValidateRefPersonTypefindByBusinessKey(txnTransferObj);
			theRefPersonTypeComponentRule.preExecuteRefPersonTypefindByBusinessKey(txnTransferObj);

			RefPersonTypeDO dbimageRefPersonTypeDO = executeRepositoryQuery(
					reqRefPersonTypeDO.getConfigLanguageCodeKey(), reqRefPersonTypeDO.getKey(),
					reqRefPersonTypeDO.getInquiryFilter());

			if (null == dbimageRefPersonTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPersonTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefPersonTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefPersonTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefPersonTypeDO(new RefPersonTypeDO(dbimageRefPersonTypeDO));
			}

			theRefPersonTypeComponentRule.postExecuteRefPersonTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefPersonTypeDO returns the populated RefPersonTypeDO object
	 */
	@CacheResult(cacheName = "REFPERSONTYPE_BUSKEY")
	public RefPersonTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefPersonTypeDO dbimageRefPersonTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefPersonTypeDO = this.theRefPersonTypeRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefPersonTypeDO = this.theRefPersonTypeRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefPersonTypeDO = this.theRefPersonTypeRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefPersonTypeDO;
	}

	/**
	 * perform the common validation that RefPersonTypeDO,
	 * RefPersonTypeDO.configLanguageCodeKey and RefPersonTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPersonTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonTypeDO is needed in the request");
		}
		RefPersonTypeDO theRefPersonTypeDO = (RefPersonTypeDO) txnTransferObj.getTxnPayload().getRefPersonTypeDO();
		if (null == theRefPersonTypeDO.getKey() || theRefPersonTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPersonTypeDO.key should not be null");
		}

		if (null == theRefPersonTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPersonTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPersonTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPersonTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPersonTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPersonTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefPersonTypeDO and
	 * RefPersonTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPersonTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonTypeDO is needed in the request");
		}
		RefPersonTypeDO theRefPersonTypeDO = (RefPersonTypeDO) txnTransferObj.getTxnPayload().getRefPersonTypeDO();

		if (null == theRefPersonTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPersonTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPersonTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPersonTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPersonTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPersonTypeDO().getInquiryFilter());
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
