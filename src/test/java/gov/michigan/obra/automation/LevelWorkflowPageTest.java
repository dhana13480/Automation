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
import static gov.michigan.obra.automation.util.Reader.getEnvironmentConfig;

import java.io.IOException;
//Flow 5
public class LevelWorkflowPageTest extends BaseTest{


    @Test(priority = 1, description = "Verify that  Level II Not needed NSMI ")
    public void testLevel2NSMNotNeeded() throws InterruptedException, IOException {
       try {
    	openPageByUrl(APPLICATION_URL);//ProfessionalC8414/Newyork@8414
        String obraPage =pages().getMILogin().loginToTPQA(CMH_USERNAME, CMH_PASSWORD);
        pages().getLevelWorkflowPage().level2NSMNotNeededFlow(getEnvironmentConfig("edit.consumer.with.legalrep.ssn"));
        pages().getMILogin().miLoginTPLogOut(obraPage);
       }catch (Exception e) {
		e.printStackTrace();
	}
    }
    
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
    }
    
    
    @Test(priority = 5, description = "Verify reset ")
    public void testLevel2Reset() throws InterruptedException, IOException {
       try {
    	openPageByUrl(APPLICATION_URL);//ProfessionalC8414/Newyork@8414
        String obraPage =pages().getMILogin().loginToTPQA(CMH_USERNAME, CMH_PASSWORD);
        pages().getLevelWorkflowPage().level2Reset();
        pages().getMILogin().miLoginTPLogOut(obraPage);
       }catch (Exception e) {
		e.printStackTrace();
	}
    }
    
    
    @Test(priority = 6, description = "Verify other ")
    public void testLevel2Other() throws InterruptedException, IOException {
       try {
    	openPageByUrl(APPLICATION_URL);//ProfessionalC8414/Newyork@8414
        String obraPage =pages().getMILogin().loginToTPQA(CMH_USERNAME, CMH_PASSWORD);
        pages().getLevelWorkflowPage().level2Other();
        pages().getMILogin().miLoginTPLogOut(obraPage);
       }catch (Exception e) {
		e.printStackTrace();
	}
    }
    
    @Test(priority = 1, description = "Verify send letter to facility ")
    public void testsendLettertoFacility() throws InterruptedException, IOException {
       try {
    	openPageByUrl(APPLICATION_URL);//ProfessionalC8414/Newyork@8414
    	String obraPage=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        pages().getLevelWorkflowPage().levelSendLetter();
        pages().getMILogin().miLoginTPLogOut(obraPage);
       }catch (Exception e) {
		e.printStackTrace();
	}
    }

}
