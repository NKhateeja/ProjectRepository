package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewCustomerByAcNoPage {
	
	//declaration
	@FindBy(name="account_no") private WebElement customerActNoTxtFld;
	@FindBy(name="submit_view") private WebElement submittBtn;
	
	//initialization
	public ViewCustomerByAcNoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void viewActiveCustomer(String accountNo) {
		customerActNoTxtFld.sendKeys(accountNo);
		submittBtn.click();
	}
}
