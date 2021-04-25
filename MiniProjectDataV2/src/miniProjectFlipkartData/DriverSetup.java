package miniProjectFlipkartData;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.opera.OperaDriver;

public class DriverSetup {

	public static WebDriver getDriver(String browser) {
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\resources\\drivers\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\resources\\drivers\\geckodriver.exe");

			ProfilesIni settings = new ProfilesIni();
			FirefoxProfile profile = settings.getProfile("selenium");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setProfile(profile);
			driver = new FirefoxDriver(firefoxOptions);
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\resources\\drivers\\msedgedriver.exe");

			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("opera")) {
			System.setProperty("webdriver.opera.driver",
					System.getProperty("user.dir") + "\\resources\\drivers\\operadriver.exe");

			driver = new OperaDriver();
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		String baseUrl = "https://www.flipkart.com";
		driver.get(baseUrl);
		return driver;
	}
}
