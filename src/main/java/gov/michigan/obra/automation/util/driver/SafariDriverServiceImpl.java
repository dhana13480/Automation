package gov.michigan.obra.automation.util.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import static gov.michigan.obra.automation.common.constant.Constants.*;


public class SafariDriverServiceImpl implements WebDriverService {
    private WebDriver driver;

    @Override
    public void spinUpDriver() {
        driver = WebDriverManager.safaridriver().create();
        driver.manage()
                .timeouts()
                .implicitlyWait(IMPLICIT_WAIT_IN_SECONDS);
    }

    @Override
    public void closeDriver() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
