package com.yugandhar.mdm.abstractdobj;
/* Generated Jul 1, 2017 2:56:24 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yugandhar.mdm.extern.dobj.AccountAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.AccountPhoneAssocDO;
import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.mdm.extern.dobj.AccountDO;

/**
 * Abstract DO class for AccountDO class mapped to database ACCOUNT entity
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * @see com.yugandhar.mdm.extern.dobj.AccountDO
 */

@MappedSuperclass
public abstract class AbstractAccountDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String contractSignedLangRefkey;
	protected String contractSignedLangRefValue;
	protected String currencyRefkey;
	protected String currencyRefValue;
	protected String billingModeTypeRefkey;
	protected String billingModeTypeRefValue;
	protected Integer frequencyOfPayment;
	protected String lobtypeRefkey;
	protected String lobtypeRefValue;
	protected String lobDescription;
	protected String sourceSystemRefkey;
	protected String sourceSystemRefValue;
	protected String sourceAccountId;
	protected String managedbyBuCode;
	protected String managedbyBuId;
	protected String branchCodeRefkey;
	protected String branchCodeRefValue;
	protected String accountName;
	protected String accountName2;
	protected String accountDescription;
	protected String accountSourceStatusRefkey;
	protected String accountSourceStatusRefValue;
	protected String accountMdmStatusRefkey;
	protected String accountMdmStatusRefValue;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date signedDate;
	protected Date signedPlace;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date executedDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date terminatedDate;
	protected String terminationReasonRefkey;
	protected String terminationReasonRefValue;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	protected String inquiryLevel;

	// refered entities
	protected List<AccountAddressAssocDO> accountAddressAssocDOList;
	protected List<AccountPhoneAssocDO> accountPhoneAssocDOList;
	protected List<LeAccountAssocDO> leAccountAssocDOList;

	public AbstractAccountDO() {
	}

	public AbstractAccountDO(AccountDO theAccountDO) {
		idPk = theAccountDO.idPk;
		version = theAccountDO.version;
		createdTs = theAccountDO.createdTs;
		deletedTs = theAccountDO.deletedTs;
		updatedTs = theAccountDO.updatedTs;
		updatedByUser = theAccountDO.updatedByUser;
		updatedByTxnId = theAccountDO.updatedByTxnId;
		contractSignedLangRefkey = theAccountDO.contractSignedLangRefkey;
		currencyRefkey = theAccountDO.currencyRefkey;
		billingModeTypeRefkey = theAccountDO.billingModeTypeRefkey;
		frequencyOfPayment = theAccountDO.frequencyOfPayment;
		lobtypeRefkey = theAccountDO.lobtypeRefkey;
		lobDescription = theAccountDO.lobDescription;
		sourceSystemRefkey = theAccountDO.sourceSystemRefkey;
		sourceAccountId = theAccountDO.sourceAccountId;
		managedbyBuCode = theAccountDO.managedbyBuCode;
		managedbyBuId = theAccountDO.managedbyBuId;
		branchCodeRefkey = theAccountDO.branchCodeRefkey;
		accountName = theAccountDO.accountName;
		accountName2 = theAccountDO.accountName2;
		accountDescription = theAccountDO.accountDescription;
		accountSourceStatusRefkey = theAccountDO.accountSourceStatusRefkey;
		accountMdmStatusRefkey = theAccountDO.accountMdmStatusRefkey;
		signedDate = theAccountDO.signedDate;
		signedPlace = theAccountDO.signedPlace;
		executedDate = theAccountDO.executedDate;
		terminatedDate = theAccountDO.terminatedDate;
		terminationReasonRefkey = theAccountDO.terminationReasonRefkey;
		inquiryFilter = theAccountDO.inquiryFilter;
		
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

	@Column(name = "CONTRACT_SIGNED_LANG_REFKEY", length = 50)
	public String getContractSignedLangRefkey() {
		return this.contractSignedLangRefkey;
	}

	public void setContractSignedLangRefkey(String contractSignedLangRefkey) {
		this.contractSignedLangRefkey = contractSignedLangRefkey;
	}

	@Column(name = "CURRENCY_REFKEY", length = 50)
	public String getCurrencyRefkey() {
		return this.currencyRefkey;
	}

	public void setCurrencyRefkey(String currencyRefkey) {
		this.currencyRefkey = currencyRefkey;
	}

	@Column(name = "BILLING_MODE_TYPE_REFKEY", length = 50)
	public String getBillingModeTypeRefkey() {
		return this.billingModeTypeRefkey;
	}

	public void setBillingModeTypeRefkey(String billingModeTypeRefkey) {
		this.billingModeTypeRefkey = billingModeTypeRefkey;
	}

	@Column(name = "FREQUENCY_OF_PAYMENT", scale = 0)
	public Integer getFrequencyOfPayment() {
		return this.frequencyOfPayment;
	}

	public void setFrequencyOfPayment(Integer frequencyOfPayment) {
		this.frequencyOfPayment = frequencyOfPayment;
	}

	@Column(name = "LOBTYPE_REFKEY", length = 50)
	public String getLobtypeRefkey() {
		return this.lobtypeRefkey;
	}

	public void setLobtypeRefkey(String lobtypeRefkey) {
		this.lobtypeRefkey = lobtypeRefkey;
	}

	@Column(name = "LOB_DESCRIPTION", length = 100)
	public String getLobDescription() {
		return this.lobDescription;
	}

	public void setLobDescription(String lobDescription) {
		this.lobDescription = lobDescription;
	}

	@Column(name = "SOURCE_SYSTEM_REFKEY", length = 50)
	public String getSourceSystemRefkey() {
		return this.sourceSystemRefkey;
	}

	public void setSourceSystemRefkey(String sourceSystemRefkey) {
		this.sourceSystemRefkey = sourceSystemRefkey;
	}

	@Column(name = "SOURCE_ACCOUNT_ID", length = 50)
	public String getSourceAccountId() {
		return this.sourceAccountId;
	}

	public void setSourceAccountId(String sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	@Column(name = "MANAGEDBY_BU_CODE", length = 30)
	public String getManagedbyBuCode() {
		return this.managedbyBuCode;
	}

	public void setManagedbyBuCode(String managedbyBuCode) {
		this.managedbyBuCode = managedbyBuCode;
	}

	@Column(name = "MANAGEDBY_BU_ID", length = 30)
	public String getManagedbyBuId() {
		return this.managedbyBuId;
	}

	public void setManagedbyBuId(String managedbyBuId) {
		this.managedbyBuId = managedbyBuId;
	}

	@Column(name = "BRANCH_CODE_REFKEY", length = 50)
	public String getBranchCodeRefkey() {
		return this.branchCodeRefkey;
	}

	public void setBranchCodeRefkey(String branchCodeRefkey) {
		this.branchCodeRefkey = branchCodeRefkey;
	}

	@Column(name = "ACCOUNT_NAME", nullable = false, length = 50)
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "ACCOUNT_NAME2", length = 50)
	public String getAccountName2() {
		return this.accountName2;
	}

	public void setAccountName2(String accountName2) {
		this.accountName2 = accountName2;
	}

	@Column(name = "ACCOUNT_DESCRIPTION", length = 100)
	public String getAccountDescription() {
		return this.accountDescription;
	}

	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	@Column(name = "ACCOUNT_SOURCE_STATUS_REFKEY", length = 50)
	public String getAccountSourceStatusRefkey() {
		return this.accountSourceStatusRefkey;
	}

	public void setAccountSourceStatusRefkey(String accountSourceStatusRefkey) {
		this.accountSourceStatusRefkey = accountSourceStatusRefkey;
	}

	@Column(name = "ACCOUNT_MDM_STATUS_REFKEY", length = 50)
	public String getAccountMdmStatusRefkey() {
		return this.accountMdmStatusRefkey;
	}

	public void setAccountMdmStatusRefkey(String accountMdmStatusRefkey) {
		this.accountMdmStatusRefkey = accountMdmStatusRefkey;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SIGNED_DATE", length = 7)
	public Date getSignedDate() {
		return this.signedDate;
	}

	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SIGNED_PLACE", length = 7)
	public Date getSignedPlace() {
		return this.signedPlace;
	}

	public void setSignedPlace(Date signedPlace) {
		this.signedPlace = signedPlace;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXECUTED_DATE", length = 7)
	public Date getExecutedDate() {
		return this.executedDate;
	}

	public void setExecutedDate(Date executedDate) {
		this.executedDate = executedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TERMINATED_DATE", length = 7)
	public Date getTerminatedDate() {
		return this.terminatedDate;
	}

	public void setTerminatedDate(Date terminatedDate) {
		this.terminatedDate = terminatedDate;
	}

	@Column(name = "TERMINATION_REASON_REFKEY", length = 50)
	public String getTerminationReasonRefkey() {
		return this.terminationReasonRefkey;
	}

	public void setTerminationReasonRefkey(String terminationReasonRefkey) {
		this.terminationReasonRefkey = terminationReasonRefkey;
	}

	/**
	 * @return the inquiryFilter
	 */
	@Transient
	public String getInquiryFilter() {
		return inquiryFilter;
	}

	/**
	 * @param inquiryFilter
	 *            the inquiryFilter to set
	 */
	public void setInquiryFilter(String inquiryFilter) {
		this.inquiryFilter = inquiryFilter;
	}

	/**
	 * @return the accountAddressAssocDOList
	 */
	@Transient
	public List<AccountAddressAssocDO> getAccountAddressAssocDOList() {
		return accountAddressAssocDOList;
	}

	/**
	 * @param accountAddressAssocDOList
	 *            the accountAddressAssocDOList to set
	 */
	public void setAccountAddressAssocDOList(List<AccountAddressAssocDO> accountAddressAssocDOList) {
		this.accountAddressAssocDOList = accountAddressAssocDOList;
	}

	/**
	 * @return the accountPhoneAssocDOList
	 */
	@Transient
	public List<AccountPhoneAssocDO> getAccountPhoneAssocDOList() {
		return accountPhoneAssocDOList;
	}

	/**
	 * @param accountPhoneAssocDOList
	 *            the accountPhoneAssocDOList to set
	 */
	public void setAccountPhoneAssocDOList(List<AccountPhoneAssocDO> accountPhoneAssocDOList) {
		this.accountPhoneAssocDOList = accountPhoneAssocDOList;
	}

	/**
	 * @return the primaryKeyDO
	 */
	@Transient
	public PrimaryKeyDO getPrimaryKeyDO() {
		return primaryKeyDO;
	}

	/**
	 * @param primaryKeyDO
	 *            the primaryKeyDO to set
	 */
	public void setPrimaryKeyDO(PrimaryKeyDO primaryKeyDO) {
		this.primaryKeyDO = primaryKeyDO;
	}

	/**
	 * @return the inquiryLevel
	 */
	@Transient
	public String getInquiryLevel() {
		return inquiryLevel;
	}

	/**
	 * @param inquiryLevel
	 *            the inquiryLevel to set
	 */
	public void setInquiryLevel(String inquiryLevel) {
		this.inquiryLevel = inquiryLevel;
	}

	/**
	 * @return the currencyRefValue
	 */
	@Transient
	public String getCurrencyRefValue() {
		return currencyRefValue;
	}

	/**
	 * @param currencyRefValue
	 *            the currencyRefValue to set
	 */
	public void setCurrencyRefValue(String currencyRefValue) {
		this.currencyRefValue = currencyRefValue;
	}

	/**
	 * @return the contractSignedLangRefValue
	 */
	@Transient
	public String getContractSignedLangRefValue() {
		return contractSignedLangRefValue;
	}

	/**
	 * @param contractSignedLangRefValue
	 *            the contractSignedLangRefValue to set
	 */
	public void setContractSignedLangRefValue(String contractSignedLangRefValue) {
		this.contractSignedLangRefValue = contractSignedLangRefValue;
	}

	/**
	 * @return the billingModeTypeRefValue
	 */
	@Transient
	public String getBillingModeTypeRefValue() {
		return billingModeTypeRefValue;
	}

	/**
	 * @param billingModeTypeRefValue
	 *            the billingModeTypeRefValue to set
	 */
	public void setBillingModeTypeRefValue(String billingModeTypeRefValue) {
		this.billingModeTypeRefValue = billingModeTypeRefValue;
	}

	/**
	 * @return the lobtypeRefValue
	 */
	@Transient
	public String getLobtypeRefValue() {
		return lobtypeRefValue;
	}

	/**
	 * @param lobtypeRefValue
	 *            the lobtypeRefValue to set
	 */
	public void setLobtypeRefValue(String lobtypeRefValue) {
		this.lobtypeRefValue = lobtypeRefValue;
	}

	/**
	 * @return the sourceSystemRefValue
	 */
	@Transient
	public String getSourceSystemRefValue() {
		return sourceSystemRefValue;
	}

	/**
	 * @param sourceSystemRefValue
	 *            the sourceSystemRefValue to set
	 */
	public void setSourceSystemRefValue(String sourceSystemRefValue) {
		this.sourceSystemRefValue = sourceSystemRefValue;
	}

	/**
	 * @return the branchCodeRefValue
	 */
	@Transient
	public String getBranchCodeRefValue() {
		return branchCodeRefValue;
	}

	/**
	 * @param branchCodeRefValue
	 *            the branchCodeRefValue to set
	 */
	public void setBranchCodeRefValue(String branchCodeRefValue) {
		this.branchCodeRefValue = branchCodeRefValue;
	}

	/**
	 * @return the accountSourceStatusRefValue
	 */
	@Transient
	public String getAccountSourceStatusRefValue() {
		return accountSourceStatusRefValue;
	}

	/**
	 * @param accountSourceStatusRefValue
	 *            the accountSourceStatusRefValue to set
	 */
	public void setAccountSourceStatusRefValue(String accountSourceStatusRefValue) {
		this.accountSourceStatusRefValue = accountSourceStatusRefValue;
	}

	/**
	 * @return the accountMdmStatusRefValue
	 */
	@Transient
	public String getAccountMdmStatusRefValue() {
		return accountMdmStatusRefValue;
	}

	/**
	 * @param accountMdmStatusRefValue
	 *            the accountMdmStatusRefValue to set
	 */
	public void setAccountMdmStatusRefValue(String accountMdmStatusRefValue) {
		this.accountMdmStatusRefValue = accountMdmStatusRefValue;
	}

	/**
	 * @return the terminationReasonRefValue
	 */
	@Transient
	public String getTerminationReasonRefValue() {
		return terminationReasonRefValue;
	}

	/**
	 * @param terminationReasonRefValue
	 *            the terminationReasonRefValue to set
	 */
	public void setTerminationReasonRefValue(String terminationReasonRefValue) {
		this.terminationReasonRefValue = terminationReasonRefValue;
	}
	
	/**
	 * @return the leAccountAssocDOList
	 */
	@Transient
	public List<LeAccountAssocDO> getLeAccountAssocDOList() {
		return leAccountAssocDOList;
	}

	/**
	 * @param leAccountAssocDOList
	 *            the leAccountAssocDOList to set
	 */
	public void setLeAccountAssocDOList(List<LeAccountAssocDO> leAccountAssocDOList) {
		this.leAccountAssocDOList = leAccountAssocDOList;
	}

}
