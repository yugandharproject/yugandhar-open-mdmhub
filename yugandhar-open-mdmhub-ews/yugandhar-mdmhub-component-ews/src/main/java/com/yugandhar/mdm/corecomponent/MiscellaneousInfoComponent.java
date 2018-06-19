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
import com.yugandhar.mdm.extern.dobj.MiscellaneousInfoDO;
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model MiscellaneousInfoDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class MiscellaneousInfoComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	MiscellaneousInfoComponentRule theMiscellaneousInfoComponentRule;

	// get the repository instance
	@Autowired
	private MiscellaneousInfoRepository theMiscellaneousInfoRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public MiscellaneousInfoComponent() {
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
	 *             if MiscellaneousInfoDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theMiscellaneousInfoComponentRule.prevalidateMiscellaneousInfoCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			MiscellaneousInfoDO reqMiscellaneousInfoDO = (MiscellaneousInfoDO) txnTransferObj.getTxnPayload()
					.getMiscellaneousInfoDO();
			if (null == reqMiscellaneousInfoDO.getPrimaryKeyDO()
					|| null == reqMiscellaneousInfoDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqMiscellaneousInfoDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqMiscellaneousInfoDO.setIdPk(reqMiscellaneousInfoDO.getPrimaryKeyDO().getIdPk());
				MiscellaneousInfoDO dbimageMiscellaneousInfoDO = entityManager.find(MiscellaneousInfoDO.class,
						reqMiscellaneousInfoDO.getIdPk());
				if (null != dbimageMiscellaneousInfoDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"MiscellaneousInfoComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theMiscellaneousInfoComponentRule.preExecuteMiscellaneousInfoCompPersist(reqMiscellaneousInfoDO,
					txnTransferObj);
			entityManager.persist(reqMiscellaneousInfoDO);
			entityManager.flush();
			reqMiscellaneousInfoDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setMiscellaneousInfoDO(new MiscellaneousInfoDO(reqMiscellaneousInfoDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getMiscellaneousInfoDO());
			theMiscellaneousInfoComponentRule.postExecuteMiscellaneousInfoCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"MiscellaneousInfoComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"MiscellaneousInfoComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MiscellaneousInfoComponent.persist failed unexpectedly");
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
	 *             if MiscellaneousInfoDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theMiscellaneousInfoComponentRule.PrevalidateMiscellaneousInfoCompMerge(txnTransferObj);
			MiscellaneousInfoDO reqMiscellaneousInfoDO = (MiscellaneousInfoDO) txnTransferObj.getTxnPayload()
					.getMiscellaneousInfoDO();
			MiscellaneousInfoDO dbimageMiscellaneousInfoDO = entityManager.find(MiscellaneousInfoDO.class,
					reqMiscellaneousInfoDO.getIdPk());
			if (null == dbimageMiscellaneousInfoDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"MiscellaneousInfoComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqMiscellaneousInfoDO);
			BeanUtils.copyProperties(reqMiscellaneousInfoDO, dbimageMiscellaneousInfoDO, strIgnoreProperties);
			entityManager.detach(dbimageMiscellaneousInfoDO);
			theMiscellaneousInfoComponentRule.preExecuteMiscellaneousInfoCompMerge(reqMiscellaneousInfoDO,
					dbimageMiscellaneousInfoDO, txnTransferObj);
			MiscellaneousInfoDO mergedMiscellaneousInfoDO = entityManager.merge(dbimageMiscellaneousInfoDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setMiscellaneousInfoDO(new MiscellaneousInfoDO(mergedMiscellaneousInfoDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getMiscellaneousInfoDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theMiscellaneousInfoComponentRule.postExecuteMiscellaneousInfoCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in MiscellaneousInfoComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"MiscellaneousInfoComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"MiscellaneousInfoComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MiscellaneousInfoComponent.merge failed unexpectedly");
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
	 *             if MiscellaneousInfoDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theMiscellaneousInfoComponentRule.prevalidateMiscellaneousInfoCompFindById(txnTransferObj);
			MiscellaneousInfoDO reqMiscellaneousInfoDO = (MiscellaneousInfoDO) txnTransferObj.getTxnPayload()
					.getMiscellaneousInfoDO();
			MiscellaneousInfoDO dbimageMiscellaneousInfoDO = entityManager.find(MiscellaneousInfoDO.class,
					reqMiscellaneousInfoDO.getIdPk());
			if (null == dbimageMiscellaneousInfoDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"MiscellaneousInfoComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setMiscellaneousInfoDO(new MiscellaneousInfoDO(dbimageMiscellaneousInfoDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getMiscellaneousInfoDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theMiscellaneousInfoComponentRule.postExecuteMiscellaneousInfoCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MiscellaneousInfoComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting MiscellaneousInfoDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MiscellaneousInfoDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MiscellaneousInfoDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getEntityObjectTypeRefkey()
				|| txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getEntityObjectTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10038",
					"MiscellaneousInfoDO.entityObjectTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getEntityIdpk()
				|| txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getEntityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10039",
					"MiscellaneousInfoDO.entityIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getName1()
				|| txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getName1().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10040",
					"MiscellaneousInfoDO.name1 is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getValue1()
				|| txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getValue1().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10041",
					"MiscellaneousInfoDO.value1 is needed in the request");
		}

		MiscellaneousInfoDO theMiscellaneousInfoDO = (MiscellaneousInfoDO) txnTransferObj.getTxnPayload()
				.getMiscellaneousInfoDO();
		theMiscellaneousInfoDO.setCreatedTs(new Date());
		theMiscellaneousInfoDO.setUpdatedTs(new Date());
		theMiscellaneousInfoDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theMiscellaneousInfoDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating MiscellaneousInfoDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MiscellaneousInfoDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MiscellaneousInfoDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"MiscellaneousInfoDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"MiscellaneousInfoDO.version should not be null");
		}

		MiscellaneousInfoDO theMiscellaneousInfoDO = (MiscellaneousInfoDO) txnTransferObj.getTxnPayload()
				.getMiscellaneousInfoDO();
		theMiscellaneousInfoDO.setUpdatedTs(new Date());
		theMiscellaneousInfoDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theMiscellaneousInfoDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that MiscellaneousInfoDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MiscellaneousInfoDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MiscellaneousInfoDO is needed in the request");
		}
		MiscellaneousInfoDO theMiscellaneousInfoDO = (MiscellaneousInfoDO) txnTransferObj.getTxnPayload()
				.getMiscellaneousInfoDO();
		if (null == theMiscellaneousInfoDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"MiscellaneousInfoDO.idpk should not be null");
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
	 *             if MiscellaneousInfoDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByEntityIdPk(txnTransferObj);
			theMiscellaneousInfoComponentRule.prevalidateMiscellaneousInfoCompFindByEntityIdPk(txnTransferObj);
			MiscellaneousInfoDO reqMiscellaneousInfoDO = (MiscellaneousInfoDO) txnTransferObj.getTxnPayload()
					.getMiscellaneousInfoDO();

			Page<MiscellaneousInfoDO> pageMiscellaneousInfoDO = findByEntityIdPkFromRepository(
					reqMiscellaneousInfoDO.getEntityObjectTypeRefkey(), reqMiscellaneousInfoDO.getEntityIdpk(),
					reqMiscellaneousInfoDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageMiscellaneousInfoDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageMiscellaneousInfoDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageMiscellaneousInfoDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageMiscellaneousInfoDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageMiscellaneousInfoDO.getSize());

			// process pages if response have results
			if (pageMiscellaneousInfoDO.getTotalPages() != 0) {
				if ((pageMiscellaneousInfoDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for MiscellaneousInfoComponent.findByEntityIdPk, total number of pages is "
									+ pageMiscellaneousInfoDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<MiscellaneousInfoDO> dbimageMiscellaneousInfoDOlist = pageMiscellaneousInfoDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<MiscellaneousInfoDO> clonedMiscellaneousInfoDOList = new ArrayList<MiscellaneousInfoDO>();
				if (null != dbimageMiscellaneousInfoDOlist && dbimageMiscellaneousInfoDOlist.size() > 0) {
					Iterator<MiscellaneousInfoDO> itr = dbimageMiscellaneousInfoDOlist.iterator();
					while (itr.hasNext()) {
						MiscellaneousInfoDO theMiscellaneousInfoDO = new MiscellaneousInfoDO(itr.next());
						clonedMiscellaneousInfoDOList.add(theMiscellaneousInfoDO);
					}
				}

				if (null != clonedMiscellaneousInfoDOList && clonedMiscellaneousInfoDOList.size() > 0) {
					Iterator<MiscellaneousInfoDO> itr = clonedMiscellaneousInfoDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setMiscellaneousInfoDOList(clonedMiscellaneousInfoDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theMiscellaneousInfoComponentRule.postExecuteMiscellaneousInfoCompFindByEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("MiscellaneousInfoComponent.findByEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"MiscellaneousInfoComponent.findByEntityIdPk failed unexpectedly");
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

	private void performCommonvalidationBeforeFindByEntityIdPk(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getMiscellaneousInfoDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"MiscellaneousInfoDO is needed in the request");
		}
		MiscellaneousInfoDO theMiscellaneousInfoDO = (MiscellaneousInfoDO) txnTransferObj.getTxnPayload()
				.getMiscellaneousInfoDO();
		if (null == theMiscellaneousInfoDO.getEntityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10064",
					"MiscellaneousInfoDO.entityIdpk should not be null");
		}

		if (null == theMiscellaneousInfoDO.getEntityObjectTypeRefkey()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10065",
					"MiscellaneousInfoDO.entityObjectTypeRefkey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theMiscellaneousInfoDO.getInquiryFilter() || theMiscellaneousInfoDO.getInquiryFilter().isEmpty()) {
			theMiscellaneousInfoDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theMiscellaneousInfoDO.getInquiryFilter());
		}

	}

	public Page<MiscellaneousInfoDO> findByEntityIdPkFromRepository(String entityObjectTypeRefkey, String entityIdPk,
			String filter, Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "entityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<MiscellaneousInfoDO> pageMiscellaneousInfoDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageMiscellaneousInfoDOlist = this.theMiscellaneousInfoRepository
					.findAllActiveByEntityIdpk(entityObjectTypeRefkey, entityIdPk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageMiscellaneousInfoDOlist = this.theMiscellaneousInfoRepository
					.findAllInActiveByEntityIdpk(entityObjectTypeRefkey, entityIdPk, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageMiscellaneousInfoDOlist = this.theMiscellaneousInfoRepository
					.findAllByEntityIdpk(entityObjectTypeRefkey, entityIdPk, localPageable);

		}

		return pageMiscellaneousInfoDOlist;

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			MiscellaneousInfoDO theMiscellaneousInfoDO) {

		// EntityObjectTypeRefkey
		if (!(null == theMiscellaneousInfoDO.getEntityObjectTypeRefkey()
				|| theMiscellaneousInfoDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					requesterLanguage, theMiscellaneousInfoDO.getEntityObjectTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {
				theMiscellaneousInfoDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
			}
		}

	}

}
