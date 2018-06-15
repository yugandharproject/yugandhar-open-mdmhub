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
import com.yugandhar.mdm.corecomponent.LePhoneAssocComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.corecomponent.PhoneStandardizedComponent;
import com.yugandhar.mdm.extern.dobj.LePhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.PhoneStandardizedDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.CreateLePhoneService")
public class CreateLePhoneService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	
	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	LePhoneAssocDO respLePhoneAssocDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;
	PhoneStandardizedDO phoneStandardizedDO;
	
		@Autowired
		CommonValidationUtil commonValidationUtil;
		
		@Autowired
		PhoneStandardizedComponent phoneStandardizedComponent;
		
		@Autowired
		LePhoneAssocComponent lePhoneAssocComponent;
		
		@Autowired
		LegalentityComponent legalentityComponent;
		
		public CreateLePhoneService() {
			txnTransferObjResponse = new TxnTransferObj();
			respLePhoneAssocDO = new LePhoneAssocDO();
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
				
				validateRequestLePhoneAssocDO(txnTransferObj);
				LePhoneAssocDO reqLePhoneAssocDO= txnTransferObj.getTxnPayload().getLePhoneAssocDO();
				
				// process PhoneStandardizedDO if present
				if (null != reqLePhoneAssocDO) {
					
					if (null != reqLePhoneAssocDO.getPhoneStandardizedDO()) {
						
						transitTxnPayload= new TxnPayload();
						transitTxnPayload.setPhoneStandardizedDO(reqLePhoneAssocDO.getPhoneStandardizedDO());
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = phoneStandardizedComponent.persist(transitTxnTransferObj);
						//set the FK PhoneStandardizedIdpk and the PhoneStandardizedDO object
						reqLePhoneAssocDO.setPhoneStandardizedIdpk(transitTxnTransferObj.getTxnPayload().getPhoneStandardizedDO().getIdPk());
						phoneStandardizedDO = transitTxnTransferObj.getTxnPayload().getPhoneStandardizedDO();
					}
					
				}
			
				// Process Phone Association
				transitTxnPayload= new TxnPayload();
				transitTxnPayload.setLePhoneAssocDO(reqLePhoneAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = lePhoneAssocComponent.persist(transitTxnTransferObj);
				respLePhoneAssocDO = transitTxnTransferObj.getTxnPayload().getLePhoneAssocDO();
				respLePhoneAssocDO.setPhoneStandardizedDO(phoneStandardizedDO);
				
				//Final Response object
				respTxnPayload.setLePhoneAssocDO(respLePhoneAssocDO);
				respTxnTransferObj.setTxnPayload(respTxnPayload);
				
				
			} catch (YugandharCommonException yce) {
				logger.error("Composite Service failed", yce);
				throw yce;
			} catch (Exception e) {
				//write the logic to get error message based on error code. Error code is hard-coded here
				logger.error("Composite Service failed", e);
				e.printStackTrace();
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
						"CreateLePhoneService failed unexpectedly");

			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			return respTxnTransferObj;

		}

		private void validateRequestLePhoneAssocDO(TxnTransferObj txnTransferObj) {
			transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			LePhoneAssocDO reqLePhoneAssocDO= txnTransferObj.getTxnPayload().getLePhoneAssocDO();
			if (null != reqLePhoneAssocDO) {
				//verify LegalentityIdPk is present in database
				if (null == txnTransferObj.getTxnPayload().getLePhoneAssocDO().getLegalentityIdpk() || 
						txnTransferObj.getTxnPayload().getLePhoneAssocDO().getLegalentityIdpk().isEmpty()) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
							"LePhoneAssocDO.LegalEntityIdPk is needed in the request");
				} else {
					//search the LE by Id, if success response means validated else throw error
					try {
					transitTxnPayload= new TxnPayload();
					LegalentityDO legalentityDO = new LegalentityDO();
					legalentityDO.setIdPk(reqLePhoneAssocDO.getLegalentityIdpk());
					transitTxnPayload.setLegalentityDO(legalentityDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = legalentityComponent.findById(transitTxnTransferObj);
					} catch (YugandharCommonException yce) {
						throw yce;
					} catch (RuntimeException re) {
						logger.error("validateRequestLePhoneAssocDO failed", re);
						re.printStackTrace();
						throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
								"CreateLePhoneService.validateRequestLePhoneAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
					}
					
				}
				
				//Verify phonestandardized idpk is present in database and is valid
				
				if (null != reqLePhoneAssocDO.getPhoneStandardizedDO() && 
						(null!=reqLePhoneAssocDO.getPhoneStandardizedIdpk() && 
								!reqLePhoneAssocDO.getPhoneStandardizedIdpk().isEmpty())
						){
						
					//verify PhoneStandardizedIdpk
					try {
					transitTxnPayload= new TxnPayload();
					PhoneStandardizedDO phoneStandardizedDO = new PhoneStandardizedDO();
					phoneStandardizedDO.setIdPk(reqLePhoneAssocDO.getPhoneStandardizedIdpk());
					transitTxnPayload.setPhoneStandardizedDO(phoneStandardizedDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = phoneStandardizedComponent.findById(transitTxnTransferObj);
					} catch (YugandharCommonException yce) {
						throw yce;
					} catch (RuntimeException re) {
						logger.error("validateRequestLePhoneAssocDO failed", re);
						re.printStackTrace();
						throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
								"CreateLePhoneService.validateRequestLePhoneAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
					}
					
				}
			}
		}

		
}
