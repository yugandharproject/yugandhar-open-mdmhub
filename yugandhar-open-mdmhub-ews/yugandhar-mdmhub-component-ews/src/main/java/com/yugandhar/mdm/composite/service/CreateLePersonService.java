package com.yugandhar.mdm.composite.service;

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
import com.yugandhar.mdm.corecomponent.LePersonComponent;
import com.yugandhar.mdm.corecomponent.PersonnamesComponent;
import com.yugandhar.mdm.extern.dobj.LePersonDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.PersonnamesDO;

/**
*@author Yugandhar
*@version 1.0
*@since 1.0
 * 
*/
@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.CreateLePersonService")
public class CreateLePersonService {
	
	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	
	LegalentityDO respLegalentityDO;
	LePersonDO respLePersonDO;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	
	@Autowired
 CommonValidationUtil commonValidationUtil;
	
	@Autowired
	LePersonComponent lePersonComponent;

	@Autowired
	PersonnamesComponent personnamesComponent;
	
	public CreateLePersonService() {
		txnTransferObjResponse = new TxnTransferObj();
		respLegalentityDO = new LegalentityDO();
		respTxnTransferObj = new TxnTransferObj();
		respTxnPayload = new TxnPayload();
		respLePersonDO = new LePersonDO();
	}
	
	@Transactional
	public TxnTransferObj process(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		
		TxnTransferObj transitTxnTransferObj=new TxnTransferObj();
		TxnPayload transitTxnPayload = null;
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		
		try {
			//TODO logic here
			
			LePersonDO reqLePersonDO= txnTransferObj.getTxnPayload().getLePersonDO();
			
			
			//persist base person entity
			transitTxnPayload= new TxnPayload();
			transitTxnPayload.setLePersonDO(reqLePersonDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = lePersonComponent.persist(transitTxnTransferObj);
			respLePersonDO=transitTxnTransferObj.getTxnPayload().getLePersonDO();
			
			
			//Person Names Processing
			List<PersonnamesDO> respPersonnamesDOList= new ArrayList<PersonnamesDO>();
			if (null != reqLePersonDO.getPersonnamesDOList() && reqLePersonDO.getPersonnamesDOList() .size() > 0){
				
				Iterator<PersonnamesDO> itr = reqLePersonDO.getPersonnamesDOList().iterator();
				while(itr.hasNext()){
					PersonnamesDO thePersonnamesDO = itr.next();
					thePersonnamesDO.setLegalentityIdpk(respLePersonDO.getLegalentityIdpk());
					
					transitTxnPayload= new TxnPayload();
					transitTxnPayload.setPersonnamesDO(thePersonnamesDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = personnamesComponent.persist(transitTxnTransferObj);
					respPersonnamesDOList.add(transitTxnTransferObj.getTxnPayload().getPersonnamesDO());
				}
				
				
			}
			
			respLePersonDO.setPersonnamesDOList(respPersonnamesDOList);
			
			//Final Response object
			respTxnPayload.setLePersonDO(respLePersonDO);
			respTxnTransferObj.setTxnPayload(respTxnPayload);
			
			
		} catch (YugandharCommonException yce) {
			logger.error("Composite Service failed", yce);
			throw yce;
		} catch (Exception e) {
			//write the logic to get error message based on error code. Error code is hard-coded here
			logger.error("persist failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
					"createLegalEntityService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;
	}
	
}
