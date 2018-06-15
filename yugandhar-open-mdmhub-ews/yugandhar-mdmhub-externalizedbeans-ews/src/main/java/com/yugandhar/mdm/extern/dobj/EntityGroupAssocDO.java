package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractEntityGroupAssocDO;

/**
 * DO class for mapped to database ENTITY_GROUP_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractEntityGroupAssocDO
*/

@Entity
@Table(name = "ENTITY_GROUP_ASSOC", uniqueConstraints = @UniqueConstraint(columnNames = { "ENTITY_OBJECT_TYPE_REFKEY",
		"ENTITY_IDPK", "ENTITY_GROUP_IDPK", "ASSOC_TYPE_REFKEY" }))
public class EntityGroupAssocDO extends AbstractEntityGroupAssocDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public EntityGroupAssocDO() {
		super();
	}

	public EntityGroupAssocDO(EntityGroupAssocDO theEntityGroupAssocDO) {
		super(theEntityGroupAssocDO);
	}
}
