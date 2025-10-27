package gov.michigan.obra.automation.common;

import org.openqa.selenium.WebDriver;

import gov.michigan.obra.automation.page.BasePage;
import gov.michigan.obra.automation.page.uicomponent.AlertComponent;


public class UiComponentProvider extends BasePage {
    public UiComponentProvider(WebDriver driver) {
        super(driver);
    }

    public AlertComponent getAlertComponent() {
        return new AlertComponent(driver);
    }
}
