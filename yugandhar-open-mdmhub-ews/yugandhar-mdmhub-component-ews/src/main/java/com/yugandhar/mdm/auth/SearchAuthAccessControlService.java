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
import com.yugandhar.mdm.extern.dobj.AuthUserroleAccesscontrolDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.SearchAuthAccessControlDO;

/**
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 *  find the Auth users based on userName or roleName
 *      Mandatory object is searchAuthAccessControlDO Mandatory parameters
 *      searchAuthAccessControlDO.rolename or userName. If userName present -
 *      then return the results by userName only. If userName and roleName
 *      present then Verify that the user is part of the given role and then
 *      return the results by given role name. i.e. Authorization will be done
 *      based on roleName attribute only and userName attribute will not be used
 *      for the same. if roleName only present then return the results by
 *      roleName only.
 * 
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.auth.searchAuthAccessControlService")
public class SearchAuthAccessControlService {

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

	public SearchAuthAccessControlService() {
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
		SearchAuthAccessControlDO theSearchAuthAccessControlDO = (SearchAuthAccessControlDO) txnTransferObj
				.getTxnPayload().getSearchAuthAccessControlDO();

		// parameters
		String userName = theSearchAuthAccessControlDO.getUserName();
		String roleName = theSearchAuthAccessControlDO.getRoleName();
		Query searchQuery = null;

		if (!isNullOrEmpty(userName) && !isNullOrEmpty(roleName)) {
			searchQuery = buildSearchByUserNameAndRoleNameQuery(txnTransferObj);

		} else if (!isNullOrEmpty(userName)) {
			searchQuery = buildSearchByUserNameQuery(txnTransferObj);
		} else if (!isNullOrEmpty(roleName)) {
			searchQuery = buildSearchByRoleNameQuery(txnTransferObj);
		}

		return searchQuery;

	}

	private Query buildSearchByUserNameAndRoleNameQuery(TxnTransferObj txnTransferObj) {

		// TODO Auto-generated method stub
		SearchAuthAccessControlDO theSearchAuthAccessControlDO = (SearchAuthAccessControlDO) txnTransferObj
				.getTxnPayload().getSearchAuthAccessControlDO();

		// parameters
		String userName = theSearchAuthAccessControlDO.getUserName();
		String roleName = theSearchAuthAccessControlDO.getRoleName();
		String txnserviceName = theSearchAuthAccessControlDO.getTxnserviceName();
		String inquiryFilter = theSearchAuthAccessControlDO.getInquiryFilter();

		// String buffer for SQL
		StringBuffer queryString = new StringBuffer();

		// Parameter map
		HashMap<String, String> paramMap = new HashMap<String, String>();

		queryString.append(" SELECT DISTINCT AUTH_USERROLE_ACCESSCONTROL.* ");
		queryString.append("   FROM AUTH_USERROLE_ACCESSCONTROL ");
		queryString.append("  WHERE AUTH_USERROLE_ACCESSCONTROL.PROFILE_TYPE = 'ROLE' ");
		queryString.append("        AND AUTH_USERROLE_ACCESSCONTROL.AUTH_USER_ROLE_REGISTRY_IDPK IN ");
		queryString.append("               (SELECT DISTINCT AUTH_ROLES_REGISTRY.ID_PK ");
		queryString.append("                  FROM AUTH_ROLES_REGISTRY ");
		queryString.append("                       JOIN AUTH_USER_ROLE_ASSOC ");
		queryString.append("                          ON AUTH_USER_ROLE_ASSOC.AUTH_ROLES_REGISTRY_IDPK = ");
		queryString.append("                                AUTH_ROLES_REGISTRY.ID_PK ");
		queryString.append("                       JOIN AUTH_USER_REGISTRY ");
		queryString.append("                          ON AUTH_USER_REGISTRY.ID_PK = ");
		queryString.append("                                AUTH_USER_ROLE_ASSOC.AUTH_USER_REGISTRY_IDPK ");

		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryString.append("                 WHERE (AUTH_ROLES_REGISTRY.DELETED_TS IS NULL ");
			queryString.append("                        OR AUTH_ROLES_REGISTRY.DELETED_TS > CURRENT_TIMESTAMP) ");
			queryString.append("                       AND (AUTH_USER_REGISTRY.DELETED_TS IS NULL ");
			queryString.append("                            OR AUTH_USER_REGISTRY.DELETED_TS > ");
			queryString.append("                                  CURRENT_TIMESTAMP) ");
			queryString.append("                       AND (AUTH_USER_ROLE_ASSOC.DELETED_TS IS NULL ");
			queryString.append("                            OR AUTH_USER_ROLE_ASSOC.DELETED_TS > ");
			queryString.append("                                  CURRENT_TIMESTAMP) ");
		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {

			queryString.append("                 WHERE (AUTH_ROLES_REGISTRY.DELETED_TS IS NOT NULL ");
			queryString.append("                        AND AUTH_ROLES_REGISTRY.DELETED_TS < CURRENT_TIMESTAMP) ");
			queryString.append("                       AND (AUTH_USER_REGISTRY.DELETED_TS IS NOT NULL ");
			queryString.append("                            AND AUTH_USER_REGISTRY.DELETED_TS < ");
			queryString.append("                                  CURRENT_TIMESTAMP) ");
			queryString.append("                       AND (AUTH_USER_ROLE_ASSOC.DELETED_TS IS NOT NULL ");
			queryString.append("                            AND AUTH_USER_ROLE_ASSOC.DELETED_TS < ");
			queryString.append("                                  CURRENT_TIMESTAMP) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ALL)) {
			queryString.append("                 WHERE 1=1 ");
		}

		queryString.append("                       AND UPPER (AUTH_USER_REGISTRY.USER_NAME) = ");
		queryString.append("                              :username  ");

		queryString.append("                       AND UPPER (AUTH_ROLES_REGISTRY.ROLE_NAME) = ");
		queryString.append("                              :rolename ) ");

		queryString.append("        AND AUTH_USERROLE_ACCESSCONTROL.CONFIG_TXN_REGISTRY_IDPK = ");
		queryString.append("               (SELECT DISTINCT CONFIG_TXN_REGISTRY.ID_PK ");
		queryString.append("                  FROM CONFIG_TXN_REGISTRY ");

		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryString.append("                 WHERE (CONFIG_TXN_REGISTRY.DELETED_TS IS NULL ");
			queryString.append("                        OR CONFIG_TXN_REGISTRY.DELETED_TS > CURRENT_TIMESTAMP) ");
		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryString.append("                 WHERE (CONFIG_TXN_REGISTRY.DELETED_TS IS NOT NULL ");
			queryString.append("                        AND CONFIG_TXN_REGISTRY.DELETED_TS < CURRENT_TIMESTAMP) ");
		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ALL)) {
			queryString.append("                 WHERE 1=1 ");
		}

		queryString.append("                       AND CONFIG_TXN_REGISTRY.TXNSERVICE_NAME = ");
		queryString.append("                              :txnserviceName) ");

		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryString.append("        AND (AUTH_USERROLE_ACCESSCONTROL.DELETED_TS IS NULL ");
			queryString.append("             OR AUTH_USERROLE_ACCESSCONTROL.DELETED_TS > CURRENT_TIMESTAMP) ");
		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryString.append("        AND (AUTH_USERROLE_ACCESSCONTROL.DELETED_TS IS NOT NULL ");
			queryString.append("             AND AUTH_USERROLE_ACCESSCONTROL.DELETED_TS < CURRENT_TIMESTAMP) ");
		}

		paramMap.put("username", userName.toUpperCase());
		paramMap.put("txnserviceName", txnserviceName);
		paramMap.put("rolename", roleName.toUpperCase());

		logger.info("searchAuthAccessControlService search Query is -" + queryString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryString.toString(), AuthUserroleAccesscontrolDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("searchAuthAccessControlService parameter Name:" + mapEntry.getKey() + " Value:"
					+ mapEntry.getValue());
			searchQuery.setParameter(mapEntry.getKey(), mapEntry.getValue());
		}

		return searchQuery;

	}

	private Query buildSearchByUserNameQuery(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		SearchAuthAccessControlDO theSearchAuthAccessControlDO = (SearchAuthAccessControlDO) txnTransferObj
				.getTxnPayload().getSearchAuthAccessControlDO();

		// parameters
		String userName = theSearchAuthAccessControlDO.getUserName();
		// String roleName = theSearchAuthAccessControlDO.getRoleName();
		String txnserviceName = theSearchAuthAccessControlDO.getTxnserviceName();
		String inquiryFilter = theSearchAuthAccessControlDO.getInquiryFilter();

		// String buffer for SQL
		StringBuffer queryString = new StringBuffer();

		// Parameter map
		HashMap<String, String> paramMap = new HashMap<String, String>();

		if (!isNullOrEmpty(userName)) {
			queryString.append(" SELECT DISTINCT AUTH_USERROLE_ACCESSCONTROL.* ");
			queryString.append("   FROM AUTH_USERROLE_ACCESSCONTROL ");
			queryString.append("  WHERE AUTH_USERROLE_ACCESSCONTROL.PROFILE_TYPE = 'USER' ");
			queryString.append("        AND AUTH_USERROLE_ACCESSCONTROL.AUTH_USER_ROLE_REGISTRY_IDPK = ");
			queryString.append("               (SELECT AUTH_USER_REGISTRY.ID_PK ");
			queryString.append("                  FROM AUTH_USER_REGISTRY ");

			if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
				queryString.append("                 WHERE (AUTH_USER_REGISTRY.DELETED_TS IS NULL ");
				queryString.append("                        OR AUTH_USER_REGISTRY.DELETED_TS > CURRENT_TIMESTAMP) ");
			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
				queryString.append("                 WHERE (AUTH_USER_REGISTRY.DELETED_TS IS NOT NULL ");
				queryString.append("                        AND AUTH_USER_REGISTRY.DELETED_TS < CURRENT_TIMESTAMP) ");
			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ALL)) {
				queryString.append("                 WHERE 1=1 ");
			}

			queryString.append("                       AND UPPER (AUTH_USER_REGISTRY.USER_NAME) = ");
			queryString.append("                              :username ) ");
			queryString.append("        AND AUTH_USERROLE_ACCESSCONTROL.CONFIG_TXN_REGISTRY_IDPK = ");
			queryString.append("               (SELECT DISTINCT CONFIG_TXN_REGISTRY.ID_PK ");
			queryString.append("                  FROM CONFIG_TXN_REGISTRY ");

			if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
				queryString.append("                 WHERE (CONFIG_TXN_REGISTRY.DELETED_TS IS NULL ");
				queryString.append("                        OR CONFIG_TXN_REGISTRY.DELETED_TS > CURRENT_TIMESTAMP) ");
			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
				queryString.append("                 WHERE (CONFIG_TXN_REGISTRY.DELETED_TS IS NOT NULL ");
				queryString.append("                        AND CONFIG_TXN_REGISTRY.DELETED_TS < CURRENT_TIMESTAMP) ");

			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ALL)) {
				queryString.append("                 WHERE 1=1 ");
			}

			queryString.append("                       AND CONFIG_TXN_REGISTRY.TXNSERVICE_NAME = ");
			queryString.append("                              :txnserviceName ) ");

			if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
				queryString.append("        AND (AUTH_USERROLE_ACCESSCONTROL.DELETED_TS IS NULL ");
				queryString.append("             OR AUTH_USERROLE_ACCESSCONTROL.DELETED_TS > CURRENT_TIMESTAMP) ");
			} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
				queryString.append("        AND (AUTH_USERROLE_ACCESSCONTROL.DELETED_TS IS NOT NULL ");
				queryString.append("             AND AUTH_USERROLE_ACCESSCONTROL.DELETED_TS < CURRENT_TIMESTAMP) ");
			}

			paramMap.put("username", userName.toUpperCase());
			paramMap.put("txnserviceName", txnserviceName);
		}

		logger.info("searchAuthAccessControlService search Query is -" + queryString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryString.toString(), AuthUserroleAccesscontrolDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("searchAuthAccessControlService parameter Name:" + mapEntry.getKey() + " Value:"
					+ mapEntry.getValue());
			searchQuery.setParameter(mapEntry.getKey(), mapEntry.getValue());
		}

		return searchQuery;
	}

	private Query buildSearchByRoleNameQuery(TxnTransferObj txnTransferObj) {

		// TODO Auto-generated method stub
		SearchAuthAccessControlDO theSearchAuthAccessControlDO = (SearchAuthAccessControlDO) txnTransferObj
				.getTxnPayload().getSearchAuthAccessControlDO();

		// parameters
		String roleName = theSearchAuthAccessControlDO.getRoleName();
		String txnserviceName = theSearchAuthAccessControlDO.getTxnserviceName();
		String inquiryFilter = theSearchAuthAccessControlDO.getInquiryFilter();

		// String buffer for SQL
		StringBuffer queryString = new StringBuffer();

		// Parameter map
		HashMap<String, String> paramMap = new HashMap<String, String>();

		queryString.append(" SELECT DISTINCT AUTH_USERROLE_ACCESSCONTROL.* ");
		queryString.append("   FROM AUTH_USERROLE_ACCESSCONTROL ");
		queryString.append("  WHERE AUTH_USERROLE_ACCESSCONTROL.PROFILE_TYPE = 'ROLE' ");
		queryString.append("        AND AUTH_USER_ROLE_REGISTRY_IDPK = ");
		queryString.append("               (SELECT AUTH_ROLES_REGISTRY.ID_PK ");
		queryString.append("                  FROM AUTH_ROLES_REGISTRY ");

		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryString.append("                 WHERE (AUTH_ROLES_REGISTRY.DELETED_TS IS NULL ");
			queryString.append("                        OR AUTH_ROLES_REGISTRY.DELETED_TS > CURRENT_TIMESTAMP) ");
		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryString.append("                 WHERE (AUTH_ROLES_REGISTRY.DELETED_TS IS NOT NULL ");
			queryString.append("                        AND AUTH_ROLES_REGISTRY.DELETED_TS < CURRENT_TIMESTAMP) ");
		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ALL)) {
			queryString.append("                 WHERE 1=1 ");
		}

		queryString.append("                       AND UPPER(AUTH_ROLES_REGISTRY.ROLE_NAME) = :roleName ) ");
		queryString.append("        AND AUTH_USERROLE_ACCESSCONTROL.CONFIG_TXN_REGISTRY_IDPK = ");
		queryString.append("               (SELECT DISTINCT CONFIG_TXN_REGISTRY.ID_PK ");
		queryString.append("                  FROM CONFIG_TXN_REGISTRY ");

		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryString.append("                 WHERE (CONFIG_TXN_REGISTRY.DELETED_TS IS NULL ");
			queryString.append("                        OR CONFIG_TXN_REGISTRY.DELETED_TS > CURRENT_TIMESTAMP) ");
		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryString.append("                 WHERE (CONFIG_TXN_REGISTRY.DELETED_TS IS NOT NULL ");
			queryString.append("                        AND CONFIG_TXN_REGISTRY.DELETED_TS < CURRENT_TIMESTAMP) ");
		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ALL)) {
			queryString.append("                 WHERE 1=1 ");
		}

		queryString.append("                       AND CONFIG_TXN_REGISTRY.TXNSERVICE_NAME = ");
		queryString.append("                              :txnserviceName ) ");

		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryString.append("        AND (AUTH_USERROLE_ACCESSCONTROL.DELETED_TS IS NULL ");
			queryString.append("             OR AUTH_USERROLE_ACCESSCONTROL.DELETED_TS > CURRENT_TIMESTAMP) ");
		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryString.append("        AND (AUTH_USERROLE_ACCESSCONTROL.DELETED_TS IS NOT NULL ");
			queryString.append("             AND AUTH_USERROLE_ACCESSCONTROL.DELETED_TS < CURRENT_TIMESTAMP) ");
		}

		paramMap.put("roleName", roleName.toUpperCase());
		paramMap.put("txnserviceName", txnserviceName);

		logger.info("searchAuthAccessControlService search Query is -" + queryString.toString());
		// get Native query instance
		// Query searchQuery =
		// entityManager.createNativeQuery(queryJoinString.toString());
		Query searchQuery = entityManager.createNativeQuery(queryString.toString(), AuthUserroleAccesscontrolDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("searchAuthAccessControlService parameter Name:" + mapEntry.getKey() + " Value:"
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

		// If txnserviceName is null
		// then throw error
		if (isNullOrEmpty(theSearchAuthAccessControlDO.getTxnserviceName())) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10082",
					"searchAuthAccessControlDO.txnserviceName is required");

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
