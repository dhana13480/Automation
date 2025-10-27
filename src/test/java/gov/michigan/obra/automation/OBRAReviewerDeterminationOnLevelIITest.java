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

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;
import static gov.michigan.obra.automation.page.BasePage.driver;
import static org.testng.Assert.assertEquals;


public class OBRAReviewerDeterminationOnLevelIITest extends BaseTest{



   /* @Test(groups = "OBRA Staff Login", priority = 1, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and edit the existing admin user profile")
    public void testOBRAStaffLogin() throws InterruptedException {
       openPageByUrl(APPLICATION_WORKER_URL);
        pages().getMILogin().miLoginToWorker("chandrand1" ,"");
       }
	*/
    @Test(priority = 1 ,description = "Verify OBRA Reviewer makes determination on Level II ")//(groups = "Updating Admin User Profile", priority = 1, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and edit the existing admin user profile")
    public void testpendingQueDeterminationFlow() throws InterruptedException, IOException {
        System.out.println("Execution order test case name is testUpdateExistingAdminUserProfile");

        openPageByUrl(APPLICATION_WORKER_URL);
        pages().getMILogin().miLoginToWorker(WORKER_USERNAME,"");
        String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        //openPageByUrl(OBRA_HOME_WORKER_URL);
      try {
        String status= pages().getOBRAReviewerDeterminationOnLevelII().pendingQueDeterminationFlow();
        TimeUnit.SECONDS.sleep(10);
        assertEquals(status,"C");
      //  pages().getMILogOutPage().miWorkerLogOut();
        // Close the OBRA window, if that window no more required
        driver.close();
        pages().getMILogOutPage().miWorkerLogOut();
      }catch (Exception e) {
    	 // pages().getMILogOutPage().miWorkerLogOut();
	}

    }
    
    //@Test(priority = 2)
    public void logOut() throws InterruptedException{
       //openPageByUrl(MI_LOGIN_SELFSERVICE_URL);
       pages().getMILogOutPage().miWorkerLogOut();
    }
    /*
    
    

    @Test(priority = 1)//(groups = "Updating Facility User Profile", priority = 2, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and edit the existing facility user profile")
    public void testUpdateExistingFacilityUserProfile() throws InterruptedException {
        System.out.println("Execution order test case name is testUpdateExistingFacilityUserProfile");

        openPageByUrl(APPLICATION_WORKER_URL);
        pages().getMILogin().miLoginToWorker(WORKER_USERNAME,WORKER_PASSWORD);
        String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        pages().getOBRAPage().editExistingFacilityUserProfile();
        TimeUnit.SECONDS.sleep(10);
        WebElement userTypeElement=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-detail/div/section/div[3]/div[1]/div/dl/dd[7]"));
        assertEquals(userTypeElement.getText(),"3877/78 Admin");

        // Close the OBRA window, if that window no more required
        driver.close();

        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);
        pages().getMILogOutPage().miWorkerLogOut();

    }*/
    }
