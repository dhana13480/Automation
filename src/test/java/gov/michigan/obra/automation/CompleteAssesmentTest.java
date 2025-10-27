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

public class CompleteAssesmentTest extends BaseTest{


    @Test(priority = 1, description = "Complete Assessment form for Psychological and SensorimotorForm")
    public void testcompleteAssesment() throws InterruptedException, IOException {
       try {
    	openPageByUrl(APPLICATION_URL);
        String obraPage =pages().getMILogin().loginToTPQA(CMH_USERNAME, CMH_PASSWORD);
        pages().getCompleteAssesmentPage().completeAssesment();
        pages().getMILogin().miLoginTPLogOut(obraPage);
       }catch (Exception e) {
		e.printStackTrace();
	}
    }
/*    
    @Test(priority = 2, description = "Verify that Level IIfor Planned Discharge")
    public void testLevel2PDischargeNotNeeded() throws InterruptedException, IOException {
        openPageByUrl(APPLICATION_URL);
        String obraPage =pages().getMILogin().loginToTPQA(CMH_USERNAME, CMH_PASSWORD);
        pages().getLevelWorkflowPage().level2PDischargeNotNeeded();
        pages().getMILogin().miLoginTPLogOut(obraPage);
    }
    
    @Test(priority = 3, description = "Verify that Level II for Transfer ")
    public void testLevel2TransferNotNeeded() throws InterruptedException, IOException {
        openPageByUrl(APPLICATION_URL);
        String obraPage =pages().getMILogin().loginToTPQA(CMH_USERNAME, CMH_PASSWORD);
        pages().getLevelWorkflowPage().level2TransferNotNeeded();
        pages().getMILogin().miLoginTPLogOut(obraPage);
      }
    @Test(priority = 4, description = "Verify that Level II for Transfer ")
    public void testCreateLevel2() throws InterruptedException, IOException {
        openPageByUrl(APPLICATION_URL);
        String obraPage =pages().getMILogin().loginToTPQA(CMH_USERNAME, CMH_PASSWORD);
        pages().getLevelWorkflowPage().createLevel2();
        pages().getMILogin().miLoginTPLogOut(obraPage);
    }*/
    

}
