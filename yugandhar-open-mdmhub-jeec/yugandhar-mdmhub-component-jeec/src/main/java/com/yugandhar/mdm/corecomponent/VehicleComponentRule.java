package com.yugandhar.mdm.corecomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.extern.dobj.VehicleDO;

@Scope(value = "prototype")
@Component
public class VehicleComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	/**
	*Pre execute persist validation method for VehicleComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateVehicleCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute merge validation method for VehicleComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void PrevalidateVehicleCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	*Pre execute findbyId validation method for VehicleComp to validate mandatory attributes etc
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void prevalidateVehicleCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for persist in VehicleComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteVehicleCompPersist(VehicleDO reqVehicleDO, TxnTransferObj txnTransferObj) {
		// CountryOfRegistrationRefkey
		if (!(null == reqVehicleDO.getCountryOfRegistrationRefkey()
				|| reqVehicleDO.getCountryOfRegistrationRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryOfRegistrationDO = referenceTableHelper
					.getRefCountryIsoValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqVehicleDO.getCountryOfRegistrationRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryOfRegistrationDO) {

				if (null == reqVehicleDO.getCountryOfRegistrationRefValue()) {
					reqVehicleDO.setCountryOfRegistrationRefValue(theRefCountryOfRegistrationDO.getValue());
				} else if (!(reqVehicleDO.getCountryOfRegistrationRefValue()
						.equals(theRefCountryOfRegistrationDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11061",
							"Validation error : Recieved " + reqVehicleDO.getCountryOfRegistrationRefkey() + "-"
									+ reqVehicleDO.getCountryOfRegistrationRefValue()
									+ " as CountryOfRegistrationRefkey- CountryOfRegistrationRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11061",
						"Validation error : Recieved " + reqVehicleDO.getCountryOfRegistrationRefkey()
								+ " as CountryOfRegistrationRefkey in request which failed validation");
			}
		}


	}

	/**
	*Pre execute rule for persist in VehicleComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteVehicleCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for merge in VehicleComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void preExecuteVehicleCompMerge(VehicleDO reqVehicleDO, VehicleDO dbimageVehicleDO,
			TxnTransferObj txnTransferObj) {

		if (dbimageVehicleDO.getVinNumber().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10021",
					"Recieved empty string for VehicleDO.vinNumber, this attribute cannot be updated to null");
		}
		
		// CountryOfRegistrationRefkey
				if (!(null == dbimageVehicleDO.getCountryOfRegistrationRefkey()
						|| dbimageVehicleDO.getCountryOfRegistrationRefkey().isEmpty())) {
					RefCountryIsoDO theRefCountryOfRegistrationDO = referenceTableHelper
							.getRefCountryIsoValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
									dbimageVehicleDO.getCountryOfRegistrationRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
					if (null != theRefCountryOfRegistrationDO) {

						if (null == dbimageVehicleDO.getCountryOfRegistrationRefValue()) {
							dbimageVehicleDO.setCountryOfRegistrationRefValue(theRefCountryOfRegistrationDO.getValue());
						} else if (!(dbimageVehicleDO.getCountryOfRegistrationRefValue()
								.equals(theRefCountryOfRegistrationDO.getValue()))) {
							throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11061",
									"Validation error : Recieved " + dbimageVehicleDO.getCountryOfRegistrationRefkey() + "-"
											+ dbimageVehicleDO.getCountryOfRegistrationRefValue()
											+ " as CountryOfRegistrationRefkey- CountryOfRegistrationRefValue pair in request which failed validation");
						}

					} else {
						throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11061",
								"Validation error : Recieved " + dbimageVehicleDO.getCountryOfRegistrationRefkey()
										+ " as CountryOfRegistrationRefkey in request which failed validation");
					}
				}

	}

	/**
	*Pre execute rule for merge in VehicleComp
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteVehicleCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	*Pre execute rule for findbyId in VehicleComp 
	* This method is modularized in respective rule class
	* Use Aspect Oriented Programming (AOP) based Yugandhar Rule to override / extend the default OOTB validation
	*@throws YugandharCommonException 
	*/
	public void postExecuteVehicleCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
