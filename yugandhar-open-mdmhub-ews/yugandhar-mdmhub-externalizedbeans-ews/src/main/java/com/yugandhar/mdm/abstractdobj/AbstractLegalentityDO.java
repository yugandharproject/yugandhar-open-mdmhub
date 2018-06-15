package com.yugandhar.mdm.abstractdobj;
/* Generated Jun 28, 2017 11:57:37 AM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import com.yugandhar.mdm.extern.dobj.MiscellaneousInfoDO;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;

/**
 * Abstract DO class for LegalentityDO class mapped to database LEGALENTITY
 * entity
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * @see com.yugandhar.mdm.extern.dobj.LegalentityDO
 */

@MappedSuperclass
public abstract class AbstractLegalentityDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public AbstractLegalentityDO() {
	}

	public AbstractLegalentityDO(LegalentityDO theLegalentityDO) {
		idPk = theLegalentityDO.idPk;
		version = theLegalentityDO.version;
		createdTs = theLegalentityDO.createdTs;
		deletedTs = theLegalentityDO.deletedTs;
		updatedTs = theLegalentityDO.updatedTs;
		updatedByUser = theLegalentityDO.updatedByUser;
		updatedByTxnId = theLegalentityDO.updatedByTxnId;
		displayName = theLegalentityDO.displayName;
		entityObjectTypeRefkey = theLegalentityDO.entityObjectTypeRefkey;
		classificationCodeRefkey = theLegalentityDO.classificationCodeRefkey;
		importanceTypeRefkey = theLegalentityDO.importanceTypeRefkey;
		leRatingRefkey = theLegalentityDO.leRatingRefkey;
		statusTypeRefkey = theLegalentityDO.statusTypeRefkey;
		sourceSystemRefkey = theLegalentityDO.sourceSystemRefkey;
		onboardingDate = theLegalentityDO.onboardingDate;
		offboardingDate = theLegalentityDO.offboardingDate;
		kycVerificationFlag = theLegalentityDO.kycVerificationFlag;
		description = theLegalentityDO.description;
		phoneticDisplayName = theLegalentityDO.phoneticDisplayName;
		inquiryFilter = theLegalentityDO.inquiryFilter;
	}

	@Id

	@Column(name = "ID_PK", unique = true, nullable = false, length = 50)
	public String getIdPk() {
		return this.idPk;
	}

	public void setIdPk(String idPk) {
		this.idPk = idPk;
	}

	@Version
	@Column(name = "VERSION", nullable = false, precision = 22, scale = 0)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TS", nullable = false)
	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELETED_TS")
	public Date getDeletedTs() {
		return this.deletedTs;
	}

	public void setDeletedTs(Date deletedTs) {
		this.deletedTs = deletedTs;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_TS", nullable = false)
	public Date getUpdatedTs() {
		return this.updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	@Column(name = "UPDATED_BY_USER", nullable = false, length = 50)
	public String getUpdatedByUser() {
		return this.updatedByUser;
	}

	public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	@Column(name = "UPDATED_BY_TXN_ID", length = 100)
	public String getUpdatedByTxnId() {
		return this.updatedByTxnId;
	}

	public void setUpdatedByTxnId(String updatedByTxnId) {
		this.updatedByTxnId = updatedByTxnId;
	}

	@Column(name = "DISPLAY_NAME", nullable = false, length = 100)
	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "ENTITY_OBJECT_TYPE_REFKEY", nullable = false, length = 50)
	public String getEntityObjectTypeRefkey() {
		return this.entityObjectTypeRefkey;
	}

	public void setEntityObjectTypeRefkey(String entityObjectTypeRefkey) {
		this.entityObjectTypeRefkey = entityObjectTypeRefkey;
	}

	@Column(name = "CLASSIFICATION_CODE_REFKEY", length = 50)
	public String getClassificationCodeRefkey() {
		return this.classificationCodeRefkey;
	}

	public void setClassificationCodeRefkey(String classificationCodeRefkey) {
		this.classificationCodeRefkey = classificationCodeRefkey;
	}

	@Column(name = "IMPORTANCE_TYPE_REFKEY", length = 50)
	public String getImportanceTypeRefkey() {
		return this.importanceTypeRefkey;
	}

	public void setImportanceTypeRefkey(String importanceTypeRefkey) {
		this.importanceTypeRefkey = importanceTypeRefkey;
	}

	@Column(name = "LE_RATING_REFKEY", length = 50)
	public String getLeRatingRefkey() {
		return this.leRatingRefkey;
	}

	public void setLeRatingRefkey(String leRatingRefkey) {
		this.leRatingRefkey = leRatingRefkey;
	}

	@Column(name = "STATUS_TYPE_REFKEY", length = 50)
	public String getStatusTypeRefkey() {
		return this.statusTypeRefkey;
	}

	public void setStatusTypeRefkey(String statusTypeRefkey) {
		this.statusTypeRefkey = statusTypeRefkey;
	}

	@Column(name = "SOURCE_SYSTEM_REFKEY", length = 50)
	public String getSourceSystemRefkey() {
		return this.sourceSystemRefkey;
	}

	public void setSourceSystemRefkey(String sourceSystemRefkey) {
		this.sourceSystemRefkey = sourceSystemRefkey;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ONBOARDING_DATE", length = 7)
	public Date getOnboardingDate() {
		return this.onboardingDate;
	}

	public void setOnboardingDate(Date onboardingDate) {
		this.onboardingDate = onboardingDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OFFBOARDING_DATE", length = 7)
	public Date getOffboardingDate() {
		return this.offboardingDate;
	}

	public void setOffboardingDate(Date offboardingDate) {
		this.offboardingDate = offboardingDate;
	}

	@Column(name = "KYC_VERIFICATION_FLAG", length = 1)
	public String getKycVerificationFlag() {
		return this.kycVerificationFlag;
	}

	public void setKycVerificationFlag(String kycVerificationFlag) {
		this.kycVerificationFlag = kycVerificationFlag;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PHONETIC_DISPLAY_NAME", length = 100)
	public String getPhoneticDisplayName() {
		return this.phoneticDisplayName;
	}

	public void setPhoneticDisplayName(String phoneticDisplayName) {
		this.phoneticDisplayName = phoneticDisplayName;
	}

	/**
	 * @return the inquiryFilter
	 */
	@Transient
	public String getInquiryFilter() {
		return inquiryFilter;
	}

	/**
	 * @param inquiryFilter
	 *            the inquiryFilter to set
	 */
	public void setInquiryFilter(String inquiryFilter) {
		this.inquiryFilter = inquiryFilter;
	}

	/**
	 * @return the reactivationFlag
	 */
	@Transient
	public String getReactivationFlag() {
		return reactivationFlag;
	}

	/**
	 * @param reactivationFlag
	 *            the reactivationFlag to set
	 */
	public void setReactivationFlag(String reactivationFlag) {
		this.reactivationFlag = reactivationFlag;
	}

	/**
	 * @return the entityObjectTypeRefValue
	 */
	@Transient
	public String getEntityObjectTypeRefValue() {
		return entityObjectTypeRefValue;
	}

	/**
	 * @param entityObjectTypeRefValue
	 *            the entityObjectTypeRefValue to set
	 */
	public void setEntityObjectTypeRefValue(String entityObjectTypeRefValue) {
		this.entityObjectTypeRefValue = entityObjectTypeRefValue;
	}

	/**
	 * @return the classificationCodeRefValue
	 */
	@Transient
	public String getClassificationCodeRefValue() {
		return classificationCodeRefValue;
	}

	/**
	 * @param classificationCodeRefValue
	 *            the classificationCodeRefValue to set
	 */
	public void setClassificationCodeRefValue(String classificationCodeRefValue) {
		this.classificationCodeRefValue = classificationCodeRefValue;
	}

	/**
	 * @return the importanceTypeRefValue
	 */
	@Transient
	public String getImportanceTypeRefValue() {
		return importanceTypeRefValue;
	}

	/**
	 * @param importanceTypeRefValue
	 *            the importanceTypeRefValue to set
	 */
	public void setImportanceTypeRefValue(String importanceTypeRefValue) {
		this.importanceTypeRefValue = importanceTypeRefValue;
	}

	/**
	 * @return the leRatingRefValue
	 */
	@Transient
	public String getLeRatingRefValue() {
		return leRatingRefValue;
	}

	/**
	 * @param leRatingRefValue
	 *            the leRatingRefValue to set
	 */
	public void setLeRatingRefValue(String leRatingRefValue) {
		this.leRatingRefValue = leRatingRefValue;
	}

	/**
	 * @return the statusTypeRefValue
	 */
	@Transient
	public String getStatusTypeRefValue() {
		return statusTypeRefValue;
	}

	/**
	 * @param statusTypeRefValue
	 *            the statusTypeRefValue to set
	 */
	public void setStatusTypeRefValue(String statusTypeRefValue) {
		this.statusTypeRefValue = statusTypeRefValue;
	}

	/**
	 * @return the sourceSystemRefValue
	 */
	@Transient
	public String getSourceSystemRefValue() {
		return sourceSystemRefValue;
	}

	/**
	 * @param sourceSystemRefValue
	 *            the sourceSystemRefValue to set
	 */
	public void setSourceSystemRefValue(String sourceSystemRefValue) {
		this.sourceSystemRefValue = sourceSystemRefValue;
	}

	/**
	 * @return the lePersonDO
	 */
	@Transient
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
	 * @return the leCorporationDO
	 */
	@Transient
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
	 * @return the leAddressAssocDOList
	 */
	@Transient
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
	 * @return the lePhoneAssocDOList
	 */
	@Transient
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
	 * @return the leSystemKeysRegistryDOList
	 */
	@Transient
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
	 * @return the lePreferencesDOList
	 */
	@Transient
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
	 * @return the leIdentifierKycRegistryDOList
	 */
	@Transient
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
	 * @return the leAccountAssocDOList
	 */
	@Transient
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
	 * @return the lePropertyAssocDOList
	 */
	@Transient
	public List<LePropertyAssocDO> getLePropertyAssocDOList() {
		return lePropertyAssocDOList;
	}

	/**
	 * @param lePropertyAssocDOList the lePropertyAssocDOList to set
	 */
	public void setLePropertyAssocDOList(List<LePropertyAssocDO> lePropertyAssocDOList) {
		this.lePropertyAssocDOList = lePropertyAssocDOList;
	}



	/**
	 * @return the leVehicleAssocDOList
	 */
	@Transient
	public List<LeVehicleAssocDO> getLeVehicleAssocDOList() {
		return leVehicleAssocDOList;
	}

	/**
	 * @param leVehicleAssocDOList the leVehicleAssocDOList to set
	 */
	public void setLeVehicleAssocDOList(List<LeVehicleAssocDO> leVehicleAssocDOList) {
		this.leVehicleAssocDOList = leVehicleAssocDOList;
	}



	/**
	 * @return the miscellaneousInfoDOList
	 */
	@Transient
	public List<MiscellaneousInfoDO> getMiscellaneousInfoDOList() {
		return miscellaneousInfoDOList;
	}

	/**
	 * @param miscellaneousInfoDOList the miscellaneousInfoDOList to set
	 */
	public void setMiscellaneousInfoDOList(List<MiscellaneousInfoDO> miscellaneousInfoDOList) {
		this.miscellaneousInfoDOList = miscellaneousInfoDOList;
	}



	/**
	 * @return the leToLeRelationshipDOList
	 */
	@Transient
	public List<LeToLeRelationshipDO> getLeToLeRelationshipDOList() {
		return leToLeRelationshipDOList;
	}

	/**
	 * @param leToLeRelationshipDOList the leToLeRelationshipDOList to set
	 */
	public void setLeToLeRelationshipDOList(List<LeToLeRelationshipDO> leToLeRelationshipDOList) {
		this.leToLeRelationshipDOList = leToLeRelationshipDOList;
	}



	/**
	 * @return the primaryKeyDO
	 */
	@Transient
	public PrimaryKeyDO getPrimaryKeyDO() {
		return primaryKeyDO;
	}

	/**
	 * @param primaryKeyDO the primaryKeyDO to set
	 */
	public void setPrimaryKeyDO(PrimaryKeyDO primaryKeyDO) {
		this.primaryKeyDO = primaryKeyDO;
	}



	/**
	 * @return the inquiryLevel
	 */
	@Transient
	public String getInquiryLevel() {
		return inquiryLevel;
	}

	/**
	 * @param inquiryLevel the inquiryLevel to set
	 */
	public void setInquiryLevel(String inquiryLevel) {
		this.inquiryLevel = inquiryLevel;
	}



	/**
	 * @return the accountInquiryLevel
	 */
	@Transient
	public String getAccountInquiryLevel() {
		return accountInquiryLevel;
	}

	/**
	 * @param accountInquiryLevel the accountInquiryLevel to set
	 */
	public void setAccountInquiryLevel(String accountInquiryLevel) {
		this.accountInquiryLevel = accountInquiryLevel;
	}



	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String displayName;
	protected String entityObjectTypeRefkey;
	protected String entityObjectTypeRefValue;
	protected String classificationCodeRefkey;
	protected String classificationCodeRefValue;
	protected String importanceTypeRefkey;
	protected String importanceTypeRefValue;
	protected String leRatingRefkey;
	protected String leRatingRefValue;
	protected String statusTypeRefkey;
	protected String statusTypeRefValue;
	protected String sourceSystemRefkey;
	protected String sourceSystemRefValue;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date onboardingDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date offboardingDate;
	protected String kycVerificationFlag;
	protected String description;
	protected String phoneticDisplayName;
	protected String inquiryFilter;
	protected String reactivationFlag;
	protected PrimaryKeyDO primaryKeyDO;
	protected String inquiryLevel;
	protected String accountInquiryLevel;
	
	// Referenced entities
	protected LePersonDO lePersonDO;
	protected LeCorporationDO leCorporationDO;
	protected List<LeAddressAssocDO> leAddressAssocDOList;
	protected List<LePhoneAssocDO> lePhoneAssocDOList;
	protected List<LeSystemKeysRegistryDO> leSystemKeysRegistryDOList;
	protected List<LePreferencesDO> lePreferencesDOList;
	protected List<LeIdentifierKycRegistryDO> leIdentifierKycRegistryDOList;
	protected List<LeAccountAssocDO> leAccountAssocDOList;
	protected List<LePropertyAssocDO> lePropertyAssocDOList;
	protected List<LeVehicleAssocDO> leVehicleAssocDOList;
	protected List<MiscellaneousInfoDO> miscellaneousInfoDOList;
	protected List<LeToLeRelationshipDO> leToLeRelationshipDOList;

}
