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
import com.yugandhar.mdm.corecomponent.VehicleComponent;
import com.yugandhar.mdm.corecomponent.LeVehicleAssocComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.VehicleDO;
import com.yugandhar.mdm.extern.dobj.LeVehicleAssocDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.UpdateLeVehicleService")
public class UpdateLeVehicleService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	
	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	LeVehicleAssocDO respLeVehicleAssocDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;
	
		@Autowired
		CommonValidationUtil commonValidationUtil;
		
		@Autowired
		LeVehicleAssocComponent leVehicleAssocComponent;
		
		@Autowired
		LegalentityComponent legalentityComponent;
		
		@Autowired
		VehicleComponent VehicleComponent;
		
		
		public UpdateLeVehicleService() {
			txnTransferObjResponse = new TxnTransferObj();
			respLeVehicleAssocDO = new LeVehicleAssocDO();
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
			VehicleDO respVehicleDO= null;
			
			try {
				//TODO logic here
				validateRequestLeVehicleAssocDO(txnTransferObj);
				LeVehicleAssocDO reqLeVehicleAssocDO= txnTransferObj.getTxnPayload().getLeVehicleAssocDO();
				
				
				// if Vehicle Id is null then create Vehicle record and assing the idpk in reqLeVehicleAssocDO
				if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getVehicleIdpk()){
					
					transitTxnPayload= new TxnPayload();
					VehicleDO VehicleDO = txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getVehicleDO();
					transitTxnPayload.setVehicleDO(VehicleDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = VehicleComponent.persist(transitTxnTransferObj);
					respVehicleDO= transitTxnTransferObj.getTxnPayload().getVehicleDO();
					reqLeVehicleAssocDO.setVehicleIdpk(respVehicleDO.getIdPk());
				} else {
					
					transitTxnPayload= new TxnPayload();
					VehicleDO VehicleDO = txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getVehicleDO();
					transitTxnPayload.setVehicleDO(VehicleDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = VehicleComponent.merge(transitTxnTransferObj);
					respVehicleDO =transitTxnTransferObj.getTxnPayload().getVehicleDO();
					reqLeVehicleAssocDO.setVehicleIdpk(respVehicleDO.getIdPk());
				}
				
				//persist base association
				transitTxnPayload= new TxnPayload();
				transitTxnPayload.setLeVehicleAssocDO(reqLeVehicleAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = leVehicleAssocComponent.merge(transitTxnTransferObj);
				respLeVehicleAssocDO=transitTxnTransferObj.getTxnPayload().getLeVehicleAssocDO();
				respLeVehicleAssocDO.setVehicleDO(respVehicleDO);
				
				
				//Final Response object
				respTxnPayload.setLeVehicleAssocDO(respLeVehicleAssocDO);
				respTxnTransferObj.setTxnPayload(respTxnPayload);
				
				
			} catch (YugandharCommonException yce) {
				logger.error("Composite Service failed", yce);
				throw yce;
			} catch (Exception e) {
				//write the logic to get error message based on error code. Error code is hard-coded here
				logger.error("Composite Service failed", e);
				e.printStackTrace();
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
						"CreateLeVehicleService failed unexpectedly");

			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			return respTxnTransferObj;

		}

		private void validateRequestLeVehicleAssocDO(TxnTransferObj txnTransferObj) {
			transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			LeVehicleAssocDO reqLeVehicleAssocDO= txnTransferObj.getTxnPayload().getLeVehicleAssocDO();
			
			
			if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
						"LeVehicleAssocDO is needed in the request");
			}
			
			if(null == reqLeVehicleAssocDO.getIdPk()){
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10033",
						"LeVehicleAssocDO.idPk is needed in the request");
			}
			
			if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getLegalentityIdpk() || 
					txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getLegalentityIdpk().isEmpty()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
						"LeVehicleAssocDO.LegalEntityIdPk is needed in the request");
			} else {
				//search the LE by Id, if success response means validated else throw error
				try {
				transitTxnPayload= new TxnPayload();
				LegalentityDO legalentityDO = new LegalentityDO();
				legalentityDO.setIdPk(reqLeVehicleAssocDO.getLegalentityIdpk());
				transitTxnPayload.setLegalentityDO(legalentityDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = legalentityComponent.findById(transitTxnTransferObj);
				} catch (YugandharCommonException yce) {
					throw yce;
				} catch (RuntimeException re) {
					logger.error("validateRequestLeVehicleAssocDO failed", re);
					re.printStackTrace();
					throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
							"CreateLeVehicleService.validateRequestLeVehicleAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
				}
				
			}
			
			// if Vehicle Id is null then check if the VehicleDO is present
			if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getVehicleIdpk()){
				
				if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getVehicleDO()){
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10055",
							"Cannot Create association without VehicleDO or VehicleIdPk of existing Vehicle");
					
				} 
				
			} else if(!(reqLeVehicleAssocDO.getVehicleIdpk().equals(txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getVehicleDO().getIdPk()))){
				
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10058",
						"VehicleDO.idpk and leVehicleAssocDO.VehicleIdpk does not match");
			} else {
				//search the Vehicle baesd on VehicleIdPk, if success response means validated else throw error
				try {
				transitTxnPayload= new TxnPayload();
				VehicleDO VehicleDO = new VehicleDO();
				VehicleDO.setIdPk(reqLeVehicleAssocDO.getVehicleIdpk());
				transitTxnPayload.setVehicleDO(VehicleDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = VehicleComponent.findById(transitTxnTransferObj);
				} catch (YugandharCommonException yce) {
					throw yce;
				} catch (RuntimeException re) {
					logger.error("validateRequestLeVehicleAssocDO failed", re);
					re.printStackTrace();
					throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
							"CreateLeVehicleService.validateRequestLeVehicleAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
				}
			}
			
			
			
		}
}
