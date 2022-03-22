package com.demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author user
 *
 */
public class ReusableFunctions {

	public static void enterText(WebElement element, String text) {
		element.sendKeys(text);
	}

	/**
	 * This function will select an option from user menu drop down
	 * 
	 * @param driver Webdriver
	 * @param optionName "My Profile", "Logout", "", ""
	 * @return {boolean} true if option is selected else false
	 */
	public static boolean selectOptionInUserMenu(WebDriver driver, String optionName) {
		boolean isOptionSelected = false;
		WebElement userMenuOption = driver.findElement(By.xpath("//a[text()='" + optionName+ "']"));
		if (userMenuOption.isDisplayed()) {
			userMenuOption.click();
			isOptionSelected = true;
		} else {
			System.out.println("Usermenu option " + optionName + " is not visible");
		}
		return isOptionSelected;
	}
	
	/**
	 * @param driver
	 * @return
	 */
	public static boolean verifyUserMenuItems(WebDriver driver) {
		boolean isOptionVerified = true;
		String[] userMenuOptions = { "My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
				"Logout" };
		List<WebElement> listOfuserMenuOptions = driver.findElements(By.xpath("(//div[@id='userNav-menuItems']//a)"));
		for (int i = 0; i < userMenuOptions.length; i++) {
			String optionValue = listOfuserMenuOptions.get(i).getText();
			if (optionValue.equals(userMenuOptions[i])) {
				System.out.println("Option " + userMenuOptions[i] + " is verified");
			} else {
				System.out.println("Verification of " + userMenuOptions[i] + " failed");
				isOptionVerified = false;
			}
		}
		return isOptionVerified;
	}

//	public boolean clickOnProfile(WebElement profileElement) {
//		if (profileElement.isDisplayed()) {
//			profileElement.click();
//			return true;
//		} else
//			return false;
//	}

	public static boolean clickOnElement(WebElement element) {
		if (element.isDisplayed()) {
			element.click();
			return true;
		} else
			return false;
	}

//	public boolean clickOnPostLink(WebElement postLink) {
//		if (postLink.isDisplayed()) {
//			postLink.click();
//			return true;
//		} else
//			return false;
//	}

	public static boolean createAPost(WebElement postTextBox, WebElement shareButton, String message) {
		boolean isPostCreated = false;
		if (postTextBox.isDisplayed()) {
			postTextBox.sendKeys(message);
			if (shareButton.isEnabled()) {
				shareButton.click();
				System.out.println("Created a post successfully");
				isPostCreated = true;
			} else {
				System.out.println("Failed to click on the share button");
			}
		}
		return isPostCreated;
	}
	
	public static void clickOnFileUpload(WebElement fileUplaod) {
		fileUplaod.click();
	}
	
	public static void uploadFile(WebElement uploadFile, WebElement shareButton, String filePath) {
		if(uploadFile.isDisplayed()) {
			uploadFile.sendKeys(filePath);
			shareButton.click();
		}
	}
	
	public static void selectOptionFromDropdown(WebElement element, int index) {
		Select options = new Select(element);
		options.selectByIndex(index);
		
	}

}