package com.yugandhar.mdm.corecomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.component.util.YugandharPhoneticHelper;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.extern.dobj.AddressDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.extern.dobj.RefStateProvinceDO;

@Scope(value = "prototype")
@Component
public class AddressComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent theConfigAppPropertiesComponent;

	@Autowired
	YugandharPhoneticHelper theYugandharPhoneticHelper;

	/**
	 * Pre execute persist validation method for AddressComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateAddressCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for AddressComp to validate mandatory
	 * attributes etc This method is modularized in respective rule class Use
	 * Aspect Oriented Programming (AOP) based Yugandhar Rule to override /
	 * extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateAddressCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for AddressComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateAddressCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in AddressComp This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteAddressCompPersist(AddressDO reqAddressDO, TxnTransferObj txnTransferObj) {
		// StateProvinceRefkey
		if (!(null == reqAddressDO.getStateProvinceRefkey() || reqAddressDO.getStateProvinceRefkey().isEmpty())) {
			RefStateProvinceDO theRefStateProvinceDO = referenceTableHelper.getRefStateProvinceValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAddressDO.getStateProvinceRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefStateProvinceDO) {

				if (null == reqAddressDO.getStateProvinceRefValue()) {
					reqAddressDO.setStateProvinceRefValue(theRefStateProvinceDO.getValue());
				} else if (!(reqAddressDO.getStateProvinceRefValue().equals(theRefStateProvinceDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11014",
							"Validation error : Recieved " + reqAddressDO.getStateProvinceRefkey() + "-"
									+ reqAddressDO.getStateProvinceRefValue()
									+ " as StateProvinceRefkey- StateProvinceRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11014",
						"Validation error : Recieved " + reqAddressDO.getStateProvinceRefkey()
								+ " as StateProvinceRefkey in request which failed validation");
			}
		}

		// CountryRefkey
		if (!(null == reqAddressDO.getCountryRefkey() || reqAddressDO.getCountryRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqAddressDO.getCountryRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryDO) {

				if (null == reqAddressDO.getCountryRefValue()) {
					reqAddressDO.setCountryRefValue(theRefCountryDO.getValue());
				} else if (!(reqAddressDO.getCountryRefValue().equals(theRefCountryDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11015",
							"Validation error : Recieved " + reqAddressDO.getCountryRefkey() + "-"
									+ reqAddressDO.getCountryRefValue()
									+ " as CountryRefkey- CountryRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11015",
						"Validation error : Recieved " + reqAddressDO.getCountryRefkey()
								+ " as CountryRefkey in request which failed validation");
			}
		}

		// Phonetic
				ConfigAppPropertiesDO theConfigAppPropertiesDO = theConfigAppPropertiesComponent.executeRepositoryQuery(
						YugandharConfigurationProperties.com_yugandhar_phonetic_framework_enabled,
						yugandharConstants.FILTER_VALUE_ACTIVE);
				if (theConfigAppPropertiesDO.getValue().equalsIgnoreCase(yugandharConstants.FLAG_true)) {

					if (!(null == reqAddressDO.getAddressLineOne() || reqAddressDO.getAddressLineOne().isEmpty())) {
						reqAddressDO.setPhoneticAddressLineOne(
								theYugandharPhoneticHelper.getPhoneticValue(reqAddressDO.getAddressLineOne(), txnTransferObj));
					}

					if (!(null == reqAddressDO.getAddressLineTwo() || reqAddressDO.getAddressLineTwo().isEmpty())) {
						reqAddressDO.setPhoneticAddressLineTwo(
								theYugandharPhoneticHelper.getPhoneticValue(reqAddressDO.getAddressLineTwo(), txnTransferObj));
					}

					if (!(null == reqAddressDO.getAddressLineThree() || reqAddressDO.getAddressLineThree().isEmpty())) {
						reqAddressDO.setPhoneticAddressLineThree(
								theYugandharPhoneticHelper.getPhoneticValue(reqAddressDO.getAddressLineThree(), txnTransferObj));
					}

					if (!(null == reqAddressDO.getAddressLineFour() || reqAddressDO.getAddressLineFour().isEmpty())) {
						reqAddressDO.setPhoneticAddressLineFour(
								theYugandharPhoneticHelper.getPhoneticValue(reqAddressDO.getAddressLineFour(), txnTransferObj));
					}
					
					if (!(null == reqAddressDO.getStreetName() || reqAddressDO.getStreetName().isEmpty())) {
						reqAddressDO.setPhoneticStreetName(
								theYugandharPhoneticHelper.getPhoneticValue(reqAddressDO.getStreetName(), txnTransferObj));
					}
					
					if (!(null == reqAddressDO.getCounty() || reqAddressDO.getCounty().isEmpty())) {
						reqAddressDO.setPhoneticCounty(
								theYugandharPhoneticHelper.getPhoneticValue(reqAddressDO.getCounty(), txnTransferObj));
					}
					
					if (!(null == reqAddressDO.getCity() || reqAddressDO.getCity().isEmpty())) {
						reqAddressDO.setPhoneticCity(
								theYugandharPhoneticHelper.getPhoneticValue(reqAddressDO.getCity(), txnTransferObj));
					}
					
					if (!(null == reqAddressDO.getDistrictZone() || reqAddressDO.getDistrictZone().isEmpty())) {
						reqAddressDO.setPhoneticDistrictZone(
								theYugandharPhoneticHelper.getPhoneticValue(reqAddressDO.getDistrictZone(), txnTransferObj));
					}

				}

	}

	/**
	 * Pre execute rule for persist in AddressComp This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteAddressCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in AddressComp This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteAddressCompMerge(AddressDO reqAddressDO, AddressDO dbimageAddressDO,
			TxnTransferObj txnTransferObj) {

		if (dbimageAddressDO.getAddressLineOne().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10009",
					"Recieved empty string for AddressDO.addressLineOne, this attribute cannot be updated to null");
		}

		if (dbimageAddressDO.getCountryRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10051",
					"Recieved empty string for AddressDO.countryRefkey is needed in the request, this attribute cannot be updated to null");
		}

		if (dbimageAddressDO.getCity().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10052",
					"Recieved empty string for AddressDO.city is needed in the request, this attribute cannot be updated to null");
		}

		// StateProvinceRefkey
		if (!(null == dbimageAddressDO.getStateProvinceRefkey()
				|| dbimageAddressDO.getStateProvinceRefkey().isEmpty())) {
			RefStateProvinceDO theRefStateProvinceDO = referenceTableHelper.getRefStateProvinceValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageAddressDO.getStateProvinceRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefStateProvinceDO) {

				if (null == dbimageAddressDO.getStateProvinceRefValue()) {
					dbimageAddressDO.setStateProvinceRefValue(theRefStateProvinceDO.getValue());
				} else if (!(dbimageAddressDO.getStateProvinceRefValue().equals(theRefStateProvinceDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11014",
							"Validation error : Recieved " + dbimageAddressDO.getStateProvinceRefkey() + "-"
									+ dbimageAddressDO.getStateProvinceRefValue()
									+ " as StateProvinceRefkey- StateProvinceRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11014",
						"Validation error : Recieved " + dbimageAddressDO.getStateProvinceRefkey()
								+ " as StateProvinceRefkey in request which failed validation");
			}
		}

		// CountryRefkey
		if (!(null == dbimageAddressDO.getCountryRefkey() || dbimageAddressDO.getCountryRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryDO = referenceTableHelper.getRefCountryIsoValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageAddressDO.getCountryRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryDO) {

				if (null == dbimageAddressDO.getCountryRefValue()) {
					dbimageAddressDO.setCountryRefValue(theRefCountryDO.getValue());
				} else if (!(dbimageAddressDO.getCountryRefValue().equals(theRefCountryDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11015",
							"Validation error : Recieved " + dbimageAddressDO.getCountryRefkey() + "-"
									+ dbimageAddressDO.getCountryRefValue()
									+ " as CountryRefkey- CountryRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11015",
						"Validation error : Recieved " + dbimageAddressDO.getCountryRefkey()
								+ " as CountryRefkey in request which failed validation");
			}
		}

		
		// Phonetic
		ConfigAppPropertiesDO theConfigAppPropertiesDO = theConfigAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_framework_enabled,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		if (theConfigAppPropertiesDO.getValue().equalsIgnoreCase(yugandharConstants.FLAG_true)) {


			if (!(null == dbimageAddressDO.getAddressLineOne() || dbimageAddressDO.getAddressLineOne().isEmpty())) {
				dbimageAddressDO.setPhoneticAddressLineOne(
						theYugandharPhoneticHelper.getPhoneticValue(dbimageAddressDO.getAddressLineOne(), txnTransferObj));
			}

			if (!(null == dbimageAddressDO.getAddressLineTwo() || dbimageAddressDO.getAddressLineTwo().isEmpty())) {
				dbimageAddressDO.setPhoneticAddressLineTwo(
						theYugandharPhoneticHelper.getPhoneticValue(dbimageAddressDO.getAddressLineTwo(), txnTransferObj));
			}

			if (!(null == dbimageAddressDO.getAddressLineThree() || dbimageAddressDO.getAddressLineThree().isEmpty())) {
				dbimageAddressDO.setPhoneticAddressLineThree(
						theYugandharPhoneticHelper.getPhoneticValue(dbimageAddressDO.getAddressLineThree(), txnTransferObj));
			}

			if (!(null == dbimageAddressDO.getAddressLineFour() || dbimageAddressDO.getAddressLineFour().isEmpty())) {
				dbimageAddressDO.setPhoneticAddressLineFour(
						theYugandharPhoneticHelper.getPhoneticValue(dbimageAddressDO.getAddressLineFour(), txnTransferObj));
			}
			
			if (!(null == dbimageAddressDO.getStreetName() || dbimageAddressDO.getStreetName().isEmpty())) {
				dbimageAddressDO.setPhoneticStreetName(
						theYugandharPhoneticHelper.getPhoneticValue(dbimageAddressDO.getStreetName(), txnTransferObj));
			}
			
			if (!(null == dbimageAddressDO.getCounty() || dbimageAddressDO.getCounty().isEmpty())) {
				dbimageAddressDO.setPhoneticCounty(
						theYugandharPhoneticHelper.getPhoneticValue(dbimageAddressDO.getCounty(), txnTransferObj));
			}
			
			if (!(null == dbimageAddressDO.getCity() || dbimageAddressDO.getCity().isEmpty())) {
				dbimageAddressDO.setPhoneticCity(
						theYugandharPhoneticHelper.getPhoneticValue(dbimageAddressDO.getCity(), txnTransferObj));
			}
			
			if (!(null == dbimageAddressDO.getDistrictZone() || dbimageAddressDO.getDistrictZone().isEmpty())) {
				dbimageAddressDO.setPhoneticDistrictZone(
						theYugandharPhoneticHelper.getPhoneticValue(dbimageAddressDO.getDistrictZone(), txnTransferObj));
			}

		
		}
	}

	/**
	 * Pre execute rule for merge in AddressComp This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteAddressCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in AddressComp This method is modularized
	 * in respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteAddressCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
