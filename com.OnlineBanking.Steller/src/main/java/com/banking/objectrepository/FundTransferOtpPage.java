package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FundTransferOtpPage {

	//declaration
	@FindBy(xpath="//label[contains(.,'OTP')]") private WebElement otpPopup;
	@FindBy(name="otpcode") private WebElement otpTextField;
	@FindBy(name="verify-btn") private WebElement verifyBtn;
	
	
	//initialization
	public FundTransferOtpPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getOtpPopup() {
		return otpPopup;
	}

	public void enterOtpClickVerify(String otp) {
		otpTextField.sendKeys(otp);
		verifyBtn.click();
	}

	
	
	
}
