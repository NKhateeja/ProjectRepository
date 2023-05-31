package com.banking.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewActiveAccountPage {

	public void verifyAccountNumberDsply(WebDriver driver, String AccountNumber) {
		if(driver.findElement(By.xpath("//td[.='"+AccountNumber+"']")).isDisplayed())
			System.out.println("Account details are displayed");
		else
			System.out.println("Account details are not displayed");
	}
}
