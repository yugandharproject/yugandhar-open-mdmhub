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
import com.yugandhar.mdm.corecomponent.LeCorporationComponent;
import com.yugandhar.mdm.corecomponent.CorporationnamesComponent;
import com.yugandhar.mdm.extern.dobj.LeCorporationDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.CorporationnamesDO;

/**
*@author Yugandhar
*@version 1.0
*@since 1.0
 * 
*/
@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.CreateLeCorporationService")
public class CreateLeCorporationService {
	
	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	LegalentityDO respLegalentityDO;
	LeCorporationDO respLeCorporationDO;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	
	@Autowired
 CommonValidationUtil commonValidationUtil;
	
	@Autowired
	LeCorporationComponent leCorporationComponent;

	@Autowired
	CorporationnamesComponent personnamesComponent;
	
	public CreateLeCorporationService() {
		txnTransferObjResponse = new TxnTransferObj();
		respLegalentityDO = new LegalentityDO();
		respTxnTransferObj = new TxnTransferObj();
		respTxnPayload = new TxnPayload();
		respLeCorporationDO = new LeCorporationDO();
	}
	
	@Transactional
	public TxnTransferObj process(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		
		TxnTransferObj transitTxnTransferObj=new TxnTransferObj();
		TxnPayload transitTxnPayload = null;
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		
		try {
			//TODO logic here
			
			LeCorporationDO reqLeCorporationDO= txnTransferObj.getTxnPayload().getLeCorporationDO();
			
			
			//persist base person entity
			transitTxnPayload= new TxnPayload();
			transitTxnPayload.setLeCorporationDO(reqLeCorporationDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = leCorporationComponent.persist(transitTxnTransferObj);
			respLeCorporationDO=transitTxnTransferObj.getTxnPayload().getLeCorporationDO();
			
			
			//Corporation Names Processing
			List<CorporationnamesDO> respCorporationnamesDOList= new ArrayList<CorporationnamesDO>();
			if (null != reqLeCorporationDO.getCorporationnamesDOList() && reqLeCorporationDO.getCorporationnamesDOList() .size() > 0){
				
				Iterator<CorporationnamesDO> itr = reqLeCorporationDO.getCorporationnamesDOList().iterator();
				while(itr.hasNext()){
					CorporationnamesDO theCorporationnamesDO = itr.next();
					theCorporationnamesDO.setLegalentityIdpk(respLeCorporationDO.getLegalentityIdpk());
					
					transitTxnPayload= new TxnPayload();
					transitTxnPayload.setCorporationnamesDO(theCorporationnamesDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = personnamesComponent.persist(transitTxnTransferObj);
					respCorporationnamesDOList.add(transitTxnTransferObj.getTxnPayload().getCorporationnamesDO());
				}
				
				
			}
			
			respLeCorporationDO.setCorporationnamesDOList(respCorporationnamesDOList);
			
			//Final Response object
			respTxnPayload.setLeCorporationDO(respLeCorporationDO);
			respTxnTransferObj.setTxnPayload(respTxnPayload);
			
			
		} catch (YugandharCommonException yce) {
			logger.error("Composite Service failed", yce);
			throw yce;
		} catch (Exception e) {
			//write the logic to get error message based on error code. Error code is hard-coded here
			logger.error("Composite Service failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
					"CreateLeCorporationService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;
	}
	
}
