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

public class OnDemandPrintDeterminationLetterTest extends BaseTest{


    @Test(priority = 1, description = "Submit to OBRA staff reviewer")
    public void testPrintDeterminationLetter() throws InterruptedException, IOException {
       try {

         openPageByUrl(APPLICATION_WORKER_URL);
         pages().getMILogin().miLoginToWorker("chandrand1","");
         String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        pages().getOnDemandPrintDeterminationLetter().testPrintDeterminationLetter();
       pages().getMILogOutPage().miWorkerLogOut();
       }catch (Exception e) {
    	   pages().getMILogOutPage().miWorkerLogOut();
	}
    }

    

}
