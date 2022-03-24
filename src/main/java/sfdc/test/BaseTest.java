package sfdc.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import sfdc.pages.LoginPage;
import sfdc.pages.UserMenuPage;
import sfdc.utilities.DataUtilities;
import sfdc.utilities.ReusableUtils;

public class BaseTest {

	DataUtilities du = new DataUtilities();
	public static WebDriver driver = null;
	public static ExtentReports extent = null;
	public static ExtentHtmlReporter report = null;
	public static ExtentTest test = null;
	public static LoginPage loginPage = null;
	public static ReusableUtils reusableUtils = null;
	public static UserMenuPage userMenuPage = null;
	
	@BeforeTest
	public void setup() {
		initializeReports();
		driver = getDriver("chrome");
		loginPage = new LoginPage(driver);
		reusableUtils = new ReusableUtils();
		userMenuPage = new UserMenuPage(driver);
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		extent.flush();
	}
	

	/**
	 * This function will return browser configuration
	 * 
	 * @param sBrowserName eg: chrome, safari, firefox
	 * @return driver
	 */
	public WebDriver getDriver(String sBrowserName) {
		String browserName = sBrowserName.toLowerCase();
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
//			test.log(Status.INFO, "ChromeBrowser is initialized");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;

		default:
			driver = null;
			break;
		}

		return driver;
	}

	
	public String selectEnvirnoment(String envirnoment) throws IOException {
		String appURL = null;
		switch (envirnoment) {
		case "prod":
			appURL = du.readAppEnvirnoments("prod.url");
			break;
		case "qa":
			appURL = du.readAppEnvirnoments("qa.url");
			break;
		case "dev":
			appURL = du.readAppEnvirnoments("dev.url");
			break;
		default:
			appURL = null;
		}
		return appURL;
	}
	
	public void initializeReports() {
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String reportPath = System.getProperty("user.dir")+"//target//Reports//"+dateFormat+"_SFDCReport.html";
		extent = new ExtentReports();
		report = new ExtentHtmlReporter(reportPath);
		extent.attachReporter(report);
	}

}
