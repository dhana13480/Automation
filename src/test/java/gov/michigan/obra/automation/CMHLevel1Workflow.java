package gov.michigan.obra.automation;


import static gov.michigan.obra.automation.common.constant.ApplicationConstants.CMH_LOGIN_PASSWORD;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.CMH_LOGIN_USERNAME;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.WORKER_PASSWORD;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.WORKER_USERNAME;

/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/10/24, 11:28 AM
 *
 */

import org.testng.annotations.Test;

public class CMHLevel1Workflow extends BaseTest{


    @Test(description = "Verify that a user can login to the MI Login application using the correct credentials and register admin user",priority = 1)
    public void testFacilityAdminRegistration() throws InterruptedException {
    	 pages().getMILogin().miLoginToWorker(WORKER_USERNAME,WORKER_PASSWORD);
        pages().getCMHLevel1WorkflowPage().level1PreviewLetter();
    }




}
