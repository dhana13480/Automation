package gov.michigan.obra.automation.util.driver;

import org.openqa.selenium.WebDriver;


public interface WebDriverService {
    void spinUpDriver();

    void closeDriver();

    WebDriver getDriver();
}
