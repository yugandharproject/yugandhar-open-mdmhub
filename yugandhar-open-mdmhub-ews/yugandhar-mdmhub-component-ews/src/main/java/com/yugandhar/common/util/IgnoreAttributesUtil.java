package com.yugandhar.common.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class IgnoreAttributesUtil {

	public String[] getAttributesToIgnore(Object obj) {
		// This method is used to get the comma separate attributes from configuration file to ignore the attributes
		//String[] strIgnoreProperties = new String[] {"createdTs","updatedByUser"};
		ArrayList<String> strList=new ArrayList<String>();
		strList= getListOfNullProperties(obj);
		strList.add("createdTs");
		String[] strIgnoreProperties = new String[strList.size()];
		strIgnoreProperties = strList.toArray(strIgnoreProperties);
		return (strIgnoreProperties);
	}
	
	
    /**
     * Gets the properties which have null values from the given object.
     * 
     * @param obj source object
     * 
     * @return  ArrayList<String> arraylist of property names.
     */
    public static ArrayList<String> getListOfNullProperties(Object obj) {
        final BeanWrapper src = new BeanWrapperImpl(obj);
        PropertyDescriptor[] propertyDescriptor = src.getPropertyDescriptors();

        ArrayList<String> nullPropertiesList = new ArrayList<String>();
        for (PropertyDescriptor pd : propertyDescriptor) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
            	nullPropertiesList.add(pd.getName());
        }
        return nullPropertiesList;
    }

}
