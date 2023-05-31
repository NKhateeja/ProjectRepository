package com.banking.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ViewBeneficiaryPage {
	public ViewBeneficiaryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void checkAddedBenificiaryDtls(WebDriver driver,String beneficiaryAcNo) {
		boolean b = driver.findElement(By.xpath("//td[.='"+beneficiaryAcNo+"']")).isDisplayed();
		if(b)
			System.out.println("Beneficiary details are displayed");
		else
			System.out.println("Beneficiary details are displayed");
	}
}
