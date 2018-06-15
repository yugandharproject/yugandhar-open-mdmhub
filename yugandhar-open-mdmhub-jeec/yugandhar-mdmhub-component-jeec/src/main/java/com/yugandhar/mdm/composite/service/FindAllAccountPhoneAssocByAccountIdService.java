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
import com.yugandhar.mdm.corecomponent.AccountPhoneAssocComponent;
import com.yugandhar.mdm.corecomponent.AccountComponent;
import com.yugandhar.mdm.corecomponent.PhoneStandardizedComponent;
import com.yugandhar.mdm.extern.dobj.AccountPhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.PhoneStandardizedDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.FindAllAccountPhoneAssocByAccountIdService")
public class FindAllAccountPhoneAssocByAccountIdService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	List<AccountPhoneAssocDO> respAccountPhoneAssocDOList = null;
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

	public FindAllAccountPhoneAssocByAccountIdService() {
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

			AccountPhoneAssocDO reqAccountPhoneAssocDO = txnTransferObj.getTxnPayload().getAccountPhoneAssocDO();

			// Process Phone Association
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setAccountPhoneAssocDO(reqAccountPhoneAssocDO);
			//copy pagination properties from request object
			commonValidationUtil.copyPaginationProperties(txnTransferObj.getTxnPayload(), transitTxnPayload);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = accountPhoneAssocComponent.findByAccountIdPk(transitTxnTransferObj);
			respAccountPhoneAssocDOList = transitTxnTransferObj.getTxnPayload().getAccountPhoneAssocDOList();

			//copy pagination properties to the response object
			commonValidationUtil.copyPaginationProperties(transitTxnTransferObj.getTxnPayload(), respTxnPayload);
			// process PhoneStandardizedDO if present
			if (null != respAccountPhoneAssocDOList && respAccountPhoneAssocDOList.size() > 0) {
				Iterator<AccountPhoneAssocDO> respAccountPhoneAssocDOItr = respAccountPhoneAssocDOList.iterator();
				while (respAccountPhoneAssocDOItr.hasNext()) {
					AccountPhoneAssocDO respAccountPhoneAssocDO = respAccountPhoneAssocDOItr.next();
					if (!(null == respAccountPhoneAssocDO.getPhoneStandardizedIdpk()
							|| respAccountPhoneAssocDO.getPhoneStandardizedIdpk().isEmpty())) {

						transitTxnPayload = new TxnPayload();
						PhoneStandardizedDO phoneStandardizedDO = new PhoneStandardizedDO();
						phoneStandardizedDO.setIdPk(respAccountPhoneAssocDO.getPhoneStandardizedIdpk());

						transitTxnPayload.setPhoneStandardizedDO(phoneStandardizedDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = phoneStandardizedComponent.findById(transitTxnTransferObj);
						// set the FK PhoneStandardizedIdpk and the
						// PhoneStandardizedDO object
						respAccountPhoneAssocDO
								.setPhoneStandardizedDO(transitTxnTransferObj.getTxnPayload().getPhoneStandardizedDO());

					}
				}
			}

			// Final Response object
			respTxnPayload.setAccountPhoneAssocDOList(respAccountPhoneAssocDOList);
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
					"CreateAccountPhoneService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

	}

}
