package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yugandhar.mdm.abstractdobj.AbstractLeVehicleAssocDO;

/**
 * DO class for mapped to database LE_VEHICLE_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractLeVehicleAssocDO
*/

@Entity
@Table(name = "LE_VEHICLE_ASSOC")
public class LeVehicleAssocDO extends AbstractLeVehicleAssocDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public LeVehicleAssocDO() {
		super();
	}

	public LeVehicleAssocDO(LeVehicleAssocDO theLeVehicleAssocDO) {
		super(theLeVehicleAssocDO);
	}
}
