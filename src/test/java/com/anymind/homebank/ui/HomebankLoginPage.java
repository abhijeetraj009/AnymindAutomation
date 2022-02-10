package com.anymind.homebank.ui;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.anymind.homebank.framework.GetDriverInstance;


import junit.framework.Assert;

public class HomebankLoginPage {

	Random random = new Random();   
	int randomID = random.nextInt(50000);   
	static WebDriver driver;
	
	
   @BeforeClass
	public static void testLoginPage() {
	 
		driver = GetDriverInstance.getDriverInstance();
		GetDriverInstance.openTheHomepage(driver, "https://anylogi-recruitment.web.app/signup");
	}

	@Test
	
	public void usernameCannotbeBlank() throws InterruptedException {
		driver.get("https://anylogi-recruitment.web.app/signup");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[1]")).clear();
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		Thread.sleep(1000);
		if (!driver.findElement(By.xpath("//span[contains(text(),'User name cannot be blank')]")).isDisplayed()) {
			Assert.fail("No username cannot be empty message is shown");

		}
	}

	@Test
	public void usernamecannotcontainwhitespaces() throws InterruptedException {
		driver.get("https://anylogi-recruitment.web.app/signup");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[1]")).sendKeys("Abhijeet Kulkarni"+randomID);
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[2]")).sendKeys("123");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		Thread.sleep(2000);
		if (!driver.findElement(By.xpath("//span[contains(text(),'User name cannot contain whitespaces')]"))
				.isDisplayed()) {
			Assert.fail("User name cannot contain whitespaces");
		}
	}

	@Test
	public void passwordCannotbelessthanEightChars() throws InterruptedException {
		driver.get("https://anylogi-recruitment.web.app/signup");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[1]")).sendKeys("AbhijeetKulkarni"+randomID);
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[2]")).sendKeys("123");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		Thread.sleep(2000);
		if (!driver.findElement(By.xpath("//span[contains(text(),'Password cannot be less than 8 characters')]"))
				.isDisplayed()) {
			Assert.fail("Password cannot be less than 8 characters");
		}
	}

	@Test
	public void passwordCannotbeLargerthanThirtyTwoChars() throws InterruptedException {
		driver.get("https://anylogi-recruitment.web.app/signup");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[1]")).sendKeys("AbhijeetKulkarni"+randomID);
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[2]"))
				.sendKeys("1234567890abcdefghijklmnopqrstuvwxyz");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		Thread.sleep(2000);
		if (!driver.findElement(By.xpath("//span[contains(text(),'Password cannot be longer than 32 characters')]"))
				.isDisplayed()) {
			Assert.fail("Password cannot be longer than 32 characters");
		}
	}

	@Test
	public void passwordMustContainNumbers() throws InterruptedException {
		driver.get("https://anylogi-recruitment.web.app/signup");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[1]")).sendKeys("AbhijeetKulkarni"+randomID);
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[2]")).sendKeys("nopqrstuvwxyz");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		Thread.sleep(2000);
		if (!driver.findElement(By.xpath("//span[contains(text(),'Password must contain numbers')]")).isDisplayed()) {
			Assert.fail("Password must contain numbers");
		}
	}

	@Test
	public void passwordMustContainUppercaseLetters() throws InterruptedException {
		String name = "AbhijeetK" + randomID;
		driver.get("https://anylogi-recruitment.web.app/signup");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[1]")).sendKeys(name);
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[2]")).sendKeys("nopqrstuv123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		Thread.sleep(2000);
		if (!driver.findElement(By.xpath("//span[contains(text(),'Password must contain uppercase letters')]"))
				.isDisplayed()) {
			Assert.fail("Password must contain uppercase letters");
		}
	}
	
	@Test
	public void withdrawAmount() throws InterruptedException {
		String name = "AbhijeetK" + randomID;
		driver.get("https://anylogi-recruitment.web.app/signup");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[1]")).sendKeys(name);
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[2]")).sendKeys("Nopqrstuv123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		Thread.sleep(2000);
		double beforeWithdraw = Double.parseDouble(driver.findElement(By.xpath("//td[contains(text(),'Balance')]/..//td[2]")).getText());
		driver.findElement(By.xpath("(//a)[2]")).click();
		double amountToWithdraw=1000;
		
		driver.findElement(By.xpath("//input")).sendKeys(""+amountToWithdraw);
		driver.findElement(By.xpath("//button")).click();
		Thread.sleep(8000);
		double Updatedamount =Double.parseDouble(driver.findElement(By.xpath("//td[contains(text(),'Balance')]/..//td[2]")).getText());
		double newAmount = beforeWithdraw - (amountToWithdraw + (int) (amountToWithdraw * 0.30));
		
		if(Updatedamount!=newAmount) {
			Assert.fail("withdrawal value not matching");
		}
		driver.findElement(By.xpath("//button")).click();
		Thread.sleep(2000);
	}
	
	@Test
	public void depositMoney() throws InterruptedException {
		String name = "AbhijeetK" + randomID;
		driver.get("https://anylogi-recruitment.web.app/signup");
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[1]")).sendKeys(name);
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[2]")).sendKeys("Nopqrstuv123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'SIGNUP')]")).click();
		Thread.sleep(2000);
		double beforeDeposit = Double.parseDouble(driver.findElement(By.xpath("//td[contains(text(),'Balance')]/..//td[2]")).getText());
		driver.findElement(By.xpath("(//a)[1]")).click();
		double amountToDeposit=1000;
		
		driver.findElement(By.xpath("//input")).sendKeys(""+amountToDeposit);
		driver.findElement(By.xpath("//button")).click();
		Thread.sleep(8000);
		double Updatedamount =Double.parseDouble(driver.findElement(By.xpath("//td[contains(text(),'Balance')]/..//td[2]")).getText());
		double newAmount = beforeDeposit + amountToDeposit - ((int) (amountToDeposit * 0.30));
		
		if(Updatedamount!=newAmount) {
			Assert.fail("withdrawal value not matching");
		}
		
		driver.findElement(By.xpath("//button")).click();
		Thread.sleep(2000);
	}
	
	@After
	public void cleanFields() {
		driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[1]")).clear();
    	driver.findElement(By.xpath("(//*[contains(text(),'Username')]/input)[2]")).clear();
	}
	@AfterClass
	public static void teardown() {
		driver.quit();
	}
}
