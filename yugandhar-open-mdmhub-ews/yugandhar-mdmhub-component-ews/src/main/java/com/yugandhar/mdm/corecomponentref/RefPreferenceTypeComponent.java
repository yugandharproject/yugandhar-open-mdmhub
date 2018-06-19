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
import com.yugandhar.mdm.extern.dobj.RefPreferenceTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefPreferenceTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefPreferenceTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefPreferenceTypeRepository theRefPreferenceTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefPreferenceTypeComponentRule theRefPreferenceTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefPreferenceTypeComponent() {
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
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefPreferenceTypeComponentRule.prevalidateRefPreferenceTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefPreferenceTypeDO reqRefPreferenceTypeDO = (RefPreferenceTypeDO) txnTransferObj.getTxnPayload()
					.getRefPreferenceTypeDO();
			if (null == reqRefPreferenceTypeDO.getPrimaryKeyDO()
					|| null == reqRefPreferenceTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefPreferenceTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefPreferenceTypeDO.setIdPk(reqRefPreferenceTypeDO.getPrimaryKeyDO().getIdPk());
				RefPreferenceTypeDO dbimageRefPreferenceTypeDO = entityManager.find(RefPreferenceTypeDO.class,
						reqRefPreferenceTypeDO.getIdPk());
				if (null != dbimageRefPreferenceTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefPreferenceTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefPreferenceTypeComponentRule.preExecuteRefPreferenceTypeCompPersist(reqRefPreferenceTypeDO,
					txnTransferObj);
			entityManager.persist(reqRefPreferenceTypeDO);
			entityManager.flush();
			reqRefPreferenceTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefPreferenceTypeDO(new RefPreferenceTypeDO(reqRefPreferenceTypeDO));
			theRefPreferenceTypeComponentRule.postExecuteRefPreferenceTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPreferenceTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPreferenceTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPreferenceTypeComponent.persist failed unexpectedly");
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
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefPreferenceTypeComponentRule.PrevalidateRefPreferenceTypeCompMerge(txnTransferObj);
			RefPreferenceTypeDO reqRefPreferenceTypeDO = (RefPreferenceTypeDO) txnTransferObj.getTxnPayload()
					.getRefPreferenceTypeDO();
			RefPreferenceTypeDO dbimageRefPreferenceTypeDO = entityManager.find(RefPreferenceTypeDO.class,
					reqRefPreferenceTypeDO.getIdPk());
			if (null == dbimageRefPreferenceTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPreferenceTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefPreferenceTypeDO);
			BeanUtils.copyProperties(reqRefPreferenceTypeDO, dbimageRefPreferenceTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefPreferenceTypeDO);
			theRefPreferenceTypeComponentRule.preExecuteRefPreferenceTypeCompMerge(reqRefPreferenceTypeDO,
					dbimageRefPreferenceTypeDO, txnTransferObj);
			RefPreferenceTypeDO mergedRefPreferenceTypeDO = entityManager.merge(dbimageRefPreferenceTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefPreferenceTypeDO(new RefPreferenceTypeDO(mergedRefPreferenceTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPreferenceTypeComponentRule.postExecuteRefPreferenceTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefPreferenceTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPreferenceTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPreferenceTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPreferenceTypeComponent.merge failed unexpectedly");
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
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefPreferenceTypeComponentRule.prevalidateRefPreferenceTypeCompFindById(txnTransferObj);
			RefPreferenceTypeDO reqRefPreferenceTypeDO = (RefPreferenceTypeDO) txnTransferObj.getTxnPayload()
					.getRefPreferenceTypeDO();
			RefPreferenceTypeDO dbimageRefPreferenceTypeDO = entityManager.find(RefPreferenceTypeDO.class,
					reqRefPreferenceTypeDO.getIdPk());
			if (null == dbimageRefPreferenceTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPreferenceTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefPreferenceTypeDO(new RefPreferenceTypeDO(dbimageRefPreferenceTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPreferenceTypeComponentRule.postExecuteRefPreferenceTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPreferenceTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefPreferenceTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPreferenceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPreferenceTypeDO is needed in the request");
		}

		RefPreferenceTypeDO theRefPreferenceTypeDO = (RefPreferenceTypeDO) txnTransferObj.getTxnPayload()
				.getRefPreferenceTypeDO();
		if (null == theRefPreferenceTypeDO.getKey() || theRefPreferenceTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPreferenceTypeDO.key should not be null");
		}

		if (null == theRefPreferenceTypeDO.getConfigLanguageCodeKey()
				|| theRefPreferenceTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPreferenceTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPreferenceTypeDO.getValue() || theRefPreferenceTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPreferenceTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPreferenceTypeDO.getConfigLanguageCodeKey()
				|| theRefPreferenceTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPreferenceTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPreferenceTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPreferenceTypeDO.setCreatedTs(new Date());
		theRefPreferenceTypeDO.setUpdatedTs(new Date());
		theRefPreferenceTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPreferenceTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefPreferenceTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPreferenceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPreferenceTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPreferenceTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefPreferenceTypeDO.version should not be null");
		}

		RefPreferenceTypeDO theRefPreferenceTypeDO = (RefPreferenceTypeDO) txnTransferObj.getTxnPayload()
				.getRefPreferenceTypeDO();
		if (null == theRefPreferenceTypeDO.getKey() || theRefPreferenceTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPreferenceTypeDO.key should not be null");
		}

		if (null == theRefPreferenceTypeDO.getConfigLanguageCodeKey()
				|| theRefPreferenceTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPreferenceTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPreferenceTypeDO.getValue() || theRefPreferenceTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPreferenceTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPreferenceTypeDO.getConfigLanguageCodeKey()
				|| theRefPreferenceTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPreferenceTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPreferenceTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPreferenceTypeDO.setUpdatedTs(new Date());
		theRefPreferenceTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPreferenceTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefPreferenceTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPreferenceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPreferenceTypeDO is needed in the request");
		}
		RefPreferenceTypeDO theRefPreferenceTypeDO = (RefPreferenceTypeDO) txnTransferObj.getTxnPayload()
				.getRefPreferenceTypeDO();
		if (null == theRefPreferenceTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPreferenceTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefPreferenceTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefPreferenceTypeDO> pageRefPreferenceTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefPreferenceTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPreferenceType reference list does not have any records in the database");
			}

			if ((pageRefPreferenceTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefPreferenceType, total number of pages is "
								+ pageRefPreferenceTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefPreferenceTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefPreferenceTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefPreferenceTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefPreferenceTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefPreferenceTypeDO.getSize());

			List<RefPreferenceTypeDO> dbimageRefPreferenceTypeDOlist = pageRefPreferenceTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefPreferenceTypeDO> clonedRefPreferenceTypeDOList = null;
			if (null != dbimageRefPreferenceTypeDOlist && dbimageRefPreferenceTypeDOlist.size() > 0) {
				clonedRefPreferenceTypeDOList = new ArrayList<RefPreferenceTypeDO>();
				Iterator<RefPreferenceTypeDO> itr = dbimageRefPreferenceTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefPreferenceTypeDO theRefPreferenceTypeDO = new RefPreferenceTypeDO(itr.next());
					clonedRefPreferenceTypeDOList.add(theRefPreferenceTypeDO);
				}
			}

			if (null == clonedRefPreferenceTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPreferenceType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefPreferenceTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPreferenceType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefPreferenceTypeDOList(clonedRefPreferenceTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPreferenceTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFPREFERENCETYPE_BUSKEY")
	public Page<RefPreferenceTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefPreferenceTypeDO> pageRefPreferenceTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefPreferenceTypeDO = this.theRefPreferenceTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefPreferenceTypeDO = this.theRefPreferenceTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefPreferenceTypeDO = this.theRefPreferenceTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefPreferenceTypeDO;
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
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefPreferenceTypeDO reqRefPreferenceTypeDO = (RefPreferenceTypeDO) txnTransferObj.getTxnPayload()
					.getRefPreferenceTypeDO();
			theRefPreferenceTypeComponentRule.preValidateRefPreferenceTypefindByBusinessKey(txnTransferObj);
			theRefPreferenceTypeComponentRule.preExecuteRefPreferenceTypefindByBusinessKey(txnTransferObj);

			RefPreferenceTypeDO dbimageRefPreferenceTypeDO = executeRepositoryQuery(
					reqRefPreferenceTypeDO.getConfigLanguageCodeKey(), reqRefPreferenceTypeDO.getKey(),
					reqRefPreferenceTypeDO.getInquiryFilter());

			if (null == dbimageRefPreferenceTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPreferenceTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefPreferenceTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefPreferenceTypeDO(new RefPreferenceTypeDO(dbimageRefPreferenceTypeDO));
			}

			theRefPreferenceTypeComponentRule.postExecuteRefPreferenceTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPreferenceTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefPreferenceTypeDO returns the populated RefPreferenceTypeDO object
	 */
	@CacheResult(cacheName = "REFPREFERENCETYPE_BUSKEY")
	public RefPreferenceTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefPreferenceTypeDO dbimageRefPreferenceTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefPreferenceTypeDO = this.theRefPreferenceTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefPreferenceTypeDO = this.theRefPreferenceTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefPreferenceTypeDO = this.theRefPreferenceTypeRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefPreferenceTypeDO;
	}

	/**
	 * perform the common validation that RefPreferenceTypeDO,
	 * RefPreferenceTypeDO.configLanguageCodeKey and RefPreferenceTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPreferenceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPreferenceTypeDO is needed in the request");
		}
		RefPreferenceTypeDO theRefPreferenceTypeDO = (RefPreferenceTypeDO) txnTransferObj.getTxnPayload()
				.getRefPreferenceTypeDO();
		if (null == theRefPreferenceTypeDO.getKey() || theRefPreferenceTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPreferenceTypeDO.key should not be null");
		}

		if (null == theRefPreferenceTypeDO.getConfigLanguageCodeKey()
				|| theRefPreferenceTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPreferenceTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefPreferenceTypeDO and
	 * RefPreferenceTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPreferenceTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPreferenceTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPreferenceTypeDO is needed in the request");
		}
		RefPreferenceTypeDO theRefPreferenceTypeDO = (RefPreferenceTypeDO) txnTransferObj.getTxnPayload()
				.getRefPreferenceTypeDO();

		if (null == theRefPreferenceTypeDO.getConfigLanguageCodeKey()
				|| theRefPreferenceTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPreferenceTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPreferenceTypeDO().getInquiryFilter());
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
