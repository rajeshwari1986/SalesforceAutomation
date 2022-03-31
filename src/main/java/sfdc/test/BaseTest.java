package sfdc.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import sfdc.pages.LoginPage;
import sfdc.pages.UserMenuPage;
import sfdc.utilities.AppConstants;
import sfdc.utilities.DataUtilities;
import sfdc.utilities.ReusableUtils;

public class BaseTest {
	public static long startTime = 0;
	public static long endTime = 0;
	DataUtilities du = new DataUtilities();
	public static WebDriver driver = null;
	public static ExtentReports extent = null;
	public static ExtentHtmlReporter report = null;
	public static ExtentTest test = null;
	public static LoginPage loginPage = null;
	public static ReusableUtils reusableUtils = null;
	public static UserMenuPage userMenuPage = null;
	public Logger logger = Logger.getLogger(getClass().getSimpleName());
	
	@BeforeSuite
	public void suiteLevelConfigs() throws IOException {
		intializeLog4jLogging();
		logger.info("Logging is starting");
	}
	
	@Parameters("browser name")
	@BeforeTest
	public void setup(String sBrowserName ) throws IOException {
		initializeReports();
		logger.info("Extent reports are configured");
		driver = getDriver(sBrowserName, false);
		logger.info("Browser config is set");
		loginPage = new LoginPage(driver);
		reusableUtils = new ReusableUtils();
		userMenuPage = new UserMenuPage(driver);

	}

	@AfterTest
	public void tearDown() {
		driver.close();
		logger.info("Browser is closed");
		extent.flush();
		logger.info("Report is flushed");
	}

	/**
	 * This function will return browser configuration
	 * 
	 * @param sBrowserName eg: chrome, safari, firefox
	 * @return driver
	 */
	public WebDriver getDriver(String sBrowserName, boolean headless) {
		String browserName = sBrowserName.toLowerCase();
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			if (headless == true) {
				driver = new ChromeDriver(headLessMode());
				logger.info("Chromebrowser in headless mode configured");
			} else {
				driver = new ChromeDriver();
				logger.info("Chromebrowser in normal mode configured");
			}

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

	public ChromeOptions headLessMode() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080", "--ignore-certificate-errors");
		logger.info("Headless mode configuration initialized");
		return options;
	}

	public String selectEnvirnoment(String envirnoment) throws IOException {
		String appURL = null;
		switch (envirnoment) {
		case "prod":
			appURL = du.readAppEnvirnoments("prod.url");
			logger.info("Tests will start in PROD envirnoment");
			break;
		case "qa":
			appURL = du.readAppEnvirnoments("qa.url");
			logger.info("Tests will start in QA envirnoment");
			break;
		case "dev":
			appURL = du.readAppEnvirnoments("dev.url");
			logger.info("Tests will start in DEV envirnoment");
			break;
		default:
			appURL = null;
			logger.fatal("Envirnoment selection FAILED");
		}
		return appURL;
	}

	public void initializeReports() {
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "//target//Reports//" + dateFormat + "_SFDCReport.html";
		extent = new ExtentReports();
		report = new ExtentHtmlReporter(reportPath);
		extent.attachReporter(report);
	}

	public void logoutFromApp() {
		userMenuPage.userMenuDropDown.click();
		logger.info("logging out called");
		userMenuPage.selectOptionFromUserMenu("Logout");
		logger.info("App is logged out");
	}
	
	public void intializeLog4jLogging() throws IOException {
		Properties log4jProp = new Properties();
		FileInputStream fis = new FileInputStream(AppConstants.LOG4J_PROPERTIES_PATH);
		log4jProp.load(fis);
		PropertyConfigurator.configure(fis);
	}
	

}
