package hometest.pages;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import hometest.utilities.TestUtils;

public class SignUpPage extends TestUtils {
	Logger logger = Logger.getLogger(Log.class.getName());
	
	public SignUpPage() throws Exception {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='login']")
	WebElement signInBtn;

	@FindBy(id = "email_create")
	WebElement createAccountEmail;

	@FindBy(id = "email")
	WebElement signInEmail;

	@FindBy(id = "SubmitCreate")
	WebElement createAccountBtn;

	@FindBy(id = "customer_firstname")
	WebElement firstName;

	@FindBy(id = "customer_lastname")
	WebElement lastName;

	@FindBy(id = "passwd")
	WebElement password;

	@FindBy(id = "firstname")
	WebElement addressFastName;

	@FindBy(id = "lastname")
	WebElement addressLastName;

	@FindBy(id = "address1")
	WebElement address;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "id_state")
	WebElement state;

	@FindBy(id = "postcode")
	WebElement postCode;

	@FindBy(id = "phone_mobile")
	WebElement mobileNumber;

	@FindBy(id = "alias")
	WebElement aliasAddress;

	@FindBy(id = "submitAccount")
	WebElement registerBtn;

	@FindBy(id = "SubmitLogin")
	WebElement signInSubmitBtn;

	@FindBy(xpath = "//a[@title='View my customer account']/span")
	WebElement userNameInfo;

	@FindBy(xpath = "//a[@class='logout']")
	WebElement userSignOut;
	
	/*
	 * Method used to Register new account
	 * @param user email address
	 */
	public void createAccount(String email) {
		try {
			logger.debug("waiting for SignIn button to display");
			waitForElementToDisplay(signInBtn);
			logger.debug("Clicking on SignIn button");
			signInBtn.click();
			logger.info("Clicked on SignIn Button");
			logger.debug("waiting for Create Account page to load");
			waitForElementToDisplay(createAccountEmail);
			logger.info("Create Account page loaded");
			createAccountEmail.sendKeys(email);
			logger.info("entered email id "+email+" in the CreateAccount Page");
			createAccountBtn.click();
			logger.info("Clicked on Create Account Button");
		} catch(NoSuchElementException e) {
			logger.error("Specified element not diplayed", e);
		} catch(Exception e) {
			logger.error("exception", e);
		}
	}
	
	/*
	 * Method used to enter personal Info in the registration page
	 * @param username and password of user
	 * @return MyAccount page
	 */
	public MyAccountPage enterPersonalInfoAndRegister(String name, String pwd) throws Exception {
		firstName.sendKeys(name);
		logger.info("Entered FirstName in registration page");
		lastName.sendKeys(name);
		logger.info("Entered lastname in registration page");
		password.sendKeys(pwd);
		logger.info("Entered password in registration page");
		addressFastName.sendKeys("South");
		logger.info("Entered address firstName in registration page");
		addressLastName.sendKeys("Gate");
		logger.info("Entered address lastname in registration page");
		address.sendKeys("Greenfield");
		logger.info("Entered address in registration page");
		city.sendKeys("Gilbert");
		logger.info("Entered city in registration page");
		selectTextFromDropDown(state, "Alabama");
		logger.info("Selected Alabama state from the dropdown list in registration page");
		postCode.sendKeys("85297");
		logger.info("Entered postalcode in registration page");
		mobileNumber.sendKeys("9876543210");
		logger.info("Entered mobile number in registration page");
		aliasAddress.sendKeys("Greenfield");
		logger.info("Entered alias address in registration page");
		registerBtn.click();
		logger.info("Clicked on register button in the registration page");
		return new MyAccountPage();
	}
	
	/*
	 * Method used to get username displayed in the my account page
	 * @return username
	 */
	public String verifyUserNaviagtaionToMyAccount() {
		logger.debug("Waiting for username to be display in the my account page");
		waitForElementToDisplay(userNameInfo);
		return userNameInfo.getText();
	}
	
	/*
	 * Method used to SignIn
	 * @param username and password
	 * @return MyAccount page
	 */
	public MyAccountPage userSignin(String email, String pwd) throws Exception {
		logger.debug("Waiting for SignIn button to be display in the signIn page");
		waitForElementToDisplay(signInBtn);
		signInBtn.click();
		logger.info("Clicked on SignIn button in the signIn page");
		logger.debug("Entering username and password");
		signInEmail.sendKeys(email);
		password.sendKeys(pwd);
		signInSubmitBtn.click();
		logger.info("Clicked on Submit SignIn button in the signIn page");
		return new MyAccountPage();
	}

}
