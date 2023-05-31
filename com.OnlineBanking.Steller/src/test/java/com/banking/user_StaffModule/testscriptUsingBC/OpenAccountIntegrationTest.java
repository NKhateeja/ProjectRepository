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


public class OpenAccountIntegrationTest extends BaseClass{

	//creating objects of pom class
	UserHomePage uhp=new UserHomePage(driver);
	OpenAccountPage oap=new OpenAccountPage(driver);
	ConformationWindow cw=new ConformationWindow(driver);
	StaffLoginPage slp=new StaffLoginPage(driver);
	ApprovePendindAccountAccountPage apa=new ApprovePendindAccountAccountPage(driver);
	StaffHomePage shp=new StaffHomePage(driver);
	
	@Test
	public void openAccount() throws Throwable {
		
		//declaring variables used in testScript
		String partUrl;
		String alertMsg;
		String ApplicationNo="";
		String staffId;
		String password;
		String accountNo="";
		
		//click on open account button
		uhp.setOpenAccountBtn();
		
		//verify online account opening form is displayed or not
		partUrl="customer_reg_form";
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
		Thread.sleep(2000);

		oap.clickSubmittBtn();
		
		//verify configuration window is displayed or not
		partUrl="cust_regfrm_confirm";
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
		
		//verify user home page is displayed or not
		partUrl="index";
		Wut.waitForAUrl(driver, partUrl);
		
		//click on staff login link
		uhp.setStaffLogin();
		
		//verify whether staff login page is displayed or not
		partUrl="staff_login";
		Wut.waitForAUrl(driver, partUrl);
		
		//fetch the logins from user
		staffId=Fut.getPropertyKeyValue("username");
		password=Fut.getPropertyKeyValue("password");
		
		//enter staff lodin and password and click on login button
		slp.setStaffLogin(staffId, password);
		
		//verify whether staff home page is displayed or not
		partUrl="staff_profile";
		Wut.waitForAUrl(driver, partUrl);
		
		//click on approve pending accoount button
		shp.clickApprovePendingAccountBtn();
		
		//check whether approve pending account page is displayed or not
		partUrl="pending_customers";
		Wut.waitForAUrl(driver, partUrl);
		
		//enter application number in apllication number text field and click on search button
		apa.setApprovePendingAccount(ApplicationNo);
		
		//verify whether application details are displayed or not
		apa.checkAppDetaisDisplayed(ApplicationNo, driver);
		
		//click on approve button
		apa.clickApproveBtn();
		
		//verify whether popup is displayes or not and click on ok button
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
		
		//click on logout link
		shp.clickLogoutBtn();
		
		//verify whether staff login page is displyed or not
		partUrl="staff_login";
		Wut.waitForAUrl(driver, partUrl);
		
		
		
		
		
}
}