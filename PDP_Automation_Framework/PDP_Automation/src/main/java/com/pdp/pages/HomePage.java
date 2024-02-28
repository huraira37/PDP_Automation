package com.pdp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.selenium.commons.Actions;
import com.selenium.commons.CommonCode;
import com.selenium.commons.Configuration;

public class HomePage {

	public WebDriver driver = Configuration.browser();

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	static By homeLink = By.xpath("//input[@id='btnMastrHome']");
	static By modelDropDown = By.id("ddlModel");
	static By pdpType = By.id("ddlPDPType");
	static By submitButton = By.id("btnMastrSubmit");
	By createPLD = By.xpath("//a[text()='Create PLD']");

	public void launchingPLD(String modelNo, String PdpType) {
		CommonCode.retryingFind(homeLink);
		Actions.click(homeLink);
		Actions.selectByVisibleText(modelDropDown, modelNo);
		CommonCode.retryingFind(pdpType);
		Actions.selectByVisibleText(pdpType, PdpType);
		CommonCode.retryingFind(submitButton);
		Actions.click(submitButton);
		Actions.click(createPLD);
	}
	public static void validationPLD(String modelNo, String PdpType) {
		CommonCode.retryingFind(homeLink);
		Actions.click(homeLink);
		Actions.selectByVisibleText(modelDropDown, modelNo);
		CommonCode.retryingFind(pdpType);
		Actions.selectByVisibleText(pdpType, PdpType);
		CommonCode.retryingFind(submitButton);
		Actions.click(submitButton);
		
		
	}


}
