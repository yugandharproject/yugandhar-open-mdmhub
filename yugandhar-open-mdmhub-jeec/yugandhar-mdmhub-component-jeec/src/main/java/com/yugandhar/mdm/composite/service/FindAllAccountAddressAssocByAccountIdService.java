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
import com.yugandhar.mdm.corecomponent.AddressComponent;
import com.yugandhar.mdm.corecomponent.AccountAddressAssocComponent;
import com.yugandhar.mdm.corecomponent.AccountComponent;
import com.yugandhar.mdm.extern.dobj.AddressDO;
import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.FindAllAccountAddressAssocByAccountIdService")
public class FindAllAccountAddressAssocByAccountIdService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	List<AccountAddressAssocDO> respAccountAddressAssocDOList;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AccountAddressAssocComponent accountAddressAssocComponent;

	@Autowired
	AccountComponent accountComponent;

	@Autowired
	AddressComponent addressComponent;

	public FindAllAccountAddressAssocByAccountIdService() {
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

			validateRequestAccountAddressAssocDO(txnTransferObj);
			AccountAddressAssocDO reqAccountAddressAssocDO = txnTransferObj.getTxnPayload().getAccountAddressAssocDO();
			
			// retrieve base association
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setAccountAddressAssocDO(reqAccountAddressAssocDO);
			//copy pagination properties from request object
			commonValidationUtil.copyPaginationProperties(txnTransferObj.getTxnPayload(), transitTxnPayload);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = accountAddressAssocComponent.findByAccountIdPk(transitTxnTransferObj);
			respAccountAddressAssocDOList = transitTxnTransferObj.getTxnPayload().getAccountAddressAssocDOList();

			//copy pagination properties to the response object
			commonValidationUtil.copyPaginationProperties(transitTxnTransferObj.getTxnPayload(), respTxnPayload);
			// Process Addresses
			if (null != respAccountAddressAssocDOList) {

				Iterator<AccountAddressAssocDO> addrIterator = respAccountAddressAssocDOList.iterator();
				
				while (addrIterator.hasNext()) {
					AccountAddressAssocDO respAccountAddressAssocDO = addrIterator.next();
					transitTxnPayload = new TxnPayload();
					AddressDO addressDO = new AddressDO();
					addressDO.setIdPk(respAccountAddressAssocDO.getAddressIdpk());
					transitTxnPayload.setAddressDO(addressDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = addressComponent.findById(transitTxnTransferObj);
					respAccountAddressAssocDO.setAddressDO(transitTxnTransferObj.getTxnPayload().getAddressDO());
					
				}
			}

			// retrieve Address
			

			// Final Response object
			respTxnPayload.setAccountAddressAssocDOList(respAccountAddressAssocDOList);
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

		if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAccountIdpk()
				|| txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAccountIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"AccountAddressAssocDO.AccountIdPk is needed in the request");
		}

	}
}
