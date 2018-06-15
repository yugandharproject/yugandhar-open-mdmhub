package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractCorporationnamesDO;

/**
 * DO class for mapped to database CORPORATIONNAMES entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractCorporationnamesDO
*/

@Entity
@Table(name = "CORPORATIONNAMES", uniqueConstraints = @UniqueConstraint(columnNames = { "LEGALENTITY_IDPK",
		"CORPORATION_NAME_TYPE_REFKEY" }))
public class CorporationnamesDO extends AbstractCorporationnamesDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public CorporationnamesDO() {
		super();
	}

	public CorporationnamesDO(CorporationnamesDO theCorporationnamesDO) {
		super(theCorporationnamesDO);
	}
}
