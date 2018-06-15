package com.yugandhar.mdm.match.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.InactiveLeRegistryDO;
import com.yugandhar.mdm.extern.dobj.RefInactivationReasonDO;

@Scope(value = "prototype")
@Component
public class InactiveLeRegistryComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for InactiveLeRegistryComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateInactiveLeRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for InactiveLeRegistryComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateInactiveLeRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for InactiveLeRegistryComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateInactiveLeRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in InactiveLeRegistryComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteInactiveLeRegistryCompPersist(InactiveLeRegistryDO reqInactiveLeRegistryDO,
			TxnTransferObj txnTransferObj) {

		// inactivationReasonRefkey
				if (!(null == reqInactiveLeRegistryDO.getInactivationReasonRefkey()
						|| reqInactiveLeRegistryDO.getInactivationReasonRefkey().isEmpty())) {
					RefInactivationReasonDO theRefInactivationReasonDO = referenceTableHelper.getRefInactivationReasonValueByKey(
							txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqInactiveLeRegistryDO.getInactivationReasonRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
					if (null != theRefInactivationReasonDO) {

						if (null == reqInactiveLeRegistryDO.getInactivationReasonRefValue()) {
							reqInactiveLeRegistryDO.setInactivationReasonRefValue(theRefInactivationReasonDO.getValue());
						} else if (!(reqInactiveLeRegistryDO.getInactivationReasonRefValue()
								.equals(theRefInactivationReasonDO.getValue()))) {
							throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11062",
									"Validation error : Recieved " + reqInactiveLeRegistryDO.getInactivationReasonRefkey() + "-"
											+ reqInactiveLeRegistryDO.getInactivationReasonRefValue()
											+ " as inactivationReasonRefkey- inactivationReasonRefValue pair in request which failed validation");
						}

					} else {
						throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11062",
								"Validation error : Recieved " + reqInactiveLeRegistryDO.getInactivationReasonRefkey()
										+ " as inactivationReasonRefkey in request which failed validation");
					}
				}

	}

	/**
	*post execute rule for persist in InactiveLeRegistryComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteInactiveLeRegistryCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in InactiveLeRegistryComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteInactiveLeRegistryCompMerge(InactiveLeRegistryDO reqInactiveLeRegistryDO,
			InactiveLeRegistryDO dbimageInactiveLeRegistryDO, TxnTransferObj txnTransferObj) {

		if (null == dbimageInactiveLeRegistryDO.getLegalentityIdpk()
				|| dbimageInactiveLeRegistryDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10098",
					"Recieved empty string for inactiveLeRegistryDO.legalentityIdpk, this attribute cannot be updated to null");
		}
		
		if (null == dbimageInactiveLeRegistryDO.getInactivationReasonRefkey()
				|| dbimageInactiveLeRegistryDO.getInactivationReasonRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10100",
					"Recieved empty string for inactiveLeRegistryDO.inactivationReasonRefkey, this attribute cannot be updated to null");
		}
		
		
		// inactivationReasonRefkey
		if (!(null == dbimageInactiveLeRegistryDO.getInactivationReasonRefkey()
				|| dbimageInactiveLeRegistryDO.getInactivationReasonRefkey().isEmpty())) {
			RefInactivationReasonDO theRefInactivationReasonDO = referenceTableHelper.getRefInactivationReasonValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageInactiveLeRegistryDO.getInactivationReasonRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefInactivationReasonDO) {

				if (null == dbimageInactiveLeRegistryDO.getInactivationReasonRefValue()) {
					dbimageInactiveLeRegistryDO.setInactivationReasonRefValue(theRefInactivationReasonDO.getValue());
				} else if (!(dbimageInactiveLeRegistryDO.getInactivationReasonRefValue()
						.equals(theRefInactivationReasonDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11062",
							"Validation error : Recieved " + dbimageInactiveLeRegistryDO.getInactivationReasonRefkey() + "-"
									+ dbimageInactiveLeRegistryDO.getInactivationReasonRefValue()
									+ " as inactivationReasonRefkey- inactivationReasonRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11062",
						"Validation error : Recieved " + dbimageInactiveLeRegistryDO.getInactivationReasonRefkey()
								+ " as inactivationReasonRefkey in request which failed validation");
			}
		}
		

	}

	/**
	*post execute rule for merge in InactiveLeRegistryComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteInactiveLeRegistryCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*post execute rule for findbyId in InactiveLeRegistryComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteInactiveLeRegistryCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	
	/**
	*Pre execute rule for SearchInactiveLeRegistry in InactiveLeRegistryComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateInactiveLeRegistryCompSearchInactiveLeRegistry(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	*Post execute rule for SearchInactiveLeRegistry in InactiveLeRegistryComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/

	public void postExecuteInactiveLeRegistryCompSearchInactiveLeRegistry(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub
		
	}

}
