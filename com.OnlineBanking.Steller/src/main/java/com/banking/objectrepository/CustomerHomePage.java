package com.banking.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerHomePage {

	@FindBy(name="logout_btn") private WebElement logoutBtn;
	@FindBy(name="home") private WebElement homeBtn;
	@FindBy(xpath="//li[.='My Account']") private WebElement myAccountlink;
	@FindBy(xpath="//li[.='My Profile']") private WebElement myProfileLink;
	@FindBy(xpath="//li[.='Change Password']") private WebElement changePasswrdLink;
	@FindBy(xpath="//li[.='Fund Transfer']") private WebElement fundTransferLink;
	@FindBy(xpath="//li[.='Statement']") private WebElement statementLink;
	
	
	//initialization
	public CustomerHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//Utilization
	public void clickLogoutBtn() {
		logoutBtn.click();
	}
	
	public void clickHomeBtn() {
		homeBtn.click();
	}
	
	public void clickMyAccountlink() {
		myAccountlink.click();
	}
	
	public void clickMyProfileLink() {
		myProfileLink.click();
	}
	
	public void clickChangePasswrdLink() {
		changePasswrdLink.click();
	}
	
	public void clickFundTransferLink() {
		fundTransferLink.click();
	}
	
	public void clickStatementLink() {
		statementLink.click();
		
	}
	
	public void verifyCustomerHomePage(WebDriver driver, String accountHolderName) {
		if(driver.findElement(By.xpath("//label[.='Welcome "+accountHolderName+"']")).isDisplayed())
		System.out.println("Customer home page is displayed");
		else 
			System.out.println("Customer home page is not displayed");
		}
}
