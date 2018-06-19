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
import com.yugandhar.common.transobj.TxnHeader;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.jms.YugJMSMessageSender;
import com.yugandhar.mdm.batch.component.BatchEntityToProcessComponent;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.corecomponent.LeIdentifierKycRegistryComponent;
import com.yugandhar.mdm.corecomponent.LePreferencesComponent;
import com.yugandhar.mdm.corecomponent.LeSystemKeysRegistryComponent;
import com.yugandhar.mdm.corecomponent.LeToLeRelationshipComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.corecomponent.MiscellaneousInfoComponent;
import com.yugandhar.mdm.extern.dobj.BatchEntityToProcessDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;
import com.yugandhar.mdm.extern.dobj.LeAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.LeIdentifierKycRegistryDO;
import com.yugandhar.mdm.extern.dobj.LePhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.LePreferencesDO;
import com.yugandhar.mdm.extern.dobj.LePropertyAssocDO;
import com.yugandhar.mdm.extern.dobj.LeSystemKeysRegistryDO;
import com.yugandhar.mdm.extern.dobj.LeVehicleAssocDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.MiscellaneousInfoDO;
import com.yugandhar.mdm.extern.dobj.PerformLeMatchRequestDO;
import com.yugandhar.mdm.match.rules.LeCorporationFuzzyMatchRule;
import com.yugandhar.mdm.match.rules.LePersonDeterministicMatchRule;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.rest.controller.RequestProcessor;

/**
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.CreateLegalEntityService")
public class CreateLegalEntityService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	private static final Logger matchEngineLogger = LoggerFactory
			.getLogger(yugandharConstants.YUGANDHAR_MATCH_ENGINE_APPENDER);

	TxnTransferObj txnTransferObjResponse;
	LegalentityDO respLegalentityDO;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	LegalentityComponent legalentityComponent;

	@Autowired
	CreateLePersonService createLePersonService;

	@Autowired
	CreateLeCorporationService createLeCorporationService;

	@Autowired
	CreateLeAddressService createLeAddressService;

	@Autowired
	CreateLePhoneService createLePhoneService;

	@Autowired
	LeSystemKeysRegistryComponent leSystemKeysRegistryComponent;

	@Autowired
	LePreferencesComponent lePreferencesComponent;

	@Autowired
	LeIdentifierKycRegistryComponent leIdentifierKycRegistryComponent;

	@Autowired
	CreateLeAccountService createLeAccountService;

	@Autowired
	CreateLePropertyService createLePropertyService;

	@Autowired
	CreateLeVehicleService createLeVehicleService;

	@Autowired
	MiscellaneousInfoComponent miscellaneousInfoComponent;

	@Autowired
	LeToLeRelationshipComponent leToLeRelationshipComponent;

	@Autowired
	LeCorporationFuzzyMatchRule theLeCorporationFuzzyMatchRule;

	@Autowired
	LePersonDeterministicMatchRule theLePersonDeterministicMatchRule;

	@Autowired
	PerformLeMatchService thePerformLeMatchService;

	@Autowired
	YugJMSMessageSender theYugJMSMessageSender;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;
	
	@Autowired
	BatchEntityToProcessComponent theBatchEntityToProcessComponent;
	
	@Autowired
	RequestProcessor RequestProcessor;

	public CreateLegalEntityService() {
		txnTransferObjResponse = new TxnTransferObj();
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
			// TODO logic here

			LegalentityDO reqlegalentityDO = txnTransferObj.getTxnPayload().getLegalentityDO();

			// persist base legal entity
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLegalentityDO(reqlegalentityDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = legalentityComponent.persist(transitTxnTransferObj);
			respLegalentityDO = transitTxnTransferObj.getTxnPayload().getLegalentityDO();

			// Validate LePerson or LeCorporation Objects
			if (null == reqlegalentityDO.getLePersonDO() && null == reqlegalentityDO.getLeCorporationDO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10048",
						"Validation failed - Either lePersonDO or leCorporationDO is required in the request");
			}

			if (respLegalentityDO.getEntityObjectTypeRefkey().equals("1") && null == reqlegalentityDO.getLePersonDO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10049",
						"Validation failed - required data object as per entityObjectTypeRefkey not present");

			} else if (respLegalentityDO.getEntityObjectTypeRefkey().equals("2")
					&& null == reqlegalentityDO.getLeCorporationDO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10049",
						"Validation failed - required data object as per entityObjectTypeRefkey not present");

			}

			// process legal entities
			if (respLegalentityDO.getEntityObjectTypeRefkey().equals("1")) {
				// Process Person
				PrimaryKeyDO primaryKeyDO = new PrimaryKeyDO();
				primaryKeyDO.setIdPk(respLegalentityDO.getIdPk());
				reqlegalentityDO.getLePersonDO().setPrimaryKeyDO(primaryKeyDO);

				transitTxnPayload = new TxnPayload();
				transitTxnPayload.setLePersonDO(reqlegalentityDO.getLePersonDO());
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = createLePersonService.process(transitTxnTransferObj);
				respLegalentityDO.setLePersonDO(transitTxnTransferObj.getTxnPayload().getLePersonDO());

			} else if (respLegalentityDO.getEntityObjectTypeRefkey().equals("2")) {
				// Process Corporation
				PrimaryKeyDO primaryKeyDO = new PrimaryKeyDO();
				primaryKeyDO.setIdPk(respLegalentityDO.getIdPk());
				reqlegalentityDO.getLeCorporationDO().setPrimaryKeyDO(primaryKeyDO);

				transitTxnPayload = new TxnPayload();
				transitTxnPayload.setLeCorporationDO(reqlegalentityDO.getLeCorporationDO());
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				transitTxnTransferObj = createLeCorporationService.process(transitTxnTransferObj);
				respLegalentityDO.setLeCorporationDO(transitTxnTransferObj.getTxnPayload().getLeCorporationDO());
			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10026",
						"Validation failed - EntityObjectTypeRefkey is not valid");
			}

			// Process LeSystemRegistryKeys
			if (null != reqlegalentityDO.getLeSystemKeysRegistryDOList()
					&& reqlegalentityDO.getLeSystemKeysRegistryDOList().size() > 0) {
				Iterator<LeSystemKeysRegistryDO> leSysKeyRegIterator = reqlegalentityDO.getLeSystemKeysRegistryDOList()
						.iterator();
				List<LeSystemKeysRegistryDO> respLePhoneAssocDOList = new ArrayList<LeSystemKeysRegistryDO>();

				while (leSysKeyRegIterator.hasNext()) {
					LeSystemKeysRegistryDO reqLeSystemKeysRegistryDO = leSysKeyRegIterator.next();

					if (null != reqLeSystemKeysRegistryDO) {
						transitTxnPayload = new TxnPayload();
						reqLeSystemKeysRegistryDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
						transitTxnPayload.setLeSystemKeysRegistryDO(reqLeSystemKeysRegistryDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = leSystemKeysRegistryComponent.persist(transitTxnTransferObj);
						respLePhoneAssocDOList.add(transitTxnTransferObj.getTxnPayload().getLeSystemKeysRegistryDO());

					}

				}
				respLegalentityDO.setLeSystemKeysRegistryDOList(respLePhoneAssocDOList);

			}

			// Process LePreferences
			if (null != reqlegalentityDO.getLePreferencesDOList()
					&& reqlegalentityDO.getLePreferencesDOList().size() > 0) {
				Iterator<LePreferencesDO> leprefIterator = reqlegalentityDO.getLePreferencesDOList().iterator();
				List<LePreferencesDO> respLePhoneAssocDOList = new ArrayList<LePreferencesDO>();

				while (leprefIterator.hasNext()) {
					LePreferencesDO reqLePreferencesDO = leprefIterator.next();

					if (null != reqLePreferencesDO) {
						transitTxnPayload = new TxnPayload();
						reqLePreferencesDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
						transitTxnPayload.setLePreferencesDO(reqLePreferencesDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = lePreferencesComponent.persist(transitTxnTransferObj);
						respLePhoneAssocDOList.add(transitTxnTransferObj.getTxnPayload().getLePreferencesDO());

					}

				}
				respLegalentityDO.setLePreferencesDOList(respLePhoneAssocDOList);

			}

			// Process LeIdentifierKYC
			if (null != reqlegalentityDO.getLeIdentifierKycRegistryDOList()
					&& reqlegalentityDO.getLeIdentifierKycRegistryDOList().size() > 0) {
				Iterator<LeIdentifierKycRegistryDO> leIdentifierKycIterator = reqlegalentityDO
						.getLeIdentifierKycRegistryDOList().iterator();
				List<LeIdentifierKycRegistryDO> respLePhoneAssocDOList = new ArrayList<LeIdentifierKycRegistryDO>();

				while (leIdentifierKycIterator.hasNext()) {
					LeIdentifierKycRegistryDO reqLeIdentifierKycRegistryDO = leIdentifierKycIterator.next();

					if (null != reqLeIdentifierKycRegistryDO) {
						transitTxnPayload = new TxnPayload();
						reqLeIdentifierKycRegistryDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
						transitTxnPayload.setLeIdentifierKycRegistryDO(reqLeIdentifierKycRegistryDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = leIdentifierKycRegistryComponent.persist(transitTxnTransferObj);
						respLePhoneAssocDOList
								.add(transitTxnTransferObj.getTxnPayload().getLeIdentifierKycRegistryDO());

					}

				}
				respLegalentityDO.setLeIdentifierKycRegistryDOList(respLePhoneAssocDOList);

			}

			// Process Addresses
			if (null != reqlegalentityDO.getLeAddressAssocDOList()) {

				Iterator<LeAddressAssocDO> addrIterator = reqlegalentityDO.getLeAddressAssocDOList().iterator();
				List<LeAddressAssocDO> respLeAddressAssocDOList = new ArrayList<LeAddressAssocDO>();

				while (addrIterator.hasNext()) {
					LeAddressAssocDO reqLeAddressAssocDO = addrIterator.next();

					if (null != reqLeAddressAssocDO) {
						transitTxnPayload = new TxnPayload();
						reqLeAddressAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
						transitTxnPayload.setLeAddressAssocDO(reqLeAddressAssocDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = createLeAddressService.process(transitTxnTransferObj);
						respLeAddressAssocDOList.add(transitTxnTransferObj.getTxnPayload().getLeAddressAssocDO());

					}

				}
				respLegalentityDO.setLeAddressAssocDOList(respLeAddressAssocDOList);

			}

			// process Phone associations
			if (null != reqlegalentityDO.getLePhoneAssocDOList()) {

				Iterator<LePhoneAssocDO> addrIterator = reqlegalentityDO.getLePhoneAssocDOList().iterator();
				List<LePhoneAssocDO> respLePhoneAssocDOList = new ArrayList<LePhoneAssocDO>();

				while (addrIterator.hasNext()) {
					LePhoneAssocDO reqLePhoneAssocDO = addrIterator.next();

					if (null != reqLePhoneAssocDO) {
						transitTxnPayload = new TxnPayload();
						reqLePhoneAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
						transitTxnPayload.setLePhoneAssocDO(reqLePhoneAssocDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = createLePhoneService.process(transitTxnTransferObj);
						respLePhoneAssocDOList.add(transitTxnTransferObj.getTxnPayload().getLePhoneAssocDO());

					}

				}
				respLegalentityDO.setLePhoneAssocDOList(respLePhoneAssocDOList);

			}

			// Process Accounts
			if (null != reqlegalentityDO.getLeAccountAssocDOList()) {

				Iterator<LeAccountAssocDO> addrIterator = reqlegalentityDO.getLeAccountAssocDOList().iterator();
				List<LeAccountAssocDO> respLeAccountAssocDOList = new ArrayList<LeAccountAssocDO>();

				while (addrIterator.hasNext()) {
					LeAccountAssocDO reqLeAccountAssocDO = addrIterator.next();

					if (null != reqLeAccountAssocDO) {
						transitTxnPayload = new TxnPayload();
						reqLeAccountAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
						transitTxnPayload.setLeAccountAssocDO(reqLeAccountAssocDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = createLeAccountService.process(transitTxnTransferObj);
						respLeAccountAssocDOList.add(transitTxnTransferObj.getTxnPayload().getLeAccountAssocDO());

					}

				}
				respLegalentityDO.setLeAccountAssocDOList(respLeAccountAssocDOList);

			}

			// Process Propertys
			if (null != reqlegalentityDO.getLePropertyAssocDOList()) {

				Iterator<LePropertyAssocDO> addrIterator = reqlegalentityDO.getLePropertyAssocDOList().iterator();
				List<LePropertyAssocDO> respLePropertyAssocDOList = new ArrayList<LePropertyAssocDO>();

				while (addrIterator.hasNext()) {
					LePropertyAssocDO reqLePropertyAssocDO = addrIterator.next();

					if (null != reqLePropertyAssocDO) {
						transitTxnPayload = new TxnPayload();
						reqLePropertyAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
						transitTxnPayload.setLePropertyAssocDO(reqLePropertyAssocDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = createLePropertyService.process(transitTxnTransferObj);
						respLePropertyAssocDOList.add(transitTxnTransferObj.getTxnPayload().getLePropertyAssocDO());

					}

				}
				respLegalentityDO.setLePropertyAssocDOList(respLePropertyAssocDOList);

			}

			// Process Vehicles
			if (null != reqlegalentityDO.getLeVehicleAssocDOList()) {

				Iterator<LeVehicleAssocDO> addrIterator = reqlegalentityDO.getLeVehicleAssocDOList().iterator();
				List<LeVehicleAssocDO> respLeVehicleAssocDOList = new ArrayList<LeVehicleAssocDO>();

				while (addrIterator.hasNext()) {
					LeVehicleAssocDO reqLeVehicleAssocDO = addrIterator.next();

					if (null != reqLeVehicleAssocDO) {
						transitTxnPayload = new TxnPayload();
						reqLeVehicleAssocDO.setLegalentityIdpk(respLegalentityDO.getIdPk());
						transitTxnPayload.setLeVehicleAssocDO(reqLeVehicleAssocDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = createLeVehicleService.process(transitTxnTransferObj);
						respLeVehicleAssocDOList.add(transitTxnTransferObj.getTxnPayload().getLeVehicleAssocDO());

					}

				}
				respLegalentityDO.setLeVehicleAssocDOList(respLeVehicleAssocDOList);

			}

			// Process MiscellaneousInfo
			if (null != reqlegalentityDO.getMiscellaneousInfoDOList()
					&& reqlegalentityDO.getMiscellaneousInfoDOList().size() > 0) {
				Iterator<MiscellaneousInfoDO> itrMiscellaneousInfoDO = reqlegalentityDO.getMiscellaneousInfoDOList()
						.iterator();
				List<MiscellaneousInfoDO> respLePhoneAssocDOList = new ArrayList<MiscellaneousInfoDO>();

				while (itrMiscellaneousInfoDO.hasNext()) {
					MiscellaneousInfoDO reqMiscellaneousInfoDO = itrMiscellaneousInfoDO.next();

					if (null != reqMiscellaneousInfoDO) {
						transitTxnPayload = new TxnPayload();
						reqMiscellaneousInfoDO.setEntityObjectTypeRefkey("3");
						reqMiscellaneousInfoDO.setEntityIdpk(respLegalentityDO.getIdPk());
						transitTxnPayload.setMiscellaneousInfoDO(reqMiscellaneousInfoDO);
						transitTxnTransferObj.setTxnPayload(transitTxnPayload);
						transitTxnTransferObj = miscellaneousInfoComponent.persist(transitTxnTransferObj);
						respLePhoneAssocDOList.add(transitTxnTransferObj.getTxnPayload().getMiscellaneousInfoDO());

					}

				}
				respLegalentityDO.setMiscellaneousInfoDOList(respLePhoneAssocDOList);

			}

			// Final Response object
			respTxnPayload.setLegalentityDO(respLegalentityDO);
			respTxnTransferObj.setTxnPayload(respTxnPayload);

			performLeMatch(transitTxnPayload, transitTxnTransferObj, txnTransferObj);

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

	private void performLeMatch(TxnPayload transitTxnPayload, TxnTransferObj transitTxnTransferObj,
			TxnTransferObj txnTransferObj) {

		String candidateSearchProcessingMode = null;
		boolean match_le_framework_enabled = false;

		ConfigAppPropertiesDO theConfigAppPropertiesDOForleMatch = configAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_match_le_framework_enabled,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		if (null == theConfigAppPropertiesDOForleMatch
				|| commonValidationUtil.isNullOrEmpty(theConfigAppPropertiesDOForleMatch.getValue())) {
			match_le_framework_enabled = false;
		} else {
			if (theConfigAppPropertiesDOForleMatch.getValue().equals(yugandharConstants.FLAG_true)) {
				match_le_framework_enabled = true;
			}
		}

		if (match_le_framework_enabled) {

			// realtime,near-realtime or batch
			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_match_le_candidateSearch_processing_mode,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigAppPropertiesDO
					|| commonValidationUtil.isNullOrEmpty(theConfigAppPropertiesDO.getValue())) {
				candidateSearchProcessingMode = yugandharConstants.com_yugandhar_match_le_candidateSearch_processing_mode_batch;
			} else {
				candidateSearchProcessingMode = theConfigAppPropertiesDO.getValue();
			}

			if (candidateSearchProcessingMode.equalsIgnoreCase(
					yugandharConstants.com_yugandhar_match_le_candidateSearch_processing_mode_batch)) {
				
				// thePerformLeMatchService.process(transitTxnTransferObj);
				TxnHeader txnHeader = new TxnHeader(transitTxnTransferObj.getTxnHeader());
				txnHeader.setTransactionServiceName("createRefBatchActionStatusBase");
				transitTxnTransferObj.setTxnHeader(txnHeader);
				
				BatchEntityToProcessDO theBatchEntityToProcessDO = new BatchEntityToProcessDO();
				theBatchEntityToProcessDO.setEntityObjectTypeRefkey("3");
				theBatchEntityToProcessDO.setEntityIdpk(respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk());
				theBatchEntityToProcessDO.setBatchProposedActionRefkey("2");
				theBatchEntityToProcessDO.setBatchActionStatusRefkey("1");
				theBatchEntityToProcessDO.setEntryMadeBySubsystemName("MATCH FRAMEWORK");
				transitTxnPayload.setBatchEntityToProcessDO(theBatchEntityToProcessDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				theBatchEntityToProcessComponent.persist(transitTxnTransferObj);

				//make an entry in BATCH_ENTITY_TO_PROCESS table with proposed action
				matchEngineLogger.warn("CreateLegalEntityService.process for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
						+ " candidateSearch to be performed in batch mode");
				logger.warn("CreateLegalEntityService.process for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
						+ " candidateSearch to be performed in batch mode");

			} else if (candidateSearchProcessingMode.equalsIgnoreCase(
					yugandharConstants.com_yugandhar_match_le_candidateSearch_processing_mode_realtime)) {

				if (matchEngineLogger.isInfoEnabled()) {
					matchEngineLogger.info("CreateLegalEntityService.process for TxnMessageId:"
							+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
							+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
							+ " performing realtime candidateSearch");
				}

				if (logger.isInfoEnabled()) {
					logger.info("CreateLegalEntityService.process for TxnMessageId:"
							+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
							+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
							+ " performing realtime candidateSearch ");
				}

				// Invoke Match framework
				PerformLeMatchRequestDO thePerformLeMatchRequestDO = new PerformLeMatchRequestDO();
				thePerformLeMatchRequestDO.setLegalentityDO(respTxnTransferObj.getTxnPayload().getLegalentityDO());
				transitTxnPayload.setPerformLeMatchRequestDO(thePerformLeMatchRequestDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				// thePerformLeMatchService.process(transitTxnTransferObj);
				TxnHeader txnHeader = new TxnHeader(transitTxnTransferObj.getTxnHeader());
				txnHeader.setTransactionServiceName("performLeMatch");
				transitTxnTransferObj.setTxnHeader(txnHeader);
				thePerformLeMatchService.process(transitTxnTransferObj);

			} else if (candidateSearchProcessingMode.equalsIgnoreCase(
					yugandharConstants.com_yugandhar_match_le_candidateSearch_processing_mode_nearrealtime)) {
				// Invoke Match framework
				PerformLeMatchRequestDO thePerformLeMatchRequestDO = new PerformLeMatchRequestDO();
				thePerformLeMatchRequestDO.setLegalentityDO(respTxnTransferObj.getTxnPayload().getLegalentityDO());
				transitTxnPayload.setPerformLeMatchRequestDO(thePerformLeMatchRequestDO);
				transitTxnTransferObj.setTxnPayload(transitTxnPayload);
				// thePerformLeMatchService.process(transitTxnTransferObj);
				TxnHeader txnHeader = new TxnHeader(transitTxnTransferObj.getTxnHeader());
				txnHeader.setTransactionServiceName("performLeMatch");
				transitTxnTransferObj.setTxnHeader(txnHeader);

				//RequestProcessor.processMessageAsync(transitTxnTransferObj);
				String textMessage = theYugJMSMessageSender.convertObjectToJSONString(transitTxnTransferObj);
				theYugJMSMessageSender.sendTextMessageToDefaultRequestQueue(textMessage);

				if (matchEngineLogger.isInfoEnabled()) {
					matchEngineLogger.info("CreateLegalEntityService.process for TxnMessageId:"
							+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
							+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
							+ " Sent to MatchEngine Queue for processing ");
				}

				if (logger.isInfoEnabled()) {
					logger.info("CreateLegalEntityService.process for TxnMessageId:"
							+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
							+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
							+ " Sent to MatchEngine Queue for processing ");
				}
			} else {
				matchEngineLogger.warn("CreateLegalEntityService.process for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
						+ " candidateSearch not performed as configuration property "
						+ yugandharConstants.com_yugandhar_match_le_candidateSearch_processing_mode_nearrealtime
						+ " value is not correct ");
				logger.warn("CreateLegalEntityService.process for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
						+ " candidateSearch not performed as configuration property "
						+ yugandharConstants.com_yugandhar_match_le_candidateSearch_processing_mode_nearrealtime
						+ " value is not correct ");
			}

		} else {
			matchEngineLogger.warn("CreateLegalEntityService.process for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
					+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
					+ " candidateSearch not performed as match framwork is disabled");
			logger.warn("CreateLegalEntityService.process for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
					+ respTxnTransferObj.getTxnPayload().getLegalentityDO().getIdPk()
					+ " candidateSearch not performed as match framwork is disabled");
		}

	}

}
