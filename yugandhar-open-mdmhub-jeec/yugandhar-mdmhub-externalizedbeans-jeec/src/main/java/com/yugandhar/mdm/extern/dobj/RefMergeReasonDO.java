package com.yugandhar.mdm.extern.dobj;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractRefMergeReasonDO;

/**
 * DO class for mapped to database REF_MERGE_REASON entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractRefMergeReasonDO
*/

@Entity
@Table(name = "REF_MERGE_REASON", uniqueConstraints = @UniqueConstraint(columnNames = { "CONFIG_LANGUAGE_CODE_KEY",
		"KEY" }))
public class RefMergeReasonDO extends AbstractRefMergeReasonDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public RefMergeReasonDO() {
		super();
	}

	public RefMergeReasonDO(RefMergeReasonDO theRefMergeReasonDO) {
		super(theRefMergeReasonDO);
	}
}
