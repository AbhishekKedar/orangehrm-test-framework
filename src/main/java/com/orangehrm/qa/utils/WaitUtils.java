package com.orangehrm.qa.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.qa.config.ConfigReader;

public class WaitUtils {

	public static WebElement waitForVisibility(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getIntProperty("timeout")));

		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement waitForClickability(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getIntProperty("timeout")));

		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}