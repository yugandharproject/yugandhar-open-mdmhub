package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractAccountPhoneAssocDO;

/**
 * DO class for mapped to database ACCOUNT_PHONE_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractAccountPhoneAssocDO
*/

@Entity
@Table(name = "ACCOUNT_PHONE_ASSOC", uniqueConstraints = @UniqueConstraint(columnNames = { "ACCOUNT_IDPK",
		"PHONE_TYPE_REFKEY" }))
public class AccountPhoneAssocDO extends AbstractAccountPhoneAssocDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public AccountPhoneAssocDO() {
		super();
	}

	public AccountPhoneAssocDO(AccountPhoneAssocDO theAccountPhoneAssocDO) {
		super(theAccountPhoneAssocDO);
	}
}
