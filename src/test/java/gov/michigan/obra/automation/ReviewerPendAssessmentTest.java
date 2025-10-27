package gov.michigan.obra.automation;/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/30/24, 11:06 AM
 *
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;
import static gov.michigan.obra.automation.page.BasePage.driver;
import static org.testng.Assert.assertEquals;


public class ReviewerPendAssessmentTest extends BaseWorkerTest{

    @Test(groups = "OBRA Staff Login", priority = 1, description = "OBRA Staff Login")
    public void testOBRAStaffLogin() throws InterruptedException {
        openPageByUrl(APPLICATION_WORKER_URL);
        pages().getMILogin().miLoginToWorker("chandrand1" ,"");
       }

    
    @Test(dependsOnGroups = "OBRA Staff Login", priority = 2, description = "OBRA Reviewer pends an assessment form to obtain more information from Assessor ")
    public void testreviewerPendingAssesment() throws InterruptedException {
        openPageByUrl(OBRA_HOME_WORKER_URL);
        
        pages().getOBRAPage().reviewerPendingAssesment();
        TimeUnit.SECONDS.sleep(10);
       // WebElement userTypeElement=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-detail/div/section/div[3]/div[1]/div/dl/dd[7]"));
       // assertEquals(userTypeElement.getText(),"3877/78 User");
    }

   @Test(dependsOnGroups = "OBRA Staff Login", priority = 4,description = "logout as OBRA staff")
    public void logOut() throws InterruptedException{
       openPageByUrl(MI_LOGIN_SELFSERVICE_URL);
       pages().getMILogOutPage().miWorkerLogOut();
    }

    }
