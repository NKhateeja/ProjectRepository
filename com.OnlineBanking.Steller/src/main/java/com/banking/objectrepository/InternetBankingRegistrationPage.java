package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InternetBankingRegistrationPage {
	@FindBy(name="holder_name")
	private WebElement ActHolderNameTxtFld;
	
	@FindBy(name="accnum")
	private WebElement AccountNoTxtFld;
	
	@FindBy(name="dbtcard")
	private WebElement DbtCardNoTxtFld;
	
	@FindBy(name="dbtpin")
	private WebElement DbtPinNoTxtFld;
	
	@FindBy(name="mobile")
	private WebElement RgrMobileNoTxtFld;
	
	@FindBy(name="pan_no")
	private WebElement PanNoTxtFld;
	
	@FindBy(name="dob")
	private WebElement DateOfBirthTxtFld;
	
	@FindBy(name="last_trans")
	private WebElement LastTransactnTxtFld;
	
	@FindBy(name="password")
	private WebElement passwordTxtFld;
	
	@FindBy(name="cnfrm_password")
	private WebElement confirmPwdTxtFld;
	
	@FindBy(name="submit")
	private WebElement submittBtn;
	
	//initialization
	public InternetBankingRegistrationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Execution
	public void enterDetails(String name, String accountNo, String DdtCardNo, String DbtPinNo,String mobileNo, String PanNo, String lasttrnsactn, String password, String confrmPassword) {
		ActHolderNameTxtFld.sendKeys(name);
		AccountNoTxtFld.sendKeys(accountNo);
		DbtCardNoTxtFld.sendKeys(DdtCardNo);
		DbtPinNoTxtFld.sendKeys(DbtPinNo);
		RgrMobileNoTxtFld.sendKeys(mobileNo);
		PanNoTxtFld.sendKeys(PanNo);
		LastTransactnTxtFld.sendKeys(lasttrnsactn);
		passwordTxtFld.sendKeys(password);
		confirmPwdTxtFld.sendKeys(confrmPassword);
		}
	
	public void enterDate(String DOB_day, String DOB_month, String DOB_year) {
		DateOfBirthTxtFld.click();
		DateOfBirthTxtFld.sendKeys(DOB_day);
		DateOfBirthTxtFld.sendKeys(DOB_month);
		DateOfBirthTxtFld.sendKeys(DOB_year);
	}
	
	public void clickSubmitBtn() {
		submittBtn.click();
	}
	
}
