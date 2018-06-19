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
import com.yugandhar.mdm.extern.dobj.MatchCandidateLeRegistryDO;

/**
 * search all records of MATCH_CANDIDATE_LE_REGISTRY table based on
 * legalentityidpk candidateLegalentityidpk or matchPattern or
 * matchProposedActionRefkey or matchActionstatusRefkey or all attributes
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.SearchMatchCandidateLeRegistryBase")
public class SearchMatchCandidateLeRegistryBase {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	MatchCandidateLeRegistryDO respMatchCandidateLeRegistryDO;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@PersistenceContext
	private EntityManager entityManager;

	public SearchMatchCandidateLeRegistryBase() {
		txnTransferObjResponse = new TxnTransferObj();
		respMatchCandidateLeRegistryDO = new MatchCandidateLeRegistryDO();
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
			List<MatchCandidateLeRegistryDO> searchResultMatchCandidateLeRegistryDOList = searchQuery.getResultList();

			respTxnPayload.setMatchCandidateLeRegistryDOList(searchResultMatchCandidateLeRegistryDOList);

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
		MatchCandidateLeRegistryDO reqMatchCandidateLeRegistryDO = txnTransferObj.getTxnPayload()
				.getMatchCandidateLeRegistryDO();

		// parameters
		String legalentityIdpk = reqMatchCandidateLeRegistryDO.getLegalentityIdpk();
		String candidateLegalentityidpk = reqMatchCandidateLeRegistryDO.getCandidateLegalentityidpk();
		String matchPattern = reqMatchCandidateLeRegistryDO.getMatchPattern();
		String matchProposedActionRefkey = reqMatchCandidateLeRegistryDO.getMatchProposedActionRefkey();
		String matchActionstatusRefkey = reqMatchCandidateLeRegistryDO.getMatchActionstatusRefkey();
		String inquiryFilter = reqMatchCandidateLeRegistryDO.getInquiryFilter();

		// String buffer for SQL
		StringBuffer queryJoinString = new StringBuffer();
		StringBuffer queryCriteriaString = new StringBuffer();

		// Parameter map
		HashMap<String, String> paramMap = new HashMap<String, String>();

		queryJoinString
				.append(" SELECT  MATCH_CANDIDATE_LE_REGISTRY.ID_PK, MATCH_CANDIDATE_LE_REGISTRY.VERSION, MATCH_CANDIDATE_LE_REGISTRY.CREATED_TS,"
						+ " MATCH_CANDIDATE_LE_REGISTRY.DELETED_TS, MATCH_CANDIDATE_LE_REGISTRY.UPDATED_TS, MATCH_CANDIDATE_LE_REGISTRY.UPDATED_BY_USER,"
						+ " MATCH_CANDIDATE_LE_REGISTRY.UPDATED_BY_TXN_ID, MATCH_CANDIDATE_LE_REGISTRY.LEGALENTITY_IDPK, MATCH_CANDIDATE_LE_REGISTRY.CANDIDATE_LEGALENTITYIDPK, "
						+ " MATCH_CANDIDATE_LE_REGISTRY.MATCH_PATTERN, MATCH_CANDIDATE_LE_REGISTRY.MATCH_PROPOSED_ACTION_REFKEY, "
						+ " MATCH_CANDIDATE_LE_REGISTRY.MATCH_ACTIONSTATUS_REFKEY, MATCH_CANDIDATE_LE_REGISTRY.MATCH_PERCENTAGE_DESCRIPTION "
						+ " FROM MATCH_CANDIDATE_LE_REGISTRY ");
		if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_ACTIVE)) {
			queryCriteriaString.append(
					" where (MATCH_CANDIDATE_LE_REGISTRY.DELETED_TS is null OR MATCH_CANDIDATE_LE_REGISTRY.DELETED_TS > current_timestamp) ");

		} else if (inquiryFilter.equals(yugandharConstants.FILTER_VALUE_INACTIVE)) {
			queryCriteriaString.append(
					" where (MATCH_CANDIDATE_LE_REGISTRY.DELETED_TS IS NOT NULL AND MATCH_CANDIDATE_LE_REGISTRY.DELETED_TS < current_timestamp) ");
		} else {
			queryCriteriaString.append(" where 1=1 ");
		}

		if (!isNullOrEmpty(legalentityIdpk)) {
			queryCriteriaString.append(" AND MATCH_CANDIDATE_LE_REGISTRY.LEGALENTITY_IDPK like :legalentityIdpk ");
			paramMap.put("legalentityIdpk", legalentityIdpk);
		}

		if (!isNullOrEmpty(candidateLegalentityidpk)) {
			queryCriteriaString.append(
					" AND MATCH_CANDIDATE_LE_REGISTRY.CANDIDATE_LEGALENTITYIDPK like :candidateLegalentityidpk ");
			paramMap.put("candidateLegalentityidpk", candidateLegalentityidpk);
		}

		if (!isNullOrEmpty(matchPattern)) {
			queryCriteriaString.append(" AND MATCH_CANDIDATE_LE_REGISTRY.MATCH_PATTERN like :matchPattern ");
			paramMap.put("matchPattern", matchPattern);
		}

		if (!isNullOrEmpty(matchProposedActionRefkey)) {
			queryCriteriaString.append(
					" AND MATCH_CANDIDATE_LE_REGISTRY.MATCH_PROPOSED_ACTION_REFKEY like :matchProposedActionRefkey ");
			paramMap.put("matchProposedActionRefkey", matchProposedActionRefkey);
		}

		if (!isNullOrEmpty(matchActionstatusRefkey)) {
			queryCriteriaString.append(
					" AND MATCH_CANDIDATE_LE_REGISTRY.MATCH_ACTIONSTATUS_REFKEY like :matchActionstatusRefkey ");
			paramMap.put("matchActionstatusRefkey", matchActionstatusRefkey);
		}

		queryJoinString.append(queryCriteriaString);
		logger.info("SearchMatchCandidateLeRegistryBase search Query is -" + queryJoinString.toString());

		Query searchQuery = entityManager.createNativeQuery(queryJoinString.toString(),
				MatchCandidateLeRegistryDO.class);

		// set the paramaters of the query from hashmap
		for (Iterator<Entry<String, String>> iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> mapEntry = iterator.next();
			logger.debug("SearchMatchCandidateLeRegistryBase parameter Name:" + mapEntry.getKey() + " Value:"
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
	 * MatchCandidateLeRegistryDO.legalentityIdpk or candidateLegalentityidpk or
	 * matchPattern or matchProposedActionRefkey or matchActionstatusRefkey is
	 * not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if MatchCandidateLeRegistryDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeExecution(TxnTransferObj txnTransferObj) {

		if (null == txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"matchCandidateLeRegistryDO is needed in the request");
		}

		MatchCandidateLeRegistryDO theMatchCandidateLeRegistryDO = (MatchCandidateLeRegistryDO) txnTransferObj
				.getTxnPayload().getMatchCandidateLeRegistryDO();

		// Check if atleast one of the legalentityIdpk or
		// InactivationReasonRefkey is provided in the request
		if (isNullOrEmpty(theMatchCandidateLeRegistryDO.getLegalentityIdpk())
				&& isNullOrEmpty(theMatchCandidateLeRegistryDO.getCandidateLegalentityidpk())
				&& isNullOrEmpty(theMatchCandidateLeRegistryDO.getMatchPattern())
				&& isNullOrEmpty(theMatchCandidateLeRegistryDO.getMatchProposedActionRefkey())
				&& isNullOrEmpty(theMatchCandidateLeRegistryDO.getMatchActionstatusRefkey())) {

			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10110",
					"matchCandidateLeRegistryDO.legalentityidpk or candidateLegalentityidpk or matchPattern or matchProposedActionRefkey or matchActionstatusRefkey is needed in the request");
		}

		if (isNullOrEmpty(txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getInquiryFilter())) {
			txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO()
					.setInquiryFilter(yugandharConstants.FILTER_VALUE_ACTIVE);
		} else {

			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getMatchCandidateLeRegistryDO().getInquiryFilter());
		}

	}
}
