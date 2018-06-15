package com.yugandhar.mdm.config.txnregistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.extern.dobj.ConfigTxnRegistryDO;

@Scope(value = "prototype")
@Component
public class ConfigTxnRegistryComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;


	// Validate methods for ConfigTxnRegistryComp
	public void prevalidateConfigTxnRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void PrevalidateConfigTxnRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateConfigTxnRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void preValidateConfigTxnRegistryfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	// Validate methods for ConfigErrorcodeRegistryComp
	public void prevalidateConfigErrorcodeRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	// Pre Execution Rules for ConfigTxnRegistryComp

	public void preExecuteConfigTxnRegistryCompPersist(ConfigTxnRegistryDO reqConfigTxnRegistryDO,
			TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void postExecuteConfigTxnRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void preExecuteConfigTxnRegistryCompMerge(ConfigTxnRegistryDO reqConfigTxnRegistryDO,
			ConfigTxnRegistryDO dbimageConfigTxnRegistryDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void postExecuteConfigTxnRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void postExecuteConfigTxnRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void preExecuteConfigTxnRegistryfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void postExecuteConfigTxnRegistryfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
