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
import com.yugandhar.mdm.extern.dobj.LeToLeRelationshipDO;
import com.yugandhar.mdm.extern.dobj.RefLeRelationshipTypeDO;
import com.yugandhar.mdm.extern.dobj.RefRelationshipStatusDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LeToLeRelationshipDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LeToLeRelationshipComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeToLeRelationshipComponentRule theLeToLeRelationshipComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LeToLeRelationshipComponent() {
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
	 *             if LeToLeRelationshipDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLeToLeRelationshipComponentRule.prevalidateLeToLeRelationshipCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			LeToLeRelationshipDO reqLeToLeRelationshipDO = (LeToLeRelationshipDO) txnTransferObj.getTxnPayload()
					.getLeToLeRelationshipDO();
			if (null == reqLeToLeRelationshipDO.getPrimaryKeyDO()
					|| null == reqLeToLeRelationshipDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLeToLeRelationshipDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLeToLeRelationshipDO.setIdPk(reqLeToLeRelationshipDO.getPrimaryKeyDO().getIdPk());
				LeToLeRelationshipDO dbimageLeToLeRelationshipDO = entityManager.find(LeToLeRelationshipDO.class,
						reqLeToLeRelationshipDO.getIdPk());
				if (null != dbimageLeToLeRelationshipDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LeToLeRelationshipComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLeToLeRelationshipComponentRule.preExecuteLeToLeRelationshipCompPersist(reqLeToLeRelationshipDO,
					txnTransferObj);
			entityManager.persist(reqLeToLeRelationshipDO);
			entityManager.flush();
			reqLeToLeRelationshipDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setLeToLeRelationshipDO(new LeToLeRelationshipDO(reqLeToLeRelationshipDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeToLeRelationshipDO());
			theLeToLeRelationshipComponentRule.postExecuteLeToLeRelationshipCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeToLeRelationshipComponent.persist failed - Unique Key Violated, record with fromLegalentityIdpk - toLegalentityIdpk - leRelationshipTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeToLeRelationshipComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeToLeRelationshipComponent.persist failed unexpectedly");
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
	 *             if LeToLeRelationshipDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLeToLeRelationshipComponentRule.PrevalidateLeToLeRelationshipCompMerge(txnTransferObj);
			LeToLeRelationshipDO reqLeToLeRelationshipDO = (LeToLeRelationshipDO) txnTransferObj.getTxnPayload()
					.getLeToLeRelationshipDO();
			LeToLeRelationshipDO dbimageLeToLeRelationshipDO = entityManager.find(LeToLeRelationshipDO.class,
					reqLeToLeRelationshipDO.getIdPk());
			if (null == dbimageLeToLeRelationshipDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeToLeRelationshipComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLeToLeRelationshipDO);
			BeanUtils.copyProperties(reqLeToLeRelationshipDO, dbimageLeToLeRelationshipDO, strIgnoreProperties);
			entityManager.detach(dbimageLeToLeRelationshipDO);
			theLeToLeRelationshipComponentRule.preExecuteLeToLeRelationshipCompMerge(reqLeToLeRelationshipDO,
					dbimageLeToLeRelationshipDO, txnTransferObj);
			LeToLeRelationshipDO mergedLeToLeRelationshipDO = entityManager.merge(dbimageLeToLeRelationshipDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setLeToLeRelationshipDO(new LeToLeRelationshipDO(mergedLeToLeRelationshipDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeToLeRelationshipDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeToLeRelationshipComponentRule.postExecuteLeToLeRelationshipCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LeToLeRelationshipComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeToLeRelationshipComponent.persist failed - Unique Key Violated, record with fromLegalentityIdpk - toLegalentityIdpk - leRelationshipTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeToLeRelationshipComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeToLeRelationshipComponent.merge failed unexpectedly");
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
	 *             if LeToLeRelationshipDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLeToLeRelationshipComponentRule.prevalidateLeToLeRelationshipCompFindById(txnTransferObj);
			LeToLeRelationshipDO reqLeToLeRelationshipDO = (LeToLeRelationshipDO) txnTransferObj.getTxnPayload()
					.getLeToLeRelationshipDO();
			LeToLeRelationshipDO dbimageLeToLeRelationshipDO = entityManager.find(LeToLeRelationshipDO.class,
					reqLeToLeRelationshipDO.getIdPk());
			if (null == dbimageLeToLeRelationshipDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeToLeRelationshipComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setLeToLeRelationshipDO(new LeToLeRelationshipDO(dbimageLeToLeRelationshipDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeToLeRelationshipDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeToLeRelationshipComponentRule.postExecuteLeToLeRelationshipCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeToLeRelationshipComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LeToLeRelationshipDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeToLeRelationshipDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeToLeRelationshipDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeToLeRelationshipDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeToLeRelationshipDO().getFromLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeToLeRelationshipDO().getFromLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10028",
					"LeToLeRelationshipDO.fromLegalentityIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeToLeRelationshipDO().getToLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeToLeRelationshipDO().getToLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10029",
					"LeToLeRelationshipDO.toLegalentityIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeToLeRelationshipDO().getLeRelationshipTypeRefkey()
				|| txnTransferObj.getTxnPayload().getLeToLeRelationshipDO().getLeRelationshipTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10030",
					"LeToLeRelationshipDO.leRelationshipTypeRefkey is needed in the request");
		}

		LeToLeRelationshipDO theLeToLeRelationshipDO = (LeToLeRelationshipDO) txnTransferObj.getTxnPayload()
				.getLeToLeRelationshipDO();
		theLeToLeRelationshipDO.setCreatedTs(new Date());
		theLeToLeRelationshipDO.setUpdatedTs(new Date());
		theLeToLeRelationshipDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeToLeRelationshipDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LeToLeRelationshipDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeToLeRelationshipDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeToLeRelationshipDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeToLeRelationshipDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLeToLeRelationshipDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLeToLeRelationshipDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeToLeRelationshipDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLeToLeRelationshipDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LeToLeRelationshipDO.version should not be null");
		}

		LeToLeRelationshipDO theLeToLeRelationshipDO = (LeToLeRelationshipDO) txnTransferObj.getTxnPayload()
				.getLeToLeRelationshipDO();
		theLeToLeRelationshipDO.setUpdatedTs(new Date());
		theLeToLeRelationshipDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeToLeRelationshipDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LeToLeRelationshipDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeToLeRelationshipDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeToLeRelationshipDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeToLeRelationshipDO is needed in the request");
		}
		LeToLeRelationshipDO theLeToLeRelationshipDO = (LeToLeRelationshipDO) txnTransferObj.getTxnPayload()
				.getLeToLeRelationshipDO();
		if (null == theLeToLeRelationshipDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeToLeRelationshipDO.idpk should not be null");
		}

	}

	// get the repository instance
	@Autowired
	private LeToLeRelationshipRepository theLeToLeRelationshipRepository;

	/**
	 * This method search the database record based on primary key.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeToLeRelationshipDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalentityIdpk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalentityIdpk(txnTransferObj);
			theLeToLeRelationshipComponentRule.prevalidateLeToLeRelationshipCompFindByLegalentityIdpk(txnTransferObj);
			LeToLeRelationshipDO reqLeToLeRelationshipDO = (LeToLeRelationshipDO) txnTransferObj.getTxnPayload()
					.getLeToLeRelationshipDO();

			Page<LeToLeRelationshipDO> pageLeToLeRelationshipDO = findByLegalentityIdpkFromRepository(
					reqLeToLeRelationshipDO.getFromLegalentityIdpk(), reqLeToLeRelationshipDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageLeToLeRelationshipDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageLeToLeRelationshipDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageLeToLeRelationshipDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageLeToLeRelationshipDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLeToLeRelationshipDO.getSize());

			// process pages if response have results
			if (pageLeToLeRelationshipDO.getTotalPages() != 0) {
				if ((pageLeToLeRelationshipDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for LeToLeRelationshipComponent.findByLegalEntityIdPk, total number of pages is "
									+ pageLeToLeRelationshipDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<LeToLeRelationshipDO> dbimageLeToLeRelationshipDOList = pageLeToLeRelationshipDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<LeToLeRelationshipDO> clonedLeToLeRelationshipDOList = new ArrayList<LeToLeRelationshipDO>();
				if (null != dbimageLeToLeRelationshipDOList && dbimageLeToLeRelationshipDOList.size() > 0) {
					Iterator<LeToLeRelationshipDO> itr = dbimageLeToLeRelationshipDOList.iterator();
					while (itr.hasNext()) {
						LeToLeRelationshipDO theLeToLeRelationshipDO = new LeToLeRelationshipDO(itr.next());
						clonedLeToLeRelationshipDOList.add(theLeToLeRelationshipDO);
					}
				}

				// populate reference data attributes
				if (null != clonedLeToLeRelationshipDOList && clonedLeToLeRelationshipDOList.size() > 0) {
					Iterator<LeToLeRelationshipDO> itr = clonedLeToLeRelationshipDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setLeToLeRelationshipDOList(clonedLeToLeRelationshipDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeToLeRelationshipComponentRule
					.postExecuteLeToLeRelationshipCompFindByLegalentityIdpk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LeToLeRelationshipComponent.findByLegalentityIdpk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeToLeRelationshipComponent.findByLegalentityIdpk failed unexpectedly");
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

	private void performCommonvalidationBeforeFindByLegalentityIdpk(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeToLeRelationshipDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeToLeRelationshipDO is needed in the request");
		}
		LeToLeRelationshipDO theLeToLeRelationshipDO = (LeToLeRelationshipDO) txnTransferObj.getTxnPayload()
				.getLeToLeRelationshipDO();
		if (null == theLeToLeRelationshipDO.getFromLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10069",
					"LeToLeRelationshipDO.fromLegalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLeToLeRelationshipDO.getInquiryFilter()
				|| theLeToLeRelationshipDO.getInquiryFilter().isEmpty()) {
			theLeToLeRelationshipDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLeToLeRelationshipDO.getInquiryFilter());
		}

	}

	public Page<LeToLeRelationshipDO> findByLegalentityIdpkFromRepository(String theLegalEntityIdpk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "fromLegalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<LeToLeRelationshipDO> pageLeToLeRelationshipDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLeToLeRelationshipDOlist = this.theLeToLeRelationshipRepository
					.findAllActiveByLegalentityIdpk(theLegalEntityIdpk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLeToLeRelationshipDOlist = this.theLeToLeRelationshipRepository
					.findAllInActiveByLegalentityIdpk(theLegalEntityIdpk, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLeToLeRelationshipDOlist = this.theLeToLeRelationshipRepository
					.findAllByLegalentityIdpk(theLegalEntityIdpk, localPageable);

		}

		return pageLeToLeRelationshipDOlist;

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			LeToLeRelationshipDO theLeToLeRelationshipDO) {

		// LeRelationshipTypeRefkey
		if (!(null == theLeToLeRelationshipDO.getLeRelationshipTypeRefkey()
				|| theLeToLeRelationshipDO.getLeRelationshipTypeRefkey().isEmpty())) {
			RefLeRelationshipTypeDO theRefLeRelationshipTypeDO = referenceTableHelper
					.getRefLeRelationshipTypeValueByKey(requesterLanguage,
							theLeToLeRelationshipDO.getLeRelationshipTypeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRelationshipTypeDO) {
				theLeToLeRelationshipDO.setLeRelationshipTypeRefValue(theRefLeRelationshipTypeDO.getValue());
			}
		}

		// RelationshipStatusRefkey
		if (!(null == theLeToLeRelationshipDO.getRelationshipStatusRefkey()
				|| theLeToLeRelationshipDO.getRelationshipStatusRefkey().isEmpty())) {
			RefRelationshipStatusDO theRefRelationshipStatusDO = referenceTableHelper
					.getRefRelationshipStatusValueByKey(requesterLanguage,
							theLeToLeRelationshipDO.getRelationshipStatusRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefRelationshipStatusDO) {
				theLeToLeRelationshipDO.setRelationshipStatusRefValue(theRefRelationshipStatusDO.getValue());
			}
		}

	}

}
