package com.selenium.commons;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Configuration {

	public static Properties properties = new Properties(); // Here we imported
															// Java.util

	public static String filepath;
	public static FileInputStream in;
	public static String URL;
	public static String Browser;
	public static WebDriver driver;
	

	public static WebDriver browser() {
		//Reporter.log("Invoked browser", true);

		if (Browser.equalsIgnoreCase("firefox")) {
			if (driver == null) {
				WebDriverManager.chromedriver().setup();
				driver = new FirefoxDriver();
			} else
				return driver;

		}

		else if (Browser.equalsIgnoreCase("edge")) {
			if (driver == null) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else
				return driver;

		} else if (Browser.equalsIgnoreCase("chrome")) {
			if (driver == null) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else
				return driver;
		} else if (Browser.equalsIgnoreCase("safari")) {
			if (driver == null)
				WebDriverManager.chromedriver().setup();
				driver = new SafariDriver();
			return driver;
		}
		return driver;

	}

	static {
		try {
			if (System.getProperty("EnvType") == null) {
				filepath = "src/test/resources/QA-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties qa loop", true);

			} else if (System.getProperty("EnvType").equalsIgnoreCase("qa")) {
				filepath = "src/test/resources/QA-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties qa loop", true);

			} else if (System.getProperty("EnvType").equalsIgnoreCase("dev")) {
				filepath = "src/test/resources/dev-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties dev loop", true);

			} else if (System.getProperty("EnvType").equalsIgnoreCase("staging")) {
				filepath = "src/test/resources/staging-environment.properties";
				in = new FileInputStream(System.getProperty("user.dir") + "/" + filepath);
				properties.load(in);
				Reporter.log("in properties staging loop", true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		URL = properties.getProperty("url");
		Browser = properties.getProperty("Browser");
		//username = properties.getProperty("username");
		//password = properties.getProperty("password");

	}

	public static String LoginURL() {
		Reporter.log("URL is" + URL, true);
		return URL;

	}
}
