package com.banking.user_StaffModule.testscriptUsingBC;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.OnlineBanking.Steller.Utilities.BaseClass;
import com.banking.objectrepository.ApprovePendindAccountAccountPage;
import com.banking.objectrepository.ConformationWindow;
import com.banking.objectrepository.OpenAccountPage;
import com.banking.objectrepository.StaffHomePage;
import com.banking.objectrepository.StaffLoginPage;
import com.banking.objectrepository.UserHomePage;
import com.banking.objectrepository.ViewActiveAccountPage;


public class OpenAccountTest extends BaseClass{
	
	@Test(priority = -1)
	public void openAccount() throws Throwable {
		
		//creating objects of pom class
		UserHomePage uhp=new UserHomePage(driver);
		OpenAccountPage oap=new OpenAccountPage(driver);
		ConformationWindow cw=new ConformationWindow(driver);
		
		//declaring variables used in testScript
		String partUrl;
		String msg;
		String alertMsg;
		String ApplicationNo="";
		
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
		oap.openingAccount(map, driver, Jut, Wut);
		Thread.sleep(5000);

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
		
	}
	
	@Test
	public void approveAccount() throws Throwable {
		
		//creating objects of pom class
		UserHomePage uhp=new UserHomePage(driver);
		StaffLoginPage slp=new StaffLoginPage(driver);
		ApprovePendindAccountAccountPage apa=new ApprovePendindAccountAccountPage(driver);
		StaffHomePage shp=new StaffHomePage(driver);
		
		//declaring variables uesd in test case
		String partUrl;
		String msg;
		String alertMsg;
		String ApplicationNo="";
		String staffId;
		String password;
		String accountNo="";
		
		//Fetching the application number
		ApplicationNo = Eut.getExcelData("OpenAccount", 2, 19);
		
		//click on staff login link
		uhp.setStaffLogin();
				
		//verify whether staff login page is displayed or not
		partUrl="staff_login";
		if(Wut.waitForAUrl(driver, partUrl))
			System.out.println("staff login page is displayed");
		else 
			System.out.println("staff login page is not displayed");	
				
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
				
		//enter application number in application number text field and click on search button
		apa.setApprovePendingAccount(ApplicationNo);
				
		//verify whether application details are displayed or not
		apa.checkAppDetaisDisplayed(ApplicationNo, driver);
				
		//click on approve button
		apa.clickApproveBtn();
				
		//verify whether popup is displayed or not and click on ok button
		alertMsg="Account Created Successfully";
		String alrtText=Wut.switchToAlertPopupAndAcceptIt(driver, alertMsg);
				
		for(int i=0;i<alrtText.length();i++) {
			if(Character.isDigit(alrtText.charAt(i)))
				accountNo=accountNo+alrtText.charAt(i);
		}
			
		//storing account number in excel file
		Eut.insertDataIntoExcel("ApproveAccount", 1, 0, accountNo);
				
		//verify whether pending customer page is displayed or not
		partUrl="pending_customers";
		Wut.waitForAUrl(driver, partUrl);
				
		//click on logout link
		shp.clickLogoutBtn();
				
		//verify whether staff login page is displyed or not
		partUrl="staff_login";
		msg="Staff login page";
		Wut.waitForAUrl(driver, partUrl);
	}
	
	@Test
	public void viewActiveAccount() throws Throwable {
		
		//creating objects for pom classes
		UserHomePage uhp=new UserHomePage(driver);
		StaffLoginPage slp=new StaffLoginPage(driver);
		StaffHomePage shp=new StaffHomePage(driver);
		ViewActiveAccountPage vaa=new ViewActiveAccountPage();
		
		//declaring variables used in test case
		String partUrl;
		String msg;
		
		//click on staff login link
		uhp.setStaffLogin();
		
		//verify whether staff login page is displayed or not
		partUrl="staff_login";
		msg="Staff login page";
		Wut.waitForAUrl(driver, partUrl);
		
		//fetch the logins from property file
		String staffId=Fut.getPropertyKeyValue("staffId");
		String password=Fut.getPropertyKeyValue("password");
						
		//enter staff lodin and password and click on login button
		slp.setStaffLogin(staffId, password);
						
		//verify whether staff home page is displayed or not
		partUrl="staff_profile";
		msg="Staff home page";
		Wut.waitForAUrl(driver, partUrl);
		
		//click on view active account link
		shp.clickViewActiveCustomerBtn();
		
		
		//fetching account number from excel file
		String accountNumber = Eut.getExcelData("ApproveAccount", 1, 0);
		
		//verify whether account details are displayed
		vaa.verifyAccountNumberDsply(driver, accountNumber);
		
		

	}
}
