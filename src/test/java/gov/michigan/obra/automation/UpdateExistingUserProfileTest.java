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


public class UpdateExistingUserProfileTest extends BaseTest{

/*
    @Test(groups = "OBRA Staff Login", priority = 1, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and edit the existing admin user profile")
    public void testOBRAStaffLogin() throws InterruptedException {
        openPageByUrl(APPLICATION_WORKER_URL);
        pages().getMILogin().miLoginToWorker(WORKER_USERNAME,WORKER_PASSWORD);
       }
*/

    @Test(priority = 0)//(groups = "Updating Admin User Profile", priority = 1, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and edit the existing admin user profile")
    public void testUpdateExistingAdminUserProfile() throws InterruptedException {
        System.out.println("Execution order test case name is testUpdateExistingAdminUserProfile");

        openPageByUrl(APPLICATION_WORKER_URL);
        pages().getMILogin().miLoginToWorker(WORKER_USERNAME,WORKER_PASSWORD);
        String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        //openPageByUrl(OBRA_HOME_WORKER_URL);
        pages().getOBRAPage().editExistingAdminUserProfile();
        TimeUnit.SECONDS.sleep(10);
        WebElement userTypeElement=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-detail/div/section/div[3]/div[1]/div/dl/dd[7]"));
        assertEquals(userTypeElement.getText(),"3877/78 User");

        // Close the OBRA window, if that window no more required
        driver.close();

        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);
        pages().getMILogOutPage().miWorkerLogOut();

    }

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


    }

/*   @Test(dependsOnGroups = "OBRA Staff Login", priority = 4,description = "logout as OBRA staff")
    public void logOut() throws InterruptedException{
       openPageByUrl(MI_LOGIN_SELFSERVICE_URL);
       pages().getMILogOutPage().miWorkerLogOut();
    }*/

    }
