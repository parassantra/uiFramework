package com.uiFramework.paras.selframework.testScripts.registration;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiFramework.paras.selframework.helper.assertion.AssertionHelper;
import com.uiFramework.paras.selframework.helper.browserConfigurations.config.ObjectReader;
import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;
import com.uiFramework.paras.selframework.pageObject.LoginPage;
import com.uiFramework.paras.selframework.pageObject.MyAccountPage;
import com.uiFramework.paras.selframework.pageObject.RegistrationPage;
import com.uiFramework.paras.selframework.testbase.TestBase;

public class ResgistrationTest extends TestBase{
	private final Logger log = LoggerHelper.getLogger(ResgistrationTest.class);
	LoginPage loginPage;
	RegistrationPage register;
	MyAccountPage myAccountPage;
	
	@Test(description="New Registration test with valid credentials")
	public void testRegistration(){
		getApplicationUrl(ObjectReader.reader.getUrl());
		LoginPage lp = new LoginPage(driver);
		lp.clickOnSignInLink();
		lp.enterRegistrationEmail();
		register=lp.clickOnCreateAnAccount();
		
		register.setMrRadioButton();
		register.setFirstName("firstName");
		register.setLastname("lastname");
		register.setPassword("password");
		register.setAddress("address");
		register.setDay("5");
		register.setMonth("June");
		register.setYear("2017");
		register.setYourAddressFirstName("yourAddressFirstName");
		register.setYourAddressLastName("yourAddressLastName");
		register.setYourAddressCompany("yourAddressCompany");
		register.setYourAddressCity("yourAddressCity");
		register.setYourAddressState("Alaska");
		register.setYourAddressPostCode("99501");
		register.setMobilePhone("9999999999");
		register.setAddressAlias("addressAlias");
		register.clickRegistration();
		
		myAccountPage = new MyAccountPage(driver);
		boolean status = myAccountPage.isYourAccountPageMessage();
		
		AssertionHelper.updateTestStatus(status);
	}
	
	
}
