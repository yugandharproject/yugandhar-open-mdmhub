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
import com.yugandhar.mdm.extern.dobj.LePreferencesDO;
import com.yugandhar.mdm.extern.dobj.RefPreferenceTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model LePreferencesDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class LePreferencesComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LePreferencesComponentRule theLePreferencesComponentRule;

	@Autowired
	LePreferencesRepository theLePreferencesRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public LePreferencesComponent() {
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
	 *             if LePreferencesDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theLePreferencesComponentRule.prevalidateLePreferencesCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			LePreferencesDO reqLePreferencesDO = (LePreferencesDO) txnTransferObj.getTxnPayload().getLePreferencesDO();
			if (null == reqLePreferencesDO.getPrimaryKeyDO()
					|| null == reqLePreferencesDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqLePreferencesDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqLePreferencesDO.setIdPk(reqLePreferencesDO.getPrimaryKeyDO().getIdPk());
				LePreferencesDO dbimageLePreferencesDO = entityManager.find(LePreferencesDO.class,
						reqLePreferencesDO.getIdPk());
				if (null != dbimageLePreferencesDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"LePreferencesComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theLePreferencesComponentRule.preExecuteLePreferencesCompPersist(reqLePreferencesDO, txnTransferObj);
			entityManager.persist(reqLePreferencesDO);
			entityManager.flush();
			reqLePreferencesDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setLePreferencesDO(new LePreferencesDO(reqLePreferencesDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePreferencesDO());
			theLePreferencesComponentRule.postExecuteLePreferencesCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LePreferencesComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - prefTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LePreferencesComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePreferencesComponent.persist failed unexpectedly");
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
	 *             if LePreferencesDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theLePreferencesComponentRule.PrevalidateLePreferencesCompMerge(txnTransferObj);
			LePreferencesDO reqLePreferencesDO = (LePreferencesDO) txnTransferObj.getTxnPayload().getLePreferencesDO();
			LePreferencesDO dbimageLePreferencesDO = entityManager.find(LePreferencesDO.class,
					reqLePreferencesDO.getIdPk());
			if (null == dbimageLePreferencesDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LePreferencesComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqLePreferencesDO);
			BeanUtils.copyProperties(reqLePreferencesDO, dbimageLePreferencesDO, strIgnoreProperties);
			entityManager.detach(dbimageLePreferencesDO);
			theLePreferencesComponentRule.preExecuteLePreferencesCompMerge(reqLePreferencesDO, dbimageLePreferencesDO,
					txnTransferObj);
			LePreferencesDO mergedLePreferencesDO = entityManager.merge(dbimageLePreferencesDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLePreferencesDO(new LePreferencesDO(mergedLePreferencesDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePreferencesDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePreferencesComponentRule.postExecuteLePreferencesCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in LePreferencesComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"LePreferencesComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - prefTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"LePreferencesComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePreferencesComponent.merge failed unexpectedly");
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
	 *             if LePreferencesDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theLePreferencesComponentRule.prevalidateLePreferencesCompFindById(txnTransferObj);
			LePreferencesDO reqLePreferencesDO = (LePreferencesDO) txnTransferObj.getTxnPayload().getLePreferencesDO();
			LePreferencesDO dbimageLePreferencesDO = entityManager.find(LePreferencesDO.class,
					reqLePreferencesDO.getIdPk());
			if (null == dbimageLePreferencesDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"LePreferencesComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setLePreferencesDO(new LePreferencesDO(dbimageLePreferencesDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getLePreferencesDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePreferencesComponentRule.postExecuteLePreferencesCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePreferencesComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting LePreferencesDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePreferencesDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getLePreferencesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePreferencesDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLePreferencesDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLePreferencesDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LePreferencesDO.legalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLePreferencesDO().getPreferenceTypeRefkey()
				|| txnTransferObj.getTxnPayload().getLePreferencesDO().getPreferenceTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10038",
					"LePreferencesDO.preferenceTypeRefkey is needed in the request");
		}

		LePreferencesDO theLePreferencesDO = (LePreferencesDO) txnTransferObj.getTxnPayload().getLePreferencesDO();
		theLePreferencesDO.setCreatedTs(new Date());
		theLePreferencesDO.setUpdatedTs(new Date());
		theLePreferencesDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLePreferencesDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating LePreferencesDO object in
	 * the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePreferencesDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLePreferencesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePreferencesDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getLePreferencesDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLePreferencesDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LePreferencesDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getLePreferencesDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"LePreferencesDO.version should not be null");
		}

		LePreferencesDO theLePreferencesDO = (LePreferencesDO) txnTransferObj.getTxnPayload().getLePreferencesDO();
		theLePreferencesDO.setUpdatedTs(new Date());
		theLePreferencesDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theLePreferencesDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that LePreferencesDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LePreferencesDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getLePreferencesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePreferencesDO is needed in the request");
		}
		LePreferencesDO theLePreferencesDO = (LePreferencesDO) txnTransferObj.getTxnPayload().getLePreferencesDO();
		if (null == theLePreferencesDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LePreferencesDO.idpk should not be null");
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
	 *             if LePreferencesDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
			theLePreferencesComponentRule.prevalidateLePreferencesCompFindByLegalEntityIdPk(txnTransferObj);
			LePreferencesDO reqLePreferencesDO = (LePreferencesDO) txnTransferObj.getTxnPayload()
					.getLePreferencesDO();

			Page<LePreferencesDO> pageLePreferencesDO = findByLegalEntityIdPkFromRepository(
					reqLePreferencesDO.getLegalentityIdpk(), reqLePreferencesDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageLePreferencesDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageLePreferencesDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageLePreferencesDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageLePreferencesDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageLePreferencesDO.getSize());

			// process pages if response have results
			if (pageLePreferencesDO.getTotalPages() != 0) {
				if ((pageLePreferencesDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for LePreferencesComponent.findByLegalEntityIdPk, total number of pages is "
									+ pageLePreferencesDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<LePreferencesDO> dbimageLePreferencesDOList = pageLePreferencesDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<LePreferencesDO> clonedLePreferencesDOList = new ArrayList<LePreferencesDO>();
				if (null != dbimageLePreferencesDOList && dbimageLePreferencesDOList.size() > 0) {
					Iterator<LePreferencesDO> itr = dbimageLePreferencesDOList.iterator();
					while (itr.hasNext()) {
						LePreferencesDO theLePreferencesDO = new LePreferencesDO(itr.next());
						clonedLePreferencesDOList.add(theLePreferencesDO);
					}
				}

				// populate reference data attributes
				if (null != clonedLePreferencesDOList && clonedLePreferencesDOList.size() > 0) {
					Iterator<LePreferencesDO> itr = clonedLePreferencesDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setLePreferencesDOList(clonedLePreferencesDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theLePreferencesComponentRule.postExecuteLePreferencesCompFindByLegalEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("LePreferencesComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"LePreferencesComponent.findByLegalEntityIdPk failed unexpectedly");
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
		if (null == txnTransferObj.getTxnPayload().getLePreferencesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePreferencesDO is needed in the request");
		}
		LePreferencesDO theLePreferencesDO = (LePreferencesDO) txnTransferObj.getTxnPayload().getLePreferencesDO();
		if (null == theLePreferencesDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LePreferencesDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theLePreferencesDO.getInquiryFilter() || theLePreferencesDO.getInquiryFilter().isEmpty()) {
			theLePreferencesDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theLePreferencesDO.getInquiryFilter());
		}

	}

	public Page<LePreferencesDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<LePreferencesDO> pageLePreferencesDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageLePreferencesDOlist = this.theLePreferencesRepository.findAllActiveByLegalentityIdpk(legalEntityIdPk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageLePreferencesDOlist = this.theLePreferencesRepository.findAllInActiveByLegalentityIdpk(legalEntityIdPk, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageLePreferencesDOlist = this.theLePreferencesRepository.findAllByLegalentityIdpk(legalEntityIdPk, localPageable);

		}

		return pageLePreferencesDOlist;

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, LePreferencesDO theLePreferencesDO) {

		// PPrefTypeRefkey
		if (!(null == theLePreferencesDO.getPreferenceTypeRefkey()
				|| theLePreferencesDO.getPreferenceTypeRefkey().isEmpty())) {
			RefPreferenceTypeDO theRefPreferenceTypeDO = referenceTableHelper.getRefPreferenceTypeValueByKey(
					requesterLanguage, theLePreferencesDO.getPreferenceTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPreferenceTypeDO) {
				theLePreferencesDO.setPreferenceTypeRefValue(theRefPreferenceTypeDO.getValue());
			}
		}

	}

}
