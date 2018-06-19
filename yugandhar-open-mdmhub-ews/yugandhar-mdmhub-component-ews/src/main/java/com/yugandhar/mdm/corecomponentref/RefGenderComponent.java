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
import com.yugandhar.mdm.extern.dobj.RefGenderDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefGenderDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefGenderComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefGenderRepository theRefGenderRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefGenderComponentRule theRefGenderComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefGenderComponent() {
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
	 *             if RefGenderDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefGenderComponentRule.prevalidateRefGenderCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefGenderDO reqRefGenderDO = (RefGenderDO) txnTransferObj.getTxnPayload().getRefGenderDO();
			if (null == reqRefGenderDO.getPrimaryKeyDO() || null == reqRefGenderDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefGenderDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefGenderDO.setIdPk(reqRefGenderDO.getPrimaryKeyDO().getIdPk());
				RefGenderDO dbimageRefGenderDO = entityManager.find(RefGenderDO.class, reqRefGenderDO.getIdPk());
				if (null != dbimageRefGenderDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefGenderComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefGenderComponentRule.preExecuteRefGenderCompPersist(reqRefGenderDO, txnTransferObj);
			entityManager.persist(reqRefGenderDO);
			entityManager.flush();
			reqRefGenderDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefGenderDO(new RefGenderDO(reqRefGenderDO));
			theRefGenderComponentRule.postExecuteRefGenderCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefGenderComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefGenderComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGenderComponent.persist failed unexpectedly");
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
	 *             if RefGenderDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefGenderComponentRule.PrevalidateRefGenderCompMerge(txnTransferObj);
			RefGenderDO reqRefGenderDO = (RefGenderDO) txnTransferObj.getTxnPayload().getRefGenderDO();
			RefGenderDO dbimageRefGenderDO = entityManager.find(RefGenderDO.class, reqRefGenderDO.getIdPk());
			if (null == dbimageRefGenderDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefGenderComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefGenderDO);
			BeanUtils.copyProperties(reqRefGenderDO, dbimageRefGenderDO, strIgnoreProperties);
			entityManager.detach(dbimageRefGenderDO);
			theRefGenderComponentRule.preExecuteRefGenderCompMerge(reqRefGenderDO, dbimageRefGenderDO, txnTransferObj);
			RefGenderDO mergedRefGenderDO = entityManager.merge(dbimageRefGenderDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefGenderDO(new RefGenderDO(mergedRefGenderDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefGenderComponentRule.postExecuteRefGenderCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefGenderComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefGenderComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefGenderComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGenderComponent.merge failed unexpectedly");
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
	 *             if RefGenderDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefGenderComponentRule.prevalidateRefGenderCompFindById(txnTransferObj);
			RefGenderDO reqRefGenderDO = (RefGenderDO) txnTransferObj.getTxnPayload().getRefGenderDO();
			RefGenderDO dbimageRefGenderDO = entityManager.find(RefGenderDO.class, reqRefGenderDO.getIdPk());
			if (null == dbimageRefGenderDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefGenderComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefGenderDO(new RefGenderDO(dbimageRefGenderDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefGenderComponentRule.postExecuteRefGenderCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGenderComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefGenderDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGenderDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefGenderDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGenderDO is needed in the request");
		}

		RefGenderDO theRefGenderDO = (RefGenderDO) txnTransferObj.getTxnPayload().getRefGenderDO();
		if (null == theRefGenderDO.getKey() || theRefGenderDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefGenderDO.key should not be null");
		}

		if (null == theRefGenderDO.getConfigLanguageCodeKey() || theRefGenderDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGenderDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefGenderDO.getValue() || theRefGenderDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefGenderDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefGenderDO.getConfigLanguageCodeKey()
				|| theRefGenderDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefGenderDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefGenderDO.configLanguageCodeKey is not valid");
			}
		}

		theRefGenderDO.setCreatedTs(new Date());
		theRefGenderDO.setUpdatedTs(new Date());
		theRefGenderDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefGenderDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefGenderDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGenderDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefGenderDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGenderDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefGenderDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefGenderDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefGenderDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefGenderDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefGenderDO.version should not be null");
		}

		RefGenderDO theRefGenderDO = (RefGenderDO) txnTransferObj.getTxnPayload().getRefGenderDO();
		if (null == theRefGenderDO.getKey() || theRefGenderDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefGenderDO.key should not be null");
		}

		if (null == theRefGenderDO.getConfigLanguageCodeKey() || theRefGenderDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGenderDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefGenderDO.getValue() || theRefGenderDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefGenderDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefGenderDO.getConfigLanguageCodeKey()
				|| theRefGenderDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefGenderDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefGenderDO.configLanguageCodeKey is not valid");
			}
		}

		theRefGenderDO.setUpdatedTs(new Date());
		theRefGenderDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefGenderDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefGenderDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGenderDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefGenderDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGenderDO is needed in the request");
		}
		RefGenderDO theRefGenderDO = (RefGenderDO) txnTransferObj.getTxnPayload().getRefGenderDO();
		if (null == theRefGenderDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefGenderDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefGenderDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGenderDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefGenderDO> pageRefGenderDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefGenderDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefGenderDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefGenderDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefGender reference list does not have any records in the database");
			}

			if ((pageRefGenderDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefGender, total number of pages is "
								+ pageRefGenderDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefGenderDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefGenderDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefGenderDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefGenderDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefGenderDO.getSize());

			List<RefGenderDO> dbimageRefGenderDOlist = pageRefGenderDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefGenderDO> clonedRefGenderDOList = null;
			if (null != dbimageRefGenderDOlist && dbimageRefGenderDOlist.size() > 0) {
				clonedRefGenderDOList = new ArrayList<RefGenderDO>();
				Iterator<RefGenderDO> itr = dbimageRefGenderDOlist.iterator();
				while (itr.hasNext()) {
					RefGenderDO theRefGenderDO = new RefGenderDO(itr.next());
					clonedRefGenderDOList.add(theRefGenderDO);
				}
			}

			if (null == clonedRefGenderDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefGender reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefGenderDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefGender reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefGenderDOList(clonedRefGenderDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGenderComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFGENDER_BUSKEY")
	public Page<RefGenderDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefGenderDO> pageRefGenderDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefGenderDO = this.theRefGenderRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefGenderDO = this.theRefGenderRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefGenderDO = this.theRefGenderRepository.findAllByLanguageCode(configLanguageCodeKey, localPageable);

		}

		return pageRefGenderDO;
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
	 *             if RefGenderDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefGenderDO reqRefGenderDO = (RefGenderDO) txnTransferObj.getTxnPayload().getRefGenderDO();
			theRefGenderComponentRule.preValidateRefGenderfindByBusinessKey(txnTransferObj);
			theRefGenderComponentRule.preExecuteRefGenderfindByBusinessKey(txnTransferObj);

			RefGenderDO dbimageRefGenderDO = executeRepositoryQuery(reqRefGenderDO.getConfigLanguageCodeKey(),
					reqRefGenderDO.getKey(), reqRefGenderDO.getInquiryFilter());

			if (null == dbimageRefGenderDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefGenderComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefGenderDO.setInquiryFilter(txnTransferObj.getTxnPayload().getRefGenderDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefGenderDO(new RefGenderDO(dbimageRefGenderDO));
			}

			theRefGenderComponentRule.postExecuteRefGenderfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefGenderComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefGenderDO returns the populated RefGenderDO object
	 */
	@CacheResult(cacheName = "REFGENDER_BUSKEY")
	public RefGenderDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefGenderDO dbimageRefGenderDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefGenderDO = this.theRefGenderRepository.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefGenderDO = this.theRefGenderRepository.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefGenderDO = this.theRefGenderRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefGenderDO;
	}

	/**
	 * perform the common validation that RefGenderDO,
	 * RefGenderDO.configLanguageCodeKey and RefGenderDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGenderDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefGenderDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGenderDO is needed in the request");
		}
		RefGenderDO theRefGenderDO = (RefGenderDO) txnTransferObj.getTxnPayload().getRefGenderDO();
		if (null == theRefGenderDO.getKey() || theRefGenderDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefGenderDO.key should not be null");
		}

		if (null == theRefGenderDO.getConfigLanguageCodeKey() || theRefGenderDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGenderDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefGenderDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefGenderDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefGenderDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefGenderDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefGenderDO and
	 * RefGenderDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefGenderDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefGenderDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefGenderDO is needed in the request");
		}
		RefGenderDO theRefGenderDO = (RefGenderDO) txnTransferObj.getTxnPayload().getRefGenderDO();

		if (null == theRefGenderDO.getConfigLanguageCodeKey() || theRefGenderDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefGenderDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefGenderDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefGenderDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefGenderDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefGenderDO().getInquiryFilter());
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
