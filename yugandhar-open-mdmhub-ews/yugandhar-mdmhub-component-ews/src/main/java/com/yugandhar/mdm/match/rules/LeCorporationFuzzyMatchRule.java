package com.yugandhar.mdm.match.rules;

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
import com.yugandhar.mdm.composite.service.SearchMatchCandidateLEService;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.CorporationnamesDO;
import com.yugandhar.mdm.extern.dobj.LeAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.LeIdentifierKycRegistryDO;
import com.yugandhar.mdm.extern.dobj.LePhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.MatchCandidateLeRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefMatchScoreDO;
import com.yugandhar.mdm.extern.dobj.RefMatchThresholdDO;
import com.yugandhar.mdm.extern.dobj.SearchMatchCandidateLERequestDO;
import com.yugandhar.mdm.match.component.MatchCandidateLeRegistryComponent;
import com.yugandhar.mdm.match.componentref.RefMatchScoreComponent;
import com.yugandhar.mdm.match.componentref.RefMatchThresholdComponent;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.match.rules.LeCorporationFuzzyMatchRule")
public class LeCorporationFuzzyMatchRule {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	private static final Logger matchEngineLogger = LoggerFactory
			.getLogger(yugandharConstants.YUGANDHAR_MATCH_ENGINE_APPENDER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	SearchMatchCandidateLEService theSearchMatchCandidateLEService;

	@Autowired
	RefMatchThresholdComponent theRefMatchThresholdComponent;

	@Autowired
	RefMatchScoreComponent theRefMatchScoreComponent;

	@Autowired
	MatchCandidateLeRegistryComponent theMatchCandidateLeRegistryComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@Autowired
	LeAddressAndPhoneMatchRule theLeAddressMatchRule;

	@Autowired
	YugandharMatchingUtils theYugandharMatchingUtils;

	public LeCorporationFuzzyMatchRule() {
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

			performCommonvalidationBeforeExecution(txnTransferObj);

			if (logger.isInfoEnabled()) {
				logger.info("LeCorporationFuzzyMatchRule.process - Started Matching rule for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO().getLegalentityDO().getIdPk()
						+ " detailed logs are captured in match engine logs");
			}

			// set inquiry level
			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_match_le_Fuzzy_LeCorporation_inquiryLevel_default,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			
			
			//retrieve LegalEntity
			LegalentityDO reqLegalentityDO = theYugandharMatchingUtils.retrieveLegalEntitiesAsPerInquiryLevel(txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO().getLegalentityDO(),
					txnTransferObj, theConfigAppPropertiesDO.getValue());
			
			txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO().setLegalentityDO(reqLegalentityDO);
			
			//create searchMatchCandidateLEDO 
			SearchMatchCandidateLERequestDO theSearchMatchCandidateLERequestDO = createRequestSearchMatchCandidateLEDO(
					txnTransferObj);
			theSearchMatchCandidateLERequestDO.setPhoneticSearch(false);
			theSearchMatchCandidateLERequestDO.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
			theSearchMatchCandidateLERequestDO.setInquiryLevel(theConfigAppPropertiesDO.getValue());

			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setSearchMatchCandidateLERequestDO(theSearchMatchCandidateLERequestDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = theSearchMatchCandidateLEService.process(transitTxnTransferObj);
			List<LegalentityDO> probableCandidateLegalentityDOList = transitTxnTransferObj.getTxnPayload().getLegalentityDOList();
			
			
			if (null != transitTxnTransferObj.getTxnPayload().getLegalentityDOList()
					&& transitTxnTransferObj.getTxnPayload().getLegalentityDOList().size() > 0) {
				performMatching(reqLegalentityDO, probableCandidateLegalentityDOList, transitTxnTransferObj);
			}

			if (logger.isInfoEnabled()) {
				logger.info("LeCorporationFuzzyMatchRule.process - Finished Matching rule for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO().getLegalentityDO().getIdPk()
						+ " detailed logs are captured in match engine logs");
			}

			respTxnTransferObj.setTxnPayload(respTxnPayload);

		} catch (YugandharCommonException yce) {
			logger.error("PerformLeMatchService Composite Service failed", yce);
			throw yce;
		} catch (Exception e) {
			// write the logic to get error message based on error code. Error
			// code is hard-coded here
			logger.error("PerformLeMatchService Composite Service failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
					"PerformLeMatchService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

	}

	private String matchCorporationLE(LegalentityDO reqLegalentityDO, LegalentityDO probableCandidateLegalentityDO,
			TxnTransferObj txnTransferObj) {

		if (matchEngineLogger.isInfoEnabled()) {
			matchEngineLogger.info("LeCorporationFuzzyMatchRule.matchCorporationLE for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
					+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
					+ probableCandidateLegalentityDO.getIdPk() + " Started");
		}

		String corporationNameMatch = "N";
		String identificationMatch = "N";
		String addressMatch = "N";
		String phoneNumberMatch = "N";

		// Match Corporation Names
		if (null != reqLegalentityDO.getLeCorporationDO().getCorporationnamesDOList()
				&& null != probableCandidateLegalentityDO.getLeCorporationDO().getCorporationnamesDOList()) {
			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
						+ probableCandidateLegalentityDO.getIdPk() + " starting to match Corporation names");
			}
			for (CorporationnamesDO reqCorporationnamesDO : reqLegalentityDO.getLeCorporationDO()
					.getCorporationnamesDOList()) {
				for (CorporationnamesDO probableCandidateCorporationnamesDO : probableCandidateLegalentityDO
						.getLeCorporationDO().getCorporationnamesDOList()) {

					corporationNameMatch = matchCorporationName(reqCorporationnamesDO,
							probableCandidateCorporationnamesDO, txnTransferObj);
					if (corporationNameMatch.equals("Y"))
						;
					break;
				}
			}

			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
						+ probableCandidateLegalentityDO.getIdPk() + " Finished matching Corporation names");
			}

		}

		// Match IdentifierKyc
		if (null != reqLegalentityDO.getLeIdentifierKycRegistryDOList()
				&& null != probableCandidateLegalentityDO.getLeIdentifierKycRegistryDOList()) {

			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
						+ probableCandidateLegalentityDO.getIdPk() + " Started matching LeIdentifierKycRegistryDO");
			}

			for (LeIdentifierKycRegistryDO reqLeIdentifierKycRegistryDO : reqLegalentityDO
					.getLeIdentifierKycRegistryDOList()) {
				for (LeIdentifierKycRegistryDO probableCandidateLeIdentifierKycRegistryDO : probableCandidateLegalentityDO
						.getLeIdentifierKycRegistryDOList()) {
					if (reqLeIdentifierKycRegistryDO.getIdentificationNumber()
							.equalsIgnoreCase(probableCandidateLeIdentifierKycRegistryDO.getIdentificationNumber())
							&& reqLeIdentifierKycRegistryDO.getIdentificationTypeRefkey()
									.equals(probableCandidateLeIdentifierKycRegistryDO.getIdentificationTypeRefkey())) {
						identificationMatch = "Y";
						break;
					}
				}

				if (matchEngineLogger.isInfoEnabled()) {
					matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
							+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
							+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
							+ probableCandidateLegalentityDO.getIdPk() + " identificationMatch: "
							+ identificationMatch);
				}

				if (identificationMatch.equals("Y"))
					break;

			}

			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
						+ probableCandidateLegalentityDO.getIdPk() + " Finished matching LeIdentifierKycRegistryDO");
			}
		}

		// match Corporation Address
		if (null != reqLegalentityDO.getLeAddressAssocDOList()
				&& null != probableCandidateLegalentityDO.getLeAddressAssocDOList()) {

			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
						+ probableCandidateLegalentityDO.getIdPk() + " Started matching LeAddressAssocDO");
			}
			for (LeAddressAssocDO reqLeAddressAssocDO : reqLegalentityDO.getLeAddressAssocDOList()) {
				for (LeAddressAssocDO probableCandidateLeAddressAssocDO : probableCandidateLegalentityDO
						.getLeAddressAssocDOList()) {
					addressMatch = theLeAddressMatchRule.matchAddress(reqLeAddressAssocDO.getAddressDO(),
							probableCandidateLeAddressAssocDO.getAddressDO(), txnTransferObj);
				}

				if (addressMatch.equals("Y"))
					break;

			}

			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
						+ probableCandidateLegalentityDO.getIdPk() + " Started matching LeAddressAssocDO");
			}
		}

		// Match Phone Numbers
		if (null != reqLegalentityDO.getLePhoneAssocDOList()
				&& null != probableCandidateLegalentityDO.getLePhoneAssocDOList()) {

			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
						+ probableCandidateLegalentityDO.getIdPk() + " Started matching LePhoneAssocDO");
			}

			for (LePhoneAssocDO reqLePhoneAssocDO : reqLegalentityDO.getLePhoneAssocDOList()) {
				for (LePhoneAssocDO probableCandidateLePhoneAssocDO : probableCandidateLegalentityDO
						.getLePhoneAssocDOList()) {

					phoneNumberMatch = theLeAddressMatchRule.matchPhoneNumber(reqLePhoneAssocDO,
							probableCandidateLePhoneAssocDO, txnTransferObj);
					if (phoneNumberMatch.equals("Y"))
						break;
				}
				if (matchEngineLogger.isInfoEnabled()) {
					matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
							+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
							+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
							+ probableCandidateLegalentityDO.getIdPk() + " phoneNumberMatch:" + phoneNumberMatch);
				}

				if (phoneNumberMatch.equals("Y"))
					break;

			}

			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
						+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
						+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
						+ probableCandidateLegalentityDO.getIdPk() + " Finished matching LePhoneAssocDO");
			}
		}

		StringBuffer attributeMatchPattern = new StringBuffer();
		attributeMatchPattern.append(corporationNameMatch);
		attributeMatchPattern.append(identificationMatch);
		attributeMatchPattern.append(addressMatch);
		attributeMatchPattern.append(phoneNumberMatch);

		if (matchEngineLogger.isInfoEnabled()) {
			matchEngineLogger.info("LeCorporationFuzzyMatchRule.matchCorporationLE for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
					+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpk:"
					+ probableCandidateLegalentityDO.getIdPk() + " attributeMatchPattern:"
					+ attributeMatchPattern.toString() + " Finished");
		}

		return attributeMatchPattern.toString();

	}

	private void performMatching(LegalentityDO reqLegalentityDO, List<LegalentityDO> probableCandidateLegalentityDOList,
			TxnTransferObj txnTransferObj) {

		// Just for logging
		StringBuffer candidateLeIdsForLogging = new StringBuffer();
		for (LegalentityDO probableCandidateLegalentityDO : probableCandidateLegalentityDOList) {
			candidateLeIdsForLogging.append(probableCandidateLegalentityDO.getIdPk() + ",");
		}

		if (matchEngineLogger.isInfoEnabled()) {
			matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching  for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
					+ reqLegalentityDO.getIdPk() + " Candidate LegalEntityIdpkList: "
					+ candidateLeIdsForLogging.toString());
		}

		for (LegalentityDO probableCandidateLegalentityDO : probableCandidateLegalentityDOList) {

			if (!theYugandharMatchingUtils.isNullOrEmpty(reqLegalentityDO.getIdPk())) {
				if (probableCandidateLegalentityDO.getIdPk().equals(reqLegalentityDO.getIdPk())) {
					continue;
				}
			}

			String attributeMatchPattern = matchCorporationLE(reqLegalentityDO, probableCandidateLegalentityDO,
					txnTransferObj);

			RefMatchScoreDO respRefMatchScoreDO = theRefMatchScoreComponent.executeRepositoryQuery(
					YugandharMatchingConstants.MATCH_ENTITY_OBJECT_NAME_CORPORATION, attributeMatchPattern,
					yugandharConstants.FILTER_VALUE_ACTIVE);

			if (null == respRefMatchScoreDO) {

				if (matchEngineLogger.isInfoEnabled()) {
					matchEngineLogger
							.info("LePersonDeterministicMatchRule.performMatching result action  for TxnMessageId:"
									+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
									+ reqLegalentityDO.getIdPk() + " probableCandidateLegalentityDO: "
									+ probableCandidateLegalentityDO.getIdPk()
									+ " Match Score (respRefMatchScoreDO) not found for attributeMatchPattern: "
									+ attributeMatchPattern);

				}
			} else if (matchEngineLogger.isInfoEnabled()) {
					matchEngineLogger
							.info("LeCorporationFuzzyMatchRule.performMatching result action  for TxnMessageId:"
									+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
									+ reqLegalentityDO.getIdPk() + " probableCandidateLegalentityDO: "
									+ probableCandidateLegalentityDO.getIdPk() + " MATCH_ENTITY_OBJECT_NAME:"
									+ respRefMatchScoreDO.getMatchEntityObjectName() + "|" + " MATCH_ATTR_PATTERN:"
									+ respRefMatchScoreDO.getMatchAttrPattern() + "|" + " MATCH_RESULT_REFKEY:"
									+ respRefMatchScoreDO.getMatchResultRefkey() + "|"
									+ " MATCH_PROPOSED_ACTION_REFKEY:"
									+ respRefMatchScoreDO.getMatchProposedActionRefkey());
			}
			// if response is null then its non match
			if (null == respRefMatchScoreDO) {
				// do nothing
			} else if (respRefMatchScoreDO.getMatchProposedActionRefkey().equals("1")) {
				// do auto merge or other logic to be done for perfect match
				// although proposed Action 1 is auto merge, for the OOTB rule
				// we will be creating candidates
				addToMatchCandidateLeRegistry(reqLegalentityDO.getIdPk(), probableCandidateLegalentityDO.getIdPk(),
						attributeMatchPattern.toString(), "2", "1", txnTransferObj);
			} else if (respRefMatchScoreDO.getMatchProposedActionRefkey().equals("2")) {
				// do auto merge or other logic to be done for perfect match
				// addToMatchCandidateLeRegistry(legalentityIdpk,
				// candidateLegalentityidpk,matchPattern,
				// matchProposedActionRefkey, matchActionstatusRefkey,
				// txnTransferObj)
				addToMatchCandidateLeRegistry(reqLegalentityDO.getIdPk(), probableCandidateLegalentityDO.getIdPk(),
						attributeMatchPattern.toString(), "2", "1", txnTransferObj);
			}

		}
	}

	public String matchCorporationName(CorporationnamesDO reqCorporationNameDO,
			CorporationnamesDO corporationNameToMatchDO, TxnTransferObj txnTransferObj) {

		String isCorporationNameMatch = "N";
		Double distanceAfterDecayCorporationName = (double) 0;

		Double daysSinceLastUpdate = theYugandharMatchingUtils
				.calculateDaysSinceLastUpdate(corporationNameToMatchDO.getUpdatedTs());

		Double distanceCorporationName = theYugandharMatchingUtils.getJaroWinklerDistancePercent(
				reqCorporationNameDO.getCorporationName(), corporationNameToMatchDO.getCorporationName());

		if (distanceCorporationName != 0) {
			RefMatchThresholdDO theRefMatchThresholdDO = theRefMatchThresholdComponent.executeRepositoryQuery(
					YugandharMatchingConstants.CORPORATION_ATTR_NAME, yugandharConstants.FILTER_VALUE_ACTIVE);

			distanceAfterDecayCorporationName = theYugandharMatchingUtils.getDistanceAfterDecay(distanceCorporationName,
					theRefMatchThresholdDO.getDecayThresholdInDays(), theRefMatchThresholdDO.getDecayPercentage(),
					theRefMatchThresholdDO.getMaxDecayPercentage(), daysSinceLastUpdate);

			if (distanceAfterDecayCorporationName > theRefMatchThresholdDO.getMatchThreshold()) {
				isCorporationNameMatch = "Y";
			}
		}

		if (matchEngineLogger.isInfoEnabled()) {
			matchEngineLogger.info("LeCorporationFuzzyMatchRule.performMatching for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLegalentityIdpk:"
					+ reqCorporationNameDO.getLegalentityIdpk() + " Candidate LegalEntityIdpk:"
					+ corporationNameToMatchDO.getLegalentityIdpk() + " reqCorporationName:"
					+ reqCorporationNameDO.getCorporationName() + " corporationNameToMatchDO: "
					+ corporationNameToMatchDO.getCorporationName() + " distanceCorporationName:"
					+ distanceCorporationName.toString() + " distanceAfterDecayCorporationName:"
					+ distanceAfterDecayCorporationName.toString() + " isCorporationNameMatch:"
					+ isCorporationNameMatch);
		}

		return isCorporationNameMatch;

	}

	private SearchMatchCandidateLERequestDO createRequestSearchMatchCandidateLEDO(TxnTransferObj txnTransferObj) {
		LegalentityDO reqLegalentityDO = txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO().getLegalentityDO();

		List<String> corporationNameList = null;
		List<String> identificationTypeRefkeyList = null;
		List<String> identificationNumberList = null;
		// Address
		List<String> addressLineOneList = null;
		List<String> addressLineTwoList = null;
		List<String> addressLineThreeList = null;
		List<String> addressLineFourList = null;
		List<String> streetNameList = null;
		List<String> cityList = null;
		List<String> countryRefkeyList = null;
		List<String> postalCodeList = null;
		// Phone Numbers
		List<String> phoneNumberList = null;

		if (matchEngineLogger.isInfoEnabled()) {
			matchEngineLogger
					.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE creating request SearchMatchCandidateLE for reqLegalentityIdpk:"
							+ reqLegalentityDO.getIdPk());
		}
		// Retrieve Identification numbers
		if (null != reqLegalentityDO.getLeCorporationDO().getCorporationnamesDOList()
				&& reqLegalentityDO.getLeCorporationDO().getCorporationnamesDOList().size() > 0) {
			Iterator<CorporationnamesDO> itr = reqLegalentityDO.getLeCorporationDO().getCorporationnamesDOList()
					.iterator();
			corporationNameList = new ArrayList<String>();
			;

			while (itr.hasNext()) {
				CorporationnamesDO theCorporationnamesDO = (CorporationnamesDO) itr.next();
				corporationNameList.add(theCorporationnamesDO.getCorporationName());
			}
			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " corporationNameList : -" + corporationNameList);
			}

		}

		// Retrieve Identification numbers
		if (null != reqLegalentityDO.getLeIdentifierKycRegistryDOList()
				&& reqLegalentityDO.getLeIdentifierKycRegistryDOList().size() > 0) {
			Iterator<LeIdentifierKycRegistryDO> itr = reqLegalentityDO.getLeIdentifierKycRegistryDOList().iterator();
			identificationTypeRefkeyList = new ArrayList<String>();
			identificationNumberList = new ArrayList<String>();

			while (itr.hasNext()) {
				LeIdentifierKycRegistryDO theLeIdentifierKycRegistryDO = (LeIdentifierKycRegistryDO) itr.next();
				identificationTypeRefkeyList.add(theLeIdentifierKycRegistryDO.getIdentificationTypeRefkey());
				identificationNumberList.add(theLeIdentifierKycRegistryDO.getIdentificationNumber());
			}
			if (matchEngineLogger.isInfoEnabled()) {
				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " identificationNumberList : -"
								+ identificationNumberList);
			}
		}

		// Retrieve Identification numbers
		if (null != reqLegalentityDO.getLeAddressAssocDOList()
				&& reqLegalentityDO.getLeAddressAssocDOList().size() > 0) {
			Iterator<LeAddressAssocDO> itr = reqLegalentityDO.getLeAddressAssocDOList().iterator();
			addressLineOneList = new ArrayList<String>();
			addressLineTwoList = new ArrayList<String>();
			addressLineThreeList = new ArrayList<String>();
			addressLineFourList = new ArrayList<String>();
			streetNameList = new ArrayList<String>();
			cityList = new ArrayList<String>();
			countryRefkeyList = new ArrayList<String>();
			postalCodeList = new ArrayList<String>();

			while (itr.hasNext()) {
				LeAddressAssocDO theLeAddressAssocDO = (LeAddressAssocDO) itr.next();
				addressLineOneList.add(theLeAddressAssocDO.getAddressDO().getAddressLineOne());
				addressLineTwoList.add(theLeAddressAssocDO.getAddressDO().getAddressLineTwo());
				addressLineThreeList.add(theLeAddressAssocDO.getAddressDO().getAddressLineThree());
				addressLineFourList.add(theLeAddressAssocDO.getAddressDO().getAddressLineFour());
				streetNameList.add(theLeAddressAssocDO.getAddressDO().getStreetName());
				cityList.add(theLeAddressAssocDO.getAddressDO().getCity());
				countryRefkeyList.add(theLeAddressAssocDO.getAddressDO().getCountryRefkey());
				postalCodeList.add(theLeAddressAssocDO.getAddressDO().getPostalCode());
			}

			if (matchEngineLogger.isInfoEnabled()) {

				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " addressLineOneList : -" + addressLineOneList);
				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " addressLineTwoList : -" + addressLineTwoList);
				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " addressLineThreeList : -" + addressLineThreeList);
				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " addressLineFourList : -" + addressLineFourList);
				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " streetNameList : -" + streetNameList);
				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " cityList : -" + cityList);
				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " countryRefkeyList : -" + countryRefkeyList);
				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " postalCodeList : -" + postalCodeList);
			}

		}

		// Retrieve Identification numbers
		if (null != reqLegalentityDO.getLePhoneAssocDOList() && reqLegalentityDO.getLePhoneAssocDOList().size() > 0) {
			Iterator<LePhoneAssocDO> itr = reqLegalentityDO.getLePhoneAssocDOList().iterator();
			phoneNumberList = new ArrayList<String>();

			while (itr.hasNext()) {
				LePhoneAssocDO theLePhoneAssocDO = (LePhoneAssocDO) itr.next();
				phoneNumberList.add(theLePhoneAssocDO.getPhoneNumber());
			}

			if (matchEngineLogger.isInfoEnabled()) {

				matchEngineLogger
						.info("LeCorporationFuzzyMatchRule.createRequestSearchMatchCandidateLE - reqLegalentityIdpk:"
								+ reqLegalentityDO.getIdPk() + " phoneNumberList : -" + phoneNumberList);

			}
		}

		// set the attributes
		// As our coproraiton match rule is defined in such a way that
		// Identification and Name must match, we are not considering address
		// and phone for searching candidates
		SearchMatchCandidateLERequestDO theSearchMatchCandidateLERequestDO = new SearchMatchCandidateLERequestDO();
		theSearchMatchCandidateLERequestDO.setCorporationNameList(corporationNameList);
		theSearchMatchCandidateLERequestDO.setIdentificationTypeRefkeyList(identificationTypeRefkeyList);
		theSearchMatchCandidateLERequestDO.setIdentificationNumberList(identificationNumberList);
		// theSearchMatchCandidateLERequestDO.setAddressLineOneList(addressLineOneList);
		// theSearchMatchCandidateLERequestDO.setAddressLineTwoList(addressLineTwoList);
		// theSearchMatchCandidateLERequestDO.setAddressLineThreeList(addressLineThreeList);
		// theSearchMatchCandidateLERequestDO.setAddressLineFourList(addressLineFourList);
		// theSearchMatchCandidateLERequestDO.setStreetNameList(streetNameList);
		// theSearchMatchCandidateLERequestDO.setCityList(cityList);
		// theSearchMatchCandidateLERequestDO.setCountryRefkeyList(countryRefkeyList);
		// theSearchMatchCandidateLERequestDO.setPostalCodeList(postalCodeList);
		// theSearchMatchCandidateLERequestDO.setPhoneNumberList(phoneNumberList);

		return theSearchMatchCandidateLERequestDO;
	}

	private void performCommonvalidationBeforeExecution(TxnTransferObj txnTransferObj) {

		if (null == txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO()) {

			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"performLeMatchRequestDO is needed in the request");

		}

		if (null == txnTransferObj.getTxnPayload().getPerformLeMatchRequestDO().getLegalentityDO()) {

			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"performLeMatchRequestDO.egalentityDO is needed in the request");

		}

	}

	@Transactional
	public void addToMatchCandidateLeRegistry(String legalentityIdpk, String candidateLegalentityidpk,
			String matchPattern, String matchProposedActionRefkey, String matchActionstatusRefkey,
			TxnTransferObj txnTransferObj) {

		if (matchEngineLogger.isInfoEnabled()) {

			matchEngineLogger.info(" LeCorporationFuzzyMatchRule.addToMatchCandidateLeRegistry - legalentityIdpk:"
					+ legalentityIdpk + " candidateLegalentityidpk : -" + candidateLegalentityidpk
					+ " created entry in MatchCandidateLeRegistry");

		}

		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		TxnPayload transitTxnPayload = new TxnPayload();
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		MatchCandidateLeRegistryDO theMatchCandidateLeRegistryDO = new MatchCandidateLeRegistryDO();
		theMatchCandidateLeRegistryDO.setLegalentityIdpk(legalentityIdpk);
		theMatchCandidateLeRegistryDO.setCandidateLegalentityidpk(candidateLegalentityidpk);
		theMatchCandidateLeRegistryDO.setMatchPattern(matchPattern);
		theMatchCandidateLeRegistryDO.setMatchProposedActionRefkey(matchProposedActionRefkey);
		theMatchCandidateLeRegistryDO.setMatchActionstatusRefkey(matchActionstatusRefkey);
		theMatchCandidateLeRegistryDO.setMatchPercentageDescription("LeCorporationFuzzyMatchRule");

		transitTxnPayload.setMatchCandidateLeRegistryDO(theMatchCandidateLeRegistryDO);
		transitTxnTransferObj.setTxnPayload(transitTxnPayload);

		transitTxnTransferObj = theMatchCandidateLeRegistryComponent.persist(transitTxnTransferObj);

	}

}
