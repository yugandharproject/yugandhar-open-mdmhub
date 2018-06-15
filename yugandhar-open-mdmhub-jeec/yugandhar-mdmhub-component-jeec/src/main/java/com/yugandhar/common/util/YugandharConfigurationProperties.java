package com.yugandhar.common.util;


public final class YugandharConfigurationProperties {
	
	public String getValueForProperty(String propertyName){
		
		switch(propertyName){
		case "com.yugandhar.dateFormat": 	return "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		
		}
		
		return null;
	}
	
	//Default inquiry level for retrieve result, to be used if inquiry level is not provided in request
	public static final String com_yugandhar_inqlevel_defaultvalue_retrieve_LegalentityDO = "com_yugandhar_inqlevel_defaultvalue_retrieve_LegalentityDO";
	public static final String com_yugandhar_inqlevel_defaultvalue_retrieve_AccountDO = "com_yugandhar_inqlevel_defaultvalue_retrieve_AccountDO";
	
	//Default inquiry level for search results, to be used if inquiry level is not provided in request
	public static final String com_yugandhar_inqlevel_defaultvalue_search_LegalentityDO = "com_yugandhar_inqlevel_defaultvalue_search_LegalentityDO";
	public static final String com_yugandhar_inqlevel_defaultvalue_search_AccountDO = "com_yugandhar_inqlevel_defaultvalue_search_AccountDO";
	
	//date format
	public static final String com_yugandhar_dateFormat ="com_yugandhar_dateFormat";

	//Pagination
	public static final String com_yugandhar_pagination_referencelov_default_pagesize = "com_yugandhar_pagination_referencelov_default_pagesize";
	public static final String com_yugandhar_pagination_datatables_default_pagesize = "com_yugandhar_pagination_datatables_default_pagesize";
	public static final String com_yugandhar_pagination_default_pagesize_search = "com_yugandhar_pagination_default_pagesize_search";
	
	//phonetic
	public static final String com_yugandhar_phonetic_framework_enabled = "com_yugandhar_phonetic_framework_enabled";
	public static final String com_yugandhar_phonetic_algorithm_class = "com_yugandhar_phonetic_algorithm_class";
	public static final String com_yugandhar_phonetic_algorithm_class_method = "com_yugandhar_phonetic_algorithm_class_method";
	
	//Authorization
	public static final String com_yugandhar_authorization_framework_enabled = "com_yugandhar_authorization_framework_enabled";
	
	//Match Framework
	public static final String com_yugandhar_match_le_framework_enabled = "com_yugandhar_match_le_framework_enabled";
	public static final String com_yugandhar_match_le_engine_type = "com_yugandhar_match_le_engine_type";
	public static final String com_yugandhar_match_le_Deterministic_LePerson_RuleClass = "com_yugandhar_match_le_Deterministic_LePerson_RuleClass";
	public static final String com_yugandhar_match_le_Deterministic_LePerson_RuleClassMethod = "com_yugandhar_match_le_Deterministic_LePerson_RuleClassMethod";
	public static final String com_yugandhar_match_le_Fuzzy_LePerson_RuleClass = "com_yugandhar_match_le_Fuzzy_LePerson_RuleClass";
	public static final String com_yugandhar_match_le_Fuzzy_LePerson_RuleClassMethod = "com_yugandhar_match_le_Fuzzy_LePerson_RuleClassMethod";
	public static final String com_yugandhar_match_le_Deterministic_LeCorporation_RuleClass = "com_yugandhar_match_le_Deterministic_LeCorporation_RuleClass";
	public static final String com_yugandhar_match_le_Deterministic_LeCorporation_RuleClassMethod = "com_yugandhar_match_le_Deterministic_LeCorporation_RuleClassMethod";
	public static final String com_yugandhar_match_le_Fuzzy_LeCorporation_RuleClass = "com_yugandhar_match_le_Fuzzy_LeCorporation_RuleClass";
	public static final String com_yugandhar_match_le_Fuzzy_LeCorporation_RuleClassMethod = "com_yugandhar_match_le_Fuzzy_LeCorporation_RuleClassMethod";
	public static final String com_yugandhar_match_le_candidateSearch_processing_mode = "com_yugandhar_match_le_candidateSearch_processing_mode";
	
	//Person default LE inquiry level for matching
	public static final String com_yugandhar_match_le_Deterministic_LePerson_inquiryLevel_default = "com_yugandhar_match_le_Deterministic_LePerson_inquiryLevel_default";
	public static final String com_yugandhar_match_le_Fuzzy_LePerson_inquiryLevel_default = "com_yugandhar_match_le_Fuzzy_LePerson_inquiryLevel_default";
	public static final String com_yugandhar_match_le_Deterministic_LeCorporation_inquiryLevel_default = "com_yugandhar_match_le_Deterministic_LeCorporation_inquiryLevel_default";
	public static final String com_yugandhar_match_le_Fuzzy_LeCorporation_inquiryLevel_default = "com_yugandhar_match_le_Fuzzy_LeCorporation_inquiryLevel_default";
	
	

	
}
