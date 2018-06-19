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
import com.yugandhar.mdm.extern.dobj.RefMergeReasonDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefMergeReasonDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 */

@Scope(value = "prototype")
@Component
public class RefMergeReasonComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefMergeReasonRepository theRefMergeReasonRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefMergeReasonComponentRule theRefMergeReasonComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefMergeReasonComponent() {
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
	 *             if RefMergeReasonDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefMergeReasonComponentRule.prevalidateRefMergeReasonCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefMergeReasonDO reqRefMergeReasonDO = (RefMergeReasonDO) txnTransferObj.getTxnPayload()
					.getRefMergeReasonDO();
			if (null == reqRefMergeReasonDO.getPrimaryKeyDO()
					|| null == reqRefMergeReasonDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefMergeReasonDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefMergeReasonDO.setIdPk(reqRefMergeReasonDO.getPrimaryKeyDO().getIdPk());
				RefMergeReasonDO dbimageRefMergeReasonDO = entityManager.find(RefMergeReasonDO.class,
						reqRefMergeReasonDO.getIdPk());
				if (null != dbimageRefMergeReasonDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefMergeReasonComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefMergeReasonComponentRule.preExecuteRefMergeReasonCompPersist(reqRefMergeReasonDO, txnTransferObj);
			entityManager.persist(reqRefMergeReasonDO);
			entityManager.flush();
			reqRefMergeReasonDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefMergeReasonDO(new RefMergeReasonDO(reqRefMergeReasonDO));
			theRefMergeReasonComponentRule.postExecuteRefMergeReasonCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMergeReasonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMergeReasonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMergeReasonComponent.persist failed unexpectedly");
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
	 *             if RefMergeReasonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefMergeReasonComponentRule.PrevalidateRefMergeReasonCompMerge(txnTransferObj);
			RefMergeReasonDO reqRefMergeReasonDO = (RefMergeReasonDO) txnTransferObj.getTxnPayload()
					.getRefMergeReasonDO();
			RefMergeReasonDO dbimageRefMergeReasonDO = entityManager.find(RefMergeReasonDO.class,
					reqRefMergeReasonDO.getIdPk());
			if (null == dbimageRefMergeReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMergeReasonComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefMergeReasonDO);
			BeanUtils.copyProperties(reqRefMergeReasonDO, dbimageRefMergeReasonDO, strIgnoreProperties);
			entityManager.detach(dbimageRefMergeReasonDO);
			theRefMergeReasonComponentRule.preExecuteRefMergeReasonCompMerge(reqRefMergeReasonDO,
					dbimageRefMergeReasonDO, txnTransferObj);
			RefMergeReasonDO mergedRefMergeReasonDO = entityManager.merge(dbimageRefMergeReasonDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefMergeReasonDO(new RefMergeReasonDO(mergedRefMergeReasonDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMergeReasonComponentRule.postExecuteRefMergeReasonCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefMergeReasonComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMergeReasonComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMergeReasonComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMergeReasonComponent.merge failed unexpectedly");
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
	 *             if RefMergeReasonDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefMergeReasonComponentRule.prevalidateRefMergeReasonCompFindById(txnTransferObj);
			RefMergeReasonDO reqRefMergeReasonDO = (RefMergeReasonDO) txnTransferObj.getTxnPayload()
					.getRefMergeReasonDO();
			RefMergeReasonDO dbimageRefMergeReasonDO = entityManager.find(RefMergeReasonDO.class,
					reqRefMergeReasonDO.getIdPk());
			if (null == dbimageRefMergeReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMergeReasonComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefMergeReasonDO(new RefMergeReasonDO(dbimageRefMergeReasonDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMergeReasonComponentRule.postExecuteRefMergeReasonCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMergeReasonComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefMergeReasonDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMergeReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMergeReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMergeReasonDO is needed in the request");
		}

		RefMergeReasonDO theRefMergeReasonDO = (RefMergeReasonDO) txnTransferObj.getTxnPayload().getRefMergeReasonDO();
		if (null == theRefMergeReasonDO.getKey() || theRefMergeReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMergeReasonDO.key should not be null");
		}

		if (null == theRefMergeReasonDO.getConfigLanguageCodeKey()
				|| theRefMergeReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMergeReasonDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefMergeReasonDO.getValue() || theRefMergeReasonDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefMergeReasonDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefMergeReasonDO.getConfigLanguageCodeKey()
				|| theRefMergeReasonDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefMergeReasonDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefMergeReasonDO.configLanguageCodeKey is not valid");
			}
		}

		theRefMergeReasonDO.setCreatedTs(new Date());
		theRefMergeReasonDO.setUpdatedTs(new Date());
		theRefMergeReasonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMergeReasonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefMergeReasonDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMergeReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMergeReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMergeReasonDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefMergeReasonDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefMergeReasonDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMergeReasonDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefMergeReasonDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefMergeReasonDO.version should not be null");
		}

		RefMergeReasonDO theRefMergeReasonDO = (RefMergeReasonDO) txnTransferObj.getTxnPayload().getRefMergeReasonDO();
		if (null == theRefMergeReasonDO.getKey() || theRefMergeReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMergeReasonDO.key should not be null");
		}

		if (null == theRefMergeReasonDO.getConfigLanguageCodeKey()
				|| theRefMergeReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMergeReasonDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefMergeReasonDO.getValue() || theRefMergeReasonDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefMergeReasonDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefMergeReasonDO.getConfigLanguageCodeKey()
				|| theRefMergeReasonDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefMergeReasonDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefMergeReasonDO.configLanguageCodeKey is not valid");
			}
		}

		theRefMergeReasonDO.setUpdatedTs(new Date());
		theRefMergeReasonDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMergeReasonDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefMergeReasonDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMergeReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMergeReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMergeReasonDO is needed in the request");
		}
		RefMergeReasonDO theRefMergeReasonDO = (RefMergeReasonDO) txnTransferObj.getTxnPayload().getRefMergeReasonDO();
		if (null == theRefMergeReasonDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMergeReasonDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefMergeReasonDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMergeReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefMergeReasonDO> pageRefMergeReasonDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefMergeReasonDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefMergeReasonDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefMergeReasonDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMergeReason reference list does not have any records in the database");
			}

			if ((pageRefMergeReasonDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefMergeReason, total number of pages is "
								+ pageRefMergeReasonDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefMergeReasonDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefMergeReasonDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefMergeReasonDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefMergeReasonDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefMergeReasonDO.getSize());

			List<RefMergeReasonDO> dbimageRefMergeReasonDOlist = pageRefMergeReasonDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefMergeReasonDO> clonedRefMergeReasonDOList = null;
			if (null != dbimageRefMergeReasonDOlist && dbimageRefMergeReasonDOlist.size() > 0) {
				clonedRefMergeReasonDOList = new ArrayList<RefMergeReasonDO>();
				Iterator<RefMergeReasonDO> itr = dbimageRefMergeReasonDOlist.iterator();
				while (itr.hasNext()) {
					RefMergeReasonDO theRefMergeReasonDO = new RefMergeReasonDO(itr.next());
					clonedRefMergeReasonDOList.add(theRefMergeReasonDO);
				}
			}

			if (null == clonedRefMergeReasonDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMergeReason reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefMergeReasonDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMergeReason reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefMergeReasonDOList(clonedRefMergeReasonDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMergeReasonComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFMERGEREASON_BUSKEY")
	public Page<RefMergeReasonDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefMergeReasonDO> pageRefMergeReasonDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefMergeReasonDO = this.theRefMergeReasonRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefMergeReasonDO = this.theRefMergeReasonRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefMergeReasonDO = this.theRefMergeReasonRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefMergeReasonDO;
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
	 *             if RefMergeReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefMergeReasonDO reqRefMergeReasonDO = (RefMergeReasonDO) txnTransferObj.getTxnPayload()
					.getRefMergeReasonDO();
			theRefMergeReasonComponentRule.preValidateRefMergeReasonfindByBusinessKey(txnTransferObj);
			theRefMergeReasonComponentRule.preExecuteRefMergeReasonfindByBusinessKey(txnTransferObj);

			RefMergeReasonDO dbimageRefMergeReasonDO = executeRepositoryQuery(
					reqRefMergeReasonDO.getConfigLanguageCodeKey(), reqRefMergeReasonDO.getKey(),
					reqRefMergeReasonDO.getInquiryFilter());

			if (null == dbimageRefMergeReasonDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMergeReasonComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefMergeReasonDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefMergeReasonDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefMergeReasonDO(new RefMergeReasonDO(dbimageRefMergeReasonDO));
			}

			theRefMergeReasonComponentRule.postExecuteRefMergeReasonfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMergeReasonComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefMergeReasonDO returns the populated RefMergeReasonDO object
	 */
	@CacheResult(cacheName = "REFMERGEREASON_BUSKEY")
	public RefMergeReasonDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefMergeReasonDO dbimageRefMergeReasonDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefMergeReasonDO = this.theRefMergeReasonRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefMergeReasonDO = this.theRefMergeReasonRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefMergeReasonDO = this.theRefMergeReasonRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefMergeReasonDO;
	}

	/**
	 * perform the common validation that RefMergeReasonDO,
	 * RefMergeReasonDO.configLanguageCodeKey and RefMergeReasonDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMergeReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMergeReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMergeReasonDO is needed in the request");
		}
		RefMergeReasonDO theRefMergeReasonDO = (RefMergeReasonDO) txnTransferObj.getTxnPayload().getRefMergeReasonDO();
		if (null == theRefMergeReasonDO.getKey() || theRefMergeReasonDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMergeReasonDO.key should not be null");
		}

		if (null == theRefMergeReasonDO.getConfigLanguageCodeKey()
				|| theRefMergeReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMergeReasonDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMergeReasonDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMergeReasonDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMergeReasonDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMergeReasonDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefMergeReasonDO and
	 * RefMergeReasonDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMergeReasonDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMergeReasonDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMergeReasonDO is needed in the request");
		}
		RefMergeReasonDO theRefMergeReasonDO = (RefMergeReasonDO) txnTransferObj.getTxnPayload().getRefMergeReasonDO();

		if (null == theRefMergeReasonDO.getConfigLanguageCodeKey()
				|| theRefMergeReasonDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMergeReasonDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMergeReasonDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMergeReasonDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMergeReasonDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMergeReasonDO().getInquiryFilter());
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
