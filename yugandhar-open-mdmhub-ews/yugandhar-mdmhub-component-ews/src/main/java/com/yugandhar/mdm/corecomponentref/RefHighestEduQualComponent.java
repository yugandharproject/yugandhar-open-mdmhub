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
import com.yugandhar.mdm.extern.dobj.RefHighestEduQualDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefHighestEduQualDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefHighestEduQualComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefHighestEduQualRepository theRefHighestEduQualRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefHighestEduQualComponentRule theRefHighestEduQualComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefHighestEduQualComponent() {
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
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefHighestEduQualComponentRule.prevalidateRefHighestEduQualCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefHighestEduQualDO reqRefHighestEduQualDO = (RefHighestEduQualDO) txnTransferObj.getTxnPayload()
					.getRefHighestEduQualDO();
			if (null == reqRefHighestEduQualDO.getPrimaryKeyDO()
					|| null == reqRefHighestEduQualDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefHighestEduQualDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefHighestEduQualDO.setIdPk(reqRefHighestEduQualDO.getPrimaryKeyDO().getIdPk());
				RefHighestEduQualDO dbimageRefHighestEduQualDO = entityManager.find(RefHighestEduQualDO.class,
						reqRefHighestEduQualDO.getIdPk());
				if (null != dbimageRefHighestEduQualDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefHighestEduQualComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefHighestEduQualComponentRule.preExecuteRefHighestEduQualCompPersist(reqRefHighestEduQualDO,
					txnTransferObj);
			entityManager.persist(reqRefHighestEduQualDO);
			entityManager.flush();
			reqRefHighestEduQualDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefHighestEduQualDO(new RefHighestEduQualDO(reqRefHighestEduQualDO));
			theRefHighestEduQualComponentRule.postExecuteRefHighestEduQualCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefHighestEduQualComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefHighestEduQualComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefHighestEduQualComponent.persist failed unexpectedly");
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
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefHighestEduQualComponentRule.PrevalidateRefHighestEduQualCompMerge(txnTransferObj);
			RefHighestEduQualDO reqRefHighestEduQualDO = (RefHighestEduQualDO) txnTransferObj.getTxnPayload()
					.getRefHighestEduQualDO();
			RefHighestEduQualDO dbimageRefHighestEduQualDO = entityManager.find(RefHighestEduQualDO.class,
					reqRefHighestEduQualDO.getIdPk());
			if (null == dbimageRefHighestEduQualDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefHighestEduQualComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefHighestEduQualDO);
			BeanUtils.copyProperties(reqRefHighestEduQualDO, dbimageRefHighestEduQualDO, strIgnoreProperties);
			entityManager.detach(dbimageRefHighestEduQualDO);
			theRefHighestEduQualComponentRule.preExecuteRefHighestEduQualCompMerge(reqRefHighestEduQualDO,
					dbimageRefHighestEduQualDO, txnTransferObj);
			RefHighestEduQualDO mergedRefHighestEduQualDO = entityManager.merge(dbimageRefHighestEduQualDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefHighestEduQualDO(new RefHighestEduQualDO(mergedRefHighestEduQualDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefHighestEduQualComponentRule.postExecuteRefHighestEduQualCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefHighestEduQualComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefHighestEduQualComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefHighestEduQualComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefHighestEduQualComponent.merge failed unexpectedly");
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
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefHighestEduQualComponentRule.prevalidateRefHighestEduQualCompFindById(txnTransferObj);
			RefHighestEduQualDO reqRefHighestEduQualDO = (RefHighestEduQualDO) txnTransferObj.getTxnPayload()
					.getRefHighestEduQualDO();
			RefHighestEduQualDO dbimageRefHighestEduQualDO = entityManager.find(RefHighestEduQualDO.class,
					reqRefHighestEduQualDO.getIdPk());
			if (null == dbimageRefHighestEduQualDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefHighestEduQualComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefHighestEduQualDO(new RefHighestEduQualDO(dbimageRefHighestEduQualDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefHighestEduQualComponentRule.postExecuteRefHighestEduQualCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefHighestEduQualComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefHighestEduQualDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefHighestEduQualDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefHighestEduQualDO is needed in the request");
		}

		RefHighestEduQualDO theRefHighestEduQualDO = (RefHighestEduQualDO) txnTransferObj.getTxnPayload()
				.getRefHighestEduQualDO();
		if (null == theRefHighestEduQualDO.getKey() || theRefHighestEduQualDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefHighestEduQualDO.key should not be null");
		}

		if (null == theRefHighestEduQualDO.getConfigLanguageCodeKey()
				|| theRefHighestEduQualDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefHighestEduQualDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefHighestEduQualDO.getValue() || theRefHighestEduQualDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefHighestEduQualDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefHighestEduQualDO.getConfigLanguageCodeKey()
				|| theRefHighestEduQualDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefHighestEduQualDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefHighestEduQualDO.configLanguageCodeKey is not valid");
			}
		}

		theRefHighestEduQualDO.setCreatedTs(new Date());
		theRefHighestEduQualDO.setUpdatedTs(new Date());
		theRefHighestEduQualDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefHighestEduQualDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefHighestEduQualDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefHighestEduQualDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefHighestEduQualDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefHighestEduQualDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefHighestEduQualDO.version should not be null");
		}

		RefHighestEduQualDO theRefHighestEduQualDO = (RefHighestEduQualDO) txnTransferObj.getTxnPayload()
				.getRefHighestEduQualDO();
		if (null == theRefHighestEduQualDO.getKey() || theRefHighestEduQualDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefHighestEduQualDO.key should not be null");
		}

		if (null == theRefHighestEduQualDO.getConfigLanguageCodeKey()
				|| theRefHighestEduQualDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefHighestEduQualDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefHighestEduQualDO.getValue() || theRefHighestEduQualDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefHighestEduQualDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefHighestEduQualDO.getConfigLanguageCodeKey()
				|| theRefHighestEduQualDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefHighestEduQualDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefHighestEduQualDO.configLanguageCodeKey is not valid");
			}
		}

		theRefHighestEduQualDO.setUpdatedTs(new Date());
		theRefHighestEduQualDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefHighestEduQualDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefHighestEduQualDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefHighestEduQualDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefHighestEduQualDO is needed in the request");
		}
		RefHighestEduQualDO theRefHighestEduQualDO = (RefHighestEduQualDO) txnTransferObj.getTxnPayload()
				.getRefHighestEduQualDO();
		if (null == theRefHighestEduQualDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefHighestEduQualDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefHighestEduQualDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefHighestEduQualDO> pageRefHighestEduQualDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefHighestEduQualDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefHighestEduQual reference list does not have any records in the database");
			}

			if ((pageRefHighestEduQualDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefHighestEduQual, total number of pages is "
								+ pageRefHighestEduQualDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefHighestEduQualDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefHighestEduQualDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefHighestEduQualDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefHighestEduQualDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefHighestEduQualDO.getSize());

			List<RefHighestEduQualDO> dbimageRefHighestEduQualDOlist = pageRefHighestEduQualDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefHighestEduQualDO> clonedRefHighestEduQualDOList = null;
			if (null != dbimageRefHighestEduQualDOlist && dbimageRefHighestEduQualDOlist.size() > 0) {
				clonedRefHighestEduQualDOList = new ArrayList<RefHighestEduQualDO>();
				Iterator<RefHighestEduQualDO> itr = dbimageRefHighestEduQualDOlist.iterator();
				while (itr.hasNext()) {
					RefHighestEduQualDO theRefHighestEduQualDO = new RefHighestEduQualDO(itr.next());
					clonedRefHighestEduQualDOList.add(theRefHighestEduQualDO);
				}
			}

			if (null == clonedRefHighestEduQualDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefHighestEduQual reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefHighestEduQualDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefHighestEduQual reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefHighestEduQualDOList(clonedRefHighestEduQualDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefHighestEduQualComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFHIGHESTEDUQUAL_BUSKEY")
	public Page<RefHighestEduQualDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefHighestEduQualDO> pageRefHighestEduQualDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefHighestEduQualDO = this.theRefHighestEduQualRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefHighestEduQualDO = this.theRefHighestEduQualRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefHighestEduQualDO = this.theRefHighestEduQualRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefHighestEduQualDO;
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
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefHighestEduQualDO reqRefHighestEduQualDO = (RefHighestEduQualDO) txnTransferObj.getTxnPayload()
					.getRefHighestEduQualDO();
			theRefHighestEduQualComponentRule.preValidateRefHighestEduQualfindByBusinessKey(txnTransferObj);
			theRefHighestEduQualComponentRule.preExecuteRefHighestEduQualfindByBusinessKey(txnTransferObj);

			RefHighestEduQualDO dbimageRefHighestEduQualDO = executeRepositoryQuery(
					reqRefHighestEduQualDO.getConfigLanguageCodeKey(), reqRefHighestEduQualDO.getKey(),
					reqRefHighestEduQualDO.getInquiryFilter());

			if (null == dbimageRefHighestEduQualDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefHighestEduQualComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefHighestEduQualDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefHighestEduQualDO(new RefHighestEduQualDO(dbimageRefHighestEduQualDO));
			}

			theRefHighestEduQualComponentRule.postExecuteRefHighestEduQualfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefHighestEduQualComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefHighestEduQualDO returns the populated RefHighestEduQualDO object
	 */
	@CacheResult(cacheName = "REFHIGHESTEDUQUAL_BUSKEY")
	public RefHighestEduQualDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefHighestEduQualDO dbimageRefHighestEduQualDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefHighestEduQualDO = this.theRefHighestEduQualRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefHighestEduQualDO = this.theRefHighestEduQualRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefHighestEduQualDO = this.theRefHighestEduQualRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefHighestEduQualDO;
	}

	/**
	 * perform the common validation that RefHighestEduQualDO,
	 * RefHighestEduQualDO.configLanguageCodeKey and RefHighestEduQualDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefHighestEduQualDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefHighestEduQualDO is needed in the request");
		}
		RefHighestEduQualDO theRefHighestEduQualDO = (RefHighestEduQualDO) txnTransferObj.getTxnPayload()
				.getRefHighestEduQualDO();
		if (null == theRefHighestEduQualDO.getKey() || theRefHighestEduQualDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefHighestEduQualDO.key should not be null");
		}

		if (null == theRefHighestEduQualDO.getConfigLanguageCodeKey()
				|| theRefHighestEduQualDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefHighestEduQualDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefHighestEduQualDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefHighestEduQualDO and
	 * RefHighestEduQualDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefHighestEduQualDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefHighestEduQualDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefHighestEduQualDO is needed in the request");
		}
		RefHighestEduQualDO theRefHighestEduQualDO = (RefHighestEduQualDO) txnTransferObj.getTxnPayload()
				.getRefHighestEduQualDO();

		if (null == theRefHighestEduQualDO.getConfigLanguageCodeKey()
				|| theRefHighestEduQualDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefHighestEduQualDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefHighestEduQualDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefHighestEduQualDO().getInquiryFilter());
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
