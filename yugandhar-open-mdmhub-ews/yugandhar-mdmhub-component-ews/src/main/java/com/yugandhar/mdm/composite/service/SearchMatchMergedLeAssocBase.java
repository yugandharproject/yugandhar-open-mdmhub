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
import com.yugandhar.mdm.extern.dobj.MatchMergedLeAssocDO;

/**
 * search all records of MATCH_MERGED_LE_ASSOC table based on
 * survivorLegalentityIdpk or mergedLegalentityIdpk or mergeReasonRefkey or all
 * attributes
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.SearchMatchMergedLeAssocBase")
public class SearchMatchMergedLeAssocBase {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	MatchMergedLeAssocDO respMatchMergedLeAssocDO;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@PersistenceContext
	private EntityManager entityManager;

	public SearchMatchMergedLeAssocBase() {
		txnTransferObjResponse = new TxnTransferObj();
		respMatchMergedLeAssocDO = new MatchMergedLeAssocDO();
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
			List<MatchMergedLeAssocDO> searchResultMatchMergedLeAssocDOList = searchQuery.getResultList();

			respTxnPayload.setMatchMergedLeAssocDOList(searchResultMatchMergedLeAssocDOList);

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
		MatchMergedLeAssocDO reqMatchMergedLeAssocDO = txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO();

		// parameters
		String survivorLegalentityIdpk = reqMatchMergedLeAssocDO.getSurvivorLegalentityIdpk();
		String mergedLegalentityIdpk = reqMatchMergedLeAssocDO.getMergedLegalentityIdpk();
		String mergeReasonRefkey = reqMatchMergedLeAssocDO.getMergeReasonRefkey();
		String inquiryFilter = reqMatchMergedLeAssocDO.getInquiryFilter();
		
		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		HashMap<String, String> paramMap = new HashMap<String, String>();

		queryJoinString
				.append(" SELECT MATCH_MERGED_LE_ASSOC.ID_PK, MATCH_MERGED_LE_ASSOC.VERSION, MATCH_MERGED_LE_ASSOC.CREATED_TS, MATCH_MERGED_LE_ASSOC.DELETED_TS, "
						+ " MATCH_MERGED_LE_ASSOC.UPDATED_TS, MATCH_MERGED_LE_ASSOC.UPDATED_BY_USER, MATCH_MERGED_LE_ASSOC.UPDATED_BY_TXN_ID, "
						+ " MATCH_MERGED_LE_ASSOC.SURVIVOR_LEGALENTITY_IDPK, MATCH_MERGED_LE_ASSOC.MERGED_LEGALENTITY_IDPK, "
						+ " MATCH_MERGED_LE_ASSOC.MERGE_REASON_REFKEY, MATCH_MERGED_LE_ASSOC.COMMENTS "
						+ " FROM MATCH_MERGED_LE_ASSOC ");
		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString.append(
					" where (MATCH_MERGED_LE_ASSOC.DELETED_TS is null OR MATCH_MERGED_LE_ASSOC.DELETED_TS > current_timestamp) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString.append(
					" where (MATCH_MERGED_LE_ASSOC.DELETED_TS IS NOT NULL AND MATCH_MERGED_LE_ASSOC.DELETED_TS < current_timestamp) ");
		} else {
			queryCriteriaString.append(" where 1=1 ");
		}

		if (!isNullOrEmpty(survivorLegalentityIdpk)) {
			queryCriteriaString.append(" AND MATCH_MERGED_LE_ASSOC.SURVIVOR_LEGALENTITY_IDPK like :survivorLegalentityIdpk ");
			paramMap.put("survivorLegalentityIdpk", survivorLegalentityIdpk);
		}

		if (!isNullOrEmpty(mergedLegalentityIdpk)) {
			queryCriteriaString.append(" AND MATCH_MERGED_LE_ASSOC.MERGED_LEGALENTITY_IDPK like :mergedLegalentityIdpk ");
			paramMap.put("mergedLegalentityIdpk", mergedLegalentityIdpk);
		}
		
		if (!isNullOrEmpty(mergeReasonRefkey)) {
			queryCriteriaString.append(" AND MATCH_MERGED_LE_ASSOC.MERGE_REASON_REFKEY like :mergeReasonRefkey ");
			paramMap.put("mergeReasonRefkey", mergeReasonRefkey);
		}
		

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchMatchMergedLeAssocBase search Query is -" + queryJoinString.toString());

		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(), MatchMergedLeAssocDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("SearchMatchMergedLeAssocBase parameter Name:" + mapEntry.getKey() + " Value:"
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
	 * perform the common validation that
	 * MatchMergedLeAssocDO.survivorLegalentityIdpk or mergedLegalentityIdpk or
	 * mergeReasonRefkey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MatchMergedLeAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeExecution(TxnTransferObj txnTransferObj) {

		if (null == txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"matchMergedLeAssocDO is needed in the request");
		}

		MatchMergedLeAssocDO theMatchMergedLeAssocDO = (MatchMergedLeAssocDO) txnTransferObj.getTxnPayload()
				.getMatchMergedLeAssocDO();

		// Check if atleast one of the legalentityIdpk or
		// InactivationReasonRefkey is provided in the request
		if (isNullOrEmpty(theMatchMergedLeAssocDO.getSurvivorLegalentityIdpk())
				&& isNullOrEmpty(theMatchMergedLeAssocDO.getMergedLegalentityIdpk())
				&& isNullOrEmpty(theMatchMergedLeAssocDO.getMergeReasonRefkey())) {

			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10111",
					"MatchMergedLeAssocDO.survivorLegalentityIdpk or mergedLegalentityIdpk or mergeReasonRefkey is needed in the request");
		}

		if (isNullOrEmpty(txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getInquiryFilter())) {
			txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO()
					.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
		} else {

			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getMatchMergedLeAssocDO().getInquiryFilter());
		}

	}
}
