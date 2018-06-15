package com.yugandhar.mdm.extern.dobj;
/* Generated Aug 8, 2017 4:29:11 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yugandhar.mdm.abstractdobj.AbstractConfigErrorcodeRegistryDO;

/**
 * DO class for mapped to database CONFIG_ERRORCODE_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractConfigErrorcodeRegistryDO
*/

@Entity
@Table(name = "CONFIG_ERRORCODE_REGISTRY", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CONFIG_LANGUAGE_CODE_KEY", "ERROR_CODE" }))
public class ConfigErrorcodeRegistryDO extends AbstractConfigErrorcodeRegistryDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public ConfigErrorcodeRegistryDO() {
	}

}
