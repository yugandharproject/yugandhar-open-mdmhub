package com.yugandhar.mdm.match.componentref;
/* Generated Oct 27, 2017 5:17:39 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import com.yugandhar.common.constant.yugandharConstants;
import org.springframework.transaction.annotation.Transactional;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.util.CommonValidationUtil;

/**
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see Documentation
*/

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.match.componentref.RefMatchProposedActionService")
public class RefMatchProposedActionService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	private RefMatchProposedActionComponent theRefMatchProposedActionComponent;

	public RefMatchProposedActionService() {
		txnTransferObjResponse = new TxnTransferObj();
	}

	/**
	*This service creates a record in database. generates the idpk if not provided in the request and 
	*populate the updatedByUser, updatedTxnRefId, createdTsString, updatedTsString attributes.
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with persisted instance 
	*@throws YugandharCommonException if RefMatchProposedActionDO object is not present in the request or other mandatory attributes not present
	*
	*/
	@Transactional
	public TxnTransferObj add(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj respTxnTransferObj;
		try {
			respTxnTransferObj = theRefMatchProposedActionComponent.persist(txnTransferObj);
		} catch (YugandharCommonException yce) {
			logger.error("RefMatchProposedActionService.add failed", yce);
			throw yce;
		} catch (Exception e) {
			//write the logic to get error message based on error code. Error code is hard-coded here
			logger.error("RefMatchProposedActionService.add failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", e,
					"RefMatchProposedActionService.add failed unexpectedly");

		}
		return respTxnTransferObj;

	}

	/**This service updates the record in database. populate the updatedByUser, updatedTxnRefId, updatedTsString attributes
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if RefMatchProposedActionDO object is not present in the request or mandatory attributes primary key is not present
	*/
	@Transactional
	public TxnTransferObj merge(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj respTxnTransferObj;
		try {
			respTxnTransferObj = theRefMatchProposedActionComponent.merge(txnTransferObj);
		} catch (YugandharCommonException yce) {
			logger.error("RefMatchProposedActionService.merge failed", yce);
			throw yce;
		} catch (Exception e) {
			//write the logic to get error message based on error code. Error code is hard-coded here
			logger.error("RefMatchProposedActionService.merge failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", e,
					"RefMatchProposedActionService.merge failed unexpectedly");

		}
		return respTxnTransferObj;
	}

	/**
	* This method search the database record based on primary key. 
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if RefMatchProposedActionDO object is not present in the request or mandatory attributes primary key is not present
	*/
	@Transactional(readOnly = true)
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj respTxnTransferObj;
		try {
			respTxnTransferObj = theRefMatchProposedActionComponent.findById(txnTransferObj);
		} catch (YugandharCommonException yce) {
			logger.error("RefMatchProposedActionService.findById failed", yce);
			throw yce;
		} catch (Exception e) {
			//write the logic to get error message based on error code. Error code is hard-coded here
			logger.error("RefMatchProposedActionService.findById failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", e,
					"RefMatchProposedActionService.findById failed unexpectedly");

		}
		return respTxnTransferObj;

	}

	/**
	* This method search the database record based on business key (e.g.LanguageCode and Key)
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if RefMatchProposedActionDO object is not present in the request or mandatory attributes business key is not present
	*/
	@Transactional(readOnly = true)
	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj respTxnTransferObj;
		try {
			respTxnTransferObj = theRefMatchProposedActionComponent.findByBusinessKey(txnTransferObj);
		} catch (YugandharCommonException yce) {
			logger.error("RefMatchProposedActionService.findByBusinessKey failed", yce);
			throw yce;
		} catch (Exception e) {
			//write the logic to get error message based on error code. Error code is hard-coded here
			logger.error("RefMatchProposedActionService.findByBusinessKey failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", e,
					"RefMatchProposedActionService.findByBusinessKey failed unexpectedly");

		}
		return respTxnTransferObj;

	}

	/*	This method gets all the records based on language code
	 *	@param txnTransferObj  Transfer Object TxnTransferObj instance 
	 *	@return txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with list of RefMatchProposedActionDO objects
	 */
	@Transactional(readOnly = true)
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		try {
			txnTransferObj = theRefMatchProposedActionComponent.findAllRecordsByLanguageCode(txnTransferObj);
		} catch (YugandharCommonException yce) {
			logger.error("RefMatchProposedActionService.findAllRecordsByLanguageCode failed", yce);
			throw yce;
		} catch (Exception e) {
			//write the logic to get error message based on error code. Error code is hard-coded here
			logger.error("RefMatchProposedActionService.findAllRecordsByLanguageCode failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", e,
					"RefMatchProposedActionService.findAllRecordsByLanguageCode failed unexpectedly");

		}
		return txnTransferObj;

	}

}
