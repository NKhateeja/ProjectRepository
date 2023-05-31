package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddBeneficiaryPage {
	
	//declaration
	@FindBy(name="beneficiary_name") private WebElement benificiaryNameTxtFld;
	@FindBy(name="beneficiary_acno") private WebElement benificiaryAcNoTxtFld;
	@FindBy(name="Ifsc_code") private WebElement IFSCCodeTxtFld;
	@FindBy(name="beneficiary_acc_type") private WebElement accountTypeDD;
	@FindBy(name="add_beneficiary_btn") private WebElement AddBtn;
	
	
	//initialization
	public AddBeneficiaryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void eneterDetails(String beneficiaryName, String beneficiaryAcNo, String IFSCCode,String ActType) {
		benificiaryNameTxtFld.sendKeys(beneficiaryName);
		benificiaryAcNoTxtFld.sendKeys(beneficiaryAcNo);
		IFSCCodeTxtFld.sendKeys(IFSCCode);
		accountTypeDD.sendKeys(ActType);
		AddBtn.click();
	}
}
