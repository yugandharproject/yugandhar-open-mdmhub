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
import com.yugandhar.mdm.extern.dobj.LeAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.RefAddressSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefAddressTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LeAddressAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LeAddressAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeAddressAssocComponentRule theLeAddressAssocComponentRule;

	// get the repository instance
	@Autowired
	private LeAddressAssocRepository theLeAddressAssocRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LeAddressAssocComponent() {
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
	 *             if LeAddressAssocDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLeAddressAssocComponentRule.prevalidateLeAddressAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			LeAddressAssocDO reqLeAddressAssocDO = (LeAddressAssocDO) txnTransferObj.getTxnPayload()
					.getLeAddressAssocDO();
			if (null == reqLeAddressAssocDO.getPrimaryKeyDO()
					|| null == reqLeAddressAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLeAddressAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLeAddressAssocDO.setIdPk(reqLeAddressAssocDO.getPrimaryKeyDO().getIdPk());
				LeAddressAssocDO dbimageLeAddressAssocDO = entityManager.find(LeAddressAssocDO.class,
						reqLeAddressAssocDO.getIdPk());
				if (null != dbimageLeAddressAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LeAddressAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLeAddressAssocComponentRule.preExecuteLeAddressAssocCompPersist(reqLeAddressAssocDO, txnTransferObj);
			entityManager.persist(reqLeAddressAssocDO);
			entityManager.flush();
			reqLeAddressAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setLeAddressAssocDO(new LeAddressAssocDO(reqLeAddressAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
			respTxnTransferObj.getTxnPayload().getLeAddressAssocDO());
			theLeAddressAssocComponentRule.postExecuteLeAddressAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeAddressAssocComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - AddressTypeRefkey - AddressSubtypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeAddressAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeAddressAssocComponent.persist failed unexpectedly");
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
	 *             if LeAddressAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLeAddressAssocComponentRule.PrevalidateLeAddressAssocCompMerge(txnTransferObj);
			LeAddressAssocDO reqLeAddressAssocDO = (LeAddressAssocDO) txnTransferObj.getTxnPayload()
					.getLeAddressAssocDO();
			LeAddressAssocDO dbimageLeAddressAssocDO = entityManager.find(LeAddressAssocDO.class,
					reqLeAddressAssocDO.getIdPk());
			if (null == dbimageLeAddressAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeAddressAssocComponent.merge failed with Validation Exception"); // Record
				// Record not found for given Idpk // not

			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLeAddressAssocDO);
			BeanUtils.copyProperties(reqLeAddressAssocDO, dbimageLeAddressAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageLeAddressAssocDO);
			theLeAddressAssocComponentRule.preExecuteLeAddressAssocCompMerge(reqLeAddressAssocDO,
					dbimageLeAddressAssocDO, txnTransferObj);
			LeAddressAssocDO mergedLeAddressAssocDO = entityManager.merge(dbimageLeAddressAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLeAddressAssocDO(new LeAddressAssocDO(mergedLeAddressAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeAddressAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeAddressAssocComponentRule.postExecuteLeAddressAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LeAddressAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LeAddressAssocComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - AddressTypeRefkey - AddressSubtypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LeAddressAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeAddressAssocComponent.merge failed unexpectedly");
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
	 *             if LeAddressAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLeAddressAssocComponentRule.prevalidateLeAddressAssocCompFindById(txnTransferObj);
			LeAddressAssocDO reqLeAddressAssocDO = (LeAddressAssocDO) txnTransferObj.getTxnPayload()
					.getLeAddressAssocDO();
			LeAddressAssocDO dbimageLeAddressAssocDO = entityManager.find(LeAddressAssocDO.class,
					reqLeAddressAssocDO.getIdPk());
			if (null == dbimageLeAddressAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LeAddressAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLeAddressAssocDO(new LeAddressAssocDO(dbimageLeAddressAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLeAddressAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLeAddressAssocComponentRule.postExecuteLeAddressAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeAddressAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LeAddressAssocDO object
	 * in the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeAddressAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAddressAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeAddressAssocDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeAddressAssocDO.legalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressIdpk()
				|| txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10017",
					"LeAddressAssocDO.addressIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressTypeRefkey()
				|| txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10008",
					"LeAddressAssocDO.addressTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressSubtypeRefkey()
				|| txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressSubtypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10050",
					"LeAddressAssocDO.addressSubtypeRefkey is needed in the request");
		}

		LeAddressAssocDO theLeAddressAssocDO = (LeAddressAssocDO) txnTransferObj.getTxnPayload().getLeAddressAssocDO();
		theLeAddressAssocDO.setCreatedTs(new Date());
		theLeAddressAssocDO.setUpdatedTs(new Date());
		theLeAddressAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeAddressAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LeAddressAssocDO object in
	 * the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeAddressAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAddressAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLeAddressAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeAddressAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LeAddressAssocDO.version should not be null");
		}

		LeAddressAssocDO theLeAddressAssocDO = (LeAddressAssocDO) txnTransferObj.getTxnPayload().getLeAddressAssocDO();
		theLeAddressAssocDO.setUpdatedTs(new Date());
		theLeAddressAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLeAddressAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LeAddressAssocDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LeAddressAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAddressAssocDO is needed in the request");
		}
		LeAddressAssocDO theLeAddressAssocDO = (LeAddressAssocDO) txnTransferObj.getTxnPayload().getLeAddressAssocDO();
		if (null == theLeAddressAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LeAddressAssocDO.idpk should not be null");
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
	 *             if LeAddressAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

			try {
				performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
				theLeAddressAssocComponentRule.prevalidateLeAddressAssocCompFindByLegalEntityIdPk(txnTransferObj);
				LeAddressAssocDO reqLeAddressAssocDO = (LeAddressAssocDO) txnTransferObj.getTxnPayload()
						.getLeAddressAssocDO();

				Page<LeAddressAssocDO> pageLeAddressAssocDO = findByLegalEntityIdPkFromRepository(
						reqLeAddressAssocDO.getLegalentityIdpk(), reqLeAddressAssocDO.getInquiryFilter(),
						txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
						txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

				// Copy pagination properties in response
				respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageLeAddressAssocDO.getNumber());
				respTxnTransferObj.getTxnPayload()
						.setPaginationInfoElementsOnCurrentSlice(pageLeAddressAssocDO.getNumberOfElements());
				respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageLeAddressAssocDO.getTotalElements());
				respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageLeAddressAssocDO.getTotalPages());
				respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLeAddressAssocDO.getSize());

				// process pages if response have results
				if (pageLeAddressAssocDO.getTotalPages() != 0) {
					if ((pageLeAddressAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
							.getPaginationIndexOfCurrentSlice()) {
						throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
								"Invalid page number in the request for LeAddressAssocComponent.findByLegalEntityIdPk, total number of pages is "
										+ pageLeAddressAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
					}

					List<LeAddressAssocDO> dbimageLeAddressAssocDOList = pageLeAddressAssocDO.getContent();

					// clone the object before sending response.
					// This is important to prevent editing/ future referencing of
					// entity manager level 1 (L1) cached object
					List<LeAddressAssocDO> clonedLeAddressAssocDOList = new ArrayList<LeAddressAssocDO>();
					if (null != dbimageLeAddressAssocDOList && dbimageLeAddressAssocDOList.size() > 0) {
						Iterator<LeAddressAssocDO> itr = dbimageLeAddressAssocDOList.iterator();
						while (itr.hasNext()) {
							LeAddressAssocDO theLeAddressAssocDO = new LeAddressAssocDO(itr.next());
							clonedLeAddressAssocDOList.add(theLeAddressAssocDO);
						}
					}

					// populate reference data attributes
					if (null != clonedLeAddressAssocDOList && clonedLeAddressAssocDOList.size() > 0) {
						Iterator<LeAddressAssocDO> itr = clonedLeAddressAssocDOList.iterator();
						while (itr.hasNext()) {
							populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
									itr.next());
						}
					}

					respTxnTransferObj.getTxnPayload().setLeAddressAssocDOList(clonedLeAddressAssocDOList);
				}
				respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
				theLeAddressAssocComponentRule.postExecuteLeAddressAssocCompFindByLegalEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LeAddressAssocComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LeAddressAssocComponent.findByLegalEntityIdPk failed unexpectedly");
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
		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAddressAssocDO is needed in the request");
		}
		LeAddressAssocDO theLeAddressAssocDO = (LeAddressAssocDO) txnTransferObj.getTxnPayload().getLeAddressAssocDO();
		if (null == theLeAddressAssocDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeAddressAssocDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLeAddressAssocDO.getInquiryFilter() || theLeAddressAssocDO.getInquiryFilter().isEmpty()) {
			theLeAddressAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLeAddressAssocDO.getInquiryFilter());
		}

	}

	public Page<LeAddressAssocDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);
		
		Page<LeAddressAssocDO> pageLeAddressAssocDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLeAddressAssocDOlist = this.theLeAddressAssocRepository.findAllActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLeAddressAssocDOlist = this.theLeAddressAssocRepository
					.findAllInActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLeAddressAssocDOlist = this.theLeAddressAssocRepository.findAllByLegalentityIdpk(legalEntityIdPk,localPageable);

		}
		return pageLeAddressAssocDOlist;

	}

	/*
	 * Sample Code
	 * 
	 * @Autowired protected RefEntityObjectTypeComponent
	 * theRefEntityObjectTypeComponent;
	 */

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, LeAddressAssocDO theLeAddressAssocDO) {

		// AddressTypeRefkey
		if (!(null == theLeAddressAssocDO.getAddressTypeRefkey()
				|| theLeAddressAssocDO.getAddressTypeRefkey().isEmpty())) {
			RefAddressTypeDO theRefAddressTypeDO = referenceTableHelper.getRefAddressTypeValueByKey(requesterLanguage,
					theLeAddressAssocDO.getAddressTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressTypeDO) {
				theLeAddressAssocDO.setAddressTypeRefValue(theRefAddressTypeDO.getValue());
			}
		}

		// AddressSubtypeRefkey
		if (!(null == theLeAddressAssocDO.getAddressSubtypeRefkey()
				|| theLeAddressAssocDO.getAddressSubtypeRefkey().isEmpty())) {
			RefAddressSubtypeDO theRefAddressSubtypeDO = referenceTableHelper.getRefAddressSubtypeValueByKey(
					requesterLanguage, theLeAddressAssocDO.getAddressSubtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressSubtypeDO) {
				theLeAddressAssocDO.setAddressSubtypeRefValue(theRefAddressSubtypeDO.getValue());
			}
		}

	}

}
