package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yugandhar.mdm.abstractdobj.AbstractLePersonDO;

/**
 * DO class for mapped to database LE_PERSON entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractLePersonDO
*/

@Entity
@Table(name = "LE_PERSON")
public class LePersonDO extends AbstractLePersonDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public LePersonDO() {
		super();
	}

	public LePersonDO(LePersonDO theLePersonDO) {
		super(theLePersonDO);
	}
}
