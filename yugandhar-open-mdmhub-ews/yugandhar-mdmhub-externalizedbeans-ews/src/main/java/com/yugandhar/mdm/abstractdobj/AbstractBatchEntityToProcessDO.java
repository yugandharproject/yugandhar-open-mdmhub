package com.yugandhar.mdm.abstractdobj;
/* Generated Dec 13, 2017 12:32:46 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.BatchEntityToProcessDO;

/**
 * Abstract DO class for BatchEntityToProcessDO class mapped to database BATCH_ENTITY_TO_PROCESS entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.BatchEntityToProcessDO
*/

@MappedSuperclass
public abstract class AbstractBatchEntityToProcessDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
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
	protected String batchProposedActionRefkey;
	protected String batchProposedActionRefValue;
	protected String batchActionStatusRefkey;
	protected String batchActionStatusRefValue;
	protected Date processAfterTimestamp;
	protected Date processBeforeTimestamp;
	protected String entryMadeBySubsystemName;
	protected String comments;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractBatchEntityToProcessDO() {
	}

	public AbstractBatchEntityToProcessDO(BatchEntityToProcessDO theBatchEntityToProcessDO) {
		idPk = theBatchEntityToProcessDO.idPk;
		version = theBatchEntityToProcessDO.version;
		createdTs = theBatchEntityToProcessDO.createdTs;
		deletedTs = theBatchEntityToProcessDO.deletedTs;
		updatedTs = theBatchEntityToProcessDO.updatedTs;
		updatedByUser = theBatchEntityToProcessDO.updatedByUser;
		updatedByTxnId = theBatchEntityToProcessDO.updatedByTxnId;
		entityObjectTypeRefkey = theBatchEntityToProcessDO.entityObjectTypeRefkey;
		entityIdpk = theBatchEntityToProcessDO.entityIdpk;
		batchProposedActionRefkey = theBatchEntityToProcessDO.batchProposedActionRefkey;
		batchActionStatusRefkey = theBatchEntityToProcessDO.batchActionStatusRefkey;
		processAfterTimestamp = theBatchEntityToProcessDO.processAfterTimestamp;
		processBeforeTimestamp = theBatchEntityToProcessDO.processBeforeTimestamp;
		entryMadeBySubsystemName = theBatchEntityToProcessDO.entryMadeBySubsystemName;
		comments = theBatchEntityToProcessDO.comments;
		inquiryFilter = theBatchEntityToProcessDO.inquiryFilter;
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

	@Column(name = "BATCH_PROPOSED_ACTION_REFKEY", nullable = false, length = 50)
	public String getBatchProposedActionRefkey() {
		return this.batchProposedActionRefkey;
	}

	public void setBatchProposedActionRefkey(String batchProposedActionRefkey) {
		this.batchProposedActionRefkey = batchProposedActionRefkey;
	}

	@Column(name = "BATCH_ACTION_STATUS_REFKEY", nullable = false, length = 50)
	public String getBatchActionStatusRefkey() {
		return this.batchActionStatusRefkey;
	}

	public void setBatchActionStatusRefkey(String batchActionStatusRefkey) {
		this.batchActionStatusRefkey = batchActionStatusRefkey;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROCESS_AFTER_TIMESTAMP")
	public Date getProcessAfterTimestamp() {
		return this.processAfterTimestamp;
	}

	public void setProcessAfterTimestamp(Date processAfterTimestamp) {
		this.processAfterTimestamp = processAfterTimestamp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROCESS_BEFORE_TIMESTAMP")
	public Date getProcessBeforeTimestamp() {
		return this.processBeforeTimestamp;
	}

	public void setProcessBeforeTimestamp(Date processBeforeTimestamp) {
		this.processBeforeTimestamp = processBeforeTimestamp;
	}

	@Column(name = "ENTRY_MADE_BY_SUBSYSTEM_NAME", nullable = false, length = 50)
	public String getEntryMadeBySubsystemName() {
		return this.entryMadeBySubsystemName;
	}

	public void setEntryMadeBySubsystemName(String entryMadeBySubsystemName) {
		this.entryMadeBySubsystemName = entryMadeBySubsystemName;
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
	 * @return the batchProposedActionRefValue
	 */
	@Transient
	public String getBatchProposedActionRefValue() {
		return batchProposedActionRefValue;
	}

	/**
	 * @param batchProposedActionRefValue the batchProposedActionRefValue to set
	 */
	public void setBatchProposedActionRefValue(String batchProposedActionRefValue) {
		this.batchProposedActionRefValue = batchProposedActionRefValue;
	}

	/**
	 * @return the batchActionStatusRefValue
	 */
	@Transient
	public String getBatchActionStatusRefValue() {
		return batchActionStatusRefValue;
	}

	/**
	 * @param batchActionStatusRefValue the batchActionStatusRefValue to set
	 */
	public void setBatchActionStatusRefValue(String batchActionStatusRefValue) {
		this.batchActionStatusRefValue = batchActionStatusRefValue;
	}

	
	
	
}
