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
import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;
import com.yugandhar.mdm.extern.dobj.RefAgreementTypeDO;
import com.yugandhar.mdm.extern.dobj.RefDeactivationReasonDO;
import com.yugandhar.mdm.extern.dobj.RefLeRoletypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LeAccountAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LeAccountAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeAccountAssocComponentRule theLeAccountAssocComponentRule;

	// get the repository instance
	@Autowired
	private LeAccountAssocRepository theLeAccountAssocRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LeAccountAssocComponent() {
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
	 *             if LeAccountAssocDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLeAccountAssocComponentRule.prevalidateLeAccountAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			LeAccountAssocDO reqLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload()
					.getLeAccountAssocDO();
			if (null == reqLeAccountAssocDO.getPrimaryKeyDO()
					|| null == reqLeAccountAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLeAccountAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLeAccountAssocDO.setIdPk(reqLeAccountAssocDO.getPrimaryKeyDO().getIdPk());
				LeAccountAssocDO dbimageLeAccountAssocDO = entityManager.find(LeAccountAssocDO.class,
						reqLeAccountAssocDO.getIdPk());
				if (null != dbimageLeAccountAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LeAccountAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLeAccountAssocComponentRule.preExecuteLeAccountAssocCompPersist(reqLeAccountAssocDO, txnTransferObj);
			entityManager.persist(reqLeAccountAssocDO);
			entityManager.flush();
			reqLeAccountAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setLeAccountAssocDO(new LeAccountAssocDO(reqLeAccountAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeAccountAssocDO());
			theLeAccountAssocComponentRule.postExecuteLeAccountAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeAccountAssocComponent.persist failed - Unique Key Violated, record with legalentityIdpk - leRoletypeRefkey- accountIdpk combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeAccountAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeAccountAssocComponent.persist failed unexpectedly");
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
	 *             if LeAccountAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLeAccountAssocComponentRule.PrevalidateLeAccountAssocCompMerge(txnTransferObj);
			LeAccountAssocDO reqLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload()
					.getLeAccountAssocDO();
			LeAccountAssocDO dbimageLeAccountAssocDO = entityManager.find(LeAccountAssocDO.class,
					reqLeAccountAssocDO.getIdPk());
			if (null == dbimageLeAccountAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeAccountAssocComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLeAccountAssocDO);
			BeanUtils.copyProperties(reqLeAccountAssocDO, dbimageLeAccountAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageLeAccountAssocDO);
			theLeAccountAssocComponentRule.preExecuteLeAccountAssocCompMerge(reqLeAccountAssocDO,
					dbimageLeAccountAssocDO, txnTransferObj);

			LeAccountAssocDO mergedLeAccountAssocDO = entityManager.merge(dbimageLeAccountAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLeAccountAssocDO(new LeAccountAssocDO(mergedLeAccountAssocDO));

			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeAccountAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeAccountAssocComponentRule.postExecuteLeAccountAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LeAccountAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeAccountAssocComponent.persist failed - Unique Key Violated, record with legalentityIdpk - leRoletypeRefkey- accountIdpk combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeAccountAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeAccountAssocComponent.merge failed unexpectedly");
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
	 *             if LeAccountAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLeAccountAssocComponentRule.prevalidateLeAccountAssocCompFindById(txnTransferObj);
			LeAccountAssocDO reqLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload()
					.getLeAccountAssocDO();
			LeAccountAssocDO dbimageLeAccountAssocDO = entityManager.find(LeAccountAssocDO.class,
					reqLeAccountAssocDO.getIdPk());
			if (null == dbimageLeAccountAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeAccountAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLeAccountAssocDO(new LeAccountAssocDO(dbimageLeAccountAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeAccountAssocDO());

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeAccountAssocComponentRule.postExecuteLeAccountAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeAccountAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LeAccountAssocDO object
	 * in the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeAccountAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAccountAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeAccountAssocDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeAccountAssocDO.legalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getLeRoletypeRefkey()
				|| txnTransferObj.getTxnPayload().getLeAccountAssocDO().getLeRoletypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10013",
					"LeAccountAssocDO.roletypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getAccountIdpk()
				|| txnTransferObj.getTxnPayload().getLeAccountAssocDO().getAccountIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10014",
					"LeAccountAssocDO.accountIdpk is needed in the request");
		}

		LeAccountAssocDO theLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload().getLeAccountAssocDO();
		theLeAccountAssocDO.setCreatedTs(new Date());
		theLeAccountAssocDO.setUpdatedTs(new Date());
		theLeAccountAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeAccountAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LeAccountAssocDO object in
	 * the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeAccountAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAccountAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLeAccountAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeAccountAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LeAccountAssocDO.version should not be null");
		}

		LeAccountAssocDO theLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload().getLeAccountAssocDO();
		theLeAccountAssocDO.setUpdatedTs(new Date());
		theLeAccountAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeAccountAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LeAccountAssocDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeAccountAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAccountAssocDO is needed in the request");
		}
		LeAccountAssocDO theLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload().getLeAccountAssocDO();
		if (null == theLeAccountAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeAccountAssocDO.idpk should not be null");
		}

	}

	/**
	 * This method search the database record based on LegalEntityIdPk.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeAccountAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
			theLeAccountAssocComponentRule.prevalidateLeAccountAssocCompFindByLegalEntityIdPk(txnTransferObj);
			LeAccountAssocDO reqLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload()
					.getLeAccountAssocDO();

			Page<LeAccountAssocDO> pageLeAccountAssocDO = findByLegalEntityIdPkFromRepository(
					reqLeAccountAssocDO.getLegalentityIdpk(), reqLeAccountAssocDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageLeAccountAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageLeAccountAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageLeAccountAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageLeAccountAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLeAccountAssocDO.getSize());

			// process pages if response have results
			if (pageLeAccountAssocDO.getTotalPages() != 0) {
				if ((pageLeAccountAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for LeAccountAssocComponent.findByLegalEntityIdPk, total number of pages is "
									+ pageLeAccountAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<LeAccountAssocDO> dbimageLeAccountAssocDOList = pageLeAccountAssocDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<LeAccountAssocDO> clonedLeAccountAssocDOList = new ArrayList<LeAccountAssocDO>();
				if (null != dbimageLeAccountAssocDOList && dbimageLeAccountAssocDOList.size() > 0) {
					Iterator<LeAccountAssocDO> itr = dbimageLeAccountAssocDOList.iterator();
					while (itr.hasNext()) {
						LeAccountAssocDO theLeAccountAssocDO = new LeAccountAssocDO(itr.next());
						clonedLeAccountAssocDOList.add(theLeAccountAssocDO);
					}
				}

				// populate reference data attributes
				if (null != clonedLeAccountAssocDOList && clonedLeAccountAssocDOList.size() > 0) {
					Iterator<LeAccountAssocDO> itr = clonedLeAccountAssocDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setLeAccountAssocDOList(clonedLeAccountAssocDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeAccountAssocComponentRule.postExecuteLeAccountAssocCompFindByLegalEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LeAccountAssocComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeAccountAssocComponent.findByLegalEntityIdPk failed unexpectedly");
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
		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAccountAssocDO is needed in the request");
		}
		LeAccountAssocDO theLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload().getLeAccountAssocDO();
		if (null == theLeAccountAssocDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeAccountAssocDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLeAccountAssocDO.getInquiryFilter() || theLeAccountAssocDO.getInquiryFilter().isEmpty()) {
			theLeAccountAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLeAccountAssocDO.getInquiryFilter());
		}

	}

	public Page<LeAccountAssocDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<LeAccountAssocDO> pageLeAccountAssocDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLeAccountAssocDO = this.theLeAccountAssocRepository.findAllActiveByLegalentityIdpk(legalEntityIdPk,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLeAccountAssocDO = this.theLeAccountAssocRepository.findAllInActiveByLegalentityIdpk(legalEntityIdPk,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLeAccountAssocDO = this.theLeAccountAssocRepository.findAllByLegalentityIdpk(legalEntityIdPk,
					localPageable);

		}

		return pageLeAccountAssocDO;

	}

	/**
	 * This method search the database record based on AccountIdpk.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeAccountAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByAccountIdpk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByAccountIdpk(txnTransferObj);
			theLeAccountAssocComponentRule.prevalidateLeAccountAssocCompFindByAccountIdpk(txnTransferObj);
			LeAccountAssocDO reqLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload()
					.getLeAccountAssocDO();

			Page<LeAccountAssocDO> pageLeAccountAssocDO = findByAccountIdpkFromRepository(
					reqLeAccountAssocDO.getAccountIdpk(), reqLeAccountAssocDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageLeAccountAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageLeAccountAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageLeAccountAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageLeAccountAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLeAccountAssocDO.getSize());

			// process pages if response have results
			if (pageLeAccountAssocDO.getTotalPages() != 0) {
				if ((pageLeAccountAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for LeAccountAssocComponent.findByAccountIdpk, total number of pages is "
									+ pageLeAccountAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<LeAccountAssocDO> dbimageLeAccountAssocDOlist = pageLeAccountAssocDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity
				// manager level 1 (L1) cached object
				List<LeAccountAssocDO> clonedLeAccountAssocDOList = new ArrayList<LeAccountAssocDO>();
				if (null != dbimageLeAccountAssocDOlist && dbimageLeAccountAssocDOlist.size() > 0) {
					Iterator<LeAccountAssocDO> itr = dbimageLeAccountAssocDOlist.iterator();
					while (itr.hasNext()) {
						LeAccountAssocDO theAccountAssocDO = new LeAccountAssocDO(itr.next());
						clonedLeAccountAssocDOList.add(theAccountAssocDO);
					}
				}

				if (null != clonedLeAccountAssocDOList && clonedLeAccountAssocDOList.size() > 0) {
					Iterator<LeAccountAssocDO> itr = clonedLeAccountAssocDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setLeAccountAssocDOList(clonedLeAccountAssocDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeAccountAssocComponentRule.postExecuteLeAccountAssocCompFindByAccountIdpk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LeAccountAssocComponent.findByAccountIdpk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeAccountAssocComponent.findByAccountIdpk failed unexpectedly");
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

	private void performCommonvalidationBeforeFindByAccountIdpk(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAccountAssocDO is needed in the request");
		}
		LeAccountAssocDO theLeAccountAssocDO = (LeAccountAssocDO) txnTransferObj.getTxnPayload().getLeAccountAssocDO();
		if (null == theLeAccountAssocDO.getAccountIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10016",
					"LeAccountAssocDO.accountIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLeAccountAssocDO.getInquiryFilter() || theLeAccountAssocDO.getInquiryFilter().isEmpty()) {
			theLeAccountAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLeAccountAssocDO.getInquiryFilter());
		}

	}

	public Page<LeAccountAssocDO> findByAccountIdpkFromRepository(String accountIdpk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "accountIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<LeAccountAssocDO> pageLeAccountAssocDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLeAccountAssocDO = this.theLeAccountAssocRepository.findAllActiveByAccountIdpk(accountIdpk,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLeAccountAssocDO = this.theLeAccountAssocRepository.findAllInActiveByAccountIdpk(accountIdpk,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLeAccountAssocDO = this.theLeAccountAssocRepository.findAllByAccountIdpk(accountIdpk, localPageable);

		}

		return pageLeAccountAssocDO;

	}

	/*
	 * Sample Code
	 * 
	 * @Autowired protected RefEntityObjectTypeComponent
	 * theRefEntityObjectTypeComponent;
	 */

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, LeAccountAssocDO theLeAccountAssocDO) {

		// LeRoletypeRefkey
		if (!(null == theLeAccountAssocDO.getLeRoletypeRefkey()
				|| theLeAccountAssocDO.getLeRoletypeRefkey().isEmpty())) {
			RefLeRoletypeDO theRefLeRoletypeDO = referenceTableHelper.getRefLeRoletypeValueByKey(requesterLanguage,
					theLeAccountAssocDO.getLeRoletypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRoletypeDO) {
				theLeAccountAssocDO.setLeRoletypeRefValue(theRefLeRoletypeDO.getValue());
			}
		}

		// DeactivationReasonRefkey
		if (!(null == theLeAccountAssocDO.getDeactivationReasonRefkey()
				|| theLeAccountAssocDO.getDeactivationReasonRefkey().isEmpty())) {
			RefDeactivationReasonDO theRefDeactivationReasonDO = referenceTableHelper
					.getRefDeactivationReasonValueByKey(requesterLanguage,
							theLeAccountAssocDO.getDeactivationReasonRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefDeactivationReasonDO) {
				theLeAccountAssocDO.setDeactivationReasonRefValue(theRefDeactivationReasonDO.getValue());
			}
		}

		// AgreementTypeRefkey
		if (!(null == theLeAccountAssocDO.getAgreementTypeRefkey()
				|| theLeAccountAssocDO.getAgreementTypeRefkey().isEmpty())) {
			RefAgreementTypeDO theRefAgreementTypeDO = referenceTableHelper.getRefAgreementTypeValueByKey(
					requesterLanguage, theLeAccountAssocDO.getAgreementTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAgreementTypeDO) {
				theLeAccountAssocDO.setAgreementTypeRefValue(theRefAgreementTypeDO.getValue());
			}
		}

	}

}
