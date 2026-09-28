package com.orangehrm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.qa.utils.WaitUtils;

public class PersonalDetailsPage {

	private WebDriver driver;

	@FindBy(xpath = "//h6[normalize-space()='Personal Details']")
	private WebElement personalDetailsHeading;

	public PersonalDetailsPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public boolean isPersonalDetailsDisplayed() {

		return WaitUtils.waitForVisibility(driver, personalDetailsHeading).isDisplayed();
	}
}