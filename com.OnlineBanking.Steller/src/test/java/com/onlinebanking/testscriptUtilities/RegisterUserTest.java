package com.onlinebanking.testscriptUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.OnlineBanking.Steller.Utilities.ExcelUtilities;
import com.OnlineBanking.Steller.Utilities.FileUtilities;
import com.OnlineBanking.Steller.Utilities.JavaUtility;
import com.OnlineBanking.Steller.Utilities.WebDriverUtility1;
import com.banking.objectrepository.ApplyDebitCardPage;
import com.banking.objectrepository.ApprovePendindAccountAccountPage;
import com.banking.objectrepository.ConformationWindow;
import com.banking.objectrepository.CustomerHomePage;
import com.banking.objectrepository.CustomerLoginPage;
import com.banking.objectrepository.InternetBankingRegistrationPage;
import com.banking.objectrepository.OpenAccountPage;
import com.banking.objectrepository.StaffHomePage;
import com.banking.objectrepository.StaffLoginPage;
import com.banking.objectrepository.UserHomePage;


public class RegisterUserTest {

	public static void main(String[] args) throws InterruptedException, IOException {
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
				//creating object fo JavaUtility
				JavaUtility Jut=new JavaUtility();
				
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

				
				//creating objects of pom classes
				//creating object of UserHomePage class
				UserHomePage uhp=new UserHomePage(driver);
				//creating object of ConformationWindow class
				ConformationWindow cw=new ConformationWindow(driver);
				//creating object of StaffLoginPage class
				StaffLoginPage slp=new StaffLoginPage(driver);
				//creating object of StaffHomePage class
				StaffHomePage shp=new StaffHomePage(driver);
				//creating object of ApprovePendindAccountAccountPage class
				ApprovePendindAccountAccountPage apa=new ApprovePendindAccountAccountPage(driver);
				//creating object for ApplyDebitCardPage class
				ApplyDebitCardPage adc=new ApplyDebitCardPage(driver);
				//creating object for InternetBankingRegistrationPage class
				InternetBankingRegistrationPage ibrp=new InternetBankingRegistrationPage(driver);
				//create object for CustomerLoginPage class
				CustomerLoginPage clp=new CustomerLoginPage(driver);
				//create object for CustomerHomePage class
				CustomerHomePage chp=new CustomerHomePage(driver);
				
				
				//click on open account button
				uhp.setOpenAccountBtn();
					
				
				//verifying online account page is displayed or not
				String actOpenUrl = driver.getCurrentUrl();
				if(actOpenUrl.contains("customer_reg_form"))
					System.out.println("online account opening form is displaying");
				else
					System.out.println("online account opening form is not displaying");
						
				

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
						
				//enter details in name TF
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
				String url2 = driver.getCurrentUrl();
				if(url2.contains("cust_regfrm_confirm"))
					System.out.println("Confirmation window is displayed");
				else
					System.out.println("Confirmation window is not displayed");
						
						
				
				//click on confirm btn
				cw.clickConfirmBtn();
						
						
				//verifying popup is displayed or not
				String altText = Wut.switchToAlertPopupAndAcceptIt(driver, "Application submitted successfully");
				//System.out.println(altText);
				String Appno="";
				for(int i=0;i<altText.length();i++) {
					if(Character.isDigit(altText.charAt(i))) {
						Appno=Appno+altText.charAt(i);
					}
				}
				Eut.insertDataIntoExcel("OpenAccount", 2, 19, Appno);
				
				//Verify User home page is displayed or nor
				String url4 = driver.getCurrentUrl();
				//System.out.println(url4);
				if(url4.contains("index"))
					System.out.println("User home page is displayed");
				else 
					System.out.println("User home page is not displayed");
				
				
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
				
				//click on approve pending account btn
				shp.clickApprovePendingAccountBtn();
				
				//verify whether approve pending account page is displayed or not
				String aprvPenAcUrl=driver.getCurrentUrl();
				if(aprvPenAcUrl.contains("pending_customers"))
					System.out.println("Approve pending account page is displayed");
				else
					System.out.println("Approve pending account page is not displayed");
				
				//Enter the application number in Application Number text field
				apa.setApprovePendingAccount(Appno);
				
				//verify application details are displayed or not
				apa.checkAppDetaisDisplayed(Appno, driver);
				
				//click on approve btn
				apa.clickApproveBtn();
				
				//verifying whether popup is displayed or not
				String altText1 = Wut.switchToAlertPopupAndAcceptIt(driver, "Account Created Successfully");
				//System.out.println(altText);
				String AccountNumber = "";
				for(int i=0;i<altText1.length();i++) {
					if(Character.isDigit(altText1.charAt(i)))
						AccountNumber=AccountNumber+altText1.charAt(i);
				}
				Eut.insertDataIntoExcel("ApproveAccount", 1, 0, AccountNumber);
				
				//verifying whether no pending application page is displayed or not
				String NoPenAppUrl = driver.getCurrentUrl();
				if(NoPenAppUrl.contains("pending_customers"))
					{
					System.out.println("No pending application page is displayed");
					}
				else
					{
					System.out.println("No pending application page is not displayed");
					}
				
				//click on logout button
				shp.clickLogoutBtn();
				
				//verifying staff logout successfully/staff login pag is displayed
				String loggedoutUrl = driver.getCurrentUrl();
				if(loggedoutUrl.contains("staff_login"))
					System.out.println("Logged out successfully");
				else
					System.out.println("Not logged out");
				
				//click on home link
				uhp.setHomeLink();
				
				//click on debit card button
				uhp.setAplyDbtCrdBtn();
				
				//verifying debit card form is displayed or not
				String DbtCardFormUrl = driver.getCurrentUrl();
				if(DbtCardFormUrl.contains("debit_card_form"))
					System.out.println("debit card form is displayed");
				else
					System.out.println("debit card form is not displayed");
				
				//enter name, PANno,mobile no and account number in respective textfields
				adc.enterDetails(name, PANNo, Mobile, AccountNumber);
				
				//enter dob in Date of birthtext field
				adc.enterDate(DOB_day, DOB_month, DOB_year);
						
				//click on submit button
				adc.clickSubmittBtn();
				
				//verify whether popup is displayed or not
				String altText2 = Wut.switchToAlertPopupAndAcceptIt(driver, "Debit Card issued successfully");
				String pinNo=altText2.split("Pin is : ")[1].split(" ")[0].trim();
				String DebitCardNumber=altText2.split("Debit Card No is : ")[1].split(" ")[0].trim();
				Eut.insertDataIntoExcel("ApplyDebtCard", 1, 0, DebitCardNumber);
				Eut.insertDataIntoExcel("ApplyDebtCard", 2, 1, pinNo);
				
				//verify whether Debit card form page is displayed or not
				if(DbtCardFormUrl.contains("debit_card_form"))
					System.out.println("debit card form is displayed");
				else
					System.out.println("debit card form is not displayed");
				
				//click on Home link
				uhp.setHomeLink();
				
				//mouse hover to internet banking btn
				uhp.setInternetbankingBtn(driver, Wut);
				
				//click on register button
				uhp.clickRegisterLink();
				
				//verify whether Internet Banking Registration form is displayed or not
				String regformUrl = driver.getCurrentUrl();
				if(regformUrl.contains("ebanking_reg_form"))
					System.out.println("registration form is displayed");
				else
					System.out.println("registration form is not displayed");
				
				//fetching data from excel
				String lasttransaction=Eut.getExcelData("RegisterUser", 1, 0);
				String CustomerPassword=Eut.getExcelData("RegisterUser", 1, 1);
				String confirmpassword=Eut.getExcelData("RegisterUser", 1, 2);
				
				
				//enter details in text fields
				ibrp.enterDetails(name, AccountNumber, DebitCardNumber, pinNo, Mobile, PANNo, lasttransaction, CustomerPassword, confirmpassword);
				ibrp.enterDate(DOB_day, DOB_month, DOB_year);
				ibrp.clickSubmitBtn();
				
				//verifying registration successful popup is displayed or not
				String altText3 = Wut.switchToAlertPopupAndAcceptIt(driver, "Registration Successful");
				String CustomerId="";
				for(int i=0;i<altText3.length();i++) {
					if(Character.isDigit(altText3.charAt(i))) {
						CustomerId=CustomerId+altText3.charAt(i);
					}
				}
				Eut.insertDataIntoExcel("RegisterUser", 2, 3, CustomerId);
				
				//click on home link
				uhp.setHomeLink();
				
				//mousehover to Internet Banking
				uhp.setInternetbankingBtn(driver, Wut);
				
				//click on login button
				uhp.clickLoginLink();
				
				String customerloginUrl = driver.getCurrentUrl();
				if(customerloginUrl.contains("customer_login"))
				{
					System.out.println("Customer login page is displayed");
				}
				else {
					System.out.println("Customer login page is displayed");
				}
				
				//enter credentials and clilck on login button
				clp.setCustomerLogin(CustomerId, CustomerPassword);
				
				//verifying customer home page is displayed or not
			chp.verifyCustomerHomePage(driver, name);
					
				//click on logout btn
				chp.clickLogoutBtn();
				
				String cusUrl = driver.getCurrentUrl();
				if(cusUrl.contains("customer_login"))
					System.out.println("Customer login page is displayed");
				else
					System.out.println("Customer login page is not displayed");

				//close the browser
				driver.close();
	}

}
