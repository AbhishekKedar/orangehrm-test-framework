package com.orangehrm.qa.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtils {

	public static String captureScreenshot(WebDriver driver, String testName) {

		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_"
				+ System.currentTimeMillis() + ".png";

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destination = new File(screenshotPath);

		destination.getParentFile().mkdirs();

		try {

			FileHandler.copy(source, destination);

		} catch (IOException e) {

			throw new RuntimeException("Failed to save screenshot", e);
		}

		return screenshotPath;
	}
}