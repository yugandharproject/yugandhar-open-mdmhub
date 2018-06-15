package com.yugandhar.mdm.abstractdobj;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.RefMatchThresholdDO;

/**
 * Abstract DO class for RefMatchThresholdDO class mapped to database REF_MATCH_THRESHOLD entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.RefMatchThresholdDO
*/

@MappedSuperclass
public abstract class AbstractRefMatchThresholdDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String attrBlockName;
	protected Integer matchThreshold;
	protected Integer decayThresholdInDays;
	protected Double decayPercentage;
	protected Integer maxDecayPercentage;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractRefMatchThresholdDO() {
	}

	public AbstractRefMatchThresholdDO(RefMatchThresholdDO theRefMatchThresholdDO) {
		idPk = theRefMatchThresholdDO.idPk;
		version = theRefMatchThresholdDO.version;
		createdTs = theRefMatchThresholdDO.createdTs;
		deletedTs = theRefMatchThresholdDO.deletedTs;
		updatedTs = theRefMatchThresholdDO.updatedTs;
		updatedByUser = theRefMatchThresholdDO.updatedByUser;
		updatedByTxnId = theRefMatchThresholdDO.updatedByTxnId;
		attrBlockName = theRefMatchThresholdDO.attrBlockName;
		matchThreshold = theRefMatchThresholdDO.matchThreshold;
		decayThresholdInDays = theRefMatchThresholdDO.decayThresholdInDays;
		decayPercentage = theRefMatchThresholdDO.decayPercentage;
		maxDecayPercentage = theRefMatchThresholdDO.maxDecayPercentage;
		inquiryFilter = theRefMatchThresholdDO.inquiryFilter;
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

	@Column(name = "ATTR_BLOCK_NAME", unique = true, nullable = false, length = 100)
	public String getAttrBlockName() {
		return this.attrBlockName;
	}

	public void setAttrBlockName(String attrBlockName) {
		this.attrBlockName = attrBlockName;
	}

	@Column(name = "MATCH_THRESHOLD", nullable = false, precision = 22, scale = 0)
	public Integer getMatchThreshold() {
		return this.matchThreshold;
	}

	public void setMatchThreshold(Integer matchThreshold) {
		this.matchThreshold = matchThreshold;
	}

	@Column(name = "DECAY_THRESHOLD_IN_DAYS", nullable = false, precision = 22, scale = 0)
	public Integer getDecayThresholdInDays() {
		return this.decayThresholdInDays;
	}

	public void setDecayThresholdInDays(Integer decayThresholdInDays) {
		this.decayThresholdInDays = decayThresholdInDays;
	}

	@Column(name = "DECAY_PERCENTAGE", nullable = false, precision = 11, scale = 8)
	public Double getDecayPercentage() {
		return this.decayPercentage;
	}

	public void setDecayPercentage(Double decayPercentage) {
		this.decayPercentage = decayPercentage;
	}

	@Column(name = "MAX_DECAY_PERCENTAGE", nullable = false, precision = 22, scale = 0)
	public Integer getMaxDecayPercentage() {
		return this.maxDecayPercentage;
	}

	public void setMaxDecayPercentage(Integer maxDecayPercentage) {
		this.maxDecayPercentage = maxDecayPercentage;
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
