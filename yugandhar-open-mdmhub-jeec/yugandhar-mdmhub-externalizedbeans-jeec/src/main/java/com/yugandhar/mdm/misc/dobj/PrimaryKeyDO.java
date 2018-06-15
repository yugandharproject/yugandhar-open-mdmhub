package com.yugandhar.mdm.misc.dobj;
/* Generated Jun 28, 2017 11:57:37 AM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

/**
 * Primary Key DO class which will be used to create a record with user defined
 * primary key attributes. Set the idPk attribute of this object inside any
 * entity DO object so that the persist method picks the idpk value
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 */

public class PrimaryKeyDO {
	private String idPk;

	/**
	 * @return the idPk
	 */
	public String getIdPk() {
		return idPk;
	}

	/**
	 * @param idPk
	 *            the idPk to set
	 */
	public void setIdPk(String idPk) {
		this.idPk = idPk;
	}

}
