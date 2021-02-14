package Utility;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import Base.Base;

public class SeleniumDriverUtility extends Base {

	@Override
	public void initiate_testing(String test_name) {
		test = extent.startTest(test_name);
	}

	public void startReporting() {
		if (extent == null) {
			String html_report = System.getProperty("user.dir") + "/Reports/InspiredTesting-Assessment.html";
			extent = new ExtentReports(html_report, true);
			extent.addSystemInfo("Environment", "Inspired-Testing").addSystemInfo("Ran by", "Luxolo Dyobiso");
		}
	}

	@Override
	public void endTesting() {
		extent.endTest(test);
		if (browser_started) {
			driver.quit();
			browser_started = false;
			navigated = false;
		}
	}

	public void shutDown() {
		extent.flush();
	}

	@Override
	public boolean start_browser(String browser_name) {

		try {
			if (System.getProperty("os.name").contains("Windows")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver.exe");
			} else {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
			}
			if (browser_name.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			} else if (browser_name.equalsIgnoreCase("firefox")) {
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				driver = new FirefoxDriver(capabilities);
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			}
			browser_started = true;
			return true;
		} catch (Exception e) {
			System.out.println("ERROR Failed to start '" + browser_name + "' browser - " + e);
			return false;
		}

	}

	public boolean navigate(String url) {
		try {
			driver.get(url);
			navigated = true;
			Thread.sleep(3000);
			driver.manage().window().maximize();
			return true;
		} catch (Exception e) {
			System.out.println("ERROR - Failed to navigate to '" + url + "' - " + e);
			return false;
		}
	}

	public boolean waitForElement(By element, Integer timeout) {
		boolean elementFound = false;
		try {
			int waitCount = 0;
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			while (!elementFound && waitCount < timeout) {
				try {
					WebDriverWait wait = new WebDriverWait(driver, 1);
					if (wait.until(ExpectedConditions.presenceOfElementLocated(element)) != null) {
						elementFound = true;
						break;
					}
				} catch (Exception e) {
					elementFound = false;
				}
				waitCount++;
			}
		} catch (Exception e) {
			elementFound = false;
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return elementFound;
	}

	public boolean userClick(By element) {
		try {
			waitForElement(element, 15);
			driver.findElement(element).click();
			return true;
		} catch (Exception e) {
			System.out.println("ERROR - failed to click '" + element + "'- " + e);
			return false;
		}
	}

	public void implicitly_wait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	
	public boolean enterText(By element, String text) {
		try {
			waitForElement(element, 15);
			//driver.findElement(element).clear();
			driver.findElement(element).sendKeys(text);
			return true;
		} catch (Exception e) {
			System.out.println("ERROR - failed to enter '" + text + "' in '" + element + "' textbox - " + e);
			return false;
		}
	}

	public boolean selectFromDopdownlistUsingValue(By element, String valueToSelect) {
		try {
			waitForElement(element, 15);
			Select dropDownList = new Select(driver.findElement(element));
			dropDownList.selectByVisibleText(valueToSelect);
			return true;
		} catch (Exception e) {
			System.out
					.println("[ERROR] Failed to select '" + valueToSelect + "' from dropdown '" + element + "'- " + e);
			return false;
		}
	}
	
	public boolean selectFromDopdownlistUsingIndex(By element, int index) {
		try {
			waitForElement(element, 15);
			Select dropDownList = new Select(driver.findElement(element));
			dropDownList.selectByIndex(index);
			return true;
		} catch (Exception e) {
			System.out
					.println("[ERROR] Failed to select value from dropdown '" + element + "'- " + e);
			return false;
		}
	}

	public List<WebElement> getDropDownOptions(By element) {
		try {
			waitForElement(element, 15);
			Select dropDownList = new Select(driver.findElement(element));
			//dropDownList.selectByIndex(index);
			return dropDownList.getOptions();
		} catch (Exception e) {
			System.out.println("[ERROR] Failed to get dropdown options " + e);
			return null;
		}
	}
	
	
	public String getElementText(By element) {
		try {
			String str = null;
			WebElement webText = driver.findElement(element);
			str = webText.getText();
			return str;
		} catch (Exception e) {
			System.out.println("ERROR - Failed to get text from element : '" + element + "'- " + e);
			return null;
		}
	}

	public boolean validateEquals(String expected, String actual) {
		try {
			assertEquals(expected, actual);
			test.log(LogStatus.PASS, "The expected message : '" + expected
					+ "' is the same as the message on the page :'" + actual + "'");
			return true;
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "The expected message : '" + expected
					+ "' is not the same as the message on page : '" + actual + "' ERROR - " + e);
			extent.endTest(test);
			return false;
		}
	}
	
	public static String capture(String screenShotName) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\Screenshots\\"+screenShotName+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);        
                     
        return dest;
    }

}
