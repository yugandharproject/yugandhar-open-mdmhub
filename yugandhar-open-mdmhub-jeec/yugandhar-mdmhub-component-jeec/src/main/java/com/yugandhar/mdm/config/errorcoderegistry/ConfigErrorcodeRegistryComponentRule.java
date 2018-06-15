package com.yugandhar.mdm.config.errorcoderegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.extern.dobj.ConfigErrorcodeRegistryDO;

@Scope(value = "prototype")
@Component
public class ConfigErrorcodeRegistryComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;
	
	
	//Validate methods for ConfigErrorcodeRegistryComp
			public void prevalidateConfigErrorcodeRegistryCompPersit(TxnTransferObj txnTransferObj) {
				// TODO Auto-generated method stub
				
			}

			public void PrevalidateConfigErrorcodeRegistryCompMerge(TxnTransferObj txnTransferObj) {
				// TODO Auto-generated method stub
				
			}

			public void prevalidateConfigErrorcodeRegistryCompFindById(TxnTransferObj txnTransferObj) {
				// TODO Auto-generated method stub
				
			}
			
			// Pre Execution Rules for ConfigErrorcodeRegistryComp

			public void preExecuteConfigErrorcodeRegistryCompPersist(ConfigErrorcodeRegistryDO reqConfigErrorcodeRegistryDO,
					TxnTransferObj txnTransferObj) {
				// TODO Auto-generated method stub

			}

			public void postExecuteConfigErrorcodeRegistryCompPersit(TxnTransferObj txnTransferObj) {
				// TODO Auto-generated method stub

			}

			public void preExecuteConfigErrorcodeRegistryCompMerge(ConfigErrorcodeRegistryDO reqConfigErrorcodeRegistryDO,
					ConfigErrorcodeRegistryDO dbimageConfigErrorcodeRegistryDO, TxnTransferObj txnTransferObj) {
				// TODO Auto-generated method stub

			}

			public void postExecuteConfigErrorcodeRegistryCompMerge(TxnTransferObj txnTransferObj) {
				// TODO Auto-generated method stub

			}

			public void postExecuteConfigErrorcodeRegistryCompFindById(TxnTransferObj txnTransferObj) {
				// TODO Auto-generated method stub

			}
}
