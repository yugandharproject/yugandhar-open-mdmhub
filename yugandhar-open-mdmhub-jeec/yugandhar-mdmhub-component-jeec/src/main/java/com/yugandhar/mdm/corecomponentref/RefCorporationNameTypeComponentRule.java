package com.yugandhar.mdm.corecomponentref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.RefCorporationNameTypeDO;
import com.yugandhar.common.util.CommonValidationUtil;

@Scope(value = "prototype")
@Component
public class RefCorporationNameTypeComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for RefCorporationNameTypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefCorporationNameTypeCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for RefCorporationNameTypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateRefCorporationNameTypeCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for RefCorporationNameTypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateRefCorporationNameTypeCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute findByBusinessKey validation method for RefCorporationNameTypeComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preValidateRefCorporationNameTypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefCorporationNameTypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefCorporationNameTypeCompPersist(RefCorporationNameTypeDO reqRefCorporationNameTypeDO,
			TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in RefCorporationNameTypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefCorporationNameTypeCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefCorporationNameTypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefCorporationNameTypeCompMerge(RefCorporationNameTypeDO reqRefCorporationNameTypeDO,
			RefCorporationNameTypeDO dbimageRefCorporationNameTypeDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in RefCorporationNameTypeComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefCorporationNameTypeCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in RefCorporationNameTypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefCorporationNameTypeCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefCorporationNameTypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteRefCorporationNameTypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in RefCorporationNameTypeComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteRefCorporationNameTypefindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
