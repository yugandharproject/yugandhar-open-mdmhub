package com.yugandhar.mdm.abstractdobj;
/* Generated Jun 13, 2017 1:01:46 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.yugandhar.mdm.extern.dobj.ConfigTxnRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefAccountMdmStatusDO;

@MappedSuperclass
public abstract class AbstractConfigTxnRegistryDO implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected String txnserviceName;
	protected String txnserviceClass;
	protected String txnserviceClassmethod;
	protected String description;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedTxnRefId;
	protected String inquiryFilter;
	protected String reactivationFlag;

	public AbstractConfigTxnRegistryDO() {
	}
	
	public AbstractConfigTxnRegistryDO(ConfigTxnRegistryDO theConfigTxnRegistryDO) {
		idPk = theConfigTxnRegistryDO.idPk;
		version = theConfigTxnRegistryDO.version;
		createdTs = theConfigTxnRegistryDO.createdTs;
		deletedTs = theConfigTxnRegistryDO.deletedTs;
		updatedTs = theConfigTxnRegistryDO.updatedTs;
		updatedByUser = theConfigTxnRegistryDO.updatedByUser;
		txnserviceName = theConfigTxnRegistryDO.getTxnserviceName();
		txnserviceClass = theConfigTxnRegistryDO.getTxnserviceClass();
		txnserviceClassmethod = theConfigTxnRegistryDO.getTxnserviceClassmethod();
		description = theConfigTxnRegistryDO.description;
		inquiryFilter = theConfigTxnRegistryDO.inquiryFilter;
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
	@Column(name = "VERSION", precision = 22, scale = 0)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "TXNSERVICE_NAME", length = 50)
	public String getTxnserviceName() {
		return this.txnserviceName;
	}

	public void setTxnserviceName(String txnserviceName) {
		this.txnserviceName = txnserviceName;
	}

	@Column(name = "TXNSERVICE_CLASS", length = 200)
	public String getTxnserviceClass() {
		return this.txnserviceClass;
	}

	public void setTxnserviceClass(String txnserviceClass) {
		this.txnserviceClass = txnserviceClass;
	}

	@Column(name = "TXNSERVICE_CLASSMETHOD", length = 100)
	public String getTxnserviceClassmethod() {
		return this.txnserviceClassmethod;
	}

	public void setTxnserviceClassmethod(String txnserviceClassmethod) {
		this.txnserviceClassmethod = txnserviceClassmethod;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TS")
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
	@Column(name = "UPDATED_TS")
	public Date getUpdatedTs() {
		return this.updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	@Column(name = "UPDATED_BY_USER", length = 50)
	public String getUpdatedByUser() {
		return this.updatedByUser;
	}

	public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	@Column(name = "UPDATED_TXN_REF_ID", length = 100)
	public String getUpdatedTxnRefId() {
		return this.updatedTxnRefId;
	}

	public void setUpdatedTxnRefId(String updatedTxnRefId) {
		this.updatedTxnRefId = updatedTxnRefId;
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
	 * @return the reactivationFlag
	 */
	@Transient
	public String getReactivationFlag() {
		return reactivationFlag;
	}

	/**
	 * @param reactivationFlag the reactivationFlag to set
	 */
	public void setReactivationFlag(String reactivationFlag) {
		this.reactivationFlag = reactivationFlag;
	}

}
