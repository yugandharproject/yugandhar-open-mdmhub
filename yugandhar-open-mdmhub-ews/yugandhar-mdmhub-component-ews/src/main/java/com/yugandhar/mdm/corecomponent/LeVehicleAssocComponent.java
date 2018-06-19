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
import com.yugandhar.mdm.extern.dobj.LeVehicleAssocDO;
import com.yugandhar.mdm.extern.dobj.RefAgreementTypeDO;
import com.yugandhar.mdm.extern.dobj.RefDeactivationReasonDO;
import com.yugandhar.mdm.extern.dobj.RefLeRoletypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LeVehicleAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LeVehicleAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeVehicleAssocComponentRule theLeVehicleAssocComponentRule;

	// get the repository instance
	@Autowired
	private LeVehicleAssocRepository theLeVehicleAssocRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LeVehicleAssocComponent() {
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
	 *             if LeVehicleAssocDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLeVehicleAssocComponentRule.prevalidateLeVehicleAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			LeVehicleAssocDO reqLeVehicleAssocDO = (LeVehicleAssocDO) txnTransferObj.getTxnPayload()
					.getLeVehicleAssocDO();
			if (null == reqLeVehicleAssocDO.getPrimaryKeyDO()
					|| null == reqLeVehicleAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLeVehicleAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLeVehicleAssocDO.setIdPk(reqLeVehicleAssocDO.getPrimaryKeyDO().getIdPk());
				LeVehicleAssocDO dbimageLeVehicleAssocDO = entityManager.find(LeVehicleAssocDO.class,
						reqLeVehicleAssocDO.getIdPk());
				if (null != dbimageLeVehicleAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LeVehicleAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLeVehicleAssocComponentRule.preExecuteLeVehicleAssocCompPersist(reqLeVehicleAssocDO, txnTransferObj);
			entityManager.persist(reqLeVehicleAssocDO);
			entityManager.flush();
			reqLeVehicleAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setLeVehicleAssocDO(new LeVehicleAssocDO(reqLeVehicleAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeVehicleAssocDO());
			theLeVehicleAssocComponentRule.postExecuteLeVehicleAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeVehicleAssocComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - legalentityIdpk -leRoletypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeVehicleAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeVehicleAssocComponent.persist failed unexpectedly");
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
	 *             if LeVehicleAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLeVehicleAssocComponentRule.PrevalidateLeVehicleAssocCompMerge(txnTransferObj);
			LeVehicleAssocDO reqLeVehicleAssocDO = (LeVehicleAssocDO) txnTransferObj.getTxnPayload()
					.getLeVehicleAssocDO();
			LeVehicleAssocDO dbimageLeVehicleAssocDO = entityManager.find(LeVehicleAssocDO.class,
					reqLeVehicleAssocDO.getIdPk());
			if (null == dbimageLeVehicleAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeVehicleAssocComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLeVehicleAssocDO);
			BeanUtils.copyProperties(reqLeVehicleAssocDO, dbimageLeVehicleAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageLeVehicleAssocDO);
			theLeVehicleAssocComponentRule.preExecuteLeVehicleAssocCompMerge(reqLeVehicleAssocDO,
					dbimageLeVehicleAssocDO, txnTransferObj);
			LeVehicleAssocDO mergedLeVehicleAssocDO = entityManager.merge(dbimageLeVehicleAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLeVehicleAssocDO(new LeVehicleAssocDO(mergedLeVehicleAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeVehicleAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeVehicleAssocComponentRule.postExecuteLeVehicleAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LeVehicleAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeVehicleAssocComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - legalentityIdpk -leRoletypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeVehicleAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeVehicleAssocComponent.merge failed unexpectedly");
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
	 *             if LeVehicleAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLeVehicleAssocComponentRule.prevalidateLeVehicleAssocCompFindById(txnTransferObj);
			LeVehicleAssocDO reqLeVehicleAssocDO = (LeVehicleAssocDO) txnTransferObj.getTxnPayload()
					.getLeVehicleAssocDO();
			LeVehicleAssocDO dbimageLeVehicleAssocDO = entityManager.find(LeVehicleAssocDO.class,
					reqLeVehicleAssocDO.getIdPk());
			if (null == dbimageLeVehicleAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeVehicleAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLeVehicleAssocDO(new LeVehicleAssocDO(dbimageLeVehicleAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeVehicleAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeVehicleAssocComponentRule.postExecuteLeVehicleAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeVehicleAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LeVehicleAssocDO object
	 * in the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeVehicleAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeVehicleAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeVehicleAssocDO.legalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getLeRoletypeRefkey()
				|| txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getLeRoletypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10023",
					"LeVehicleAssocDO.leRoletypeRefkey is needed in the request");
		}

		LeVehicleAssocDO theLeVehicleAssocDO = (LeVehicleAssocDO) txnTransferObj.getTxnPayload().getLeVehicleAssocDO();
		theLeVehicleAssocDO.setCreatedTs(new Date());
		theLeVehicleAssocDO.setUpdatedTs(new Date());
		theLeVehicleAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeVehicleAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LeVehicleAssocDO object in
	 * the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeVehicleAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeVehicleAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeVehicleAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LeVehicleAssocDO.version should not be null");
		}

		LeVehicleAssocDO theLeVehicleAssocDO = (LeVehicleAssocDO) txnTransferObj.getTxnPayload().getLeVehicleAssocDO();
		theLeVehicleAssocDO.setUpdatedTs(new Date());
		theLeVehicleAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeVehicleAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LeVehicleAssocDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeVehicleAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeVehicleAssocDO is needed in the request");
		}
		LeVehicleAssocDO theLeVehicleAssocDO = (LeVehicleAssocDO) txnTransferObj.getTxnPayload().getLeVehicleAssocDO();
		if (null == theLeVehicleAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeVehicleAssocDO.idpk should not be null");
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
	 *             if LeVehicleAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
			theLeVehicleAssocComponentRule.prevalidateLeVehicleAssocCompFindByLegalEntityIdPk(txnTransferObj);
			LeVehicleAssocDO reqLeVehicleAssocDO = (LeVehicleAssocDO) txnTransferObj.getTxnPayload()
					.getLeVehicleAssocDO();

			Page<LeVehicleAssocDO> pageLeVehicleAssocDO = findByLegalEntityIdPkFromRepository(
					reqLeVehicleAssocDO.getLegalentityIdpk(), reqLeVehicleAssocDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageLeVehicleAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageLeVehicleAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageLeVehicleAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageLeVehicleAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLeVehicleAssocDO.getSize());

			// process pages if response have results
			if (pageLeVehicleAssocDO.getTotalPages() != 0) {
				if ((pageLeVehicleAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for LeVehicleAssocComponent.findByLegalEntityIdPk, total number of pages is "
									+ pageLeVehicleAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<LeVehicleAssocDO> dbimageLeVehicleAssocDOList = pageLeVehicleAssocDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<LeVehicleAssocDO> clonedLeVehicleAssocDOList = new ArrayList<LeVehicleAssocDO>();
				if (null != dbimageLeVehicleAssocDOList && dbimageLeVehicleAssocDOList.size() > 0) {
					Iterator<LeVehicleAssocDO> itr = dbimageLeVehicleAssocDOList.iterator();
					while (itr.hasNext()) {
						LeVehicleAssocDO theLeVehicleAssocDO = new LeVehicleAssocDO(itr.next());
						clonedLeVehicleAssocDOList.add(theLeVehicleAssocDO);
					}
				}

				// populate reference data attributes
				if (null != clonedLeVehicleAssocDOList && clonedLeVehicleAssocDOList.size() > 0) {
					Iterator<LeVehicleAssocDO> itr = clonedLeVehicleAssocDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setLeVehicleAssocDOList(clonedLeVehicleAssocDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeVehicleAssocComponentRule.postExecuteLeVehicleAssocCompFindByLegalEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LeVehicleAssocComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeVehicleAssocComponent.findByLegalEntityIdPk failed unexpectedly");
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
		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeVehicleAssocDO is needed in the request");
		}
		LeVehicleAssocDO theLeVehicleAssocDO = (LeVehicleAssocDO) txnTransferObj.getTxnPayload().getLeVehicleAssocDO();
		if (null == theLeVehicleAssocDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeVehicleAssocDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLeVehicleAssocDO.getInquiryFilter() || theLeVehicleAssocDO.getInquiryFilter().isEmpty()) {
			theLeVehicleAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLeVehicleAssocDO.getInquiryFilter());
		}

	}

	public Page<LeVehicleAssocDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<LeVehicleAssocDO> pageLeVehicleAssocDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLeVehicleAssocDOlist = this.theLeVehicleAssocRepository.findAllActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLeVehicleAssocDOlist = this.theLeVehicleAssocRepository
					.findAllInActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLeVehicleAssocDOlist = this.theLeVehicleAssocRepository.findAllByLegalentityIdpk(legalEntityIdPk,localPageable);

		}
		return pageLeVehicleAssocDOlist;

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, LeVehicleAssocDO theLeVehicleAssocDO) {

		// LeRoletypeRefkey
		if (!(null == theLeVehicleAssocDO.getLeRoletypeRefkey()
				|| theLeVehicleAssocDO.getLeRoletypeRefkey().isEmpty())) {
			RefLeRoletypeDO theRefLeRoletypeDO = referenceTableHelper.getRefLeRoletypeValueByKey(requesterLanguage,
					theLeVehicleAssocDO.getLeRoletypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRoletypeDO) {
				theLeVehicleAssocDO.setLeRoletypeRefValue(theRefLeRoletypeDO.getValue());
			}
		}

		// DeactivationReasonRefkey
		if (!(null == theLeVehicleAssocDO.getDeactivationReasonRefkey()
				|| theLeVehicleAssocDO.getDeactivationReasonRefkey().isEmpty())) {
			RefDeactivationReasonDO theRefDeactivationReasonDO = referenceTableHelper
					.getRefDeactivationReasonValueByKey(requesterLanguage,
							theLeVehicleAssocDO.getDeactivationReasonRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefDeactivationReasonDO) {
				theLeVehicleAssocDO.setDeactivationReasonRefValue(theRefDeactivationReasonDO.getValue());
			}
		}

		// AgreementTypeRefkey
		if (!(null == theLeVehicleAssocDO.getAgreementTypeRefkey()
				|| theLeVehicleAssocDO.getAgreementTypeRefkey().isEmpty())) {
			RefAgreementTypeDO theRefAgreementTypeDO = referenceTableHelper.getRefAgreementTypeValueByKey(
					requesterLanguage, theLeVehicleAssocDO.getAgreementTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAgreementTypeDO) {
				theLeVehicleAssocDO.setAgreementTypeRefValue(theRefAgreementTypeDO.getValue());
			}
		}
	}

}
