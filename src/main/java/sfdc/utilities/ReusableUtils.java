package sfdc.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sfdc.test.BaseTest;

public class ReusableUtils {

	public boolean isElementDisplayed(WebElement element, WebDriver driver) {
		boolean isElementFound = false;
		if (element.isDisplayed()) {
			isElementFound = true;
		}
		return isElementFound;
	}

	public boolean waitForElementClickable(WebDriver driver, WebElement element) {
		boolean isElementClickable = false;
		WebDriverWait wait = new WebDriverWait(driver, WaitConstants.ELEMENT_WAIT_DURATION);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			isElementClickable = true;
		} catch (Exception e) {
			System.out.println("Exception occured while waiting for the element" + e.getMessage());
		}
		return isElementClickable;
	}

	public String takeScreenshot() throws IOException {
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) BaseTest.driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir")+"//target//Screenshots//"+dateFormat+".PNG";
		File destination = new File(destinationPath);
		FileUtils.copyFile(srcFile, destination);
		return destinationPath;
	}
}
