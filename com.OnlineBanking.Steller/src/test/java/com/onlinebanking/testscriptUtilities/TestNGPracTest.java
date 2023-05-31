package com.onlinebanking.testscriptUtilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.OnlineBanking.Steller.Utilities.BaseClass;

public class TestNGPracTest extends BaseClass{

	@Test
	public void createAccount() {
		System.out.println("create account");
	}
}
