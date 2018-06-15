package com.yugandhar.common.constant;

public final class yugandharConstants {
	
	public static String RESPONSE_CODE_SUCCESS = "SUCCESS";
	public static String RESPONSE_CODE_FAIL ="FAIL";
	public static String PROPNAME_DATE_FORMAT="com.yugandhar.dateFormat";
	public static String YUGANDHAR_COMMON_LOGGER="YugandharCommonLogger";
	public static String YUGANDHAR_PERFSUMMARY_LOGGER="YugandharPerfSummaryLogger";
	public static String YUGANDHAR_ERROR_PERFSUMMARY_LOGGER="YugandharPerfErrorSummaryLogger";
	public static String YUGANDHAR_CACHE_LOGGER="YugandharCacheLogger";
	public static String YUGANDHAR_MQ_REQ_RESP_LOGGER="YugandharMQRequestResponseLogger";
	public static String YUGANDHAR_MATCH_ENGINE_APPENDER = "YugandharMatchEngineLogger";
	
	// Exception Additional Message categories
	public static String ERROR_CATEGORY_CONFIGURATION="Yugandhar Configuration Excepiton";
	public static String ERROR_HEADER_VALIDATION="Message Header Validation Exception";
	public static String ERROR_FILTER_VALIDATION="Filter Value Validation failed, valid values are ACTIVE, INACTIVE, ALL";
	
	//Filter values
	public static String FILTER_VALUE_ACTIVE="ACTIVE";
	public static String FILTER_VALUE_INACTIVE="INACTIVE";
	public static String FILTER_VALUE_ALL="ALL";
	
	//inquiry level applicatble object names
	public static String INQUIRY_LEVEL_APPLICABLE_OBJ_NAME_LEGALENTITY="LegalentityDO";
	public static String INQUIRY_LEVEL_APPLICABLE_OBJ_NAME_ACCOUNT="AccountDO";
	
	//flag
	public static String FLAG_true = "true";
	public static String FLAG_false = "false";

	public static final String match_le_engine_type_fuzzy = "fuzzy";
	public static final String match_le_engine_type_deterministic = "deterministic";
	
	public static final String com_yugandhar_match_le_candidateSearch_processing_mode_batch = "batch";
	public static final String com_yugandhar_match_le_candidateSearch_processing_mode_realtime = "realtime";
	public static final String com_yugandhar_match_le_candidateSearch_processing_mode_nearrealtime = "near-realtime";
	
}
