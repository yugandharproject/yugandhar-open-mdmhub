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
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.LeAddressAssocDO;
import com.yugandhar.mdm.extern.dobj.RefAddressSubtypeDO;
import com.yugandhar.mdm.extern.dobj.RefAddressTypeDO;

@Scope(value = "prototype")
@Component
public class LeAddressAssocComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	/**
	 * Pre execute persist validation method for LeAddressAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeAddressAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LeAddressAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLeAddressAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LeAddressAssocComp to validate
	 * mandatory attributes etc This method is modularized in respective rule
	 * class Use Aspect Oriented Programming (AOP) based Yugandhar Rule to
	 * override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeAddressAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in LeAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeAddressAssocCompPersist(LeAddressAssocDO reqLeAddressAssocDO,
			TxnTransferObj txnTransferObj) {
		// AddressTypeRefkey
		if (!(null == reqLeAddressAssocDO.getAddressTypeRefkey()
				|| reqLeAddressAssocDO.getAddressTypeRefkey().isEmpty())) {
			RefAddressTypeDO theRefAddressTypeDO = referenceTableHelper.getRefAddressTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLeAddressAssocDO.getAddressTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressTypeDO) {

				if (null == reqLeAddressAssocDO.getAddressTypeRefValue()) {
					reqLeAddressAssocDO.setAddressTypeRefValue(theRefAddressTypeDO.getValue());
				} else if (!(reqLeAddressAssocDO.getAddressTypeRefValue().equals(theRefAddressTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11025",
							"Validation error : Recieved " + reqLeAddressAssocDO.getAddressTypeRefkey() + "-"
									+ reqLeAddressAssocDO.getAddressTypeRefValue()
									+ " as AddressTypeRefkey- AddressTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11025",
						"Validation error : Recieved " + reqLeAddressAssocDO.getAddressTypeRefkey()
								+ " as AddressTypeRefkey in request which failed validation");
			}
		}

		// AddressSubtypeRefkey
		if (!(null == reqLeAddressAssocDO.getAddressSubtypeRefkey()
				|| reqLeAddressAssocDO.getAddressSubtypeRefkey().isEmpty())) {
			RefAddressSubtypeDO theRefAddressSubtypeDO = referenceTableHelper.getRefAddressSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLeAddressAssocDO.getAddressSubtypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressSubtypeDO) {

				if (null == reqLeAddressAssocDO.getAddressSubtypeRefValue()) {
					reqLeAddressAssocDO.setAddressSubtypeRefValue(theRefAddressSubtypeDO.getValue());
				} else if (!(reqLeAddressAssocDO.getAddressSubtypeRefValue()
						.equals(theRefAddressSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11026",
							"Validation error : Recieved " + reqLeAddressAssocDO.getAddressSubtypeRefkey() + "-"
									+ reqLeAddressAssocDO.getAddressSubtypeRefValue()
									+ " as AddressSubtypeRefkey- AddressSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11026",
						"Validation error : Recieved " + reqLeAddressAssocDO.getAddressSubtypeRefkey()
								+ " as AddressSubtypeRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in LeAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeAddressAssocCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LeAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeAddressAssocCompMerge(LeAddressAssocDO reqLeAddressAssocDO,
			LeAddressAssocDO dbimageLeAddressAssocDO, TxnTransferObj txnTransferObj) {

		if (dbimageLeAddressAssocDO.getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"Recieved empty string for LeAddressAssocDO.LegalEntityIdPk, this attribute cannot be updated to null");
		}

		if (dbimageLeAddressAssocDO.getAddressIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10017",
					"Recieved empty string for LeAddressAssocDO.addressIdpk, this attribute cannot be updated to null");
		}

		if (dbimageLeAddressAssocDO.getAddressTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10008",
					"Recieved empty string for LeAddressAssocDO.addressTypeRefkey, this attribute cannot be updated to null");
		}

		if (dbimageLeAddressAssocDO.getAddressSubtypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10050",
					"Recieved empty string for LeAddressAssocDO.addressSubtypeRefkey, this attribute cannot be updated to null");
		}
		// AddressTypeRefkey
		if (!(null == dbimageLeAddressAssocDO.getAddressTypeRefkey()
				|| dbimageLeAddressAssocDO.getAddressTypeRefkey().isEmpty())) {
			RefAddressTypeDO theRefAddressTypeDO = referenceTableHelper.getRefAddressTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLeAddressAssocDO.getAddressTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressTypeDO) {

				if (null == dbimageLeAddressAssocDO.getAddressTypeRefValue()) {
					dbimageLeAddressAssocDO.setAddressTypeRefValue(theRefAddressTypeDO.getValue());
				} else if (!(dbimageLeAddressAssocDO.getAddressTypeRefValue().equals(theRefAddressTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11025",
							"Validation error : Recieved " + dbimageLeAddressAssocDO.getAddressTypeRefkey() + "-"
									+ dbimageLeAddressAssocDO.getAddressTypeRefValue()
									+ " as AddressTypeRefkey- AddressTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11025",
						"Validation error : Recieved " + dbimageLeAddressAssocDO.getAddressTypeRefkey()
								+ " as AddressTypeRefkey in request which failed validation");
			}
		}

		// AddressSubtypeRefkey
		if (!(null == dbimageLeAddressAssocDO.getAddressSubtypeRefkey()
				|| dbimageLeAddressAssocDO.getAddressSubtypeRefkey().isEmpty())) {
			RefAddressSubtypeDO theRefAddressSubtypeDO = referenceTableHelper.getRefAddressSubtypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLeAddressAssocDO.getAddressSubtypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefAddressSubtypeDO) {

				if (null == dbimageLeAddressAssocDO.getAddressSubtypeRefValue()) {
					dbimageLeAddressAssocDO.setAddressSubtypeRefValue(theRefAddressSubtypeDO.getValue());
				} else if (!(dbimageLeAddressAssocDO.getAddressSubtypeRefValue()
						.equals(theRefAddressSubtypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11026",
							"Validation error : Recieved " + dbimageLeAddressAssocDO.getAddressSubtypeRefkey() + "-"
									+ dbimageLeAddressAssocDO.getAddressSubtypeRefValue()
									+ " as AddressSubtypeRefkey- AddressSubtypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11026",
						"Validation error : Recieved " + dbimageLeAddressAssocDO.getAddressSubtypeRefkey()
								+ " as AddressSubtypeRefkey in request which failed validation");
			}
		}
	}

	/**
	 * Pre execute rule for merge in LeAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeAddressAssocCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LeAddressAssocComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeAddressAssocCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateLeAddressAssocCompFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		// set the current page index to zero (0) if page index is not provided
		// in the request or negative value is provided
		if (null == txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice()
				|| txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice() < 0) {
			txnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(0);
		}

		// set default page size as configured in application properties
		if (null == txnTransferObj.getTxnPayload().getPaginationPageSize()
				|| txnTransferObj.getTxnPayload().getPaginationPageSize() <= 0) {
			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_pagination_datatables_default_pagesize,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			txnTransferObj.getTxnPayload().setPaginationPageSize(Integer.valueOf(theConfigAppPropertiesDO.getValue()));

		}

	}

	public void postExecuteLeAddressAssocCompFindByLegalEntityIdPk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
