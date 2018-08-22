package com.uiFramework.paras.selframework.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.uiFramework.paras.selframework.helper.logger.LoggerHelper;

public class WindowHelper {
		WebDriver driver;
		Logger log = LoggerHelper.getLogger(WindowHelper.class);
		
		public WindowHelper(WebDriver driver) {
			this.driver = driver;
		}
		
		public void switchToParentWindow()
		{
			log.info("Switching to parent window");
			driver.switchTo().defaultContent();
		}
		
		public void switchToWindow(int index){
			Set<String> windows = driver.getWindowHandles();
			int i = 1;
			for(String window : windows)
			{
				if(i==index)
				{
					log.info("Switched to: "+index+" window");
					driver.switchTo().window(window);
				}
				else{
					i++;
				}
			}
		}
		
		public void closeAllTabsAndSwitchToMainWindow(){
			Set<String> windows = driver.getWindowHandles();
			String mainWindow = driver.getWindowHandle();
			
			for(String window: windows){
				if(!window.equalsIgnoreCase(mainWindow)){
					driver.close();
				}
			}
			driver.switchTo().window(mainWindow);
			log.info("Switched to main window");
		}
		
		public void navigateBack(){
			log.info("Navigating Back");
			driver.navigate().back();
		}
		
		public void navigateForward(){
			log.info("Navigating Forward");
			driver.navigate().forward();;
		}
		
		
}
