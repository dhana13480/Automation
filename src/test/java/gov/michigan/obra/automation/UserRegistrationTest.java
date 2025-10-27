package gov.michigan.obra.automation;

/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/10/24, 11:28 AM
 *
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;
import static gov.michigan.obra.automation.page.BasePage.driver;
import static org.testng.Assert.assertEquals;

public class UserRegistrationTest extends BaseTest{

    @Test(priority = 0,description = "Verify that a user can login to the MI Login application using the correct credentials and register admin user")//groups = "Facility Admin", priority = 0, description = "Verify that a user can login to the MI Login application using the correct credentials and register admin user")
    public void testUserFacilityAdminRegistartion() throws InterruptedException, FileNotFoundException {
        String successMessage="Your Registration to OBRA Application is submitted successfully and is under review. Please allow 24-48 hours for the OBRA Administrator to make a decision.";
        boolean isPDFContentValidated=false;
        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_AD_USERNAME,FAC_AD_PASSWORD);
       // String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        //System.out.println("winHandleMiLogin from loginToTPQA"+winHandleMiLogin);
        isPDFContentValidated=pages().getUserRegistration().registerFacilityAdmin();
        System.out.println("isPDFContentValidated from testUserFacilityAdminRegistartion"+isPDFContentValidated);

        WebElement succMsg= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-reg-success/div/section/div[2]/div/div/div[2]/p"));
        assertEquals(successMessage,succMsg.getText());

        // Close the OBRA window, if that window no more required
        driver.close();

        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);

        pages().getMILogOutPage().miLoginTPLogOut();
        assertEquals(isPDFContentValidated,true);

    }

    @Test(priority = 1,description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and approve the users")//(dependsOnGroups = "Facility Admin", priority = 1, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and approve the users")
    public void testAdminUserApproval() throws InterruptedException {
        openPageByUrl(APPLICATION_WORKER_URL);
        pages().getMILogin().miLoginToWorker(WORKER_USERNAME,WORKER_PASSWORD);
        String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        pages().getUserApprovalPage().approveAdminUser(WORKER_USERNAME,WORKER_PASSWORD);
        // Close the OBRA window, if that window no more required
        driver.close();

        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);

        pages().getMILogOutPage().miWorkerLogOut();
    }



    @Test(priority = 2)//(groups = "Facility User Registration",priority = 2, description = "Verify that a user can login to the MI Login application using the correct credentials")
    public void testFacilityUserRegistration() throws InterruptedException {
        System.out.println("Execution order test case name is testFacilityUserRegistration");

        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME, FAC_FU_PASSWORD);
        //String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        System.out.println("winHandleMiLogin from loginToTPQA"+winHandleMiLogin);
        pages().getUserRegistration().registerFacilityUser();
        //WebElement succMsg= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-reg-success/div/section/div[2]/div/div/div[2]/p"));
        //assertEquals(successMessage,succMsg.getText());

        // Close the OBRA window, if that window no more required
        driver.close();

        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);

        pages().getMILogOutPage().miLoginTPLogOut();

    }

    @Test(priority = 3)//(dependsOnGroups = "Facility User Registration",priority = 3, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and approve the users")
    public void testFacilityUserApproval() throws InterruptedException {
        System.out.println("Execution order test case name is testFacilityUserApproval");

        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_AD_USERNAME,FAC_AD_PASSWORD);
        //String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        pages().getUserApprovalPage().approveFacilityUser();
        //WebElement succMsg= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-reg-success/div/section/div[2]/div/div/div[2]/p"));
        //assertEquals(successMessage,succMsg.getText());
        // Close the OBRA window, if that window no more required
        driver.close();

        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);

        pages().getMILogOutPage().miLoginTPLogOut();

    }

    @Test(priority = 4)//(groups = "CMH User Registration",priority = 4, description = "Verify that a user can login to the MI Login application using the correct credentials")
    public void testCmhUserRegistration() throws InterruptedException {
        System.out.println("Execution order test case name is testCmhUserRegistration");

        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_CMH_ADUSERNAME, FAC_CMH_ADPASSWORD);
        //String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        pages().getUserRegistration().registerCMHUser();
        //WebElement succMsg= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-reg-success/div/section/div[2]/div/div/div[2]/p"));
        //assertEquals(successMessage,succMsg.getText());
        // Close the OBRA window, if that window no more required
        driver.close();

        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);

        pages().getMILogOutPage().miLoginTPLogOut();
    }



   /* @Test(priority = 2)
    public void logOutTP() throws InterruptedException{
        //pages().getOBRALogOutPage().obraLogOut();
        openPageByUrl("https://milogintpqa.michigan.gov/uisecure/tpselfservice");
        pages().getMILogOutPage().miLoginTPLogOut();
    }

    @Test(description = "Verify that a user can logout to the MI Login application",priority = 4)
    public void testLogOut() throws InterruptedException {
        pages().getMILogOutPage().miWorkerLogOut();
        System.out.println("driver.getTitle() is ="+driver.getTitle());
    }

    @Test(description = "Verify that a user can login to the MI Login application using the correct credentials",priority = 5)
    public void testInValidMILogin() throws InterruptedException {
        pages().getMILoginPage().miInValidLoginUser(INVALID_LOGIN_USERNAME,INVALID_LOGIN_PASSWORD);

    }

        @Test(priority = 2)
    public void logOutTP() throws InterruptedException{
        //pages().getOBRALogOutPage().obraLogOut();
        //openPageByUrl("https://milogintpqa.michigan.gov/uisecure/tpselfservice");
        pages().getMILogOutPage().miLoginTPLogOut();
    }

       /* @Test(dependsOnGroups = "Facility Admin", priority = 3)
    public void logOut() throws InterruptedException{
        //pages().getOBRALogOutPage().obraLogOut();
        openPageByUrl("https://miloginworkerqa.michigan.gov/uisecure/selfservice");
        pages().getMILogOutPage().miWorkerLogOut();
    }*/


/*    @Test(groups = "Validate PDF", priority = 1, description = "Verify that a user can login to the MI Login application using the correct credentials and register admin user")
    public void testValidatePDF() throws InterruptedException, IOException {
        String pdfFile="src/test/resources/uploads/sample-authorization-letter.pdf";
        PDDocument doc=PDDocument.load(new File(pdfFile));
        PDFTextStripper pdfStripper=new PDFTextStripper();
        String pdfText=pdfStripper.getText(doc);
        System.out.println("pdfContent is "+pdfText);
        if(pdfText.contains("Long Term Care Operations/OBRA Programs Long Term")){
            System.out.println("PDF is not empty and validated....!");
        }else if(pdfText.isBlank() || pdfText.isEmpty()){
            System.out.println("PDF is empty/blank and validated....!");
        }
//        PDDocument document=PDDocument.load(new File(pdfFile));

    }*/

    /*
        @Test(groups = "MI Worker Login", priority = 2, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and approve the users")
        public void testMiWorkerLogin() throws InterruptedException {
            openPageByUrl(APPLICATION_WORKER_URL);
            pages().getMILogin().loginToWorker(WORKER_USERNAME,WORKER_PASSWORD);
           // pages().getMILogOutPage().miWorkerLogOut();
        }
    */
/*    @Test(groups = "Facility Admin", priority = 1, description = "Verify that a user can login to the MI Login application using the correct credentials and register admin user")
    public void testFacilityAdminRegistration() throws InterruptedException {
        String successMessage="Your Registration to OBRA Application is submitted successfully and is under review. Please allow 24-48 hours for the OBRA Administrator to make a decision.";
        openPageByUrl(APPLICATION_URL);
        //pages().getMILogin().loginToTPQA(FAC_AD_USERNAME, FAC_AD_PASSWORD);
        pages().getUserRegistration().miLoginAsFacilityUser(FAC_AD_USERNAME, FAC_AD_PASSWORD);
        WebElement succMsg= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-reg-success/div/section/div[2]/div/div/div[2]/p"));
        assertEquals(successMessage,succMsg.getText());
        pages().getMILogOutPage().miLoginTPLogOut();

    }*/



}
