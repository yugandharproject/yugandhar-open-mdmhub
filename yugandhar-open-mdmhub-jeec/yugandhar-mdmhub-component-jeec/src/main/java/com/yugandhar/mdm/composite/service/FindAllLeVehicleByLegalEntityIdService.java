package com.yugandhar.mdm.composite.service;
/* Generated Jul 3, 2017 2:32:54 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

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
import com.yugandhar.mdm.corecomponent.VehicleComponent;
import com.yugandhar.mdm.corecomponent.LeVehicleAssocComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.VehicleDO;
import com.yugandhar.mdm.extern.dobj.LeVehicleAssocDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.FindAllLeVehicleByLegalEntityIdService")
public class FindAllLeVehicleByLegalEntityIdService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	List<LeVehicleAssocDO> respLeVehicleAssocDOList;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeVehicleAssocComponent LeVehicleAssocComponent;

	@Autowired
	LegalentityComponent legalentityComponent;

	@Autowired
	VehicleComponent VehicleComponent;

	public FindAllLeVehicleByLegalEntityIdService() {
		txnTransferObjResponse = new TxnTransferObj();
		respTxnTransferObj = new TxnTransferObj();
		respTxnPayload = new TxnPayload();
		transitTxnTransferObj = new TxnTransferObj();
	}

	@Transactional
	public TxnTransferObj process(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		TxnPayload transitTxnPayload = null;
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {

			validateRequestLeVehicleAssocDO(txnTransferObj);
			LeVehicleAssocDO reqLeVehicleAssocDO = txnTransferObj.getTxnPayload().getLeVehicleAssocDO();

			// retrieve base association
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLeVehicleAssocDO(reqLeVehicleAssocDO);
			//copy pagination properties from request object
			commonValidationUtil.copyPaginationProperties(txnTransferObj.getTxnPayload(), transitTxnPayload);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = LeVehicleAssocComponent.findByLegalEntityIdPk(transitTxnTransferObj);
			respLeVehicleAssocDOList = transitTxnTransferObj.getTxnPayload().getLeVehicleAssocDOList();

			//copy pagination properties to the response object
			commonValidationUtil.copyPaginationProperties(transitTxnTransferObj.getTxnPayload(), respTxnPayload);
			// Process Vehiclees
			if (null != respLeVehicleAssocDOList) {

				Iterator<LeVehicleAssocDO> addrIterator = respLeVehicleAssocDOList.iterator();
				
				while (addrIterator.hasNext()) {
					LeVehicleAssocDO respLeVehicleAssocDO = addrIterator.next();
					transitTxnPayload = new TxnPayload();
					VehicleDO VehicleDO = new VehicleDO();
					VehicleDO.setIdPk(respLeVehicleAssocDO.getVehicleIdpk());
					transitTxnPayload.setVehicleDO(VehicleDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = VehicleComponent.findById(transitTxnTransferObj);
					respLeVehicleAssocDO.setVehicleDO(transitTxnTransferObj.getTxnPayload().getVehicleDO());
					
				}
			}

			// retrieve Vehicle
			

			// Final Response object
			respTxnPayload.setLeVehicleAssocDOList(respLeVehicleAssocDOList);
			respTxnTransferObj.setTxnPayload(respTxnPayload);

		} catch (YugandharCommonException yce) {
			logger.error("Composite Service failed", yce);
			throw yce;
		} catch (Exception e) {
			// write the logic to get error message based on error code. Error
			// code is hard-coded here
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
		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeVehicleAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeVehicleAssocDO.LegalEntityIdPk is needed in the request");
		}

	}
}
