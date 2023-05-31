package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplyDebitCardPage {
	
	//declaration
	@FindBy(name="holder_name")	private WebElement AccHolderNameTxtFld;
	
	@FindBy(name="dob")	private WebElement dateofBirthIcon;
	
	@FindBy(name="pan")	private WebElement panNoTxtFld;
	
	@FindBy(name="mob")	private WebElement mobileNoTxtFld;
	
	@FindBy(name="acc_no")	private WebElement AcNoTxtFld;
	
	@FindBy(name="dbt_crd_submit")	private WebElement submittBtn;
	
	
	//initialization
	public ApplyDebitCardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public void enterDetails(String name,String panNo,String mobile,String ActNo) {
		AccHolderNameTxtFld.sendKeys(name);
		panNoTxtFld.sendKeys(panNo);
		mobileNoTxtFld.sendKeys(mobile);
		AcNoTxtFld.sendKeys(ActNo);
	}
	
		public void enterDate(String DOB_day, String DOB_month, String DOB_year) {
			dateofBirthIcon.click();
			dateofBirthIcon.sendKeys(DOB_day);
			dateofBirthIcon.sendKeys(DOB_month);
			dateofBirthIcon.sendKeys(DOB_year);
		}
			
	public void clickSubmittBtn() {
		submittBtn.click();
	}
}
