package com.orangehrm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.qa.utils.WaitUtils;

public class LoginPage {

	private WebDriver driver;

	@FindBy(name = "username")
	private WebElement usernameField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//p[normalize-space()='Invalid credentials']")
	private WebElement invalidCredentialsMessage;

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {

		WaitUtils.waitForVisibility(driver, usernameField).sendKeys(username);
	}

	public void enterPassword(String password) {

		WaitUtils.waitForVisibility(driver, passwordField).sendKeys(password);
	}

	public void clickLoginButton() {

		WaitUtils.waitForClickability(driver, loginButton).click();
	}

	public void login(String username, String password) {

		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
	}

	public boolean isInvalidCredentialsMessageDisplayed() {

		return WaitUtils.waitForVisibility(driver, invalidCredentialsMessage).isDisplayed();
	}

	public String getInvalidCredentialsMessage() {

		return WaitUtils.waitForVisibility(driver, invalidCredentialsMessage).getText();
	}

	public boolean isLoginPageDisplayed() {

		return WaitUtils.waitForVisibility(driver, usernameField).isDisplayed();
	}
}