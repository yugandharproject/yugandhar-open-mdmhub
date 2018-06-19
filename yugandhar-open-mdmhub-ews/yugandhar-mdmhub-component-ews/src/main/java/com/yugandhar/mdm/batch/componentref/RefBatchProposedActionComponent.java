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
import com.yugandhar.mdm.extern.dobj.RefBatchProposedActionDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefBatchProposedActionDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 */

@Scope(value = "prototype")
@Component
public class RefBatchProposedActionComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefBatchProposedActionRepository theRefBatchProposedActionRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefBatchProposedActionComponentRule theRefBatchProposedActionComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefBatchProposedActionComponent() {
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
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefBatchProposedActionComponentRule.prevalidateRefBatchProposedActionCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefBatchProposedActionDO reqRefBatchProposedActionDO = (RefBatchProposedActionDO) txnTransferObj
					.getTxnPayload().getRefBatchProposedActionDO();
			if (null == reqRefBatchProposedActionDO.getPrimaryKeyDO()
					|| null == reqRefBatchProposedActionDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefBatchProposedActionDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefBatchProposedActionDO.setIdPk(reqRefBatchProposedActionDO.getPrimaryKeyDO().getIdPk());
				RefBatchProposedActionDO dbimageRefBatchProposedActionDO = entityManager
						.find(RefBatchProposedActionDO.class, reqRefBatchProposedActionDO.getIdPk());
				if (null != dbimageRefBatchProposedActionDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefBatchProposedActionComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefBatchProposedActionComponentRule
					.preExecuteRefBatchProposedActionCompPersist(reqRefBatchProposedActionDO, txnTransferObj);
			entityManager.persist(reqRefBatchProposedActionDO);
			entityManager.flush();
			reqRefBatchProposedActionDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefBatchProposedActionDO(new RefBatchProposedActionDO(reqRefBatchProposedActionDO));
			theRefBatchProposedActionComponentRule.postExecuteRefBatchProposedActionCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefBatchProposedActionComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefBatchProposedActionComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchProposedActionComponent.persist failed unexpectedly");
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
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefBatchProposedActionComponentRule.PrevalidateRefBatchProposedActionCompMerge(txnTransferObj);
			RefBatchProposedActionDO reqRefBatchProposedActionDO = (RefBatchProposedActionDO) txnTransferObj
					.getTxnPayload().getRefBatchProposedActionDO();
			RefBatchProposedActionDO dbimageRefBatchProposedActionDO = entityManager
					.find(RefBatchProposedActionDO.class, reqRefBatchProposedActionDO.getIdPk());
			if (null == dbimageRefBatchProposedActionDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBatchProposedActionComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefBatchProposedActionDO);
			BeanUtils.copyProperties(reqRefBatchProposedActionDO, dbimageRefBatchProposedActionDO, strIgnoreProperties);
			entityManager.detach(dbimageRefBatchProposedActionDO);
			theRefBatchProposedActionComponentRule.preExecuteRefBatchProposedActionCompMerge(
					reqRefBatchProposedActionDO, dbimageRefBatchProposedActionDO, txnTransferObj);
			RefBatchProposedActionDO mergedRefBatchProposedActionDO = entityManager
					.merge(dbimageRefBatchProposedActionDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefBatchProposedActionDO(new RefBatchProposedActionDO(mergedRefBatchProposedActionDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefBatchProposedActionComponentRule.postExecuteRefBatchProposedActionCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefBatchProposedActionComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefBatchProposedActionComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefBatchProposedActionComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchProposedActionComponent.merge failed unexpectedly");
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
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefBatchProposedActionComponentRule.prevalidateRefBatchProposedActionCompFindById(txnTransferObj);
			RefBatchProposedActionDO reqRefBatchProposedActionDO = (RefBatchProposedActionDO) txnTransferObj
					.getTxnPayload().getRefBatchProposedActionDO();
			RefBatchProposedActionDO dbimageRefBatchProposedActionDO = entityManager
					.find(RefBatchProposedActionDO.class, reqRefBatchProposedActionDO.getIdPk());
			if (null == dbimageRefBatchProposedActionDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBatchProposedActionComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefBatchProposedActionDO(new RefBatchProposedActionDO(dbimageRefBatchProposedActionDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefBatchProposedActionComponentRule.postExecuteRefBatchProposedActionCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchProposedActionComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefBatchProposedActionDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefBatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchProposedActionDO is needed in the request");
		}

		RefBatchProposedActionDO theRefBatchProposedActionDO = (RefBatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefBatchProposedActionDO();
		if (null == theRefBatchProposedActionDO.getKey() || theRefBatchProposedActionDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBatchProposedActionDO.key should not be null");
		}

		if (null == theRefBatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefBatchProposedActionDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBatchProposedActionDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefBatchProposedActionDO.getValue() || theRefBatchProposedActionDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefBatchProposedActionDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefBatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefBatchProposedActionDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefBatchProposedActionDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefBatchProposedActionDO.configLanguageCodeKey is not valid");
			}
		}

		theRefBatchProposedActionDO.setCreatedTs(new Date());
		theRefBatchProposedActionDO.setUpdatedTs(new Date());
		theRefBatchProposedActionDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefBatchProposedActionDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefBatchProposedActionDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchProposedActionDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefBatchProposedActionDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefBatchProposedActionDO.version should not be null");
		}

		RefBatchProposedActionDO theRefBatchProposedActionDO = (RefBatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefBatchProposedActionDO();
		if (null == theRefBatchProposedActionDO.getKey() || theRefBatchProposedActionDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBatchProposedActionDO.key should not be null");
		}

		if (null == theRefBatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefBatchProposedActionDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBatchProposedActionDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefBatchProposedActionDO.getValue() || theRefBatchProposedActionDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefBatchProposedActionDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefBatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefBatchProposedActionDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefBatchProposedActionDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefBatchProposedActionDO.configLanguageCodeKey is not valid");
			}
		}

		theRefBatchProposedActionDO.setUpdatedTs(new Date());
		theRefBatchProposedActionDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefBatchProposedActionDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefBatchProposedActionDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefBatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchProposedActionDO is needed in the request");
		}
		RefBatchProposedActionDO theRefBatchProposedActionDO = (RefBatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefBatchProposedActionDO();
		if (null == theRefBatchProposedActionDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefBatchProposedActionDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefBatchProposedActionDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefBatchProposedActionDO> pageRefBatchProposedActionDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefBatchProposedActionDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBatchProposedAction reference list does not have any records in the database");
			}

			if ((pageRefBatchProposedActionDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefBatchProposedAction, total number of pages is "
								+ pageRefBatchProposedActionDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefBatchProposedActionDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefBatchProposedActionDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefBatchProposedActionDO.getTotalElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalPages(pageRefBatchProposedActionDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefBatchProposedActionDO.getSize());

			List<RefBatchProposedActionDO> dbimageRefBatchProposedActionDOlist = pageRefBatchProposedActionDO
					.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefBatchProposedActionDO> clonedRefBatchProposedActionDOList = null;
			if (null != dbimageRefBatchProposedActionDOlist && dbimageRefBatchProposedActionDOlist.size() > 0) {
				clonedRefBatchProposedActionDOList = new ArrayList<RefBatchProposedActionDO>();
				Iterator<RefBatchProposedActionDO> itr = dbimageRefBatchProposedActionDOlist.iterator();
				while (itr.hasNext()) {
					RefBatchProposedActionDO theRefBatchProposedActionDO = new RefBatchProposedActionDO(itr.next());
					clonedRefBatchProposedActionDOList.add(theRefBatchProposedActionDO);
				}
			}

			if (null == clonedRefBatchProposedActionDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBatchProposedAction reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefBatchProposedActionDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBatchProposedAction reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefBatchProposedActionDOList(clonedRefBatchProposedActionDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchProposedActionComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFBATCHPROPOSEDACTION_BUSKEY")
	public Page<RefBatchProposedActionDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefBatchProposedActionDO> pageRefBatchProposedActionDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefBatchProposedActionDO = this.theRefBatchProposedActionRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefBatchProposedActionDO = this.theRefBatchProposedActionRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefBatchProposedActionDO = this.theRefBatchProposedActionRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefBatchProposedActionDO;
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
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefBatchProposedActionDO reqRefBatchProposedActionDO = (RefBatchProposedActionDO) txnTransferObj
					.getTxnPayload().getRefBatchProposedActionDO();
			theRefBatchProposedActionComponentRule.preValidateRefBatchProposedActionfindByBusinessKey(txnTransferObj);
			theRefBatchProposedActionComponentRule.preExecuteRefBatchProposedActionfindByBusinessKey(txnTransferObj);

			RefBatchProposedActionDO dbimageRefBatchProposedActionDO = executeRepositoryQuery(
					reqRefBatchProposedActionDO.getConfigLanguageCodeKey(), reqRefBatchProposedActionDO.getKey(),
					reqRefBatchProposedActionDO.getInquiryFilter());

			if (null == dbimageRefBatchProposedActionDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBatchProposedActionComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefBatchProposedActionDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefBatchProposedActionDO(new RefBatchProposedActionDO(dbimageRefBatchProposedActionDO));
			}

			theRefBatchProposedActionComponentRule
					.postExecuteRefBatchProposedActionfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBatchProposedActionComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param configLanguageCodeKey config language code key
	 * @param key key
	 * @param filter filter ACTIVE, INACTIVE or ALL
	 * @return RefBatchProposedActionDO returns the populated RefBatchProposedActionDO object
	 */
	@CacheResult(cacheName = "REFBATCHPROPOSEDACTION_BUSKEY")
	public RefBatchProposedActionDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefBatchProposedActionDO dbimageRefBatchProposedActionDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefBatchProposedActionDO = this.theRefBatchProposedActionRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefBatchProposedActionDO = this.theRefBatchProposedActionRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefBatchProposedActionDO = this.theRefBatchProposedActionRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefBatchProposedActionDO;
	}

	/**
	 * perform the common validation that RefBatchProposedActionDO,
	 * RefBatchProposedActionDO.configLanguageCodeKey and RefBatchProposedActionDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchProposedActionDO is needed in the request");
		}
		RefBatchProposedActionDO theRefBatchProposedActionDO = (RefBatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefBatchProposedActionDO();
		if (null == theRefBatchProposedActionDO.getKey() || theRefBatchProposedActionDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBatchProposedActionDO.key should not be null");
		}

		if (null == theRefBatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefBatchProposedActionDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBatchProposedActionDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefBatchProposedActionDO and
	 * RefBatchProposedActionDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBatchProposedActionDO is needed in the request");
		}
		RefBatchProposedActionDO theRefBatchProposedActionDO = (RefBatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefBatchProposedActionDO();

		if (null == theRefBatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefBatchProposedActionDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBatchProposedActionDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefBatchProposedActionDO().getInquiryFilter());
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
