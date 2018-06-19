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
import com.yugandhar.mdm.extern.dobj.RefStatusInSourceDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefStatusInSourceDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefStatusInSourceComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefStatusInSourceRepository theRefStatusInSourceRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefStatusInSourceComponentRule theRefStatusInSourceComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefStatusInSourceComponent() {
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
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefStatusInSourceComponentRule.prevalidateRefStatusInSourceCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefStatusInSourceDO reqRefStatusInSourceDO = (RefStatusInSourceDO) txnTransferObj.getTxnPayload()
					.getRefStatusInSourceDO();
			if (null == reqRefStatusInSourceDO.getPrimaryKeyDO()
					|| null == reqRefStatusInSourceDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefStatusInSourceDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefStatusInSourceDO.setIdPk(reqRefStatusInSourceDO.getPrimaryKeyDO().getIdPk());
				RefStatusInSourceDO dbimageRefStatusInSourceDO = entityManager.find(RefStatusInSourceDO.class,
						reqRefStatusInSourceDO.getIdPk());
				if (null != dbimageRefStatusInSourceDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefStatusInSourceComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefStatusInSourceComponentRule.preExecuteRefStatusInSourceCompPersist(reqRefStatusInSourceDO,
					txnTransferObj);
			entityManager.persist(reqRefStatusInSourceDO);
			entityManager.flush();
			reqRefStatusInSourceDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefStatusInSourceDO(new RefStatusInSourceDO(reqRefStatusInSourceDO));
			theRefStatusInSourceComponentRule.postExecuteRefStatusInSourceCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefStatusInSourceComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefStatusInSourceComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusInSourceComponent.persist failed unexpectedly");
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
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefStatusInSourceComponentRule.PrevalidateRefStatusInSourceCompMerge(txnTransferObj);
			RefStatusInSourceDO reqRefStatusInSourceDO = (RefStatusInSourceDO) txnTransferObj.getTxnPayload()
					.getRefStatusInSourceDO();
			RefStatusInSourceDO dbimageRefStatusInSourceDO = entityManager.find(RefStatusInSourceDO.class,
					reqRefStatusInSourceDO.getIdPk());
			if (null == dbimageRefStatusInSourceDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefStatusInSourceComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefStatusInSourceDO);
			BeanUtils.copyProperties(reqRefStatusInSourceDO, dbimageRefStatusInSourceDO, strIgnoreProperties);
			entityManager.detach(dbimageRefStatusInSourceDO);
			theRefStatusInSourceComponentRule.preExecuteRefStatusInSourceCompMerge(reqRefStatusInSourceDO,
					dbimageRefStatusInSourceDO, txnTransferObj);
			RefStatusInSourceDO mergedRefStatusInSourceDO = entityManager.merge(dbimageRefStatusInSourceDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefStatusInSourceDO(new RefStatusInSourceDO(mergedRefStatusInSourceDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefStatusInSourceComponentRule.postExecuteRefStatusInSourceCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefStatusInSourceComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefStatusInSourceComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefStatusInSourceComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusInSourceComponent.merge failed unexpectedly");
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
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefStatusInSourceComponentRule.prevalidateRefStatusInSourceCompFindById(txnTransferObj);
			RefStatusInSourceDO reqRefStatusInSourceDO = (RefStatusInSourceDO) txnTransferObj.getTxnPayload()
					.getRefStatusInSourceDO();
			RefStatusInSourceDO dbimageRefStatusInSourceDO = entityManager.find(RefStatusInSourceDO.class,
					reqRefStatusInSourceDO.getIdPk());
			if (null == dbimageRefStatusInSourceDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefStatusInSourceComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefStatusInSourceDO(new RefStatusInSourceDO(dbimageRefStatusInSourceDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefStatusInSourceComponentRule.postExecuteRefStatusInSourceCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusInSourceComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefStatusInSourceDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefStatusInSourceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusInSourceDO is needed in the request");
		}

		RefStatusInSourceDO theRefStatusInSourceDO = (RefStatusInSourceDO) txnTransferObj.getTxnPayload()
				.getRefStatusInSourceDO();
		if (null == theRefStatusInSourceDO.getKey() || theRefStatusInSourceDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefStatusInSourceDO.key should not be null");
		}

		if (null == theRefStatusInSourceDO.getConfigLanguageCodeKey()
				|| theRefStatusInSourceDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStatusInSourceDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefStatusInSourceDO.getValue() || theRefStatusInSourceDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefStatusInSourceDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefStatusInSourceDO.getConfigLanguageCodeKey()
				|| theRefStatusInSourceDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefStatusInSourceDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefStatusInSourceDO.configLanguageCodeKey is not valid");
			}
		}

		theRefStatusInSourceDO.setCreatedTs(new Date());
		theRefStatusInSourceDO.setUpdatedTs(new Date());
		theRefStatusInSourceDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefStatusInSourceDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefStatusInSourceDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefStatusInSourceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusInSourceDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefStatusInSourceDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefStatusInSourceDO.version should not be null");
		}

		RefStatusInSourceDO theRefStatusInSourceDO = (RefStatusInSourceDO) txnTransferObj.getTxnPayload()
				.getRefStatusInSourceDO();
		if (null == theRefStatusInSourceDO.getKey() || theRefStatusInSourceDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefStatusInSourceDO.key should not be null");
		}

		if (null == theRefStatusInSourceDO.getConfigLanguageCodeKey()
				|| theRefStatusInSourceDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStatusInSourceDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefStatusInSourceDO.getValue() || theRefStatusInSourceDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefStatusInSourceDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefStatusInSourceDO.getConfigLanguageCodeKey()
				|| theRefStatusInSourceDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefStatusInSourceDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefStatusInSourceDO.configLanguageCodeKey is not valid");
			}
		}

		theRefStatusInSourceDO.setUpdatedTs(new Date());
		theRefStatusInSourceDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefStatusInSourceDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefStatusInSourceDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefStatusInSourceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusInSourceDO is needed in the request");
		}
		RefStatusInSourceDO theRefStatusInSourceDO = (RefStatusInSourceDO) txnTransferObj.getTxnPayload()
				.getRefStatusInSourceDO();
		if (null == theRefStatusInSourceDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefStatusInSourceDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefStatusInSourceDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefStatusInSourceDO> pageRefStatusInSourceDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefStatusInSourceDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefStatusInSource reference list does not have any records in the database");
			}

			if ((pageRefStatusInSourceDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefStatusInSource, total number of pages is "
								+ pageRefStatusInSourceDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefStatusInSourceDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefStatusInSourceDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefStatusInSourceDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefStatusInSourceDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefStatusInSourceDO.getSize());

			List<RefStatusInSourceDO> dbimageRefStatusInSourceDOlist = pageRefStatusInSourceDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefStatusInSourceDO> clonedRefStatusInSourceDOList = null;
			if (null != dbimageRefStatusInSourceDOlist && dbimageRefStatusInSourceDOlist.size() > 0) {
				clonedRefStatusInSourceDOList = new ArrayList<RefStatusInSourceDO>();
				Iterator<RefStatusInSourceDO> itr = dbimageRefStatusInSourceDOlist.iterator();
				while (itr.hasNext()) {
					RefStatusInSourceDO theRefStatusInSourceDO = new RefStatusInSourceDO(itr.next());
					clonedRefStatusInSourceDOList.add(theRefStatusInSourceDO);
				}
			}

			if (null == clonedRefStatusInSourceDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefStatusInSource reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefStatusInSourceDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefStatusInSource reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefStatusInSourceDOList(clonedRefStatusInSourceDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusInSourceComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFSTATUSINSOURCE_BUSKEY")
	public Page<RefStatusInSourceDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefStatusInSourceDO> pageRefStatusInSourceDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefStatusInSourceDO = this.theRefStatusInSourceRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefStatusInSourceDO = this.theRefStatusInSourceRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefStatusInSourceDO = this.theRefStatusInSourceRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefStatusInSourceDO;
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
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefStatusInSourceDO reqRefStatusInSourceDO = (RefStatusInSourceDO) txnTransferObj.getTxnPayload()
					.getRefStatusInSourceDO();
			theRefStatusInSourceComponentRule.preValidateRefStatusInSourcefindByBusinessKey(txnTransferObj);
			theRefStatusInSourceComponentRule.preExecuteRefStatusInSourcefindByBusinessKey(txnTransferObj);

			RefStatusInSourceDO dbimageRefStatusInSourceDO = executeRepositoryQuery(
					reqRefStatusInSourceDO.getConfigLanguageCodeKey(), reqRefStatusInSourceDO.getKey(),
					reqRefStatusInSourceDO.getInquiryFilter());

			if (null == dbimageRefStatusInSourceDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefStatusInSourceComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefStatusInSourceDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefStatusInSourceDO(new RefStatusInSourceDO(dbimageRefStatusInSourceDO));
			}

			theRefStatusInSourceComponentRule.postExecuteRefStatusInSourcefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusInSourceComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefStatusInSourceDO returns the populated RefStatusInSourceDO object
	 */
	@CacheResult(cacheName = "REFSTATUSINSOURCE_BUSKEY")
	public RefStatusInSourceDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefStatusInSourceDO dbimageRefStatusInSourceDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefStatusInSourceDO = this.theRefStatusInSourceRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefStatusInSourceDO = this.theRefStatusInSourceRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefStatusInSourceDO = this.theRefStatusInSourceRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefStatusInSourceDO;
	}

	/**
	 * perform the common validation that RefStatusInSourceDO,
	 * RefStatusInSourceDO.configLanguageCodeKey and RefStatusInSourceDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefStatusInSourceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusInSourceDO is needed in the request");
		}
		RefStatusInSourceDO theRefStatusInSourceDO = (RefStatusInSourceDO) txnTransferObj.getTxnPayload()
				.getRefStatusInSourceDO();
		if (null == theRefStatusInSourceDO.getKey() || theRefStatusInSourceDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefStatusInSourceDO.key should not be null");
		}

		if (null == theRefStatusInSourceDO.getConfigLanguageCodeKey()
				|| theRefStatusInSourceDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStatusInSourceDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefStatusInSourceDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefStatusInSourceDO and
	 * RefStatusInSourceDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusInSourceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefStatusInSourceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusInSourceDO is needed in the request");
		}
		RefStatusInSourceDO theRefStatusInSourceDO = (RefStatusInSourceDO) txnTransferObj.getTxnPayload()
				.getRefStatusInSourceDO();

		if (null == theRefStatusInSourceDO.getConfigLanguageCodeKey()
				|| theRefStatusInSourceDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStatusInSourceDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefStatusInSourceDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefStatusInSourceDO().getInquiryFilter());
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
