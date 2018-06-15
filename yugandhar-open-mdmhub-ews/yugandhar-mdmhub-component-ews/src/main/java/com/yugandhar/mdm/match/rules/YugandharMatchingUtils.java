package com.yugandhar.mdm.match.rules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.extern.transferobj.TxnPayload;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.mdm.composite.service.RetrieveLegalEntityByLegalEntityIdService;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.match.rules.YugandharMatchingUtils")
public class YugandharMatchingUtils {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	JaroWinklerDistance theJaroWinklerDistance;
	
	@Autowired
	RetrieveLegalEntityByLegalEntityIdService retrieveLegalEntityByLegalEntityIdService;

	public Double getDistanceAfterDecay(Double jaroWinklerDistance, Integer decayThresholdInDays,
			Double decayPercentage, Integer maxDecayPercentage, Double daysSinceLastUpdate) {

		if(null==decayThresholdInDays || decayThresholdInDays < 1){
			decayThresholdInDays = 1;
		}
		
		Double actualDecayPercentage = daysSinceLastUpdate * (decayPercentage / decayThresholdInDays);

		if (actualDecayPercentage >= maxDecayPercentage) {
			actualDecayPercentage = Double.parseDouble(maxDecayPercentage.toString());
		}

		Double distanceAfterDecay = jaroWinklerDistance - (actualDecayPercentage / 100 * jaroWinklerDistance);

		return distanceAfterDecay;
	}

	public Double getJaroWinklerDistancePercent(String str1, String str2) {
		theJaroWinklerDistance = new JaroWinklerDistance();
		
		if (isNullOrEmpty(str1)) {
			str1 = "";
		}

		if (isNullOrEmpty(str2)) {
			str2 = "";
		}

		// if both the strings are null or empty then consider that the fields
		// are matching and return exact match.
		// theJaroWinklerDistance returns 0 for comparison of two empty strings
		// to this is manually modified.
		if (str1.trim().isEmpty() && str2.trim().isEmpty()) {
			return (double) 100;
		}

		Double jaroWinklerDistance = theJaroWinklerDistance.apply(str1, str2);
		Double distance = jaroWinklerDistance * 100;
		return distance;

	}

	public boolean isNullOrEmpty(String strToCheck) {

		if (null == strToCheck || strToCheck.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
	
	
	//Calculates the day difference between current timestamp and last update date
	public Double calculateDaysSinceLastUpdate(Date sinceDate){
		Long timeDiffInMillies = System.currentTimeMillis() - sinceDate.getTime();
		Long dayDifference = (timeDiffInMillies) / (1000 * 60 * 60 * 24);
		Double daysSinceLastUpdate = Math.floor(Double.parseDouble(dayDifference.toString()));
		return daysSinceLastUpdate;
	}
	
	public boolean isDateSame(Date reqDateOfBirth, Date dateOfBirthToMatch) {
		SimpleDateFormat dateOfBirthsdf = new SimpleDateFormat("yyyyMMdd");
		String reqDateOfBirthStr = dateOfBirthsdf.format(reqDateOfBirth);
		String dateOfBirthToMatchStr = dateOfBirthsdf.format(dateOfBirthToMatch);
		if(reqDateOfBirthStr.equals(dateOfBirthToMatchStr)){
			return true;
		}
		return false;

	}

	public boolean isDateSameXXX(Date reqDateOfBirth, Date dateOfBirthToMatch) {
		Calendar reqCal = Calendar.getInstance();
		Calendar reqDateToMatch = Calendar.getInstance();
		reqCal.setTime(reqDateOfBirth);
		reqDateToMatch.setTime(dateOfBirthToMatch);

		boolean isDayMatch = false;
		boolean isMonthMatch = false;
		boolean isYearMatch = false;

		if (reqCal.get(Calendar.DAY_OF_MONTH) == reqDateToMatch.get(Calendar.DAY_OF_MONTH)) {
			isDayMatch = true;
		}

		if (reqCal.get(Calendar.MONTH) == reqDateToMatch.get(Calendar.MONTH)) {
			isMonthMatch = true;
		}

		if (reqCal.get(Calendar.YEAR) == reqDateToMatch.get(Calendar.YEAR)) {
			isYearMatch = true;
		}

		if (isDayMatch && isMonthMatch && isYearMatch) {
			return true;
		}

		return false;

	}
	
	public LegalentityDO retrieveLegalEntitiesAsPerInquiryLevel(
			LegalentityDO reqLegalentityDO, TxnTransferObj txnTransferObj, String inquiryLevel) {
		// TODO Auto-generated method stub

		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		TxnPayload transitTxnPayload = null;

		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

			LegalentityDO theLegalentityDO = new LegalentityDO();
			theLegalentityDO.setIdPk(reqLegalentityDO.getIdPk());
			theLegalentityDO.setInquiryFilter(yugandharConstants.FILTER_VALUE_ALL);
			theLegalentityDO.setInquiryLevel(inquiryLevel);
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLegalentityDO(theLegalentityDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);

			transitTxnTransferObj = retrieveLegalEntityByLegalEntityIdService.process(transitTxnTransferObj);
			
			return transitTxnTransferObj.getTxnPayload().getLegalentityDO();
	}
	

}
