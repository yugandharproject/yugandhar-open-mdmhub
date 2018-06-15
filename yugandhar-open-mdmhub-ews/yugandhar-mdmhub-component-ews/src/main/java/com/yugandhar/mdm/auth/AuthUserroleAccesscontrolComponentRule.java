package com.yugandhar.mdm.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.AuthUserroleAccesscontrolDO;
import com.yugandhar.common.util.CommonValidationUtil;

@Scope(value = "prototype")
@Component
public class AuthUserroleAccesscontrolComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for AuthUserroleAccesscontrolComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateAuthUserroleAccesscontrolCompPersit(TxnTransferObj txnTransferObj) {
		AuthUserroleAccesscontrolDO theAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
				.getTxnPayload().getAuthUserroleAccesscontrolDO();
		if (!(theAuthUserroleAccesscontrolDO.getProfileType().toUpperCase().equals("USER")
				|| theAuthUserroleAccesscontrolDO.getProfileType().toUpperCase().equals("ROLE"))) {
			
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10075",
					"authUserroleAccesscontrolDO.profileType value is not valid, recieved " + theAuthUserroleAccesscontrolDO.getProfileType() + 
					" in the request which is not a valid value");
		}
	}

	/**
	*Pre execute merge validation method for AuthUserroleAccesscontrolComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateAuthUserroleAccesscontrolCompMerge(TxnTransferObj txnTransferObj) {
		AuthUserroleAccesscontrolDO theAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
				.getTxnPayload().getAuthUserroleAccesscontrolDO();
		if (!(theAuthUserroleAccesscontrolDO.getProfileType().toUpperCase().equals("USER")
				|| theAuthUserroleAccesscontrolDO.getProfileType().toUpperCase().equals("ROLE"))) {
			
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10075",
					"authUserroleAccesscontrolDO.profileType value is not valid, recieved " + theAuthUserroleAccesscontrolDO.getProfileType() + 
					" in the request which is not a valid value");
		}
	}

	/**
	*Pre execute findbyId validation method for AuthUserroleAccesscontrolComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateAuthUserroleAccesscontrolCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute findByBusinessKey validation method for AuthUserroleAccesscontrolComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preValidateAuthUserroleAccesscontrolfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in AuthUserroleAccesscontrolComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteAuthUserroleAccesscontrolCompPersist(
			AuthUserroleAccesscontrolDO reqAuthUserroleAccesscontrolDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in AuthUserroleAccesscontrolComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteAuthUserroleAccesscontrolCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in AuthUserroleAccesscontrolComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteAuthUserroleAccesscontrolCompMerge(AuthUserroleAccesscontrolDO reqAuthUserroleAccesscontrolDO,
			AuthUserroleAccesscontrolDO dbimageAuthUserroleAccesscontrolDO, TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in AuthUserroleAccesscontrolComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteAuthUserroleAccesscontrolCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in AuthUserroleAccesscontrolComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteAuthUserroleAccesscontrolCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in AuthUserroleAccesscontrolComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteAuthUserroleAccesscontrolfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findByBusinessKey in AuthUserroleAccesscontrolComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteAuthUserroleAccesscontrolfindByBusinessKey(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
