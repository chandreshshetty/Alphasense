package hometest.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestUtils extends BaseTest implements ITestListener {
	
	public TestUtils() throws Exception {
		super();
	}

	public static void selectTextFromDropDown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public String randomText(int n) {
		return RandomStringUtils.randomAlphabetic(n);
	}

	public static void waitForElementToDisplay(WebElement ele) {
		Logger logger = Logger.getLogger(Log.class.getName());
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (TimeoutException e) {
			logger.error("Element not displayed after waiting for 20 seconds", e);
		} 
		catch (Exception e) {
			logger.error("Exception", e);
		} 
	}

	public void onTestSuccess(ITestResult result) {
		try {
			takeScreenshot(result.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		try {
			takeScreenshot(result.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestStart(ITestResult result) {
		logger.info("*********started with Test "+result.getMethod()+" **********");
	}

	public static void takeScreenshot(String name) throws Exception {
		String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("screenshots/" + name + "_" + fileSuffix + ".png"));
	}

}
