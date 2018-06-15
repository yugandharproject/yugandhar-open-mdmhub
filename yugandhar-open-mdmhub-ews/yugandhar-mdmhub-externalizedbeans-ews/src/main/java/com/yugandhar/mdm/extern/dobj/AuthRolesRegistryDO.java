package com.yugandhar.mdm.extern.dobj;
/* Generated Oct 1, 2017 8:10:37 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractAuthRolesRegistryDO;

/**
 * DO class for mapped to database AUTH_ROLES_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractAuthRolesRegistryDO
*/

@Entity
@Table(name = "AUTH_ROLES_REGISTRY", uniqueConstraints = @UniqueConstraint(columnNames = "ROLE_NAME"))
public class AuthRolesRegistryDO extends AbstractAuthRolesRegistryDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public AuthRolesRegistryDO() {
		super();
	}

	public AuthRolesRegistryDO(AuthRolesRegistryDO theAuthRolesRegistryDO) {
		super(theAuthRolesRegistryDO);
	}
}
