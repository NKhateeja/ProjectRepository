package com.banking.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApprovePendindAccountAccountPage {

	//declaration
	@FindBy(name="application_no")	private WebElement AppNoTxtFld;
	
	@FindBy(name="search_application")	private WebElement searchBtn;
	
	@FindBy(name="approve_cust")	private WebElement approveBtn;
	
	public ApprovePendindAccountAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public void setApprovePendingAccount(String appNo) {
		AppNoTxtFld.sendKeys(appNo);
		searchBtn.click();
	}
	
	public void clickApproveBtn() {
		approveBtn.click();
	}
	
	public void checkAppDetaisDisplayed(String Appno,WebDriver driver) {
		boolean b = driver.findElement(By.xpath("//td[.='"+Appno+"']")).isDisplayed();
		if(b)
			System.out.println("Application details are displayed");
			else
				System.out.println("Application details are not displayed");
	}
}
