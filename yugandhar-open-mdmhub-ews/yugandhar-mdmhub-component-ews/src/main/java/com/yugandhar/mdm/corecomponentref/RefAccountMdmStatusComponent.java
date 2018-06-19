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
import com.yugandhar.mdm.extern.dobj.RefAccountMdmStatusDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefAccountMdmStatusDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefAccountMdmStatusComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefAccountMdmStatusRepository theRefAccountMdmStatusRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefAccountMdmStatusComponentRule theRefAccountMdmStatusComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefAccountMdmStatusComponent() {
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
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefAccountMdmStatusComponentRule.prevalidateRefAccountMdmStatusCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefAccountMdmStatusDO reqRefAccountMdmStatusDO = (RefAccountMdmStatusDO) txnTransferObj.getTxnPayload()
					.getRefAccountMdmStatusDO();
			if (null == reqRefAccountMdmStatusDO.getPrimaryKeyDO()
					|| null == reqRefAccountMdmStatusDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefAccountMdmStatusDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefAccountMdmStatusDO.setIdPk(reqRefAccountMdmStatusDO.getPrimaryKeyDO().getIdPk());
				RefAccountMdmStatusDO dbimageRefAccountMdmStatusDO = entityManager.find(RefAccountMdmStatusDO.class,
						reqRefAccountMdmStatusDO.getIdPk());
				if (null != dbimageRefAccountMdmStatusDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefAccountMdmStatusComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefAccountMdmStatusComponentRule.preExecuteRefAccountMdmStatusCompPersist(reqRefAccountMdmStatusDO,
					txnTransferObj);
			entityManager.persist(reqRefAccountMdmStatusDO);
			entityManager.flush();
			reqRefAccountMdmStatusDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefAccountMdmStatusDO(new RefAccountMdmStatusDO(reqRefAccountMdmStatusDO));
			theRefAccountMdmStatusComponentRule.postExecuteRefAccountMdmStatusCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAccountMdmStatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAccountMdmStatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountMdmStatusComponent.persist failed unexpectedly");
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
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefAccountMdmStatusComponentRule.PrevalidateRefAccountMdmStatusCompMerge(txnTransferObj);
			RefAccountMdmStatusDO reqRefAccountMdmStatusDO = (RefAccountMdmStatusDO) txnTransferObj.getTxnPayload()
					.getRefAccountMdmStatusDO();
			RefAccountMdmStatusDO dbimageRefAccountMdmStatusDO = entityManager.find(RefAccountMdmStatusDO.class,
					reqRefAccountMdmStatusDO.getIdPk());
			if (null == dbimageRefAccountMdmStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAccountMdmStatusComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefAccountMdmStatusDO);
			BeanUtils.copyProperties(reqRefAccountMdmStatusDO, dbimageRefAccountMdmStatusDO, strIgnoreProperties);
			entityManager.detach(dbimageRefAccountMdmStatusDO);
			theRefAccountMdmStatusComponentRule.preExecuteRefAccountMdmStatusCompMerge(reqRefAccountMdmStatusDO,
					dbimageRefAccountMdmStatusDO, txnTransferObj);
			RefAccountMdmStatusDO mergedRefAccountMdmStatusDO = entityManager.merge(dbimageRefAccountMdmStatusDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefAccountMdmStatusDO(new RefAccountMdmStatusDO(mergedRefAccountMdmStatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAccountMdmStatusComponentRule.postExecuteRefAccountMdmStatusCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefAccountMdmStatusComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAccountMdmStatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAccountMdmStatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountMdmStatusComponent.merge failed unexpectedly");
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
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefAccountMdmStatusComponentRule.prevalidateRefAccountMdmStatusCompFindById(txnTransferObj);
			RefAccountMdmStatusDO reqRefAccountMdmStatusDO = (RefAccountMdmStatusDO) txnTransferObj.getTxnPayload()
					.getRefAccountMdmStatusDO();
			RefAccountMdmStatusDO dbimageRefAccountMdmStatusDO = entityManager.find(RefAccountMdmStatusDO.class,
					reqRefAccountMdmStatusDO.getIdPk());
			if (null == dbimageRefAccountMdmStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAccountMdmStatusComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefAccountMdmStatusDO(new RefAccountMdmStatusDO(dbimageRefAccountMdmStatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAccountMdmStatusComponentRule.postExecuteRefAccountMdmStatusCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountMdmStatusComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefAccountMdmStatusDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountMdmStatusDO is needed in the request");
		}

		RefAccountMdmStatusDO theRefAccountMdmStatusDO = (RefAccountMdmStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountMdmStatusDO();
		if (null == theRefAccountMdmStatusDO.getKey() || theRefAccountMdmStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAccountMdmStatusDO.key should not be null");
		}

		if (null == theRefAccountMdmStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountMdmStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAccountMdmStatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAccountMdmStatusDO.getValue() || theRefAccountMdmStatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAccountMdmStatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAccountMdmStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountMdmStatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAccountMdmStatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAccountMdmStatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAccountMdmStatusDO.setCreatedTs(new Date());
		theRefAccountMdmStatusDO.setUpdatedTs(new Date());
		theRefAccountMdmStatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAccountMdmStatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefAccountMdmStatusDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountMdmStatusDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAccountMdmStatusDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefAccountMdmStatusDO.version should not be null");
		}

		RefAccountMdmStatusDO theRefAccountMdmStatusDO = (RefAccountMdmStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountMdmStatusDO();
		if (null == theRefAccountMdmStatusDO.getKey() || theRefAccountMdmStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAccountMdmStatusDO.key should not be null");
		}

		if (null == theRefAccountMdmStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountMdmStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAccountMdmStatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAccountMdmStatusDO.getValue() || theRefAccountMdmStatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAccountMdmStatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAccountMdmStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountMdmStatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAccountMdmStatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAccountMdmStatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAccountMdmStatusDO.setUpdatedTs(new Date());
		theRefAccountMdmStatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAccountMdmStatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefAccountMdmStatusDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountMdmStatusDO is needed in the request");
		}
		RefAccountMdmStatusDO theRefAccountMdmStatusDO = (RefAccountMdmStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountMdmStatusDO();
		if (null == theRefAccountMdmStatusDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAccountMdmStatusDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefAccountMdmStatusDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefAccountMdmStatusDO> pageRefAccountMdmStatusDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefAccountMdmStatusDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAccountMdmStatus reference list does not have any records in the database");
			}

			if ((pageRefAccountMdmStatusDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefAccountMdmStatus, total number of pages is "
								+ pageRefAccountMdmStatusDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefAccountMdmStatusDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefAccountMdmStatusDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefAccountMdmStatusDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefAccountMdmStatusDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefAccountMdmStatusDO.getSize());

			List<RefAccountMdmStatusDO> dbimageRefAccountMdmStatusDOlist = pageRefAccountMdmStatusDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefAccountMdmStatusDO> clonedRefAccountMdmStatusDOList = null;
			if (null != dbimageRefAccountMdmStatusDOlist && dbimageRefAccountMdmStatusDOlist.size() > 0) {
				clonedRefAccountMdmStatusDOList = new ArrayList<RefAccountMdmStatusDO>();
				Iterator<RefAccountMdmStatusDO> itr = dbimageRefAccountMdmStatusDOlist.iterator();
				while (itr.hasNext()) {
					RefAccountMdmStatusDO theRefAccountMdmStatusDO = new RefAccountMdmStatusDO(itr.next());
					clonedRefAccountMdmStatusDOList.add(theRefAccountMdmStatusDO);
				}
			}

			if (null == clonedRefAccountMdmStatusDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAccountMdmStatus reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefAccountMdmStatusDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAccountMdmStatus reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefAccountMdmStatusDOList(clonedRefAccountMdmStatusDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountMdmStatusComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFACCOUNTMDMSTATUS_BUSKEY")
	public Page<RefAccountMdmStatusDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefAccountMdmStatusDO> pageRefAccountMdmStatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefAccountMdmStatusDO = this.theRefAccountMdmStatusRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefAccountMdmStatusDO = this.theRefAccountMdmStatusRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefAccountMdmStatusDO = this.theRefAccountMdmStatusRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefAccountMdmStatusDO;
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
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefAccountMdmStatusDO reqRefAccountMdmStatusDO = (RefAccountMdmStatusDO) txnTransferObj.getTxnPayload()
					.getRefAccountMdmStatusDO();
			theRefAccountMdmStatusComponentRule.preValidateRefAccountMdmStatusfindByBusinessKey(txnTransferObj);
			theRefAccountMdmStatusComponentRule.preExecuteRefAccountMdmStatusfindByBusinessKey(txnTransferObj);

			RefAccountMdmStatusDO dbimageRefAccountMdmStatusDO = executeRepositoryQuery(
					reqRefAccountMdmStatusDO.getConfigLanguageCodeKey(), reqRefAccountMdmStatusDO.getKey(),
					reqRefAccountMdmStatusDO.getInquiryFilter());

			if (null == dbimageRefAccountMdmStatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAccountMdmStatusComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefAccountMdmStatusDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefAccountMdmStatusDO(new RefAccountMdmStatusDO(dbimageRefAccountMdmStatusDO));
			}

			theRefAccountMdmStatusComponentRule.postExecuteRefAccountMdmStatusfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAccountMdmStatusComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefAccountMdmStatusDO returns the populated RefAccountMdmStatusDO object
	 */
	@CacheResult(cacheName = "REFACCOUNTMDMSTATUS_BUSKEY")
	public RefAccountMdmStatusDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefAccountMdmStatusDO dbimageRefAccountMdmStatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefAccountMdmStatusDO = this.theRefAccountMdmStatusRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefAccountMdmStatusDO = this.theRefAccountMdmStatusRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefAccountMdmStatusDO = this.theRefAccountMdmStatusRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefAccountMdmStatusDO;
	}

	/**
	 * perform the common validation that RefAccountMdmStatusDO,
	 * RefAccountMdmStatusDO.configLanguageCodeKey and RefAccountMdmStatusDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountMdmStatusDO is needed in the request");
		}
		RefAccountMdmStatusDO theRefAccountMdmStatusDO = (RefAccountMdmStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountMdmStatusDO();
		if (null == theRefAccountMdmStatusDO.getKey() || theRefAccountMdmStatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAccountMdmStatusDO.key should not be null");
		}

		if (null == theRefAccountMdmStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountMdmStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAccountMdmStatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefAccountMdmStatusDO and
	 * RefAccountMdmStatusDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAccountMdmStatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAccountMdmStatusDO is needed in the request");
		}
		RefAccountMdmStatusDO theRefAccountMdmStatusDO = (RefAccountMdmStatusDO) txnTransferObj.getTxnPayload()
				.getRefAccountMdmStatusDO();

		if (null == theRefAccountMdmStatusDO.getConfigLanguageCodeKey()
				|| theRefAccountMdmStatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAccountMdmStatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAccountMdmStatusDO().getInquiryFilter());
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
