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

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.RetrieveLeVehicleLeVehicleAssocIdService")
public class RetrieveLeVehicleLeVehicleAssocIdService {

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
	VehicleComponent vehicleComponent;

	public RetrieveLeVehicleLeVehicleAssocIdService() {
		txnTransferObjResponse = new TxnTransferObj();
		respLeVehicleAssocDO = new LeVehicleAssocDO();
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
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = leVehicleAssocComponent.findById(transitTxnTransferObj);
			respLeVehicleAssocDO = transitTxnTransferObj.getTxnPayload().getLeVehicleAssocDO();

			// retrieve Vehicle
			transitTxnPayload = new TxnPayload();
			VehicleDO VehicleDO = new VehicleDO();
			VehicleDO.setIdPk(respLeVehicleAssocDO.getVehicleIdpk());
			transitTxnPayload.setVehicleDO(VehicleDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = vehicleComponent.findById(transitTxnTransferObj);
			respLeVehicleAssocDO.setVehicleDO(transitTxnTransferObj.getTxnPayload().getVehicleDO());

			// Final Response object
			respTxnPayload.setLeVehicleAssocDO(respLeVehicleAssocDO);
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

		if (null == txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLeVehicleAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10033",
					"LeVehicleAssocDO.IdPk is needed in the request");
		}

	}
}
