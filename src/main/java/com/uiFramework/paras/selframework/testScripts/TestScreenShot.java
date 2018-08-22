package com.uiFramework.paras.selframework.testScripts;

import org.testng.annotations.Test;

import com.uiFramework.paras.selframework.testbase.TestBase;

public class TestScreenShot extends TestBase{

	@Test
	public void testScreen(){
		driver.get("http://google.com");
		captureScreen("firstTestScreen",driver); 
	}
}
