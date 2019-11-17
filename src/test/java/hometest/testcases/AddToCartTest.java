package hometest.testcases;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import hometest.pages.AddToCartPage;
import hometest.pages.MyAccountPage;
import hometest.pages.SignUpPage;
import hometest.utilities.BaseTest;

@Listeners(hometest.utilities.TestUtils.class)
public class AddToCartTest extends BaseTest {
	Logger logger = Logger.getLogger(Log.class.getName());
	SignUpPage signup;
	MyAccountPage myAccount;
	AddToCartPage cart;

	public AddToCartTest() throws Exception {
		super();
	}

	@BeforeClass
	public void setUp() throws Exception {
		initialization();
		signup = new SignUpPage();
		myAccount = new MyAccountPage();
		cart = new AddToCartPage();
	}
	
	/****************************************************************************************************
	* TEST CASE DESCRIPTION - Search for the keyword ‘Dress’, assert the search results count and verify the
							  results are according to the searched keyword
	* TEST STEPS -  1) Open the Website
	 				2) SignIn with existing user
	 				3) Enter "Dress" keyword in the "Search" box and Click on "Search" button
	 				4) Verify count displayed against product listed after search
	 				5) Verify product listed are adequate to the keyword searched from the user
	****************************************************************************************************/
	@Test()
	public void verifyProductListing() throws Exception {
		signup.userSignin(prop.getProperty("search_user"), prop.getProperty("password"));
		myAccount.searchForProduct("Dress");
		cart.verifyProductListCount();
		cart.verifyProductListingBasedOnSearch("Dress");
		myAccount.userSignOut();
	}
	
	@AfterClass
	public void closeBrowser() {
		tearDown();
	}
}
