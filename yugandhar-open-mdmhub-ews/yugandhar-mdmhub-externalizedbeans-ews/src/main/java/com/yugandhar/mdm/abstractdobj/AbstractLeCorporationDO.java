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
import com.yugandhar.mdm.extern.dobj.CorporationnamesDO;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.mdm.extern.dobj.LeCorporationDO;

/**
 * Abstract DO class for LeCorporationDO class mapped to database LE_CORPORATION entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.LeCorporationDO
*/

@MappedSuperclass
public abstract class AbstractLeCorporationDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String legalentityIdpk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String classificationCodeRefkey;
	protected String classificationCodeRefValue;
	protected String industryCodeRefkey;
	protected String industryCodeRefValue;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date govtRegistrationDate;
	protected String countryRegistrationRefkey;
	protected String countryRegistrationRefValue;
	protected String notprofitFlag;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	//referred
	protected List<CorporationnamesDO> corporationnamesDOList;

	public AbstractLeCorporationDO() {
	}

	public AbstractLeCorporationDO(LeCorporationDO theLeCorporationDO) {
		legalentityIdpk = theLeCorporationDO.legalentityIdpk;
		version = theLeCorporationDO.version;
		createdTs = theLeCorporationDO.createdTs;
		deletedTs = theLeCorporationDO.deletedTs;
		updatedTs = theLeCorporationDO.updatedTs;
		updatedByUser = theLeCorporationDO.updatedByUser;
		updatedByTxnId = theLeCorporationDO.updatedByTxnId;
		classificationCodeRefkey = theLeCorporationDO.classificationCodeRefkey;
		industryCodeRefkey = theLeCorporationDO.industryCodeRefkey;
		govtRegistrationDate = theLeCorporationDO.govtRegistrationDate;
		countryRegistrationRefkey = theLeCorporationDO.countryRegistrationRefkey;
		notprofitFlag = theLeCorporationDO.notprofitFlag;
		inquiryFilter = theLeCorporationDO.inquiryFilter;
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

	@Column(name = "CLASSIFICATION_CODE_REFKEY", length = 50)
	public String getClassificationCodeRefkey() {
		return this.classificationCodeRefkey;
	}

	public void setClassificationCodeRefkey(String classificationCodeRefkey) {
		this.classificationCodeRefkey = classificationCodeRefkey;
	}

	@Column(name = "INDUSTRY_CODE_REFKEY", length = 50)
	public String getIndustryCodeRefkey() {
		return this.industryCodeRefkey;
	}

	public void setIndustryCodeRefkey(String industryCodeRefkey) {
		this.industryCodeRefkey = industryCodeRefkey;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GOVT_REGISTRATION_DATE", length = 7)
	public Date getGovtRegistrationDate() {
		return this.govtRegistrationDate;
	}

	public void setGovtRegistrationDate(Date govtRegistrationDate) {
		this.govtRegistrationDate = govtRegistrationDate;
	}

	@Column(name = "COUNTRY_REGISTRATION_REFKEY", length = 50)
	public String getCountryRegistrationRefkey() {
		return this.countryRegistrationRefkey;
	}

	public void setCountryRegistrationRefkey(String countryRegistrationRefkey) {
		this.countryRegistrationRefkey = countryRegistrationRefkey;
	}

	@Column(name = "NOTPROFIT_FLAG", length = 1)
	public String getNotprofitFlag() {
		return this.notprofitFlag;
	}

	public void setNotprofitFlag(String notprofitFlag) {
		this.notprofitFlag = notprofitFlag;
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
	 * @return the corporationnamesDOList
	 */
	@Transient
	public List<CorporationnamesDO> getCorporationnamesDOList() {
		return corporationnamesDOList;
	}

	/**
	 * @param corporationnamesDOList the corporationnamesDOList to set
	 */
	public void setCorporationnamesDOList(List<CorporationnamesDO> corporationnamesDOList) {
		this.corporationnamesDOList = corporationnamesDOList;
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
	 * @return the classificationCodeRefValue
	 */
	@Transient
	public String getClassificationCodeRefValue() {
		return classificationCodeRefValue;
	}

	/**
	 * @param classificationCodeRefValue the classificationCodeRefValue to set
	 */
	public void setClassificationCodeRefValue(String classificationCodeRefValue) {
		this.classificationCodeRefValue = classificationCodeRefValue;
	}

	/**
	 * @return the industryCodeRefValue
	 */
	@Transient
	public String getIndustryCodeRefValue() {
		return industryCodeRefValue;
	}

	/**
	 * @param industryCodeRefValue the industryCodeRefValue to set
	 */
	public void setIndustryCodeRefValue(String industryCodeRefValue) {
		this.industryCodeRefValue = industryCodeRefValue;
	}

	/**
	 * @return the countryRegistrationRefValue
	 */
	@Transient
	public String getCountryRegistrationRefValue() {
		return countryRegistrationRefValue;
	}

	/**
	 * @param countryRegistrationRefValue the countryRegistrationRefValue to set
	 */
	public void setCountryRegistrationRefValue(String countryRegistrationRefValue) {
		this.countryRegistrationRefValue = countryRegistrationRefValue;
	}
	
	

}
