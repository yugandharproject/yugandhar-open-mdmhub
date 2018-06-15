package com.yugandhar.mdm.abstractdobj;
/* Generated Jul 1, 2017 2:56:24 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.LePersonDO;
import com.yugandhar.mdm.extern.dobj.PersonnamesDO;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;

/**
 * Abstract DO class for LePersonDO class mapped to database LE_PERSON entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.LePersonDO
*/

@MappedSuperclass
public abstract class AbstractLePersonDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String legalentityIdpk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String personTypeRefkey;
	protected String personTypeRefValue;
	protected String genderRefkey;
	protected String genderRefValue;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date dateOfBirth;
	protected String countryOfBirthRefkey;
	protected String countryOfBirthRefValue;
	protected String countryCitizenshipRefkey;
	protected String countryCitizenshipRefValue;
	protected String countryOfDomicileRefkey;
	protected String countryOfDomicileRefValue;
	protected String maritalStatus;
	protected String highestEduQualRefkey;
	protected String highestEduQualRefValue;
	protected String isDeceasedFlag;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date deceasedDate;
	protected String isHandicappedFlag;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date handicappedSinceDate;
	protected Integer numberOfDependents;
	protected Integer numberOfChildren;
	protected String preferredLanguageRefkey;
	protected String preferredLanguageRefValue;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	//referenced entities
	protected List<PersonnamesDO> personnamesDOList;

	public AbstractLePersonDO() {
	}

	public AbstractLePersonDO(LePersonDO theLePersonDO) {
		legalentityIdpk = theLePersonDO.legalentityIdpk;
		version = theLePersonDO.version;
		createdTs = theLePersonDO.createdTs;
		deletedTs = theLePersonDO.deletedTs;
		updatedTs = theLePersonDO.updatedTs;
		updatedByUser = theLePersonDO.updatedByUser;
		updatedByTxnId = theLePersonDO.updatedByTxnId;
		personTypeRefkey = theLePersonDO.personTypeRefkey;
		genderRefkey = theLePersonDO.genderRefkey;
		dateOfBirth = theLePersonDO.dateOfBirth;
		countryOfBirthRefkey = theLePersonDO.countryOfBirthRefkey;
		countryCitizenshipRefkey = theLePersonDO.countryCitizenshipRefkey;
		countryOfDomicileRefkey = theLePersonDO.countryOfDomicileRefkey;
		maritalStatus = theLePersonDO.maritalStatus;
		highestEduQualRefkey = theLePersonDO.highestEduQualRefkey;
		isDeceasedFlag = theLePersonDO.isDeceasedFlag;
		deceasedDate = theLePersonDO.deceasedDate;
		isHandicappedFlag = theLePersonDO.isHandicappedFlag;
		handicappedSinceDate = theLePersonDO.handicappedSinceDate;
		numberOfDependents = theLePersonDO.numberOfDependents;
		numberOfChildren = theLePersonDO.numberOfChildren;
		preferredLanguageRefkey = theLePersonDO.preferredLanguageRefkey;
		inquiryFilter = theLePersonDO.inquiryFilter;
	}

	@Id

	@Column(name = "LEGALENTITY_IDPK", unique = true, nullable = false, length = 50)
	public String getLegalentityIdpk() {
		return this.legalentityIdpk;
	}

	public void setLegalentityIdpk(String legalentityIdpk) {
		this.legalentityIdpk = legalentityIdpk;
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

	@Column(name = "PERSON_TYPE_REFKEY", length = 50)
	public String getPersonTypeRefkey() {
		return this.personTypeRefkey;
	}

	public void setPersonTypeRefkey(String personTypeRefkey) {
		this.personTypeRefkey = personTypeRefkey;
	}

	@Column(name = "GENDER_REFKEY", length = 50)
	public String getGenderRefkey() {
		return this.genderRefkey;
	}

	public void setGenderRefkey(String genderRefkey) {
		this.genderRefkey = genderRefkey;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", length = 7)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "COUNTRY_OF_BIRTH__REFKEY", length = 50)
	public String getCountryOfBirthRefkey() {
		return this.countryOfBirthRefkey;
	}

	public void setCountryOfBirthRefkey(String countryOfBirthRefkey) {
		this.countryOfBirthRefkey = countryOfBirthRefkey;
	}

	@Column(name = "COUNTRY_CITIZENSHIP_REFKEY", length = 50)
	public String getCountryCitizenshipRefkey() {
		return this.countryCitizenshipRefkey;
	}

	public void setCountryCitizenshipRefkey(String countryCitizenshipRefkey) {
		this.countryCitizenshipRefkey = countryCitizenshipRefkey;
	}

	@Column(name = "COUNTRY_OF_DOMICILE__REFKEY", length = 50)
	public String getCountryOfDomicileRefkey() {
		return this.countryOfDomicileRefkey;
	}

	public void setCountryOfDomicileRefkey(String countryOfDomicileRefkey) {
		this.countryOfDomicileRefkey = countryOfDomicileRefkey;
	}

	@Column(name = "MARITAL_STATUS", length = 30)
	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Column(name = "HIGHEST_EDU_QUAL_REFKEY", length = 50)
	public String getHighestEduQualRefkey() {
		return this.highestEduQualRefkey;
	}

	public void setHighestEduQualRefkey(String highestEduQualRefkey) {
		this.highestEduQualRefkey = highestEduQualRefkey;
	}

	@Column(name = "IS_DECEASED_FLAG", length = 1)
	public String getIsDeceasedFlag() {
		return this.isDeceasedFlag;
	}

	public void setIsDeceasedFlag(String isDeceasedFlag) {
		this.isDeceasedFlag = isDeceasedFlag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DECEASED_DATE", length = 7)
	public Date getDeceasedDate() {
		return this.deceasedDate;
	}

	public void setDeceasedDate(Date deceasedDate) {
		this.deceasedDate = deceasedDate;
	}

	@Column(name = "IS_HANDICAPPED_FLAG", length = 1)
	public String getIsHandicappedFlag() {
		return this.isHandicappedFlag;
	}

	public void setIsHandicappedFlag(String isHandicappedFlag) {
		this.isHandicappedFlag = isHandicappedFlag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HANDICAPPED_SINCE_DATE", length = 7)
	public Date getHandicappedSinceDate() {
		return this.handicappedSinceDate;
	}

	public void setHandicappedSinceDate(Date handicappedSinceDate) {
		this.handicappedSinceDate = handicappedSinceDate;
	}

	@Column(name = "NUMBER_OF_DEPENDENTS", precision = 22, scale = 0)
	public Integer getNumberOfDependents() {
		return this.numberOfDependents;
	}

	public void setNumberOfDependents(Integer numberOfDependents) {
		this.numberOfDependents = numberOfDependents;
	}

	@Column(name = "NUMBER_OF_CHILDREN", precision = 22, scale = 0)
	public Integer getNumberOfChildren() {
		return this.numberOfChildren;
	}

	public void setNumberOfChildren(Integer numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	@Column(name = "PREFERRED_LANGUAGE_REFKEY", length = 50)
	public String getPreferredLanguageRefkey() {
		return this.preferredLanguageRefkey;
	}

	public void setPreferredLanguageRefkey(String preferredLanguageRefkey) {
		this.preferredLanguageRefkey = preferredLanguageRefkey;
	}

	/**
	 * @return the inquiryFilter
	 */
	@Transient
	public String getInquiryFilter() {
		return inquiryFilter;
	}

	/**
	 * @param inquiryFilter the inquiryFilter to set
	 */
	public void setInquiryFilter(String inquiryFilter) {
		this.inquiryFilter = inquiryFilter;
	}

	/**
	 * @return the personnamesDOList
	 */
	@Transient
	public List<PersonnamesDO> getPersonnamesDOList() {
		return personnamesDOList;
	}

	/**
	 * @param personnamesDOList the personnamesDOList to set
	 */
	public void setPersonnamesDOList(List<PersonnamesDO> personnamesDOList) {
		this.personnamesDOList = personnamesDOList;
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
	 * @return the personTypeRefValue
	 */
	@Transient
	public String getPersonTypeRefValue() {
		return personTypeRefValue;
	}

	/**
	 * @param personTypeRefValue the personTypeRefValue to set
	 */
	public void setPersonTypeRefValue(String personTypeRefValue) {
		this.personTypeRefValue = personTypeRefValue;
	}

	/**
	 * @return the genderRefValue
	 */
	@Transient
	public String getGenderRefValue() {
		return genderRefValue;
	}

	/**
	 * @param genderRefValue the genderRefValue to set
	 */
	public void setGenderRefValue(String genderRefValue) {
		this.genderRefValue = genderRefValue;
	}

	/**
	 * @return the countryOfBirthRefValue
	 */
	@Transient
	public String getCountryOfBirthRefValue() {
		return countryOfBirthRefValue;
	}

	/**
	 * @param countryOfBirthRefValue the countryOfBirthRefValue to set
	 */
	public void setCountryOfBirthRefValue(String countryOfBirthRefValue) {
		this.countryOfBirthRefValue = countryOfBirthRefValue;
	}

	/**
	 * @return the countryCitizenshipRefValue
	 */
	@Transient
	public String getCountryCitizenshipRefValue() {
		return countryCitizenshipRefValue;
	}

	/**
	 * @param countryCitizenshipRefValue the countryCitizenshipRefValue to set
	 */
	public void setCountryCitizenshipRefValue(String countryCitizenshipRefValue) {
		this.countryCitizenshipRefValue = countryCitizenshipRefValue;
	}

	/**
	 * @return the countryOfDomicileRefValue
	 */
	@Transient
	public String getCountryOfDomicileRefValue() {
		return countryOfDomicileRefValue;
	}

	/**
	 * @param countryOfDomicileRefValue the countryOfDomicileRefValue to set
	 */
	public void setCountryOfDomicileRefValue(String countryOfDomicileRefValue) {
		this.countryOfDomicileRefValue = countryOfDomicileRefValue;
	}

	/**
	 * @return the highestEduQualRefValue
	 */
	@Transient
	public String getHighestEduQualRefValue() {
		return highestEduQualRefValue;
	}

	/**
	 * @param highestEduQualRefValue the highestEduQualRefValue to set
	 */
	public void setHighestEduQualRefValue(String highestEduQualRefValue) {
		this.highestEduQualRefValue = highestEduQualRefValue;
	}

	/**
	 * @return the preferredLanguageRefValue
	 */
	@Transient
	public String getPreferredLanguageRefValue() {
		return preferredLanguageRefValue;
	}

	/**
	 * @param preferredLanguageRefValue the preferredLanguageRefValue to set
	 */
	public void setPreferredLanguageRefValue(String preferredLanguageRefValue) {
		this.preferredLanguageRefValue = preferredLanguageRefValue;
	}
	
	
	
}
