package gov.michigan.obra.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class OBRALogOutPage extends BasePage{
    public OBRALogOutPage(WebDriver driver) {
        super(driver);
    }

    public void obrasuccessPageLogout()  throws InterruptedException {
        clickOnMenuToLogOut();
        clickOnLogoutOnSuccessPage();
    }

    private void clickOnLogoutOnSuccessPage() {
        click(By.xpath("//*[@id=\"nav-main\"]/div/div[2]/ul/li[9]/a"));
    }

    private void clickOnMenuToLogOut() {
       click(By.xpath("//*[@id=\"nav-main\"]/div/div[2]/button[2]"));
    }

    public void obraLogOut() throws InterruptedException {
        clickOnMenu();
        TimeUnit.SECONDS.sleep(3);
        clickOnLogOut();
        TimeUnit.SECONDS.sleep(2);
      //  navigateToMILoginWindow();

    }

    private void navigateToMILoginWindow() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);

        driver.get("https://miloginworkerqa.michigan.gov/uisecure/selfservice");
        TimeUnit.SECONDS.sleep(2);


    }

    private void clickOnLogOut() {
        click(By.xpath("//*[@id=\"nav-main\"]/div/div[3]/ul/li[12]/a"));
    }

    private void clickOnMenu() {
        click(By.xpath("//*[@id=\"nav-main\"]/div/div[3]/button[2]/span[1]")); ////*[@id="nav-main"]/div/div[2]/button[2]
    }


}
