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
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefCountryIsoDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefCountryIsoComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefCountryIsoRepository theRefCountryIsoRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefCountryIsoComponentRule theRefCountryIsoComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefCountryIsoComponent() {
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
	 *             if RefCountryIsoDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefCountryIsoComponentRule.prevalidateRefCountryIsoCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefCountryIsoDO reqRefCountryIsoDO = (RefCountryIsoDO) txnTransferObj.getTxnPayload().getRefCountryIsoDO();
			if (null == reqRefCountryIsoDO.getPrimaryKeyDO()
					|| null == reqRefCountryIsoDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefCountryIsoDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefCountryIsoDO.setIdPk(reqRefCountryIsoDO.getPrimaryKeyDO().getIdPk());
				RefCountryIsoDO dbimageRefCountryIsoDO = entityManager.find(RefCountryIsoDO.class,
						reqRefCountryIsoDO.getIdPk());
				if (null != dbimageRefCountryIsoDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefCountryIsoComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefCountryIsoComponentRule.preExecuteRefCountryIsoCompPersist(reqRefCountryIsoDO, txnTransferObj);
			entityManager.persist(reqRefCountryIsoDO);
			entityManager.flush();
			reqRefCountryIsoDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefCountryIsoDO(new RefCountryIsoDO(reqRefCountryIsoDO));
			theRefCountryIsoComponentRule.postExecuteRefCountryIsoCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefCountryIsoComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefCountryIsoComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCountryIsoComponent.persist failed unexpectedly");
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
	 *             if RefCountryIsoDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefCountryIsoComponentRule.PrevalidateRefCountryIsoCompMerge(txnTransferObj);
			RefCountryIsoDO reqRefCountryIsoDO = (RefCountryIsoDO) txnTransferObj.getTxnPayload().getRefCountryIsoDO();
			RefCountryIsoDO dbimageRefCountryIsoDO = entityManager.find(RefCountryIsoDO.class,
					reqRefCountryIsoDO.getIdPk());
			if (null == dbimageRefCountryIsoDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCountryIsoComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefCountryIsoDO);
			BeanUtils.copyProperties(reqRefCountryIsoDO, dbimageRefCountryIsoDO, strIgnoreProperties);
			entityManager.detach(dbimageRefCountryIsoDO);
			theRefCountryIsoComponentRule.preExecuteRefCountryIsoCompMerge(reqRefCountryIsoDO, dbimageRefCountryIsoDO,
					txnTransferObj);
			RefCountryIsoDO mergedRefCountryIsoDO = entityManager.merge(dbimageRefCountryIsoDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefCountryIsoDO(new RefCountryIsoDO(mergedRefCountryIsoDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefCountryIsoComponentRule.postExecuteRefCountryIsoCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefCountryIsoComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefCountryIsoComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefCountryIsoComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCountryIsoComponent.merge failed unexpectedly");
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
	 *             if RefCountryIsoDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefCountryIsoComponentRule.prevalidateRefCountryIsoCompFindById(txnTransferObj);
			RefCountryIsoDO reqRefCountryIsoDO = (RefCountryIsoDO) txnTransferObj.getTxnPayload().getRefCountryIsoDO();
			RefCountryIsoDO dbimageRefCountryIsoDO = entityManager.find(RefCountryIsoDO.class,
					reqRefCountryIsoDO.getIdPk());
			if (null == dbimageRefCountryIsoDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCountryIsoComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefCountryIsoDO(new RefCountryIsoDO(dbimageRefCountryIsoDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefCountryIsoComponentRule.postExecuteRefCountryIsoCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCountryIsoComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefCountryIsoDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCountryIsoDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefCountryIsoDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCountryIsoDO is needed in the request");
		}

		RefCountryIsoDO theRefCountryIsoDO = (RefCountryIsoDO) txnTransferObj.getTxnPayload().getRefCountryIsoDO();
		if (null == theRefCountryIsoDO.getKey() || theRefCountryIsoDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCountryIsoDO.key should not be null");
		}

		if (null == theRefCountryIsoDO.getConfigLanguageCodeKey()
				|| theRefCountryIsoDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCountryIsoDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefCountryIsoDO.getValue() || theRefCountryIsoDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefCountryIsoDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefCountryIsoDO.getConfigLanguageCodeKey()
				|| theRefCountryIsoDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefCountryIsoDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefCountryIsoDO.configLanguageCodeKey is not valid");
			}
		}

		theRefCountryIsoDO.setCreatedTs(new Date());
		theRefCountryIsoDO.setUpdatedTs(new Date());
		theRefCountryIsoDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefCountryIsoDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefCountryIsoDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCountryIsoDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCountryIsoDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCountryIsoDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefCountryIsoDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefCountryIsoDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefCountryIsoDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefCountryIsoDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefCountryIsoDO.version should not be null");
		}

		RefCountryIsoDO theRefCountryIsoDO = (RefCountryIsoDO) txnTransferObj.getTxnPayload().getRefCountryIsoDO();
		if (null == theRefCountryIsoDO.getKey() || theRefCountryIsoDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCountryIsoDO.key should not be null");
		}

		if (null == theRefCountryIsoDO.getConfigLanguageCodeKey()
				|| theRefCountryIsoDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCountryIsoDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefCountryIsoDO.getValue() || theRefCountryIsoDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefCountryIsoDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefCountryIsoDO.getConfigLanguageCodeKey()
				|| theRefCountryIsoDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefCountryIsoDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefCountryIsoDO.configLanguageCodeKey is not valid");
			}
		}

		theRefCountryIsoDO.setUpdatedTs(new Date());
		theRefCountryIsoDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefCountryIsoDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefCountryIsoDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCountryIsoDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefCountryIsoDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCountryIsoDO is needed in the request");
		}
		RefCountryIsoDO theRefCountryIsoDO = (RefCountryIsoDO) txnTransferObj.getTxnPayload().getRefCountryIsoDO();
		if (null == theRefCountryIsoDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefCountryIsoDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefCountryIsoDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCountryIsoDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefCountryIsoDO> pageRefCountryIsoDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefCountryIsoDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefCountryIsoDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefCountryIsoDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCountryIso reference list does not have any records in the database");
			}

			if ((pageRefCountryIsoDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefCountryIso, total number of pages is "
								+ pageRefCountryIsoDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefCountryIsoDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefCountryIsoDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefCountryIsoDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefCountryIsoDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefCountryIsoDO.getSize());

			List<RefCountryIsoDO> dbimageRefCountryIsoDOlist = pageRefCountryIsoDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefCountryIsoDO> clonedRefCountryIsoDOList = null;
			if (null != dbimageRefCountryIsoDOlist && dbimageRefCountryIsoDOlist.size() > 0) {
				clonedRefCountryIsoDOList = new ArrayList<RefCountryIsoDO>();
				Iterator<RefCountryIsoDO> itr = dbimageRefCountryIsoDOlist.iterator();
				while (itr.hasNext()) {
					RefCountryIsoDO theRefCountryIsoDO = new RefCountryIsoDO(itr.next());
					clonedRefCountryIsoDOList.add(theRefCountryIsoDO);
				}
			}

			if (null == clonedRefCountryIsoDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCountryIso reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefCountryIsoDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefCountryIso reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefCountryIsoDOList(clonedRefCountryIsoDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCountryIsoComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFCOUNTRYISO_BUSKEY")
	public Page<RefCountryIsoDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefCountryIsoDO> pageRefCountryIsoDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefCountryIsoDO = this.theRefCountryIsoRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefCountryIsoDO = this.theRefCountryIsoRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefCountryIsoDO = this.theRefCountryIsoRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefCountryIsoDO;
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
	 *             if RefCountryIsoDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefCountryIsoDO reqRefCountryIsoDO = (RefCountryIsoDO) txnTransferObj.getTxnPayload().getRefCountryIsoDO();
			theRefCountryIsoComponentRule.preValidateRefCountryIsofindByBusinessKey(txnTransferObj);
			theRefCountryIsoComponentRule.preExecuteRefCountryIsofindByBusinessKey(txnTransferObj);

			RefCountryIsoDO dbimageRefCountryIsoDO = executeRepositoryQuery(
					reqRefCountryIsoDO.getConfigLanguageCodeKey(), reqRefCountryIsoDO.getKey(),
					reqRefCountryIsoDO.getInquiryFilter());

			if (null == dbimageRefCountryIsoDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefCountryIsoComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefCountryIsoDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefCountryIsoDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefCountryIsoDO(new RefCountryIsoDO(dbimageRefCountryIsoDO));
			}

			theRefCountryIsoComponentRule.postExecuteRefCountryIsofindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefCountryIsoComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefCountryIsoDO returns the populated RefCountryIsoDO object
	 */
	@CacheResult(cacheName = "REFCOUNTRYISO_BUSKEY")
	public RefCountryIsoDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefCountryIsoDO dbimageRefCountryIsoDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefCountryIsoDO = this.theRefCountryIsoRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefCountryIsoDO = this.theRefCountryIsoRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefCountryIsoDO = this.theRefCountryIsoRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefCountryIsoDO;
	}

	/**
	 * perform the common validation that RefCountryIsoDO,
	 * RefCountryIsoDO.configLanguageCodeKey and RefCountryIsoDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCountryIsoDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCountryIsoDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCountryIsoDO is needed in the request");
		}
		RefCountryIsoDO theRefCountryIsoDO = (RefCountryIsoDO) txnTransferObj.getTxnPayload().getRefCountryIsoDO();
		if (null == theRefCountryIsoDO.getKey() || theRefCountryIsoDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefCountryIsoDO.key should not be null");
		}

		if (null == theRefCountryIsoDO.getConfigLanguageCodeKey()
				|| theRefCountryIsoDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCountryIsoDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefCountryIsoDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefCountryIsoDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefCountryIsoDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefCountryIsoDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefCountryIsoDO and
	 * RefCountryIsoDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefCountryIsoDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefCountryIsoDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefCountryIsoDO is needed in the request");
		}
		RefCountryIsoDO theRefCountryIsoDO = (RefCountryIsoDO) txnTransferObj.getTxnPayload().getRefCountryIsoDO();

		if (null == theRefCountryIsoDO.getConfigLanguageCodeKey()
				|| theRefCountryIsoDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefCountryIsoDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefCountryIsoDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefCountryIsoDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefCountryIsoDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefCountryIsoDO().getInquiryFilter());
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
