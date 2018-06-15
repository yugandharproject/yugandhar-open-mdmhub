package com.yugandhar.mdm.extern.dobj;
/* Generated Oct 1, 2017 8:10:37 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractAuthUserroleAccesscontrolDO;

/**
 * DO class for mapped to database AUTH_USERROLE_ACCESSCONTROL entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractAuthUserroleAccesscontrolDO
*/

@Entity
@Table(name = "AUTH_USERROLE_ACCESSCONTROL", uniqueConstraints = @UniqueConstraint(columnNames = { "PROFILE_TYPE",
		"AUTH_USER_ROLE_REGISTRY_IDPK", "CONFIG_TXN_REGISTRY_IDPK" }))
public class AuthUserroleAccesscontrolDO extends AbstractAuthUserroleAccesscontrolDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public AuthUserroleAccesscontrolDO() {
		super();
	}

	public AuthUserroleAccesscontrolDO(AuthUserroleAccesscontrolDO theAuthUserroleAccesscontrolDO) {
		super(theAuthUserroleAccesscontrolDO);
	}
}
