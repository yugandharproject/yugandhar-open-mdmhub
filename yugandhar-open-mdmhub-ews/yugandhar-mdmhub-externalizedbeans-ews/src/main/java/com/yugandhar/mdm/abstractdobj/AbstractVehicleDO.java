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
import com.yugandhar.mdm.extern.dobj.VehicleDO;

/**
 * Abstract DO class for VehicleDO class mapped to database VEHICLE entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.VehicleDO
*/

@MappedSuperclass
public abstract class AbstractVehicleDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String vinNumber;
	protected String chassisNumber;
	protected String make;
	protected String model;
	protected String year;
	protected String interiorColor;
	protected String exteriorColor;
	protected String registrationNumber;
	protected String countryOfRegistrationRefkey;
	protected String countryOfRegistrationRefValue;
	protected String insuranceIssuedBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date insuranceIssuedDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date insuranceExpiryDate;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractVehicleDO() {
	}

	public AbstractVehicleDO(VehicleDO theVehicleDO) {
		idPk = theVehicleDO.idPk;
		version = theVehicleDO.version;
		createdTs = theVehicleDO.createdTs;
		deletedTs = theVehicleDO.deletedTs;
		updatedTs = theVehicleDO.updatedTs;
		updatedByUser = theVehicleDO.updatedByUser;
		updatedByTxnId = theVehicleDO.updatedByTxnId;
		vinNumber = theVehicleDO.vinNumber;
		chassisNumber = theVehicleDO.chassisNumber;
		make = theVehicleDO.make;
		model = theVehicleDO.model;
		year = theVehicleDO.year;
		interiorColor = theVehicleDO.interiorColor;
		exteriorColor = theVehicleDO.exteriorColor;
		registrationNumber = theVehicleDO.registrationNumber;
		countryOfRegistrationRefkey = theVehicleDO.countryOfRegistrationRefkey;
		insuranceIssuedBy = theVehicleDO.insuranceIssuedBy;
		insuranceIssuedDate = theVehicleDO.insuranceIssuedDate;
		insuranceExpiryDate = theVehicleDO.insuranceExpiryDate;
		inquiryFilter = theVehicleDO.inquiryFilter;
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

	@Column(name = "VIN_NUMBER", nullable = false, length = 100)
	public String getVinNumber() {
		return this.vinNumber;
	}

	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	@Column(name = "CHASSIS_NUMBER", length = 100)
	public String getChassisNumber() {
		return this.chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	@Column(name = "MAKE", length = 30)
	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	@Column(name = "MODEL", length = 30)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "YEAR", length = 30)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "INTERIOR_COLOR", length = 30)
	public String getInteriorColor() {
		return this.interiorColor;
	}

	public void setInteriorColor(String interiorColor) {
		this.interiorColor = interiorColor;
	}

	@Column(name = "EXTERIOR_COLOR", length = 30)
	public String getExteriorColor() {
		return this.exteriorColor;
	}

	public void setExteriorColor(String exteriorColor) {
		this.exteriorColor = exteriorColor;
	}

	@Column(name = "REGISTRATION_NUMBER", length = 30)
	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@Column(name = "COUNTRY_OF_REGISTRATION_REFKEY", length = 30)
	public String getCountryOfRegistrationRefkey() {
		return this.countryOfRegistrationRefkey;
	}

	public void setCountryOfRegistrationRefkey(String countryOfRegistrationRefkey) {
		this.countryOfRegistrationRefkey = countryOfRegistrationRefkey;
	}

	@Column(name = "INSURANCE_ISSUED_BY", length = 50)
	public String getInsuranceIssuedBy() {
		return this.insuranceIssuedBy;
	}

	public void setInsuranceIssuedBy(String insuranceIssuedBy) {
		this.insuranceIssuedBy = insuranceIssuedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INSURANCE_ISSUED_DATE", length = 7)
	public Date getInsuranceIssuedDate() {
		return this.insuranceIssuedDate;
	}

	public void setInsuranceIssuedDate(Date insuranceIssuedDate) {
		this.insuranceIssuedDate = insuranceIssuedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INSURANCE_EXPIRY_DATE", length = 7)
	public Date getInsuranceExpiryDate() {
		return this.insuranceExpiryDate;
	}

	public void setInsuranceExpiryDate(Date insuranceExpiryDate) {
		this.insuranceExpiryDate = insuranceExpiryDate;
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
	 * @return the countryOfRegistrationRefValue
	 */
	@Transient
	public String getCountryOfRegistrationRefValue() {
		return countryOfRegistrationRefValue;
	}

	/**
	 * @param countryOfRegistrationRefValue the countryOfRegistrationRefValue to set
	 */
	public void setCountryOfRegistrationRefValue(String countryOfRegistrationRefValue) {
		this.countryOfRegistrationRefValue = countryOfRegistrationRefValue;
	}
	
	
	
}
