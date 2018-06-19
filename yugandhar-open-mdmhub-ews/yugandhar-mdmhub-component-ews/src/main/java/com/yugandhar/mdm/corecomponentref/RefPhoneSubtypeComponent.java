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
import com.yugandhar.mdm.extern.dobj.RefPhoneSubtypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefPhoneSubtypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefPhoneSubtypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefPhoneSubtypeRepository theRefPhoneSubtypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefPhoneSubtypeComponentRule theRefPhoneSubtypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefPhoneSubtypeComponent() {
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
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefPhoneSubtypeComponentRule.prevalidateRefPhoneSubtypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefPhoneSubtypeDO reqRefPhoneSubtypeDO = (RefPhoneSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefPhoneSubtypeDO();
			if (null == reqRefPhoneSubtypeDO.getPrimaryKeyDO()
					|| null == reqRefPhoneSubtypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefPhoneSubtypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefPhoneSubtypeDO.setIdPk(reqRefPhoneSubtypeDO.getPrimaryKeyDO().getIdPk());
				RefPhoneSubtypeDO dbimageRefPhoneSubtypeDO = entityManager.find(RefPhoneSubtypeDO.class,
						reqRefPhoneSubtypeDO.getIdPk());
				if (null != dbimageRefPhoneSubtypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefPhoneSubtypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefPhoneSubtypeComponentRule.preExecuteRefPhoneSubtypeCompPersist(reqRefPhoneSubtypeDO, txnTransferObj);
			entityManager.persist(reqRefPhoneSubtypeDO);
			entityManager.flush();
			reqRefPhoneSubtypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefPhoneSubtypeDO(new RefPhoneSubtypeDO(reqRefPhoneSubtypeDO));
			theRefPhoneSubtypeComponentRule.postExecuteRefPhoneSubtypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPhoneSubtypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPhoneSubtypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneSubtypeComponent.persist failed unexpectedly");
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
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefPhoneSubtypeComponentRule.PrevalidateRefPhoneSubtypeCompMerge(txnTransferObj);
			RefPhoneSubtypeDO reqRefPhoneSubtypeDO = (RefPhoneSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefPhoneSubtypeDO();
			RefPhoneSubtypeDO dbimageRefPhoneSubtypeDO = entityManager.find(RefPhoneSubtypeDO.class,
					reqRefPhoneSubtypeDO.getIdPk());
			if (null == dbimageRefPhoneSubtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPhoneSubtypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefPhoneSubtypeDO);
			BeanUtils.copyProperties(reqRefPhoneSubtypeDO, dbimageRefPhoneSubtypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefPhoneSubtypeDO);
			theRefPhoneSubtypeComponentRule.preExecuteRefPhoneSubtypeCompMerge(reqRefPhoneSubtypeDO,
					dbimageRefPhoneSubtypeDO, txnTransferObj);
			RefPhoneSubtypeDO mergedRefPhoneSubtypeDO = entityManager.merge(dbimageRefPhoneSubtypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefPhoneSubtypeDO(new RefPhoneSubtypeDO(mergedRefPhoneSubtypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPhoneSubtypeComponentRule.postExecuteRefPhoneSubtypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefPhoneSubtypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPhoneSubtypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPhoneSubtypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneSubtypeComponent.merge failed unexpectedly");
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
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefPhoneSubtypeComponentRule.prevalidateRefPhoneSubtypeCompFindById(txnTransferObj);
			RefPhoneSubtypeDO reqRefPhoneSubtypeDO = (RefPhoneSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefPhoneSubtypeDO();
			RefPhoneSubtypeDO dbimageRefPhoneSubtypeDO = entityManager.find(RefPhoneSubtypeDO.class,
					reqRefPhoneSubtypeDO.getIdPk());
			if (null == dbimageRefPhoneSubtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPhoneSubtypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefPhoneSubtypeDO(new RefPhoneSubtypeDO(dbimageRefPhoneSubtypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPhoneSubtypeComponentRule.postExecuteRefPhoneSubtypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneSubtypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefPhoneSubtypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneSubtypeDO is needed in the request");
		}

		RefPhoneSubtypeDO theRefPhoneSubtypeDO = (RefPhoneSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefPhoneSubtypeDO();
		if (null == theRefPhoneSubtypeDO.getKey() || theRefPhoneSubtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPhoneSubtypeDO.key should not be null");
		}

		if (null == theRefPhoneSubtypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPhoneSubtypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPhoneSubtypeDO.getValue() || theRefPhoneSubtypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPhoneSubtypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPhoneSubtypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneSubtypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPhoneSubtypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPhoneSubtypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPhoneSubtypeDO.setCreatedTs(new Date());
		theRefPhoneSubtypeDO.setUpdatedTs(new Date());
		theRefPhoneSubtypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPhoneSubtypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefPhoneSubtypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneSubtypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPhoneSubtypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefPhoneSubtypeDO.version should not be null");
		}

		RefPhoneSubtypeDO theRefPhoneSubtypeDO = (RefPhoneSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefPhoneSubtypeDO();
		if (null == theRefPhoneSubtypeDO.getKey() || theRefPhoneSubtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPhoneSubtypeDO.key should not be null");
		}

		if (null == theRefPhoneSubtypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPhoneSubtypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPhoneSubtypeDO.getValue() || theRefPhoneSubtypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPhoneSubtypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPhoneSubtypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneSubtypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPhoneSubtypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPhoneSubtypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPhoneSubtypeDO.setUpdatedTs(new Date());
		theRefPhoneSubtypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPhoneSubtypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefPhoneSubtypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneSubtypeDO is needed in the request");
		}
		RefPhoneSubtypeDO theRefPhoneSubtypeDO = (RefPhoneSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefPhoneSubtypeDO();
		if (null == theRefPhoneSubtypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPhoneSubtypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefPhoneSubtypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefPhoneSubtypeDO> pageRefPhoneSubtypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefPhoneSubtypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPhoneSubtype reference list does not have any records in the database");
			}

			if ((pageRefPhoneSubtypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefPhoneSubtype, total number of pages is "
								+ pageRefPhoneSubtypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefPhoneSubtypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefPhoneSubtypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefPhoneSubtypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefPhoneSubtypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefPhoneSubtypeDO.getSize());

			List<RefPhoneSubtypeDO> dbimageRefPhoneSubtypeDOlist = pageRefPhoneSubtypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefPhoneSubtypeDO> clonedRefPhoneSubtypeDOList = null;
			if (null != dbimageRefPhoneSubtypeDOlist && dbimageRefPhoneSubtypeDOlist.size() > 0) {
				clonedRefPhoneSubtypeDOList = new ArrayList<RefPhoneSubtypeDO>();
				Iterator<RefPhoneSubtypeDO> itr = dbimageRefPhoneSubtypeDOlist.iterator();
				while (itr.hasNext()) {
					RefPhoneSubtypeDO theRefPhoneSubtypeDO = new RefPhoneSubtypeDO(itr.next());
					clonedRefPhoneSubtypeDOList.add(theRefPhoneSubtypeDO);
				}
			}

			if (null == clonedRefPhoneSubtypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPhoneSubtype reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefPhoneSubtypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPhoneSubtype reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefPhoneSubtypeDOList(clonedRefPhoneSubtypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneSubtypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFPHONESUBTYPE_BUSKEY")
	public Page<RefPhoneSubtypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefPhoneSubtypeDO> pageRefPhoneSubtypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefPhoneSubtypeDO = this.theRefPhoneSubtypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefPhoneSubtypeDO = this.theRefPhoneSubtypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefPhoneSubtypeDO = this.theRefPhoneSubtypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefPhoneSubtypeDO;
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
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefPhoneSubtypeDO reqRefPhoneSubtypeDO = (RefPhoneSubtypeDO) txnTransferObj.getTxnPayload()
					.getRefPhoneSubtypeDO();
			theRefPhoneSubtypeComponentRule.preValidateRefPhoneSubtypefindByBusinessKey(txnTransferObj);
			theRefPhoneSubtypeComponentRule.preExecuteRefPhoneSubtypefindByBusinessKey(txnTransferObj);

			RefPhoneSubtypeDO dbimageRefPhoneSubtypeDO = executeRepositoryQuery(
					reqRefPhoneSubtypeDO.getConfigLanguageCodeKey(), reqRefPhoneSubtypeDO.getKey(),
					reqRefPhoneSubtypeDO.getInquiryFilter());

			if (null == dbimageRefPhoneSubtypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPhoneSubtypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefPhoneSubtypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefPhoneSubtypeDO(new RefPhoneSubtypeDO(dbimageRefPhoneSubtypeDO));
			}

			theRefPhoneSubtypeComponentRule.postExecuteRefPhoneSubtypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPhoneSubtypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefPhoneSubtypeDO returns the populated RefPhoneSubtypeDO object
	 */
	@CacheResult(cacheName = "REFPHONESUBTYPE_BUSKEY")
	public RefPhoneSubtypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefPhoneSubtypeDO dbimageRefPhoneSubtypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefPhoneSubtypeDO = this.theRefPhoneSubtypeRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefPhoneSubtypeDO = this.theRefPhoneSubtypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefPhoneSubtypeDO = this.theRefPhoneSubtypeRepository.findByBusinessKeyAll(configLanguageCodeKey,
					key);

		}

		return dbimageRefPhoneSubtypeDO;
	}

	/**
	 * perform the common validation that RefPhoneSubtypeDO,
	 * RefPhoneSubtypeDO.configLanguageCodeKey and RefPhoneSubtypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneSubtypeDO is needed in the request");
		}
		RefPhoneSubtypeDO theRefPhoneSubtypeDO = (RefPhoneSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefPhoneSubtypeDO();
		if (null == theRefPhoneSubtypeDO.getKey() || theRefPhoneSubtypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPhoneSubtypeDO.key should not be null");
		}

		if (null == theRefPhoneSubtypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPhoneSubtypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefPhoneSubtypeDO and
	 * RefPhoneSubtypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPhoneSubtypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPhoneSubtypeDO is needed in the request");
		}
		RefPhoneSubtypeDO theRefPhoneSubtypeDO = (RefPhoneSubtypeDO) txnTransferObj.getTxnPayload()
				.getRefPhoneSubtypeDO();

		if (null == theRefPhoneSubtypeDO.getConfigLanguageCodeKey()
				|| theRefPhoneSubtypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPhoneSubtypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPhoneSubtypeDO().getInquiryFilter());
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
