package com.selenium.commons;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;


import com.google.common.base.Function;

//this class is were we will use all the reuseable functions e.g getwindow handle.

public class CommonCode {
	public static WebDriver driver = Configuration.browser(); // This will read the
														// configuration.java >
														// Gets the webdriver
														// instance

	public void getWindowHandle() {
		Set<String> handles = driver.getWindowHandles();// We will use this if
														// windows is greater
														// than 1 - otherwise we
														// just getwindowhandle
														// ();
		if (handles.size() >= 1) {
			System.out.println("Number of broiwsers opened are"
					+ handles.size());
			for (String handle : handles) {
				driver.switchTo().window(handle);

			}
		}

	}

	public WebElement fluentWait(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return foo;
	};

	public static WebElement explicitWait(WebDriver driver, By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(20, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id("foo"));
			}
		});
		return element;
	}
	public static void selectFromDropDown(WebElement ele, String options) {
		Select selectDriver = new Select(ele);
		selectDriver.selectByVisibleText(options);
	}
	public static void selectFromDropDownWithIndex(WebElement ele, int index) {
		Select selectDriver = new Select(ele);
		selectDriver.selectByIndex(index);
	}
	public static void waitMethod(int i) {
		try {
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static WebElement retryingFind(By by) {
	    WebElement result = null;
	    int attempts = 0;
	    while(attempts < 30) {
	        try {
	           waitMethod(1); 
	            result=driver.findElement(by);
	            break;
	        } catch(Exception e) {
	        }
	        attempts++;
	    }
	    return result;
	}

	public static String generateRandomDigits() {
		int k;
	    int m = (int) Math.pow(10, 8 - 1);
	    k= m + new Random().nextInt(9 * m);
	    String input = String.valueOf(k);
		 
		String number = input.replaceFirst("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1-$2-$3-$4");
		 
		System.out.println(number);
	    return number;
	}
	

}
