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
import com.yugandhar.mdm.corecomponent.AccountPhoneAssocComponent;
import com.yugandhar.mdm.corecomponent.AccountComponent;
import com.yugandhar.mdm.corecomponent.PhoneStandardizedComponent;
import com.yugandhar.mdm.extern.dobj.AccountPhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.AccountDO;
import com.yugandhar.mdm.extern.dobj.PhoneStandardizedDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.UpdateAccountPhoneService")
public class UpdateAccountPhoneService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	
	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	AccountPhoneAssocDO respAccountPhoneAssocDO;
	PhoneStandardizedDO respPhoneStandardizedDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;
	
		@Autowired
		CommonValidationUtil commonValidationUtil;
		
		@Autowired
		PhoneStandardizedComponent phoneStandardizedComponent;
		
		@Autowired
		AccountPhoneAssocComponent accountPhoneAssocComponent;
		
		@Autowired
		AccountComponent accountComponent;
		
		public UpdateAccountPhoneService() {
			txnTransferObjResponse = new TxnTransferObj();
			respAccountPhoneAssocDO = new AccountPhoneAssocDO();
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
				
				validateRequestAccountPhoneAssocDO(txnTransferObj);
				AccountPhoneAssocDO reqAccountPhoneAssocDO= txnTransferObj.getTxnPayload().getAccountPhoneAssocDO();
				
				// process PhoneStandardizedDO if present
				if (null != reqAccountPhoneAssocDO) {
					
					if (null != reqAccountPhoneAssocDO.getPhoneStandardizedDO()) {
						
						transitTxnPayload= new TxnPayload();
						transitTxnPayload.setPhoneStandardizedDO(reqAccountPhoneAssocDO.getPhoneStandardizedDO());
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						
						if(reqAccountPhoneAssocDO.getPhoneStandardizedDO().getIdPk() == null || reqAccountPhoneAssocDO.getPhoneStandardizedDO().getIdPk().isEmpty())
						{
							
							transitTxnTransferObj = phoneStandardizedComponent.persist(transitTxnTransferObj);
							//set the FK PhoneStandardizedIdpk
							reqAccountPhoneAssocDO.setPhoneStandardizedIdpk(transitTxnTransferObj.getTxnPayload().getPhoneStandardizedDO().getIdPk());
							respPhoneStandardizedDO =transitTxnTransferObj.getTxnPayload().getPhoneStandardizedDO();
							
						} else {
							
							
							transitTxnTransferObj = phoneStandardizedComponent.merge(transitTxnTransferObj);
							//set the FK PhoneStandardizedIdpk
							reqAccountPhoneAssocDO.setPhoneStandardizedIdpk(transitTxnTransferObj.getTxnPayload().getPhoneStandardizedDO().getIdPk());
							respPhoneStandardizedDO =transitTxnTransferObj.getTxnPayload().getPhoneStandardizedDO();
						}
						
						
					}
					
				}
			
				// Process Phone Association
				transitTxnPayload= new TxnPayload();
				transitTxnPayload.setAccountPhoneAssocDO(reqAccountPhoneAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = accountPhoneAssocComponent.merge(transitTxnTransferObj);
				respAccountPhoneAssocDO = transitTxnTransferObj.getTxnPayload().getAccountPhoneAssocDO();
				respAccountPhoneAssocDO.setPhoneStandardizedDO(respPhoneStandardizedDO);
				
				//Final Response object
				respTxnPayload.setAccountPhoneAssocDO(respAccountPhoneAssocDO);
				respTxnTransferObj.setTxnPayload(respTxnPayload);
				
				
			} catch (YugandharCommonException yce) {
				logger.error("Composite Service failed", yce);
				throw yce;
			} catch (Exception e) {
				//write the logic to get error message based on error code. Error code is hard-coded here
				logger.error("Composite Service failed", e);
				e.printStackTrace();
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
						"CreateAccountPhoneService failed unexpectedly");

			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			return respTxnTransferObj;

		}

		private void validateRequestAccountPhoneAssocDO(TxnTransferObj txnTransferObj) {
			transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			AccountPhoneAssocDO reqAccountPhoneAssocDO= txnTransferObj.getTxnPayload().getAccountPhoneAssocDO();
			if (null != reqAccountPhoneAssocDO) {
				//verify AccountIdPk is present in database
				if (null == txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getAccountIdpk() || 
						txnTransferObj.getTxnPayload().getAccountPhoneAssocDO().getAccountIdpk().isEmpty()) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
							"AccountPhoneAssocDO.AccountIdPk is needed in the request");
				} else {
					//search the LE by Id, if success response means validated else throw error
					try {
					transitTxnPayload= new TxnPayload();
					AccountDO accountDO = new AccountDO();
					accountDO.setIdPk(reqAccountPhoneAssocDO.getAccountIdpk());
					transitTxnPayload.setAccountDO(accountDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = accountComponent.findById(transitTxnTransferObj);
					} catch (YugandharCommonException yce) {
						throw yce;
					} catch (RuntimeException re) {
						logger.error("validateRequestAccountPhoneAssocDO failed", re);
						re.printStackTrace();
						throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
								"CreateAccountPhoneService.validateRequestAccountPhoneAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
					}
					
				}
				
				//Verify phonestandardized idpk is present in database and is valid
				
				if (null != reqAccountPhoneAssocDO.getPhoneStandardizedDO() && 
						(null!=reqAccountPhoneAssocDO.getPhoneStandardizedIdpk() && 
								!reqAccountPhoneAssocDO.getPhoneStandardizedIdpk().isEmpty())
						){
						
					//verify PhoneStandardizedIdpk
					try {
					transitTxnPayload= new TxnPayload();
					PhoneStandardizedDO phoneStandardizedDO = new PhoneStandardizedDO();
					phoneStandardizedDO.setIdPk(reqAccountPhoneAssocDO.getPhoneStandardizedIdpk());
					transitTxnPayload.setPhoneStandardizedDO(phoneStandardizedDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = phoneStandardizedComponent.findById(transitTxnTransferObj);
					} catch (YugandharCommonException yce) {
						throw yce;
					} catch (RuntimeException re) {
						logger.error("validateRequestAccountPhoneAssocDO failed", re);
						re.printStackTrace();
						throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
								"CreateAccountPhoneService.validateRequestAccountPhoneAssocDO failed unexpectedly"); //Transaction Failed due to unknown error, please check statck trace
					}
					
				}
			}
		}

		
}
