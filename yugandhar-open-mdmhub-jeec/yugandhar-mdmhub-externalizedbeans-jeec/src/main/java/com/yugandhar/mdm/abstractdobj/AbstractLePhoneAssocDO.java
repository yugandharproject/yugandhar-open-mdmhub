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

import com.yugandhar.mdm.extern.dobj.PhoneStandardizedDO;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.mdm.extern.dobj.LePhoneAssocDO;

/**
 * Abstract DO class for LePhoneAssocDO class mapped to database LE_PHONE_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.LePhoneAssocDO
*/

@MappedSuperclass
public abstract class AbstractLePhoneAssocDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String legalentityIdpk;
	protected String phoneTypeRefkey;
	protected String phoneTypeRefValue;
	protected String phoneSubtypeRefkey;
	protected String phoneSubtypeRefValue;
	protected String preferredFlag;
	protected String phoneNumber;
	protected String phoneStandardizedIdpk;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	//referred
	protected PhoneStandardizedDO phoneStandardizedDO;

	public AbstractLePhoneAssocDO() {
	}

	public AbstractLePhoneAssocDO(LePhoneAssocDO theLePhoneAssocDO) {
		idPk = theLePhoneAssocDO.idPk;
		version = theLePhoneAssocDO.version;
		createdTs = theLePhoneAssocDO.createdTs;
		deletedTs = theLePhoneAssocDO.deletedTs;
		updatedTs = theLePhoneAssocDO.updatedTs;
		updatedByUser = theLePhoneAssocDO.updatedByUser;
		updatedByTxnId = theLePhoneAssocDO.updatedByTxnId;
		legalentityIdpk = theLePhoneAssocDO.legalentityIdpk;
		phoneTypeRefkey = theLePhoneAssocDO.phoneTypeRefkey;
		phoneSubtypeRefkey = theLePhoneAssocDO.phoneSubtypeRefkey;
		preferredFlag = theLePhoneAssocDO.preferredFlag;
		phoneNumber = theLePhoneAssocDO.phoneNumber;
		phoneStandardizedIdpk = theLePhoneAssocDO.phoneStandardizedIdpk;
		inquiryFilter = theLePhoneAssocDO.inquiryFilter;
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

	@Column(name = "PHONE_TYPE_REFKEY", nullable = false, length = 50)
	public String getPhoneTypeRefkey() {
		return this.phoneTypeRefkey;
	}

	public void setPhoneTypeRefkey(String phoneTypeRefkey) {
		this.phoneTypeRefkey = phoneTypeRefkey;
	}

	@Column(name = "PHONE_SUBTYPE_REFKEY", length = 50)
	public String getPhoneSubtypeRefkey() {
		return this.phoneSubtypeRefkey;
	}

	public void setPhoneSubtypeRefkey(String phoneSubtypeRefkey) {
		this.phoneSubtypeRefkey = phoneSubtypeRefkey;
	}

	@Column(name = "PREFERRED_FLAG", length = 1)
	public String getPreferredFlag() {
		return this.preferredFlag;
	}

	public void setPreferredFlag(String preferredFlag) {
		this.preferredFlag = preferredFlag;
	}

	@Column(name = "PHONE_NUMBER", nullable = false, length = 30)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "PHONE_STANDARDIZED_IDPK", length = 50)
	public String getPhoneStandardizedIdpk() {
		return this.phoneStandardizedIdpk;
	}

	public void setPhoneStandardizedIdpk(String phoneStandardizedIdpk) {
		this.phoneStandardizedIdpk = phoneStandardizedIdpk;
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
	 * @return the phoneStandardizedDO
	 */
	@Transient
	public PhoneStandardizedDO getPhoneStandardizedDO() {
		return phoneStandardizedDO;
	}

	/**
	 * @param phoneStandardizedDO the phoneStandardizedDO to set
	 */
	public void setPhoneStandardizedDO(PhoneStandardizedDO phoneStandardizedDO) {
		this.phoneStandardizedDO = phoneStandardizedDO;
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
	 * @return the phoneTypeRefValue
	 */
	@Transient
	public String getPhoneTypeRefValue() {
		return phoneTypeRefValue;
	}

	/**
	 * @param phoneTypeRefValue the phoneTypeRefValue to set
	 */
	public void setPhoneTypeRefValue(String phoneTypeRefValue) {
		this.phoneTypeRefValue = phoneTypeRefValue;
	}

	/**
	 * @return the phoneSubtypeRefValue
	 */
	@Transient
	public String getPhoneSubtypeRefValue() {
		return phoneSubtypeRefValue;
	}

	/**
	 * @param phoneSubtypeRefValue the phoneSubtypeRefValue to set
	 */
	public void setPhoneSubtypeRefValue(String phoneSubtypeRefValue) {
		this.phoneSubtypeRefValue = phoneSubtypeRefValue;
	}
	
	
	
}
