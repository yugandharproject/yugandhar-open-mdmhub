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
import com.yugandhar.mdm.extern.dobj.RefLeRoletypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefLeRoletypeDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefLeRoletypeComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefLeRoletypeRepository theRefLeRoletypeRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefLeRoletypeComponentRule theRefLeRoletypeComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefLeRoletypeComponent() {
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
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefLeRoletypeComponentRule.prevalidateRefLeRoletypeCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefLeRoletypeDO reqRefLeRoletypeDO = (RefLeRoletypeDO) txnTransferObj.getTxnPayload().getRefLeRoletypeDO();
			if (null == reqRefLeRoletypeDO.getPrimaryKeyDO()
					|| null == reqRefLeRoletypeDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefLeRoletypeDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefLeRoletypeDO.setIdPk(reqRefLeRoletypeDO.getPrimaryKeyDO().getIdPk());
				RefLeRoletypeDO dbimageRefLeRoletypeDO = entityManager.find(RefLeRoletypeDO.class,
						reqRefLeRoletypeDO.getIdPk());
				if (null != dbimageRefLeRoletypeDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefLeRoletypeComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefLeRoletypeComponentRule.preExecuteRefLeRoletypeCompPersist(reqRefLeRoletypeDO, txnTransferObj);
			entityManager.persist(reqRefLeRoletypeDO);
			entityManager.flush();
			reqRefLeRoletypeDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefLeRoletypeDO(new RefLeRoletypeDO(reqRefLeRoletypeDO));
			theRefLeRoletypeComponentRule.postExecuteRefLeRoletypeCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLeRoletypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLeRoletypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRoletypeComponent.persist failed unexpectedly");
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
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefLeRoletypeComponentRule.PrevalidateRefLeRoletypeCompMerge(txnTransferObj);
			RefLeRoletypeDO reqRefLeRoletypeDO = (RefLeRoletypeDO) txnTransferObj.getTxnPayload().getRefLeRoletypeDO();
			RefLeRoletypeDO dbimageRefLeRoletypeDO = entityManager.find(RefLeRoletypeDO.class,
					reqRefLeRoletypeDO.getIdPk());
			if (null == dbimageRefLeRoletypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLeRoletypeComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefLeRoletypeDO);
			BeanUtils.copyProperties(reqRefLeRoletypeDO, dbimageRefLeRoletypeDO, strIgnoreProperties);
			entityManager.detach(dbimageRefLeRoletypeDO);
			theRefLeRoletypeComponentRule.preExecuteRefLeRoletypeCompMerge(reqRefLeRoletypeDO, dbimageRefLeRoletypeDO,
					txnTransferObj);
			RefLeRoletypeDO mergedRefLeRoletypeDO = entityManager.merge(dbimageRefLeRoletypeDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefLeRoletypeDO(new RefLeRoletypeDO(mergedRefLeRoletypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLeRoletypeComponentRule.postExecuteRefLeRoletypeCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefLeRoletypeComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefLeRoletypeComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefLeRoletypeComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRoletypeComponent.merge failed unexpectedly");
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
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefLeRoletypeComponentRule.prevalidateRefLeRoletypeCompFindById(txnTransferObj);
			RefLeRoletypeDO reqRefLeRoletypeDO = (RefLeRoletypeDO) txnTransferObj.getTxnPayload().getRefLeRoletypeDO();
			RefLeRoletypeDO dbimageRefLeRoletypeDO = entityManager.find(RefLeRoletypeDO.class,
					reqRefLeRoletypeDO.getIdPk());
			if (null == dbimageRefLeRoletypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLeRoletypeComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefLeRoletypeDO(new RefLeRoletypeDO(dbimageRefLeRoletypeDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefLeRoletypeComponentRule.postExecuteRefLeRoletypeCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRoletypeComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefLeRoletypeDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLeRoletypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRoletypeDO is needed in the request");
		}

		RefLeRoletypeDO theRefLeRoletypeDO = (RefLeRoletypeDO) txnTransferObj.getTxnPayload().getRefLeRoletypeDO();
		if (null == theRefLeRoletypeDO.getKey() || theRefLeRoletypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLeRoletypeDO.key should not be null");
		}

		if (null == theRefLeRoletypeDO.getConfigLanguageCodeKey()
				|| theRefLeRoletypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRoletypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLeRoletypeDO.getValue() || theRefLeRoletypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLeRoletypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLeRoletypeDO.getConfigLanguageCodeKey()
				|| theRefLeRoletypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLeRoletypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLeRoletypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLeRoletypeDO.setCreatedTs(new Date());
		theRefLeRoletypeDO.setUpdatedTs(new Date());
		theRefLeRoletypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLeRoletypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefLeRoletypeDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLeRoletypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRoletypeDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLeRoletypeDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefLeRoletypeDO.version should not be null");
		}

		RefLeRoletypeDO theRefLeRoletypeDO = (RefLeRoletypeDO) txnTransferObj.getTxnPayload().getRefLeRoletypeDO();
		if (null == theRefLeRoletypeDO.getKey() || theRefLeRoletypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLeRoletypeDO.key should not be null");
		}

		if (null == theRefLeRoletypeDO.getConfigLanguageCodeKey()
				|| theRefLeRoletypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRoletypeDO.configLanguageCodeKey should not be null");
		}

		if (null == theRefLeRoletypeDO.getValue() || theRefLeRoletypeDO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"RefLeRoletypeDO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == theRefLeRoletypeDO.getConfigLanguageCodeKey()
				|| theRefLeRoletypeDO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(theRefLeRoletypeDO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"RefLeRoletypeDO.configLanguageCodeKey is not valid");
			}
		}

		theRefLeRoletypeDO.setUpdatedTs(new Date());
		theRefLeRoletypeDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefLeRoletypeDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefLeRoletypeDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefLeRoletypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRoletypeDO is needed in the request");
		}
		RefLeRoletypeDO theRefLeRoletypeDO = (RefLeRoletypeDO) txnTransferObj.getTxnPayload().getRefLeRoletypeDO();
		if (null == theRefLeRoletypeDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefLeRoletypeDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefLeRoletypeDO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<RefLeRoletypeDO> pageRefLeRoletypeDO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefLeRoletypeDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLeRoletype reference list does not have any records in the database");
			}

			if ((pageRefLeRoletypeDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefLeRoletype, total number of pages is "
								+ pageRefLeRoletypeDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefLeRoletypeDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefLeRoletypeDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefLeRoletypeDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefLeRoletypeDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefLeRoletypeDO.getSize());

			List<RefLeRoletypeDO> dbimageRefLeRoletypeDOlist = pageRefLeRoletypeDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefLeRoletypeDO> clonedRefLeRoletypeDOList = null;
			if (null != dbimageRefLeRoletypeDOlist && dbimageRefLeRoletypeDOlist.size() > 0) {
				clonedRefLeRoletypeDOList = new ArrayList<RefLeRoletypeDO>();
				Iterator<RefLeRoletypeDO> itr = dbimageRefLeRoletypeDOlist.iterator();
				while (itr.hasNext()) {
					RefLeRoletypeDO theRefLeRoletypeDO = new RefLeRoletypeDO(itr.next());
					clonedRefLeRoletypeDOList.add(theRefLeRoletypeDO);
				}
			}

			if (null == clonedRefLeRoletypeDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLeRoletype reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefLeRoletypeDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefLeRoletype reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefLeRoletypeDOList(clonedRefLeRoletypeDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRoletypeComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFLEROLETYPE_BUSKEY")
	public Page<RefLeRoletypeDO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefLeRoletypeDO> pageRefLeRoletypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefLeRoletypeDO = this.theRefLeRoletypeRepository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefLeRoletypeDO = this.theRefLeRoletypeRepository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefLeRoletypeDO = this.theRefLeRoletypeRepository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return pageRefLeRoletypeDO;
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
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefLeRoletypeDO reqRefLeRoletypeDO = (RefLeRoletypeDO) txnTransferObj.getTxnPayload().getRefLeRoletypeDO();
			theRefLeRoletypeComponentRule.preValidateRefLeRoletypefindByBusinessKey(txnTransferObj);
			theRefLeRoletypeComponentRule.preExecuteRefLeRoletypefindByBusinessKey(txnTransferObj);

			RefLeRoletypeDO dbimageRefLeRoletypeDO = executeRepositoryQuery(
					reqRefLeRoletypeDO.getConfigLanguageCodeKey(), reqRefLeRoletypeDO.getKey(),
					reqRefLeRoletypeDO.getInquiryFilter());

			if (null == dbimageRefLeRoletypeDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefLeRoletypeComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefLeRoletypeDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefLeRoletypeDO(new RefLeRoletypeDO(dbimageRefLeRoletypeDO));
			}

			theRefLeRoletypeComponentRule.postExecuteRefLeRoletypefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefLeRoletypeComponent.findByBusinessKey failed unexpectedly");
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
	 * @return RefLeRoletypeDO returns the populated RefLeRoletypeDO object
	 */
	@CacheResult(cacheName = "REFLEROLETYPE_BUSKEY")
	public RefLeRoletypeDO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		RefLeRoletypeDO dbimageRefLeRoletypeDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefLeRoletypeDO = this.theRefLeRoletypeRepository.findByBusinessKeyActive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefLeRoletypeDO = this.theRefLeRoletypeRepository.findByBusinessKeyInactive(configLanguageCodeKey,
					key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefLeRoletypeDO = this.theRefLeRoletypeRepository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimageRefLeRoletypeDO;
	}

	/**
	 * perform the common validation that RefLeRoletypeDO,
	 * RefLeRoletypeDO.configLanguageCodeKey and RefLeRoletypeDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLeRoletypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRoletypeDO is needed in the request");
		}
		RefLeRoletypeDO theRefLeRoletypeDO = (RefLeRoletypeDO) txnTransferObj.getTxnPayload().getRefLeRoletypeDO();
		if (null == theRefLeRoletypeDO.getKey() || theRefLeRoletypeDO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"RefLeRoletypeDO.key should not be null");
		}

		if (null == theRefLeRoletypeDO.getConfigLanguageCodeKey()
				|| theRefLeRoletypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRoletypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLeRoletypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that RefLeRoletypeDO and
	 * RefLeRoletypeDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefLeRoletypeDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefLeRoletypeDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefLeRoletypeDO is needed in the request");
		}
		RefLeRoletypeDO theRefLeRoletypeDO = (RefLeRoletypeDO) txnTransferObj.getTxnPayload().getRefLeRoletypeDO();

		if (null == theRefLeRoletypeDO.getConfigLanguageCodeKey()
				|| theRefLeRoletypeDO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"RefLeRoletypeDO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefLeRoletypeDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefLeRoletypeDO().getInquiryFilter());
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
