package com.banking.objectrepository;

import java.util.HashMap;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OnlineBanking.Steller.Utilities.JavaUtility;
import com.OnlineBanking.Steller.Utilities.WebDriverUtility1;

public class OpenAccountPage {
	
	//declaration
	@FindBy (name="name")	private WebElement nameTxtField;
	
	@FindBy (name="gender")	private WebElement genderDD;
		
	@FindBy (name="mobile")	private WebElement mobileTxtField;
	
	@FindBy (name="email")	private WebElement emailIdTxtFld;
	
	@FindBy (name="landline")	private WebElement landlineTF;
	
	@FindBy (name="dob")	private WebElement dobicon;
		
	@FindBy (name="pan_no")	private WebElement PANtxtFld;
		
	@FindBy (name="citizenship") private WebElement citizenshipTxtFld;
	
	@FindBy (name="homeaddrs")	private WebElement homeaddrsTxtFld;
	
	@FindBy (name="officeaddrs") private WebElement officeaddrsTxtFld;
	
	@FindBy (name="state")	private WebElement stateDD;
	
	@FindBy (name="city")	private WebElement cityDD;
	
	@FindBy (name="pin")	private WebElement pincodeTxtFld;
	
	@FindBy (name="arealoc") private WebElement areaTxtFld;
	
	@FindBy (name="nominee_name") private WebElement nomineeNameTxtFld;
	
	@FindBy (name="nominee_ac_no")	private WebElement nomineeAcNoTxtFld;
	
	@FindBy (name="acctype")	private WebElement actTypeDD;
	
	@FindBy(name="submit")	private WebElement submittBtn;

	
	
	//initialization
	public OpenAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//utilization
	public void enterDetails(String name,String mobile,String email,String landline,String PanNo,String citizenshipNo,String homeAdrs,String OffAdrss,String pincode,String area,String nomineeName,String nomineeAcNo) 
	{
		nameTxtField.sendKeys(name);
		mobileTxtField.sendKeys(mobile);
		emailIdTxtFld.sendKeys(email);
		landlineTF.sendKeys(landline);
		PANtxtFld.sendKeys(PanNo);
		citizenshipTxtFld.sendKeys(citizenshipNo);
		homeaddrsTxtFld.sendKeys(homeAdrs);
		officeaddrsTxtFld.sendKeys(OffAdrss);
		pincodeTxtFld.sendKeys(pincode);
		areaTxtFld.sendKeys(area);
		nomineeNameTxtFld.sendKeys(nomineeName);
		nomineeAcNoTxtFld.sendKeys(nomineeAcNo);
	}
	
	public void selectgender(String gender, WebDriverUtility1 wut) {
		wut.selectElementInDropDown(gender, genderDD);
	}
	public String getName(String name, JavaUtility Jut) {
	
		String holderName = name+Jut.retRandumNumber();
		nameTxtField.sendKeys(name+Jut.retRandumNumber());
		return holderName;
	}
	
	public void enterDate(String DOB_day, String DOB_month, String DOB_year) {
		dobicon.click();
		dobicon.sendKeys(DOB_day+""+DOB_month+""+DOB_year);
		//dobicon.sendKeys(DOB_month);
		//dobicon.sendKeys(DOB_year);
	}
	public void selectState(String state, WebDriverUtility1 Wut) {
		Wut.selectElementInDropDown(state, stateDD);
	}
	
	public void selectCity(String city, WebDriverUtility1 Wut) {
		Wut.selectElementInDropDown(city, cityDD);
		}
	
	public void selectAccType(String actType, WebDriverUtility1 Wut) {
		Wut.selectElementInDropDown(actType, actTypeDD);
	}
	
	public void clickSubmittBtn() {
		submittBtn.click();
	}
	
	//bussiness library
	public String openingAccount(HashMap<String, String> fields, WebDriver driver, JavaUtility Jut, WebDriverUtility1 Wut) throws InterruptedException {
		
		String Name="";
		for(Entry<String, String> p:fields.entrySet())
		{
		
		if(p.getKey().equalsIgnoreCase("gender")) {
			Wut.selectElementInDropDown(p.getValue(),genderDD);
		}
		else if(p.getKey().equalsIgnoreCase("state")) {
			Wut.selectElementInDropDown(p.getValue(), stateDD);}
		
		else if(p.getKey().equalsIgnoreCase("city")) {
			Wut.selectElementInDropDown(p.getValue(), cityDD);}
		
		else if(p.getKey().equalsIgnoreCase("acctype")) {
			Wut.selectElementInDropDown(p.getValue(), actTypeDD);}
			
		else if(p.getKey().equalsIgnoreCase("name")) {
			Name=p.getValue()+Jut.retRandumNumber();
			driver.findElement(By.name(p.getKey())).sendKeys(Name);
			
		}
		
//		else if(p.getKey().equalsIgnoreCase("dob")) {
//			OpenAccountPage oap=new OpenAccountPage(driver);
//			oap.enterDate(day, month, year);
//		}
		else
			driver.findElement(By.name(p.getKey())).sendKeys(p.getValue());
		}
		return Name;
	}
	
	
	
}
