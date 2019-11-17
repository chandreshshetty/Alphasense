package hometest.pages;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import hometest.utilities.TestUtils;

public class AddToCartPage extends TestUtils {
	Logger logger = Logger.getLogger(Log.class.getName());

	public AddToCartPage() throws Exception {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='product-container']")
	List<WebElement> productListed;

	@FindBy(xpath = "//span[@class='heading-counter']")
	WebElement productCount;

	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	WebElement addToCartBtn;

	@FindBy(xpath = "//div[@class='layer_cart_product col-xs-12 col-md-6']/h2")
	WebElement miniCartPage;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement ProceedToCheckOutBtn;

	/*
	 * Method used to validate between product count and product listed after specific search
	 */
	public void verifyProductListCount() {
		logger.debug("Waiting for product page to be loaded aftre search");
		waitForElementToDisplay(productCount);
		String[] str = productCount.getText().split(" ");
		int countDisplayed = Integer.parseInt(str[0]);
		int productDisplayed = productListed.size();
		logger.debug("Validating between product count and listed product");
		Assert.assertEquals(countDisplayed, productDisplayed,
				"Product and Count displayed are not matching in Cart Page after search");
		logger.info("Successfully validated between product count and listed product");
	}
	
	/*
	 * Method used to validate product listed against searched keyword
	 */
	public void verifyProductListingBasedOnSearch(String product) {
		SoftAssert softAssert = new SoftAssert();
		logger.debug("Validating product listed based on product serached");
		for (WebElement ele : productListed) {
			if (!ele.getText().contains(product)) {
				softAssert.assertTrue(false, "Product listed without having " + product + " keyword");
			}
		}
		logger.info("Successfully validated product listed based on product serached");
	}

	/*
	 * Method used to add product AddToCart page
	 */
	public void addToCartProduct() {
		Actions action = new Actions(driver);
		logger.debug("Waiting for Add To cart button");
		action.moveToElement(productListed.get(0)).build().perform();
		addToCartBtn.click();
		logger.info("Clicked on Add To Cart button");
	}

	/*
	 * Method used to validate product added in the mini cart page
	 */
	public void verifyAndCheckOutProductInMiniCart() {
		logger.debug("Waiting for mini cart page to load after Add To Cart");
		waitForElementToDisplay(miniCartPage);
		Assert.assertEquals(miniCartPage.getText(), "Product successfully added to your shopping cart");
		logger.info("Successfully validated product in the Mini cart page");
		ProceedToCheckOutBtn.click();
		logger.info("Clicked on proceed to checkout button in mini cart page");
	}
}
