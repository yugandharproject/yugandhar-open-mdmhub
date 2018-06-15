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
import com.yugandhar.mdm.extern.dobj.PhoneStandardizedDO;

/**
 * Abstract DO class for PhoneStandardizedDO class mapped to database PHONE_STANDARDIZED entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.PhoneStandardizedDO
*/

@MappedSuperclass
public abstract class AbstractPhoneStandardizedDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String ituCountryCallingCode;
	protected String areaCode;
	protected String exchange;
	protected String phoneNumber;
	protected String extension;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractPhoneStandardizedDO() {
	}

	public AbstractPhoneStandardizedDO(PhoneStandardizedDO thePhoneStandardizedDO) {
		idPk = thePhoneStandardizedDO.idPk;
		version = thePhoneStandardizedDO.version;
		createdTs = thePhoneStandardizedDO.createdTs;
		deletedTs = thePhoneStandardizedDO.deletedTs;
		updatedTs = thePhoneStandardizedDO.updatedTs;
		updatedByUser = thePhoneStandardizedDO.updatedByUser;
		updatedByTxnId = thePhoneStandardizedDO.updatedByTxnId;
		ituCountryCallingCode = thePhoneStandardizedDO.ituCountryCallingCode;
		areaCode = thePhoneStandardizedDO.areaCode;
		exchange = thePhoneStandardizedDO.exchange;
		phoneNumber = thePhoneStandardizedDO.phoneNumber;
		extension = thePhoneStandardizedDO.extension;
		inquiryFilter = thePhoneStandardizedDO.inquiryFilter;
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

	@Column(name = "ITU_COUNTRY_CALLING_CODE", length = 4)
	public String getItuCountryCallingCode() {
		return this.ituCountryCallingCode;
	}

	public void setItuCountryCallingCode(String ituCountryCallingCode) {
		this.ituCountryCallingCode = ituCountryCallingCode;
	}

	@Column(name = "AREA_CODE", length = 6)
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "EXCHANGE", length = 6)
	public String getExchange() {
		return this.exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@Column(name = "PHONE_NUMBER", length = 20)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "EXTENSION", length = 8)
	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
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
