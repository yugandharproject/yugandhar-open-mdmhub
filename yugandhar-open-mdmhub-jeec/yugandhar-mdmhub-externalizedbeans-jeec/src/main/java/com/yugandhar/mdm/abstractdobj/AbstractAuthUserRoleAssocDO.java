package com.yugandhar.mdm.abstractdobj;
/* Generated Oct 2, 2017 1:29:11 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.AuthUserRoleAssocDO;

/**
 * Abstract DO class for AuthUserRoleAssocDO class mapped to database AUTH_USER_ROLE_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.AuthUserRoleAssocDO
*/

@MappedSuperclass
public abstract class AbstractAuthUserRoleAssocDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String authUserRegistryIdpk;
	protected String authRolesRegistryIdpk;
	protected String description;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractAuthUserRoleAssocDO() {
	}

	public AbstractAuthUserRoleAssocDO(AuthUserRoleAssocDO theAuthUserRoleAssocDO) {
		idPk = theAuthUserRoleAssocDO.idPk;
		version = theAuthUserRoleAssocDO.version;
		createdTs = theAuthUserRoleAssocDO.createdTs;
		deletedTs = theAuthUserRoleAssocDO.deletedTs;
		updatedTs = theAuthUserRoleAssocDO.updatedTs;
		updatedByUser = theAuthUserRoleAssocDO.updatedByUser;
		updatedByTxnId = theAuthUserRoleAssocDO.updatedByTxnId;
		authUserRegistryIdpk = theAuthUserRoleAssocDO.authUserRegistryIdpk;
		authRolesRegistryIdpk = theAuthUserRoleAssocDO.authRolesRegistryIdpk;
		description = theAuthUserRoleAssocDO.description;
		inquiryFilter = theAuthUserRoleAssocDO.inquiryFilter;
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

	@Column(name = "AUTH_USER_REGISTRY_IDPK", nullable = false, length = 50)
	public String getAuthUserRegistryIdpk() {
		return this.authUserRegistryIdpk;
	}

	public void setAuthUserRegistryIdpk(String authUserRegistryIdpk) {
		this.authUserRegistryIdpk = authUserRegistryIdpk;
	}

	@Column(name = "AUTH_ROLES_REGISTRY_IDPK", nullable = false, length = 50)
	public String getAuthRolesRegistryIdpk() {
		return this.authRolesRegistryIdpk;
	}

	public void setAuthRolesRegistryIdpk(String authRolesRegistryIdpk) {
		this.authRolesRegistryIdpk = authRolesRegistryIdpk;
	}

	@Column(name = "DESCRIPTION", length = 150)
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
