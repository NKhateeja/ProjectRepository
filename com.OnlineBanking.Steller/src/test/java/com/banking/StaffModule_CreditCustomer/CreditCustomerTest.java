package com.banking.StaffModule_CreditCustomer;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CreditCustomerTest {

	public static void main(String[] args) {
		//Open the browser
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
						
		//maximizing the browser
		driver.manage().window().maximize();
						
		//1.navigating to the application
		String url="http://rmgtestingserver/domain/Online_Banking_System/";
		driver.get(url);
		
		//click on staff login link
		driver.findElement(By.xpath("//a[.='Staff Login']")).click();
		
		//verifying staff login page is displayed or not
		String staffurl = driver.getCurrentUrl();
		if(staffurl.contains("staff_login"))
			System.out.println("Staff login page is displayed");
		else
			System.out.println("Staff login page is not displayed");
		
		
		//Enter staff id as "210001" in staff ID text field
		driver.findElement(By.name("staff_id")).sendKeys("210001");
		
		//Enter password as "password" in password text field
		driver.findElement(By.name("password")).sendKeys("password");
		
		//click on login button
		driver.findElement(By.name("staff_login-btn")).click();
		
		
		//verify whether home page is displayed or not
		String homeurl = driver.getCurrentUrl();
		if(homeurl.contains("staff_profile"))
			System.out.println("Staff home page is displayed");
		else 
			System.out.println("Staff home page is not dislplayed");
		
		//Click on Credit Customer Button
		driver.findElement(By.name("credit_cust_ac")).click();
		
		//verify whether credit customer page is displayed or not
		String CreCusUrl = driver.getCurrentUrl();
		if(CreCusUrl.contains("credit_customer_ac"))
		{
			System.out.println("Credit customer page is displayed");
		}
		else
			System.out.println("Credit customer page is not displayed");
		
		//Enter Customer Account Number in Customer A/C No text field
		driver.findElement(By.name("customer_account_no")).sendKeys("1011461011590");
		
		//Enter amount in Amount text field
		String amt="5000";
		driver.findElement(By.name("credit_amount")).sendKeys(amt);
		
		//click on credit btn
		driver.findElement(By.name("credit_btn")).click();
		
		//verifying Amount Credited popup is displayed or not
		Alert a1 = driver.switchTo().alert();
		
		//Getting the text of popup
		String text1 = a1.getText();
		if(text1.contains("Amount credited Successfully"))
			System.out.println("Amount credited Successfully popup is displayed");
		
		//clicking the ok button on alert popup
		a1.accept();
		
		//click on logout link
		driver.findElement(By.name("logout_btn")).click();
		
		//verify staff login page is displayed or not
		String staffPageUrl = driver.getCurrentUrl();
		
		if(staffPageUrl.contains("staff_login"))
			System.out.println("Staff Login page is displayed");
		else
			System.out.println("Staff Login page is not displayed");
		
		//click on home link
		driver.findElement(By.xpath("//a[.='Home']")).click();
		
		//click on fund transfer button
		driver.findElement(By.xpath("//li[.='Fund Transfer']")).click();
		
		//verify customer login page is displayed or not
		String cusLgnUrl = driver.getCurrentUrl();
		if(cusLgnUrl.contains("customer_login"))
			System.out.println("Customer login page is displayed");
		else
			System.out.println("Customer login page is not displayed");
		
		//Enter customer id in customer id text field
		String cusId="1011590";
		driver.findElement(By.name("customer_id")).sendKeys(cusId);
		
		String password="Happy@123";
		driver.findElement(By.name("password")).sendKeys(password);
		
		//click on login btn
		driver.findElement(By.name("login-btn")).click();
		
		//verify whether customer profile page is displayed or not
		String cusProUrl = driver.getCurrentUrl();
		
		if(cusProUrl.contains("customer_profile"))
			System.out.println("Customer profile page is displayed");
		else
			System.out.println("Customer profile page is not displayed");
		
		//click on statement link
		driver.findElement(By.xpath("//li[.='Statement']")).click();
		
		//verify whether customer statement page is displayed or not
		String cusStmtUrl = driver.getCurrentUrl();
		if(cusStmtUrl.contains("cust_statement"))
			System.out.println("Customer statememt page is displayed");
		else
			System.out.println("Customer statememt page is not displayed");
		
		//verify the credited amount details should be displayed or not
		boolean creditamt = driver.findElement(By.xpath("//td[.='"+amt+"']")).isDisplayed();
		if(creditamt)
			System.out.println("Amount credited statement is displayed");
		else
			System.out.println("Amount credited statement is not displayed");
		
		//click on logout button
		driver.findElement(By.name("logout_btn")).click();
		
		//close the browser
		driver.close();
		}
	}


