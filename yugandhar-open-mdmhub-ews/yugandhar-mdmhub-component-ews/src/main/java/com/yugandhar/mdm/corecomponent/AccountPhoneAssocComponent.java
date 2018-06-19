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
import com.yugandhar.mdm.extern.dobj.AccountPhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model AccountPhoneAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class AccountPhoneAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AccountPhoneAssocComponentRule theAccountPhoneAssocComponentRule;

	// get the repository instance
	@Autowired
	private AccountPhoneAssocRepository theAccountPhoneAssocRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public AccountPhoneAssocComponent() {
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
	 *             if AccountPhoneAssocDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theAccountPhoneAssocComponentRule.prevalidateAccountPhoneAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			AccountPhoneAssocDO reqAccountPhoneAssocDO = (AccountPhoneAssocDO) txnTransferObj.getTxnPayload()
					.getAccountPhoneAssocDO();
			if (null == reqAccountPhoneAssocDO.getPrimaryKeyDO()
					|| null == reqAccountPhoneAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqAccountPhoneAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqAccountPhoneAssocDO.setIdPk(reqAccountPhoneAssocDO.getPrimaryKeyDO().getIdPk());
				AccountPhoneAssocDO dbimageAccountPhoneAssocDO = entityManager.find(AccountPhoneAssocDO.class,
						reqAccountPhoneAssocDO.getIdPk());
				if (null != dbimageAccountPhoneAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"AccountPhoneAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theAccountPhoneAssocComponentRule.preExecuteAccountPhoneAssocCompPersist(reqAccountPhoneAssocDO,
					txnTransferObj);
			entityManager.persist(reqAccountPhoneAssocDO);
			entityManager.flush();
			reqAccountPhoneAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setAccountPhoneAssocDO(new AccountPhoneAssocDO(reqAccountPhoneAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAccountPhoneAssocDO());
			theAccountPhoneAssocComponentRule.postExecuteAccountPhoneAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AccountPhoneAssocComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AccountPhoneAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountPhoneAssocComponent.persist failed unexpectedly");
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
	 *             if AccountPhoneAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theAccountPhoneAssocComponentRule.PrevalidateAccountPhoneAssocCompMerge(txnTransferObj);
			AccountPhoneAssocDO reqAccountPhoneAssocDO = (AccountPhoneAssocDO) txnTransferObj.getTxnPayload()
					.getAccountPhoneAssocDO();
			AccountPhoneAssocDO dbimageAccountPhoneAssocDO = entityManager.find(AccountPhoneAssocDO.class,
					reqAccountPhoneAssocDO.getIdPk());
			if (null == dbimageAccountPhoneAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AccountPhoneAssocComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqAccountPhoneAssocDO);
			BeanUtils.copyProperties(reqAccountPhoneAssocDO, dbimageAccountPhoneAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageAccountPhoneAssocDO);
			theAccountPhoneAssocComponentRule.preExecuteAccountPhoneAssocCompMerge(reqAccountPhoneAssocDO,
					dbimageAccountPhoneAssocDO, txnTransferObj);
			AccountPhoneAssocDO mergedAccountPhoneAssocDO = entityManager.merge(dbimageAccountPhoneAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setAccountPhoneAssocDO(new AccountPhoneAssocDO(mergedAccountPhoneAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAccountPhoneAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAccountPhoneAssocComponentRule.postExecuteAccountPhoneAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in AccountPhoneAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AccountPhoneAssocComponent.persist failed - Unique Key Violated, record with accountIdPk - phoneTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AccountPhoneAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountPhoneAssocComponent.merge failed unexpectedly");
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
	 *             if AccountPhoneAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theAccountPhoneAssocComponentRule.prevalidateAccountPhoneAssocCompFindById(txnTransferObj);
			AccountPhoneAssocDO reqAccountPhoneAssocDO = (AccountPhoneAssocDO) txnTransferObj.getTxnPayload()
					.getAccountPhoneAssocDO();
			AccountPhoneAssocDO dbimageAccountPhoneAssocDO = entityManager.find(AccountPhoneAssocDO.class,
					reqAccountPhoneAssocDO.getIdPk());
			if (null == dbimageAccountPhoneAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AccountPhoneAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setAccountPhoneAssocDO(new AccountPhoneAssocDO(dbimageAccountPhoneAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAccountPhoneAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAccountPhoneAssocComponentRule.postExecuteAccountPhoneAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountPhoneAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting AccountPhoneAssocDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountPhoneAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountPhoneAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getAccountIdpk()
				|| txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getAccountIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10014",
					"LePhoneAssocDO.accountIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getPhoneTypeRefkey()
				|| txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getPhoneTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10010",
					"LePhoneAssocDO.phoneTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getPhoneNumber()
				|| txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getPhoneNumber().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10011",
					"LePhoneAssocDO.phoneNumber is needed in the request");
		}

		AccountPhoneAssocDO theAccountPhoneAssocDO = (AccountPhoneAssocDO) txnTransferObj.getTxnPayload()
				.getAccountPhoneAssocDO();
		theAccountPhoneAssocDO.setCreatedTs(new Date());
		theAccountPhoneAssocDO.setUpdatedTs(new Date());
		theAccountPhoneAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAccountPhoneAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating AccountPhoneAssocDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountPhoneAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountPhoneAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AccountPhoneAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"AccountPhoneAssocDO.version should not be null");
		}

		AccountPhoneAssocDO theAccountPhoneAssocDO = (AccountPhoneAssocDO) txnTransferObj.getTxnPayload()
				.getAccountPhoneAssocDO();
		theAccountPhoneAssocDO.setUpdatedTs(new Date());
		theAccountPhoneAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAccountPhoneAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that AccountPhoneAssocDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountPhoneAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountPhoneAssocDO is needed in the request");
		}
		AccountPhoneAssocDO theAccountPhoneAssocDO = (AccountPhoneAssocDO) txnTransferObj.getTxnPayload()
				.getAccountPhoneAssocDO();
		if (null == theAccountPhoneAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AccountPhoneAssocDO.idpk should not be null");
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
	 *             if AccountPhoneAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByAccountIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByAccountIdPk(txnTransferObj);
			theAccountPhoneAssocComponentRule.prevalidateAccountPhoneAssocCompFindByAccountIdPk(txnTransferObj);
			AccountPhoneAssocDO reqAccountPhoneAssocDO = (AccountPhoneAssocDO) txnTransferObj.getTxnPayload()
					.getAccountPhoneAssocDO();

			Page<AccountPhoneAssocDO> pageAccountPhoneAssocDO = findByAccountIdPkFromRepository(
					reqAccountPhoneAssocDO.getAccountIdpk(), reqAccountPhoneAssocDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageAccountPhoneAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageAccountPhoneAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageAccountPhoneAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageAccountPhoneAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageAccountPhoneAssocDO.getSize());

			// process pages if response have results
			if (pageAccountPhoneAssocDO.getTotalPages() != 0) {
				if ((pageAccountPhoneAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for AccountPhoneAssocComponent.findByAccountIdPk, total number of pages is "
									+ pageAccountPhoneAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<AccountPhoneAssocDO> dbimageAccountPhoneAssocDOlist = pageAccountPhoneAssocDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity
				// manager level 1 (L1) cached object
				List<AccountPhoneAssocDO> clonedAccountPhoneAssocDOList = new ArrayList<AccountPhoneAssocDO>();
				if (null != dbimageAccountPhoneAssocDOlist && dbimageAccountPhoneAssocDOlist.size() > 0) {
					Iterator<AccountPhoneAssocDO> itr = dbimageAccountPhoneAssocDOlist.iterator();
					while (itr.hasNext()) {
						AccountPhoneAssocDO theAccountPhoneAssocDO = new AccountPhoneAssocDO(itr.next());
						clonedAccountPhoneAssocDOList.add(theAccountPhoneAssocDO);
					}
				}

				if (null != clonedAccountPhoneAssocDOList && clonedAccountPhoneAssocDOList.size() > 0) {
					Iterator<AccountPhoneAssocDO> itr = clonedAccountPhoneAssocDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setAccountPhoneAssocDOList(clonedAccountPhoneAssocDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAccountPhoneAssocComponentRule.postExecuteAccountPhoneAssocCompFindByAccountIdPk(respTxnTransferObj);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("AccountPhoneAssocComponent.findByAccountIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountPhoneAssocComponent.findByAccountIdPk failed unexpectedly");
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

	private void performCommonvalidationBeforeFindByAccountIdPk(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountPhoneAssocDO is needed in the request");
		}
		AccountPhoneAssocDO theAccountPhoneAssocDO = (AccountPhoneAssocDO) txnTransferObj.getTxnPayload()
				.getAccountPhoneAssocDO();
		if (null == theAccountPhoneAssocDO.getAccountIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"AccountPhoneAssocDO.accountIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theAccountPhoneAssocDO.getInquiryFilter() || theAccountPhoneAssocDO.getInquiryFilter().isEmpty()) {
			theAccountPhoneAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theAccountPhoneAssocDO.getInquiryFilter());
		}

	}

	public Page<AccountPhoneAssocDO> findByAccountIdPkFromRepository(String accountIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "accountIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<AccountPhoneAssocDO> pageAccountPhoneAssocDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageAccountPhoneAssocDOlist = this.theAccountPhoneAssocRepository.findAllActiveByAccountIdpk(accountIdPk,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageAccountPhoneAssocDOlist = this.theAccountPhoneAssocRepository.findAllInActiveByAccountIdpk(accountIdPk,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageAccountPhoneAssocDOlist = this.theAccountPhoneAssocRepository.findAllByAccountIdpk(accountIdPk,
					localPageable);
		}

		return pageAccountPhoneAssocDOlist;
	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			AccountPhoneAssocDO theAccountPhoneAssocDO) {

		// phoneTypeRefkey
		if (!(null == theAccountPhoneAssocDO.getPhoneTypeRefkey()
				|| theAccountPhoneAssocDO.getPhoneTypeRefkey().isEmpty())) {
			RefPhoneTypeDO theRefPhoneTypeDO = referenceTableHelper.getRefPhoneTypeValueByKey(requesterLanguage,
					theAccountPhoneAssocDO.getPhoneTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneTypeDO) {
				theAccountPhoneAssocDO.setPhoneTypeRefValue(theRefPhoneTypeDO.getValue());
			}
		}

		// phoneSubtypeRefkey
		if (!(null == theAccountPhoneAssocDO.getPhoneSubtypeRefkey()
				|| theAccountPhoneAssocDO.getPhoneSubtypeRefkey().isEmpty())) {
			RefPhoneSubtypeDO theRefPhoneSubtypeDO = referenceTableHelper.getRefPhoneSubtypeValueByKey(
					requesterLanguage, theAccountPhoneAssocDO.getPhoneSubtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPhoneSubtypeDO) {
				theAccountPhoneAssocDO.setPhoneSubtypeRefValue(theRefPhoneSubtypeDO.getValue());
			}
		}

	}

}
