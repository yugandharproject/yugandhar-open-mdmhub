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
@Service("com.yugandhar.mdm.composite.service.SearchAccountByAccountAttributesService")
public class SearchAccountByAccountAttributesService {

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

	public SearchAccountByAccountAttributesService() {
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
					"createAccountService failed unexpectedly");

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
		String accountidPk = reqSearchAccountRequestDO.getAccountidPk();
		String sourceSystemRefkey = reqSearchAccountRequestDO.getSourceSystemRefkey();
		String sourceAccountId = reqSearchAccountRequestDO.getSourceAccountId();
		String accountName = reqSearchAccountRequestDO.getAccountName();
		String accountName2 = reqSearchAccountRequestDO.getAccountName2();
		String accountDescription = reqSearchAccountRequestDO.getAccountDescription();
		String accountSourceStatusRefkey = reqSearchAccountRequestDO.getAccountSourceStatusRefkey();
		String accountMdmStatusRefkey = reqSearchAccountRequestDO.getAccountMdmStatusRefkey();
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
		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString
					.append(" where (ACCOUNT.DELETED_TS IS NULL OR ACCOUNT.DELETED_TS > CURRENT_TIMESTAMP) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString
					.append(" where (ACCOUNT.DELETED_TS IS NOT NULL AND ACCOUNT.DELETED_TS < CURRENT_TIMESTAMP) ");
		} else {
			queryCriteriaString.append(" where 1=1 ");
		}

		if (!(isNullOrEmpty(accountidPk) && isNullOrEmpty(sourceSystemRefkey) && isNullOrEmpty(sourceAccountId)
				&& isNullOrEmpty(accountName) && isNullOrEmpty(accountName2) && isNullOrEmpty(accountDescription)
				&& isNullOrEmpty(accountSourceStatusRefkey) && isNullOrEmpty(accountMdmStatusRefkey))) {

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

		}

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchAccountByAccountAttributesService search Query is -" + queryJoinString.toString());
		// get Native query instance
		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), AccountDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("SearchAccountByAccountAttributesService parameter Name:" + mapEntry.getKey() + " Value:"
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
	 * perform the common validation that AccountDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AccountDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeExecution(TxnTransferObj txnTransferObj) {

		if (null == txnTransferObj.getTxnPayload().getSearchAccountRequestDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"SearchAccountRequestDO is needed in the request");
		}

		SearchAccountRequestDO theSearchAccountRequestDO = (SearchAccountRequestDO) txnTransferObj.getTxnPayload()
				.getSearchAccountRequestDO();

		// If all the attributes are null then throw error
		if (isNullOrEmpty(theSearchAccountRequestDO.getAccountidPk())
				&& isNullOrEmpty(theSearchAccountRequestDO.getSourceSystemRefkey())
				&& isNullOrEmpty(theSearchAccountRequestDO.getSourceAccountId())
				&& isNullOrEmpty(theSearchAccountRequestDO.getAccountName())
				&& isNullOrEmpty(theSearchAccountRequestDO.getAccountName2())
				&& isNullOrEmpty(theSearchAccountRequestDO.getAccountDescription())
				&& isNullOrEmpty(theSearchAccountRequestDO.getAccountSourceStatusRefkey())
				&& isNullOrEmpty(theSearchAccountRequestDO.getAccountMdmStatusRefkey())) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10070",
					"Must provide atleast one of the attributes accountidPk,sourceSystemRefkey,sourceAccountId,accountName,accountName2,accountDescription,accountSourceStatusRefkey or accountMdmStatusRefkey");

		}

		if (null == txnTransferObj.getTxnPayload().getSearchAccountRequestDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getSearchAccountRequestDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getSearchAccountRequestDO()
					.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
		} else {

			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getSearchAccountRequestDO().getInquiryFilter());
		}

	}
}
