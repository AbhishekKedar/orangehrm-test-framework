package com.orangehrm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.qa.utils.WaitUtils;

public class DashboardPage {

	private WebDriver driver;

	@FindBy(xpath = "//h6[normalize-space()='Dashboard']")
	private WebElement dashboardHeading;

	public DashboardPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public boolean isDashboardDisplayed() {

		WebElement visibleHeading = WaitUtils.waitForVisibility(driver, dashboardHeading);

		return visibleHeading.isDisplayed();
	}
}