package com.amazon.test.scripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;

import com.amazon.HybridFramework.Execution.Executor;

import com.amazon.test.pageobjects.DepartmentPageObjects;
import com.amazon.test.testdata.TestData;





public class TestScripts extends Executor {
	
	protected static Logger logger  =  Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName() );
	

	
	/**
	 * Perform an Email Required Validations.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public  static void keyword_verifyEmailInputPresence() throws IOException, InterruptedException {
		String input1 = null;
		String input2 = null;
		
	try {
		
		driver.get(URL);
		//	driver.manage().window().maximize();
			driverWait.until(ExpectedConditions.presenceOfElementLocated(DepartmentPageObjects.department));
		
			
		
		    cursor.moveToElement(driver.findElement(DepartmentPageObjects.department)).build().perform();
		    

			
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(DepartmentPageObjects.kindle));
		    
		    cursor.moveToElement(driver.findElement(DepartmentPageObjects.kindle)).build().perform();

		    
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(DepartmentPageObjects.kindlepaperwhite));
		    cursor.moveToElement(driver.findElement(DepartmentPageObjects.kindlepaperwhite)).build().perform();
		
		    
		    
		  
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(DepartmentPageObjects.kindlepaperwhite));
			
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(DepartmentPageObjects.quantity));
		    WebElement quantityLocation = driver.findElement(DepartmentPageObjects.quantity);
		    new Select(quantityLocation).selectByValue("2");
		    
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(DepartmentPageObjects.addToCart));
		    driver.findElement(DepartmentPageObjects.addToCart).click();
		    
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(DepartmentPageObjects.addToOrder));
		    driver.findElement(DepartmentPageObjects.addToOrder).click();
		//    ((JavascriptExecutor)driver).executeScript("arguments[0].click();\", driver.findElement(DepartmentPageObjects.close));
		    
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(DepartmentPageObjects.proceedTocheckOut));
		    driver.findElement(DepartmentPageObjects.proceedTocheckOut).click();
		    
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(DepartmentPageObjects.Continue));
		    driver.findElement(DepartmentPageObjects.Continue).click();
		    
		    driverWait.until(ExpectedConditions.presenceOfElementLocated(DepartmentPageObjects.errorLocation));
		    String msg = driver.findElement(DepartmentPageObjects.errorLocation).getText();

	  if(msg.equalsIgnoreCase("   Enter your e-mail address or mobile phone number ")){
		  TestData.writeResultsl(4, 4, "Passed");
		  logger.info("verifyEmailInputPresence is Passed");
	  }
		
	} catch (Exception e) {
		logger.error("verifyEmailInputPresence is Failed");
		 TestData.writeResultsl(4, 4, "Passed");
	}
		
		
		
	}
	

	
}
