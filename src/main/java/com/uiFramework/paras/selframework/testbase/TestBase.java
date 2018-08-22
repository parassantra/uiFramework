package com.uiFramework.paras.selframework.testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.reporters.Files;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiFramework.paras.selframework.helper.browserConfigurations.BrowserType;
import com.uiFramework.paras.selframework.helper.browserConfigurations.ChromeBrowser;
import com.uiFramework.paras.selframework.helper.browserConfigurations.FirefoxBrowser;
import com.uiFramework.paras.selframework.helper.browserConfigurations.IExploreBrowser;
import com.uiFramework.paras.selframework.helper.browserConfigurations.config.ObjectReader;
import com.uiFramework.paras.selframework.helper.browserConfigurations.config.PropertyReader;
import com.uiFramework.paras.selframework.helper.excel.ExcelHelper;
import com.uiFramework.paras.selframework.helper.javaScript.JavaScriptHelper;
import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;
import com.uiFramework.paras.selframework.helper.wait.WaitHelper;
import com.uiFramework.paras.selframework.utils.ExtentManager;
import com.uiFramework.resource.ResourceHelper;
import org.apache.commons.io.FileUtils;

public class TestBase {
	
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);

	public static ExtentReports extent;
	public static ExtentTest test;
	public static File reportDirectory;
	
	@BeforeSuite
	public void beforeSuite(){
		extent = ExtentManager.getInstance();
	}
	
	@BeforeTest
	public void beforeTest(){
		ObjectReader.reader = new PropertyReader();
		reportDirectory=new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
		setupDriver(ObjectReader.reader.getBrowserType());
		test = extent.createTest(getClass().getSimpleName());
	}
	
	@AfterTest
	public void afterTest(){
		driver.close();
	}
		
	@BeforeMethod
	public void beforeMethod(Method method){
		test.log(Status.INFO, method.getName()+"**************test started***************");
		log.info("**************"+method.getName()+"Started***************");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException{
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(),driver);
			test.addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(Status.PASS, result.getName()+" is Passed");
			String imagePath = captureScreen(result.getName(),driver);
			test.addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, result.getThrowable());
		}
		
		log.info("**************"+result.getName()+"Finished***************");
		extent.flush();
	}
	
	public WebDriver getBrowserObject(BrowserType btype) throws Exception{
		try{
			switch(btype){
			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions chromeOptions = chrome.getChromeOptions();
				return chrome.getChromeDriver(chromeOptions);
			case FireFox:
				FirefoxBrowser ff = FirefoxBrowser.class.newInstance();
				FirefoxOptions ffOptions = ff.getFirefoxOptions();
				return ff.getFirefoxDriver(ffOptions);
				
			case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions ieoptions = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(ieoptions);
			
			default:
				throw new Exception("Driver not found "+btype.name());
			}
		}
		catch(Exception e){
			log.info(e.getMessage());
			throw e;
		}
	}
	
	public void setupDriver(BrowserType btype){
		try {
			driver = getBrowserObject(btype);
			log.info("Initialized WebDriver "+driver.hashCode());
			WaitHelper wait = new WaitHelper(driver);
			wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
			wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String captureScreen(String fileName, WebDriver driver){
		if(driver == null){
			log.info("driver is null..");
			return null;
		}
		if(fileName==""){
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			destFile = new File(reportDirectory+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			FileUtils.copyFile(screFile, destFile);
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return destFile.toString();
	}
	
	public void getNavigationScreen(WebDriver driver){
		log.info("Capturing navigation Screen");
		new JavaScriptHelper(driver).zoomInBy40Percentage();
		String screen = captureScreen("",driver);
		new JavaScriptHelper(driver).zoomInBy100Percentage();
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getApplicationUrl(String url){
		driver.get(url);
		logExtentReport("navigating to ..."+url);
	}
	
	public static void logExtentReport(String s1){
		test.log(Status.INFO, s1);
	}
	
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = ResourceHelper.getResourcePath("src/main/resources/configfile/")+excelName;
		log.info("excel location "+excelLocation);
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
	}
	
}
