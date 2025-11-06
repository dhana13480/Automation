package gov.michigan.obra.automation;

import gov.michigan.obra.automation.page.testdata.UserData;
import gov.michigan.obra.automation.page.testdata.UserDataProvider;
import org.testng.annotations.Test;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;
import static gov.michigan.obra.automation.page.BasePage.driver;

public class InitiateLevelIFormForPASTest extends BaseTest{

    @Test(priority = 3,dataProvider = "User Data",dataProviderClass = UserDataProvider.class,description = "Initiating Level I for PAS",
    		groups = "initiateLevelIForPAS")
    public void testInitiateLevelIForPAS(UserData userData) throws InterruptedException {
        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        pages().getConsumer().initiateLevelIForPAS(userData,EDIT_CONSUMER_SSN);
        // Close the OBRA window, if that window no more required
        driver.close();
        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);
        pages().getMILogOutPage().miLoginTPLogOut();
    }



    @Test(priority = 1,dataProvider = "User Data",dataProviderClass = UserDataProvider.class,description = "Initiating 3878 for Level I for PAS",
    		groups = "initiateLevelIForPAS3877")
    public void testInitiateLevelIForPAS3878(UserData userData) throws InterruptedException {
        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        pages().getConsumer().initiateLevelIForPAS3878(userData, EDIT_CONSUMER_WITHOUT_LEGALREP_SSN);
        // Close the OBRA window, if that window no more required
        driver.close();
        // Switch back to original browser MiLogin
        driver.switchTo().window(winHandleMiLogin);
        pages().getMILogOutPage().miLoginTPLogOut();
    }

}
