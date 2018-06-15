package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractPersonnamesDO;

/**
 * DO class for mapped to database PERSONNAMES entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractPersonnamesDO
*/

@Entity
@Table(name = "PERSONNAMES", uniqueConstraints = @UniqueConstraint(columnNames = { "LEGALENTITY_IDPK",
		"PERSONNAME_TYPE_REFKEY" }))
public class PersonnamesDO extends AbstractPersonnamesDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public PersonnamesDO() {
		super();
	}

	public PersonnamesDO(PersonnamesDO thePersonnamesDO) {
		super(thePersonnamesDO);
	}
}
