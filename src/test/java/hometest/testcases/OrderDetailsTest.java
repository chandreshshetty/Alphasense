package hometest.testcases;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hometest.pages.OrderDetailsPage;
import hometest.pages.SignUpPage;
import hometest.utilities.BaseTest;

public class OrderDetailsTest extends BaseTest{
	
	Logger logger = Logger.getLogger(Log.class.getName());
	SignUpPage signup;
	OrderDetailsPage orderDetails;
	
	public OrderDetailsTest() throws Exception {
		super();
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		initialization();
		signup = new SignUpPage();
		orderDetails = new OrderDetailsPage();
	}
	
	/****************************************************************************************************
	* TEST CASE DESCRIPTION - Download the invoice pdf from order details page and make sure that the
							   download is successful
	* TEST STEPS -  1) Open the Website
	 				2) SignIn with existing user
	 				3) Click on OrderHistory and Details button in the My Account page 
	 				4) Click on Download Invoice button for first order
	 				5) Verify downloaded Invoice in the downloadedInvoice folder
	* PRE REQUISITE - User should have one order placed in order details page
	****************************************************************************************************/
	@Test()
	public void downloadOrderInvoice() throws Exception {
		signup.userSignin(prop.getProperty("order_user"), prop.getProperty("password"));
		orderDetails.navigateToOrderDetails();
		orderDetails.downloadInvoice();
		orderDetails.verifyDownloadedInvoiceName();
	}
	
	@AfterClass
	public void closeBrowser() {
		tearDown();
	}

}
