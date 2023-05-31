package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OnlineBanking.Steller.Utilities.WebDriverUtility1;

public class FundTransferPage {
	
	//declaration
	@FindBy(name="add_beneficiary") private WebElement addBeneficiaryLink;
	@FindBy(name="delete_beneficiary") private WebElement deleteBenificiaryLink;
	@FindBy(name="view_beneficiary") private WebElement viewBeneficiaryLink;
	@FindBy(name="beneficiary") private WebElement selectBeneficiaryDropdown;
	@FindBy(name="trnsf_amount") private WebElement amountTxtFld;
	@FindBy(name="trnsf_remark") private WebElement remarksTxtFld;
	@FindBy(name="fnd_trns_btn") private WebElement sendBtn;
	WebDriverUtility1 Wut=new WebDriverUtility1();
	
	public FundTransferPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddBeneficiaryLink() {
		addBeneficiaryLink.click();
	}
	
	public void clickDeleteBenificiaryLink() {
		deleteBenificiaryLink.click();
	}
	
	public void clickViewBeneficiaryLink() {
		viewBeneficiaryLink.click();
	}
	
	public void enterDetails(String beneficiaryName, String amount) {
		Wut.selectElementInDropDown(beneficiaryName, selectBeneficiaryDropdown);
		amountTxtFld.sendKeys(amount);
		sendBtn.click();
	}
}
