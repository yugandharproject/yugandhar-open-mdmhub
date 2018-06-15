package com.yugandhar.mdm.abstractdobj;

import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.MatchCandidateLeRegistryDO;

/**
 * Abstract DO class for PerformLeMatchRequestDO used for searching Legal entity
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 */

public abstract class AbstractPerformLeMatchRequestDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// match engine type (fuzzy or deterministic)
	protected String matchEngineType;
	// filter

	protected LegalentityDO legalentityDO;

	protected MatchCandidateLeRegistryDO matchResult;

	
	/**
	 * @return the matchEngineType
	 */
	public String getMatchEngineType() {
		return matchEngineType;
	}

	/**
	 * @param matchEngineType the matchEngineType to set
	 */
	public void setMatchEngineType(String matchEngineType) {
		this.matchEngineType = matchEngineType;
	}

	/**
	 * @return the legalentityDO
	 */
	public LegalentityDO getLegalentityDO() {
		return legalentityDO;
	}

	/**
	 * @param legalentityDO
	 *            the legalentityDO to set
	 */
	public void setLegalentityDO(LegalentityDO legalentityDO) {
		this.legalentityDO = legalentityDO;
	}

	/**
	 * @return the matchResult
	 */
	public MatchCandidateLeRegistryDO getMatchResult() {
		return matchResult;
	}

	/**
	 * @param matchResult the matchResult to set
	 */
	public void setMatchResult(MatchCandidateLeRegistryDO matchResult) {
		this.matchResult = matchResult;
	}

	
}
