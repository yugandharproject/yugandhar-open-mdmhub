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
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;
import com.yugandhar.mdm.extern.dobj.RefClassificationCodeDO;
import com.yugandhar.mdm.extern.dobj.RefEntityObjectTypeDO;
import com.yugandhar.mdm.extern.dobj.RefImportanceTypeDO;
import com.yugandhar.mdm.extern.dobj.RefLeRatingDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.extern.dobj.RefStatusTypeDO;

@Scope(value = "prototype")
@Component
public class LegalentityComponentRule {

	@Autowired
	protected CommonValidationUtil commonValidationUtil;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	@Autowired
	ConfigAppPropertiesComponent theConfigAppPropertiesComponent;

	@Autowired
	YugandharPhoneticHelper theYugandharPhoneticHelper;

	/**
	 * Pre execute rule for merge in LegalentityComp This rule is externalized
	 * in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	 * Override this method in YugandharPrePostComponentRule to modify the
	 * default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLegalentityCompMerge(LegalentityDO reqLegalentityDO, LegalentityDO dbimageLegalentityDO,
			TxnTransferObj txnTransferObj) {

		if (dbimageLegalentityDO.getDisplayName().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10001",
					"Recieved empty string for LegalentityDO.DisplayName, this attribute cannot be updated to null");
		}

		if (dbimageLegalentityDO.getEntityObjectTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10002",
					"Recieved empty string for LegalentityDO.EntityObjectTypeRefkey, this attribute cannot be updated to null");
		}

		// entityObjectTypeRefkey
		if (!(null == dbimageLegalentityDO.getEntityObjectTypeRefkey()
				|| dbimageLegalentityDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLegalentityDO.getEntityObjectTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {

				if (null == dbimageLegalentityDO.getEntityObjectTypeRefValue()) {
					dbimageLegalentityDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
				} else if (!(dbimageLegalentityDO.getEntityObjectTypeRefValue()
						.equals(theRefEntityObjectTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11030",
							"Validation error : Recieved " + dbimageLegalentityDO.getEntityObjectTypeRefkey() + "-"
									+ dbimageLegalentityDO.getEntityObjectTypeRefValue()
									+ " as entityObjectTypeRefkey- entityObjectTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11030",
						"Validation error : Recieved " + dbimageLegalentityDO.getEntityObjectTypeRefkey()
								+ " as entityObjectTypeRefkey in request which failed validation");
			}
		}

		// classificationCodeRefkey
		if (!(null == dbimageLegalentityDO.getClassificationCodeRefkey()
				|| dbimageLegalentityDO.getClassificationCodeRefkey().isEmpty())) {
			RefClassificationCodeDO theRefClassificationCodeDO = referenceTableHelper
					.getRefClassificationCodeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							dbimageLegalentityDO.getClassificationCodeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefClassificationCodeDO) {

				if (null == dbimageLegalentityDO.getClassificationCodeRefValue()) {
					dbimageLegalentityDO.setClassificationCodeRefValue(theRefClassificationCodeDO.getValue());
				} else if (!(dbimageLegalentityDO.getClassificationCodeRefValue()
						.equals(theRefClassificationCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11031",
							"Validation error : Recieved " + dbimageLegalentityDO.getClassificationCodeRefkey() + "-"
									+ dbimageLegalentityDO.getClassificationCodeRefValue()
									+ " as ClassificationCodeRefkey- ClassificationCodeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11031",
						"Validation error : Recieved " + dbimageLegalentityDO.getClassificationCodeRefkey()
								+ " as ClassificationCodeRefkey in request which failed validation");
			}
		}

		// ImportanceTypeRefkey
		if (!(null == dbimageLegalentityDO.getImportanceTypeRefkey()
				|| dbimageLegalentityDO.getImportanceTypeRefkey().isEmpty())) {
			RefImportanceTypeDO theRefImportanceTypeDO = referenceTableHelper.getRefImportanceTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(),
					dbimageLegalentityDO.getImportanceTypeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefImportanceTypeDO) {

				if (null == dbimageLegalentityDO.getImportanceTypeRefValue()) {
					dbimageLegalentityDO.setImportanceTypeRefValue(theRefImportanceTypeDO.getValue());
				} else if (!(dbimageLegalentityDO.getImportanceTypeRefValue()
						.equals(theRefImportanceTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11032",
							"Validation error : Recieved " + dbimageLegalentityDO.getImportanceTypeRefkey() + "-"
									+ dbimageLegalentityDO.getImportanceTypeRefValue()
									+ " as ImportanceTypeRefkey- ImportanceTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11032",
						"Validation error : Recieved " + dbimageLegalentityDO.getImportanceTypeRefkey()
								+ " as ImportanceTypeRefkey in request which failed validation");
			}
		}

		// LeRatingRefkey
		if (!(null == dbimageLegalentityDO.getLeRatingRefkey() || dbimageLegalentityDO.getLeRatingRefkey().isEmpty())) {
			RefLeRatingDO theRefLeRatingDO = referenceTableHelper.getRefLeRatingValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLegalentityDO.getLeRatingRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRatingDO) {

				if (null == dbimageLegalentityDO.getLeRatingRefValue()) {
					dbimageLegalentityDO.setLeRatingRefValue(theRefLeRatingDO.getValue());
				} else if (!(dbimageLegalentityDO.getLeRatingRefValue().equals(theRefLeRatingDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11033",
							"Validation error : Recieved " + dbimageLegalentityDO.getLeRatingRefkey() + "-"
									+ dbimageLegalentityDO.getLeRatingRefValue()
									+ " as LeRatingRefkey- LeRatingRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11033",
						"Validation error : Recieved " + dbimageLegalentityDO.getLeRatingRefkey()
								+ " as LeRatingRefkey in request which failed validation");
			}
		}

		// StatusTypeRefkey
		if (!(null == dbimageLegalentityDO.getStatusTypeRefkey()
				|| dbimageLegalentityDO.getStatusTypeRefkey().isEmpty())) {
			RefStatusTypeDO theRefStatusTypeDO = referenceTableHelper.getRefStatusTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLegalentityDO.getStatusTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefStatusTypeDO) {

				if (null == dbimageLegalentityDO.getStatusTypeRefValue()) {
					dbimageLegalentityDO.setStatusTypeRefValue(theRefStatusTypeDO.getValue());
				} else if (!(dbimageLegalentityDO.getStatusTypeRefValue().equals(theRefStatusTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11034",
							"Validation error : Recieved " + dbimageLegalentityDO.getStatusTypeRefkey() + "-"
									+ dbimageLegalentityDO.getStatusTypeRefValue()
									+ " as StatusTypeRefkey- StatusTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11034",
						"Validation error : Recieved " + dbimageLegalentityDO.getStatusTypeRefkey()
								+ " as StatusTypeRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == dbimageLegalentityDO.getSourceSystemRefkey()
				|| dbimageLegalentityDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), dbimageLegalentityDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == dbimageLegalentityDO.getSourceSystemRefValue()) {
					dbimageLegalentityDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(dbimageLegalentityDO.getSourceSystemRefValue().equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11035",
							"Validation error : Recieved " + dbimageLegalentityDO.getSourceSystemRefkey() + "-"
									+ dbimageLegalentityDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11035",
						"Validation error : Recieved " + dbimageLegalentityDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}

		// Phonetic
		ConfigAppPropertiesDO theConfigAppPropertiesDO = theConfigAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_framework_enabled,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		if (theConfigAppPropertiesDO.getValue().equalsIgnoreCase(yugandharConstants.FLAG_true)) {

			if (!(null == dbimageLegalentityDO.getDisplayName() || dbimageLegalentityDO.getDisplayName().isEmpty())) {
				dbimageLegalentityDO.setPhoneticDisplayName(theYugandharPhoneticHelper
						.getPhoneticValue(dbimageLegalentityDO.getDisplayName(), txnTransferObj));
			}

		}

	}

	/**
	 * Pre execute rule for persist in LegalentityComp This rule is externalized
	 * in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	 * Override this method in YugandharPrePostComponentRule to modify the
	 * default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void preExecuteLegalentityCompPersist(LegalentityDO reqLegalentityDO, TxnTransferObj txnTransferObj) {
		// entityObjectTypeRefkey
		if (!(null == reqLegalentityDO.getEntityObjectTypeRefkey()
				|| reqLegalentityDO.getEntityObjectTypeRefkey().isEmpty())) {
			RefEntityObjectTypeDO theRefEntityObjectTypeDO = referenceTableHelper.getRefEntityObjectTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLegalentityDO.getEntityObjectTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefEntityObjectTypeDO) {

				if (null == reqLegalentityDO.getEntityObjectTypeRefValue()) {
					reqLegalentityDO.setEntityObjectTypeRefValue(theRefEntityObjectTypeDO.getValue());
				} else if (!(reqLegalentityDO.getEntityObjectTypeRefValue()
						.equals(theRefEntityObjectTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11030",
							"Validation error : Recieved " + reqLegalentityDO.getEntityObjectTypeRefkey() + "-"
									+ reqLegalentityDO.getEntityObjectTypeRefValue()
									+ " as entityObjectTypeRefkey- entityObjectTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11030",
						"Validation error : Recieved " + reqLegalentityDO.getEntityObjectTypeRefkey()
								+ " as entityObjectTypeRefkey in request which failed validation");
			}
		}

		// classificationCodeRefkey
		if (!(null == reqLegalentityDO.getClassificationCodeRefkey()
				|| reqLegalentityDO.getClassificationCodeRefkey().isEmpty())) {
			RefClassificationCodeDO theRefClassificationCodeDO = referenceTableHelper
					.getRefClassificationCodeValueByKey(txnTransferObj.getTxnHeader().getRequesterLanguage(),
							reqLegalentityDO.getClassificationCodeRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefClassificationCodeDO) {

				if (null == reqLegalentityDO.getClassificationCodeRefValue()) {
					reqLegalentityDO.setClassificationCodeRefValue(theRefClassificationCodeDO.getValue());
				} else if (!(reqLegalentityDO.getClassificationCodeRefValue()
						.equals(theRefClassificationCodeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11031",
							"Validation error : Recieved " + reqLegalentityDO.getClassificationCodeRefkey() + "-"
									+ reqLegalentityDO.getClassificationCodeRefValue()
									+ " as ClassificationCodeRefkey- ClassificationCodeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11031",
						"Validation error : Recieved " + reqLegalentityDO.getClassificationCodeRefkey()
								+ " as ClassificationCodeRefkey in request which failed validation");
			}
		}

		// ImportanceTypeRefkey
		if (!(null == reqLegalentityDO.getImportanceTypeRefkey()
				|| reqLegalentityDO.getImportanceTypeRefkey().isEmpty())) {
			RefImportanceTypeDO theRefImportanceTypeDO = referenceTableHelper.getRefImportanceTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLegalentityDO.getImportanceTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefImportanceTypeDO) {

				if (null == reqLegalentityDO.getImportanceTypeRefValue()) {
					reqLegalentityDO.setImportanceTypeRefValue(theRefImportanceTypeDO.getValue());
				} else if (!(reqLegalentityDO.getImportanceTypeRefValue().equals(theRefImportanceTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11032",
							"Validation error : Recieved " + reqLegalentityDO.getImportanceTypeRefkey() + "-"
									+ reqLegalentityDO.getImportanceTypeRefValue()
									+ " as ImportanceTypeRefkey- ImportanceTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11032",
						"Validation error : Recieved " + reqLegalentityDO.getImportanceTypeRefkey()
								+ " as ImportanceTypeRefkey in request which failed validation");
			}
		}

		// LeRatingRefkey
		if (!(null == reqLegalentityDO.getLeRatingRefkey() || reqLegalentityDO.getLeRatingRefkey().isEmpty())) {
			RefLeRatingDO theRefLeRatingDO = referenceTableHelper.getRefLeRatingValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLegalentityDO.getLeRatingRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefLeRatingDO) {

				if (null == reqLegalentityDO.getLeRatingRefValue()) {
					reqLegalentityDO.setLeRatingRefValue(theRefLeRatingDO.getValue());
				} else if (!(reqLegalentityDO.getLeRatingRefValue().equals(theRefLeRatingDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11033",
							"Validation error : Recieved " + reqLegalentityDO.getLeRatingRefkey() + "-"
									+ reqLegalentityDO.getLeRatingRefValue()
									+ " as LeRatingRefkey- LeRatingRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11033",
						"Validation error : Recieved " + reqLegalentityDO.getLeRatingRefkey()
								+ " as LeRatingRefkey in request which failed validation");
			}
		}

		// StatusTypeRefkey
		if (!(null == reqLegalentityDO.getStatusTypeRefkey() || reqLegalentityDO.getStatusTypeRefkey().isEmpty())) {
			RefStatusTypeDO theRefStatusTypeDO = referenceTableHelper.getRefStatusTypeValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLegalentityDO.getStatusTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefStatusTypeDO) {

				if (null == reqLegalentityDO.getStatusTypeRefValue()) {
					reqLegalentityDO.setStatusTypeRefValue(theRefStatusTypeDO.getValue());
				} else if (!(reqLegalentityDO.getStatusTypeRefValue().equals(theRefStatusTypeDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11034",
							"Validation error : Recieved " + reqLegalentityDO.getStatusTypeRefkey() + "-"
									+ reqLegalentityDO.getStatusTypeRefValue()
									+ " as StatusTypeRefkey- StatusTypeRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11034",
						"Validation error : Recieved " + reqLegalentityDO.getStatusTypeRefkey()
								+ " as StatusTypeRefkey in request which failed validation");
			}
		}

		// SourceSystemRefkey
		if (!(null == reqLegalentityDO.getSourceSystemRefkey() || reqLegalentityDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					txnTransferObj.getTxnHeader().getRequesterLanguage(), reqLegalentityDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {

				if (null == reqLegalentityDO.getSourceSystemRefValue()) {
					reqLegalentityDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
				} else if (!(reqLegalentityDO.getSourceSystemRefValue().equals(theRefSourceSystemDO.getValue()))) {
					throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11035",
							"Validation error : Recieved " + reqLegalentityDO.getSourceSystemRefkey() + "-"
									+ reqLegalentityDO.getSourceSystemRefValue()
									+ " as SourceSystemRefkey- SourceSystemRefValue pair in request which failed validation");
				}

			} else {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "11035",
						"Validation error : Recieved " + reqLegalentityDO.getSourceSystemRefkey()
								+ " as SourceSystemRefkey in request which failed validation");
			}
		}

		// Phonetic
		ConfigAppPropertiesDO theConfigAppPropertiesDO = theConfigAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_framework_enabled,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		if (theConfigAppPropertiesDO.getValue().equalsIgnoreCase(yugandharConstants.FLAG_true)) {

			if (!(null == reqLegalentityDO.getDisplayName() || reqLegalentityDO.getDisplayName().isEmpty())) {
				reqLegalentityDO.setPhoneticDisplayName(
						theYugandharPhoneticHelper.getPhoneticValue(reqLegalentityDO.getDisplayName(), txnTransferObj));
			}

		}

	}

	/**
	 * Pre execute rule for persist in LegalentityComp This rule is externalized
	 * in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	 * Override this method in YugandharPrePostComponentRule to modify the
	 * default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLegalentityCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for merge in LegalentityComp This rule is externalized
	 * in com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	 * Override this method in YugandharPrePostComponentRule to modify the
	 * default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLegalentityCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute rule for findbyId in LegalentityComp This rule is
	 * externalized in
	 * com.yugandhar.mdm.extern.util.YugandharPrePostComponentRule class
	 * Override this method in YugandharPrePostComponentRule to modify the
	 * default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void postExecuteLegalentityCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

	/**
	 * Pre execute persist validation method for LegalentityComp to validate
	 * mandatory attributes etc This method is externalized in
	 * com.yugandhar.mdm.extern.util.CommonValidationUtil class Override this
	 * method in CommonValidationUtil to modify the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLegalentityCompPersit(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute merge validation method for LegalentityComp to validate
	 * mandatory attributes etc This method is externalized in
	 * com.yugandhar.mdm.extern.util.CommonValidationUtil class Override this
	 * method in CommonValidationUtil to modify the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void PrevalidateLegalentityCompMerge(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Pre execute findbyId validation method for LegalentityComp to validate
	 * mandatory attributes etc This method is externalized in
	 * com.yugandhar.mdm.extern.util.CommonValidationUtil class Override this
	 * method in CommonValidationUtil to modify the default OOTB validation
	 * 
	 * @throws YugandharCommonException
	 */
	public void prevalidateLegalentityCompFindById(TxnTransferObj txnTransferObj) {
		// TODO Auto-generated method stub

	}

}
