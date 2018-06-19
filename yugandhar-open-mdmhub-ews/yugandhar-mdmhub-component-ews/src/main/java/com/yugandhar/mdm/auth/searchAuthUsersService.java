package com.yugandhar.mdm.auth;

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
import com.yugandhar.mdm.extern.dobj.AuthUserRegistryDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.SearchAuthAccessControlDO;

/**
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 *  
 * find the Auth users based on userName or roleName
 * Mandatory object is searchAuthAccessControlDO
 * Mandatory parameters searchAuthAccessControlDO.rolename or userName
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.auth.searchAuthUsersService")
public class searchAuthUsersService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@PersistenceContext
	private EntityManager entityManager;

	public searchAuthUsersService() {
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
			List<AuthUserRegistryDO> searchResultAuthUserRegistryDOList = searchQuery.getResultList();

			respTxnPayload.setAuthUserRegistryDOList(searchResultAuthUserRegistryDOList);

			// Final Response object

			respTxnTransferObj.setTxnPayload(respTxnPayload);

		} catch (YugandharCommonException yce) {
			logger.error("Composite Service failed", yce);
			throw yce;
		} catch (Exception e) {
			// write the logic to get error message based on error code. Error
			// code is hard-coded here
			logger.error("Composite Service failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
					"SearchRolesByUserName failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

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
		SearchAuthAccessControlDO theSearchAuthAccessControlDO = (SearchAuthAccessControlDO) txnTransferObj
				.getTxnPayload().getSearchAuthAccessControlDO();

		// parameters
		String userName = theSearchAuthAccessControlDO.getUserName();
		String roleName = theSearchAuthAccessControlDO.getRoleName();
		String inquiryFilter = theSearchAuthAccessControlDO.getInquiryFilter();

		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		HashMap<String, String> paramMap = new HashMap<String, String>();

		/*queryJoinString.append(
				" SELECT DISTINCT AUTH_USER_REGISTRY.ID_PK, AUTH_USER_REGISTRY.VERSION, AUTH_USER_REGISTRY.CREATED_TS, AUTH_USER_REGISTRY.DELETED_TS, AUTH_USER_REGISTRY.UPDATED_TS, AUTH_USER_REGISTRY.UPDATED_BY_USER, AUTH_USER_REGISTRY.UPDATED_BY_TXN_ID, AUTH_USER_REGISTRY.USER_NAME, AUTH_USER_REGISTRY.DESCRIPTION FROM AUTH_USER_REGISTRY ");*/
		
		queryJoinString.append(
				" SELECT DISTINCT AUTH_USER_REGISTRY.* FROM AUTH_USER_REGISTRY ");

		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString.append(
					" WHERE (AUTH_USER_REGISTRY.DELETED_TS IS NULL OR AUTH_USER_REGISTRY.DELETED_TS > CURRENT_TIMESTAMP) ");

			if (!isNullOrEmpty(roleName)) {
				queryCriteriaString.append(
						" AND (AUTH_ROLES_REGISTRY.DELETED_TS IS NULL OR AUTH_ROLES_REGISTRY.DELETED_TS > CURRENT_TIMESTAMP)  ");

				queryCriteriaString.append(
						" AND (AUTH_USER_ROLE_ASSOC.DELETED_TS IS NULL OR AUTH_USER_ROLE_ASSOC.DELETED_TS > CURRENT_TIMESTAMP) ");
			}

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString.append(
					" WHERE (AUTH_USER_REGISTRY.DELETED_TS IS NOT NULL AND AUTH_USER_REGISTRY.DELETED_TS < CURRENT_TIMESTAMP) ");

			if (!isNullOrEmpty(roleName)) {
				queryCriteriaString.append(
						" AND (AUTH_ROLES_REGISTRY.DELETED_TS IS NOT NULL AND AUTH_ROLES_REGISTRY.DELETED_TS < CURRENT_TIMESTAMP)  ");

				queryCriteriaString.append(
						" AND (AUTH_USER_ROLE_ASSOC.DELETED_TS IS NOT NULL AND AUTH_USER_ROLE_ASSOC.DELETED_TS < CURRENT_TIMESTAMP) ");
			}

		} else {
			queryCriteriaString.append(" WHERE 1=1 ");
		}

		if (!isNullOrEmpty(userName)) {

			queryCriteriaString.append(" AND UPPER(AUTH_USER_REGISTRY.USER_NAME) LIKE :userName ");
			paramMap.put("userName", userName.toUpperCase());

		}

		if (!isNullOrEmpty(roleName)) {

			queryCriteriaString.append(" AND UPPER(AUTH_ROLES_REGISTRY.ROLE_NAME) LIKE :roleName ");
			paramMap.put("roleName", roleName.toUpperCase());
			
			queryJoinString.append(
					" JOIN AUTH_USER_ROLE_ASSOC ON AUTH_USER_ROLE_ASSOC.AUTH_USER_REGISTRY_IDPK =  AUTH_USER_REGISTRY.ID_PK ");
			queryJoinString.append(
					" JOIN AUTH_ROLES_REGISTRY  ON AUTH_ROLES_REGISTRY.ID_PK = AUTH_USER_ROLE_ASSOC.AUTH_ROLES_REGISTRY_IDPK ");
		}

		queryJoinString.append(queryCriteriaString);
		logger.info("searchAuthUsersService search Query is -" + queryJoinString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), AuthUserRegistryDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug(
					"searchAuthUsersService parameter Name:" + mapEntry.getKey() + " Value:" + mapEntry.getValue());
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
	 * perform the common validation
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if searchAuthAccessControlDO.rolename or userName is not
	 *             present in the request or mandatory attributes business key
	 *             is not present
	 */
	private void performCommonvalidationBeforeExecution(TxnTransferObj txnTransferObj) {

		if (null == txnTransferObj.getTxnPayload().getSearchAuthAccessControlDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"SearchAuthAccessControlDO is needed in the request");
		}

		SearchAuthAccessControlDO theSearchAuthAccessControlDO = (SearchAuthAccessControlDO) txnTransferObj
				.getTxnPayload().getSearchAuthAccessControlDO();

		// If getRoleName or getUserName is null
		// then throw error
		if (isNullOrEmpty(theSearchAuthAccessControlDO.getRoleName())
				&& isNullOrEmpty(theSearchAuthAccessControlDO.getUserName())) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10081",
					"One of the attributes searchAuthAccessControlDO.rolename or userName is required");

		}

		if (isNullOrEmpty(txnTransferObj.getTxnPayload().getSearchAuthAccessControlDO().getInquiryFilter())) {
			txnTransferObj.getTxnPayload().getSearchAuthAccessControlDO()
					.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
		} else {

			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getSearchAuthAccessControlDO().getInquiryFilter());
		}

	}
}
