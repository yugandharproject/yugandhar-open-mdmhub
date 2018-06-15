package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractAccountAddressAssocDO;

/**
 * DO class for mapped to database ACCOUNT_ADDRESS_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractAccountAddressAssocDO
*/

@Entity
@Table(name = "ACCOUNT_ADDRESS_ASSOC", uniqueConstraints = @UniqueConstraint(columnNames = { "ACCOUNT_IDPK",
		"ADDRESS_IDPK", "ADDRESS_TYPE_REFKEY" }))
public class AccountAddressAssocDO extends AbstractAccountAddressAssocDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public AccountAddressAssocDO() {
		super();
	}

	public AccountAddressAssocDO(AccountAddressAssocDO theAccountAddressAssocDO) {
		super(theAccountAddressAssocDO);
	}
}
