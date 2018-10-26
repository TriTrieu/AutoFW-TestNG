package com.bosch.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import com.bosch.models.BrowserModel;
import com.bosch.models.ResultModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WebActions {
	protected static final int TIMEOUT = 10;
	private WebDriver driver;

	public WebActions(WebDriver driver) {
		super();
		this.driver = driver;
	}

	protected ResultModel clickOnWebElement(final WebElement webelement, final String elementName) {
		ResultModel res = new ResultModel();
		try {
			webelement.click();
			hardDelay(2);
			res.setMessage(String.format("Successfully clicked on the WebElement %s", elementName));
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(String.format("Unable to click on the WebElement %s. An error occurred: %s", elementName,
					e.getMessage()));
		}
		return res;
	}

	protected ResultModel clickOnWebElement(final List<WebElement> webelements, final String elementName) {
		ResultModel res = new ResultModel();
		try {
			webelements.get(1).click();
			res.setMessage(String.format("Successfully clicked on the WebElement %s", elementName));
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(String.format("Unable to click on the WebElement %s. ERROR: ", elementName, e.getMessage()));
		}
		return res;
	}

	protected ResultModel doubleClickWebElement(final WebElement webElement, final String elementName) {
		ResultModel res = new ResultModel();
		try {
			Actions actions = new Actions(driver);
			actions.doubleClick(webElement).build().perform();
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(
					String.format("Unable to double click on the WebElement %s. Error", elementName, e.getMessage()));
		}
		return res;
	}

	public void enterWebElement() {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ENTER).build().perform();
		} catch (Exception e) {

		}

		// ResultModel res = new ResultModel();
		// try {
		// element.sendKeys(Keys.ENTER);
		// res.setMessage(String.format("Successfully enter on the WebElement
		// %s", elementName));
		// } catch (Exception e) {
		// res.setResult(false);
		// res.setMessage(String.format("Unable to enter on the WebElement %s.
		// ERROR: ", elementName, e.getMessage()));
		// }
		// return res;
	}

	protected ResultModel sendInputdata(final WebElement element, final String elementName, final String inputValue) {
		ResultModel res = new ResultModel();
		try {
			element.clear();
			element.sendKeys(inputValue);
			element.sendKeys(Keys.ENTER);
			if (elementName.toUpperCase().contains("PASSWORD")) {
				res.setMessage(String.format("Successfully entered the text [Password]  into %s.", elementName));
			} else {
				res.setMessage(String.format("Successfully entered the text [%s] into %s.", inputValue, elementName));
			}
		} catch (Exception e) {
			res.setResult(false);
			if (elementName.toUpperCase().contains("PASSWORD")) {
				res.setMessage(String.format("Unable to enter the text [Password] into %s. An error occurred: %s",
						elementName, e.getMessage()));
			} else {
				res.setMessage(String.format("Unable to enter the text [%s] into %s. An error occurred: %s", inputValue,
						elementName, e.getMessage()));

			}
		}
		return res;
	}

	protected ResultModel sendKeys(final WebElement element, final String elementName, final String inputValue) {
		ResultModel res = new ResultModel();
		try {
			element.clear();
			element.sendKeys(inputValue);
			element.sendKeys(Keys.TAB);
			if (elementName.toUpperCase().contains("PASSWORD")) {
				res.setMessage(String.format("Successfully entered the text [Password]  into %s.", elementName));
			} else {
				res.setMessage(String.format("Successfully entered the text [%s] into %s.", inputValue, elementName));
			}
		} catch (Exception e) {
			res.setResult(false);
			if (elementName.toUpperCase().contains("PASSWORD")) {
				res.setMessage(String.format("Unable to enter the text [Password] into %s. An error occurred: %s",
						elementName, e.getMessage()));
			} else {
				res.setMessage(String.format("Unable to enter the text [%s] into %s. An error occurred: %s", inputValue,
						elementName, e.getMessage()));

			}
		}
		return res;
	}

	protected ResultModel sendKeys(final List<WebElement> element, final String elementName, final String inputValue) {
		ResultModel res = new ResultModel();
		try {
			element.clear();

			element.get(1).sendKeys(inputValue);
			element.get(1).sendKeys(Keys.TAB);
			if (elementName.toUpperCase().contains("PASSWORD")) {
				res.setMessage(String.format("Successfully entered the text [Password]  into %s.", elementName));
			} else {
				res.setMessage(String.format("Successfully entered the text [%s] into %s.", inputValue, elementName));
			}
		} catch (Exception e) {
			res.setResult(false);
			if (elementName.toUpperCase().contains("PASSWORD")) {
				res.setMessage(String.format("Unable to enter the text [Password] into %s.", elementName));
			} else {
				res.setMessage(String.format("Unable to enter the text [%s] into %s.", inputValue, elementName));

			}
		}
		return res;
	}

	protected ResultModel selectCheckbox(WebElement element, String elementName) {
		ResultModel res = new ResultModel();
		try {
			if (element.isSelected()) {
				res.setMessage(String.format("%s is already selected.", elementName));
				return res;
			} else {
				element.click();
				res.setMessage(String.format("%s is selected successfully.", elementName));
				return res;
			}

		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(String.format("Failed to select %s. An error occurred: %s", elementName, e.getMessage()));
			return res;
		}
	}

	protected ResultModel unselectCheckbox(WebElement element, String elementName) {
		ResultModel res = new ResultModel();
		try {
			if (!element.isSelected()) {
				res.setMessage(String.format("%s is already unselected.", elementName));
				return res;
			} else {
				element.click();
				res.setMessage(String.format("%s is unselected successfully.", elementName));
				return res;
			}

		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(String.format("Failed to unselect %s. An error occurred: %s", elementName, e.getMessage()));
			return res;
		}
	}

	protected ResultModel selectDropdownByValue(final WebElement element, final String elementName,
			final String inputValue) {
		ResultModel res = new ResultModel();
		try {
			Select select = new Select(element);
			select.selectByValue(inputValue);
			res.setMessage(String.format("Selected item [%s] in the %s successfully", inputValue, elementName));
			return res;
		} catch (Exception ex) {
			res.setResult(false);
			res.setMessage(String.format("Failed to select item [%s] in the %s successfully. An error occurred: %s",
					inputValue, elementName, ex.getMessage()));
			return res;
		}
	}

	protected ResultModel selectDropdownByText(final WebElement element, final String elementName, final String text) {
		ResultModel res = new ResultModel();
		try {
			Select select = new Select(element);
			select.selectByVisibleText(text);
			res.setMessage(String.format("Selected item [%s] in the %s successfully", text, elementName));
			return res;
		} catch (Exception ex) {
			res.setResult(false);
			res.setMessage(String.format("Failed to select item [%s] in the %s successfully. An error occurred: %s",
					text, elementName, ex.getMessage()));
			return res;
		}
	}

	protected ResultModel verifyBrowserURL(String expectedBrowserURL) {
		ResultModel res = new ResultModel();
		try {
			String actualBrowserUrl = driver.getCurrentUrl();
			if (actualBrowserUrl.toLowerCase().contains(expectedBrowserURL.toLowerCase())
					|| actualBrowserUrl.equalsIgnoreCase(expectedBrowserURL)) {
				res.setMessage(String.format("Browser URL displays as expected - [%s]", expectedBrowserURL));
				return res;
			} else {
				res.setResult(false);
				res.setMessage(String.format("Browser URL is mismatching. Expected is: [%s]. Actual is: [%s]",
						expectedBrowserURL, actualBrowserUrl));
				return res;
			}
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(String.format("Failed to verify browser url. An error occured: %s", e.getMessage()));
			return res;
		}
	}

	protected ResultModel verifyBrowserTitle(String expectedBrowserTitle) {
		ResultModel res = new ResultModel();
		try {
			String actualBrowserTitle = driver.getTitle();
			if (actualBrowserTitle.toLowerCase().contains(expectedBrowserTitle.toLowerCase())
					|| actualBrowserTitle.equalsIgnoreCase(expectedBrowserTitle)) {
				res.setMessage(String.format("Browser title displays as expected - [%s]", expectedBrowserTitle));
				return res;
			} else {
				res.setResult(false);
				res.setMessage(String.format("Browser title is mismatching. Expected is: [%s]. Actual is [%s]",
						expectedBrowserTitle, actualBrowserTitle));
				return res;
			}

		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(String.format("Failed to verify browser title. An error occured: %s", e.getMessage()));
			return res;
		}
	}

	protected ResultModel verifyCssValue(WebElement webElement, String elementName, String cssName,
			String expectedCssValue) {
		ResultModel res = new ResultModel();
		try {
			String cssValue = webElement.getCssValue(cssName);
			if (cssValue.equalsIgnoreCase(expectedCssValue)) {
				res.setMessage(String.format("The [%s] property of [%s] has value as expected: [%s]", cssName,
						elementName, expectedCssValue));
				return res;
			} else {
				res.setResult(false);
				res.setMessage(String.format(
						"The [%s] propterty of [%s] has value which is not expected. Expected: %s. Actual: ", cssName,
						elementName, expectedCssValue, cssValue));
				return res;
			}
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(String.format("Failed to verfiy CSS value. An error occured: %s", e.getMessage()));
			return res;
		}
	}

	protected ResultModel verifyWebElementIsDisable(WebElement webElement, String elementName) {
		ResultModel res = new ResultModel();
		try {
			if (!webElement.isEnabled()) {
				res.setMessage(String.format("The [%s] is disable as expected.", elementName));
				return res;
			} else {
				res.setResult(true);
				res.setMessage(String.format("The [%s] is enable which is not expected.", elementName));
				return res;
			}
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(
					String.format("Failed to verify web element is disable. An error occured: %s", e.getMessage()));
			return res;
		}
	}

	public ResultModel closeAllOtherBrowserWindows() {
		ResultModel res = new ResultModel();
		try {
			String currentHandler = driver.getWindowHandle();
			Set<String> s = driver.getWindowHandles();
			Iterator<String> ite = s.iterator();
			while (ite.hasNext()) {
				String popupHandle = ite.next();
				if (!popupHandle.contains(currentHandler)) {
					driver.switchTo().window(popupHandle);
					driver.close();
				}
				driver.switchTo().window(currentHandler);
			}
			res.setMessage(String.format("Except Main Driver window, all other browsers were closed"));
			res.setDriver(driver);
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setDriver(driver);
			res.setMessage(
					String.format("Failed to close all other browser window. An error accourred: %s", e.getMessage()));
			return res;
		}
	}

	public ResultModel switchToNewWindow(String newPageTitle) {
		hardDelay(3);
		ResultModel res = new ResultModel();
		try {
			String currentHandler = driver.getWindowHandle();
			Set<String> s = driver.getWindowHandles();
			boolean flag = false;
			Iterator<String> ite = s.iterator();
			while (ite.hasNext()) {
				String popupHandle = ite.next().toString();
				if (!popupHandle.contains(currentHandler)) {
					driver.switchTo().window(popupHandle);
					if (driver.getTitle().equalsIgnoreCase(newPageTitle)) {
						flag = true;
						res.setMessage(String.format("Switched to new browser window with title [%s]", newPageTitle));
						res.setDriver(driver);
						return res;
					} else {
						driver.switchTo().window(currentHandler);
					}
				}
			}
			if (!flag) {
				res.setResult(false);
				res.setDriver(driver);
				res.setMessage(String.format("Failed to switch to new browser window with title [%s]", newPageTitle));
				return res;
			}
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setDriver(driver);
			res.setMessage(String.format("Failed to switch to new browser window with title [%s]", newPageTitle));
			return res;
		}

	}

	public ResultModel switchToIFrameByNameOrId(String nameIframe) {
		ResultModel res = new ResultModel();
		try {
			driver.switchTo().frame(nameIframe);
			res.setDriver(driver);
			res.setMessage(String.format("Switch to ifram successfully"));
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setDriver(driver);
			res.setMessage(String.format("Failed to switch to iframe. An error occurred: %s", e.getMessage()));
			return res;
		}

	}

	public ResultModel deSwitchtoIframe() {
		ResultModel res = new ResultModel();
		try {
			driver.switchTo().defaultContent();
			res.setMessage(String.format("De-switch to default Iframe successfully"));
			res.setDriver(driver);
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(String.format("Failed to de-switch to iframe"));
			return res;
		}
	}

	protected ResultModel switchToIFrameByWebElement(WebElement element) {
		ResultModel res = new ResultModel();
		try {
			driver.switchTo().frame(element);
			res.setDriver(driver);
			res.setMessage(String.format("Switch to ifram successfully"));
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setDriver(driver);
			res.setMessage(String.format("Failed to switch to iframe. An error occurred: %s", e.getMessage()));
			return res;
		}
	}

	protected ResultModel deSwitchToMainDriver(String maindriverHandler) {
		ResultModel res = new ResultModel();
		try {
			driver.switchTo().window(maindriverHandler);
			res.setDriver(driver);
			res.setMessage(String.format("Deswitch to main driver successfully"));
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setDriver(driver);
			res.setMessage(String.format("Failed to deswitch to main window. An error occurred: %s", e.getMessage()));
			return res;
		}
	}

	public static String generateRandomString() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static void hardDelay(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ResultModel navigatetoURL(String url) {
		ResultModel res = new ResultModel();
		try {
			driver.navigate().to(url);
			res.setMessage(String.format("Navigate to url [%s] successfully", url));
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(
					String.format("Failed to navigate to new url: [%s]. An error occurred: %s", url, e.getMessage()));
			return res;
		}
	}

	public ResultModel navigatetoURLInNewTab(String url) {
		ResultModel res = new ResultModel();
		try {
			if ("".equalsIgnoreCase(BrowserModel.GOOGLE_CHROME)) {
				((JavascriptExecutor) driver).executeScript("window.open()");
				List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
			}
			driver.get(url);
			res.setDriver(driver);
			res.setMessage(String.format("Opened the new tab and navigate to the url [%s] successfully", url));
			return res;
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(
					String.format("Failed to navigate to new url: [%s]. An error occurred: %s", url, e.getMessage()));
			return res;
		}
	}

	protected ResultModel compareText(WebElement element, String elementName, String expected) {
		ResultModel res = new ResultModel();
		String text = element.getText();
		try {
			if (!(text.toUpperCase().contains(expected.toUpperCase()))) {
				res.setResult(false);
				res.setMessage(String.format("Text %s does not display as expected: %s. Actual: %s", elementName,
						expected, text));
				return res;
			} else {
				res.setMessage(String.format("Text %s display as expected: %s", elementName, expected));
				return res;
			}
		} catch (Exception e) {
			res.setMessage(String.format("Failed to compare text. An error occurred: %s", e.getMessage()));
			return res;
		}
	}

	protected ResultModel compareValueOfWebElement(WebElement element, String elementName, String expected) {
		ResultModel res = new ResultModel();
		String value = element.getAttribute("value");
		try {
			if (!(value.toUpperCase().contains(expected.toUpperCase()))) {
				res.setResult(false);
				res.setMessage(String.format("Value %s does not display as expected: %s. Actual: %s", elementName,
						expected, value));
				return res;
			} else {
				res.setMessage(String.format("Value %s display as expected: %s", elementName, expected));
				return res;
			}
		} catch (Exception e) {
			res.setMessage(String.format("Failed to compare value. An error occurred: %s", e.getMessage()));
			return res;
		}
	}

	protected ResultModel compareText(final List<WebElement> element, String elementName, String expected) {
		ResultModel res = new ResultModel();
		if (!element.get(1).getText().toUpperCase().contains(expected.toUpperCase())) {
			res.setResult(false);
			res.setMessage(String.format("Text of WebElement %s does not display as expected: %s. Actual: %s",
					elementName, expected, element.get(1).getText()));
		}
		return res;
	}

	public void scrollToView(WebElement webElement) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
	}

	public void scrollUp() {
		try {
			hardDelay(2);
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
			actions.keyUp(Keys.CONTROL).perform();
			hardDelay(1);
		} catch (Exception e) {

		}
	}

	public void scrollDown() {
		try {
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			actions.keyUp(Keys.CONTROL).perform();
		} catch (Exception e) {

		}
	}

	protected WebElement getChildWebElement(WebElement parentElement, By childLocator) {
		WebElement element = null;
		for (int i = 0; i < TIMEOUT; i++) {
			try {
				element = parentElement.findElement(childLocator);
				hardDelay(1);
				break;
			} catch (NoSuchElementException e) {
				hardDelay(1);
			}
		}
		return element;
	}

	protected WebElement getWebElement(By locator) {
		WebElement element = null;
		for (int i = 0; i < TIMEOUT; i++) {
			try {
				element = driver.findElement(locator);
				hardDelay(1);
				break;
			} catch (NoSuchElementException e) {
				hardDelay(1);
			}
		}
		return element;
	}

	protected List<WebElement> getListWebElement(By locator) {
		List<WebElement> elements = new ArrayList<WebElement>();
		for (int i = 0; i < TIMEOUT; i++) {
			try {
				elements = driver.findElements(locator);
				break;
			} catch (NoSuchElementException e) {
				hardDelay(1);
			}
		}
		return elements;
	}

	protected List<WebElement> getWebElements(By locator) {
		List<WebElement> listElement = null;
		for (int i = 0; i < TIMEOUT; i++) {
			try {
				listElement = driver.findElements(locator);
				if (listElement.size() > 0)
					break;
				else
					hardDelay(1);
			} catch (NoSuchElementException e) {
				hardDelay(1);
			}
		}
		return listElement;
	}

	// public ResultModel uploadFile(String path) {
	// ResultModel res = new ResultModel();
	// try {
	// Pattern textboxFileName = new Pattern();
	// Pattern buttonOpen = new Pattern();
	// if (SystemVariable.BROWSER.equalsIgnoreCase(BrowserModel.GOOGLE_CHROME))
	// {
	// textboxFileName = new
	// Pattern("src/main/resources/file/textboxFileName.PNG");
	// buttonOpen = new
	// Pattern("src/main/resources/file/buttonOpen_Chrome.PNG");
	// }
	// Screen screen = new Screen();
	// screen.type(textboxFileName, path);
	// screen.click(buttonOpen);
	// res.setMessage(String.format("Uploaded the file [%s] successfully.",
	// path));
	// return res;
	// } catch (Exception e) {
	// res.setResult(false);
	// res.setMessage(String.format("Failed to upload file. An error occurred:
	// %s", e.getMessage()));
	// return res;
	// }
	// }

	public ResultModel getText(WebElement element, String eleName) {
		ResultModel res = new ResultModel();
		try {
			String temp = element.getText();
			res.setElementText(temp);
			return res;
		} catch (Exception e) {
			res.setMessage(null);
			res.setResult(false);
			res.setMessage(String.format("Failed to get text of webelement %s. ERROR: %s", eleName, e.getMessage()));
			return res;
		}
	}

	public ResultModel getValue(WebElement element, String eleName) {
		ResultModel res = new ResultModel();
		try {
			String temp = element.getAttribute("value");
			res.setElementText(temp);
			return res;
		} catch (Exception e) {
			res.setMessage(null);
			res.setResult(false);
			res.setMessage(String.format("Failed to get value of webelement %s. ERROR: %s", eleName, e.getMessage()));
			return res;
		}
	}

	public ResultModel isWebElementDisplayed(WebElement element, String eleName) {
		ResultModel res = new ResultModel();
		try {
			if (element.isDisplayed()) {
				res.setMessage(String.format("WebElment %s display as expected", eleName));
				return res;
			} else {
				res.setResult(false);
				res.setMessage(String.format("The web element %s does not display which is not expected", eleName));
				return res;
			}
		} catch (Exception e) {
			res.setResult(false);
			res.setMessage(String.format("The web element %s does not display which is not expected", eleName));
			return res;
		}

	}

	protected static void waitLoadForm(WebDriver webDriver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(webDriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void fluientWaitforElement(WebElement element, int timoutSec, int pollingSec) {

		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(timoutSec, TimeUnit.SECONDS)
				.pollingEvery(pollingSec, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class, TimeoutException.class)
				.ignoring(StaleElementReferenceException.class);

		for (int i = 0; i < 2; i++) {
			try {
				// fWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='reportmanager-wrapper']/div[1]/div[2]/ul/li/span[3]/i[@data-original--title='We
				// are processing through trillions of data events, this insight
				// may take more than 15 minutes to complete.']")));
				fWait.until(ExpectedConditions.visibilityOf(element));
				fWait.until(ExpectedConditions.elementToBeClickable(element));
			} catch (Exception e) {
				System.out.println("Element Not found trying again - " + element.toString().substring(70));
				e.printStackTrace();

			}
		}
	}

	protected void waitUntilElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected ResultModel waitUntilElementDisapeared(By locator, String eleName) {
		ResultModel res = new ResultModel();

		for (int i = 0; i < TIMEOUT; i++) {
			try {
				WebElement element = driver.findElement(locator);
				if (element.isDisplayed()) {
					hardDelay(1);
				}
			} catch (Exception e) {
				res.setMessage(String.format("The web element %s does not display as expected", eleName));
				return res;
			}
		}
		res.setResult(false);
		res.setMessage(String.format("The web element %s display which is not expected", eleName));
		return res;
	}

	protected ResultModel waitUntilElementsDisapeared(By locator, String eleName) {
		ResultModel res = new ResultModel();

		for (int i = 0; i < TIMEOUT; i++) {
			try {
				List<WebElement> element = driver.findElements(locator);
				if (element.size() > 0) {
					hardDelay(1);
				}
			} catch (Exception e) {
				res.setMessage(String.format("The web element %s does not display as expected", eleName));
				return res;
			}
		}
		res.setResult(false);
		res.setMessage(String.format("The web element %s display which is not expected", eleName));
		return res;
	}

	protected ResultModel waitUntilElementsDisplayed(By locator, String eleName) {
		ResultModel res = new ResultModel();

		for (int i = 0; i < TIMEOUT; i++) {
			try {
				List<WebElement> element = driver.findElements(locator);
				if (element.size() == 0) {
					hardDelay(1);
				}
			} catch (Exception e) {
				res.setResult(false);
				res.setMessage(String.format("The web element %s does not display which is not expected", eleName));
				return res;
			}
		}
		res.setResult(true);
		res.setMessage(String.format("The web element %s display which as expected", eleName));
		return res;
	}

	public void BackPreviousPage() {
		driver.navigate().back();
	}

	public void BackPreviousPageUsingJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.history.go(-1)");
	}

	public WebElement getWebElementFromList(List<WebElement> listElements, String expectedStr, By child) {
		try {
			for (WebElement ele : listElements) {
				if (ele.getText().toUpperCase().trim().contains(expectedStr.toUpperCase().trim())) {
					return getChildWebElement(ele, child);
				}
			}
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
}
