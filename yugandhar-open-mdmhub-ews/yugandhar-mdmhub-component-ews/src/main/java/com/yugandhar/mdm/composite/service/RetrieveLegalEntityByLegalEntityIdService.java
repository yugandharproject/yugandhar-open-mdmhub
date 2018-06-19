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
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.config.inqlevel.ConfigInquiryLevelsComponent;
import com.yugandhar.mdm.corecomponent.LeIdentifierKycRegistryComponent;
import com.yugandhar.mdm.corecomponent.LePreferencesComponent;
import com.yugandhar.mdm.corecomponent.LeSystemKeysRegistryComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.corecomponent.MiscellaneousInfoComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.ConfigInquiryLevelsDO;
import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;
import com.yugandhar.mdm.extern.dobj.LeAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.LeCorporationDO;
import com.yugandhar.mdm.extern.dobj.LeIdentifierKycRegistryDO;
import com.yugandhar.mdm.extern.dobj.LePersonDO;
import com.yugandhar.mdm.extern.dobj.LePhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.LePreferencesDO;
import com.yugandhar.mdm.extern.dobj.LePropertyAssocDO;
import com.yugandhar.mdm.extern.dobj.LeSystemKeysRegistryDO;
import com.yugandhar.mdm.extern.dobj.LeVehicleAssocDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.MiscellaneousInfoDO;

/**
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.RetrieveLegalEntityByLegalEntityIdService")
public class RetrieveLegalEntityByLegalEntityIdService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	LegalentityDO respLegalentityDO;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LegalentityComponent legalentityComponent;

	@Autowired
	RetrieveLePersonByLegalEntityIdService retrieveLePersonService;

	@Autowired
	RetrieveLeCorporationByLegalEntityIdService retrieveLeCorporationService;

	@Autowired
	FindAllLeAddressByLegalEntityIdService findAllLeAddressService;

	@Autowired
	FindAllLePhoneByLegalEntityIdService findAllLePhoneService;

	@Autowired
	LeSystemKeysRegistryComponent leSystemKeysRegistryComponent;

	@Autowired
	LePreferencesComponent lePreferencesComponent;

	@Autowired
	LeIdentifierKycRegistryComponent leIdentifierKycRegistryComponent;

	@Autowired
	FindAllLeAccountByLegalEntityIdService findAllLeAccountService;

	@Autowired
	FindAllLePropertyByLegalEntityIdService findAllLePropertyService;

	@Autowired
	FindAllLeVehicleByLegalEntityIdService findAllLeVehicleService;

	@Autowired
	MiscellaneousInfoComponent miscellaneousInfoComponent;

	@Autowired
	ConfigInquiryLevelsComponent configInquiryLevelsComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	public RetrieveLegalEntityByLegalEntityIdService() {
		txnTransferObjResponse = new TxnTransferObj();
		respLegalentityDO = new LegalentityDO();
		respTxnTransferObj = new TxnTransferObj();
		respTxnPayload = new TxnPayload();
	}

	@Transactional
	public TxnTransferObj process(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		TxnPayload transitTxnPayload = null;
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			// Perform common validation
			performCommonvalidationBeforeExecution(txnTransferObj);

			LegalentityDO reqlegalentityDO = txnTransferObj.getTxnPayload().getLegalentityDO();

			// Retrieve base legal entity
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLegalentityDO(reqlegalentityDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = legalentityComponent.findById(transitTxnTransferObj);
			respLegalentityDO = transitTxnTransferObj.getTxnPayload().getLegalentityDO();

			// Retrieve inquiry levels
			ArrayList<String> childDobjList = retrieveConfigInquiryLevelChildObjList(txnTransferObj,
					reqlegalentityDO.getInquiryLevel(),
					yugandharConstants.INQUIRY_LEVEL_APPLICABLE_OBJ_NAME_LEGALENTITY,
					yugandharConstants.FILTER_VALUE_ACTIVE);

			// Retrieve Person or Corporation DO
			if (childDobjList.contains("LePersonDO") || childDobjList.contains("LeCorporationDO")) {
				if (respLegalentityDO.getEntityObjectTypeRefkey().equals("1")) {
					LePersonDO lePersonDO = new LePersonDO();
					lePersonDO.setLegalentityIdpk(reqlegalentityDO.getIdPk());
					lePersonDO.setInquiryFilter(reqlegalentityDO.getInquiryFilter());

					transitTxnPayload = new TxnPayload();
					transitTxnPayload.setLePersonDO(lePersonDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = retrieveLePersonService.process(transitTxnTransferObj);
					respLegalentityDO.setLePersonDO(transitTxnTransferObj.getTxnPayload().getLePersonDO());
				} else if (respLegalentityDO.getEntityObjectTypeRefkey().equals("2")) {
					LeCorporationDO leCorporationDO = new LeCorporationDO();
					leCorporationDO.setLegalentityIdpk(reqlegalentityDO.getIdPk());
					leCorporationDO.setInquiryFilter(reqlegalentityDO.getInquiryFilter());

					transitTxnPayload = new TxnPayload();
					transitTxnPayload.setLeCorporationDO(leCorporationDO);
					transitTxnTransferObj.setTxnPayload(transitTxnPayload);
					transitTxnTransferObj = retrieveLeCorporationService.process(transitTxnTransferObj);
					respLegalentityDO.setLeCorporationDO(transitTxnTransferObj.getTxnPayload().getLeCorporationDO());
				} else {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10026",
							"Validation failed - EntityObjectTypeRefkey is not valid");
				}
			}

			if (childDobjList.contains("LeSystemKeysRegistryDO")) {
				// find all system Keys registry
				transitTxnPayload = new TxnPayload();
				LeSystemKeysRegistryDO transitLeSystemKeysRegistryDO = new LeSystemKeysRegistryDO();
				transitLeSystemKeysRegistryDO.setLegalentityIdpk(reqlegalentityDO.getIdPk());
				transitTxnPayload.setLeSystemKeysRegistryDO(transitLeSystemKeysRegistryDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = leSystemKeysRegistryComponent.findByLegalEntityIdPk(transitTxnTransferObj);
				respLegalentityDO.setLeSystemKeysRegistryDOList(
						transitTxnTransferObj.getTxnPayload().getLeSystemKeysRegistryDOList());
			}

			if (childDobjList.contains("LePreferencesDO")) {
				// find all Preferences
				transitTxnPayload = new TxnPayload();
				LePreferencesDO transitLeLePreferencesDO = new LePreferencesDO();
				transitLeLePreferencesDO.setLegalentityIdpk(reqlegalentityDO.getIdPk());
				transitTxnPayload.setLePreferencesDO(transitLeLePreferencesDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = lePreferencesComponent.findByLegalEntityIdPk(transitTxnTransferObj);
				respLegalentityDO
						.setLePreferencesDOList(transitTxnTransferObj.getTxnPayload().getLePreferencesDOList());
			}

			if (childDobjList.contains("LeIdentifierKycRegistryDO")) {
				// find all IdentifierKyc
				transitTxnPayload = new TxnPayload();
				LeIdentifierKycRegistryDO transitLeLeIdentifierKycRegistryDO = new LeIdentifierKycRegistryDO();
				transitLeLeIdentifierKycRegistryDO.setLegalentityIdpk(reqlegalentityDO.getIdPk());
				transitTxnPayload.setLeIdentifierKycRegistryDO(transitLeLeIdentifierKycRegistryDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = leIdentifierKycRegistryComponent.findByLegalEntityIdPk(transitTxnTransferObj);
				respLegalentityDO.setLeIdentifierKycRegistryDOList(
						transitTxnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDOList());
			}

			if (childDobjList.contains("LeAddressAssocDO")) {
				// find all address associations
				transitTxnPayload = new TxnPayload();
				LeAddressAssocDO leAddressAssocDO = new LeAddressAssocDO();
				leAddressAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
				leAddressAssocDO.setInquiryFilter(respLegalentityDO.getInquiryFilter());
				transitTxnPayload.setLeAddressAssocDO(leAddressAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = findAllLeAddressService.process(transitTxnTransferObj);
				respLegalentityDO
						.setLeAddressAssocDOList(transitTxnTransferObj.getTxnPayload().getLeAddressAssocDOList());
			}

			if (childDobjList.contains("LePhoneAssocDO")) {
				// find All Phone Numbers
				transitTxnPayload = new TxnPayload();
				LePhoneAssocDO lePhoneAssocDO = new LePhoneAssocDO();
				lePhoneAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
				lePhoneAssocDO.setInquiryFilter(respLegalentityDO.getInquiryFilter());
				transitTxnPayload.setLePhoneAssocDO(lePhoneAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = findAllLePhoneService.process(transitTxnTransferObj);
				respLegalentityDO.setLePhoneAssocDOList(transitTxnTransferObj.getTxnPayload().getLePhoneAssocDOList());
			}

			if (childDobjList.contains("LeAccountAssocDO")) {
				// find all Account associations
				transitTxnPayload = new TxnPayload();
				LeAccountAssocDO leAccountAssocDO = new LeAccountAssocDO();
				//copy pagination properties from request object
				commonValidationUtil.copyPaginationProperties(txnTransferObj.getTxnPayload(), transitTxnPayload);
				
				leAccountAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
				leAccountAssocDO.setInquiryFilter(respLegalentityDO.getInquiryFilter());
				leAccountAssocDO.setAccountInquiryLevel(reqlegalentityDO.getAccountInquiryLevel());
				transitTxnPayload.setLeAccountAssocDO(leAccountAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = findAllLeAccountService.process(transitTxnTransferObj);
				respLegalentityDO
						.setLeAccountAssocDOList(transitTxnTransferObj.getTxnPayload().getLeAccountAssocDOList());
				//copy pagination properties to the response object
				commonValidationUtil.copyPaginationProperties(transitTxnTransferObj.getTxnPayload(), respTxnPayload);
			}

			if (childDobjList.contains("LePropertyAssocDO")) {
				// find all Property associations
				transitTxnPayload = new TxnPayload();
				LePropertyAssocDO lePropertyAssocDO = new LePropertyAssocDO();
				lePropertyAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
				lePropertyAssocDO.setInquiryFilter(respLegalentityDO.getInquiryFilter());
				transitTxnPayload.setLePropertyAssocDO(lePropertyAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = findAllLePropertyService.process(transitTxnTransferObj);
				respLegalentityDO
						.setLePropertyAssocDOList(transitTxnTransferObj.getTxnPayload().getLePropertyAssocDOList());
			}

			if (childDobjList.contains("LeVehicleAssocDO")) {
				// find all Vehicle associations
				transitTxnPayload = new TxnPayload();
				LeVehicleAssocDO leVehicleAssocDO = new LeVehicleAssocDO();
				leVehicleAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
				leVehicleAssocDO.setInquiryFilter(respLegalentityDO.getInquiryFilter());
				transitTxnPayload.setLeVehicleAssocDO(leVehicleAssocDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = findAllLeVehicleService.process(transitTxnTransferObj);
				respLegalentityDO
						.setLeVehicleAssocDOList(transitTxnTransferObj.getTxnPayload().getLeVehicleAssocDOList());
			}

			if (childDobjList.contains("MiscellaneousInfoDO")) {
				// find all MiscellaneousInfoDO
				transitTxnPayload = new TxnPayload();
				MiscellaneousInfoDO transitMiscellaneousInfoDO = new MiscellaneousInfoDO();
				transitMiscellaneousInfoDO.setEntityObjectTypeRefkey("3");
				transitMiscellaneousInfoDO.setEntityIdpk(reqlegalentityDO.getIdPk());
				transitTxnPayload.setMiscellaneousInfoDO(transitMiscellaneousInfoDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = miscellaneousInfoComponent.findByEntityIdPk(transitTxnTransferObj);
				respLegalentityDO
						.setMiscellaneousInfoDOList(transitTxnTransferObj.getTxnPayload().getMiscellaneousInfoDOList());
			}
			// Final Response object
			respTxnPayload.setLegalentityDO(respLegalentityDO);
			respTxnTransferObj.setTxnPayload(respTxnPayload);

		} catch (YugandharCommonException yce) {
			logger.error("Composite Service failed", yce);
			throw yce;
		} catch (Exception e) {
			// write the logic to get error message based on error code. Error
			// code is hard-coded here
			logger.error("persist failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
					"createLegalEntityService failed unexpectedly");

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
					"Invalid inquiry level provided in the request for LegalentityDO");
		}

		return childDobjList;
	}

	/**
	 * perform the common validation that LegalentityDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if LegalentityDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeExecution(TxnTransferObj txnTransferObj) {

		if (null == txnTransferObj.getTxnPayload().getLegalentityDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"LegalentityDO is needed in the request");
		}
		LegalentityDO theLegalentityDO = (LegalentityDO) txnTransferObj.getTxnPayload().getLegalentityDO();
		if (null == theLegalentityDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"LegalentityDO.idpk should not be null");
		}

		if (null == theLegalentityDO.getInquiryLevel() || theLegalentityDO.getInquiryLevel().isEmpty()) {
			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_inqlevel_defaultvalue_retrieve_LegalentityDO,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			theLegalentityDO.setInquiryLevel(theConfigAppPropertiesDO.getValue());

		}

	}
}
