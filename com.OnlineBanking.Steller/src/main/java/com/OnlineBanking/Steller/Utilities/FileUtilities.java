package com.OnlineBanking.Steller.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is used to handle file utilities
 * @author HP
 *
 */

public class FileUtilities {
	/**
	 * this method is used to get the values from property file
	 * @param key
	 * @return
	 * @throws IOException 
	 */
public String getPropertyKeyValue(String key) throws IOException
{
	FileInputStream fis=new FileInputStream(IPathConstants.propertyFilePath);
	Properties p=new Properties();
	p.load(fis);
	String value = p.getProperty(key);
	return value;
}
}
