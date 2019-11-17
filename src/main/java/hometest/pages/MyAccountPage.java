package hometest.pages;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import hometest.utilities.TestUtils;

public class MyAccountPage extends TestUtils {
	Logger logger = Logger.getLogger(Log.class.getName());
	public MyAccountPage() throws Exception {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='logout']")
	WebElement userSignOut;
	
	@FindBy(id = "search_query_top")
	WebElement searchtextBox;
	
	@FindBy(name = "submit_search")
	WebElement searchBtn;
	
	@FindBy(xpath = "//a[@class='login']")
	WebElement signInBtn;
	
	public SignUpPage userSignOut() throws Exception {
		logger.info("Waiting for Signout page to be displayed");
		waitForElementToDisplay(userSignOut);
		userSignOut.click();
		logger.info("Clicked on Signout button in the My Account page");
		Assert.assertTrue(signInBtn.isDisplayed(), "Signout not working");
		logger.info("User successfully logged out of the system");
		return new SignUpPage();
	}

	public AddToCartPage searchForProduct(String product) throws Exception {
		logger.info("Waiting for search box to display");
		waitForElementToDisplay(searchtextBox);
		logger.info("Searching for keyword "+product);
		searchtextBox.sendKeys(product);
		searchBtn.click();
		logger.info("Clicked on search button after entering the keyword");
		return new AddToCartPage();
	}

}
