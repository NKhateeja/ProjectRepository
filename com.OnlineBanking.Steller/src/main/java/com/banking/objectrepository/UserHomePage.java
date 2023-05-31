package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OnlineBanking.Steller.Utilities.WebDriverUtility1;

public class UserHomePage {

		//declaration
		@FindBy (xpath ="//a[.='Home']") 
		private WebElement homeLink;
		
		@FindBy(xpath="//a[.='About Us']")
		private WebElement aboutUsLink;
		
		@FindBy(xpath="//a[.='Contact Us']")
		private WebElement contactUsLink;
		
		@FindBy(xpath="//a[.='Staff Login']")
		private WebElement staffLoginLink;
		
		@FindBy(xpath="//li[.='Open Account']")
		private WebElement openAccountBtn;
		
		@FindBy(xpath="//li[.='Apply Debit Card']")
		private WebElement aplyDbtCrdBtn;
		
		@FindBy(xpath="//a[contains(.,'Internet Banking')]")
		private WebElement internetbankingBtn;
		
		@FindBy(xpath="//a[.='Register']")
		private WebElement registerLink;
		
		@FindBy(xpath="//a[.='Login ']")
		private WebElement loginBtn;
		
		@FindBy(xpath="//a[.='Fund Transfer']")
		private WebElement fundTransferBtn;
		
		
		//initialization
		public UserHomePage(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
		}
		
		
		//utilization
		public void setHomeLink() {
			homeLink.click();
		}
		
		public void setStaffLogin() {
			staffLoginLink.click();
		}
		
		public void setOpenAccountBtn() {
			openAccountBtn.click();
		}
		
		public void setAplyDbtCrdBtn() {
			aplyDbtCrdBtn.click();
		}
		
		public void setInternetbankingBtn(WebDriver driver, WebDriverUtility1 Wut) {
			 Wut.MouseOverOnElement(driver, internetbankingBtn);
		}
		
		public void setFundTransferBtn() {
			fundTransferBtn.click();
		}
		
		public void clickRegisterLink() {
			registerLink.click();
		}
		
		public void clickLoginLink() {
			loginBtn.click();
		}
}
