package com.orangehrm.qa.base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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

		if (browser.equalsIgnoreCase("chrome")) {

			DriverManager.setDriver(new ChromeDriver());

		} else if (browser.equalsIgnoreCase("edge")) {

			DriverManager.setDriver(new EdgeDriver());

		} else {

			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		DriverManager.getDriver().manage().window().maximize();

		DriverManager.getDriver().get(ConfigReader.getProperty("baseURL"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		DriverManager.quitDriver();
		DriverManager.unloadDriver();
	}
}