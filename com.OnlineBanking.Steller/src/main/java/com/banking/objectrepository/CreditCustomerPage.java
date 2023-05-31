package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreditCustomerPage {
	
	//declaration
	@FindBy(name="customer_account_no") private WebElement cusActNoTxtFld;
	@FindBy(name="credit_amount") private WebElement amountTxtFld;
	@FindBy(name="credit_btn")private WebElement creditBtn;
	
	
	//initialization
	public CreditCustomerPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterDetails(String accountNo,String amount) {
		cusActNoTxtFld.sendKeys(accountNo);
		amountTxtFld.sendKeys(amount);
		creditBtn.click();
	}
}
