package sfdc.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sfdc.utilities.DataUtilities;

public class LoginPage {
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "username")
	public WebElement username;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(id = "Login")
	public WebElement loginButton;
	
	@FindBy(id = "rem")
	public WebElement rememberMe;
	
	@FindBy(id = "error")
	public WebElement loginErrorMsg;
	
	public void loginToApp(WebDriver driver, String URL) throws IOException {
		driver.get(URL);
		DataUtilities du = new DataUtilities();
		username.sendKeys(du.readAccountProperties("username.prod.valid"));
		password.sendKeys(du.readAccountProperties("password.prod.valid"));
		loginButton.click();
	}
	
	public boolean verifyErrorMessagae() throws IOException {
		DataUtilities du = new DataUtilities();
		boolean isErrorMessage = false;
		String expectedErrrorMessage = du.readPageValidationsText("loginpage.errorMessage");
		if(expectedErrrorMessage.equals(loginErrorMsg.getText())){
			isErrorMessage = true;
		}
		return isErrorMessage;
	}
	

}
