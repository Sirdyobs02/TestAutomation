package Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import PageProp.Welcome;
import TestCase.Deposit;
import TestCase.Login;
import TestCase.Transaction;
import TestCase.Withdraw;
import Utility.GeneralUtility;
import Utility.SeleniumDriverUtility;

public class TaskII extends SeleniumDriverUtility {
	Login login = new Login();
	Deposit deposit = new Deposit();
	Withdraw withdraw = new Withdraw();
	Transaction trans = new Transaction();
	GeneralUtility gu = new GeneralUtility();
	Welcome welcomepage = new Welcome();

	@BeforeClass
	public void setUpClass() throws Exception {
		startReporting();
	}

	@BeforeTest
	public void setUpTest() throws Exception {
		start_browser(prop.getProperty("browser"));
	}

	@AfterTest
	public void tearDownTest() throws Exception {
		endTesting();
	}
	
	@AfterClass
	public void tearDownClass() throws Exception {
		shutDown();
		
	}
	
	
	@Test
	public void Test1() throws IOException {
		initiate_testing("WEB: Test 1: - Login, Deposit:1500, Validate");
		login.test_login("Hermoine Granger");
		deposit.test_deposit("1003", "1500");
	}

	@Test(priority = 2)
	public void Test2() throws IOException {
		initiate_testing("WEB: Test 2: Login, Deposit on all accounts, Validate");
		login.test_login("Hermoine Granger");
		List<WebElement> account_numbers = getDropDownOptions(By.name(welcomepage.select_account_name()));
		for (WebElement value : account_numbers) {
			deposit.test_deposit(value.getText(), "1500");
		}
	}

	@DataProvider
	public Object getTestData() {
		Object data[][] = gu.getTestData("BankingActivity.xlsx");
		return data;
	}

	@Test(priority = 3, dataProvider = "getTestData")
	public void Test3(String activity,String user, String account_number, String amount) throws InterruptedException, IOException {
		initiate_testing("WEB: Test 3 - Login,"+ activity+"Check Transaction");
		login.test_login(user);
		switch (activity) {
			case "Deposit":
				deposit.test_deposit(account_number, amount);
				trans.validate_transaction_details(gu.get_current_datetime(), amount, "Credit");
				break;
			case "Withdraw":
				withdraw.test_withdraw(account_number, amount);
				trans.validate_transaction_details(gu.get_current_datetime(), amount, "Debit");	
				break;
		}
	}
	
	@DataProvider
	public Object getJSonTestData() {
		Object data[][] = gu.getJSonData("banking.json");
		return data;
	}
	
	@Test(priority = 4, dataProvider = "getJSonTestData")
	public void Test4(Object key, Map<String,String> data) throws InterruptedException, IOException {
		initiate_testing("WEB: Test 4 - Login,"+ key+"Check Transaction");
		login.test_login(data.get("CustomerName").toString());
		switch (key.toString()) {
			case "Deposit":
				deposit.test_deposit(data.get("AccountNumber").toString(),data.get("Amount").toString());
				trans.validate_transaction_details(gu.get_current_datetime(),data.get("Amount").toString(), "Credit");
				break;
			case "Withdraw":
				withdraw.test_withdraw(data.get("AccountNumber").toString(),data.get("Amount").toString());
				trans.validate_transaction_details(gu.get_current_datetime(),data.get("Amount").toString(), "Debit");	
				break;
		}
	}
	

}
