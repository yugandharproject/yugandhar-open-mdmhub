package com.yugandhar.mdm.extern.dobj;
/* Generated Sep 4, 2017 4:22:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.yugandhar.mdm.abstractdobj.AbstractLeIdentifierKycRegistryDO;

/**
 * DO class for mapped to database LE_IDENTIFIER_KYC_REGISTRY entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see AbstractLeIdentifierKycRegistryDO
*/

@Entity
@Table(name = "LE_IDENTIFIER_KYC_REGISTRY", uniqueConstraints = @UniqueConstraint(columnNames = {
		"IDENTIFICATION_TYPE_REFKEY", "LEGALENTITY_IDPK" }))
public class LeIdentifierKycRegistryDO extends AbstractLeIdentifierKycRegistryDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public LeIdentifierKycRegistryDO() {
		super();
	}

	public LeIdentifierKycRegistryDO(LeIdentifierKycRegistryDO theLeIdentifierKycRegistryDO) {
		super(theLeIdentifierKycRegistryDO);
	}
}
