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
import com.yugandhar.mdm.extern.dobj.EntityGroupAssocDO;

/**
 * Abstract DO class for EntityGroupAssocDO class mapped to database ENTITY_GROUP_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.EntityGroupAssocDO
*/

@MappedSuperclass
public abstract class AbstractEntityGroupAssocDO implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String entityObjectTypeRefkey;
	protected String entityObjectTypeRefValue;
	protected String entityIdpk;
	protected String entityGroupIdpk;
	protected String assocTypeRefkey;
	protected String assocTypeRefValue;
	protected String description;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractEntityGroupAssocDO() {
	}

	public AbstractEntityGroupAssocDO(EntityGroupAssocDO theEntityGroupAssocDO) {
		idPk = theEntityGroupAssocDO.idPk;
		version = theEntityGroupAssocDO.version;
		createdTs = theEntityGroupAssocDO.createdTs;
		deletedTs = theEntityGroupAssocDO.deletedTs;
		updatedTs = theEntityGroupAssocDO.updatedTs;
		updatedByUser = theEntityGroupAssocDO.updatedByUser;
		updatedByTxnId = theEntityGroupAssocDO.updatedByTxnId;
		entityObjectTypeRefkey = theEntityGroupAssocDO.entityObjectTypeRefkey;
		entityIdpk = theEntityGroupAssocDO.entityIdpk;
		entityGroupIdpk = theEntityGroupAssocDO.entityGroupIdpk;
		assocTypeRefkey = theEntityGroupAssocDO.assocTypeRefkey;
		description = theEntityGroupAssocDO.description;
		inquiryFilter = theEntityGroupAssocDO.inquiryFilter;
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

	@Column(name = "ENTITY_OBJECT_TYPE_REFKEY", nullable = false, length = 50)
	public String getEntityObjectTypeRefkey() {
		return this.entityObjectTypeRefkey;
	}

	public void setEntityObjectTypeRefkey(String entityObjectTypeRefkey) {
		this.entityObjectTypeRefkey = entityObjectTypeRefkey;
	}

	@Column(name = "ENTITY_IDPK", nullable = false, length = 50)
	public String getEntityIdpk() {
		return this.entityIdpk;
	}

	public void setEntityIdpk(String entityIdpk) {
		this.entityIdpk = entityIdpk;
	}

	@Column(name = "ENTITY_GROUP_IDPK", nullable = false, length = 50)
	public String getEntityGroupIdpk() {
		return this.entityGroupIdpk;
	}

	public void setEntityGroupIdpk(String entityGroupIdpk) {
		this.entityGroupIdpk = entityGroupIdpk;
	}

	@Column(name = "ASSOC_TYPE_REFKEY", nullable = false, length = 50)
	public String getAssocTypeRefkey() {
		return this.assocTypeRefkey;
	}

	public void setAssocTypeRefkey(String assocTypeRefkey) {
		this.assocTypeRefkey = assocTypeRefkey;
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
	 * @return the entityObjectTypeRefValue
	 */
	@Transient
	public String getEntityObjectTypeRefValue() {
		return entityObjectTypeRefValue;
	}

	/**
	 * @param entityObjectTypeRefValue the entityObjectTypeRefValue to set
	 */
	public void setEntityObjectTypeRefValue(String entityObjectTypeRefValue) {
		this.entityObjectTypeRefValue = entityObjectTypeRefValue;
	}

	/**
	 * @return the assocTypeRefValue
	 */
	@Transient
	public String getAssocTypeRefValue() {
		return assocTypeRefValue;
	}

	/**
	 * @param assocTypeRefValue the assocTypeRefValue to set
	 */
	public void setAssocTypeRefValue(String assocTypeRefValue) {
		this.assocTypeRefValue = assocTypeRefValue;
	}


}
