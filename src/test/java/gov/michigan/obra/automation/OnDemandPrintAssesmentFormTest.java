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

public class OnDemandPrintAssesmentFormTest extends BaseTest{


    @Test(priority = 1, description = "Submit to OBRA staff reviewer")
    public void testOnDemandPrintForm() throws InterruptedException, IOException {
    	openPageByUrl(APPLICATION_URL);
        String obraPage =pages().getMILogin().loginToTPQA("cmhdeepaku1039", "Dec@2024");
       try {
    	
        pages().getOnDemandPrintAssesmentForm().testPrintAssesmentForm();
        pages().getMILogin().miLoginTPLogOut(obraPage);
       }catch (Exception e) {
    	   pages().getMILogin().miLoginTPLogOut(obraPage);
	}
    }

    

}
