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
import com.yugandhar.mdm.corecomponent.AccountAddressAssocComponent;
import com.yugandhar.mdm.corecomponent.AddressComponent;
import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.AddressDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.RetrieveAccountAddressByAccountAddressAssocIdService")
public class RetrieveAccountAddressByAccountAddressAssocIdService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	AccountAddressAssocDO respAccountAddressAssocDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AccountAddressAssocComponent accountAddressAssocComponent;

	@Autowired
	AddressComponent addressComponent;

	public RetrieveAccountAddressByAccountAddressAssocIdService() {
		txnTransferObjResponse = new TxnTransferObj();
		respAccountAddressAssocDO = new AccountAddressAssocDO();
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

			validateRequestAccountAddressAssocDO(txnTransferObj);
			AccountAddressAssocDO reqAccountAddressAssocDO = txnTransferObj.getTxnPayload().getAccountAddressAssocDO();

			// retrieve base association
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setAccountAddressAssocDO(reqAccountAddressAssocDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = accountAddressAssocComponent.findById(transitTxnTransferObj);
			respAccountAddressAssocDO = transitTxnTransferObj.getTxnPayload().getAccountAddressAssocDO();

			// retrieve Address
			transitTxnPayload = new TxnPayload();
			AddressDO addressDO = new AddressDO();
			addressDO.setIdPk(respAccountAddressAssocDO.getAddressIdpk());
			transitTxnPayload.setAddressDO(addressDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = addressComponent.findById(transitTxnTransferObj);
			respAccountAddressAssocDO.setAddressDO(transitTxnTransferObj.getTxnPayload().getAddressDO());

			// Final Response object
			respTxnPayload.setAccountAddressAssocDO(respAccountAddressAssocDO);
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
					"CreateAccountAddressService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

	}

	private void validateRequestAccountAddressAssocDO(TxnTransferObj txnTransferObj) {
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AccountAddressAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10059",
					"AccountAddressAssocDO.IdPk is needed in the request");
		}

	}
}
