package com.yugandhar.mdm.config.app.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;

@Scope(value = "prototype")
@Component
public class ConfigAppPropertiesComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	
	/**
	*Pre execute persist validation method for ConfigAppPropertiesComp to validate mandatory attributes etc
	* This method is externalized in com.yugandhar.mdm.extern.util.CommonValidationUtil class
	* Override this method in CommonValidationUtil to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateConfigAppPropertiesCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for ConfigAppPropertiesComp to validate mandatory attributes etc
	* This method is externalized in com.yugandhar.mdm.extern.util.CommonValidationUtil class
	* Override this method in CommonValidationUtil to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateConfigAppPropertiesCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for ConfigAppPropertiesComp to validate mandatory attributes etc
	* This method is externalized in com.yugandhar.mdm.extern.util.CommonValidationUtil class
	* Override this method in CommonValidationUtil to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateConfigAppPropertiesCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}

	/**
	*Pre execute findByBusinessKey validation method for ConfigAppPropertiesComp to validate mandatory attributes etc
	* This method is externalized in com.yugandhar.mdm.extern.util.CommonValidationUtil class
	* Override this method in CommonValidationUtil to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preValidateConfigAppPropertiesfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	*Pre execute rule for persist in ConfigAppPropertiesComp
	* This rule is externalized in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	* Override this method in YugandharPrePostComponentRule to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteConfigAppPropertiesCompPersist(ConfigAppPropertiesDO reqConfigAppPropertiesDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}

	/**
	*Pre execute rule for persist in ConfigAppPropertiesComp
	* This rule is externalized in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	* Override this method in YugandharPrePostComponentRule to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteConfigAppPropertiesCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}

	/**
	*Pre execute rule for merge in ConfigAppPropertiesComp
	* This rule is externalized in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	* Override this method in YugandharPrePostComponentRule to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteConfigAppPropertiesCompMerge(ConfigAppPropertiesDO reqConfigAppPropertiesDO,
			ConfigAppPropertiesDO dbimageConfigAppPropertiesDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}

	/**
	*Pre execute rule for merge in ConfigAppPropertiesComp
	* This rule is externalized in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	* Override this method in YugandharPrePostComponentRule to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteConfigAppPropertiesCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}

	/**
	*Pre execute rule for findbyId in ConfigAppPropertiesComp 
	* This rule is externalized in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	* Override this method in YugandharPrePostComponentRule to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteConfigAppPropertiesCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	*Pre execute rule for findByBusinessKey in ConfigAppPropertiesComp 
	* This rule is externalized in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	* Override this method in YugandharPrePostComponentRule to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteConfigAppPropertiesfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	*Pre execute rule for findByBusinessKey in ConfigAppPropertiesComp 
	* This rule is externalized in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	* Override this method in YugandharPrePostComponentRule to modify the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteConfigAppPropertiesfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}
}
