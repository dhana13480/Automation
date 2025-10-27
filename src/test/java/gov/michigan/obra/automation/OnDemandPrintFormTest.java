package gov.michigan.obra.automation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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

public class OnDemandPrintFormTest extends BaseTest{

	

    @Test(priority = 1, description = "Verify On Demand Print 3877 and 3878 Forms ")
    public void testOnDemandPrintForm() throws InterruptedException, IOException {
    	openPageByUrl(APPLICATION_URL);
    	String obraPage =pages().getMILogin().loginToTPQA("FacilityW1234", "Zxcv@333");
    	try {
    	
        //String obraPage =pages().getMILogin().loginToTPQA("FacilityW1234", "Zxcv@333");
        pages().getOnDemandPrintFormPage().testPrint7877Form();
        pages().getMILogin().miLoginTPLogOut(obraPage);
       }catch (Exception e) {
    	   pages().getMILogin().miLoginTPLogOut(obraPage);
	}
    }

    

}
