package com.yugandhar.mdm.corecomponentref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.RefTerminationReasonDO;
import com.yugandhar.common.util.CommonValidationUtil;

@Scope(value = "prototype")
@Component
public class RefTerminationReasonComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for RefTerminationReasonComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefTerminationReasonCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for RefTerminationReasonComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateRefTerminationReasonCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for RefTerminationReasonComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefTerminationReasonCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute findByBusinessKey validation method for RefTerminationReasonComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preValidateRefTerminationReasonfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefTerminationReasonComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefTerminationReasonCompPersist(RefTerminationReasonDO reqRefTerminationReasonDO,
			TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefTerminationReasonComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefTerminationReasonCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefTerminationReasonComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefTerminationReasonCompMerge(RefTerminationReasonDO reqRefTerminationReasonDO,
			RefTerminationReasonDO dbimageRefTerminationReasonDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefTerminationReasonComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefTerminationReasonCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in RefTerminationReasonComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefTerminationReasonCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefTerminationReasonComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefTerminationReasonfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefTerminationReasonComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefTerminationReasonfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
