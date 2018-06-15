package com.yugandhar.mdm.abstractdobj;

import javax.persistence.MappedSuperclass;

import com.yugandhar.mdm.extern.dobj.SearchAuthAccessControlDO;

/**
 * Abstract DO class for SearchAuthAccessControlDO class used for searching the
 * Authorization framework
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 */

@MappedSuperclass
public abstract class AbstractSearchAuthAccessControlDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String userName;
	protected String roleName;
	protected String txnserviceName;

	// filter
	protected String inquiryFilter;

	public AbstractSearchAuthAccessControlDO (){
		
		
	}
	
	public AbstractSearchAuthAccessControlDO (SearchAuthAccessControlDO theSearchAuthAccessControlDO){
		
		userName = theSearchAuthAccessControlDO.userName;
		roleName = theSearchAuthAccessControlDO.roleName;
		txnserviceName = theSearchAuthAccessControlDO.txnserviceName;
		inquiryFilter = theSearchAuthAccessControlDO.inquiryFilter;
		
	}
	
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the txnserviceName
	 */
	public String getTxnserviceName() {
		return txnserviceName;
	}

	/**
	 * @param txnserviceName
	 *            the txnserviceName to set
	 */
	public void setTxnserviceName(String txnserviceName) {
		this.txnserviceName = txnserviceName;
	}

	/**
	 * @return the inquiryFilter
	 */
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

}