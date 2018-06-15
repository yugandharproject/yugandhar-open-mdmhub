package com.yugandhar.mdm.abstractdobj;
/* Generated Jul 1, 2017 2:56:24 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.mdm.extern.dobj.PersonnamesDO;

/**
 * Abstract DO class for PersonnamesDO class mapped to database PERSONNAMES entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.PersonnamesDO
*/

@MappedSuperclass
public abstract class AbstractPersonnamesDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String legalentityIdpk;
	protected String personnameTypeRefkey;
	protected String personnameTypeRefValue;
	protected String prefixNameRefkey;
	protected String prefixNameRefValue;
	protected String prefixMisc;
	protected String nameOne;
	protected String nameTwo;
	protected String nameThree;
	protected String nameFour;
	protected String lastName;
	protected String nickName;
	protected String popularName;
	protected String suffixNameRefkey;
	protected String suffixNameRefValue;
	protected String suffixMisc;
	protected String nameStandardisedFlag;
	protected String sourceSystemRefkey;
	protected String sourceSystemRefValue;
	protected String phoneticNameOne;
	protected String phoneticNameTwo;
	protected String phoneticNameThree;
	protected String phoneticLastName;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractPersonnamesDO() {
	}

	public AbstractPersonnamesDO(PersonnamesDO thePersonnamesDO) {
		idPk = thePersonnamesDO.idPk;
		version = thePersonnamesDO.version;
		createdTs = thePersonnamesDO.createdTs;
		deletedTs = thePersonnamesDO.deletedTs;
		updatedTs = thePersonnamesDO.updatedTs;
		updatedByUser = thePersonnamesDO.updatedByUser;
		updatedByTxnId = thePersonnamesDO.updatedByTxnId;
		legalentityIdpk = thePersonnamesDO.legalentityIdpk;
		personnameTypeRefkey = thePersonnamesDO.personnameTypeRefkey;
		prefixNameRefkey = thePersonnamesDO.prefixNameRefkey;
		prefixMisc = thePersonnamesDO.prefixMisc;
		nameOne = thePersonnamesDO.nameOne;
		nameTwo = thePersonnamesDO.nameTwo;
		nameThree = thePersonnamesDO.nameThree;
		nameFour = thePersonnamesDO.nameFour;
		lastName = thePersonnamesDO.lastName;
		nickName = thePersonnamesDO.nickName;
		popularName = thePersonnamesDO.popularName;
		suffixNameRefkey = thePersonnamesDO.suffixNameRefkey;
		suffixMisc = thePersonnamesDO.suffixMisc;
		nameStandardisedFlag = thePersonnamesDO.nameStandardisedFlag;
		sourceSystemRefkey = thePersonnamesDO.sourceSystemRefkey;
		phoneticNameOne = thePersonnamesDO.phoneticNameOne;
		phoneticNameTwo = thePersonnamesDO.phoneticNameTwo;
		phoneticNameThree = thePersonnamesDO.phoneticNameThree;
		phoneticLastName = thePersonnamesDO.phoneticLastName;
		inquiryFilter = thePersonnamesDO.inquiryFilter;
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

	@Column(name = "LEGALENTITY_IDPK", nullable = false, length = 50)
	public String getLegalentityIdpk() {
		return this.legalentityIdpk;
	}

	public void setLegalentityIdpk(String legalentityIdpk) {
		this.legalentityIdpk = legalentityIdpk;
	}

	@Column(name = "PERSONNAME_TYPE_REFKEY", nullable = false, length = 50)
	public String getPersonnameTypeRefkey() {
		return this.personnameTypeRefkey;
	}

	public void setPersonnameTypeRefkey(String personnameTypeRefkey) {
		this.personnameTypeRefkey = personnameTypeRefkey;
	}

	@Column(name = "PREFIX_NAME_REFKEY", length = 50)
	public String getPrefixNameRefkey() {
		return this.prefixNameRefkey;
	}

	public void setPrefixNameRefkey(String prefixNameRefkey) {
		this.prefixNameRefkey = prefixNameRefkey;
	}

	@Column(name = "PREFIX_MISC", length = 30)
	public String getPrefixMisc() {
		return this.prefixMisc;
	}

	public void setPrefixMisc(String prefixMisc) {
		this.prefixMisc = prefixMisc;
	}

	@Column(name = "NAME_ONE", nullable = false, length = 50)
	public String getNameOne() {
		return this.nameOne;
	}

	public void setNameOne(String nameOne) {
		this.nameOne = nameOne;
	}

	@Column(name = "NAME_TWO", length = 50)
	public String getNameTwo() {
		return this.nameTwo;
	}

	public void setNameTwo(String nameTwo) {
		this.nameTwo = nameTwo;
	}

	@Column(name = "NAME_THREE", length = 50)
	public String getNameThree() {
		return this.nameThree;
	}

	public void setNameThree(String nameThree) {
		this.nameThree = nameThree;
	}

	@Column(name = "NAME_FOUR", length = 50)
	public String getNameFour() {
		return this.nameFour;
	}

	public void setNameFour(String nameFour) {
		this.nameFour = nameFour;
	}

	@Column(name = "LAST_NAME", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "NICK_NAME", length = 50)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "POPULAR_NAME", length = 50)
	public String getPopularName() {
		return this.popularName;
	}

	public void setPopularName(String popularName) {
		this.popularName = popularName;
	}

	@Column(name = "SUFFIX_NAME_REFKEY", length = 50)
	public String getSuffixNameRefkey() {
		return this.suffixNameRefkey;
	}

	public void setSuffixNameRefkey(String suffixNameRefkey) {
		this.suffixNameRefkey = suffixNameRefkey;
	}

	@Column(name = "SUFFIX_MISC", length = 30)
	public String getSuffixMisc() {
		return this.suffixMisc;
	}

	public void setSuffixMisc(String suffixMisc) {
		this.suffixMisc = suffixMisc;
	}

	@Column(name = "NAME_STANDARDISED_FLAG", length = 1)
	public String getNameStandardisedFlag() {
		return this.nameStandardisedFlag;
	}

	public void setNameStandardisedFlag(String nameStandardisedFlag) {
		this.nameStandardisedFlag = nameStandardisedFlag;
	}

	@Column(name = "SOURCE_SYSTEM_REFKEY", length = 50)
	public String getSourceSystemRefkey() {
		return this.sourceSystemRefkey;
	}

	public void setSourceSystemRefkey(String sourceSystemRefkey) {
		this.sourceSystemRefkey = sourceSystemRefkey;
	}

	@Column(name = "PHONETIC_NAME_ONE", length = 50)
	public String getPhoneticNameOne() {
		return this.phoneticNameOne;
	}

	public void setPhoneticNameOne(String phoneticNameOne) {
		this.phoneticNameOne = phoneticNameOne;
	}

	@Column(name = "PHONETIC_NAME_TWO", length = 50)
	public String getPhoneticNameTwo() {
		return this.phoneticNameTwo;
	}

	public void setPhoneticNameTwo(String phoneticNameTwo) {
		this.phoneticNameTwo = phoneticNameTwo;
	}

	@Column(name = "PHONETIC_NAME_THREE", length = 50)
	public String getPhoneticNameThree() {
		return this.phoneticNameThree;
	}

	public void setPhoneticNameThree(String phoneticNameThree) {
		this.phoneticNameThree = phoneticNameThree;
	}

	@Column(name = "PHONETIC_LAST_NAME", length = 50)
	public String getPhoneticLastName() {
		return this.phoneticLastName;
	}

	public void setPhoneticLastName(String phoneticLastName) {
		this.phoneticLastName = phoneticLastName;
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
	 * @return the personnameTypeRefValue
	 */
	@Transient
	public String getPersonnameTypeRefValue() {
		return personnameTypeRefValue;
	}

	/**
	 * @param personnameTypeRefValue the personnameTypeRefValue to set
	 */
	public void setPersonnameTypeRefValue(String personnameTypeRefValue) {
		this.personnameTypeRefValue = personnameTypeRefValue;
	}

	/**
	 * @return the prefixNameRefValue
	 */
	@Transient
	public String getPrefixNameRefValue() {
		return prefixNameRefValue;
	}

	/**
	 * @param prefixNameRefValue the prefixNameRefValue to set
	 */
	public void setPrefixNameRefValue(String prefixNameRefValue) {
		this.prefixNameRefValue = prefixNameRefValue;
	}

	/**
	 * @return the suffixNameRefValue
	 */
	@Transient
	public String getSuffixNameRefValue() {
		return suffixNameRefValue;
	}

	/**
	 * @param suffixNameRefValue the suffixNameRefValue to set
	 */
	public void setSuffixNameRefValue(String suffixNameRefValue) {
		this.suffixNameRefValue = suffixNameRefValue;
	}

	/**
	 * @return the sourceSystemRefValue
	 */
	@Transient
	public String getSourceSystemRefValue() {
		return sourceSystemRefValue;
	}

	/**
	 * @param sourceSystemRefValue the sourceSystemRefValue to set
	 */
	public void setSourceSystemRefValue(String sourceSystemRefValue) {
		this.sourceSystemRefValue = sourceSystemRefValue;
	}
	
	
	
}
