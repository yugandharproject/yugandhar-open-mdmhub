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
import com.yugandhar.mdm.extern.dobj.RefLeRelationshipTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefLeRelationshipTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefLeRelationshipTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefLeRelationshipTypeRepository theRefLeRelationshipTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefLeRelationshipTypeComponentRule theRefLeRelationshipTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefLeRelationshipTypeComponent() {
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
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefLeRelationshipTypeComponentRule.prevalidateRefLeRelationshipTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefLeRelationshipTypeDO reqRefLeRelationshipTypeDO = (RefLeRelationshipTypeDO) txnTransferObj
					.getTxnPayload().getRefLeRelationshipTypeDO();
			if (null == reqRefLeRelationshipTypeDO.getPrimaryKeyDO()
					|| null == reqRefLeRelationshipTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefLeRelationshipTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefLeRelationshipTypeDO.setIdPk(reqRefLeRelationshipTypeDO.getPrimaryKeyDO().getIdPk());
				RefLeRelationshipTypeDO dbimageRefLeRelationshipTypeDO = entityManager
						.find(RefLeRelationshipTypeDO.class, reqRefLeRelationshipTypeDO.getIdPk());
				if (null != dbimageRefLeRelationshipTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefLeRelationshipTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefLeRelationshipTypeComponentRule.preExecuteRefLeRelationshipTypeCompPersist(reqRefLeRelationshipTypeDO,
					txnTransferObj);
			entityManager.persist(reqRefLeRelationshipTypeDO);
			entityManager.flush();
			reqRefLeRelationshipTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefLeRelationshipTypeDO(new RefLeRelationshipTypeDO(reqRefLeRelationshipTypeDO));
			theRefLeRelationshipTypeComponentRule.postExecuteRefLeRelationshipTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLeRelationshipTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLeRelationshipTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRelationshipTypeComponent.persist failed unexpectedly");
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
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefLeRelationshipTypeComponentRule.PrevalidateRefLeRelationshipTypeCompMerge(txnTransferObj);
			RefLeRelationshipTypeDO reqRefLeRelationshipTypeDO = (RefLeRelationshipTypeDO) txnTransferObj
					.getTxnPayload().getRefLeRelationshipTypeDO();
			RefLeRelationshipTypeDO dbimageRefLeRelationshipTypeDO = entityManager.find(RefLeRelationshipTypeDO.class,
					reqRefLeRelationshipTypeDO.getIdPk());
			if (null == dbimageRefLeRelationshipTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLeRelationshipTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefLeRelationshipTypeDO);
			BeanUtils.copyProperties(reqRefLeRelationshipTypeDO, dbimageRefLeRelationshipTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefLeRelationshipTypeDO);
			theRefLeRelationshipTypeComponentRule.preExecuteRefLeRelationshipTypeCompMerge(reqRefLeRelationshipTypeDO,
					dbimageRefLeRelationshipTypeDO, txnTransferObj);
			RefLeRelationshipTypeDO mergedRefLeRelationshipTypeDO = entityManager.merge(dbimageRefLeRelationshipTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefLeRelationshipTypeDO(new RefLeRelationshipTypeDO(mergedRefLeRelationshipTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLeRelationshipTypeComponentRule.postExecuteRefLeRelationshipTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefLeRelationshipTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLeRelationshipTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLeRelationshipTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRelationshipTypeComponent.merge failed unexpectedly");
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
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefLeRelationshipTypeComponentRule.prevalidateRefLeRelationshipTypeCompFindById(txnTransferObj);
			RefLeRelationshipTypeDO reqRefLeRelationshipTypeDO = (RefLeRelationshipTypeDO) txnTransferObj
					.getTxnPayload().getRefLeRelationshipTypeDO();
			RefLeRelationshipTypeDO dbimageRefLeRelationshipTypeDO = entityManager.find(RefLeRelationshipTypeDO.class,
					reqRefLeRelationshipTypeDO.getIdPk());
			if (null == dbimageRefLeRelationshipTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLeRelationshipTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefLeRelationshipTypeDO(new RefLeRelationshipTypeDO(dbimageRefLeRelationshipTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLeRelationshipTypeComponentRule.postExecuteRefLeRelationshipTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRelationshipTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefLeRelationshipTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRelationshipTypeDO is needed in the request");
		}

		RefLeRelationshipTypeDO theRefLeRelationshipTypeDO = (RefLeRelationshipTypeDO) txnTransferObj.getTxnPayload()
				.getRefLeRelationshipTypeDO();
		if (null == theRefLeRelationshipTypeDO.getKey() || theRefLeRelationshipTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLeRelationshipTypeDO.key should not be null");
		}

		if (null == theRefLeRelationshipTypeDO.getConfigLanguageCodeKey()
				|| theRefLeRelationshipTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRelationshipTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLeRelationshipTypeDO.getValue() || theRefLeRelationshipTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLeRelationshipTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLeRelationshipTypeDO.getConfigLanguageCodeKey()
				|| theRefLeRelationshipTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLeRelationshipTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLeRelationshipTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLeRelationshipTypeDO.setCreatedTs(new Date());
		theRefLeRelationshipTypeDO.setUpdatedTs(new Date());
		theRefLeRelationshipTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLeRelationshipTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefLeRelationshipTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRelationshipTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLeRelationshipTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefLeRelationshipTypeDO.version should not be null");
		}

		RefLeRelationshipTypeDO theRefLeRelationshipTypeDO = (RefLeRelationshipTypeDO) txnTransferObj.getTxnPayload()
				.getRefLeRelationshipTypeDO();
		if (null == theRefLeRelationshipTypeDO.getKey() || theRefLeRelationshipTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLeRelationshipTypeDO.key should not be null");
		}

		if (null == theRefLeRelationshipTypeDO.getConfigLanguageCodeKey()
				|| theRefLeRelationshipTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRelationshipTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLeRelationshipTypeDO.getValue() || theRefLeRelationshipTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLeRelationshipTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLeRelationshipTypeDO.getConfigLanguageCodeKey()
				|| theRefLeRelationshipTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLeRelationshipTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLeRelationshipTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLeRelationshipTypeDO.setUpdatedTs(new Date());
		theRefLeRelationshipTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLeRelationshipTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefLeRelationshipTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRelationshipTypeDO is needed in the request");
		}
		RefLeRelationshipTypeDO theRefLeRelationshipTypeDO = (RefLeRelationshipTypeDO) txnTransferObj.getTxnPayload()
				.getRefLeRelationshipTypeDO();
		if (null == theRefLeRelationshipTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLeRelationshipTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefLeRelationshipTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefLeRelationshipTypeDO> pageRefLeRelationshipTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefLeRelationshipTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLeRelationshipType reference list does not have any records in the database");
			}

			if ((pageRefLeRelationshipTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefLeRelationshipType, total number of pages is "
								+ pageRefLeRelationshipTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageRefLeRelationshipTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefLeRelationshipTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefLeRelationshipTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefLeRelationshipTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefLeRelationshipTypeDO.getSize());

			List<RefLeRelationshipTypeDO> dbimageRefLeRelationshipTypeDOlist = pageRefLeRelationshipTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefLeRelationshipTypeDO> clonedRefLeRelationshipTypeDOList = null;
			if (null != dbimageRefLeRelationshipTypeDOlist && dbimageRefLeRelationshipTypeDOlist.size() > 0) {
				clonedRefLeRelationshipTypeDOList = new ArrayList<RefLeRelationshipTypeDO>();
				Iterator<RefLeRelationshipTypeDO> itr = dbimageRefLeRelationshipTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefLeRelationshipTypeDO theRefLeRelationshipTypeDO = new RefLeRelationshipTypeDO(itr.next());
					clonedRefLeRelationshipTypeDOList.add(theRefLeRelationshipTypeDO);
				}
			}

			if (null == clonedRefLeRelationshipTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLeRelationshipType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefLeRelationshipTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLeRelationshipType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefLeRelationshipTypeDOList(clonedRefLeRelationshipTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRelationshipTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFLERELATIONSHIPTYPE_BUSKEY")
	public Page<RefLeRelationshipTypeDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefLeRelationshipTypeDO> pageRefLeRelationshipTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefLeRelationshipTypeDO = this.theRefLeRelationshipTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefLeRelationshipTypeDO = this.theRefLeRelationshipTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefLeRelationshipTypeDO = this.theRefLeRelationshipTypeRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefLeRelationshipTypeDO;
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
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefLeRelationshipTypeDO reqRefLeRelationshipTypeDO = (RefLeRelationshipTypeDO) txnTransferObj
					.getTxnPayload().getRefLeRelationshipTypeDO();
			theRefLeRelationshipTypeComponentRule.preValidateRefLeRelationshipTypefindByBusinessKey(txnTransferObj);
			theRefLeRelationshipTypeComponentRule.preExecuteRefLeRelationshipTypefindByBusinessKey(txnTransferObj);

			RefLeRelationshipTypeDO dbimageRefLeRelationshipTypeDO = executeRepositoryQuery(
					reqRefLeRelationshipTypeDO.getConfigLanguageCodeKey(), reqRefLeRelationshipTypeDO.getKey(),
					reqRefLeRelationshipTypeDO.getInquiryFilter());

			if (null == dbimageRefLeRelationshipTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLeRelationshipTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefLeRelationshipTypeDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefLeRelationshipTypeDO(new RefLeRelationshipTypeDO(dbimageRefLeRelationshipTypeDO));
			}

			theRefLeRelationshipTypeComponentRule.postExecuteRefLeRelationshipTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRelationshipTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefLeRelationshipTypeDO returns the populated RefLeRelationshipTypeDO object
	 */
	@CacheResult(cacheName = "REFLERELATIONSHIPTYPE_BUSKEY")
	public RefLeRelationshipTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefLeRelationshipTypeDO dbimageRefLeRelationshipTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefLeRelationshipTypeDO = this.theRefLeRelationshipTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefLeRelationshipTypeDO = this.theRefLeRelationshipTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefLeRelationshipTypeDO = this.theRefLeRelationshipTypeRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefLeRelationshipTypeDO;
	}

	/**
	 * perform the common validation that RefLeRelationshipTypeDO,
	 * RefLeRelationshipTypeDO.configLanguageCodeKey and RefLeRelationshipTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRelationshipTypeDO is needed in the request");
		}
		RefLeRelationshipTypeDO theRefLeRelationshipTypeDO = (RefLeRelationshipTypeDO) txnTransferObj.getTxnPayload()
				.getRefLeRelationshipTypeDO();
		if (null == theRefLeRelationshipTypeDO.getKey() || theRefLeRelationshipTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLeRelationshipTypeDO.key should not be null");
		}

		if (null == theRefLeRelationshipTypeDO.getConfigLanguageCodeKey()
				|| theRefLeRelationshipTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRelationshipTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefLeRelationshipTypeDO and
	 * RefLeRelationshipTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRelationshipTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRelationshipTypeDO is needed in the request");
		}
		RefLeRelationshipTypeDO theRefLeRelationshipTypeDO = (RefLeRelationshipTypeDO) txnTransferObj.getTxnPayload()
				.getRefLeRelationshipTypeDO();

		if (null == theRefLeRelationshipTypeDO.getConfigLanguageCodeKey()
				|| theRefLeRelationshipTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRelationshipTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLeRelationshipTypeDO().getInquiryFilter());
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
