package testcases;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pages.iphonePage;
import utils.utility;

public class testcases {
	iphonePage iphone;
	utility utils;
	WebDriver driver;
	DesiredCapabilities caps = new DesiredCapabilities();
	public static final String USERNAME = "aniket_XuJIvP";
	public static final String AUTOMATE_KEY = "z6KyPt67pZpAuiaV4pia";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@Parameters("browsername")
	@BeforeTest
	public void setup(String browsername) throws Exception {
		if(browsername.equalsIgnoreCase("Safari")) {
			caps.setCapability("os", "OS X");
			caps.setCapability("os_version", "Big Sur");
			caps.setCapability("browser", "Safari");
			caps.setCapability("browser_version", "14.1");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.selenium_version", "3.14.0");
			caps.setCapability("browserstack.console", "info");
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("project", "Amazon_Product_Search");
			caps.setCapability("build", "Build_3");
			caps.setCapability("name", "Search_Product");
			caps.setCapability("browserstack.timezone" ,"Kolkata");
		    driver = new RemoteWebDriver(new java.net.URL(URL), caps);

		} else if(browsername.equalsIgnoreCase("Chrome")) {
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "94.0");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.selenium_version", "3.14.0");
			caps.setCapability("browserstack.console", "info");
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("project", "Amazon_Product_Search");
			caps.setCapability("build", "Build_3");
			caps.setCapability("name", "Search_Product");
			caps.setCapability("browserstack.timezone" ,"Kolkata");
			driver = new RemoteWebDriver(new java.net.URL(URL), caps);			
		} else {
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", "Firefox");
			caps.setCapability("browser_version", "92.0");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("browserstack.selenium_version", "3.10.0");
			caps.setCapability("browserstack.console", "info");
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("project", "Amazon_Product_Search");
			caps.setCapability("build", "Build_3");
			caps.setCapability("name", "Search_Product");
			caps.setCapability("browserstack.timezone" ,"Kolkata");
			driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(utility.getPropertyValue("baseurl"));
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_001() throws Exception {
		iphone = new iphonePage(driver);
		iphone.verifyTitle();

		iphone.searchProduct(utility.getPropertyValue("productName"));
		iphone.waitforfew();
		iphone.goclick();
		iphone.waitforfew();
		iphone.selectSort();
		iphone.waitforfew();
		iphone.selectCheckbox();
		iphone.waitforfew();
		iphone.displayResult();
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}

}
