package com.OnlineBanking.Steller.Utilities;
/**
 * This class consists of all webdriver related methods 
 */
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility1 {

	/**
	 * This method is used to maximize the browser window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to minimize the browser window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	
	/**
	 * This method will wait until the page gets loaded
	 * @param driver
	 */
	
	public void waitTillPageGetsLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IPathConstants.implicitWaitDuration));
	}
	
	
	/**
	 * This method wait till the element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitTillElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IPathConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	/**
	 * This method wait till element to be click in a UI
	 * @param driver
	 * @param element
	 */
	public void waitTillTheElementToBeClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IPathConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method wait until the alert pesent in UI
	 */
	public void waitForAlertPopup(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IPathConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * This method waits until the title to be visible in webpage
	 * @param driver
	 * @param title
	 */
	public void waitForATitle(WebDriver driver, String title)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IPathConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	/**
	 * This method waits until the url is visible in the webpage
	 * @param driver
	 * @param url
	 */
	public boolean waitForAUrl(WebDriver driver,String url)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IPathConstants.explicitWaitDuration));
		if(wait.until(ExpectedConditions.urlContains(url)))
			return true;
		else 
			return false;
			
		
	}
	
	/**
	 * This method ignores if its get NoSuchElementException
	 * @param driver
	 * @param url
	 */
	public void ignoreNoSuchElementExc(WebDriver driver)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IPathConstants.explicitWaitDuration));
		wait.ignoring(NoSuchElementException.class);
	}
	
	
	/**
	 * This method is used to select elemet in dropdown based on index
	 * @param element
	 * @param index
	 */
	public void selectElementInDropDown(WebElement element, int index)
	{
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	
	/**
	 * This method is used to select elemet in dropdown based on value
	 * @param element
	 * @param index
	 */
	public void selectElementInDropDown(WebElement element, String value)
	{
		Select s=new Select(element);
		s.selectByValue(value);
	}
	
	
	/**
	 * This method is used to select elemet in dropdown based on visible text
	 * @param element
	 * @param index
	 */
	public void selectElementInDropDown(String text,WebElement element)
	{
		Select s=new Select(element);
		s.selectByVisibleText(text);
	}
	
	/**
	 * this method will fetch all options from dropdowm
	 * @param element
	 */
	public void getAllOptions(WebElement element) {
		Select s=new Select(element);
		List<WebElement> allOptions = s.getOptions();
		for(WebElement option:allOptions)
		{
			String text=option.getText();
			System.out.println(text);
		}
	}
	
	
	/**
	 * This method is used to move over an element
	 * @param driver
	 * @param element
	 */
	public void MouseOverOnElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();
	}
	
	
	/**
	 * This method is used to perform right click option
	 * @param driver
	 * @param element
	 */
	public void rightClickOnElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.contextClick(element).perform();
	}
	
	/**
	 * This method is used to perform right click option
	 * @param driver
	 * @param element
	 */
	public void doubleClickOnElement(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.doubleClick(element).perform();
	}
	
	/**
	 * This method is used to switch frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to switch frame based on id
	 * @param driver
	 * @param index
	 */
	public void switchFrame(WebDriver driver, String id) {
		driver.switchTo().frame(id);
	}
	
	
	/**
	 * This method is used to switch frame based on WebElement
	 * @param driver
	 * @param index
	 */
	public void switchFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is used to switch to main frame of webpage
	 * @param driver
	 * @param index
	 */
	public void switchFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
}
	
	/**
	 * This method is used to accept the alert popup
	 * @param driver
	 * @param text
	 */
	public String switchToAlertPopupAndAcceptIt(WebDriver driver, String text) {
		Alert a = driver.switchTo().alert();
		String altText=a.getText();
		if(a.getText().contains(text))
			System.out.println("alert is present");
		else
			System.out.println("Alert is not present");
		a.accept();
		return altText;
				
	}
	
	/**
	 * This method is used to dismiss the alert popup
	 * @param driver
	 * @param text
	 */
	public void switchToAlertPopupAndDismissIt(WebDriver driver, String text) {
		Alert a = driver.switchTo().alert();
		if(a.getText().trim().equalsIgnoreCase(text.trim()))
			System.out.println("alert is present");
		else
			System.out.println("Alert is not present");
		a.dismiss();;		
	}
	
	/**
	 * This method is used to handle file upload popup
	 * @param element
	 * @param path
	 */
	public void fileUpload(WebElement element, String path) {
		element.sendKeys(path);
	}
	
	/**
	 * This method is used to provide user own polling time
	 * @param duration
	 * @param element
	 * @param pollingTime
	 */
	public void customWait(int duration, WebElement element, long pollingTime)
	{
		int count=0;
		while(count<duration) {
			try {
				element.click();
				break;
			}catch(Exception e) {
				try {
					Thread.sleep(pollingTime);
					}catch(InterruptedException e1)
				{
						e1.printStackTrace();
				}
				count++;
			}
		}
	}
	
	/**
	 * This method is used to get screenshots
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 */
	public String takeScreenShot(WebDriver driver, String screenshotName) throws IOException {
		TakesScreenshot t=(TakesScreenshot) driver;
		File src = t.getScreenshotAs(OutputType.FILE);
		LocalDateTime ldt=LocalDateTime.now();
		File des=new File("./screenshots/"+screenshotName+""+ldt+".png");
		FileUtils.copyFile(src, des);
		return screenshotName;
		} 
	
	
	/**
	 * This method is used to switch to child window
	 * @param driver
	 * @param title
	 */
	public void switchWindow(WebDriver driver, String title) {
		Set<String> allwinhan = driver.getWindowHandles();
		for(String winhan:allwinhan)
		{
			driver.switchTo().window(winhan);
			String text = driver.getTitle();
			if(text.contains(title)) {
				break;
			}
		}
	}
	
	/**
	 * This method is used to switch to child window
	 * @param driver
	 * @param url
	 */
	public void switchingWindow(WebDriver driver, String url) {
		Set<String> allwinhan = driver.getWindowHandles();
		Iterator<String> it=allwinhan.iterator();
		while(it.hasNext()) {
			String winhan=it.next();
			driver.switchTo().window(winhan);
			String text=driver.getCurrentUrl();
			if(text.contains(url))
				break;
		}
	}
	
	public String getUrl(WebDriver driver) {
		String url = driver.getCurrentUrl();
		return url;
		
	}
}