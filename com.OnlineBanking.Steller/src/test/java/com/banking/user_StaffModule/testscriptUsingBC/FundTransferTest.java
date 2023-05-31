package com.banking.user_StaffModule.testscriptUsingBC;

import org.testng.annotations.Test;

import com.OnlineBanking.Steller.Utilities.BaseClass;
import com.onlinebanking.pom.AddBeneficiaryPage;
import com.onlinebanking.pom.CustomerHomePage;
import com.onlinebanking.pom.CustomerLoginPage;
import com.onlinebanking.pom.FundTransferOtpPage;
import com.onlinebanking.pom.FundTransferPage;
import com.onlinebanking.pom.UserHomePage;
import com.onlinebanking.pom.ViewBeneficiaryPage;

public class FundTransferTest extends BaseClass{
	
	@Test(groups = "smokeTest")
	public void fundTransfer() throws Throwable {
		
		//creating objects for pom classes
		UserHomePage uhp=new UserHomePage(driver);
		CustomerLoginPage clp=new CustomerLoginPage(driver);
		CustomerHomePage chp=new CustomerHomePage(driver);
		FundTransferPage ftp=new FundTransferPage(driver);
		AddBeneficiaryPage abp=new AddBeneficiaryPage(driver);
		ViewBeneficiaryPage vbp=new ViewBeneficiaryPage(driver);
		FundTransferOtpPage ftop=new FundTransferOtpPage(driver);
						
		//click on Fund Transfer button in user home page
		uhp.setFundTransferBtn();
						
		//verify customer login page should be displayed
		if(Wut.getUrl(driver).contains("customer_login"))
			System.out.println("Customer login page is displayed");
		else
			System.out.println("Customer login page is not displayed");
					
		//Enter customer id and password and click on login button
		String CustomerId = Eut.getExcelData("RegisterUser", 2, 3);
		String Cuspassowrd=Eut.getExcelData("RegisterUser", 1, 1);
		clp.setCustomerLogin(CustomerId, Cuspassowrd);
					
		//verify whether customer profile page is displayed or not
		String cusProUrl = driver.getCurrentUrl();
						
		if(Wut.getUrl(driver).contains("customer_profile"))
			System.out.println("Customer profile page is displayed");
		else
			System.out.println("Customer profile page is not displayed");
						
		//click on Fund transfer link
		chp.clickFundTransferLink();
						
		//click on add benificiary link
		ftp.clickAddBeneficiaryLink();
						
		//enter details in text fileds
		String beneficiaryName=Eut.getExcelData("AddBeneficiary", 1, 0);
		String benificiaryAcNo=Eut.getExcelData("AddBeneficiary", 1, 1);
		String IFSCCode=Eut.getExcelData("AddBeneficiary", 1, 2);
		String AccountType=Eut.getExcelData("AddBeneficiary", 1, 3);
		abp.eneterDetails(beneficiaryName, benificiaryAcNo, IFSCCode, AccountType);
						
		//verify popup is displayed or not
		Wut.switchToAlertPopupAndAcceptIt(driver, "Beneficiary Added Successfully");
				
		//click on fund transfer link
		chp.clickFundTransferLink();
						
		//verify whether fund transfer page is displayed or not
		if(Wut.getUrl(driver).contains("fund_transfer"))
				System.out.println("fund transfer page is displayed");
		else 
				System.out.println("fund transfer page is displayed");
			
		//click on view beneficiary button
		ftp.clickViewBeneficiaryLink();
					
		//check whether beneficiary is added or not
		vbp.checkAddedBenificiaryDtls(driver, benificiaryAcNo);
				
		//click on fund transfer btn
		chp.clickFundTransferLink();
				
		//verify whether fund transfer page is displayed or not
		if(Wut.getUrl(driver).contains("fund_transfer"))
				System.out.println("fund transfer page is displayed");
		else 
				System.out.println("fund transfer page is displayed");
				
		//fetching amount from excel
		String amount = Eut.getExcelData("FundTransfer", 1, 0);
		//select added benificiary by clicking on select beneficiary dropdown, enter amount and click on send button
		String beneficiaryValue = " "+beneficiaryName+"-"+benificiaryAcNo+" ";
		ftp.enterDetails(beneficiaryValue, amount);
				
		//verify whether popup is genetated with OTP or not
		String text = ftop.getOtpPopup().getText();
		String[] arr=text.split(":");
		String otp=arr[1];
				
		//enter otp in OTP text field and click on verify button
		ftop.enterOtpClickVerify(otp);
				
		//verify whether verify successful popup is displayed or not
		Wut.switchToAlertPopupAndAcceptIt(driver, "Transaction Successful");
				
		//click on logout button
		chp.clickLogoutBtn();
	}
	

}
