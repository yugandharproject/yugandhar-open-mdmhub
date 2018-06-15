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
import com.yugandhar.mdm.extern.dobj.LeToLeRelationshipDO;

/**
 * Abstract DO class for LeToLeRelationshipDO class mapped to database LE_TO_LE_RELATIONSHIP entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.LeToLeRelationshipDO
*/

@MappedSuperclass
public abstract class AbstractLeToLeRelationshipDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String fromLegalentityIdpk;
	protected String toLegalentityIdpk;
	protected String leRelationshipTypeRefkey;
	protected String leRelationshipTypeRefValue;
	protected String relationshipStatusRefkey;
	protected String relationshipStatusRefValue;
	protected String relationshipDescription;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractLeToLeRelationshipDO() {
	}

	public AbstractLeToLeRelationshipDO(LeToLeRelationshipDO theLeToLeRelationshipDO) {
		idPk = theLeToLeRelationshipDO.idPk;
		version = theLeToLeRelationshipDO.version;
		createdTs = theLeToLeRelationshipDO.createdTs;
		deletedTs = theLeToLeRelationshipDO.deletedTs;
		updatedTs = theLeToLeRelationshipDO.updatedTs;
		updatedByUser = theLeToLeRelationshipDO.updatedByUser;
		updatedByTxnId = theLeToLeRelationshipDO.updatedByTxnId;
		fromLegalentityIdpk = theLeToLeRelationshipDO.fromLegalentityIdpk;
		toLegalentityIdpk = theLeToLeRelationshipDO.toLegalentityIdpk;
		leRelationshipTypeRefkey = theLeToLeRelationshipDO.leRelationshipTypeRefkey;
		relationshipStatusRefkey = theLeToLeRelationshipDO.relationshipStatusRefkey;
		relationshipDescription = theLeToLeRelationshipDO.relationshipDescription;
		inquiryFilter = theLeToLeRelationshipDO.inquiryFilter;
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

	@Column(name = "FROM_LEGALENTITY_IDPK", nullable = false, length = 50)
	public String getFromLegalentityIdpk() {
		return this.fromLegalentityIdpk;
	}

	public void setFromLegalentityIdpk(String fromLegalentityIdpk) {
		this.fromLegalentityIdpk = fromLegalentityIdpk;
	}

	@Column(name = "TO_LEGALENTITY_IDPK", nullable = false, length = 50)
	public String getToLegalentityIdpk() {
		return this.toLegalentityIdpk;
	}

	public void setToLegalentityIdpk(String toLegalentityIdpk) {
		this.toLegalentityIdpk = toLegalentityIdpk;
	}

	@Column(name = "LE_RELATIONSHIP_TYPE_REFKEY", nullable = false, length = 50)
	public String getLeRelationshipTypeRefkey() {
		return this.leRelationshipTypeRefkey;
	}

	public void setLeRelationshipTypeRefkey(String leRelationshipTypeRefkey) {
		this.leRelationshipTypeRefkey = leRelationshipTypeRefkey;
	}

	@Column(name = "RELATIONSHIP_STATUS_REFKEY", length = 50)
	public String getRelationshipStatusRefkey() {
		return this.relationshipStatusRefkey;
	}

	public void setRelationshipStatusRefkey(String relationshipStatusRefkey) {
		this.relationshipStatusRefkey = relationshipStatusRefkey;
	}

	@Column(name = "RELATIONSHIP_DESCRIPTION", length = 100)
	public String getRelationshipDescription() {
		return this.relationshipDescription;
	}

	public void setRelationshipDescription(String relationshipDescription) {
		this.relationshipDescription = relationshipDescription;
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
	 * @return the leRelationshipTypeRefValue
	 */
	@Transient
	public String getLeRelationshipTypeRefValue() {
		return leRelationshipTypeRefValue;
	}

	/**
	 * @param leRelationshipTypeRefValue the leRelationshipTypeRefValue to set
	 */
	public void setLeRelationshipTypeRefValue(String leRelationshipTypeRefValue) {
		this.leRelationshipTypeRefValue = leRelationshipTypeRefValue;
	}

	/**
	 * @return the relationshipStatusRefValue
	 */
	@Transient
	public String getRelationshipStatusRefValue() {
		return relationshipStatusRefValue;
	}

	/**
	 * @param relationshipStatusRefValue the relationshipStatusRefValue to set
	 */
	public void setRelationshipStatusRefValue(String relationshipStatusRefValue) {
		this.relationshipStatusRefValue = relationshipStatusRefValue;
	}
	
	
	
}
