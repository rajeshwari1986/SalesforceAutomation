package sfdc.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import sfdc.utilities.DataUtilities;

public class UserMenuPage {

	public UserMenuPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userNavLabel")
	public WebElement userMenu;

	@FindBy(css = ".menuButtonMenu.menuWidthExtended")
	public WebElement userMenuDropDown;

	@FindBy(xpath = "//div[@id='userNav-menuItems']//a")
	public List<WebElement> listOfuserMenuOptions;

	public boolean verifyUserMenuItems(WebDriver driver) {
		boolean isOptionVerified = true;
		String[] userMenuOptions = { "My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
				"Logout" };
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

	public boolean verifyUserName() throws IOException {
		DataUtilities du = new DataUtilities();
		boolean isUserNameValid = false;
		String expectedUserName = du.readPageValidationsText("homepage.username");
		if (expectedUserName.equals(userMenu.getText())) {
			isUserNameValid = true;
		}
		return isUserNameValid;
	}
}
