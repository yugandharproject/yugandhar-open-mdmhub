package com.yugandhar.mdm.composite.service;

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
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.InactiveLeRegistryDO;

/**
 * search all records of INACTIVE_LE_REGISTRY table based on LegalentityIdpk or inactivationReasonRefkey or both
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.SearchInactiveLeRegistryBase")
public class SearchInactiveLeRegistryBase {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	InactiveLeRegistryDO respInactiveLeRegistryDO;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@PersistenceContext
	private EntityManager entityManager;

	public SearchInactiveLeRegistryBase() {
		txnTransferObjResponse = new TxnTransferObj();
		respInactiveLeRegistryDO = new InactiveLeRegistryDO();
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
			List<InactiveLeRegistryDO> searchResultInactiveLeRegistryDOList = searchQuery.getResultList();

			respTxnPayload.setInactiveLeRegistryDOList(searchResultInactiveLeRegistryDOList);

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
		InactiveLeRegistryDO reqInactiveLeRegistryDO = txnTransferObj.getTxnPayload().getInactiveLeRegistryDO();

		// parameters
		String legalentityIdpk = reqInactiveLeRegistryDO.getLegalentityIdpk();
		String inactivationReasonRefkey = reqInactiveLeRegistryDO.getInactivationReasonRefkey();
		String inquiryFilter = reqInactiveLeRegistryDO.getInquiryFilter();

		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		HashMap<String, String> paramMap = new HashMap<String, String>();

		queryJoinString.append(" SELECT INACTIVE_LE_REGISTRY.ID_PK, " + " INACTIVE_LE_REGISTRY.VERSION, "
				+ " INACTIVE_LE_REGISTRY.CREATED_TS, " + " INACTIVE_LE_REGISTRY.DELETED_TS, "
				+ " INACTIVE_LE_REGISTRY.UPDATED_TS, " + " INACTIVE_LE_REGISTRY.UPDATED_BY_USER, "
				+ " INACTIVE_LE_REGISTRY.UPDATED_BY_TXN_ID, " + " INACTIVE_LE_REGISTRY.LEGALENTITY_IDPK, "
				+ " INACTIVE_LE_REGISTRY.INACTIVATED_TS, " + " INACTIVE_LE_REGISTRY.INACTIVATION_REASON_REFKEY, "
				+ " INACTIVE_LE_REGISTRY.COMMENTS " + " FROM INACTIVE_LE_REGISTRY ");
		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString.append(
					" where (INACTIVE_LE_REGISTRY.DELETED_TS is null OR INACTIVE_LE_REGISTRY.DELETED_TS > current_timestamp) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString.append(
					" where (INACTIVE_LE_REGISTRY.DELETED_TS IS NOT NULL AND INACTIVE_LE_REGISTRY.DELETED_TS < current_timestamp) ");
		} else {
			queryCriteriaString.append(" where 1=1 ");
		}

		if (!isNullOrEmpty(legalentityIdpk)) {
			queryCriteriaString.append(" AND INACTIVE_LE_REGISTRY.LEGALENTITY_IDPK like :legalentityIdpk ");
			paramMap.put("legalentityIdpk", legalentityIdpk);
		}

		if (!isNullOrEmpty(inactivationReasonRefkey)) {

			queryCriteriaString.append(" AND INACTIVE_LE_REGISTRY.INACTIVATION_REASON_REFKEY like :inactivationReasonRefkey ");
			paramMap.put("inactivationReasonRefkey", inactivationReasonRefkey);
		}

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchInactiveLeRegistryBase search Query is -" + queryJoinString.toString());

		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), InactiveLeRegistryDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("SearchInactiveLeRegistryBase parameter Name:" + mapEntry.getKey() + " Value:"
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
	 * perform the common validation that InactiveLeRegistryDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if InactiveLeRegistryDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeExecution(TxnTransferObj txnTransferObj) {

		if (null == txnTransferObj.getTxnPayload().getInactiveLeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"inactiveLeRegistryDO is needed in the request");
		}

		InactiveLeRegistryDO theInactiveLeRegistryDO = (InactiveLeRegistryDO) txnTransferObj.getTxnPayload()
				.getInactiveLeRegistryDO();

		// Check if atleast one of the legalentityIdpk or
		// InactivationReasonRefkey is provided in the request
		if (isNullOrEmpty(theInactiveLeRegistryDO.getLegalentityIdpk())
				&& isNullOrEmpty(theInactiveLeRegistryDO.getInactivationReasonRefkey())) {

			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10109",
					"inactiveLeRegistryDO.legalentityIdpk or inactivationReasonRefkey is needed in the request");
		}

		if (isNullOrEmpty(txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getInquiryFilter())) {
			txnTransferObj.getTxnPayload().getInactiveLeRegistryDO()
					.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
		} else {

			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getInactiveLeRegistryDO().getInquiryFilter());
		}

	}
}
