package Base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public abstract class Base {

	protected static WebDriver driver;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	protected static boolean browser_started = false;
	protected static boolean navigated = false;
	protected static Properties prop;
	protected BufferedReader rd;
	protected String api_response = null;
	protected String return_parameter = null;
	protected org.testng.ITestResult result;
	public Base() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\Config\\config");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	
	
	public void initiate_testing(String test_name) {}

	public void endTesting() {}

	public boolean start_browser(String browser_name) {
		return false;
	}
	
	
	
	
}
