package com.uiFramework.paras.selframework.testScripts;

import org.testng.annotations.Test;

import com.uiFramework.paras.selframework.helper.assertion.AssertionHelper;
import com.uiFramework.paras.selframework.testbase.TestBase;

public class Test2 extends TestBase{

	@Test
	public void testLogin1(){
		AssertionHelper.markPass();
	}
	
	@Test
	public void testLogin2(){
		AssertionHelper.markFail();
	}
	
	@Test
	public void testLogin3(){
		AssertionHelper.markPass();
	}
	
	@Test
	public void testLogin4(){
		AssertionHelper.markFail();
	}
}
