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
import com.yugandhar.mdm.extern.dobj.RefSuffixNameDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefSuffixNameDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefSuffixNameComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefSuffixNameRepository theRefSuffixNameRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefSuffixNameComponentRule theRefSuffixNameComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefSuffixNameComponent() {
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
	 *             if RefSuffixNameDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefSuffixNameComponentRule.prevalidateRefSuffixNameCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefSuffixNameDO reqRefSuffixNameDO = (RefSuffixNameDO) txnTransferObj.getTxnPayload().getRefSuffixNameDO();
			if (null == reqRefSuffixNameDO.getPrimaryKeyDO()
					|| null == reqRefSuffixNameDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefSuffixNameDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefSuffixNameDO.setIdPk(reqRefSuffixNameDO.getPrimaryKeyDO().getIdPk());
				RefSuffixNameDO dbimageRefSuffixNameDO = entityManager.find(RefSuffixNameDO.class,
						reqRefSuffixNameDO.getIdPk());
				if (null != dbimageRefSuffixNameDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefSuffixNameComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefSuffixNameComponentRule.preExecuteRefSuffixNameCompPersist(reqRefSuffixNameDO, txnTransferObj);
			entityManager.persist(reqRefSuffixNameDO);
			entityManager.flush();
			reqRefSuffixNameDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefSuffixNameDO(new RefSuffixNameDO(reqRefSuffixNameDO));
			theRefSuffixNameComponentRule.postExecuteRefSuffixNameCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefSuffixNameComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefSuffixNameComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSuffixNameComponent.persist failed unexpectedly");
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
	 *             if RefSuffixNameDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefSuffixNameComponentRule.PrevalidateRefSuffixNameCompMerge(txnTransferObj);
			RefSuffixNameDO reqRefSuffixNameDO = (RefSuffixNameDO) txnTransferObj.getTxnPayload().getRefSuffixNameDO();
			RefSuffixNameDO dbimageRefSuffixNameDO = entityManager.find(RefSuffixNameDO.class,
					reqRefSuffixNameDO.getIdPk());
			if (null == dbimageRefSuffixNameDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefSuffixNameComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefSuffixNameDO);
			BeanUtils.copyProperties(reqRefSuffixNameDO, dbimageRefSuffixNameDO, strIgnoreProperties);
			entityManager.detach(dbimageRefSuffixNameDO);
			theRefSuffixNameComponentRule.preExecuteRefSuffixNameCompMerge(reqRefSuffixNameDO, dbimageRefSuffixNameDO,
					txnTransferObj);
			RefSuffixNameDO mergedRefSuffixNameDO = entityManager.merge(dbimageRefSuffixNameDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefSuffixNameDO(new RefSuffixNameDO(mergedRefSuffixNameDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefSuffixNameComponentRule.postExecuteRefSuffixNameCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefSuffixNameComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefSuffixNameComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefSuffixNameComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSuffixNameComponent.merge failed unexpectedly");
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
	 *             if RefSuffixNameDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefSuffixNameComponentRule.prevalidateRefSuffixNameCompFindById(txnTransferObj);
			RefSuffixNameDO reqRefSuffixNameDO = (RefSuffixNameDO) txnTransferObj.getTxnPayload().getRefSuffixNameDO();
			RefSuffixNameDO dbimageRefSuffixNameDO = entityManager.find(RefSuffixNameDO.class,
					reqRefSuffixNameDO.getIdPk());
			if (null == dbimageRefSuffixNameDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefSuffixNameComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefSuffixNameDO(new RefSuffixNameDO(dbimageRefSuffixNameDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefSuffixNameComponentRule.postExecuteRefSuffixNameCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSuffixNameComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefSuffixNameDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSuffixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefSuffixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSuffixNameDO is needed in the request");
		}

		RefSuffixNameDO theRefSuffixNameDO = (RefSuffixNameDO) txnTransferObj.getTxnPayload().getRefSuffixNameDO();
		if (null == theRefSuffixNameDO.getKey() || theRefSuffixNameDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefSuffixNameDO.key should not be null");
		}

		if (null == theRefSuffixNameDO.getConfigLanguageCodeKey()
				|| theRefSuffixNameDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefSuffixNameDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefSuffixNameDO.getValue() || theRefSuffixNameDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefSuffixNameDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefSuffixNameDO.getConfigLanguageCodeKey()
				|| theRefSuffixNameDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefSuffixNameDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefSuffixNameDO.configLanguageCodeKey is not valid");
			}
		}

		theRefSuffixNameDO.setCreatedTs(new Date());
		theRefSuffixNameDO.setUpdatedTs(new Date());
		theRefSuffixNameDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefSuffixNameDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefSuffixNameDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSuffixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefSuffixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSuffixNameDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefSuffixNameDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefSuffixNameDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefSuffixNameDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefSuffixNameDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefSuffixNameDO.version should not be null");
		}

		RefSuffixNameDO theRefSuffixNameDO = (RefSuffixNameDO) txnTransferObj.getTxnPayload().getRefSuffixNameDO();
		if (null == theRefSuffixNameDO.getKey() || theRefSuffixNameDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefSuffixNameDO.key should not be null");
		}

		if (null == theRefSuffixNameDO.getConfigLanguageCodeKey()
				|| theRefSuffixNameDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefSuffixNameDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefSuffixNameDO.getValue() || theRefSuffixNameDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefSuffixNameDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefSuffixNameDO.getConfigLanguageCodeKey()
				|| theRefSuffixNameDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefSuffixNameDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefSuffixNameDO.configLanguageCodeKey is not valid");
			}
		}

		theRefSuffixNameDO.setUpdatedTs(new Date());
		theRefSuffixNameDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefSuffixNameDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefSuffixNameDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSuffixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefSuffixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSuffixNameDO is needed in the request");
		}
		RefSuffixNameDO theRefSuffixNameDO = (RefSuffixNameDO) txnTransferObj.getTxnPayload().getRefSuffixNameDO();
		if (null == theRefSuffixNameDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefSuffixNameDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefSuffixNameDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSuffixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefSuffixNameDO> pageRefSuffixNameDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefSuffixNameDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefSuffixNameDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefSuffixNameDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefSuffixName reference list does not have any records in the database");
			}

			if ((pageRefSuffixNameDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefSuffixName, total number of pages is "
								+ pageRefSuffixNameDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefSuffixNameDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefSuffixNameDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefSuffixNameDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefSuffixNameDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefSuffixNameDO.getSize());

			List<RefSuffixNameDO> dbimageRefSuffixNameDOlist = pageRefSuffixNameDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefSuffixNameDO> clonedRefSuffixNameDOList = null;
			if (null != dbimageRefSuffixNameDOlist && dbimageRefSuffixNameDOlist.size() > 0) {
				clonedRefSuffixNameDOList = new ArrayList<RefSuffixNameDO>();
				Iterator<RefSuffixNameDO> itr = dbimageRefSuffixNameDOlist.iterator();
				while (itr.hasNext()) {
					RefSuffixNameDO theRefSuffixNameDO = new RefSuffixNameDO(itr.next());
					clonedRefSuffixNameDOList.add(theRefSuffixNameDO);
				}
			}

			if (null == clonedRefSuffixNameDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefSuffixName reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefSuffixNameDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefSuffixName reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefSuffixNameDOList(clonedRefSuffixNameDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSuffixNameComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFSUFFIXNAME_BUSKEY")
	public Page<RefSuffixNameDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefSuffixNameDO> pageRefSuffixNameDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefSuffixNameDO = this.theRefSuffixNameRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefSuffixNameDO = this.theRefSuffixNameRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefSuffixNameDO = this.theRefSuffixNameRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefSuffixNameDO;
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
	 *             if RefSuffixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefSuffixNameDO reqRefSuffixNameDO = (RefSuffixNameDO) txnTransferObj.getTxnPayload().getRefSuffixNameDO();
			theRefSuffixNameComponentRule.preValidateRefSuffixNamefindByBusinessKey(txnTransferObj);
			theRefSuffixNameComponentRule.preExecuteRefSuffixNamefindByBusinessKey(txnTransferObj);

			RefSuffixNameDO dbimageRefSuffixNameDO = executeRepositoryQuery(
					reqRefSuffixNameDO.getConfigLanguageCodeKey(), reqRefSuffixNameDO.getKey(),
					reqRefSuffixNameDO.getInquiryFilter());

			if (null == dbimageRefSuffixNameDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefSuffixNameComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefSuffixNameDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefSuffixNameDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefSuffixNameDO(new RefSuffixNameDO(dbimageRefSuffixNameDO));
			}

			theRefSuffixNameComponentRule.postExecuteRefSuffixNamefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefSuffixNameComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefSuffixNameDO returns the populated RefSuffixNameDO object
	 */
	@CacheResult(cacheName = "REFSUFFIXNAME_BUSKEY")
	public RefSuffixNameDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefSuffixNameDO dbimageRefSuffixNameDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefSuffixNameDO = this.theRefSuffixNameRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefSuffixNameDO = this.theRefSuffixNameRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefSuffixNameDO = this.theRefSuffixNameRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefSuffixNameDO;
	}

	/**
	 * perform the common validation that RefSuffixNameDO,
	 * RefSuffixNameDO.configLanguageCodeKey and RefSuffixNameDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSuffixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefSuffixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSuffixNameDO is needed in the request");
		}
		RefSuffixNameDO theRefSuffixNameDO = (RefSuffixNameDO) txnTransferObj.getTxnPayload().getRefSuffixNameDO();
		if (null == theRefSuffixNameDO.getKey() || theRefSuffixNameDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefSuffixNameDO.key should not be null");
		}

		if (null == theRefSuffixNameDO.getConfigLanguageCodeKey()
				|| theRefSuffixNameDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefSuffixNameDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefSuffixNameDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefSuffixNameDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefSuffixNameDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefSuffixNameDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefSuffixNameDO and
	 * RefSuffixNameDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefSuffixNameDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefSuffixNameDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefSuffixNameDO is needed in the request");
		}
		RefSuffixNameDO theRefSuffixNameDO = (RefSuffixNameDO) txnTransferObj.getTxnPayload().getRefSuffixNameDO();

		if (null == theRefSuffixNameDO.getConfigLanguageCodeKey()
				|| theRefSuffixNameDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefSuffixNameDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefSuffixNameDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefSuffixNameDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefSuffixNameDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefSuffixNameDO().getInquiryFilter());
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
