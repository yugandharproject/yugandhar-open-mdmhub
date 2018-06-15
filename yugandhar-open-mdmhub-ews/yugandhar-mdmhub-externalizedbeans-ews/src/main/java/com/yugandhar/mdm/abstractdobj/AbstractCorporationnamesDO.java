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
import com.yugandhar.mdm.extern.dobj.CorporationnamesDO;

/**
 * Abstract DO class for CorporationnamesDO class mapped to database CORPORATIONNAMES entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.CorporationnamesDO
*/

@MappedSuperclass
public abstract class AbstractCorporationnamesDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String legalentityIdpk;
	protected String corporationNameTypeRefkey;
	protected String corporationNameTypeRefValue;
	protected String corporationName;
	protected String sourceSystemRefkey;
	protected String sourceSystemRefValue;
	protected String phoneticCorporationName;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	

	public AbstractCorporationnamesDO() {
	}

	public AbstractCorporationnamesDO(CorporationnamesDO theCorporationnamesDO) {
		idPk = theCorporationnamesDO.idPk;
		version = theCorporationnamesDO.version;
		createdTs = theCorporationnamesDO.createdTs;
		deletedTs = theCorporationnamesDO.deletedTs;
		updatedTs = theCorporationnamesDO.updatedTs;
		updatedByUser = theCorporationnamesDO.updatedByUser;
		updatedByTxnId = theCorporationnamesDO.updatedByTxnId;
		legalentityIdpk = theCorporationnamesDO.legalentityIdpk;
		corporationNameTypeRefkey = theCorporationnamesDO.corporationNameTypeRefkey;
		corporationName = theCorporationnamesDO.corporationName;
		sourceSystemRefkey = theCorporationnamesDO.sourceSystemRefkey;
		phoneticCorporationName = theCorporationnamesDO.phoneticCorporationName;
		inquiryFilter = theCorporationnamesDO.inquiryFilter;
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

	@Column(name = "CORPORATION_NAME_TYPE_REFKEY", nullable = false, length = 50)
	public String getCorporationNameTypeRefkey() {
		return this.corporationNameTypeRefkey;
	}

	public void setCorporationNameTypeRefkey(String corporationNameTypeRefkey) {
		this.corporationNameTypeRefkey = corporationNameTypeRefkey;
	}

	@Column(name = "CORPORATION_NAME", nullable = false, length = 100)
	public String getCorporationName() {
		return this.corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	@Column(name = "SOURCE_SYSTEM_REFKEY", length = 50)
	public String getSourceSystemRefkey() {
		return this.sourceSystemRefkey;
	}

	public void setSourceSystemRefkey(String sourceSystemRefkey) {
		this.sourceSystemRefkey = sourceSystemRefkey;
	}

	@Column(name = "PHONETIC_CORPORATION_NAME", length = 100)
	public String getPhoneticCorporationName() {
		return this.phoneticCorporationName;
	}

	public void setPhoneticCorporationName(String phoneticCorporationName) {
		this.phoneticCorporationName = phoneticCorporationName;
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
	 * @return the corporationNameTypeRefValue
	 */
	@Transient
	public String getCorporationNameTypeRefValue() {
		return corporationNameTypeRefValue;
	}

	/**
	 * @param corporationNameTypeRefValue the corporationNameTypeRefValue to set
	 */
	public void setCorporationNameTypeRefValue(String corporationNameTypeRefValue) {
		this.corporationNameTypeRefValue = corporationNameTypeRefValue;
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
