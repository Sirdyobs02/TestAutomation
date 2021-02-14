package Test;

import java.io.UnsupportedEncodingException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import Model.CreateJsonFromClasses;
import PageProp.UserAPI;
import TestCase.APIUsers;
import Utility.SeleniumDriverUtility;

public class TaskI extends SeleniumDriverUtility{
	
	@BeforeTest
	public void start_test() throws Exception {
		startReporting();
	}

	@AfterTest
	public void tearDownClass() throws Exception {
		shutDown();
		endTesting();
	}
	
	APIUsers test_api = new APIUsers();
	UserAPI api_prop = new UserAPI();
	
	@Test(priority = 1)
	public void Test1() throws JsonProcessingException, UnsupportedEncodingException {
		initiate_testing("API: Test 1 - Post a new user");
		CreateJsonFromClasses pay_load = new CreateJsonFromClasses();
		String user_payload = pay_load.return_json_object("Luxolo", "Lux", "lux@email.com", "12.3652", "71.5698",
				"10 Avenue", "Apt.001", "CT", "8001", "0743256789", "www.g4t.com");
		test_api.Test1(user_payload);
	}
	
	@Test(priority = 2)
	public void Test2() throws JsonProcessingException, UnsupportedEncodingException {
		initiate_testing("API: Test 2 - return a single user");
		test_api.Test2();
	}
	
	@Test(priority = 3)
	public void Test3() throws JsonProcessingException, UnsupportedEncodingException {
		initiate_testing("API: Test 3 - return 10 users");
		test_api.Test3();
	}
}
