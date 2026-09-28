package com.orangehrm.qa.base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.orangehrm.qa.config.ConfigReader;
import com.orangehrm.qa.driver.DriverManager;

public class BaseTest {

	@Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("") String browser) {

		if (browser.isBlank()) {

			browser = ConfigReader.getProperty("browser");
		}

		boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions chromeOptions = new ChromeOptions();

			if (headless) {

				chromeOptions.addArguments("--headless=new");
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--disable-dev-shm-usage");
			}

			DriverManager.setDriver(new ChromeDriver(chromeOptions));

		} else if (browser.equalsIgnoreCase("firefox")) {

			FirefoxOptions firefoxOptions = new FirefoxOptions();

			if (headless) {

				firefoxOptions.addArguments("-headless");
			}

			DriverManager.setDriver(new FirefoxDriver(firefoxOptions));

		} else {

			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		if (headless) {

			DriverManager.getDriver().manage().window().setSize(new Dimension(1920, 1080));

		} else {

			DriverManager.getDriver().manage().window().maximize();
		}

		DriverManager.getDriver().get(ConfigReader.getProperty("baseURL"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		DriverManager.quitDriver();
		DriverManager.unloadDriver();
	}
}