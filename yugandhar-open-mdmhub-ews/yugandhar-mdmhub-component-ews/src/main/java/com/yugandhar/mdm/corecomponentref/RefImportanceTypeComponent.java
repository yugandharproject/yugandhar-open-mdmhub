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
import com.yugandhar.mdm.extern.dobj.RefImportanceTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefImportanceTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefImportanceTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefImportanceTypeRepository theRefImportanceTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefImportanceTypeComponentRule theRefImportanceTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefImportanceTypeComponent() {
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
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefImportanceTypeComponentRule.prevalidateRefImportanceTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefImportanceTypeDO reqRefImportanceTypeDO = (RefImportanceTypeDO) txnTransferObj.getTxnPayload()
					.getRefImportanceTypeDO();
			if (null == reqRefImportanceTypeDO.getPrimaryKeyDO()
					|| null == reqRefImportanceTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefImportanceTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefImportanceTypeDO.setIdPk(reqRefImportanceTypeDO.getPrimaryKeyDO().getIdPk());
				RefImportanceTypeDO dbimageRefImportanceTypeDO = entityManager.find(RefImportanceTypeDO.class,
						reqRefImportanceTypeDO.getIdPk());
				if (null != dbimageRefImportanceTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefImportanceTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefImportanceTypeComponentRule.preExecuteRefImportanceTypeCompPersist(reqRefImportanceTypeDO,
					txnTransferObj);
			entityManager.persist(reqRefImportanceTypeDO);
			entityManager.flush();
			reqRefImportanceTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefImportanceTypeDO(new RefImportanceTypeDO(reqRefImportanceTypeDO));
			theRefImportanceTypeComponentRule.postExecuteRefImportanceTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefImportanceTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefImportanceTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefImportanceTypeComponent.persist failed unexpectedly");
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
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefImportanceTypeComponentRule.PrevalidateRefImportanceTypeCompMerge(txnTransferObj);
			RefImportanceTypeDO reqRefImportanceTypeDO = (RefImportanceTypeDO) txnTransferObj.getTxnPayload()
					.getRefImportanceTypeDO();
			RefImportanceTypeDO dbimageRefImportanceTypeDO = entityManager.find(RefImportanceTypeDO.class,
					reqRefImportanceTypeDO.getIdPk());
			if (null == dbimageRefImportanceTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefImportanceTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefImportanceTypeDO);
			BeanUtils.copyProperties(reqRefImportanceTypeDO, dbimageRefImportanceTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefImportanceTypeDO);
			theRefImportanceTypeComponentRule.preExecuteRefImportanceTypeCompMerge(reqRefImportanceTypeDO,
					dbimageRefImportanceTypeDO, txnTransferObj);
			RefImportanceTypeDO mergedRefImportanceTypeDO = entityManager.merge(dbimageRefImportanceTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefImportanceTypeDO(new RefImportanceTypeDO(mergedRefImportanceTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefImportanceTypeComponentRule.postExecuteRefImportanceTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefImportanceTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefImportanceTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefImportanceTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefImportanceTypeComponent.merge failed unexpectedly");
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
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefImportanceTypeComponentRule.prevalidateRefImportanceTypeCompFindById(txnTransferObj);
			RefImportanceTypeDO reqRefImportanceTypeDO = (RefImportanceTypeDO) txnTransferObj.getTxnPayload()
					.getRefImportanceTypeDO();
			RefImportanceTypeDO dbimageRefImportanceTypeDO = entityManager.find(RefImportanceTypeDO.class,
					reqRefImportanceTypeDO.getIdPk());
			if (null == dbimageRefImportanceTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefImportanceTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefImportanceTypeDO(new RefImportanceTypeDO(dbimageRefImportanceTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefImportanceTypeComponentRule.postExecuteRefImportanceTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefImportanceTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefImportanceTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefImportanceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefImportanceTypeDO is needed in the request");
		}

		RefImportanceTypeDO theRefImportanceTypeDO = (RefImportanceTypeDO) txnTransferObj.getTxnPayload()
				.getRefImportanceTypeDO();
		if (null == theRefImportanceTypeDO.getKey() || theRefImportanceTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefImportanceTypeDO.key should not be null");
		}

		if (null == theRefImportanceTypeDO.getConfigLanguageCodeKey()
				|| theRefImportanceTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefImportanceTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefImportanceTypeDO.getValue() || theRefImportanceTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefImportanceTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefImportanceTypeDO.getConfigLanguageCodeKey()
				|| theRefImportanceTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefImportanceTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefImportanceTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefImportanceTypeDO.setCreatedTs(new Date());
		theRefImportanceTypeDO.setUpdatedTs(new Date());
		theRefImportanceTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefImportanceTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefImportanceTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefImportanceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefImportanceTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefImportanceTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefImportanceTypeDO.version should not be null");
		}

		RefImportanceTypeDO theRefImportanceTypeDO = (RefImportanceTypeDO) txnTransferObj.getTxnPayload()
				.getRefImportanceTypeDO();
		if (null == theRefImportanceTypeDO.getKey() || theRefImportanceTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefImportanceTypeDO.key should not be null");
		}

		if (null == theRefImportanceTypeDO.getConfigLanguageCodeKey()
				|| theRefImportanceTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefImportanceTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefImportanceTypeDO.getValue() || theRefImportanceTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefImportanceTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefImportanceTypeDO.getConfigLanguageCodeKey()
				|| theRefImportanceTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefImportanceTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefImportanceTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefImportanceTypeDO.setUpdatedTs(new Date());
		theRefImportanceTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefImportanceTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefImportanceTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefImportanceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefImportanceTypeDO is needed in the request");
		}
		RefImportanceTypeDO theRefImportanceTypeDO = (RefImportanceTypeDO) txnTransferObj.getTxnPayload()
				.getRefImportanceTypeDO();
		if (null == theRefImportanceTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefImportanceTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefImportanceTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefImportanceTypeDO> pageRefImportanceTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefImportanceTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefImportanceType reference list does not have any records in the database");
			}

			if ((pageRefImportanceTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefImportanceType, total number of pages is "
								+ pageRefImportanceTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefImportanceTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefImportanceTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefImportanceTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefImportanceTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefImportanceTypeDO.getSize());

			List<RefImportanceTypeDO> dbimageRefImportanceTypeDOlist = pageRefImportanceTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefImportanceTypeDO> clonedRefImportanceTypeDOList = null;
			if (null != dbimageRefImportanceTypeDOlist && dbimageRefImportanceTypeDOlist.size() > 0) {
				clonedRefImportanceTypeDOList = new ArrayList<RefImportanceTypeDO>();
				Iterator<RefImportanceTypeDO> itr = dbimageRefImportanceTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefImportanceTypeDO theRefImportanceTypeDO = new RefImportanceTypeDO(itr.next());
					clonedRefImportanceTypeDOList.add(theRefImportanceTypeDO);
				}
			}

			if (null == clonedRefImportanceTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefImportanceType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefImportanceTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefImportanceType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefImportanceTypeDOList(clonedRefImportanceTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefImportanceTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFIMPORTANCETYPE_BUSKEY")
	public Page<RefImportanceTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefImportanceTypeDO> pageRefImportanceTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefImportanceTypeDO = this.theRefImportanceTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefImportanceTypeDO = this.theRefImportanceTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefImportanceTypeDO = this.theRefImportanceTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefImportanceTypeDO;
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
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefImportanceTypeDO reqRefImportanceTypeDO = (RefImportanceTypeDO) txnTransferObj.getTxnPayload()
					.getRefImportanceTypeDO();
			theRefImportanceTypeComponentRule.preValidateRefImportanceTypefindByBusinessKey(txnTransferObj);
			theRefImportanceTypeComponentRule.preExecuteRefImportanceTypefindByBusinessKey(txnTransferObj);

			RefImportanceTypeDO dbimageRefImportanceTypeDO = executeRepositoryQuery(
					reqRefImportanceTypeDO.getConfigLanguageCodeKey(), reqRefImportanceTypeDO.getKey(),
					reqRefImportanceTypeDO.getInquiryFilter());

			if (null == dbimageRefImportanceTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefImportanceTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefImportanceTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefImportanceTypeDO(new RefImportanceTypeDO(dbimageRefImportanceTypeDO));
			}

			theRefImportanceTypeComponentRule.postExecuteRefImportanceTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefImportanceTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefImportanceTypeDO returns the populated RefImportanceTypeDO object
	 */
	@CacheResult(cacheName = "REFIMPORTANCETYPE_BUSKEY")
	public RefImportanceTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefImportanceTypeDO dbimageRefImportanceTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefImportanceTypeDO = this.theRefImportanceTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefImportanceTypeDO = this.theRefImportanceTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefImportanceTypeDO = this.theRefImportanceTypeRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefImportanceTypeDO;
	}

	/**
	 * perform the common validation that RefImportanceTypeDO,
	 * RefImportanceTypeDO.configLanguageCodeKey and RefImportanceTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefImportanceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefImportanceTypeDO is needed in the request");
		}
		RefImportanceTypeDO theRefImportanceTypeDO = (RefImportanceTypeDO) txnTransferObj.getTxnPayload()
				.getRefImportanceTypeDO();
		if (null == theRefImportanceTypeDO.getKey() || theRefImportanceTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefImportanceTypeDO.key should not be null");
		}

		if (null == theRefImportanceTypeDO.getConfigLanguageCodeKey()
				|| theRefImportanceTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefImportanceTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefImportanceTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefImportanceTypeDO and
	 * RefImportanceTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefImportanceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefImportanceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefImportanceTypeDO is needed in the request");
		}
		RefImportanceTypeDO theRefImportanceTypeDO = (RefImportanceTypeDO) txnTransferObj.getTxnPayload()
				.getRefImportanceTypeDO();

		if (null == theRefImportanceTypeDO.getConfigLanguageCodeKey()
				|| theRefImportanceTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefImportanceTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefImportanceTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefImportanceTypeDO().getInquiryFilter());
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
