package com.yugandhar.mdm.composite.service;
/* Generated Jul 3, 2017 2:32:54 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

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
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.config.inqlevel.ConfigInquiryLevelsComponent;
import com.yugandhar.mdm.corecomponent.AccountComponent;
import com.yugandhar.mdm.corecomponent.LeAccountAssocComponent;
import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.AccountDO;
import com.yugandhar.mdm.extern.dobj.AccountPhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.ConfigInquiryLevelsDO;
import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.RetrieveAccountByAccountIdService")
public class RetrieveAccountByAccountIdService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	AccountDO respAccountDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AccountComponent AccountComponent;

	@Autowired
	FindAllAccountAddressAssocByAccountIdService findAllAccountAddressService;

	@Autowired
	FindAllAccountPhoneAssocByAccountIdService findAllAccountPhoneService;

	@Autowired
	ConfigInquiryLevelsComponent configInquiryLevelsComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@Autowired
	LeAccountAssocComponent LeAccountAssocComponent;

	public RetrieveAccountByAccountIdService() {
		txnTransferObjResponse = new TxnTransferObj();
		respAccountDO = new AccountDO();
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

			validateRequestAccountDO(txnTransferObj);
			AccountDO reqAccountDO = txnTransferObj.getTxnPayload().getAccountDO();

			// retrieve base account
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setAccountDO(reqAccountDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = AccountComponent.findById(transitTxnTransferObj);
			respAccountDO = transitTxnTransferObj.getTxnPayload().getAccountDO();

			// Retrieve inquiry levels
			ArrayList<String> childDobjList = retrieveConfigInquiryLevelChildObjList(txnTransferObj,
					reqAccountDO.getInquiryLevel(), yugandharConstants.INQUIRY_LEVEL_APPLICABLE_OBJ_NAME_ACCOUNT,
					yugandharConstants.FILTER_VALUE_ACTIVE);

			// retrieve Account Addresses
			if (childDobjList.contains("AccountAddressAssocDO")) {
				transitTxnPayload = new TxnPayload();
				AccountAddressAssocDO acountAddressAssocDO = new AccountAddressAssocDO();
				acountAddressAssocDO.setAccountIdpk(respAccountDO.getIdPk());
				transitTxnPayload.setAccountAddressAssocDO(acountAddressAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = findAllAccountAddressService.process(transitTxnTransferObj);
				respAccountDO.setAccountAddressAssocDOList(
						transitTxnTransferObj.getTxnPayload().getAccountAddressAssocDOList());
			}

			if (childDobjList.contains("AccountPhoneAssocDO")) {
				// retrieve Account Phones
				transitTxnPayload = new TxnPayload();
				AccountPhoneAssocDO acountPhoneAssocDO = new AccountPhoneAssocDO();
				acountPhoneAssocDO.setAccountIdpk(respAccountDO.getIdPk());
				transitTxnPayload.setAccountPhoneAssocDO(acountPhoneAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = findAllAccountPhoneService.process(transitTxnTransferObj);
				respAccountDO
						.setAccountPhoneAssocDOList(transitTxnTransferObj.getTxnPayload().getAccountPhoneAssocDOList());
			}

			if (childDobjList.contains("LeAccountAssocDO")) {
				// retrieve LeAccountAssocDO by LegalEntityIdPk
				transitTxnPayload = new TxnPayload();
				LeAccountAssocDO theLeAccountAssocDO = new LeAccountAssocDO();
				theLeAccountAssocDO.setAccountIdpk(respAccountDO.getIdPk());
				transitTxnPayload.setLeAccountAssocDO(theLeAccountAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = LeAccountAssocComponent.findByAccountIdpk(transitTxnTransferObj);
				respAccountDO.setLeAccountAssocDOList(transitTxnTransferObj.getTxnPayload().getLeAccountAssocDOList());

			}

			// Final Response object
			respTxnPayload.setAccountDO(respAccountDO);
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
					"CreateLeAccountService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

	}

	private ArrayList<String> retrieveConfigInquiryLevelChildObjList(TxnTransferObj txnTransferObj, String inquiryLevel,
			String applicableObjName, String filter) {
		// Retrieve inquiry levels
		ArrayList<String> childDobjList = new ArrayList<String>();

		List<ConfigInquiryLevelsDO> theConfigInquiryLevelsDOList = configInquiryLevelsComponent
				.executeRepositoryQuery(inquiryLevel, applicableObjName, filter);

		if (null != theConfigInquiryLevelsDOList && theConfigInquiryLevelsDOList.size() > 0) {
			Iterator<ConfigInquiryLevelsDO> itr = theConfigInquiryLevelsDOList.iterator();
			while (itr.hasNext()) {
				ConfigInquiryLevelsDO theConfigInquiryLevelsDO = (ConfigInquiryLevelsDO) itr.next();
				childDobjList.add(theConfigInquiryLevelsDO.getChildDobj());
			}
		} else {

			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1020",
					"Invalid inquiry level provided in the request for AccountDO");
		}

		return childDobjList;
	}

	private void validateRequestAccountDO(TxnTransferObj txnTransferObj) {
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		if (null == txnTransferObj.getTxnPayload().getAccountDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"accountDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAccountDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10063",
					"accountDO.IdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAccountDO().getInquiryLevel()
				|| txnTransferObj.getTxnPayload().getAccountDO().getInquiryLevel().isEmpty()) {
			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_inqlevel_defaultvalue_retrieve_AccountDO,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			txnTransferObj.getTxnPayload().getAccountDO().setInquiryLevel(theConfigAppPropertiesDO.getValue());

		}
	}
}
