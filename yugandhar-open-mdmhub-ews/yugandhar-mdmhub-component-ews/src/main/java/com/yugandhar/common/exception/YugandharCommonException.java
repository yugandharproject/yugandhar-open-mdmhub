package com.yugandhar.common.exception;

import com.yugandhar.common.transobj.TxnHeader;

public class YugandharCommonException extends RuntimeException  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String errorCode;
	protected String errorMessage;
	protected String stackErrorMessage;
	protected String additionalErrorMessage;
	protected String serviceComponent;
	protected String responseCode="FAIL";
	protected TxnHeader txnHeader;
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the stackErrorMessage
	 */
	public String getStackErrorMessage() {
		return stackErrorMessage;
	}
	/**
	 * @param stackErrorMessage the stackErrorMessage to set
	 */
	public void setStackErrorMessage(String stackErrorMessage) {
		this.stackErrorMessage = stackErrorMessage;
	}
	/**
	 * @return the additionalErrorMessage
	 */
	public String getAdditionalErrorMessage() {
		return additionalErrorMessage;
	}
	/**
	 * @param additionalErrorMessage the additionalErrorMessage to set
	 */
	public void setAdditionalErrorMessage(String additionalErrorMessage) {
		this.additionalErrorMessage = additionalErrorMessage;
	}
	/**
	 * @return the serviceComponent
	 */
	public String getServiceComponent() {
		return serviceComponent;
	}
	/**
	 * @param serviceComponent the serviceComponent to set
	 */
	public void setServiceComponent(String serviceComponent) {
		this.serviceComponent = serviceComponent;
	}
	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the txnHeader
	 */
	public TxnHeader getTxnHeader() {
		return txnHeader;
	}
	/**
	 * @param txnHeader the txnHeader to set
	 */
	public void setTxnHeader(TxnHeader txnHeader) {
		this.txnHeader = txnHeader;
	}

	
	
	
}
