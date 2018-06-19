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
@Service("com.yugandhar.mdm.composite.service.SearchLegalEntityByAccountAttributesService")
public class SearchLegalEntityByAccountAttributesService {

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

	public SearchLegalEntityByAccountAttributesService() {
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
		String accountidPk = reqSearchLegalEntityRequestDO.getAccountidPk();
		String sourceSystemRefkey = reqSearchLegalEntityRequestDO.getSourceSystemRefkey();
		String sourceAccountId = reqSearchLegalEntityRequestDO.getSourceAccountId();
		String accountName = reqSearchLegalEntityRequestDO.getAccountName();
		String accountName2 = reqSearchLegalEntityRequestDO.getAccountName2();
		String accountDescription = reqSearchLegalEntityRequestDO.getAccountDescription();
		String accountSourceStatusRefkey = reqSearchLegalEntityRequestDO.getAccountSourceStatusRefkey();
		String accountMdmStatusRefkey = reqSearchLegalEntityRequestDO.getAccountMdmStatusRefkey();
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

		if (!(isNullOrEmpty(accountidPk) && isNullOrEmpty(sourceSystemRefkey) && isNullOrEmpty(sourceAccountId)
				&& isNullOrEmpty(accountName) && isNullOrEmpty(accountName2) && isNullOrEmpty(accountDescription)
				&& isNullOrEmpty(accountSourceStatusRefkey) && isNullOrEmpty(accountMdmStatusRefkey))) {
			queryJoinString.append(" join LE_ACCOUNT_ASSOC on legalentity.ID_PK = LE_ACCOUNT_ASSOC.LEGALENTITY_IDPK ");
			queryJoinString.append(" join ACCOUNT on LE_ACCOUNT_ASSOC.ACCOUNT_IDPK = ACCOUNT.ID_PK ");

			if (!isNullOrEmpty(accountidPk)) {
				queryCriteriaString.append(" and ACCOUNT.ID_PK like :accountidPk ");
				paramMap.put("accountidPk", accountidPk);
			}

			if (!isNullOrEmpty(sourceSystemRefkey)) {
				queryCriteriaString.append(" AND ACCOUNT.SOURCE_SYSTEM_REFKEY like :sourceSystemRefkey ");
				paramMap.put("sourceSystemRefkey", sourceSystemRefkey);
			}
			if (!isNullOrEmpty(sourceAccountId)) {
				queryCriteriaString.append(" AND ACCOUNT.SOURCE_ACCOUNT_ID like :sourceAccountId ");
				paramMap.put("sourceAccountId", sourceAccountId);
			}

			if (!isNullOrEmpty(accountName)) {
				queryCriteriaString.append(" AND ACCOUNT.ACCOUNT_NAME like :accountName ");
				paramMap.put("accountName", accountName);
			}
			if (!isNullOrEmpty(accountName2)) {
				queryCriteriaString.append(" AND ACCOUNT.ACCOUNT_NAME2 like :accountName2 ");
				paramMap.put("accountName2", accountName2);
			}

			if (!isNullOrEmpty(accountDescription)) {
				queryCriteriaString.append(" AND ACCOUNT.ACCOUNT_DESCRIPTION like :accountDescription ");
				paramMap.put("accountDescription", accountDescription);
			}

			if (!isNullOrEmpty(accountSourceStatusRefkey)) {
				queryCriteriaString.append(" AND ACCOUNT.ACCOUNT_SOURCE_STATUS_REFKEY= :accountSourceStatusRefkey ");
				paramMap.put("accountSourceStatusRefkey", accountSourceStatusRefkey);
			}

			if (!isNullOrEmpty(accountMdmStatusRefkey)) {
				queryCriteriaString.append(" AND ACCOUNT.ACCOUNT_MDM_STATUS_REFKEY= :accountMdmStatusRefkey ");
				paramMap.put("accountMdmStatusRefkey", accountMdmStatusRefkey);
			}

			if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_ACCOUNT_ASSOC.DELETED_TS is null or LE_ACCOUNT_ASSOC.DELETED_TS > current_timestamp) ");
				queryCriteriaString
						.append(" AND (ACCOUNT.DELETED_TS is null or ACCOUNT.DELETED_TS > current_timestamp) ");
			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
				queryCriteriaString.append(
						" AND (LE_ACCOUNT_ASSOC.DELETED_TS IS NOT NULL AND LE_ACCOUNT_ASSOC.DELETED_TS < current_timestamp) ");
				queryCriteriaString
						.append(" AND (ACCOUNT.DELETED_TS IS NOT NULL AND ACCOUNT.DELETED_TS < current_timestamp) ");
			}

		}

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchLegalEntityByAccountAttributesService search Query is -" + queryJoinString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), LegalentityDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("SearchLegalEntityByAccountAttributesService parameter Name:" +mapEntry.getKey() + " Value:" + mapEntry.getValue());
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

		// If all the attributes are null then throw error
		if (isNullOrEmpty(theSearchLegalEntityRequestDO.getDisplayName())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getAccountidPk())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getSourceSystemRefkey())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getSourceAccountId())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getAccountName())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getAccountName2())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getAccountDescription())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getAccountSourceStatusRefkey())
				&& isNullOrEmpty(theSearchLegalEntityRequestDO.getAccountMdmStatusRefkey())) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10070",
					"Must provide atleast one of the attributes displayName,accountidPk,sourceSystemRefkey,sourceAccountId,accountName,accountName2,accountDescription,accountSourceStatusRefkey or accountMdmStatusRefkey");

		}

		if (null == txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO()
					.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
		} else {

			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getSearchLegalEntityRequestDO().getInquiryFilter());
		}

	}
}
