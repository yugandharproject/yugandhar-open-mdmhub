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
import com.yugandhar.mdm.extern.dobj.MatchMergedLeAssocDO;

/**
 * Abstract DO class for MatchMergedLeAssocDO class mapped to database MATCH_MERGED_LE_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.MatchMergedLeAssocDO
*/

@MappedSuperclass
public abstract class AbstractMatchMergedLeAssocDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String survivorLegalentityIdpk;
	protected String mergedLegalentityIdpk;
	protected String mergeReasonRefkey;
	protected String mergeReasonRefValue;
	protected String comments;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractMatchMergedLeAssocDO() {
	}

	public AbstractMatchMergedLeAssocDO(MatchMergedLeAssocDO theMatchMergedLeAssocDO) {
		idPk = theMatchMergedLeAssocDO.idPk;
		version = theMatchMergedLeAssocDO.version;
		createdTs = theMatchMergedLeAssocDO.createdTs;
		deletedTs = theMatchMergedLeAssocDO.deletedTs;
		updatedTs = theMatchMergedLeAssocDO.updatedTs;
		updatedByUser = theMatchMergedLeAssocDO.updatedByUser;
		updatedByTxnId = theMatchMergedLeAssocDO.updatedByTxnId;
		survivorLegalentityIdpk = theMatchMergedLeAssocDO.survivorLegalentityIdpk;
		mergedLegalentityIdpk = theMatchMergedLeAssocDO.mergedLegalentityIdpk;
		mergeReasonRefkey = theMatchMergedLeAssocDO.mergeReasonRefkey;
		comments = theMatchMergedLeAssocDO.comments;
		inquiryFilter = theMatchMergedLeAssocDO.inquiryFilter;
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

	@Column(name = "SURVIVOR_LEGALENTITY_IDPK", nullable = false, length = 50)
	public String getSurvivorLegalentityIdpk() {
		return this.survivorLegalentityIdpk;
	}

	public void setSurvivorLegalentityIdpk(String survivorLegalentityIdpk) {
		this.survivorLegalentityIdpk = survivorLegalentityIdpk;
	}

	@Column(name = "MERGED_LEGALENTITY_IDPK", nullable = false, length = 50)
	public String getMergedLegalentityIdpk() {
		return this.mergedLegalentityIdpk;
	}

	public void setMergedLegalentityIdpk(String mergedLegalentityIdpk) {
		this.mergedLegalentityIdpk = mergedLegalentityIdpk;
	}

	@Column(name = "MERGE_REASON_REFKEY", nullable = false, length = 50)
	public String getMergeReasonRefkey() {
		return this.mergeReasonRefkey;
	}

	public void setMergeReasonRefkey(String mergeReasonRefkey) {
		this.mergeReasonRefkey = mergeReasonRefkey;
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
	 * @return the mergeReasonRefValue
	 */
	@Transient
	public String getMergeReasonRefValue() {
		return mergeReasonRefValue;
	}

	/**
	 * @param mergeReasonRefValue the mergeReasonRefValue to set
	 */
	public void setMergeReasonRefValue(String mergeReasonRefValue) {
		this.mergeReasonRefValue = mergeReasonRefValue;
	}

	
}
