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

import com.yugandhar.mdm.extern.dobj.EntityGroupAssocDO;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.mdm.extern.dobj.EntityGroupDO;

/**
 * Abstract DO class for EntityGroupDO class mapped to database ENTITY_GROUP entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.EntityGroupDO
*/

@MappedSuperclass
public abstract class AbstractEntityGroupDO implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String groupTypeRefkey;
	protected String groupTypeRefValue;
	protected String groupSubtypeRefkey;
	protected String groupSubtypeRefValue;
	protected String groupName;
	protected String description;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	//referred entities
	protected List<EntityGroupAssocDO> entityGroupAssocDOList;

	public AbstractEntityGroupDO() {
	}

	public AbstractEntityGroupDO(EntityGroupDO theEntityGroupDO) {
		idPk = theEntityGroupDO.idPk;
		version = theEntityGroupDO.version;
		createdTs = theEntityGroupDO.createdTs;
		deletedTs = theEntityGroupDO.deletedTs;
		updatedTs = theEntityGroupDO.updatedTs;
		updatedByUser = theEntityGroupDO.updatedByUser;
		updatedByTxnId = theEntityGroupDO.updatedByTxnId;
		groupTypeRefkey = theEntityGroupDO.groupTypeRefkey;
		groupSubtypeRefkey = theEntityGroupDO.groupSubtypeRefkey;
		groupName = theEntityGroupDO.groupName;
		description = theEntityGroupDO.description;
		inquiryFilter = theEntityGroupDO.inquiryFilter;
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

	@Column(name = "GROUP_TYPE_REFKEY", nullable = false, length = 50)
	public String getGroupTypeRefkey() {
		return this.groupTypeRefkey;
	}

	public void setGroupTypeRefkey(String groupTypeRefkey) {
		this.groupTypeRefkey = groupTypeRefkey;
	}

	@Column(name = "GROUP_SUBTYPE_REFKEY", length = 50)
	public String getGroupSubtypeRefkey() {
		return this.groupSubtypeRefkey;
	}

	public void setGroupSubtypeRefkey(String groupSubtypeRefkey) {
		this.groupSubtypeRefkey = groupSubtypeRefkey;
	}

	@Column(name = "GROUP_NAME", nullable = false, length = 50)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	 * @return the entityGroupAssocDOList
	 */
	@Transient
	public List<EntityGroupAssocDO> getEntityGroupAssocDOList() {
		return entityGroupAssocDOList;
	}

	/**
	 * @param entityGroupAssocDOList the entityGroupAssocDOList to set
	 */
	public void setEntityGroupAssocDOList(List<EntityGroupAssocDO> entityGroupAssocDOList) {
		this.entityGroupAssocDOList = entityGroupAssocDOList;
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
	 * @return the groupTypeRefValue
	 */
	@Transient
	public String getGroupTypeRefValue() {
		return groupTypeRefValue;
	}

	/**
	 * @param groupTypeRefValue the groupTypeRefValue to set
	 */
	public void setGroupTypeRefValue(String groupTypeRefValue) {
		this.groupTypeRefValue = groupTypeRefValue;
	}

	/**
	 * @return the groupSubtypeRefValue
	 */
	@Transient
	public String getGroupSubtypeRefValue() {
		return groupSubtypeRefValue;
	}

	/**
	 * @param groupSubtypeRefValue the groupSubtypeRefValue to set
	 */
	public void setGroupSubtypeRefValue(String groupSubtypeRefValue) {
		this.groupSubtypeRefValue = groupSubtypeRefValue;
	}


	
}
