package com.yugandhar.mdm.extern.dobj;
/* Generated Oct 27, 2017 5:20:01 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yugandhar.mdm.abstractdobj.AbstractMatchMergedLeAssocDO;

/**
 * DO class for mapped to database MATCH_MERGED_LE_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractMatchMergedLeAssocDO
*/

@Entity
@Table(name = "MATCH_MERGED_LE_ASSOC")
public class MatchMergedLeAssocDO extends AbstractMatchMergedLeAssocDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public MatchMergedLeAssocDO() {
		super();
	}

	public MatchMergedLeAssocDO(MatchMergedLeAssocDO theMatchMergedLeAssocDO) {
		super(theMatchMergedLeAssocDO);
	}
}
