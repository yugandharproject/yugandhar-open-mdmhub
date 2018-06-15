package com.yugandhar.mdm.corecomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.EntityGroupDO;
import com.yugandhar.mdm.extern.dobj.RefGroupSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefGroupTypeDO;

@Scope(value = "prototype")
@Component
public class EntityGroupComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for EntityGroupComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateEntityGroupCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for EntityGroupComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateEntityGroupCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for EntityGroupComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateEntityGroupCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in EntityGroupComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteEntityGroupCompPersist(EntityGroupDO reqEntityGroupDO, TxnTransferObj txnTransferObj) {
		// GroupTypeRefkey
		if (!(null == reqEntityGroupDO.getGroupTypeRefkey() || reqEntityGroupDO.getGroupTypeRefkey().isEmpty())) {
			RefGroupTypeDO theRefGroupTypeDO = referenceTableHelper.getRefGroupTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqEntityGroupDO.getGroupTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefGroupTypeDO) {

				if (null == reqEntityGroupDO.getGroupTypeRefValue()) {
					reqEntityGroupDO.setGroupTypeRefValue(theRefGroupTypeDO.getValue());
				} else if (!(reqEntityGroupDO.getGroupTypeRefValue().equals(theRefGroupTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11020",
							"Validation error : Recieved " + reqEntityGroupDO.getGroupTypeRefkey() + "-"
									+ reqEntityGroupDO.getGroupTypeRefValue()
									+ " as GroupTypeRefkey- GroupTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11020",
						"Validation error : Recieved " + reqEntityGroupDO.getGroupTypeRefkey()
								+ " as GroupTypeRefkey in request which failed validation");
			}
		}

		// GroupSubtypeRefkey
		if (!(null == reqEntityGroupDO.getGroupSubtypeRefkey() || reqEntityGroupDO.getGroupSubtypeRefkey().isEmpty())) {
			RefGroupSubtypeDO theRefGroupSubtypeDO = referenceTableHelper.getRefGroupSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqEntityGroupDO.getGroupSubtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefGroupSubtypeDO) {

				if (null == reqEntityGroupDO.getGroupSubtypeRefValue()) {
					reqEntityGroupDO.setGroupSubtypeRefValue(theRefGroupSubtypeDO.getValue());
				} else if (!(reqEntityGroupDO.getGroupSubtypeRefValue().equals(theRefGroupSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11021",
							"Validation error : Recieved " + reqEntityGroupDO.getGroupSubtypeRefkey() + "-"
									+ reqEntityGroupDO.getGroupSubtypeRefValue()
									+ " as GroupSubtypeRefkey- GroupSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11021",
						"Validation error : Recieved " + reqEntityGroupDO.getGroupSubtypeRefkey()
								+ " as GroupSubtypeRefkey in request which failed validation");
			}
		}

	}

	/**
	*Pre execute rule for persist in EntityGroupComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteEntityGroupCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in EntityGroupComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteEntityGroupCompMerge(EntityGroupDO reqEntityGroupDO, EntityGroupDO dbimageEntityGroupDO,
			TxnTransferObj txnTransferObj) {

		if (dbimageEntityGroupDO.getGroupTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10043",
					"Recieved empty string for EntityGroupDO.groupTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageEntityGroupDO.getGroupName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10044",
					"Recieved empty string for EntityGroupDO.groupName, this attribute cannot be updated to null");
		}

		// GroupTypeRefkey
		if (!(null == dbimageEntityGroupDO.getGroupTypeRefkey()
				|| dbimageEntityGroupDO.getGroupTypeRefkey().isEmpty())) {
			RefGroupTypeDO theRefGroupTypeDO = referenceTableHelper.getRefGroupTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageEntityGroupDO.getGroupTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefGroupTypeDO) {

				if (null == dbimageEntityGroupDO.getGroupTypeRefValue()) {
					dbimageEntityGroupDO.setGroupTypeRefValue(theRefGroupTypeDO.getValue());
				} else if (!(dbimageEntityGroupDO.getGroupTypeRefValue().equals(theRefGroupTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11020",
							"Validation error : Recieved " + dbimageEntityGroupDO.getGroupTypeRefkey() + "-"
									+ dbimageEntityGroupDO.getGroupTypeRefValue()
									+ " as GroupTypeRefkey- GroupTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11020",
						"Validation error : Recieved " + dbimageEntityGroupDO.getGroupTypeRefkey()
								+ " as GroupTypeRefkey in request which failed validation");
			}
		}

		// GroupSubtypeRefkey
		if (!(null == dbimageEntityGroupDO.getGroupSubtypeRefkey()
				|| dbimageEntityGroupDO.getGroupSubtypeRefkey().isEmpty())) {
			RefGroupSubtypeDO theRefGroupSubtypeDO = referenceTableHelper.getRefGroupSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageEntityGroupDO.getGroupSubtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefGroupSubtypeDO) {

				if (null == dbimageEntityGroupDO.getGroupSubtypeRefValue()) {
					dbimageEntityGroupDO.setGroupSubtypeRefValue(theRefGroupSubtypeDO.getValue());
				} else if (!(dbimageEntityGroupDO.getGroupSubtypeRefValue().equals(theRefGroupSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11021",
							"Validation error : Recieved " + dbimageEntityGroupDO.getGroupSubtypeRefkey() + "-"
									+ dbimageEntityGroupDO.getGroupSubtypeRefValue()
									+ " as GroupSubtypeRefkey- GroupSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11021",
						"Validation error : Recieved " + dbimageEntityGroupDO.getGroupSubtypeRefkey()
								+ " as GroupSubtypeRefkey in request which failed validation");
			}
		}
	}

	/**
	*Pre execute rule for merge in EntityGroupComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteEntityGroupCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in EntityGroupComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteEntityGroupCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
