package TestCase;

import java.io.IOException;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import PageProp.TransactionProp;
import Utility.SeleniumDriverUtility;

public class Transaction extends SeleniumDriverUtility {

	TransactionProp trans_prop = new TransactionProp();

	public void validate_transaction_details(String datetime, String amount, String trans_type) throws InterruptedException, IOException {
		Thread.sleep(10000);
		if (!userClick(By.xpath(trans_prop.button_transaction_xpath()))){
			test.log(LogStatus.FAIL, "Failed to click button transaction");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("failed_to_click_transaction_button")));
			return;
		}
		test.log(LogStatus.PASS, "Button transaction clicked successfully");
		
		if (!enterText(By.name(trans_prop.start_date_name()), datetime.replaceAll("[^0-9]", ""))) {
			test.log(LogStatus.FAIL, "Failed to enter start time");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("failed_to_enter_startdate")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully entered start time");
		
		if (!waitForElement(By.xpath(trans_prop.new_transaction_xpath(datetime,amount,trans_type)), 15)) {
			test.log(LogStatus.FAIL, "Transaction does not appear");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("search_did_not_return_results")));
			return;
		}
		test.log(LogStatus.PASS, "Transaction appears");
		
		if (!userClick(By.xpath(trans_prop.button_back_xpath()))){
			test.log(LogStatus.FAIL, "Failed to click button back");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("failed_to_click_back_button")));
			return;
		}
		test.log(LogStatus.PASS, "Button back clicked successfully");	
	}

}
