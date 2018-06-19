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
import com.yugandhar.mdm.extern.dobj.CorporationnamesDO;
import com.yugandhar.mdm.extern.dobj.RefCorporationNameTypeDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model CorporationnamesDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class CorporationnamesComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	CorporationnamesComponentRule theCorporationnamesComponentRule;

	// get the repository instance
	@Autowired
	private CorporationnamesRepository theCorporationnamesRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public CorporationnamesComponent() {
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
	 *             if CorporationnamesDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theCorporationnamesComponentRule.prevalidateCorporationnamesCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			CorporationnamesDO reqCorporationnamesDO = (CorporationnamesDO) txnTransferObj.getTxnPayload()
					.getCorporationnamesDO();
			if (null == reqCorporationnamesDO.getPrimaryKeyDO()
					|| null == reqCorporationnamesDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqCorporationnamesDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqCorporationnamesDO.setIdPk(reqCorporationnamesDO.getPrimaryKeyDO().getIdPk());
				CorporationnamesDO dbimageCorporationnamesDO = entityManager.find(CorporationnamesDO.class,
						reqCorporationnamesDO.getIdPk());
				if (null != dbimageCorporationnamesDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"CorporationnamesComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theCorporationnamesComponentRule.preExecuteCorporationnamesCompPersist(reqCorporationnamesDO,
					txnTransferObj);
			entityManager.persist(reqCorporationnamesDO);
			entityManager.flush();
			reqCorporationnamesDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setCorporationnamesDO(new CorporationnamesDO(reqCorporationnamesDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getCorporationnamesDO());
			theCorporationnamesComponentRule.postExecuteCorporationnamesCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"CorporationnamesComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - corporationnameTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"CorporationnamesComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"CorporationnamesComponent.persist failed unexpectedly");
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
	 *             if CorporationnamesDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theCorporationnamesComponentRule.PrevalidateCorporationnamesCompMerge(txnTransferObj);
			CorporationnamesDO reqCorporationnamesDO = (CorporationnamesDO) txnTransferObj.getTxnPayload()
					.getCorporationnamesDO();
			CorporationnamesDO dbimageCorporationnamesDO = entityManager.find(CorporationnamesDO.class,
					reqCorporationnamesDO.getIdPk());
			if (null == dbimageCorporationnamesDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"CorporationnamesComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqCorporationnamesDO);
			BeanUtils.copyProperties(reqCorporationnamesDO, dbimageCorporationnamesDO, strIgnoreProperties);
			entityManager.detach(dbimageCorporationnamesDO);
			theCorporationnamesComponentRule.preExecuteCorporationnamesCompMerge(reqCorporationnamesDO,
					dbimageCorporationnamesDO, txnTransferObj);
			CorporationnamesDO mergedCorporationnamesDO = entityManager.merge(dbimageCorporationnamesDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setCorporationnamesDO(new CorporationnamesDO(mergedCorporationnamesDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getCorporationnamesDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theCorporationnamesComponentRule.postExecuteCorporationnamesCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in CorporationnamesComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"CorporationnamesComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - corporationnameTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"CorporationnamesComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"CorporationnamesComponent.merge failed unexpectedly");
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
	 *             if CorporationnamesDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theCorporationnamesComponentRule.prevalidateCorporationnamesCompFindById(txnTransferObj);
			CorporationnamesDO reqCorporationnamesDO = (CorporationnamesDO) txnTransferObj.getTxnPayload()
					.getCorporationnamesDO();
			CorporationnamesDO dbimageCorporationnamesDO = entityManager.find(CorporationnamesDO.class,
					reqCorporationnamesDO.getIdPk());
			if (null == dbimageCorporationnamesDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"CorporationnamesComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setCorporationnamesDO(new CorporationnamesDO(dbimageCorporationnamesDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getCorporationnamesDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theCorporationnamesComponentRule.postExecuteCorporationnamesCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"CorporationnamesComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting CorporationnamesDO object
	 * in the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if CorporationnamesDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getCorporationnamesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"CorporationnamesDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getCorporationnamesDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getCorporationnamesDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10005",
					"CorporationnamesDO.legalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getCorporationnamesDO().getCorporationNameTypeRefkey()
				|| txnTransferObj.getTxnPayload().getCorporationnamesDO().getCorporationNameTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10006",
					"CorporationnamesDO.corporationNameTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getCorporationnamesDO().getCorporationName()
				|| txnTransferObj.getTxnPayload().getCorporationnamesDO().getCorporationName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10007",
					"CorporationnamesDO.corporationName is needed in the request");
		}

		CorporationnamesDO theCorporationnamesDO = (CorporationnamesDO) txnTransferObj.getTxnPayload()
				.getCorporationnamesDO();
		theCorporationnamesDO.setCreatedTs(new Date());
		theCorporationnamesDO.setUpdatedTs(new Date());
		theCorporationnamesDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theCorporationnamesDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating CorporationnamesDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if CorporationnamesDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getCorporationnamesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"CorporationnamesDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getCorporationnamesDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getCorporationnamesDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"CorporationnamesDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getCorporationnamesDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"CorporationnamesDO.version should not be null");
		}

		CorporationnamesDO theCorporationnamesDO = (CorporationnamesDO) txnTransferObj.getTxnPayload()
				.getCorporationnamesDO();
		theCorporationnamesDO.setUpdatedTs(new Date());
		theCorporationnamesDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theCorporationnamesDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that CorporationnamesDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if CorporationnamesDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getCorporationnamesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"CorporationnamesDO is needed in the request");
		}
		CorporationnamesDO theCorporationnamesDO = (CorporationnamesDO) txnTransferObj.getTxnPayload()
				.getCorporationnamesDO();
		if (null == theCorporationnamesDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"CorporationnamesDO.idpk should not be null");
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
	 *             if CorporationnamesDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
			theCorporationnamesComponentRule.prevalidateCorporationnamesCompFindByLegalEntityIdPk(txnTransferObj);
			CorporationnamesDO reqCorporationnamesDO = (CorporationnamesDO) txnTransferObj.getTxnPayload()
					.getCorporationnamesDO();

			Page<CorporationnamesDO> pageCorporationnamesDO = findByLegalEntityIdPkFromRepository(
					reqCorporationnamesDO.getLegalentityIdpk(), reqCorporationnamesDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageCorporationnamesDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageCorporationnamesDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pageCorporationnamesDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageCorporationnamesDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageCorporationnamesDO.getSize());

			// process pages if response have results
			if (pageCorporationnamesDO.getTotalPages() != 0) {
				if ((pageCorporationnamesDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for CorporationnamesComponent.findByLegalEntityIdPk, total number of pages is "
									+ pageCorporationnamesDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<CorporationnamesDO> dbimageCorporationnamesDOList = pageCorporationnamesDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<CorporationnamesDO> clonedCorporationnamesDOList = new ArrayList<CorporationnamesDO>();
				if (null != dbimageCorporationnamesDOList && dbimageCorporationnamesDOList.size() > 0) {
					Iterator<CorporationnamesDO> itr = dbimageCorporationnamesDOList.iterator();
					while (itr.hasNext()) {
						CorporationnamesDO theCorporationnamesDO = new CorporationnamesDO(itr.next());
						clonedCorporationnamesDOList.add(theCorporationnamesDO);
					}
				}

				// populate reference data attributes
				if (null != clonedCorporationnamesDOList && clonedCorporationnamesDOList.size() > 0) {
					Iterator<CorporationnamesDO> itr = clonedCorporationnamesDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setCorporationnamesDOList(clonedCorporationnamesDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theCorporationnamesComponentRule.postExecuteCorporationnamesCompFindByLegalEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("CorporationnamesComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"CorporationnamesComponent.findByLegalEntityIdPk failed unexpectedly");
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
		if (null == txnTransferObj.getTxnPayload().getCorporationnamesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"CorporationnamesDO is needed in the request");
		}
		CorporationnamesDO theCorporationnamesDO = (CorporationnamesDO) txnTransferObj.getTxnPayload()
				.getCorporationnamesDO();
		if (null == theCorporationnamesDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1009",
					"CorporationnamesDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theCorporationnamesDO.getInquiryFilter() || theCorporationnamesDO.getInquiryFilter().isEmpty()) {
			theCorporationnamesDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theCorporationnamesDO.getInquiryFilter());
		}

	}

	public Page<CorporationnamesDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);
		
		Page<CorporationnamesDO> pageCorporationnamesDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageCorporationnamesDOlist = this.theCorporationnamesRepository
					.findAllActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageCorporationnamesDOlist = this.theCorporationnamesRepository
					.findAllInActiveByLegalentityIdpk(legalEntityIdPk,localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageCorporationnamesDOlist = this.theCorporationnamesRepository.findAllByLegalentityIdpk(legalEntityIdPk,localPageable);

		}
		return pageCorporationnamesDOlist;

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, CorporationnamesDO theCorporationnamesDO) {

		// corporationNameTypeRefkey
		if (!(null == theCorporationnamesDO.getCorporationNameTypeRefkey()
				|| theCorporationnamesDO.getCorporationNameTypeRefkey().isEmpty())) {
			RefCorporationNameTypeDO theRefCorporationNameTypeDO = referenceTableHelper
					.getRefCorporationNameTypeValueByKey(requesterLanguage,
							theCorporationnamesDO.getCorporationNameTypeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCorporationNameTypeDO) {
				theCorporationnamesDO.setCorporationNameTypeRefValue(theRefCorporationNameTypeDO.getValue());
			}
		}

		// SourceSystemRefkey
		if (!(null == theCorporationnamesDO.getSourceSystemRefkey()
				|| theCorporationnamesDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					requesterLanguage, theCorporationnamesDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {
				theCorporationnamesDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
			}
		}
	}

}
