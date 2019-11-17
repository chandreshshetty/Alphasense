package hometest.utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	Logger logger = Logger.getLogger(Log.class.getName());
	public static WebDriver driver;
	public static Properties prop;
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public BaseTest() throws Exception {
		prop = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/config.properties");
		prop.load(ip);
	}

	public static void initialization() {
		Logger logger = Logger.getLogger(Log.class.getName());
		// takes input from user at runtime to execute test case on required environment
		// if parameter null if executes tests in production environment
		String parameter = System.getProperty("suiteParam");
		if (parameter != null) {
			parameter = parameter.toLowerCase();
		}
		logger.info("Environment to execute the testcases : " + parameter);
		// fetches browser parameter from config.properties file
		String browserName = prop.getProperty("browser");
		logger.info("Browser invoked : " + browserName);
		if (browserName.equals("chrome")) {
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			// used chromeoptions to customize download folder of chrome to specified folder
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "/downloadedInvoice");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome_driver_path"));
			driver = new ChromeDriver(options);
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefox_driver_path"));
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		logger.info("Browser window maximised");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

		if (parameter != null) {
			driver.get(prop.getProperty(parameter));
			logger.info("Navigated to " + parameter + " environment");
		} else {
			driver.get(prop.getProperty("prod"));
			logger.info("Navigated to production environment");
		}

	}

	public void tearDown() {
		driver.quit();
	}

}
