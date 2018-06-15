package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractLeSystemKeysRegistryDO;

/**
 * DO class for mapped to database LE_SYSTEM_KEYS_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractLeSystemKeysRegistryDO
*/

@Entity
@Table(name = "LE_SYSTEM_KEYS_REGISTRY", uniqueConstraints = @UniqueConstraint(columnNames = { "SOURCE_SYSTEM_REFKEY",
		"LEGALENTITY_IDPK" }))
public class LeSystemKeysRegistryDO extends AbstractLeSystemKeysRegistryDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public LeSystemKeysRegistryDO() {
		super();
	}

	public LeSystemKeysRegistryDO(LeSystemKeysRegistryDO theLeSystemKeysRegistryDO) {
		super(theLeSystemKeysRegistryDO);
	}
}
