package com.banking.StaffModule_FundTransfer;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FundTrans_Add_Delete_Beneficiary {

	public static void main(String[] args) throws Throwable {
		//Open the browser
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
							
		//maximizing the browser
		driver.manage().window().maximize();
								
		//1.navigating to the application
		String url="http://rmgtestingserver/domain/Online_Banking_System/";
		driver.get(url);
		
		//click on fund transfer button in user home page
		driver.findElement(By.xpath("//a[.='Fund Transfer']")).click();
				
		//verify customer login page is displayed or not
		String cusLgnUrl = driver.getCurrentUrl();
		if(cusLgnUrl.contains("customer_login"))
			System.out.println("Customer login page is displayed");
		else
			System.out.println("Customer login page is not displayed");
				
		//Enter customer id in customer id text field
		String cusId="1011590";
		driver.findElement(By.name("customer_id")).sendKeys(cusId);
			
		String password="Happy@123";
		driver.findElement(By.name("password")).sendKeys(password);
				
		//click on login btn
		driver.findElement(By.name("login-btn")).click();
		
		//verify whether customer profile page is displayed or not
		String cusProUrl = driver.getCurrentUrl();
				
		if(cusProUrl.contains("customer_profile"))
			System.out.println("Customer profile page is displayed");
		else
			System.out.println("Customer profile page is not displayed");
		
		//click on fund transfer btn
				driver.findElement(By.xpath("//li[.='Fund Transfer']")).click();
				
				//click on add beneficiary
				driver.findElement(By.name("add_beneficiary")).click();
				
				//verify add beneficiary page should be displayed
				String addBenUrl = driver.getCurrentUrl();
				if(addBenUrl.contains("add_beneficiary"))
					System.out.println("Benificiary page is displayed");
				else
					System.out.println("Benificiary page is not displayed");
				
				//Enter Beneficiary name as "Happy A" in Beneficiary Name text field
				String BenName="Happy A";
				driver.findElement(By.name("beneficiary_name")).sendKeys(BenName);
				
				//Enter Beneficiary A/c no in Beneficiary ac no text field
				String BenAcNo="101181011387";
				driver.findElement(By.name("beneficiary_acno")).sendKeys(BenAcNo);
				
				//Enter IFSC code in IFSC code text field
				String IFSC="1011";
				driver.findElement(By.name("Ifsc_code")).sendKeys(IFSC);
				
				//Select account type as savings
				WebElement BenAcType = driver.findElement(By.name("beneficiary_acc_type"));
				Select s=new Select(BenAcType);
				s.selectByVisibleText("Saving");
				
				//Click on Add button
				driver.findElement(By.name("add_beneficiary_btn")).click();
				
				//verify popup is displayed or not
				Alert a = driver.switchTo().alert();
				String text = a.getText();
				if(text.contains("Beneficiary Added Successfully")){
					System.out.println("Beneficiary Added Successfully popup is displayed");
				}
				else
					{
					System.out.println("Beneficiary Added Successfully popup is not displayed");
					}
				a.accept();
				
				//verify add beneficiary page should be displayed
						String addBenUrl1 = driver.getCurrentUrl();
						if(addBenUrl1.contains("add_beneficiary"))
							System.out.println("Benificiary page is displayed");
						else
							System.out.println("Benificiary page is not displayed");
						
				//click on fund transfer link
				driver.findElement(By.xpath("//li[.='Fund Transfer']")).click();
				
				//verify whether fund transfer page is displayed or not
				String FTUrl = driver.getCurrentUrl();
				if(FTUrl.contains("fund_transfer"))
				{
					System.out.println("fund transfer page is displayed");
				}
					else 
						System.out.println("fund transfer page is displayed");
				
				//click on view beneficiary button
				driver.findElement(By.name("view_beneficiary")).click();
				
				//check whether beneficiary is added or not
				String acno = "101181011387";
				boolean b = driver.findElement(By.xpath("//td[.='"+acno+"']")).isDisplayed();
				if(b)
					System.out.println("Beneficiary details are displayed");
				else
					System.out.println("Beneficiary details are displayed");
				
				//click on fund transfer btn
						driver.findElement(By.xpath("//li[.='Fund Transfer']")).click();
						
			//click on delete beneficiary
			driver.findElement(By.name("delete_beneficiary")).click();
			
			//select the added beneficiary
			driver.findElement(By.xpath("//td[.='"+acno+"']/..//input[@type='radio']")).click();
			
			//click on delete beneficiary button
			driver.findElement(By.name("delete_beneficiary")).click();
			Thread.sleep(2000);
			
			//verify whether Beneficiary Deleated Successfully popup is displayed or not
			Alert alrt = driver.switchTo().alert();
			String alrttext = a.getText();
			if(alrttext.contains("Beneficiary Deleated Successfully"))
				System.out.println("Beneficiary deleted succcessfully");
			else
				System.out.println("Beneficiary deleted succcessfully");
			a.accept();
			
			//click on logout button
			driver.findElement(By.name("logout_btn")).click();
			
			//close the browser
			driver.close();
			
	}
}
