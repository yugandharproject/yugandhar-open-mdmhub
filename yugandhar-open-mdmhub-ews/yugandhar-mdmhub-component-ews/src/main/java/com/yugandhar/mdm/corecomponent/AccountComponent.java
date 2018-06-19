package com.yugandhar.mdm.corecomponent;
/* Generated Jul 1, 2017 2:56:24 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.IgnoreAttributesUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.AccountDO;
import com.yugandhar.mdm.extern.dobj.RefAccountMdmStatusDO;
import com.yugandhar.mdm.extern.dobj.RefAccountSourceStatusDO;
import com.yugandhar.mdm.extern.dobj.RefBillingModeTypeDO;
import com.yugandhar.mdm.extern.dobj.RefBranchCodeDO;
import com.yugandhar.mdm.extern.dobj.RefCurrencyDO;
import com.yugandhar.mdm.extern.dobj.RefLanguageCodeDO;
import com.yugandhar.mdm.extern.dobj.RefLobtypeDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.extern.dobj.RefTerminationReasonDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model AccountDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class AccountComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AccountComponentRule theAccountComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public AccountComponent() {
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
	 *             if AccountDO object is not present in the request or other
	 *             mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theAccountComponentRule.prevalidateAccountCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			AccountDO reqAccountDO = (AccountDO) txnTransferObj.getTxnPayload().getAccountDO();
			if (null == reqAccountDO.getPrimaryKeyDO() || null == reqAccountDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqAccountDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqAccountDO.setIdPk(reqAccountDO.getPrimaryKeyDO().getIdPk());
				AccountDO dbimageAccountDO = entityManager.find(AccountDO.class, reqAccountDO.getIdPk());
				if (null != dbimageAccountDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"AccountComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theAccountComponentRule.preExecuteAccountCompPersist(reqAccountDO, txnTransferObj);
			entityManager.persist(reqAccountDO);
			entityManager.flush();
			reqAccountDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setAccountDO(new AccountDO(reqAccountDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAccountDO());
			theAccountComponentRule.postExecuteAccountCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AccountComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AccountComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountComponent.persist failed unexpectedly");
			// Transaction Failed due to unknown error,please check statck trace
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
	 *             if AccountDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theAccountComponentRule.PrevalidateAccountCompMerge(txnTransferObj);
			AccountDO reqAccountDO = (AccountDO) txnTransferObj.getTxnPayload().getAccountDO();
			AccountDO dbimageAccountDO = entityManager.find(AccountDO.class, reqAccountDO.getIdPk());
			if (null == dbimageAccountDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AccountComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqAccountDO);
			BeanUtils.copyProperties(reqAccountDO, dbimageAccountDO, strIgnoreProperties);
			entityManager.detach(dbimageAccountDO);
			theAccountComponentRule.preExecuteAccountCompMerge(reqAccountDO, dbimageAccountDO, txnTransferObj);
			AccountDO mergedAccountDO = entityManager.merge(dbimageAccountDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setAccountDO(new AccountDO(mergedAccountDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAccountDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAccountComponentRule.postExecuteAccountCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in AccountComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AccountComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AccountComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountComponent.merge failed unexpectedly");
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
	 *             if AccountDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theAccountComponentRule.prevalidateAccountCompFindById(txnTransferObj);
			AccountDO reqAccountDO = (AccountDO) txnTransferObj.getTxnPayload().getAccountDO();
			AccountDO dbimageAccountDO = entityManager.find(AccountDO.class, reqAccountDO.getIdPk());
			if (null == dbimageAccountDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AccountComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setAccountDO(new AccountDO(dbimageAccountDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAccountDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAccountComponentRule.postExecuteAccountCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AccountComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting AccountDO object in the
	 * database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAccountDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountDO().getAccountName()
				|| txnTransferObj.getTxnPayload().getAccountDO().getAccountName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10015",
					"AccountDO.accountName is needed in the request");
		}

		if (!(null == txnTransferObj.getTxnPayload().getAccountDO().getSourceAccountId()
				|| txnTransferObj.getTxnPayload().getAccountDO().getSourceAccountId().isEmpty())) {

			if (null == txnTransferObj.getTxnPayload().getAccountDO().getSourceSystemRefkey()
					|| txnTransferObj.getTxnPayload().getAccountDO().getSourceSystemRefkey().isEmpty()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10071",
						"accountDO.sourceSystemRefkey is needed in the request if sourceAccountId is provided");
			}

		}
		
		if (!(null == txnTransferObj.getTxnPayload().getAccountDO().getSourceSystemRefkey()
				|| txnTransferObj.getTxnPayload().getAccountDO().getSourceSystemRefkey().isEmpty())) {

			if (null == txnTransferObj.getTxnPayload().getAccountDO().getSourceAccountId()
					|| txnTransferObj.getTxnPayload().getAccountDO().getSourceAccountId().isEmpty()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10072",
						"accountDO.sourceAccountId is needed in the request if sourceSystemRefkey is provided");
			}

		}
		

		AccountDO theAccountDO = (AccountDO) txnTransferObj.getTxnPayload().getAccountDO();
		theAccountDO.setCreatedTs(new Date());
		theAccountDO.setUpdatedTs(new Date());
		theAccountDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAccountDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating AccountDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAccountDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getAccountDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAccountDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AccountDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"AccountDO.version should not be null");
		}

		AccountDO theAccountDO = (AccountDO) txnTransferObj.getTxnPayload().getAccountDO();
		theAccountDO.setUpdatedTs(new Date());
		theAccountDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAccountDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that AccountDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAccountDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountDO is needed in the request");
		}
		AccountDO theAccountDO = (AccountDO) txnTransferObj.getTxnPayload().getAccountDO();
		if (null == theAccountDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AccountDO.idpk should not be null");
		}

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, AccountDO theAccountDO) {

		// ContractSignedLangRefkey
		if (!(null == theAccountDO.getContractSignedLangRefkey()
				|| theAccountDO.getContractSignedLangRefkey().isEmpty())) {
			RefLanguageCodeDO theRefLanguageCodeDO = referenceTableHelper.getRefLanguageCodeValueByKey(
					requesterLanguage, theAccountDO.getContractSignedLangRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLanguageCodeDO) {
				theAccountDO.setContractSignedLangRefValue(theRefLanguageCodeDO.getValue());
			}
		}

		// CurrencyRefkey
		if (!(null == theAccountDO.getCurrencyRefkey() || theAccountDO.getCurrencyRefkey().isEmpty())) {
			RefCurrencyDO theRefCurrencyDO = referenceTableHelper.getRefCurrencyValueByKey(requesterLanguage,
					theAccountDO.getCurrencyRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCurrencyDO) {
				theAccountDO.setCurrencyRefValue(theRefCurrencyDO.getValue());
			}
		}

		// BillingModeTypeRefkey
		if (!(null == theAccountDO.getBillingModeTypeRefkey() || theAccountDO.getBillingModeTypeRefkey().isEmpty())) {
			RefBillingModeTypeDO theRefBillingModeTypeDO = referenceTableHelper.getRefBillingModeTypeValueByKey(
					requesterLanguage, theAccountDO.getBillingModeTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBillingModeTypeDO) {
				theAccountDO.setBillingModeTypeRefValue(theRefBillingModeTypeDO.getValue());
			}
		}

		// LobtypeRefkey
		if (!(null == theAccountDO.getLobtypeRefkey() || theAccountDO.getLobtypeRefkey().isEmpty())) {
			RefLobtypeDO theRefLobtypeDO = referenceTableHelper.getRefLobtypeValueByKey(requesterLanguage,
					theAccountDO.getLobtypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLobtypeDO) {
				theAccountDO.setLobtypeRefValue(theRefLobtypeDO.getValue());
			}
		}

		// BranchCodeRefkey
		if (!(null == theAccountDO.getBranchCodeRefkey() || theAccountDO.getBranchCodeRefkey().isEmpty())) {
			RefBranchCodeDO theRefBranchCodeDO = referenceTableHelper.getRefBranchCodeValueByKey(requesterLanguage,
					theAccountDO.getBranchCodeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefBranchCodeDO) {
				theAccountDO.setBranchCodeRefValue(theRefBranchCodeDO.getValue());
			}
		}

		// AccountSourceStatusRefkey
		if (!(null == theAccountDO.getAccountSourceStatusRefkey()
				|| theAccountDO.getAccountSourceStatusRefkey().isEmpty())) {
			RefAccountSourceStatusDO theRefAccountSourceStatusDO = referenceTableHelper
					.getRefAccountSourceStatusValueByKey(requesterLanguage, theAccountDO.getAccountSourceStatusRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAccountSourceStatusDO) {
				theAccountDO.setAccountSourceStatusRefValue(theRefAccountSourceStatusDO.getValue());
			}
		}

		// AccountMdmStatusRefkey
		if (!(null == theAccountDO.getAccountMdmStatusRefkey() || theAccountDO.getAccountMdmStatusRefkey().isEmpty())) {
			RefAccountMdmStatusDO theRefAccountMdmStatusDO = referenceTableHelper.getRefAccountMdmStatusValueByKey(
					requesterLanguage, theAccountDO.getAccountMdmStatusRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAccountMdmStatusDO) {
				theAccountDO.setAccountMdmStatusRefValue(theRefAccountMdmStatusDO.getValue());
			}
		}

		// TerminationReasonRefkey
		if (!(null == theAccountDO.getTerminationReasonRefkey()
				|| theAccountDO.getTerminationReasonRefkey().isEmpty())) {
			RefTerminationReasonDO theRefTerminationReasonDO = referenceTableHelper.getRefTerminationReasonValueByKey(
					requesterLanguage, theAccountDO.getTerminationReasonRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefTerminationReasonDO) {
				theAccountDO.setTerminationReasonRefValue(theRefTerminationReasonDO.getValue());
			}
		}

		// SourceSystemRefkey
		if (!(null == theAccountDO.getSourceSystemRefkey() || theAccountDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					requesterLanguage, theAccountDO.getSourceSystemRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {
				theAccountDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
			}
		}

	}

}
