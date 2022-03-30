package sfdc.test;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserMenuTest extends BaseTest{
	
	@Test(groups = {"usermenu","smoke", "regression"})
	public void selectUserMenuDropDownTC05(Method name) throws IOException, InterruptedException {
		test = extent.createTest(name.getName());
		loginPage.loginToApp(driver, selectEnvirnoment("prod"));
		test.info("Logged in to the app");
		Assert.assertTrue(userMenuPage.verifyUserName(), "Username verification failed");
		userMenuPage.userMenu.click();
		Thread.sleep(4000);
		if(reusableUtils.isElementDisplayed(userMenuPage.userMenuDropDown, driver)) {
			boolean status = userMenuPage.verifyUserMenuItems(driver);
			Assert.assertTrue(status, "Failed to verify user menu items");
			test.pass(name.getName());
		} else {
			test.fail("Usermenu item is not displayed");
		}
	}
}
