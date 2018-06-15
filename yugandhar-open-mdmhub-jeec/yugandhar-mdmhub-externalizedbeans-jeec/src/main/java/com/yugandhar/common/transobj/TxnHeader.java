package com.yugandhar.common.transobj;

public class TxnHeader implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Common properties
	protected String requesterLanguage;
	protected String requesterLocale;
	
	//Authorization properties
	protected String userName;
	protected String userRole;
	protected String accessToken;
	
	//correlation properties
	protected String txnCorrelationId;
	protected String txnMessageId;
	
	//Request originator and interfacing system trace
	protected String requestOriginSource;
	protected String requesterSystem;
	
	//Trasaction Status
	protected String transactionResponseCode; //Either SUCCESS or FAIL
	protected String requestTimeStamp;
	protected String responseTimeStamp;
	protected String TotalExecutionTimeMillies;
	
	//Transaction Name
	protected String transactionServiceName;
	
	public TxnHeader(){
		
	}
	
	public TxnHeader(TxnHeader txnHeader){
		requesterLanguage = txnHeader.requesterLanguage;
		requesterLocale = txnHeader.requesterLocale;
		userName = txnHeader.userName;
		userRole = txnHeader.userRole;
		accessToken = txnHeader.accessToken;
		txnCorrelationId = txnHeader.txnCorrelationId;
		txnMessageId = txnHeader.txnMessageId;
		requestOriginSource = txnHeader.requestOriginSource;
		requesterSystem = txnHeader.requesterSystem;
		transactionResponseCode = txnHeader.transactionResponseCode;
		requestTimeStamp = txnHeader.requestTimeStamp;
		responseTimeStamp = txnHeader.responseTimeStamp;
		TotalExecutionTimeMillies = txnHeader.TotalExecutionTimeMillies;
		transactionServiceName = txnHeader.transactionServiceName;
	}
	
	
	/**
	 * @return the requesterLanguage
	 */
	public String getRequesterLanguage() {
		return requesterLanguage;
	}
	/**
	 * @param requesterLanguage the requesterLanguage to set
	 */
	public void setRequesterLanguage(String requesterLanguage) {
		this.requesterLanguage = requesterLanguage;
	}
	/**
	 * @return the requesterLocale
	 */
	public String getRequesterLocale() {
		return requesterLocale;
	}
	/**
	 * @param requesterLocale the requesterLocale to set
	 */
	public void setRequesterLocale(String requesterLocale) {
		this.requesterLocale = requesterLocale;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @return the txnCorrelationId
	 */
	public String getTxnCorrelationId() {
		return txnCorrelationId;
	}
	/**
	 * @param txnCorrelationId the txnCorrelationId to set
	 */
	public void setTxnCorrelationId(String txnCorrelationId) {
		this.txnCorrelationId = txnCorrelationId;
	}
	/**
	 * @return the txnMessageId
	 */
	public String getTxnMessageId() {
		return txnMessageId;
	}
	/**
	 * @param txnMessageId the txnMessageId to set
	 */
	public void setTxnMessageId(String txnMessageId) {
		this.txnMessageId = txnMessageId;
	}
	/**
	 * @return the requestOriginSource
	 */
	public String getRequestOriginSource() {
		return requestOriginSource;
	}
	/**
	 * @param requestOriginSource the requestOriginSource to set
	 */
	public void setRequestOriginSource(String requestOriginSource) {
		this.requestOriginSource = requestOriginSource;
	}
	/**
	 * @return the requesterSystem
	 */
	public String getRequesterSystem() {
		return requesterSystem;
	}
	/**
	 * @param requesterSystem the requesterSystem to set
	 */
	public void setRequesterSystem(String requesterSystem) {
		this.requesterSystem = requesterSystem;
	}
	
	/**
	 * @return the transactionResponseCode
	 */
	public String getTransactionResponseCode() {
		return transactionResponseCode;
	}
	/**
	 * @param transactionResponseCode the transactionResponseCode to set
	 */
	public void setTransactionResponseCode(String transactionResponseCode) {
		this.transactionResponseCode = transactionResponseCode;
	}
	/**
	 * @return the requestTimeStamp
	 */
	public String getRequestTimeStamp() {
		return requestTimeStamp;
	}
	/**
	 * @param requestTimeStamp the requestTimeStamp to set
	 */
	public void setRequestTimeStamp(String requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}
	/**
	 * @return the responseTimeStamp
	 */
	public String getResponseTimeStamp() {
		return responseTimeStamp;
	}
	/**
	 * @param responseTimeStamp the responseTimeStamp to set
	 */
	public void setResponseTimeStamp(String responseTimeStamp) {
		this.responseTimeStamp = responseTimeStamp;
	}
	/**
	 * @return the totalExecutionTime
	 */
	public String getTotalExecutionTimeMillies() {
		return TotalExecutionTimeMillies;
	}
	/**
	 * @param totalExecutionTimeMillies the totalExecutionTime to set
	 */
	public void setTotalExecutionTimeMillies(String totalExecutionTimeMillies) {
		TotalExecutionTimeMillies = totalExecutionTimeMillies;
	}


	/**
	 * @return the transactionServiceName
	 */
	public String getTransactionServiceName() {
		return transactionServiceName;
	}
	/**
	 * @param transactionServiceName the transactionServiceName to set
	 */
	public void setTransactionServiceName(String transactionServiceName) {
		this.transactionServiceName = transactionServiceName;
	}
	

}
