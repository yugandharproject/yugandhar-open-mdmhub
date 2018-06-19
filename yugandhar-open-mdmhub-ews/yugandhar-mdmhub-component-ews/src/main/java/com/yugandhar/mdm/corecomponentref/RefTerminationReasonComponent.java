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
import com.yugandhar.mdm.extern.dobj.RefTerminationReasonDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefTerminationReasonDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefTerminationReasonComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefTerminationReasonRepository theRefTerminationReasonRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefTerminationReasonComponentRule theRefTerminationReasonComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefTerminationReasonComponent() {
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
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefTerminationReasonComponentRule.prevalidateRefTerminationReasonCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefTerminationReasonDO reqRefTerminationReasonDO = (RefTerminationReasonDO) txnTransferObj.getTxnPayload()
					.getRefTerminationReasonDO();
			if (null == reqRefTerminationReasonDO.getPrimaryKeyDO()
					|| null == reqRefTerminationReasonDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefTerminationReasonDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefTerminationReasonDO.setIdPk(reqRefTerminationReasonDO.getPrimaryKeyDO().getIdPk());
				RefTerminationReasonDO dbimageRefTerminationReasonDO = entityManager.find(RefTerminationReasonDO.class,
						reqRefTerminationReasonDO.getIdPk());
				if (null != dbimageRefTerminationReasonDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefTerminationReasonComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefTerminationReasonComponentRule.preExecuteRefTerminationReasonCompPersist(reqRefTerminationReasonDO,
					txnTransferObj);
			entityManager.persist(reqRefTerminationReasonDO);
			entityManager.flush();
			reqRefTerminationReasonDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefTerminationReasonDO(new RefTerminationReasonDO(reqRefTerminationReasonDO));
			theRefTerminationReasonComponentRule.postExecuteRefTerminationReasonCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefTerminationReasonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefTerminationReasonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefTerminationReasonComponent.persist failed unexpectedly");
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
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefTerminationReasonComponentRule.PrevalidateRefTerminationReasonCompMerge(txnTransferObj);
			RefTerminationReasonDO reqRefTerminationReasonDO = (RefTerminationReasonDO) txnTransferObj.getTxnPayload()
					.getRefTerminationReasonDO();
			RefTerminationReasonDO dbimageRefTerminationReasonDO = entityManager.find(RefTerminationReasonDO.class,
					reqRefTerminationReasonDO.getIdPk());
			if (null == dbimageRefTerminationReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefTerminationReasonComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefTerminationReasonDO);
			BeanUtils.copyProperties(reqRefTerminationReasonDO, dbimageRefTerminationReasonDO, strIgnoreProperties);
			entityManager.detach(dbimageRefTerminationReasonDO);
			theRefTerminationReasonComponentRule.preExecuteRefTerminationReasonCompMerge(reqRefTerminationReasonDO,
					dbimageRefTerminationReasonDO, txnTransferObj);
			RefTerminationReasonDO mergedRefTerminationReasonDO = entityManager.merge(dbimageRefTerminationReasonDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefTerminationReasonDO(new RefTerminationReasonDO(mergedRefTerminationReasonDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefTerminationReasonComponentRule.postExecuteRefTerminationReasonCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefTerminationReasonComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefTerminationReasonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefTerminationReasonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefTerminationReasonComponent.merge failed unexpectedly");
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
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefTerminationReasonComponentRule.prevalidateRefTerminationReasonCompFindById(txnTransferObj);
			RefTerminationReasonDO reqRefTerminationReasonDO = (RefTerminationReasonDO) txnTransferObj.getTxnPayload()
					.getRefTerminationReasonDO();
			RefTerminationReasonDO dbimageRefTerminationReasonDO = entityManager.find(RefTerminationReasonDO.class,
					reqRefTerminationReasonDO.getIdPk());
			if (null == dbimageRefTerminationReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefTerminationReasonComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefTerminationReasonDO(new RefTerminationReasonDO(dbimageRefTerminationReasonDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefTerminationReasonComponentRule.postExecuteRefTerminationReasonCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefTerminationReasonComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefTerminationReasonDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefTerminationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefTerminationReasonDO is needed in the request");
		}

		RefTerminationReasonDO theRefTerminationReasonDO = (RefTerminationReasonDO) txnTransferObj.getTxnPayload()
				.getRefTerminationReasonDO();
		if (null == theRefTerminationReasonDO.getKey() || theRefTerminationReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefTerminationReasonDO.key should not be null");
		}

		if (null == theRefTerminationReasonDO.getConfigLanguageCodeKey()
				|| theRefTerminationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefTerminationReasonDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefTerminationReasonDO.getValue() || theRefTerminationReasonDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefTerminationReasonDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefTerminationReasonDO.getConfigLanguageCodeKey()
				|| theRefTerminationReasonDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefTerminationReasonDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefTerminationReasonDO.configLanguageCodeKey is not valid");
			}
		}

		theRefTerminationReasonDO.setCreatedTs(new Date());
		theRefTerminationReasonDO.setUpdatedTs(new Date());
		theRefTerminationReasonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefTerminationReasonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefTerminationReasonDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefTerminationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefTerminationReasonDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefTerminationReasonDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefTerminationReasonDO.version should not be null");
		}

		RefTerminationReasonDO theRefTerminationReasonDO = (RefTerminationReasonDO) txnTransferObj.getTxnPayload()
				.getRefTerminationReasonDO();
		if (null == theRefTerminationReasonDO.getKey() || theRefTerminationReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefTerminationReasonDO.key should not be null");
		}

		if (null == theRefTerminationReasonDO.getConfigLanguageCodeKey()
				|| theRefTerminationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefTerminationReasonDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefTerminationReasonDO.getValue() || theRefTerminationReasonDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefTerminationReasonDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefTerminationReasonDO.getConfigLanguageCodeKey()
				|| theRefTerminationReasonDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefTerminationReasonDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefTerminationReasonDO.configLanguageCodeKey is not valid");
			}
		}

		theRefTerminationReasonDO.setUpdatedTs(new Date());
		theRefTerminationReasonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefTerminationReasonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefTerminationReasonDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefTerminationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefTerminationReasonDO is needed in the request");
		}
		RefTerminationReasonDO theRefTerminationReasonDO = (RefTerminationReasonDO) txnTransferObj.getTxnPayload()
				.getRefTerminationReasonDO();
		if (null == theRefTerminationReasonDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefTerminationReasonDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefTerminationReasonDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefTerminationReasonDO> pageRefTerminationReasonDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefTerminationReasonDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefTerminationReason reference list does not have any records in the database");
			}

			if ((pageRefTerminationReasonDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefTerminationReason, total number of pages is "
								+ pageRefTerminationReasonDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefTerminationReasonDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefTerminationReasonDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefTerminationReasonDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefTerminationReasonDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefTerminationReasonDO.getSize());

			List<RefTerminationReasonDO> dbimageRefTerminationReasonDOlist = pageRefTerminationReasonDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefTerminationReasonDO> clonedRefTerminationReasonDOList = null;
			if (null != dbimageRefTerminationReasonDOlist && dbimageRefTerminationReasonDOlist.size() > 0) {
				clonedRefTerminationReasonDOList = new ArrayList<RefTerminationReasonDO>();
				Iterator<RefTerminationReasonDO> itr = dbimageRefTerminationReasonDOlist.iterator();
				while (itr.hasNext()) {
					RefTerminationReasonDO theRefTerminationReasonDO = new RefTerminationReasonDO(itr.next());
					clonedRefTerminationReasonDOList.add(theRefTerminationReasonDO);
				}
			}

			if (null == clonedRefTerminationReasonDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefTerminationReason reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefTerminationReasonDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefTerminationReason reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefTerminationReasonDOList(clonedRefTerminationReasonDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefTerminationReasonComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFTERMINATIONREASON_BUSKEY")
	public Page<RefTerminationReasonDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefTerminationReasonDO> pageRefTerminationReasonDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefTerminationReasonDO = this.theRefTerminationReasonRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefTerminationReasonDO = this.theRefTerminationReasonRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefTerminationReasonDO = this.theRefTerminationReasonRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefTerminationReasonDO;
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
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefTerminationReasonDO reqRefTerminationReasonDO = (RefTerminationReasonDO) txnTransferObj.getTxnPayload()
					.getRefTerminationReasonDO();
			theRefTerminationReasonComponentRule.preValidateRefTerminationReasonfindByBusinessKey(txnTransferObj);
			theRefTerminationReasonComponentRule.preExecuteRefTerminationReasonfindByBusinessKey(txnTransferObj);

			RefTerminationReasonDO dbimageRefTerminationReasonDO = executeRepositoryQuery(
					reqRefTerminationReasonDO.getConfigLanguageCodeKey(), reqRefTerminationReasonDO.getKey(),
					reqRefTerminationReasonDO.getInquiryFilter());

			if (null == dbimageRefTerminationReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefTerminationReasonComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefTerminationReasonDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefTerminationReasonDO(new RefTerminationReasonDO(dbimageRefTerminationReasonDO));
			}

			theRefTerminationReasonComponentRule.postExecuteRefTerminationReasonfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefTerminationReasonComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefTerminationReasonDO returns the populated RefTerminationReasonDO object
	 */
	@CacheResult(cacheName = "REFTERMINATIONREASON_BUSKEY")
	public RefTerminationReasonDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefTerminationReasonDO dbimageRefTerminationReasonDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefTerminationReasonDO = this.theRefTerminationReasonRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefTerminationReasonDO = this.theRefTerminationReasonRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefTerminationReasonDO = this.theRefTerminationReasonRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefTerminationReasonDO;
	}

	/**
	 * perform the common validation that RefTerminationReasonDO,
	 * RefTerminationReasonDO.configLanguageCodeKey and RefTerminationReasonDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefTerminationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefTerminationReasonDO is needed in the request");
		}
		RefTerminationReasonDO theRefTerminationReasonDO = (RefTerminationReasonDO) txnTransferObj.getTxnPayload()
				.getRefTerminationReasonDO();
		if (null == theRefTerminationReasonDO.getKey() || theRefTerminationReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefTerminationReasonDO.key should not be null");
		}

		if (null == theRefTerminationReasonDO.getConfigLanguageCodeKey()
				|| theRefTerminationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefTerminationReasonDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefTerminationReasonDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefTerminationReasonDO and
	 * RefTerminationReasonDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefTerminationReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefTerminationReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefTerminationReasonDO is needed in the request");
		}
		RefTerminationReasonDO theRefTerminationReasonDO = (RefTerminationReasonDO) txnTransferObj.getTxnPayload()
				.getRefTerminationReasonDO();

		if (null == theRefTerminationReasonDO.getConfigLanguageCodeKey()
				|| theRefTerminationReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefTerminationReasonDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefTerminationReasonDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefTerminationReasonDO().getInquiryFilter());
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
