package gov.michigan.obra.automation;

import gov.michigan.obra.automation.page.testdata.UserData;
import gov.michigan.obra.automation.page.testdata.UserDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;
import static gov.michigan.obra.automation.page.BasePage.driver;
import static gov.michigan.obra.automation.util.Write.setEnvironmentConfig;
import static org.testng.Assert.assertEquals;

import java.io.IOException;


public class EditConsumerTest extends BaseTest{

    @Test(priority = 2,dataProvider = "User Data",dataProviderClass = UserDataProvider.class,
    		groups = "initiateLevelIForPAS")//(dataProvider = "User Data",dataProviderClass = UserDataProvider.class, priority = 1, description = "Verify that a facility user can login to the MI Login application using the correct credentials and create consumer with legal rep")
    public void testEditConsumerWithLegalRep(UserData userData) throws InterruptedException, IOException {
        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        //String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        String SSN = pages().getConsumer().editConsumer(userData,EDIT_CONSUMER_SSN);
        storeSSN(SSN);
        WebElement unlinkLegRep= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-consumer-detail/section/div[3]/div/div[2]/div/div[2]/div/div"));
        assertEquals("No Legal Representative Assigned",unlinkLegRep.getText());
        // Close the OBRA window, if that window no more required
        driver.close();
        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);

        pages().getMILogOutPage().miLoginTPLogOut();
    }
    
    private void storeSSN(String ssn) throws IOException {
    	setEnvironmentConfig("edit.consumer.with.legalrep.ssn", formatAsSSN(ssn));
    	System.out.println("Stored " + "edit.consumer.with.legalrep.ssn" + " = " + ssn);
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

    @Test(dataProvider = "User Data",dataProviderClass = UserDataProvider.class, priority = 2, description = "Verify that a facility user can login to the MI Login application using the correct credentials and create consumer with legal rep")
    public void testEditConsumerWithoutLegalRep(UserData userData) throws InterruptedException {
        openPageByUrl(APPLICATION_URL);
        pages().getMILogin().miLoginToTP(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        pages().getConsumer().editConsumerWithoutLegalRep(userData);
        WebElement unlinkLegRep= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-consumer-detail/section/div[3]/div/div[2]/div/div[2]/div/div"));
        assertEquals("No Legal Representative Assigned",unlinkLegRep.getText());
        // Close the OBRA window, if that window no more required
        driver.close();
        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);

        pages().getMILogOutPage().miLoginTPLogOut();


    }
*/

}
