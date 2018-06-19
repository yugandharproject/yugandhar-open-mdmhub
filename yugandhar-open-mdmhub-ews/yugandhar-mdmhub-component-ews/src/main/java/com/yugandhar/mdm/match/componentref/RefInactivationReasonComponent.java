package com.yugandhar.mdm.match.componentref;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.RefInactivationReasonDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefInactivationReasonDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefInactivationReasonComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefInactivationReasonRepository theRefInactivationReasonRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefInactivationReasonComponentRule theRefInactivationReasonComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefInactivationReasonComponent() {
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
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefInactivationReasonComponentRule.prevalidateRefInactivationReasonCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefInactivationReasonDO reqRefInactivationReasonDO = (RefInactivationReasonDO) txnTransferObj
					.getTxnPayload().getRefInactivationReasonDO();
			if (null == reqRefInactivationReasonDO.getPrimaryKeyDO()
					|| null == reqRefInactivationReasonDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefInactivationReasonDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefInactivationReasonDO.setIdPk(reqRefInactivationReasonDO.getPrimaryKeyDO().getIdPk());
				RefInactivationReasonDO dbimageRefInactivationReasonDO = entityManager
						.find(RefInactivationReasonDO.class, reqRefInactivationReasonDO.getIdPk());
				if (null != dbimageRefInactivationReasonDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefInactivationReasonComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefInactivationReasonComponentRule.preExecuteRefInactivationReasonCompPersist(reqRefInactivationReasonDO,
					txnTransferObj);
			entityManager.persist(reqRefInactivationReasonDO);
			entityManager.flush();
			reqRefInactivationReasonDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefInactivationReasonDO(new RefInactivationReasonDO(reqRefInactivationReasonDO));
			theRefInactivationReasonComponentRule.postExecuteRefInactivationReasonCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefInactivationReasonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefInactivationReasonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefInactivationReasonComponent.persist failed unexpectedly");
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
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefInactivationReasonComponentRule.PrevalidateRefInactivationReasonCompMerge(txnTransferObj);
			RefInactivationReasonDO reqRefInactivationReasonDO = (RefInactivationReasonDO) txnTransferObj
					.getTxnPayload().getRefInactivationReasonDO();
			RefInactivationReasonDO dbimageRefInactivationReasonDO = entityManager.find(RefInactivationReasonDO.class,
					reqRefInactivationReasonDO.getIdPk());
			if (null == dbimageRefInactivationReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefInactivationReasonComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefInactivationReasonDO);
			BeanUtils.copyProperties(reqRefInactivationReasonDO, dbimageRefInactivationReasonDO, strIgnoreProperties);
			entityManager.detach(dbimageRefInactivationReasonDO);
			theRefInactivationReasonComponentRule.preExecuteRefInactivationReasonCompMerge(reqRefInactivationReasonDO,
					dbimageRefInactivationReasonDO, txnTransferObj);
			RefInactivationReasonDO mergedRefInactivationReasonDO = entityManager.merge(dbimageRefInactivationReasonDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefInactivationReasonDO(new RefInactivationReasonDO(mergedRefInactivationReasonDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefInactivationReasonComponentRule.postExecuteRefInactivationReasonCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefInactivationReasonComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefInactivationReasonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefInactivationReasonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefInactivationReasonComponent.merge failed unexpectedly");
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
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefInactivationReasonComponentRule.prevalidateRefInactivationReasonCompFindById(txnTransferObj);
			RefInactivationReasonDO reqRefInactivationReasonDO = (RefInactivationReasonDO) txnTransferObj
					.getTxnPayload().getRefInactivationReasonDO();
			RefInactivationReasonDO dbimageRefInactivationReasonDO = entityManager.find(RefInactivationReasonDO.class,
					reqRefInactivationReasonDO.getIdPk());
			if (null == dbimageRefInactivationReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefInactivationReasonComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefInactivationReasonDO(new RefInactivationReasonDO(dbimageRefInactivationReasonDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefInactivationReasonComponentRule.postExecuteRefInactivationReasonCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefInactivationReasonComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefInactivationReasonDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefInactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefInactivationReasonDO is needed in the request");
		}

		RefInactivationReasonDO theRefInactivationReasonDO = (RefInactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefInactivationReasonDO();
		if (null == theRefInactivationReasonDO.getKey() || theRefInactivationReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefInactivationReasonDO.key should not be null");
		}

		if (null == theRefInactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefInactivationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefInactivationReasonDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefInactivationReasonDO.getValue() || theRefInactivationReasonDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefInactivationReasonDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefInactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefInactivationReasonDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefInactivationReasonDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefInactivationReasonDO.configLanguageCodeKey is not valid");
			}
		}

		theRefInactivationReasonDO.setCreatedTs(new Date());
		theRefInactivationReasonDO.setUpdatedTs(new Date());
		theRefInactivationReasonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefInactivationReasonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefInactivationReasonDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefInactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefInactivationReasonDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefInactivationReasonDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefInactivationReasonDO.version should not be null");
		}

		RefInactivationReasonDO theRefInactivationReasonDO = (RefInactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefInactivationReasonDO();
		if (null == theRefInactivationReasonDO.getKey() || theRefInactivationReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefInactivationReasonDO.key should not be null");
		}

		if (null == theRefInactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefInactivationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefInactivationReasonDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefInactivationReasonDO.getValue() || theRefInactivationReasonDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefInactivationReasonDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefInactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefInactivationReasonDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefInactivationReasonDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefInactivationReasonDO.configLanguageCodeKey is not valid");
			}
		}

		theRefInactivationReasonDO.setUpdatedTs(new Date());
		theRefInactivationReasonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefInactivationReasonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefInactivationReasonDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefInactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefInactivationReasonDO is needed in the request");
		}
		RefInactivationReasonDO theRefInactivationReasonDO = (RefInactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefInactivationReasonDO();
		if (null == theRefInactivationReasonDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefInactivationReasonDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefInactivationReasonDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefInactivationReasonDO> pageRefInactivationReasonDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefInactivationReasonDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefInactivationReason reference list does not have any records in the database");
			}

			if ((pageRefInactivationReasonDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefInactivationReason, total number of pages is "
								+ pageRefInactivationReasonDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefInactivationReasonDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefInactivationReasonDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefInactivationReasonDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefInactivationReasonDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefInactivationReasonDO.getSize());

			List<RefInactivationReasonDO> dbimageRefInactivationReasonDOlist = pageRefInactivationReasonDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefInactivationReasonDO> clonedRefInactivationReasonDOList = null;
			if (null != dbimageRefInactivationReasonDOlist && dbimageRefInactivationReasonDOlist.size() > 0) {
				clonedRefInactivationReasonDOList = new ArrayList<RefInactivationReasonDO>();
				Iterator<RefInactivationReasonDO> itr = dbimageRefInactivationReasonDOlist.iterator();
				while (itr.hasNext()) {
					RefInactivationReasonDO theRefInactivationReasonDO = new RefInactivationReasonDO(itr.next());
					clonedRefInactivationReasonDOList.add(theRefInactivationReasonDO);
				}
			}

			if (null == clonedRefInactivationReasonDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefInactivationReason reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefInactivationReasonDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefInactivationReason reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefInactivationReasonDOList(clonedRefInactivationReasonDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefInactivationReasonComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFINACTIVATIONREASON_BUSKEY")
	public Page<RefInactivationReasonDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefInactivationReasonDO> pageRefInactivationReasonDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefInactivationReasonDO = this.theRefInactivationReasonRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefInactivationReasonDO = this.theRefInactivationReasonRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefInactivationReasonDO = this.theRefInactivationReasonRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefInactivationReasonDO;
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
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefInactivationReasonDO reqRefInactivationReasonDO = (RefInactivationReasonDO) txnTransferObj
					.getTxnPayload().getRefInactivationReasonDO();
			theRefInactivationReasonComponentRule.preValidateRefInactivationReasonfindByBusinessKey(txnTransferObj);
			theRefInactivationReasonComponentRule.preExecuteRefInactivationReasonfindByBusinessKey(txnTransferObj);

			RefInactivationReasonDO dbimageRefInactivationReasonDO = executeRepositoryQuery(
					reqRefInactivationReasonDO.getConfigLanguageCodeKey(), reqRefInactivationReasonDO.getKey(),
					reqRefInactivationReasonDO.getInquiryFilter());

			if (null == dbimageRefInactivationReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefInactivationReasonComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefInactivationReasonDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefInactivationReasonDO(new RefInactivationReasonDO(dbimageRefInactivationReasonDO));
			}

			theRefInactivationReasonComponentRule.postExecuteRefInactivationReasonfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefInactivationReasonComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param configLanguageCodeKey
	 * @param key
	 * @param filter
	 * @return RefInactivationReasonDO returns the populated RefInactivationReasonDO object
	 */
	@CacheResult(cacheName = "REFINACTIVATIONREASON_BUSKEY")
	public RefInactivationReasonDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefInactivationReasonDO dbimageRefInactivationReasonDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefInactivationReasonDO = this.theRefInactivationReasonRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefInactivationReasonDO = this.theRefInactivationReasonRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefInactivationReasonDO = this.theRefInactivationReasonRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefInactivationReasonDO;
	}

	/**
	 * perform the common validation that RefInactivationReasonDO,
	 * RefInactivationReasonDO.configLanguageCodeKey and RefInactivationReasonDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefInactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefInactivationReasonDO is needed in the request");
		}
		RefInactivationReasonDO theRefInactivationReasonDO = (RefInactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefInactivationReasonDO();
		if (null == theRefInactivationReasonDO.getKey() || theRefInactivationReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefInactivationReasonDO.key should not be null");
		}

		if (null == theRefInactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefInactivationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefInactivationReasonDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefInactivationReasonDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefInactivationReasonDO and
	 * RefInactivationReasonDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefInactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefInactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefInactivationReasonDO is needed in the request");
		}
		RefInactivationReasonDO theRefInactivationReasonDO = (RefInactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefInactivationReasonDO();

		if (null == theRefInactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefInactivationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefInactivationReasonDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefInactivationReasonDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefInactivationReasonDO().getInquiryFilter());
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
