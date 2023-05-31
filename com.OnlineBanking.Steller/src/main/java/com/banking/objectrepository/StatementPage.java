package com.banking.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StatementPage {
	public boolean checkAmountDetails(WebDriver driver, String amt) 
	{
		if(driver.findElement(By.xpath("//td[.='"+amt+"']")).isDisplayed())
			return true;
		else 
			return false;
	}
}
