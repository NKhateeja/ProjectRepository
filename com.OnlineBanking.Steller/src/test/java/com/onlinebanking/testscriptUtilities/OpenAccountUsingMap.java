package com.onlinebanking.testscriptUtilities;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.OnlineBanking.Steller.Utilities.ExcelUtilities;
import com.OnlineBanking.Steller.Utilities.FileUtilities;
import com.OnlineBanking.Steller.Utilities.JavaUtility;
import com.OnlineBanking.Steller.Utilities.WebDriverUtility1;
import com.banking.objectrepository.OpenAccountPage;
import com.banking.objectrepository.UserHomePage;


public class OpenAccountUsingMap {

	public static void main(String[] args) throws Throwable {
		//Open the browser
				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
				WebDriver driver;
				
				//creating object foe FileUtilities class
				FileUtilities Fut=new FileUtilities();
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
				driver.manage().window().maximize();
						
				//1.navigating to the application
				driver.get(Url);
				
				//creating object of UserHomePage class
				UserHomePage uhp=new UserHomePage(driver);
				//click on open account button
				uhp.setOpenAccountBtn();
				
				ExcelUtilities Eut=new ExcelUtilities();
				HashMap<String, String> map = Eut.getMultipleData("OpenAccountByMap");
				
				OpenAccountPage oap=new OpenAccountPage(driver);
				JavaUtility Jut=new JavaUtility();
				WebDriverUtility1 Wut=new WebDriverUtility1();
				oap.enterDate("01","02","2004");
				oap.openingAccount(map, driver, Jut, Wut);
				
				oap.clickSubmittBtn();
				
	}
}
