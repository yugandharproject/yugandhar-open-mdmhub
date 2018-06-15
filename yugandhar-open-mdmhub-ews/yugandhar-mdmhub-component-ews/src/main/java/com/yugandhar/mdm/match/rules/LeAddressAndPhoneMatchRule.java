package com.yugandhar.mdm.match.rules;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.extern.transferobj.TxnPayload;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.extern.dobj.AddressDO;
import com.yugandhar.mdm.extern.dobj.LePhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.RefMatchScoreDO;
import com.yugandhar.mdm.extern.dobj.RefMatchThresholdDO;
import com.yugandhar.mdm.match.componentref.RefMatchScoreComponent;
import com.yugandhar.mdm.match.componentref.RefMatchThresholdComponent;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.match.rules.LeAddressAndPhoneMatchRule")
public class LeAddressAndPhoneMatchRule {

	private static final Logger matchEngineLogger = LoggerFactory
			.getLogger(yugandharConstants.YUGANDHAR_MATCH_ENGINE_APPENDER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;
	JaroWinklerDistance theJaroWinklerDistance;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RefMatchThresholdComponent theRefMatchThresholdComponent;

	@Autowired
	RefMatchScoreComponent theRefMatchScoreComponent;

	@Autowired
	YugandharMatchingUtils theYugandharMatchingUtils;

	public LeAddressAndPhoneMatchRule() {
		txnTransferObjResponse = new TxnTransferObj();
		respTxnTransferObj = new TxnTransferObj();
		respTxnPayload = new TxnPayload();
		transitTxnTransferObj = new TxnTransferObj();
		theJaroWinklerDistance = new JaroWinklerDistance();
	}

	public String matchAddress(AddressDO reqAddressDO, AddressDO addressDOToMatch, TxnTransferObj txnTransferObj) {
		// Double Consolidateddistance = null;

		if (matchEngineLogger.isInfoEnabled()) {
			matchEngineLogger.info("LeAddressAndPhoneMatchRule.matchAddress for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqAddressDO -" + reqAddressDO.getIdPk()
					+ " Candidates addressDOToMatch:" + addressDOToMatch.getIdPk() + " starting to match Address");
		}

		String isAddressLineOneMatch = "N";
		String isAddressLineTwoMatch = "N";
		String isAddressLineThreeMatch = "N";
		String isAddressLineFourMatch = "N";
		String isStreetNameMatch = "N";
		String isCityMatch = "N";
		String isCountryMatch = "N";
		String isPostalCodeMatch = "N";
		String isAddressBlockMatch = "N";

		Double distanceAfterDecayAddressLineOne = (double) 0;
		Double distanceAfterDecayAddressLineTwo = (double) 0;
		Double distanceAfterDecayAddressLineThree = (double) 0;
		Double distanceAfterDecayAddressLineFour = (double) 0;
		Double distanceAfterDecayAddressStreetName = (double) 0;
		Double distanceAfterDecayAddressCity = (double) 0;

		Double daysSinceLastUpdate = theYugandharMatchingUtils
				.calculateDaysSinceLastUpdate(addressDOToMatch.getUpdatedTs());

		Double distanceAddressLineOne = theYugandharMatchingUtils
				.getJaroWinklerDistancePercent(reqAddressDO.getAddressLineOne(), addressDOToMatch.getAddressLineOne());
		Double distanceAddressLineTwo = theYugandharMatchingUtils
				.getJaroWinklerDistancePercent(reqAddressDO.getAddressLineTwo(), addressDOToMatch.getAddressLineTwo());
		Double distanceAddressLineThree = theYugandharMatchingUtils.getJaroWinklerDistancePercent(
				reqAddressDO.getAddressLineThree(), addressDOToMatch.getAddressLineThree());
		Double distanceAddressLineFour = theYugandharMatchingUtils.getJaroWinklerDistancePercent(
				reqAddressDO.getAddressLineFour(), addressDOToMatch.getAddressLineFour());
		Double distanceStreetName = theYugandharMatchingUtils
				.getJaroWinklerDistancePercent(reqAddressDO.getStreetName(), addressDOToMatch.getStreetName());
		Double distanceCity = theYugandharMatchingUtils.getJaroWinklerDistancePercent(reqAddressDO.getCity(),
				addressDOToMatch.getCity());

		// AddressLineOne
		if (distanceAddressLineOne != 0) {
			RefMatchThresholdDO theRefMatchThresholdDO = theRefMatchThresholdComponent.executeRepositoryQuery(
					YugandharMatchingConstants.ADDRESS_ATTR_ADDRESS_LINE_ONE, yugandharConstants.FILTER_VALUE_ACTIVE);

			distanceAfterDecayAddressLineOne = theYugandharMatchingUtils.getDistanceAfterDecay(distanceAddressLineOne,
					theRefMatchThresholdDO.getDecayThresholdInDays(), theRefMatchThresholdDO.getDecayPercentage(),
					theRefMatchThresholdDO.getMaxDecayPercentage(), daysSinceLastUpdate);

			if (distanceAfterDecayAddressLineOne > theRefMatchThresholdDO.getMatchThreshold()) {
				isAddressLineOneMatch = "Y";
			}
		}
		// AddressLineOTwo
		if (distanceAddressLineTwo != 0) {
			RefMatchThresholdDO theRefMatchThresholdDO = theRefMatchThresholdComponent.executeRepositoryQuery(
					YugandharMatchingConstants.ADDRESS_ATTR_ADDRESS_LINE_TWO, yugandharConstants.FILTER_VALUE_ACTIVE);

			distanceAfterDecayAddressLineTwo = theYugandharMatchingUtils.getDistanceAfterDecay(distanceAddressLineTwo,
					theRefMatchThresholdDO.getDecayThresholdInDays(), theRefMatchThresholdDO.getDecayPercentage(),
					theRefMatchThresholdDO.getMaxDecayPercentage(), daysSinceLastUpdate);

			if (distanceAfterDecayAddressLineTwo > theRefMatchThresholdDO.getMatchThreshold()) {
				isAddressLineTwoMatch = "Y";
			}
		}

		// AddressLineOThree
		if (distanceAddressLineThree != 0) {
			RefMatchThresholdDO theRefMatchThresholdDO = theRefMatchThresholdComponent.executeRepositoryQuery(
					YugandharMatchingConstants.ADDRESS_ATTR_ADDRESS_LINE_THREE, yugandharConstants.FILTER_VALUE_ACTIVE);

			distanceAfterDecayAddressLineThree = theYugandharMatchingUtils.getDistanceAfterDecay(
					distanceAddressLineThree, theRefMatchThresholdDO.getDecayThresholdInDays(),
					theRefMatchThresholdDO.getDecayPercentage(), theRefMatchThresholdDO.getMaxDecayPercentage(),
					daysSinceLastUpdate);

			if (distanceAfterDecayAddressLineThree > theRefMatchThresholdDO.getMatchThreshold()) {
				isAddressLineThreeMatch = "Y";
			}
		}

		// AddressLineOFour
		if (distanceAddressLineFour != 0) {
			RefMatchThresholdDO theRefMatchThresholdDO = theRefMatchThresholdComponent.executeRepositoryQuery(
					YugandharMatchingConstants.ADDRESS_ATTR_ADDRESS_LINE_FOUR, yugandharConstants.FILTER_VALUE_ACTIVE);

			distanceAfterDecayAddressLineFour = theYugandharMatchingUtils.getDistanceAfterDecay(distanceAddressLineFour,
					theRefMatchThresholdDO.getDecayThresholdInDays(), theRefMatchThresholdDO.getDecayPercentage(),
					theRefMatchThresholdDO.getMaxDecayPercentage(), daysSinceLastUpdate);

			if (distanceAfterDecayAddressLineFour > theRefMatchThresholdDO.getMatchThreshold()) {
				isAddressLineFourMatch = "Y";
			}
		}

		// Street Name
		if (distanceStreetName != 0) {
			RefMatchThresholdDO theRefMatchThresholdDO = theRefMatchThresholdComponent.executeRepositoryQuery(
					YugandharMatchingConstants.ADDRESS_ATTR_STREET_NAME, yugandharConstants.FILTER_VALUE_ACTIVE);

			distanceAfterDecayAddressStreetName = theYugandharMatchingUtils.getDistanceAfterDecay(distanceStreetName,
					theRefMatchThresholdDO.getDecayThresholdInDays(), theRefMatchThresholdDO.getDecayPercentage(),
					theRefMatchThresholdDO.getMaxDecayPercentage(), daysSinceLastUpdate);

			if (distanceAfterDecayAddressStreetName > theRefMatchThresholdDO.getMatchThreshold()) {
				isStreetNameMatch = "Y";
			}
		}

		// City
		if (distanceCity != 0) {
			RefMatchThresholdDO theRefMatchThresholdDO = theRefMatchThresholdComponent.executeRepositoryQuery(
					YugandharMatchingConstants.ADDRESS_ATTR_CITY, yugandharConstants.FILTER_VALUE_ACTIVE);

			distanceAfterDecayAddressCity = theYugandharMatchingUtils.getDistanceAfterDecay(distanceCity,
					theRefMatchThresholdDO.getDecayThresholdInDays(), theRefMatchThresholdDO.getDecayPercentage(),
					theRefMatchThresholdDO.getMaxDecayPercentage(), daysSinceLastUpdate);

			if (distanceAfterDecayAddressCity > theRefMatchThresholdDO.getMatchThreshold()) {
				isCityMatch = "Y";
			}
		}

		// exact match attributes
		if (!(theYugandharMatchingUtils.isNullOrEmpty(reqAddressDO.getCountryRefkey())
				&& theYugandharMatchingUtils.isNullOrEmpty(addressDOToMatch.getCountryRefkey()))) {
			if (reqAddressDO.getCountryRefkey().equalsIgnoreCase(addressDOToMatch.getCountryRefkey())) {
				isCountryMatch = "Y";
			}
		}

		if (!(theYugandharMatchingUtils.isNullOrEmpty(reqAddressDO.getPostalCode())
				&& theYugandharMatchingUtils.isNullOrEmpty(addressDOToMatch.getPostalCode()))) {
			if (reqAddressDO.getPostalCode().equalsIgnoreCase(addressDOToMatch.getPostalCode())) {
				isPostalCodeMatch = "Y";
			}
		}

		StringBuffer attributeMatchPattern = new StringBuffer();
		StringBuffer consolidatedDistanceAfterDecay = new StringBuffer();

		// Append all the match results
		attributeMatchPattern.append(isAddressLineOneMatch);
		attributeMatchPattern.append(isAddressLineTwoMatch);
		attributeMatchPattern.append(isAddressLineThreeMatch);
		attributeMatchPattern.append(isAddressLineFourMatch);
		attributeMatchPattern.append(isStreetNameMatch);
		attributeMatchPattern.append(isCityMatch);
		attributeMatchPattern.append(isCountryMatch);
		attributeMatchPattern.append(isPostalCodeMatch);

		// Append all the match DistanceAfterDecay variables
		consolidatedDistanceAfterDecay.append(distanceAfterDecayAddressLineOne.toString() + "|");
		consolidatedDistanceAfterDecay.append(distanceAfterDecayAddressLineTwo.toString() + "|");
		consolidatedDistanceAfterDecay.append(distanceAfterDecayAddressLineThree.toString() + "|");
		consolidatedDistanceAfterDecay.append(distanceAfterDecayAddressLineFour.toString() + "|");
		consolidatedDistanceAfterDecay.append(distanceAfterDecayAddressStreetName.toString() + "|");
		consolidatedDistanceAfterDecay.append(distanceAfterDecayAddressCity.toString() + "|");

		RefMatchScoreDO respRefMatchScoreDO = theRefMatchScoreComponent.executeRepositoryQuery(
				YugandharMatchingConstants.MATCH_ENTITY_OBJECT_NAME_ADDRESS, attributeMatchPattern.toString(),
				yugandharConstants.FILTER_VALUE_ACTIVE);

		if (null != respRefMatchScoreDO) {
			if ((respRefMatchScoreDO.getMatchResultRefkey().equals("1"))
					|| (respRefMatchScoreDO.getMatchResultRefkey().equals("2"))) {
				isAddressBlockMatch = "Y";
			}
		}

		if (matchEngineLogger.isInfoEnabled()) {
			matchEngineLogger.info("LeAddressAndPhoneMatchRule.matchAddress for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqAddressDO -" + reqAddressDO.getIdPk()
					+ " Candidates addressDOToMatch:" + addressDOToMatch.getIdPk() + " attributeMatchPattern:"
					+ attributeMatchPattern + " consolidatedDistanceAfterDecay:"
					+ consolidatedDistanceAfterDecay.toString() + " isAddressBlockMatch: " + isAddressBlockMatch
					+ " Finished match Address");
		}
		// // if response is null then its non match
		// if (null == respRefMatchScoreDO) {
		// return isAddressBlockMatch;
		// } else {
		// return "Y";
		// }

		return isAddressBlockMatch;

	}

	public String matchPhoneNumber(LePhoneAssocDO reqLePhoneAssocDO, LePhoneAssocDO lePhoneAssocDOToMatch,
			TxnTransferObj txnTransferObj) {

		if (matchEngineLogger.isInfoEnabled()) {
			matchEngineLogger.info("LeAddressAndPhoneMatchRule.matchPhoneNumber for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLePhoneAssocDO -"
					+ reqLePhoneAssocDO.getIdPk() + " Candidate lePhoneAssocDOToMatch:"
					+ lePhoneAssocDOToMatch.getIdPk() + " Started to match LePhoneAssocDO");
		}

		String isPhoneNumberMatch = "N";
		Double distanceAfterDecayPhoneNumber = (double) 0;

		Double daysSinceLastUpdate = theYugandharMatchingUtils
				.calculateDaysSinceLastUpdate(lePhoneAssocDOToMatch.getUpdatedTs());

		Double distancePhoneNumber = theYugandharMatchingUtils.getJaroWinklerDistancePercent(
				reqLePhoneAssocDO.getPhoneNumber(), lePhoneAssocDOToMatch.getPhoneNumber());

		if (distancePhoneNumber != 0) {
			RefMatchThresholdDO theRefMatchThresholdDO = theRefMatchThresholdComponent.executeRepositoryQuery(
					YugandharMatchingConstants.CORPORATION_ATTR_PHONE, yugandharConstants.FILTER_VALUE_ACTIVE);

			distanceAfterDecayPhoneNumber = theYugandharMatchingUtils.getDistanceAfterDecay(distancePhoneNumber,
					theRefMatchThresholdDO.getDecayThresholdInDays(), theRefMatchThresholdDO.getDecayPercentage(),
					theRefMatchThresholdDO.getMaxDecayPercentage(), daysSinceLastUpdate);

			if (distanceAfterDecayPhoneNumber > theRefMatchThresholdDO.getMatchThreshold()) {
				isPhoneNumberMatch = "Y";
			}
		}

		if (matchEngineLogger.isInfoEnabled()) {
			matchEngineLogger.info("LeAddressAndPhoneMatchRule.matchPhoneNumber for TxnMessageId:"
					+ txnTransferObj.getTxnHeader().getTxnMessageId() + " reqLePhoneAssocDO -"
					+ reqLePhoneAssocDO.getIdPk() + " Candidate lePhoneAssocDOToMatch:"
					+ lePhoneAssocDOToMatch.getIdPk() + " isPhoneNumberMatch:" + isPhoneNumberMatch
					+ " Finished to match LePhoneAssocDO");
		}

		return isPhoneNumberMatch;

	}
}
