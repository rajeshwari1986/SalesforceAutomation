package sfdc.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DataUtilities {
	
	/**
	 * @param keyName
	 * @return
	 * @throws IOException
	 */
	public String readAccountProperties(String keyName) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.USER_ACCOUNTS_FILE_PATH);
		prop.load(fis);
		return prop.getProperty(keyName);
	}
	
	/**
	 * @param keyNamen pass the url key name eg: prod.url
	 * @return URL of perticular envirnoment
	 * @throws IOException
	 */
	public String readAppEnvirnoments(String keyName) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.APP_ENV_ACCOUNTS_FILE_PATH);
		prop.load(fis);
		return prop.getProperty(keyName);
	}
	
	public String readPageValidationsText(String keyName) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.PAGE_VALIDATIONS_FILE_PATH);
		prop.load(fis);
		return prop.getProperty(keyName);
	}

}
