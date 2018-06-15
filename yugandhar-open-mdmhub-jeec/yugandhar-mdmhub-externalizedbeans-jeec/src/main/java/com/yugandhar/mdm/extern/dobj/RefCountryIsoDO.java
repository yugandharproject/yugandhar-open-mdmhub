package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 5, 2017 1:54:23 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractRefCountryIsoDO;

/**
 * DO class for mapped to database REF_COUNTRY_ISO entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractRefCountryIsoDO
*/

@Entity
@Table(name = "REF_COUNTRY_ISO", uniqueConstraints = @UniqueConstraint(columnNames = { "CONFIG_LANGUAGE_CODE_KEY",
		"KEY" }))
public class RefCountryIsoDO extends AbstractRefCountryIsoDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public RefCountryIsoDO() {
		super();
	}

	public RefCountryIsoDO(RefCountryIsoDO theRefCountryIsoDO) {
		super(theRefCountryIsoDO);
	}
}
