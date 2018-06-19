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
import com.yugandhar.mdm.extern.dobj.RefPersonnameTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefPersonnameTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefPersonnameTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefPersonnameTypeRepository theRefPersonnameTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefPersonnameTypeComponentRule theRefPersonnameTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefPersonnameTypeComponent() {
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
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefPersonnameTypeComponentRule.prevalidateRefPersonnameTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefPersonnameTypeDO reqRefPersonnameTypeDO = (RefPersonnameTypeDO) txnTransferObj.getTxnPayload()
					.getRefPersonnameTypeDO();
			if (null == reqRefPersonnameTypeDO.getPrimaryKeyDO()
					|| null == reqRefPersonnameTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefPersonnameTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefPersonnameTypeDO.setIdPk(reqRefPersonnameTypeDO.getPrimaryKeyDO().getIdPk());
				RefPersonnameTypeDO dbimageRefPersonnameTypeDO = entityManager.find(RefPersonnameTypeDO.class,
						reqRefPersonnameTypeDO.getIdPk());
				if (null != dbimageRefPersonnameTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefPersonnameTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefPersonnameTypeComponentRule.preExecuteRefPersonnameTypeCompPersist(reqRefPersonnameTypeDO,
					txnTransferObj);
			entityManager.persist(reqRefPersonnameTypeDO);
			entityManager.flush();
			reqRefPersonnameTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefPersonnameTypeDO(new RefPersonnameTypeDO(reqRefPersonnameTypeDO));
			theRefPersonnameTypeComponentRule.postExecuteRefPersonnameTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPersonnameTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPersonnameTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonnameTypeComponent.persist failed unexpectedly");
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
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefPersonnameTypeComponentRule.PrevalidateRefPersonnameTypeCompMerge(txnTransferObj);
			RefPersonnameTypeDO reqRefPersonnameTypeDO = (RefPersonnameTypeDO) txnTransferObj.getTxnPayload()
					.getRefPersonnameTypeDO();
			RefPersonnameTypeDO dbimageRefPersonnameTypeDO = entityManager.find(RefPersonnameTypeDO.class,
					reqRefPersonnameTypeDO.getIdPk());
			if (null == dbimageRefPersonnameTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPersonnameTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefPersonnameTypeDO);
			BeanUtils.copyProperties(reqRefPersonnameTypeDO, dbimageRefPersonnameTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefPersonnameTypeDO);
			theRefPersonnameTypeComponentRule.preExecuteRefPersonnameTypeCompMerge(reqRefPersonnameTypeDO,
					dbimageRefPersonnameTypeDO, txnTransferObj);
			RefPersonnameTypeDO mergedRefPersonnameTypeDO = entityManager.merge(dbimageRefPersonnameTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefPersonnameTypeDO(new RefPersonnameTypeDO(mergedRefPersonnameTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPersonnameTypeComponentRule.postExecuteRefPersonnameTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefPersonnameTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPersonnameTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPersonnameTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonnameTypeComponent.merge failed unexpectedly");
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
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefPersonnameTypeComponentRule.prevalidateRefPersonnameTypeCompFindById(txnTransferObj);
			RefPersonnameTypeDO reqRefPersonnameTypeDO = (RefPersonnameTypeDO) txnTransferObj.getTxnPayload()
					.getRefPersonnameTypeDO();
			RefPersonnameTypeDO dbimageRefPersonnameTypeDO = entityManager.find(RefPersonnameTypeDO.class,
					reqRefPersonnameTypeDO.getIdPk());
			if (null == dbimageRefPersonnameTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPersonnameTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefPersonnameTypeDO(new RefPersonnameTypeDO(dbimageRefPersonnameTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPersonnameTypeComponentRule.postExecuteRefPersonnameTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonnameTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefPersonnameTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPersonnameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonnameTypeDO is needed in the request");
		}

		RefPersonnameTypeDO theRefPersonnameTypeDO = (RefPersonnameTypeDO) txnTransferObj.getTxnPayload()
				.getRefPersonnameTypeDO();
		if (null == theRefPersonnameTypeDO.getKey() || theRefPersonnameTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPersonnameTypeDO.key should not be null");
		}

		if (null == theRefPersonnameTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonnameTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPersonnameTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPersonnameTypeDO.getValue() || theRefPersonnameTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPersonnameTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPersonnameTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonnameTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPersonnameTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPersonnameTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPersonnameTypeDO.setCreatedTs(new Date());
		theRefPersonnameTypeDO.setUpdatedTs(new Date());
		theRefPersonnameTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPersonnameTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefPersonnameTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPersonnameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonnameTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPersonnameTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefPersonnameTypeDO.version should not be null");
		}

		RefPersonnameTypeDO theRefPersonnameTypeDO = (RefPersonnameTypeDO) txnTransferObj.getTxnPayload()
				.getRefPersonnameTypeDO();
		if (null == theRefPersonnameTypeDO.getKey() || theRefPersonnameTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPersonnameTypeDO.key should not be null");
		}

		if (null == theRefPersonnameTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonnameTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPersonnameTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPersonnameTypeDO.getValue() || theRefPersonnameTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPersonnameTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPersonnameTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonnameTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPersonnameTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPersonnameTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPersonnameTypeDO.setUpdatedTs(new Date());
		theRefPersonnameTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPersonnameTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefPersonnameTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPersonnameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonnameTypeDO is needed in the request");
		}
		RefPersonnameTypeDO theRefPersonnameTypeDO = (RefPersonnameTypeDO) txnTransferObj.getTxnPayload()
				.getRefPersonnameTypeDO();
		if (null == theRefPersonnameTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPersonnameTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefPersonnameTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefPersonnameTypeDO> pageRefPersonnameTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefPersonnameTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPersonnameType reference list does not have any records in the database");
			}

			if ((pageRefPersonnameTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefPersonnameType, total number of pages is "
								+ pageRefPersonnameTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefPersonnameTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefPersonnameTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefPersonnameTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefPersonnameTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefPersonnameTypeDO.getSize());

			List<RefPersonnameTypeDO> dbimageRefPersonnameTypeDOlist = pageRefPersonnameTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefPersonnameTypeDO> clonedRefPersonnameTypeDOList = null;
			if (null != dbimageRefPersonnameTypeDOlist && dbimageRefPersonnameTypeDOlist.size() > 0) {
				clonedRefPersonnameTypeDOList = new ArrayList<RefPersonnameTypeDO>();
				Iterator<RefPersonnameTypeDO> itr = dbimageRefPersonnameTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefPersonnameTypeDO theRefPersonnameTypeDO = new RefPersonnameTypeDO(itr.next());
					clonedRefPersonnameTypeDOList.add(theRefPersonnameTypeDO);
				}
			}

			if (null == clonedRefPersonnameTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPersonnameType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefPersonnameTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPersonnameType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefPersonnameTypeDOList(clonedRefPersonnameTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonnameTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFPERSONNAMETYPE_BUSKEY")
	public Page<RefPersonnameTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefPersonnameTypeDO> pageRefPersonnameTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefPersonnameTypeDO = this.theRefPersonnameTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefPersonnameTypeDO = this.theRefPersonnameTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefPersonnameTypeDO = this.theRefPersonnameTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefPersonnameTypeDO;
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
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefPersonnameTypeDO reqRefPersonnameTypeDO = (RefPersonnameTypeDO) txnTransferObj.getTxnPayload()
					.getRefPersonnameTypeDO();
			theRefPersonnameTypeComponentRule.preValidateRefPersonnameTypefindByBusinessKey(txnTransferObj);
			theRefPersonnameTypeComponentRule.preExecuteRefPersonnameTypefindByBusinessKey(txnTransferObj);

			RefPersonnameTypeDO dbimageRefPersonnameTypeDO = executeRepositoryQuery(
					reqRefPersonnameTypeDO.getConfigLanguageCodeKey(), reqRefPersonnameTypeDO.getKey(),
					reqRefPersonnameTypeDO.getInquiryFilter());

			if (null == dbimageRefPersonnameTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPersonnameTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefPersonnameTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefPersonnameTypeDO(new RefPersonnameTypeDO(dbimageRefPersonnameTypeDO));
			}

			theRefPersonnameTypeComponentRule.postExecuteRefPersonnameTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPersonnameTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefPersonnameTypeDO returns the populated RefPersonnameTypeDO object
	 */
	@CacheResult(cacheName = "REFPERSONNAMETYPE_BUSKEY")
	public RefPersonnameTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefPersonnameTypeDO dbimageRefPersonnameTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefPersonnameTypeDO = this.theRefPersonnameTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefPersonnameTypeDO = this.theRefPersonnameTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefPersonnameTypeDO = this.theRefPersonnameTypeRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefPersonnameTypeDO;
	}

	/**
	 * perform the common validation that RefPersonnameTypeDO,
	 * RefPersonnameTypeDO.configLanguageCodeKey and RefPersonnameTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPersonnameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonnameTypeDO is needed in the request");
		}
		RefPersonnameTypeDO theRefPersonnameTypeDO = (RefPersonnameTypeDO) txnTransferObj.getTxnPayload()
				.getRefPersonnameTypeDO();
		if (null == theRefPersonnameTypeDO.getKey() || theRefPersonnameTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPersonnameTypeDO.key should not be null");
		}

		if (null == theRefPersonnameTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonnameTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPersonnameTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefPersonnameTypeDO and
	 * RefPersonnameTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPersonnameTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPersonnameTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPersonnameTypeDO is needed in the request");
		}
		RefPersonnameTypeDO theRefPersonnameTypeDO = (RefPersonnameTypeDO) txnTransferObj.getTxnPayload()
				.getRefPersonnameTypeDO();

		if (null == theRefPersonnameTypeDO.getConfigLanguageCodeKey()
				|| theRefPersonnameTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPersonnameTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPersonnameTypeDO().getInquiryFilter());
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
