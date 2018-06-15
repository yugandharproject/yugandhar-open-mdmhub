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
import com.yugandhar.mdm.corecomponent.AddressComponent;
import com.yugandhar.mdm.corecomponent.LeAddressAssocComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.AddressDO;
import com.yugandhar.mdm.extern.dobj.LeAddressAssocDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.FindAllLeAddressByLegalEntityIdService")
public class FindAllLeAddressByLegalEntityIdService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	List<LeAddressAssocDO> respLeAddressAssocDOList;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LeAddressAssocComponent leAddressAssocComponent;

	@Autowired
	LegalentityComponent legalentityComponent;

	@Autowired
	AddressComponent addressComponent;

	public FindAllLeAddressByLegalEntityIdService() {
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

			validateRequestLeAddressAssocDO(txnTransferObj);
			LeAddressAssocDO reqLeAddressAssocDO = txnTransferObj.getTxnPayload().getLeAddressAssocDO();

			// retrieve base association
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLeAddressAssocDO(reqLeAddressAssocDO);
			//copy pagination properties from request object
			commonValidationUtil.copyPaginationProperties(txnTransferObj.getTxnPayload(), transitTxnPayload);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = leAddressAssocComponent.findByLegalEntityIdPk(transitTxnTransferObj);
			respLeAddressAssocDOList = transitTxnTransferObj.getTxnPayload().getLeAddressAssocDOList();

			//copy pagination properties to the response object
			commonValidationUtil.copyPaginationProperties(transitTxnTransferObj.getTxnPayload(), respTxnPayload);
			// Process Addresses
			if (null != respLeAddressAssocDOList) {

				Iterator<LeAddressAssocDO> addrIterator = respLeAddressAssocDOList.iterator();
				
				while (addrIterator.hasNext()) {
					LeAddressAssocDO respLeAddressAssocDO = addrIterator.next();
					transitTxnPayload = new TxnPayload();
					AddressDO addressDO = new AddressDO();
					addressDO.setIdPk(respLeAddressAssocDO.getAddressIdpk());
					transitTxnPayload.setAddressDO(addressDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = addressComponent.findById(transitTxnTransferObj);
					respLeAddressAssocDO.setAddressDO(transitTxnTransferObj.getTxnPayload().getAddressDO());
					
				}
			}

			// retrieve Address
			

			// Final Response object
			respTxnPayload.setLeAddressAssocDOList(respLeAddressAssocDOList);
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
					"CreateLeAddressService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

	}

	private void validateRequestLeAddressAssocDO(TxnTransferObj txnTransferObj) {
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LeAddressAssocDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getLeAddressAssocDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getLeAddressAssocDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"LeAddressAssocDO.LegalEntityIdPk is needed in the request");
		}

	}
}
