package com.yugandhar.mdm.composite.service;
/* Generated Jul 3, 2017 2:32:54 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.ArrayList;
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
import com.yugandhar.mdm.corecomponent.AccountComponent;
import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.AccountDO;
import com.yugandhar.mdm.extern.dobj.AccountPhoneAssocDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.CreateAccountService")
public class CreateAccountService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	
	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	AccountDO respAccountDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;
	
		@Autowired
		CommonValidationUtil commonValidationUtil;
		
		@Autowired
		AccountComponent AccountComponent;
		
		@Autowired
		CreateAccountAddressService createAccountAddressService;

		@Autowired
		CreateAccountPhoneService createAccountPhoneService;
		
		public CreateAccountService() {
			txnTransferObjResponse = new TxnTransferObj();
			respAccountDO = new AccountDO();
			respTxnTransferObj = new TxnTransferObj();
			respTxnPayload = new TxnPayload();
			transitTxnTransferObj=new TxnTransferObj();
		}
	
		@Transactional
		public TxnTransferObj process(TxnTransferObj txnTransferObj) throws YugandharCommonException {
			TxnTransferObj transitTxnTransferObj=new TxnTransferObj();
			TxnPayload transitTxnPayload = null;
			respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			
			try {
				//TODO logic here
				validateRequestAccountDO(txnTransferObj);
				AccountDO reqAccountDO= txnTransferObj.getTxnPayload().getAccountDO();
				AccountDO respAccountDO= null;
				
				//process Account Object
				transitTxnPayload= new TxnPayload();
				AccountDO accountDO = txnTransferObj.getTxnPayload().getAccountDO();
				transitTxnPayload.setAccountDO(accountDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = AccountComponent.persist(transitTxnTransferObj);
				respAccountDO= transitTxnTransferObj.getTxnPayload().getAccountDO();
				
				
				// Process Addresses
				if (null != reqAccountDO.getAccountAddressAssocDOList()) {

					Iterator<AccountAddressAssocDO> addrIterator = reqAccountDO.getAccountAddressAssocDOList().iterator();
					List<AccountAddressAssocDO> respAccountAddressAssocDOList = new ArrayList<AccountAddressAssocDO>();

					while (addrIterator.hasNext()) {
						AccountAddressAssocDO reqAccountAddressAssocDO = addrIterator.next();

						if (null != reqAccountAddressAssocDO) {
							transitTxnPayload = new TxnPayload();
							reqAccountAddressAssocDO.setAccountIdpk(respAccountDO.getIdPk());
							transitTxnPayload.setAccountAddressAssocDO(reqAccountAddressAssocDO);
							transitTxnTransferObj.setTxnPayload(transitTxnPayload);
							transitTxnTransferObj = createAccountAddressService.process(transitTxnTransferObj);
							respAccountAddressAssocDOList.add(transitTxnTransferObj.getTxnPayload().getAccountAddressAssocDO());

						}

					}
					respAccountDO.setAccountAddressAssocDOList(respAccountAddressAssocDOList);

				}

				// process Phone associations
				if (null != reqAccountDO.getAccountPhoneAssocDOList()) {

					Iterator<AccountPhoneAssocDO> addrIterator = reqAccountDO.getAccountPhoneAssocDOList().iterator();
					List<AccountPhoneAssocDO> respAccountPhoneAssocDOList = new ArrayList<AccountPhoneAssocDO>();

					while (addrIterator.hasNext()) {
						AccountPhoneAssocDO reqAccountPhoneAssocDO = addrIterator.next();

						if (null != reqAccountPhoneAssocDO) {
							transitTxnPayload = new TxnPayload();
							reqAccountPhoneAssocDO.setAccountIdpk(respAccountDO.getIdPk());
							transitTxnPayload.setAccountPhoneAssocDO(reqAccountPhoneAssocDO);
							transitTxnTransferObj.setTxnPayload(transitTxnPayload);
							transitTxnTransferObj = createAccountPhoneService.process(transitTxnTransferObj);
							respAccountPhoneAssocDOList.add(transitTxnTransferObj.getTxnPayload().getAccountPhoneAssocDO());

						}

					}
					respAccountDO.setAccountPhoneAssocDOList(respAccountPhoneAssocDOList);

				}
				//Final Response object
				respTxnPayload.setAccountDO(respAccountDO);
				respTxnTransferObj.setTxnPayload(respTxnPayload);
				
				
			} catch (YugandharCommonException yce) {
				logger.error("Composite Service failed", yce);
				throw yce;
			} catch (Exception e) {
				//write the logic to get error message based on error code. Error code is hard-coded here
				logger.error("Composite Service failed", e);
				e.printStackTrace();
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
						"CreateLeAccountService failed unexpectedly");

			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			return respTxnTransferObj;

		}

		private void validateRequestAccountDO(TxnTransferObj txnTransferObj) {
			transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			if (null == txnTransferObj.getTxnPayload().getAccountDO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
						"AccountDO is needed in the request");
			}
			
			
		}
}
