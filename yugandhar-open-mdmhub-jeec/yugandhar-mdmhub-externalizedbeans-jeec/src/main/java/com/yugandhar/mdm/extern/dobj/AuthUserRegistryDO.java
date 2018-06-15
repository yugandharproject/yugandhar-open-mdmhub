package com.yugandhar.mdm.extern.dobj;
/* Generated Oct 2, 2017 1:29:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractAuthUserRegistryDO;

/**
 * DO class for mapped to database AUTH_USER_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractAuthUserRegistryDO
*/

@Entity
@Table(name = "AUTH_USER_REGISTRY", uniqueConstraints = @UniqueConstraint(columnNames = "USER_NAME"))
public class AuthUserRegistryDO extends AbstractAuthUserRegistryDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public AuthUserRegistryDO() {
		super();
	}

	public AuthUserRegistryDO(AuthUserRegistryDO theAuthUserRegistryDO) {
		super(theAuthUserRegistryDO);
	}
}
