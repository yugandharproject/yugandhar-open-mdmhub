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
import com.yugandhar.mdm.corecomponent.PropertyComponent;
import com.yugandhar.mdm.corecomponent.LePropertyAssocComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.PropertyDO;
import com.yugandhar.mdm.extern.dobj.LePropertyAssocDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.UpdateLePropertyService")
public class UpdateLePropertyService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	
	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	LePropertyAssocDO respLePropertyAssocDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;
	
		@Autowired
		CommonValidationUtil commonValidationUtil;
		
		@Autowired
		LePropertyAssocComponent lePropertyAssocComponent;
		
		@Autowired
		LegalentityComponent legalentityComponent;
		
		@Autowired
		PropertyComponent PropertyComponent;
		
		
		public UpdateLePropertyService() {
			txnTransferObjResponse = new TxnTransferObj();
			respLePropertyAssocDO = new LePropertyAssocDO();
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
			PropertyDO respPropertyDO= null;
			
			try {
				//TODO logic here
				validateRequestLePropertyAssocDO(txnTransferObj);
				LePropertyAssocDO reqLePropertyAssocDO= txnTransferObj.getTxnPayload().getLePropertyAssocDO();
				
				
				// if Property Id is null then create Property record and assing the idpk in reqLePropertyAssocDO
				if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyIdpk()){
					
					transitTxnPayload= new TxnPayload();
					PropertyDO PropertyDO = txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyDO();
					transitTxnPayload.setPropertyDO(PropertyDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = PropertyComponent.persist(transitTxnTransferObj);
					respPropertyDO= transitTxnTransferObj.getTxnPayload().getPropertyDO();
					reqLePropertyAssocDO.setPropertyIdpk(respPropertyDO.getIdPk());
				} else {
					
					transitTxnPayload= new TxnPayload();
					PropertyDO PropertyDO = txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyDO();
					transitTxnPayload.setPropertyDO(PropertyDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = PropertyComponent.merge(transitTxnTransferObj);
					respPropertyDO =transitTxnTransferObj.getTxnPayload().getPropertyDO();
					reqLePropertyAssocDO.setPropertyIdpk(respPropertyDO.getIdPk());
				}
				
				//persist base association
				transitTxnPayload= new TxnPayload();
				transitTxnPayload.setLePropertyAssocDO(reqLePropertyAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = lePropertyAssocComponent.merge(transitTxnTransferObj);
				respLePropertyAssocDO=transitTxnTransferObj.getTxnPayload().getLePropertyAssocDO();
				respLePropertyAssocDO.setPropertyDO(respPropertyDO);
				
				
				//Final Response object
				respTxnPayload.setLePropertyAssocDO(respLePropertyAssocDO);
				respTxnTransferObj.setTxnPayload(respTxnPayload);
				
				
			} catch (YugandharCommonException yce) {
				logger.error("Composite Service failed", yce);
				throw yce;
			} catch (Exception e) {
				//write the logic to get error message based on error code. Error code is hard-coded here
				logger.error("Composite Service failed", e);
				e.printStackTrace();
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
						"CreateLePropertyService failed unexpectedly");

			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			return respTxnTransferObj;

		}

		private void validateRequestLePropertyAssocDO(TxnTransferObj txnTransferObj) {
			transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			LePropertyAssocDO reqLePropertyAssocDO= txnTransferObj.getTxnPayload().getLePropertyAssocDO();
			
			
			if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
						"LePropertyAssocDO is needed in the request");
			}
			
			if(null == reqLePropertyAssocDO.getIdPk()){
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10033",
						"LePropertyAssocDO.idPk is needed in the request");
			}
			
			if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getLegalentityIdpk() || 
					txnTransferObj.getTxnPayload().getLePropertyAssocDO().getLegalentityIdpk().isEmpty()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
						"LePropertyAssocDO.LegalEntityIdPk is needed in the request");
			} else {
				//search the LE by Id, if success response means validated else throw error
				try {
				transitTxnPayload= new TxnPayload();
				LegalentityDO legalentityDO = new LegalentityDO();
				legalentityDO.setIdPk(reqLePropertyAssocDO.getLegalentityIdpk());
				transitTxnPayload.setLegalentityDO(legalentityDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = legalentityComponent.findById(transitTxnTransferObj);
				} catch (YugandharCommonException yce) {
					throw yce;
				} catch (RuntimeException re) {
					logger.error("validateRequestLePropertyAssocDO failed", re);
					re.printStackTrace();
					throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
							"CreateLePropertyService.validateRequestLePropertyAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
				}
				
			}
			
			// if Property Id is null then check if the PropertyDO is present
			if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyIdpk()){
				
				if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyDO()){
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10054",
							"Cannot Create association without PropertyDO or PropertyIdPk of existing Property");
					
				} 
				
			} else if(!(reqLePropertyAssocDO.getPropertyIdpk().equals(txnTransferObj.getTxnPayload().getLePropertyAssocDO().getPropertyDO().getIdPk()))){
				
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10057",
						"PropertyDO.idpk and lePropertyAssocDO.PropertyIdpk does not match");
			} else {
				//search the Property baesd on PropertyIdPk, if success response means validated else throw error
				try {
				transitTxnPayload= new TxnPayload();
				PropertyDO PropertyDO = new PropertyDO();
				PropertyDO.setIdPk(reqLePropertyAssocDO.getPropertyIdpk());
				transitTxnPayload.setPropertyDO(PropertyDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = PropertyComponent.findById(transitTxnTransferObj);
				} catch (YugandharCommonException yce) {
					throw yce;
				} catch (RuntimeException re) {
					logger.error("validateRequestLePropertyAssocDO failed", re);
					re.printStackTrace();
					throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
							"CreateLePropertyService.validateRequestLePropertyAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
				}
			}
			
			
			
		}
}
