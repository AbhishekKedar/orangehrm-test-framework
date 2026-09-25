package com.orangehrm.qa.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	// Setter
	public static void setDriver(WebDriver webDriver) {

		driver.set(webDriver);
	}

	// Getter
	public static WebDriver getDriver() {

		return driver.get();
	}

	// Quit
	public static void quitDriver() {

		if (driver.get() != null) {

			driver.get().quit();
		}
	}
	
	//Unloading reference from ThreadLocal
	public static void unloadDriver() {

		driver.remove();
	}

}
