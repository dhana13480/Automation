package gov.michigan.obra.automation.util.driver;

import java.security.InvalidParameterException;

import static gov.michigan.obra.automation.common.constant.Browser.*;
import static gov.michigan.obra.automation.common.constant.Constants.*;

public class WebDriverFactory {
    public WebDriverService getDriverService() {
        WebDriverService driver;

        switch (BROWSER_NAME) {
            case CHROME:
                driver = new ChromeDriverServiceImpl();
                break;
            case HEADLESS_CHROME:
                driver = new HeadlessChromeDriverServiceImpl();
                break;
            case FIREFOX:
                driver = new FirefoxDriverServiceImpl();
                break;
            case HEADLESS_FIREFOX:
                driver = new HeadlessFirefoxDriverServiceImpl();
                break;
            case EDGE:
                driver = new EdgeDriverServiceImpl();
                break;
            case IE:
                driver = new InternetExplorerDriverServiceImpl();
                break;
            case SAFARI:
                driver = new SafariDriverServiceImpl();
                break;

            default:
                throw new InvalidParameterException("'" + BROWSER_NAME + "' browser is not implemented");
        }

        return driver;
    }
}
