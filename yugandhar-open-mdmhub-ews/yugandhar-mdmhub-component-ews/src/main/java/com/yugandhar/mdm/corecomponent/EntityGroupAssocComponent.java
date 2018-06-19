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
import com.yugandhar.mdm.extern.dobj.EntityGroupAssocDO;
import com.yugandhar.mdm.extern.dobj.RefAssocTypeDO;
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model EntityGroupAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class EntityGroupAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	EntityGroupAssocComponentRule theEntityGroupAssocComponentRule;

	// get the repository instance
	@Autowired
	private EntityGroupAssocRepository theEntityGroupAssocRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public EntityGroupAssocComponent() {
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
	 *             if EntityGroupAssocDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theEntityGroupAssocComponentRule.prevalidateEntityGroupAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			EntityGroupAssocDO reqEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
					.getEntityGroupAssocDO();
			if (null == reqEntityGroupAssocDO.getPrimaryKeyDO()
					|| null == reqEntityGroupAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqEntityGroupAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqEntityGroupAssocDO.setIdPk(reqEntityGroupAssocDO.getPrimaryKeyDO().getIdPk());
				EntityGroupAssocDO dbimageEntityGroupAssocDO = entityManager.find(EntityGroupAssocDO.class,
						reqEntityGroupAssocDO.getIdPk());
				if (null != dbimageEntityGroupAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"EntityGroupAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theEntityGroupAssocComponentRule.preExecuteEntityGroupAssocCompPersist(reqEntityGroupAssocDO,
					txnTransferObj);
			entityManager.persist(reqEntityGroupAssocDO);
			entityManager.flush();
			reqEntityGroupAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setEntityGroupAssocDO(new EntityGroupAssocDO(reqEntityGroupAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getEntityGroupAssocDO());
			theEntityGroupAssocComponentRule.postExecuteEntityGroupAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"EntityGroupAssocComponent.persist failed - Unique Key Violated, record with entityObjectTypeRefkey, entityIdpk, entityGroupIdpk ,  assocTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"EntityGroupAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"EntityGroupAssocComponent.persist failed unexpectedly");
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
	 *             if EntityGroupAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theEntityGroupAssocComponentRule.PrevalidateEntityGroupAssocCompMerge(txnTransferObj);
			EntityGroupAssocDO reqEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
					.getEntityGroupAssocDO();
			EntityGroupAssocDO dbimageEntityGroupAssocDO = entityManager.find(EntityGroupAssocDO.class,
					reqEntityGroupAssocDO.getIdPk());
			if (null == dbimageEntityGroupAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"EntityGroupAssocComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqEntityGroupAssocDO);
			BeanUtils.copyProperties(reqEntityGroupAssocDO, dbimageEntityGroupAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageEntityGroupAssocDO);
			theEntityGroupAssocComponentRule.preExecuteEntityGroupAssocCompMerge(reqEntityGroupAssocDO,
					dbimageEntityGroupAssocDO, txnTransferObj);
			EntityGroupAssocDO mergedEntityGroupAssocDO = entityManager.merge(dbimageEntityGroupAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setEntityGroupAssocDO(new EntityGroupAssocDO(mergedEntityGroupAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getEntityGroupAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theEntityGroupAssocComponentRule.postExecuteEntityGroupAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in EntityGroupAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"EntityGroupAssocComponent.persist failed - Unique Key Violated, record with entityObjectTypeRefkey, entityIdpk, entityGroupIdpk ,  assocTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"EntityGroupAssocComponent.persist failed unexpectedly with PersistenceException"); // Transaction
																											// Failed
																											// due
																											// to
																											// unknown
																											// error,
																											// please
																											// check
																											// statck
																											// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"EntityGroupAssocComponent.merge failed unexpectedly");
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
	 *             if EntityGroupAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theEntityGroupAssocComponentRule.prevalidateEntityGroupAssocCompFindById(txnTransferObj);
			EntityGroupAssocDO reqEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
					.getEntityGroupAssocDO();
			EntityGroupAssocDO dbimageEntityGroupAssocDO = entityManager.find(EntityGroupAssocDO.class,
					reqEntityGroupAssocDO.getIdPk());
			if (null == dbimageEntityGroupAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"EntityGroupAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setEntityGroupAssocDO(new EntityGroupAssocDO(dbimageEntityGroupAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getEntityGroupAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theEntityGroupAssocComponentRule.postExecuteEntityGroupAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"EntityGroupAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting EntityGroupAssocDO object
	 * in the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if EntityGroupAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"EntityGroupAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getEntityObjectTypeRefkey()
				|| txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getEntityObjectTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10045",
					"EntityGroupAssocDO.entityObjectTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getEntityIdpk()
				|| txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getEntityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10040",
					"EntityGroupAssocDO.entityIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getEntityGroupIdpk()
				|| txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getEntityGroupIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10046",
					"EntityGroupAssocDO.entityGroupIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getAssocTypeRefkey()
				|| txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getAssocTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10047",
					"EntityGroupAssocDO.assocTypeRefkey is needed in the request");
		}

		EntityGroupAssocDO theEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
				.getEntityGroupAssocDO();
		theEntityGroupAssocDO.setCreatedTs(new Date());
		theEntityGroupAssocDO.setUpdatedTs(new Date());
		theEntityGroupAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theEntityGroupAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating EntityGroupAssocDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if EntityGroupAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"EntityGroupAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"EntityGroupAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"EntityGroupAssocDO.version should not be null");
		}

		EntityGroupAssocDO theEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
				.getEntityGroupAssocDO();
		theEntityGroupAssocDO.setUpdatedTs(new Date());
		theEntityGroupAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theEntityGroupAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that EntityGroupAssocDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if EntityGroupAssocDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"EntityGroupAssocDO is needed in the request");
		}
		EntityGroupAssocDO theEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
				.getEntityGroupAssocDO();
		if (null == theEntityGroupAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"EntityGroupAssocDO.idpk should not be null");
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
	 *             if EntityGroupAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByEntityIdPk(txnTransferObj);
			theEntityGroupAssocComponentRule.prevalidateEntityGroupAssocCompFindByEntityIdPk(txnTransferObj);
			EntityGroupAssocDO reqEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
					.getEntityGroupAssocDO();

			Page<EntityGroupAssocDO> pageEntityGroupAssocDO = findByEntityIdPkFromRepository(
					reqEntityGroupAssocDO.getEntityObjectTypeRefkey(), reqEntityGroupAssocDO.getEntityIdpk(),
					reqEntityGroupAssocDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageEntityGroupAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageEntityGroupAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageEntityGroupAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageEntityGroupAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageEntityGroupAssocDO.getSize());

			// process pages if response have results
			if (pageEntityGroupAssocDO.getTotalPages() != 0) {
				if ((pageEntityGroupAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for EntityGroupAssocComponent.findByEntityIdPk, total number of pages is "
									+ pageEntityGroupAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<EntityGroupAssocDO> dbimageEntityGroupAssocDOList = pageEntityGroupAssocDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<EntityGroupAssocDO> clonedEntityGroupAssocDOList = new ArrayList<EntityGroupAssocDO>();
				if (null != dbimageEntityGroupAssocDOList && dbimageEntityGroupAssocDOList.size() > 0) {
					Iterator<EntityGroupAssocDO> itr = dbimageEntityGroupAssocDOList.iterator();
					while (itr.hasNext()) {
						EntityGroupAssocDO theEntityGroupAssocDO = new EntityGroupAssocDO(itr.next());
						clonedEntityGroupAssocDOList.add(theEntityGroupAssocDO);
					}
				}

				// populate reference data attributes
				if (null != clonedEntityGroupAssocDOList && clonedEntityGroupAssocDOList.size() > 0) {
					Iterator<EntityGroupAssocDO> itr = clonedEntityGroupAssocDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setEntityGroupAssocDOList(clonedEntityGroupAssocDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theEntityGroupAssocComponentRule.postExecuteEntityGroupAssocCompFindByEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("EntityGroupAssocComponent.findByEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"EntityGroupAssocComponent.findByEntityIdPk failed unexpectedly");
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
		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"EntityGroupAssocDO is needed in the request");
		}
		EntityGroupAssocDO theEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
				.getEntityGroupAssocDO();
		if (null == theEntityGroupAssocDO.getEntityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10066",
					"EntityGroupAssocDO.EntityIdpk should not be null");
		}

		if (null == theEntityGroupAssocDO.getEntityObjectTypeRefkey()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10067",
					"EntityGroupAssocDO.entityObjectTypeRefkey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theEntityGroupAssocDO.getInquiryFilter() || theEntityGroupAssocDO.getInquiryFilter().isEmpty()) {
			theEntityGroupAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theEntityGroupAssocDO.getInquiryFilter());
		}

	}

	public Page<EntityGroupAssocDO> findByEntityIdPkFromRepository(String entityObjectTypeRefkey, String entityIdPk,
			String filter, Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "entityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<EntityGroupAssocDO> pageEntityGroupAssocDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageEntityGroupAssocDOlist = this.theEntityGroupAssocRepository
					.findAllActiveByEntityIdpk(entityObjectTypeRefkey, entityIdPk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageEntityGroupAssocDOlist = this.theEntityGroupAssocRepository
					.findAllInActiveByEntityIdpk(entityObjectTypeRefkey, entityIdPk, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageEntityGroupAssocDOlist = this.theEntityGroupAssocRepository.findAllByEntityIdpk(entityObjectTypeRefkey,
					entityIdPk, localPageable);

		}

		return pageEntityGroupAssocDOlist;

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
	 *             if EntityGroupAssocDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByEntityGroupIdpk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByEntityGroupIdpk(txnTransferObj);
			theEntityGroupAssocComponentRule.prevalidateEntityGroupAssocCompFindByEntityGroupIdpk(txnTransferObj);
			EntityGroupAssocDO reqEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
					.getEntityGroupAssocDO();

			Page<EntityGroupAssocDO> pageEntityGroupAssocDO = findByEntityGroupIdpkFromRepository(
					reqEntityGroupAssocDO.getEntityGroupIdpk(), reqEntityGroupAssocDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageEntityGroupAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageEntityGroupAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageEntityGroupAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageEntityGroupAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageEntityGroupAssocDO.getSize());

			// process pages if response have results
			if (pageEntityGroupAssocDO.getTotalPages() != 0) {
				if ((pageEntityGroupAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for EntityGroupAssocComponent.findByEntityGroupIdpk, total number of pages is "
									+ pageEntityGroupAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<EntityGroupAssocDO> dbimageEntityGroupAssocDOList = pageEntityGroupAssocDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<EntityGroupAssocDO> clonedEntityGroupAssocDOList = new ArrayList<EntityGroupAssocDO>();
				if (null != dbimageEntityGroupAssocDOList && dbimageEntityGroupAssocDOList.size() > 0) {
					Iterator<EntityGroupAssocDO> itr = dbimageEntityGroupAssocDOList.iterator();
					while (itr.hasNext()) {
						EntityGroupAssocDO theEntityGroupAssocDO = new EntityGroupAssocDO(itr.next());
						clonedEntityGroupAssocDOList.add(theEntityGroupAssocDO);
					}
				}

				// populate reference data attributes
				if (null != clonedEntityGroupAssocDOList && clonedEntityGroupAssocDOList.size() > 0) {
					Iterator<EntityGroupAssocDO> itr = clonedEntityGroupAssocDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setEntityGroupAssocDOList(clonedEntityGroupAssocDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theEntityGroupAssocComponentRule.postExecuteEntityGroupAssocCompFindByEntityGroupIdpk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("EntityGroupAssocComponent.findByEntityGroupIdpk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"EntityGroupAssocComponent.findByEntityGroupIdpk failed unexpectedly");
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

	private void performCommonvalidationBeforeFindByEntityGroupIdpk(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getEntityGroupAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"EntityGroupAssocDO is needed in the request");
		}
		EntityGroupAssocDO theEntityGroupAssocDO = (EntityGroupAssocDO) txnTransferObj.getTxnPayload()
				.getEntityGroupAssocDO();
		if (null == theEntityGroupAssocDO.getEntityGroupIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10068",
					"EntityGroupAssocDO.EntityGroupIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theEntityGroupAssocDO.getInquiryFilter() || theEntityGroupAssocDO.getInquiryFilter().isEmpty()) {
			theEntityGroupAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theEntityGroupAssocDO.getInquiryFilter());
		}

	}

	public Page<EntityGroupAssocDO> findByEntityGroupIdpkFromRepository(String entityGroupIdpk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "entityGroupIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<EntityGroupAssocDO> pageEntityGroupAssocDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageEntityGroupAssocDOlist = this.theEntityGroupAssocRepository
					.findAllActiveByEntityGroupIdpk(entityGroupIdpk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageEntityGroupAssocDOlist = this.theEntityGroupAssocRepository
					.findAllInActiveByEntityGroupIdpk(entityGroupIdpk, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageEntityGroupAssocDOlist = this.theEntityGroupAssocRepository.findAllByEntityGroupIdpk(entityGroupIdpk,
					localPageable);

		}

		return pageEntityGroupAssocDOlist;

	}

	/*
	 * Sample Code
	 * 
	 * @Autowired protected RefEntityObjectTypeComponent
	 * theRefEntityObjectTypeComponent;
	 */

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, EntityGroupAssocDO theEntityGroupAssocDO) {
		// EntityObjectTypeRefkey
		if (!(null == theEntityGroupAssocDO.getEntityObjectTypeRefkey()
				|| theEntityGroupAssocDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					requesterLanguage, theEntityGroupAssocDO.getEntityObjectTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {
				theEntityGroupAssocDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
			}
		}

		// assocTypeRefkey
		if (!(null == theEntityGroupAssocDO.getAssocTypeRefkey()
				|| theEntityGroupAssocDO.getAssocTypeRefkey().isEmpty())) {
			RefAssocTypeDO theRefAssocTypeDO = referenceTableHelper.getRefAssocTypeValueByKey(requesterLanguage,
					theEntityGroupAssocDO.getAssocTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAssocTypeDO) {
				theEntityGroupAssocDO.setAssocTypeRefValue(theRefAssocTypeDO.getValue());
			}
		}

	}

}
