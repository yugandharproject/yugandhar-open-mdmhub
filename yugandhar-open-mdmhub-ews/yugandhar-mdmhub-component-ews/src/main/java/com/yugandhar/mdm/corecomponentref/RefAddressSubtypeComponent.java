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
import com.yugandhar.mdm.extern.dobj.RefAddressSubtypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefAddressSubtypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefAddressSubtypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefAddressSubtypeRepository theRefAddressSubtypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefAddressSubtypeComponentRule theRefAddressSubtypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefAddressSubtypeComponent() {
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
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefAddressSubtypeComponentRule.prevalidateRefAddressSubtypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefAddressSubtypeDO reqRefAddressSubtypeDO = (RefAddressSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefAddressSubtypeDO();
			if (null == reqRefAddressSubtypeDO.getPrimaryKeyDO()
					|| null == reqRefAddressSubtypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefAddressSubtypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefAddressSubtypeDO.setIdPk(reqRefAddressSubtypeDO.getPrimaryKeyDO().getIdPk());
				RefAddressSubtypeDO dbimageRefAddressSubtypeDO = entityManager.find(RefAddressSubtypeDO.class,
						reqRefAddressSubtypeDO.getIdPk());
				if (null != dbimageRefAddressSubtypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefAddressSubtypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefAddressSubtypeComponentRule.preExecuteRefAddressSubtypeCompPersist(reqRefAddressSubtypeDO,
					txnTransferObj);
			entityManager.persist(reqRefAddressSubtypeDO);
			entityManager.flush();
			reqRefAddressSubtypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefAddressSubtypeDO(new RefAddressSubtypeDO(reqRefAddressSubtypeDO));
			theRefAddressSubtypeComponentRule.postExecuteRefAddressSubtypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAddressSubtypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAddressSubtypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressSubtypeComponent.persist failed unexpectedly");
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
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefAddressSubtypeComponentRule.PrevalidateRefAddressSubtypeCompMerge(txnTransferObj);
			RefAddressSubtypeDO reqRefAddressSubtypeDO = (RefAddressSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefAddressSubtypeDO();
			RefAddressSubtypeDO dbimageRefAddressSubtypeDO = entityManager.find(RefAddressSubtypeDO.class,
					reqRefAddressSubtypeDO.getIdPk());
			if (null == dbimageRefAddressSubtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAddressSubtypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefAddressSubtypeDO);
			BeanUtils.copyProperties(reqRefAddressSubtypeDO, dbimageRefAddressSubtypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefAddressSubtypeDO);
			theRefAddressSubtypeComponentRule.preExecuteRefAddressSubtypeCompMerge(reqRefAddressSubtypeDO,
					dbimageRefAddressSubtypeDO, txnTransferObj);
			RefAddressSubtypeDO mergedRefAddressSubtypeDO = entityManager.merge(dbimageRefAddressSubtypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefAddressSubtypeDO(new RefAddressSubtypeDO(mergedRefAddressSubtypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAddressSubtypeComponentRule.postExecuteRefAddressSubtypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefAddressSubtypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefAddressSubtypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefAddressSubtypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressSubtypeComponent.merge failed unexpectedly");
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
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefAddressSubtypeComponentRule.prevalidateRefAddressSubtypeCompFindById(txnTransferObj);
			RefAddressSubtypeDO reqRefAddressSubtypeDO = (RefAddressSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefAddressSubtypeDO();
			RefAddressSubtypeDO dbimageRefAddressSubtypeDO = entityManager.find(RefAddressSubtypeDO.class,
					reqRefAddressSubtypeDO.getIdPk());
			if (null == dbimageRefAddressSubtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAddressSubtypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefAddressSubtypeDO(new RefAddressSubtypeDO(dbimageRefAddressSubtypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefAddressSubtypeComponentRule.postExecuteRefAddressSubtypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressSubtypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefAddressSubtypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAddressSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressSubtypeDO is needed in the request");
		}

		RefAddressSubtypeDO theRefAddressSubtypeDO = (RefAddressSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefAddressSubtypeDO();
		if (null == theRefAddressSubtypeDO.getKey() || theRefAddressSubtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAddressSubtypeDO.key should not be null");
		}

		if (null == theRefAddressSubtypeDO.getConfigLanguageCodeKey()
				|| theRefAddressSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAddressSubtypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAddressSubtypeDO.getValue() || theRefAddressSubtypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAddressSubtypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAddressSubtypeDO.getConfigLanguageCodeKey()
				|| theRefAddressSubtypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAddressSubtypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAddressSubtypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAddressSubtypeDO.setCreatedTs(new Date());
		theRefAddressSubtypeDO.setUpdatedTs(new Date());
		theRefAddressSubtypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAddressSubtypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefAddressSubtypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAddressSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressSubtypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAddressSubtypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefAddressSubtypeDO.version should not be null");
		}

		RefAddressSubtypeDO theRefAddressSubtypeDO = (RefAddressSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefAddressSubtypeDO();
		if (null == theRefAddressSubtypeDO.getKey() || theRefAddressSubtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAddressSubtypeDO.key should not be null");
		}

		if (null == theRefAddressSubtypeDO.getConfigLanguageCodeKey()
				|| theRefAddressSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAddressSubtypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefAddressSubtypeDO.getValue() || theRefAddressSubtypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefAddressSubtypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefAddressSubtypeDO.getConfigLanguageCodeKey()
				|| theRefAddressSubtypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefAddressSubtypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefAddressSubtypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefAddressSubtypeDO.setUpdatedTs(new Date());
		theRefAddressSubtypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefAddressSubtypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefAddressSubtypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefAddressSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressSubtypeDO is needed in the request");
		}
		RefAddressSubtypeDO theRefAddressSubtypeDO = (RefAddressSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefAddressSubtypeDO();
		if (null == theRefAddressSubtypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefAddressSubtypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefAddressSubtypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefAddressSubtypeDO> pageRefAddressSubtypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefAddressSubtypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAddressSubtype reference list does not have any records in the database");
			}

			if ((pageRefAddressSubtypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefAddressSubtype, total number of pages is "
								+ pageRefAddressSubtypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefAddressSubtypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefAddressSubtypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefAddressSubtypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefAddressSubtypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefAddressSubtypeDO.getSize());

			List<RefAddressSubtypeDO> dbimageRefAddressSubtypeDOlist = pageRefAddressSubtypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefAddressSubtypeDO> clonedRefAddressSubtypeDOList = null;
			if (null != dbimageRefAddressSubtypeDOlist && dbimageRefAddressSubtypeDOlist.size() > 0) {
				clonedRefAddressSubtypeDOList = new ArrayList<RefAddressSubtypeDO>();
				Iterator<RefAddressSubtypeDO> itr = dbimageRefAddressSubtypeDOlist.iterator();
				while (itr.hasNext()) {
					RefAddressSubtypeDO theRefAddressSubtypeDO = new RefAddressSubtypeDO(itr.next());
					clonedRefAddressSubtypeDOList.add(theRefAddressSubtypeDO);
				}
			}

			if (null == clonedRefAddressSubtypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAddressSubtype reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefAddressSubtypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefAddressSubtype reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefAddressSubtypeDOList(clonedRefAddressSubtypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressSubtypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFADDRESSSUBTYPE_BUSKEY")
	public Page<RefAddressSubtypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefAddressSubtypeDO> pageRefAddressSubtypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefAddressSubtypeDO = this.theRefAddressSubtypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefAddressSubtypeDO = this.theRefAddressSubtypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefAddressSubtypeDO = this.theRefAddressSubtypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefAddressSubtypeDO;
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
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefAddressSubtypeDO reqRefAddressSubtypeDO = (RefAddressSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefAddressSubtypeDO();
			theRefAddressSubtypeComponentRule.preValidateRefAddressSubtypefindByBusinessKey(txnTransferObj);
			theRefAddressSubtypeComponentRule.preExecuteRefAddressSubtypefindByBusinessKey(txnTransferObj);

			RefAddressSubtypeDO dbimageRefAddressSubtypeDO = executeRepositoryQuery(
					reqRefAddressSubtypeDO.getConfigLanguageCodeKey(), reqRefAddressSubtypeDO.getKey(),
					reqRefAddressSubtypeDO.getInquiryFilter());

			if (null == dbimageRefAddressSubtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefAddressSubtypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefAddressSubtypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefAddressSubtypeDO(new RefAddressSubtypeDO(dbimageRefAddressSubtypeDO));
			}

			theRefAddressSubtypeComponentRule.postExecuteRefAddressSubtypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefAddressSubtypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefAddressSubtypeDO returns the populated RefAddressSubtypeDO object
	 */
	@CacheResult(cacheName = "REFADDRESSSUBTYPE_BUSKEY")
	public RefAddressSubtypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefAddressSubtypeDO dbimageRefAddressSubtypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefAddressSubtypeDO = this.theRefAddressSubtypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefAddressSubtypeDO = this.theRefAddressSubtypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefAddressSubtypeDO = this.theRefAddressSubtypeRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefAddressSubtypeDO;
	}

	/**
	 * perform the common validation that RefAddressSubtypeDO,
	 * RefAddressSubtypeDO.configLanguageCodeKey and RefAddressSubtypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAddressSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressSubtypeDO is needed in the request");
		}
		RefAddressSubtypeDO theRefAddressSubtypeDO = (RefAddressSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefAddressSubtypeDO();
		if (null == theRefAddressSubtypeDO.getKey() || theRefAddressSubtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefAddressSubtypeDO.key should not be null");
		}

		if (null == theRefAddressSubtypeDO.getConfigLanguageCodeKey()
				|| theRefAddressSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAddressSubtypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefAddressSubtypeDO and
	 * RefAddressSubtypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefAddressSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefAddressSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefAddressSubtypeDO is needed in the request");
		}
		RefAddressSubtypeDO theRefAddressSubtypeDO = (RefAddressSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefAddressSubtypeDO();

		if (null == theRefAddressSubtypeDO.getConfigLanguageCodeKey()
				|| theRefAddressSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefAddressSubtypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefAddressSubtypeDO().getInquiryFilter());
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
