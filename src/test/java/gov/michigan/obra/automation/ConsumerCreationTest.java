package gov.michigan.obra.automation;

import gov.michigan.obra.automation.page.testdata.UserData;
import gov.michigan.obra.automation.page.testdata.UserDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;
import static gov.michigan.obra.automation.page.BasePage.driver;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import static gov.michigan.obra.automation.util.Write.setEnvironmentConfig;
import static gov.michigan.obra.automation.util.Reader.getEnvironmentConfig;

//Flow 1
public class ConsumerCreationTest extends BaseTest{
    @Test(dataProvider = "User Data",dataProviderClass = UserDataProvider.class,
    		groups = "initiateLevelIForPAS" )//(dataProvider = "User Data",dataProviderClass = UserDataProvider.class, groups = "Consumer Creation", priority = 1, description = "Verify that a facility user can login to the MI Login application using the correct credentials and create consumer with legal rep")
    public void testCreateConsumer(UserData userData, ITestContext context) throws InterruptedException, IOException {
    	String flowtype = context.getCurrentXmlTest().getParameter("flowType");
        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        //String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        String SSN = pages().getConsumer().createConsumer(FAC_FU_USERNAME,FAC_FU_PASSWORD,userData);
        storeSSN(SSN, flowtype);
        WebElement legalRep=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-consumer-detail/section/div[3]/div/div[2]/div/div[2]/div/div/div[2]"));
        System.out.println("Consumer created and legalrep added successfully with ="+legalRep.getText());
        assertNotNull(legalRep.getText());
        // Close the OBRA window, if that window no more required
        driver.close();
        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);
        pages().getMILogOutPage().miLoginTPLogOut();
    }
    
    @Test(dataProvider = "User Data",dataProviderClass = UserDataProvider.class,
    		groups = {"initiateLevelIForPAS3877","initiateCMHWorkflow1"} )//(dataProvider = "User Data",dataProviderClass = UserDataProvider.class, groups = "Consumer Creation", priority = 1, description = "Verify that a facility user can login to the MI Login application using the correct credentials and create consumer with legal rep")
    public void testCreateConsumerWithoutLegalRep(UserData userData, ITestContext context) throws InterruptedException, IOException {
    	String flowtype = context.getCurrentXmlTest().getParameter("flowType");
        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        //String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        String SSN = pages().getConsumer().createConsumerWithoutLegalRep(FAC_FU_USERNAME,FAC_FU_PASSWORD,userData);
        storeSSN(SSN, flowtype);
        // Close the OBRA window, if that window no more required
        driver.close();
        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);
        pages().getMILogOutPage().miLoginTPLogOut();
    }
    
    
    private void storeSSN(String ssn, String flowType) throws IOException {
    	String keyName = (flowType.equalsIgnoreCase("ssn2")) ? "edit.consumer.without.legalrep.ssn" : "edit.consumer.with.legalrep.ssn";
    	setEnvironmentConfig(keyName, formatAsSSN(ssn));
    	System.out.println("Stored " + keyName + " = " + getEnvironmentConfig(keyName));
    }
    
    private static String formatAsSSN(String input) {
        // If it’s just digits like "123456789", format it
        if (input.matches("\\d{9}")) {
            return input.substring(0, 3) + "-" + input.substring(3, 5) + "-" + input.substring(5);
        }
        // Otherwise, return as-is (already formatted)
        return input;
    }
/*
    @Test(dataProvider = "User Data",dataProviderClass = UserDataProvider.class, dependsOnGroups = "Consumer Creation", priority = 2, description = "Verify that a facility user can login to the MI Login application using the correct credentials and create consumer with legal rep")
    public void testEditConsumer(UserData userData) throws InterruptedException {
        //openPageByUrl("https://obra-sit.state.mi.us/web/portal/#/tp/dashboard/splashpage");
        openPageByUrl(APPLICATION_URL);
        pages().getCreateConsumer().editConsumer(userData);
        WebElement unlinkLegRep= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-consumer-detail/section/div[3]/div/div[2]/div/div[2]/div/div"));
        assertEquals("No Legal Representative Assigned",unlinkLegRep.getText());

    }

   @Test(groups = "Facility User Login", priority = 1, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and edit the existing admin user profile")
    public void testFacilityUserLogin() throws InterruptedException {
        openPageByUrl(APPLICATION_URL);
        pages().getCreateConsumer().createConsumer(FAC_FU_USERNAME,FAC_FU_PASSWORD);
    }

    @Test(dependsOnGroups = "Facility User Login", priority = 2, description = "Verify that a OBRA Staff user can login to the MI Login application using the correct credentials and edit the existing admin user profile")
    public void testCreateConsumer() throws InterruptedException {
        openPageByUrl(MI_LOGIN_TP_SELFSERVICE_URL);
        pages().getCreateConsumer().editConsumer();
    }*/


}
