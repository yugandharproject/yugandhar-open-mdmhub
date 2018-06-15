package com.yugandhar.common.transobj;

import com.yugandhar.common.extern.transferobj.TxnPayload;

public class TxnTransferObj implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String responseCode="FAIL";
	protected TxnHeader txnHeader;
	protected TxnPayload txnPayload;
	
	public TxnTransferObj() {
		 txnPayload = new TxnPayload();
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

	/**
	 * @return the txnPayload
	 */
	public TxnPayload getTxnPayload() {
		return txnPayload;
	}
	/**
	 * @param txnPayload the txnPayload to set
	 */
	public void setTxnPayload(TxnPayload txnPayload) {
		this.txnPayload = txnPayload;
	}

		

	
}
