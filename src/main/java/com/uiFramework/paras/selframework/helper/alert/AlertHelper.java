package com.uiFramework.paras.selframework.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.uiFramework.paras.selframework.helper.frame.FrameHelper;
import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;

public class AlertHelper {

	private WebDriver driver;
	Logger log = LoggerHelper.getLogger(FrameHelper.class);

	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		log.info("AlertHelper object is created!!");
	}
	
	public Alert getAlert(){
		log.info("alert test: "+driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}
	
	public void acceptAlert(){
		getAlert().accept();
		log.info("Accept alert is done... ");
	}
	
	public void dismissAlert(){
		getAlert().dismiss();
		log.info("Dismiss alert is done... ");
	}
	
	public String getAlertText(){
		String text = getAlert().getText();
		log.info("Alert text is: "+text);
		return text;
	}
	
	public boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			log.info("Alert is present");
			return true;
		}catch(NoAlertPresentException e){
			log.info(e.getCause());
			return false;
		}	
	}
	
	public void acceptAlertIfPresent(){
		if(isAlertPresent()){
			acceptAlert();
			log.info("Alert Present and accepted");
		}
		else{
			log.info("Alert not Present");
		}
	}
	
	public void dismissAlertIfPresent(){
		if(isAlertPresent()){
			dismissAlert();
			log.info("Alert Present and dismissed");
		}
		else{
			log.info("Alert not Present");
		}
	}
	
	public void acceptPrompt(String text){
		if(isAlertPresent()){
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("Alert text: "+text);
		}
	}
}
