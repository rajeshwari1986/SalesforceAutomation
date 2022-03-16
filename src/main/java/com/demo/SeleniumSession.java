package com.demo;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumSession {
	static WebDriver driver;
	
	static {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	public static void loginError01() {
		driver.get("https://login.salesforce.com");
		driver.navigate().to("https://google.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.name("username")).sendKeys("lakshmip@sales.com");
		driver.findElement(By.id("password")).sendKeys("Lakshmi123");
		driver.findElement(By.name("Login")).click();
		String actualTitle = "Home Page ~ Salesforce - Developer Edition";
		if (actualTitle.equals(driver.getTitle())) {
			System.out.println("My TC 1 is passed");
		} else
			System.out.println("My TC1 failed");
	}

	public static void main(String[] args) throws InterruptedException {
		
	
		
		String s = "mithun";
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("nsjdbfusydf");
		username.click();
		
		List<WebElement> ribbonElements = driver.findElements(By.xpath("//li"));
		
		
		// Explicit wait
		
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(""))));
		
		
		
		// Fluent wait
		
//		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
//				.withTimeout(Duration.ofSeconds(30))
//				.pollingEvery(Duration.ofSeconds(10))
//				.ignoring(NoSuchElementException.class);
//		
//		WebElement loginButton = wait.until(new Function<WebDriver, WebElement>() {
//			public WebElement apply(WebDriver driver) {
//				return driver.findElement(By.id("Login"));
//			}
//		});
		
		driver.quit();
		driver.close();
	}

}
