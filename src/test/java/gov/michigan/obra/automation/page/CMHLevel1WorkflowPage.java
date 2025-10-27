/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/26/24, 2:31 PM
 *
 */

package gov.michigan.obra.automation.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CMHLevel1WorkflowPage extends BasePage{
    public CMHLevel1WorkflowPage(WebDriver driver) {
        super(driver);
    }
    public static final String FIRST_NAME = "a";



public void level1PreviewLetter() throws InterruptedException{
	clickOn14daysQueue();
	
	
}








    private void clickOn14daysQueue() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        WebElement userQueue = driver.findElement(By.xpath("//*[@id=\"main\"]/li[14]"));

            Actions actions = new Actions(driver);
            actions.moveToElement(userQueue).click().build().perform();

        //click(By.xpath("//*[@id=\"main\"]/li[20]/a"));
    }
    
    private void clickOnUser(By by,String textToSearch) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        searchAndClickByText(by,textToSearch);
        //click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr"));
    }



}
