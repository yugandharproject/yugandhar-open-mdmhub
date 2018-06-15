package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yugandhar.mdm.abstractdobj.AbstractAccountDO;

/**
 * DO class for mapped to database ACCOUNT entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractAccountDO
*/

@Entity
@Table(name = "ACCOUNT")
public class AccountDO extends AbstractAccountDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public AccountDO() {
		super();
	}

	public AccountDO(AccountDO theAccountDO) {
		super(theAccountDO);
	}
}
