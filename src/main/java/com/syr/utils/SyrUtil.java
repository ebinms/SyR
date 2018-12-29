package com.syr.utils;

import java.util.Hashtable;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author apple
 * @on 27-Sep-2018
 * @version 
 */
public class SyrUtil {

	private static Hashtable<String, Properties> propTable;
	
	public static Properties getProperty(String propName) {
		if(propTable!=null&&propTable.containsKey(propName)){
			System.out.println("Got Property From Hash Table........."+propName);
			return propTable.get(propName);
		}else{
			Properties property = null;
			try {
				
				Resource resource = new ClassPathResource("Properties/"+propName+".properties");
				property = PropertiesLoaderUtils.loadProperties(resource);
				
				if(propTable==null){
					propTable	= new Hashtable<>(1);
				}
				propTable.put(propName, property);
			} catch (Exception e) {
				System.out.println("Error while fetching property...."+propName+"---------------->"+e.getMessage());
			}
			return property;
		}
	}
	
	public static double formatDouble(double value) {
		return Double.valueOf(String.format("%.2f", value));
	}
}
