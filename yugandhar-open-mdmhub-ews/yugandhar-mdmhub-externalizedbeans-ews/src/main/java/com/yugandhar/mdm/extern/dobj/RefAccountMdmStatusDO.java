package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 5, 2017 1:54:23 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractRefAccountMdmStatusDO;

/**
 * DO class for mapped to database REF_ACCOUNT_MDM_STATUS entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractRefAccountMdmStatusDO
*/

@Entity
@Table(name = "REF_ACCOUNT_MDM_STATUS", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CONFIG_LANGUAGE_CODE_KEY", "KEY" }))
public class RefAccountMdmStatusDO extends AbstractRefAccountMdmStatusDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public RefAccountMdmStatusDO() {
		super();
	}

	public RefAccountMdmStatusDO(RefAccountMdmStatusDO theRefAccountMdmStatusDO) {
		super(theRefAccountMdmStatusDO);
	}
}
