package com.yugandhar.jms;

import java.io.IOException;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.misc.dobj.CommonValidationResponse;
import com.yugandhar.rest.controller.RequestProcessor;

@Component
public class YugDefaultRequestQueueListener {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	private static final Logger mqReqResplogger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_MQ_REQ_RESP_LOGGER);
	

	@Autowired
	CommonValidationUtil commonValidationUtil;
	CommonValidationResponse commonValidationResponse;

	@Autowired
	RequestProcessor requestProcessor;

	@Autowired
	ConfigurableApplicationContext yugcontext;

	@Autowired
	YugJMSMessageSender theYugJMSMessageSender;

	@Autowired
	Jackson2ObjectMapperBuilder theAutowiredJackson2ObjectMapperBuilder;
	
	@JmsListener(destination="YUG.DEFAULT.REQUEST") //YUG.DEFAULT.REQUEST
	public void onMessage(Message message) {

		if (message instanceof TextMessage) {
			try {
				String text = ((TextMessage) message).getText();
				
				if (mqReqResplogger.isDebugEnabled()) {
					mqReqResplogger.debug("REQUEST MESSAGE: " + text);
				}
				
				ObjectMapper yugMapper = theAutowiredJackson2ObjectMapperBuilder.build(); 

				TxnTransferObj txnTransferObj = yugMapper.readValue(text, TxnTransferObj.class);

				TxnTransferObj respTxnTransferObj = processMessage(txnTransferObj);
				sendResponseToOutboundQueue(respTxnTransferObj);
			} catch (JMSException e) {
				e.printStackTrace();
				TxnTransferObj errTxnTransferObj = new TxnTransferObj();
				errTxnTransferObj.getTxnPayload().setErrorResponseObj(
						commonValidationUtil.createCommonValidationResponseFromException(errTxnTransferObj, "1", e));
				// send to response queue
				sendResponseToOutboundQueue(errTxnTransferObj);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				TxnTransferObj errTxnTransferObj = new TxnTransferObj();
				errTxnTransferObj.getTxnPayload().setErrorResponseObj(
						commonValidationUtil.createCommonValidationResponseFromException(errTxnTransferObj, "1", e));
				// send to response queue
				sendResponseToOutboundQueue(errTxnTransferObj);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				TxnTransferObj errTxnTransferObj = new TxnTransferObj();
				errTxnTransferObj.getTxnPayload().setErrorResponseObj(
						commonValidationUtil.createCommonValidationResponseFromException(errTxnTransferObj, "1", e));
				// send to response queue
				sendResponseToOutboundQueue(errTxnTransferObj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				TxnTransferObj errTxnTransferObj = new TxnTransferObj();
				errTxnTransferObj.getTxnPayload().setErrorResponseObj(
						commonValidationUtil.createCommonValidationResponseFromException(errTxnTransferObj, "1", e));
				// send to response queue
				sendResponseToOutboundQueue(errTxnTransferObj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				TxnTransferObj errTxnTransferObj = new TxnTransferObj();
				errTxnTransferObj.getTxnPayload().setErrorResponseObj(
						commonValidationUtil.createCommonValidationResponseFromException(errTxnTransferObj, "1", e));
				// send to response queue
				sendResponseToOutboundQueue(errTxnTransferObj);
			}
		} else {
			TxnTransferObj errTxnTransferObj = new TxnTransferObj();
			Exception e = new Exception("Inbound message is Not a valid TextMessage");
			errTxnTransferObj.getTxnPayload().setErrorResponseObj(
					commonValidationUtil.createCommonValidationResponseFromException(errTxnTransferObj, "1", e));
			// send to response queue
			sendResponseToOutboundQueue(errTxnTransferObj);
		}
	}

	private TxnTransferObj processMessage(TxnTransferObj txnTransferObj) {

		try {
			yugcontext.getBean(RequestProcessor.class);
			return requestProcessor.processMessage(txnTransferObj);

		} catch (Exception e) {

			logger.error("Transaction failed", e);
			TxnTransferObj errTxnTransferObj = new TxnTransferObj();
			errTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
			if (e instanceof YugandharCommonException) {
				YugandharCommonException yce = (YugandharCommonException) e;
				errTxnTransferObj.getTxnPayload().setErrorResponseObj(
						commonValidationUtil.createCommonValidationResponseFromYugException(errTxnTransferObj, yce));

			} else {
				errTxnTransferObj.getTxnPayload().setErrorResponseObj(
						commonValidationUtil.createCommonValidationResponseFromException(errTxnTransferObj, "1", e));

			}
			return errTxnTransferObj;

		}

	}

	public void sendResponseToOutboundQueue(TxnTransferObj txnTransferObj) {

		ObjectMapper yugMapper = theAutowiredJackson2ObjectMapperBuilder.build(); 
		String responseJSONMessageAsString = null;
		try {
			responseJSONMessageAsString = yugMapper.writeValueAsString(txnTransferObj);
			
			if (mqReqResplogger.isDebugEnabled()) {
				mqReqResplogger.debug("RESPONSE MESSAGE: " + responseJSONMessageAsString);
			}
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TxnTransferObj errTxnTransferObj = new TxnTransferObj();
			errTxnTransferObj.getTxnPayload().setErrorResponseObj(
					commonValidationUtil.createCommonValidationResponseFromException(errTxnTransferObj, "1", e));
		}

		theYugJMSMessageSender.sendTextMessageToDefaultResponseQueue(responseJSONMessageAsString);
	}

}
