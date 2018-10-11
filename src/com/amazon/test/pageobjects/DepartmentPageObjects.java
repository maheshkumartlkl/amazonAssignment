package com.amazon.test.pageobjects;

import org.openqa.selenium.By;


public class DepartmentPageObjects {
	
	
	public static By department = By.id("nav-link-shopall");
	
	public static By kindle = By.xpath("//span[@aria-label='Kindle']");
	
	public static By kindlepaperwhite = By.xpath("//span[text()='Kindle Paperwhite']");
	
	
	public static By quantity=By.id("quantity");
	
	public static By addToCart = By.id("add-to-cart-button");
	
	public static By close = By.xpath("//button[@aria-label='Close']/i");
	
	public static By proceedTocheckOut = By.xpath("//div[@id='hlb-next-steps']/a[2]");
	
	public static By addToOrder = By.id("intl_pop_addToOrder");
	
	public static By Continue = By.id("continue");
	
	public static By errorLocation = By.id("auth-email-missing-alert");
	
	 
	
	
	
	
	
	

	
	


}
