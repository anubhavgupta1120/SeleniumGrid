package TestCases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DoSomeAction {
	WebDriver driver;

	@Test
	public void Test() {
		
		radioButtonOption("radio2", driver);
		SuggestiveCountryList("India", driver, "ind");
		SelectingDropDownUsingSelect("option2", driver);
		selectCheckboxes(List.of("Option1", "Option3"), driver);
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "MicrosoftEdge");
		driver = new RemoteWebDriver(new URL("http://192.168.1.3:4444"), desiredCapabilities);
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	public static void radioButtonOption(String optionName, WebDriver driver) {
		// System.out.println("Anubhav");
		int count = driver.findElements(By.name("radioButton")).size();
//		System.out.println(count);
		List<WebElement> radioButtons = driver.findElements(By.xpath("//label[starts-with(@for,'radio')]"));
		for (int i = 0; i < count; i++) {
			String elementInText = radioButtons.get(i).getText();
			if (optionName.equalsIgnoreCase(elementInText)) {
				driver.findElement(By.xpath("//input[@value = \"" + optionName + "\"]")).click();
			}
		}

	}

	public static void SuggestiveCountryList(String option, WebDriver driver, String first3Letters) {
		WebElement country = driver.findElement(By.xpath("//input[@placeholder = 'Type to Select Countries']"));
		country.click();
		country.sendKeys(first3Letters);
		List<WebElement> countriesElements = driver.findElements(By.xpath("//li[@class = \"ui-menu-item\"]//div"));
		for (WebElement ele : countriesElements) {
			String eleToString = ele.getText();
			if (option.equalsIgnoreCase(eleToString)) {
				ele.click();
			}
		}

	}

	public static void SelectingDropDownUsingSelect(String option, WebDriver driver) {
		Select select = new Select(driver.findElement(By.xpath("//select[@id = \"dropdown-class-example\"]")));
		select.selectByValue(option);
	}

	public static void selectCheckboxes(List<String> option, WebDriver driver) {
		List<WebElement> checkBox = driver.findElements(By.xpath("//div[@id = \"checkbox-example\"]//label"));
		int index = 0;
		for (WebElement ele : checkBox) {
			String elementToText = ele.getText();

			if (option.get(index).equals(elementToText)) {
				ele.findElement(By.tagName("input")).click();

				index++;
			}
			if (index == option.size()) {
				break;
			}

		}
	}

}
