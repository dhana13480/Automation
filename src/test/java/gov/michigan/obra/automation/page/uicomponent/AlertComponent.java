package gov.michigan.obra.automation.page.uicomponent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AlertComponent extends gov.michigan.obra.automation.page.BasePage {
    public AlertComponent(WebDriver driver) {
        super(driver);
    }

    public Alert switchToAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
