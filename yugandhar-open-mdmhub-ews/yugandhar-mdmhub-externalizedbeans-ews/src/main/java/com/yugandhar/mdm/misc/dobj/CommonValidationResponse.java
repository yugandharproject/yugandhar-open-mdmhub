package com.yugandhar.mdm.misc.dobj;

public class CommonValidationResponse {

	protected boolean validationResult=false;
	protected String errorCode;
	protected String errorMessage;
	protected String stackErrorMessage;
	protected String method;
	protected String serviceComponent;
	protected String additionalErrorMessage;
	
	
	
	/**
	 * @return the validationResult
	 */
	public boolean getValidationResult() {
		return validationResult;
	}
	/**
	 * @param validationResult the validationResult to set
	 */
	public void setValidationResult(boolean validationResult) {
		this.validationResult = validationResult;
	}
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
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
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
	 * @return the systemErrorMessage
	 */
	public String getStackErrorMessage() {
		return stackErrorMessage;
	}
	/**
	 * @param systemErrorMessage the systemErrorMessage to set
	 */
	public void setStackErrorMessage(String systemErrorMessage) {
		stackErrorMessage = systemErrorMessage;
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
	
	
}

