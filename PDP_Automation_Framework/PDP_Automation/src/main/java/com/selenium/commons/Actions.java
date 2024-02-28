package com.selenium.commons;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Actions {
	public static WebDriver driver = Configuration.browser();

	public static WebElement fluentWait(WebDriver driver, By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class).ignoring(ElementNotInteractableException.class)
				.ignoring(ElementNotVisibleException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(driver.findElement(by))));
				return driver.findElement(by);
			}
		});
		return element;
	}

	public static void sendkeys(By locator, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(text);

	}

	public static void selectByVisibleText(By locator, String element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		Select s = new Select(wait.until(ExpectedConditions.elementToBeClickable(locator)));
		s.selectByVisibleText(element);

	}

	public static void selectByIndex(By locator, int index) {
		Select s = new Select(fluentWait(driver, locator));
		s.selectByIndex(index);

	}

	public static boolean isSelected(By locator) {
		boolean b = false;
		try {
			b = fluentWait(driver, locator).isSelected();
		} catch (Exception e) {
			Assert.fail("element not found");
		}
		return b;

	}

	/*
	 * public static void click(By locator) { fluentWait(driver, locator).click();
	 * 
	 * }
	 */

	public static void click(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public static boolean isDisplayed(By locator) {
		boolean b = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			b = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
		} catch (Exception e) {
		}
		return b;
	}

}
