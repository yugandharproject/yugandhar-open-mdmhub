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
import com.yugandhar.mdm.extern.dobj.AccountDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.SearchAccountRequestDO;

/**
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.SearchAccountByLEAttributesService")
public class SearchAccountByLEAttributesService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	RetrieveAccountByAccountIdService retrieveAccountByAccountIdService;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@PersistenceContext
	private EntityManager entityManager;

	public SearchAccountByLEAttributesService() {
		txnTransferObjResponse = new TxnTransferObj();
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
			List<AccountDO> searchResultAccountDOList = searchQuery.getResultList();

			String inquiryLevel = txnTransferObj.getTxnPayload().getSearchAccountRequestDO().getInquiryLevel();

			if (null == inquiryLevel || inquiryLevel.isEmpty()) {
				respTxnPayload.setAccountDOList(searchResultAccountDOList);
			} else {
				List<AccountDO> retrieveResultAccountDOList = retrieveAccountAsPerInquiryLevel(
						searchResultAccountDOList, txnTransferObj);
				respTxnPayload.setAccountDOList(retrieveResultAccountDOList);
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

	private List<AccountDO> retrieveAccountAsPerInquiryLevel(List<AccountDO> searchResultAccountDOList,
			TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		TxnPayload transitTxnPayload = null;

		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		List<AccountDO> retrieveResultAccountDOList = new ArrayList<AccountDO>();

		for (AccountDO resultAccountDO : searchResultAccountDOList) {
			AccountDO theAccountDO = new AccountDO();
			theAccountDO.setIdPk(resultAccountDO.getIdPk());
			theAccountDO.setInquiryFilter(yugandharConstants.FILTER_VALUE_ALL);
			theAccountDO.setInquiryLevel(txnTransferObj.getTxnPayload().getSearchAccountRequestDO().getInquiryLevel());
			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setAccountDO(theAccountDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);

			transitTxnTransferObj = retrieveAccountByAccountIdService.process(transitTxnTransferObj);
			retrieveResultAccountDOList.add(transitTxnTransferObj.getTxnPayload().getAccountDO());
		}

		return retrieveResultAccountDOList;
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
		SearchAccountRequestDO reqSearchAccountRequestDO = txnTransferObj.getTxnPayload().getSearchAccountRequestDO();

		// parameters
		String displayName = reqSearchAccountRequestDO.getDisplayName();
		String personNameOne = reqSearchAccountRequestDO.getPersonNameOne();
		String personLastName = reqSearchAccountRequestDO.getPersonLastName();
		String corporationName = reqSearchAccountRequestDO.getCorporationName();
		String addressLineOne = reqSearchAccountRequestDO.getAddressLineOne();
		String addressLineTwo = reqSearchAccountRequestDO.getAddressLineTwo();
		String city = reqSearchAccountRequestDO.getCity();
		String stateProvinceRefkey = reqSearchAccountRequestDO.getStateProvinceRefkey();
		String countryRefkey = reqSearchAccountRequestDO.getCountryRefkey();
		String postalCode = reqSearchAccountRequestDO.getPostalCode();
		String identificationTypeRefkey = reqSearchAccountRequestDO.getIdentificationTypeRefkey();
		String identificationNumber = reqSearchAccountRequestDO.getIdentificationNumber();
		String phoneNumber = reqSearchAccountRequestDO.getPhoneNumber();
		String systemKeysRegistryReferenceId = reqSearchAccountRequestDO.getSystemKeysRegistryReferenceId();
		String systemKeysRegistrySourceSystemRefkey = reqSearchAccountRequestDO
				.getSystemKeysRegistrySourceSystemRefkey();
		String inquiryFilter = reqSearchAccountRequestDO.getInquiryFilter();
		// String inquiryLevel =
		// reqSearchAccountRequestDO.getInquiryLevel();

		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		HashMap<String, String> paramMap = new HashMap<String, String>();

		queryJoinString.append(
				"select distinct ACCOUNT.ID_PK, ACCOUNT.VERSION, ACCOUNT.CREATED_TS, ACCOUNT.DELETED_TS, ACCOUNT.UPDATED_TS, ACCOUNT.UPDATED_BY_USER, ACCOUNT.UPDATED_BY_TXN_ID, ACCOUNT.CONTRACT_SIGNED_LANG_REFKEY, ACCOUNT.CURRENCY_REFKEY, ACCOUNT.BILLING_MODE_TYPE_REFKEY, ACCOUNT.FREQUENCY_OF_PAYMENT, ACCOUNT.LOBTYPE_REFKEY, ACCOUNT.LOB_DESCRIPTION, ACCOUNT.SOURCE_SYSTEM_REFKEY, ACCOUNT.SOURCE_ACCOUNT_ID, ACCOUNT.MANAGEDBY_BU_CODE, ACCOUNT.MANAGEDBY_BU_ID, ACCOUNT.BRANCH_CODE_REFKEY, ACCOUNT.ACCOUNT_NAME, ACCOUNT.ACCOUNT_NAME2, ACCOUNT.ACCOUNT_DESCRIPTION, ACCOUNT.ACCOUNT_SOURCE_STATUS_REFKEY, ACCOUNT.ACCOUNT_MDM_STATUS_REFKEY, ACCOUNT.SIGNED_DATE, ACCOUNT.SIGNED_PLACE, ACCOUNT.EXECUTED_DATE, ACCOUNT.TERMINATED_DATE, ACCOUNT.TERMINATION_REASON_REFKEY FROM ACCOUNT ");

		queryJoinString.append(" JOIN LE_ACCOUNT_ASSOC ON LE_ACCOUNT_ASSOC.ACCOUNT_IDPK = ACCOUNT.ID_PK ");
		queryJoinString.append(" JOIN LEGALENTITY ON LEGALENTITY.ID_PK = LE_ACCOUNT_ASSOC.LEGALENTITY_IDPK ");

		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString
					.append(" where (ACCOUNT.DELETED_TS IS NULL OR ACCOUNT.DELETED_TS > CURRENT_TIMESTAMP) ");
			queryCriteriaString.append(
					" AND (LE_ACCOUNT_ASSOC.DELETED_TS IS NULL OR LE_ACCOUNT_ASSOC.DELETED_TS > CURRENT_TIMESTAMP) ");
			queryCriteriaString
					.append(" AND (legalentity.DELETED_TS is null or legalentity.DELETED_TS > current_timestamp) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString
					.append(" where (ACCOUNT.DELETED_TS IS NOT NULL AND ACCOUNT.DELETED_TS < CURRENT_TIMESTAMP) ");
			queryCriteriaString.append(
					" AND (LE_ACCOUNT_ASSOC.DELETED_TS IS NOT NULL AND LE_ACCOUNT_ASSOC.DELETED_TS < CURRENT_TIMESTAMP) ");
			queryCriteriaString
					.append(" AND (legalentity.DELETED_TS IS NOT NULL AND legalentity.DELETED_TS < current_timestamp) ");
		} else {
			queryCriteriaString.append(" where 1=1 ");
		}

		if (!isNullOrEmpty(displayName)) {

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
		logger.info("SearchAccountByLEAttributesService search Query is -" + queryJoinString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), AccountDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("SearchAccountByLEAttributesService parameter Name:" + mapEntry.getKey() + " Value:"
					+ mapEntry.getValue());
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

		if (null == txnTransferObj.getTxnPayload().getSearchAccountRequestDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"SearchAccountRequestDO is needed in the request");
		}

		SearchAccountRequestDO theSearchAccountRequestDO = (SearchAccountRequestDO) txnTransferObj.getTxnPayload()
				.getSearchAccountRequestDO();

		// If unique identifiers like systemKeysRegistryReferenceId or
		// identificationNumber or phoneNumber are null
		// then check if atleast one of the names is provided in the request
		if (isNullOrEmpty(theSearchAccountRequestDO.getSystemKeysRegistryReferenceId())
				&& isNullOrEmpty(theSearchAccountRequestDO.getIdentificationNumber())
				&& isNullOrEmpty(theSearchAccountRequestDO.getPhoneNumber())) {

			if (isNullOrEmpty(theSearchAccountRequestDO.getDisplayName())
					&& isNullOrEmpty(theSearchAccountRequestDO.getCorporationName())
					&& isNullOrEmpty(theSearchAccountRequestDO.getPersonNameOne())
					&& isNullOrEmpty(theSearchAccountRequestDO.getPersonLastName())) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10070",
						"One of the attributes SearchAccountRequestDO.displayName, nameOne, lastName, corporationName is required if systemKeysRegistryReferenceId or identificationNumber or phoneNumber is not provided");
			}
		}

		if (isNullOrEmpty(txnTransferObj.getTxnPayload().getSearchAccountRequestDO().getInquiryFilter())) {
			txnTransferObj.getTxnPayload().getSearchAccountRequestDO()
					.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
		} else {

			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getSearchAccountRequestDO().getInquiryFilter());
		}

	}
}
