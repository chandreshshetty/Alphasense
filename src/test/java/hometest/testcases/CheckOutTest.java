package hometest.testcases;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hometest.pages.AddToCartPage;
import hometest.pages.CheckOutPage;
import hometest.pages.MyAccountPage;
import hometest.pages.SignUpPage;
import hometest.utilities.BaseTest;

public class CheckOutTest extends BaseTest{
	Logger logger = Logger.getLogger(Log.class.getName());
	SignUpPage signup;
	MyAccountPage myAccount;
	AddToCartPage cart;
	CheckOutPage checkout;

	public CheckOutTest() throws Exception {
		super();
	}

	@BeforeClass
	public void setUp() throws Exception {
		initialization();
		signup = new SignUpPage();
		myAccount = new MyAccountPage();
		cart = new AddToCartPage();
		checkout = new CheckOutPage();
	}
	
	/****************************************************************************************************
	* TEST CASE DESCRIPTION - Add a product to the card and proceed to checkout. Make sure the order is
							  successfully done and assert the confirmation message
	* TEST STEPS -  1) Open the Website
	 				2) SignIn with existing user
	 				3) Enter "Dress" keyword in the "Search" box and Click on "Search" button 
	 				4) Verify product listed are adequate to the keyword searched from the user
	 				5) Click on "AddToCart" button for first listed product
	 				6) Verify product added to the Mini cart page after clicking on "AddToCart" button
	 				   and then click on "Proceed To Checkout" button
	 				7) Verify Summary,Address,Shipping,payment,Order Confirmation page for the product 
	 				   after clicking on "Proceed To Checkout" button in the each page
	 				8) Verify order confirmation success message after placing the order
	****************************************************************************************************/
	@Test
	public void placeOrder() throws Exception {
		signup.userSignin(prop.getProperty("search_user"), prop.getProperty("password"));
		myAccount.searchForProduct("Dress");
		cart.verifyProductListingBasedOnSearch("Dress");
		cart.addToCartProduct();
		cart.verifyAndCheckOutProductInMiniCart();
		checkout.checkOutInSummaryPage();
		checkout.checkOutInAddressPage();
		checkout.checkOutInShippingPage();
		checkout.checkOutInPaymentPage();
		checkout.verifyOrderConfirmation();
	}

	@AfterClass
	public void closeBrowser() {
		tearDown();
	}
}
