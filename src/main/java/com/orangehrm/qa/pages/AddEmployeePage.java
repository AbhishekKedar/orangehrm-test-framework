package com.orangehrm.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.qa.utils.WaitUtils;

public class AddEmployeePage {

	private WebDriver driver;

	@FindBy(name = "firstName")
	private WebElement firstNameField;

	@FindBy(name = "middleName")
	private WebElement middleNameField;

	@FindBy(name = "lastName")
	private WebElement lastNameField;

	@FindBy(xpath = "//label[normalize-space()='Employee Id']/parent::div/following-sibling::div/input")
	private WebElement employeeIdField;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveButton;

	@FindBy(css = ".oxd-form-loader")
	private WebElement formLoader;

	public AddEmployeePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstName) {

		WaitUtils.waitForVisibility(driver, firstNameField).sendKeys(firstName);
	}

	public void enterMiddleName(String middleName) {

		WaitUtils.waitForVisibility(driver, middleNameField).sendKeys(middleName);
	}

	public void enterLastName(String lastName) {

		WaitUtils.waitForVisibility(driver, lastNameField).sendKeys(lastName);
	}

	public String getEmployeeId() {

		return WaitUtils.waitForVisibility(driver, employeeIdField).getAttribute("value");
	}

	public PersonalDetailsPage clickSaveButton() {

		WaitUtils.waitForInvisibility(driver, formLoader);

		WaitUtils.waitForClickability(driver, saveButton).click();

		return new PersonalDetailsPage(driver);
	}

	public PersonalDetailsPage addEmployee(String firstName, String middleName, String lastName) {

		enterFirstName(firstName);
		enterMiddleName(middleName);
		enterLastName(lastName);

		return clickSaveButton();
	}

	public void enterEmployeeId(String employeeId) {

		WebElement employeeIdInput = WaitUtils.waitForVisibility(driver, employeeIdField);

		employeeIdInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));

		employeeIdInput.sendKeys(employeeId);
	}
}