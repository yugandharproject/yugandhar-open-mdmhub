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
import com.yugandhar.mdm.corecomponent.LeAddressAssocComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.AddressDO;
import com.yugandhar.mdm.extern.dobj.LeAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.CreateLeAddressService")
public class CreateLeAddressService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	
	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	LeAddressAssocDO respLeAddressAssocDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;
	
		@Autowired
		CommonValidationUtil commonValidationUtil;
		
		@Autowired
		LeAddressAssocComponent leAddressAssocComponent;
		
		@Autowired
		LegalentityComponent legalentityComponent;
		
		@Autowired
		AddressComponent addressComponent;
		
		
		public CreateLeAddressService() {
			txnTransferObjResponse = new TxnTransferObj();
			respLeAddressAssocDO = new LeAddressAssocDO();
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
				validateRequestLeAddressAssocDO(txnTransferObj);
				LeAddressAssocDO reqLeAddressAssocDO= txnTransferObj.getTxnPayload().getLeAddressAssocDO();
				AddressDO respAddressDO= null;
				
				// if address Id is null then process address Object
				if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressIdpk()){
					
					transitTxnPayload= new TxnPayload();
					AddressDO addressDO = txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressDO();
					transitTxnPayload.setAddressDO(addressDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = addressComponent.persist(transitTxnTransferObj);
					respAddressDO= transitTxnTransferObj.getTxnPayload().getAddressDO();
					
					
				}
				
				reqLeAddressAssocDO.setAddressIdpk(respAddressDO.getIdPk());
				//persist base association
				transitTxnPayload= new TxnPayload();
				transitTxnPayload.setLeAddressAssocDO(reqLeAddressAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = leAddressAssocComponent.persist(transitTxnTransferObj);
				respLeAddressAssocDO=transitTxnTransferObj.getTxnPayload().getLeAddressAssocDO();
				respLeAddressAssocDO.setAddressDO(respAddressDO);
				
				
				
				//Final Response object
				respTxnPayload.setLeAddressAssocDO(respLeAddressAssocDO);
				respTxnTransferObj.setTxnPayload(respTxnPayload);
				
				
			} catch (YugandharCommonException yce) {
				logger.error("Composite Service failed", yce);
				throw yce;
			} catch (Exception e) {
				//write the logic to get error message based on error code. Error code is hard-coded here
				logger.error("Composite Service failed", e);
				e.printStackTrace();
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
						"CreateLeAddressService failed unexpectedly");

			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			return respTxnTransferObj;

		}

		private void validateRequestLeAddressAssocDO(TxnTransferObj txnTransferObj) {
			transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			LeAddressAssocDO reqLeAddressAssocDO= txnTransferObj.getTxnPayload().getLeAddressAssocDO();
			if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
						"LeAddressAssocDO is needed in the request");
			}
			
			if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getLegalentityIdpk() || 
					txnTransferObj.getTxnPayload().getLeAddressAssocDO().getLegalentityIdpk().isEmpty()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
						"leAddressAssocDO.legalEntityIdPk is needed in the request");
			} else {
				//search the LE by Id, if success response means validated else throw error
				try {
				transitTxnPayload= new TxnPayload();
				LegalentityDO legalentityDO = new LegalentityDO();
				legalentityDO.setIdPk(reqLeAddressAssocDO.getLegalentityIdpk());
				transitTxnPayload.setLegalentityDO(legalentityDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = legalentityComponent.findById(transitTxnTransferObj);
				} catch (YugandharCommonException yce) {
					throw yce;
				} catch (RuntimeException re) {
					logger.error("validateRequestLeAddressAssocDO failed", re);
					re.printStackTrace();
					throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
							"CreateLeAddressService.validateRequestLeAddressAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
				}
				
			}
			
			// if address Id is null then check if the addressDO is present
			if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressIdpk()){
				
				if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getAddressDO()){
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10036",
							"Cannot Create association without AddressDO or AddressIdPk of existing address");
					
				}
				
			} else {
				//search the address baesd on addressIdPk, if success response means validated else throw error
				try {
				transitTxnPayload= new TxnPayload();
				AddressDO addressDO = new AddressDO();
				addressDO.setIdPk(reqLeAddressAssocDO.getAddressIdpk());
				transitTxnPayload.setAddressDO(addressDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = addressComponent.findById(transitTxnTransferObj);
				} catch (YugandharCommonException yce) {
					throw yce;
				} catch (RuntimeException re) {
					logger.error("validateRequestLeAddressAssocDO failed", re);
					re.printStackTrace();
					throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
							"CreateLeAddressService.validateRequestLeAddressAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
				}
			}
			
			
			
		}
}
