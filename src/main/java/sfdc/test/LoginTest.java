package sfdc.test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
	
	@Test
	public void loginErrorMessageTC01(Method name) throws IOException {
		test = extent.createTest(name.getName());
		driver.get(selectEnvirnoment("prod"));
		if(reusableUtils.isElementDisplayed(loginPage.username, driver)) {
			
			loginPage.username.sendKeys(du.readAccountProperties("username.prod"));
			test.info("Username entered");
			loginPage.password.clear();
			if(reusableUtils.waitForElementClickable(driver, loginPage.loginButton)) {
				loginPage.loginButton.click();
				Assert.assertTrue(loginPage.verifyErrorMessagae(),"Failed to verify error message");
				test.pass("loginErrorMessageTC01");
			}
			else {
				System.out.println("Login button is not clickable");
			}
		} else {
			System.out.println("Username element is not visible");
		}
	}
	
	
}
