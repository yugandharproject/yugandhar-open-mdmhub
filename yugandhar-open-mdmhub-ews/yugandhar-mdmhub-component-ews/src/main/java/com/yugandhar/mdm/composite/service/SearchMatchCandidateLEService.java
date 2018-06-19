package com.yugandhar.mdm.composite.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
import com.yugandhar.mdm.component.util.YugandharPhoneticHelper;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.SearchMatchCandidateLERequestDO;

/**
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.SearchMatchCandidateLEService")
public class SearchMatchCandidateLEService {

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
	RetrieveLegalEntityByLegalEntityIdService retrieveLegalEntityByLegalEntityIdService;

	@Autowired
	YugandharPhoneticHelper theYugandharPhoneticHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@PersistenceContext
	private EntityManager entityManager;

	public SearchMatchCandidateLEService() {
		txnTransferObjResponse = new TxnTransferObj();
		respLegalentityDO = new LegalentityDO();
		respTxnTransferObj = new TxnTransferObj();
		respTxnPayload = new TxnPayload();
	}

	@Transactional
	public TxnTransferObj process(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			// Perform common validation
			performCommonvalidationBeforeExecution(txnTransferObj);

			SearchMatchCandidateLERequestDO theSearchMatchCandidateLERequestDO = (SearchMatchCandidateLERequestDO) txnTransferObj
					.getTxnPayload().getSearchMatchCandidateLERequestDO();

			Query searchQuery = null;

			// if phonetic search is true then check if the request is for
			// person or corporation
			if (theSearchMatchCandidateLERequestDO.isPhoneticSearch()) {

				if (isNullOrEmpty(theSearchMatchCandidateLERequestDO.getPersonNameOneList())
						&& isNullOrEmpty(theSearchMatchCandidateLERequestDO.getPersonLastNameList())) {
					// searchCorporation
					searchQuery = buildPhoneticSearchQueryForCorporation(txnTransferObj);

				} else {
					// Search Person
					searchQuery = buildPhoneticSearchQueryForPerson(txnTransferObj);
				}

			} else {
				if (isNullOrEmpty(theSearchMatchCandidateLERequestDO.getPersonNameOneList())
						&& isNullOrEmpty(theSearchMatchCandidateLERequestDO.getPersonLastNameList())) {
					// searchCorporation
					searchQuery = buildSearchQueryForCorporation(txnTransferObj);

				} else {
					// Search Person
					searchQuery = buildSearchQueryForPerson(txnTransferObj);
				}
			}
			setPaginationProperties(searchQuery, txnTransferObj);

			@SuppressWarnings("unchecked")
			List<LegalentityDO> searchResultLegalentityDOList = searchQuery.getResultList();

			String inquiryLevel = txnTransferObj.getTxnPayload().getSearchMatchCandidateLERequestDO().getInquiryLevel();

			if (null == inquiryLevel || inquiryLevel.isEmpty()) {
				respTxnPayload.setLegalentityDOList(searchResultLegalentityDOList);
			} else {
				List<LegalentityDO> retrieveResultLegalentityDOList = retrieveLegalEntitiesAsPerInquiryLevel(
						searchResultLegalentityDOList, txnTransferObj);
				respTxnPayload.setLegalentityDOList(retrieveResultLegalentityDOList);
			}

			// Final Response object

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

	private Query buildPhoneticSearchQueryForCorporation(TxnTransferObj txnTransferObj) {

		SearchMatchCandidateLERequestDO reqSearchMatchCandidateLERequestDO = (SearchMatchCandidateLERequestDO) txnTransferObj
				.getTxnPayload().getSearchMatchCandidateLERequestDO();

		// parameters

		List<String> identificationTypeRefkeyList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO
				.getIdentificationTypeRefkeyList());
		List<String> identificationNumberList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getIdentificationNumberList());

		// Address
		List<String> countryRefkeyList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCountryRefkeyList());
		List<String> postalCodeList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPostalCodeList());

		// Phone Numbers
		List<String> phoneNumberList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPhoneNumberList());

		// Corporation Name Phonetic
		List<String> phoneticCorporationNameList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCorporationNameList()), txnTransferObj);

		// Address - phontic attributes
		List<String> phoneticAddressLineOneList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineOneList()), txnTransferObj);
		List<String> phoneticAddressLineTwoList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineTwoList()), txnTransferObj);
		List<String> phoneticAddressLineThreeList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO
				.getPhoneticAddressLineThreeList()), txnTransferObj);
		List<String> phoneticAddressLineFourList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineFourList()), txnTransferObj);
		List<String> phoneticStreetNameList = theYugandharPhoneticHelper.getPhoneticValue( removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getStreetNameList()), txnTransferObj);
		List<String> phoneticCityList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCityList()), txnTransferObj);

		// filter
		String inquiryFilter = reqSearchMatchCandidateLERequestDO.getInquiryFilter();

		boolean isAddressNull = false;
		if (isNullOrEmpty(phoneticAddressLineOneList) && isNullOrEmpty(phoneticAddressLineTwoList)
				&& isNullOrEmpty(phoneticAddressLineThreeList) && isNullOrEmpty(phoneticAddressLineFourList)
				&& isNullOrEmpty(phoneticStreetNameList) && isNullOrEmpty(phoneticCityList)
				&& isNullOrEmpty(countryRefkeyList) && isNullOrEmpty(postalCodeList)) {
			isAddressNull = true;
		}

		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		// HashMap<String, String> paramMap = new HashMap<String, String>();
		HashMap<String, List<String>> paramMapForListOfString = new HashMap<String, List<String>>();

		queryJoinString.append(" SELECT DISTINCT LEGALENTITY.* FROM LEGALENTITY ");
		queryJoinString.append(" LEFT JOIN LE_CORPORATION on LEGALENTITY.ID_PK=LE_CORPORATION.LEGALENTITY_IDPK  ");

		queryJoinString.append(
				" LEFT JOIN CORPORATIONNAMES ON LE_CORPORATION.LEGALENTITY_IDPK = CORPORATIONNAMES.LEGALENTITY_IDPK ");
		queryJoinString.append(
				" LEFT JOIN LE_IDENTIFIER_KYC_REGISTRY ON LE_CORPORATION.LEGALENTITY_IDPK = LE_IDENTIFIER_KYC_REGISTRY.LEGALENTITY_IDPK ");

		queryCriteriaString.append(" WHERE LEGALENTITY.ENTITY_OBJECT_TYPE_REFKEY = 2 ");
		
		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString
					.append(" AND (LEGALENTITY.DELETED_TS is null or LEGALENTITY.DELETED_TS > current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_CORPORATION.DELETED_TS is null or LE_CORPORATION.DELETED_TS > current_timestamp) ");
			queryCriteriaString.append(
					" AND (CORPORATIONNAMES.DELETED_TS is null or CORPORATIONNAMES.DELETED_TS > current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS is null or LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS > current_timestamp) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString.append(
					" AND (LEGALENTITY.DELETED_TS IS NOT NULL AND LEGALENTITY.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_CORPORATION.DELETED_TS IS NOT NULL AND LE_CORPORATION.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (CORPORATIONNAMES.DELETED_TS IS NOT NULL AND CORPORATIONNAMES.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS IS NOT NULL AND LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS < current_timestamp) ");
		} 

		queryCriteriaString
				.append(" AND ((CORPORATIONNAMES.PHONETIC_CORPORATION_NAME IN (:phoneticCorporationNameList) ) OR ");
		queryCriteriaString
				.append(" ( LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_TYPE_REFKEY IN (:identificationTypeRefkeyList) ");
		queryCriteriaString
				.append(" AND LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_NUMBER IN (:identificationNumberList))) ");

		if (!(isNullOrEmpty(phoneNumberList) && isAddressNull)) {
			queryCriteriaString.append(" AND (  1=2 ");

			if (!isNullOrEmpty(phoneNumberList)) {
				queryJoinString.append(
						" LEFT JOIN LE_PHONE_ASSOC ON LE_CORPORATION.LEGALENTITY_IDPK = LE_PHONE_ASSOC.LEGALENTITY_IDPK ");
				queryCriteriaString.append("  OR (LE_PHONE_ASSOC.PHONE_NUMBER IN (:phoneNumberList)  ");

				if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
					queryCriteriaString.append(
							" AND (LE_PHONE_ASSOC.DELETED_TS is null or LE_PHONE_ASSOC.DELETED_TS > current_timestamp)) ");

				} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
					queryCriteriaString.append(
							" AND (LE_PHONE_ASSOC.DELETED_TS IS NOT NULL AND LE_PHONE_ASSOC.DELETED_TS < current_timestamp)) ");
				}

				paramMapForListOfString.put("phoneNumberList", phoneNumberList);

			}

			// if address is not null then append the Address related SQL
			// queries
			if (isAddressNull == false) {

				queryJoinString.append(
						" LEFT JOIN LE_ADDRESS_ASSOC ON LE_CORPORATION.LEGALENTITY_IDPK = LE_ADDRESS_ASSOC.LEGALENTITY_IDPK ");
				queryJoinString.append(" LEFT JOIN ADDRESS ON LE_ADDRESS_ASSOC.ADDRESS_IDPK = ADDRESS.ID_PK ");

				queryCriteriaString.append("OR (  ");

				if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
					queryCriteriaString.append(
							" (LE_ADDRESS_ASSOC.DELETED_TS is null or LE_ADDRESS_ASSOC.DELETED_TS > current_timestamp) ");

				} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
					queryCriteriaString.append(
							" (LE_ADDRESS_ASSOC.DELETED_TS IS NOT NULL AND LE_ADDRESS_ASSOC.DELETED_TS < current_timestamp) ");
				} else {
					queryCriteriaString.append(" 1=1 ");
				}

				if (!isNullOrEmpty(phoneticAddressLineOneList)) {
					queryCriteriaString
							.append(" AND ADDRESS.PHONETIC_ADDRESS_LINE_ONE IN (:phoneticAddressLineOneList) ");
					paramMapForListOfString.put("phoneticAddressLineOneList", phoneticAddressLineOneList);
				}
				if (!isNullOrEmpty(phoneticAddressLineTwoList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.PHONETIC_ADDRESS_LINE_TWO IN (:phoneticAddressLineTwoList) OR ADDRESS.PHONETIC_ADDRESS_LINE_TWO IS NULL) ");
					paramMapForListOfString.put("phoneticAddressLineTwoList", phoneticAddressLineTwoList);
				}
				if (!isNullOrEmpty(phoneticAddressLineThreeList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.PHONETIC_ADDRESS_LINE_THREE IN (:phoneticAddressLineThreeList) OR ADDRESS.PHONETIC_ADDRESS_LINE_THREE IS NULL) ");
					paramMapForListOfString.put("phoneticAddressLineThreeList", phoneticAddressLineThreeList);
				}
				if (!isNullOrEmpty(phoneticAddressLineFourList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.PHONETIC_ADDRESS_LINE_FOUR IN (:phoneticAddressLineFourList) OR ADDRESS.PHONETIC_ADDRESS_LINE_FOUR IS NULL) ");
					paramMapForListOfString.put("phoneticAddressLineFourList", phoneticAddressLineFourList);
				}
				if (!isNullOrEmpty(phoneticStreetNameList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.PHONETIC_STREET_NAME IN (:phoneticStreetNameList) OR ADDRESS.PHONETIC_STREET_NAME IS NULL) ");
					paramMapForListOfString.put("phoneticStreetNameList", phoneticStreetNameList);
				}
				if (!isNullOrEmpty(phoneticCityList)) {
					queryCriteriaString.append(" AND ADDRESS.PHONETIC_CITY IN (:phoneticCityList) ");
					paramMapForListOfString.put("phoneticCityList", phoneticCityList);
				}
				if (!isNullOrEmpty(countryRefkeyList)) {
					queryCriteriaString.append(" AND ADDRESS.COUNTRY_REFKEY IN (:countryRefkeyList) ");
					paramMapForListOfString.put("countryRefkeyList", countryRefkeyList);
				}
				if (!isNullOrEmpty(postalCodeList)) {
					queryCriteriaString
							.append(" AND (ADDRESS.POSTAL_CODE IN (:postalCodeList) OR ADDRESS.POSTAL_CODE IS NULL)");
					paramMapForListOfString.put("postalCodeList", postalCodeList);
				}

				queryCriteriaString.append(") ");

			}

			queryCriteriaString.append(" ) ");
		}
		paramMapForListOfString.put("phoneticCorporationNameList", phoneticCorporationNameList);
		paramMapForListOfString.put("identificationTypeRefkeyList", identificationTypeRefkeyList);
		paramMapForListOfString.put("identificationNumberList", identificationNumberList);

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchLegalEntityByLEAttributesService Corporation phonetic search Query is -"
				+ queryJoinString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), LegalentityDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, List<String>>> iterator = paramMapForListOfString.entrySet().iterator(); iterator
				.hasNext();) {
			Entry<String, List<String>> mapEntry = iterator.next();
			logger.debug("SearchLegalEntityByLEAttributesService parameter Name:" + mapEntry.getKey() + " Value:"
					+ mapEntry.getValue());
			searchQuery.setParameter(mapEntry.getKey(), mapEntry.getValue());
		}

		return searchQuery;

	}

	// Phonetic Query for Personal Legal Entity
	private Query buildPhoneticSearchQueryForPerson(TxnTransferObj txnTransferObj) {

		SearchMatchCandidateLERequestDO reqSearchMatchCandidateLERequestDO = (SearchMatchCandidateLERequestDO) txnTransferObj
				.getTxnPayload().getSearchMatchCandidateLERequestDO();

		// parameters

		Date dateOfBirth = reqSearchMatchCandidateLERequestDO.getDateOfBirth();
		String genderRefkey = reqSearchMatchCandidateLERequestDO.getGenderRefkey();

		List<String> identificationTypeRefkeyList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO
				.getIdentificationTypeRefkeyList());
		List<String> identificationNumberList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getIdentificationNumberList());

		// Address
		List<String> countryRefkeyList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCountryRefkeyList());
		List<String> postalCodeList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPostalCodeList());

		// Phone Numbers
		List<String> phoneNumberList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPhoneNumberList());

		// PersonName Phonetic
		List<String> phoneticPersonNameOneList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPersonNameOneList()), txnTransferObj);
		List<String> phoneticPersonLastNameList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPersonLastNameList()), txnTransferObj);

		// Address - phontic attributes
		List<String> phoneticAddressLineOneList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineOneList()), txnTransferObj);
		List<String> phoneticAddressLineTwoList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineTwoList()), txnTransferObj);
		List<String> phoneticAddressLineThreeList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO
				.getPhoneticAddressLineThreeList()), txnTransferObj);
		List<String> phoneticAddressLineFourList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineFourList()), txnTransferObj);
		List<String> phoneticStreetNameList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getStreetNameList()), txnTransferObj);
		List<String> phoneticCityList = theYugandharPhoneticHelper.getPhoneticValue(removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCityList()), txnTransferObj);

		// filter
		String inquiryFilter = reqSearchMatchCandidateLERequestDO.getInquiryFilter();

		boolean isAddressNull = false;
		if (isNullOrEmpty(phoneticAddressLineOneList) && isNullOrEmpty(phoneticAddressLineTwoList)
				&& isNullOrEmpty(phoneticAddressLineThreeList) && isNullOrEmpty(phoneticAddressLineFourList)
				&& isNullOrEmpty(phoneticStreetNameList) && isNullOrEmpty(phoneticCityList)
				&& isNullOrEmpty(countryRefkeyList) && isNullOrEmpty(postalCodeList)) {
			isAddressNull = true;
		}

		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		// HashMap<String, String> paramMap = new HashMap<String, String>();
		HashMap<String, List<String>> paramMapForListOfString = new HashMap<String, List<String>>();

		queryJoinString.append(" SELECT DISTINCT LEGALENTITY.* FROM LEGALENTITY ");
		queryJoinString.append(" LEFT JOIN LE_PERSON on LEGALENTITY.ID_PK=LE_PERSON.LEGALENTITY_IDPK  ");

		// queryJoinString.append(" SELECT LE_PERSON.* FROM LE_PERSON ");
		queryJoinString.append(" LEFT JOIN PERSONNAMES ON LE_PERSON.LEGALENTITY_IDPK = PERSONNAMES.LEGALENTITY_IDPK ");
		queryJoinString.append(
				" LEFT JOIN LE_IDENTIFIER_KYC_REGISTRY ON LE_PERSON.LEGALENTITY_IDPK = LE_IDENTIFIER_KYC_REGISTRY.LEGALENTITY_IDPK ");

		queryCriteriaString.append(" WHERE LEGALENTITY.ENTITY_OBJECT_TYPE_REFKEY = 1 ");
		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString
					.append(" AND (LEGALENTITY.DELETED_TS is null or LEGALENTITY.DELETED_TS > current_timestamp) ");
			queryCriteriaString
					.append(" AND (LE_PERSON.DELETED_TS is null or LE_PERSON.DELETED_TS > current_timestamp) ");
			queryCriteriaString
					.append(" AND (PERSONNAMES.DELETED_TS is null or PERSONNAMES.DELETED_TS > current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS is null or LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS > current_timestamp) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString.append(
					" AND (LEGALENTITY.DELETED_TS IS NOT NULL AND LEGALENTITY.DELETED_TS < current_timestamp) ");
			queryCriteriaString
					.append(" AND (LE_PERSON.DELETED_TS IS NOT NULL AND LE_PERSON.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (PERSONNAMES.DELETED_TS IS NOT NULL AND PERSONNAMES.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS IS NOT NULL AND LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS < current_timestamp) ");
		} 

		queryCriteriaString.append(
				" AND ((PERSONNAMES.PHONETIC_NAME_ONE IN (:phoneticPersonNameOneList) AND PERSONNAMES.PHONETIC_LAST_NAME IN (:phoneticPersonLastNameList) ) OR ");
		queryCriteriaString
				.append(" ( LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_TYPE_REFKEY IN (:identificationTypeRefkeyList) ");
		queryCriteriaString
				.append(" AND LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_NUMBER IN (:identificationNumberList))) ");

		if (!(null == dateOfBirth && isNullOrEmpty(genderRefkey) && isNullOrEmpty(phoneNumberList) && isAddressNull)) {
			queryCriteriaString.append(" AND (  1=2 ");

			if (null != dateOfBirth) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String strDateOfBirth = df.format(dateOfBirth);
				queryCriteriaString
						.append("OR LE_PERSON.DATE_OF_BIRTH = TO_DATE('" + strDateOfBirth + "','yyyy-MM-dd') ");
			}

			if (!isNullOrEmpty(genderRefkey)) {
				queryCriteriaString.append(" OR LE_PERSON.GENDER_REFKEY =:genderRefkey ");
			}

			if (!isNullOrEmpty(phoneNumberList)) {
				queryJoinString.append(
						" LEFT JOIN LE_PHONE_ASSOC ON LE_PERSON.LEGALENTITY_IDPK = LE_PHONE_ASSOC.LEGALENTITY_IDPK ");
				queryCriteriaString.append("  OR (LE_PHONE_ASSOC.PHONE_NUMBER IN (:phoneNumberList)  ");

				if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
					queryCriteriaString.append(
							" AND (LE_PHONE_ASSOC.DELETED_TS is null or LE_PHONE_ASSOC.DELETED_TS > current_timestamp)) ");

				} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
					queryCriteriaString.append(
							" AND (LE_PHONE_ASSOC.DELETED_TS IS NOT NULL AND LE_PHONE_ASSOC.DELETED_TS < current_timestamp)) ");
				}

				paramMapForListOfString.put("phoneNumberList", phoneNumberList);

			}

			// if address is not null then append the Address related SQL
			// queries
			if (isAddressNull == false) {

				queryJoinString.append(
						" LEFT JOIN LE_ADDRESS_ASSOC ON LE_PERSON.LEGALENTITY_IDPK = LE_ADDRESS_ASSOC.LEGALENTITY_IDPK ");
				queryJoinString.append(" LEFT JOIN ADDRESS ON LE_ADDRESS_ASSOC.ADDRESS_IDPK = ADDRESS.ID_PK ");

				queryCriteriaString.append("OR (  ");

				if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
					queryCriteriaString.append(
							" (LE_ADDRESS_ASSOC.DELETED_TS is null or LE_ADDRESS_ASSOC.DELETED_TS > current_timestamp) ");

				} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
					queryCriteriaString.append(
							" (LE_ADDRESS_ASSOC.DELETED_TS IS NOT NULL AND LE_ADDRESS_ASSOC.DELETED_TS < current_timestamp) ");
				} else {
					queryCriteriaString.append(" 1=1 ");
				}

				if (!isNullOrEmpty(phoneticAddressLineOneList)) {
					queryCriteriaString
							.append(" AND ADDRESS.PHONETIC_ADDRESS_LINE_ONE IN (:phoneticAddressLineOneList) ");
					paramMapForListOfString.put("phoneticAddressLineOneList", phoneticAddressLineOneList);
				}
				if (!isNullOrEmpty(phoneticAddressLineTwoList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.PHONETIC_ADDRESS_LINE_TWO IN (:phoneticAddressLineTwoList) OR ADDRESS.PHONETIC_ADDRESS_LINE_TWO IS NULL) ");
					paramMapForListOfString.put("phoneticAddressLineTwoList", phoneticAddressLineTwoList);
				}
				if (!isNullOrEmpty(phoneticAddressLineThreeList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.PHONETIC_ADDRESS_LINE_THREE IN (:phoneticAddressLineThreeList) OR ADDRESS.PHONETIC_ADDRESS_LINE_THREE IS NULL) ");
					paramMapForListOfString.put("phoneticAddressLineThreeList", phoneticAddressLineThreeList);
				}
				if (!isNullOrEmpty(phoneticAddressLineFourList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.PHONETIC_ADDRESS_LINE_FOUR IN (:phoneticAddressLineFourList) OR ADDRESS.PHONETIC_ADDRESS_LINE_FOUR IS NULL) ");
					paramMapForListOfString.put("phoneticAddressLineFourList", phoneticAddressLineFourList);
				}
				if (!isNullOrEmpty(phoneticStreetNameList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.PHONETIC_STREET_NAME IN (:phoneticStreetNameList) OR ADDRESS.PHONETIC_STREET_NAME IS NULL) ");
					paramMapForListOfString.put("phoneticStreetNameList", phoneticStreetNameList);
				}
				if (!isNullOrEmpty(phoneticCityList)) {
					queryCriteriaString.append(" AND ADDRESS.PHONETIC_CITY IN (:phoneticCityList) ");
					paramMapForListOfString.put("phoneticCityList", phoneticCityList);
				}

				if (!isNullOrEmpty(countryRefkeyList)) {
					queryCriteriaString.append(" AND ADDRESS.COUNTRY_REFKEY IN (:countryRefkeyList) ");
					paramMapForListOfString.put("countryRefkeyList", countryRefkeyList);
				}
				if (!isNullOrEmpty(postalCodeList)) {
					queryCriteriaString
							.append(" AND (ADDRESS.POSTAL_CODE IN (:postalCodeList) OR ADDRESS.POSTAL_CODE IS NULL)");
					paramMapForListOfString.put("postalCodeList", postalCodeList);
				}

				queryCriteriaString.append(") ");

			}

			queryCriteriaString.append(" ) ");
		}
		paramMapForListOfString.put("phoneticPersonNameOneList", phoneticPersonNameOneList);
		paramMapForListOfString.put("phoneticPersonLastNameList", phoneticPersonLastNameList);
		paramMapForListOfString.put("identificationTypeRefkeyList", identificationTypeRefkeyList);
		paramMapForListOfString.put("identificationNumberList", identificationNumberList);

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchLegalEntityByLEAttributesService search Query is -" + queryJoinString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), LegalentityDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, List<String>>> iterator = paramMapForListOfString.entrySet().iterator(); iterator
				.hasNext();) {
			Entry<String, List<String>> mapEntry = iterator.next();
			logger.debug("SearchLegalEntityByLEAttributesService parameter Name:" + mapEntry.getKey() + " Value:"
					+ mapEntry.getValue());
			searchQuery.setParameter(mapEntry.getKey(), mapEntry.getValue());
		}

		// set the query parameters date of birth and gender as those are not
		// added in the paramMapForListOfString
		if (null != dateOfBirth) {
			// searchQuery.setParameter("dateOfBirth", dateOfBirth);
			logger.debug("SearchLegalEntityByLEAttributesService parameter Name: dateOfBirth Value:" + dateOfBirth);
		}

		if (!isNullOrEmpty(genderRefkey)) {
			searchQuery.setParameter("genderRefkey", genderRefkey);
			logger.debug("SearchLegalEntityByLEAttributesService parameter Name: genderRefkey Value:" + genderRefkey);
		}

		return searchQuery;

	}

	private Query buildSearchQueryForCorporation(TxnTransferObj txnTransferObj) {

		SearchMatchCandidateLERequestDO reqSearchMatchCandidateLERequestDO = (SearchMatchCandidateLERequestDO) txnTransferObj
				.getTxnPayload().getSearchMatchCandidateLERequestDO();

		// parameters

		List<String> corporationNameList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCorporationNameList());

		List<String> identificationTypeRefkeyList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO
				.getIdentificationTypeRefkeyList());
		List<String> identificationNumberList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getIdentificationNumberList());

		// Address
		List<String> addressLineOneList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineOneList());
		List<String> addressLineTwoList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineTwoList());
		List<String> addressLineThreeList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineThreeList());
		List<String> addressLineFourList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineFourList());
		List<String> streetNameList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getStreetNameList());
		List<String> cityList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCityList());
		List<String> countryRefkeyList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCountryRefkeyList());
		List<String> postalCodeList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPostalCodeList());

		// Phone Numbers
		List<String> phoneNumberList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPhoneNumberList());

		// filter
		String inquiryFilter = reqSearchMatchCandidateLERequestDO.getInquiryFilter();

		boolean isAddressNull = false;
		if (isNullOrEmpty(addressLineOneList) && isNullOrEmpty(addressLineTwoList)
				&& isNullOrEmpty(addressLineThreeList) && isNullOrEmpty(addressLineFourList)
				&& isNullOrEmpty(streetNameList) && isNullOrEmpty(cityList) && isNullOrEmpty(countryRefkeyList)
				&& isNullOrEmpty(postalCodeList)) {
			isAddressNull = true;
		}

		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		// HashMap<String, String> paramMap = new HashMap<String, String>();
		HashMap<String, List<String>> paramMapForListOfString = new HashMap<String, List<String>>();

		queryJoinString.append(" SELECT DISTINCT LEGALENTITY.* FROM LEGALENTITY ");
		queryJoinString.append(" LEFT JOIN LE_CORPORATION on LEGALENTITY.ID_PK=LE_CORPORATION.LEGALENTITY_IDPK  ");

		queryJoinString.append(
				" LEFT JOIN CORPORATIONNAMES ON LE_CORPORATION.LEGALENTITY_IDPK = CORPORATIONNAMES.LEGALENTITY_IDPK ");
		queryJoinString.append(
				" LEFT JOIN LE_IDENTIFIER_KYC_REGISTRY ON LE_CORPORATION.LEGALENTITY_IDPK = LE_IDENTIFIER_KYC_REGISTRY.LEGALENTITY_IDPK ");

		queryCriteriaString.append(" WHERE LEGALENTITY.ENTITY_OBJECT_TYPE_REFKEY = 2 ");
		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString
					.append(" AND (LEGALENTITY.DELETED_TS is null or LEGALENTITY.DELETED_TS > current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_CORPORATION.DELETED_TS is null or LE_CORPORATION.DELETED_TS > current_timestamp) ");
			queryCriteriaString.append(
					" AND (CORPORATIONNAMES.DELETED_TS is null or CORPORATIONNAMES.DELETED_TS > current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS is null or LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS > current_timestamp) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString.append(
					" AND (LEGALENTITY.DELETED_TS IS NOT NULL AND LEGALENTITY.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_CORPORATION.DELETED_TS IS NOT NULL AND LE_CORPORATION.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (CORPORATIONNAMES.DELETED_TS IS NOT NULL AND CORPORATIONNAMES.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS IS NOT NULL AND LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS < current_timestamp) ");
		} 

		queryCriteriaString.append(" AND ((CORPORATIONNAMES.CORPORATION_NAME IN (:corporationNameList) ) OR ");
		queryCriteriaString
				.append(" ( LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_TYPE_REFKEY IN (:identificationTypeRefkeyList) ");
		queryCriteriaString
				.append(" AND LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_NUMBER IN (:identificationNumberList))) ");

		if (!(isNullOrEmpty(phoneNumberList) && isAddressNull)) {
			queryCriteriaString.append(" AND (  1=2 ");

			if (!isNullOrEmpty(phoneNumberList)) {
				queryJoinString.append(
						" LEFT JOIN LE_PHONE_ASSOC ON LE_CORPORATION.LEGALENTITY_IDPK = LE_PHONE_ASSOC.LEGALENTITY_IDPK ");
				queryCriteriaString.append("  OR (LE_PHONE_ASSOC.PHONE_NUMBER IN (:phoneNumberList)  ");

				if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
					queryCriteriaString.append(
							" AND (LE_PHONE_ASSOC.DELETED_TS is null or LE_PHONE_ASSOC.DELETED_TS > current_timestamp)) ");

				} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
					queryCriteriaString.append(
							" AND (LE_PHONE_ASSOC.DELETED_TS IS NOT NULL AND LE_PHONE_ASSOC.DELETED_TS < current_timestamp)) ");
				}

				paramMapForListOfString.put("phoneNumberList", phoneNumberList);

			}

			// if address is not null then append the Address related SQL
			// queries
			if (isAddressNull == false) {

				queryJoinString.append(
						" LEFT JOIN LE_ADDRESS_ASSOC ON LE_CORPORATION.LEGALENTITY_IDPK = LE_ADDRESS_ASSOC.LEGALENTITY_IDPK ");
				queryJoinString.append(" LEFT JOIN ADDRESS ON LE_ADDRESS_ASSOC.ADDRESS_IDPK = ADDRESS.ID_PK ");

				queryCriteriaString.append("OR (  ");

				if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
					queryCriteriaString.append(
							" (LE_ADDRESS_ASSOC.DELETED_TS is null or LE_ADDRESS_ASSOC.DELETED_TS > current_timestamp) ");

				} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
					queryCriteriaString.append(
							" (LE_ADDRESS_ASSOC.DELETED_TS IS NOT NULL AND LE_ADDRESS_ASSOC.DELETED_TS < current_timestamp) ");
				} else {
					queryCriteriaString.append(" 1=1 ");
				}

				if (!isNullOrEmpty(addressLineOneList)) {
					queryCriteriaString.append(" AND ADDRESS.ADDRESS_LINE_ONE IN (:addressLineOneList) ");
					paramMapForListOfString.put("addressLineOneList", addressLineOneList);
				}
				if (!isNullOrEmpty(addressLineTwoList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.ADDRESS_LINE_TWO IN (:addressLineTwoList) OR ADDRESS.ADDRESS_LINE_TWO IS NULL) ");
					paramMapForListOfString.put("addressLineTwoList", addressLineTwoList);
				}
				if (!isNullOrEmpty(addressLineThreeList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.ADDRESS_LINE_THREE IN (:addressLineThreeList) OR ADDRESS.ADDRESS_LINE_THREE IS NULL) ");
					paramMapForListOfString.put("addressLineThreeList", addressLineThreeList);
				}
				if (!isNullOrEmpty(addressLineFourList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.ADDRESS_LINE_FOUR IN (:addressLineFourList) OR ADDRESS.ADDRESS_LINE_FOUR IS NULL) ");
					paramMapForListOfString.put("addressLineFourList", addressLineFourList);
				}
				if (!isNullOrEmpty(streetNameList)) {
					queryCriteriaString
							.append(" AND (ADDRESS.STREET_NAME IN (:streetNameList) OR ADDRESS.STREET_NAME IS NULL) ");
					paramMapForListOfString.put("streetNameList", streetNameList);
				}
				if (!isNullOrEmpty(cityList)) {
					queryCriteriaString.append(" AND ADDRESS.CITY IN (:cityList) ");
					paramMapForListOfString.put("cityList", cityList);
				}
				if (!isNullOrEmpty(countryRefkeyList)) {
					queryCriteriaString.append(" AND ADDRESS.COUNTRY_REFKEY IN (:countryRefkeyList) ");
					paramMapForListOfString.put("countryRefkeyList", countryRefkeyList);
				}
				if (!isNullOrEmpty(postalCodeList)) {
					queryCriteriaString
							.append(" AND (ADDRESS.POSTAL_CODE IN (:postalCodeList) OR ADDRESS.POSTAL_CODE IS NULL)");
					paramMapForListOfString.put("postalCodeList", postalCodeList);
				}

				queryCriteriaString.append(") ");

			}

			queryCriteriaString.append(" ) ");
		}
		paramMapForListOfString.put("corporationNameList", corporationNameList);
		paramMapForListOfString.put("identificationTypeRefkeyList", identificationTypeRefkeyList);
		paramMapForListOfString.put("identificationNumberList", identificationNumberList);

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchLegalEntityByLEAttributesService search Query is -" + queryJoinString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), LegalentityDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, List<String>>> iterator = paramMapForListOfString.entrySet().iterator(); iterator
				.hasNext();) {
			Entry<String, List<String>> mapEntry = iterator.next();
			logger.debug("SearchLegalEntityByLEAttributesService parameter Name:" + mapEntry.getKey() + " Value:"
					+ mapEntry.getValue());
			searchQuery.setParameter(mapEntry.getKey(), mapEntry.getValue());
		}

		return searchQuery;

	}

	private List<LegalentityDO> retrieveLegalEntitiesAsPerInquiryLevel(
			List<LegalentityDO> searchResultLegalentityDOList, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		TxnPayload transitTxnPayload = null;

		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		List<LegalentityDO> retrieveResultLegalentityDOList = new ArrayList<LegalentityDO>();

		for (LegalentityDO resultLegalentityDO : searchResultLegalentityDOList) {
			LegalentityDO theLegalentityDO = new LegalentityDO();
			theLegalentityDO.setIdPk(resultLegalentityDO.getIdPk());
			theLegalentityDO.setInquiryFilter(yugandharConstants.FILTER_VALUE_ALL);
			theLegalentityDO.setInquiryLevel(
					txnTransferObj.getTxnPayload().getSearchMatchCandidateLERequestDO().getInquiryLevel());
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setLegalentityDO(theLegalentityDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);

			transitTxnTransferObj = retrieveLegalEntityByLegalEntityIdService.process(transitTxnTransferObj);
			retrieveResultLegalentityDOList.add(transitTxnTransferObj.getTxnPayload().getLegalentityDO());
		}

		return retrieveResultLegalentityDOList;
	}

	private void setPaginationProperties(Query searchQuery, TxnTransferObj txnTransferObj) {
		// set pagination properties
		// set the current page index to zero (0) if page index is not
		// provided in the request or negative value is provided
		ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_pagination_default_pagesize_search,
				yugandharConstants.FILTER_VALUE_ACTIVE);

		// set default page size as configured in application properties
		if (null == txnTransferObj.getTxnPayload().getPaginationPageSize()
				|| txnTransferObj.getTxnPayload().getPaginationPageSize() <= 0) {
			searchQuery.setMaxResults(Integer.valueOf(theConfigAppPropertiesDO.getValue()));

		} else {
			searchQuery.setMaxResults(txnTransferObj.getTxnPayload().getPaginationPageSize());
		}

		if (null == txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice()
				|| txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice() < 0) {
			searchQuery.setFirstResult(0);
			respTxnPayload.setPaginationIndexOfCurrentSlice(0);
		} else {
			searchQuery.setFirstResult(
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice() * searchQuery.getMaxResults());
			respTxnPayload.setPaginationIndexOfCurrentSlice(
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice());
		}

		respTxnPayload.setPaginationPageSize(searchQuery.getMaxResults());

	}

	private Query buildSearchQueryForPerson(TxnTransferObj txnTransferObj) {

		SearchMatchCandidateLERequestDO reqSearchMatchCandidateLERequestDO = (SearchMatchCandidateLERequestDO) txnTransferObj
				.getTxnPayload().getSearchMatchCandidateLERequestDO();

		// parameters

		Date dateOfBirth = reqSearchMatchCandidateLERequestDO.getDateOfBirth();
		String genderRefkey = reqSearchMatchCandidateLERequestDO.getGenderRefkey();

		List<String> personNameOneList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPersonNameOneList());
		List<String> personLastNameList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPersonLastNameList());

		List<String> identificationTypeRefkeyList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO
				.getIdentificationTypeRefkeyList());
		List<String> identificationNumberList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getIdentificationNumberList());

		// Address
		List<String> addressLineOneList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineOneList());
		List<String> addressLineTwoList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineTwoList());
		List<String> addressLineThreeList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineThreeList());
		List<String> addressLineFourList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getAddressLineFourList());
		List<String> streetNameList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getStreetNameList());
		List<String> cityList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCityList());
		List<String> countryRefkeyList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getCountryRefkeyList());
		List<String> postalCodeList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPostalCodeList());
		
		// Phone Numbers
		List<String> phoneNumberList = removeNullObjectsFromList(reqSearchMatchCandidateLERequestDO.getPhoneNumberList());

		// filter
		String inquiryFilter = reqSearchMatchCandidateLERequestDO.getInquiryFilter();

		boolean isAddressNull = false;
		if (isNullOrEmpty(addressLineOneList) && isNullOrEmpty(addressLineTwoList)
				&& isNullOrEmpty(addressLineThreeList) && isNullOrEmpty(addressLineFourList)
				&& isNullOrEmpty(streetNameList) && isNullOrEmpty(cityList) && isNullOrEmpty(countryRefkeyList)
				&& isNullOrEmpty(postalCodeList)) {
			isAddressNull = true;
		}

		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		// HashMap<String, String> paramMap = new HashMap<String, String>();
		HashMap<String, List<String>> paramMapForListOfString = new HashMap<String, List<String>>();

		queryJoinString.append(" SELECT DISTINCT LEGALENTITY.* FROM LEGALENTITY ");
		queryJoinString.append(" LEFT JOIN LE_PERSON on LEGALENTITY.ID_PK=LE_PERSON.LEGALENTITY_IDPK  ");

		// queryJoinString.append(" SELECT LE_PERSON.* FROM LE_PERSON ");
		queryJoinString.append(" LEFT JOIN PERSONNAMES ON LE_PERSON.LEGALENTITY_IDPK = PERSONNAMES.LEGALENTITY_IDPK ");
		queryJoinString.append(
				" LEFT JOIN LE_IDENTIFIER_KYC_REGISTRY ON LE_PERSON.LEGALENTITY_IDPK = LE_IDENTIFIER_KYC_REGISTRY.LEGALENTITY_IDPK ");

		queryCriteriaString.append(" WHERE LEGALENTITY.ENTITY_OBJECT_TYPE_REFKEY = 1 ");
		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString
					.append(" AND (LEGALENTITY.DELETED_TS is null or LEGALENTITY.DELETED_TS > current_timestamp) ");
			queryCriteriaString
					.append(" AND (LE_PERSON.DELETED_TS is null or LE_PERSON.DELETED_TS > current_timestamp) ");
			queryCriteriaString
					.append(" AND (PERSONNAMES.DELETED_TS is null or PERSONNAMES.DELETED_TS > current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS is null or LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS > current_timestamp) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString.append(
					" AND (LEGALENTITY.DELETED_TS IS NOT NULL AND LEGALENTITY.DELETED_TS < current_timestamp) ");
			queryCriteriaString
					.append(" AND (LE_PERSON.DELETED_TS IS NOT NULL AND LE_PERSON.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (PERSONNAMES.DELETED_TS IS NOT NULL AND PERSONNAMES.DELETED_TS < current_timestamp) ");
			queryCriteriaString.append(
					" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS IS NOT NULL AND LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS < current_timestamp) ");
		} else {
			queryCriteriaString.append(" WHERE 1=1 ");
		}

		queryCriteriaString.append(
				" AND ((PERSONNAMES.NAME_ONE IN (:personNameOneList) AND PERSONNAMES.LAST_NAME IN (:personLastNameList) ) OR ");
		queryCriteriaString
				.append(" ( LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_TYPE_REFKEY IN (:identificationTypeRefkeyList) ");
		queryCriteriaString
				.append(" AND LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_NUMBER IN (:identificationNumberList))) ");

		if (!(null == dateOfBirth && isNullOrEmpty(genderRefkey) && isNullOrEmpty(phoneNumberList) && isAddressNull)) {
			queryCriteriaString.append(" AND (  1=2 ");

			if (null != dateOfBirth) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String strDateOfBirth = df.format(dateOfBirth);
				queryCriteriaString
						.append("OR LE_PERSON.DATE_OF_BIRTH = TO_DATE('" + strDateOfBirth + "','yyyy-MM-dd') ");
			}

			if (!isNullOrEmpty(genderRefkey)) {
				queryCriteriaString.append(" OR LE_PERSON.GENDER_REFKEY =:genderRefkey ");
			}

			if (!isNullOrEmpty(phoneNumberList)) {
				queryJoinString.append(
						" LEFT JOIN LE_PHONE_ASSOC ON LE_PERSON.LEGALENTITY_IDPK = LE_PHONE_ASSOC.LEGALENTITY_IDPK ");
				queryCriteriaString.append("  OR (LE_PHONE_ASSOC.PHONE_NUMBER IN (:phoneNumberList)  ");

				if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
					queryCriteriaString.append(
							" AND (LE_PHONE_ASSOC.DELETED_TS is null or LE_PHONE_ASSOC.DELETED_TS > current_timestamp)) ");

				} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
					queryCriteriaString.append(
							" AND (LE_PHONE_ASSOC.DELETED_TS IS NOT NULL AND LE_PHONE_ASSOC.DELETED_TS < current_timestamp)) ");
				}

				paramMapForListOfString.put("phoneNumberList", phoneNumberList);

			}

			// if address is not null then append the Address related SQL
			// queries
			if (isAddressNull == false) {

				queryJoinString.append(
						" LEFT JOIN LE_ADDRESS_ASSOC ON LE_PERSON.LEGALENTITY_IDPK = LE_ADDRESS_ASSOC.LEGALENTITY_IDPK ");
				queryJoinString.append(" LEFT JOIN ADDRESS ON LE_ADDRESS_ASSOC.ADDRESS_IDPK = ADDRESS.ID_PK ");

				queryCriteriaString.append("OR (  ");

				if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
					queryCriteriaString.append(
							" (LE_ADDRESS_ASSOC.DELETED_TS is null or LE_ADDRESS_ASSOC.DELETED_TS > current_timestamp) ");

				} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
					queryCriteriaString.append(
							" (LE_ADDRESS_ASSOC.DELETED_TS IS NOT NULL AND LE_ADDRESS_ASSOC.DELETED_TS < current_timestamp) ");
				} else {
					queryCriteriaString.append(" 1=1 ");
				}

				if (!isNullOrEmpty(addressLineOneList)) {
					queryCriteriaString.append(" AND ADDRESS.ADDRESS_LINE_ONE IN (:addressLineOneList) ");
					paramMapForListOfString.put("addressLineOneList", addressLineOneList);
				}
				if (!isNullOrEmpty(addressLineTwoList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.ADDRESS_LINE_TWO IN (:addressLineTwoList) OR ADDRESS.ADDRESS_LINE_TWO IS NULL) ");
					paramMapForListOfString.put("addressLineTwoList", addressLineTwoList);
				}
				if (!isNullOrEmpty(addressLineThreeList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.ADDRESS_LINE_THREE IN (:addressLineThreeList) OR ADDRESS.ADDRESS_LINE_THREE IS NULL) ");
					paramMapForListOfString.put("addressLineThreeList", addressLineThreeList);
				}
				if (!isNullOrEmpty(addressLineFourList)) {
					queryCriteriaString.append(
							" AND (ADDRESS.ADDRESS_LINE_FOUR IN (:addressLineFourList) OR ADDRESS.ADDRESS_LINE_FOUR IS NULL) ");
					paramMapForListOfString.put("addressLineFourList", addressLineFourList);
				}
				if (!isNullOrEmpty(streetNameList)) {
					queryCriteriaString
							.append(" AND (ADDRESS.STREET_NAME IN (:streetNameList) OR ADDRESS.STREET_NAME IS NULL) ");
					paramMapForListOfString.put("streetNameList", streetNameList);
				}
				if (!isNullOrEmpty(cityList)) {
					queryCriteriaString.append(" AND ADDRESS.CITY IN (:cityList) ");
					paramMapForListOfString.put("cityList", cityList);
				}
				if (!isNullOrEmpty(countryRefkeyList)) {
					queryCriteriaString.append(" AND ADDRESS.COUNTRY_REFKEY IN (:countryRefkeyList) ");
					paramMapForListOfString.put("countryRefkeyList", countryRefkeyList);
				}
				if (!isNullOrEmpty(postalCodeList)) {
					queryCriteriaString
							.append(" AND (ADDRESS.POSTAL_CODE IN (:postalCodeList) OR ADDRESS.POSTAL_CODE IS NULL)");
					paramMapForListOfString.put("postalCodeList", postalCodeList);
				}

				queryCriteriaString.append(") ");

			}

			queryCriteriaString.append(" ) ");
		}
		paramMapForListOfString.put("personNameOneList", personNameOneList);
		paramMapForListOfString.put("personLastNameList", personLastNameList);
		paramMapForListOfString.put("identificationTypeRefkeyList", identificationTypeRefkeyList);
		paramMapForListOfString.put("identificationNumberList", identificationNumberList);

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchLegalEntityByLEAttributesService search Query is -" + queryJoinString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), LegalentityDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, List<String>>> iterator = paramMapForListOfString.entrySet().iterator(); iterator
				.hasNext();) {
			Entry<String, List<String>> mapEntry = iterator.next();
			logger.debug("SearchLegalEntityByLEAttributesService parameter Name:" + mapEntry.getKey() + " Value:"
					+ mapEntry.getValue());
			searchQuery.setParameter(mapEntry.getKey(), mapEntry.getValue());
		}

		// set the query parameters date of birth and gender as those are not
		// added in the paramMapForListOfString
		if (null != dateOfBirth) {
			// searchQuery.setParameter("dateOfBirth", dateOfBirth);
			logger.debug("SearchLegalEntityByLEAttributesService parameter Name: dateOfBirth Value:" + dateOfBirth);
		}

		if (!isNullOrEmpty(genderRefkey)) {
			searchQuery.setParameter("genderRefkey", genderRefkey);
			logger.debug("SearchLegalEntityByLEAttributesService parameter Name: genderRefkey Value:" + genderRefkey);
		}

		return searchQuery;
	}

	
	//remove null objects from List
	private List<String> removeNullObjectsFromList(List<String> inputList) {
		if(null != inputList && inputList.size() > 0){
			Iterator<String> itr = inputList.iterator();
			while(itr.hasNext()){
				if(null == itr.next()){
					itr.remove();
				}
			}
			
		}
		
		return inputList;
	}

	private boolean isNullOrEmpty(String strToCheck) {

		if (null == strToCheck || strToCheck.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isNullOrEmpty(List<String> strListToCheck) {

		if (null == strListToCheck || strListToCheck.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * perform the common validation that SearchMatchCandidateLERequestDO is not
	 * null and mandatory inputs
	 * searchMatchCandidateLERequestDO.identificationNumberList and nameOneList
	 * and lastNameList or
	 * searchMatchCandidateLERequestDO.identificationNumberList and
	 * corporationNameList is provided
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

		if (null == txnTransferObj.getTxnPayload().getSearchMatchCandidateLERequestDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"searchMatchCandidateLERequestDO is needed in the request");
		}

		SearchMatchCandidateLERequestDO theSearchMatchCandidateLERequestDO = (SearchMatchCandidateLERequestDO) txnTransferObj
				.getTxnPayload().getSearchMatchCandidateLERequestDO();

		// Check that atleast LE Names and Identifier is present in the request
		if (isNullOrEmpty(theSearchMatchCandidateLERequestDO.getIdentificationTypeRefkeyList())) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10112",
					"The attributes searchMatchCandidateLERequestDO.IdentificationTypeRefkeyList is required");
		}

		if (isNullOrEmpty(theSearchMatchCandidateLERequestDO.getIdentificationNumberList())) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10112",
					"The attributes searchMatchCandidateLERequestDO.identificationNumberList is required");
		}

		if (isNullOrEmpty(theSearchMatchCandidateLERequestDO.getCorporationNameList())) {

			if (isNullOrEmpty(theSearchMatchCandidateLERequestDO.getPersonNameOneList())) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10112",
						"The attributes searchMatchCandidateLERequestDO.nameOneList is required");
			}

			if (isNullOrEmpty(theSearchMatchCandidateLERequestDO.getPersonLastNameList())) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10112",
						"The attributes searchMatchCandidateLERequestDO.lastNameList is required");
			}

		}

		if (isNullOrEmpty(txnTransferObj.getTxnPayload().getSearchMatchCandidateLERequestDO().getInquiryFilter())) {
			txnTransferObj.getTxnPayload().getSearchMatchCandidateLERequestDO()
					.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
		} else {

			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getSearchMatchCandidateLERequestDO().getInquiryFilter());
		}

	}
}
