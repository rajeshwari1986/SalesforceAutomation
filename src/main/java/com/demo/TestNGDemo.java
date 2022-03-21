package com.demo;

import java.util.NoSuchElementException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGDemo {
	
	@BeforeClass
	public void xyz() {
		
		
		
	}
	
	@BeforeTest
	public void setBrowser() {
		System.out.println("Setup browser");
	}
	
	@AfterTest()
	public void closeBrowser() {
		System.out.println("Browser closed");
	}
	
	@BeforeMethod
	public void login() {
		System.out.println("Login to the application");
	}
	
	@AfterMethod
	public void logout() {
		System.out.println("Logout from SFDC");
	}
	
	@Test(priority = 1)
	public void TC01() {
		System.out.println("My firsrt tc");
//		throw new NoSuchElementException();
	}
	
	// Default priority 0
	@Test(priority = -1, dependsOnMethods = "TC01" )
	public void TC02() {
		
		System.out.println("My second tc");
		
	}
	
	@Test(priority = 3)
	public void TC03() {
		System.out.println("My third tc");
		String actualText = "Hello";
		String expectedText = "Hi";
//		Hard assert
		Assert.assertEquals(actualText, expectedText);
//		Soft assert // verify
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualText, expectedText);
		System.out.println("COntinue exec..");
		sa.assertAll();
	}
	
	@Test (priority = 4)
	public void TC04() {
		
		System.out.println("My fourth tc");
		
	}
	

}
