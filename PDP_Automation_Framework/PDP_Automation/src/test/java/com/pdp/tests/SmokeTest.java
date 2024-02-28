package com.pdp.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.pdp.pages.CreatePldPage;
import com.pdp.pages.HomePage;
import com.selenium.commons.CommonCode;
import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class SmokeTest {

	public WebDriver driver = Configuration.browser();
	public HomePage home;
	public CreatePldPage createPLD;
	
	public SmokeTest() {
		home = new HomePage();
		createPLD = new CreatePldPage();
	}
	
	@BeforeSuite(alwaysRun = true)
	public void invokeBrowser() {
		driver.get(Configuration.URL);
		//CommonCode.waitMethod(5);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(testName = "SmokeTest", description = "creating_PLD", timeOut = 190000, enabled = true, groups = {"smoke", "1" }, priority = 1)
	public void validatingPLD_creation() {
		home.launchingPLD("E737", "PLD");
		createPLD.create_PDL();
		createPLD.personalActiveList();
		createPLD.managementlActiveList();
		System.out.println("PLD created & validated successfully");
	}
	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
		CommonCode.waitMethod(10);
		driver.quit();
	}
}
