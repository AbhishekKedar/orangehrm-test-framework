package com.orangehrm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.qa.config.ConfigReader;

import com.orangehrm.qa.base.BaseTest;
import com.orangehrm.qa.driver.DriverManager;
import com.orangehrm.qa.pages.DashboardPage;
import com.orangehrm.qa.pages.LoginPage;

public class LogoutTest extends BaseTest {

	@Test
	public void verifyLogoutFunctionality() {

		LoginPage loginPage = new LoginPage(DriverManager.getDriver());

		String username = ConfigReader.getProperty("adminUsername");

		String password = ConfigReader.getProperty("adminPassword");

		loginPage.login(username, password);

		DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());

		Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard was not displayed after login");

		System.out.println("Dashboard Page displayed");

		loginPage = dashboardPage.logout();

		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page was not displayed after logout");

		System.out.println("Login Page displayed");

	}
}