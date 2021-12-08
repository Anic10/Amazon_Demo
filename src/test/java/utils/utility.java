package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class utility {

	public static String getPropertyValue(String propertyName) throws Exception {
		File file = new File(System.getProperty("user.dir")+"/config.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		String propertyValue = prop.getProperty(propertyName);
		return propertyValue;
	}
}
