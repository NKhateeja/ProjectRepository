package com.banking.user_StaffModule.testscriptsUsingAssertion;


import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.OnlineBanking.Steller.Utilities.BaseClass;
import com.banking.objectrepository.ApprovePendindAccountAccountPage;
import com.banking.objectrepository.ConformationWindow;
import com.banking.objectrepository.OpenAccountPage;
import com.banking.objectrepository.StaffHomePage;
import com.banking.objectrepository.StaffLoginPage;
import com.banking.objectrepository.UserHomePage;


public class ApproveAccountTest extends BaseClass {

	@Test
	public void openAccount() throws Throwable {
		//creating objects of pom class
		UserHomePage uhp=new UserHomePage(driver);
		OpenAccountPage oap=new OpenAccountPage(driver);
		ConformationWindow cw=new ConformationWindow(driver);
		StaffLoginPage slp=new StaffLoginPage(driver);
		StaffHomePage shp=new StaffHomePage(driver);
		ApprovePendindAccountAccountPage apa=new ApprovePendindAccountAccountPage(driver);
				
		//declaring variables used in testScript
		String alertMsg;
		String ApplicationNo="";
		String staffId;
		String password;
		String accountNo="";
			
		//click on open account button
		uhp.setOpenAccountBtn();
				
		//verify online account opening form is displayed or not
		Assert.assertTrue(Wut.getUrl(driver).contains("reg_form"));
				
		//fetching the data from excel
		HashMap<String, String> map = Eut.getMultipleData("OpenAccountByMap");
		//fetching date from excel
		String day=Eut.getExcelData("OpenAccount", 1, 5);
		String month=Eut.getExcelData("OpenAccount", 1, 6);
		String year=Eut.getExcelData("OpenAccount", 1, 7);
						
		//entering all the details in text fields
		oap.enterDate(day, month, year);
		oap.openingAccount(map, driver, Jut, Wut);

		//click on submit button
		oap.clickSubmittBtn();
				
		//verify configuration window is displayed or not
		Assert.assertTrue(Wut.getUrl(driver).contains("regfrm_confirm"));
		
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
				
		//click on staff login link
		uhp.setStaffLogin();
		
		//verify whether staff login page is displayed or not
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(Wut.getUrl(driver).contains("staff_login"));
		
		//fetch the logins from property file
		staffId=Fut.getPropertyKeyValue("staffId");
		password=Fut.getPropertyKeyValue("password");
						
		//enter staff lodin and password and click on login button
		slp.setStaffLogin(staffId, password);
						
		//verify whether staff home page is displayed or not
		sa.assertTrue(Wut.getUrl(driver).contains("staff_profile"), "Staff home page is displayed");
		
		//click on approve pending accoount button
		shp.clickApprovePendingAccountBtn();
		
		//check whether approve pending account page is displayed or not
		sa.assertTrue(Wut.getUrl(driver).contains("pending_customers"));
		
		//enter application number in application number text field and click on search button
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
		sa.assertTrue(Wut.getUrl(driver).contains("pending_customers"));
						
		//click on logout link
		shp.clickLogoutBtn();
				
		Reporter.log("ApproveAccountTest testcase is passed",true);
}
	
}