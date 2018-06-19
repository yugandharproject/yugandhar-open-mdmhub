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
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.RefMatchProposedActionDO;
import com.yugandhar.mdm.extern.dobj.RefMatchResultDO;
import com.yugandhar.mdm.extern.dobj.RefMatchScoreDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model RefMatchScoreDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class RefMatchScoreComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private RefMatchScoreRepository theRefMatchScoreRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefMatchScoreComponentRule theRefMatchScoreComponentRule;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public RefMatchScoreComponent() {
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
	 *             if RefMatchScoreDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theRefMatchScoreComponentRule.prevalidateRefMatchScoreCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			RefMatchScoreDO reqRefMatchScoreDO = (RefMatchScoreDO) txnTransferObj.getTxnPayload().getRefMatchScoreDO();
			if (null == reqRefMatchScoreDO.getPrimaryKeyDO()
					|| null == reqRefMatchScoreDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqRefMatchScoreDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqRefMatchScoreDO.setIdPk(reqRefMatchScoreDO.getPrimaryKeyDO().getIdPk());
				RefMatchScoreDO dbimageRefMatchScoreDO = entityManager.find(RefMatchScoreDO.class,
						reqRefMatchScoreDO.getIdPk());
				if (null != dbimageRefMatchScoreDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"RefMatchScoreComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theRefMatchScoreComponentRule.preExecuteRefMatchScoreCompPersist(reqRefMatchScoreDO, txnTransferObj);
			entityManager.persist(reqRefMatchScoreDO);
			entityManager.flush();
			reqRefMatchScoreDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setRefMatchScoreDO(new RefMatchScoreDO(reqRefMatchScoreDO));
			theRefMatchScoreComponentRule.postExecuteRefMatchScoreCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchScoreComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchScoreComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchScoreComponent.persist failed unexpectedly");
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
	 *             if RefMatchScoreDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theRefMatchScoreComponentRule.PrevalidateRefMatchScoreCompMerge(txnTransferObj);
			RefMatchScoreDO reqRefMatchScoreDO = (RefMatchScoreDO) txnTransferObj.getTxnPayload().getRefMatchScoreDO();
			RefMatchScoreDO dbimageRefMatchScoreDO = entityManager.find(RefMatchScoreDO.class,
					reqRefMatchScoreDO.getIdPk());
			if (null == dbimageRefMatchScoreDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchScoreComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqRefMatchScoreDO);
			BeanUtils.copyProperties(reqRefMatchScoreDO, dbimageRefMatchScoreDO, strIgnoreProperties);
			entityManager.detach(dbimageRefMatchScoreDO);
			theRefMatchScoreComponentRule.preExecuteRefMatchScoreCompMerge(reqRefMatchScoreDO, dbimageRefMatchScoreDO,
					txnTransferObj);
			RefMatchScoreDO mergedRefMatchScoreDO = entityManager.merge(dbimageRefMatchScoreDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefMatchScoreDO(new RefMatchScoreDO(mergedRefMatchScoreDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchScoreComponentRule.postExecuteRefMatchScoreCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in RefMatchScoreComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"RefMatchScoreComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"RefMatchScoreComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchScoreComponent.merge failed unexpectedly");
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
	 *             if RefMatchScoreDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theRefMatchScoreComponentRule.prevalidateRefMatchScoreCompFindById(txnTransferObj);
			RefMatchScoreDO reqRefMatchScoreDO = (RefMatchScoreDO) txnTransferObj.getTxnPayload().getRefMatchScoreDO();
			RefMatchScoreDO dbimageRefMatchScoreDO = entityManager.find(RefMatchScoreDO.class,
					reqRefMatchScoreDO.getIdPk());
			if (null == dbimageRefMatchScoreDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchScoreComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setRefMatchScoreDO(new RefMatchScoreDO(dbimageRefMatchScoreDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theRefMatchScoreComponentRule.postExecuteRefMatchScoreCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchScoreComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting RefMatchScoreDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchScoreDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchScoreDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchScoreDO is needed in the request");
		}

		RefMatchScoreDO theRefMatchScoreDO = (RefMatchScoreDO) txnTransferObj.getTxnPayload().getRefMatchScoreDO();
		if (null == theRefMatchScoreDO.getMatchEntityObjectName()
				|| theRefMatchScoreDO.getMatchEntityObjectName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10087",
					"refMatchScoreDO.matchEntityObjectName is needed in the request");
		}

		if (null == theRefMatchScoreDO.getMatchAttrPattern() || theRefMatchScoreDO.getMatchAttrPattern().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10088",
					"refMatchScoreDO.matchAttrPattern is needed in the request");
		}

		if (null == theRefMatchScoreDO.getMatchResultRefkey() || theRefMatchScoreDO.getMatchResultRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10089",
					"refMatchScoreDO.matchResultRefkey is needed in the request");
		}

		if (null == theRefMatchScoreDO.getMatchProposedActionRefkey()
				|| theRefMatchScoreDO.getMatchProposedActionRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10090",
					"refMatchScoreDO.matchProposedActionRefkey is needed in the request");
		}

		if (!(null == theRefMatchScoreDO.getMatchResultRefkey()
				|| theRefMatchScoreDO.getMatchResultRefkey().isEmpty())) {
			RefMatchResultDO theRefMatchResultDO = referenceTableHelper.getRefMatchResultValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), theRefMatchScoreDO.getMatchResultRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theRefMatchResultDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10091",
						"refMatchScoreDO.matchResultRefkey is not valid");
			}
		}

		if (!(null == theRefMatchScoreDO.getMatchProposedActionRefkey()
				|| theRefMatchScoreDO.getMatchProposedActionRefkey().isEmpty())) {
			RefMatchProposedActionDO theRefMatchProposedActionDO = referenceTableHelper
					.getRefMatchProposedActionValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							theRefMatchScoreDO.getMatchProposedActionRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theRefMatchProposedActionDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10092",
						"refMatchScoreDO.matchProposedActionRefkey is not valid");
			}
		}

		theRefMatchScoreDO.setCreatedTs(new Date());
		theRefMatchScoreDO.setUpdatedTs(new Date());
		theRefMatchScoreDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchScoreDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating RefMatchScoreDO object in
	 * the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchScoreDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchScoreDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchScoreDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getRefMatchScoreDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getRefMatchScoreDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchScoreDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getRefMatchScoreDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"RefMatchScoreDO.version should not be null");
		}

		RefMatchScoreDO theRefMatchScoreDO = (RefMatchScoreDO) txnTransferObj.getTxnPayload().getRefMatchScoreDO();

		if (null == theRefMatchScoreDO.getMatchEntityObjectName()
				|| theRefMatchScoreDO.getMatchEntityObjectName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10087",
					"refMatchScoreDO.matchEntityObjectName is needed in the request");
		}

		if (null == theRefMatchScoreDO.getMatchAttrPattern() || theRefMatchScoreDO.getMatchAttrPattern().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10088",
					"refMatchScoreDO.matchAttrPattern is needed in the request");
		}

		if (null == theRefMatchScoreDO.getMatchResultRefkey() || theRefMatchScoreDO.getMatchResultRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10089",
					"refMatchScoreDO.matchResultRefkey is needed in the request");
		}

		if (null == theRefMatchScoreDO.getMatchProposedActionRefkey()
				|| theRefMatchScoreDO.getMatchProposedActionRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10090",
					"refMatchScoreDO.matchProposedActionRefkey is needed in the request");
		}

		if (!(null == theRefMatchScoreDO.getMatchResultRefkey()
				|| theRefMatchScoreDO.getMatchResultRefkey().isEmpty())) {
			RefMatchResultDO theRefMatchResultDO = referenceTableHelper.getRefMatchResultValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), theRefMatchScoreDO.getMatchResultRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theRefMatchResultDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10091",
						"refMatchScoreDO.matchResultRefkey is not valid");
			}
		}

		if (!(null == theRefMatchScoreDO.getMatchProposedActionRefkey()
				|| theRefMatchScoreDO.getMatchProposedActionRefkey().isEmpty())) {
			RefMatchProposedActionDO theRefMatchProposedActionDO = referenceTableHelper
					.getRefMatchProposedActionValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							theRefMatchScoreDO.getMatchProposedActionRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theRefMatchProposedActionDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10092",
						"refMatchScoreDO.matchProposedActionRefkey is not valid");
			}
		}

		theRefMatchScoreDO.setUpdatedTs(new Date());
		theRefMatchScoreDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theRefMatchScoreDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that RefMatchScoreDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchScoreDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getRefMatchScoreDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchScoreDO is needed in the request");
		}
		RefMatchScoreDO theRefMatchScoreDO = (RefMatchScoreDO) txnTransferObj.getTxnPayload().getRefMatchScoreDO();
		if (null == theRefMatchScoreDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"RefMatchScoreDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for RefMatchScoreDO entity based on
	 * matchEntityObjectName, matchAttrPattern, matchResultRefkey,
	 * matchProposedActionRefkey and maximum of records as configuration in
	 * properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchScoreDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByMatchEntityObjectName(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByMatchEntityObjectName(txnTransferObj);
			Page<RefMatchScoreDO> pageRefMatchScoreDO = findAllRecordsByMatchEntityObjectNameFromRepository(
					txnTransferObj.getTxnPayload().getRefMatchScoreDO().getMatchEntityObjectName(),
					txnTransferObj.getTxnPayload().getRefMatchScoreDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageRefMatchScoreDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchScore reference list does not have any records in the database");
			}

			if ((pageRefMatchScoreDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for RefMatchScore, total number of pages is "
								+ pageRefMatchScoreDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageRefMatchScoreDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageRefMatchScoreDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageRefMatchScoreDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageRefMatchScoreDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageRefMatchScoreDO.getSize());

			List<RefMatchScoreDO> dbimageRefMatchScoreDOlist = pageRefMatchScoreDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<RefMatchScoreDO> clonedRefMatchScoreDOList = null;
			if (null != dbimageRefMatchScoreDOlist && dbimageRefMatchScoreDOlist.size() > 0) {
				clonedRefMatchScoreDOList = new ArrayList<RefMatchScoreDO>();
				Iterator<RefMatchScoreDO> itr = dbimageRefMatchScoreDOlist.iterator();
				while (itr.hasNext()) {
					RefMatchScoreDO theRefMatchScoreDO = new RefMatchScoreDO(itr.next());
					clonedRefMatchScoreDOList.add(theRefMatchScoreDO);
				}
			}

			if (null == clonedRefMatchScoreDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchScore reference list does not have any records in the database");
				// Record not found
			} else if (clonedRefMatchScoreDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"RefMatchScore reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setRefMatchScoreDOList(clonedRefMatchScoreDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByMatchEntityObjectNameAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchScoreComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "REFMATCHSCORE_BUSKEY")
	public Page<RefMatchScoreDO> findAllRecordsByMatchEntityObjectNameFromRepository(
			@CacheKey String matchEntityObjectName, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<RefMatchScoreDO> pageRefMatchScoreDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageRefMatchScoreDO = this.theRefMatchScoreRepository
					.findAllActiveByMatchEntityObjectName(matchEntityObjectName, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageRefMatchScoreDO = this.theRefMatchScoreRepository
					.findAllInActiveByMatchEntityObjectName(matchEntityObjectName, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageRefMatchScoreDO = this.theRefMatchScoreRepository.findAllByMatchEntityObjectName(matchEntityObjectName,
					localPageable);

		}

		return pageRefMatchScoreDO;
	}

	/**
	 * perform the common validation that RefMatchScoreDO and
	 * RefMatchScoreDO.configMatchEntityObjectNameKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchScoreDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByMatchEntityObjectName(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchScoreDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchScoreDO is needed in the request");
		}
		RefMatchScoreDO theRefMatchScoreDO = (RefMatchScoreDO) txnTransferObj.getTxnPayload().getRefMatchScoreDO();

		if (null == theRefMatchScoreDO.getMatchEntityObjectName()
				|| theRefMatchScoreDO.getMatchEntityObjectName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10087",
					"refMatchScoreDO.matchEntityObjectName is needed in the request");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchScoreDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchScoreDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchScoreDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchScoreDO().getInquiryFilter());
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

	/**
	 * This method search the database table records based on business key
	 * (matchEntityObjectName and matchAttrPattern)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchScoreDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			RefMatchScoreDO reqRefMatchScoreDO = (RefMatchScoreDO) txnTransferObj.getTxnPayload().getRefMatchScoreDO();
			theRefMatchScoreComponentRule.preValidateRefMatchScorefindByBusinessKey(txnTransferObj);
			theRefMatchScoreComponentRule.preExecuteRefMatchScorefindByBusinessKey(txnTransferObj);

			RefMatchScoreDO dbimageRefMatchScoreDO = executeRepositoryQuery(
					reqRefMatchScoreDO.getMatchEntityObjectName(), reqRefMatchScoreDO.getMatchAttrPattern(),
					reqRefMatchScoreDO.getInquiryFilter());

			if (null == dbimageRefMatchScoreDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"RefMatchScoreComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageRefMatchScoreDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getRefMatchScoreDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setRefMatchScoreDO(new RefMatchScoreDO(dbimageRefMatchScoreDO));
			}

			theRefMatchScoreComponentRule.postExecuteRefMatchScorefindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"RefMatchScoreComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	 * perform the common validation that RefMatchScoreDO,
	 * RefMatchScoreDO.matchEntityObjectName, matchAttrPattern,
	 * matchResultRefkey, matchProposedActionRefkey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if RefMatchScoreDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getRefMatchScoreDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"RefMatchScoreDO is needed in the request");
		}
		RefMatchScoreDO theRefMatchScoreDO = (RefMatchScoreDO) txnTransferObj.getTxnPayload().getRefMatchScoreDO();

		if (null == theRefMatchScoreDO.getMatchEntityObjectName()
				|| theRefMatchScoreDO.getMatchEntityObjectName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10087",
					"refMatchScoreDO.matchEntityObjectName is needed in the request");
		}

		if (null == theRefMatchScoreDO.getMatchAttrPattern() || theRefMatchScoreDO.getMatchAttrPattern().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10088",
					"refMatchScoreDO.matchAttrPattern is needed in the request");
		}

		

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getRefMatchScoreDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getRefMatchScoreDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getRefMatchScoreDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getRefMatchScoreDO().getInquiryFilter());
		}
	}

	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param matchEntityObjectName match entity object name
	 * @param matchAttrPattern match pattern
	 * @param filter filter value
	 * @return RefMatchScoreDO returns the populated RefMatchScoreDO object
	 */
	@CacheResult(cacheName = "REFMATCHSCORE_BUSKEY")
	public RefMatchScoreDO executeRepositoryQuery(String matchEntityObjectName, String matchAttrPattern,
			String filter) {
		RefMatchScoreDO dbimageRefMatchScoreDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageRefMatchScoreDO = this.theRefMatchScoreRepository.findByBusinessKeyActive(matchEntityObjectName,
					matchAttrPattern);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageRefMatchScoreDO = this.theRefMatchScoreRepository.findByBusinessKeyInactive(matchEntityObjectName,
					matchAttrPattern);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageRefMatchScoreDO = this.theRefMatchScoreRepository.findByBusinessKeyAll(matchEntityObjectName,
					matchAttrPattern);

		}

		return dbimageRefMatchScoreDO;
	}

}
