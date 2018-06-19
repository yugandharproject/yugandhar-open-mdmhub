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
import com.yugandhar.mdm.extern.dobj.RefMatchActionstatusDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefMatchActionstatusDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 */

@Scope(value = "prototype")
@Component
public class RefMatchActionstatusComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefMatchActionstatusRepository theRefMatchActionstatusRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefMatchActionstatusComponentRule theRefMatchActionstatusComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefMatchActionstatusComponent() {
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
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefMatchActionstatusComponentRule.prevalidateRefMatchActionstatusCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefMatchActionstatusDO reqRefMatchActionstatusDO = (RefMatchActionstatusDO) txnTransferObj.getTxnPayload()
					.getRefMatchActionstatusDO();
			if (null == reqRefMatchActionstatusDO.getPrimaryKeyDO()
					|| null == reqRefMatchActionstatusDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefMatchActionstatusDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefMatchActionstatusDO.setIdPk(reqRefMatchActionstatusDO.getPrimaryKeyDO().getIdPk());
				RefMatchActionstatusDO dbimageRefMatchActionstatusDO = entityManager.find(RefMatchActionstatusDO.class,
						reqRefMatchActionstatusDO.getIdPk());
				if (null != dbimageRefMatchActionstatusDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefMatchActionstatusComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefMatchActionstatusComponentRule.preExecuteRefMatchActionstatusCompPersist(reqRefMatchActionstatusDO,
					txnTransferObj);
			entityManager.persist(reqRefMatchActionstatusDO);
			entityManager.flush();
			reqRefMatchActionstatusDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setRefMatchActionstatusDO(new RefMatchActionstatusDO(reqRefMatchActionstatusDO));
			theRefMatchActionstatusComponentRule.postExecuteRefMatchActionstatusCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchActionstatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchActionstatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchActionstatusComponent.persist failed unexpectedly");
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
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefMatchActionstatusComponentRule.PrevalidateRefMatchActionstatusCompMerge(txnTransferObj);
			RefMatchActionstatusDO reqRefMatchActionstatusDO = (RefMatchActionstatusDO) txnTransferObj.getTxnPayload()
					.getRefMatchActionstatusDO();
			RefMatchActionstatusDO dbimageRefMatchActionstatusDO = entityManager.find(RefMatchActionstatusDO.class,
					reqRefMatchActionstatusDO.getIdPk());
			if (null == dbimageRefMatchActionstatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchActionstatusComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefMatchActionstatusDO);
			BeanUtils.copyProperties(reqRefMatchActionstatusDO, dbimageRefMatchActionstatusDO, strIgnoreProperties);
			entityManager.detach(dbimageRefMatchActionstatusDO);
			theRefMatchActionstatusComponentRule.preExecuteRefMatchActionstatusCompMerge(reqRefMatchActionstatusDO,
					dbimageRefMatchActionstatusDO, txnTransferObj);
			RefMatchActionstatusDO mergedRefMatchActionstatusDO = entityManager.merge(dbimageRefMatchActionstatusDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefMatchActionstatusDO(new RefMatchActionstatusDO(mergedRefMatchActionstatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchActionstatusComponentRule.postExecuteRefMatchActionstatusCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefMatchActionstatusComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchActionstatusComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchActionstatusComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchActionstatusComponent.merge failed unexpectedly");
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
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefMatchActionstatusComponentRule.prevalidateRefMatchActionstatusCompFindById(txnTransferObj);
			RefMatchActionstatusDO reqRefMatchActionstatusDO = (RefMatchActionstatusDO) txnTransferObj.getTxnPayload()
					.getRefMatchActionstatusDO();
			RefMatchActionstatusDO dbimageRefMatchActionstatusDO = entityManager.find(RefMatchActionstatusDO.class,
					reqRefMatchActionstatusDO.getIdPk());
			if (null == dbimageRefMatchActionstatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchActionstatusComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setRefMatchActionstatusDO(new RefMatchActionstatusDO(dbimageRefMatchActionstatusDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchActionstatusComponentRule.postExecuteRefMatchActionstatusCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchActionstatusComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefMatchActionstatusDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchActionstatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchActionstatusDO is needed in the request");
		}

		RefMatchActionstatusDO theRefMatchActionstatusDO = (RefMatchActionstatusDO) txnTransferObj.getTxnPayload()
				.getRefMatchActionstatusDO();
		if (null == theRefMatchActionstatusDO.getKey() || theRefMatchActionstatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMatchActionstatusDO.key should not be null");
		}

		if (null == theRefMatchActionstatusDO.getConfigLanguageCodeKey()
				|| theRefMatchActionstatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchActionstatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefMatchActionstatusDO.getValue() || theRefMatchActionstatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefMatchActionstatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefMatchActionstatusDO.getConfigLanguageCodeKey()
				|| theRefMatchActionstatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefMatchActionstatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefMatchActionstatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefMatchActionstatusDO.setCreatedTs(new Date());
		theRefMatchActionstatusDO.setUpdatedTs(new Date());
		theRefMatchActionstatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchActionstatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefMatchActionstatusDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchActionstatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchActionstatusDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchActionstatusDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefMatchActionstatusDO.version should not be null");
		}

		RefMatchActionstatusDO theRefMatchActionstatusDO = (RefMatchActionstatusDO) txnTransferObj.getTxnPayload()
				.getRefMatchActionstatusDO();
		if (null == theRefMatchActionstatusDO.getKey() || theRefMatchActionstatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMatchActionstatusDO.key should not be null");
		}

		if (null == theRefMatchActionstatusDO.getConfigLanguageCodeKey()
				|| theRefMatchActionstatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchActionstatusDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefMatchActionstatusDO.getValue() || theRefMatchActionstatusDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefMatchActionstatusDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefMatchActionstatusDO.getConfigLanguageCodeKey()
				|| theRefMatchActionstatusDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefMatchActionstatusDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefMatchActionstatusDO.configLanguageCodeKey is not valid");
			}
		}

		theRefMatchActionstatusDO.setUpdatedTs(new Date());
		theRefMatchActionstatusDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchActionstatusDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefMatchActionstatusDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchActionstatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchActionstatusDO is needed in the request");
		}
		RefMatchActionstatusDO theRefMatchActionstatusDO = (RefMatchActionstatusDO) txnTransferObj.getTxnPayload()
				.getRefMatchActionstatusDO();
		if (null == theRefMatchActionstatusDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchActionstatusDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefMatchActionstatusDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefMatchActionstatusDO> pageRefMatchActionstatusDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefMatchActionstatusDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchActionstatus reference list does not have any records in the database");
			}

			if ((pageRefMatchActionstatusDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefMatchActionstatus, total number of pages is "
								+ pageRefMatchActionstatusDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefMatchActionstatusDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefMatchActionstatusDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageRefMatchActionstatusDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefMatchActionstatusDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefMatchActionstatusDO.getSize());

			List<RefMatchActionstatusDO> dbimageRefMatchActionstatusDOlist = pageRefMatchActionstatusDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefMatchActionstatusDO> clonedRefMatchActionstatusDOList = null;
			if (null != dbimageRefMatchActionstatusDOlist && dbimageRefMatchActionstatusDOlist.size() > 0) {
				clonedRefMatchActionstatusDOList = new ArrayList<RefMatchActionstatusDO>();
				Iterator<RefMatchActionstatusDO> itr = dbimageRefMatchActionstatusDOlist.iterator();
				while (itr.hasNext()) {
					RefMatchActionstatusDO theRefMatchActionstatusDO = new RefMatchActionstatusDO(itr.next());
					clonedRefMatchActionstatusDOList.add(theRefMatchActionstatusDO);
				}
			}

			if (null == clonedRefMatchActionstatusDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchActionstatus reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefMatchActionstatusDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchActionstatus reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefMatchActionstatusDOList(clonedRefMatchActionstatusDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchActionstatusComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFMATCHACTIONSTATUS_BUSKEY")
	public Page<RefMatchActionstatusDO> findAllRecordsByLanguageCodeFromRepository(
			@CacheKey String configLanguageCodeKey, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefMatchActionstatusDO> pageRefMatchActionstatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefMatchActionstatusDO = this.theRefMatchActionstatusRepository
					.findAllActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefMatchActionstatusDO = this.theRefMatchActionstatusRepository
					.findAllInActiveByLanguageCode(configLanguageCodeKey, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefMatchActionstatusDO = this.theRefMatchActionstatusRepository
					.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefMatchActionstatusDO;
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
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefMatchActionstatusDO reqRefMatchActionstatusDO = (RefMatchActionstatusDO) txnTransferObj.getTxnPayload()
					.getRefMatchActionstatusDO();
			theRefMatchActionstatusComponentRule.preValidateRefMatchActionstatusfindByBusinessKey(txnTransferObj);
			theRefMatchActionstatusComponentRule.preExecuteRefMatchActionstatusfindByBusinessKey(txnTransferObj);

			RefMatchActionstatusDO dbimageRefMatchActionstatusDO = executeRepositoryQuery(
					reqRefMatchActionstatusDO.getConfigLanguageCodeKey(), reqRefMatchActionstatusDO.getKey(),
					reqRefMatchActionstatusDO.getInquiryFilter());

			if (null == dbimageRefMatchActionstatusDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchActionstatusComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefMatchActionstatusDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setRefMatchActionstatusDO(new RefMatchActionstatusDO(dbimageRefMatchActionstatusDO));
			}

			theRefMatchActionstatusComponentRule.postExecuteRefMatchActionstatusfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchActionstatusComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefMatchActionstatusDO returns the populated RefMatchActionstatusDO object
	 */
	@CacheResult(cacheName = "REFMATCHACTIONSTATUS_BUSKEY")
	public RefMatchActionstatusDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefMatchActionstatusDO dbimageRefMatchActionstatusDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefMatchActionstatusDO = this.theRefMatchActionstatusRepository
					.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefMatchActionstatusDO = this.theRefMatchActionstatusRepository
					.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefMatchActionstatusDO = this.theRefMatchActionstatusRepository
					.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefMatchActionstatusDO;
	}

	/**
	 * perform the common validation that RefMatchActionstatusDO,
	 * RefMatchActionstatusDO.configLanguageCodeKey and RefMatchActionstatusDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchActionstatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchActionstatusDO is needed in the request");
		}
		RefMatchActionstatusDO theRefMatchActionstatusDO = (RefMatchActionstatusDO) txnTransferObj.getTxnPayload()
				.getRefMatchActionstatusDO();
		if (null == theRefMatchActionstatusDO.getKey() || theRefMatchActionstatusDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefMatchActionstatusDO.key should not be null");
		}

		if (null == theRefMatchActionstatusDO.getConfigLanguageCodeKey()
				|| theRefMatchActionstatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchActionstatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefMatchActionstatusDO and
	 * RefMatchActionstatusDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchActionstatusDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchActionstatusDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchActionstatusDO is needed in the request");
		}
		RefMatchActionstatusDO theRefMatchActionstatusDO = (RefMatchActionstatusDO) txnTransferObj.getTxnPayload()
				.getRefMatchActionstatusDO();

		if (null == theRefMatchActionstatusDO.getConfigLanguageCodeKey()
				|| theRefMatchActionstatusDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefMatchActionstatusDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchActionstatusDO().getInquiryFilter());
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
