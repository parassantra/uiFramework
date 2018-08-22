package com.uiFramework.paras.selframework.helper.select;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uiFramework.paras.selframework.helper.frame.FrameHelper;
import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;

public class DropDownHelper {
	private WebDriver driver;
	Logger log = LoggerHelper.getLogger(FrameHelper.class);

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		log.info("DropDownHelper object is created!!");
	}
	
	public void selectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		log.info("Select using value: "+value);
		select.selectByValue(value);
	}
	
	public void selectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		log.info("Select using index: "+index);
		select.selectByIndex(index);
	}
	
	public void selectUsingVisibleText(WebElement element, String text){
		Select select = new Select(element);
		log.info("Select using visible text: "+text);
		select.selectByVisibleText(text);
	}
	public void deselectUsingValue(WebElement element, String value){
		Select select = new Select(element);
		log.info("deselect using value: "+value);
		select.deselectByValue(value);
	}
	
	public void deselectUsingIndex(WebElement element, int index){
		Select select = new Select(element);
		log.info("deselect using index: "+index);
		select.deselectByIndex(index);
	}
	
	public void deselectUsingVisibleText(WebElement element, String text){
		Select select = new Select(element);
		log.info("deselect using visibleText: "+text);
		select.deselectByVisibleText(text);
	}
	
	public List<String> getAllDropDownData(WebElement element){
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> values = new LinkedList<String>();
		for(WebElement ele : elementList){
			log.info("Value is: "+ele.getText());
			values.add(ele.getText());
		}
		return values;
	}
	
}
