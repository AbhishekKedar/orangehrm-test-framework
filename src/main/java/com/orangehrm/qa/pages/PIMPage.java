package com.orangehrm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.qa.utils.WaitUtils;

public class PIMPage {

	private WebDriver driver;

	@FindBy(xpath = "//span[normalize-space()='PIM']")
	private WebElement pimMenu;

	@FindBy(xpath = "//a[normalize-space()='Add Employee']")
	private WebElement addEmployeeTab;

	public PIMPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void openPIM() {

		WaitUtils.waitForClickability(driver, pimMenu).click();
	}

	public AddEmployeePage openAddEmployeePage() {

		WaitUtils.waitForClickability(driver, addEmployeeTab).click();

		return new AddEmployeePage(driver);
	}
}