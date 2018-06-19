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
import com.yugandhar.mdm.extern.dobj.RefGroupTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefGroupTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefGroupTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefGroupTypeRepository theRefGroupTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefGroupTypeComponentRule theRefGroupTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefGroupTypeComponent() {
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
	 *             if RefGroupTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefGroupTypeComponentRule.prevalidateRefGroupTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefGroupTypeDO reqRefGroupTypeDO = (RefGroupTypeDO) txnTransferObj.getTxnPayload().getRefGroupTypeDO();
			if (null == reqRefGroupTypeDO.getPrimaryKeyDO() || null == reqRefGroupTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefGroupTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefGroupTypeDO.setIdPk(reqRefGroupTypeDO.getPrimaryKeyDO().getIdPk());
				RefGroupTypeDO dbimageRefGroupTypeDO = entityManager.find(RefGroupTypeDO.class,
						reqRefGroupTypeDO.getIdPk());
				if (null != dbimageRefGroupTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefGroupTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefGroupTypeComponentRule.preExecuteRefGroupTypeCompPersist(reqRefGroupTypeDO, txnTransferObj);
			entityManager.persist(reqRefGroupTypeDO);
			entityManager.flush();
			reqRefGroupTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefGroupTypeDO(new RefGroupTypeDO(reqRefGroupTypeDO));
			theRefGroupTypeComponentRule.postExecuteRefGroupTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefGroupTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefGroupTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupTypeComponent.persist failed unexpectedly");
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
	 *             if RefGroupTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefGroupTypeComponentRule.PrevalidateRefGroupTypeCompMerge(txnTransferObj);
			RefGroupTypeDO reqRefGroupTypeDO = (RefGroupTypeDO) txnTransferObj.getTxnPayload().getRefGroupTypeDO();
			RefGroupTypeDO dbimageRefGroupTypeDO = entityManager.find(RefGroupTypeDO.class,
					reqRefGroupTypeDO.getIdPk());
			if (null == dbimageRefGroupTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefGroupTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefGroupTypeDO);
			BeanUtils.copyProperties(reqRefGroupTypeDO, dbimageRefGroupTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefGroupTypeDO);
			theRefGroupTypeComponentRule.preExecuteRefGroupTypeCompMerge(reqRefGroupTypeDO, dbimageRefGroupTypeDO,
					txnTransferObj);
			RefGroupTypeDO mergedRefGroupTypeDO = entityManager.merge(dbimageRefGroupTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefGroupTypeDO(new RefGroupTypeDO(mergedRefGroupTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefGroupTypeComponentRule.postExecuteRefGroupTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefGroupTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefGroupTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefGroupTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupTypeComponent.merge failed unexpectedly");
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
	 *             if RefGroupTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefGroupTypeComponentRule.prevalidateRefGroupTypeCompFindById(txnTransferObj);
			RefGroupTypeDO reqRefGroupTypeDO = (RefGroupTypeDO) txnTransferObj.getTxnPayload().getRefGroupTypeDO();
			RefGroupTypeDO dbimageRefGroupTypeDO = entityManager.find(RefGroupTypeDO.class,
					reqRefGroupTypeDO.getIdPk());
			if (null == dbimageRefGroupTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefGroupTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefGroupTypeDO(new RefGroupTypeDO(dbimageRefGroupTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefGroupTypeComponentRule.postExecuteRefGroupTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefGroupTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefGroupTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupTypeDO is needed in the request");
		}

		RefGroupTypeDO theRefGroupTypeDO = (RefGroupTypeDO) txnTransferObj.getTxnPayload().getRefGroupTypeDO();
		if (null == theRefGroupTypeDO.getKey() || theRefGroupTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefGroupTypeDO.key should not be null");
		}

		if (null == theRefGroupTypeDO.getConfigLanguageCodeKey()
				|| theRefGroupTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGroupTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefGroupTypeDO.getValue() || theRefGroupTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefGroupTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefGroupTypeDO.getConfigLanguageCodeKey()
				|| theRefGroupTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefGroupTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefGroupTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefGroupTypeDO.setCreatedTs(new Date());
		theRefGroupTypeDO.setUpdatedTs(new Date());
		theRefGroupTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefGroupTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefGroupTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefGroupTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefGroupTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefGroupTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefGroupTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefGroupTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefGroupTypeDO.version should not be null");
		}

		RefGroupTypeDO theRefGroupTypeDO = (RefGroupTypeDO) txnTransferObj.getTxnPayload().getRefGroupTypeDO();
		if (null == theRefGroupTypeDO.getKey() || theRefGroupTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefGroupTypeDO.key should not be null");
		}

		if (null == theRefGroupTypeDO.getConfigLanguageCodeKey()
				|| theRefGroupTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGroupTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefGroupTypeDO.getValue() || theRefGroupTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefGroupTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefGroupTypeDO.getConfigLanguageCodeKey()
				|| theRefGroupTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefGroupTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefGroupTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefGroupTypeDO.setUpdatedTs(new Date());
		theRefGroupTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefGroupTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefGroupTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefGroupTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupTypeDO is needed in the request");
		}
		RefGroupTypeDO theRefGroupTypeDO = (RefGroupTypeDO) txnTransferObj.getTxnPayload().getRefGroupTypeDO();
		if (null == theRefGroupTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefGroupTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefGroupTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefGroupTypeDO> pageRefGroupTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefGroupTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefGroupTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefGroupTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefGroupType reference list does not have any records in the database");
			}

			if ((pageRefGroupTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefGroupType, total number of pages is "
								+ pageRefGroupTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefGroupTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefGroupTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefGroupTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefGroupTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefGroupTypeDO.getSize());

			List<RefGroupTypeDO> dbimageRefGroupTypeDOlist = pageRefGroupTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefGroupTypeDO> clonedRefGroupTypeDOList = null;
			if (null != dbimageRefGroupTypeDOlist && dbimageRefGroupTypeDOlist.size() > 0) {
				clonedRefGroupTypeDOList = new ArrayList<RefGroupTypeDO>();
				Iterator<RefGroupTypeDO> itr = dbimageRefGroupTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefGroupTypeDO theRefGroupTypeDO = new RefGroupTypeDO(itr.next());
					clonedRefGroupTypeDOList.add(theRefGroupTypeDO);
				}
			}

			if (null == clonedRefGroupTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefGroupType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefGroupTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefGroupType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefGroupTypeDOList(clonedRefGroupTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFGROUPTYPE_BUSKEY")
	public Page<RefGroupTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefGroupTypeDO> pageRefGroupTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefGroupTypeDO = this.theRefGroupTypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefGroupTypeDO = this.theRefGroupTypeRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefGroupTypeDO = this.theRefGroupTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefGroupTypeDO;
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
	 *             if RefGroupTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefGroupTypeDO reqRefGroupTypeDO = (RefGroupTypeDO) txnTransferObj.getTxnPayload().getRefGroupTypeDO();
			theRefGroupTypeComponentRule.preValidateRefGroupTypefindByBusinessKey(txnTransferObj);
			theRefGroupTypeComponentRule.preExecuteRefGroupTypefindByBusinessKey(txnTransferObj);

			RefGroupTypeDO dbimageRefGroupTypeDO = executeRepositoryQuery(reqRefGroupTypeDO.getConfigLanguageCodeKey(),
					reqRefGroupTypeDO.getKey(), reqRefGroupTypeDO.getInquiryFilter());

			if (null == dbimageRefGroupTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefGroupTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefGroupTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefGroupTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefGroupTypeDO(new RefGroupTypeDO(dbimageRefGroupTypeDO));
			}

			theRefGroupTypeComponentRule.postExecuteRefGroupTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefGroupTypeDO returns the populated RefGroupTypeDO object
	 */
	@CacheResult(cacheName = "REFGROUPTYPE_BUSKEY")
	public RefGroupTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefGroupTypeDO dbimageRefGroupTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefGroupTypeDO = this.theRefGroupTypeRepository.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefGroupTypeDO = this.theRefGroupTypeRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefGroupTypeDO = this.theRefGroupTypeRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefGroupTypeDO;
	}

	/**
	 * perform the common validation that RefGroupTypeDO,
	 * RefGroupTypeDO.configLanguageCodeKey and RefGroupTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefGroupTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupTypeDO is needed in the request");
		}
		RefGroupTypeDO theRefGroupTypeDO = (RefGroupTypeDO) txnTransferObj.getTxnPayload().getRefGroupTypeDO();
		if (null == theRefGroupTypeDO.getKey() || theRefGroupTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefGroupTypeDO.key should not be null");
		}

		if (null == theRefGroupTypeDO.getConfigLanguageCodeKey()
				|| theRefGroupTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGroupTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefGroupTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefGroupTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefGroupTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefGroupTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefGroupTypeDO and
	 * RefGroupTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefGroupTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupTypeDO is needed in the request");
		}
		RefGroupTypeDO theRefGroupTypeDO = (RefGroupTypeDO) txnTransferObj.getTxnPayload().getRefGroupTypeDO();

		if (null == theRefGroupTypeDO.getConfigLanguageCodeKey()
				|| theRefGroupTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGroupTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefGroupTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefGroupTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefGroupTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefGroupTypeDO().getInquiryFilter());
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
