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
import com.yugandhar.mdm.extern.dobj.MatchCandidateLeRegistryDO;

/**
 * Abstract DO class for MatchCandidateLeRegistryDO class mapped to database MATCH_CANDIDATE_LE_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.MatchCandidateLeRegistryDO
*/

@MappedSuperclass
public abstract class AbstractMatchCandidateLeRegistryDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String legalentityIdpk;
	protected String candidateLegalentityidpk;
	protected String matchPattern;
	protected String matchProposedActionRefkey;
	protected String matchProposedActionRefValue;
	protected String matchActionstatusRefkey;
	protected String matchActionstatusRefValue;
	protected String matchPercentageDescription;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractMatchCandidateLeRegistryDO() {
	}

	public AbstractMatchCandidateLeRegistryDO(MatchCandidateLeRegistryDO theMatchCandidateLeRegistryDO) {
		idPk = theMatchCandidateLeRegistryDO.idPk;
		version = theMatchCandidateLeRegistryDO.version;
		createdTs = theMatchCandidateLeRegistryDO.createdTs;
		deletedTs = theMatchCandidateLeRegistryDO.deletedTs;
		updatedTs = theMatchCandidateLeRegistryDO.updatedTs;
		updatedByUser = theMatchCandidateLeRegistryDO.updatedByUser;
		updatedByTxnId = theMatchCandidateLeRegistryDO.updatedByTxnId;
		legalentityIdpk = theMatchCandidateLeRegistryDO.legalentityIdpk;
		candidateLegalentityidpk = theMatchCandidateLeRegistryDO.candidateLegalentityidpk;
		matchPattern = theMatchCandidateLeRegistryDO.matchPattern;
		matchProposedActionRefkey = theMatchCandidateLeRegistryDO.matchProposedActionRefkey;
		matchActionstatusRefkey = theMatchCandidateLeRegistryDO.matchActionstatusRefkey;
		matchPercentageDescription = theMatchCandidateLeRegistryDO.matchPercentageDescription;
		inquiryFilter = theMatchCandidateLeRegistryDO.inquiryFilter;
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

	@Column(name = "CANDIDATE_LEGALENTITYIDPK", nullable = false, length = 50)
	public String getCandidateLegalentityidpk() {
		return this.candidateLegalentityidpk;
	}

	public void setCandidateLegalentityidpk(String candidateLegalentityidpk) {
		this.candidateLegalentityidpk = candidateLegalentityidpk;
	}

	@Column(name = "MATCH_PATTERN", nullable = false, length = 50)
	public String getMatchPattern() {
		return this.matchPattern;
	}

	public void setMatchPattern(String matchPattern) {
		this.matchPattern = matchPattern;
	}

	@Column(name = "MATCH_PROPOSED_ACTION_REFKEY", nullable = false, length = 50)
	public String getMatchProposedActionRefkey() {
		return this.matchProposedActionRefkey;
	}

	public void setMatchProposedActionRefkey(String matchProposedActionRefkey) {
		this.matchProposedActionRefkey = matchProposedActionRefkey;
	}

	@Column(name = "MATCH_ACTIONSTATUS_REFKEY", nullable = false, length = 50)
	public String getMatchActionstatusRefkey() {
		return this.matchActionstatusRefkey;
	}

	public void setMatchActionstatusRefkey(String matchActionstatusRefkey) {
		this.matchActionstatusRefkey = matchActionstatusRefkey;
	}

	@Column(name = "MATCH_PERCENTAGE_DESCRIPTION", length = 500)
	public String getMatchPercentageDescription() {
		return this.matchPercentageDescription;
	}

	public void setMatchPercentageDescription(String matchPercentageDescription) {
		this.matchPercentageDescription = matchPercentageDescription;
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
	 * @return the matchProposedActionRefValue
	 */
	@Transient
	public String getMatchProposedActionRefValue() {
		return matchProposedActionRefValue;
	}

	/**
	 * @param matchProposedActionRefValue the matchProposedActionRefValue to set
	 */
	public void setMatchProposedActionRefValue(String matchProposedActionRefValue) {
		this.matchProposedActionRefValue = matchProposedActionRefValue;
	}

	/**
	 * @return the matchActionstatusRefValue
	 */
	@Transient
	public String getMatchActionstatusRefValue() {
		return matchActionstatusRefValue;
	}

	/**
	 * @param matchActionstatusRefValue the matchActionstatusRefValue to set
	 */
	public void setMatchActionstatusRefValue(String matchActionstatusRefValue) {
		this.matchActionstatusRefValue = matchActionstatusRefValue;
	}

	
}
