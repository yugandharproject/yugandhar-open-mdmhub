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
import com.yugandhar.mdm.extern.dobj.LeToLeRelationshipDO;
import com.yugandhar.mdm.extern.dobj.RefLeRelationshipTypeDO;
import com.yugandhar.mdm.extern.dobj.RefRelationshipStatusDO;

@Scope(value = "prototype")
@Component
public class LeToLeRelationshipComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;
	
	/**
	 * Pre execute persist validation method for LeToLeRelationshipComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeToLeRelationshipCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LeToLeRelationshipComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLeToLeRelationshipCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LeToLeRelationshipComp to
	 * validate mandatory attributes etc This method is modularized in
	 * respective rule class Use Aspect Oriented Programming (AOP) based
	 * Yugandhar Rule to override / extend the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLeToLeRelationshipCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for persist in LeToLeRelationshipComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeToLeRelationshipCompPersist(LeToLeRelationshipDO reqLeToLeRelationshipDO,
			TxnTransferObj txnTransferObj) {

		// LeRelationshipTypeRefkey
		if (!(null == reqLeToLeRelationshipDO.getLeRelationshipTypeRefkey()
				|| reqLeToLeRelationshipDO.getLeRelationshipTypeRefkey().isEmpty())) {
			RefLeRelationshipTypeDO theRefLeRelationshipTypeDO = referenceTableHelper
					.getRefLeRelationshipTypeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqLeToLeRelationshipDO.getLeRelationshipTypeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRelationshipTypeDO) {

				if (null == reqLeToLeRelationshipDO.getLeRelationshipTypeRefValue()) {
					reqLeToLeRelationshipDO.setLeRelationshipTypeRefValue(theRefLeRelationshipTypeDO.getValue());
				} else if (!(reqLeToLeRelationshipDO.getLeRelationshipTypeRefValue()
						.equals(theRefLeRelationshipTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11051",
							"Validation error : Recieved " + reqLeToLeRelationshipDO.getLeRelationshipTypeRefkey() + "-"
									+ reqLeToLeRelationshipDO.getLeRelationshipTypeRefValue()
									+ " as LeRelationshipTypeRefkey- LeRelationshipTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11051",
						"Validation error : Recieved " + reqLeToLeRelationshipDO.getLeRelationshipTypeRefkey()
								+ " as LeRelationshipTypeRefkey in request which failed validation");
			}
		}

		// RelationshipStatusRefkey
		if (!(null == reqLeToLeRelationshipDO.getRelationshipStatusRefkey()
				|| reqLeToLeRelationshipDO.getRelationshipStatusRefkey().isEmpty())) {
			RefRelationshipStatusDO theRefRelationshipStatusDO = referenceTableHelper
					.getRefRelationshipStatusValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqLeToLeRelationshipDO.getRelationshipStatusRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefRelationshipStatusDO) {

				if (null == reqLeToLeRelationshipDO.getRelationshipStatusRefValue()) {
					reqLeToLeRelationshipDO.setRelationshipStatusRefValue(theRefRelationshipStatusDO.getValue());
				} else if (!(reqLeToLeRelationshipDO.getRelationshipStatusRefValue()
						.equals(theRefRelationshipStatusDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11052",
							"Validation error : Recieved " + reqLeToLeRelationshipDO.getRelationshipStatusRefkey() + "-"
									+ reqLeToLeRelationshipDO.getRelationshipStatusRefValue()
									+ " as RelationshipStatusRefkey-  RelationshipStatusRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11052",
						"Validation error : Recieved " + reqLeToLeRelationshipDO.getRelationshipStatusRefkey()
								+ " as RelationshipStatusRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for persist in LeToLeRelationshipComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeToLeRelationshipCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LeToLeRelationshipComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLeToLeRelationshipCompMerge(LeToLeRelationshipDO reqLeToLeRelationshipDO,
			LeToLeRelationshipDO dbimageLeToLeRelationshipDO, TxnTransferObj txnTransferObj) {

		if (dbimageLeToLeRelationshipDO.getFromLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10028",
					"Recieved empty string for LeToLeRelationshipDO.fromLegalentityIdpk, this attribute cannot be updated to null");
		}

		if (dbimageLeToLeRelationshipDO.getToLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10029",
					"Recieved empty string for LeToLeRelationshipDO.toLegalentityIdpk, this attribute cannot be updated to null");
		}

		if (dbimageLeToLeRelationshipDO.getLeRelationshipTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10030",
					"Recieved empty string for LeToLeRelationshipDO.relationshipTypeRefkey, this attribute cannot be updated to null");
		}

		// LeRelationshipTypeRefkey
		if (!(null == dbimageLeToLeRelationshipDO.getLeRelationshipTypeRefkey()
				|| dbimageLeToLeRelationshipDO.getLeRelationshipTypeRefkey().isEmpty())) {
			RefLeRelationshipTypeDO theRefLeRelationshipTypeDO = referenceTableHelper
					.getRefLeRelationshipTypeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageLeToLeRelationshipDO.getLeRelationshipTypeRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRelationshipTypeDO) {

				if (null == dbimageLeToLeRelationshipDO.getLeRelationshipTypeRefValue()) {
					dbimageLeToLeRelationshipDO.setLeRelationshipTypeRefValue(theRefLeRelationshipTypeDO.getValue());
				} else if (!(dbimageLeToLeRelationshipDO.getLeRelationshipTypeRefValue()
						.equals(theRefLeRelationshipTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11051",
							"Validation error : Recieved " + dbimageLeToLeRelationshipDO.getLeRelationshipTypeRefkey()
									+ "-" + dbimageLeToLeRelationshipDO.getLeRelationshipTypeRefValue()
									+ " as LeRelationshipTypeRefkey- LeRelationshipTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11051",
						"Validation error : Recieved " + dbimageLeToLeRelationshipDO.getLeRelationshipTypeRefkey()
								+ " as LeRelationshipTypeRefkey in request which failed validation");
			}
		}

		// RelationshipStatusRefkey
		if (!(null == dbimageLeToLeRelationshipDO.getRelationshipStatusRefkey()
				|| dbimageLeToLeRelationshipDO.getRelationshipStatusRefkey().isEmpty())) {
			RefRelationshipStatusDO theRefRelationshipStatusDO = referenceTableHelper
					.getRefRelationshipStatusValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageLeToLeRelationshipDO.getRelationshipStatusRefkey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefRelationshipStatusDO) {

				if (null == dbimageLeToLeRelationshipDO.getRelationshipStatusRefValue()) {
					dbimageLeToLeRelationshipDO.setRelationshipStatusRefValue(theRefRelationshipStatusDO.getValue());
				} else if (!(dbimageLeToLeRelationshipDO.getRelationshipStatusRefValue()
						.equals(theRefRelationshipStatusDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11052",
							"Validation error : Recieved " + dbimageLeToLeRelationshipDO.getRelationshipStatusRefkey()
									+ "-" + dbimageLeToLeRelationshipDO.getRelationshipStatusRefValue()
									+ " as RelationshipStatusRefkey-  RelationshipStatusRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11052",
						"Validation error : Recieved " + dbimageLeToLeRelationshipDO.getRelationshipStatusRefkey()
								+ " as RelationshipStatusRefkey in request which failed validation");
			}
		}

	}

	/**
	 * Pre execute rule for merge in LeToLeRelationshipComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeToLeRelationshipCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LeToLeRelationshipComp This method is
	 * modularized in respective rule class Use Aspect Oriented Programming
	 * (AOP) based Yugandhar Rule to override / extend the default OOTB
	 * validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLeToLeRelationshipCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	public void prevalidateLeToLeRelationshipCompFindByLegalentityIdpk(TxnTransferObj txnTransferObj) {
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

	public void postExecuteLeToLeRelationshipCompFindByLegalentityIdpk(TxnTransferObj respTxnTransferObj) {
		// TODO Auto-generated method stub

	}

}
