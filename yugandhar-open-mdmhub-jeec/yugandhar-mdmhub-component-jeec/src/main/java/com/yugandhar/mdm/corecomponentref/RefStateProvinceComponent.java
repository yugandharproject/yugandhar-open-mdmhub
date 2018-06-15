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
import com.yugandhar.mdm.extern.dobj.RefStateProvinceDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefStateProvinceDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * @see Documentation
 */

@Scope(value = "prototype")
@Component
public class RefStateProvinceComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefStateProvinceRepository theRefStateProvinceRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefStateProvinceComponentRule theRefStateProvinceComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefStateProvinceComponent() {
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
	 *             if RefStateProvinceDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefStateProvinceComponentRule.prevalidateRefStateProvinceCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefStateProvinceDO reqRefStateProvinceDO = (RefStateProvinceDO) txnTransferObj.getTxnPayload()
					.getRefStateProvinceDO();
			if (null == reqRefStateProvinceDO.getPrimaryKeyDO()
					|| null == reqRefStateProvinceDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefStateProvinceDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefStateProvinceDO.setIdPk(reqRefStateProvinceDO.getPrimaryKeyDO().getIdPk());
				RefStateProvinceDO dbimageRefStateProvinceDO = entityManager.find(RefStateProvinceDO.class,
						reqRefStateProvinceDO.getIdPk());
				if (null != dbimageRefStateProvinceDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefStateProvinceComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefStateProvinceComponentRule.preExecuteRefStateProvinceCompPersist(reqRefStateProvinceDO,
					txnTransferObj);
			entityManager.persist(reqRefStateProvinceDO);
			entityManager.flush();
			reqRefStateProvinceDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefStateProvinceDO(new RefStateProvinceDO(reqRefStateProvinceDO));
			theRefStateProvinceComponentRule.postExecuteRefStateProvinceCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefStateProvinceComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefStateProvinceComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStateProvinceComponent.persist failed unexpectedly");
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
	 *             if RefStateProvinceDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefStateProvinceComponentRule.PrevalidateRefStateProvinceCompMerge(txnTransferObj);
			RefStateProvinceDO reqRefStateProvinceDO = (RefStateProvinceDO) txnTransferObj.getTxnPayload()
					.getRefStateProvinceDO();
			RefStateProvinceDO dbimageRefStateProvinceDO = entityManager.find(RefStateProvinceDO.class,
					reqRefStateProvinceDO.getIdPk());
			if (null == dbimageRefStateProvinceDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefStateProvinceComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefStateProvinceDO);
			BeanUtils.copyProperties(reqRefStateProvinceDO, dbimageRefStateProvinceDO, strIgnoreProperties);
			entityManager.detach(dbimageRefStateProvinceDO);
			theRefStateProvinceComponentRule.preExecuteRefStateProvinceCompMerge(reqRefStateProvinceDO,
					dbimageRefStateProvinceDO, txnTransferObj);
			RefStateProvinceDO mergedRefStateProvinceDO = entityManager.merge(dbimageRefStateProvinceDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefStateProvinceDO(new RefStateProvinceDO(mergedRefStateProvinceDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefStateProvinceComponentRule.postExecuteRefStateProvinceCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefStateProvinceComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefStateProvinceComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefStateProvinceComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStateProvinceComponent.merge failed unexpectedly");
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
	 *             if RefStateProvinceDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefStateProvinceComponentRule.prevalidateRefStateProvinceCompFindById(txnTransferObj);
			RefStateProvinceDO reqRefStateProvinceDO = (RefStateProvinceDO) txnTransferObj.getTxnPayload()
					.getRefStateProvinceDO();
			RefStateProvinceDO dbimageRefStateProvinceDO = entityManager.find(RefStateProvinceDO.class,
					reqRefStateProvinceDO.getIdPk());
			if (null == dbimageRefStateProvinceDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefStateProvinceComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefStateProvinceDO(new RefStateProvinceDO(dbimageRefStateProvinceDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefStateProvinceComponentRule.postExecuteRefStateProvinceCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStateProvinceComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefStateProvinceDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStateProvinceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefStateProvinceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStateProvinceDO is needed in the request");
		}

		RefStateProvinceDO theRefStateProvinceDO = (RefStateProvinceDO) txnTransferObj.getTxnPayload()
				.getRefStateProvinceDO();
		if (null == theRefStateProvinceDO.getKey() || theRefStateProvinceDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefStateProvinceDO.key should not be null");
		}

		if (null == theRefStateProvinceDO.getConfigLanguageCodeKey()
				|| theRefStateProvinceDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStateProvinceDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefStateProvinceDO.getValue() || theRefStateProvinceDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefStateProvinceDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefStateProvinceDO.getConfigLanguageCodeKey()
				|| theRefStateProvinceDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefStateProvinceDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefStateProvinceDO.configLanguageCodeKey is not valid");
			}
		}

		theRefStateProvinceDO.setCreatedTs(new Date());
		theRefStateProvinceDO.setUpdatedTs(new Date());
		theRefStateProvinceDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefStateProvinceDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefStateProvinceDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStateProvinceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefStateProvinceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStateProvinceDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefStateProvinceDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefStateProvinceDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefStateProvinceDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefStateProvinceDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefStateProvinceDO.version should not be null");
		}

		RefStateProvinceDO theRefStateProvinceDO = (RefStateProvinceDO) txnTransferObj.getTxnPayload()
				.getRefStateProvinceDO();
		if (null == theRefStateProvinceDO.getKey() || theRefStateProvinceDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefStateProvinceDO.key should not be null");
		}

		if (null == theRefStateProvinceDO.getConfigLanguageCodeKey()
				|| theRefStateProvinceDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStateProvinceDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefStateProvinceDO.getValue() || theRefStateProvinceDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefStateProvinceDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefStateProvinceDO.getConfigLanguageCodeKey()
				|| theRefStateProvinceDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefStateProvinceDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefStateProvinceDO.configLanguageCodeKey is not valid");
			}
		}

		theRefStateProvinceDO.setUpdatedTs(new Date());
		theRefStateProvinceDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefStateProvinceDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefStateProvinceDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStateProvinceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefStateProvinceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStateProvinceDO is needed in the request");
		}
		RefStateProvinceDO theRefStateProvinceDO = (RefStateProvinceDO) txnTransferObj.getTxnPayload()
				.getRefStateProvinceDO();
		if (null == theRefStateProvinceDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefStateProvinceDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefStateProvinceDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStateProvinceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefStateProvinceDO> pageRefStateProvinceDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefStateProvinceDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefStateProvinceDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefStateProvinceDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefStateProvince reference list does not have any records in the database");
			}

			if ((pageRefStateProvinceDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefStateProvince, total number of pages is "
								+ pageRefStateProvinceDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefStateProvinceDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefStateProvinceDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefStateProvinceDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefStateProvinceDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefStateProvinceDO.getSize());

			List<RefStateProvinceDO> dbimageRefStateProvinceDOlist = pageRefStateProvinceDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefStateProvinceDO> clonedRefStateProvinceDOList = null;
			if (null != dbimageRefStateProvinceDOlist && dbimageRefStateProvinceDOlist.size() > 0) {
				clonedRefStateProvinceDOList = new ArrayList<RefStateProvinceDO>();
				Iterator<RefStateProvinceDO> itr = dbimageRefStateProvinceDOlist.iterator();
				while (itr.hasNext()) {
					RefStateProvinceDO theRefStateProvinceDO = new RefStateProvinceDO(itr.next());
					clonedRefStateProvinceDOList.add(theRefStateProvinceDO);
				}
			}

			if (null == clonedRefStateProvinceDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefStateProvince reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefStateProvinceDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefStateProvince reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefStateProvinceDOList(clonedRefStateProvinceDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStateProvinceComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFSTATEPROVINCE_BUSKEY")
	public Page<RefStateProvinceDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefStateProvinceDO> pageRefStateProvinceDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefStateProvinceDO = this.theRefStateProvinceRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefStateProvinceDO = this.theRefStateProvinceRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefStateProvinceDO = this.theRefStateProvinceRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefStateProvinceDO;
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
	 *             if RefStateProvinceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefStateProvinceDO reqRefStateProvinceDO = (RefStateProvinceDO) txnTransferObj.getTxnPayload()
					.getRefStateProvinceDO();
			theRefStateProvinceComponentRule.preValidateRefStateProvincefindByBusinessKey(txnTransferObj);
			theRefStateProvinceComponentRule.preExecuteRefStateProvincefindByBusinessKey(txnTransferObj);

			RefStateProvinceDO dbimageRefStateProvinceDO = executeRepositoryQuery(
					reqRefStateProvinceDO.getConfigLanguageCodeKey(), reqRefStateProvinceDO.getKey(),
					reqRefStateProvinceDO.getInquiryFilter());

			if (null == dbimageRefStateProvinceDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefStateProvinceComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefStateProvinceDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefStateProvinceDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefStateProvinceDO(new RefStateProvinceDO(dbimageRefStateProvinceDO));
			}

			theRefStateProvinceComponentRule.postExecuteRefStateProvincefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefStateProvinceComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefStateProvinceDO returns the populated RefStateProvinceDO object
	 */
	@CacheResult(cacheName = "REFSTATEPROVINCE_BUSKEY")
	public RefStateProvinceDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefStateProvinceDO dbimageRefStateProvinceDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefStateProvinceDO = this.theRefStateProvinceRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefStateProvinceDO = this.theRefStateProvinceRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefStateProvinceDO = this.theRefStateProvinceRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefStateProvinceDO;
	}

	/**
	 * perform the common validation that RefStateProvinceDO,
	 * RefStateProvinceDO.configLanguageCodeKey and RefStateProvinceDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStateProvinceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefStateProvinceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStateProvinceDO is needed in the request");
		}
		RefStateProvinceDO theRefStateProvinceDO = (RefStateProvinceDO) txnTransferObj.getTxnPayload()
				.getRefStateProvinceDO();
		if (null == theRefStateProvinceDO.getKey() || theRefStateProvinceDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefStateProvinceDO.key should not be null");
		}

		if (null == theRefStateProvinceDO.getConfigLanguageCodeKey()
				|| theRefStateProvinceDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStateProvinceDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefStateProvinceDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefStateProvinceDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefStateProvinceDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefStateProvinceDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefStateProvinceDO and
	 * RefStateProvinceDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefStateProvinceDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefStateProvinceDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefStateProvinceDO is needed in the request");
		}
		RefStateProvinceDO theRefStateProvinceDO = (RefStateProvinceDO) txnTransferObj.getTxnPayload()
				.getRefStateProvinceDO();

		if (null == theRefStateProvinceDO.getConfigLanguageCodeKey()
				|| theRefStateProvinceDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefStateProvinceDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefStateProvinceDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefStateProvinceDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefStateProvinceDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefStateProvinceDO().getInquiryFilter());
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
