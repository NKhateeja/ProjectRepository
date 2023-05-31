package com.onlinebanking.testscriptUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.OnlineBanking.Steller.Utilities.ExcelUtilities;
import com.OnlineBanking.Steller.Utilities.FileUtilities;
import com.OnlineBanking.Steller.Utilities.WebDriverUtility1;
import com.banking.objectrepository.CreditCustomerPage;
import com.banking.objectrepository.CustomerHomePage;
import com.banking.objectrepository.CustomerLoginPage;
import com.banking.objectrepository.StaffHomePage;
import com.banking.objectrepository.StaffLoginPage;
import com.banking.objectrepository.StatementPage;
import com.banking.objectrepository.UserHomePage;

public class CreditCustomerTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		//Open the browser
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver;
				
		//creating objects of utility classes
		//creating object foe FileUtilities class
		FileUtilities Fut=new FileUtilities();
		//creating object of excel utilities class
		ExcelUtilities Eut=new ExcelUtilities();
		//creating the object of WebDriverUtility1 class
		WebDriverUtility1 Wut=new WebDriverUtility1();
				
		//Fetching the data from property file
		String Url =Fut.getPropertyKeyValue("url");
		String Browser = Fut.getPropertyKeyValue("browser");
										
		//selecting browser
		if(Browser.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		else if(Browser.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else if(Browser.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else
			driver=new ChromeDriver();
									
		//maximizing the browser
		Wut.maximizeWindow(driver);
						
		//1.navigating to the application
		driver.get(Url);
		
		//creating objects for pom classes
		UserHomePage uhp=new UserHomePage(driver);
		StaffLoginPage slp=new StaffLoginPage(driver);
		StaffHomePage shp=new StaffHomePage(driver);
		CreditCustomerPage ccp=new CreditCustomerPage(driver);
		CustomerLoginPage clp=new CustomerLoginPage(driver);
		CustomerHomePage chp=new CustomerHomePage(driver);
		StatementPage sp=new StatementPage();
		
		//click on staff login link
				uhp.setStaffLogin();
				
				//verifying staff login page is displayed or not
				String staffurl = driver.getCurrentUrl();
				if(staffurl.contains("staff_login"))
					System.out.println("Staff login page is displayed");
				else
					System.out.println("Staff login page is not displayed");
				
				//fetching data from property file
				String staffId = Fut.getPropertyKeyValue("staffId");
				String password=Fut.getPropertyKeyValue("password");
				
				//entering staffId and password and click on login btn
				slp.setStaffLogin(staffId, password);
				
				//verify whether home page is displayed or not
				String homeurl = driver.getCurrentUrl();
				if(homeurl.contains("staff_profile"))
					System.out.println("Staff home page is displayed");
				else 
					System.out.println("Staff home page is not dislplayed");
				
				//click on credit customer Button
				shp.clickCreditCustomerBtn();
				
				//verify whether credit customer page is displayed or not
				String CreCusUrl = driver.getCurrentUrl();
				if(CreCusUrl.contains("credit_customer_ac"))
				{
					System.out.println("Credit customer page is displayed");
				}
				else
					System.out.println("Credit customer page is not displayed");
				
				//enter the information in text fields and click on credit button
				String accountNo=Eut.getExcelData("ApproveAccount", 1, 0);
				String amount=Eut.getExcelData("CreditCustomer", 1, 0);
				ccp.enterDetails(accountNo, amount);
				
				//verify amount credited successfully popup is displayed
				Wut.switchToAlertPopupAndAcceptIt(driver, "Amount credited Successfully");
				
				//click on logout button
				shp.clickLogoutBtn();
				
				//verify staff login page is displayed or not
				String staffPageUrl = driver.getCurrentUrl();
				
				if(staffPageUrl.contains("staff_login"))
					System.out.println("Staff Login page is displayed");
				else
					System.out.println("Staff Login page is not displayed");
				//Thread.sleep(2000);
				
				//click on home link
				uhp.setHomeLink();
				//Thread.sleep(2000);
				//mouseover to internetbanking
				uhp.setInternetbankingBtn(driver, Wut);
				
				//click on login button
				uhp.clickLoginLink();
				
				//verify customer login page is displayed or not
				String cusLgnUrl = driver.getCurrentUrl();
				if(cusLgnUrl.contains("customer_login"))
					System.out.println("Customer login page is displayed");
				else
					System.out.println("Customer login page is not displayed");
				
				//Enter customer id in customer id text field
				String CustomerId = Eut.getExcelData("RegisterUser", 2, 3);
				String Cuspassowrd=Eut.getExcelData("RegisterUser", 1, 1);
				clp.setCustomerLogin(CustomerId, Cuspassowrd);
				
				//verify whether customer profile page is displayed or not
				String cusProUrl = driver.getCurrentUrl();
				
				if(cusProUrl.contains("customer_profile"))
					System.out.println("Customer profile page is displayed");
				else
					System.out.println("Customer profile page is not displayed");
				
				//click on statement link
				chp.clickStatementLink();
				
				//verify whether customer statement page is displayed or not
				String cusStmtUrl = driver.getCurrentUrl();
				if(cusStmtUrl.contains("cust_statement"))
					System.out.println("Customer statememt page is displayed");
				else
					System.out.println("Customer statememt page is not displayed");
				
				//check amount credited statement is displayed or not
				boolean res = sp.checkAmountDetails(driver, amount);
				if(res)
					System.out.println("Amount credited details are displayed");
				else
					System.out.println("Amount credited details are not displayed");
				
				//click on logout button
				chp.clickLogoutBtn();
				
				//verify login page is displayed or not
				String cusLgnUrl11=driver.getCurrentUrl();
				if(cusLgnUrl11.contains("customer_login"))
					System.out.println("customer logged out successfully");
				else
					System.out.println("Customer not logged out successfully");
				
				driver.close();
	}			

}
