package com.yugandhar.mdm.extern.dobj;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractRefMatchThresholdDO;

/**
 * DO class for mapped to database REF_MATCH_THRESHOLD entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractRefMatchThresholdDO
*/

@Entity
@Table(name = "REF_MATCH_THRESHOLD", uniqueConstraints = @UniqueConstraint(columnNames = "ATTR_BLOCK_NAME"))
public class RefMatchThresholdDO extends AbstractRefMatchThresholdDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public RefMatchThresholdDO() {
		super();
	}

	public RefMatchThresholdDO(RefMatchThresholdDO theRefMatchThresholdDO) {
		super(theRefMatchThresholdDO);
	}
}
