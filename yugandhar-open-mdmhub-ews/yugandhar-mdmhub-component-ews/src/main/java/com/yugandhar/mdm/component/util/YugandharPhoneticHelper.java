package com.yugandhar.mdm.component.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;

@Scope(value = "prototype")
@Component
public class YugandharPhoneticHelper {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	public String getPhoneticValue(String inputStringToEncode, TxnTransferObj txnTransferObj) {

		if(null == inputStringToEncode || inputStringToEncode.isEmpty()){
			return null;
		} else {
		String outputString = null;
		String className = null;
		String methodName = null;
		// Phonetic
		ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_algorithm_class,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		
		className = theConfigAppPropertiesDO.getValue();
		
		theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_algorithm_class_method,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		methodName = theConfigAppPropertiesDO.getValue();
		
		try {

			Class<?> phoneticEncodeClass;
			@SuppressWarnings("rawtypes")
			Class[] paramString = new Class[1];
			paramString[0] = String.class;
			phoneticEncodeClass = Class.forName(className);
			Object phoneticEncoder = phoneticEncodeClass.newInstance();
			Method m = phoneticEncodeClass.getDeclaredMethod(methodName, paramString);
			outputString = (String) m.invoke(phoneticEncoder, inputStringToEncode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("YugandharPhoneticHelper failed", e);
			YugandharCommonException yce = commonValidationUtil.populateErrorResponse(txnTransferObj, "1", e,
					"YugandharPhoneticHelper.getPhoneticValue failed unexpectedly, either it could not find the method or class defined in configuration");
			throw yce;

		}

		return outputString;

	}
	}
	
	
	public List<String> getPhoneticValue(List<String> inputStringToEncode, TxnTransferObj txnTransferObj) {

		if(null == inputStringToEncode || inputStringToEncode.isEmpty()){
			return null;
		} else {
		List<String> outputStringList = new ArrayList<String>();;
		String className = null;
		String methodName = null;
		// Phonetic
		ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_algorithm_class,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		
		className = theConfigAppPropertiesDO.getValue();
		
		theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
				YugandharConfigurationProperties.com_yugandhar_phonetic_algorithm_class_method,
				yugandharConstants.FILTER_VALUE_ACTIVE);
		methodName = theConfigAppPropertiesDO.getValue();
		
		try {

			Class<?> phoneticEncodeClass;
			@SuppressWarnings("rawtypes")
			Class[] paramString = new Class[1];
			paramString[0] = String.class;
			phoneticEncodeClass = Class.forName(className);
			Object phoneticEncoder = phoneticEncodeClass.newInstance();
			Method m = phoneticEncodeClass.getDeclaredMethod(methodName, paramString);
			for(String inputString:inputStringToEncode){
				String phoneticValueOutput = (String) m.invoke(phoneticEncoder, inputString);
				outputStringList.add(phoneticValueOutput);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("YugandharPhoneticHelper failed", e);
			YugandharCommonException yce = commonValidationUtil.populateErrorResponse(txnTransferObj, "1", e,
					"YugandharPhoneticHelper.getPhoneticValue failed unexpectedly, either it could not find the method or class defined in configuration");
			throw yce;

		}

		return outputStringList;

	}
	}

}
