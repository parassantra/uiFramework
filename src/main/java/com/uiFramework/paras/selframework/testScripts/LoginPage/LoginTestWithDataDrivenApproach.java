package com.uiFramework.paras.selframework.testScripts.LoginPage;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.paras.selframework.helper.assertion.AssertionHelper;
import com.uiFramework.paras.selframework.helper.browserConfigurations.config.ObjectReader;
import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;
import com.uiFramework.paras.selframework.pageObject.LoginPage;
import com.uiFramework.paras.selframework.pageObject.NavigationMenu;
import com.uiFramework.paras.selframework.testbase.TestBase;

public class LoginTestWithDataDrivenApproach extends TestBase{
	private final Logger log = LoggerHelper.getLogger(LoginTestWithDataDrivenApproach.class);
	
	LoginPage login;
	NavigationMenu navigationMenu;
	
	
	@DataProvider(name="testData")
	public Object[][] testData(){
		Object[][] data = getExcelData("testData.xlsx", "loginData");
		return data;
	}
	@BeforeClass
	public void beforeClass(){
		getApplicationUrl(ObjectReader.reader.getUrl());
		login = new LoginPage(driver);
	}
	@Test(dataProvider="testData")
	public void loginTest(String userName, String password, String runMode){
		
		if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("Run mode for this set of data is marked N");
		}
		login.loginToApplication(userName, password);
		boolean status = login.verifySuccessLoginMsg();
		AssertionHelper.updateTestStatus(status);
		login.logout();
	}
}