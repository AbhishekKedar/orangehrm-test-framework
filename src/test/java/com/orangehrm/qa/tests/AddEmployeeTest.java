package com.orangehrm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.BaseTest;
import com.orangehrm.qa.driver.DriverManager;
import com.orangehrm.qa.pages.AddEmployeePage;
import com.orangehrm.qa.pages.DashboardPage;
import com.orangehrm.qa.pages.LoginPage;
import com.orangehrm.qa.pages.PIMPage;
import com.orangehrm.qa.pages.PersonalDetailsPage;

public class AddEmployeeTest extends BaseTest {

	@Test
	public void verifyAddEmployeeFunctionality() {

		LoginPage loginPage = new LoginPage(DriverManager.getDriver());

		loginPage.login("Admin", "admin123");

		DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());

		Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard was not displayed after login");

		PIMPage pimPage = new PIMPage(DriverManager.getDriver());

		pimPage.openPIM();

		AddEmployeePage addEmployeePage = pimPage.openAddEmployeePage();

		String employeeId = "QA" + Math.abs(System.nanoTime() % 100000000L);

		addEmployeePage.enterEmployeeId(employeeId);

		PersonalDetailsPage personalDetailsPage = addEmployeePage.addEmployee("Rahu", "Raj", "Varma");

		Assert.assertTrue(personalDetailsPage.isPersonalDetailsDisplayed(),
				"Personal Details page was not displayed after adding employee");

		System.out.println("Employee added successfully with ID: " + employeeId);
	}
}