package gov.michigan.obra.automation;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.APPLICATION_URL;
import static gov.michigan.obra.automation.common.constant.Constants.BROWSER_NAME;
import static gov.michigan.obra.automation.common.constant.Constants.EXECUTION_ENV_NAME;
import static gov.michigan.obra.automation.common.constant.Constants.IMPLICIT_WAIT_IN_SECONDS;
import static gov.michigan.obra.automation.util.driver.WebDriverHolder.getDriver;
import static gov.michigan.obra.automation.util.driver.WebDriverHolder.setDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.maxsoft.testngtestresultsanalyzer.DriverHolder;
import com.maxsoft.testngtestresultsanalyzer.TestAnalyzeReportListener;

import gov.michigan.obra.automation.common.PageProvider;
import gov.michigan.obra.automation.common.UiComponentProvider;
import gov.michigan.obra.automation.util.driver.WebDriverFactory;
import gov.michigan.obra.automation.util.driver.WebDriverService;


@Listeners(TestAnalyzeReportListener.class)
public class BaseTest {
    private final WebDriverService driverService = new WebDriverFactory().getDriverService();
    private final Logger logger = LogManager.getLogger();

    @BeforeSuite
    public void oneTimeSetup() {
        logger.debug("Test execution browser: {}", BROWSER_NAME);
        logger.debug("Test execution environment: {}", EXECUTION_ENV_NAME);
    }

    @BeforeMethod
    public void openBrowser() {
        driverService.spinUpDriver();
        setDriver(driverService.getDriver());
        DriverHolder.setDriver(getDriver());    // Setting the driver for the html-reporter library
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_IN_SECONDS);
        getDriver().manage().window().maximize();
        //getDriver().get(APPLICATION_URL);
    }

    public PageProvider pages() {
        return new PageProvider(getDriver());
    }

    public UiComponentProvider uiComponents() {
        return new UiComponentProvider(getDriver());
    }

    public void openPageByUrl(String url) {getDriver().get(url); }

    @AfterMethod
    public void after() {
        driverService.closeDriver();
    }
}
