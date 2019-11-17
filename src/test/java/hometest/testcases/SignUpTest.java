package hometest.testcases;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import hometest.pages.MyAccountPage;
import hometest.pages.SignUpPage;
import hometest.utilities.TestUtils;

@Listeners(hometest.utilities.TestUtils.class)
public class SignUpTest extends TestUtils {
	Logger logger = Logger.getLogger(Log.class.getName());
	SignUpPage signup;
	MyAccountPage myAccount;

	public SignUpTest() throws Exception {
		super();
	}

	@BeforeClass
	public void setUp() throws Exception {
		initialization();
		signup = new SignUpPage();
		myAccount = new MyAccountPage();
	}

	/****************************************************************************************************
	* TEST CASE DESCRIPTION - Create a new user account and make sure the user can login successfully
							  after the account creation
	* TEST STEPS -  1) Open the Website
	 				2) Click on "SignIn" button
	 				3) Enter email address in the Create Account page and Click on "Create Account" button
	 				4) Enter all mandatory fields in the registration page as per requirement
	 				5) Click on "register" button
	 				6) Verify user navigated to My Account page after registration and then click on SignOut button
	 				7) SignIn with email and Password used in the registration and click on signIn button
	 				8) Verify user able to login with registered user
	****************************************************************************************************/
	@Test(description = "verify login by creating new account", groups = { "SignUpTestCase.signIn" })
	public void createAccountTest() throws Exception {
		String name = randomText(4);
		String email = name + "@automation.com";
		String pwd = randomText(6) + "123";
		signup.createAccount(email);
		signup.enterPersonalInfoAndRegister(name, pwd);
		Assert.assertEquals(name + " " + name, signup.verifyUserNaviagtaionToMyAccount(),
				"User Name not displyed in the header after Registration");
		signup = myAccount.userSignOut();
		signup.userSignin(email, pwd);
		Assert.assertEquals(name + " " + name, signup.verifyUserNaviagtaionToMyAccount(),
				"User Name not displyed in the header after Registration");
	}

	@AfterClass
	public void closeBrowser() {
		tearDown();
	}

}
