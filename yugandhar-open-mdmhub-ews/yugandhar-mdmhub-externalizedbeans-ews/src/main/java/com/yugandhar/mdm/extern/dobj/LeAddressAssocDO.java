package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractLeAddressAssocDO;

/**
 * DO class for mapped to database LE_ADDRESS_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractLeAddressAssocDO
*/

@Entity
@Table(name = "LE_ADDRESS_ASSOC", uniqueConstraints = @UniqueConstraint(columnNames = { "LEGALENTITY_IDPK",
		"ADDRESS_TYPE_REFKEY", "ADDRESS_SUBTYPE_REFKEY" }))
public class LeAddressAssocDO extends AbstractLeAddressAssocDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public LeAddressAssocDO() {
		super();
	}

	public LeAddressAssocDO(LeAddressAssocDO theLeAddressAssocDO) {
		super(theLeAddressAssocDO);
	}
}
