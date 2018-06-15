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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yugandhar.mdm.extern.dobj.LeIdentifierKycRegistryDO;

/**
 * Abstract DO class for LeIdentifierKycRegistryDO class mapped to database LE_IDENTIFIER_KYC_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.LeIdentifierKycRegistryDO
*/

@MappedSuperclass
public abstract class AbstractLeIdentifierKycRegistryDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String identificationTypeRefkey;
	protected String identificationTypeRefValue;
	protected String identificationNumber;
	protected String legalentityIdpk;
	protected String document;
	protected String issuedBy;
	protected String idConsideredForKycFlag;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date issuedDate;
	protected String sourceSystemRefkey;
	protected String sourceSystemRefValue;
	protected String identityDescription;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractLeIdentifierKycRegistryDO() {
	}

	public AbstractLeIdentifierKycRegistryDO(LeIdentifierKycRegistryDO theLeIdentifierKycRegistryDO) {
		idPk = theLeIdentifierKycRegistryDO.idPk;
		version = theLeIdentifierKycRegistryDO.version;
		createdTs = theLeIdentifierKycRegistryDO.createdTs;
		deletedTs = theLeIdentifierKycRegistryDO.deletedTs;
		updatedTs = theLeIdentifierKycRegistryDO.updatedTs;
		updatedByUser = theLeIdentifierKycRegistryDO.updatedByUser;
		updatedByTxnId = theLeIdentifierKycRegistryDO.updatedByTxnId;
		identificationTypeRefkey = theLeIdentifierKycRegistryDO.identificationTypeRefkey;
		identificationNumber = theLeIdentifierKycRegistryDO.identificationNumber;
		legalentityIdpk = theLeIdentifierKycRegistryDO.legalentityIdpk;
		document = theLeIdentifierKycRegistryDO.document;
		issuedBy = theLeIdentifierKycRegistryDO.issuedBy;
		idConsideredForKycFlag = theLeIdentifierKycRegistryDO.idConsideredForKycFlag;
		issuedDate = theLeIdentifierKycRegistryDO.issuedDate;
		sourceSystemRefkey = theLeIdentifierKycRegistryDO.sourceSystemRefkey;
		identityDescription = theLeIdentifierKycRegistryDO.identityDescription;
		inquiryFilter = theLeIdentifierKycRegistryDO.inquiryFilter;
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

	@Column(name = "IDENTIFICATION_TYPE_REFKEY", nullable = false, length = 50)
	public String getIdentificationTypeRefkey() {
		return this.identificationTypeRefkey;
	}

	public void setIdentificationTypeRefkey(String identificationTypeRefkey) {
		this.identificationTypeRefkey = identificationTypeRefkey;
	}

	@Column(name = "IDENTIFICATION_NUMBER", nullable = false, length = 50)
	public String getIdentificationNumber() {
		return this.identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	@Column(name = "LEGALENTITY_IDPK", nullable = false, length = 50)
	public String getLegalentityIdpk() {
		return this.legalentityIdpk;
	}

	public void setLegalentityIdpk(String legalentityIdpk) {
		this.legalentityIdpk = legalentityIdpk;
	}

	@Column(name = "DOCUMENT", length = 50)
	public String getDocument() {
		return this.document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	@Column(name = "ISSUED_BY", length = 50)
	public String getIssuedBy() {
		return this.issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	@Column(name = "ID_CONSIDERED_FOR_KYC_FLAG", length = 50)
	public String getIdConsideredForKycFlag() {
		return this.idConsideredForKycFlag;
	}

	public void setIdConsideredForKycFlag(String idConsideredForKycFlag) {
		this.idConsideredForKycFlag = idConsideredForKycFlag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ISSUED_DATE", length = 7)
	public Date getIssuedDate() {
		return this.issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	@Column(name = "SOURCE_SYSTEM_REFKEY", length = 50)
	public String getSourceSystemRefkey() {
		return this.sourceSystemRefkey;
	}

	public void setSourceSystemRefkey(String sourceSystemRefkey) {
		this.sourceSystemRefkey = sourceSystemRefkey;
	}

	@Column(name = "IDENTITY_DESCRIPTION", length = 50)
	public String getIdentityDescription() {
		return this.identityDescription;
	}

	public void setIdentityDescription(String identityDescription) {
		this.identityDescription = identityDescription;
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
	 * @return the identificationTypeRefValue
	 */
	@Transient
	public String getIdentificationTypeRefValue() {
		return identificationTypeRefValue;
	}

	/**
	 * @param identificationTypeRefValue the identificationTypeRefValue to set
	 */
	public void setIdentificationTypeRefValue(String identificationTypeRefValue) {
		this.identificationTypeRefValue = identificationTypeRefValue;
	}

	/**
	 * @return the sourceSystemRefValue
	 */
	@Transient
	public String getSourceSystemRefValue() {
		return sourceSystemRefValue;
	}

	/**
	 * @param sourceSystemRefValue the sourceSystemRefValue to set
	 */
	public void setSourceSystemRefValue(String sourceSystemRefValue) {
		this.sourceSystemRefValue = sourceSystemRefValue;
	}
	
	
}
