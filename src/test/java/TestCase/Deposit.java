package TestCase;

import java.io.IOException;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import PageProp.TransactionProp;
import PageProp.Welcome;
import Utility.SeleniumDriverUtility;

public class Deposit extends SeleniumDriverUtility{

	
	Welcome welcome_page = new Welcome();
	TransactionProp trans_prop = new TransactionProp();
	
	
	public void test_deposit(String account_number, String amount_to_deposit) throws IOException {
		
		if (!userClick(By.xpath(welcome_page.button_deposit_xpath()))) {
			test.log(LogStatus.FAIL, "Failed to click button deposit");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("click button deposit")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully clicked button deposit");

		if (!selectFromDopdownlistUsingValue(By.name(welcome_page.select_account_name()), account_number)) {
			test.log(LogStatus.FAIL, "Failed to select account number from dropdown");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("account_number")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully clicked selected account number from dropdown");
		
		Double current_balance = Double.parseDouble(getElementText(By.xpath(welcome_page.balance_field_xpath())));
		test.log(LogStatus.INFO, "Balance before deposit is: "+ current_balance);
		if (!enterText(By.xpath(welcome_page.input_field_deposit_xpath()), amount_to_deposit)) {
			test.log(LogStatus.FAIL, "Failed to enter amout to deposit");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("deposit")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully enterd amount to deposit");
		
		if (!userClick(By.xpath(welcome_page.button_complete_deposit_xpath()))) {
			test.log(LogStatus.FAIL, "Failed to click deposit button");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("btn_complete")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully clicked deposit button");

		Double balance_after_deposit = Double.parseDouble(getElementText(By.xpath(welcome_page.balance_field_xpath())));		
		test.log(LogStatus.INFO, "Balance after deposit is: "+ balance_after_deposit);
		if (!getElementText(By.xpath(welcome_page.success_text_xpath())).equals("Deposit Successful") & current_balance+Double.parseDouble(amount_to_deposit) == balance_after_deposit) {
			test.log(LogStatus.FAIL, "Failed make deposit: expected text is equal to text retrieved");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("validate_deposit")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully made deposit");
			
	}
	
	
	
}
