package com.yugandhar.mdm.composite.service;
/* Generated Jul 3, 2017 2:32:54 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.extern.transferobj.TxnPayload;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.corecomponent.AccountComponent;
import com.yugandhar.mdm.corecomponent.LeAccountAssocComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.AccountDO;
import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.UpdateLeAccountService")
public class UpdateLeAccountService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	LeAccountAssocDO respLeAccountAssocDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeAccountAssocComponent leAccountAssocComponent;

	@Autowired
	LegalentityComponent legalentityComponent;

	@Autowired
	AccountComponent accountComponent;

	@Autowired
	CreateAccountService createAccountService;

	@Autowired
	UpdateAccountService updateAccountService;

	public UpdateLeAccountService() {
		txnTransferObjResponse = new TxnTransferObj();
		respLeAccountAssocDO = new LeAccountAssocDO();
		respTxnTransferObj = new TxnTransferObj();
		respTxnPayload = new TxnPayload();
		transitTxnTransferObj = new TxnTransferObj();
	}

	@Transactional
	public TxnTransferObj process(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		TxnPayload transitTxnPayload = null;
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		AccountDO respAccountDO = null;

		try {
			// TODO logic here
			validateRequestLeAccountAssocDO(txnTransferObj);
			LeAccountAssocDO reqLeAccountAssocDO = txnTransferObj.getTxnPayload().getLeAccountAssocDO();

			// if Account Id is null then create Account record and assign the
			// idpk in reqLeAccountAssocDO
			// else update the existing account record
			if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getAccountIdpk()) {

				transitTxnPayload = new TxnPayload();
				AccountDO AccountDO = txnTransferObj.getTxnPayload().getLeAccountAssocDO().getAccountDO();
				transitTxnPayload.setAccountDO(AccountDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = createAccountService.process(transitTxnTransferObj);
				respAccountDO = transitTxnTransferObj.getTxnPayload().getAccountDO();
				reqLeAccountAssocDO.setAccountIdpk(respAccountDO.getIdPk());
			} else {

				transitTxnPayload = new TxnPayload();
				AccountDO AccountDO = txnTransferObj.getTxnPayload().getLeAccountAssocDO().getAccountDO();
				transitTxnPayload.setAccountDO(AccountDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = updateAccountService.process(transitTxnTransferObj);
				respAccountDO = transitTxnTransferObj.getTxnPayload().getAccountDO();
				reqLeAccountAssocDO.setAccountIdpk(respAccountDO.getIdPk());
			}

			// persist base association
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLeAccountAssocDO(reqLeAccountAssocDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = leAccountAssocComponent.merge(transitTxnTransferObj);
			respLeAccountAssocDO = transitTxnTransferObj.getTxnPayload().getLeAccountAssocDO();
			respLeAccountAssocDO.setAccountDO(respAccountDO);

			// Final Response object
			respTxnPayload.setLeAccountAssocDO(respLeAccountAssocDO);
			respTxnTransferObj.setTxnPayload(respTxnPayload);

		} catch (YugandharCommonException yce) {
			logger.error("Composite Service failed", yce);
			throw yce;
		} catch (Exception e) {
			// write the logic to get error message based on error code. Error
			// code is hard-coded here
			logger.error("Composite Service failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
					"CreateLeAccountService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

	}

	private void validateRequestLeAccountAssocDO(TxnTransferObj txnTransferObj) {
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		LeAccountAssocDO reqLeAccountAssocDO = txnTransferObj.getTxnPayload().getLeAccountAssocDO();

		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAccountAssocDO is needed in the request");
		}

		if (null == reqLeAccountAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10033",
					"LeAccountAssocDO.idPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeAccountAssocDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeAccountAssocDO.LegalEntityIdPk is needed in the request");
		} else {
			// search the LE by Id, if success response means validated else
			// throw error
			try {
				transitTxnPayload = new TxnPayload();
				LegalentityDO legalentityDO = new LegalentityDO();
				legalentityDO.setIdPk(reqLeAccountAssocDO.getLegalentityIdpk());
				transitTxnPayload.setLegalentityDO(legalentityDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = legalentityComponent.findById(transitTxnTransferObj);
			} catch (YugandharCommonException yce) {
				throw yce;
			} catch (RuntimeException re) {
				logger.error("validateRequestLeAccountAssocDO failed", re);
				re.printStackTrace();
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
						"CreateLeAccountService.validateRequestLeAccountAssocDO failed unexpectedly"); // Transaction
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

		}

		// if Account Id is null then check if the AccountDO is present
		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getAccountIdpk()) {

			if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getAccountDO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10053",
						"Cannot Create association without AccountDO or AccountIdPk of existing Account");

			}

		} else if (!(reqLeAccountAssocDO.getAccountIdpk()
				.equals(txnTransferObj.getTxnPayload().getLeAccountAssocDO().getAccountDO().getIdPk()))) {

			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10056",
					"AccountDO.idpk and leAccountAssocDO.AccountIdpk does not match");
		} else {
			// search the Account baesd on AccountIdPk, if success response
			// means validated else throw error
			try {
				transitTxnPayload = new TxnPayload();
				AccountDO AccountDO = new AccountDO();
				AccountDO.setIdPk(reqLeAccountAssocDO.getAccountIdpk());
				transitTxnPayload.setAccountDO(AccountDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = accountComponent.findById(transitTxnTransferObj);
			} catch (YugandharCommonException yce) {
				throw yce;
			} catch (RuntimeException re) {
				logger.error("validateRequestLeAccountAssocDO failed", re);
				re.printStackTrace();
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
						"CreateLeAccountService.validateRequestLeAccountAssocDO failed unexpectedly"); // Transaction
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
		}

	}
}
