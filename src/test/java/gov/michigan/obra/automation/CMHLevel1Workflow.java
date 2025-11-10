package gov.michigan.obra.automation;


import static gov.michigan.obra.automation.common.constant.ApplicationConstants.APPLICATION_URL;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.CMH_LOGIN_PASSWORD;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.CMH_LOGIN_USERNAME;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.FAC_FU_PASSWORD;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.FAC_FU_USERNAME;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.WORKER_PASSWORD;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.WORKER_USERNAME;
import static gov.michigan.obra.automation.page.BasePage.driver;
import static gov.michigan.obra.automation.util.Reader.getEnvironmentConfig;

/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/10/24, 11:28 AM
 *
 */

import org.testng.annotations.Test;

import gov.michigan.obra.automation.page.testdata.UserData;
import gov.michigan.obra.automation.page.testdata.UserDataProvider;
//Flow-4
public class CMHLevel1Workflow extends BaseTest{


    @Test(description = "Verify that a user can login to the MI Login application using the correct credentials and register admin user",priority = 1)
    public void testFacilityAdminRegistration() throws InterruptedException {
    	 pages().getMILogin().miLoginToWorker(WORKER_USERNAME,WORKER_PASSWORD);
        pages().getCMHLevel1WorkflowPage().level1PreviewLetter();
    }

    @Test(priority = 4,dataProvider = "User Data",dataProviderClass = UserDataProvider.class,description = "Initiating Level I for CMH",
    		groups = "initiateCMHWorkflow1")
    public void testCMHLevel1WorkflowARR(UserData userData) throws InterruptedException {
        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        pages().getConsumer().CMHLevel1WorkflowARR(userData,getEnvironmentConfig("edit.consumer.with.legalrep.ssn"));
        // Close the OBRA window, if that window no more required
        driver.close();
        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);
        pages().getMILogOutPage().miLoginTPLogOut();
    }
    
    @Test(priority = 5,dataProvider = "User Data",dataProviderClass = UserDataProvider.class,description = "Initiating Level I for CMH",
    		groups = "initiateCMHWorkflow1")
    public void testCMHLevel1WorkflowPAS(UserData userData) throws InterruptedException {
        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        pages().getConsumer().CMHLevel1WorkflowPAS(userData, getEnvironmentConfig("edit.consumer.with.legalrep.ssn"));
        // Close the OBRA window, if that window no more required
        driver.close();
        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);
        pages().getMILogOutPage().miLoginTPLogOut();
    }



}
