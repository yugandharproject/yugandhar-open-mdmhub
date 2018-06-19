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
import com.yugandhar.mdm.extern.dobj.RefPrefixNameDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefPrefixNameDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefPrefixNameComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefPrefixNameRepository theRefPrefixNameRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefPrefixNameComponentRule theRefPrefixNameComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefPrefixNameComponent() {
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
	 *             if RefPrefixNameDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefPrefixNameComponentRule.prevalidateRefPrefixNameCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefPrefixNameDO reqRefPrefixNameDO = (RefPrefixNameDO) txnTransferObj.getTxnPayload().getRefPrefixNameDO();
			if (null == reqRefPrefixNameDO.getPrimaryKeyDO()
					|| null == reqRefPrefixNameDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefPrefixNameDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefPrefixNameDO.setIdPk(reqRefPrefixNameDO.getPrimaryKeyDO().getIdPk());
				RefPrefixNameDO dbimageRefPrefixNameDO = entityManager.find(RefPrefixNameDO.class,
						reqRefPrefixNameDO.getIdPk());
				if (null != dbimageRefPrefixNameDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefPrefixNameComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefPrefixNameComponentRule.preExecuteRefPrefixNameCompPersist(reqRefPrefixNameDO, txnTransferObj);
			entityManager.persist(reqRefPrefixNameDO);
			entityManager.flush();
			reqRefPrefixNameDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefPrefixNameDO(new RefPrefixNameDO(reqRefPrefixNameDO));
			theRefPrefixNameComponentRule.postExecuteRefPrefixNameCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPrefixNameComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPrefixNameComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPrefixNameComponent.persist failed unexpectedly");
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
	 *             if RefPrefixNameDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefPrefixNameComponentRule.PrevalidateRefPrefixNameCompMerge(txnTransferObj);
			RefPrefixNameDO reqRefPrefixNameDO = (RefPrefixNameDO) txnTransferObj.getTxnPayload().getRefPrefixNameDO();
			RefPrefixNameDO dbimageRefPrefixNameDO = entityManager.find(RefPrefixNameDO.class,
					reqRefPrefixNameDO.getIdPk());
			if (null == dbimageRefPrefixNameDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPrefixNameComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefPrefixNameDO);
			BeanUtils.copyProperties(reqRefPrefixNameDO, dbimageRefPrefixNameDO, strIgnoreProperties);
			entityManager.detach(dbimageRefPrefixNameDO);
			theRefPrefixNameComponentRule.preExecuteRefPrefixNameCompMerge(reqRefPrefixNameDO, dbimageRefPrefixNameDO,
					txnTransferObj);
			RefPrefixNameDO mergedRefPrefixNameDO = entityManager.merge(dbimageRefPrefixNameDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefPrefixNameDO(new RefPrefixNameDO(mergedRefPrefixNameDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPrefixNameComponentRule.postExecuteRefPrefixNameCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefPrefixNameComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefPrefixNameComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefPrefixNameComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPrefixNameComponent.merge failed unexpectedly");
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
	 *             if RefPrefixNameDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefPrefixNameComponentRule.prevalidateRefPrefixNameCompFindById(txnTransferObj);
			RefPrefixNameDO reqRefPrefixNameDO = (RefPrefixNameDO) txnTransferObj.getTxnPayload().getRefPrefixNameDO();
			RefPrefixNameDO dbimageRefPrefixNameDO = entityManager.find(RefPrefixNameDO.class,
					reqRefPrefixNameDO.getIdPk());
			if (null == dbimageRefPrefixNameDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPrefixNameComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefPrefixNameDO(new RefPrefixNameDO(dbimageRefPrefixNameDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefPrefixNameComponentRule.postExecuteRefPrefixNameCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPrefixNameComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefPrefixNameDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPrefixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPrefixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPrefixNameDO is needed in the request");
		}

		RefPrefixNameDO theRefPrefixNameDO = (RefPrefixNameDO) txnTransferObj.getTxnPayload().getRefPrefixNameDO();
		if (null == theRefPrefixNameDO.getKey() || theRefPrefixNameDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPrefixNameDO.key should not be null");
		}

		if (null == theRefPrefixNameDO.getConfigLanguageCodeKey()
				|| theRefPrefixNameDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPrefixNameDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPrefixNameDO.getValue() || theRefPrefixNameDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPrefixNameDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPrefixNameDO.getConfigLanguageCodeKey()
				|| theRefPrefixNameDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPrefixNameDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPrefixNameDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPrefixNameDO.setCreatedTs(new Date());
		theRefPrefixNameDO.setUpdatedTs(new Date());
		theRefPrefixNameDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPrefixNameDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefPrefixNameDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPrefixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPrefixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPrefixNameDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefPrefixNameDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefPrefixNameDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPrefixNameDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefPrefixNameDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefPrefixNameDO.version should not be null");
		}

		RefPrefixNameDO theRefPrefixNameDO = (RefPrefixNameDO) txnTransferObj.getTxnPayload().getRefPrefixNameDO();
		if (null == theRefPrefixNameDO.getKey() || theRefPrefixNameDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPrefixNameDO.key should not be null");
		}

		if (null == theRefPrefixNameDO.getConfigLanguageCodeKey()
				|| theRefPrefixNameDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPrefixNameDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefPrefixNameDO.getValue() || theRefPrefixNameDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefPrefixNameDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefPrefixNameDO.getConfigLanguageCodeKey()
				|| theRefPrefixNameDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefPrefixNameDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefPrefixNameDO.configLanguageCodeKey is not valid");
			}
		}

		theRefPrefixNameDO.setUpdatedTs(new Date());
		theRefPrefixNameDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefPrefixNameDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefPrefixNameDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPrefixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefPrefixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPrefixNameDO is needed in the request");
		}
		RefPrefixNameDO theRefPrefixNameDO = (RefPrefixNameDO) txnTransferObj.getTxnPayload().getRefPrefixNameDO();
		if (null == theRefPrefixNameDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefPrefixNameDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefPrefixNameDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPrefixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefPrefixNameDO> pageRefPrefixNameDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefPrefixNameDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefPrefixNameDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefPrefixNameDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPrefixName reference list does not have any records in the database");
			}

			if ((pageRefPrefixNameDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefPrefixName, total number of pages is "
								+ pageRefPrefixNameDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefPrefixNameDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefPrefixNameDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefPrefixNameDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefPrefixNameDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefPrefixNameDO.getSize());

			List<RefPrefixNameDO> dbimageRefPrefixNameDOlist = pageRefPrefixNameDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefPrefixNameDO> clonedRefPrefixNameDOList = null;
			if (null != dbimageRefPrefixNameDOlist && dbimageRefPrefixNameDOlist.size() > 0) {
				clonedRefPrefixNameDOList = new ArrayList<RefPrefixNameDO>();
				Iterator<RefPrefixNameDO> itr = dbimageRefPrefixNameDOlist.iterator();
				while (itr.hasNext()) {
					RefPrefixNameDO theRefPrefixNameDO = new RefPrefixNameDO(itr.next());
					clonedRefPrefixNameDOList.add(theRefPrefixNameDO);
				}
			}

			if (null == clonedRefPrefixNameDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPrefixName reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefPrefixNameDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefPrefixName reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefPrefixNameDOList(clonedRefPrefixNameDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPrefixNameComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFPREFIXNAME_BUSKEY")
	public Page<RefPrefixNameDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefPrefixNameDO> pageRefPrefixNameDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefPrefixNameDO = this.theRefPrefixNameRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefPrefixNameDO = this.theRefPrefixNameRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefPrefixNameDO = this.theRefPrefixNameRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefPrefixNameDO;
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
	 *             if RefPrefixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefPrefixNameDO reqRefPrefixNameDO = (RefPrefixNameDO) txnTransferObj.getTxnPayload().getRefPrefixNameDO();
			theRefPrefixNameComponentRule.preValidateRefPrefixNamefindByBusinessKey(txnTransferObj);
			theRefPrefixNameComponentRule.preExecuteRefPrefixNamefindByBusinessKey(txnTransferObj);

			RefPrefixNameDO dbimageRefPrefixNameDO = executeRepositoryQuery(
					reqRefPrefixNameDO.getConfigLanguageCodeKey(), reqRefPrefixNameDO.getKey(),
					reqRefPrefixNameDO.getInquiryFilter());

			if (null == dbimageRefPrefixNameDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefPrefixNameComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefPrefixNameDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefPrefixNameDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefPrefixNameDO(new RefPrefixNameDO(dbimageRefPrefixNameDO));
			}

			theRefPrefixNameComponentRule.postExecuteRefPrefixNamefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefPrefixNameComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefPrefixNameDO returns the populated RefPrefixNameDO object
	 */
	@CacheResult(cacheName = "REFPREFIXNAME_BUSKEY")
	public RefPrefixNameDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefPrefixNameDO dbimageRefPrefixNameDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefPrefixNameDO = this.theRefPrefixNameRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefPrefixNameDO = this.theRefPrefixNameRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefPrefixNameDO = this.theRefPrefixNameRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefPrefixNameDO;
	}

	/**
	 * perform the common validation that RefPrefixNameDO,
	 * RefPrefixNameDO.configLanguageCodeKey and RefPrefixNameDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPrefixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPrefixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPrefixNameDO is needed in the request");
		}
		RefPrefixNameDO theRefPrefixNameDO = (RefPrefixNameDO) txnTransferObj.getTxnPayload().getRefPrefixNameDO();
		if (null == theRefPrefixNameDO.getKey() || theRefPrefixNameDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefPrefixNameDO.key should not be null");
		}

		if (null == theRefPrefixNameDO.getConfigLanguageCodeKey()
				|| theRefPrefixNameDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPrefixNameDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPrefixNameDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPrefixNameDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPrefixNameDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPrefixNameDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefPrefixNameDO and
	 * RefPrefixNameDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefPrefixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefPrefixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefPrefixNameDO is needed in the request");
		}
		RefPrefixNameDO theRefPrefixNameDO = (RefPrefixNameDO) txnTransferObj.getTxnPayload().getRefPrefixNameDO();

		if (null == theRefPrefixNameDO.getConfigLanguageCodeKey()
				|| theRefPrefixNameDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefPrefixNameDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefPrefixNameDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefPrefixNameDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefPrefixNameDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefPrefixNameDO().getInquiryFilter());
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
