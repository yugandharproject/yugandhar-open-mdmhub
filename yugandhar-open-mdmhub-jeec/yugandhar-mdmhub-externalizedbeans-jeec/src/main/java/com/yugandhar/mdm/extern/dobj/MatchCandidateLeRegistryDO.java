package com.yugandhar.mdm.extern.dobj;
/* Generated Oct 27, 2017 5:20:01 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yugandhar.mdm.abstractdobj.AbstractMatchCandidateLeRegistryDO;

/**
 * DO class for mapped to database MATCH_CANDIDATE_LE_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractMatchCandidateLeRegistryDO
*/

@Entity
@Table(name = "MATCH_CANDIDATE_LE_REGISTRY")
public class MatchCandidateLeRegistryDO extends AbstractMatchCandidateLeRegistryDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public MatchCandidateLeRegistryDO() {
		super();
	}

	public MatchCandidateLeRegistryDO(MatchCandidateLeRegistryDO theMatchCandidateLeRegistryDO) {
		super(theMatchCandidateLeRegistryDO);
	}
}
