package com.yugandhar.mdm.corecomponent;
/* Generated Jul 1, 2017 2:56:24 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.LeSystemKeysRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.extern.dobj.RefStatusInSourceDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LeSystemKeysRegistryDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LeSystemKeysRegistryComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeSystemKeysRegistryComponentRule theLeSystemKeysRegistryComponentRule;

	// get the repository instance
	@Autowired
	private LeSystemKeysRegistryRepository theLeSystemKeysRegistryRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LeSystemKeysRegistryComponent() {
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
	 *             if LeSystemKeysRegistryDO object is not present in the
	 *             request or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLeSystemKeysRegistryComponentRule.prevalidateLeSystemKeysRegistryCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			LeSystemKeysRegistryDO reqLeSystemKeysRegistryDO = (LeSystemKeysRegistryDO) txnTransferObj.getTxnPayload()
					.getLeSystemKeysRegistryDO();
			if (null == reqLeSystemKeysRegistryDO.getPrimaryKeyDO()
					|| null == reqLeSystemKeysRegistryDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLeSystemKeysRegistryDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLeSystemKeysRegistryDO.setIdPk(reqLeSystemKeysRegistryDO.getPrimaryKeyDO().getIdPk());
				LeSystemKeysRegistryDO dbimageLeSystemKeysRegistryDO = entityManager.find(LeSystemKeysRegistryDO.class,
						reqLeSystemKeysRegistryDO.getIdPk());
				if (null != dbimageLeSystemKeysRegistryDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LeSystemKeysRegistryComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLeSystemKeysRegistryComponentRule.preExecuteLeSystemKeysRegistryCompPersist(reqLeSystemKeysRegistryDO,
					txnTransferObj);
			entityManager.persist(reqLeSystemKeysRegistryDO);
			entityManager.flush();
			reqLeSystemKeysRegistryDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setLeSystemKeysRegistryDO(new LeSystemKeysRegistryDO(reqLeSystemKeysRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO());
			theLeSystemKeysRegistryComponentRule.postExecuteLeSystemKeysRegistryCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeSystemKeysRegistryComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - sourceSystemRefkey already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeSystemKeysRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeSystemKeysRegistryComponent.persist failed unexpectedly"); // Transaction
																					// Failed
																					// due
																					// to
																					// unknown
																					// error,
																					// please
																					// check
																					// statck
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
	 *             if LeSystemKeysRegistryDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLeSystemKeysRegistryComponentRule.PrevalidateLeSystemKeysRegistryCompMerge(txnTransferObj);
			LeSystemKeysRegistryDO reqLeSystemKeysRegistryDO = (LeSystemKeysRegistryDO) txnTransferObj.getTxnPayload()
					.getLeSystemKeysRegistryDO();
			LeSystemKeysRegistryDO dbimageLeSystemKeysRegistryDO = entityManager.find(LeSystemKeysRegistryDO.class,
					reqLeSystemKeysRegistryDO.getIdPk());
			if (null == dbimageLeSystemKeysRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeSystemKeysRegistryComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLeSystemKeysRegistryDO);
			BeanUtils.copyProperties(reqLeSystemKeysRegistryDO, dbimageLeSystemKeysRegistryDO, strIgnoreProperties);
			entityManager.detach(dbimageLeSystemKeysRegistryDO);
			theLeSystemKeysRegistryComponentRule.preExecuteLeSystemKeysRegistryCompMerge(reqLeSystemKeysRegistryDO,
					dbimageLeSystemKeysRegistryDO, txnTransferObj);
			LeSystemKeysRegistryDO mergedLeSystemKeysRegistryDO = entityManager.merge(dbimageLeSystemKeysRegistryDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setLeSystemKeysRegistryDO(new LeSystemKeysRegistryDO(mergedLeSystemKeysRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeSystemKeysRegistryComponentRule.postExecuteLeSystemKeysRegistryCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LeSystemKeysRegistryComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeSystemKeysRegistryComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - sourceSystemRefkey already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeSystemKeysRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeSystemKeysRegistryComponent.merge failed unexpectedly");
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
	 *             if LeSystemKeysRegistryDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLeSystemKeysRegistryComponentRule.prevalidateLeSystemKeysRegistryCompFindById(txnTransferObj);
			LeSystemKeysRegistryDO reqLeSystemKeysRegistryDO = (LeSystemKeysRegistryDO) txnTransferObj.getTxnPayload()
					.getLeSystemKeysRegistryDO();
			LeSystemKeysRegistryDO dbimageLeSystemKeysRegistryDO = entityManager.find(LeSystemKeysRegistryDO.class,
					reqLeSystemKeysRegistryDO.getIdPk());
			if (null == dbimageLeSystemKeysRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeSystemKeysRegistryComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setLeSystemKeysRegistryDO(new LeSystemKeysRegistryDO(dbimageLeSystemKeysRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeSystemKeysRegistryComponentRule.postExecuteLeSystemKeysRegistryCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeSystemKeysRegistryComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LeSystemKeysRegistryDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeSystemKeysRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeSystemKeysRegistryDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeSystemKeysRegistryDO.LegalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO().getSourceSystemRefkey()
				|| txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO().getSourceSystemRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10034",
					"LeSystemKeysRegistryDO.sourceSystemRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO().getReferenceId()
				|| txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO().getReferenceId().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10035",
					"LeSystemKeysRegistryDO.referenceId is needed in the request");
		}

		LeSystemKeysRegistryDO theLeSystemKeysRegistryDO = (LeSystemKeysRegistryDO) txnTransferObj.getTxnPayload()
				.getLeSystemKeysRegistryDO();
		theLeSystemKeysRegistryDO.setCreatedTs(new Date());
		theLeSystemKeysRegistryDO.setUpdatedTs(new Date());
		theLeSystemKeysRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeSystemKeysRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LeSystemKeysRegistryDO
	 * object in the database. populate updatedTimestamp, transaction reference
	 * Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeSystemKeysRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeSystemKeysRegistryDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeSystemKeysRegistryDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LeSystemKeysRegistryDO.version should not be null");
		}

		LeSystemKeysRegistryDO theLeSystemKeysRegistryDO = (LeSystemKeysRegistryDO) txnTransferObj.getTxnPayload()
				.getLeSystemKeysRegistryDO();
		theLeSystemKeysRegistryDO.setUpdatedTs(new Date());
		theLeSystemKeysRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeSystemKeysRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LeSystemKeysRegistryDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeSystemKeysRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeSystemKeysRegistryDO is needed in the request");
		}
		LeSystemKeysRegistryDO theLeSystemKeysRegistryDO = (LeSystemKeysRegistryDO) txnTransferObj.getTxnPayload()
				.getLeSystemKeysRegistryDO();
		if (null == theLeSystemKeysRegistryDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeSystemKeysRegistryDO.idpk should not be null");
		}

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
	 *             if LeSystemKeysRegistryDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
			theLeSystemKeysRegistryComponentRule.prevalidateLeSystemKeysRegistryCompFindByLegalEntityIdPk(txnTransferObj);
			LeSystemKeysRegistryDO reqLeSystemKeysRegistryDO = (LeSystemKeysRegistryDO) txnTransferObj.getTxnPayload()
					.getLeSystemKeysRegistryDO();

			Page<LeSystemKeysRegistryDO> pageLeSystemKeysRegistryDO = findByLegalEntityIdPkFromRepository(
					reqLeSystemKeysRegistryDO.getLegalentityIdpk(), reqLeSystemKeysRegistryDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageLeSystemKeysRegistryDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageLeSystemKeysRegistryDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageLeSystemKeysRegistryDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageLeSystemKeysRegistryDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLeSystemKeysRegistryDO.getSize());

			// process pages if response have results
			if (pageLeSystemKeysRegistryDO.getTotalPages() != 0) {
				if ((pageLeSystemKeysRegistryDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for LeSystemKeysRegistryComponent.findByLegalEntityIdPk, total number of pages is "
									+ pageLeSystemKeysRegistryDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<LeSystemKeysRegistryDO> dbimageLeSystemKeysRegistryDOList = pageLeSystemKeysRegistryDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<LeSystemKeysRegistryDO> clonedLeSystemKeysRegistryDOList = new ArrayList<LeSystemKeysRegistryDO>();
				if (null != dbimageLeSystemKeysRegistryDOList && dbimageLeSystemKeysRegistryDOList.size() > 0) {
					Iterator<LeSystemKeysRegistryDO> itr = dbimageLeSystemKeysRegistryDOList.iterator();
					while (itr.hasNext()) {
						LeSystemKeysRegistryDO theLeSystemKeysRegistryDO = new LeSystemKeysRegistryDO(itr.next());
						clonedLeSystemKeysRegistryDOList.add(theLeSystemKeysRegistryDO);
					}
				}

				// populate reference data attributes
				if (null != clonedLeSystemKeysRegistryDOList && clonedLeSystemKeysRegistryDOList.size() > 0) {
					Iterator<LeSystemKeysRegistryDO> itr = clonedLeSystemKeysRegistryDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setLeSystemKeysRegistryDOList(clonedLeSystemKeysRegistryDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeSystemKeysRegistryComponentRule.postExecuteLeSystemKeysRegistryCompFindByLegalEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LeSystemKeysRegistryComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeSystemKeysRegistryComponent.findByLegalEntityIdPk failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/*
	 * Sample Code
	 * 
	 * @Autowired protected RefEntityObjectTypeComponent
	 * theRefEntityObjectTypeComponent;
	 */

	private void performCommonvalidationBeforeFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeSystemKeysRegistryDO is needed in the request");
		}
		LeSystemKeysRegistryDO theLeSystemKeysRegistryDO = (LeSystemKeysRegistryDO) txnTransferObj.getTxnPayload()
				.getLeSystemKeysRegistryDO();
		if (null == theLeSystemKeysRegistryDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeSystemKeysRegistryDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLeSystemKeysRegistryDO.getInquiryFilter()
				|| theLeSystemKeysRegistryDO.getInquiryFilter().isEmpty()) {
			theLeSystemKeysRegistryDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLeSystemKeysRegistryDO.getInquiryFilter());
		}

	}

	public Page<LeSystemKeysRegistryDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {


		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<LeSystemKeysRegistryDO> pageLeSystemKeysRegistryDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLeSystemKeysRegistryDOlist = this.theLeSystemKeysRegistryRepository
					.findAllActiveByLegalentityIdpk(legalEntityIdPk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLeSystemKeysRegistryDOlist = this.theLeSystemKeysRegistryRepository
					.findAllInActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLeSystemKeysRegistryDOlist = this.theLeSystemKeysRegistryRepository
					.findAllByLegalentityIdpk(legalEntityIdPk,localPageable);

		}

		return pageLeSystemKeysRegistryDOlist;

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			LeSystemKeysRegistryDO theLeSystemKeysRegistryDO) {

		// SourceSystemRefkey
		if (!(null == theLeSystemKeysRegistryDO.getSourceSystemRefkey()
				|| theLeSystemKeysRegistryDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					requesterLanguage, theLeSystemKeysRegistryDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {
				theLeSystemKeysRegistryDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
			}
		}

		// StatusInSourceRefkey
		if (!(null == theLeSystemKeysRegistryDO.getStatusInSourceRefkey()
				|| theLeSystemKeysRegistryDO.getStatusInSourceRefkey().isEmpty())) {
			RefStatusInSourceDO theRefStatusInSourceDO = referenceTableHelper.getRefStatusInSourceValueByKey(
					requesterLanguage, theLeSystemKeysRegistryDO.getStatusInSourceRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefStatusInSourceDO) {
				theLeSystemKeysRegistryDO.setStatusInSourceRefValue(theRefStatusInSourceDO.getValue());
			}
		}

	}

}
