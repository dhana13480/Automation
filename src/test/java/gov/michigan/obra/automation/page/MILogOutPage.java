/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/12/24, 11:30 AM
 *  
 */

package gov.michigan.obra.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class MILogOutPage extends BasePage{
    public MILogOutPage(WebDriver driver) {
        super(driver);
    }

    public void miWorkerLogOut() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(3);
       // WebElement mainmenu=driver.findElement(By.xpath("//*[@id=\"som-button-dropdown5\"]/button")); ////*[@id="som-button-dropdown5"]
       // action.moveToElement(mainmenu).build().perform();
        WebElement mainmenu=driver.findElement(By.xpath("//*[@id=\"som-button-dropdown5\"]/button/div[2]")); ////*[@id="som-button-dropdown5"]
        action.moveToElement(mainmenu).click().build().perform();
      
        TimeUnit.SECONDS.sleep(3);

        WebElement logout = driver.findElement(By.xpath(".//*[@id=\"logoutLink\"]"));
        TimeUnit.SECONDS.sleep(3);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(logout).click().build().perform();
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"session_continue\"]/div"));
    }

    public void miLoginTPLogOut() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(3);
        WebElement mainmenu=driver.findElement(By.xpath(".//*[@id=\"som-button-dropdown4\"]/button/div[2]")); ////*[@id="som-button-dropdown5"]
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(mainmenu).click().build().perform();

        TimeUnit.SECONDS.sleep(3);

        WebElement logout = driver.findElement(By.xpath(".//*[@id=\"logoutLink\"]"));
        TimeUnit.SECONDS.sleep(3);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(logout).click().build().perform();
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"logoutBtn\"]/div"));
    }

    }
