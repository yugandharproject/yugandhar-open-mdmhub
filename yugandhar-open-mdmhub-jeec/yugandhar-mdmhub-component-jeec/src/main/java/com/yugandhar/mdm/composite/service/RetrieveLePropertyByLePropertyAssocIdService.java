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

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.RetrieveLePropertyByLePropertyAssocIdService")
public class RetrieveLePropertyByLePropertyAssocIdService {

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
	PropertyComponent propertyComponent;

	public RetrieveLePropertyByLePropertyAssocIdService() {
		txnTransferObjResponse = new TxnTransferObj();
		respLePropertyAssocDO = new LePropertyAssocDO();
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

			validateRequestLePropertyAssocDO(txnTransferObj);
			LePropertyAssocDO reqLePropertyAssocDO = txnTransferObj.getTxnPayload().getLePropertyAssocDO();

			// retrieve base association
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLePropertyAssocDO(reqLePropertyAssocDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = lePropertyAssocComponent.findById(transitTxnTransferObj);
			respLePropertyAssocDO = transitTxnTransferObj.getTxnPayload().getLePropertyAssocDO();

			// retrieve Property
			transitTxnPayload = new TxnPayload();
			PropertyDO PropertyDO = new PropertyDO();
			PropertyDO.setIdPk(respLePropertyAssocDO.getPropertyIdpk());
			transitTxnPayload.setPropertyDO(PropertyDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = propertyComponent.findById(transitTxnTransferObj);
			respLePropertyAssocDO.setPropertyDO(transitTxnTransferObj.getTxnPayload().getPropertyDO());

			// Final Response object
			respTxnPayload.setLePropertyAssocDO(respLePropertyAssocDO);
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
					"CreateLePropertyService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

	}

	private void validateRequestLePropertyAssocDO(TxnTransferObj txnTransferObj) {
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LePropertyAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLePropertyAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getLePropertyAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10033",
					"LePropertyAssocDO.IdPk is needed in the request");
		}

	}
}
