package com.yugandhar.mdm.abstractdobj;
/* Generated Oct 27, 2017 5:20:01 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.InactiveLeRegistryDO;

/**
 * Abstract DO class for InactiveLeRegistryDO class mapped to database INACTIVE_LE_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.InactiveLeRegistryDO
*/

@MappedSuperclass
public abstract class AbstractInactiveLeRegistryDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String legalentityIdpk;
	protected Date inactivatedTs;
	protected String inactivationReasonRefkey;
	protected String inactivationReasonRefValue;
	protected String comments;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractInactiveLeRegistryDO() {
	}

	public AbstractInactiveLeRegistryDO(InactiveLeRegistryDO theInactiveLeRegistryDO) {
		idPk = theInactiveLeRegistryDO.idPk;
		version = theInactiveLeRegistryDO.version;
		createdTs = theInactiveLeRegistryDO.createdTs;
		deletedTs = theInactiveLeRegistryDO.deletedTs;
		updatedTs = theInactiveLeRegistryDO.updatedTs;
		updatedByUser = theInactiveLeRegistryDO.updatedByUser;
		updatedByTxnId = theInactiveLeRegistryDO.updatedByTxnId;
		legalentityIdpk = theInactiveLeRegistryDO.legalentityIdpk;
		inactivatedTs = theInactiveLeRegistryDO.inactivatedTs;
		inactivationReasonRefkey = theInactiveLeRegistryDO.inactivationReasonRefkey;
		comments = theInactiveLeRegistryDO.comments;
		inquiryFilter = theInactiveLeRegistryDO.inquiryFilter;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INACTIVATED_TS", nullable = false)
	public Date getInactivatedTs() {
		return this.inactivatedTs;
	}

	public void setInactivatedTs(Date inactivatedTs) {
		this.inactivatedTs = inactivatedTs;
	}

	@Column(name = "INACTIVATION_REASON_REFKEY", nullable = false, length = 50)
	public String getInactivationReasonRefkey() {
		return this.inactivationReasonRefkey;
	}

	public void setInactivationReasonRefkey(String inactivationReasonRefkey) {
		this.inactivationReasonRefkey = inactivationReasonRefkey;
	}

	@Column(name = "COMMENTS", length = 100)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
	 * @return the inactivationReasonRefValue
	 */
	@Transient
	public String getInactivationReasonRefValue() {
		return inactivationReasonRefValue;
	}

	/**
	 * @param inactivationReasonRefValue the inactivationReasonRefValue to set
	 */
	public void setInactivationReasonRefValue(String inactivationReasonRefValue) {
		this.inactivationReasonRefValue = inactivationReasonRefValue;
	}

	
	
}
