package gov.michigan.obra.automation;


import static gov.michigan.obra.automation.common.constant.ApplicationConstants.APPLICATION_URL;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.CMH_PASSWORD;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.CMH_USERNAME;
import static gov.michigan.obra.automation.common.constant.ApplicationConstants.MI_LOGIN_SELFSERVICE_URL;

import java.io.IOException;

/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/10/24, 11:28 AM
 *
 */

import org.testng.annotations.Test;

public class CMHReqSubmiteToOBRAReviewerTest extends BaseTest{


    @Test(description = "Verify CMH submits requested information back to OBRA Reviewer",priority = 1)
    public void testFacilityAdminRegistration() throws InterruptedException, IOException {
    	openPageByUrl(APPLICATION_URL);
    	pages().getMILogin().loginToTPQA("cmhdeepaku1039", "Dec@2024");
    	//pages().getMILogin().loginToTPQA("AutomationT2025", "Tester123!");
    	try {
    	pages().getCMHReqSubmiteToOBRAReviewerPage().pendingQueFlow();
        pages().getMILogOutPage().miLoginTPLogOut();
    	}catch (Exception e) {
    		pages().getMILogOutPage().miLoginTPLogOut();
		}
    }




}
