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
import com.yugandhar.mdm.extern.dobj.RefMatchProposedActionDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefMatchProposedActionDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefMatchProposedActionComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefMatchProposedActionRepository theRefMatchProposedActionRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefMatchProposedActionComponentRule theRefMatchProposedActionComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefMatchProposedActionComponent() {
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
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefMatchProposedActionComponentRule.prevalidateRefMatchProposedActionCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefMatchProposedActionDO reqRefMatchProposedActionDO = (RefMatchProposedActionDO) txnTransferObj
					.getTxnPayload().getRefMatchProposedActionDO();
			if (null == reqRefMatchProposedActionDO.getPrimaryKeyDO()
					|| null == reqRefMatchProposedActionDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefMatchProposedActionDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefMatchProposedActionDO.setIdPk(reqRefMatchProposedActionDO.getPrimaryKeyDO().getIdPk());
				RefMatchProposedActionDO dbimageRefMatchProposedActionDO = entityManager
						.find(RefMatchProposedActionDO.class, reqRefMatchProposedActionDO.getIdPk());
				if (null != dbimageRefMatchProposedActionDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefMatchProposedActionComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefMatchProposedActionComponentRule
					.preExecuteRefMatchProposedActionCompPersist(reqRefMatchProposedActionDO, txnTransferObj);
			entityManager.persist(reqRefMatchProposedActionDO);
			entityManager.flush();
			reqRefMatchProposedActionDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefMatchProposedActionDO(new RefMatchProposedActionDO(reqRefMatchProposedActionDO));
			theRefMatchProposedActionComponentRule.postExecuteRefMatchProposedActionCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchProposedActionComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchProposedActionComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchProposedActionComponent.persist failed unexpectedly");
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
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefMatchProposedActionComponentRule.PrevalidateRefMatchProposedActionCompMerge(txnTransferObj);
			RefMatchProposedActionDO reqRefMatchProposedActionDO = (RefMatchProposedActionDO) txnTransferObj
					.getTxnPayload().getRefMatchProposedActionDO();
			RefMatchProposedActionDO dbimageRefMatchProposedActionDO = entityManager
					.find(RefMatchProposedActionDO.class, reqRefMatchProposedActionDO.getIdPk());
			if (null == dbimageRefMatchProposedActionDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchProposedActionComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefMatchProposedActionDO);
			BeanUtils.copyProperties(reqRefMatchProposedActionDO, dbimageRefMatchProposedActionDO, strIgnoreProperties);
			entityManager.detach(dbimageRefMatchProposedActionDO);
			theRefMatchProposedActionComponentRule.preExecuteRefMatchProposedActionCompMerge(
					reqRefMatchProposedActionDO, dbimageRefMatchProposedActionDO, txnTransferObj);
			RefMatchProposedActionDO mergedRefMatchProposedActionDO = entityManager
					.merge(dbimageRefMatchProposedActionDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefMatchProposedActionDO(new RefMatchProposedActionDO(mergedRefMatchProposedActionDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchProposedActionComponentRule.postExecuteRefMatchProposedActionCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefMatchProposedActionComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchProposedActionComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchProposedActionComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchProposedActionComponent.merge failed unexpectedly");
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
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefMatchProposedActionComponentRule.prevalidateRefMatchProposedActionCompFindById(txnTransferObj);
			RefMatchProposedActionDO reqRefMatchProposedActionDO = (RefMatchProposedActionDO) txnTransferObj
					.getTxnPayload().getRefMatchProposedActionDO();
			RefMatchProposedActionDO dbimageRefMatchProposedActionDO = entityManager
					.find(RefMatchProposedActionDO.class, reqRefMatchProposedActionDO.getIdPk());
			if (null == dbimageRefMatchProposedActionDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchProposedActionComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefMatchProposedActionDO(new RefMatchProposedActionDO(dbimageRefMatchProposedActionDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchProposedActionComponentRule.postExecuteRefMatchProposedActionCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchProposedActionComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefMatchProposedActionDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchProposedActionDO is needed in the request");
		}

		RefMatchProposedActionDO theRefMatchProposedActionDO = (RefMatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefMatchProposedActionDO();
		if (null == theRefMatchProposedActionDO.getKey() || theRefMatchProposedActionDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMatchProposedActionDO.key should not be null");
		}

		if (null == theRefMatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefMatchProposedActionDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchProposedActionDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefMatchProposedActionDO.getValue() || theRefMatchProposedActionDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefMatchProposedActionDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefMatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefMatchProposedActionDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefMatchProposedActionDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefMatchProposedActionDO.configLanguageCodeKey is not valid");
			}
		}

		theRefMatchProposedActionDO.setCreatedTs(new Date());
		theRefMatchProposedActionDO.setUpdatedTs(new Date());
		theRefMatchProposedActionDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchProposedActionDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefMatchProposedActionDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchProposedActionDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchProposedActionDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefMatchProposedActionDO.version should not be null");
		}

		RefMatchProposedActionDO theRefMatchProposedActionDO = (RefMatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefMatchProposedActionDO();
		if (null == theRefMatchProposedActionDO.getKey() || theRefMatchProposedActionDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMatchProposedActionDO.key should not be null");
		}

		if (null == theRefMatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefMatchProposedActionDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchProposedActionDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefMatchProposedActionDO.getValue() || theRefMatchProposedActionDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefMatchProposedActionDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefMatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefMatchProposedActionDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefMatchProposedActionDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefMatchProposedActionDO.configLanguageCodeKey is not valid");
			}
		}

		theRefMatchProposedActionDO.setUpdatedTs(new Date());
		theRefMatchProposedActionDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchProposedActionDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefMatchProposedActionDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchProposedActionDO is needed in the request");
		}
		RefMatchProposedActionDO theRefMatchProposedActionDO = (RefMatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefMatchProposedActionDO();
		if (null == theRefMatchProposedActionDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchProposedActionDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefMatchProposedActionDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefMatchProposedActionDO> pageRefMatchProposedActionDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefMatchProposedActionDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchProposedAction reference list does not have any records in the database");
			}

			if ((pageRefMatchProposedActionDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefMatchProposedAction, total number of pages is "
								+ pageRefMatchProposedActionDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefMatchProposedActionDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefMatchProposedActionDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefMatchProposedActionDO.getTotalElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalPages(pageRefMatchProposedActionDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefMatchProposedActionDO.getSize());

			List<RefMatchProposedActionDO> dbimageRefMatchProposedActionDOlist = pageRefMatchProposedActionDO
					.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefMatchProposedActionDO> clonedRefMatchProposedActionDOList = null;
			if (null != dbimageRefMatchProposedActionDOlist && dbimageRefMatchProposedActionDOlist.size() > 0) {
				clonedRefMatchProposedActionDOList = new ArrayList<RefMatchProposedActionDO>();
				Iterator<RefMatchProposedActionDO> itr = dbimageRefMatchProposedActionDOlist.iterator();
				while (itr.hasNext()) {
					RefMatchProposedActionDO theRefMatchProposedActionDO = new RefMatchProposedActionDO(itr.next());
					clonedRefMatchProposedActionDOList.add(theRefMatchProposedActionDO);
				}
			}

			if (null == clonedRefMatchProposedActionDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchProposedAction reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefMatchProposedActionDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchProposedAction reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefMatchProposedActionDOList(clonedRefMatchProposedActionDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchProposedActionComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFMATCHPROPOSEDACTION_BUSKEY")
	public Page<RefMatchProposedActionDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefMatchProposedActionDO> pageRefMatchProposedActionDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefMatchProposedActionDO = this.theRefMatchProposedActionRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefMatchProposedActionDO = this.theRefMatchProposedActionRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefMatchProposedActionDO = this.theRefMatchProposedActionRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefMatchProposedActionDO;
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
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefMatchProposedActionDO reqRefMatchProposedActionDO = (RefMatchProposedActionDO) txnTransferObj
					.getTxnPayload().getRefMatchProposedActionDO();
			theRefMatchProposedActionComponentRule.preValidateRefMatchProposedActionfindByBusinessKey(txnTransferObj);
			theRefMatchProposedActionComponentRule.preExecuteRefMatchProposedActionfindByBusinessKey(txnTransferObj);

			RefMatchProposedActionDO dbimageRefMatchProposedActionDO = executeRepositoryQuery(
					reqRefMatchProposedActionDO.getConfigLanguageCodeKey(), reqRefMatchProposedActionDO.getKey(),
					reqRefMatchProposedActionDO.getInquiryFilter());

			if (null == dbimageRefMatchProposedActionDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchProposedActionComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefMatchProposedActionDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefMatchProposedActionDO(new RefMatchProposedActionDO(dbimageRefMatchProposedActionDO));
			}

			theRefMatchProposedActionComponentRule
					.postExecuteRefMatchProposedActionfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchProposedActionComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefMatchProposedActionDO returns the populated RefMatchProposedActionDO object
	 */
	@CacheResult(cacheName = "REFMATCHPROPOSEDACTION_BUSKEY")
	public RefMatchProposedActionDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefMatchProposedActionDO dbimageRefMatchProposedActionDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefMatchProposedActionDO = this.theRefMatchProposedActionRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefMatchProposedActionDO = this.theRefMatchProposedActionRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefMatchProposedActionDO = this.theRefMatchProposedActionRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefMatchProposedActionDO;
	}

	/**
	 * perform the common validation that RefMatchProposedActionDO,
	 * RefMatchProposedActionDO.configLanguageCodeKey and RefMatchProposedActionDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchProposedActionDO is needed in the request");
		}
		RefMatchProposedActionDO theRefMatchProposedActionDO = (RefMatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefMatchProposedActionDO();
		if (null == theRefMatchProposedActionDO.getKey() || theRefMatchProposedActionDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMatchProposedActionDO.key should not be null");
		}

		if (null == theRefMatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefMatchProposedActionDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchProposedActionDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefMatchProposedActionDO and
	 * RefMatchProposedActionDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchProposedActionDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchProposedActionDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchProposedActionDO is needed in the request");
		}
		RefMatchProposedActionDO theRefMatchProposedActionDO = (RefMatchProposedActionDO) txnTransferObj.getTxnPayload()
				.getRefMatchProposedActionDO();

		if (null == theRefMatchProposedActionDO.getConfigLanguageCodeKey()
				|| theRefMatchProposedActionDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchProposedActionDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchProposedActionDO().getInquiryFilter());
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
