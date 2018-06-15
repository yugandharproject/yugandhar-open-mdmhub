package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 5, 2017 1:54:23 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractRefHighestEduQualDO;

/**
 * DO class for mapped to database REF_HIGHEST_EDU_QUAL entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractRefHighestEduQualDO
*/

@Entity
@Table(name = "REF_HIGHEST_EDU_QUAL", uniqueConstraints = @UniqueConstraint(columnNames = { "CONFIG_LANGUAGE_CODE_KEY",
		"KEY" }))
public class RefHighestEduQualDO extends AbstractRefHighestEduQualDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public RefHighestEduQualDO() {
		super();
	}

	public RefHighestEduQualDO(RefHighestEduQualDO theRefHighestEduQualDO) {
		super(theRefHighestEduQualDO);
	}
}
