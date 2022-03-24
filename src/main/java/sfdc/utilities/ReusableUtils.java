package sfdc.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

}
