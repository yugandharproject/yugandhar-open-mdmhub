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
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefSourceSystemDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefSourceSystemComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefSourceSystemRepository theRefSourceSystemRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefSourceSystemComponentRule theRefSourceSystemComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefSourceSystemComponent() {
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
	 *             if RefSourceSystemDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefSourceSystemComponentRule.prevalidateRefSourceSystemCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefSourceSystemDO reqRefSourceSystemDO = (RefSourceSystemDO) txnTransferObj.getTxnPayload()
					.getRefSourceSystemDO();
			if (null == reqRefSourceSystemDO.getPrimaryKeyDO()
					|| null == reqRefSourceSystemDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefSourceSystemDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefSourceSystemDO.setIdPk(reqRefSourceSystemDO.getPrimaryKeyDO().getIdPk());
				RefSourceSystemDO dbimageRefSourceSystemDO = entityManager.find(RefSourceSystemDO.class,
						reqRefSourceSystemDO.getIdPk());
				if (null != dbimageRefSourceSystemDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefSourceSystemComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefSourceSystemComponentRule.preExecuteRefSourceSystemCompPersist(reqRefSourceSystemDO, txnTransferObj);
			entityManager.persist(reqRefSourceSystemDO);
			entityManager.flush();
			reqRefSourceSystemDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefSourceSystemDO(new RefSourceSystemDO(reqRefSourceSystemDO));
			theRefSourceSystemComponentRule.postExecuteRefSourceSystemCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefSourceSystemComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefSourceSystemComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSourceSystemComponent.persist failed unexpectedly");
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
	 *             if RefSourceSystemDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefSourceSystemComponentRule.PrevalidateRefSourceSystemCompMerge(txnTransferObj);
			RefSourceSystemDO reqRefSourceSystemDO = (RefSourceSystemDO) txnTransferObj.getTxnPayload()
					.getRefSourceSystemDO();
			RefSourceSystemDO dbimageRefSourceSystemDO = entityManager.find(RefSourceSystemDO.class,
					reqRefSourceSystemDO.getIdPk());
			if (null == dbimageRefSourceSystemDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefSourceSystemComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefSourceSystemDO);
			BeanUtils.copyProperties(reqRefSourceSystemDO, dbimageRefSourceSystemDO, strIgnoreProperties);
			entityManager.detach(dbimageRefSourceSystemDO);
			theRefSourceSystemComponentRule.preExecuteRefSourceSystemCompMerge(reqRefSourceSystemDO,
					dbimageRefSourceSystemDO, txnTransferObj);
			RefSourceSystemDO mergedRefSourceSystemDO = entityManager.merge(dbimageRefSourceSystemDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefSourceSystemDO(new RefSourceSystemDO(mergedRefSourceSystemDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefSourceSystemComponentRule.postExecuteRefSourceSystemCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefSourceSystemComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefSourceSystemComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefSourceSystemComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSourceSystemComponent.merge failed unexpectedly");
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
	 *             if RefSourceSystemDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefSourceSystemComponentRule.prevalidateRefSourceSystemCompFindById(txnTransferObj);
			RefSourceSystemDO reqRefSourceSystemDO = (RefSourceSystemDO) txnTransferObj.getTxnPayload()
					.getRefSourceSystemDO();
			RefSourceSystemDO dbimageRefSourceSystemDO = entityManager.find(RefSourceSystemDO.class,
					reqRefSourceSystemDO.getIdPk());
			if (null == dbimageRefSourceSystemDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefSourceSystemComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefSourceSystemDO(new RefSourceSystemDO(dbimageRefSourceSystemDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefSourceSystemComponentRule.postExecuteRefSourceSystemCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSourceSystemComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefSourceSystemDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSourceSystemDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefSourceSystemDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSourceSystemDO is needed in the request");
		}

		RefSourceSystemDO theRefSourceSystemDO = (RefSourceSystemDO) txnTransferObj.getTxnPayload()
				.getRefSourceSystemDO();
		if (null == theRefSourceSystemDO.getKey() || theRefSourceSystemDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefSourceSystemDO.key should not be null");
		}

		if (null == theRefSourceSystemDO.getConfigLanguageCodeKey()
				|| theRefSourceSystemDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefSourceSystemDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefSourceSystemDO.getValue() || theRefSourceSystemDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefSourceSystemDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefSourceSystemDO.getConfigLanguageCodeKey()
				|| theRefSourceSystemDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefSourceSystemDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefSourceSystemDO.configLanguageCodeKey is not valid");
			}
		}

		theRefSourceSystemDO.setCreatedTs(new Date());
		theRefSourceSystemDO.setUpdatedTs(new Date());
		theRefSourceSystemDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefSourceSystemDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefSourceSystemDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSourceSystemDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefSourceSystemDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSourceSystemDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefSourceSystemDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefSourceSystemDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefSourceSystemDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefSourceSystemDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefSourceSystemDO.version should not be null");
		}

		RefSourceSystemDO theRefSourceSystemDO = (RefSourceSystemDO) txnTransferObj.getTxnPayload()
				.getRefSourceSystemDO();
		if (null == theRefSourceSystemDO.getKey() || theRefSourceSystemDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefSourceSystemDO.key should not be null");
		}

		if (null == theRefSourceSystemDO.getConfigLanguageCodeKey()
				|| theRefSourceSystemDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefSourceSystemDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefSourceSystemDO.getValue() || theRefSourceSystemDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefSourceSystemDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefSourceSystemDO.getConfigLanguageCodeKey()
				|| theRefSourceSystemDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefSourceSystemDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefSourceSystemDO.configLanguageCodeKey is not valid");
			}
		}

		theRefSourceSystemDO.setUpdatedTs(new Date());
		theRefSourceSystemDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefSourceSystemDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefSourceSystemDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSourceSystemDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefSourceSystemDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSourceSystemDO is needed in the request");
		}
		RefSourceSystemDO theRefSourceSystemDO = (RefSourceSystemDO) txnTransferObj.getTxnPayload()
				.getRefSourceSystemDO();
		if (null == theRefSourceSystemDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefSourceSystemDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefSourceSystemDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSourceSystemDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefSourceSystemDO> pageRefSourceSystemDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefSourceSystemDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefSourceSystemDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefSourceSystemDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefSourceSystem reference list does not have any records in the database");
			}

			if ((pageRefSourceSystemDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefSourceSystem, total number of pages is "
								+ pageRefSourceSystemDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefSourceSystemDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefSourceSystemDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefSourceSystemDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefSourceSystemDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefSourceSystemDO.getSize());

			List<RefSourceSystemDO> dbimageRefSourceSystemDOlist = pageRefSourceSystemDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefSourceSystemDO> clonedRefSourceSystemDOList = null;
			if (null != dbimageRefSourceSystemDOlist && dbimageRefSourceSystemDOlist.size() > 0) {
				clonedRefSourceSystemDOList = new ArrayList<RefSourceSystemDO>();
				Iterator<RefSourceSystemDO> itr = dbimageRefSourceSystemDOlist.iterator();
				while (itr.hasNext()) {
					RefSourceSystemDO theRefSourceSystemDO = new RefSourceSystemDO(itr.next());
					clonedRefSourceSystemDOList.add(theRefSourceSystemDO);
				}
			}

			if (null == clonedRefSourceSystemDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefSourceSystem reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefSourceSystemDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefSourceSystem reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefSourceSystemDOList(clonedRefSourceSystemDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSourceSystemComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFSOURCESYSTEM_BUSKEY")
	public Page<RefSourceSystemDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefSourceSystemDO> pageRefSourceSystemDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefSourceSystemDO = this.theRefSourceSystemRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefSourceSystemDO = this.theRefSourceSystemRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefSourceSystemDO = this.theRefSourceSystemRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefSourceSystemDO;
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
	 *             if RefSourceSystemDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefSourceSystemDO reqRefSourceSystemDO = (RefSourceSystemDO) txnTransferObj.getTxnPayload()
					.getRefSourceSystemDO();
			theRefSourceSystemComponentRule.preValidateRefSourceSystemfindByBusinessKey(txnTransferObj);
			theRefSourceSystemComponentRule.preExecuteRefSourceSystemfindByBusinessKey(txnTransferObj);

			RefSourceSystemDO dbimageRefSourceSystemDO = executeRepositoryQuery(
					reqRefSourceSystemDO.getConfigLanguageCodeKey(), reqRefSourceSystemDO.getKey(),
					reqRefSourceSystemDO.getInquiryFilter());

			if (null == dbimageRefSourceSystemDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefSourceSystemComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefSourceSystemDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefSourceSystemDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefSourceSystemDO(new RefSourceSystemDO(dbimageRefSourceSystemDO));
			}

			theRefSourceSystemComponentRule.postExecuteRefSourceSystemfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSourceSystemComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefSourceSystemDO returns the populated RefSourceSystemDO object
	 */
	@CacheResult(cacheName = "REFSOURCESYSTEM_BUSKEY")
	public RefSourceSystemDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefSourceSystemDO dbimageRefSourceSystemDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefSourceSystemDO = this.theRefSourceSystemRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefSourceSystemDO = this.theRefSourceSystemRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefSourceSystemDO = this.theRefSourceSystemRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefSourceSystemDO;
	}

	/**
	 * perform the common validation that RefSourceSystemDO,
	 * RefSourceSystemDO.configLanguageCodeKey and RefSourceSystemDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSourceSystemDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefSourceSystemDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSourceSystemDO is needed in the request");
		}
		RefSourceSystemDO theRefSourceSystemDO = (RefSourceSystemDO) txnTransferObj.getTxnPayload()
				.getRefSourceSystemDO();
		if (null == theRefSourceSystemDO.getKey() || theRefSourceSystemDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefSourceSystemDO.key should not be null");
		}

		if (null == theRefSourceSystemDO.getConfigLanguageCodeKey()
				|| theRefSourceSystemDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefSourceSystemDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefSourceSystemDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefSourceSystemDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefSourceSystemDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefSourceSystemDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefSourceSystemDO and
	 * RefSourceSystemDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSourceSystemDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefSourceSystemDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSourceSystemDO is needed in the request");
		}
		RefSourceSystemDO theRefSourceSystemDO = (RefSourceSystemDO) txnTransferObj.getTxnPayload()
				.getRefSourceSystemDO();

		if (null == theRefSourceSystemDO.getConfigLanguageCodeKey()
				|| theRefSourceSystemDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefSourceSystemDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefSourceSystemDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefSourceSystemDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefSourceSystemDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefSourceSystemDO().getInquiryFilter());
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
