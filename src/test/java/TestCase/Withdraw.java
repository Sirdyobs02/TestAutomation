package TestCase;

import java.io.IOException;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import PageProp.Welcome;
import Utility.SeleniumDriverUtility;

public class Withdraw extends SeleniumDriverUtility{

	
	Welcome welcome_page = new Welcome();
	
	public void test_withdraw(String account_number, String amount) throws IOException {
		
		if (!userClick(By.xpath(welcome_page.button_withdrawl_xpath()))) {
			test.log(LogStatus.FAIL, "Failed to click button withdraw");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("click_button_withdraw")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully clicked button withdraw");

		if (!selectFromDopdownlistUsingValue(By.name(welcome_page.select_account_name()), account_number)) {
			test.log(LogStatus.FAIL, "Failed to select account number from dropdown");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("select_account_number")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully clicked selected account number from dropdown");
		
		Double current_balance = Double.parseDouble(getElementText(By.xpath(welcome_page.balance_field_xpath())));
		test.log(LogStatus.INFO, "Balance before withdrawl is: "+ current_balance);
		if (!enterText(By.xpath(welcome_page.input_field_deposit_xpath()), amount)) {
			test.log(LogStatus.FAIL, "Failed to enter amount to withdraw");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("enter_withdraw_amount")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully enterd amount to withdraw");
		
		if (!userClick(By.xpath(welcome_page.button_complete_withdraw_xpath()))) {
			test.log(LogStatus.FAIL, "Failed to click withdraw button");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("click_withdraw_button")));
			return;
		}
		test.log(LogStatus.PASS, "Successfully clicked withdraw button");
		
		Double balance_after_withdrawl = Double.parseDouble(getElementText(By.xpath(welcome_page.balance_field_xpath())));
		test.log(LogStatus.INFO, "Balance after withdrawl is: "+ balance_after_withdrawl);
		
		if (!getElementText(By.xpath(welcome_page.success_text_xpath())).equals("Deposit Successful") & current_balance+Double.parseDouble(amount) == balance_after_withdrawl) {
			test.log(LogStatus.FAIL, "Failed make withdrawl: expected text is not equal to text retrieved");
			test.log(LogStatus.FAIL, test.addScreenCapture(capture("value_mismatch")));
			return;
		} 
		test.log(LogStatus.PASS, "Successfully made withdrawl");
	}
	
	
	
}
