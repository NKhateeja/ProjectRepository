package com.banking.user_StaffModule.testscriptsUsingAssertion;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.OnlineBanking.Steller.Utilities.BaseClass;
import com.banking.objectrepository.ConformationWindow;
import com.banking.objectrepository.OpenAccountPage;
import com.banking.objectrepository.UserHomePage;

public class OpenAccountTest extends BaseClass {

	@Test(groups = "RegressionTest")
	public void openAccount() throws Throwable {
		//creating objects of pom class
		UserHomePage uhp=new UserHomePage(driver);
		OpenAccountPage oap=new OpenAccountPage(driver);
		ConformationWindow cw=new ConformationWindow(driver);
				
		//declaring variables used in testScript
		String alertMsg;
		String ApplicationNo="";
			
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
		Thread.sleep(2000);
		oap.openingAccount(map, driver, Jut, Wut);
		Thread.sleep(5000);

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
				
		//inserting application number into excel file
		Eut.insertDataIntoExcel("OpenAccount", 2, 19, ApplicationNo);
		Reporter.log("OpenAccountTest testcase is pass",true);
				
	}
	
}
