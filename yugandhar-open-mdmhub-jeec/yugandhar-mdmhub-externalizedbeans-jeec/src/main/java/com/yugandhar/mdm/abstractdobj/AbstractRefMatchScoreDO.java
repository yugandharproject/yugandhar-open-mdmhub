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
import com.yugandhar.mdm.extern.dobj.RefMatchScoreDO;

/**
 * Abstract DO class for RefMatchScoreDO class mapped to database REF_MATCH_SCORE entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.RefMatchScoreDO
*/

@MappedSuperclass
public abstract class AbstractRefMatchScoreDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String matchEntityObjectName;
	protected String matchAttrPattern;
	protected String matchResultRefkey;
	protected String matchProposedActionRefkey;
	protected String matchAttrPatternDescr;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractRefMatchScoreDO() {
	}

	public AbstractRefMatchScoreDO(RefMatchScoreDO theRefMatchScoreDO) {
		idPk = theRefMatchScoreDO.idPk;
		version = theRefMatchScoreDO.version;
		createdTs = theRefMatchScoreDO.createdTs;
		deletedTs = theRefMatchScoreDO.deletedTs;
		updatedTs = theRefMatchScoreDO.updatedTs;
		updatedByUser = theRefMatchScoreDO.updatedByUser;
		updatedByTxnId = theRefMatchScoreDO.updatedByTxnId;
		matchEntityObjectName = theRefMatchScoreDO.matchEntityObjectName;
		matchAttrPattern = theRefMatchScoreDO.matchAttrPattern;
		matchResultRefkey = theRefMatchScoreDO.matchResultRefkey;
		matchProposedActionRefkey = theRefMatchScoreDO.matchProposedActionRefkey;
		matchAttrPatternDescr = theRefMatchScoreDO.matchAttrPatternDescr;
		inquiryFilter = theRefMatchScoreDO.inquiryFilter;
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

	@Column(name = "MATCH_ENTITY_OBJECT_NAME", nullable = false, length = 100)
	public String getMatchEntityObjectName() {
		return this.matchEntityObjectName;
	}

	public void setMatchEntityObjectName(String matchEntityObjectName) {
		this.matchEntityObjectName = matchEntityObjectName;
	}

	@Column(name = "MATCH_ATTR_PATTERN", nullable = false, length = 50)
	public String getMatchAttrPattern() {
		return this.matchAttrPattern;
	}

	public void setMatchAttrPattern(String matchAttrPattern) {
		this.matchAttrPattern = matchAttrPattern;
	}

	@Column(name = "MATCH_RESULT_REFKEY", nullable = false, length = 50)
	public String getMatchResultRefkey() {
		return this.matchResultRefkey;
	}

	public void setMatchResultRefkey(String matchResultRefkey) {
		this.matchResultRefkey = matchResultRefkey;
	}

	@Column(name = "MATCH_PROPOSED_ACTION_REFKEY", nullable = false, length = 50)
	public String getMatchProposedActionRefkey() {
		return this.matchProposedActionRefkey;
	}

	public void setMatchProposedActionRefkey(String matchProposedActionRefkey) {
		this.matchProposedActionRefkey = matchProposedActionRefkey;
	}

	@Column(name = "MATCH_ATTR_PATTERN_DESCR", length = 500)
	public String getMatchAttrPatternDescr() {
		return this.matchAttrPatternDescr;
	}

	public void setMatchAttrPatternDescr(String matchAttrPatternDescr) {
		this.matchAttrPatternDescr = matchAttrPatternDescr;
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
