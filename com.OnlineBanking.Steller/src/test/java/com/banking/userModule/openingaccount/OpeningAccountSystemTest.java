package com.banking.userModule.openingaccount;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class OpeningAccountSystemTest {

	public static void main(String[] args) throws Throwable {
		
		//Open the browser
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver;
		
		//Fetching the data from property file
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.property");
		Properties p=new Properties();
		p.load(fis);
		String Browser = p.getProperty("browser");
		String Url = p.getProperty("url");
		//String Username = p.getProperty("username");
		//String Password = p.getProperty("password");
		
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
		driver.manage().window().maximize();
		
		//1.navigating to the application
		driver.get(Url);
		
		//2.click on open account button
		driver.findElement(By.xpath("//a[.='Open Account']")).click();
		
		//3.verifying online account opening form is displaying 
		String url1 = driver.getCurrentUrl();
		//System.out.println(url);
		if(url1.contains("customer_reg_form"))
			System.out.println("online account opening form is displaying");
		else
			System.out.println("online account opening form is not displaying");
		
		//fetching the date from excel
		Workbook workbook = WorkbookFactory.create(new FileInputStream("./src/test/resources/testdate.xlsx"));
		Row row = workbook.getSheet("Sheet1").getRow(1);
		String name=row.getCell(0).toString();
		String Gender=row.getCell(1).toString();
		String Mobile=row.getCell(2).toString();
		String EmialId=row.getCell(3).toString();
		String LandlineNo=row.getCell(4).toString();
		String DOB_day=row.getCell(5).toString();
		String DOB_month=row.getCell(6).toString();
		String DOB_year=row.getCell(7).toString();
		String PANNo=row.getCell(8).toString();
		String CitizenNo=row.getCell(9).toString();
		String HomeAddress=row.getCell(10).toString();
		String OfficeAddress=row.getCell(11).toString();
		String State=row.getCell(12).toString();
		String City=row.getCell(13).toString();
		String PinCode=row.getCell(14).toString();
		String Area=row.getCell(15).toString();
		String NomineeNme=row.getCell(16).toString();
		String NomineeAcNo=row.getCell(17).toString();
		String AcType=row.getCell(18).toString();
		
		//Entering account holder name in Name text field
		driver.findElement(By.name("name")).sendKeys(name);
		
		//selecting male from gender dropdown
		WebElement gender = driver.findElement(By.name("gender"));
		gender.click();
		Select s=new Select(gender);
		s.selectByVisibleText(Gender);
		
		//Enter mobile number in Mobile no text field
		driver.findElement(By.name("mobile")).sendKeys(Mobile);
		
		//Enter emailid in Email id text field
		driver.findElement(By.name("email")).sendKeys(EmialId);
		
		//Enter Landline no in Landline no text field
		driver.findElement(By.name("landline")).sendKeys(LandlineNo);
		
		////Enter DateofBirth no in DateofBirth no text field
		WebElement DOB = driver.findElement(By.name("dob"));
		DOB.click();
		DOB.sendKeys(DOB_day);
		DOB.sendKeys(DOB_month);
		DOB.sendKeys(DOB_year);
		
		//Enter PAN no in PAN Number text field
		driver.findElement(By.name("pan_no")).sendKeys(PANNo);
		
		//Enter citizenship number in Citizenship Number text field
		driver.findElement(By.name("citizenship")).sendKeys(CitizenNo);
		
		//Enter home address in Home Address text field
		driver.findElement(By.name("homeaddrs")).sendKeys(HomeAddress);
		
		//Enter office address in Office Address text field
		driver.findElement(By.name("officeaddrs")).sendKeys(OfficeAddress);
		
		//selecting state Florida from state dropdown
		WebElement state = driver.findElement(By.name("state"));
		state.click();
		Select s1=new Select(state);
		s1.selectByVisibleText(State);
		
		//Selecting City San Diego from city dropdown
		WebElement city = driver.findElement(By.name("city"));
		city.click();
		Select s2=new Select(city);
		s2.selectByVisibleText(City);
		
		//Enter PinCode number in PinCode text field
		driver.findElement(By.name("pin")).sendKeys(PinCode);
		
		//Enter Area name in area text field
		driver.findElement(By.name("arealoc")).sendKeys(Area);
		
		//Enter Nominee name as Riya in Nominee Text field
		driver.findElement(By.name("nominee_name")).sendKeys(NomineeNme);
		
		//Enter Nominee Account number in Nominee Account no text filed
		driver.findElement(By.name("nominee_ac_no")).sendKeys(NomineeAcNo);
		
		//Selecting Account type as savings from Account Type dropdown
		WebElement AccType = driver.findElement(By.name("acctype"));
		AccType.click();
		Select s3=new Select(AccType);
		s3.selectByVisibleText(AcType);
		
		//click on submitt button
		driver.findElement(By.name("submit")).click();
		
		//verifying confirmation window is displayed or not
		String url2 = driver.getCurrentUrl();
		//System.out.println(url2);
		if(url2.contains("cust_regfrm_confirm"))
		
			System.out.println("Confirmation window is displayed");
		else
			System.out.println("Confirmation window is not displayed");
		
		//click on confirm btn
		driver.findElement(By.xpath("//input[@value='Confirm']")).click();
		
		//verifying popup is displayed or not
		Alert a = driver.switchTo().alert();
		
		//Getting the text of popup
		String text = a.getText();
		if(text.contains("Application submitted successfully"))
			System.out.println("Application submitted successfully popup is displayed");
		System.out.println();
		
		//Printing the text on the consloe
		//System.out.println("The text of popup is"+"\n"+text);
		
		//clicking the ok button on alert popup
		a.accept();
		
		String alltext[]=text.split(" ");
		int len = alltext.length;
//		for(int i=0;i<len;i++)
//		{
//			System.out.println(i+"------>"+alltext[i]);	
//			}
		
		String Appno=alltext[5].substring(0, 9);
		System.out.println("Application number is "+Appno);
		row.createCell(19).setCellValue(Appno);
		FileOutputStream fos=new FileOutputStream("./src/test/resources/testdate.xlsx");
		workbook.write(fos);
		workbook.close();
		
		
		//Verify User home page is displayed or nor
		String url4 = driver.getCurrentUrl();
		//System.out.println(url4);
		if(url4.contains("index"))
			System.out.println("User home page is displayed");
		
		//close the browser
		driver.close();
	}

}
