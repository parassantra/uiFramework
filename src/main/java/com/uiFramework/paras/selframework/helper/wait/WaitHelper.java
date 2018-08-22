package com.uiFramework.paras.selframework.helper.wait;

import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	public WaitHelper(WebDriver driver)
	{
		this.driver = driver;
		log.info("WaitHelper Object created");
	}
	
	public void setImplicitWait(long timeout, TimeUnit unit)
	{
		log.info("Implicit Wait is set to: "+ timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	
	private WebDriverWait getWait(int TimeOutInSeconds, int PollingEveryInMilliSec)
	{
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(TimeOutInSeconds));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	public void waitForElementVisibleWithPollingTime(WebElement element, int TimeOutInSeconds, int PollingEveryInMilliSec)
	{
		log.info("Waiting for: "+element+" for "+ TimeOutInSeconds+ " secs");
		WebDriverWait wait = getWait(TimeOutInSeconds, PollingEveryInMilliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}
	
	public void waitForElementClickable(WebElement element, int TimeOutInSeconds, int PollingEveryInMilliSec)
	{
		log.info("Waiting for: "+element+" for "+ TimeOutInSeconds+ " secs");
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is clickable now");
	}
	
	public boolean waitForElementNotPresent(WebElement element, long TimeOutInSeconds)
	{
		log.info("Waiting for: "+element+" for "+ TimeOutInSeconds+ " secs");
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("Element is invisible now");
		return status;
	}
	
	public void waitForFrameToBeAvailableAndSwitchTo(WebElement element, long TimeOutInSeconds)
	{
		log.info("Waiting for: "+element+" for "+ TimeOutInSeconds+ " secs");
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("Frame is available and switched");
	}
	
	private Wait<WebDriver> getfluentWait(int TimeOutInSeconds, int PollingEveryInMilliSec){
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(TimeOutInSeconds))
		.pollingEvery(Duration.ofMillis(PollingEveryInMilliSec)).ignoring(NoSuchElementException.class);
		return fwait;
	}
	
	public WebElement waitForElement(WebElement element,int TimeOutInSeconds, int PollingEveryInMilliSec)
	{
		Wait<WebDriver> fwait = getfluentWait(TimeOutInSeconds, PollingEveryInMilliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	
	public void pageLoadTime(long TimeOut, TimeUnit unit){
		log.info("Waiting for Page to load for: "+unit);
		driver.manage().timeouts().pageLoadTimeout(TimeOut, unit);
		log.info("Page is loaded");
	}
	
	public void waitForElement(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}

	
}
