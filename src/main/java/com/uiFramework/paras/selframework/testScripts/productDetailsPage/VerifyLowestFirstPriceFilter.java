package com.uiFramework.paras.selframework.testScripts.productDetailsPage;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import com.uiFramework.paras.selframework.helper.assertion.AssertionHelper;
import com.uiFramework.paras.selframework.helper.browserConfigurations.config.ObjectReader;
import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;
import com.uiFramework.paras.selframework.pageObject.NavigationMenu;
import com.uiFramework.paras.selframework.pageObject.ProductCategoryPage;
import com.uiFramework.paras.selframework.testScripts.registration.ResgistrationTest;
import com.uiFramework.paras.selframework.testbase.TestBase;

public class VerifyLowestFirstPriceFilter extends TestBase {
	private final Logger log = LoggerHelper.getLogger(ResgistrationTest.class);
	@Test
	public void verifyLowestFirstPriceListInProduct_deatilsPage() throws InterruptedException{
		getApplicationUrl(ObjectReader.reader.getUrl());
		NavigationMenu navMenu = new NavigationMenu(driver);
		ProductCategoryPage pcg = navMenu.clickOnMenu(navMenu.womenMenu);
		// select price filter
		pcg.selectSortByFilter("Price: Lowest first");
		// wait for some time to make sure price is sorted.
		Thread.sleep(8000);
		List<WebElement> price = pcg.getAllProductsPrice();
		ArrayList<Integer> data = new ArrayList<Integer>();
		Iterator<WebElement> itr = price.iterator();
		data = pcg.getPriceMassagedData(itr);
		boolean status = pcg.verifyArrayHasAscendingData(data);
		AssertionHelper.updateTestStatus(status);
	}

}
