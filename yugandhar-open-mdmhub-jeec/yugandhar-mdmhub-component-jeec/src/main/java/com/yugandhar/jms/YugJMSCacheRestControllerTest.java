package com.yugandhar.jms;

public class YugJMSCacheRestControllerTest {
	
}

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.misc.dobj.CommonValidationResponse;

@RestController
@RequestMapping("/yugjms")
public class YugJMSCacheRestControllerTest {
	
	@Autowired
 CommonValidationUtil commonValidationUtil;
	CommonValidationResponse commonValidationResponse;
    
   @Autowired
   ConfigurableApplicationContext yugcontext;
   
   @Autowired
	Jackson2ObjectMapperBuilder theAutowiredJackson2ObjectMapperBuilder;

    @RequestMapping(value="/YugandharJMSProcessor",headers = "Accept=application/json", method = RequestMethod.POST)
    public String YugandharRequestProcessor(@RequestBody TxnTransferObj txnTransferObj) {
		try
		{
			 YugJMSMessageSender ms = yugcontext.getBean(YugJMSMessageSender.class);
			 //String str = "{	\"txnHeader\": {		\"requesterLanguage\": \"1\",      \"requesterLocale\": null,      \"userName\": \"admin\",      \"userRole\": \"admin\",      \"accessToken\": null,      \"txnCorrelationId\": null,      \"txnMessageId\": \"1234567890123\",      \"requestOriginSource\": null,      \"requesterSystem\": null,      \"paginationStartIndex\": null,      \"paginationEndIndex\": null,      \"paginationTotalResultCount\": null,	  \"transactionServiceName\": \"retrieveRefEntityObjectTypeBase\"	},	\"txnPayload\": {			 \"refEntityObjectTypeDO\":        {         \"idPk\": \"4\"		       }			}	}  ";
			 
			 ObjectMapper yugMapper = theAutowiredJackson2ObjectMapperBuilder.build(); 
			 
			 String responseJSONMessageAsString = null;
				try {
					responseJSONMessageAsString = yugMapper.writeValueAsString(txnTransferObj);
				} catch (JsonProcessingException e) {

					return "FAIL JsonProcessingException" + e.getMessage();
				}
			 ms.sendTextMessageToDefaultRequestQueue(responseJSONMessageAsString);
		      
			
			return "SUCCESS";
		}
    	catch (Exception e)
		{}
		return null;

	}
   
   
}


*/