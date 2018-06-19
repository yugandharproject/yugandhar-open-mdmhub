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
import com.yugandhar.mdm.extern.dobj.LePropertyAssocDO;
import com.yugandhar.mdm.extern.dobj.RefPropertyLeReltypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LePropertyAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LePropertyAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LePropertyAssocComponentRule theLePropertyAssocComponentRule;

	// get the repository instance
	@Autowired
	private LePropertyAssocRepository theLePropertyAssocRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LePropertyAssocComponent() {
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
	 *             if LePropertyAssocDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLePropertyAssocComponentRule.prevalidateLePropertyAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			LePropertyAssocDO reqLePropertyAssocDO = (LePropertyAssocDO) txnTransferObj.getTxnPayload()
					.getLePropertyAssocDO();
			if (null == reqLePropertyAssocDO.getPrimaryKeyDO()
					|| null == reqLePropertyAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLePropertyAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLePropertyAssocDO.setIdPk(reqLePropertyAssocDO.getPrimaryKeyDO().getIdPk());
				LePropertyAssocDO dbimageLePropertyAssocDO = entityManager.find(LePropertyAssocDO.class,
						reqLePropertyAssocDO.getIdPk());
				if (null != dbimageLePropertyAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LePropertyAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLePropertyAssocComponentRule.preExecuteLePropertyAssocCompPersist(reqLePropertyAssocDO, txnTransferObj);
			entityManager.persist(reqLePropertyAssocDO);
			entityManager.flush();
			reqLePropertyAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setLePropertyAssocDO(new LePropertyAssocDO(reqLePropertyAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePropertyAssocDO());
			theLePropertyAssocComponentRule.postExecuteLePropertyAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LePropertyAssocComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - propertyIdpk -propertyLeReltypeRefkey  combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LePropertyAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePropertyAssocComponent.persist failed unexpectedly");
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
	 *             if LePropertyAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLePropertyAssocComponentRule.PrevalidateLePropertyAssocCompMerge(txnTransferObj);
			LePropertyAssocDO reqLePropertyAssocDO = (LePropertyAssocDO) txnTransferObj.getTxnPayload()
					.getLePropertyAssocDO();
			LePropertyAssocDO dbimageLePropertyAssocDO = entityManager.find(LePropertyAssocDO.class,
					reqLePropertyAssocDO.getIdPk());
			if (null == dbimageLePropertyAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LePropertyAssocComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLePropertyAssocDO);
			BeanUtils.copyProperties(reqLePropertyAssocDO, dbimageLePropertyAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageLePropertyAssocDO);
			theLePropertyAssocComponentRule.preExecuteLePropertyAssocCompMerge(reqLePropertyAssocDO,
					dbimageLePropertyAssocDO, txnTransferObj);
			LePropertyAssocDO mergedLePropertyAssocDO = entityManager.merge(dbimageLePropertyAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLePropertyAssocDO(new LePropertyAssocDO(mergedLePropertyAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePropertyAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePropertyAssocComponentRule.postExecuteLePropertyAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LePropertyAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LePropertyAssocComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - propertyIdpk -propertyLeReltypeRefkey  combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LePropertyAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePropertyAssocComponent.merge failed unexpectedly");
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
	 *             if LePropertyAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLePropertyAssocComponentRule.prevalidateLePropertyAssocCompFindById(txnTransferObj);
			LePropertyAssocDO reqLePropertyAssocDO = (LePropertyAssocDO) txnTransferObj.getTxnPayload()
					.getLePropertyAssocDO();
			LePropertyAssocDO dbimageLePropertyAssocDO = entityManager.find(LePropertyAssocDO.class,
					reqLePropertyAssocDO.getIdPk());
			if (null == dbimageLePropertyAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LePropertyAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLePropertyAssocDO(new LePropertyAssocDO(dbimageLePropertyAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePropertyAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePropertyAssocComponentRule.postExecuteLePropertyAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePropertyAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LePropertyAssocDO object
	 * in the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePropertyAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePropertyAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLePropertyAssocDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LePropertyAssocDO.legalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyIdpk()
				|| txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10025",
					"LePropertyAssocDO.propertyIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyLeReltypeRefkey()
				|| txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyLeReltypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10027",
					"LePropertyAssocDO.propertyLeReltypeRefkey is needed in the request");
		}

		LePropertyAssocDO theLePropertyAssocDO = (LePropertyAssocDO) txnTransferObj.getTxnPayload()
				.getLePropertyAssocDO();
		theLePropertyAssocDO.setCreatedTs(new Date());
		theLePropertyAssocDO.setUpdatedTs(new Date());
		theLePropertyAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLePropertyAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LePropertyAssocDO object in
	 * the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePropertyAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePropertyAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLePropertyAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LePropertyAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LePropertyAssocDO.version should not be null");
		}

		LePropertyAssocDO theLePropertyAssocDO = (LePropertyAssocDO) txnTransferObj.getTxnPayload()
				.getLePropertyAssocDO();
		theLePropertyAssocDO.setUpdatedTs(new Date());
		theLePropertyAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLePropertyAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LePropertyAssocDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePropertyAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePropertyAssocDO is needed in the request");
		}
		LePropertyAssocDO theLePropertyAssocDO = (LePropertyAssocDO) txnTransferObj.getTxnPayload()
				.getLePropertyAssocDO();
		if (null == theLePropertyAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LePropertyAssocDO.idpk should not be null");
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
	 *             if LePropertyAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
			theLePropertyAssocComponentRule.prevalidateLePropertyAssocCompFindByLegalEntityIdPk(txnTransferObj);
			LePropertyAssocDO reqLePropertyAssocDO = (LePropertyAssocDO) txnTransferObj.getTxnPayload()
					.getLePropertyAssocDO();

			Page<LePropertyAssocDO> pageLePropertyAssocDO = findByLegalEntityIdPkFromRepository(
					reqLePropertyAssocDO.getLegalentityIdpk(), reqLePropertyAssocDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageLePropertyAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageLePropertyAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageLePropertyAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageLePropertyAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLePropertyAssocDO.getSize());

			// process pages if response have results
			if (pageLePropertyAssocDO.getTotalPages() != 0) {
				if ((pageLePropertyAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for LePropertyAssocComponent.findByLegalEntityIdPk, total number of pages is "
									+ pageLePropertyAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<LePropertyAssocDO> dbimageLePropertyAssocDOList = pageLePropertyAssocDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<LePropertyAssocDO> clonedLePropertyAssocDOList = new ArrayList<LePropertyAssocDO>();
				if (null != dbimageLePropertyAssocDOList && dbimageLePropertyAssocDOList.size() > 0) {
					Iterator<LePropertyAssocDO> itr = dbimageLePropertyAssocDOList.iterator();
					while (itr.hasNext()) {
						LePropertyAssocDO theLePropertyAssocDO = new LePropertyAssocDO(itr.next());
						clonedLePropertyAssocDOList.add(theLePropertyAssocDO);
					}
				}

				// populate reference data attributes
				if (null != clonedLePropertyAssocDOList && clonedLePropertyAssocDOList.size() > 0) {
					Iterator<LePropertyAssocDO> itr = clonedLePropertyAssocDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setLePropertyAssocDOList(clonedLePropertyAssocDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePropertyAssocComponentRule.postExecuteLePropertyAssocCompFindByLegalEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LePropertyAssocComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePropertyAssocComponent.findByLegalEntityIdPk failed unexpectedly");
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
		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePropertyAssocDO is needed in the request");
		}
		LePropertyAssocDO theLePropertyAssocDO = (LePropertyAssocDO) txnTransferObj.getTxnPayload()
				.getLePropertyAssocDO();
		if (null == theLePropertyAssocDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LePropertyAssocDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLePropertyAssocDO.getInquiryFilter() || theLePropertyAssocDO.getInquiryFilter().isEmpty()) {
			theLePropertyAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLePropertyAssocDO.getInquiryFilter());
		}

	}

	public Page<LePropertyAssocDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<LePropertyAssocDO> pageLePropertyAssocDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLePropertyAssocDOlist = this.theLePropertyAssocRepository
					.findAllActiveByLegalentityIdpk(legalEntityIdPk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLePropertyAssocDOlist = this.theLePropertyAssocRepository
					.findAllInActiveByLegalentityIdpk(legalEntityIdPk, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLePropertyAssocDOlist = this.theLePropertyAssocRepository.findAllByLegalentityIdpk(legalEntityIdPk,
					localPageable);

		}
		return pageLePropertyAssocDOlist;

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, LePropertyAssocDO theLePropertyAssocDO) {

		// PropertyLeReltypeRefkey
		if (!(null == theLePropertyAssocDO.getPropertyLeReltypeRefkey()
				|| theLePropertyAssocDO.getPropertyLeReltypeRefkey().isEmpty())) {
			RefPropertyLeReltypeDO theRefPropertyLeReltypeDO = referenceTableHelper.getRefPropertyLeReltypeValueByKey(
					requesterLanguage, theLePropertyAssocDO.getPropertyLeReltypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPropertyLeReltypeDO) {
				theLePropertyAssocDO.setPropertyLeReltypeRefValue(theRefPropertyLeReltypeDO.getValue());
			}
		}

	}

}
