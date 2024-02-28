package com.pdp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.selenium.commons.Actions;
import com.selenium.commons.CommonCode;
import com.selenium.commons.Configuration;

public class CreatePldPage {

	public WebDriver driver = Configuration.browser();

	public CreatePldPage() {
		PageFactory.initElements(driver, this);
	}

	By figureTextBox1 = By.id("MainContent_txtFigure");
	By addFigure1 = By.id("MainContent_btnAddNewFigure");
	By figureTextBox2 = By.id("MainContent_txtAddFigureNo");

	@FindBy(id = "MainContent_txtDA")
	private WebElement dataAuthor;

	@FindBy(id = "MainContent_txtDChk")
	private WebElement dataCheker;

	By addFigure2 = By.name("ctl00$MainContent$btnAddFigure");
	By clickRow = By.xpath("(//tr[@class='RowStyle'])[last()]");
	By missingDocument = By.xpath("//input[@id='MainContent_chkMissingDocs']");
	By workDriver = By.id("MainContent_ddlWorkDriver");
	By preExistCheckBox = By.id("MainContent_grdviewAddNewError_chkPreExisting");
	By trans = By.id("MainContent_grdviewAddNewError_ddlTransaction");
	By items = By.id("MainContent_grdviewAddNewError_txtItem");
	By iLLDropDown = By.id("MainContent_grdviewAddNewError_ddlILL");
	By partNumber = By.id("MainContent_grdviewAddNewError_txtPartno");
	By type = By.id("MainContent_grdviewAddNewError_ddlType");
	By IndValue = By.id("MainContent_grdviewAddNewError_txtIndent");
	By nounValue = By.id("MainContent_grdviewAddNewError_txtNoun");
	By upaValue = By.id("MainContent_grdviewAddNewError_txtNewUPA");
	By lcValue = By.id("MainContent_grdviewAddNewError_txtLC");
	By item2 = By.id("MainContent_grdviewNotes_ddlItem");
	By note = By.id("MainContent_grdviewNotes_txtNote");
	By insertLink = By.xpath("//table[@id='MainContent_grdviewNotes']//a[text()='Insert']");
	By illSaveLink = By.id("MainContent_btnILLSave");
	By reviewLink = By.id("MainContent_btnReview");
	By daSaveLink = By.id("MainContent_btnDaSave");
	By okLink = By.id("MainContent_btnMSGOk");
	By returnPld = By.id("MainContent_btnReturnPLD");
	By reviewLink2 = By.id("MainContent_btnReview");
	By rejectLink = By.id("MainContent_btnRejectPLD");
	By reviewLink3 = By.id("MainContent_btnReview");
	By acceptlink = By.id("MainContent_btnAcceptPLD");
	static By actionClick = By.xpath("(//a[text()='Active List'])[2]");

	String number = CommonCode.generateRandomDigits();

	public void create_PDL() {

		Actions.sendkeys(figureTextBox1, number);
		Actions.click(addFigure1);
		Actions.sendkeys(figureTextBox2, number);

		dataAuthor.sendKeys("huraira");
		CommonCode.waitMethod(1);
		dataAuthor.sendKeys(Keys.ENTER);
		CommonCode.waitMethod(1);
		dataCheker.sendKeys("huraira");
		CommonCode.waitMethod(1);
		dataCheker.sendKeys(Keys.ENTER);
		CommonCode.waitMethod(1);

		Actions.click(addFigure2);
		Actions.click(clickRow);

		CommonCode.retryingFind(missingDocument).click();
		CommonCode.waitMethod(3);
		Actions.isSelected(missingDocument);
		Actions.selectByVisibleText(workDriver, "ENG");

		CommonCode.retryingFind(preExistCheckBox).click();
		Actions.isSelected(preExistCheckBox);
		Actions.selectByVisibleText(trans, "A");
		Actions.sendkeys(items, "005");
		Actions.selectByVisibleText(iLLDropDown, "Y");
		Actions.sendkeys(partNumber, "AB7DG456783");
		Actions.selectByVisibleText(type, "L");
		Actions.sendkeys(IndValue, "3");
		Actions.sendkeys(nounValue, "NHUTRE567");
		Actions.sendkeys(upaValue, "005");
		Actions.sendkeys(lcValue, "FH");

		CommonCode.retryingFind(item2);
		Actions.selectByIndex(item2, 1);
		Actions.sendkeys(note, "Demo Test");
		Actions.click(insertLink);
		Actions.click(illSaveLink);
		Actions.click(reviewLink);
		Actions.click(daSaveLink);
		Actions.click(okLink);
		Actions.click(returnPld);
		Actions.click(reviewLink2);
		Actions.click(rejectLink);
		Actions.click(reviewLink3);
		Actions.click(acceptlink);
	}

	public void personalActiveList() {
		HomePage.validationPLD("E737", "PLD");

		Assert.assertTrue(Actions.isDisplayed(By.xpath("//td[text()='" + number + "']")),
				"Figure number is not displayed");
		Assert.assertTrue(Actions.isDisplayed(By.xpath("//td[text()='" + number + "']/..//td[text()='Accepted']")),
				"Figure number Accepted is not displayed");
	}

	public void managementlActiveList() {
		CommonCode.waitMethod(5);
		CommonCode.retryingFind(actionClick);
		Actions.click(actionClick);
		Assert.assertTrue(Actions.isDisplayed(By.xpath("//td[text()='" + number + "']")),
				"Figure number is not displayed");
		Assert.assertTrue(Actions.isDisplayed(By.xpath("//td[text()='" + number + "']/..//td[text()='Accepted']")),
				"Figure number Accepted is not displayed");
	}

}
