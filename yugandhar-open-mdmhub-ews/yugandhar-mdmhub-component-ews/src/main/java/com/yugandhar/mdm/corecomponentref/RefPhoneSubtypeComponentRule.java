package com.yugandhar.mdm.corecomponentref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.RefPhoneSubtypeDO;
import com.yugandhar.common.util.CommonValidationUtil;

@Scope(value = "prototype")
@Component
public class RefPhoneSubtypeComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for RefPhoneSubtypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefPhoneSubtypeCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for RefPhoneSubtypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateRefPhoneSubtypeCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for RefPhoneSubtypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefPhoneSubtypeCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute findByBusinessKey validation method for RefPhoneSubtypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preValidateRefPhoneSubtypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefPhoneSubtypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefPhoneSubtypeCompPersist(RefPhoneSubtypeDO reqRefPhoneSubtypeDO,
			TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefPhoneSubtypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefPhoneSubtypeCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefPhoneSubtypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefPhoneSubtypeCompMerge(RefPhoneSubtypeDO reqRefPhoneSubtypeDO,
			RefPhoneSubtypeDO dbimageRefPhoneSubtypeDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefPhoneSubtypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefPhoneSubtypeCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in RefPhoneSubtypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefPhoneSubtypeCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefPhoneSubtypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefPhoneSubtypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefPhoneSubtypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefPhoneSubtypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
