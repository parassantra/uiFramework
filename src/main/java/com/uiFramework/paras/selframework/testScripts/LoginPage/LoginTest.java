package com.uiFramework.paras.selframework.testScripts.LoginPage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiFramework.paras.selframework.helper.assertion.AssertionHelper;
import com.uiFramework.paras.selframework.helper.browserConfigurations.config.ObjectReader;
import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;
import com.uiFramework.paras.selframework.pageObject.LoginPage;
import com.uiFramework.paras.selframework.testbase.TestBase;

public class LoginTest extends TestBase {
	private final Logger log =  LoggerHelper.getLogger(LoginPage.class);
	
	@Test(description="Login test with valid credentials")
	public void testLoginApplication(){
		getApplicationUrl(ObjectReader.reader.getUrl());
		LoginPage lp = new LoginPage(driver);
		lp.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		boolean status = lp.verifySuccessLoginMsg();
		AssertionHelper.updateTestStatus(status);
	}
}
