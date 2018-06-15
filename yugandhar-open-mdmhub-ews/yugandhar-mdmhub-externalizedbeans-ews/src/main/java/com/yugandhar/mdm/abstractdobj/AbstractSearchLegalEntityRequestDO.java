package com.yugandhar.mdm.abstractdobj;

/**
 * Abstract DO class for SearchLegalEntityRequestDO used for searching Legal entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*/


public abstract class AbstractSearchLegalEntityRequestDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Legal Entity Display Name
	protected String displayName;

	// Person attributes
	protected String personNameOne;
	protected String personLastName;

	// Corporation Attributes
	protected String corporationName;

	// Address Attributes
	protected String addressLineOne;
	protected String addressLineTwo;
	protected String city;
	protected String stateProvinceRefkey;
	protected String countryRefkey;
	protected String postalCode;

	// LE Identification attributes
	protected String identificationTypeRefkey;
	protected String identificationNumber;

	// LE Phone number
	protected String phoneNumber;
	
	//System keys registry
	protected String systemKeysRegistryReferenceId;
	protected String systemKeysRegistrySourceSystemRefkey;
	
	
	//account attributes
	protected String accountidPk;
	protected String sourceSystemRefkey;
	protected String sourceAccountId;
	protected String accountName;
	protected String accountName2;
	protected String accountDescription;
	protected String accountSourceStatusRefkey;
	protected String accountMdmStatusRefkey;

	
	//filter
	protected String inquiryFilter;
	protected String inquiryLevel;

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the personNameOne
	 */
	public String getPersonNameOne() {
		return personNameOne;
	}

	/**
	 * @param personNameOne the personNameOne to set
	 */
	public void setPersonNameOne(String personNameOne) {
		this.personNameOne = personNameOne;
	}

	/**
	 * @return the personLastName
	 */
	public String getPersonLastName() {
		return personLastName;
	}

	/**
	 * @param personLastName the personLastName to set
	 */
	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	/**
	 * @return the corporationName
	 */
	public String getCorporationName() {
		return corporationName;
	}

	/**
	 * @param corporationName the corporationName to set
	 */
	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	/**
	 * @return the addressLineOne
	 */
	public String getAddressLineOne() {
		return addressLineOne;
	}

	/**
	 * @param addressLineOne the addressLineOne to set
	 */
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	/**
	 * @return the addressLineTwo
	 */
	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	/**
	 * @param addressLineTwo the addressLineTwo to set
	 */
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the stateProvinceRefkey
	 */
	public String getStateProvinceRefkey() {
		return stateProvinceRefkey;
	}

	/**
	 * @param stateProvinceRefkey the stateProvinceRefkey to set
	 */
	public void setStateProvinceRefkey(String stateProvinceRefkey) {
		this.stateProvinceRefkey = stateProvinceRefkey;
	}

	/**
	 * @return the countryRefkey
	 */
	public String getCountryRefkey() {
		return countryRefkey;
	}

	/**
	 * @param countryRefkey the countryRefkey to set
	 */
	public void setCountryRefkey(String countryRefkey) {
		this.countryRefkey = countryRefkey;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the identificationTypeRefkey
	 */
	public String getIdentificationTypeRefkey() {
		return identificationTypeRefkey;
	}

	/**
	 * @param identificationTypeRefkey the identificationTypeRefkey to set
	 */
	public void setIdentificationTypeRefkey(String identificationTypeRefkey) {
		this.identificationTypeRefkey = identificationTypeRefkey;
	}

	/**
	 * @return the identificationNumber
	 */
	public String getIdentificationNumber() {
		return identificationNumber;
	}

	/**
	 * @param identificationNumber the identificationNumber to set
	 */
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the systemKeysRegistryReferenceId
	 */
	public String getSystemKeysRegistryReferenceId() {
		return systemKeysRegistryReferenceId;
	}

	/**
	 * @param systemKeysRegistryReferenceId the systemKeysRegistryReferenceId to set
	 */
	public void setSystemKeysRegistryReferenceId(String systemKeysRegistryReferenceId) {
		this.systemKeysRegistryReferenceId = systemKeysRegistryReferenceId;
	}

	/**
	 * @return the systemKeysRegistrySourceSystemRefkey
	 */
	public String getSystemKeysRegistrySourceSystemRefkey() {
		return systemKeysRegistrySourceSystemRefkey;
	}

	/**
	 * @param systemKeysRegistrySourceSystemRefkey the systemKeysRegistrySourceSystemRefkey to set
	 */
	public void setSystemKeysRegistrySourceSystemRefkey(String systemKeysRegistrySourceSystemRefkey) {
		this.systemKeysRegistrySourceSystemRefkey = systemKeysRegistrySourceSystemRefkey;
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
	 * @return the accountidPk
	 */
	public String getAccountidPk() {
		return accountidPk;
	}

	/**
	 * @param accountidPk the accountidPk to set
	 */
	public void setAccountidPk(String accountidPk) {
		this.accountidPk = accountidPk;
	}

	/**
	 * @return the sourceSystemRefkey
	 */
	public String getSourceSystemRefkey() {
		return sourceSystemRefkey;
	}

	/**
	 * @param sourceSystemRefkey the sourceSystemRefkey to set
	 */
	public void setSourceSystemRefkey(String sourceSystemRefkey) {
		this.sourceSystemRefkey = sourceSystemRefkey;
	}

	/**
	 * @return the sourceAccountId
	 */
	public String getSourceAccountId() {
		return sourceAccountId;
	}

	/**
	 * @param sourceAccountId the sourceAccountId to set
	 */
	public void setSourceAccountId(String sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the accountName2
	 */
	public String getAccountName2() {
		return accountName2;
	}

	/**
	 * @param accountName2 the accountName2 to set
	 */
	public void setAccountName2(String accountName2) {
		this.accountName2 = accountName2;
	}

	/**
	 * @return the accountDescription
	 */
	public String getAccountDescription() {
		return accountDescription;
	}

	/**
	 * @param accountDescription the accountDescription to set
	 */
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	/**
	 * @return the accountSourceStatusRefkey
	 */
	public String getAccountSourceStatusRefkey() {
		return accountSourceStatusRefkey;
	}

	/**
	 * @param accountSourceStatusRefkey the accountSourceStatusRefkey to set
	 */
	public void setAccountSourceStatusRefkey(String accountSourceStatusRefkey) {
		this.accountSourceStatusRefkey = accountSourceStatusRefkey;
	}

	/**
	 * @return the accountMdmStatusRefkey
	 */
	public String getAccountMdmStatusRefkey() {
		return accountMdmStatusRefkey;
	}

	/**
	 * @param accountMdmStatusRefkey the accountMdmStatusRefkey to set
	 */
	public void setAccountMdmStatusRefkey(String accountMdmStatusRefkey) {
		this.accountMdmStatusRefkey = accountMdmStatusRefkey;
	}

	

}
