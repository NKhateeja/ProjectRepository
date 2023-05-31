package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StaffHomePage {
	
	//declaration
	@FindBy(name="home")
	private WebElement homeBtn;
	
	@FindBy(name="logout_btn")
	private WebElement logoutBtn;
	
	@FindBy(name="viewdet")
	private WebElement viewActiveCustomerBtn;
	
	@FindBy(name="view_cust_by_ac")
	private WebElement viewCustomerByAcNoBtn;
	
	@FindBy(name="apprvac")
	private WebElement approvePendingAccountBtn;
	
	@FindBy(name="del_cust")
	private WebElement deleteCustomerAcBtn;
	
	@FindBy(name="credit_cust_ac")
	private WebElement creditCustomerBtn;
	
	//initialization
	public StaffHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public void clickHomeBtn() {
		homeBtn.click();
	}
	
	public void clickLogoutBtn() {
		logoutBtn.click();
	}
	
	public void clickViewActiveCustomerBtn() {
		viewActiveCustomerBtn.click();
	}
	
	public void clickViewCustomerByAcNoBtn() {
		viewCustomerByAcNoBtn.click();
	}
	
	public void clickApprovePendingAccountBtn() {
		approvePendingAccountBtn.click();
	}
	
	public void clickDeleteCustomerAcBtn() {
		deleteCustomerAcBtn.click();
	}
	
	public void clickCreditCustomerBtn() {
		creditCustomerBtn.click();
	}
}
