package com.demo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsDemo {
	
	static WebDriver driver;
	public static String takeScreenshot() throws IOException {
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir")+"//target//Screenshots//"+dateFormat+".PNG";
		File destination = new File(destinationPath);
		FileUtils.copyFile(srcFile, destination);
		return destinationPath;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
//		Config
		ExtentReports extent = new ExtentReports();
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String reportPath = System.getProperty("user.dir")+"//target//Reports//"+dateFormat+"_SFDCReport.html";
		ExtentHtmlReporter report = new ExtentHtmlReporter(reportPath);
		extent.attachReporter(report);
		String expectedErrorMsg = "Check your username";
//		Every testcase should have a separate information
		
		ExtentTest test = extent.createTest("LoginErrorMessage01");
		driver.get("https://login.salesforce.com");
		test.info("Login page launched");
		driver.findElement(By.name("username")).sendKeys("mithun.r@tekarch.com");
		test.info("Username entered");
		driver.findElement(By.id("password")).sendKeys("Lakshmi123");
		test.info("Passowrd entered");
		WebElement loginButton = driver.findElement(By.name("Login"));
		loginButton.click();
		test.log(Status.INFO, "Login button clicked");
		String actualErrorMsg = driver.findElement(By.id("error")).getText();
		if(expectedErrorMsg.equals(actualErrorMsg))
		{
			test.pass("LoginErrorMessage01 Passed");
		}
		else {
			test.fail("LoginErrorMessage01 Failed");
			test.addScreenCaptureFromPath(takeScreenshot());
		}
		
		
		
		ExtentTest test2 = extent.createTest("LoginScenario02");
		String expectedPageTitle = "Salesforce|Home";
		test2.info("Entered username"+"mithun");
		test2.info("entered password");
		test2.info("Login button clicked");
		
		if(expectedPageTitle.equals("Salesforce|ome"))
		{
			test2.pass("LoginErrorMessage01 Passed");
		}
		else {
			test2.fail("LoginErrorMessage01 Failed");
		}
		
		extent.flush();
		
		
	}

}
