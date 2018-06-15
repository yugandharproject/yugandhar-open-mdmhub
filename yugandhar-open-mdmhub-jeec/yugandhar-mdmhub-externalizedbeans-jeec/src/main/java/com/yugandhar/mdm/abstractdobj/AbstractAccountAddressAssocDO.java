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

import com.yugandhar.mdm.extern.dobj.AddressDO;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;

/**
 * Abstract DO class for AccountAddressAssocDO class mapped to database ACCOUNT_ADDRESS_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO
*/

@MappedSuperclass
public abstract class AbstractAccountAddressAssocDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String accountIdpk;
	protected String addressIdpk;
	protected String addressTypeRefkey;
	protected String addressTypeRefValue;
	protected String addressSubtypeRefkey;
	protected String addressSubtypeRefValue;
	protected String preferredFlag;
	protected String solicitationFlag;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	//referred entities
	AddressDO addressDO;
	

	public AbstractAccountAddressAssocDO() {
	}

	public AbstractAccountAddressAssocDO(AccountAddressAssocDO theAccountAddressAssocDO) {
		idPk = theAccountAddressAssocDO.idPk;
		version = theAccountAddressAssocDO.version;
		createdTs = theAccountAddressAssocDO.createdTs;
		deletedTs = theAccountAddressAssocDO.deletedTs;
		updatedTs = theAccountAddressAssocDO.updatedTs;
		updatedByUser = theAccountAddressAssocDO.updatedByUser;
		updatedByTxnId = theAccountAddressAssocDO.updatedByTxnId;
		accountIdpk = theAccountAddressAssocDO.accountIdpk;
		addressIdpk = theAccountAddressAssocDO.addressIdpk;
		addressTypeRefkey = theAccountAddressAssocDO.addressTypeRefkey;
		addressSubtypeRefkey = theAccountAddressAssocDO.addressSubtypeRefkey;
		preferredFlag = theAccountAddressAssocDO.preferredFlag;
		solicitationFlag = theAccountAddressAssocDO.solicitationFlag;
		inquiryFilter = theAccountAddressAssocDO.inquiryFilter;
		
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

	@Column(name = "ACCOUNT_IDPK", nullable = false, length = 50)
	public String getAccountIdpk() {
		return this.accountIdpk;
	}

	public void setAccountIdpk(String accountIdpk) {
		this.accountIdpk = accountIdpk;
	}

	@Column(name = "ADDRESS_IDPK", nullable = false, length = 50)
	public String getAddressIdpk() {
		return this.addressIdpk;
	}

	public void setAddressIdpk(String addressIdpk) {
		this.addressIdpk = addressIdpk;
	}

	@Column(name = "ADDRESS_TYPE_REFKEY", nullable = false, length = 50)
	public String getAddressTypeRefkey() {
		return this.addressTypeRefkey;
	}

	public void setAddressTypeRefkey(String addressTypeRefkey) {
		this.addressTypeRefkey = addressTypeRefkey;
	}

	@Column(name = "ADDRESS_SUBTYPE_REFKEY", nullable = false, length = 50)
	public String getAddressSubtypeRefkey() {
		return this.addressSubtypeRefkey;
	}

	public void setAddressSubtypeRefkey(String addressSubtypeRefkey) {
		this.addressSubtypeRefkey = addressSubtypeRefkey;
	}

	@Column(name = "PREFERRED_FLAG", length = 1)
	public String getPreferredFlag() {
		return this.preferredFlag;
	}

	public void setPreferredFlag(String preferredFlag) {
		this.preferredFlag = preferredFlag;
	}

	@Column(name = "SOLICITATION_FLAG", length = 1)
	public String getSolicitationFlag() {
		return this.solicitationFlag;
	}

	public void setSolicitationFlag(String solicitationFlag) {
		this.solicitationFlag = solicitationFlag;
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
	 * @return the addressDO
	 */
	@Transient
	public AddressDO getAddressDO() {
		return addressDO;
	}

	/**
	 * @param addressDO the addressDO to set
	 */
	public void setAddressDO(AddressDO addressDO) {
		this.addressDO = addressDO;
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
	 * @return the addressTypeRefValue
	 */
	@Transient
	public String getAddressTypeRefValue() {
		return addressTypeRefValue;
	}

	/**
	 * @param addressTypeRefValue the addressTypeRefValue to set
	 */
	public void setAddressTypeRefValue(String addressTypeRefValue) {
		this.addressTypeRefValue = addressTypeRefValue;
	}

	/**
	 * @return the addressSubtypeRefValue
	 */
	@Transient
	public String getAddressSubtypeRefValue() {
		return addressSubtypeRefValue;
	}

	/**
	 * @param addressSubtypeRefValue the addressSubtypeRefValue to set
	 */
	public void setAddressSubtypeRefValue(String addressSubtypeRefValue) {
		this.addressSubtypeRefValue = addressSubtypeRefValue;
	}
	
	
}
