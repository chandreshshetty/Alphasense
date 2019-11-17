package hometest.pages;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import hometest.utilities.TestUtils;

public class OrderDetailsPage extends TestUtils{
	
	Logger logger = Logger.getLogger(Log.class.getName());
	
	public OrderDetailsPage() throws Exception {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[contains(text(),'Order history and details')]")
	WebElement orderHistoryAndDetails;
	
	@FindBy(xpath="//tr[contains(@class,'first_item')]//td[@class='history_invoice']")
	WebElement invoiceDownload;
	
	@FindBy(xpath="//tr[contains(@class,'first_item')]//td[@class='history_invoiceoo']")
	WebElement invoiceDownloadFail;
	
	/*
	 * Method used to navigate to order details page
	 */
	public void navigateToOrderDetails() {
		logger.info("Waiting for order history page to load");
		waitForElementToDisplay(orderHistoryAndDetails);
		orderHistoryAndDetails.click();
		logger.info("Successfully clicked on order history button");

	}
	
	/*
	 * Method used to download first visible order invoice 
	 */
	public void downloadInvoice() {
		logger.info("Waiting for invoice to diplay in order history page");
		waitForElementToDisplay(invoiceDownload);
		invoiceDownload.click();
		logger.info("Successfully clicked on invoice in order history page");
	}
	
	/*
	 * Method added to fail test case
	 */
	public void downloadInvoiceFail() {
		logger.info("Waiting for invoice to diplay in order history page");
		waitForElementToDisplay(invoiceDownloadFail);
		invoiceDownloadFail.click();
		logger.info("Successfully clicked on invoice in order history page");
	}
	
	/*
	 * Method used to verify downloaded invoice in downloadedInvoice in the workspace
	 */
	public void verifyDownloadedInvoiceName() throws InterruptedException {
		//paused for 5 seconds as downloading takes time in somecases
		Thread.sleep(5000);
		//added custom folder in the workspace to store downloaded the invoice
		File folder = new File(System.getProperty("user.dir")+"/downloadedInvoice");
		 
		//List the files on that folder
		File[] listOfFiles = folder.listFiles();
		boolean found = false;
		File f = null;
		     //Looks for the file in the downloadedInvoice folder
		     for (File listOfFile : listOfFiles) {
		         if (listOfFile.isFile()) {
		              String fileName = listOfFile.getName();
		               System.out.println("File " + listOfFile.getName());
		     // used hard code file name as we are not able to find Invoice name in the UI for validating after download
		               if (fileName.matches("IN138526.pdf")) {
		                   f = new File(fileName);
		                   found = true;
		                }
		            }
		        }
		Assert.assertTrue(found, "Downloaded document is not found");
		f.deleteOnExit();
	}

}
