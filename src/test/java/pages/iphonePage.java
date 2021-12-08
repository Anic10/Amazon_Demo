package pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.utility;

public class iphonePage {
	WebDriver driver;
	utility utils;
	JavascriptExecutor jse;

	@FindBy(how = How.XPATH, using = "//input[@id = 'twotabsearchtextbox']")
	WebElement searchTextBox;

	@FindBy(how = How.XPATH, using = "//input[@value= 'Go']")
	WebElement goButton;

	@FindBy(how = How.XPATH, using = "//li[@aria-label= 'iOS']/span/a/div/label/input/following-sibling::i")
	WebElement iosCheckbox;

	@FindBy(how = How.XPATH, using = "//span[text() = 'Cell Phone Operating System']")
	WebElement cposLabel;

	@FindBy(how = How.XPATH, using = "//li/a[text() = 'Price: High to Low']")
	WebElement highoLow;

	@FindBy(how = How.XPATH, using = "//span[text() = 'Sort by:']")
	WebElement sortByDropDown;

	@FindBy(how = How.XPATH, using = "//span[@class = 'a-price']/span[@class = 'a-offscreen']")
	WebElement price;

	public iphonePage(WebDriver driver) {
		this.driver = driver;
		jse = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method searches for the product
	 * 
	 * @param name : Product name to be searched
	 */
	public void searchProduct(String name) {
		searchTextBox.sendKeys(name);
	}

	/**
	 * This method verifies the Title
	 * 
	 * @throws Exception
	 */
	public void verifyTitle() throws Exception {
		if (driver.getTitle().equals(utility.getPropertyValue("Title"))) {
			jse.executeScript(
					"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
		} else {
			jse.executeScript(
					"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
		}
	}

	/**
	 * This method clicks the Go button
	 */
	public void goclick() {
		goButton.click();
	}

	/**
	 * This method hels to wait for few seconds
	 * @throws InterruptedException
	 */
	public void waitforfew() throws InterruptedException {
		Thread.sleep(3000);
	}

	/**
	 * This method selects High to Low filter
	 * @throws InterruptedException
	 */
	public void selectSort() throws InterruptedException {
		sortByDropDown.click();
		waitforfew();
		highoLow.click();
	}

	/**
	 * This method select ios checkbox
	 */
	public void selectCheckbox() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cposLabel);
		iosCheckbox.click();
	}

	/**
	 * This method verifies that if element is present or not
	 * @param by
	 * @return : true/false
	 */
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * This method display the result on console
	 * @throws Exception
	 */
	public void displayResult() throws Exception {
		List<WebElement> links = driver.findElements(By.xpath("//h2/a"));

		for (int i = 0; i < links.size(); i++) {
			int j = i + 1;
			boolean val = isElementPresent(By.xpath("//div[" + j
					+ "]/div/span/div/div/div[2]/div[2]/div/div/div[3]/div[1]/div/div[1]/div/a/span[1]/span[1]"));
			System.out.println("Product name is : " + links.get(i).getText());
			System.out.println("Link for product is : " + links.get(i).getAttribute("href"));
			if (val) {
				WebElement ele = driver.findElement(By.xpath("//div[" + j
						+ "]/div/span/div/div/div[2]/div[2]/div/div/div[3]/div[1]/div/div[1]/div/a/span[1]/span[1]"));
				System.out.println("Price of the product is : " + ele.getAttribute("innerText"));
				System.out.println();
			} else {
				System.out.println("Price of the product is not displayed");
				System.out.println();
			}
		}
	}
}
