package com.yugandhar.mdm.extern.dobj;
/* Generated Jun 13, 2017 1:01:46 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yugandhar.mdm.abstractdobj.AbstractConfigTxnRegistryDO;

@Entity
@Table(name = "CONFIG_TXN_REGISTRY")
public class ConfigTxnRegistryDO extends AbstractConfigTxnRegistryDO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

	public ConfigTxnRegistryDO() {
		super();
	}

	public ConfigTxnRegistryDO(ConfigTxnRegistryDO theConfigTxnRegistryDO) {
		super(theConfigTxnRegistryDO);
	}

}
