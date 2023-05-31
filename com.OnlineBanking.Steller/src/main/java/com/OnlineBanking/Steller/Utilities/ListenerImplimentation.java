package com.OnlineBanking.Steller.Utilities;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ListenerImplimentation extends BaseClass implements ITestListener {

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot tsc=(TakesScreenshot) driver;
		File src = tsc.getScreenshotAs(OutputType.FILE);
		LocalDateTime localDateTime=LocalDateTime.now();
		String cuDateTime = localDateTime.toString().replace(" ", "_").replace(":","_");
		File des=new File("./screenshot/"+" "+testName+" "+cuDateTime+".png");
		try {
			FileUtils.copyFile(src, des);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

	
	
	
}
