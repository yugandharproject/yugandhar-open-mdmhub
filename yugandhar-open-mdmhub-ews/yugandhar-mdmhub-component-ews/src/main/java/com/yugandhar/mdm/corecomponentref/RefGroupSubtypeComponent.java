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
import com.yugandhar.mdm.extern.dobj.RefGroupSubtypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefGroupSubtypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefGroupSubtypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefGroupSubtypeRepository theRefGroupSubtypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefGroupSubtypeComponentRule theRefGroupSubtypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefGroupSubtypeComponent() {
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
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefGroupSubtypeComponentRule.prevalidateRefGroupSubtypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefGroupSubtypeDO reqRefGroupSubtypeDO = (RefGroupSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefGroupSubtypeDO();
			if (null == reqRefGroupSubtypeDO.getPrimaryKeyDO()
					|| null == reqRefGroupSubtypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefGroupSubtypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefGroupSubtypeDO.setIdPk(reqRefGroupSubtypeDO.getPrimaryKeyDO().getIdPk());
				RefGroupSubtypeDO dbimageRefGroupSubtypeDO = entityManager.find(RefGroupSubtypeDO.class,
						reqRefGroupSubtypeDO.getIdPk());
				if (null != dbimageRefGroupSubtypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefGroupSubtypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefGroupSubtypeComponentRule.preExecuteRefGroupSubtypeCompPersist(reqRefGroupSubtypeDO, txnTransferObj);
			entityManager.persist(reqRefGroupSubtypeDO);
			entityManager.flush();
			reqRefGroupSubtypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefGroupSubtypeDO(new RefGroupSubtypeDO(reqRefGroupSubtypeDO));
			theRefGroupSubtypeComponentRule.postExecuteRefGroupSubtypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefGroupSubtypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefGroupSubtypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupSubtypeComponent.persist failed unexpectedly");
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
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefGroupSubtypeComponentRule.PrevalidateRefGroupSubtypeCompMerge(txnTransferObj);
			RefGroupSubtypeDO reqRefGroupSubtypeDO = (RefGroupSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefGroupSubtypeDO();
			RefGroupSubtypeDO dbimageRefGroupSubtypeDO = entityManager.find(RefGroupSubtypeDO.class,
					reqRefGroupSubtypeDO.getIdPk());
			if (null == dbimageRefGroupSubtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefGroupSubtypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefGroupSubtypeDO);
			BeanUtils.copyProperties(reqRefGroupSubtypeDO, dbimageRefGroupSubtypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefGroupSubtypeDO);
			theRefGroupSubtypeComponentRule.preExecuteRefGroupSubtypeCompMerge(reqRefGroupSubtypeDO,
					dbimageRefGroupSubtypeDO, txnTransferObj);
			RefGroupSubtypeDO mergedRefGroupSubtypeDO = entityManager.merge(dbimageRefGroupSubtypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefGroupSubtypeDO(new RefGroupSubtypeDO(mergedRefGroupSubtypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefGroupSubtypeComponentRule.postExecuteRefGroupSubtypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefGroupSubtypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefGroupSubtypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefGroupSubtypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupSubtypeComponent.merge failed unexpectedly");
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
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefGroupSubtypeComponentRule.prevalidateRefGroupSubtypeCompFindById(txnTransferObj);
			RefGroupSubtypeDO reqRefGroupSubtypeDO = (RefGroupSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefGroupSubtypeDO();
			RefGroupSubtypeDO dbimageRefGroupSubtypeDO = entityManager.find(RefGroupSubtypeDO.class,
					reqRefGroupSubtypeDO.getIdPk());
			if (null == dbimageRefGroupSubtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefGroupSubtypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefGroupSubtypeDO(new RefGroupSubtypeDO(dbimageRefGroupSubtypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefGroupSubtypeComponentRule.postExecuteRefGroupSubtypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupSubtypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefGroupSubtypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefGroupSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupSubtypeDO is needed in the request");
		}

		RefGroupSubtypeDO theRefGroupSubtypeDO = (RefGroupSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefGroupSubtypeDO();
		if (null == theRefGroupSubtypeDO.getKey() || theRefGroupSubtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefGroupSubtypeDO.key should not be null");
		}

		if (null == theRefGroupSubtypeDO.getConfigLanguageCodeKey()
				|| theRefGroupSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGroupSubtypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefGroupSubtypeDO.getValue() || theRefGroupSubtypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefGroupSubtypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefGroupSubtypeDO.getConfigLanguageCodeKey()
				|| theRefGroupSubtypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefGroupSubtypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefGroupSubtypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefGroupSubtypeDO.setCreatedTs(new Date());
		theRefGroupSubtypeDO.setUpdatedTs(new Date());
		theRefGroupSubtypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefGroupSubtypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefGroupSubtypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefGroupSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupSubtypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefGroupSubtypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefGroupSubtypeDO.version should not be null");
		}

		RefGroupSubtypeDO theRefGroupSubtypeDO = (RefGroupSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefGroupSubtypeDO();
		if (null == theRefGroupSubtypeDO.getKey() || theRefGroupSubtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefGroupSubtypeDO.key should not be null");
		}

		if (null == theRefGroupSubtypeDO.getConfigLanguageCodeKey()
				|| theRefGroupSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGroupSubtypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefGroupSubtypeDO.getValue() || theRefGroupSubtypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefGroupSubtypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefGroupSubtypeDO.getConfigLanguageCodeKey()
				|| theRefGroupSubtypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefGroupSubtypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefGroupSubtypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefGroupSubtypeDO.setUpdatedTs(new Date());
		theRefGroupSubtypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefGroupSubtypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefGroupSubtypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefGroupSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupSubtypeDO is needed in the request");
		}
		RefGroupSubtypeDO theRefGroupSubtypeDO = (RefGroupSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefGroupSubtypeDO();
		if (null == theRefGroupSubtypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefGroupSubtypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefGroupSubtypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefGroupSubtypeDO> pageRefGroupSubtypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefGroupSubtypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefGroupSubtype reference list does not have any records in the database");
			}

			if ((pageRefGroupSubtypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefGroupSubtype, total number of pages is "
								+ pageRefGroupSubtypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefGroupSubtypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefGroupSubtypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefGroupSubtypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefGroupSubtypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefGroupSubtypeDO.getSize());

			List<RefGroupSubtypeDO> dbimageRefGroupSubtypeDOlist = pageRefGroupSubtypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefGroupSubtypeDO> clonedRefGroupSubtypeDOList = null;
			if (null != dbimageRefGroupSubtypeDOlist && dbimageRefGroupSubtypeDOlist.size() > 0) {
				clonedRefGroupSubtypeDOList = new ArrayList<RefGroupSubtypeDO>();
				Iterator<RefGroupSubtypeDO> itr = dbimageRefGroupSubtypeDOlist.iterator();
				while (itr.hasNext()) {
					RefGroupSubtypeDO theRefGroupSubtypeDO = new RefGroupSubtypeDO(itr.next());
					clonedRefGroupSubtypeDOList.add(theRefGroupSubtypeDO);
				}
			}

			if (null == clonedRefGroupSubtypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefGroupSubtype reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefGroupSubtypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefGroupSubtype reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefGroupSubtypeDOList(clonedRefGroupSubtypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupSubtypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFGROUPSUBTYPE_BUSKEY")
	public Page<RefGroupSubtypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefGroupSubtypeDO> pageRefGroupSubtypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefGroupSubtypeDO = this.theRefGroupSubtypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefGroupSubtypeDO = this.theRefGroupSubtypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefGroupSubtypeDO = this.theRefGroupSubtypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefGroupSubtypeDO;
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
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefGroupSubtypeDO reqRefGroupSubtypeDO = (RefGroupSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefGroupSubtypeDO();
			theRefGroupSubtypeComponentRule.preValidateRefGroupSubtypefindByBusinessKey(txnTransferObj);
			theRefGroupSubtypeComponentRule.preExecuteRefGroupSubtypefindByBusinessKey(txnTransferObj);

			RefGroupSubtypeDO dbimageRefGroupSubtypeDO = executeRepositoryQuery(
					reqRefGroupSubtypeDO.getConfigLanguageCodeKey(), reqRefGroupSubtypeDO.getKey(),
					reqRefGroupSubtypeDO.getInquiryFilter());

			if (null == dbimageRefGroupSubtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefGroupSubtypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefGroupSubtypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefGroupSubtypeDO(new RefGroupSubtypeDO(dbimageRefGroupSubtypeDO));
			}

			theRefGroupSubtypeComponentRule.postExecuteRefGroupSubtypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGroupSubtypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefGroupSubtypeDO returns the populated RefGroupSubtypeDO object
	 */
	@CacheResult(cacheName = "REFGROUPSUBTYPE_BUSKEY")
	public RefGroupSubtypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefGroupSubtypeDO dbimageRefGroupSubtypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefGroupSubtypeDO = this.theRefGroupSubtypeRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefGroupSubtypeDO = this.theRefGroupSubtypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefGroupSubtypeDO = this.theRefGroupSubtypeRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefGroupSubtypeDO;
	}

	/**
	 * perform the common validation that RefGroupSubtypeDO,
	 * RefGroupSubtypeDO.configLanguageCodeKey and RefGroupSubtypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefGroupSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupSubtypeDO is needed in the request");
		}
		RefGroupSubtypeDO theRefGroupSubtypeDO = (RefGroupSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefGroupSubtypeDO();
		if (null == theRefGroupSubtypeDO.getKey() || theRefGroupSubtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefGroupSubtypeDO.key should not be null");
		}

		if (null == theRefGroupSubtypeDO.getConfigLanguageCodeKey()
				|| theRefGroupSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGroupSubtypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefGroupSubtypeDO and
	 * RefGroupSubtypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGroupSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefGroupSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGroupSubtypeDO is needed in the request");
		}
		RefGroupSubtypeDO theRefGroupSubtypeDO = (RefGroupSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefGroupSubtypeDO();

		if (null == theRefGroupSubtypeDO.getConfigLanguageCodeKey()
				|| theRefGroupSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGroupSubtypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefGroupSubtypeDO().getInquiryFilter());
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
