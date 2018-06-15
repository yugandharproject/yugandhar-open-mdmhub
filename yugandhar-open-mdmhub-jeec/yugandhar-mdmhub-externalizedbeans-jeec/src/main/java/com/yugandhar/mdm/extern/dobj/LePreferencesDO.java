package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractLePreferencesDO;

/**
 * DO class for mapped to database LE_PREFERENCES entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractLePreferencesDO
*/

@Entity
@Table(name = "LE_PREFERENCES", uniqueConstraints = @UniqueConstraint(columnNames = { "LEGALENTITY_IDPK",
		"PREFERENCE_TYPE_REFKEY" }))
public class LePreferencesDO extends AbstractLePreferencesDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public LePreferencesDO() {
		super();
	}

	public LePreferencesDO(LePreferencesDO theLePreferencesDO) {
		super(theLePreferencesDO);
	}
}
