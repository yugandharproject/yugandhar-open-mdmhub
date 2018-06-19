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
import com.yugandhar.mdm.extern.dobj.RefStatusTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefStatusTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefStatusTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefStatusTypeRepository theRefStatusTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefStatusTypeComponentRule theRefStatusTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefStatusTypeComponent() {
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
	 *             if RefStatusTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefStatusTypeComponentRule.prevalidateRefStatusTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefStatusTypeDO reqRefStatusTypeDO = (RefStatusTypeDO) txnTransferObj.getTxnPayload().getRefStatusTypeDO();
			if (null == reqRefStatusTypeDO.getPrimaryKeyDO()
					|| null == reqRefStatusTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefStatusTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefStatusTypeDO.setIdPk(reqRefStatusTypeDO.getPrimaryKeyDO().getIdPk());
				RefStatusTypeDO dbimageRefStatusTypeDO = entityManager.find(RefStatusTypeDO.class,
						reqRefStatusTypeDO.getIdPk());
				if (null != dbimageRefStatusTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefStatusTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefStatusTypeComponentRule.preExecuteRefStatusTypeCompPersist(reqRefStatusTypeDO, txnTransferObj);
			entityManager.persist(reqRefStatusTypeDO);
			entityManager.flush();
			reqRefStatusTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefStatusTypeDO(new RefStatusTypeDO(reqRefStatusTypeDO));
			theRefStatusTypeComponentRule.postExecuteRefStatusTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefStatusTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefStatusTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusTypeComponent.persist failed unexpectedly");
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
	 *             if RefStatusTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefStatusTypeComponentRule.PrevalidateRefStatusTypeCompMerge(txnTransferObj);
			RefStatusTypeDO reqRefStatusTypeDO = (RefStatusTypeDO) txnTransferObj.getTxnPayload().getRefStatusTypeDO();
			RefStatusTypeDO dbimageRefStatusTypeDO = entityManager.find(RefStatusTypeDO.class,
					reqRefStatusTypeDO.getIdPk());
			if (null == dbimageRefStatusTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefStatusTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefStatusTypeDO);
			BeanUtils.copyProperties(reqRefStatusTypeDO, dbimageRefStatusTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefStatusTypeDO);
			theRefStatusTypeComponentRule.preExecuteRefStatusTypeCompMerge(reqRefStatusTypeDO, dbimageRefStatusTypeDO,
					txnTransferObj);
			RefStatusTypeDO mergedRefStatusTypeDO = entityManager.merge(dbimageRefStatusTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefStatusTypeDO(new RefStatusTypeDO(mergedRefStatusTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefStatusTypeComponentRule.postExecuteRefStatusTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefStatusTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefStatusTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefStatusTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusTypeComponent.merge failed unexpectedly");
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
	 *             if RefStatusTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefStatusTypeComponentRule.prevalidateRefStatusTypeCompFindById(txnTransferObj);
			RefStatusTypeDO reqRefStatusTypeDO = (RefStatusTypeDO) txnTransferObj.getTxnPayload().getRefStatusTypeDO();
			RefStatusTypeDO dbimageRefStatusTypeDO = entityManager.find(RefStatusTypeDO.class,
					reqRefStatusTypeDO.getIdPk());
			if (null == dbimageRefStatusTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefStatusTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefStatusTypeDO(new RefStatusTypeDO(dbimageRefStatusTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefStatusTypeComponentRule.postExecuteRefStatusTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefStatusTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefStatusTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusTypeDO is needed in the request");
		}

		RefStatusTypeDO theRefStatusTypeDO = (RefStatusTypeDO) txnTransferObj.getTxnPayload().getRefStatusTypeDO();
		if (null == theRefStatusTypeDO.getKey() || theRefStatusTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefStatusTypeDO.key should not be null");
		}

		if (null == theRefStatusTypeDO.getConfigLanguageCodeKey()
				|| theRefStatusTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStatusTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefStatusTypeDO.getValue() || theRefStatusTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefStatusTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefStatusTypeDO.getConfigLanguageCodeKey()
				|| theRefStatusTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefStatusTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefStatusTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefStatusTypeDO.setCreatedTs(new Date());
		theRefStatusTypeDO.setUpdatedTs(new Date());
		theRefStatusTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefStatusTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefStatusTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefStatusTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefStatusTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefStatusTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefStatusTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefStatusTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefStatusTypeDO.version should not be null");
		}

		RefStatusTypeDO theRefStatusTypeDO = (RefStatusTypeDO) txnTransferObj.getTxnPayload().getRefStatusTypeDO();
		if (null == theRefStatusTypeDO.getKey() || theRefStatusTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefStatusTypeDO.key should not be null");
		}

		if (null == theRefStatusTypeDO.getConfigLanguageCodeKey()
				|| theRefStatusTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStatusTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefStatusTypeDO.getValue() || theRefStatusTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefStatusTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefStatusTypeDO.getConfigLanguageCodeKey()
				|| theRefStatusTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefStatusTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefStatusTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefStatusTypeDO.setUpdatedTs(new Date());
		theRefStatusTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefStatusTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefStatusTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefStatusTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusTypeDO is needed in the request");
		}
		RefStatusTypeDO theRefStatusTypeDO = (RefStatusTypeDO) txnTransferObj.getTxnPayload().getRefStatusTypeDO();
		if (null == theRefStatusTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefStatusTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefStatusTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefStatusTypeDO> pageRefStatusTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefStatusTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefStatusTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefStatusTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefStatusType reference list does not have any records in the database");
			}

			if ((pageRefStatusTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefStatusType, total number of pages is "
								+ pageRefStatusTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefStatusTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefStatusTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefStatusTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefStatusTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefStatusTypeDO.getSize());

			List<RefStatusTypeDO> dbimageRefStatusTypeDOlist = pageRefStatusTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefStatusTypeDO> clonedRefStatusTypeDOList = null;
			if (null != dbimageRefStatusTypeDOlist && dbimageRefStatusTypeDOlist.size() > 0) {
				clonedRefStatusTypeDOList = new ArrayList<RefStatusTypeDO>();
				Iterator<RefStatusTypeDO> itr = dbimageRefStatusTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefStatusTypeDO theRefStatusTypeDO = new RefStatusTypeDO(itr.next());
					clonedRefStatusTypeDOList.add(theRefStatusTypeDO);
				}
			}

			if (null == clonedRefStatusTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefStatusType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefStatusTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefStatusType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefStatusTypeDOList(clonedRefStatusTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFSTATUSTYPE_BUSKEY")
	public Page<RefStatusTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefStatusTypeDO> pageRefStatusTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefStatusTypeDO = this.theRefStatusTypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefStatusTypeDO = this.theRefStatusTypeRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefStatusTypeDO = this.theRefStatusTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefStatusTypeDO;
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
	 *             if RefStatusTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefStatusTypeDO reqRefStatusTypeDO = (RefStatusTypeDO) txnTransferObj.getTxnPayload().getRefStatusTypeDO();
			theRefStatusTypeComponentRule.preValidateRefStatusTypefindByBusinessKey(txnTransferObj);
			theRefStatusTypeComponentRule.preExecuteRefStatusTypefindByBusinessKey(txnTransferObj);

			RefStatusTypeDO dbimageRefStatusTypeDO = executeRepositoryQuery(
					reqRefStatusTypeDO.getConfigLanguageCodeKey(), reqRefStatusTypeDO.getKey(),
					reqRefStatusTypeDO.getInquiryFilter());

			if (null == dbimageRefStatusTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefStatusTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefStatusTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefStatusTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefStatusTypeDO(new RefStatusTypeDO(dbimageRefStatusTypeDO));
			}

			theRefStatusTypeComponentRule.postExecuteRefStatusTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStatusTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefStatusTypeDO returns the populated RefStatusTypeDO object
	 */
	@CacheResult(cacheName = "REFSTATUSTYPE_BUSKEY")
	public RefStatusTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefStatusTypeDO dbimageRefStatusTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefStatusTypeDO = this.theRefStatusTypeRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefStatusTypeDO = this.theRefStatusTypeRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefStatusTypeDO = this.theRefStatusTypeRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefStatusTypeDO;
	}

	/**
	 * perform the common validation that RefStatusTypeDO,
	 * RefStatusTypeDO.configLanguageCodeKey and RefStatusTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefStatusTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusTypeDO is needed in the request");
		}
		RefStatusTypeDO theRefStatusTypeDO = (RefStatusTypeDO) txnTransferObj.getTxnPayload().getRefStatusTypeDO();
		if (null == theRefStatusTypeDO.getKey() || theRefStatusTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefStatusTypeDO.key should not be null");
		}

		if (null == theRefStatusTypeDO.getConfigLanguageCodeKey()
				|| theRefStatusTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStatusTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefStatusTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefStatusTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefStatusTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefStatusTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefStatusTypeDO and
	 * RefStatusTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStatusTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefStatusTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStatusTypeDO is needed in the request");
		}
		RefStatusTypeDO theRefStatusTypeDO = (RefStatusTypeDO) txnTransferObj.getTxnPayload().getRefStatusTypeDO();

		if (null == theRefStatusTypeDO.getConfigLanguageCodeKey()
				|| theRefStatusTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStatusTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefStatusTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefStatusTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefStatusTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefStatusTypeDO().getInquiryFilter());
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
