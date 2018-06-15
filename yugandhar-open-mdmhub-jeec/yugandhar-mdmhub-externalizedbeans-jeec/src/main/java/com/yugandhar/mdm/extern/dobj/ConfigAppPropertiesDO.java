package com.yugandhar.mdm.extern.dobj;
/* Generated Aug 7, 2017 3:56:42 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yugandhar.mdm.abstractdobj.AbstractConfigAppPropertiesDO;

/**
 * DO class for mapped to database CONFIG_APP_PROPERTIES entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractConfigAppPropertiesDO
*/

@Entity
@Table(name = "CONFIG_APP_PROPERTIES", uniqueConstraints = @UniqueConstraint(columnNames = "KEY"))
public class ConfigAppPropertiesDO extends AbstractConfigAppPropertiesDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public ConfigAppPropertiesDO() {
	}

}
