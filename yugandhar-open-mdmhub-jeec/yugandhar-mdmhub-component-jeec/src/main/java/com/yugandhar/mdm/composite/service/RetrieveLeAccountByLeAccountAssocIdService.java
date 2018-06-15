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
import com.yugandhar.mdm.corecomponent.LeAccountAssocComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.AccountDO;
import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.RetrieveLeAccountByLeAccountAssocIdService")
public class RetrieveLeAccountByLeAccountAssocIdService {

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
	RetrieveAccountByAccountIdService retrieveAccountService;

	public RetrieveLeAccountByLeAccountAssocIdService() {
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

		try {

			validateRequestLeAccountAssocDO(txnTransferObj);
			LeAccountAssocDO reqLeAccountAssocDO = txnTransferObj.getTxnPayload().getLeAccountAssocDO();

			// retrieve base association
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLeAccountAssocDO(reqLeAccountAssocDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = leAccountAssocComponent.findById(transitTxnTransferObj);
			respLeAccountAssocDO = transitTxnTransferObj.getTxnPayload().getLeAccountAssocDO();

			// retrieve Account
			transitTxnPayload = new TxnPayload();
			AccountDO AccountDO = new AccountDO();
			AccountDO.setIdPk(respLeAccountAssocDO.getAccountIdpk());
			AccountDO.setInquiryLevel(reqLeAccountAssocDO.getAccountInquiryLevel());
			transitTxnPayload.setAccountDO(AccountDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = retrieveAccountService.process(transitTxnTransferObj);
			respLeAccountAssocDO.setAccountDO(transitTxnTransferObj.getTxnPayload().getAccountDO());

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
		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAccountAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLeAccountAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10033",
					"LeAccountAssocDO.IdPk is needed in the request");
		}

	}
}
