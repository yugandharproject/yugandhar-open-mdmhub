package com.yugandhar.mdm.batch.componentref;
/* Generated Dec 12, 2017 6:26:11 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.RefBatchActionStatusDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefBatchActionStatusDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefBatchActionStatusComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefBatchActionStatusRepository theRefBatchActionStatusRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefBatchActionStatusComponentRule theRefBatchActionStatusComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefBatchActionStatusComponent() {
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
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefBatchActionStatusComponentRule.prevalidateRefBatchActionStatusCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefBatchActionStatusDO reqRefBatchActionStatusDO = (RefBatchActionStatusDO) txnTransferObj.getTxnPayload()
					.getRefBatchActionStatusDO();
			if (null == reqRefBatchActionStatusDO.getPrimaryKeyDO()
					|| null == reqRefBatchActionStatusDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefBatchActionStatusDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefBatchActionStatusDO.setIdPk(reqRefBatchActionStatusDO.getPrimaryKeyDO().getIdPk());
				RefBatchActionStatusDO dbimageRefBatchActionStatusDO = entityManager.find(RefBatchActionStatusDO.class,
						reqRefBatchActionStatusDO.getIdPk());
				if (null != dbimageRefBatchActionStatusDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefBatchActionStatusComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefBatchActionStatusComponentRule.preExecuteRefBatchActionStatusCompPersist(reqRefBatchActionStatusDO,
					txnTransferObj);
			entityManager.persist(reqRefBatchActionStatusDO);
			entityManager.flush();
			reqRefBatchActionStatusDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefBatchActionStatusDO(new RefBatchActionStatusDO(reqRefBatchActionStatusDO));
			theRefBatchActionStatusComponentRule.postExecuteRefBatchActionStatusCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefBatchActionStatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefBatchActionStatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchActionStatusComponent.persist failed unexpectedly");
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
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefBatchActionStatusComponentRule.PrevalidateRefBatchActionStatusCompMerge(txnTransferObj);
			RefBatchActionStatusDO reqRefBatchActionStatusDO = (RefBatchActionStatusDO) txnTransferObj.getTxnPayload()
					.getRefBatchActionStatusDO();
			RefBatchActionStatusDO dbimageRefBatchActionStatusDO = entityManager.find(RefBatchActionStatusDO.class,
					reqRefBatchActionStatusDO.getIdPk());
			if (null == dbimageRefBatchActionStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBatchActionStatusComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefBatchActionStatusDO);
			BeanUtils.copyProperties(reqRefBatchActionStatusDO, dbimageRefBatchActionStatusDO, strIgnoreProperties);
			entityManager.detach(dbimageRefBatchActionStatusDO);
			theRefBatchActionStatusComponentRule.preExecuteRefBatchActionStatusCompMerge(reqRefBatchActionStatusDO,
					dbimageRefBatchActionStatusDO, txnTransferObj);
			RefBatchActionStatusDO mergedRefBatchActionStatusDO = entityManager.merge(dbimageRefBatchActionStatusDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefBatchActionStatusDO(new RefBatchActionStatusDO(mergedRefBatchActionStatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefBatchActionStatusComponentRule.postExecuteRefBatchActionStatusCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefBatchActionStatusComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefBatchActionStatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefBatchActionStatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchActionStatusComponent.merge failed unexpectedly");
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
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefBatchActionStatusComponentRule.prevalidateRefBatchActionStatusCompFindById(txnTransferObj);
			RefBatchActionStatusDO reqRefBatchActionStatusDO = (RefBatchActionStatusDO) txnTransferObj.getTxnPayload()
					.getRefBatchActionStatusDO();
			RefBatchActionStatusDO dbimageRefBatchActionStatusDO = entityManager.find(RefBatchActionStatusDO.class,
					reqRefBatchActionStatusDO.getIdPk());
			if (null == dbimageRefBatchActionStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBatchActionStatusComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefBatchActionStatusDO(new RefBatchActionStatusDO(dbimageRefBatchActionStatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefBatchActionStatusComponentRule.postExecuteRefBatchActionStatusCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchActionStatusComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefBatchActionStatusDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefBatchActionStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchActionStatusDO is needed in the request");
		}

		RefBatchActionStatusDO theRefBatchActionStatusDO = (RefBatchActionStatusDO) txnTransferObj.getTxnPayload()
				.getRefBatchActionStatusDO();
		if (null == theRefBatchActionStatusDO.getKey() || theRefBatchActionStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBatchActionStatusDO.key should not be null");
		}

		if (null == theRefBatchActionStatusDO.getConfigLanguageCodeKey()
				|| theRefBatchActionStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBatchActionStatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefBatchActionStatusDO.getValue() || theRefBatchActionStatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefBatchActionStatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefBatchActionStatusDO.getConfigLanguageCodeKey()
				|| theRefBatchActionStatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefBatchActionStatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefBatchActionStatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefBatchActionStatusDO.setCreatedTs(new Date());
		theRefBatchActionStatusDO.setUpdatedTs(new Date());
		theRefBatchActionStatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefBatchActionStatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefBatchActionStatusDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBatchActionStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchActionStatusDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefBatchActionStatusDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefBatchActionStatusDO.version should not be null");
		}

		RefBatchActionStatusDO theRefBatchActionStatusDO = (RefBatchActionStatusDO) txnTransferObj.getTxnPayload()
				.getRefBatchActionStatusDO();
		if (null == theRefBatchActionStatusDO.getKey() || theRefBatchActionStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBatchActionStatusDO.key should not be null");
		}

		if (null == theRefBatchActionStatusDO.getConfigLanguageCodeKey()
				|| theRefBatchActionStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBatchActionStatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefBatchActionStatusDO.getValue() || theRefBatchActionStatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefBatchActionStatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefBatchActionStatusDO.getConfigLanguageCodeKey()
				|| theRefBatchActionStatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefBatchActionStatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefBatchActionStatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefBatchActionStatusDO.setUpdatedTs(new Date());
		theRefBatchActionStatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefBatchActionStatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefBatchActionStatusDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefBatchActionStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchActionStatusDO is needed in the request");
		}
		RefBatchActionStatusDO theRefBatchActionStatusDO = (RefBatchActionStatusDO) txnTransferObj.getTxnPayload()
				.getRefBatchActionStatusDO();
		if (null == theRefBatchActionStatusDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefBatchActionStatusDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefBatchActionStatusDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefBatchActionStatusDO> pageRefBatchActionStatusDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefBatchActionStatusDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBatchActionStatus reference list does not have any records in the database");
			}

			if ((pageRefBatchActionStatusDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefBatchActionStatus, total number of pages is "
								+ pageRefBatchActionStatusDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefBatchActionStatusDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefBatchActionStatusDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefBatchActionStatusDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefBatchActionStatusDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefBatchActionStatusDO.getSize());

			List<RefBatchActionStatusDO> dbimageRefBatchActionStatusDOlist = pageRefBatchActionStatusDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefBatchActionStatusDO> clonedRefBatchActionStatusDOList = null;
			if (null != dbimageRefBatchActionStatusDOlist && dbimageRefBatchActionStatusDOlist.size() > 0) {
				clonedRefBatchActionStatusDOList = new ArrayList<RefBatchActionStatusDO>();
				Iterator<RefBatchActionStatusDO> itr = dbimageRefBatchActionStatusDOlist.iterator();
				while (itr.hasNext()) {
					RefBatchActionStatusDO theRefBatchActionStatusDO = new RefBatchActionStatusDO(itr.next());
					clonedRefBatchActionStatusDOList.add(theRefBatchActionStatusDO);
				}
			}

			if (null == clonedRefBatchActionStatusDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBatchActionStatus reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefBatchActionStatusDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBatchActionStatus reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefBatchActionStatusDOList(clonedRefBatchActionStatusDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchActionStatusComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFBATCHACTIONSTATUS_BUSKEY")
	public Page<RefBatchActionStatusDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefBatchActionStatusDO> pageRefBatchActionStatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefBatchActionStatusDO = this.theRefBatchActionStatusRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefBatchActionStatusDO = this.theRefBatchActionStatusRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefBatchActionStatusDO = this.theRefBatchActionStatusRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefBatchActionStatusDO;
	}

	/**
	 * This method search the database table records based on
	 * business key (e.g.LanguageCode and Key)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefBatchActionStatusDO reqRefBatchActionStatusDO = (RefBatchActionStatusDO) txnTransferObj.getTxnPayload()
					.getRefBatchActionStatusDO();
			theRefBatchActionStatusComponentRule.preValidateRefBatchActionStatusfindByBusinessKey(txnTransferObj);
			theRefBatchActionStatusComponentRule.preExecuteRefBatchActionStatusfindByBusinessKey(txnTransferObj);

			RefBatchActionStatusDO dbimageRefBatchActionStatusDO = executeRepositoryQuery(
					reqRefBatchActionStatusDO.getConfigLanguageCodeKey(), reqRefBatchActionStatusDO.getKey(),
					reqRefBatchActionStatusDO.getInquiryFilter());

			if (null == dbimageRefBatchActionStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBatchActionStatusComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefBatchActionStatusDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefBatchActionStatusDO(new RefBatchActionStatusDO(dbimageRefBatchActionStatusDO));
			}

			theRefBatchActionStatusComponentRule.postExecuteRefBatchActionStatusfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchActionStatusComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param configLanguageCodeKey config Language Code Key
	 * @param key key
	 * @param filter filter value ACTIVE, INACTIVE or ALL
	 * @return RefBatchActionStatusDO returns the populated RefBatchActionStatusDO object
	 */
	@CacheResult(cacheName = "REFBATCHACTIONSTATUS_BUSKEY")
	public RefBatchActionStatusDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefBatchActionStatusDO dbimageRefBatchActionStatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefBatchActionStatusDO = this.theRefBatchActionStatusRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefBatchActionStatusDO = this.theRefBatchActionStatusRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefBatchActionStatusDO = this.theRefBatchActionStatusRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefBatchActionStatusDO;
	}

	/**
	 * perform the common validation that RefBatchActionStatusDO,
	 * RefBatchActionStatusDO.configLanguageCodeKey and RefBatchActionStatusDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBatchActionStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchActionStatusDO is needed in the request");
		}
		RefBatchActionStatusDO theRefBatchActionStatusDO = (RefBatchActionStatusDO) txnTransferObj.getTxnPayload()
				.getRefBatchActionStatusDO();
		if (null == theRefBatchActionStatusDO.getKey() || theRefBatchActionStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBatchActionStatusDO.key should not be null");
		}

		if (null == theRefBatchActionStatusDO.getConfigLanguageCodeKey()
				|| theRefBatchActionStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBatchActionStatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefBatchActionStatusDO and
	 * RefBatchActionStatusDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchActionStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBatchActionStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchActionStatusDO is needed in the request");
		}
		RefBatchActionStatusDO theRefBatchActionStatusDO = (RefBatchActionStatusDO) txnTransferObj.getTxnPayload()
				.getRefBatchActionStatusDO();

		if (null == theRefBatchActionStatusDO.getConfigLanguageCodeKey()
				|| theRefBatchActionStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBatchActionStatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefBatchActionStatusDO().getInquiryFilter());
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
