package com.uiFramework.paras.selframework.helper.javaScript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;

public class JavaScriptHelper {
	private   WebDriver driver;
	
	Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);

	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper has been initialised");
	}
	
	public Object executeScript(String script)
	{
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		return exe.executeScript(script);
	}
	
	public Object executeScript(String script, Object...args)
	{
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		return exe.executeScript(script, args);
	}
	
	public void scrollToElement(WebElement element){
		log.info("Scroll to: "+element);
		executeScript("window.scrollTo(args[0],args[1])",element.getLocation().x,element.getLocation().y);
	}
	 
	public void scrollToElementAndClick(WebElement element){
		log.info("Scroll to: "+element);
		scrollToElement(element);
		element.click();
		log.info("Element Clicked: "+element.toString());
	}
	
	public void scrollIntoView(WebElement element){
		executeScript("arguments[0].scrollIntoView",element);
	}
	
	public void scrollIntoViewAndClick(WebElement element){
		scrollIntoView(element);
		element.click();
		log.info("Element Clicked: "+element.toString());
	}
	
	public void scrollDownVertically(){
		log.info("Scrolling Down Vertically");
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollUpVertically(){
		log.info("Scrolling Up Vertically");
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}
	
	public void scrollDownByPixels(int pixel){
		executeScript("window.scrollBy(0,"+pixel+")");
	}
	
	public void scrollUpByPixels(int pixel){
		executeScript("window.scrollBy(0,-"+pixel+")");
	}
	
	public void zoomInBy100Percentage(){
		executeScript("document.body.style.zoom='100%'");
	}
	
	public void zoomInBy40Percentage(){
		executeScript("document.body.style.zoom='40%'");
	}
	
	public void clickElement(WebElement element){
		executeScript("arguements[0].click();",element);
	}
	
	
	
}
