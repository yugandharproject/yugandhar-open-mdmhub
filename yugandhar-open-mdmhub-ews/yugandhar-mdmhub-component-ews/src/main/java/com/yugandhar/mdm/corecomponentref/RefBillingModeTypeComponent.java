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
import com.yugandhar.mdm.extern.dobj.RefBillingModeTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefBillingModeTypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefBillingModeTypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefBillingModeTypeRepository theRefBillingModeTypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefBillingModeTypeComponentRule theRefBillingModeTypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefBillingModeTypeComponent() {
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
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefBillingModeTypeComponentRule.prevalidateRefBillingModeTypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefBillingModeTypeDO reqRefBillingModeTypeDO = (RefBillingModeTypeDO) txnTransferObj.getTxnPayload()
					.getRefBillingModeTypeDO();
			if (null == reqRefBillingModeTypeDO.getPrimaryKeyDO()
					|| null == reqRefBillingModeTypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefBillingModeTypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefBillingModeTypeDO.setIdPk(reqRefBillingModeTypeDO.getPrimaryKeyDO().getIdPk());
				RefBillingModeTypeDO dbimageRefBillingModeTypeDO = entityManager.find(RefBillingModeTypeDO.class,
						reqRefBillingModeTypeDO.getIdPk());
				if (null != dbimageRefBillingModeTypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefBillingModeTypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefBillingModeTypeComponentRule.preExecuteRefBillingModeTypeCompPersist(reqRefBillingModeTypeDO,
					txnTransferObj);
			entityManager.persist(reqRefBillingModeTypeDO);
			entityManager.flush();
			reqRefBillingModeTypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefBillingModeTypeDO(new RefBillingModeTypeDO(reqRefBillingModeTypeDO));
			theRefBillingModeTypeComponentRule.postExecuteRefBillingModeTypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefBillingModeTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefBillingModeTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBillingModeTypeComponent.persist failed unexpectedly");
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
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefBillingModeTypeComponentRule.PrevalidateRefBillingModeTypeCompMerge(txnTransferObj);
			RefBillingModeTypeDO reqRefBillingModeTypeDO = (RefBillingModeTypeDO) txnTransferObj.getTxnPayload()
					.getRefBillingModeTypeDO();
			RefBillingModeTypeDO dbimageRefBillingModeTypeDO = entityManager.find(RefBillingModeTypeDO.class,
					reqRefBillingModeTypeDO.getIdPk());
			if (null == dbimageRefBillingModeTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBillingModeTypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefBillingModeTypeDO);
			BeanUtils.copyProperties(reqRefBillingModeTypeDO, dbimageRefBillingModeTypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefBillingModeTypeDO);
			theRefBillingModeTypeComponentRule.preExecuteRefBillingModeTypeCompMerge(reqRefBillingModeTypeDO,
					dbimageRefBillingModeTypeDO, txnTransferObj);
			RefBillingModeTypeDO mergedRefBillingModeTypeDO = entityManager.merge(dbimageRefBillingModeTypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefBillingModeTypeDO(new RefBillingModeTypeDO(mergedRefBillingModeTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefBillingModeTypeComponentRule.postExecuteRefBillingModeTypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefBillingModeTypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefBillingModeTypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefBillingModeTypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBillingModeTypeComponent.merge failed unexpectedly");
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
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefBillingModeTypeComponentRule.prevalidateRefBillingModeTypeCompFindById(txnTransferObj);
			RefBillingModeTypeDO reqRefBillingModeTypeDO = (RefBillingModeTypeDO) txnTransferObj.getTxnPayload()
					.getRefBillingModeTypeDO();
			RefBillingModeTypeDO dbimageRefBillingModeTypeDO = entityManager.find(RefBillingModeTypeDO.class,
					reqRefBillingModeTypeDO.getIdPk());
			if (null == dbimageRefBillingModeTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBillingModeTypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefBillingModeTypeDO(new RefBillingModeTypeDO(dbimageRefBillingModeTypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefBillingModeTypeComponentRule.postExecuteRefBillingModeTypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBillingModeTypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefBillingModeTypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefBillingModeTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBillingModeTypeDO is needed in the request");
		}

		RefBillingModeTypeDO theRefBillingModeTypeDO = (RefBillingModeTypeDO) txnTransferObj.getTxnPayload()
				.getRefBillingModeTypeDO();
		if (null == theRefBillingModeTypeDO.getKey() || theRefBillingModeTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBillingModeTypeDO.key should not be null");
		}

		if (null == theRefBillingModeTypeDO.getConfigLanguageCodeKey()
				|| theRefBillingModeTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBillingModeTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefBillingModeTypeDO.getValue() || theRefBillingModeTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefBillingModeTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefBillingModeTypeDO.getConfigLanguageCodeKey()
				|| theRefBillingModeTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefBillingModeTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefBillingModeTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefBillingModeTypeDO.setCreatedTs(new Date());
		theRefBillingModeTypeDO.setUpdatedTs(new Date());
		theRefBillingModeTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefBillingModeTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefBillingModeTypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBillingModeTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBillingModeTypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefBillingModeTypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefBillingModeTypeDO.version should not be null");
		}

		RefBillingModeTypeDO theRefBillingModeTypeDO = (RefBillingModeTypeDO) txnTransferObj.getTxnPayload()
				.getRefBillingModeTypeDO();
		if (null == theRefBillingModeTypeDO.getKey() || theRefBillingModeTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBillingModeTypeDO.key should not be null");
		}

		if (null == theRefBillingModeTypeDO.getConfigLanguageCodeKey()
				|| theRefBillingModeTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBillingModeTypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefBillingModeTypeDO.getValue() || theRefBillingModeTypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefBillingModeTypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefBillingModeTypeDO.getConfigLanguageCodeKey()
				|| theRefBillingModeTypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefBillingModeTypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefBillingModeTypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefBillingModeTypeDO.setUpdatedTs(new Date());
		theRefBillingModeTypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefBillingModeTypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefBillingModeTypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefBillingModeTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBillingModeTypeDO is needed in the request");
		}
		RefBillingModeTypeDO theRefBillingModeTypeDO = (RefBillingModeTypeDO) txnTransferObj.getTxnPayload()
				.getRefBillingModeTypeDO();
		if (null == theRefBillingModeTypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefBillingModeTypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefBillingModeTypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefBillingModeTypeDO> pageRefBillingModeTypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefBillingModeTypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBillingModeType reference list does not have any records in the database");
			}

			if ((pageRefBillingModeTypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefBillingModeType, total number of pages is "
								+ pageRefBillingModeTypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefBillingModeTypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefBillingModeTypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefBillingModeTypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefBillingModeTypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefBillingModeTypeDO.getSize());

			List<RefBillingModeTypeDO> dbimageRefBillingModeTypeDOlist = pageRefBillingModeTypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefBillingModeTypeDO> clonedRefBillingModeTypeDOList = null;
			if (null != dbimageRefBillingModeTypeDOlist && dbimageRefBillingModeTypeDOlist.size() > 0) {
				clonedRefBillingModeTypeDOList = new ArrayList<RefBillingModeTypeDO>();
				Iterator<RefBillingModeTypeDO> itr = dbimageRefBillingModeTypeDOlist.iterator();
				while (itr.hasNext()) {
					RefBillingModeTypeDO theRefBillingModeTypeDO = new RefBillingModeTypeDO(itr.next());
					clonedRefBillingModeTypeDOList.add(theRefBillingModeTypeDO);
				}
			}

			if (null == clonedRefBillingModeTypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBillingModeType reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefBillingModeTypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefBillingModeType reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefBillingModeTypeDOList(clonedRefBillingModeTypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBillingModeTypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFBILLINGMODETYPE_BUSKEY")
	public Page<RefBillingModeTypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefBillingModeTypeDO> pageRefBillingModeTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefBillingModeTypeDO = this.theRefBillingModeTypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefBillingModeTypeDO = this.theRefBillingModeTypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefBillingModeTypeDO = this.theRefBillingModeTypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefBillingModeTypeDO;
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
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefBillingModeTypeDO reqRefBillingModeTypeDO = (RefBillingModeTypeDO) txnTransferObj.getTxnPayload()
					.getRefBillingModeTypeDO();
			theRefBillingModeTypeComponentRule.preValidateRefBillingModeTypefindByBusinessKey(txnTransferObj);
			theRefBillingModeTypeComponentRule.preExecuteRefBillingModeTypefindByBusinessKey(txnTransferObj);

			RefBillingModeTypeDO dbimageRefBillingModeTypeDO = executeRepositoryQuery(
					reqRefBillingModeTypeDO.getConfigLanguageCodeKey(), reqRefBillingModeTypeDO.getKey(),
					reqRefBillingModeTypeDO.getInquiryFilter());

			if (null == dbimageRefBillingModeTypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefBillingModeTypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefBillingModeTypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefBillingModeTypeDO(new RefBillingModeTypeDO(dbimageRefBillingModeTypeDO));
			}

			theRefBillingModeTypeComponentRule.postExecuteRefBillingModeTypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefBillingModeTypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefBillingModeTypeDO returns the populated RefBillingModeTypeDO object
	 */
	@CacheResult(cacheName = "REFBILLINGMODETYPE_BUSKEY")
	public RefBillingModeTypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefBillingModeTypeDO dbimageRefBillingModeTypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefBillingModeTypeDO = this.theRefBillingModeTypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefBillingModeTypeDO = this.theRefBillingModeTypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefBillingModeTypeDO = this.theRefBillingModeTypeRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefBillingModeTypeDO;
	}

	/**
	 * perform the common validation that RefBillingModeTypeDO,
	 * RefBillingModeTypeDO.configLanguageCodeKey and RefBillingModeTypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBillingModeTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBillingModeTypeDO is needed in the request");
		}
		RefBillingModeTypeDO theRefBillingModeTypeDO = (RefBillingModeTypeDO) txnTransferObj.getTxnPayload()
				.getRefBillingModeTypeDO();
		if (null == theRefBillingModeTypeDO.getKey() || theRefBillingModeTypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefBillingModeTypeDO.key should not be null");
		}

		if (null == theRefBillingModeTypeDO.getConfigLanguageCodeKey()
				|| theRefBillingModeTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBillingModeTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefBillingModeTypeDO and
	 * RefBillingModeTypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefBillingModeTypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefBillingModeTypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefBillingModeTypeDO is needed in the request");
		}
		RefBillingModeTypeDO theRefBillingModeTypeDO = (RefBillingModeTypeDO) txnTransferObj.getTxnPayload()
				.getRefBillingModeTypeDO();

		if (null == theRefBillingModeTypeDO.getConfigLanguageCodeKey()
				|| theRefBillingModeTypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefBillingModeTypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefBillingModeTypeDO().getInquiryFilter());
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
