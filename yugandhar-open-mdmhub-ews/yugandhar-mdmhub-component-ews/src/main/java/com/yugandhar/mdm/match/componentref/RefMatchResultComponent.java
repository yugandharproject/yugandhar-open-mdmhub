package com.yugandhar.mdm.match.componentref;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.RefMatchResultDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefMatchResultDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefMatchResultComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefMatchResultRepository theRefMatchResultRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefMatchResultComponentRule theRefMatchResultComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefMatchResultComponent() {
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
	 *             if RefMatchResultDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefMatchResultComponentRule.prevalidateRefMatchResultCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefMatchResultDO reqRefMatchResultDO = (RefMatchResultDO) txnTransferObj.getTxnPayload()
					.getRefMatchResultDO();
			if (null == reqRefMatchResultDO.getPrimaryKeyDO()
					|| null == reqRefMatchResultDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefMatchResultDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefMatchResultDO.setIdPk(reqRefMatchResultDO.getPrimaryKeyDO().getIdPk());
				RefMatchResultDO dbimageRefMatchResultDO = entityManager.find(RefMatchResultDO.class,
						reqRefMatchResultDO.getIdPk());
				if (null != dbimageRefMatchResultDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefMatchResultComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefMatchResultComponentRule.preExecuteRefMatchResultCompPersist(reqRefMatchResultDO, txnTransferObj);
			entityManager.persist(reqRefMatchResultDO);
			entityManager.flush();
			reqRefMatchResultDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefMatchResultDO(new RefMatchResultDO(reqRefMatchResultDO));
			theRefMatchResultComponentRule.postExecuteRefMatchResultCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchResultComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchResultComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchResultComponent.persist failed unexpectedly");
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
	 *             if RefMatchResultDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefMatchResultComponentRule.PrevalidateRefMatchResultCompMerge(txnTransferObj);
			RefMatchResultDO reqRefMatchResultDO = (RefMatchResultDO) txnTransferObj.getTxnPayload()
					.getRefMatchResultDO();
			RefMatchResultDO dbimageRefMatchResultDO = entityManager.find(RefMatchResultDO.class,
					reqRefMatchResultDO.getIdPk());
			if (null == dbimageRefMatchResultDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchResultComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefMatchResultDO);
			BeanUtils.copyProperties(reqRefMatchResultDO, dbimageRefMatchResultDO, strIgnoreProperties);
			entityManager.detach(dbimageRefMatchResultDO);
			theRefMatchResultComponentRule.preExecuteRefMatchResultCompMerge(reqRefMatchResultDO,
					dbimageRefMatchResultDO, txnTransferObj);
			RefMatchResultDO mergedRefMatchResultDO = entityManager.merge(dbimageRefMatchResultDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefMatchResultDO(new RefMatchResultDO(mergedRefMatchResultDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchResultComponentRule.postExecuteRefMatchResultCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefMatchResultComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchResultComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchResultComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchResultComponent.merge failed unexpectedly");
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
	 *             if RefMatchResultDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefMatchResultComponentRule.prevalidateRefMatchResultCompFindById(txnTransferObj);
			RefMatchResultDO reqRefMatchResultDO = (RefMatchResultDO) txnTransferObj.getTxnPayload()
					.getRefMatchResultDO();
			RefMatchResultDO dbimageRefMatchResultDO = entityManager.find(RefMatchResultDO.class,
					reqRefMatchResultDO.getIdPk());
			if (null == dbimageRefMatchResultDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchResultComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefMatchResultDO(new RefMatchResultDO(dbimageRefMatchResultDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchResultComponentRule.postExecuteRefMatchResultCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchResultComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefMatchResultDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchResultDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchResultDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchResultDO is needed in the request");
		}

		RefMatchResultDO theRefMatchResultDO = (RefMatchResultDO) txnTransferObj.getTxnPayload().getRefMatchResultDO();
		if (null == theRefMatchResultDO.getKey() || theRefMatchResultDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMatchResultDO.key should not be null");
		}

		if (null == theRefMatchResultDO.getConfigLanguageCodeKey()
				|| theRefMatchResultDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchResultDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefMatchResultDO.getValue() || theRefMatchResultDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefMatchResultDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefMatchResultDO.getConfigLanguageCodeKey()
				|| theRefMatchResultDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefMatchResultDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefMatchResultDO.configLanguageCodeKey is not valid");
			}
		}

		theRefMatchResultDO.setCreatedTs(new Date());
		theRefMatchResultDO.setUpdatedTs(new Date());
		theRefMatchResultDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchResultDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefMatchResultDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchResultDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchResultDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchResultDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefMatchResultDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefMatchResultDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchResultDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefMatchResultDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefMatchResultDO.version should not be null");
		}

		RefMatchResultDO theRefMatchResultDO = (RefMatchResultDO) txnTransferObj.getTxnPayload().getRefMatchResultDO();
		if (null == theRefMatchResultDO.getKey() || theRefMatchResultDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMatchResultDO.key should not be null");
		}

		if (null == theRefMatchResultDO.getConfigLanguageCodeKey()
				|| theRefMatchResultDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchResultDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefMatchResultDO.getValue() || theRefMatchResultDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefMatchResultDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefMatchResultDO.getConfigLanguageCodeKey()
				|| theRefMatchResultDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefMatchResultDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefMatchResultDO.configLanguageCodeKey is not valid");
			}
		}

		theRefMatchResultDO.setUpdatedTs(new Date());
		theRefMatchResultDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchResultDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefMatchResultDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchResultDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchResultDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchResultDO is needed in the request");
		}
		RefMatchResultDO theRefMatchResultDO = (RefMatchResultDO) txnTransferObj.getTxnPayload().getRefMatchResultDO();
		if (null == theRefMatchResultDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchResultDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefMatchResultDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchResultDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefMatchResultDO> pageRefMatchResultDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefMatchResultDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefMatchResultDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefMatchResultDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchResult reference list does not have any records in the database");
			}

			if ((pageRefMatchResultDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefMatchResult, total number of pages is "
								+ pageRefMatchResultDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefMatchResultDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefMatchResultDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefMatchResultDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefMatchResultDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefMatchResultDO.getSize());

			List<RefMatchResultDO> dbimageRefMatchResultDOlist = pageRefMatchResultDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefMatchResultDO> clonedRefMatchResultDOList = null;
			if (null != dbimageRefMatchResultDOlist && dbimageRefMatchResultDOlist.size() > 0) {
				clonedRefMatchResultDOList = new ArrayList<RefMatchResultDO>();
				Iterator<RefMatchResultDO> itr = dbimageRefMatchResultDOlist.iterator();
				while (itr.hasNext()) {
					RefMatchResultDO theRefMatchResultDO = new RefMatchResultDO(itr.next());
					clonedRefMatchResultDOList.add(theRefMatchResultDO);
				}
			}

			if (null == clonedRefMatchResultDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchResult reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefMatchResultDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchResult reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefMatchResultDOList(clonedRefMatchResultDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchResultComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFMATCHRESULT_BUSKEY")
	public Page<RefMatchResultDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefMatchResultDO> pageRefMatchResultDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefMatchResultDO = this.theRefMatchResultRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefMatchResultDO = this.theRefMatchResultRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefMatchResultDO = this.theRefMatchResultRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefMatchResultDO;
	}

	/**
	 * This method search the database table records based on
	 * business key (e.g.LanguageCode and Key)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchResultDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefMatchResultDO reqRefMatchResultDO = (RefMatchResultDO) txnTransferObj.getTxnPayload()
					.getRefMatchResultDO();
			theRefMatchResultComponentRule.preValidateRefMatchResultfindByBusinessKey(txnTransferObj);
			theRefMatchResultComponentRule.preExecuteRefMatchResultfindByBusinessKey(txnTransferObj);

			RefMatchResultDO dbimageRefMatchResultDO = executeRepositoryQuery(
					reqRefMatchResultDO.getConfigLanguageCodeKey(), reqRefMatchResultDO.getKey(),
					reqRefMatchResultDO.getInquiryFilter());

			if (null == dbimageRefMatchResultDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchResultComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefMatchResultDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefMatchResultDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefMatchResultDO(new RefMatchResultDO(dbimageRefMatchResultDO));
			}

			theRefMatchResultComponentRule.postExecuteRefMatchResultfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchResultComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param configLanguageCodeKey
	 * @param key
	 * @param filter
	 * @return RefMatchResultDO returns the populated RefMatchResultDO object
	 */
	@CacheResult(cacheName = "REFMATCHRESULT_BUSKEY")
	public RefMatchResultDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefMatchResultDO dbimageRefMatchResultDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefMatchResultDO = this.theRefMatchResultRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefMatchResultDO = this.theRefMatchResultRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefMatchResultDO = this.theRefMatchResultRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefMatchResultDO;
	}

	/**
	 * perform the common validation that RefMatchResultDO,
	 * RefMatchResultDO.configLanguageCodeKey and RefMatchResultDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchResultDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchResultDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchResultDO is needed in the request");
		}
		RefMatchResultDO theRefMatchResultDO = (RefMatchResultDO) txnTransferObj.getTxnPayload().getRefMatchResultDO();
		if (null == theRefMatchResultDO.getKey() || theRefMatchResultDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMatchResultDO.key should not be null");
		}

		if (null == theRefMatchResultDO.getConfigLanguageCodeKey()
				|| theRefMatchResultDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchResultDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchResultDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchResultDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchResultDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchResultDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefMatchResultDO and
	 * RefMatchResultDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchResultDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchResultDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchResultDO is needed in the request");
		}
		RefMatchResultDO theRefMatchResultDO = (RefMatchResultDO) txnTransferObj.getTxnPayload().getRefMatchResultDO();

		if (null == theRefMatchResultDO.getConfigLanguageCodeKey()
				|| theRefMatchResultDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchResultDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchResultDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchResultDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchResultDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchResultDO().getInquiryFilter());
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
