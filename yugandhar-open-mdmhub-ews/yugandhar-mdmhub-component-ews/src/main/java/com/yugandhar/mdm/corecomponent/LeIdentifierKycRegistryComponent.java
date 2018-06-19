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
import com.yugandhar.mdm.extern.dobj.LeIdentifierKycRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefIdentificationTypeDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LeIdentifierKycRegistryDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LeIdentifierKycRegistryComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeIdentifierKycRegistryComponentRule theLeIdentifierKycRegistryComponentRule;

	@Autowired
	LeIdentifierKycRegistryRepository theLeIdentifierKycRegistryRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LeIdentifierKycRegistryComponent() {
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
	 *             if LeIdentifierKycRegistryDO object is not present in the
	 *             request or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLeIdentifierKycRegistryComponentRule.prevalidateLeIdentifierKycRegistryCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			LeIdentifierKycRegistryDO reqLeIdentifierKycRegistryDO = (LeIdentifierKycRegistryDO) txnTransferObj
					.getTxnPayload().getLeIdentifierKycRegistryDO();
			if (null == reqLeIdentifierKycRegistryDO.getPrimaryKeyDO()
					|| null == reqLeIdentifierKycRegistryDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLeIdentifierKycRegistryDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLeIdentifierKycRegistryDO.setIdPk(reqLeIdentifierKycRegistryDO.getPrimaryKeyDO().getIdPk());
				LeIdentifierKycRegistryDO dbimageLeIdentifierKycRegistryDO = entityManager
						.find(LeIdentifierKycRegistryDO.class, reqLeIdentifierKycRegistryDO.getIdPk());
				if (null != dbimageLeIdentifierKycRegistryDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LeIdentifierKycRegistryComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLeIdentifierKycRegistryComponentRule
					.preExecuteLeIdentifierKycRegistryCompPersist(reqLeIdentifierKycRegistryDO, txnTransferObj);
			entityManager.persist(reqLeIdentifierKycRegistryDO);
			entityManager.flush();
			reqLeIdentifierKycRegistryDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setLeIdentifierKycRegistryDO(new LeIdentifierKycRegistryDO(reqLeIdentifierKycRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO());
			theLeIdentifierKycRegistryComponentRule.postExecuteLeIdentifierKycRegistryCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeIdentifierKycRegistryComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - identificationTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeIdentifierKycRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeIdentifierKycRegistryComponent.persist failed unexpectedly");
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
	 *             if LeIdentifierKycRegistryDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLeIdentifierKycRegistryComponentRule.PrevalidateLeIdentifierKycRegistryCompMerge(txnTransferObj);
			LeIdentifierKycRegistryDO reqLeIdentifierKycRegistryDO = (LeIdentifierKycRegistryDO) txnTransferObj
					.getTxnPayload().getLeIdentifierKycRegistryDO();
			LeIdentifierKycRegistryDO dbimageLeIdentifierKycRegistryDO = entityManager
					.find(LeIdentifierKycRegistryDO.class, reqLeIdentifierKycRegistryDO.getIdPk());
			if (null == dbimageLeIdentifierKycRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeIdentifierKycRegistryComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLeIdentifierKycRegistryDO);
			BeanUtils.copyProperties(reqLeIdentifierKycRegistryDO, dbimageLeIdentifierKycRegistryDO,
					strIgnoreProperties);
			entityManager.detach(dbimageLeIdentifierKycRegistryDO);
			theLeIdentifierKycRegistryComponentRule.preExecuteLeIdentifierKycRegistryCompMerge(
					reqLeIdentifierKycRegistryDO, dbimageLeIdentifierKycRegistryDO, txnTransferObj);
			LeIdentifierKycRegistryDO mergedLeIdentifierKycRegistryDO = entityManager
					.merge(dbimageLeIdentifierKycRegistryDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setLeIdentifierKycRegistryDO(new LeIdentifierKycRegistryDO(mergedLeIdentifierKycRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeIdentifierKycRegistryComponentRule.postExecuteLeIdentifierKycRegistryCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LeIdentifierKycRegistryComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeIdentifierKycRegistryComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - identificationTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeIdentifierKycRegistryComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeIdentifierKycRegistryComponent.merge failed unexpectedly");
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
	 *             if LeIdentifierKycRegistryDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLeIdentifierKycRegistryComponentRule.prevalidateLeIdentifierKycRegistryCompFindById(txnTransferObj);
			LeIdentifierKycRegistryDO reqLeIdentifierKycRegistryDO = (LeIdentifierKycRegistryDO) txnTransferObj
					.getTxnPayload().getLeIdentifierKycRegistryDO();
			LeIdentifierKycRegistryDO dbimageLeIdentifierKycRegistryDO = entityManager
					.find(LeIdentifierKycRegistryDO.class, reqLeIdentifierKycRegistryDO.getIdPk());
			if (null == dbimageLeIdentifierKycRegistryDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeIdentifierKycRegistryComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setLeIdentifierKycRegistryDO(new LeIdentifierKycRegistryDO(dbimageLeIdentifierKycRegistryDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeIdentifierKycRegistryComponentRule.postExecuteLeIdentifierKycRegistryCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeIdentifierKycRegistryComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LeIdentifierKycRegistryDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeIdentifierKycRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeIdentifierKycRegistryDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeIdentifierKycRegistryDO.legalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO().getIdentificationTypeRefkey()
				|| txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO().getIdentificationTypeRefkey()
						.isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10031",
					"LeIdentifierKycRegistryDO.identificationTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO().getIdentificationNumber()
				|| txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO().getIdentificationNumber().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10032",
					"LeIdentifierKycRegistryDO.identificationNumber is needed in the request");
		}

		LeIdentifierKycRegistryDO theLeIdentifierKycRegistryDO = (LeIdentifierKycRegistryDO) txnTransferObj
				.getTxnPayload().getLeIdentifierKycRegistryDO();
		theLeIdentifierKycRegistryDO.setCreatedTs(new Date());
		theLeIdentifierKycRegistryDO.setUpdatedTs(new Date());
		theLeIdentifierKycRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeIdentifierKycRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LeIdentifierKycRegistryDO
	 * object in the database. populate updatedTimestamp, transaction reference
	 * Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeIdentifierKycRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeIdentifierKycRegistryDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeIdentifierKycRegistryDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LeIdentifierKycRegistryDO.version should not be null");
		}

		LeIdentifierKycRegistryDO theLeIdentifierKycRegistryDO = (LeIdentifierKycRegistryDO) txnTransferObj
				.getTxnPayload().getLeIdentifierKycRegistryDO();
		theLeIdentifierKycRegistryDO.setUpdatedTs(new Date());
		theLeIdentifierKycRegistryDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeIdentifierKycRegistryDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LeIdentifierKycRegistryDO and idpk is
	 * not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeIdentifierKycRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeIdentifierKycRegistryDO is needed in the request");
		}
		LeIdentifierKycRegistryDO theLeIdentifierKycRegistryDO = (LeIdentifierKycRegistryDO) txnTransferObj
				.getTxnPayload().getLeIdentifierKycRegistryDO();
		if (null == theLeIdentifierKycRegistryDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeIdentifierKycRegistryDO.idpk should not be null");
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
	 *             if LeIdentifierKycRegistryDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
			theLeIdentifierKycRegistryComponentRule
					.prevalidateLeIdentifierKycRegistryCompFindByLegalEntityIdPk(txnTransferObj);
			LeIdentifierKycRegistryDO reqLeIdentifierKycRegistryDO = (LeIdentifierKycRegistryDO) txnTransferObj
					.getTxnPayload().getLeIdentifierKycRegistryDO();

			Page<LeIdentifierKycRegistryDO> pageLeIdentifierKycRegistryDO = findByLegalEntityIdPkFromRepository(
					reqLeIdentifierKycRegistryDO.getLegalentityIdpk(), reqLeIdentifierKycRegistryDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageLeIdentifierKycRegistryDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageLeIdentifierKycRegistryDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageLeIdentifierKycRegistryDO.getTotalElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalPages(pageLeIdentifierKycRegistryDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLeIdentifierKycRegistryDO.getSize());

			// process pages if response have results
			if (pageLeIdentifierKycRegistryDO.getTotalPages() != 0) {
				if ((pageLeIdentifierKycRegistryDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for LeIdentifierKycRegistryComponent.findByLegalEntityIdPk, total number of pages is "
									+ pageLeIdentifierKycRegistryDO.getTotalPages()
									+ ". Note- Pages Index starts with 0");
				}

				List<LeIdentifierKycRegistryDO> dbimageLeIdentifierKycRegistryDOList = pageLeIdentifierKycRegistryDO
						.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<LeIdentifierKycRegistryDO> clonedLeIdentifierKycRegistryDOList = new ArrayList<LeIdentifierKycRegistryDO>();
				if (null != dbimageLeIdentifierKycRegistryDOList && dbimageLeIdentifierKycRegistryDOList.size() > 0) {
					Iterator<LeIdentifierKycRegistryDO> itr = dbimageLeIdentifierKycRegistryDOList.iterator();
					while (itr.hasNext()) {
						LeIdentifierKycRegistryDO theLeIdentifierKycRegistryDO = new LeIdentifierKycRegistryDO(
								itr.next());
						clonedLeIdentifierKycRegistryDOList.add(theLeIdentifierKycRegistryDO);
					}
				}

				// populate reference data attributes
				if (null != clonedLeIdentifierKycRegistryDOList && clonedLeIdentifierKycRegistryDOList.size() > 0) {
					Iterator<LeIdentifierKycRegistryDO> itr = clonedLeIdentifierKycRegistryDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload()
						.setLeIdentifierKycRegistryDOList(clonedLeIdentifierKycRegistryDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeIdentifierKycRegistryComponentRule
					.postExecuteLeIdentifierKycRegistryCompFindByLegalEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LeIdentifierKycRegistryComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeIdentifierKycRegistryComponent.findByLegalEntityIdPk failed unexpectedly");
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
		if (null == txnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeIdentifierKycRegistryDO is needed in the request");
		}
		LeIdentifierKycRegistryDO theLeIdentifierKycRegistryDO = (LeIdentifierKycRegistryDO) txnTransferObj
				.getTxnPayload().getLeIdentifierKycRegistryDO();
		if (null == theLeIdentifierKycRegistryDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeIdentifierKycRegistryDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLeIdentifierKycRegistryDO.getInquiryFilter()
				|| theLeIdentifierKycRegistryDO.getInquiryFilter().isEmpty()) {
			theLeIdentifierKycRegistryDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLeIdentifierKycRegistryDO.getInquiryFilter());
		}

	}

	public Page<LeIdentifierKycRegistryDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<LeIdentifierKycRegistryDO> pageLeIdentifierKycRegistryDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLeIdentifierKycRegistryDOlist = this.theLeIdentifierKycRegistryRepository
					.findAllActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLeIdentifierKycRegistryDOlist = this.theLeIdentifierKycRegistryRepository
					.findAllInActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLeIdentifierKycRegistryDOlist = this.theLeIdentifierKycRegistryRepository
					.findAllByLegalentityIdpk(legalEntityIdPk,localPageable);

		}

		return pageLeIdentifierKycRegistryDOlist;

	}

	/*
	 * Sample Code
	 * 
	 * @Autowired protected RefEntityObjectTypeComponent
	 * theRefEntityObjectTypeComponent;
	 */

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			LeIdentifierKycRegistryDO theLeIdentifierKycRegistryDO) {

		// IdentificationTypeRefkey
		if (!(null == theLeIdentifierKycRegistryDO.getIdentificationTypeRefkey()
				|| theLeIdentifierKycRegistryDO.getIdentificationTypeRefkey().isEmpty())) {
			RefIdentificationTypeDO theRefIdentificationTypeDO = referenceTableHelper
					.getRefIdentificationTypeValueByKey(requesterLanguage,
							theLeIdentifierKycRegistryDO.getIdentificationTypeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefIdentificationTypeDO) {
				theLeIdentifierKycRegistryDO.setIdentificationTypeRefValue(theRefIdentificationTypeDO.getValue());
			}
		}

		// SourceSystemRefkey
		if (!(null == theLeIdentifierKycRegistryDO.getSourceSystemRefkey()
				|| theLeIdentifierKycRegistryDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					requesterLanguage, theLeIdentifierKycRegistryDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {
				theLeIdentifierKycRegistryDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
			}
		}

	}

}
