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
import com.yugandhar.mdm.corecomponent.CorporationnamesComponent;
import com.yugandhar.mdm.corecomponent.LeCorporationComponent;
import com.yugandhar.mdm.extern.dobj.CorporationnamesDO;
import com.yugandhar.mdm.extern.dobj.LeCorporationDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;

/**
*@author Yugandhar
*@version 1.0
*@since 1.0
 * 
*/

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.UpdateLeCorporationService")
public class UpdateLeCorporationService {
	
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
	CorporationnamesComponent corporationnamesComponent;
	
	public UpdateLeCorporationService() {
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
			
			//update base person entity
			transitTxnPayload= new TxnPayload();
			transitTxnPayload.setLeCorporationDO(reqLeCorporationDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = leCorporationComponent.merge(transitTxnTransferObj);
			respLeCorporationDO=transitTxnTransferObj.getTxnPayload().getLeCorporationDO();
			List<CorporationnamesDO> respPersonnamesDOList = processCorporationNames(transitTxnTransferObj, reqLeCorporationDO);
			respLeCorporationDO.setCorporationnamesDOList(respPersonnamesDOList);
			
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
					"UpdateLeCorporation Service failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;
	}


	private List<CorporationnamesDO> processCorporationNames(TxnTransferObj transitTxnTransferObj, LeCorporationDO reqLeCorporationDO) {
		// find the personnames
					CorporationnamesDO transitCorporationnamesDO= new CorporationnamesDO();
					TxnPayload transitTxnPayload= new TxnPayload();
					transitCorporationnamesDO.setLegalentityIdpk(reqLeCorporationDO.getLegalentityIdpk());
					transitCorporationnamesDO.setInquiryFilter(yugandharConstants.FILTER_VALUE_ALL);
					transitTxnPayload.setCorporationnamesDO(transitCorporationnamesDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = corporationnamesComponent.findByLegalEntityIdPk(transitTxnTransferObj);
					List<CorporationnamesDO> dbCorporationnamesDOList = transitTxnTransferObj.getTxnPayload().getCorporationnamesDOList();
					//process
					
					//Lists for processing
					List<CorporationnamesDO> respCorporationnamesDOList= new ArrayList<CorporationnamesDO>();
					List<CorporationnamesDO> updateCorporationnamesDOList= new ArrayList<CorporationnamesDO>();
					
					
					
					if (null != reqLeCorporationDO.getCorporationnamesDOList() && reqLeCorporationDO.getCorporationnamesDOList().size() > 0 ){
						Iterator<CorporationnamesDO> itr = reqLeCorporationDO.getCorporationnamesDOList().iterator();
						while(itr.hasNext()){
							CorporationnamesDO reqCorporationnamesDO = itr.next();
							
							Iterator<CorporationnamesDO> dbCorporationDOitr = null;
							if (null != dbCorporationnamesDOList && dbCorporationnamesDOList.size() > 0){
								dbCorporationDOitr = dbCorporationnamesDOList.iterator();
							}
							if (null != dbCorporationDOitr){
								while(dbCorporationDOitr.hasNext()){
									CorporationnamesDO dbCorporationnamesDO = dbCorporationDOitr.next();
									if(dbCorporationnamesDO.getIdPk().equals(reqCorporationnamesDO.getIdPk())){
										reqCorporationnamesDO.setIdPk(dbCorporationnamesDO.getIdPk());
										updateCorporationnamesDOList.add(reqCorporationnamesDO);
										itr.remove();
									} 
								}
							}
						}
							
						}
					
					
					if (null != reqLeCorporationDO.getCorporationnamesDOList() && reqLeCorporationDO.getCorporationnamesDOList().size() > 0){
						
						Iterator<CorporationnamesDO> itr = reqLeCorporationDO.getCorporationnamesDOList().iterator();
						while(itr.hasNext()){
							CorporationnamesDO theCorporationnamesDO = itr.next();
							theCorporationnamesDO.setLegalentityIdpk(respLeCorporationDO.getLegalentityIdpk());
							transitTxnPayload= new TxnPayload();
							transitTxnPayload.setCorporationnamesDO(theCorporationnamesDO);
							transitTxnTransferObj.setTxnPayload(transitTxnPayload);
							transitTxnTransferObj = corporationnamesComponent.persist(transitTxnTransferObj);
							respCorporationnamesDOList.add(transitTxnTransferObj.getTxnPayload().getCorporationnamesDO());
						}
						
						
					}
					
					if (null != updateCorporationnamesDOList && updateCorporationnamesDOList.size() > 0){
						
						Iterator<CorporationnamesDO> itr = updateCorporationnamesDOList.iterator();
						while(itr.hasNext()){
							CorporationnamesDO theCorporationnamesDO = itr.next();
							transitTxnPayload= new TxnPayload();
							transitTxnPayload.setCorporationnamesDO(theCorporationnamesDO);
							transitTxnTransferObj.setTxnPayload(transitTxnPayload);
							transitTxnTransferObj = corporationnamesComponent.merge(transitTxnTransferObj);
							respCorporationnamesDOList.add(transitTxnTransferObj.getTxnPayload().getCorporationnamesDO());
						}
						
						
					}
					return respCorporationnamesDOList;
		
	}
}
