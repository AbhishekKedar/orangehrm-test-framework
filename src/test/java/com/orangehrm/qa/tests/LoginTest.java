package com.orangehrm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.BaseTest;
import com.orangehrm.qa.driver.DriverManager;
import com.orangehrm.qa.pages.DashboardPage;
import com.orangehrm.qa.pages.LoginPage;
import com.orangehrm.qa.utils.ExcelUtils;

public class LoginTest extends BaseTest {

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() {

		String[][] excelData = ExcelUtils.getExcelData("src/test/resources/testdata/LoginData.xlsx", "LoginData");

		Object[][] loginData = new Object[excelData.length][3];

		for (int rowIndex = 0; rowIndex < excelData.length; rowIndex++) {

			loginData[rowIndex][0] = excelData[rowIndex][0];

			loginData[rowIndex][1] = excelData[rowIndex][1];

			loginData[rowIndex][2] = Boolean.parseBoolean(excelData[rowIndex][2]);
		}

		return loginData;
	}

	@Test(priority = 1)
	public void verifyLoginPageTitle() {
		System.out.println("---------------------------------------------------");

		LoginPage loginPage = new LoginPage(DriverManager.getDriver());

		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");

		String actualTitle = DriverManager.getDriver().getTitle();

		Assert.assertEquals(actualTitle, "OrangeHRM", "Login page title is incorrect");

		System.out.println("Assertion Passed because title match and this line is executed");
	}

	@Test(dataProvider = "loginData", priority = 2)
	public void verifyLoginFunctionality(String username, String password, boolean expected) {
		System.out.println("---------------------------------------------------");

		try {

			LoginPage loginPage = new LoginPage(DriverManager.getDriver());

			loginPage.login(username, password);

			if (expected == true) {

				DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());

				Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Valid login failed for user: " + username);

				System.out.println("Valid login test passed for user: " + username);

			} else if (username.equals("") || password.equals("")) {

				if (username.equals("")) {
					System.out.println("Username Field is blank");
				}

				if (password.equals("")) {
					System.out.println("Password Field is blank");

				}

			} else {

				Assert.assertTrue(loginPage.isInvalidCredentialsMessageDisplayed(),
						"Error message was not displayed for user: " + username);

				Assert.assertEquals(loginPage.getInvalidCredentialsMessage(), "Invalid credentials",
						"Incorrect error message for user: " + username);

				System.out.println("Invalid login test passed for user: " + username);
			}

		} catch (Exception e) {

			Assert.fail("Login test failed for user: " + username + ". Reason: " + e.getMessage(), e);
		}
	}
}