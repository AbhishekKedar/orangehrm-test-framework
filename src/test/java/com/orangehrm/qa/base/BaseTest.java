package com.orangehrm.qa.base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
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
				chromeOptions.addArguments("--window-size=1920,1080");
			}

			DriverManager.setDriver(new ChromeDriver(chromeOptions));

		} else if (browser.equalsIgnoreCase("edge")) {

			EdgeOptions edgeOptions = new EdgeOptions();

			if (headless) {

				edgeOptions.addArguments("--headless=new");
				edgeOptions.addArguments("--window-size=1920,1080");
			}

			DriverManager.setDriver(new EdgeDriver(edgeOptions));

		} else {

			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		if (!headless) {

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