package com.orangehrm.qa.listeners;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.IExecutionListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.qa.driver.DriverManager;
import com.orangehrm.qa.utils.ExtentManager;
import com.orangehrm.qa.utils.ScreenshotUtils;

public class TestListener implements ITestListener, IExecutionListener {

	private static ExtentReports extentReports = ExtentManager.getExtentReports();

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {

		String testName = result.getMethod().getMethodName() + " " + Arrays.toString(result.getParameters());

		ExtentTest test = extentReports.createTest(testName);

		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().pass("Test passed successfully");

		extentTest.remove();
	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());

		WebDriver driver = DriverManager.getDriver();

		if (driver != null) {

			try {

				String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());

				extentTest.get().addScreenCaptureFromPath(screenshotPath);

			} catch (RuntimeException e) {

				extentTest.get().warning("Screenshot could not be captured or attached: " + e.getMessage());
			}

		} else {

			extentTest.get().warning("Screenshot was not captured because driver is null");
		}

		extentTest.remove();
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		if (result.getThrowable() != null) {

			extentTest.get().skip(result.getThrowable());

		} else {

			extentTest.get().skip("Test was skipped");
		}

		extentTest.remove();
	}

	@Override
	public void onExecutionFinish() {

		extentReports.flush();
	}
}