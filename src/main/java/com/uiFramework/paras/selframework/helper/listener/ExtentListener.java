package com.uiFramework.paras.selframework.helper.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;
import com.uiFramework.paras.selframework.utils.ExtentManager;

public class ExtentListener implements ITestListener{
	private Logger log = LoggerHelper.getLogger(ExtentListener.class);
	public static ExtentReports extent;
	public static ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		//test.log(Status.INFO, result.getName()+" started");
		Reporter.log(result.getMethod().getMethodName()+" test Started..");
		log.info(result.getMethod().getMethodName()+" test Started..");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//test.log(Status.INFO, result.getName()+" passed");
		Reporter.log(result.getMethod().getMethodName()+" test Passed..");
		log.info(result.getMethod().getMethodName()+" test Passed..");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" test Failed.."+result.getThrowable());
		log.error(result.getMethod().getMethodName()+" test Failed.."+result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//test.log(Status.SKIP, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" test Skiped.."+result.getThrowable());
		log.warn(result.getMethod().getMethodName()+" test Skiped.."+result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		//extent = ExtentManager.getInstance();
		//test=extent.createTest(context.getName());
		//test=extent.createTest(context.getCurrentXmlTest().getName());
		Reporter.log(context.getName()+" test started..");
		log.info(context.getName()+" test started..");
	}

	@Override
	public void onFinish(ITestContext context) {
		Reporter.log(context.getName()+" test finished..");
		log.info(context.getName()+" test finished..");
	}

}
