package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 5, 2017 1:54:23 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractRefBranchCodeDO;

/**
 * DO class for mapped to database REF_BRANCH_CODE entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractRefBranchCodeDO
*/

@Entity
@Table(name = "REF_BRANCH_CODE", uniqueConstraints = @UniqueConstraint(columnNames = { "CONFIG_LANGUAGE_CODE_KEY",
		"KEY" }))
public class RefBranchCodeDO extends AbstractRefBranchCodeDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public RefBranchCodeDO() {
		super();
	}

	public RefBranchCodeDO(RefBranchCodeDO theRefBranchCodeDO) {
		super(theRefBranchCodeDO);
	}
}
