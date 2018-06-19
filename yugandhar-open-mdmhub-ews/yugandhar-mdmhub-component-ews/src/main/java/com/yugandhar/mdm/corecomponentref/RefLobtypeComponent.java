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
import com.yugandhar.mdm.extern.dobj.RefLobtypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefLobtypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefLobtypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefLobtypeRepository theRefLobtypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefLobtypeComponentRule theRefLobtypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefLobtypeComponent() {
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
	 *             if RefLobtypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefLobtypeComponentRule.prevalidateRefLobtypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefLobtypeDO reqRefLobtypeDO = (RefLobtypeDO) txnTransferObj.getTxnPayload().getRefLobtypeDO();
			if (null == reqRefLobtypeDO.getPrimaryKeyDO() || null == reqRefLobtypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefLobtypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefLobtypeDO.setIdPk(reqRefLobtypeDO.getPrimaryKeyDO().getIdPk());
				RefLobtypeDO dbimageRefLobtypeDO = entityManager.find(RefLobtypeDO.class, reqRefLobtypeDO.getIdPk());
				if (null != dbimageRefLobtypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefLobtypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefLobtypeComponentRule.preExecuteRefLobtypeCompPersist(reqRefLobtypeDO, txnTransferObj);
			entityManager.persist(reqRefLobtypeDO);
			entityManager.flush();
			reqRefLobtypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefLobtypeDO(new RefLobtypeDO(reqRefLobtypeDO));
			theRefLobtypeComponentRule.postExecuteRefLobtypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLobtypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLobtypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLobtypeComponent.persist failed unexpectedly");
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
	 *             if RefLobtypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefLobtypeComponentRule.PrevalidateRefLobtypeCompMerge(txnTransferObj);
			RefLobtypeDO reqRefLobtypeDO = (RefLobtypeDO) txnTransferObj.getTxnPayload().getRefLobtypeDO();
			RefLobtypeDO dbimageRefLobtypeDO = entityManager.find(RefLobtypeDO.class, reqRefLobtypeDO.getIdPk());
			if (null == dbimageRefLobtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLobtypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefLobtypeDO);
			BeanUtils.copyProperties(reqRefLobtypeDO, dbimageRefLobtypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefLobtypeDO);
			theRefLobtypeComponentRule.preExecuteRefLobtypeCompMerge(reqRefLobtypeDO, dbimageRefLobtypeDO,
					txnTransferObj);
			RefLobtypeDO mergedRefLobtypeDO = entityManager.merge(dbimageRefLobtypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefLobtypeDO(new RefLobtypeDO(mergedRefLobtypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLobtypeComponentRule.postExecuteRefLobtypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefLobtypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLobtypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLobtypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLobtypeComponent.merge failed unexpectedly");
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
	 *             if RefLobtypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefLobtypeComponentRule.prevalidateRefLobtypeCompFindById(txnTransferObj);
			RefLobtypeDO reqRefLobtypeDO = (RefLobtypeDO) txnTransferObj.getTxnPayload().getRefLobtypeDO();
			RefLobtypeDO dbimageRefLobtypeDO = entityManager.find(RefLobtypeDO.class, reqRefLobtypeDO.getIdPk());
			if (null == dbimageRefLobtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLobtypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefLobtypeDO(new RefLobtypeDO(dbimageRefLobtypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLobtypeComponentRule.postExecuteRefLobtypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLobtypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefLobtypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLobtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLobtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLobtypeDO is needed in the request");
		}

		RefLobtypeDO theRefLobtypeDO = (RefLobtypeDO) txnTransferObj.getTxnPayload().getRefLobtypeDO();
		if (null == theRefLobtypeDO.getKey() || theRefLobtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLobtypeDO.key should not be null");
		}

		if (null == theRefLobtypeDO.getConfigLanguageCodeKey()
				|| theRefLobtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLobtypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLobtypeDO.getValue() || theRefLobtypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLobtypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLobtypeDO.getConfigLanguageCodeKey()
				|| theRefLobtypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLobtypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLobtypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLobtypeDO.setCreatedTs(new Date());
		theRefLobtypeDO.setUpdatedTs(new Date());
		theRefLobtypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLobtypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefLobtypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLobtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLobtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLobtypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefLobtypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefLobtypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLobtypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefLobtypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefLobtypeDO.version should not be null");
		}

		RefLobtypeDO theRefLobtypeDO = (RefLobtypeDO) txnTransferObj.getTxnPayload().getRefLobtypeDO();
		if (null == theRefLobtypeDO.getKey() || theRefLobtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLobtypeDO.key should not be null");
		}

		if (null == theRefLobtypeDO.getConfigLanguageCodeKey()
				|| theRefLobtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLobtypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLobtypeDO.getValue() || theRefLobtypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLobtypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLobtypeDO.getConfigLanguageCodeKey()
				|| theRefLobtypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLobtypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLobtypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLobtypeDO.setUpdatedTs(new Date());
		theRefLobtypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLobtypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefLobtypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLobtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLobtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLobtypeDO is needed in the request");
		}
		RefLobtypeDO theRefLobtypeDO = (RefLobtypeDO) txnTransferObj.getTxnPayload().getRefLobtypeDO();
		if (null == theRefLobtypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLobtypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefLobtypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLobtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefLobtypeDO> pageRefLobtypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefLobtypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefLobtypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefLobtypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLobtype reference list does not have any records in the database");
			}

			if ((pageRefLobtypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefLobtype, total number of pages is "
								+ pageRefLobtypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefLobtypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefLobtypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefLobtypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefLobtypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefLobtypeDO.getSize());

			List<RefLobtypeDO> dbimageRefLobtypeDOlist = pageRefLobtypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefLobtypeDO> clonedRefLobtypeDOList = null;
			if (null != dbimageRefLobtypeDOlist && dbimageRefLobtypeDOlist.size() > 0) {
				clonedRefLobtypeDOList = new ArrayList<RefLobtypeDO>();
				Iterator<RefLobtypeDO> itr = dbimageRefLobtypeDOlist.iterator();
				while (itr.hasNext()) {
					RefLobtypeDO theRefLobtypeDO = new RefLobtypeDO(itr.next());
					clonedRefLobtypeDOList.add(theRefLobtypeDO);
				}
			}

			if (null == clonedRefLobtypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLobtype reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefLobtypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLobtype reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefLobtypeDOList(clonedRefLobtypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLobtypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFLOBTYPE_BUSKEY")
	public Page<RefLobtypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefLobtypeDO> pageRefLobtypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefLobtypeDO = this.theRefLobtypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefLobtypeDO = this.theRefLobtypeRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefLobtypeDO = this.theRefLobtypeRepository.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefLobtypeDO;
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
	 *             if RefLobtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefLobtypeDO reqRefLobtypeDO = (RefLobtypeDO) txnTransferObj.getTxnPayload().getRefLobtypeDO();
			theRefLobtypeComponentRule.preValidateRefLobtypefindByBusinessKey(txnTransferObj);
			theRefLobtypeComponentRule.preExecuteRefLobtypefindByBusinessKey(txnTransferObj);

			RefLobtypeDO dbimageRefLobtypeDO = executeRepositoryQuery(reqRefLobtypeDO.getConfigLanguageCodeKey(),
					reqRefLobtypeDO.getKey(), reqRefLobtypeDO.getInquiryFilter());

			if (null == dbimageRefLobtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLobtypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefLobtypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefLobtypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefLobtypeDO(new RefLobtypeDO(dbimageRefLobtypeDO));
			}

			theRefLobtypeComponentRule.postExecuteRefLobtypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLobtypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefLobtypeDO returns the populated RefLobtypeDO object
	 */
	@CacheResult(cacheName = "REFLOBTYPE_BUSKEY")
	public RefLobtypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefLobtypeDO dbimageRefLobtypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefLobtypeDO = this.theRefLobtypeRepository.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefLobtypeDO = this.theRefLobtypeRepository.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefLobtypeDO = this.theRefLobtypeRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefLobtypeDO;
	}

	/**
	 * perform the common validation that RefLobtypeDO,
	 * RefLobtypeDO.configLanguageCodeKey and RefLobtypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLobtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLobtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLobtypeDO is needed in the request");
		}
		RefLobtypeDO theRefLobtypeDO = (RefLobtypeDO) txnTransferObj.getTxnPayload().getRefLobtypeDO();
		if (null == theRefLobtypeDO.getKey() || theRefLobtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLobtypeDO.key should not be null");
		}

		if (null == theRefLobtypeDO.getConfigLanguageCodeKey()
				|| theRefLobtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLobtypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLobtypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLobtypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLobtypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLobtypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefLobtypeDO and
	 * RefLobtypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLobtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLobtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLobtypeDO is needed in the request");
		}
		RefLobtypeDO theRefLobtypeDO = (RefLobtypeDO) txnTransferObj.getTxnPayload().getRefLobtypeDO();

		if (null == theRefLobtypeDO.getConfigLanguageCodeKey()
				|| theRefLobtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLobtypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLobtypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLobtypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLobtypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLobtypeDO().getInquiryFilter());
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
