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
import com.yugandhar.mdm.extern.dobj.LePhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LePhoneAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LePhoneAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LePhoneAssocComponentRule theLePhoneAssocComponentRule;

	// get the repository instance
	@Autowired
	private LePhoneAssocRepository theLePhoneAssocRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LePhoneAssocComponent() {
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
	 *             if LePhoneAssocDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLePhoneAssocComponentRule.prevalidateLePhoneAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			LePhoneAssocDO reqLePhoneAssocDO = (LePhoneAssocDO) txnTransferObj.getTxnPayload().getLePhoneAssocDO();
			if (null == reqLePhoneAssocDO.getPrimaryKeyDO() || null == reqLePhoneAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLePhoneAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLePhoneAssocDO.setIdPk(reqLePhoneAssocDO.getPrimaryKeyDO().getIdPk());
				LePhoneAssocDO dbimageLePhoneAssocDO = entityManager.find(LePhoneAssocDO.class,
						reqLePhoneAssocDO.getIdPk());
				if (null != dbimageLePhoneAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LePhoneAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLePhoneAssocComponentRule.preExecuteLePhoneAssocCompPersist(reqLePhoneAssocDO, txnTransferObj);
			entityManager.persist(reqLePhoneAssocDO);
			entityManager.flush();
			reqLePhoneAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setLePhoneAssocDO(new LePhoneAssocDO(reqLePhoneAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePhoneAssocDO());
			theLePhoneAssocComponentRule.postExecuteLePhoneAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LePhoneAssocComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - phoneTypeRefkey, phoneSubTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LePhoneAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePhoneAssocComponent.persist failed unexpectedly");
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
	 *             if LePhoneAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLePhoneAssocComponentRule.PrevalidateLePhoneAssocCompMerge(txnTransferObj);
			LePhoneAssocDO reqLePhoneAssocDO = (LePhoneAssocDO) txnTransferObj.getTxnPayload().getLePhoneAssocDO();
			LePhoneAssocDO dbimageLePhoneAssocDO = entityManager.find(LePhoneAssocDO.class,
					reqLePhoneAssocDO.getIdPk());
			if (null == dbimageLePhoneAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LePhoneAssocComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLePhoneAssocDO);
			BeanUtils.copyProperties(reqLePhoneAssocDO, dbimageLePhoneAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageLePhoneAssocDO);
			theLePhoneAssocComponentRule.preExecuteLePhoneAssocCompMerge(reqLePhoneAssocDO, dbimageLePhoneAssocDO,
					txnTransferObj);
			LePhoneAssocDO mergedLePhoneAssocDO = entityManager.merge(dbimageLePhoneAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLePhoneAssocDO(new LePhoneAssocDO(mergedLePhoneAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePhoneAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePhoneAssocComponentRule.postExecuteLePhoneAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LePhoneAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LePhoneAssocComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - phoneTypeRefkey, phoneSubTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LePhoneAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePhoneAssocComponent.merge failed unexpectedly");
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
	 *             if LePhoneAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLePhoneAssocComponentRule.prevalidateLePhoneAssocCompFindById(txnTransferObj);
			LePhoneAssocDO reqLePhoneAssocDO = (LePhoneAssocDO) txnTransferObj.getTxnPayload().getLePhoneAssocDO();
			LePhoneAssocDO dbimageLePhoneAssocDO = entityManager.find(LePhoneAssocDO.class,
					reqLePhoneAssocDO.getIdPk());
			if (null == dbimageLePhoneAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LePhoneAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLePhoneAssocDO(new LePhoneAssocDO(dbimageLePhoneAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePhoneAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePhoneAssocComponentRule.postExecuteLePhoneAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePhoneAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LePhoneAssocDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePhoneAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePhoneAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLePhoneAssocDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LePhoneAssocDO.LegalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO().getPhoneTypeRefkey()
				|| txnTransferObj.getTxnPayload().getLePhoneAssocDO().getPhoneTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10010",
					"LePhoneAssocDO.phoneTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO().getPhoneNumber()
				|| txnTransferObj.getTxnPayload().getLePhoneAssocDO().getPhoneNumber().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10011",
					"LePhoneAssocDO.phoneNumber is needed in the request");
		}

		LePhoneAssocDO theLePhoneAssocDO = (LePhoneAssocDO) txnTransferObj.getTxnPayload().getLePhoneAssocDO();
		theLePhoneAssocDO.setCreatedTs(new Date());
		theLePhoneAssocDO.setUpdatedTs(new Date());
		theLePhoneAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLePhoneAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LePhoneAssocDO object in
	 * the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePhoneAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePhoneAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLePhoneAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LePhoneAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LePhoneAssocDO.version should not be null");
		}

		LePhoneAssocDO theLePhoneAssocDO = (LePhoneAssocDO) txnTransferObj.getTxnPayload().getLePhoneAssocDO();
		theLePhoneAssocDO.setUpdatedTs(new Date());
		theLePhoneAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLePhoneAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LePhoneAssocDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePhoneAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePhoneAssocDO is needed in the request");
		}
		LePhoneAssocDO theLePhoneAssocDO = (LePhoneAssocDO) txnTransferObj.getTxnPayload().getLePhoneAssocDO();
		if (null == theLePhoneAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LePhoneAssocDO.idpk should not be null");
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
	 *             if LePhoneAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
			theLePhoneAssocComponentRule.prevalidateLePhoneAssocCompFindByLegalEntityIdPk(txnTransferObj);
			LePhoneAssocDO reqLePhoneAssocDO = (LePhoneAssocDO) txnTransferObj.getTxnPayload().getLePhoneAssocDO();

			Page<LePhoneAssocDO> pageLePhoneAssocDO = findByLegalEntityIdPkFromRepository(
					reqLePhoneAssocDO.getLegalentityIdpk(), reqLePhoneAssocDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageLePhoneAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageLePhoneAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageLePhoneAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageLePhoneAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLePhoneAssocDO.getSize());

			// process pages if response have results
			if (pageLePhoneAssocDO.getTotalPages() != 0) {
				if ((pageLePhoneAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for LePhoneAssocComponent.findByLegalEntityIdPk, total number of pages is "
									+ pageLePhoneAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<LePhoneAssocDO> dbimageLePhoneAssocDOList = pageLePhoneAssocDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<LePhoneAssocDO> clonedLePhoneAssocDOList = new ArrayList<LePhoneAssocDO>();
				if (null != dbimageLePhoneAssocDOList && dbimageLePhoneAssocDOList.size() > 0) {
					Iterator<LePhoneAssocDO> itr = dbimageLePhoneAssocDOList.iterator();
					while (itr.hasNext()) {
						LePhoneAssocDO theLePhoneAssocDO = new LePhoneAssocDO(itr.next());
						clonedLePhoneAssocDOList.add(theLePhoneAssocDO);
					}
				}

				// populate reference data attributes
				if (null != clonedLePhoneAssocDOList && clonedLePhoneAssocDOList.size() > 0) {
					Iterator<LePhoneAssocDO> itr = clonedLePhoneAssocDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setLePhoneAssocDOList(clonedLePhoneAssocDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePhoneAssocComponentRule.postExecuteLePhoneAssocCompFindByLegalEntityIdPk(respTxnTransferObj);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LePhoneAssocComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePhoneAssocComponent.findByLegalEntityIdPk failed unexpectedly");
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
		if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePhoneAssocDO is needed in the request");
		}
		LePhoneAssocDO theLePhoneAssocDO = (LePhoneAssocDO) txnTransferObj.getTxnPayload().getLePhoneAssocDO();
		if (null == theLePhoneAssocDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LePhoneAssocDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLePhoneAssocDO.getInquiryFilter() || theLePhoneAssocDO.getInquiryFilter().isEmpty()) {
			theLePhoneAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLePhoneAssocDO.getInquiryFilter());
		}

	}

	public Page<LePhoneAssocDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<LePhoneAssocDO> pageLePhoneAssocDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLePhoneAssocDOlist = this.theLePhoneAssocRepository.findAllActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLePhoneAssocDOlist = this.theLePhoneAssocRepository.findAllInActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLePhoneAssocDOlist = this.theLePhoneAssocRepository.findAllByLegalentityIdpk(legalEntityIdPk,localPageable);

		}
		return pageLePhoneAssocDOlist;
	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, LePhoneAssocDO theLePhoneAssocDO) {

		// PhoneTypeRefkey
		if (!(null == theLePhoneAssocDO.getPhoneTypeRefkey() || theLePhoneAssocDO.getPhoneTypeRefkey().isEmpty())) {
			RefPhoneTypeDO theRefPhoneTypeDO = referenceTableHelper.getRefPhoneTypeValueByKey(requesterLanguage,
					theLePhoneAssocDO.getPhoneTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneTypeDO) {
				theLePhoneAssocDO.setPhoneTypeRefValue(theRefPhoneTypeDO.getValue());
			}
		}

		// PhoneSubtypeRefkey
		if (!(null == theLePhoneAssocDO.getPhoneSubtypeRefkey()
				|| theLePhoneAssocDO.getPhoneSubtypeRefkey().isEmpty())) {
			RefPhoneSubtypeDO theRefPhoneSubtypeDO = referenceTableHelper.getRefPhoneSubtypeValueByKey(
					requesterLanguage, theLePhoneAssocDO.getPhoneSubtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneSubtypeDO) {
				theLePhoneAssocDO.setPhoneSubtypeRefValue(theRefPhoneSubtypeDO.getValue());
			}
		}

	}

}
