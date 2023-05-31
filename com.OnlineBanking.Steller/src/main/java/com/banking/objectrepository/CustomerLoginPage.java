package com.banking.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CustomerLoginPage {
	@FindBy(name="customer_id") private WebElement customerIdTxtFld;
	@FindBy(name="password") private WebElement passwordTxtFld;
	@FindBy(name="login-btn") private WebElement loginBtn;
	
	public CustomerLoginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void setCustomerLogin(String customerId, String password) {
		customerIdTxtFld.sendKeys(customerId);
		passwordTxtFld.sendKeys(password);
		loginBtn.click();
	}
	
	
}
