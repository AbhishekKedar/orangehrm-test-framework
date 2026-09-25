package com.orangehrm.qa.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {

	private static ExtentReports extentReports;

	public static synchronized ExtentReports getExtentReports() {

		if (extentReports == null) {

			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

			String reportPath = System.getProperty("user.dir") + "/reports/" + "OrangeHRM-Test-Report_" + timestamp + ".html";

			File reportFile = new File(reportPath);

			reportFile.getParentFile().mkdirs();

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

			sparkReporter.config().setDocumentTitle("OrangeHRM Automation Report");

			sparkReporter.config().setReportName("OrangeHRM Test Execution");

			extentReports = new ExtentReports();

			extentReports.attachReporter(sparkReporter);

			extentReports.setSystemInfo("Project", "OrangeHRM");

			extentReports.setSystemInfo("Environment", "QA");
		}

		return extentReports;
	}
}