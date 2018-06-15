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
import com.yugandhar.mdm.extern.dobj.AddressDO;

/**
 * Abstract DO class for AddressDO class mapped to database ADDRESS entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.AddressDO
*/

@MappedSuperclass
public abstract class AbstractAddressDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String addressLineOne;
	protected String addressLineTwo;
	protected String addressLineThree;
	protected String addressLineFour;
	protected String streetNumber;
	protected String streetName;
	protected String houseNumber;
	protected String buildingNumber;
	protected String county;
	protected String city;
	protected String districtZone;
	protected String stateProvinceRefkey;
	protected String stateProvinceRefValue;
	protected String countryRefkey;
	protected String countryRefValue;
	protected String postalCode;
	protected String nearestLandmark;
	protected String boxDesignator;
	protected String boxIdentifier;
	protected String nearestRailwayStation;
	protected String nearestAirport;
	protected String phoneticAddressLineOne;
	protected String phoneticAddressLineTwo;
	protected String phoneticAddressLineThree;
	protected String phoneticAddressLineFour;
	protected String phoneticStreetName;
	protected String phoneticCounty;
	protected String phoneticCity;
	protected String phoneticDistrictZone;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractAddressDO() {
	}

	public AbstractAddressDO(AddressDO theAddressDO) {
		idPk = theAddressDO.idPk;
		version = theAddressDO.version;
		createdTs = theAddressDO.createdTs;
		deletedTs = theAddressDO.deletedTs;
		updatedTs = theAddressDO.updatedTs;
		updatedByUser = theAddressDO.updatedByUser;
		updatedByTxnId = theAddressDO.updatedByTxnId;
		addressLineOne = theAddressDO.addressLineOne;
		addressLineTwo = theAddressDO.addressLineTwo;
		addressLineThree = theAddressDO.addressLineThree;
		addressLineFour = theAddressDO.addressLineFour;
		streetNumber = theAddressDO.streetNumber;
		streetName = theAddressDO.streetName;
		houseNumber = theAddressDO.houseNumber;
		buildingNumber = theAddressDO.buildingNumber;
		county = theAddressDO.county;
		city = theAddressDO.city;
		districtZone = theAddressDO.districtZone;
		stateProvinceRefkey = theAddressDO.stateProvinceRefkey;
		countryRefkey = theAddressDO.countryRefkey;
		postalCode = theAddressDO.postalCode;
		nearestLandmark = theAddressDO.nearestLandmark;
		boxDesignator = theAddressDO.boxDesignator;
		boxIdentifier = theAddressDO.boxIdentifier;
		nearestRailwayStation = theAddressDO.nearestRailwayStation;
		nearestAirport = theAddressDO.nearestAirport;
		phoneticAddressLineOne = theAddressDO.phoneticAddressLineOne;
		phoneticAddressLineTwo = theAddressDO.phoneticAddressLineTwo;
		phoneticAddressLineThree = theAddressDO.phoneticAddressLineThree;
		phoneticAddressLineFour = theAddressDO.phoneticAddressLineFour;
		phoneticStreetName = theAddressDO.phoneticStreetName;
		phoneticCounty = theAddressDO.phoneticCounty;
		phoneticCity = theAddressDO.phoneticCity;
		phoneticDistrictZone = theAddressDO.phoneticDistrictZone;
		inquiryFilter = theAddressDO.inquiryFilter;
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

	@Column(name = "ADDRESS_LINE_ONE", nullable = false, length = 100)
	public String getAddressLineOne() {
		return this.addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	@Column(name = "ADDRESS_LINE_TWO", length = 100)
	public String getAddressLineTwo() {
		return this.addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	@Column(name = "ADDRESS_LINE_THREE", length = 100)
	public String getAddressLineThree() {
		return this.addressLineThree;
	}

	public void setAddressLineThree(String addressLineThree) {
		this.addressLineThree = addressLineThree;
	}

	@Column(name = "ADDRESS_LINE_FOUR", length = 100)
	public String getAddressLineFour() {
		return this.addressLineFour;
	}

	public void setAddressLineFour(String addressLineFour) {
		this.addressLineFour = addressLineFour;
	}

	@Column(name = "STREET_NUMBER", length = 30)
	public String getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	@Column(name = "STREET_NAME", length = 50)
	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name = "HOUSE_NUMBER", length = 30)
	public String getHouseNumber() {
		return this.houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Column(name = "BUILDING_NUMBER", length = 30)
	public String getBuildingNumber() {
		return this.buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	@Column(name = "COUNTY", length = 50)
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(name = "CITY", nullable = false, length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "DISTRICT_ZONE", length = 100)
	public String getDistrictZone() {
		return this.districtZone;
	}

	public void setDistrictZone(String districtZone) {
		this.districtZone = districtZone;
	}

	@Column(name = "STATE_PROVINCE_REFKEY", length = 50)
	public String getStateProvinceRefkey() {
		return this.stateProvinceRefkey;
	}

	public void setStateProvinceRefkey(String stateProvinceRefkey) {
		this.stateProvinceRefkey = stateProvinceRefkey;
	}

	@Column(name = "COUNTRY_REFKEY", nullable = false, length = 50)
	public String getCountryRefkey() {
		return this.countryRefkey;
	}

	public void setCountryRefkey(String countryRefkey) {
		this.countryRefkey = countryRefkey;
	}

	@Column(name = "POSTAL_CODE", length = 30)
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "NEAREST_LANDMARK", length = 50)
	public String getNearestLandmark() {
		return this.nearestLandmark;
	}

	public void setNearestLandmark(String nearestLandmark) {
		this.nearestLandmark = nearestLandmark;
	}

	@Column(name = "BOX_DESIGNATOR", length = 50)
	public String getBoxDesignator() {
		return this.boxDesignator;
	}

	public void setBoxDesignator(String boxDesignator) {
		this.boxDesignator = boxDesignator;
	}

	@Column(name = "BOX_IDENTIFIER", length = 50)
	public String getBoxIdentifier() {
		return this.boxIdentifier;
	}

	public void setBoxIdentifier(String boxIdentifier) {
		this.boxIdentifier = boxIdentifier;
	}

	@Column(name = "NEAREST_RAILWAY_STATION", length = 50)
	public String getNearestRailwayStation() {
		return this.nearestRailwayStation;
	}

	public void setNearestRailwayStation(String nearestRailwayStation) {
		this.nearestRailwayStation = nearestRailwayStation;
	}

	@Column(name = "NEAREST_AIRPORT", length = 50)
	public String getNearestAirport() {
		return this.nearestAirport;
	}

	public void setNearestAirport(String nearestAirport) {
		this.nearestAirport = nearestAirport;
	}

	@Column(name = "PHONETIC_ADDRESS_LINE_ONE", length = 50)
	public String getPhoneticAddressLineOne() {
		return this.phoneticAddressLineOne;
	}

	public void setPhoneticAddressLineOne(String phoneticAddressLineOne) {
		this.phoneticAddressLineOne = phoneticAddressLineOne;
	}

	@Column(name = "PHONETIC_ADDRESS_LINE_TWO", length = 50)
	public String getPhoneticAddressLineTwo() {
		return this.phoneticAddressLineTwo;
	}

	public void setPhoneticAddressLineTwo(String phoneticAddressLineTwo) {
		this.phoneticAddressLineTwo = phoneticAddressLineTwo;
	}

	@Column(name = "PHONETIC_ADDRESS_LINE_THREE", length = 50)
	public String getPhoneticAddressLineThree() {
		return this.phoneticAddressLineThree;
	}

	public void setPhoneticAddressLineThree(String phoneticAddressLineThree) {
		this.phoneticAddressLineThree = phoneticAddressLineThree;
	}

	@Column(name = "PHONETIC_ADDRESS_LINE_FOUR", length = 50)
	public String getPhoneticAddressLineFour() {
		return this.phoneticAddressLineFour;
	}

	public void setPhoneticAddressLineFour(String phoneticAddressLineFour) {
		this.phoneticAddressLineFour = phoneticAddressLineFour;
	}

	@Column(name = "PHONETIC_STREET_NAME", length = 50)
	public String getPhoneticStreetName() {
		return this.phoneticStreetName;
	}

	public void setPhoneticStreetName(String phoneticStreetName) {
		this.phoneticStreetName = phoneticStreetName;
	}

	@Column(name = "PHONETIC_COUNTY", length = 50)
	public String getPhoneticCounty() {
		return this.phoneticCounty;
	}

	public void setPhoneticCounty(String phoneticCounty) {
		this.phoneticCounty = phoneticCounty;
	}

	@Column(name = "PHONETIC_CITY", length = 100)
	public String getPhoneticCity() {
		return this.phoneticCity;
	}

	public void setPhoneticCity(String phoneticCity) {
		this.phoneticCity = phoneticCity;
	}

	@Column(name = "PHONETIC_DISTRICT_ZONE", length = 100)
	public String getPhoneticDistrictZone() {
		return this.phoneticDistrictZone;
	}

	public void setPhoneticDistrictZone(String phoneticDistrictZone) {
		this.phoneticDistrictZone = phoneticDistrictZone;
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
	 * @return the stateProvinceRefValue
	 */
	@Transient
	public String getStateProvinceRefValue() {
		return stateProvinceRefValue;
	}

	/**
	 * @param stateProvinceRefValue the stateProvinceRefValue to set
	 */
	public void setStateProvinceRefValue(String stateProvinceRefValue) {
		this.stateProvinceRefValue = stateProvinceRefValue;
	}

	/**
	 * @return the countryRefValue
	 */
	@Transient
	public String getCountryRefValue() {
		return countryRefValue;
	}

	/**
	 * @param countryRefValue the countryRefValue to set
	 */
	public void setCountryRefValue(String countryRefValue) {
		this.countryRefValue = countryRefValue;
	}
	
	
}
