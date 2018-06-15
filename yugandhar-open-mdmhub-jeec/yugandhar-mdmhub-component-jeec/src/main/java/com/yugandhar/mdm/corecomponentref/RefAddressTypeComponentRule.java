package com.yugandhar.mdm.corecomponentref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.RefAddressTypeDO;
import com.yugandhar.common.util.CommonValidationUtil;

@Scope(value = "prototype")
@Component
public class RefAddressTypeComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for RefAddressTypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefAddressTypeCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for RefAddressTypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateRefAddressTypeCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for RefAddressTypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefAddressTypeCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute findByBusinessKey validation method for RefAddressTypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preValidateRefAddressTypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefAddressTypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefAddressTypeCompPersist(RefAddressTypeDO reqRefAddressTypeDO,
			TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefAddressTypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefAddressTypeCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefAddressTypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefAddressTypeCompMerge(RefAddressTypeDO reqRefAddressTypeDO,
			RefAddressTypeDO dbimageRefAddressTypeDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefAddressTypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefAddressTypeCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in RefAddressTypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefAddressTypeCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefAddressTypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefAddressTypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefAddressTypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefAddressTypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
