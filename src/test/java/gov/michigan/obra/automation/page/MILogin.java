package gov.michigan.obra.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class MILogin extends BasePage{

    public MILogin(WebDriver driver) {
        super(driver);
    }

    public void miLoginToWorker(String workerUserName, String workerPassword) throws InterruptedException {
        sendKeys(By.id("username"), workerUserName);
        TimeUnit.SECONDS.sleep(3);
        sendKeys(By.id("password"), workerPassword);
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"loginbutton\"]/button"));
        TimeUnit.SECONDS.sleep(3);

        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"acessServiceLink102\"]"));
        TimeUnit.SECONDS.sleep(2);
        clickOnTPTermsCheckBox();
        //navigateToOBRAWindow();
    }


    public String loginToTPQA(String userId, String password) throws InterruptedException {

   //public void miLoginToTP(String userId, String password) throws InterruptedException {

        sendKeys(By.id("userid"), userId);
        TimeUnit.SECONDS.sleep(3);
        sendKeys(By.id("password"), password);
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"login-form\"]/div[1]/div[3]/div/div/label"));
        click(By.xpath("//*[@id=\"loginbutton\"]/button"));
        TimeUnit.SECONDS.sleep(3);
        
        if(driver.findElement(By.id("acessServiceLink81")).isDisplayed()){
            clickOnTPMDHHS();
        }
        TimeUnit.SECONDS.sleep(3);

        clickOnTPTermsCheckBox();

        TimeUnit.SECONDS.sleep(2);
        String winHandleMiLogin = driver.getWindowHandle();
        // Perform the click operation that opens new window
        TimeUnit.SECONDS.sleep(2);
        clickOnLaunchService();

        // Switch to new window opened
        for(String winHandleObra : driver.getWindowHandles()){
            driver.switchTo().window(winHandleObra);
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println("winHandleMiLogin from  navigateToOBRAWindow"+winHandleMiLogin);

        return winHandleMiLogin;
        //navigateToOBRAWindow();

    }



    public String navigateToOBRAWindow() throws InterruptedException {
        // Store the current window handle
        String winHandleMiLogin = driver.getWindowHandle();
        // Perform the click operation that opens new window
        TimeUnit.SECONDS.sleep(2);
        clickOnLaunchService();
        TimeUnit.SECONDS.sleep(2);

        // Switch to new window opened
        for(String winHandleObra : driver.getWindowHandles()){
            driver.switchTo().window(winHandleObra);
        }

        TimeUnit.SECONDS.sleep(10);
        System.out.println("winHandleMiLogin from  navigateToOBRAWindow"+winHandleMiLogin);

        return winHandleMiLogin;
    }
    
    public void miLoginTPLogOut(String mainWindowHandle) throws InterruptedException {
   	 // switch to the first tab
        driver.switchTo().window(mainWindowHandle);
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
