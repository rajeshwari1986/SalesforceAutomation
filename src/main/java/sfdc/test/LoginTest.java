package sfdc.test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.listeners.TestListeners;

@Listeners(TestListeners.class)
public class LoginTest extends BaseTest {
	
	
	@BeforeMethod
	public void launchAPP() throws IOException {
		driver.get(selectEnvirnoment("prod"));
	}

	@Test(invocationCount = 2)
	public void loginErrorMessageTC01(Method name) throws IOException {
		if (reusableUtils.isElementDisplayed(loginPage.username, driver)) {

			loginPage.username.sendKeys(du.readAccountProperties("username.prod"));
			test.info("Username entered");
			loginPage.password.clear();
			if (reusableUtils.waitForElementClickable(driver, loginPage.loginButton)) {
				loginPage.loginButton.click();
				Assert.assertTrue(loginPage.verifyErrorMessagae(), "Failed to verify error message");
//				test.pass("loginErrorMessageTC01");
			} else {
				System.out.println("Login button is not clickable");
			}
		} else {
			System.out.println("Username element is not visible");
		}
	}

	@Test(dataProvider = "testAccounts", dataProviderClass = LoginTest.class)
	public void loginAccounts(String username, String password, Method name) throws IOException {
//		test = extent.createTest(name.getName());
		if (reusableUtils.isElementDisplayed(loginPage.username, driver)) {

			loginPage.username.sendKeys(username);
			test.info("Username entered");
			loginPage.password.clear();
			loginPage.password.sendKeys(password);
			if (reusableUtils.waitForElementClickable(driver, loginPage.loginButton)) {
				loginPage.loginButton.click();
				Assert.assertTrue(loginPage.verifyErrorMessagae(), "Failed to verify error message");
//				test.pass("loginErrorMessageTC01");
			} else {
				System.out.println("Login button is not clickable");
			}
		} else {
			System.out.println("Username element is not visible");
		}
	}
	
	
	@DataProvider(name = "testAccounts")
	public Object[][] userAccounts(){
		return new Object[][] {{"mithun.r@tekarch.com","mithun123"},{"lakshmi@gmail.com","laskmi234"},{"",""}};
	}
	
	

}
