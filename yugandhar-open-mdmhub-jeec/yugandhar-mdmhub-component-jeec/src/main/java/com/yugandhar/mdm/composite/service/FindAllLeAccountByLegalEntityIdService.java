package com.yugandhar.mdm.composite.service;
/* Generated Jul 3, 2017 2:32:54 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Iterator;
import java.util.List;
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
@Service("com.yugandhar.mdm.composite.service.FindAllLeAccountByLegalEntityIdService")
public class FindAllLeAccountByLegalEntityIdService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeAccountAssocComponent LeAccountAssocComponent;

	@Autowired
	LegalentityComponent legalentityComponent;

	@Autowired
	RetrieveAccountByAccountIdService retrieveAccountService;

	public FindAllLeAccountByLegalEntityIdService() {
		txnTransferObjResponse = new TxnTransferObj();
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

			// retrieve LeAccountAssocDO by LegalEntityIdPk
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLeAccountAssocDO(reqLeAccountAssocDO);
			//copy pagination properties from request object
			commonValidationUtil.copyPaginationProperties(txnTransferObj.getTxnPayload(), transitTxnPayload);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = LeAccountAssocComponent.findByLegalEntityIdPk(transitTxnTransferObj);
			List<LeAccountAssocDO> respLeAccountAssocDOList = transitTxnTransferObj.getTxnPayload().getLeAccountAssocDOList();

			//copy pagination properties to the response object
			commonValidationUtil.copyPaginationProperties(transitTxnTransferObj.getTxnPayload(), respTxnPayload);
			// Process Accounts
			if (null != respLeAccountAssocDOList) {

				Iterator<LeAccountAssocDO> respLeAccountAssocDOListIterator = respLeAccountAssocDOList.iterator();
				
				while (respLeAccountAssocDOListIterator.hasNext()) {
					LeAccountAssocDO respLeAccountAssocDO = respLeAccountAssocDOListIterator.next();
					transitTxnPayload = new TxnPayload();
					AccountDO accountDO = new AccountDO();
					accountDO.setIdPk(respLeAccountAssocDO.getAccountIdpk());
					accountDO.setInquiryLevel(reqLeAccountAssocDO.getAccountInquiryLevel());
					transitTxnPayload.setAccountDO(accountDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = retrieveAccountService.process(transitTxnTransferObj);
					AccountDO theaccountDO = new AccountDO();
					theaccountDO = transitTxnTransferObj.getTxnPayload().getAccountDO();
					//respLeAccountAssocDO.setAccountDO(transitTxnTransferObj.getTxnPayload().getAccountDO());
					respLeAccountAssocDO.setAccountDO(theaccountDO);
					
				}
			}

			// retrieve Account

			// Final Response object
			respTxnPayload.setLeAccountAssocDOList(respLeAccountAssocDOList);
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

		if (null == txnTransferObj.getTxnPayload().getLeAccountAssocDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeAccountAssocDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeAccountAssocDO.LegalEntityIdPk is needed in the request");
		}

	}
}
