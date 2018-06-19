package com.yugandhar.mdm.composite.service;

import java.util.ArrayList;
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
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.corecomponent.LegalentityComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.SearchLegalEntityRequestDO;

/**
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.SearchLegalEntityByLEAttributesService")
public class SearchLegalEntityByLEAttributesService {

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
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@PersistenceContext
	private EntityManager entityManager;

	public SearchLegalEntityByLEAttributesService() {
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

			Query searchQuery = buildSearchQuery(txnTransferObj);

			setPaginationProperties(searchQuery, txnTransferObj);

			@SuppressWarnings("unchecked")
			List<LegalentityDO> searchResultLegalentityDOList = searchQuery.getResultList();

			String inquiryLevel = txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO().getInquiryLevel();

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
			theLegalentityDO
					.setInquiryLevel(txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO().getInquiryLevel());
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

	private Query buildSearchQuery(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		SearchLegalEntityRequestDO reqSearchLegalEntityRequestDO = txnTransferObj.getTxnPayload()
				.getSearchLegalEntityRequestDO();

		// parameters
		String displayName = reqSearchLegalEntityRequestDO.getDisplayName();
		String personNameOne = reqSearchLegalEntityRequestDO.getPersonNameOne();
		String personLastName = reqSearchLegalEntityRequestDO.getPersonLastName();
		String corporationName = reqSearchLegalEntityRequestDO.getCorporationName();
		String addressLineOne = reqSearchLegalEntityRequestDO.getAddressLineOne();
		String addressLineTwo = reqSearchLegalEntityRequestDO.getAddressLineTwo();
		String city = reqSearchLegalEntityRequestDO.getCity();
		String stateProvinceRefkey = reqSearchLegalEntityRequestDO.getStateProvinceRefkey();
		String countryRefkey = reqSearchLegalEntityRequestDO.getCountryRefkey();
		String postalCode = reqSearchLegalEntityRequestDO.getPostalCode();
		String identificationTypeRefkey = reqSearchLegalEntityRequestDO.getIdentificationTypeRefkey();
		String identificationNumber = reqSearchLegalEntityRequestDO.getIdentificationNumber();
		String phoneNumber = reqSearchLegalEntityRequestDO.getPhoneNumber();
		String systemKeysRegistryReferenceId = reqSearchLegalEntityRequestDO.getSystemKeysRegistryReferenceId();
		String systemKeysRegistrySourceSystemRefkey = reqSearchLegalEntityRequestDO
				.getSystemKeysRegistrySourceSystemRefkey();
		String inquiryFilter = reqSearchLegalEntityRequestDO.getInquiryFilter();
		// String inquiryLevel =
		// reqSearchLegalEntityRequestDO.getInquiryLevel();

		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		HashMap<String, String> paramMap = new HashMap<String, String>();

		queryJoinString.append(
				"select distinct legalentity.id_pk, legalentity.VERSION, legalentity.CREATED_TS, legalentity.DELETED_TS, legalentity.UPDATED_TS, legalentity.UPDATED_BY_USER, legalentity.UPDATED_BY_TXN_ID, legalentity.DISPLAY_NAME, legalentity.ENTITY_OBJECT_TYPE_REFKEY, legalentity.CLASSIFICATION_CODE_REFKEY, legalentity.IMPORTANCE_TYPE_REFKEY, legalentity.LE_RATING_REFKEY, legalentity.STATUS_TYPE_REFKEY, legalentity.SOURCE_SYSTEM_REFKEY, legalentity.ONBOARDING_DATE, legalentity.OFFBOARDING_DATE, legalentity.KYC_VERIFICATION_FLAG, legalentity.DESCRIPTION from legalentity ");
		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString
					.append(" where (legalentity.DELETED_TS is null or legalentity.DELETED_TS > current_timestamp) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString.append(
					" where (legalentity.DELETED_TS IS NOT NULL AND legalentity.DELETED_TS < current_timestamp) ");
		} else {
			queryCriteriaString.append(" where 1=1 ");
		}

		if (!isNullOrEmpty(displayName)) {
			// queryCriteriaString.append(" and legalentity.display_name like
			// '%" + displayName + "%'");
			queryCriteriaString.append(" and legalentity.display_name like :displayName ");
			paramMap.put("displayName", displayName);
		}

		if (!isNullOrEmpty(personNameOne) || !isNullOrEmpty(personLastName)) {
			queryJoinString.append("  join PERSONNAMES on legalentity.ID_PK = PERSONNAMES.LEGALENTITY_IDPK ");

			if (!isNullOrEmpty(personNameOne)) {
				queryCriteriaString.append(" and PERSONNAMES.NAME_ONE like :personNameOne ");
				paramMap.put("personNameOne", personNameOne);
			}

			if (!isNullOrEmpty(personLastName)) {
				queryCriteriaString.append("and PERSONNAMES.LAST_NAME like :personLastName ");
				paramMap.put("personLastName", personLastName);
			}

			if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
				queryCriteriaString
						.append(" AND (PERSONNAMES.DELETED_TS is null or PERSONNAMES.DELETED_TS > current_timestamp) ");

			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
				queryCriteriaString.append(
						" AND (PERSONNAMES.DELETED_TS IS NOT NULL AND PERSONNAMES.DELETED_TS < current_timestamp) ");
			}

		} else {

			if (!isNullOrEmpty(corporationName)) {
				queryJoinString
						.append(" join CORPORATIONNAMES on legalentity.ID_PK = CORPORATIONNAMES.LEGALENTITY_IDPK ");

				queryCriteriaString.append(" and CORPORATIONNAMES.CORPORATION_NAME like :corporationName ");
				paramMap.put("corporationName", corporationName);

				if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
					queryCriteriaString.append(
							" AND (CORPORATIONNAMES.DELETED_TS is null or CORPORATIONNAMES.DELETED_TS > current_timestamp) ");

				} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
					queryCriteriaString.append(
							" AND (CORPORATIONNAMES.DELETED_TS IS NOT NULL AND CORPORATIONNAMES.DELETED_TS < current_timestamp) ");
				}

			}
		}

		if (!isNullOrEmpty(addressLineOne) || !isNullOrEmpty(addressLineTwo) || !isNullOrEmpty(city)
				|| !isNullOrEmpty(stateProvinceRefkey) || !isNullOrEmpty(countryRefkey) || !isNullOrEmpty(postalCode)) {
			queryJoinString.append(" join LE_ADDRESS_ASSOC on legalentity.ID_PK = LE_ADDRESS_ASSOC.LEGALENTITY_IDPK "
					+ " join ADDRESS on LE_ADDRESS_ASSOC.ADDRESS_IDPK = ADDRESS.ID_PK ");

			if (!isNullOrEmpty(addressLineOne)) {
				queryCriteriaString.append(" and ADDRESS.ADDRESS_LINE_ONE like :addressLineOne ");
				paramMap.put("addressLineOne", addressLineOne);
			}

			if (!isNullOrEmpty(addressLineTwo)) {
				queryCriteriaString.append(" and ADDRESS.ADDRESS_LINE_TWO like :addressLineTwo ");
				paramMap.put("addressLineTwo", addressLineTwo);
			}
			if (!isNullOrEmpty(city)) {
				queryCriteriaString.append(" and ADDRESS.CITY like :city ");
				paramMap.put("city", city);
			}

			if (!isNullOrEmpty(stateProvinceRefkey)) {
				queryCriteriaString.append(" and ADDRESS.STATE_PROVINCE_REFKEY = :stateProvinceRefkey ");
				paramMap.put("stateProvinceRefkey", stateProvinceRefkey);
			}
			if (!isNullOrEmpty(countryRefkey)) {
				queryCriteriaString.append(" and ADDRESS.COUNTRY_REFKEY = :countryRefkey ");
				paramMap.put("countryRefkey", countryRefkey);
			}

			if (!isNullOrEmpty(postalCode)) {
				queryCriteriaString.append(" and ADDRESS.POSTAL_CODE like :postalCode ");
				paramMap.put("postalCode", postalCode);
			}

			if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_ADDRESS_ASSOC.DELETED_TS is null or LE_ADDRESS_ASSOC.DELETED_TS > current_timestamp) ");

			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_ADDRESS_ASSOC.DELETED_TS IS NOT NULL AND LE_ADDRESS_ASSOC.DELETED_TS < current_timestamp) ");
			}

		}

		if (!isNullOrEmpty(phoneNumber)) {
			queryJoinString.append(" join LE_PHONE_ASSOC on legalentity.ID_PK = LE_PHONE_ASSOC.LEGALENTITY_IDPK ");

			queryCriteriaString.append(" and LE_PHONE_ASSOC.PHONE_NUMBER like :phoneNumber ");
			paramMap.put("phoneNumber", phoneNumber);

			if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_PHONE_ASSOC.DELETED_TS is null or LE_PHONE_ASSOC.DELETED_TS > current_timestamp) ");

			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_PHONE_ASSOC.DELETED_TS IS NOT NULL AND LE_PHONE_ASSOC.DELETED_TS < current_timestamp) ");
			}

		}

		if (!isNullOrEmpty(identificationTypeRefkey) || !isNullOrEmpty(identificationNumber)) {
			queryJoinString.append(
					" join LE_IDENTIFIER_KYC_REGISTRY on legalentity.ID_PK = LE_IDENTIFIER_KYC_REGISTRY.LEGALENTITY_IDPK ");

			if (!isNullOrEmpty(identificationTypeRefkey)) {
				queryCriteriaString.append(
						" and LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_TYPE_REFKEY =  :identificationTypeRefkey ");
				paramMap.put("identificationTypeRefkey", identificationTypeRefkey);

			}

			if (!isNullOrEmpty(identificationNumber)) {
				queryCriteriaString
						.append("and LE_IDENTIFIER_KYC_REGISTRY.IDENTIFICATION_NUMBER like :identificationNumber  ");
				paramMap.put("identificationNumber", identificationNumber);

			}

			if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS is null or LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS > current_timestamp) ");

			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS IS NOT NULL AND LE_IDENTIFIER_KYC_REGISTRY.DELETED_TS < current_timestamp) ");
			}

		}

		if (!isNullOrEmpty(systemKeysRegistrySourceSystemRefkey) || !isNullOrEmpty(systemKeysRegistryReferenceId)) {
			queryJoinString.append(
					" join LE_SYSTEM_KEYS_REGISTRY on legalentity.ID_PK = LE_SYSTEM_KEYS_REGISTRY.LEGALENTITY_IDPK ");

			if (!isNullOrEmpty(systemKeysRegistrySourceSystemRefkey)) {
				queryCriteriaString.append(
						" and LE_SYSTEM_KEYS_REGISTRY.SOURCE_SYSTEM_REFKEY= :systemKeysRegistrySourceSystemRefkey ");
				paramMap.put("systemKeysRegistrySourceSystemRefkey", systemKeysRegistrySourceSystemRefkey);

			}

			if (!isNullOrEmpty(systemKeysRegistryReferenceId)) {
				queryCriteriaString
						.append("and LE_SYSTEM_KEYS_REGISTRY.REFERENCE_ID like :systemKeysRegistryReferenceId ");
				paramMap.put("systemKeysRegistryReferenceId", systemKeysRegistryReferenceId);

			}

			if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_SYSTEM_KEYS_REGISTRY.DELETED_TS is null or LE_SYSTEM_KEYS_REGISTRY.DELETED_TS > current_timestamp) ");

			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_SYSTEM_KEYS_REGISTRY.DELETED_TS IS NOT NULL AND LE_SYSTEM_KEYS_REGISTRY.DELETED_TS < current_timestamp) ");
			}

		}

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchLegalEntityByLEAttributesService search Query is -" + queryJoinString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), LegalentityDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("SearchLegalEntityByLEAttributesService parameter Name:" +mapEntry.getKey() + " Value:" + mapEntry.getValue());
			searchQuery.setParameter(mapEntry.getKey(), mapEntry.getValue());
		}

		return searchQuery;
	}

	private boolean isNullOrEmpty(String strToCheck) {

		if (null == strToCheck || strToCheck.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}

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

		if (null == txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"SearchLegalEntityRequestDO is needed in the request");
		}

		SearchLegalEntityRequestDO theSearchLegalEntityRequestDO = (SearchLegalEntityRequestDO) txnTransferObj
				.getTxnPayload().getSearchLegalEntityRequestDO();

		// If unique identifiers like systemKeysRegistryReferenceId or
		// identificationNumber or phoneNumber are null
		// then check if atleast one of the names is provided in the request
		if (isNullOrEmpty(theSearchLegalEntityRequestDO.getSystemKeysRegistryReferenceId())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getIdentificationNumber())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getPhoneNumber())) {

			if (isNullOrEmpty(theSearchLegalEntityRequestDO.getDisplayName())
					&& isNullOrEmpty(theSearchLegalEntityRequestDO.getCorporationName())
					&& isNullOrEmpty(theSearchLegalEntityRequestDO.getPersonNameOne())
					&& isNullOrEmpty(theSearchLegalEntityRequestDO.getPersonLastName())) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10070",
						"One of the attributes SearchLegalEntityRequestDO.displayName, nameOne, lastName, corporationName is required if systemKeysRegistryReferenceId or identificationNumber or phoneNumber is not provided");
			}
		}

		if (isNullOrEmpty(txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO().getInquiryFilter())) {
			txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO()
					.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
		} else {

			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO().getInquiryFilter());
		}

	}
}
