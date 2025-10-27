package gov.michigan.obra.automation;

/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/10/24, 11:28 AM
 *
 */

import org.testng.annotations.Test;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;

import java.io.IOException;

public class CmhObraReviwerFlowTest extends BaseTest{


    @Test(priority = 1, description = "Submit to OBRA staff reviewer")
    public void testSubmitToObrSataff() throws InterruptedException, IOException {
       try {
    	openPageByUrl(APPLICATION_URL);
        String obraPage =pages().getMILogin().loginToTPQA(CMH_USERNAME, CMH_PASSWORD);
        pages().getCmhObraReviewerFlowPage().submitToObraStaff();
        pages().getMILogin().miLoginTPLogOut(obraPage);
       }catch (Exception e) {
		e.printStackTrace();
	}
    }

    

}
