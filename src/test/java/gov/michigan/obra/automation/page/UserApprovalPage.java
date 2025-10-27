/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/26/24, 2:31 PM
 *
 */

package gov.michigan.obra.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class UserApprovalPage extends BasePage{
    public UserApprovalPage(WebDriver driver) {
        super(driver);
    }
    public static final String FIRST_NAME = "a";

        public void approveAdminUser(String workerUserName,String workerPassword) throws InterruptedException {
// Perform the actions on new window
            TimeUnit.SECONDS.sleep(5);
            WebElement userQueue = driver.findElement(By.xpath("//*[@id=\"main\"]/li[20]/a"));
            clickOnUserQueue(userQueue);
            clickOnUser(By.xpath("//*[@id=\"gridTable\"]/tbody/tr"),"");
            selectStatus(By.id("status"),"A");
            approveUser();
        }
    //TODO Approve Facility User
    public void approveFacilityUser() throws InterruptedException {
        // Perform the actions on new window
        TimeUnit.SECONDS.sleep(5);
        WebElement userQueue = driver.findElement(By.xpath("//*[@id=\"main\"]/li[20]/a"));

        clickOnUserQueue(userQueue);

        clickOnUser(By.xpath("//*[@id=\"gridTable\"]/tbody/tr"),"");
        selectStatus(By.id("status"),"A");
        approveFacilityTypeUser();
    }

    private void approveFacilityTypeUser() throws InterruptedException {
        //TimeUnit.SECONDS.sleep(5);
        //click(By.xpath("//*[@id=\"main_content\"]/app-approve-user/div/section/form/div[11]/button[1]"));

        TimeUnit.SECONDS.sleep(5);
        //click(By.id("add_user_save_btn"));
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-approve-user/div/section/form/div[12]/button[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(saveButton).click().build().perform();
        System.out.println("saveButton clicked ? = "+saveButton.isSelected());
        TimeUnit.SECONDS.sleep(5);
    }

    private void approveUser() throws InterruptedException {
        //TimeUnit.SECONDS.sleep(5);
        //click(By.xpath("//*[@id=\"main_content\"]/app-approve-user/div/section/form/div[11]/button[1]"));

        TimeUnit.SECONDS.sleep(5);
        //click(By.id("add_user_save_btn"));
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-approve-user/div/section/form/div[11]/button[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(saveButton).click().build().perform();
        System.out.println("saveButton clicked ? = "+saveButton.isSelected());
        TimeUnit.SECONDS.sleep(5);
    }

    private void clickOnUser(By by,String textToSearch) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        searchAndClickByText(by,textToSearch);
        //click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr"));
    }




  /*  public void editExistingUserProfile (String workerUserName,String workerPassword) throws InterruptedException {
        loginToWorkerAsOBRAStaff(workerUserName, workerPassword);
        clickOnOBRAMDHHS();
        clickOnUsersModule();
        inputFirstNameAndUserTypeSearch(FIRST_NAME);
        clickOnUserRow();
        clickOnEditUserDropDown();
        editUserTypeToFacility();
        clearSendKeys(By.xpath("//*[@id=\"add_position\"]"),"Resident Physician");
        editUserRoleForFacilityUser();
        clearSendKeys(By.xpath("//*[@id=\"license\"]"),"123456789");
        clearFacilityAndSelectOne();
        clearQualificationAndSelectOne();
        editStatus();
    }
    private void clearQualificationAndSelectOne() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userQualificationsDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[8]/div/div/angular2-multiselect/div/div[1]/div"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userQualificationsDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);
        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[8]/div/div/angular2-multiselect/div/div[2]/div[3]/div[1]/label[2]/span[1]"));
        click(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[8]/div/div/angular2-multiselect/div/div[2]/div[3]/div[1]/label[2]/span[2]"));

        WebElement userQualification = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[8]/div/div/angular2-multiselect/div/div[2]/div[3]/div[3]/ul/li[1]/label[2]"));
        TimeUnit.SECONDS.sleep(2);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(userQualification).click().build().perform();
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("/html/body/app-root/div[2]/div/app-footer/footer/div/div/div/div")).click();
        System.out.println("Touched"+driver.findElement(By.xpath("/html/body/app-root/div[2]/div/app-footer/footer/div/div/div/div")).isDisplayed());

    }
  */ /* private void editStatus() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Select objSelect = new Select(driver.findElement(By.id("status")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("A");
        TimeUnit.SECONDS.sleep(3);

    }

    private void editUserRoleForFacilityUser() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userRoleDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[6]/div[1]/div/angular2-multiselect/div/div[1]/div"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userRoleDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);

        //WebElement adminUserRole = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[1]/div[1]/div/angular2-multiselect/div/div[2]/div[3]/div[2]/ul/li[2]/label[2]"));
        WebElement facilityUserRole = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[6]/div[1]/div/angular2-multiselect/div/div[2]/div[3]/div[3]/ul/li[3]/label[2]"));
        TimeUnit.SECONDS.sleep(2);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(facilityUserRole).click().build().perform();
        TimeUnit.SECONDS.sleep(2);
    }
    private void clearFacilityAndSelectOne() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement facilityDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[7]/div/div/angular2-multiselect/div/div[1]/div"));
        action.moveToElement(facilityDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[7]/div/div/angular2-multiselect/div/div[2]/div[3]/div[1]/label[2]/span[1]"));
        click(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[7]/div/div/angular2-multiselect/div/div[2]/div[3]/div[1]/label[2]/span[2]"));

        sendKeys(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[7]/div/div/angular2-multiselect/div/div[2]/div[3]/div[2]/input"), "sparrow");
        WebElement selectAllFilteredValues = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[7]/div/div/angular2-multiselect/div/div[2]/div[3]/div[3]/div/label/span[1]"));

        action.moveToElement(selectAllFilteredValues).click().build().perform();
        TimeUnit.SECONDS.sleep(2);

    }

    private void editUserTypeToFacility() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("7");
    }

    private void clickOnEditUserDropDown() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userProfileDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-detail/div/section/div[2]/button[2]"));
        action.moveToElement(userProfileDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);
        WebElement editUserLink = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-user-detail/div/section/div[2]/ul/li[1]"));

        action.moveToElement(editUserLink).click().build().perform();
    }


    private void clickOnUserRow() {
            click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[3]"));
    }

    private void inputFirstNameAndUserTypeSearch(String firstName) throws InterruptedException {
        sendKeys(By.id("firstName"), firstName);
        selectUserTypeForUserSearch();
        click(By.xpath("//*[@id=\"main_content\"]/app-users/app-user-search/div/section/form/div/div[6]/div/button[1]"));

    }

    private void selectUserTypeForUserSearch() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("6");
    }



        private void clickOnUsersModule() {
        click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[3]/div[2]/a/h2"));
    }

*//*    private void clickOnOBRAMDHHS() {
        click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[1]"));
    }*//*

    private void loginToWorkerAsOBRAStaff(String workerUserName, String workerPassword) throws InterruptedException {
        inputUserName(workerUserName);
        TimeUnit.SECONDS.sleep(3);
        inputUserPassword(workerPassword);
        TimeUnit.SECONDS.sleep(3);
        clickMILoginButton();
        TimeUnit.SECONDS.sleep(3);

        clickOnMDHHSOBRA();
        clickOnTermsCheckBox();
        navigateToOBRAWindow();
    }
*/

    /*

    private void loginToWorkerAsAdminAndApproveFacilityUser(String adminUserName, String adminPassword) throws InterruptedException {
        inputUserId(adminUserName);
        TimeUnit.SECONDS.sleep(3);
        inputUserPassword(adminPassword);
        TimeUnit.SECONDS.sleep(3);
        clickMILoginButton();
        TimeUnit.SECONDS.sleep(3);
        clickOnMDHHSOBRAForAdminUser();
        clickOnTermsCheckBox();
        navigateToOBRAWindowAndApproveFacilityUser();
    }
*/
/*
    private void inputUserName(String userName) {
        sendKeys(By.id("username"), userName);
    }



    private void inputUserId(String userId) {
        sendKeys(By.id("userid"), userId);
    }

    private void inputUserPassword(String password) {
        sendKeys(By.id("password"), password);
    }

    private void clickMILoginButton() {
        //click(By.xpath("//*[@id=\"loginbutton\"]"));
        click(By.xpath("//*[@id=\"loginbutton\"]/button"));
    }
*/

   /* private void navigateToOBRAWindow() throws InterruptedException {
        // Store the current window handle
        String winHandleMiLogin = driver.getWindowHandle();

        // Perform the click operation that opens new window
        TimeUnit.SECONDS.sleep(5);
        clickOnLaunchService();
        TimeUnit.SECONDS.sleep(5);

        // Switch to new window opened
        for(String winHandleObra : driver.getWindowHandles()){
            driver.switchTo().window(winHandleObra);
        }

        // Perform the actions on new window
        TimeUnit.SECONDS.sleep(5);
        clickOnUserQueue();

        clickOnUser(By.xpath("//*[@id=\"gridTable\"]/tbody/tr"),"");
        selectStatus();
        approveUser();
        //System.out.println("Before Clicking on UserQueue Window is "+driver.getWindowHandle());
        //clickOnUserQueue();
        //registerAdminUser();

// Close the new window, if that window no more required
        driver.close();

// Switch back to original browser (first window)
        driver.switchTo().window(winHandleMiLogin);

// Continue with original browser (first window)
    }

    private void navigateToOBRAWindowAndApproveFacilityUser() throws InterruptedException {
        // Store the current window handle
        String winHandleMiLogin = driver.getWindowHandle();

        // Perform the click operation that opens new window
        TimeUnit.SECONDS.sleep(5);
        clickOnLaunchService();
        TimeUnit.SECONDS.sleep(5);

        // Switch to new window opened
        for(String winHandleObra : driver.getWindowHandles()){
            driver.switchTo().window(winHandleObra);
        }

        // Perform the actions on new window
        TimeUnit.SECONDS.sleep(5);
        clickOnUserQueue();

        clickOnUser(By.xpath("//*[@id=\"gridTable\"]/tbody/tr"),"");
        selectStatus();
        approveFacilityUser();
        //System.out.println("Before Clicking on UserQueue Window is "+driver.getWindowHandle());
        //clickOnUserQueue();
        //registerAdminUser();

// Close the new window, if that window no more required
        driver.close();

// Switch back to original browser (first window)
        driver.switchTo().window(winHandleMiLogin);

// Continue with original browser (first window)
    }
*/

/*

    private void clickOnMDHHSOBRA() throws InterruptedException {
            TimeUnit.SECONDS.sleep(5);
            click(By.xpath("//*[@id=\"acessServiceLink102\"]"));
            TimeUnit.SECONDS.sleep(5);
        }

    private void clickOnMDHHSOBRAForAdminUser() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"acessServiceLink81\"]"));
        TimeUnit.SECONDS.sleep(5);
    }

    private static void clickOnTermsCheckBox() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section/div/div[2]/div/som-card/div/div/div[2]")).click();
        WebElement checkBox = driver.findElement(By.name("terms"));
        Actions action = new Actions(driver);
        action.moveToElement(checkBox).click().build().perform();
        System.out.println("Terms and Conditions Checked ? = "+driver.findElement(By.name("terms")).isSelected());
    }
*/

/*    private void clickOnLaunchService() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"launchServiceBtn\"]"));//acessServiceLink81
    }*/

}
