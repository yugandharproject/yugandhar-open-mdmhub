package com.yugandhar.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.misc.dobj.CommonValidationResponse;


/**
 * DefaultJsonRESTController is the default REST controller for Yugandhar application
 * This controller is mapping web requests to /rest url 
 * e.g. rest url to invoke transaction where yugandhar MDM is deployed having port 8090 would be as follows.
 * would be - http://localhost:8090/YugandharBootProject-1.0.0-SNAPSHOT/rest/YugandharRequestProcessor 
 * You must change the url values as per the host:port and YugandharBootProject snapshot version
 *
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@RestController
@RequestMapping("/rest")
public class DefaultJsonRESTController {
	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	@Autowired
	CommonValidationUtil commonValidationUtil;
	CommonValidationResponse commonValidationResponse;

	@Autowired
	RequestProcessor requestProcessor;

	
	
	/**
	 * This method is exposed as webservice and mapped to value YugandharRequestProcessor 
	 * accepting application/json messages through http POST method
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with persisted instance
	 * @throws YugandharCommonException
	 *             if transaction fails due to any reason
	 *
	 */
	
	@RequestMapping(value = "/YugandharRequestProcessor", method = RequestMethod.POST)
	public TxnTransferObj YugandharRequestProcessor(@RequestBody TxnTransferObj txnTransferObj) {
		try {
			return requestProcessor.processMessage(txnTransferObj);
		} catch (Exception e) {

			logger.error("Transaction failed", e);
			TxnTransferObj txnErrTransferObj = new TxnTransferObj();
			txnErrTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			if (e instanceof YugandharCommonException) {
				YugandharCommonException yce = (YugandharCommonException) e;
				txnErrTransferObj.getTxnPayload().setErrorResponseObj(
						commonValidationUtil.createCommonValidationResponseFromYugException(txnErrTransferObj, yce));

			} else {
				txnErrTransferObj.getTxnPayload().setErrorResponseObj(
						commonValidationUtil.createCommonValidationResponseFromException(txnErrTransferObj, "1", e));

			}
			return txnErrTransferObj;

		}

	}

}
