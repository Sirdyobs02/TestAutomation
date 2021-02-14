package TestCase;

import java.io.IOException;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import PageProp.Home;
import PageProp.LoginPage;
import Utility.SeleniumDriverUtility;

public class Login extends SeleniumDriverUtility {

	Home home_page = new Home();
	LoginPage login_page = new LoginPage();

	public void test_login(String user) throws IOException {
		/*if (browser_started == false) {
			if (!start_browser(prop.getProperty("browser"))) {
				test.log(LogStatus.FAIL, "Failed to start " + prop.getProperty("browser") + " browser");
				return;
			}
			test.log(LogStatus.PASS, "Successfully started the broswer");*/

			//if (navigated == false) {
				if (!navigate(prop.getProperty("banking_url"))) {
					test.log(LogStatus.FAIL, "Failed to navigate to url: " + prop.getProperty("banking_url"));
					test.log(LogStatus.FAIL, test.addScreenCapture(capture("failed_to_navigate_to_url:"+prop.getProperty("banking_url"))));
					return;
				}
				test.log(LogStatus.PASS, "Successfully navigated to the URL");
			//}
			if (!userClick(By.xpath(home_page.entity_login_button_xpath("Customer Login")))) {
				test.log(LogStatus.FAIL, "Failed to click Customer Login button");
				test.addScreenCapture(capture("failed_to_click_login_button"));
				return;
			}
			test.log(LogStatus.PASS, "Successfully clicked customer login button");

			if (!selectFromDopdownlistUsingValue(By.name(login_page.dropdown_username_name()), user)) {
				test.log(LogStatus.FAIL, "Failed to select value from the dropdown");
				test.addScreenCapture(capture("failed_to_select_user_from_dropdown"));
				return;
			}
			test.log(LogStatus.PASS, "Successfully select value from the dropdown");

			if (!userClick(By.xpath(login_page.button_login_xpath()))) {
				test.log(LogStatus.FAIL, "Failed to click button login");
				test.addScreenCapture(capture("failed_to_click_login_button"));
				return;
			}
			test.log(LogStatus.PASS, "Successfully clicked login button");

		}

	
}
