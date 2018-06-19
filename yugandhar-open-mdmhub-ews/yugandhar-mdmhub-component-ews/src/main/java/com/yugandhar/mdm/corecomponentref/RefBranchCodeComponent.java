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
import com.yugandhar.mdm.extern.dobj.RefBranchCodeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefBranchCodeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefBranchCodeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefBranchCodeRepository theRefBranchCodeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefBranchCodeComponentRule theRefBranchCodeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefBranchCodeComponent() {
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
	 *             if RefBranchCodeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefBranchCodeComponentRule.prevalidateRefBranchCodeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefBranchCodeDO reqRefBranchCodeDO = (RefBranchCodeDO) txnTransferObj.getTxnPayload().getRefBranchCodeDO();
			if (null == reqRefBranchCodeDO.getPrimaryKeyDO()
					|| null == reqRefBranchCodeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefBranchCodeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefBranchCodeDO.setIdPk(reqRefBranchCodeDO.getPrimaryKeyDO().getIdPk());
				RefBranchCodeDO dbimageRefBranchCodeDO = entityManager.find(RefBranchCodeDO.class,
						reqRefBranchCodeDO.getIdPk());
				if (null != dbimageRefBranchCodeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefBranchCodeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefBranchCodeComponentRule.preExecuteRefBranchCodeCompPersist(reqRefBranchCodeDO, txnTransferObj);
			entityManager.persist(reqRefBranchCodeDO);
			entityManager.flush();
			reqRefBranchCodeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefBranchCodeDO(new RefBranchCodeDO(reqRefBranchCodeDO));
			theRefBranchCodeComponentRule.postExecuteRefBranchCodeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefBranchCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefBranchCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBranchCodeComponent.persist failed unexpectedly");
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
	 *             if RefBranchCodeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefBranchCodeComponentRule.PrevalidateRefBranchCodeCompMerge(txnTransferObj);
			RefBranchCodeDO reqRefBranchCodeDO = (RefBranchCodeDO) txnTransferObj.getTxnPayload().getRefBranchCodeDO();
			RefBranchCodeDO dbimageRefBranchCodeDO = entityManager.find(RefBranchCodeDO.class,
					reqRefBranchCodeDO.getIdPk());
			if (null == dbimageRefBranchCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBranchCodeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefBranchCodeDO);
			BeanUtils.copyProperties(reqRefBranchCodeDO, dbimageRefBranchCodeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefBranchCodeDO);
			theRefBranchCodeComponentRule.preExecuteRefBranchCodeCompMerge(reqRefBranchCodeDO, dbimageRefBranchCodeDO,
					txnTransferObj);
			RefBranchCodeDO mergedRefBranchCodeDO = entityManager.merge(dbimageRefBranchCodeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefBranchCodeDO(new RefBranchCodeDO(mergedRefBranchCodeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefBranchCodeComponentRule.postExecuteRefBranchCodeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefBranchCodeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefBranchCodeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefBranchCodeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBranchCodeComponent.merge failed unexpectedly");
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
	 *             if RefBranchCodeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefBranchCodeComponentRule.prevalidateRefBranchCodeCompFindById(txnTransferObj);
			RefBranchCodeDO reqRefBranchCodeDO = (RefBranchCodeDO) txnTransferObj.getTxnPayload().getRefBranchCodeDO();
			RefBranchCodeDO dbimageRefBranchCodeDO = entityManager.find(RefBranchCodeDO.class,
					reqRefBranchCodeDO.getIdPk());
			if (null == dbimageRefBranchCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBranchCodeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefBranchCodeDO(new RefBranchCodeDO(dbimageRefBranchCodeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefBranchCodeComponentRule.postExecuteRefBranchCodeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBranchCodeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefBranchCodeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBranchCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefBranchCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBranchCodeDO is needed in the request");
		}

		RefBranchCodeDO theRefBranchCodeDO = (RefBranchCodeDO) txnTransferObj.getTxnPayload().getRefBranchCodeDO();
		if (null == theRefBranchCodeDO.getKey() || theRefBranchCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBranchCodeDO.key should not be null");
		}

		if (null == theRefBranchCodeDO.getConfigLanguageCodeKey()
				|| theRefBranchCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBranchCodeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefBranchCodeDO.getValue() || theRefBranchCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefBranchCodeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefBranchCodeDO.getConfigLanguageCodeKey()
				|| theRefBranchCodeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefBranchCodeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefBranchCodeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefBranchCodeDO.setCreatedTs(new Date());
		theRefBranchCodeDO.setUpdatedTs(new Date());
		theRefBranchCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefBranchCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefBranchCodeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBranchCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBranchCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBranchCodeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefBranchCodeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefBranchCodeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefBranchCodeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefBranchCodeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefBranchCodeDO.version should not be null");
		}

		RefBranchCodeDO theRefBranchCodeDO = (RefBranchCodeDO) txnTransferObj.getTxnPayload().getRefBranchCodeDO();
		if (null == theRefBranchCodeDO.getKey() || theRefBranchCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBranchCodeDO.key should not be null");
		}

		if (null == theRefBranchCodeDO.getConfigLanguageCodeKey()
				|| theRefBranchCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBranchCodeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefBranchCodeDO.getValue() || theRefBranchCodeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefBranchCodeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefBranchCodeDO.getConfigLanguageCodeKey()
				|| theRefBranchCodeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefBranchCodeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefBranchCodeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefBranchCodeDO.setUpdatedTs(new Date());
		theRefBranchCodeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefBranchCodeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefBranchCodeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBranchCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefBranchCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBranchCodeDO is needed in the request");
		}
		RefBranchCodeDO theRefBranchCodeDO = (RefBranchCodeDO) txnTransferObj.getTxnPayload().getRefBranchCodeDO();
		if (null == theRefBranchCodeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefBranchCodeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefBranchCodeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBranchCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefBranchCodeDO> pageRefBranchCodeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefBranchCodeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefBranchCodeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefBranchCodeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBranchCode reference list does not have any records in the database");
			}

			if ((pageRefBranchCodeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefBranchCode, total number of pages is "
								+ pageRefBranchCodeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefBranchCodeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefBranchCodeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefBranchCodeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefBranchCodeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefBranchCodeDO.getSize());

			List<RefBranchCodeDO> dbimageRefBranchCodeDOlist = pageRefBranchCodeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefBranchCodeDO> clonedRefBranchCodeDOList = null;
			if (null != dbimageRefBranchCodeDOlist && dbimageRefBranchCodeDOlist.size() > 0) {
				clonedRefBranchCodeDOList = new ArrayList<RefBranchCodeDO>();
				Iterator<RefBranchCodeDO> itr = dbimageRefBranchCodeDOlist.iterator();
				while (itr.hasNext()) {
					RefBranchCodeDO theRefBranchCodeDO = new RefBranchCodeDO(itr.next());
					clonedRefBranchCodeDOList.add(theRefBranchCodeDO);
				}
			}

			if (null == clonedRefBranchCodeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBranchCode reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefBranchCodeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBranchCode reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefBranchCodeDOList(clonedRefBranchCodeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBranchCodeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFBRANCHCODE_BUSKEY")
	public Page<RefBranchCodeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefBranchCodeDO> pageRefBranchCodeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefBranchCodeDO = this.theRefBranchCodeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefBranchCodeDO = this.theRefBranchCodeRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefBranchCodeDO = this.theRefBranchCodeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefBranchCodeDO;
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
	 *             if RefBranchCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefBranchCodeDO reqRefBranchCodeDO = (RefBranchCodeDO) txnTransferObj.getTxnPayload().getRefBranchCodeDO();
			theRefBranchCodeComponentRule.preValidateRefBranchCodefindByBusinessKey(txnTransferObj);
			theRefBranchCodeComponentRule.preExecuteRefBranchCodefindByBusinessKey(txnTransferObj);

			RefBranchCodeDO dbimageRefBranchCodeDO = executeRepositoryQuery(
					reqRefBranchCodeDO.getConfigLanguageCodeKey(), reqRefBranchCodeDO.getKey(),
					reqRefBranchCodeDO.getInquiryFilter());

			if (null == dbimageRefBranchCodeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBranchCodeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefBranchCodeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefBranchCodeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefBranchCodeDO(new RefBranchCodeDO(dbimageRefBranchCodeDO));
			}

			theRefBranchCodeComponentRule.postExecuteRefBranchCodefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBranchCodeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefBranchCodeDO returns the populated RefBranchCodeDO object
	 */
	@CacheResult(cacheName = "REFBRANCHCODE_BUSKEY")
	public RefBranchCodeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefBranchCodeDO dbimageRefBranchCodeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefBranchCodeDO = this.theRefBranchCodeRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefBranchCodeDO = this.theRefBranchCodeRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefBranchCodeDO = this.theRefBranchCodeRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefBranchCodeDO;
	}

	/**
	 * perform the common validation that RefBranchCodeDO,
	 * RefBranchCodeDO.configLanguageCodeKey and RefBranchCodeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBranchCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBranchCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBranchCodeDO is needed in the request");
		}
		RefBranchCodeDO theRefBranchCodeDO = (RefBranchCodeDO) txnTransferObj.getTxnPayload().getRefBranchCodeDO();
		if (null == theRefBranchCodeDO.getKey() || theRefBranchCodeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBranchCodeDO.key should not be null");
		}

		if (null == theRefBranchCodeDO.getConfigLanguageCodeKey()
				|| theRefBranchCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBranchCodeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefBranchCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefBranchCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefBranchCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefBranchCodeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefBranchCodeDO and
	 * RefBranchCodeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBranchCodeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBranchCodeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBranchCodeDO is needed in the request");
		}
		RefBranchCodeDO theRefBranchCodeDO = (RefBranchCodeDO) txnTransferObj.getTxnPayload().getRefBranchCodeDO();

		if (null == theRefBranchCodeDO.getConfigLanguageCodeKey()
				|| theRefBranchCodeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBranchCodeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefBranchCodeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefBranchCodeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefBranchCodeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefBranchCodeDO().getInquiryFilter());
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
