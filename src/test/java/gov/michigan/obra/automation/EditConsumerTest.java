package gov.michigan.obra.automation;

import gov.michigan.obra.automation.page.testdata.UserData;
import gov.michigan.obra.automation.page.testdata.UserDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;
import static gov.michigan.obra.automation.page.BasePage.driver;
import static org.testng.Assert.assertEquals;


public class EditConsumerTest extends BaseTest{

    @Test(priority = 0,dataProvider = "User Data",dataProviderClass = UserDataProvider.class)//(dataProvider = "User Data",dataProviderClass = UserDataProvider.class, priority = 1, description = "Verify that a facility user can login to the MI Login application using the correct credentials and create consumer with legal rep")
    public void testEditConsumerWithLegalRep(UserData userData) throws InterruptedException {
        openPageByUrl(APPLICATION_URL);
        String winHandleMiLogin=pages().getMILogin().loginToTPQA(FAC_FU_USERNAME,FAC_FU_PASSWORD);
        //String winHandleMiLogin=pages().getMILogin().navigateToOBRAWindow();
        pages().getConsumer().editConsumer(userData,EDIT_CONSUMER_SSN);
        WebElement unlinkLegRep= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-consumer-detail/section/div[3]/div/div[2]/div/div[2]/div/div"));
        assertEquals("No Legal Representative Assigned",unlinkLegRep.getText());
        // Close the OBRA window, if that window no more required
        driver.close();
        // Switch back to original browser MiLogin (winHandleMiLogin)
        driver.switchTo().window(winHandleMiLogin);

        pages().getMILogOutPage().miLoginTPLogOut();
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
