package com.anymind.homebank.framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetDriverInstance {
	 static WebDriver driver;
	
	@SuppressWarnings("deprecation")
	public static WebDriver getDriverInstance() {
		
		//Setting system properties of ChromeDriver
		System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");

		//Creating an object of ChromeDriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		//Deleting all the cookies
		driver.manage().deleteAllCookies();

		//Specifiying pageLoadTimeout and Implicit wait
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return driver;
	}

	public static void openTheHomepage(WebDriver driver, String url) {
		//launching the specified URL
		driver.get(url);
	}
}
