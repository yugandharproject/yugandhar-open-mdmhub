package com.yugandhar.mdm.abstractdobj;
/* Generated Aug 22, 2017 5:25:47 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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

/**
 * Abstract DO class for ConfigInquiryLevelsDO class mapped to database CONFIG_INQUIRY_LEVELS entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.ConfigInquiryLevelsDO
*/

@MappedSuperclass
public abstract class AbstractConfigInquiryLevelsDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String idPk;
	private Integer version;
	private Date createdTs;
	private Date deletedTs;
	private Date updatedTs;
	private String updatedByUser;
	private String updatedByTxnId;
	private String inquiryLevel;
	private String applicableDobj;
	private String childDobj;
	private String description;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractConfigInquiryLevelsDO() {
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

	@Column(name = "INQUIRY_LEVEL", nullable = false, length = 20)
	public String getInquiryLevel() {
		return this.inquiryLevel;
	}

	public void setInquiryLevel(String inquiryLevel) {
		this.inquiryLevel = inquiryLevel;
	}

	@Column(name = "APPLICABLE_DOBJ", nullable = false, length = 100)
	public String getApplicableDobj() {
		return this.applicableDobj;
	}

	public void setApplicableDobj(String applicableDobj) {
		this.applicableDobj = applicableDobj;
	}

	@Column(name = "CHILD_DOBJ", nullable = false, length = 100)
	public String getChildDobj() {
		return this.childDobj;
	}

	public void setChildDobj(String childDobj) {
		this.childDobj = childDobj;
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

}
