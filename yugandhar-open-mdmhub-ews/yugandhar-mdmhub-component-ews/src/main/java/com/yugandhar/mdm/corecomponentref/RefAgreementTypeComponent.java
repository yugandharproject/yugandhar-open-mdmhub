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
import com.yugandhar.mdm.extern.dobj.RefAgreementTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefAgreementTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefAgreementTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefAgreementTypeRepository theRefAgreementTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefAgreementTypeComponentRule theRefAgreementTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefAgreementTypeComponent() {
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
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefAgreementTypeComponentRule.prevalidateRefAgreementTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefAgreementTypeDO reqRefAgreementTypeDO = (RefAgreementTypeDO) txnTransferObj.getTxnPayload()
					.getRefAgreementTypeDO();
			if (null == reqRefAgreementTypeDO.getPrimaryKeyDO()
					|| null == reqRefAgreementTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefAgreementTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefAgreementTypeDO.setIdPk(reqRefAgreementTypeDO.getPrimaryKeyDO().getIdPk());
				RefAgreementTypeDO dbimageRefAgreementTypeDO = entityManager.find(RefAgreementTypeDO.class,
						reqRefAgreementTypeDO.getIdPk());
				if (null != dbimageRefAgreementTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefAgreementTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefAgreementTypeComponentRule.preExecuteRefAgreementTypeCompPersist(reqRefAgreementTypeDO,
					txnTransferObj);
			entityManager.persist(reqRefAgreementTypeDO);
			entityManager.flush();
			reqRefAgreementTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefAgreementTypeDO(new RefAgreementTypeDO(reqRefAgreementTypeDO));
			theRefAgreementTypeComponentRule.postExecuteRefAgreementTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAgreementTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAgreementTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAgreementTypeComponent.persist failed unexpectedly");
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
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefAgreementTypeComponentRule.PrevalidateRefAgreementTypeCompMerge(txnTransferObj);
			RefAgreementTypeDO reqRefAgreementTypeDO = (RefAgreementTypeDO) txnTransferObj.getTxnPayload()
					.getRefAgreementTypeDO();
			RefAgreementTypeDO dbimageRefAgreementTypeDO = entityManager.find(RefAgreementTypeDO.class,
					reqRefAgreementTypeDO.getIdPk());
			if (null == dbimageRefAgreementTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAgreementTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefAgreementTypeDO);
			BeanUtils.copyProperties(reqRefAgreementTypeDO, dbimageRefAgreementTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefAgreementTypeDO);
			theRefAgreementTypeComponentRule.preExecuteRefAgreementTypeCompMerge(reqRefAgreementTypeDO,
					dbimageRefAgreementTypeDO, txnTransferObj);
			RefAgreementTypeDO mergedRefAgreementTypeDO = entityManager.merge(dbimageRefAgreementTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefAgreementTypeDO(new RefAgreementTypeDO(mergedRefAgreementTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAgreementTypeComponentRule.postExecuteRefAgreementTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefAgreementTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAgreementTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAgreementTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAgreementTypeComponent.merge failed unexpectedly");
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
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefAgreementTypeComponentRule.prevalidateRefAgreementTypeCompFindById(txnTransferObj);
			RefAgreementTypeDO reqRefAgreementTypeDO = (RefAgreementTypeDO) txnTransferObj.getTxnPayload()
					.getRefAgreementTypeDO();
			RefAgreementTypeDO dbimageRefAgreementTypeDO = entityManager.find(RefAgreementTypeDO.class,
					reqRefAgreementTypeDO.getIdPk());
			if (null == dbimageRefAgreementTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAgreementTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefAgreementTypeDO(new RefAgreementTypeDO(dbimageRefAgreementTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAgreementTypeComponentRule.postExecuteRefAgreementTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAgreementTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefAgreementTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAgreementTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAgreementTypeDO is needed in the request");
		}

		RefAgreementTypeDO theRefAgreementTypeDO = (RefAgreementTypeDO) txnTransferObj.getTxnPayload()
				.getRefAgreementTypeDO();
		if (null == theRefAgreementTypeDO.getKey() || theRefAgreementTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAgreementTypeDO.key should not be null");
		}

		if (null == theRefAgreementTypeDO.getConfigLanguageCodeKey()
				|| theRefAgreementTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAgreementTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAgreementTypeDO.getValue() || theRefAgreementTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAgreementTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAgreementTypeDO.getConfigLanguageCodeKey()
				|| theRefAgreementTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAgreementTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAgreementTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAgreementTypeDO.setCreatedTs(new Date());
		theRefAgreementTypeDO.setUpdatedTs(new Date());
		theRefAgreementTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAgreementTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefAgreementTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAgreementTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAgreementTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAgreementTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefAgreementTypeDO.version should not be null");
		}

		RefAgreementTypeDO theRefAgreementTypeDO = (RefAgreementTypeDO) txnTransferObj.getTxnPayload()
				.getRefAgreementTypeDO();
		if (null == theRefAgreementTypeDO.getKey() || theRefAgreementTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAgreementTypeDO.key should not be null");
		}

		if (null == theRefAgreementTypeDO.getConfigLanguageCodeKey()
				|| theRefAgreementTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAgreementTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAgreementTypeDO.getValue() || theRefAgreementTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAgreementTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAgreementTypeDO.getConfigLanguageCodeKey()
				|| theRefAgreementTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAgreementTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAgreementTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAgreementTypeDO.setUpdatedTs(new Date());
		theRefAgreementTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAgreementTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefAgreementTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAgreementTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAgreementTypeDO is needed in the request");
		}
		RefAgreementTypeDO theRefAgreementTypeDO = (RefAgreementTypeDO) txnTransferObj.getTxnPayload()
				.getRefAgreementTypeDO();
		if (null == theRefAgreementTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAgreementTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefAgreementTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefAgreementTypeDO> pageRefAgreementTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefAgreementTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAgreementType reference list does not have any records in the database");
			}

			if ((pageRefAgreementTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefAgreementType, total number of pages is "
								+ pageRefAgreementTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefAgreementTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefAgreementTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefAgreementTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefAgreementTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefAgreementTypeDO.getSize());

			List<RefAgreementTypeDO> dbimageRefAgreementTypeDOlist = pageRefAgreementTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefAgreementTypeDO> clonedRefAgreementTypeDOList = null;
			if (null != dbimageRefAgreementTypeDOlist && dbimageRefAgreementTypeDOlist.size() > 0) {
				clonedRefAgreementTypeDOList = new ArrayList<RefAgreementTypeDO>();
				Iterator<RefAgreementTypeDO> itr = dbimageRefAgreementTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefAgreementTypeDO theRefAgreementTypeDO = new RefAgreementTypeDO(itr.next());
					clonedRefAgreementTypeDOList.add(theRefAgreementTypeDO);
				}
			}

			if (null == clonedRefAgreementTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAgreementType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefAgreementTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAgreementType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefAgreementTypeDOList(clonedRefAgreementTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAgreementTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFAGREEMENTTYPE_BUSKEY")
	public Page<RefAgreementTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefAgreementTypeDO> pageRefAgreementTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefAgreementTypeDO = this.theRefAgreementTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefAgreementTypeDO = this.theRefAgreementTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefAgreementTypeDO = this.theRefAgreementTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefAgreementTypeDO;
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
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefAgreementTypeDO reqRefAgreementTypeDO = (RefAgreementTypeDO) txnTransferObj.getTxnPayload()
					.getRefAgreementTypeDO();
			theRefAgreementTypeComponentRule.preValidateRefAgreementTypefindByBusinessKey(txnTransferObj);
			theRefAgreementTypeComponentRule.preExecuteRefAgreementTypefindByBusinessKey(txnTransferObj);

			RefAgreementTypeDO dbimageRefAgreementTypeDO = executeRepositoryQuery(
					reqRefAgreementTypeDO.getConfigLanguageCodeKey(), reqRefAgreementTypeDO.getKey(),
					reqRefAgreementTypeDO.getInquiryFilter());

			if (null == dbimageRefAgreementTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAgreementTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefAgreementTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefAgreementTypeDO(new RefAgreementTypeDO(dbimageRefAgreementTypeDO));
			}

			theRefAgreementTypeComponentRule.postExecuteRefAgreementTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAgreementTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefAgreementTypeDO returns the populated RefAgreementTypeDO object
	 */
	@CacheResult(cacheName = "REFAGREEMENTTYPE_BUSKEY")
	public RefAgreementTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefAgreementTypeDO dbimageRefAgreementTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefAgreementTypeDO = this.theRefAgreementTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefAgreementTypeDO = this.theRefAgreementTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefAgreementTypeDO = this.theRefAgreementTypeRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefAgreementTypeDO;
	}

	/**
	 * perform the common validation that RefAgreementTypeDO,
	 * RefAgreementTypeDO.configLanguageCodeKey and RefAgreementTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAgreementTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAgreementTypeDO is needed in the request");
		}
		RefAgreementTypeDO theRefAgreementTypeDO = (RefAgreementTypeDO) txnTransferObj.getTxnPayload()
				.getRefAgreementTypeDO();
		if (null == theRefAgreementTypeDO.getKey() || theRefAgreementTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAgreementTypeDO.key should not be null");
		}

		if (null == theRefAgreementTypeDO.getConfigLanguageCodeKey()
				|| theRefAgreementTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAgreementTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAgreementTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefAgreementTypeDO and
	 * RefAgreementTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAgreementTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAgreementTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAgreementTypeDO is needed in the request");
		}
		RefAgreementTypeDO theRefAgreementTypeDO = (RefAgreementTypeDO) txnTransferObj.getTxnPayload()
				.getRefAgreementTypeDO();

		if (null == theRefAgreementTypeDO.getConfigLanguageCodeKey()
				|| theRefAgreementTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAgreementTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAgreementTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAgreementTypeDO().getInquiryFilter());
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
