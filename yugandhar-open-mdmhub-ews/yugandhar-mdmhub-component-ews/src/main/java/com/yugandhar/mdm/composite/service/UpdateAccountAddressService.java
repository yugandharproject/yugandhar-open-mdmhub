package com.yugandhar.mdm.composite.service;
/* Generated Jul 3, 2017 2:32:54 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yugandhar.common.constant.yugandharConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.extern.transferobj.TxnPayload;
import com.yugandhar.mdm.corecomponent.AddressComponent;
import com.yugandhar.mdm.corecomponent.AccountAddressAssocComponent;
import com.yugandhar.mdm.corecomponent.AccountComponent;
import com.yugandhar.mdm.extern.dobj.AddressDO;
import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.AccountDO;


@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.UpdateAccountAddressService")
public class UpdateAccountAddressService {

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
		AccountComponent accountComponent;
		
		@Autowired
		AddressComponent addressComponent;
		
		
		public UpdateAccountAddressService() {
			txnTransferObjResponse = new TxnTransferObj();
			respAccountAddressAssocDO = new AccountAddressAssocDO();
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
			AddressDO respAddressDO= null;
			
			try {
				//TODO logic here
				validateRequestAccountAddressAssocDO(txnTransferObj);
				AccountAddressAssocDO reqAccountAddressAssocDO= txnTransferObj.getTxnPayload().getAccountAddressAssocDO();
				
				
				// if address Id is null then create address record and assing the idpk in reqAccountAddressAssocDO
				if(null!= txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressDO() ) {
				if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressIdpk() || txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressIdpk().isEmpty()){
					
					transitTxnPayload= new TxnPayload();
					AddressDO addressDO = txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressDO();
					transitTxnPayload.setAddressDO(addressDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = addressComponent.persist(transitTxnTransferObj);
					respAddressDO= transitTxnTransferObj.getTxnPayload().getAddressDO();
					reqAccountAddressAssocDO.setAddressIdpk(respAddressDO.getIdPk());
				} else {
					
					transitTxnPayload= new TxnPayload();
					AddressDO addressDO = txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressDO();
					transitTxnPayload.setAddressDO(addressDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = addressComponent.merge(transitTxnTransferObj);
					respAddressDO =transitTxnTransferObj.getTxnPayload().getAddressDO();
					reqAccountAddressAssocDO.setAddressIdpk(respAddressDO.getIdPk());
				}
				}
				//persist base association
				transitTxnPayload= new TxnPayload();
				transitTxnPayload.setAccountAddressAssocDO(reqAccountAddressAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = accountAddressAssocComponent.merge(transitTxnTransferObj);
				respAccountAddressAssocDO=transitTxnTransferObj.getTxnPayload().getAccountAddressAssocDO();
				respAccountAddressAssocDO.setAddressDO(respAddressDO);
				
				
				//Final Response object
				respTxnPayload.setAccountAddressAssocDO(respAccountAddressAssocDO);
				respTxnTransferObj.setTxnPayload(respTxnPayload);
				
				
			} catch (YugandharCommonException yce) {
				logger.error("Composite Service failed", yce);
				throw yce;
			} catch (Exception e) {
				//write the logic to get error message based on error code. Error code is hard-coded here
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
			AccountAddressAssocDO reqAccountAddressAssocDO= txnTransferObj.getTxnPayload().getAccountAddressAssocDO();
			
			
			if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
						"AccountAddressAssocDO is needed in the request");
			}
			
			if(null == reqAccountAddressAssocDO.getIdPk()){
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10062",
						"AccountAddressAssocDO.idPk is needed in the request");
			}
			
			if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAccountIdpk() || 
					txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAccountIdpk().isEmpty()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
						"AccountAddressAssocDO.AccountgalEntityIdPk is needed in the request");
			} else {
				//search the LE by Id, if success response means validated else throw error
				try {
				transitTxnPayload= new TxnPayload();
				AccountDO accountDO = new AccountDO();
				accountDO.setIdPk(reqAccountAddressAssocDO.getAccountIdpk());
				transitTxnPayload.setAccountDO(accountDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = accountComponent.findById(transitTxnTransferObj);
				} catch (YugandharCommonException yce) {
					throw yce;
				} catch (RuntimeException re) {
					logger.error("validateRequestAccountAddressAssocDO failed", re);
					re.printStackTrace();
					throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
							"CreateAccountAddressService.validateRequestAccountAddressAssocDO failed unexpectedly"); 
					//Transaction Failed due to unknown error, please check stack trace
				}
				
			}
			
			// if address Id is null then check if the addressDO is present
			if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressIdpk()){
				
				if (null == txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressDO()){
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10036",
							"Cannot Create association without AddressDO or AddressIdPk of existing address");
					
				} 
				
			} else if(null != reqAccountAddressAssocDO.getAddressDO() && 
					!(reqAccountAddressAssocDO.getAddressIdpk().equals(txnTransferObj.getTxnPayload().getAccountAddressAssocDO().getAddressDO().getIdPk()))){
				
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10061",
						"AddressDO.idpk and accountAddressAssocDO.addressIdpk does not match");
			} else {
				//search the address baesd on addressIdPk, if success response means validated else throw error
				try {
				transitTxnPayload= new TxnPayload();
				AddressDO addressDO = new AddressDO();
				addressDO.setIdPk(reqAccountAddressAssocDO.getAddressIdpk());
				transitTxnPayload.setAddressDO(addressDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = addressComponent.findById(transitTxnTransferObj);
				} catch (YugandharCommonException yce) {
					throw yce;
				} catch (RuntimeException re) {
					logger.error("validateRequestAccountAddressAssocDO failed", re);
					re.printStackTrace();
					throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
							"CreateAccountAddressService.validateRequestAccountAddressAssocDO failed unexpectedly"); 
					//Transaction Failed due to unknown error, please check stack trace
				}
			}
			
			
			
		}
}
