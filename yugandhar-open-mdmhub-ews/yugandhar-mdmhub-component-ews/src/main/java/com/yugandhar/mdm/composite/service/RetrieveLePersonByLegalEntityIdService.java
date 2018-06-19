package com.yugandhar.mdm.composite.service;

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
@Service("com.yugandhar.mdm.composite.service.RetrieveLePersonByLegalEntityIdService")
public class RetrieveLePersonByLegalEntityIdService {
	
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
	
	public RetrieveLePersonByLegalEntityIdService() {
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
			
			
			//persist base legal entity
			transitTxnPayload= new TxnPayload();
			transitTxnPayload.setLePersonDO(reqLePersonDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = lePersonComponent.findById(transitTxnTransferObj);
			respLePersonDO=transitTxnTransferObj.getTxnPayload().getLePersonDO();
			
			
			//Person Names Processing
			PersonnamesDO transitPersonnamesDO= new PersonnamesDO();
			transitPersonnamesDO.setLegalentityIdpk(reqLePersonDO.getLegalentityIdpk());
			transitPersonnamesDO.setInquiryFilter(reqLePersonDO.getInquiryFilter());
						
			transitTxnPayload= new TxnPayload();
			transitTxnPayload.setPersonnamesDO(transitPersonnamesDO);
			//copy pagination properties from request object
			commonValidationUtil.copyPaginationProperties(txnTransferObj.getTxnPayload(), transitTxnPayload);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = personnamesComponent.findByLegalEntityIdPk(transitTxnTransferObj);
			respLePersonDO.setPersonnamesDOList(transitTxnTransferObj.getTxnPayload().getPersonnamesDOList());
			
			//copy pagination properties to the response object
			commonValidationUtil.copyPaginationProperties(transitTxnTransferObj.getTxnPayload(), respTxnPayload);
			
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
