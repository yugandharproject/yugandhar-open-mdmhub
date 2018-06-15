package com.yugandhar.mdm.extern.dobj;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractRefMatchScoreDO;

/**
 * DO class for mapped to database REF_MATCH_SCORE entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractRefMatchScoreDO
*/

@Entity
@Table(name = "REF_MATCH_SCORE", uniqueConstraints = @UniqueConstraint(columnNames = { "MATCH_ENTITY_OBJECT_NAME",
		"MATCH_ATTR_PATTERN", "MATCH_RESULT_REFKEY", "MATCH_PROPOSED_ACTION_REFKEY" }))
public class RefMatchScoreDO extends AbstractRefMatchScoreDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public RefMatchScoreDO() {
		super();
	}

	public RefMatchScoreDO(RefMatchScoreDO theRefMatchScoreDO) {
		super(theRefMatchScoreDO);
	}
}
