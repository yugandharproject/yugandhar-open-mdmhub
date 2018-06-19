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
import com.yugandhar.mdm.extern.dobj.RefPropertyLeReltypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefPropertyLeReltypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefPropertyLeReltypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefPropertyLeReltypeRepository theRefPropertyLeReltypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefPropertyLeReltypeComponentRule theRefPropertyLeReltypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefPropertyLeReltypeComponent() {
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
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefPropertyLeReltypeComponentRule.prevalidateRefPropertyLeReltypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefPropertyLeReltypeDO reqRefPropertyLeReltypeDO = (RefPropertyLeReltypeDO) txnTransferObj.getTxnPayload()
					.getRefPropertyLeReltypeDO();
			if (null == reqRefPropertyLeReltypeDO.getPrimaryKeyDO()
					|| null == reqRefPropertyLeReltypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefPropertyLeReltypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefPropertyLeReltypeDO.setIdPk(reqRefPropertyLeReltypeDO.getPrimaryKeyDO().getIdPk());
				RefPropertyLeReltypeDO dbimageRefPropertyLeReltypeDO = entityManager.find(RefPropertyLeReltypeDO.class,
						reqRefPropertyLeReltypeDO.getIdPk());
				if (null != dbimageRefPropertyLeReltypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefPropertyLeReltypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefPropertyLeReltypeComponentRule.preExecuteRefPropertyLeReltypeCompPersist(reqRefPropertyLeReltypeDO,
					txnTransferObj);
			entityManager.persist(reqRefPropertyLeReltypeDO);
			entityManager.flush();
			reqRefPropertyLeReltypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefPropertyLeReltypeDO(new RefPropertyLeReltypeDO(reqRefPropertyLeReltypeDO));
			theRefPropertyLeReltypeComponentRule.postExecuteRefPropertyLeReltypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPropertyLeReltypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPropertyLeReltypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPropertyLeReltypeComponent.persist failed unexpectedly");
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
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefPropertyLeReltypeComponentRule.PrevalidateRefPropertyLeReltypeCompMerge(txnTransferObj);
			RefPropertyLeReltypeDO reqRefPropertyLeReltypeDO = (RefPropertyLeReltypeDO) txnTransferObj.getTxnPayload()
					.getRefPropertyLeReltypeDO();
			RefPropertyLeReltypeDO dbimageRefPropertyLeReltypeDO = entityManager.find(RefPropertyLeReltypeDO.class,
					reqRefPropertyLeReltypeDO.getIdPk());
			if (null == dbimageRefPropertyLeReltypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPropertyLeReltypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefPropertyLeReltypeDO);
			BeanUtils.copyProperties(reqRefPropertyLeReltypeDO, dbimageRefPropertyLeReltypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefPropertyLeReltypeDO);
			theRefPropertyLeReltypeComponentRule.preExecuteRefPropertyLeReltypeCompMerge(reqRefPropertyLeReltypeDO,
					dbimageRefPropertyLeReltypeDO, txnTransferObj);
			RefPropertyLeReltypeDO mergedRefPropertyLeReltypeDO = entityManager.merge(dbimageRefPropertyLeReltypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefPropertyLeReltypeDO(new RefPropertyLeReltypeDO(mergedRefPropertyLeReltypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPropertyLeReltypeComponentRule.postExecuteRefPropertyLeReltypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefPropertyLeReltypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPropertyLeReltypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPropertyLeReltypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPropertyLeReltypeComponent.merge failed unexpectedly");
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
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefPropertyLeReltypeComponentRule.prevalidateRefPropertyLeReltypeCompFindById(txnTransferObj);
			RefPropertyLeReltypeDO reqRefPropertyLeReltypeDO = (RefPropertyLeReltypeDO) txnTransferObj.getTxnPayload()
					.getRefPropertyLeReltypeDO();
			RefPropertyLeReltypeDO dbimageRefPropertyLeReltypeDO = entityManager.find(RefPropertyLeReltypeDO.class,
					reqRefPropertyLeReltypeDO.getIdPk());
			if (null == dbimageRefPropertyLeReltypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPropertyLeReltypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefPropertyLeReltypeDO(new RefPropertyLeReltypeDO(dbimageRefPropertyLeReltypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPropertyLeReltypeComponentRule.postExecuteRefPropertyLeReltypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPropertyLeReltypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefPropertyLeReltypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPropertyLeReltypeDO is needed in the request");
		}

		RefPropertyLeReltypeDO theRefPropertyLeReltypeDO = (RefPropertyLeReltypeDO) txnTransferObj.getTxnPayload()
				.getRefPropertyLeReltypeDO();
		if (null == theRefPropertyLeReltypeDO.getKey() || theRefPropertyLeReltypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPropertyLeReltypeDO.key should not be null");
		}

		if (null == theRefPropertyLeReltypeDO.getConfigLanguageCodeKey()
				|| theRefPropertyLeReltypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPropertyLeReltypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPropertyLeReltypeDO.getValue() || theRefPropertyLeReltypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPropertyLeReltypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPropertyLeReltypeDO.getConfigLanguageCodeKey()
				|| theRefPropertyLeReltypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPropertyLeReltypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPropertyLeReltypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPropertyLeReltypeDO.setCreatedTs(new Date());
		theRefPropertyLeReltypeDO.setUpdatedTs(new Date());
		theRefPropertyLeReltypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPropertyLeReltypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefPropertyLeReltypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPropertyLeReltypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPropertyLeReltypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefPropertyLeReltypeDO.version should not be null");
		}

		RefPropertyLeReltypeDO theRefPropertyLeReltypeDO = (RefPropertyLeReltypeDO) txnTransferObj.getTxnPayload()
				.getRefPropertyLeReltypeDO();
		if (null == theRefPropertyLeReltypeDO.getKey() || theRefPropertyLeReltypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPropertyLeReltypeDO.key should not be null");
		}

		if (null == theRefPropertyLeReltypeDO.getConfigLanguageCodeKey()
				|| theRefPropertyLeReltypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPropertyLeReltypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPropertyLeReltypeDO.getValue() || theRefPropertyLeReltypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPropertyLeReltypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPropertyLeReltypeDO.getConfigLanguageCodeKey()
				|| theRefPropertyLeReltypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPropertyLeReltypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPropertyLeReltypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPropertyLeReltypeDO.setUpdatedTs(new Date());
		theRefPropertyLeReltypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPropertyLeReltypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefPropertyLeReltypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPropertyLeReltypeDO is needed in the request");
		}
		RefPropertyLeReltypeDO theRefPropertyLeReltypeDO = (RefPropertyLeReltypeDO) txnTransferObj.getTxnPayload()
				.getRefPropertyLeReltypeDO();
		if (null == theRefPropertyLeReltypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPropertyLeReltypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefPropertyLeReltypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefPropertyLeReltypeDO> pageRefPropertyLeReltypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefPropertyLeReltypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPropertyLeReltype reference list does not have any records in the database");
			}

			if ((pageRefPropertyLeReltypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefPropertyLeReltype, total number of pages is "
								+ pageRefPropertyLeReltypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefPropertyLeReltypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefPropertyLeReltypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefPropertyLeReltypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefPropertyLeReltypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefPropertyLeReltypeDO.getSize());

			List<RefPropertyLeReltypeDO> dbimageRefPropertyLeReltypeDOlist = pageRefPropertyLeReltypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefPropertyLeReltypeDO> clonedRefPropertyLeReltypeDOList = null;
			if (null != dbimageRefPropertyLeReltypeDOlist && dbimageRefPropertyLeReltypeDOlist.size() > 0) {
				clonedRefPropertyLeReltypeDOList = new ArrayList<RefPropertyLeReltypeDO>();
				Iterator<RefPropertyLeReltypeDO> itr = dbimageRefPropertyLeReltypeDOlist.iterator();
				while (itr.hasNext()) {
					RefPropertyLeReltypeDO theRefPropertyLeReltypeDO = new RefPropertyLeReltypeDO(itr.next());
					clonedRefPropertyLeReltypeDOList.add(theRefPropertyLeReltypeDO);
				}
			}

			if (null == clonedRefPropertyLeReltypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPropertyLeReltype reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefPropertyLeReltypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPropertyLeReltype reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefPropertyLeReltypeDOList(clonedRefPropertyLeReltypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPropertyLeReltypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFPROPERTYLERELTYPE_BUSKEY")
	public Page<RefPropertyLeReltypeDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefPropertyLeReltypeDO> pageRefPropertyLeReltypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefPropertyLeReltypeDO = this.theRefPropertyLeReltypeRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefPropertyLeReltypeDO = this.theRefPropertyLeReltypeRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefPropertyLeReltypeDO = this.theRefPropertyLeReltypeRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefPropertyLeReltypeDO;
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
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefPropertyLeReltypeDO reqRefPropertyLeReltypeDO = (RefPropertyLeReltypeDO) txnTransferObj.getTxnPayload()
					.getRefPropertyLeReltypeDO();
			theRefPropertyLeReltypeComponentRule.preValidateRefPropertyLeReltypefindByBusinessKey(txnTransferObj);
			theRefPropertyLeReltypeComponentRule.preExecuteRefPropertyLeReltypefindByBusinessKey(txnTransferObj);

			RefPropertyLeReltypeDO dbimageRefPropertyLeReltypeDO = executeRepositoryQuery(
					reqRefPropertyLeReltypeDO.getConfigLanguageCodeKey(), reqRefPropertyLeReltypeDO.getKey(),
					reqRefPropertyLeReltypeDO.getInquiryFilter());

			if (null == dbimageRefPropertyLeReltypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPropertyLeReltypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefPropertyLeReltypeDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefPropertyLeReltypeDO(new RefPropertyLeReltypeDO(dbimageRefPropertyLeReltypeDO));
			}

			theRefPropertyLeReltypeComponentRule.postExecuteRefPropertyLeReltypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPropertyLeReltypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefPropertyLeReltypeDO returns the populated RefPropertyLeReltypeDO object
	 */
	@CacheResult(cacheName = "REFPROPERTYLERELTYPE_BUSKEY")
	public RefPropertyLeReltypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefPropertyLeReltypeDO dbimageRefPropertyLeReltypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefPropertyLeReltypeDO = this.theRefPropertyLeReltypeRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefPropertyLeReltypeDO = this.theRefPropertyLeReltypeRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefPropertyLeReltypeDO = this.theRefPropertyLeReltypeRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefPropertyLeReltypeDO;
	}

	/**
	 * perform the common validation that RefPropertyLeReltypeDO,
	 * RefPropertyLeReltypeDO.configLanguageCodeKey and RefPropertyLeReltypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPropertyLeReltypeDO is needed in the request");
		}
		RefPropertyLeReltypeDO theRefPropertyLeReltypeDO = (RefPropertyLeReltypeDO) txnTransferObj.getTxnPayload()
				.getRefPropertyLeReltypeDO();
		if (null == theRefPropertyLeReltypeDO.getKey() || theRefPropertyLeReltypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPropertyLeReltypeDO.key should not be null");
		}

		if (null == theRefPropertyLeReltypeDO.getConfigLanguageCodeKey()
				|| theRefPropertyLeReltypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPropertyLeReltypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefPropertyLeReltypeDO and
	 * RefPropertyLeReltypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPropertyLeReltypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPropertyLeReltypeDO is needed in the request");
		}
		RefPropertyLeReltypeDO theRefPropertyLeReltypeDO = (RefPropertyLeReltypeDO) txnTransferObj.getTxnPayload()
				.getRefPropertyLeReltypeDO();

		if (null == theRefPropertyLeReltypeDO.getConfigLanguageCodeKey()
				|| theRefPropertyLeReltypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPropertyLeReltypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPropertyLeReltypeDO().getInquiryFilter());
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
