package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractLePropertyAssocDO;

/**
 * DO class for mapped to database LE_PROPERTY_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractLePropertyAssocDO
*/

@Entity
@Table(name = "LE_PROPERTY_ASSOC", uniqueConstraints = @UniqueConstraint(columnNames = { "PROPERTY_IDPK",
		"LEGALENTITY_IDPK", "PROPERTY_LE_RELTYPE_REFKEY" }))
public class LePropertyAssocDO extends AbstractLePropertyAssocDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public LePropertyAssocDO() {
		super();
	}

	public LePropertyAssocDO(LePropertyAssocDO theLePropertyAssocDO) {
		super(theLePropertyAssocDO);
	}
}
