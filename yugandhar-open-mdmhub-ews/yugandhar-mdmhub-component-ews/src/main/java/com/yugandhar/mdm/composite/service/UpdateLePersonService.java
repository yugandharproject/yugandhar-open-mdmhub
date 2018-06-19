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
@Service("com.yugandhar.mdm.composite.service.UpdateLePersonService")
public class UpdateLePersonService {
	
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
	
	public UpdateLePersonService() {
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
			
			//update base person entity
			transitTxnPayload= new TxnPayload();
			transitTxnPayload.setLePersonDO(reqLePersonDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = lePersonComponent.merge(transitTxnTransferObj);
			respLePersonDO=transitTxnTransferObj.getTxnPayload().getLePersonDO();
			List<PersonnamesDO> respPersonnamesDOList = processPersonNames(transitTxnTransferObj, reqLePersonDO);
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


	private List<PersonnamesDO> processPersonNames(TxnTransferObj transitTxnTransferObj, LePersonDO reqLePersonDO) {
		// find the personnames
					PersonnamesDO transitPersonnamesDO= new PersonnamesDO();
					TxnPayload transitTxnPayload= new TxnPayload();
					transitPersonnamesDO.setLegalentityIdpk(reqLePersonDO.getLegalentityIdpk());
					transitPersonnamesDO.setInquiryFilter(yugandharConstants.FILTER_VALUE_ALL);
					transitTxnPayload.setPersonnamesDO(transitPersonnamesDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = personnamesComponent.findByLegalEntityIdPk(transitTxnTransferObj);
					List<PersonnamesDO> dbPersonnamesDOList = transitTxnTransferObj.getTxnPayload().getPersonnamesDOList();
					//process
					
					//Lists for processing
					List<PersonnamesDO> respPersonnamesDOList= new ArrayList<PersonnamesDO>();
					List<PersonnamesDO> updatePersonnamesDOList= new ArrayList<PersonnamesDO>();
					
					
					
					if (null != reqLePersonDO.getPersonnamesDOList() && reqLePersonDO.getPersonnamesDOList().size() > 0 ){
						Iterator<PersonnamesDO> itr = reqLePersonDO.getPersonnamesDOList().iterator();
						while(itr.hasNext()){
							PersonnamesDO reqPersonnamesDO = itr.next();
							
							Iterator<PersonnamesDO> dbPersonDOitr = null;
							if (null != dbPersonnamesDOList && dbPersonnamesDOList.size() > 0){
								 dbPersonDOitr = dbPersonnamesDOList.iterator();
							}
							if (null != dbPersonDOitr){
								while(dbPersonDOitr.hasNext()){
									PersonnamesDO dbPersonnamesDO = dbPersonDOitr.next();
									if(dbPersonnamesDO.getIdPk().equals(reqPersonnamesDO.getIdPk())){
										reqPersonnamesDO.setIdPk(dbPersonnamesDO.getIdPk());
										updatePersonnamesDOList.add(reqPersonnamesDO);
										itr.remove();
									} 
								}
							}
						}
							
						}
					
					
					if (null != reqLePersonDO.getPersonnamesDOList() && reqLePersonDO.getPersonnamesDOList().size() > 0){
						
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
					
					if (null != updatePersonnamesDOList && updatePersonnamesDOList.size() > 0){
						
						Iterator<PersonnamesDO> itr = updatePersonnamesDOList.iterator();
						while(itr.hasNext()){
							PersonnamesDO thePersonnamesDO = itr.next();
							transitTxnPayload= new TxnPayload();
							transitTxnPayload.setPersonnamesDO(thePersonnamesDO);
							transitTxnTransferObj.setTxnPayload(transitTxnPayload);
							transitTxnTransferObj = personnamesComponent.merge(transitTxnTransferObj);
							respPersonnamesDOList.add(transitTxnTransferObj.getTxnPayload().getPersonnamesDO());
						}
						
						
					}
					return respPersonnamesDOList;
		
	}
}
