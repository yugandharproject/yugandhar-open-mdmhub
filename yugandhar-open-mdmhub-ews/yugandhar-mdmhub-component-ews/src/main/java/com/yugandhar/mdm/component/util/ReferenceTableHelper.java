package com.yugandhar.mdm.component.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.mdm.batch.componentref.RefBatchActionStatusComponent;
import com.yugandhar.mdm.batch.componentref.RefBatchProposedActionComponent;
import com.yugandhar.mdm.corecomponentref.RefAccountMdmStatusComponent;
import com.yugandhar.mdm.corecomponentref.RefAccountSourceStatusComponent;
import com.yugandhar.mdm.corecomponentref.RefAddressSubtypeComponent;
import com.yugandhar.mdm.corecomponentref.RefAddressTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefAgreementTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefAssocTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefBillingModeTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefBranchCodeComponent;
import com.yugandhar.mdm.corecomponentref.RefClassificationCodeComponent;
import com.yugandhar.mdm.corecomponentref.RefCorporationNameTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefCorporationTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefCountryIsoComponent;
import com.yugandhar.mdm.corecomponentref.RefCurrencyComponent;
import com.yugandhar.mdm.corecomponentref.RefDeactivationReasonComponent;
import com.yugandhar.mdm.corecomponentref.RefEntityObjectTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefGenderComponent;
import com.yugandhar.mdm.corecomponentref.RefGroupSubtypeComponent;
import com.yugandhar.mdm.corecomponentref.RefGroupTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefHighestEduQualComponent;
import com.yugandhar.mdm.corecomponentref.RefIdentificationTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefImportanceTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefIndustryCodeComponent;
import com.yugandhar.mdm.corecomponentref.RefLanguageCodeComponent;
import com.yugandhar.mdm.corecomponentref.RefLeRatingComponent;
import com.yugandhar.mdm.corecomponentref.RefLeRelationshipTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefLeRoletypeComponent;
import com.yugandhar.mdm.corecomponentref.RefLobtypeComponent;
import com.yugandhar.mdm.corecomponentref.RefPersonTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefPersonnameTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefPhoneSubtypeComponent;
import com.yugandhar.mdm.corecomponentref.RefPhoneTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefPreferenceTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefPrefixNameComponent;
import com.yugandhar.mdm.corecomponentref.RefPropertyLeReltypeComponent;
import com.yugandhar.mdm.corecomponentref.RefRelationshipStatusComponent;
import com.yugandhar.mdm.corecomponentref.RefSourceSystemComponent;
import com.yugandhar.mdm.corecomponentref.RefStateProvinceComponent;
import com.yugandhar.mdm.corecomponentref.RefStatusInSourceComponent;
import com.yugandhar.mdm.corecomponentref.RefStatusTypeComponent;
import com.yugandhar.mdm.corecomponentref.RefSuffixNameComponent;
import com.yugandhar.mdm.corecomponentref.RefTerminationReasonComponent;
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
import com.yugandhar.mdm.match.componentref.RefInactivationReasonComponent;
import com.yugandhar.mdm.match.componentref.RefMatchActionstatusComponent;
import com.yugandhar.mdm.match.componentref.RefMatchProposedActionComponent;
import com.yugandhar.mdm.match.componentref.RefMatchResultComponent;
import com.yugandhar.mdm.match.componentref.RefMergeReasonComponent;

@Scope(value = "singleton")
@Component
public class ReferenceTableHelper {

	@Autowired
	RefAccountMdmStatusComponent theRefAccountMdmStatusComponent;

	@Autowired
	RefAccountSourceStatusComponent theRefAccountSourceStatusComponent;

	@Autowired
	RefAddressSubtypeComponent theRefAddressSubtypeComponent;

	@Autowired
	RefAddressTypeComponent theRefAddressTypeComponent;

	@Autowired
	RefAgreementTypeComponent theRefAgreementTypeComponent;

	@Autowired
	RefAssocTypeComponent theRefAssocTypeComponent;

	@Autowired
	RefBillingModeTypeComponent theRefBillingModeTypeComponent;

	@Autowired
	RefBranchCodeComponent theRefBranchCodeComponent;

	@Autowired
	RefClassificationCodeComponent theRefClassificationCodeComponent;

	@Autowired
	RefCorporationNameTypeComponent theRefCorporationNameTypeComponent;

	@Autowired
	RefCorporationTypeComponent theRefCorporationTypeComponent;

	@Autowired
	RefCountryIsoComponent theRefCountryIsoComponent;

	@Autowired
	RefCurrencyComponent theRefCurrencyComponent;

	@Autowired
	RefDeactivationReasonComponent theRefDeactivationReasonComponent;

	@Autowired
	RefEntityObjectTypeComponent theRefEntityObjectTypeComponent;

	@Autowired
	RefGenderComponent theRefGenderComponent;

	@Autowired
	RefGroupSubtypeComponent theRefGroupSubtypeComponent;

	@Autowired
	RefGroupTypeComponent theRefGroupTypeComponent;

	@Autowired
	RefHighestEduQualComponent theRefHighestEduQualComponent;

	@Autowired
	RefIdentificationTypeComponent theRefIdentificationTypeComponent;

	@Autowired
	RefImportanceTypeComponent theRefImportanceTypeComponent;

	@Autowired
	RefIndustryCodeComponent theRefIndustryCodeComponent;

	@Autowired
	RefLanguageCodeComponent theRefLanguageCodeComponent;

	@Autowired
	RefLeRatingComponent theRefLeRatingComponent;

	@Autowired
	RefLeRelationshipTypeComponent theRefLeRelationshipTypeComponent;

	@Autowired
	RefLeRoletypeComponent theRefLeRoletypeComponent;

	@Autowired
	RefLobtypeComponent theRefLobtypeComponent;

	@Autowired
	RefPersonnameTypeComponent theRefPersonnameTypeComponent;

	@Autowired
	RefPersonTypeComponent theRefPersonTypeComponent;

	@Autowired
	RefPhoneSubtypeComponent theRefPhoneSubtypeComponent;

	@Autowired
	RefPhoneTypeComponent theRefPhoneTypeComponent;

	@Autowired
	RefPrefixNameComponent theRefPrefixNameComponent;

	@Autowired
	RefPreferenceTypeComponent theRefPreferenceTypeComponent;

	@Autowired
	RefPropertyLeReltypeComponent theRefPropertyLeReltypeComponent;

	@Autowired
	RefRelationshipStatusComponent theRefRelationshipStatusComponent;

	@Autowired
	RefSourceSystemComponent theRefSourceSystemComponent;

	@Autowired
	RefStateProvinceComponent theRefStateProvinceComponent;

	@Autowired
	RefStatusInSourceComponent theRefStatusInSourceComponent;

	@Autowired
	RefStatusTypeComponent theRefStatusTypeComponent;

	@Autowired
	RefSuffixNameComponent theRefSuffixNameComponent;

	@Autowired
	RefTerminationReasonComponent theRefTerminationReasonComponent;

	@Autowired
	RefMatchResultComponent theRefMatchResultComponent;

	@Autowired
	RefMatchProposedActionComponent theRefMatchProposedActionComponent;

	@Autowired
	RefInactivationReasonComponent theRefInactivationReasonComponent;

	@Autowired
	RefMatchActionstatusComponent theRefMatchActionstatusComponent;

	@Autowired
	RefMergeReasonComponent theRefMergeReasonComponent;

	@Autowired
	RefBatchProposedActionComponent theRefBatchProposedActionComponent;

	@Autowired
	RefBatchActionStatusComponent theRefBatchActionStatusComponent;
	/*
	 * @Autowired ConfigLanguageCodeComponent theConfigLanguageCodeComponent;
	 * 
	 * 
	 * public ConfigLanguageCodeDO getConfigLanguageCodeValueByKey(String
	 * Refkey, String filterValue) { return
	 * theConfigLanguageCodeComponent.executeRepositoryQuery(Refkey,
	 * filterValue); }
	 */

	public RefLanguageCodeDO getRefLanguageCodeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefLanguageCodeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefAccountMdmStatusDO getRefAccountMdmStatusValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefAccountMdmStatusComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefAccountSourceStatusDO getRefAccountSourceStatusValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefAccountSourceStatusComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefAddressSubtypeDO getRefAddressSubtypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefAddressSubtypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefAddressTypeDO getRefAddressTypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefAddressTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefAgreementTypeDO getRefAgreementTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefAgreementTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefAssocTypeDO getRefAssocTypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefAssocTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefBillingModeTypeDO getRefBillingModeTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefBillingModeTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefBranchCodeDO getRefBranchCodeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefBranchCodeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefClassificationCodeDO getRefClassificationCodeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefClassificationCodeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefCorporationNameTypeDO getRefCorporationNameTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefCorporationNameTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefCorporationTypeDO getRefCorporationTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefCorporationTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefCountryIsoDO getRefCountryIsoValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefCountryIsoComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefCurrencyDO getRefCurrencyValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefCurrencyComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefDeactivationReasonDO getRefDeactivationReasonValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefDeactivationReasonComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefEntityObjectTypeDO getRefEntityObjectTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefEntityObjectTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefGenderDO getRefGenderValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefGenderComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefGroupSubtypeDO getRefGroupSubtypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefGroupSubtypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefGroupTypeDO getRefGroupTypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefGroupTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefHighestEduQualDO getRefHighestEduQualValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefHighestEduQualComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefIdentificationTypeDO getRefIdentificationTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefIdentificationTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefImportanceTypeDO getRefImportanceTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefImportanceTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefIndustryCodeDO getRefIndustryCodeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefIndustryCodeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefLeRatingDO getRefLeRatingValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefLeRatingComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefLeRelationshipTypeDO getRefLeRelationshipTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefLeRelationshipTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefLeRoletypeDO getRefLeRoletypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefLeRoletypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefLobtypeDO getRefLobtypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefLobtypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefPersonnameTypeDO getRefPersonnameTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefPersonnameTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefPersonTypeDO getRefPersonTypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefPersonTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefPhoneSubtypeDO getRefPhoneSubtypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefPhoneSubtypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefPhoneTypeDO getRefPhoneTypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefPhoneTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefPrefixNameDO getRefPrefixNameValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefPrefixNameComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefPreferenceTypeDO getRefPreferenceTypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefPreferenceTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefPropertyLeReltypeDO getRefPropertyLeReltypeValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefPropertyLeReltypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefRelationshipStatusDO getRefRelationshipStatusValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefRelationshipStatusComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefSourceSystemDO getRefSourceSystemValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefSourceSystemComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefStateProvinceDO getRefStateProvinceValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefStateProvinceComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefStatusInSourceDO getRefStatusInSourceValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefStatusInSourceComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefStatusTypeDO getRefStatusTypeValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefStatusTypeComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefSuffixNameDO getRefSuffixNameValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefSuffixNameComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefTerminationReasonDO getRefTerminationReasonValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefTerminationReasonComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefMatchResultDO getRefMatchResultValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefMatchResultComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefMatchProposedActionDO getRefMatchProposedActionValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefMatchProposedActionComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefInactivationReasonDO getRefInactivationReasonValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefInactivationReasonComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefMatchActionstatusDO getRefMatchActionstatusValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefMatchActionstatusComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefMergeReasonDO getRefMergeReasonValueByKey(String requesterLanguage, String Refkey, String filterValue) {
		return theRefMergeReasonComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefBatchProposedActionDO getRefBatchProposedActionValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefBatchProposedActionComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

	public RefBatchActionStatusDO getRefBatchActionStatusValueByKey(String requesterLanguage, String Refkey,
			String filterValue) {
		return theRefBatchActionStatusComponent.executeRepositoryQuery(requesterLanguage, Refkey, filterValue);
	}

}
