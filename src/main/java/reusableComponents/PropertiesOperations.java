package reusableComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesOperations {
	
	// To create the object of Properties File, using this object we are going to load and read the data from properties file
	static Properties prop = new Properties();
	
	public static String getPropertyValueByKey(String key) throws Exception {
		// Load data from Properties File
		
		// To get the location of Properties file 
		String propFilePath = System.getProperty("user.dir")+"/src/test/resources/config.properties";
		FileInputStream fis = new FileInputStream(propFilePath);
		prop.load(fis);
 		
		// Read data from Properties File
		String value = prop.get(key).toString();
		
		if(StringUtils.isEmpty(value)) {
			throw new Exception("Value is not specified for key: "+key+" in properties file");
		}
		
		return value;
	}
}