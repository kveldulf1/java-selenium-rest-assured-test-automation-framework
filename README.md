# Test Automation Framework

A robust test automation framework built with Java, supporting both API and UI testing. The framework uses Cucumber for BDD, RestAssured for API testing, and Selenium WebDriver for UI testing.

🚧 **Project Status: In Development**  
This framework is actively being developed. Planned improvements include:
- Enhanced architectural patterns
- Expanded test coverage
- Additional testing features
- Jenkinsfile for CI/CD pipeline execution
- More comprehensive documentation

## 🌿 Branches

⚠️ **Important**: Active development happens in the following branches. Please check them for the latest code:

### [cucumber-tests](https://github.com/kveldulf1/java-selenium-rest-assured-test-automation-framework/tree/cucumber-tests)
- Single-threaded execution
- BDD implementation with Cucumber
- Cucumber HTML reporting
- Step definitions and feature files

### [parallel-tests-logs-allure-reports](https://github.com/kveldulf1/java-selenium-rest-assured-test-automation-framework/tree/parallel-tests-logs-allure-reports)
- Concurrent test execution
- Resource locking for thread safety during parallel execution
- Fluent chain pattern implementation
- Allure reporting integration
- Buffered logs for concurrent tests displayed sequentially in the console
- Parallel test execution capabilities

## 🎯 Tested Application

This framework is designed to test [GAD (GUI API Demo)](https://github.com/jaktestowac/gad-gui-api-demo) - an application specifically created for testing purposes that provides:
- GUI interface
- REST API
- Integrated Swagger documentation
- Deliberately included bugs and challenges

### Application Setup
For the best testing experience, we recommend:
1. Local installation of GAD (see [GAD installation guide](https://github.com/jaktestowac/gad-gui-api-demo#deploy-on-local))
2. Run on default port 3000
3. Basic setup commands:
   ```bash
   git clone https://github.com/jaktestowac/gad-gui-api-demo.git
   npm i
   npm run start
   ```
The application will be available at `http://localhost:3000`

## 🛠 Tech Stack & Versions
- Java 21
- Selenium WebDriver 4.24.0
- RestAssured 5.4.0
- Cucumber 7.20.1
- JUnit Jupiter 5.11.0
- Logback Classic 1.4.11
- Gson 2.11.0
- Cucumber JUnit Platform Engine 7.15.0
- JUnit Platform Suite 1.10.2
- Maven Surefire Plugin 2.22.2
- Allure Reports 2.25.0
- Maven 3.9.6
- Cucumber HTML Reports 5.7.7

## 📁 Project Structure
```
src/test/java/
├── api/            # API service layer
├── config/         # Configuration classes
├── constants/      # Constants and endpoints
├── helpers/        # Utility classes
├── hooks/          # Cucumber hooks
├── pageobjects/    # Page Object Model classes
├── pojo/           # Request/Response POJOs
├── runner/         # Cucumber test runner
├── stepdefs/       # Step definitions
└── utils/          # Common utilities
```

## 🔑 Key Features

- **BDD Testing**: Uses Cucumber for behavior-driven development
- **API Testing**: RestAssured-based API testing with request/response POJOs
- **UI Testing**: Page Object Model implementation with Selenium WebDriver
- **Multi-Browser Support**: Chrome, Firefox, and Edge
- **Headless Mode**: Supports headless browser testing
- **Logging**: Comprehensive logging with Logback
- **Test Data Management**: JSON-based test data with dynamic value resolution
- **Configuration**: Properties-based configuration
- **Clean Architecture**: SOLID principles and clean code practices

## 🚀 Getting Started

1. Clone the repository
2. Run tests using:
   ```bash
   mvn clean test
   ```

## 📋 Configuration

Configure the following in `configuration.properties`:
- `browser`: chrome/firefox/edge
- `headless`: true/false
- `baseURL`: Application base URL

## Parallel Execution Settings
Configure parallel execution in `src/test/resources/junit-platform.properties`

## 📝 Test Data

Test data is managed through JSON files in `src/test/resources/testdata/`:
- `users.json`: User test data
- Add more test data files as needed

## 📊 Reports

### Cucumber HTML Reports
Generated at:
`target/cucumber-reports/report.html`

### Allure Reports
Allure provides rich test reports with detailed test execution data, including steps, attachments, and timelines.

#### Generate and View Reports
Single command to run tests and view report:
```powershell
mvn clean test allure:report allure:serve
```


#### Report Locations
- Allure results: `target/allure-results`
- HTML report: `target/site/allure-report/index.html`

#### Report Features
- Test execution timeline
- Detailed test steps and attachments
- Environment information
- Test categorization and filtering
- Severity levels visualization
- Test duration statistics
- Failed test analysis
- Screenshots for UI test failures

## 🔧 Framework Components

### API Testing
- Base service layer for HTTP methods
- Request/Response POJOs
- API endpoints management
- Authentication handling

### UI Testing
- Page Object Model implementation
- Component-based architecture
- Cross-browser support
- Headless testing capability

### Utilities
- Dynamic test data resolution
- Browser factory
- Configuration management
- Logging system

### Logging
- Use logback.xml for logging level and other configurations

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

Hire me ;) LinkedIn: https://www.linkedin.com/in/michal-kownacki/