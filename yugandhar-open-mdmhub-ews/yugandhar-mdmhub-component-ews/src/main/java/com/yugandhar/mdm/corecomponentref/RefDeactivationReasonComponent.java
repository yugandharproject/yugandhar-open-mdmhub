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
import com.yugandhar.mdm.extern.dobj.RefDeactivationReasonDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefDeactivationReasonDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefDeactivationReasonComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefDeactivationReasonRepository theRefDeactivationReasonRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefDeactivationReasonComponentRule theRefDeactivationReasonComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefDeactivationReasonComponent() {
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
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefDeactivationReasonComponentRule.prevalidateRefDeactivationReasonCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefDeactivationReasonDO reqRefDeactivationReasonDO = (RefDeactivationReasonDO) txnTransferObj
					.getTxnPayload().getRefDeactivationReasonDO();
			if (null == reqRefDeactivationReasonDO.getPrimaryKeyDO()
					|| null == reqRefDeactivationReasonDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefDeactivationReasonDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefDeactivationReasonDO.setIdPk(reqRefDeactivationReasonDO.getPrimaryKeyDO().getIdPk());
				RefDeactivationReasonDO dbimageRefDeactivationReasonDO = entityManager
						.find(RefDeactivationReasonDO.class, reqRefDeactivationReasonDO.getIdPk());
				if (null != dbimageRefDeactivationReasonDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefDeactivationReasonComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefDeactivationReasonComponentRule.preExecuteRefDeactivationReasonCompPersist(reqRefDeactivationReasonDO,
					txnTransferObj);
			entityManager.persist(reqRefDeactivationReasonDO);
			entityManager.flush();
			reqRefDeactivationReasonDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefDeactivationReasonDO(new RefDeactivationReasonDO(reqRefDeactivationReasonDO));
			theRefDeactivationReasonComponentRule.postExecuteRefDeactivationReasonCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefDeactivationReasonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefDeactivationReasonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefDeactivationReasonComponent.persist failed unexpectedly");
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
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefDeactivationReasonComponentRule.PrevalidateRefDeactivationReasonCompMerge(txnTransferObj);
			RefDeactivationReasonDO reqRefDeactivationReasonDO = (RefDeactivationReasonDO) txnTransferObj
					.getTxnPayload().getRefDeactivationReasonDO();
			RefDeactivationReasonDO dbimageRefDeactivationReasonDO = entityManager.find(RefDeactivationReasonDO.class,
					reqRefDeactivationReasonDO.getIdPk());
			if (null == dbimageRefDeactivationReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefDeactivationReasonComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefDeactivationReasonDO);
			BeanUtils.copyProperties(reqRefDeactivationReasonDO, dbimageRefDeactivationReasonDO, strIgnoreProperties);
			entityManager.detach(dbimageRefDeactivationReasonDO);
			theRefDeactivationReasonComponentRule.preExecuteRefDeactivationReasonCompMerge(reqRefDeactivationReasonDO,
					dbimageRefDeactivationReasonDO, txnTransferObj);
			RefDeactivationReasonDO mergedRefDeactivationReasonDO = entityManager.merge(dbimageRefDeactivationReasonDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefDeactivationReasonDO(new RefDeactivationReasonDO(mergedRefDeactivationReasonDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefDeactivationReasonComponentRule.postExecuteRefDeactivationReasonCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefDeactivationReasonComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefDeactivationReasonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefDeactivationReasonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefDeactivationReasonComponent.merge failed unexpectedly");
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
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefDeactivationReasonComponentRule.prevalidateRefDeactivationReasonCompFindById(txnTransferObj);
			RefDeactivationReasonDO reqRefDeactivationReasonDO = (RefDeactivationReasonDO) txnTransferObj
					.getTxnPayload().getRefDeactivationReasonDO();
			RefDeactivationReasonDO dbimageRefDeactivationReasonDO = entityManager.find(RefDeactivationReasonDO.class,
					reqRefDeactivationReasonDO.getIdPk());
			if (null == dbimageRefDeactivationReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefDeactivationReasonComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefDeactivationReasonDO(new RefDeactivationReasonDO(dbimageRefDeactivationReasonDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefDeactivationReasonComponentRule.postExecuteRefDeactivationReasonCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefDeactivationReasonComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefDeactivationReasonDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefDeactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefDeactivationReasonDO is needed in the request");
		}

		RefDeactivationReasonDO theRefDeactivationReasonDO = (RefDeactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefDeactivationReasonDO();
		if (null == theRefDeactivationReasonDO.getKey() || theRefDeactivationReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefDeactivationReasonDO.key should not be null");
		}

		if (null == theRefDeactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefDeactivationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefDeactivationReasonDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefDeactivationReasonDO.getValue() || theRefDeactivationReasonDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefDeactivationReasonDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefDeactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefDeactivationReasonDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefDeactivationReasonDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefDeactivationReasonDO.configLanguageCodeKey is not valid");
			}
		}

		theRefDeactivationReasonDO.setCreatedTs(new Date());
		theRefDeactivationReasonDO.setUpdatedTs(new Date());
		theRefDeactivationReasonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefDeactivationReasonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefDeactivationReasonDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefDeactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefDeactivationReasonDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefDeactivationReasonDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefDeactivationReasonDO.version should not be null");
		}

		RefDeactivationReasonDO theRefDeactivationReasonDO = (RefDeactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefDeactivationReasonDO();
		if (null == theRefDeactivationReasonDO.getKey() || theRefDeactivationReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefDeactivationReasonDO.key should not be null");
		}

		if (null == theRefDeactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefDeactivationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefDeactivationReasonDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefDeactivationReasonDO.getValue() || theRefDeactivationReasonDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefDeactivationReasonDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefDeactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefDeactivationReasonDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefDeactivationReasonDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefDeactivationReasonDO.configLanguageCodeKey is not valid");
			}
		}

		theRefDeactivationReasonDO.setUpdatedTs(new Date());
		theRefDeactivationReasonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefDeactivationReasonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefDeactivationReasonDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefDeactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefDeactivationReasonDO is needed in the request");
		}
		RefDeactivationReasonDO theRefDeactivationReasonDO = (RefDeactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefDeactivationReasonDO();
		if (null == theRefDeactivationReasonDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefDeactivationReasonDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefDeactivationReasonDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefDeactivationReasonDO> pageRefDeactivationReasonDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefDeactivationReasonDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefDeactivationReason reference list does not have any records in the database");
			}

			if ((pageRefDeactivationReasonDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefDeactivationReason, total number of pages is "
								+ pageRefDeactivationReasonDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefDeactivationReasonDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefDeactivationReasonDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefDeactivationReasonDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefDeactivationReasonDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefDeactivationReasonDO.getSize());

			List<RefDeactivationReasonDO> dbimageRefDeactivationReasonDOlist = pageRefDeactivationReasonDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefDeactivationReasonDO> clonedRefDeactivationReasonDOList = null;
			if (null != dbimageRefDeactivationReasonDOlist && dbimageRefDeactivationReasonDOlist.size() > 0) {
				clonedRefDeactivationReasonDOList = new ArrayList<RefDeactivationReasonDO>();
				Iterator<RefDeactivationReasonDO> itr = dbimageRefDeactivationReasonDOlist.iterator();
				while (itr.hasNext()) {
					RefDeactivationReasonDO theRefDeactivationReasonDO = new RefDeactivationReasonDO(itr.next());
					clonedRefDeactivationReasonDOList.add(theRefDeactivationReasonDO);
				}
			}

			if (null == clonedRefDeactivationReasonDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefDeactivationReason reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefDeactivationReasonDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefDeactivationReason reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefDeactivationReasonDOList(clonedRefDeactivationReasonDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefDeactivationReasonComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFDEACTIVATIONREASON_BUSKEY")
	public Page<RefDeactivationReasonDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefDeactivationReasonDO> pageRefDeactivationReasonDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefDeactivationReasonDO = this.theRefDeactivationReasonRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefDeactivationReasonDO = this.theRefDeactivationReasonRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefDeactivationReasonDO = this.theRefDeactivationReasonRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefDeactivationReasonDO;
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
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefDeactivationReasonDO reqRefDeactivationReasonDO = (RefDeactivationReasonDO) txnTransferObj
					.getTxnPayload().getRefDeactivationReasonDO();
			theRefDeactivationReasonComponentRule.preValidateRefDeactivationReasonfindByBusinessKey(txnTransferObj);
			theRefDeactivationReasonComponentRule.preExecuteRefDeactivationReasonfindByBusinessKey(txnTransferObj);

			RefDeactivationReasonDO dbimageRefDeactivationReasonDO = executeRepositoryQuery(
					reqRefDeactivationReasonDO.getConfigLanguageCodeKey(), reqRefDeactivationReasonDO.getKey(),
					reqRefDeactivationReasonDO.getInquiryFilter());

			if (null == dbimageRefDeactivationReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefDeactivationReasonComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefDeactivationReasonDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefDeactivationReasonDO(new RefDeactivationReasonDO(dbimageRefDeactivationReasonDO));
			}

			theRefDeactivationReasonComponentRule.postExecuteRefDeactivationReasonfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefDeactivationReasonComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefDeactivationReasonDO returns the populated RefDeactivationReasonDO object
	 */
	@CacheResult(cacheName = "REFDEACTIVATIONREASON_BUSKEY")
	public RefDeactivationReasonDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefDeactivationReasonDO dbimageRefDeactivationReasonDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefDeactivationReasonDO = this.theRefDeactivationReasonRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefDeactivationReasonDO = this.theRefDeactivationReasonRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefDeactivationReasonDO = this.theRefDeactivationReasonRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefDeactivationReasonDO;
	}

	/**
	 * perform the common validation that RefDeactivationReasonDO,
	 * RefDeactivationReasonDO.configLanguageCodeKey and RefDeactivationReasonDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefDeactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefDeactivationReasonDO is needed in the request");
		}
		RefDeactivationReasonDO theRefDeactivationReasonDO = (RefDeactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefDeactivationReasonDO();
		if (null == theRefDeactivationReasonDO.getKey() || theRefDeactivationReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefDeactivationReasonDO.key should not be null");
		}

		if (null == theRefDeactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefDeactivationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefDeactivationReasonDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefDeactivationReasonDO and
	 * RefDeactivationReasonDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefDeactivationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefDeactivationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefDeactivationReasonDO is needed in the request");
		}
		RefDeactivationReasonDO theRefDeactivationReasonDO = (RefDeactivationReasonDO) txnTransferObj.getTxnPayload()
				.getRefDeactivationReasonDO();

		if (null == theRefDeactivationReasonDO.getConfigLanguageCodeKey()
				|| theRefDeactivationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefDeactivationReasonDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefDeactivationReasonDO().getInquiryFilter());
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
