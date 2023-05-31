package com.banking.user_StaffModule.testscriptUsingBC;

import org.testng.annotations.Test;

import com.OnlineBanking.Steller.Utilities.BaseClass;
import com.banking.objectrepository.CreditCustomerPage;
import com.banking.objectrepository.CustomerHomePage;
import com.banking.objectrepository.CustomerLoginPage;
import com.banking.objectrepository.StaffHomePage;
import com.banking.objectrepository.StaffLoginPage;
import com.banking.objectrepository.StatementPage;
import com.banking.objectrepository.UserHomePage;


public class CreditCustomerTest extends BaseClass {

	
	@Test(groups = "smokeTest")
	public void creditCustomer() throws Throwable {
		
		//creating objects of pom classes
		UserHomePage uhp=new UserHomePage(driver);
		StaffLoginPage slp=new StaffLoginPage(driver);
		StaffHomePage shp=new StaffHomePage(driver);
		CreditCustomerPage ccp=new CreditCustomerPage(driver);
		CustomerLoginPage clp=new CustomerLoginPage(driver);
		CustomerHomePage chp=new CustomerHomePage(driver);
		StatementPage sp=new StatementPage();
		
		//declaring variables
		String partUrl;
		String msg;
		String staffId;
		String password;
		
		
		//click on staff login link
		uhp.setStaffLogin();
		
		//verify whether staff login page is displayed or not
		partUrl="staff_login";
		Wut.waitForAUrl(driver, partUrl);
				
		//fetch the logins from property file
		staffId=Fut.getPropertyKeyValue("staffId");
		password=Fut.getPropertyKeyValue("password");
				
		//enter staff lodin and password and click on login button
		slp.setStaffLogin(staffId, password);
				
		//verify whether staff home page is displayed or not
		partUrl="staff_profile";
		Wut.waitForAUrl(driver, partUrl);
		
		//click on credit customer button
		shp.clickCreditCustomerBtn();
		
		//verify whether credit customer page is displayed or not
		partUrl="credit_customer_ac";
		Wut.waitForAUrl(driver, partUrl);
		
		//fetching the data fromexcel file
		String accountNo=Eut.getExcelData("ApproveAccount", 1, 0);
		String amount=Eut.getExcelData("CreditCustomer", 1, 0);
		
		//Enter Customer Account Number, amount in Customer A/C No text field
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

		//mouseover to internetbanking
		uhp.setInternetbankingBtn(driver, Wut);
		
		//click on login button
		uhp.clickLoginLink();
		
		//veify whether customer login page is displayed or not
		if(Wut.getUrl(driver).contains("customer_login"))
			System.out.println("customer login page is displayed");
		
		else
			System.out.println("customer login page is not displayed");
		
		//Enter customer id in customer id text field
		String CustomerId = Eut.getExcelData("RegisterUser", 2, 3);
		String Cuspassowrd=Eut.getExcelData("RegisterUser", 1, 1);
		clp.setCustomerLogin(CustomerId, Cuspassowrd);
		
		//verify whether customer profile page is displayed or not
		if(Wut.getUrl(driver).contains("customer_profile"))
			System.out.println("customer home page is displayed");
		else
			System.out.println("customer home page is not displayed");
		
		//click on statement link
		chp.clickStatementLink();
		
		if(Wut.getUrl(driver).contains("cust_statement"))
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
	}
}
