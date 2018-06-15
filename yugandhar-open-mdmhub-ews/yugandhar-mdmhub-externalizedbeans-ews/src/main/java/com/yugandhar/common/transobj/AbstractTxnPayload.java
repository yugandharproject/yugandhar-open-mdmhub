package com.yugandhar.common.transobj;

import java.util.List;

import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.AccountDO;
import com.yugandhar.mdm.extern.dobj.AccountPhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.AddressDO;
import com.yugandhar.mdm.extern.dobj.AuthRolesRegistryDO;
import com.yugandhar.mdm.extern.dobj.AuthUserRegistryDO;
import com.yugandhar.mdm.extern.dobj.AuthUserRoleAssocDO;
import com.yugandhar.mdm.extern.dobj.AuthUserroleAccesscontrolDO;
import com.yugandhar.mdm.extern.dobj.BatchEntityToProcessDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.ConfigErrorcodeRegistryDO;
import com.yugandhar.mdm.extern.dobj.ConfigInquiryLevelsDO;
import com.yugandhar.mdm.extern.dobj.ConfigLanguageCodeDO;
import com.yugandhar.mdm.extern.dobj.ConfigTxnRegistryDO;
import com.yugandhar.mdm.extern.dobj.CorporationnamesDO;
import com.yugandhar.mdm.extern.dobj.EntityGroupAssocDO;
import com.yugandhar.mdm.extern.dobj.EntityGroupDO;
import com.yugandhar.mdm.extern.dobj.InactiveLeRegistryDO;
import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;
import com.yugandhar.mdm.extern.dobj.LeAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.LeCorporationDO;
import com.yugandhar.mdm.extern.dobj.LeIdentifierKycRegistryDO;
import com.yugandhar.mdm.extern.dobj.LePersonDO;
import com.yugandhar.mdm.extern.dobj.LePhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.LePreferencesDO;
import com.yugandhar.mdm.extern.dobj.LePropertyAssocDO;
import com.yugandhar.mdm.extern.dobj.LeSystemKeysRegistryDO;
import com.yugandhar.mdm.extern.dobj.LeToLeRelationshipDO;
import com.yugandhar.mdm.extern.dobj.LeVehicleAssocDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.MatchCandidateLeRegistryDO;
import com.yugandhar.mdm.extern.dobj.MatchMergedLeAssocDO;
import com.yugandhar.mdm.extern.dobj.MiscellaneousInfoDO;
import com.yugandhar.mdm.extern.dobj.PerformLeMatchRequestDO;
import com.yugandhar.mdm.extern.dobj.PersonnamesDO;
import com.yugandhar.mdm.extern.dobj.PhoneStandardizedDO;
import com.yugandhar.mdm.extern.dobj.PropertyDO;
import com.yugandhar.mdm.extern.dobj.RefAccountMdmStatusDO;
import com.yugandhar.mdm.extern.dobj.RefAccountSourceStatusDO;
import com.yugandhar.mdm.extern.dobj.RefAddressSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefAddressTypeDO;
import com.yugandhar.mdm.extern.dobj.RefAgreementTypeDO;
import com.yugandhar.mdm.extern.dobj.RefAssocTypeDO;
import com.yugandhar.mdm.extern.dobj.RefBatchActionStatusDO;
import com.yugandhar.mdm.extern.dobj.RefBatchProposedActionDO;
import com.yugandhar.mdm.extern.dobj.RefBillingModeTypeDO;
import com.yugandhar.mdm.extern.dobj.RefBranchCodeDO;
import com.yugandhar.mdm.extern.dobj.RefClassificationCodeDO;
import com.yugandhar.mdm.extern.dobj.RefCorporationNameTypeDO;
import com.yugandhar.mdm.extern.dobj.RefCorporationTypeDO;
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.extern.dobj.RefCurrencyDO;
import com.yugandhar.mdm.extern.dobj.RefDeactivationReasonDO;
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;
import com.yugandhar.mdm.extern.dobj.RefGenderDO;
import com.yugandhar.mdm.extern.dobj.RefGroupSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefGroupTypeDO;
import com.yugandhar.mdm.extern.dobj.RefHighestEduQualDO;
import com.yugandhar.mdm.extern.dobj.RefIdentificationTypeDO;
import com.yugandhar.mdm.extern.dobj.RefImportanceTypeDO;
import com.yugandhar.mdm.extern.dobj.RefInactivationReasonDO;
import com.yugandhar.mdm.extern.dobj.RefIndustryCodeDO;
import com.yugandhar.mdm.extern.dobj.RefLanguageCodeDO;
import com.yugandhar.mdm.extern.dobj.RefLeRatingDO;
import com.yugandhar.mdm.extern.dobj.RefLeRelationshipTypeDO;
import com.yugandhar.mdm.extern.dobj.RefLeRoletypeDO;
import com.yugandhar.mdm.extern.dobj.RefLobtypeDO;
import com.yugandhar.mdm.extern.dobj.RefMatchActionstatusDO;
import com.yugandhar.mdm.extern.dobj.RefMatchProposedActionDO;
import com.yugandhar.mdm.extern.dobj.RefMatchResultDO;
import com.yugandhar.mdm.extern.dobj.RefMatchScoreDO;
import com.yugandhar.mdm.extern.dobj.RefMatchThresholdDO;
import com.yugandhar.mdm.extern.dobj.RefMergeReasonDO;
import com.yugandhar.mdm.extern.dobj.RefPersonTypeDO;
import com.yugandhar.mdm.extern.dobj.RefPersonnameTypeDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefPhoneTypeDO;
import com.yugandhar.mdm.extern.dobj.RefPreferenceTypeDO;
import com.yugandhar.mdm.extern.dobj.RefPrefixNameDO;
import com.yugandhar.mdm.extern.dobj.RefPropertyLeReltypeDO;
import com.yugandhar.mdm.extern.dobj.RefRelationshipStatusDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.extern.dobj.RefStateProvinceDO;
import com.yugandhar.mdm.extern.dobj.RefStatusInSourceDO;
import com.yugandhar.mdm.extern.dobj.RefStatusTypeDO;
import com.yugandhar.mdm.extern.dobj.RefSuffixNameDO;
import com.yugandhar.mdm.extern.dobj.RefTerminationReasonDO;
import com.yugandhar.mdm.extern.dobj.SearchAccountRequestDO;
import com.yugandhar.mdm.extern.dobj.SearchAuthAccessControlDO;
import com.yugandhar.mdm.extern.dobj.SearchLegalEntityRequestDO;
import com.yugandhar.mdm.extern.dobj.SearchMatchCandidateLERequestDO;
import com.yugandhar.mdm.extern.dobj.VehicleDO;
import com.yugandhar.mdm.misc.dobj.CommonValidationResponse;

public abstract class AbstractTxnPayload implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Pagination
	protected Integer paginationIndexOfCurrentSlice; // Input parameter
	protected Integer paginationPageSize; // Input parameter to define the size
											// of the page (e.g. 25 elements or
											// 100 elements)
	protected Integer paginationInfoElementsOnCurrentSlice; // output
															// information on
															// elements
															// (database rows)
															// on the current
															// slice or page
	protected Long paginationInfoTotalElements; // output information on total
												// number of elements (database
												// rows) for given
												// search/retrieve/find criteria
	protected Integer paginationInfoTotalPages; // output information on total
												// number of pages for given
												// search/retrieve/find criteria

	// Common Objects
	protected CommonValidationResponse errorResponseObj;

	// Data Objects
	protected ConfigTxnRegistryDO configTxnRegistryDO;
	protected ConfigErrorcodeRegistryDO configErrorcodeRegistryDO;
	protected LegalentityDO legalentityDO;
	protected RefEntityObjectTypeDO refEntityObjectTypeDO;
	protected AccountAddressAssocDO accountAddressAssocDO;
	protected AccountDO accountDO;
	protected AccountPhoneAssocDO accountPhoneAssocDO;
	protected AddressDO addressDO;
	protected CorporationnamesDO corporationnamesDO;
	protected EntityGroupAssocDO entityGroupAssocDO;
	protected EntityGroupDO entityGroupDO;
	protected LeAccountAssocDO leAccountAssocDO;
	protected LeAddressAssocDO leAddressAssocDO;
	protected LeCorporationDO leCorporationDO;
	protected LeIdentifierKycRegistryDO leIdentifierKycRegistryDO;
	protected LePersonDO lePersonDO;
	protected LePhoneAssocDO lePhoneAssocDO;
	protected LePreferencesDO lePreferencesDO;
	protected LePropertyAssocDO lePropertyAssocDO;
	protected LeSystemKeysRegistryDO leSystemKeysRegistryDO;
	protected LeToLeRelationshipDO leToLeRelationshipDO;
	protected LeVehicleAssocDO leVehicleAssocDO;
	protected MiscellaneousInfoDO miscellaneousInfoDO;
	protected PersonnamesDO personnamesDO;
	protected PhoneStandardizedDO phoneStandardizedDO;
	protected PropertyDO propertyDO;
	protected RefAccountMdmStatusDO refAccountMdmStatusDO;
	protected RefAccountSourceStatusDO refAccountSourceStatusDO;
	protected RefAddressSubtypeDO refAddressSubtypeDO;
	protected RefAddressTypeDO refAddressTypeDO;
	protected RefAgreementTypeDO refAgreementTypeDO;
	protected RefAssocTypeDO refAssocTypeDO;
	protected RefBillingModeTypeDO refBillingModeTypeDO;
	protected RefBranchCodeDO refBranchCodeDO;
	protected RefClassificationCodeDO refClassificationCodeDO;
	protected RefCorporationNameTypeDO refCorporationNameTypeDO;
	protected RefCorporationTypeDO refCorporationTypeDO;
	protected RefCountryIsoDO refCountryIsoDO;
	protected RefCurrencyDO refCurrencyDO;
	protected RefDeactivationReasonDO refDeactivationReasonDO;
	protected RefGenderDO refGenderDO;
	protected RefGroupSubtypeDO refGroupSubtypeDO;
	protected RefGroupTypeDO refGroupTypeDO;
	protected RefHighestEduQualDO refHighestEduQualDO;
	protected RefIdentificationTypeDO refIdentificationTypeDO;
	protected RefImportanceTypeDO refImportanceTypeDO;
	protected RefIndustryCodeDO refIndustryCodeDO;
	protected RefLanguageCodeDO refLanguageCodeDO;
	protected RefLeRatingDO refLeRatingDO;
	protected RefLeRelationshipTypeDO refLeRelationshipTypeDO;
	protected RefLeRoletypeDO refLeRoletypeDO;
	protected RefLobtypeDO refLobtypeDO;
	protected RefPersonnameTypeDO refPersonnameTypeDO;
	protected RefPersonTypeDO refPersonTypeDO;
	protected RefPhoneSubtypeDO refPhoneSubtypeDO;
	protected RefPhoneTypeDO refPhoneTypeDO;
	protected RefPrefixNameDO refPrefixNameDO;
	protected RefPreferenceTypeDO refPreferenceTypeDO;
	protected RefPropertyLeReltypeDO refPropertyLeReltypeDO;
	protected RefRelationshipStatusDO refRelationshipStatusDO;
	protected RefSourceSystemDO refSourceSystemDO;
	protected RefStateProvinceDO refStateProvinceDO;
	protected RefStatusInSourceDO refStatusInSourceDO;
	protected RefStatusTypeDO refStatusTypeDO;
	protected RefSuffixNameDO refSuffixNameDO;
	protected RefTerminationReasonDO refTerminationReasonDO;
	protected VehicleDO vehicleDO;

	// search request objects
	protected SearchLegalEntityRequestDO searchLegalEntityRequestDO;
	// search request objects
	protected SearchAccountRequestDO searchAccountRequestDO;

	// match request object for LE candidate search
	protected SearchMatchCandidateLERequestDO searchMatchCandidateLERequestDO;

	// Data Objects Lists
	protected List<ConfigTxnRegistryDO> configTxnRegistryDOList;
	protected List<ConfigErrorcodeRegistryDO> configErrorcodeRegistryDOList;
	protected List<LegalentityDO> legalentityDOList;
	protected List<AccountAddressAssocDO> accountAddressAssocDOList;
	protected List<AccountDO> accountDOList;
	protected List<AccountPhoneAssocDO> accountPhoneAssocDOList;
	protected List<AddressDO> addressDOList;
	protected List<CorporationnamesDO> corporationnamesDOList;
	protected List<EntityGroupAssocDO> entityGroupAssocDOList;
	protected List<EntityGroupDO> entityGroupDOList;
	protected List<LeAccountAssocDO> leAccountAssocDOList;
	protected List<LeAddressAssocDO> leAddressAssocDOList;
	protected List<LeCorporationDO> leCorporationDOList;
	protected List<LeIdentifierKycRegistryDO> leIdentifierKycRegistryDOList;
	protected List<LePersonDO> lePersonDOList;
	protected List<LePhoneAssocDO> lePhoneAssocDOList;
	protected List<LePreferencesDO> lePreferencesDOList;
	protected List<LePropertyAssocDO> lePropertyAssocDOList;
	protected List<LeSystemKeysRegistryDO> leSystemKeysRegistryDOList;
	protected List<LeToLeRelationshipDO> leToLeRelationshipDOList;
	protected List<LeVehicleAssocDO> leVehicleAssocDOList;
	protected List<MiscellaneousInfoDO> miscellaneousInfoDOList;
	protected List<PersonnamesDO> personnamesDOList;
	protected List<PhoneStandardizedDO> phoneStandardizedDOList;
	protected List<PropertyDO> propertyDOList;
	protected List<VehicleDO> vehicleDOList;

	// Reference data DO Lists
	protected List<RefAccountMdmStatusDO> refAccountMdmStatusDOList;
	protected List<RefAccountSourceStatusDO> refAccountSourceStatusDOList;
	protected List<RefAddressSubtypeDO> refAddressSubtypeDOList;
	protected List<RefAddressTypeDO> refAddressTypeDOList;
	protected List<RefAgreementTypeDO> refAgreementTypeDOList;
	protected List<RefBillingModeTypeDO> refBillingModeTypeDOList;
	protected List<RefAssocTypeDO> refAssocTypeDOList;
	protected List<RefBranchCodeDO> refBranchCodeDOList;
	protected List<RefClassificationCodeDO> refClassificationCodeDOList;
	protected List<RefCorporationNameTypeDO> refCorporationNameTypeDOList;
	protected List<RefCorporationTypeDO> refCorporationTypeDOList;
	protected List<RefCountryIsoDO> refCountryIsoDOList;
	protected List<RefCurrencyDO> refCurrencyDOList;
	protected List<RefDeactivationReasonDO> refDeactivationReasonDOList;
	protected List<RefGenderDO> refGenderDOList;
	protected List<RefGroupSubtypeDO> refGroupSubtypeDOList;
	protected List<RefGroupTypeDO> refGroupTypeDOList;
	protected List<RefHighestEduQualDO> refHighestEduQualDOList;
	protected List<RefIdentificationTypeDO> refIdentificationTypeDOList;
	protected List<RefImportanceTypeDO> refImportanceTypeDOList;
	protected List<RefIndustryCodeDO> refIndustryCodeDOList;
	protected List<RefLanguageCodeDO> refLanguageCodeDOList;
	protected List<RefLeRatingDO> refLeRatingDOList;
	protected List<RefLeRelationshipTypeDO> refLeRelationshipTypeDOList;
	protected List<RefLeRoletypeDO> refLeRoletypeDOList;
	protected List<RefLobtypeDO> refLobtypeDOList;
	protected List<RefPersonnameTypeDO> refPersonnameTypeDOList;
	protected List<RefPersonTypeDO> refPersonTypeDOList;
	protected List<RefPhoneSubtypeDO> refPhoneSubtypeDOList;
	protected List<RefPhoneTypeDO> refPhoneTypeDOList;
	protected List<RefPrefixNameDO> refPrefixNameDOList;
	protected List<RefPreferenceTypeDO> refPreferenceTypeDOList;
	protected List<RefPropertyLeReltypeDO> refPropertyLeReltypeDOList;
	protected List<RefRelationshipStatusDO> refRelationshipStatusDOList;
	protected List<RefSourceSystemDO> refSourceSystemDOList;
	protected List<RefStateProvinceDO> refStateProvinceDOList;
	protected List<RefStatusInSourceDO> refStatusInSourceDOList;
	protected List<RefStatusTypeDO> refStatusTypeDOList;
	protected List<RefSuffixNameDO> refSuffixNameDOList;
	protected List<RefTerminationReasonDO> refTerminationReasonDOList;
	protected List<RefEntityObjectTypeDO> refEntityObjectTypeDOList;

	// config
	protected ConfigAppPropertiesDO configAppPropertiesDO;
	protected ConfigLanguageCodeDO configLanguageCodeDO;
	protected ConfigInquiryLevelsDO configInquiryLevelsDO;

	protected List<ConfigAppPropertiesDO> configAppPropertiesDOList;
	protected List<ConfigLanguageCodeDO> configLanguageCodeDOList;
	protected List<ConfigInquiryLevelsDO> configInquiryLevelsDOList;

	// Authorization framework
	protected AuthRolesRegistryDO authRolesRegistryDO;
	protected AuthUserRegistryDO authUserRegistryDO;
	protected AuthUserroleAccesscontrolDO authUserroleAccesscontrolDO;
	protected AuthUserRoleAssocDO authUserRoleAssocDO;
	protected SearchAuthAccessControlDO searchAuthAccessControlDO;

	protected List<AuthRolesRegistryDO> authRolesRegistryDOList;
	protected List<AuthUserRegistryDO> authUserRegistryDOList;
	protected List<AuthUserroleAccesscontrolDO> authUserroleAccesscontrolDOList;
	protected List<AuthUserRoleAssocDO> authUserRoleAssocDOList;

	// Match framework
	protected RefInactivationReasonDO refInactivationReasonDO;
	protected RefMatchActionstatusDO refMatchActionstatusDO;
	protected RefMatchProposedActionDO refMatchProposedActionDO;
	protected RefMatchResultDO refMatchResultDO;
	protected RefMatchScoreDO refMatchScoreDO;
	protected RefMatchThresholdDO refMatchThresholdDO;
	protected RefMergeReasonDO refMergeReasonDO;

	protected List<RefInactivationReasonDO> refInactivationReasonDOList;
	protected List<RefMatchActionstatusDO> refMatchActionstatusDOList;
	protected List<RefMatchProposedActionDO> refMatchProposedActionDOList;
	protected List<RefMatchResultDO> refMatchResultDOList;
	protected List<RefMatchScoreDO> refMatchScoreDOList;
	protected List<RefMatchThresholdDO> refMatchThresholdDOList;
	protected List<RefMergeReasonDO> refMergeReasonDOList;

	protected InactiveLeRegistryDO inactiveLeRegistryDO;
	protected MatchCandidateLeRegistryDO matchCandidateLeRegistryDO;
	protected MatchMergedLeAssocDO matchMergedLeAssocDO;
	protected PerformLeMatchRequestDO performLeMatchRequestDO;

	protected List<InactiveLeRegistryDO> inactiveLeRegistryDOList;
	protected List<MatchCandidateLeRegistryDO> matchCandidateLeRegistryDOList;
	protected List<MatchMergedLeAssocDO> matchMergedLeAssocDOList;
	protected List<PerformLeMatchRequestDO> performLeMatchRequestDOList;

	// Batch Processing framwork
	protected BatchEntityToProcessDO batchEntityToProcessDO;
	protected RefBatchActionStatusDO refBatchActionStatusDO;
	protected RefBatchProposedActionDO refBatchProposedActionDO;

	protected BatchEntityToProcessDO batchEntityToProcessDOList;
	protected List<RefBatchActionStatusDO> refBatchActionStatusDOList;
	protected List<RefBatchProposedActionDO> refBatchProposedActionDOList;

	/**
	 * @return the paginationIndexOfCurrentSlice
	 */
	public Integer getPaginationIndexOfCurrentSlice() {
		return paginationIndexOfCurrentSlice;
	}

	/**
	 * @param paginationIndexOfCurrentSlice
	 *            the paginationIndexOfCurrentSlice to set
	 */
	public void setPaginationIndexOfCurrentSlice(Integer paginationIndexOfCurrentSlice) {
		this.paginationIndexOfCurrentSlice = paginationIndexOfCurrentSlice;
	}

	/**
	 * @return the paginationPageSize
	 */
	public Integer getPaginationPageSize() {
		return paginationPageSize;
	}

	/**
	 * @param paginationPageSize
	 *            the paginationPageSize to set
	 */
	public void setPaginationPageSize(Integer paginationPageSize) {
		this.paginationPageSize = paginationPageSize;
	}

	/**
	 * @return the paginationInfoElementsOnCurrentSlice
	 */
	public Integer getPaginationInfoElementsOnCurrentSlice() {
		return paginationInfoElementsOnCurrentSlice;
	}

	/**
	 * @param paginationInfoElementsOnCurrentSlice
	 *            the paginationInfoElementsOnCurrentSlice to set
	 */
	public void setPaginationInfoElementsOnCurrentSlice(Integer paginationInfoElementsOnCurrentSlice) {
		this.paginationInfoElementsOnCurrentSlice = paginationInfoElementsOnCurrentSlice;
	}

	/**
	 * @return the paginationInfoTotalElements
	 */
	public Long getPaginationInfoTotalElements() {
		return paginationInfoTotalElements;
	}

	/**
	 * @param paginationInfoTotalElements
	 *            the paginationInfoTotalElements to set
	 */
	public void setPaginationInfoTotalElements(Long paginationInfoTotalElements) {
		this.paginationInfoTotalElements = paginationInfoTotalElements;
	}

	/**
	 * @return the paginationInfoTotalPages
	 */
	public Integer getPaginationInfoTotalPages() {
		return paginationInfoTotalPages;
	}

	/**
	 * @param paginationInfoTotalPages
	 *            the paginationInfoTotalPages to set
	 */
	public void setPaginationInfoTotalPages(Integer paginationInfoTotalPages) {
		this.paginationInfoTotalPages = paginationInfoTotalPages;
	}

	/**
	 * @return the errorResponseObj
	 */
	public CommonValidationResponse getErrorResponseObj() {
		return errorResponseObj;
	}

	/**
	 * @param errorResponseObj
	 *            the errorResponseObj to set
	 */
	public void setErrorResponseObj(CommonValidationResponse errorResponseObj) {
		this.errorResponseObj = errorResponseObj;
	}

	/**
	 * @return the configTxnRegistryDO
	 */
	public ConfigTxnRegistryDO getConfigTxnRegistryDO() {
		return configTxnRegistryDO;
	}

	/**
	 * @param configTxnRegistryDO
	 *            the configTxnRegistryDO to set
	 */
	public void setConfigTxnRegistryDO(ConfigTxnRegistryDO configTxnRegistryDO) {
		this.configTxnRegistryDO = configTxnRegistryDO;
	}

	/**
	 * @return the configErrorcodeRegistryDO
	 */
	public ConfigErrorcodeRegistryDO getConfigErrorcodeRegistryDO() {
		return configErrorcodeRegistryDO;
	}

	/**
	 * @param configErrorcodeRegistryDO
	 *            the configErrorcodeRegistryDO to set
	 */
	public void setConfigErrorcodeRegistryDO(ConfigErrorcodeRegistryDO configErrorcodeRegistryDO) {
		this.configErrorcodeRegistryDO = configErrorcodeRegistryDO;
	}

	/**
	 * @return the configErrorcodeRegistryDOList
	 */
	public List<ConfigErrorcodeRegistryDO> getConfigErrorcodeRegistryDOList() {
		return configErrorcodeRegistryDOList;
	}

	/**
	 * @param configErrorcodeRegistryDOList
	 *            the configErrorcodeRegistryDOList to set
	 */
	public void setConfigErrorcodeRegistryDOList(List<ConfigErrorcodeRegistryDO> configErrorcodeRegistryDOList) {
		this.configErrorcodeRegistryDOList = configErrorcodeRegistryDOList;
	}

	/**
	 * @return the legalentityDO
	 */
	public LegalentityDO getLegalentityDO() {
		return legalentityDO;
	}

	/**
	 * @param legalentityDO
	 *            the legalentityDO to set
	 */
	public void setLegalentityDO(LegalentityDO legalentityDO) {
		this.legalentityDO = legalentityDO;
	}

	/**
	 * @return the refEntityObjectTypeDO
	 */
	public RefEntityObjectTypeDO getRefEntityObjectTypeDO() {
		return refEntityObjectTypeDO;
	}

	/**
	 * @param refEntityObjectTypeDO
	 *            the refEntityObjectTypeDO to set
	 */
	public void setRefEntityObjectTypeDO(RefEntityObjectTypeDO refEntityObjectTypeDO) {
		this.refEntityObjectTypeDO = refEntityObjectTypeDO;
	}

	/**
	 * @return the refEntityObjectTypeDOList
	 */
	public List<RefEntityObjectTypeDO> getRefEntityObjectTypeDOList() {
		return refEntityObjectTypeDOList;
	}

	/**
	 * @param refEntityObjectTypeDOList
	 *            the refEntityObjectTypeDOList to set
	 */
	public void setRefEntityObjectTypeDOList(List<RefEntityObjectTypeDO> refEntityObjectTypeDOList) {
		this.refEntityObjectTypeDOList = refEntityObjectTypeDOList;
	}

	/**
	 * @return the accountAddressAssocDO
	 */
	public AccountAddressAssocDO getAccountAddressAssocDO() {
		return accountAddressAssocDO;
	}

	/**
	 * @param accountAddressAssocDO
	 *            the accountAddressAssocDO to set
	 */
	public void setAccountAddressAssocDO(AccountAddressAssocDO accountAddressAssocDO) {
		this.accountAddressAssocDO = accountAddressAssocDO;
	}

	/**
	 * @return the accountDO
	 */
	public AccountDO getAccountDO() {
		return accountDO;
	}

	/**
	 * @param accountDO
	 *            the accountDO to set
	 */
	public void setAccountDO(AccountDO accountDO) {
		this.accountDO = accountDO;
	}

	/**
	 * @return the accountPhoneAssocDO
	 */
	public AccountPhoneAssocDO getAccountPhoneAssocDO() {
		return accountPhoneAssocDO;
	}

	/**
	 * @param accountPhoneAssocDO
	 *            the accountPhoneAssocDO to set
	 */
	public void setAccountPhoneAssocDO(AccountPhoneAssocDO accountPhoneAssocDO) {
		this.accountPhoneAssocDO = accountPhoneAssocDO;
	}

	/**
	 * @return the addressDO
	 */
	public AddressDO getAddressDO() {
		return addressDO;
	}

	/**
	 * @param addressDO
	 *            the addressDO to set
	 */
	public void setAddressDO(AddressDO addressDO) {
		this.addressDO = addressDO;
	}

	/**
	 * @return the corporationnamesDO
	 */
	public CorporationnamesDO getCorporationnamesDO() {
		return corporationnamesDO;
	}

	/**
	 * @param corporationnamesDO
	 *            the corporationnamesDO to set
	 */
	public void setCorporationnamesDO(CorporationnamesDO corporationnamesDO) {
		this.corporationnamesDO = corporationnamesDO;
	}

	/**
	 * @return the etityGroupAssocDO
	 */
	public EntityGroupAssocDO getEntityGroupAssocDO() {
		return entityGroupAssocDO;
	}

	/**
	 * @param etityGroupAssocDO
	 *            the etityGroupAssocDO to set
	 */
	public void setEntityGroupAssocDO(EntityGroupAssocDO etityGroupAssocDO) {
		this.entityGroupAssocDO = etityGroupAssocDO;
	}

	/**
	 * @return the entityGroupDO
	 */
	public EntityGroupDO getEntityGroupDO() {
		return entityGroupDO;
	}

	/**
	 * @param entityGroupDO
	 *            the entityGroupDO to set
	 */
	public void setEntityGroupDO(EntityGroupDO entityGroupDO) {
		this.entityGroupDO = entityGroupDO;
	}

	/**
	 * @return the leAccountAssocDO
	 */
	public LeAccountAssocDO getLeAccountAssocDO() {
		return leAccountAssocDO;
	}

	/**
	 * @param leAccountAssocDO
	 *            the leAccountAssocDO to set
	 */
	public void setLeAccountAssocDO(LeAccountAssocDO leAccountAssocDO) {
		this.leAccountAssocDO = leAccountAssocDO;
	}

	/**
	 * @return the leAddressAssocDO
	 */
	public LeAddressAssocDO getLeAddressAssocDO() {
		return leAddressAssocDO;
	}

	/**
	 * @param leAddressAssocDO
	 *            the leAddressAssocDO to set
	 */
	public void setLeAddressAssocDO(LeAddressAssocDO leAddressAssocDO) {
		this.leAddressAssocDO = leAddressAssocDO;
	}

	/**
	 * @return the leCorporationDO
	 */
	public LeCorporationDO getLeCorporationDO() {
		return leCorporationDO;
	}

	/**
	 * @param leCorporationDO
	 *            the leCorporationDO to set
	 */
	public void setLeCorporationDO(LeCorporationDO leCorporationDO) {
		this.leCorporationDO = leCorporationDO;
	}

	/**
	 * @return the leIdentifierKycRegistryDO
	 */
	public LeIdentifierKycRegistryDO getLeIdentifierKycRegistryDO() {
		return leIdentifierKycRegistryDO;
	}

	/**
	 * @param leIdentifierKycRegistryDO
	 *            the leIdentifierKycRegistryDO to set
	 */
	public void setLeIdentifierKycRegistryDO(LeIdentifierKycRegistryDO leIdentifierKycRegistryDO) {
		this.leIdentifierKycRegistryDO = leIdentifierKycRegistryDO;
	}

	/**
	 * @return the lePersonDO
	 */
	public LePersonDO getLePersonDO() {
		return lePersonDO;
	}

	/**
	 * @param lePersonDO
	 *            the lePersonDO to set
	 */
	public void setLePersonDO(LePersonDO lePersonDO) {
		this.lePersonDO = lePersonDO;
	}

	/**
	 * @return the lePhoneAssocDO
	 */
	public LePhoneAssocDO getLePhoneAssocDO() {
		return lePhoneAssocDO;
	}

	/**
	 * @param lePhoneAssocDO
	 *            the lePhoneAssocDO to set
	 */
	public void setLePhoneAssocDO(LePhoneAssocDO lePhoneAssocDO) {
		this.lePhoneAssocDO = lePhoneAssocDO;
	}

	/**
	 * @return the lePreferencesDO
	 */
	public LePreferencesDO getLePreferencesDO() {
		return lePreferencesDO;
	}

	/**
	 * @param lePreferencesDO
	 *            the lePreferencesDO to set
	 */
	public void setLePreferencesDO(LePreferencesDO lePreferencesDO) {
		this.lePreferencesDO = lePreferencesDO;
	}

	/**
	 * @return the lePropertyAssocDO
	 */
	public LePropertyAssocDO getLePropertyAssocDO() {
		return lePropertyAssocDO;
	}

	/**
	 * @param lePropertyAssocDO
	 *            the lePropertyAssocDO to set
	 */
	public void setLePropertyAssocDO(LePropertyAssocDO lePropertyAssocDO) {
		this.lePropertyAssocDO = lePropertyAssocDO;
	}

	/**
	 * @return the leSystemKeysRegistryDO
	 */
	public LeSystemKeysRegistryDO getLeSystemKeysRegistryDO() {
		return leSystemKeysRegistryDO;
	}

	/**
	 * @param leSystemKeysRegistryDO
	 *            the leSystemKeysRegistryDO to set
	 */
	public void setLeSystemKeysRegistryDO(LeSystemKeysRegistryDO leSystemKeysRegistryDO) {
		this.leSystemKeysRegistryDO = leSystemKeysRegistryDO;
	}

	/**
	 * @return the leToLeRelationshipDO
	 */
	public LeToLeRelationshipDO getLeToLeRelationshipDO() {
		return leToLeRelationshipDO;
	}

	/**
	 * @param leToLeRelationshipDO
	 *            the leToLeRelationshipDO to set
	 */
	public void setLeToLeRelationshipDO(LeToLeRelationshipDO leToLeRelationshipDO) {
		this.leToLeRelationshipDO = leToLeRelationshipDO;
	}

	/**
	 * @return the leVehicleAssocDO
	 */
	public LeVehicleAssocDO getLeVehicleAssocDO() {
		return leVehicleAssocDO;
	}

	/**
	 * @param leVehicleAssocDO
	 *            the leVehicleAssocDO to set
	 */
	public void setLeVehicleAssocDO(LeVehicleAssocDO leVehicleAssocDO) {
		this.leVehicleAssocDO = leVehicleAssocDO;
	}

	/**
	 * @return the miscellaneousInfoDO
	 */
	public MiscellaneousInfoDO getMiscellaneousInfoDO() {
		return miscellaneousInfoDO;
	}

	/**
	 * @param miscellaneousInfoDO
	 *            the miscellaneousInfoDO to set
	 */
	public void setMiscellaneousInfoDO(MiscellaneousInfoDO miscellaneousInfoDO) {
		this.miscellaneousInfoDO = miscellaneousInfoDO;
	}

	/**
	 * @return the personnamesDO
	 */
	public PersonnamesDO getPersonnamesDO() {
		return personnamesDO;
	}

	/**
	 * @param personnamesDO
	 *            the personnamesDO to set
	 */
	public void setPersonnamesDO(PersonnamesDO personnamesDO) {
		this.personnamesDO = personnamesDO;
	}

	/**
	 * @return the phoneStandardizedDO
	 */
	public PhoneStandardizedDO getPhoneStandardizedDO() {
		return phoneStandardizedDO;
	}

	/**
	 * @param phoneStandardizedDO
	 *            the phoneStandardizedDO to set
	 */
	public void setPhoneStandardizedDO(PhoneStandardizedDO phoneStandardizedDO) {
		this.phoneStandardizedDO = phoneStandardizedDO;
	}

	/**
	 * @return the propertyDO
	 */
	public PropertyDO getPropertyDO() {
		return propertyDO;
	}

	/**
	 * @param propertyDO
	 *            the propertyDO to set
	 */
	public void setPropertyDO(PropertyDO propertyDO) {
		this.propertyDO = propertyDO;
	}

	/**
	 * @return the refAccountMdmStatusDO
	 */
	public RefAccountMdmStatusDO getRefAccountMdmStatusDO() {
		return refAccountMdmStatusDO;
	}

	/**
	 * @param refAccountMdmStatusDO
	 *            the refAccountMdmStatusDO to set
	 */
	public void setRefAccountMdmStatusDO(RefAccountMdmStatusDO refAccountMdmStatusDO) {
		this.refAccountMdmStatusDO = refAccountMdmStatusDO;
	}

	/**
	 * @return the refAccountSourceStatusDO
	 */
	public RefAccountSourceStatusDO getRefAccountSourceStatusDO() {
		return refAccountSourceStatusDO;
	}

	/**
	 * @param refAccountSourceStatusDO
	 *            the refAccountSourceStatusDO to set
	 */
	public void setRefAccountSourceStatusDO(RefAccountSourceStatusDO refAccountSourceStatusDO) {
		this.refAccountSourceStatusDO = refAccountSourceStatusDO;
	}

	/**
	 * @return the refAddressSubtypeDO
	 */
	public RefAddressSubtypeDO getRefAddressSubtypeDO() {
		return refAddressSubtypeDO;
	}

	/**
	 * @param refAddressSubtypeDO
	 *            the refAddressSubtypeDO to set
	 */
	public void setRefAddressSubtypeDO(RefAddressSubtypeDO refAddressSubtypeDO) {
		this.refAddressSubtypeDO = refAddressSubtypeDO;
	}

	/**
	 * @return the refAddressTypeDO
	 */
	public RefAddressTypeDO getRefAddressTypeDO() {
		return refAddressTypeDO;
	}

	/**
	 * @param refAddressTypeDO
	 *            the refAddressTypeDO to set
	 */
	public void setRefAddressTypeDO(RefAddressTypeDO refAddressTypeDO) {
		this.refAddressTypeDO = refAddressTypeDO;
	}

	/**
	 * @return the refAgreementTypeDO
	 */
	public RefAgreementTypeDO getRefAgreementTypeDO() {
		return refAgreementTypeDO;
	}

	/**
	 * @param refAgreementTypeDO
	 *            the refAgreementTypeDO to set
	 */
	public void setRefAgreementTypeDO(RefAgreementTypeDO refAgreementTypeDO) {
		this.refAgreementTypeDO = refAgreementTypeDO;
	}

	/**
	 * @return the refAssocTypeDO
	 */
	public RefAssocTypeDO getRefAssocTypeDO() {
		return refAssocTypeDO;
	}

	/**
	 * @param refAssocTypeDO
	 *            the refAssocTypeDO to set
	 */
	public void setRefAssocTypeDO(RefAssocTypeDO refAssocTypeDO) {
		this.refAssocTypeDO = refAssocTypeDO;
	}

	/**
	 * @return the refBillingModeTypeDO
	 */
	public RefBillingModeTypeDO getRefBillingModeTypeDO() {
		return refBillingModeTypeDO;
	}

	/**
	 * @param refBillingModeTypeDO
	 *            the refBillingModeTypeDO to set
	 */
	public void setRefBillingModeTypeDO(RefBillingModeTypeDO refBillingModeTypeDO) {
		this.refBillingModeTypeDO = refBillingModeTypeDO;
	}

	/**
	 * @return the refBranchCodeDO
	 */
	public RefBranchCodeDO getRefBranchCodeDO() {
		return refBranchCodeDO;
	}

	/**
	 * @param refBranchCodeDO
	 *            the refBranchCodeDO to set
	 */
	public void setRefBranchCodeDO(RefBranchCodeDO refBranchCodeDO) {
		this.refBranchCodeDO = refBranchCodeDO;
	}

	/**
	 * @return the refClassificationCodeDO
	 */
	public RefClassificationCodeDO getRefClassificationCodeDO() {
		return refClassificationCodeDO;
	}

	/**
	 * @param refClassificationCodeDO
	 *            the refClassificationCodeDO to set
	 */
	public void setRefClassificationCodeDO(RefClassificationCodeDO refClassificationCodeDO) {
		this.refClassificationCodeDO = refClassificationCodeDO;
	}

	/**
	 * @return the refCorporationNameTypeDO
	 */
	public RefCorporationNameTypeDO getRefCorporationNameTypeDO() {
		return refCorporationNameTypeDO;
	}

	/**
	 * @param refCorporationNameTypeDO
	 *            the refCorporationNameTypeDO to set
	 */
	public void setRefCorporationNameTypeDO(RefCorporationNameTypeDO refCorporationNameTypeDO) {
		this.refCorporationNameTypeDO = refCorporationNameTypeDO;
	}

	/**
	 * @return the refCorporationTypeDO
	 */
	public RefCorporationTypeDO getRefCorporationTypeDO() {
		return refCorporationTypeDO;
	}

	/**
	 * @param refCorporationTypeDO
	 *            the refCorporationTypeDO to set
	 */
	public void setRefCorporationTypeDO(RefCorporationTypeDO refCorporationTypeDO) {
		this.refCorporationTypeDO = refCorporationTypeDO;
	}

	/**
	 * @return the refCountryIsoDO
	 */
	public RefCountryIsoDO getRefCountryIsoDO() {
		return refCountryIsoDO;
	}

	/**
	 * @param refCountryIsoDO
	 *            the refCountryIsoDO to set
	 */
	public void setRefCountryIsoDO(RefCountryIsoDO refCountryIsoDO) {
		this.refCountryIsoDO = refCountryIsoDO;
	}

	/**
	 * @return the refCurrencyDO
	 */
	public RefCurrencyDO getRefCurrencyDO() {
		return refCurrencyDO;
	}

	/**
	 * @param refCurrencyDO
	 *            the refCurrencyDO to set
	 */
	public void setRefCurrencyDO(RefCurrencyDO refCurrencyDO) {
		this.refCurrencyDO = refCurrencyDO;
	}

	/**
	 * @return the refDeactivationReasonDO
	 */
	public RefDeactivationReasonDO getRefDeactivationReasonDO() {
		return refDeactivationReasonDO;
	}

	/**
	 * @param refDeactivationReasonDO
	 *            the refDeactivationReasonDO to set
	 */
	public void setRefDeactivationReasonDO(RefDeactivationReasonDO refDeactivationReasonDO) {
		this.refDeactivationReasonDO = refDeactivationReasonDO;
	}

	/**
	 * @return the refGenderDO
	 */
	public RefGenderDO getRefGenderDO() {
		return refGenderDO;
	}

	/**
	 * @param refGenderDO
	 *            the refGenderDO to set
	 */
	public void setRefGenderDO(RefGenderDO refGenderDO) {
		this.refGenderDO = refGenderDO;
	}

	/**
	 * @return the refGroupSubtypeDO
	 */
	public RefGroupSubtypeDO getRefGroupSubtypeDO() {
		return refGroupSubtypeDO;
	}

	/**
	 * @param refGroupSubtypeDO
	 *            the refGroupSubtypeDO to set
	 */
	public void setRefGroupSubtypeDO(RefGroupSubtypeDO refGroupSubtypeDO) {
		this.refGroupSubtypeDO = refGroupSubtypeDO;
	}

	/**
	 * @return the refGroupTypeDO
	 */
	public RefGroupTypeDO getRefGroupTypeDO() {
		return refGroupTypeDO;
	}

	/**
	 * @param refGroupTypeDO
	 *            the refGroupTypeDO to set
	 */
	public void setRefGroupTypeDO(RefGroupTypeDO refGroupTypeDO) {
		this.refGroupTypeDO = refGroupTypeDO;
	}

	/**
	 * @return the refHighestEduQualDO
	 */
	public RefHighestEduQualDO getRefHighestEduQualDO() {
		return refHighestEduQualDO;
	}

	/**
	 * @param refHighestEduQualDO
	 *            the refHighestEduQualDO to set
	 */
	public void setRefHighestEduQualDO(RefHighestEduQualDO refHighestEduQualDO) {
		this.refHighestEduQualDO = refHighestEduQualDO;
	}

	/**
	 * @return the refIdentificationTypeDO
	 */
	public RefIdentificationTypeDO getRefIdentificationTypeDO() {
		return refIdentificationTypeDO;
	}

	/**
	 * @param refIdentificationTypeDO
	 *            the refIdentificationTypeDO to set
	 */
	public void setRefIdentificationTypeDO(RefIdentificationTypeDO refIdentificationTypeDO) {
		this.refIdentificationTypeDO = refIdentificationTypeDO;
	}

	/**
	 * @return the refImportanceTypeDO
	 */
	public RefImportanceTypeDO getRefImportanceTypeDO() {
		return refImportanceTypeDO;
	}

	/**
	 * @param refImportanceTypeDO
	 *            the refImportanceTypeDO to set
	 */
	public void setRefImportanceTypeDO(RefImportanceTypeDO refImportanceTypeDO) {
		this.refImportanceTypeDO = refImportanceTypeDO;
	}

	/**
	 * @return the refIndustryCodeDO
	 */
	public RefIndustryCodeDO getRefIndustryCodeDO() {
		return refIndustryCodeDO;
	}

	/**
	 * @param refIndustryCodeDO
	 *            the refIndustryCodeDO to set
	 */
	public void setRefIndustryCodeDO(RefIndustryCodeDO refIndustryCodeDO) {
		this.refIndustryCodeDO = refIndustryCodeDO;
	}

	/**
	 * @return the refLanguageCodeDO
	 */
	public RefLanguageCodeDO getRefLanguageCodeDO() {
		return refLanguageCodeDO;
	}

	/**
	 * @param refLanguageCodeDO
	 *            the refLanguageCodeDO to set
	 */
	public void setRefLanguageCodeDO(RefLanguageCodeDO refLanguageCodeDO) {
		this.refLanguageCodeDO = refLanguageCodeDO;
	}

	/**
	 * @return the refLeRatingDO
	 */
	public RefLeRatingDO getRefLeRatingDO() {
		return refLeRatingDO;
	}

	/**
	 * @param refLeRatingDO
	 *            the refLeRatingDO to set
	 */
	public void setRefLeRatingDO(RefLeRatingDO refLeRatingDO) {
		this.refLeRatingDO = refLeRatingDO;
	}

	/**
	 * @return the refLeRelationshipTypeDO
	 */
	public RefLeRelationshipTypeDO getRefLeRelationshipTypeDO() {
		return refLeRelationshipTypeDO;
	}

	/**
	 * @param refLeRelationshipTypeDO
	 *            the refLeRelationshipTypeDO to set
	 */
	public void setRefLeRelationshipTypeDO(RefLeRelationshipTypeDO refLeRelationshipTypeDO) {
		this.refLeRelationshipTypeDO = refLeRelationshipTypeDO;
	}

	/**
	 * @return the refLeRoletypeDO
	 */
	public RefLeRoletypeDO getRefLeRoletypeDO() {
		return refLeRoletypeDO;
	}

	/**
	 * @param refLeRoletypeDO
	 *            the refLeRoletypeDO to set
	 */
	public void setRefLeRoletypeDO(RefLeRoletypeDO refLeRoletypeDO) {
		this.refLeRoletypeDO = refLeRoletypeDO;
	}

	/**
	 * @return the refLobtypeDO
	 */
	public RefLobtypeDO getRefLobtypeDO() {
		return refLobtypeDO;
	}

	/**
	 * @param refLobtypeDO
	 *            the refLobtypeDO to set
	 */
	public void setRefLobtypeDO(RefLobtypeDO refLobtypeDO) {
		this.refLobtypeDO = refLobtypeDO;
	}

	/**
	 * @return the refPersonnameTypeDO
	 */
	public RefPersonnameTypeDO getRefPersonnameTypeDO() {
		return refPersonnameTypeDO;
	}

	/**
	 * @param refPersonnameTypeDO
	 *            the refPersonnameTypeDO to set
	 */
	public void setRefPersonnameTypeDO(RefPersonnameTypeDO refPersonnameTypeDO) {
		this.refPersonnameTypeDO = refPersonnameTypeDO;
	}

	/**
	 * @return the refPersonTypeDO
	 */
	public RefPersonTypeDO getRefPersonTypeDO() {
		return refPersonTypeDO;
	}

	/**
	 * @param refPersonTypeDO
	 *            the refPersonTypeDO to set
	 */
	public void setRefPersonTypeDO(RefPersonTypeDO refPersonTypeDO) {
		this.refPersonTypeDO = refPersonTypeDO;
	}

	/**
	 * @return the refPhoneSubtypeDO
	 */
	public RefPhoneSubtypeDO getRefPhoneSubtypeDO() {
		return refPhoneSubtypeDO;
	}

	/**
	 * @param refPhoneSubtypeDO
	 *            the refPhoneSubtypeDO to set
	 */
	public void setRefPhoneSubtypeDO(RefPhoneSubtypeDO refPhoneSubtypeDO) {
		this.refPhoneSubtypeDO = refPhoneSubtypeDO;
	}

	/**
	 * @return the refPhoneTypeDO
	 */
	public RefPhoneTypeDO getRefPhoneTypeDO() {
		return refPhoneTypeDO;
	}

	/**
	 * @param refPhoneTypeDO
	 *            the refPhoneTypeDO to set
	 */
	public void setRefPhoneTypeDO(RefPhoneTypeDO refPhoneTypeDO) {
		this.refPhoneTypeDO = refPhoneTypeDO;
	}

	/**
	 * @return the refPrefixNameDO
	 */
	public RefPrefixNameDO getRefPrefixNameDO() {
		return refPrefixNameDO;
	}

	/**
	 * @param refPrefixNameDO
	 *            the refPrefixNameDO to set
	 */
	public void setRefPrefixNameDO(RefPrefixNameDO refPrefixNameDO) {
		this.refPrefixNameDO = refPrefixNameDO;
	}

	/**
	 * @return the refPrivacyPrefTypeDO
	 */
	public RefPreferenceTypeDO getRefPreferenceTypeDO() {
		return refPreferenceTypeDO;
	}

	/**
	 * @param refPrivacyPrefTypeDO
	 *            the refPrivacyPrefTypeDO to set
	 */
	public void setRefPreferenceTypeDO(RefPreferenceTypeDO refPreferenceTypeDO) {
		this.refPreferenceTypeDO = refPreferenceTypeDO;
	}

	/**
	 * @return the refPropertyLeReltypeDO
	 */
	public RefPropertyLeReltypeDO getRefPropertyLeReltypeDO() {
		return refPropertyLeReltypeDO;
	}

	/**
	 * @param refPropertyLeReltypeDO
	 *            the refPropertyLeReltypeDO to set
	 */
	public void setRefPropertyLeReltypeDO(RefPropertyLeReltypeDO refPropertyLeReltypeDO) {
		this.refPropertyLeReltypeDO = refPropertyLeReltypeDO;
	}

	/**
	 * @return the refRelationshipStatusDO
	 */
	public RefRelationshipStatusDO getRefRelationshipStatusDO() {
		return refRelationshipStatusDO;
	}

	/**
	 * @param refRelationshipStatusDO
	 *            the refRelationshipStatusDO to set
	 */
	public void setRefRelationshipStatusDO(RefRelationshipStatusDO refRelationshipStatusDO) {
		this.refRelationshipStatusDO = refRelationshipStatusDO;
	}

	/**
	 * @return the refSourceSystemDO
	 */
	public RefSourceSystemDO getRefSourceSystemDO() {
		return refSourceSystemDO;
	}

	/**
	 * @param refSourceSystemDO
	 *            the refSourceSystemDO to set
	 */
	public void setRefSourceSystemDO(RefSourceSystemDO refSourceSystemDO) {
		this.refSourceSystemDO = refSourceSystemDO;
	}

	/**
	 * @return the refStateProvinceDO
	 */
	public RefStateProvinceDO getRefStateProvinceDO() {
		return refStateProvinceDO;
	}

	/**
	 * @param refStateProvinceDO
	 *            the refStateProvinceDO to set
	 */
	public void setRefStateProvinceDO(RefStateProvinceDO refStateProvinceDO) {
		this.refStateProvinceDO = refStateProvinceDO;
	}

	/**
	 * @return the refStatusInSourceDO
	 */
	public RefStatusInSourceDO getRefStatusInSourceDO() {
		return refStatusInSourceDO;
	}

	/**
	 * @param refStatusInSourceDO
	 *            the refStatusInSourceDO to set
	 */
	public void setRefStatusInSourceDO(RefStatusInSourceDO refStatusInSourceDO) {
		this.refStatusInSourceDO = refStatusInSourceDO;
	}

	/**
	 * @return the refStatusTypeDO
	 */
	public RefStatusTypeDO getRefStatusTypeDO() {
		return refStatusTypeDO;
	}

	/**
	 * @param refStatusTypeDO
	 *            the refStatusTypeDO to set
	 */
	public void setRefStatusTypeDO(RefStatusTypeDO refStatusTypeDO) {
		this.refStatusTypeDO = refStatusTypeDO;
	}

	/**
	 * @return the refSuffixNameDO
	 */
	public RefSuffixNameDO getRefSuffixNameDO() {
		return refSuffixNameDO;
	}

	/**
	 * @param refSuffixNameDO
	 *            the refSuffixNameDO to set
	 */
	public void setRefSuffixNameDO(RefSuffixNameDO refSuffixNameDO) {
		this.refSuffixNameDO = refSuffixNameDO;
	}

	/**
	 * @return the refTerminationReasonDO
	 */
	public RefTerminationReasonDO getRefTerminationReasonDO() {
		return refTerminationReasonDO;
	}

	/**
	 * @param refTerminationReasonDO
	 *            the refTerminationReasonDO to set
	 */
	public void setRefTerminationReasonDO(RefTerminationReasonDO refTerminationReasonDO) {
		this.refTerminationReasonDO = refTerminationReasonDO;
	}

	/**
	 * @return the vehicleDO
	 */
	public VehicleDO getVehicleDO() {
		return vehicleDO;
	}

	/**
	 * @param vehicleDO
	 *            the vehicleDO to set
	 */
	public void setVehicleDO(VehicleDO vehicleDO) {
		this.vehicleDO = vehicleDO;
	}

	/**
	 * @return the refAccountMdmStatusDOList
	 */
	public List<RefAccountMdmStatusDO> getRefAccountMdmStatusDOList() {
		return refAccountMdmStatusDOList;
	}

	/**
	 * @param refAccountMdmStatusDOList
	 *            the refAccountMdmStatusDOList to set
	 */
	public void setRefAccountMdmStatusDOList(List<RefAccountMdmStatusDO> refAccountMdmStatusDOList) {
		this.refAccountMdmStatusDOList = refAccountMdmStatusDOList;
	}

	/**
	 * @return the refAccountSourceStatusDOList
	 */
	public List<RefAccountSourceStatusDO> getRefAccountSourceStatusDOList() {
		return refAccountSourceStatusDOList;
	}

	/**
	 * @param refAccountSourceStatusDOList
	 *            the refAccountSourceStatusDOList to set
	 */
	public void setRefAccountSourceStatusDOList(List<RefAccountSourceStatusDO> refAccountSourceStatusDOList) {
		this.refAccountSourceStatusDOList = refAccountSourceStatusDOList;
	}

	/**
	 * @return the refAddressSubtypeDOList
	 */
	public List<RefAddressSubtypeDO> getRefAddressSubtypeDOList() {
		return refAddressSubtypeDOList;
	}

	/**
	 * @param refAddressSubtypeDOList
	 *            the refAddressSubtypeDOList to set
	 */
	public void setRefAddressSubtypeDOList(List<RefAddressSubtypeDO> refAddressSubtypeDOList) {
		this.refAddressSubtypeDOList = refAddressSubtypeDOList;
	}

	/**
	 * @return the refAddressTypeDOList
	 */
	public List<RefAddressTypeDO> getRefAddressTypeDOList() {
		return refAddressTypeDOList;
	}

	/**
	 * @param refAddressTypeDOList
	 *            the refAddressTypeDOList to set
	 */
	public void setRefAddressTypeDOList(List<RefAddressTypeDO> refAddressTypeDOList) {
		this.refAddressTypeDOList = refAddressTypeDOList;
	}

	/**
	 * @return the refAgreementTypeDOList
	 */
	public List<RefAgreementTypeDO> getRefAgreementTypeDOList() {
		return refAgreementTypeDOList;
	}

	/**
	 * @param refAgreementTypeDOList
	 *            the refAgreementTypeDOList to set
	 */
	public void setRefAgreementTypeDOList(List<RefAgreementTypeDO> refAgreementTypeDOList) {
		this.refAgreementTypeDOList = refAgreementTypeDOList;
	}

	/**
	 * @return the refBillingModeTypeDOList
	 */
	public List<RefBillingModeTypeDO> getRefBillingModeTypeDOList() {
		return refBillingModeTypeDOList;
	}

	/**
	 * @param refBillingModeTypeDOList
	 *            the refBillingModeTypeDOList to set
	 */
	public void setRefBillingModeTypeDOList(List<RefBillingModeTypeDO> refBillingModeTypeDOList) {
		this.refBillingModeTypeDOList = refBillingModeTypeDOList;
	}

	/**
	 * @return the refAssocTypeDOList
	 */
	public List<RefAssocTypeDO> getRefAssocTypeDOList() {
		return refAssocTypeDOList;
	}

	/**
	 * @param refAssocTypeDOList
	 *            the refAssocTypeDOList to set
	 */
	public void setRefAssocTypeDOList(List<RefAssocTypeDO> refAssocTypeDOList) {
		this.refAssocTypeDOList = refAssocTypeDOList;
	}

	/**
	 * @return the refBranchCodeDOList
	 */
	public List<RefBranchCodeDO> getRefBranchCodeDOList() {
		return refBranchCodeDOList;
	}

	/**
	 * @param refBranchCodeDOList
	 *            the refBranchCodeDOList to set
	 */
	public void setRefBranchCodeDOList(List<RefBranchCodeDO> refBranchCodeDOList) {
		this.refBranchCodeDOList = refBranchCodeDOList;
	}

	/**
	 * @return the refClassificationCodeDOList
	 */
	public List<RefClassificationCodeDO> getRefClassificationCodeDOList() {
		return refClassificationCodeDOList;
	}

	/**
	 * @param refClassificationCodeDOList
	 *            the refClassificationCodeDOList to set
	 */
	public void setRefClassificationCodeDOList(List<RefClassificationCodeDO> refClassificationCodeDOList) {
		this.refClassificationCodeDOList = refClassificationCodeDOList;
	}

	/**
	 * @return the refCorporationNameTypeDOList
	 */
	public List<RefCorporationNameTypeDO> getRefCorporationNameTypeDOList() {
		return refCorporationNameTypeDOList;
	}

	/**
	 * @param refCorporationNameTypeDOList
	 *            the refCorporationNameTypeDOList to set
	 */
	public void setRefCorporationNameTypeDOList(List<RefCorporationNameTypeDO> refCorporationNameTypeDOList) {
		this.refCorporationNameTypeDOList = refCorporationNameTypeDOList;
	}

	/**
	 * @return the refCorporationTypeDOList
	 */
	public List<RefCorporationTypeDO> getRefCorporationTypeDOList() {
		return refCorporationTypeDOList;
	}

	/**
	 * @param refCorporationTypeDOList
	 *            the refCorporationTypeDOList to set
	 */
	public void setRefCorporationTypeDOList(List<RefCorporationTypeDO> refCorporationTypeDOList) {
		this.refCorporationTypeDOList = refCorporationTypeDOList;
	}

	/**
	 * @return the refCountryIsoDOList
	 */
	public List<RefCountryIsoDO> getRefCountryIsoDOList() {
		return refCountryIsoDOList;
	}

	/**
	 * @param refCountryIsoDOList
	 *            the refCountryIsoDOList to set
	 */
	public void setRefCountryIsoDOList(List<RefCountryIsoDO> refCountryIsoDOList) {
		this.refCountryIsoDOList = refCountryIsoDOList;
	}

	/**
	 * @return the refCurrencyDOList
	 */
	public List<RefCurrencyDO> getRefCurrencyDOList() {
		return refCurrencyDOList;
	}

	/**
	 * @param refCurrencyDOList
	 *            the refCurrencyDOList to set
	 */
	public void setRefCurrencyDOList(List<RefCurrencyDO> refCurrencyDOList) {
		this.refCurrencyDOList = refCurrencyDOList;
	}

	/**
	 * @return the refDeactivationReasonDOList
	 */
	public List<RefDeactivationReasonDO> getRefDeactivationReasonDOList() {
		return refDeactivationReasonDOList;
	}

	/**
	 * @param refDeactivationReasonDOList
	 *            the refDeactivationReasonDOList to set
	 */
	public void setRefDeactivationReasonDOList(List<RefDeactivationReasonDO> refDeactivationReasonDOList) {
		this.refDeactivationReasonDOList = refDeactivationReasonDOList;
	}

	/**
	 * @return the refGenderDOList
	 */
	public List<RefGenderDO> getRefGenderDOList() {
		return refGenderDOList;
	}

	/**
	 * @param refGenderDOList
	 *            the refGenderDOList to set
	 */
	public void setRefGenderDOList(List<RefGenderDO> refGenderDOList) {
		this.refGenderDOList = refGenderDOList;
	}

	/**
	 * @return the refGroupSubtypeDOList
	 */
	public List<RefGroupSubtypeDO> getRefGroupSubtypeDOList() {
		return refGroupSubtypeDOList;
	}

	/**
	 * @param refGroupSubtypeDOList
	 *            the refGroupSubtypeDOList to set
	 */
	public void setRefGroupSubtypeDOList(List<RefGroupSubtypeDO> refGroupSubtypeDOList) {
		this.refGroupSubtypeDOList = refGroupSubtypeDOList;
	}

	/**
	 * @return the refGroupTypeDOList
	 */
	public List<RefGroupTypeDO> getRefGroupTypeDOList() {
		return refGroupTypeDOList;
	}

	/**
	 * @param refGroupTypeDOList
	 *            the refGroupTypeDOList to set
	 */
	public void setRefGroupTypeDOList(List<RefGroupTypeDO> refGroupTypeDOList) {
		this.refGroupTypeDOList = refGroupTypeDOList;
	}

	/**
	 * @return the refHighestEduQualDOList
	 */
	public List<RefHighestEduQualDO> getRefHighestEduQualDOList() {
		return refHighestEduQualDOList;
	}

	/**
	 * @param refHighestEduQualDOList
	 *            the refHighestEduQualDOList to set
	 */
	public void setRefHighestEduQualDOList(List<RefHighestEduQualDO> refHighestEduQualDOList) {
		this.refHighestEduQualDOList = refHighestEduQualDOList;
	}

	/**
	 * @return the refIdentificationTypeDOList
	 */
	public List<RefIdentificationTypeDO> getRefIdentificationTypeDOList() {
		return refIdentificationTypeDOList;
	}

	/**
	 * @param refIdentificationTypeDOList
	 *            the refIdentificationTypeDOList to set
	 */
	public void setRefIdentificationTypeDOList(List<RefIdentificationTypeDO> refIdentificationTypeDOList) {
		this.refIdentificationTypeDOList = refIdentificationTypeDOList;
	}

	/**
	 * @return the refImportanceTypeDOList
	 */
	public List<RefImportanceTypeDO> getRefImportanceTypeDOList() {
		return refImportanceTypeDOList;
	}

	/**
	 * @param refImportanceTypeDOList
	 *            the refImportanceTypeDOList to set
	 */
	public void setRefImportanceTypeDOList(List<RefImportanceTypeDO> refImportanceTypeDOList) {
		this.refImportanceTypeDOList = refImportanceTypeDOList;
	}

	/**
	 * @return the refIndustryCodeDOList
	 */
	public List<RefIndustryCodeDO> getRefIndustryCodeDOList() {
		return refIndustryCodeDOList;
	}

	/**
	 * @param refIndustryCodeDOList
	 *            the refIndustryCodeDOList to set
	 */
	public void setRefIndustryCodeDOList(List<RefIndustryCodeDO> refIndustryCodeDOList) {
		this.refIndustryCodeDOList = refIndustryCodeDOList;
	}

	/**
	 * @return the refLanguageCodeDOList
	 */
	public List<RefLanguageCodeDO> getRefLanguageCodeDOList() {
		return refLanguageCodeDOList;
	}

	/**
	 * @param refLanguageCodeDOList
	 *            the refLanguageCodeDOList to set
	 */
	public void setRefLanguageCodeDOList(List<RefLanguageCodeDO> refLanguageCodeDOList) {
		this.refLanguageCodeDOList = refLanguageCodeDOList;
	}

	/**
	 * @return the refLeRatingDOList
	 */
	public List<RefLeRatingDO> getRefLeRatingDOList() {
		return refLeRatingDOList;
	}

	/**
	 * @param refLeRatingDOList
	 *            the refLeRatingDOList to set
	 */
	public void setRefLeRatingDOList(List<RefLeRatingDO> refLeRatingDOList) {
		this.refLeRatingDOList = refLeRatingDOList;
	}

	/**
	 * @return the refLeRelationshipTypeDOList
	 */
	public List<RefLeRelationshipTypeDO> getRefLeRelationshipTypeDOList() {
		return refLeRelationshipTypeDOList;
	}

	/**
	 * @param refLeRelationshipTypeDOList
	 *            the refLeRelationshipTypeDOList to set
	 */
	public void setRefLeRelationshipTypeDOList(List<RefLeRelationshipTypeDO> refLeRelationshipTypeDOList) {
		this.refLeRelationshipTypeDOList = refLeRelationshipTypeDOList;
	}

	/**
	 * @return the refLeRoletypeDOList
	 */
	public List<RefLeRoletypeDO> getRefLeRoletypeDOList() {
		return refLeRoletypeDOList;
	}

	/**
	 * @param refLeRoletypeDOList
	 *            the refLeRoletypeDOList to set
	 */
	public void setRefLeRoletypeDOList(List<RefLeRoletypeDO> refLeRoletypeDOList) {
		this.refLeRoletypeDOList = refLeRoletypeDOList;
	}

	/**
	 * @return the refLobtypeDOList
	 */
	public List<RefLobtypeDO> getRefLobtypeDOList() {
		return refLobtypeDOList;
	}

	/**
	 * @param refLobtypeDOList
	 *            the refLobtypeDOList to set
	 */
	public void setRefLobtypeDOList(List<RefLobtypeDO> refLobtypeDOList) {
		this.refLobtypeDOList = refLobtypeDOList;
	}

	/**
	 * @return the refPersonnameTypeDOList
	 */
	public List<RefPersonnameTypeDO> getRefPersonnameTypeDOList() {
		return refPersonnameTypeDOList;
	}

	/**
	 * @param refPersonnameTypeDOList
	 *            the refPersonnameTypeDOList to set
	 */
	public void setRefPersonnameTypeDOList(List<RefPersonnameTypeDO> refPersonnameTypeDOList) {
		this.refPersonnameTypeDOList = refPersonnameTypeDOList;
	}

	/**
	 * @return the refPersonTypeDOList
	 */
	public List<RefPersonTypeDO> getRefPersonTypeDOList() {
		return refPersonTypeDOList;
	}

	/**
	 * @param refPersonTypeDOList
	 *            the refPersonTypeDOList to set
	 */
	public void setRefPersonTypeDOList(List<RefPersonTypeDO> refPersonTypeDOList) {
		this.refPersonTypeDOList = refPersonTypeDOList;
	}

	/**
	 * @return the refPhoneSubtypeDOList
	 */
	public List<RefPhoneSubtypeDO> getRefPhoneSubtypeDOList() {
		return refPhoneSubtypeDOList;
	}

	/**
	 * @param refPhoneSubtypeDOList
	 *            the refPhoneSubtypeDOList to set
	 */
	public void setRefPhoneSubtypeDOList(List<RefPhoneSubtypeDO> refPhoneSubtypeDOList) {
		this.refPhoneSubtypeDOList = refPhoneSubtypeDOList;
	}

	/**
	 * @return the refPhoneTypeDOList
	 */
	public List<RefPhoneTypeDO> getRefPhoneTypeDOList() {
		return refPhoneTypeDOList;
	}

	/**
	 * @param refPhoneTypeDOList
	 *            the refPhoneTypeDOList to set
	 */
	public void setRefPhoneTypeDOList(List<RefPhoneTypeDO> refPhoneTypeDOList) {
		this.refPhoneTypeDOList = refPhoneTypeDOList;
	}

	/**
	 * @return the refPrefixNameDOList
	 */
	public List<RefPrefixNameDO> getRefPrefixNameDOList() {
		return refPrefixNameDOList;
	}

	/**
	 * @param refPrefixNameDOList
	 *            the refPrefixNameDOList to set
	 */
	public void setRefPrefixNameDOList(List<RefPrefixNameDO> refPrefixNameDOList) {
		this.refPrefixNameDOList = refPrefixNameDOList;
	}

	/**
	 * @return the refPrivacyPrefTypeDOList
	 */
	public List<RefPreferenceTypeDO> getRefPreferenceTypeDOList() {
		return refPreferenceTypeDOList;
	}

	/**
	 * @param refPrivacyPrefTypeDOList
	 *            the refPrivacyPrefTypeDOList to set
	 */
	public void setRefPreferenceTypeDOList(List<RefPreferenceTypeDO> refPreferenceTypeDOList) {
		this.refPreferenceTypeDOList = refPreferenceTypeDOList;
	}

	/**
	 * @return the refPropertyLeReltypeDOList
	 */
	public List<RefPropertyLeReltypeDO> getRefPropertyLeReltypeDOList() {
		return refPropertyLeReltypeDOList;
	}

	/**
	 * @param refPropertyLeReltypeDOList
	 *            the refPropertyLeReltypeDOList to set
	 */
	public void setRefPropertyLeReltypeDOList(List<RefPropertyLeReltypeDO> refPropertyLeReltypeDOList) {
		this.refPropertyLeReltypeDOList = refPropertyLeReltypeDOList;
	}

	/**
	 * @return the refRelationshipStatusDOList
	 */
	public List<RefRelationshipStatusDO> getRefRelationshipStatusDOList() {
		return refRelationshipStatusDOList;
	}

	/**
	 * @param refRelationshipStatusDOList
	 *            the refRelationshipStatusDOList to set
	 */
	public void setRefRelationshipStatusDOList(List<RefRelationshipStatusDO> refRelationshipStatusDOList) {
		this.refRelationshipStatusDOList = refRelationshipStatusDOList;
	}

	/**
	 * @return the refSourceSystemDOList
	 */
	public List<RefSourceSystemDO> getRefSourceSystemDOList() {
		return refSourceSystemDOList;
	}

	/**
	 * @param refSourceSystemDOList
	 *            the refSourceSystemDOList to set
	 */
	public void setRefSourceSystemDOList(List<RefSourceSystemDO> refSourceSystemDOList) {
		this.refSourceSystemDOList = refSourceSystemDOList;
	}

	/**
	 * @return the refStateProvinceDOList
	 */
	public List<RefStateProvinceDO> getRefStateProvinceDOList() {
		return refStateProvinceDOList;
	}

	/**
	 * @param refStateProvinceDOList
	 *            the refStateProvinceDOList to set
	 */
	public void setRefStateProvinceDOList(List<RefStateProvinceDO> refStateProvinceDOList) {
		this.refStateProvinceDOList = refStateProvinceDOList;
	}

	/**
	 * @return the refStatusInSourceDOList
	 */
	public List<RefStatusInSourceDO> getRefStatusInSourceDOList() {
		return refStatusInSourceDOList;
	}

	/**
	 * @param refStatusInSourceDOList
	 *            the refStatusInSourceDOList to set
	 */
	public void setRefStatusInSourceDOList(List<RefStatusInSourceDO> refStatusInSourceDOList) {
		this.refStatusInSourceDOList = refStatusInSourceDOList;
	}

	/**
	 * @return the refStatusTypeDOList
	 */
	public List<RefStatusTypeDO> getRefStatusTypeDOList() {
		return refStatusTypeDOList;
	}

	/**
	 * @param refStatusTypeDOList
	 *            the refStatusTypeDOList to set
	 */
	public void setRefStatusTypeDOList(List<RefStatusTypeDO> refStatusTypeDOList) {
		this.refStatusTypeDOList = refStatusTypeDOList;
	}

	/**
	 * @return the refSuffixNameDOList
	 */
	public List<RefSuffixNameDO> getRefSuffixNameDOList() {
		return refSuffixNameDOList;
	}

	/**
	 * @param refSuffixNameDOList
	 *            the refSuffixNameDOList to set
	 */
	public void setRefSuffixNameDOList(List<RefSuffixNameDO> refSuffixNameDOList) {
		this.refSuffixNameDOList = refSuffixNameDOList;
	}

	/**
	 * @return the refTerminationReasonDOList
	 */
	public List<RefTerminationReasonDO> getRefTerminationReasonDOList() {
		return refTerminationReasonDOList;
	}

	/**
	 * @return the accountAddressAssocDOList
	 */
	public List<AccountAddressAssocDO> getAccountAddressAssocDOList() {
		return accountAddressAssocDOList;
	}

	/**
	 * @param accountAddressAssocDOList
	 *            the accountAddressAssocDOList to set
	 */
	public void setAccountAddressAssocDOList(List<AccountAddressAssocDO> accountAddressAssocDOList) {
		this.accountAddressAssocDOList = accountAddressAssocDOList;
	}

	/**
	 * @return the accountDOList
	 */
	public List<AccountDO> getAccountDOList() {
		return accountDOList;
	}

	/**
	 * @param accountDOList
	 *            the accountDOList to set
	 */
	public void setAccountDOList(List<AccountDO> accountDOList) {
		this.accountDOList = accountDOList;
	}

	/**
	 * @return the accountPhoneAssocDOList
	 */
	public List<AccountPhoneAssocDO> getAccountPhoneAssocDOList() {
		return accountPhoneAssocDOList;
	}

	/**
	 * @param accountPhoneAssocDOList
	 *            the accountPhoneAssocDOList to set
	 */
	public void setAccountPhoneAssocDOList(List<AccountPhoneAssocDO> accountPhoneAssocDOList) {
		this.accountPhoneAssocDOList = accountPhoneAssocDOList;
	}

	/**
	 * @return the addressDOList
	 */
	public List<AddressDO> getAddressDOList() {
		return addressDOList;
	}

	/**
	 * @param addressDOList
	 *            the addressDOList to set
	 */
	public void setAddressDOList(List<AddressDO> addressDOList) {
		this.addressDOList = addressDOList;
	}

	/**
	 * @return the corporationnamesDOList
	 */
	public List<CorporationnamesDO> getCorporationnamesDOList() {
		return corporationnamesDOList;
	}

	/**
	 * @param corporationnamesDOList
	 *            the corporationnamesDOList to set
	 */
	public void setCorporationnamesDOList(List<CorporationnamesDO> corporationnamesDOList) {
		this.corporationnamesDOList = corporationnamesDOList;
	}

	/**
	 * @return the entityGroupAssocDOList
	 */
	public List<EntityGroupAssocDO> getEntityGroupAssocDOList() {
		return entityGroupAssocDOList;
	}

	/**
	 * @param entityGroupAssocDOList
	 *            the entityGroupAssocDOList to set
	 */
	public void setEntityGroupAssocDOList(List<EntityGroupAssocDO> entityGroupAssocDOList) {
		this.entityGroupAssocDOList = entityGroupAssocDOList;
	}

	/**
	 * @return the entityGroupDOList
	 */
	public List<EntityGroupDO> getEntityGroupDOList() {
		return entityGroupDOList;
	}

	/**
	 * @param entityGroupDOList
	 *            the entityGroupDOList to set
	 */
	public void setEntityGroupDOList(List<EntityGroupDO> entityGroupDOList) {
		this.entityGroupDOList = entityGroupDOList;
	}

	/**
	 * @return the leAccountAssocDOList
	 */
	public List<LeAccountAssocDO> getLeAccountAssocDOList() {
		return leAccountAssocDOList;
	}

	/**
	 * @param leAccountAssocDOList
	 *            the leAccountAssocDOList to set
	 */
	public void setLeAccountAssocDOList(List<LeAccountAssocDO> leAccountAssocDOList) {
		this.leAccountAssocDOList = leAccountAssocDOList;
	}

	/**
	 * @return the leAddressAssocDOList
	 */
	public List<LeAddressAssocDO> getLeAddressAssocDOList() {
		return leAddressAssocDOList;
	}

	/**
	 * @param leAddressAssocDOList
	 *            the leAddressAssocDOList to set
	 */
	public void setLeAddressAssocDOList(List<LeAddressAssocDO> leAddressAssocDOList) {
		this.leAddressAssocDOList = leAddressAssocDOList;
	}

	/**
	 * @return the leCorporationDOList
	 */
	public List<LeCorporationDO> getLeCorporationDOList() {
		return leCorporationDOList;
	}

	/**
	 * @param leCorporationDOList
	 *            the leCorporationDOList to set
	 */
	public void setLeCorporationDOList(List<LeCorporationDO> leCorporationDOList) {
		this.leCorporationDOList = leCorporationDOList;
	}

	/**
	 * @return the leIdentifierKycRegistryDOList
	 */
	public List<LeIdentifierKycRegistryDO> getLeIdentifierKycRegistryDOList() {
		return leIdentifierKycRegistryDOList;
	}

	/**
	 * @param leIdentifierKycRegistryDOList
	 *            the leIdentifierKycRegistryDOList to set
	 */
	public void setLeIdentifierKycRegistryDOList(List<LeIdentifierKycRegistryDO> leIdentifierKycRegistryDOList) {
		this.leIdentifierKycRegistryDOList = leIdentifierKycRegistryDOList;
	}

	/**
	 * @return the lePersonDOList
	 */
	public List<LePersonDO> getLePersonDOList() {
		return lePersonDOList;
	}

	/**
	 * @param lePersonDOList
	 *            the lePersonDOList to set
	 */
	public void setLePersonDOList(List<LePersonDO> lePersonDOList) {
		this.lePersonDOList = lePersonDOList;
	}

	/**
	 * @return the lePhoneAssocDOList
	 */
	public List<LePhoneAssocDO> getLePhoneAssocDOList() {
		return lePhoneAssocDOList;
	}

	/**
	 * @param lePhoneAssocDOList
	 *            the lePhoneAssocDOList to set
	 */
	public void setLePhoneAssocDOList(List<LePhoneAssocDO> lePhoneAssocDOList) {
		this.lePhoneAssocDOList = lePhoneAssocDOList;
	}

	/**
	 * @return the lePreferencesDOList
	 */
	public List<LePreferencesDO> getLePreferencesDOList() {
		return lePreferencesDOList;
	}

	/**
	 * @param lePreferencesDOList
	 *            the lePreferencesDOList to set
	 */
	public void setLePreferencesDOList(List<LePreferencesDO> lePreferencesDOList) {
		this.lePreferencesDOList = lePreferencesDOList;
	}

	/**
	 * @return the lePropertyAssocDOList
	 */
	public List<LePropertyAssocDO> getLePropertyAssocDOList() {
		return lePropertyAssocDOList;
	}

	/**
	 * @param lePropertyAssocDOList
	 *            the lePropertyAssocDOList to set
	 */
	public void setLePropertyAssocDOList(List<LePropertyAssocDO> lePropertyAssocDOList) {
		this.lePropertyAssocDOList = lePropertyAssocDOList;
	}

	/**
	 * @return the leSystemKeysRegistryDOList
	 */
	public List<LeSystemKeysRegistryDO> getLeSystemKeysRegistryDOList() {
		return leSystemKeysRegistryDOList;
	}

	/**
	 * @param leSystemKeysRegistryDOList
	 *            the leSystemKeysRegistryDOList to set
	 */
	public void setLeSystemKeysRegistryDOList(List<LeSystemKeysRegistryDO> leSystemKeysRegistryDOList) {
		this.leSystemKeysRegistryDOList = leSystemKeysRegistryDOList;
	}

	/**
	 * @return the leToLeRelationshipDOList
	 */
	public List<LeToLeRelationshipDO> getLeToLeRelationshipDOList() {
		return leToLeRelationshipDOList;
	}

	/**
	 * @param leToLeRelationshipDOList
	 *            the leToLeRelationshipDOList to set
	 */
	public void setLeToLeRelationshipDOList(List<LeToLeRelationshipDO> leToLeRelationshipDOList) {
		this.leToLeRelationshipDOList = leToLeRelationshipDOList;
	}

	/**
	 * @return the leVehicleAssocDOList
	 */
	public List<LeVehicleAssocDO> getLeVehicleAssocDOList() {
		return leVehicleAssocDOList;
	}

	/**
	 * @param leVehicleAssocDOList
	 *            the leVehicleAssocDOList to set
	 */
	public void setLeVehicleAssocDOList(List<LeVehicleAssocDO> leVehicleAssocDOList) {
		this.leVehicleAssocDOList = leVehicleAssocDOList;
	}

	/**
	 * @return the miscellaneousInfoDOList
	 */
	public List<MiscellaneousInfoDO> getMiscellaneousInfoDOList() {
		return miscellaneousInfoDOList;
	}

	/**
	 * @param miscellaneousInfoDOList
	 *            the miscellaneousInfoDOList to set
	 */
	public void setMiscellaneousInfoDOList(List<MiscellaneousInfoDO> miscellaneousInfoDOList) {
		this.miscellaneousInfoDOList = miscellaneousInfoDOList;
	}

	/**
	 * @return the personnamesDOList
	 */
	public List<PersonnamesDO> getPersonnamesDOList() {
		return personnamesDOList;
	}

	/**
	 * @param personnamesDOList
	 *            the personnamesDOList to set
	 */
	public void setPersonnamesDOList(List<PersonnamesDO> personnamesDOList) {
		this.personnamesDOList = personnamesDOList;
	}

	/**
	 * @return the phoneStandardizedDOList
	 */
	public List<PhoneStandardizedDO> getPhoneStandardizedDOList() {
		return phoneStandardizedDOList;
	}

	/**
	 * @param phoneStandardizedDOList
	 *            the phoneStandardizedDOList to set
	 */
	public void setPhoneStandardizedDOList(List<PhoneStandardizedDO> phoneStandardizedDOList) {
		this.phoneStandardizedDOList = phoneStandardizedDOList;
	}

	/**
	 * @return the propertyDOList
	 */
	public List<PropertyDO> getPropertyDOList() {
		return propertyDOList;
	}

	/**
	 * @param propertyDOList
	 *            the propertyDOList to set
	 */
	public void setPropertyDOList(List<PropertyDO> propertyDOList) {
		this.propertyDOList = propertyDOList;
	}

	/**
	 * @return the vehicleDOList
	 */
	public List<VehicleDO> getVehicleDOList() {
		return vehicleDOList;
	}

	/**
	 * @param vehicleDOList
	 *            the vehicleDOList to set
	 */
	public void setVehicleDOList(List<VehicleDO> vehicleDOList) {
		this.vehicleDOList = vehicleDOList;
	}

	/**
	 * @param refTerminationReasonDOList
	 *            the refTerminationReasonDOList to set
	 */
	public void setRefTerminationReasonDOList(List<RefTerminationReasonDO> refTerminationReasonDOList) {
		this.refTerminationReasonDOList = refTerminationReasonDOList;
	}

	/**
	 * @return the configAppPropertiesDO
	 */
	public ConfigAppPropertiesDO getConfigAppPropertiesDO() {
		return configAppPropertiesDO;
	}

	/**
	 * @param configAppPropertiesDO
	 *            the configAppPropertiesDO to set
	 */
	public void setConfigAppPropertiesDO(ConfigAppPropertiesDO configAppPropertiesDO) {
		this.configAppPropertiesDO = configAppPropertiesDO;
	}

	/**
	 * @return the configAppPropertiesDOList
	 */
	public List<ConfigAppPropertiesDO> getConfigAppPropertiesDOList() {
		return configAppPropertiesDOList;
	}

	/**
	 * @param configAppPropertiesDOList
	 *            the configAppPropertiesDOList to set
	 */
	public void setConfigAppPropertiesDOList(List<ConfigAppPropertiesDO> configAppPropertiesDOList) {
		this.configAppPropertiesDOList = configAppPropertiesDOList;
	}

	/**
	 * @return the configLanguageCodeDO
	 */
	public ConfigLanguageCodeDO getConfigLanguageCodeDO() {
		return configLanguageCodeDO;
	}

	/**
	 * @param configLanguageCodeDO
	 *            the configLanguageCodeDO to set
	 */
	public void setConfigLanguageCodeDO(ConfigLanguageCodeDO configLanguageCodeDO) {
		this.configLanguageCodeDO = configLanguageCodeDO;
	}

	/**
	 * @return the configLanguageCodeDOList
	 */
	public List<ConfigLanguageCodeDO> getConfigLanguageCodeDOList() {
		return configLanguageCodeDOList;
	}

	/**
	 * @param configLanguageCodeDOList
	 *            the configLanguageCodeDOList to set
	 */
	public void setConfigLanguageCodeDOList(List<ConfigLanguageCodeDO> configLanguageCodeDOList) {
		this.configLanguageCodeDOList = configLanguageCodeDOList;
	}

	/**
	 * @return the configInquiryLevelsDO
	 */
	public ConfigInquiryLevelsDO getConfigInquiryLevelsDO() {
		return configInquiryLevelsDO;
	}

	/**
	 * @param configInquiryLevelsDO
	 *            the configInquiryLevelsDO to set
	 */
	public void setConfigInquiryLevelsDO(ConfigInquiryLevelsDO configInquiryLevelsDO) {
		this.configInquiryLevelsDO = configInquiryLevelsDO;
	}

	/**
	 * @return the configInquiryLevelsDOList
	 */
	public List<ConfigInquiryLevelsDO> getConfigInquiryLevelsDOList() {
		return configInquiryLevelsDOList;
	}

	/**
	 * @param configInquiryLevelsDOList
	 *            the configInquiryLevelsDOList to set
	 */
	public void setConfigInquiryLevelsDOList(List<ConfigInquiryLevelsDO> configInquiryLevelsDOList) {
		this.configInquiryLevelsDOList = configInquiryLevelsDOList;
	}

	/**
	 * @return the searchLegalEntityRequestDO
	 */
	public SearchLegalEntityRequestDO getSearchLegalEntityRequestDO() {
		return searchLegalEntityRequestDO;
	}

	/**
	 * @param searchLegalEntityRequestDO
	 *            the searchLegalEntityRequestDO to set
	 */
	public void setSearchLegalEntityRequestDO(SearchLegalEntityRequestDO searchLegalEntityRequestDO) {
		this.searchLegalEntityRequestDO = searchLegalEntityRequestDO;
	}

	/**
	 * @return the configTxnRegistryDOList
	 */
	public List<ConfigTxnRegistryDO> getConfigTxnRegistryDOList() {
		return configTxnRegistryDOList;
	}

	/**
	 * @param configTxnRegistryDOList
	 *            the configTxnRegistryDOList to set
	 */
	public void setConfigTxnRegistryDOList(List<ConfigTxnRegistryDO> configTxnRegistryDOList) {
		this.configTxnRegistryDOList = configTxnRegistryDOList;
	}

	/**
	 * @return the legalentityDOList
	 */
	public List<LegalentityDO> getLegalentityDOList() {
		return legalentityDOList;
	}

	/**
	 * @param legalentityDOList
	 *            the legalentityDOList to set
	 */
	public void setLegalentityDOList(List<LegalentityDO> legalentityDOList) {
		this.legalentityDOList = legalentityDOList;
	}

	/**
	 * @return the searchAccountRequestDO
	 */
	public SearchAccountRequestDO getSearchAccountRequestDO() {
		return searchAccountRequestDO;
	}

	/**
	 * @param searchAccountRequestDO
	 *            the searchAccountRequestDO to set
	 */
	public void setSearchAccountRequestDO(SearchAccountRequestDO searchAccountRequestDO) {
		this.searchAccountRequestDO = searchAccountRequestDO;
	}

	/**
	 * @return the authRolesRegistryDO
	 */
	public AuthRolesRegistryDO getAuthRolesRegistryDO() {
		return authRolesRegistryDO;
	}

	/**
	 * @param authRolesRegistryDO
	 *            the authRolesRegistryDO to set
	 */
	public void setAuthRolesRegistryDO(AuthRolesRegistryDO authRolesRegistryDO) {
		this.authRolesRegistryDO = authRolesRegistryDO;
	}

	/**
	 * @return the authUserRegistryDO
	 */
	public AuthUserRegistryDO getAuthUserRegistryDO() {
		return authUserRegistryDO;
	}

	/**
	 * @param authUserRegistryDO
	 *            the authUserRegistryDO to set
	 */
	public void setAuthUserRegistryDO(AuthUserRegistryDO authUserRegistryDO) {
		this.authUserRegistryDO = authUserRegistryDO;
	}

	/**
	 * @return the authUserroleAccesscontrolDO
	 */
	public AuthUserroleAccesscontrolDO getAuthUserroleAccesscontrolDO() {
		return authUserroleAccesscontrolDO;
	}

	/**
	 * @param authUserroleAccesscontrolDO
	 *            the authUserroleAccesscontrolDO to set
	 */
	public void setAuthUserroleAccesscontrolDO(AuthUserroleAccesscontrolDO authUserroleAccesscontrolDO) {
		this.authUserroleAccesscontrolDO = authUserroleAccesscontrolDO;
	}

	/**
	 * @return the authUserRoleAssocDO
	 */
	public AuthUserRoleAssocDO getAuthUserRoleAssocDO() {
		return authUserRoleAssocDO;
	}

	/**
	 * @param authUserRoleAssocDO
	 *            the authUserRoleAssocDO to set
	 */
	public void setAuthUserRoleAssocDO(AuthUserRoleAssocDO authUserRoleAssocDO) {
		this.authUserRoleAssocDO = authUserRoleAssocDO;
	}

	/**
	 * @return the authRolesRegistryDOList
	 */
	public List<AuthRolesRegistryDO> getAuthRolesRegistryDOList() {
		return authRolesRegistryDOList;
	}

	/**
	 * @param authRolesRegistryDOList
	 *            the authRolesRegistryDOList to set
	 */
	public void setAuthRolesRegistryDOList(List<AuthRolesRegistryDO> authRolesRegistryDOList) {
		this.authRolesRegistryDOList = authRolesRegistryDOList;
	}

	/**
	 * @return the authUserRegistryDOList
	 */
	public List<AuthUserRegistryDO> getAuthUserRegistryDOList() {
		return authUserRegistryDOList;
	}

	/**
	 * @param authUserRegistryDOList
	 *            the authUserRegistryDOList to set
	 */
	public void setAuthUserRegistryDOList(List<AuthUserRegistryDO> authUserRegistryDOList) {
		this.authUserRegistryDOList = authUserRegistryDOList;
	}

	/**
	 * @return the authUserroleAccesscontrolDOList
	 */
	public List<AuthUserroleAccesscontrolDO> getAuthUserroleAccesscontrolDOList() {
		return authUserroleAccesscontrolDOList;
	}

	/**
	 * @param authUserroleAccesscontrolDOList
	 *            the authUserroleAccesscontrolDOList to set
	 */
	public void setAuthUserroleAccesscontrolDOList(List<AuthUserroleAccesscontrolDO> authUserroleAccesscontrolDOList) {
		this.authUserroleAccesscontrolDOList = authUserroleAccesscontrolDOList;
	}

	/**
	 * @return the authUserRoleAssocDOList
	 */
	public List<AuthUserRoleAssocDO> getAuthUserRoleAssocDOList() {
		return authUserRoleAssocDOList;
	}

	/**
	 * @param authUserRoleAssocDOList
	 *            the authUserRoleAssocDOList to set
	 */
	public void setAuthUserRoleAssocDOList(List<AuthUserRoleAssocDO> authUserRoleAssocDOList) {
		this.authUserRoleAssocDOList = authUserRoleAssocDOList;
	}

	/**
	 * @return the searchAuthAccessControlDO
	 */
	public SearchAuthAccessControlDO getSearchAuthAccessControlDO() {
		return searchAuthAccessControlDO;
	}

	/**
	 * @param searchAuthAccessControlDO
	 *            the searchAuthAccessControlDO to set
	 */
	public void setSearchAuthAccessControlDO(SearchAuthAccessControlDO searchAuthAccessControlDO) {
		this.searchAuthAccessControlDO = searchAuthAccessControlDO;
	}

	/**
	 * @return the refInactivationReasonDO
	 */
	public RefInactivationReasonDO getRefInactivationReasonDO() {
		return refInactivationReasonDO;
	}

	/**
	 * @param refInactivationReasonDO
	 *            the refInactivationReasonDO to set
	 */
	public void setRefInactivationReasonDO(RefInactivationReasonDO refInactivationReasonDO) {
		this.refInactivationReasonDO = refInactivationReasonDO;
	}

	/**
	 * @return the refMatchActionstatusDO
	 */
	public RefMatchActionstatusDO getRefMatchActionstatusDO() {
		return refMatchActionstatusDO;
	}

	/**
	 * @param refMatchActionstatusDO
	 *            the refMatchActionstatusDO to set
	 */
	public void setRefMatchActionstatusDO(RefMatchActionstatusDO refMatchActionstatusDO) {
		this.refMatchActionstatusDO = refMatchActionstatusDO;
	}

	/**
	 * @return the refMatchProposedActionDO
	 */
	public RefMatchProposedActionDO getRefMatchProposedActionDO() {
		return refMatchProposedActionDO;
	}

	/**
	 * @param refMatchProposedActionDO
	 *            the refMatchProposedActionDO to set
	 */
	public void setRefMatchProposedActionDO(RefMatchProposedActionDO refMatchProposedActionDO) {
		this.refMatchProposedActionDO = refMatchProposedActionDO;
	}

	/**
	 * @return the refMatchResultDO
	 */
	public RefMatchResultDO getRefMatchResultDO() {
		return refMatchResultDO;
	}

	/**
	 * @param refMatchResultDO
	 *            the refMatchResultDO to set
	 */
	public void setRefMatchResultDO(RefMatchResultDO refMatchResultDO) {
		this.refMatchResultDO = refMatchResultDO;
	}

	/**
	 * @return the refMatchScoreDO
	 */
	public RefMatchScoreDO getRefMatchScoreDO() {
		return refMatchScoreDO;
	}

	/**
	 * @param refMatchScoreDO
	 *            the refMatchScoreDO to set
	 */
	public void setRefMatchScoreDO(RefMatchScoreDO refMatchScoreDO) {
		this.refMatchScoreDO = refMatchScoreDO;
	}

	/**
	 * @return the refMatchThresholdDO
	 */
	public RefMatchThresholdDO getRefMatchThresholdDO() {
		return refMatchThresholdDO;
	}

	/**
	 * @param refMatchThresholdDO
	 *            the refMatchThresholdDO to set
	 */
	public void setRefMatchThresholdDO(RefMatchThresholdDO refMatchThresholdDO) {
		this.refMatchThresholdDO = refMatchThresholdDO;
	}

	/**
	 * @return the refMergeReasonDO
	 */
	public RefMergeReasonDO getRefMergeReasonDO() {
		return refMergeReasonDO;
	}

	/**
	 * @param refMergeReasonDO
	 *            the refMergeReasonDO to set
	 */
	public void setRefMergeReasonDO(RefMergeReasonDO refMergeReasonDO) {
		this.refMergeReasonDO = refMergeReasonDO;
	}

	/**
	 * @return the refInactivationReasonDOList
	 */
	public List<RefInactivationReasonDO> getRefInactivationReasonDOList() {
		return refInactivationReasonDOList;
	}

	/**
	 * @param refInactivationReasonDOList
	 *            the refInactivationReasonDOList to set
	 */
	public void setRefInactivationReasonDOList(List<RefInactivationReasonDO> refInactivationReasonDOList) {
		this.refInactivationReasonDOList = refInactivationReasonDOList;
	}

	/**
	 * @return the refMatchActionstatusDOList
	 */
	public List<RefMatchActionstatusDO> getRefMatchActionstatusDOList() {
		return refMatchActionstatusDOList;
	}

	/**
	 * @param refMatchActionstatusDOList
	 *            the refMatchActionstatusDOList to set
	 */
	public void setRefMatchActionstatusDOList(List<RefMatchActionstatusDO> refMatchActionstatusDOList) {
		this.refMatchActionstatusDOList = refMatchActionstatusDOList;
	}

	/**
	 * @return the refMatchProposedActionDOList
	 */
	public List<RefMatchProposedActionDO> getRefMatchProposedActionDOList() {
		return refMatchProposedActionDOList;
	}

	/**
	 * @param refMatchProposedActionDOList
	 *            the refMatchProposedActionDOList to set
	 */
	public void setRefMatchProposedActionDOList(List<RefMatchProposedActionDO> refMatchProposedActionDOList) {
		this.refMatchProposedActionDOList = refMatchProposedActionDOList;
	}

	/**
	 * @return the refMatchResultDOList
	 */
	public List<RefMatchResultDO> getRefMatchResultDOList() {
		return refMatchResultDOList;
	}

	/**
	 * @param refMatchResultDOList
	 *            the refMatchResultDOList to set
	 */
	public void setRefMatchResultDOList(List<RefMatchResultDO> refMatchResultDOList) {
		this.refMatchResultDOList = refMatchResultDOList;
	}

	/**
	 * @return the refMatchScoreDOList
	 */
	public List<RefMatchScoreDO> getRefMatchScoreDOList() {
		return refMatchScoreDOList;
	}

	/**
	 * @param refMatchScoreDOList
	 *            the refMatchScoreDOList to set
	 */
	public void setRefMatchScoreDOList(List<RefMatchScoreDO> refMatchScoreDOList) {
		this.refMatchScoreDOList = refMatchScoreDOList;
	}

	/**
	 * @return the refMatchThresholdDOList
	 */
	public List<RefMatchThresholdDO> getRefMatchThresholdDOList() {
		return refMatchThresholdDOList;
	}

	/**
	 * @param refMatchThresholdDOList
	 *            the refMatchThresholdDOList to set
	 */
	public void setRefMatchThresholdDOList(List<RefMatchThresholdDO> refMatchThresholdDOList) {
		this.refMatchThresholdDOList = refMatchThresholdDOList;
	}

	/**
	 * @return the refMergeReasonDOList
	 */
	public List<RefMergeReasonDO> getRefMergeReasonDOList() {
		return refMergeReasonDOList;
	}

	/**
	 * @param refMergeReasonDOList
	 *            the refMergeReasonDOList to set
	 */
	public void setRefMergeReasonDOList(List<RefMergeReasonDO> refMergeReasonDOList) {
		this.refMergeReasonDOList = refMergeReasonDOList;
	}

	/**
	 * @return the inactiveLeRegistryDO
	 */
	public InactiveLeRegistryDO getInactiveLeRegistryDO() {
		return inactiveLeRegistryDO;
	}

	/**
	 * @param inactiveLeRegistryDO
	 *            the inactiveLeRegistryDO to set
	 */
	public void setInactiveLeRegistryDO(InactiveLeRegistryDO inactiveLeRegistryDO) {
		this.inactiveLeRegistryDO = inactiveLeRegistryDO;
	}

	/**
	 * @return the matchCandidateLeRegistryDO
	 */
	public MatchCandidateLeRegistryDO getMatchCandidateLeRegistryDO() {
		return matchCandidateLeRegistryDO;
	}

	/**
	 * @param matchCandidateLeRegistryDO
	 *            the matchCandidateLeRegistryDO to set
	 */
	public void setMatchCandidateLeRegistryDO(MatchCandidateLeRegistryDO matchCandidateLeRegistryDO) {
		this.matchCandidateLeRegistryDO = matchCandidateLeRegistryDO;
	}

	/**
	 * @return the matchMergedLeAssocDO
	 */
	public MatchMergedLeAssocDO getMatchMergedLeAssocDO() {
		return matchMergedLeAssocDO;
	}

	/**
	 * @param matchMergedLeAssocDO
	 *            the matchMergedLeAssocDO to set
	 */
	public void setMatchMergedLeAssocDO(MatchMergedLeAssocDO matchMergedLeAssocDO) {
		this.matchMergedLeAssocDO = matchMergedLeAssocDO;
	}

	/**
	 * @return the inactiveLeRegistryDOList
	 */
	public List<InactiveLeRegistryDO> getInactiveLeRegistryDOList() {
		return inactiveLeRegistryDOList;
	}

	/**
	 * @param inactiveLeRegistryDOList
	 *            the inactiveLeRegistryDOList to set
	 */
	public void setInactiveLeRegistryDOList(List<InactiveLeRegistryDO> inactiveLeRegistryDOList) {
		this.inactiveLeRegistryDOList = inactiveLeRegistryDOList;
	}

	/**
	 * @return the matchCandidateLeRegistryDOList
	 */
	public List<MatchCandidateLeRegistryDO> getMatchCandidateLeRegistryDOList() {
		return matchCandidateLeRegistryDOList;
	}

	/**
	 * @param matchCandidateLeRegistryDOList
	 *            the matchCandidateLeRegistryDOList to set
	 */
	public void setMatchCandidateLeRegistryDOList(List<MatchCandidateLeRegistryDO> matchCandidateLeRegistryDOList) {
		this.matchCandidateLeRegistryDOList = matchCandidateLeRegistryDOList;
	}

	/**
	 * @return the matchMergedLeAssocDOList
	 */
	public List<MatchMergedLeAssocDO> getMatchMergedLeAssocDOList() {
		return matchMergedLeAssocDOList;
	}

	/**
	 * @param matchMergedLeAssocDOList
	 *            the matchMergedLeAssocDOList to set
	 */
	public void setMatchMergedLeAssocDOList(List<MatchMergedLeAssocDO> matchMergedLeAssocDOList) {
		this.matchMergedLeAssocDOList = matchMergedLeAssocDOList;
	}

	/**
	 * @return the searchMatchCandidateLERequestDO
	 */
	public SearchMatchCandidateLERequestDO getSearchMatchCandidateLERequestDO() {
		return searchMatchCandidateLERequestDO;
	}

	/**
	 * @param searchMatchCandidateLERequestDO
	 *            the searchMatchCandidateLERequestDO to set
	 */
	public void setSearchMatchCandidateLERequestDO(SearchMatchCandidateLERequestDO searchMatchCandidateLERequestDO) {
		this.searchMatchCandidateLERequestDO = searchMatchCandidateLERequestDO;
	}

	/**
	 * @return the performLeMatchRequestDO
	 */
	public PerformLeMatchRequestDO getPerformLeMatchRequestDO() {
		return performLeMatchRequestDO;
	}

	/**
	 * @param performLeMatchRequestDO
	 *            the performLeMatchRequestDO to set
	 */
	public void setPerformLeMatchRequestDO(PerformLeMatchRequestDO performLeMatchRequestDO) {
		this.performLeMatchRequestDO = performLeMatchRequestDO;
	}

	/**
	 * @return the performLeMatchRequestDOList
	 */
	public List<PerformLeMatchRequestDO> getPerformLeMatchRequestDOList() {
		return performLeMatchRequestDOList;
	}

	/**
	 * @param performLeMatchRequestDOList
	 *            the performLeMatchRequestDOList to set
	 */
	public void setPerformLeMatchRequestDOList(List<PerformLeMatchRequestDO> performLeMatchRequestDOList) {
		this.performLeMatchRequestDOList = performLeMatchRequestDOList;
	}

	/**
	 * @return the batchEntityToProcessDO
	 */
	public BatchEntityToProcessDO getBatchEntityToProcessDO() {
		return batchEntityToProcessDO;
	}

	/**
	 * @param batchEntityToProcessDO the batchEntityToProcessDO to set
	 */
	public void setBatchEntityToProcessDO(BatchEntityToProcessDO batchEntityToProcessDO) {
		this.batchEntityToProcessDO = batchEntityToProcessDO;
	}

	/**
	 * @return the refBatchActionStatusDO
	 */
	public RefBatchActionStatusDO getRefBatchActionStatusDO() {
		return refBatchActionStatusDO;
	}

	/**
	 * @param refBatchActionStatusDO the refBatchActionStatusDO to set
	 */
	public void setRefBatchActionStatusDO(RefBatchActionStatusDO refBatchActionStatusDO) {
		this.refBatchActionStatusDO = refBatchActionStatusDO;
	}

	/**
	 * @return the refBatchProposedActionDO
	 */
	public RefBatchProposedActionDO getRefBatchProposedActionDO() {
		return refBatchProposedActionDO;
	}

	/**
	 * @param refBatchProposedActionDO the refBatchProposedActionDO to set
	 */
	public void setRefBatchProposedActionDO(RefBatchProposedActionDO refBatchProposedActionDO) {
		this.refBatchProposedActionDO = refBatchProposedActionDO;
	}

	/**
	 * @return the batchEntityToProcessDOList
	 */
	public BatchEntityToProcessDO getBatchEntityToProcessDOList() {
		return batchEntityToProcessDOList;
	}

	/**
	 * @param batchEntityToProcessDOList the batchEntityToProcessDOList to set
	 */
	public void setBatchEntityToProcessDOList(BatchEntityToProcessDO batchEntityToProcessDOList) {
		this.batchEntityToProcessDOList = batchEntityToProcessDOList;
	}

	/**
	 * @return the refBatchActionStatusDOList
	 */
	public List<RefBatchActionStatusDO> getRefBatchActionStatusDOList() {
		return refBatchActionStatusDOList;
	}

	/**
	 * @param refBatchActionStatusDOList the refBatchActionStatusDOList to set
	 */
	public void setRefBatchActionStatusDOList(List<RefBatchActionStatusDO> refBatchActionStatusDOList) {
		this.refBatchActionStatusDOList = refBatchActionStatusDOList;
	}

	/**
	 * @return the refBatchProposedActionDOList
	 */
	public List<RefBatchProposedActionDO> getRefBatchProposedActionDOList() {
		return refBatchProposedActionDOList;
	}

	/**
	 * @param refBatchProposedActionDOList the refBatchProposedActionDOList to set
	 */
	public void setRefBatchProposedActionDOList(List<RefBatchProposedActionDO> refBatchProposedActionDOList) {
		this.refBatchProposedActionDOList = refBatchProposedActionDOList;
	}
	
	

}
