package gov.michigan.obra.automation;

import com.maxsoft.testngtestresultsanalyzer.DriverHolder;
import com.maxsoft.testngtestresultsanalyzer.TestAnalyzeReportListener;
import gov.michigan.obra.automation.common.PageProvider;
import gov.michigan.obra.automation.common.UiComponentProvider;
import gov.michigan.obra.automation.util.driver.WebDriverFactory;
import gov.michigan.obra.automation.util.driver.WebDriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import static gov.michigan.obra.automation.common.constant.Constants.*;
import static gov.michigan.obra.automation.util.driver.WebDriverHolder.getDriver;
import static gov.michigan.obra.automation.util.driver.WebDriverHolder.setDriver;


@Listeners(TestAnalyzeReportListener.class)
public class BaseWorkerTest {
    private final WebDriverService driverService = new WebDriverFactory().getDriverService();
    private final Logger logger = LogManager.getLogger();

    @BeforeSuite
    public void oneTimeSetup() {
        logger.debug("Test execution browser: {}", BROWSER_NAME);
        logger.debug("Test execution environment: {}", EXECUTION_ENV_NAME);
    }

    @BeforeClass
    public void openBrowser() {
        driverService.spinUpDriver();
        setDriver(driverService.getDriver());
        DriverHolder.setDriver(getDriver());    // Setting the driver for the html-reporter library
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_IN_SECONDS);
        getDriver().manage().window().maximize();
       // getDriver().get(APPLICATION_WORKER_URL);
    }

    public PageProvider pages() {
        return new PageProvider(getDriver());
    }


    public void openPageByUrl(String url) {
        getDriver().get(url);
    }
    public UiComponentProvider uiComponents() {
        return new UiComponentProvider(getDriver());
    }

    @AfterClass
    public void after() {
        driverService.closeDriver();
    }
}
