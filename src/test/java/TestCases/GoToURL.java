package TestCases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoToURL {
	WebDriver driver;

	@Test
	public void Test() {
		driver.get("https://github.com/");
		driver.manage().window().maximize();
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		driver = new RemoteWebDriver(new URL("http://192.168.1.3:4444"), desiredCapabilities);
		
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
