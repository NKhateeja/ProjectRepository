package com.banking.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConformationWindow {
	
	@FindBy(name="cnfrm-submit")
	private WebElement confirmBtn;
	
	@FindBy(xpath="//input[@value='Go back']")
	private WebElement goBankBtn;
	
	public ConformationWindow(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickConfirmBtn() {
		confirmBtn.click();
	}
	
	public void clickGoBackBtnBtn() {
		goBankBtn.click();
	}
}
