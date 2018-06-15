package com.yugandhar.mdm.extern.dobj;
/* Generated Aug 8, 2017 3:00:17 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yugandhar.mdm.abstractdobj.AbstractConfigLanguageCodeDO;

/**
 * DO class for mapped to database CONFIG_LANGUAGE_CODE entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractConfigLanguageCodeDO
*/

@Entity
@Table(name = "CONFIG_LANGUAGE_CODE", uniqueConstraints = @UniqueConstraint(columnNames = "KEY"))
public class ConfigLanguageCodeDO extends AbstractConfigLanguageCodeDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public ConfigLanguageCodeDO() {
	}

}
