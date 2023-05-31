package com.banking.userModule.openingaccount;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.OnlineBanking.Steller.Utilities.ExcelUtilities;
import com.OnlineBanking.Steller.Utilities.FileUtilities;
import com.OnlineBanking.Steller.Utilities.JavaUtility;
import com.OnlineBanking.Steller.Utilities.WebDriverUtility1;

public class OpenAccountUsingGeneric {
	
	public static void main(String[] args) throws IOException {
		//creating objects of all utils class
		FileUtilities Fut=new FileUtilities();
		ExcelUtilities Eut=new ExcelUtilities();
		WebDriverUtility1 Wut=new WebDriverUtility1();
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver;
		String Browser = Fut.getPropertyKeyValue("browser");
		
		//checking the browser and open it
		if(Browser.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		else if(Browser.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else if(Browser.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else
			driver=new ChromeDriver();
		
		//Enter the URL
		driver.get(Fut.getPropertyKeyValue("url"));
		
		//maximize the browser
		Wut.maximizeWindow(driver);
		
		//Providing implicitly wait condition
		Wut.waitTillPageGetsLoad(driver);
		
		//click on open account button
		driver.findElement(By.xpath("//a[.='Open Account']")).click();
		
		//3.verifying online account opening form is displaying 
		String url1 = driver.getCurrentUrl();
		if(url1.contains("customer_reg_form"))
			System.out.println("online account opening form is displaying");
		else
			System.out.println("online account opening form is not displaying");
		
		String name=Eut.getExcelData("Sheet1", 1, 0);
		String Gender=Eut.getExcelData("Sheet1", 1, 1);
		String Mobile=Eut.getExcelData("Sheet1", 1, 2);
		String EmialId=Eut.getExcelData("Sheet1", 1, 3);
		String LandlineNo=Eut.getExcelData("Sheet1", 1, 4);
		String DOB_day=Eut.getExcelData("Sheet1", 1, 5);
		String DOB_month=Eut.getExcelData("Sheet1", 1, 6);
		String DOB_year=Eut.getExcelData("Sheet1", 1, 7);
		String PANNo=Eut.getExcelData("Sheet1", 1, 8);
		String CitizenNo=Eut.getExcelData("Sheet1", 1, 9);
		String HomeAddress=Eut.getExcelData("Sheet1", 1, 10);
		String OfficeAddress=Eut.getExcelData("Sheet1", 1, 11);
		String State=Eut.getExcelData("Sheet1", 1, 12);
		String City=Eut.getExcelData("Sheet1", 1, 13);
		String PinCode=Eut.getExcelData("Sheet1", 1, 14);
		String Area=Eut.getExcelData("Sheet1", 1, 15);
		String NomineeNme=Eut.getExcelData("Sheet1", 1, 16);
		String NomineeAcNo=Eut.getExcelData("Sheet1", 1, 17);
		String AcType=Eut.getExcelData("Sheet1", 1, 18);
		
		//Entering account holder name in Name text field
		driver.findElement(By.name("name")).sendKeys(name);
				
		//selecting male from gender dropdown
		WebElement gender = driver.findElement(By.name("gender"));
		Wut.selectElementInDropDown(Gender, gender);
		
		//Enter mobile number in Mobile no text field
				driver.findElement(By.name("mobile")).sendKeys(Mobile);
				
		//Enter emailid in Email id text field
		driver.findElement(By.name("email")).sendKeys(EmialId);
				
		//Enter Landline no in Landline no text field
		driver.findElement(By.name("landline")).sendKeys(LandlineNo);
				
		//Enter DateofBirth no in DateofBirth no text field
		WebElement DOB = driver.findElement(By.name("dob"));
		DOB.click();
		DOB.sendKeys(DOB_day);
		DOB.sendKeys(DOB_month);
		DOB.sendKeys(DOB_year);
		
		//Enter PAN no in PAN Number text field
		driver.findElement(By.name("pan_no")).sendKeys(PANNo);
		
		//Enter citizenship number in Citizenship Number text field
		driver.findElement(By.name("citizenship")).sendKeys(CitizenNo);
				
		//Enter home address in Home Address text field
		driver.findElement(By.name("homeaddrs")).sendKeys(HomeAddress);
				
		//Enter office address in Office Address text field
		driver.findElement(By.name("officeaddrs")).sendKeys(OfficeAddress);
				
		//selecting state Florida from state dropdown
		WebElement state = driver.findElement(By.name("state"));
		state.click();
		Wut.selectElementInDropDown(State, state);
		
		//Selecting City San Diego from city dropdown
		WebElement city = driver.findElement(By.name("city"));
		city.click();
		Wut.selectElementInDropDown(City, city);
		
		//Enter PinCode number in PinCode text field
		driver.findElement(By.name("pin")).sendKeys(PinCode);
		
		//Enter Area name in area text field
		driver.findElement(By.name("arealoc")).sendKeys(Area);
		
		//Enter Nominee name as Riya in Nominee Text field
		driver.findElement(By.name("nominee_name")).sendKeys(NomineeNme);
				
		//Enter Nominee Account number in Nominee Account no text filed
		driver.findElement(By.name("nominee_ac_no")).sendKeys(NomineeAcNo);
				
		//Selecting Account type as savings from Account Type dropdown
		WebElement AccType = driver.findElement(By.name("acctype"));
		AccType.click();
		Wut.selectElementInDropDown(AcType, AccType);
		
		//click on submitt button
		driver.findElement(By.name("submit")).click();
				
		//verifying confirmation window is displayed or not
		String url2 = driver.getCurrentUrl();
		
		//System.out.println(url2);
		if(url2.contains("cust_regfrm_confirm"))
			System.out.println("Confirmation window is displayed");
		else
			System.out.println("Confirmation window is not displayed");
				
		//click on confirm btn
		driver.findElement(By.xpath("//input[@value='Confirm']")).click();
				
		//verifying popup is displayed or not
		String altText = Wut.switchToAlertPopupAndAcceptIt(driver, "Application submitted successfully");
		//System.out.println(altText);
		String text[]=altText.split(" ");
		String Appno=text[5].substring(0, 9);
		Eut.insertDataIntoExcel("Sheet1", 2, 19, Appno);
		
		driver.close();
		
		
	}
}
