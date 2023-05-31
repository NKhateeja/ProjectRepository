package com.banking.user_StaffModule.testscriptUsingBC;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.OnlineBanking.Steller.Utilities.BaseClass;
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

public class RegisterUserTest extends BaseClass{

	@Test
	public void registerUser() throws Throwable {
		//creating objects of pom class
		UserHomePage uhp=new UserHomePage(driver);
		OpenAccountPage oap=new OpenAccountPage(driver);
		ConformationWindow cw=new ConformationWindow(driver);
		ApplyDebitCardPage adc=new ApplyDebitCardPage(driver);
		InternetBankingRegistrationPage ibrp=new InternetBankingRegistrationPage(driver);
		CustomerLoginPage clp=new CustomerLoginPage(driver);
		CustomerHomePage chp=new CustomerHomePage(driver);
				
		//declaring variables used in testScript
		String partUrl;
		String msg;
		String alertMsg;
		String ApplicationNo="";
		String staffId;
		String password;
		String accountNo="";
				
		//click on open account button
		uhp.setOpenAccountBtn();
				
		//verify online account opening form is displayed or not
		partUrl="customer_reg_form";
		msg="online account opening form";
		Wut.waitForAUrl(driver, partUrl);
				
				
		//fetching the data from excel
		HashMap<String, String> map = Eut.getMultipleData("OpenAccountByMap");
		//fetching date from excel
		String day=Eut.getExcelData("OpenAccount", 1, 5);
		String month=Eut.getExcelData("OpenAccount", 1, 6);
		String year=Eut.getExcelData("OpenAccount", 1, 7);
						
		//entering all the details in text fields
		oap.enterDate(day, month, year);
		String name = oap.openingAccount(map, driver, Jut, Wut);

		//click on submitt button
		oap.clickSubmittBtn();
				
				//verify configuration window is displayed or not
				partUrl="cust_regfrm_confirm";
				msg="Confirmation window ";
				Wut.waitForAUrl(driver, partUrl);
				
				//click on confirm button
				cw.clickConfirmBtn();
				
				//verify alert popup is displayed or not and handling it
				alertMsg="Application submitted successfully";
				String alrtText = Wut.switchToAlertPopupAndAcceptIt(driver, alertMsg);
				
				//getting the application number
				for(int i=0;i<alrtText.length();i++) {
					if(Character.isDigit(alrtText.charAt(i)))
						ApplicationNo=ApplicationNo+alrtText.charAt(i);
				}
			//	System.out.println("Application no is "+ApplicationNo);
				
				//inserting application number into excel file
				Eut.insertDataIntoExcel("OpenAccount", 2, 19, ApplicationNo);
				
				//creating objects of pom class
				StaffLoginPage slp=new StaffLoginPage(driver);
				ApprovePendindAccountAccountPage apa=new ApprovePendindAccountAccountPage(driver);
				StaffHomePage shp=new StaffHomePage(driver);
				
				//click on staff login link
				uhp.setStaffLogin();
						
				//verify whether staff login page is displayed or not
				partUrl="staff_login";
				msg="Staff login page";
				Wut.waitForAUrl(driver, partUrl);
						
				//fetch the logins from property file
				staffId=Fut.getPropertyKeyValue("staffId");
				password=Fut.getPropertyKeyValue("password");
						
				//enter staff lodin and password and click on login button
				slp.setStaffLogin(staffId, password);
						
				//verify whether staff home page is displayed or not
				partUrl="staff_profile";
				msg="Staff home page";
				Wut.waitForAUrl(driver, partUrl);
					
				//click on approve pending accoount button
				shp.clickApprovePendingAccountBtn();
						
				//check whether approve pending account page is displayed or not
				partUrl="pending_customers";
				msg="Approve pending account page";
				Wut.waitForAUrl(driver, partUrl);
						
				//enter application number in apllication number text field and click on search button
				apa.setApprovePendingAccount(ApplicationNo);
						
				//verify whether application details are displayed or not
				apa.checkAppDetaisDisplayed(ApplicationNo, driver);
						
				//click on approve button
				apa.clickApproveBtn();
						
				//verify whether popup is displayed or not and click on ok button
				alertMsg="Account Created Successfully";
				alrtText=Wut.switchToAlertPopupAndAcceptIt(driver, alertMsg);
						
				for(int i=0;i<alrtText.length();i++) {
					if(Character.isDigit(alrtText.charAt(i)))
						accountNo=accountNo+alrtText.charAt(i);
				}
					
				//storing account number in excel file
				Eut.insertDataIntoExcel("ApproveAccount", 1, 0, accountNo);
						
				//verify whether pending customer page is displayed or not
				partUrl="pending_customers";
				msg="No pending account page";
				Wut.waitForAUrl(driver, partUrl);
						
				//click on logout link
				shp.clickLogoutBtn();
						
				//verify whether staff login page is displyed or not
				partUrl="staff_login";
				msg="Staff login page";
				Wut.waitForAUrl(driver, partUrl);
				
				//click on home link
				uhp.setHomeLink();
				
				//click on apply debit card link
				uhp.setAplyDbtCrdBtn();
				
				//verify debit card form is displayed or not
				partUrl="debit_card_form";
				msg="debit card form";
				Wut.waitForAUrl(driver, partUrl);
				
				//enter name, account no, PAN number and mobile number in respective text fields
				//String name=Eut.getExcelData("OpenAccountByMap", 0, 1);
				String PANNo=Eut.getExcelData("OpenAccountByMap", 6, 1);
				String Mobile=Eut.getExcelData("OpenAccountByMap", 2, 1);
				String AccountNumber=Eut.getExcelData("ApproveAccount", 1, 0);
				//System.out.println(name+""+PANNo+""+Mobile+""+AccountNumber);
				adc.enterDetails(name, PANNo, Mobile, AccountNumber);
				
				//enter date of birth in date of birth text field
				adc.enterDate(day, month, year);
				
				//click on submitt button
				adc.clickSubmittBtn();
				
				//verify whether popup is displayed or not
				alrtText = Wut.switchToAlertPopupAndAcceptIt(driver, "Debit Card issued successfully");
				String pinNo=alrtText.split("Pin is : ")[1].split(" ")[0].trim();
				String DebitCardNumber=alrtText.split("Debit Card No is : ")[1].split(" ")[0].trim();
				System.out.println(DebitCardNumber);
				Eut.insertDataIntoExcel("ApplyDebtCard", 1, 0, DebitCardNumber);
				Eut.insertDataIntoExcel("ApplyDebtCard", 2, 1, pinNo);
				
				//verify whether Debit card form page is displayed or not
				partUrl="debit_card_form";
				msg="debit card form";
				Wut.waitForAUrl(driver, partUrl);
				
				//click on Home link
				uhp.setHomeLink();
				
				//mouse hover to internet banking btn
				uhp.setInternetbankingBtn(driver, Wut);
				
				//click on register button
				uhp.clickRegisterLink();
				
				//verify whether Internet Banking Registration form is displayed or not
				partUrl="ebanking_reg_form";
				msg="registration form";
				Wut.waitForAUrl(driver, partUrl);
				//fetching data from excel
				String lasttransaction=Eut.getExcelData("RegisterUser", 1, 0);
				String CustomerPassword=Eut.getExcelData("RegisterUser", 1, 1);
				String confirmpassword=Eut.getExcelData("RegisterUser", 1, 2);
				
				
				//enter details in text fields
				ibrp.enterDetails(name, AccountNumber, DebitCardNumber, pinNo, Mobile, PANNo, lasttransaction, CustomerPassword, confirmpassword);
				ibrp.enterDate(day, month, year);
				ibrp.clickSubmitBtn();
				
				//verifying registration successful popup is displayed or not
				alrtText = Wut.switchToAlertPopupAndAcceptIt(driver, "Registration Successful");
				String CustomerId="";
				for(int i=0;i<alrtText.length();i++) {
					if(Character.isDigit(alrtText.charAt(i))) {
						CustomerId=CustomerId+alrtText.charAt(i);
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
				
				//enter credentials and click on login button
				clp.setCustomerLogin(CustomerId, CustomerPassword);
				
				//verify whether customer home page is displayed or not
				chp.verifyCustomerHomePage(driver, name);
				
				//click on logout btn
				chp.clickLogoutBtn();
				
				//test cases done
	}
}
