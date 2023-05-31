package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StaffLoginPage {
	
	//declaration
	@FindBy(name="staff_id")
	private WebElement staffIdTxtFld;
	
	@FindBy(name="password")
	private WebElement passwordTxtFld;
	
	@FindBy(name="staff_login-btn")
	private WebElement loginBtn;
	
	
	//initialization
	public StaffLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//execution
	public void setStaffLogin(String staffId, String password) {
		staffIdTxtFld.sendKeys(staffId);
		passwordTxtFld.sendKeys(password);
		loginBtn.click();
	}
	
}
