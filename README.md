# OrangeHRM Test Automation Framework

This project is a Selenium-based automation framework created to test the OrangeHRM web application.

The framework supports data-driven testing, cross-browser execution, parallel testing, reporting, screenshots, secure credential management, and CI execution.

## Technologies Used

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Apache POI
- Extent Reports
- Log4j
- Git and GitHub
- GitHub Actions
- Jenkins

## Browsers Supported

- Google Chrome
- Mozilla Firefox

## Framework Features

- Page Object Model
- PageFactory with `@FindBy`
- Data-driven testing using Excel
- Apache POI for reading Excel data
- Cross-browser testing
- Parallel execution using TestNG
- Explicit waits using WaitUtils
- Thread-safe WebDriver management
- Screenshots for failed tests
- TestNG listeners
- Extent HTML reports
- Maven Surefire reports
- Headless browser execution
- Environment-based credential management
- GitHub Actions integration
- Jenkins integration

## Automated Test Scenarios

- Verify login page title
- Verify valid login
- Verify invalid login
- Verify blank username validation
- Verify blank password validation
- Verify logout functionality
- Verify add employee functionality

## Project Structure

```text
orangehrm-test-framework
│
├── src/main/java
│   └── com.orangehrm.qa
│       ├── config
│       │   └── ConfigReader.java
│       ├── driver
│       │   └── DriverManager.java
│       ├── pages
│       │   ├── LoginPage.java
│       │   ├── DashboardPage.java
│       │   ├── PIMPage.java
│       │   ├── AddEmployeePage.java
│       │   └── PersonalDetailsPage.java
│       └── utils
│           ├── ExcelUtils.java
│           ├── ExtentManager.java
│           ├── ScreenshotUtils.java
│           └── WaitUtils.java
│
├── src/test/java
│   └── com.orangehrm.qa
│       ├── base
│       │   └── BaseTest.java
│       ├── listeners
│       │   └── TestListener.java
│       └── tests
│           ├── LoginTest.java
│           ├── LogoutTest.java
│           └── AddEmployeeTest.java
│
├── src/test/resources
│   ├── config.properties
│   └── testdata
│       └── LoginData.xlsx
│
├── .github/workflows
│   └── ci.yml
│
├── pom.xml
├── testng.xml
└── README.md
```

## Configuration

Framework configuration is stored in:

```text
src/test/resources/config.properties
```

The configuration contains values such as:

```properties
browser=chrome
baseURL=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
timeout=20
```

Credentials can be supplied through environment variables:

```text
ADMIN_USERNAME
ADMIN_PASSWORD
```

Configuration priority:

1. Java system properties
2. Environment variables
3. `config.properties`

GitHub Actions uses GitHub Secrets, while Jenkins uses Jenkins Credentials.

## Running Tests

Run tests normally:

```bash
mvn clean test
```

Run tests in headless mode:

```bash
mvn clean test -Dheadless=true
```

The Maven Surefire plugin automatically executes the TestNG suite configured in `testng.xml`.

## Test Data

Login test data is maintained in:

```text
src/test/resources/testdata/LoginData.xlsx
```

Apache POI reads the Excel data and supplies it to TestNG through a DataProvider.

## Reports and Screenshots

After execution, results are generated in:

```text
reports/
screenshots/
target/surefire-reports/
```

- Extent Reports provide an HTML execution report.
- Screenshots are captured when a test fails.
- Surefire Reports contain Maven test results.

## Continuous Integration

### GitHub Actions

The workflow is available at:

```text
.github/workflows/ci.yml
```

GitHub Actions runs the Maven test suite in headless mode whenever code is pushed to the `main` branch or a pull request is created.

### Jenkins

The Jenkins job performs the following operations:

1. Downloads the latest project code from GitHub.
2. Injects credentials using environment variables.
3. Executes the Maven test suite in headless mode.
4. Publishes TestNG test results.
5. Archives reports and screenshots.

## Author

Abhishek Kedar