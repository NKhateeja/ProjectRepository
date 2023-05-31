package com.onlinebanking.testscriptUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.OnlineBanking.Steller.Utilities.ExcelUtilities;
import com.OnlineBanking.Steller.Utilities.FileUtilities;
import com.OnlineBanking.Steller.Utilities.WebDriverUtility1;
import com.banking.objectrepository.ConformationWindow;
import com.banking.objectrepository.OpenAccountPage;
import com.banking.objectrepository.UserHomePage;

public class OpenAccountTest {
	
public static void main(String[] args) throws Throwable {
		//welcome to branch
	
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
				
		//1.navigating to the application
		driver.get(Url);
		WebDriverUtility1 Wut=new WebDriverUtility1();
		
		//maximizing the browser
		Wut.maximizeWindow(driver);
		
		//creating object of UserHomePage class
		UserHomePage uhp=new UserHomePage(driver);
		
		//click on open account button
		uhp.setOpenAccountBtn();
		
		
		//verifying online account page is displayed or not

		String partUrl="customer_reg_form",msg="online account opening form";
		Wut.waitForAUrl(driver, partUrl);
		
		
		
		//creating object of excel utilities class
		ExcelUtilities Eut=new ExcelUtilities();
		//fetching the details from excel
		String name=Eut.getExcelData("OpenAccount", 1, 0);
		String Gender=Eut.getExcelData("OpenAccount", 1, 1);
		String Mobile=Eut.getExcelData("OpenAccount", 1, 2);
		String EmailId=Eut.getExcelData("OpenAccount", 1, 3);
		String LandlineNo=Eut.getExcelData("OpenAccount", 1, 4);
		String DOB_day=Eut.getExcelData("OpenAccount", 1, 5);
		String DOB_month=Eut.getExcelData("OpenAccount", 1, 6);
		String DOB_year=Eut.getExcelData("OpenAccount", 1, 7);
		String PANNo=Eut.getExcelData("OpenAccount", 1, 8);
		String CitizenNo=Eut.getExcelData("OpenAccount", 1, 9);
		String HomeAddress=Eut.getExcelData("OpenAccount", 1, 10);
		String OfficeAddress=Eut.getExcelData("OpenAccount", 1, 11);
		String State=Eut.getExcelData("OpenAccount", 1, 12);
		String City=Eut.getExcelData("OpenAccount", 1, 13);
		String PinCode=Eut.getExcelData("OpenAccount", 1, 14);
		String Area=Eut.getExcelData("OpenAccount", 1, 15);
		String NomineeNme=Eut.getExcelData("OpenAccount", 1, 16);
		String NomineeAcNo=Eut.getExcelData("OpenAccount", 1, 17);
		String AcType=Eut.getExcelData("OpenAccount", 1, 18);
		
		//creating object of OpenAccountPage class
		OpenAccountPage op=new OpenAccountPage(driver);
		
		//enter name in name TF
		op.enterDetails(name, Mobile, EmailId, LandlineNo, PANNo, CitizenNo, HomeAddress, OfficeAddress, PinCode, Area, NomineeNme, NomineeAcNo);
	
		//select gender from gender dropdown
		op.selectgender(Gender, Wut);
		
		//enter dob in dob icon
		op.enterDate(DOB_day, DOB_month, DOB_year);
		
		//select state from state drop down
		op.selectState(State, Wut);
		
		//select city from city dropdown
		op.selectCity(City, Wut);
		
		//select account type in account type dropdown
		op.selectAccType(AcType, Wut);
		
		//click on submitt button
		op.clickSubmittBtn();
		
		//verifying confirmation window is displayed or not
		String partUrlConWin="cust_regfrm_confirm";
		String msg2="Confirmation window ";
		Wut.waitForAUrl(driver, partUrlConWin);
		
		
		//creating object of ConformationWindow class
		ConformationWindow cw=new ConformationWindow(driver);
		//click on confirm btn
		cw.clickConfirmBtn();
		
		
		//verifying popup is displayed or not
		String altText = Wut.switchToAlertPopupAndAcceptIt(driver, "Application submitted successfully");
		String Appno="";
		for(int i=0;i<altText.length();i++) {
			if(Character.isDigit(altText.charAt(i))) {
				Appno=Appno+altText.charAt(i);
			}
		}
		Eut.insertDataIntoExcel("OpenAccount", 1, 19, Appno);		
		driver.close();		
	}

}
