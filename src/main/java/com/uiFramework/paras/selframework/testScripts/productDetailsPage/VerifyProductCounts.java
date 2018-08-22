package com.uiFramework.paras.selframework.testScripts.productDetailsPage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiFramework.paras.selframework.helper.assertion.AssertionHelper;
import com.uiFramework.paras.selframework.helper.browserConfigurations.config.ObjectReader;
import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;
import com.uiFramework.paras.selframework.pageObject.ApplicationText;
import com.uiFramework.paras.selframework.pageObject.LoginPage;
import com.uiFramework.paras.selframework.pageObject.NavigationMenu;
import com.uiFramework.paras.selframework.pageObject.ProductCategoryPage;
import com.uiFramework.paras.selframework.testScripts.registration.ResgistrationTest;
import com.uiFramework.paras.selframework.testbase.TestBase;

public class VerifyProductCounts extends TestBase{
	LoginPage loginPage;
	NavigationMenu navMenu;
	
	private final Logger log = LoggerHelper.getLogger(VerifyProductCounts.class);
	@Test
	public void testVerifyProductCounts(){
		getApplicationUrl(ObjectReader.reader.getUrl());
		NavigationMenu navMenu = new NavigationMenu(driver);
		ProductCategoryPage pcg = navMenu.clickOnMenu(navMenu.womenMenu);
		pcg.selectColor(ApplicationText.Orange);
		int count = pcg.getTotalProducts();
		
		if(count==3){
			log.info("Count of select Color "+ApplicationText.Orange+ " was: "+count);
			pcg.logExtentReport("Count of select Color "+ApplicationText.Orange+ " was: "+count);
			AssertionHelper.markPass();
		}
		else{
			log.info("Count of select Color "+ApplicationText.Orange+ " was: "+count);
			pcg.logExtentReport("Count of select Color "+ApplicationText.Orange+ " was: "+count);
			AssertionHelper.markFail();
		}
	}

}
