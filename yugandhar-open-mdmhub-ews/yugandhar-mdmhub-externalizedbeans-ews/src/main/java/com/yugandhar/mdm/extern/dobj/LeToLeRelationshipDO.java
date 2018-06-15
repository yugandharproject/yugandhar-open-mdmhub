package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractLeToLeRelationshipDO;

/**
 * DO class for mapped to database LE_TO_LE_RELATIONSHIP entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractLeToLeRelationshipDO
*/

@Entity
@Table(name = "LE_TO_LE_RELATIONSHIP", uniqueConstraints = @UniqueConstraint(columnNames = { "FROM_LEGALENTITY_IDPK",
		"TO_LEGALENTITY_IDPK", "LE_RELATIONSHIP_TYPE_REFKEY" }))
public class LeToLeRelationshipDO extends AbstractLeToLeRelationshipDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public LeToLeRelationshipDO() {
		super();
	}

	public LeToLeRelationshipDO(LeToLeRelationshipDO theLeToLeRelationshipDO) {
		super(theLeToLeRelationshipDO);
	}
}
