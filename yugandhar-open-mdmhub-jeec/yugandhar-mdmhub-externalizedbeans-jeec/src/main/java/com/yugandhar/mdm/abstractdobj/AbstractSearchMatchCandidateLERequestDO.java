package com.yugandhar.mdm.abstractdobj;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Abstract DO class for SearchMatchCandidateLERequestDO used for searching Legal
 * entity
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 */

public abstract class AbstractSearchMatchCandidateLERequestDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// LE Attributes
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date dateOfBirth;
	protected String genderRefkey;

	protected List<String> personNameOneList;
	protected List<String> personLastNameList;

	protected List<String> corporationNameList;

	protected List<String> identificationTypeRefkeyList;
	protected List<String> identificationNumberList;

	// Address
	protected List<String> addressLineOneList;
	protected List<String> addressLineTwoList;
	protected List<String> addressLineThreeList;
	protected List<String> addressLineFourList;
	protected List<String> streetNameList;
	protected List<String> cityList;
	protected List<String> countryRefkeyList;
	protected List<String> postalCodeList;

	//Phone Numbers
	protected List<String> phoneNumberList;

	
	//Corporation Name Phonetic
	protected List<String> phoneticCorporationNameList;

	//PersonName Phonetic
	protected List<String> phoneticPersonNameOneList;
	protected List<String> phoneticPersonLastNameList;

	// Address - phontic attributes
	protected List<String> phoneticAddressLineOneList;
	protected List<String> phoneticAddressLineTwoList;
	protected List<String> phoneticAddressLineThreeList;
	protected List<String> phoneticAddressLineFourList;
	protected List<String> phoneticStreetNameList;
	protected List<String> phoneticCityList;

	//is search be performed based on phonetic attributes?
	protected boolean isPhoneticSearch = false;
	// filter
	protected String inquiryFilter;
	protected String inquiryLevel;
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the genderRefkey
	 */
	public String getGenderRefkey() {
		return genderRefkey;
	}
	/**
	 * @param genderRefkey the genderRefkey to set
	 */
	public void setGenderRefkey(String genderRefkey) {
		this.genderRefkey = genderRefkey;
	}
	/**
	 * @return the personNameOneList
	 */
	public List<String> getPersonNameOneList() {
		return personNameOneList;
	}
	/**
	 * @param personNameOneList the personNameOneList to set
	 */
	public void setPersonNameOneList(List<String> personNameOneList) {
		this.personNameOneList = personNameOneList;
	}
	/**
	 * @return the personLastNameList
	 */
	public List<String> getPersonLastNameList() {
		return personLastNameList;
	}
	/**
	 * @param personLastNameList the personLastNameList to set
	 */
	public void setPersonLastNameList(List<String> personLastNameList) {
		this.personLastNameList = personLastNameList;
	}
	/**
	 * @return the corporationNameList
	 */
	public List<String> getCorporationNameList() {
		return corporationNameList;
	}
	/**
	 * @param corporationNameList the corporationNameList to set
	 */
	public void setCorporationNameList(List<String> corporationNameList) {
		this.corporationNameList = corporationNameList;
	}
	/**
	 * @return the identificationTypeRefkeyList
	 */
	public List<String> getIdentificationTypeRefkeyList() {
		return identificationTypeRefkeyList;
	}
	/**
	 * @param identificationTypeRefkeyList the identificationTypeRefkeyList to set
	 */
	public void setIdentificationTypeRefkeyList(List<String> identificationTypeRefkeyList) {
		this.identificationTypeRefkeyList = identificationTypeRefkeyList;
	}
	/**
	 * @return the identificationNumberList
	 */
	public List<String> getIdentificationNumberList() {
		return identificationNumberList;
	}
	/**
	 * @param identificationNumberList the identificationNumberList to set
	 */
	public void setIdentificationNumberList(List<String> identificationNumberList) {
		this.identificationNumberList = identificationNumberList;
	}
	/**
	 * @return the addressLineOneList
	 */
	public List<String> getAddressLineOneList() {
		return addressLineOneList;
	}
	/**
	 * @param addressLineOneList the addressLineOneList to set
	 */
	public void setAddressLineOneList(List<String> addressLineOneList) {
		this.addressLineOneList = addressLineOneList;
	}
	/**
	 * @return the addressLineTwoList
	 */
	public List<String> getAddressLineTwoList() {
		return addressLineTwoList;
	}
	/**
	 * @param addressLineTwoList the addressLineTwoList to set
	 */
	public void setAddressLineTwoList(List<String> addressLineTwoList) {
		this.addressLineTwoList = addressLineTwoList;
	}
	/**
	 * @return the addressLineThreeList
	 */
	public List<String> getAddressLineThreeList() {
		return addressLineThreeList;
	}
	/**
	 * @param addressLineThreeList the addressLineThreeList to set
	 */
	public void setAddressLineThreeList(List<String> addressLineThreeList) {
		this.addressLineThreeList = addressLineThreeList;
	}
	/**
	 * @return the addressLineFourList
	 */
	public List<String> getAddressLineFourList() {
		return addressLineFourList;
	}
	/**
	 * @param addressLineFourList the addressLineFourList to set
	 */
	public void setAddressLineFourList(List<String> addressLineFourList) {
		this.addressLineFourList = addressLineFourList;
	}
	/**
	 * @return the streetNameList
	 */
	public List<String> getStreetNameList() {
		return streetNameList;
	}
	/**
	 * @param streetNameList the streetNameList to set
	 */
	public void setStreetNameList(List<String> streetNameList) {
		this.streetNameList = streetNameList;
	}
	/**
	 * @return the cityList
	 */
	public List<String> getCityList() {
		return cityList;
	}
	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}
	/**
	 * @return the countryRefkeyList
	 */
	public List<String> getCountryRefkeyList() {
		return countryRefkeyList;
	}
	/**
	 * @param countryRefkeyList the countryRefkeyList to set
	 */
	public void setCountryRefkeyList(List<String> countryRefkeyList) {
		this.countryRefkeyList = countryRefkeyList;
	}
	/**
	 * @return the postalCodeList
	 */
	public List<String> getPostalCodeList() {
		return postalCodeList;
	}
	/**
	 * @param postalCodeList the postalCodeList to set
	 */
	public void setPostalCodeList(List<String> postalCodeList) {
		this.postalCodeList = postalCodeList;
	}
	/**
	 * @return the phoneNumberList
	 */
	public List<String> getPhoneNumberList() {
		return phoneNumberList;
	}
	/**
	 * @param phoneNumberList the phoneNumberList to set
	 */
	public void setPhoneNumberList(List<String> phoneNumberList) {
		this.phoneNumberList = phoneNumberList;
	}
	/**
	 * @return the phoneticCorporationNameList
	 */
	public List<String> getPhoneticCorporationNameList() {
		return phoneticCorporationNameList;
	}
	/**
	 * @param phoneticCorporationNameList the phoneticCorporationNameList to set
	 */
	public void setPhoneticCorporationNameList(List<String> phoneticCorporationNameList) {
		this.phoneticCorporationNameList = phoneticCorporationNameList;
	}
	
	/**
	 * @return the phoneticPersonNameOneList
	 */
	public List<String> getPhoneticPersonNameOneList() {
		return phoneticPersonNameOneList;
	}
	/**
	 * @param phoneticPersonNameOneList the phoneticPersonNameOneList to set
	 */
	public void setPhoneticPersonNameOneList(List<String> phoneticPersonNameOneList) {
		this.phoneticPersonNameOneList = phoneticPersonNameOneList;
	}
	/**
	 * @return the phoneticPersonLastNameList
	 */
	public List<String> getPhoneticPersonLastNameList() {
		return phoneticPersonLastNameList;
	}
	/**
	 * @param phoneticPersonLastNameList the phoneticPersonLastNameList to set
	 */
	public void setPhoneticPersonLastNameList(List<String> phoneticPersonLastNameList) {
		this.phoneticPersonLastNameList = phoneticPersonLastNameList;
	}
	/**
	 * @return the phoneticAddressLineOneList
	 */
	public List<String> getPhoneticAddressLineOneList() {
		return phoneticAddressLineOneList;
	}
	/**
	 * @param phoneticAddressLineOneList the phoneticAddressLineOneList to set
	 */
	public void setPhoneticAddressLineOneList(List<String> phoneticAddressLineOneList) {
		this.phoneticAddressLineOneList = phoneticAddressLineOneList;
	}
	/**
	 * @return the phoneticAddressLineTwoList
	 */
	public List<String> getPhoneticAddressLineTwoList() {
		return phoneticAddressLineTwoList;
	}
	/**
	 * @param phoneticAddressLineTwoList the phoneticAddressLineTwoList to set
	 */
	public void setPhoneticAddressLineTwoList(List<String> phoneticAddressLineTwoList) {
		this.phoneticAddressLineTwoList = phoneticAddressLineTwoList;
	}
	/**
	 * @return the phoneticAddressLineThreeList
	 */
	public List<String> getPhoneticAddressLineThreeList() {
		return phoneticAddressLineThreeList;
	}
	/**
	 * @param phoneticAddressLineThreeList the phoneticAddressLineThreeList to set
	 */
	public void setPhoneticAddressLineThreeList(List<String> phoneticAddressLineThreeList) {
		this.phoneticAddressLineThreeList = phoneticAddressLineThreeList;
	}
	/**
	 * @return the phoneticAddressLineFourList
	 */
	public List<String> getPhoneticAddressLineFourList() {
		return phoneticAddressLineFourList;
	}
	/**
	 * @param phoneticAddressLineFourList the phoneticAddressLineFourList to set
	 */
	public void setPhoneticAddressLineFourList(List<String> phoneticAddressLineFourList) {
		this.phoneticAddressLineFourList = phoneticAddressLineFourList;
	}
	/**
	 * @return the phoneticStreetNameList
	 */
	public List<String> getPhoneticStreetNameList() {
		return phoneticStreetNameList;
	}
	/**
	 * @param phoneticStreetNameList the phoneticStreetNameList to set
	 */
	public void setPhoneticStreetNameList(List<String> phoneticStreetNameList) {
		this.phoneticStreetNameList = phoneticStreetNameList;
	}
	/**
	 * @return the phoneticCityList
	 */
	public List<String> getPhoneticCityList() {
		return phoneticCityList;
	}
	/**
	 * @param phoneticCityList the phoneticCityList to set
	 */
	public void setPhoneticCityList(List<String> phoneticCityList) {
		this.phoneticCityList = phoneticCityList;
	}
	/**
	 * @return the inquiryFilter
	 */
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
	 * @return the inquiryLevel
	 */
	public String getInquiryLevel() {
		return inquiryLevel;
	}
	/**
	 * @param inquiryLevel the inquiryLevel to set
	 */
	public void setInquiryLevel(String inquiryLevel) {
		this.inquiryLevel = inquiryLevel;
	}
	/**
	 * @return the isPhoneticSearch
	 */
	public boolean isPhoneticSearch() {
		return isPhoneticSearch;
	}
	/**
	 * @param isPhoneticSearch the isPhoneticSearch to set
	 */
	public void setPhoneticSearch(boolean isPhoneticSearch) {
		this.isPhoneticSearch = isPhoneticSearch;
	}
	
	
	
	
	
	
}
