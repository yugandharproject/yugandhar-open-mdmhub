package com.yugandhar.mdm.corecomponentref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.RefAddressSubtypeDO;
import com.yugandhar.common.util.CommonValidationUtil;

@Scope(value = "prototype")
@Component
public class RefAddressSubtypeComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for RefAddressSubtypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefAddressSubtypeCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for RefAddressSubtypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateRefAddressSubtypeCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for RefAddressSubtypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefAddressSubtypeCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute findByBusinessKey validation method for RefAddressSubtypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preValidateRefAddressSubtypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefAddressSubtypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefAddressSubtypeCompPersist(RefAddressSubtypeDO reqRefAddressSubtypeDO,
			TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefAddressSubtypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefAddressSubtypeCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefAddressSubtypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefAddressSubtypeCompMerge(RefAddressSubtypeDO reqRefAddressSubtypeDO,
			RefAddressSubtypeDO dbimageRefAddressSubtypeDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefAddressSubtypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefAddressSubtypeCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in RefAddressSubtypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefAddressSubtypeCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefAddressSubtypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefAddressSubtypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefAddressSubtypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefAddressSubtypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
