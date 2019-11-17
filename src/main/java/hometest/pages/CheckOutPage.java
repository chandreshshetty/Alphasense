package hometest.pages;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import hometest.utilities.TestUtils;

public class CheckOutPage extends TestUtils{
	Logger logger = Logger.getLogger(Log.class.getName());
	
	public CheckOutPage() throws Exception {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOutBtn;
	
	@FindBy(xpath="//table[@id='cart_summary']")
	WebElement cartSummary;
	
	@FindBy(xpath="//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOutInSummaryBtn;
	
	@FindBy(id="uniform-id_address_delivery")
	WebElement chooseDeliveryBtn;
	
	@FindBy(xpath="//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOutInAddressBtn;
	
	@FindBy(xpath="//label[contains(text(),'I agree to the terms of service and will adhere to')]")
	WebElement termsOfServiceBtn;
	
	@FindBy(xpath="//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOutInShippingBtn;
	
	@FindBy(xpath="//a[@class='cheque']")
	WebElement payByChequeBtn;
	
	@FindBy(xpath="//span[contains(text(),'I confirm my order')]")
	WebElement confirmOrderBtn;
	
	@FindBy(xpath="//p[@class='alert alert-success']")
	WebElement successMessage;
	
	public void checkOutInSummaryPage() {
		logger.info("Waiting for Summary page to load");
		waitForElementToDisplay(cartSummary);
		Assert.assertTrue(cartSummary.isDisplayed(), "Summary page not loaded");
		logger.info("Successfully validated navigation to summary page");
		proceedToCheckOutInSummaryBtn.click();
		logger.info("Clicked on proceed to checkout button in the summary page");
	}
	
	public void checkOutInAddressPage() {
		logger.info("Waiting for Addresss page to load");
		waitForElementToDisplay(chooseDeliveryBtn);
		Assert.assertTrue(chooseDeliveryBtn.isDisplayed(), "Address page not loaded");
		logger.info("Successfully validated navigation to address page");
		proceedToCheckOutInAddressBtn.click();
		logger.info("Clicked on proceed to checkout button in the address page");
	}
	
	public void checkOutInShippingPage() {
		logger.info("Waiting for Shipping page to load");
		waitForElementToDisplay(termsOfServiceBtn);
		Assert.assertTrue(termsOfServiceBtn.isDisplayed(), "Shipping page not loaded");
		logger.info("Successfully validated navigation to shipping page");
		termsOfServiceBtn.click();
		logger.info("Clicked on Terms and Service button in the shipping page");
		proceedToCheckOutInShippingBtn.click();
		logger.info("Clicked on proceed to checkout button in the shipping page");

	}
	
	public void checkOutInPaymentPage() {
		logger.info("Waiting for Payment page to load");
		waitForElementToDisplay(payByChequeBtn);
		Assert.assertTrue(payByChequeBtn.isDisplayed(), "Payment page not loaded");
		logger.info("Successfully validated navigation to payment page");
		payByChequeBtn.click();
		logger.info("Clicked on PayByCheque button in the payment page");
		logger.info("Waiting for confirm order page to load");
		waitForElementToDisplay(confirmOrderBtn);
		confirmOrderBtn.click();
		logger.info("Clicked on confirm order button in the order page");

	}
	
	public void verifyOrderConfirmation() {
		Assert.assertEquals(successMessage.getText(), "Your order on My Store is complete.", "Order not Placed");
		logger.info("Successfully validated success message after placing order");
	}
}
