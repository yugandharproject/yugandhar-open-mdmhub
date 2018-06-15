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
import com.yugandhar.mdm.extern.dobj.LeSystemKeysRegistryDO;

/**
 * Abstract DO class for LeSystemKeysRegistryDO class mapped to database LE_SYSTEM_KEYS_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.LeSystemKeysRegistryDO
*/

@MappedSuperclass
public abstract class AbstractLeSystemKeysRegistryDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String sourceSystemRefkey;
	protected String sourceSystemRefValue;
	protected String referenceId;
	protected String legalentityIdpk;
	protected String statusInSourceRefkey;
	protected String statusInSourceRefValue;
	protected String description;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractLeSystemKeysRegistryDO() {
	}

	public AbstractLeSystemKeysRegistryDO(LeSystemKeysRegistryDO theLeSystemKeysRegistryDO) {
		idPk = theLeSystemKeysRegistryDO.idPk;
		version = theLeSystemKeysRegistryDO.version;
		createdTs = theLeSystemKeysRegistryDO.createdTs;
		deletedTs = theLeSystemKeysRegistryDO.deletedTs;
		updatedTs = theLeSystemKeysRegistryDO.updatedTs;
		updatedByUser = theLeSystemKeysRegistryDO.updatedByUser;
		updatedByTxnId = theLeSystemKeysRegistryDO.updatedByTxnId;
		sourceSystemRefkey = theLeSystemKeysRegistryDO.sourceSystemRefkey;
		referenceId = theLeSystemKeysRegistryDO.referenceId;
		legalentityIdpk = theLeSystemKeysRegistryDO.legalentityIdpk;
		statusInSourceRefkey = theLeSystemKeysRegistryDO.statusInSourceRefkey;
		description = theLeSystemKeysRegistryDO.description;
		inquiryFilter = theLeSystemKeysRegistryDO.inquiryFilter;
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

	@Column(name = "SOURCE_SYSTEM_REFKEY", nullable = false, length = 50)
	public String getSourceSystemRefkey() {
		return this.sourceSystemRefkey;
	}

	public void setSourceSystemRefkey(String sourceSystemRefkey) {
		this.sourceSystemRefkey = sourceSystemRefkey;
	}

	@Column(name = "REFERENCE_ID", nullable = false, length = 50)
	public String getReferenceId() {
		return this.referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	@Column(name = "LEGALENTITY_IDPK", nullable = false, length = 50)
	public String getLegalentityIdpk() {
		return this.legalentityIdpk;
	}

	public void setLegalentityIdpk(String legalentityIdpk) {
		this.legalentityIdpk = legalentityIdpk;
	}

	@Column(name = "STATUS_IN_SOURCE_REFKEY", length = 50)
	public String getStatusInSourceRefkey() {
		return this.statusInSourceRefkey;
	}

	public void setStatusInSourceRefkey(String statusInSourceRefkey) {
		this.statusInSourceRefkey = statusInSourceRefkey;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	/**
	 * @return the statusInSourceRefValue
	 */
	@Transient
	public String getStatusInSourceRefValue() {
		return statusInSourceRefValue;
	}

	/**
	 * @param statusInSourceRefValue the statusInSourceRefValue to set
	 */
	public void setStatusInSourceRefValue(String statusInSourceRefValue) {
		this.statusInSourceRefValue = statusInSourceRefValue;
	}
	
	
}
