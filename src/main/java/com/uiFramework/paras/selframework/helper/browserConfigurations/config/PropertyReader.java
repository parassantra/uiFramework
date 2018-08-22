package com.uiFramework.paras.selframework.helper.browserConfigurations.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.uiFramework.paras.selframework.helper.browserConfigurations.BrowserType;
import com.uiFramework.resource.ResourceHelper;

public class PropertyReader implements ConfigReader{
	private static FileInputStream file;
	public static Properties or;
	public PropertyReader(){
		String filePath=ResourceHelper.getResourcePath("src/main/resources/configfile/config.properties");
		try {
			file = new FileInputStream(new File(filePath));
			or = new Properties();
			or.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public int getImplicitWait() {
		return Integer.parseInt(or.getProperty("implicitwait"));
	}

	@Override
	public int getExplicitWait() {
		return Integer.parseInt(or.getProperty("explicitwait"));
	}

	@Override
	public int getPageLoadTime() {
		return Integer.parseInt(or.getProperty("pageloadtime"));
	}

	@Override
	public BrowserType getBrowserType() {
		return BrowserType.valueOf(or.getProperty("browserType"));
	}

	public String getUrl() {
		if(System.getProperty("url")!=null){
			return System.getProperty("url");
		}
		return or.getProperty("applicationUrl");
	}

	public String getUserName() {
		if(System.getProperty("userName")!=null){
			return System.getProperty("userName");
		}
		return or.getProperty("userName");
	}

	public String getPassword() {
		if(System.getProperty("password")!=null){
			return System.getProperty("password");
		}
		return or.getProperty("password");
	}

}
