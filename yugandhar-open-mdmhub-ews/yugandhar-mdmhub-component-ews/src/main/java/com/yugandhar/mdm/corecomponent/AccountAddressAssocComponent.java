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
import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.RefAddressSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefAddressTypeDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model AccountAddressAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class AccountAddressAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AccountAddressAssocComponentRule theAccountAddressAssocComponentRule;

	// get the repository instance
	@Autowired
	private AccountAddressAssocRepository theAccountAddressAssocRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public AccountAddressAssocComponent() {
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
	 *             if AccountAddressAssocDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theAccountAddressAssocComponentRule.prevalidateAccountAddressAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			AccountAddressAssocDO reqAccountAddressAssocDO = (AccountAddressAssocDO) txnTransferObj.getTxnPayload()
					.getAccountAddressAssocDO();
			if (null == reqAccountAddressAssocDO.getPrimaryKeyDO()
					|| null == reqAccountAddressAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqAccountAddressAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqAccountAddressAssocDO.setIdPk(reqAccountAddressAssocDO.getPrimaryKeyDO().getIdPk());
				AccountAddressAssocDO dbimageAccountAddressAssocDO = entityManager.find(AccountAddressAssocDO.class,
						reqAccountAddressAssocDO.getIdPk());
				if (null != dbimageAccountAddressAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"AccountAddressAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theAccountAddressAssocComponentRule.preExecuteAccountAddressAssocCompPersist(reqAccountAddressAssocDO,
					txnTransferObj);
			entityManager.persist(reqAccountAddressAssocDO);
			entityManager.flush();
			reqAccountAddressAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setAccountAddressAssocDO(new AccountAddressAssocDO(reqAccountAddressAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAccountAddressAssocDO());
			theAccountAddressAssocComponentRule.postExecuteAccountAddressAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AccountAddressAssocComponent.persist failed - Unique Key Violated, record with accountIdPk - AddressTypeRefkey - AddressSubtypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AccountAddressAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountAddressAssocComponent.persist failed unexpectedly");
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
	 *             if AccountAddressAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theAccountAddressAssocComponentRule.PrevalidateAccountAddressAssocCompMerge(txnTransferObj);
			AccountAddressAssocDO reqAccountAddressAssocDO = (AccountAddressAssocDO) txnTransferObj.getTxnPayload()
					.getAccountAddressAssocDO();
			AccountAddressAssocDO dbimageAccountAddressAssocDO = entityManager.find(AccountAddressAssocDO.class,
					reqAccountAddressAssocDO.getIdPk());
			if (null == dbimageAccountAddressAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AccountAddressAssocComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqAccountAddressAssocDO);
			BeanUtils.copyProperties(reqAccountAddressAssocDO, dbimageAccountAddressAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageAccountAddressAssocDO);
			theAccountAddressAssocComponentRule.preExecuteAccountAddressAssocCompMerge(reqAccountAddressAssocDO,
					dbimageAccountAddressAssocDO, txnTransferObj);
			AccountAddressAssocDO mergedAccountAddressAssocDO = entityManager.merge(dbimageAccountAddressAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setAccountAddressAssocDO(new AccountAddressAssocDO(mergedAccountAddressAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAccountAddressAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAccountAddressAssocComponentRule.postExecuteAccountAddressAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in AccountAddressAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AccountAddressAssocComponent.persist failed - Unique Key Violated, record with accountIdPk - AddressTypeRefkey - AddressSubtypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AccountAddressAssocComponent.persist failed unexpectedly with PersistenceException"); // Transaction
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
					"AccountAddressAssocComponent.merge failed unexpectedly");
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
	 *             if AccountAddressAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theAccountAddressAssocComponentRule.prevalidateAccountAddressAssocCompFindById(txnTransferObj);
			AccountAddressAssocDO reqAccountAddressAssocDO = (AccountAddressAssocDO) txnTransferObj.getTxnPayload()
					.getAccountAddressAssocDO();
			AccountAddressAssocDO dbimageAccountAddressAssocDO = entityManager.find(AccountAddressAssocDO.class,
					reqAccountAddressAssocDO.getIdPk());
			if (null == dbimageAccountAddressAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AccountAddressAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setAccountAddressAssocDO(new AccountAddressAssocDO(dbimageAccountAddressAssocDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAccountAddressAssocDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAccountAddressAssocComponentRule.postExecuteAccountAddressAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountAddressAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting AccountAddressAssocDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountAddressAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountAddressAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAccountIdpk()
				|| txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAccountIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10014",
					"AccountAddressAssocDO.accountIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressIdpk()
				|| txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10017",
					"AccountAddressAssocDO.addressIdpk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressTypeRefkey()
				|| txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10008",
					"AccountAddressAssocDO.addressTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressSubtypeRefkey()
				|| txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressSubtypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10050",
					"AccountAddressAssocDO.addressSubtypeRefkey is needed in the request");
		}
		AccountAddressAssocDO theAccountAddressAssocDO = (AccountAddressAssocDO) txnTransferObj.getTxnPayload()
				.getAccountAddressAssocDO();
		theAccountAddressAssocDO.setCreatedTs(new Date());
		theAccountAddressAssocDO.setUpdatedTs(new Date());
		theAccountAddressAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAccountAddressAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating AccountAddressAssocDO
	 * object in the database. populate updatedTimestamp, transaction reference
	 * Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountAddressAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountAddressAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AccountAddressAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"AccountAddressAssocDO.version should not be null");
		}

		AccountAddressAssocDO theAccountAddressAssocDO = (AccountAddressAssocDO) txnTransferObj.getTxnPayload()
				.getAccountAddressAssocDO();
		theAccountAddressAssocDO.setUpdatedTs(new Date());
		theAccountAddressAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAccountAddressAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that AccountAddressAssocDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountAddressAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountAddressAssocDO is needed in the request");
		}
		AccountAddressAssocDO theAccountAddressAssocDO = (AccountAddressAssocDO) txnTransferObj.getTxnPayload()
				.getAccountAddressAssocDO();
		if (null == theAccountAddressAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AccountAddressAssocDO.idpk should not be null");
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
	 *             if AccountAddressAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByAccountIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByAccountIdPk(txnTransferObj);
			theAccountAddressAssocComponentRule.prevalidateAccountAddressAssocCompFindByAccountIdPk(txnTransferObj);
			AccountAddressAssocDO reqAccountAddressAssocDO = (AccountAddressAssocDO) txnTransferObj.getTxnPayload()
					.getAccountAddressAssocDO();

			Page<AccountAddressAssocDO> pageAccountAddressAssocDO = findByAccountIdPkFromRepository(
					reqAccountAddressAssocDO.getAccountIdpk(), reqAccountAddressAssocDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageAccountAddressAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageAccountAddressAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageAccountAddressAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageAccountAddressAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageAccountAddressAssocDO.getSize());

			// process pages if response have results
			if (pageAccountAddressAssocDO.getTotalPages() != 0) {
				if ((pageAccountAddressAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for AccountAddressAssocComponent.findByAccountIdPk, total number of pages is "
									+ pageAccountAddressAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<AccountAddressAssocDO> dbimageAccountAddressAssocDOlist = pageAccountAddressAssocDO.getContent();
				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity
				// manager level 1 (L1) cached object
				List<AccountAddressAssocDO> clonedAccountAddressAssocDOList = new ArrayList<AccountAddressAssocDO>();
				if (null != dbimageAccountAddressAssocDOlist && dbimageAccountAddressAssocDOlist.size() > 0) {
					Iterator<AccountAddressAssocDO> itr = dbimageAccountAddressAssocDOlist.iterator();
					while (itr.hasNext()) {
						AccountAddressAssocDO theAccountAddressAssocDO = new AccountAddressAssocDO(itr.next());
						clonedAccountAddressAssocDOList.add(theAccountAddressAssocDO);
					}
				}

				if (null != clonedAccountAddressAssocDOList && clonedAccountAddressAssocDOList.size() > 0) {
					Iterator<AccountAddressAssocDO> itr = clonedAccountAddressAssocDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setAccountAddressAssocDOList(clonedAccountAddressAssocDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAccountAddressAssocComponentRule.postExecuteAccountAddressAssocCompFindByAccountIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("AccountAddressAssocComponent.findByAccountIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountAddressAssocComponent.findByAccountIdPk failed unexpectedly");
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

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountAddressAssocDO is needed in the request");
		}
		AccountAddressAssocDO theAccountAddressAssocDO = (AccountAddressAssocDO) txnTransferObj.getTxnPayload()
				.getAccountAddressAssocDO();
		if (null == theAccountAddressAssocDO.getAccountIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"AccountAddressAssocDO.accountIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == theAccountAddressAssocDO.getInquiryFilter()
				|| theAccountAddressAssocDO.getInquiryFilter().isEmpty()) {
			theAccountAddressAssocDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, theAccountAddressAssocDO.getInquiryFilter());
		}

	}

	public Page<AccountAddressAssocDO> findByAccountIdPkFromRepository(String accountIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "accountIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<AccountAddressAssocDO> pageAccountAddressAssocDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageAccountAddressAssocDO = this.theAccountAddressAssocRepository.findAllActiveByAccountIdpk(accountIdPk,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageAccountAddressAssocDO = this.theAccountAddressAssocRepository.findAllInActiveByAccountIdpk(accountIdPk,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageAccountAddressAssocDO = this.theAccountAddressAssocRepository.findAllByAccountIdpk(accountIdPk,
					localPageable);

		}

		return pageAccountAddressAssocDO;

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage,
			AccountAddressAssocDO theAccountAddressAssocDO) {

		// addressTypeRefkey
		if (!(null == theAccountAddressAssocDO.getAddressTypeRefkey()
				|| theAccountAddressAssocDO.getAddressTypeRefkey().isEmpty())) {
			RefAddressTypeDO theRefAddressTypeDO = referenceTableHelper.getRefAddressTypeValueByKey(requesterLanguage,
					theAccountAddressAssocDO.getAddressTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressTypeDO) {
				theAccountAddressAssocDO.setAddressTypeRefValue(theRefAddressTypeDO.getValue());
			}
		}

		// AddressSubtypeRefkey
		if (!(null == theAccountAddressAssocDO.getAddressSubtypeRefkey()
				|| theAccountAddressAssocDO.getAddressSubtypeRefkey().isEmpty())) {
			RefAddressSubtypeDO theRefAddressSubtypeDO = referenceTableHelper.getRefAddressSubtypeValueByKey(
					requesterLanguage, theAccountAddressAssocDO.getAddressSubtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressSubtypeDO) {
				theAccountAddressAssocDO.setAddressSubtypeRefValue(theRefAddressSubtypeDO.getValue());
			}
		}

	}

}
