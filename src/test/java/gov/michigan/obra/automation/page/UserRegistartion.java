package gov.michigan.obra.automation.page;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import gov.michigan.obra.automation.common.constant.ApplicationConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class UserRegistartion extends BasePage{
    public UserRegistartion(WebDriver driver) {
        super(driver);
    }
    public boolean registerFacilityAdmin() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        clickOnRegister();
        TimeUnit.SECONDS.sleep(5);
        sendKeys(By.xpath("//*[@id=\"positionTitle\"]"), "AutomationTestAdmin");
        //selectAdminUserType;
        selectDropDownByValue(By.id("userType"),"6");
        selectUserRole(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[1]/div[1]/div/angular2-multiselect/div"),By.xpath(".//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[1]/div[1]/div/angular2-multiselect/div/div[2]/div[3]/div[2]/ul/li[1]/label[2]"));
        //selectFacilityGroup
        selectDropDownByVisibleText(By.id("facilityGroupId"),"Sparrow");
        boolean isPDFContentValid=clickOnGenerateAuthDoc();
        System.out.println("isPDFContentValid from registerFacilityAdmin"+isPDFContentValid);

        checkAcknowledgeButton(By.xpath("//*[@id=\"signature\"]"));
        TimeUnit.SECONDS.sleep(5);
        saveButton(By.id("user_save_btn"));
        //submitRegisterUserForm();
        return isPDFContentValid;
    }
    public void registerFacilityUser() throws InterruptedException {
        clickOnRegister();
        sendKeys(By.xpath("//*[@id=\"positionTitle\"]"), "AutomationTestFacilityUser");
        //selectFacilityUserType();
        selectDropDownByValue(By.id("userType"),"7");
        selectUserRoleForFacilityUser();
        sendKeys(By.xpath("//*[@id=\"license\"]"), "automation_test_license");
        //selectFacilities();
        //multiSelect();
        //angularMultiSelectDropdown();
        searchAndMultiSelect();
        selectQualifications();
        //checkCMHUserRegTermsAndConditionsBox();
        checkAcknowledgeButton(By.xpath("//*[@id=\"signature\"]"));

        TimeUnit.SECONDS.sleep(5);
        submitFacilityUserRegistrationForm();

    }

    public void registerCMHUser() throws InterruptedException {
        clickOnRegister();
        sendKeys(By.xpath("//*[@id=\"positionTitle\"]"), "AutomationTestCMHUser");
        selectCMHUserType();
        selectCMHBoard();
        selectAgency();
        //checkCMHUserRegTermsAndConditionsBox();
        checkAcknowledgeButton(By.xpath("//*[@id=\"signature\"]"));

        TimeUnit.SECONDS.sleep(5);
        //submitRegisterUserForm();
        saveButton(By.id("user_save_btn"));
    }

    private void selectAgency() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("agenciesId")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("1: 348");
    }

    private void selectCMHBoard() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("cmhBoardsId")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("1: 2");
    }

    private void selectCMHUserType() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        //objSelect.selectByIndex(index);
        //objSelect.selectByIndex(index);
// Or can be used as
        //objSelect.selectByVisibleText(text);
        //objSelect.selectByVisibleText(text);
// Or can be used as
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("3");
    }

    private void submitFacilityUserRegistrationForm() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        //click(By.id("add_user_save_btn"));
        WebElement saveButton = driver.findElement(By.id("user_save_btn"));
        Actions action = new Actions(driver);
        action.moveToElement(saveButton).click().build().perform();
        System.out.println("saveButton clicked ? = "+saveButton.isSelected());
        TimeUnit.SECONDS.sleep(5);
    }

    private boolean clickOnGenerateAuthDoc() throws InterruptedException {

        boolean isPdfContentValid=false;
        // Store the current window handle
        String winHandleObraPage = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(3);
        // Perform the click operation that opens new window
        click(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[7]/div/div/span/a"));
        System.out.println("Window winHandleObraPage name"+driver.getTitle());

        String windHandleCurrent = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(3);

        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        for(int i =0;i<windows.size();i++ ) {
            String aWindow = windows.get(i);
            if(aWindow != windHandleCurrent) {
                driver.switchTo().window(aWindow);
            }
        }
        System.out.println("url  :  "+driver.getCurrentUrl());

        TimeUnit.SECONDS.sleep(3);
        isPdfContentValid=verifyPDFContent(driver.getCurrentUrl(), ApplicationConstants.ADMIN_USER_REGISTRATION_PDF_EXPECTED_CONTENT,"AuthorizationLetter.pdf");
        System.out.println("isPdfContentValid from clickOnGenerateAuthDoc"+isPdfContentValid);

        // switch to the first tab
        driver.switchTo().window(windHandleCurrent);
        TimeUnit.SECONDS.sleep(3);
        // Switch to new window opened
        for(String winHandleAuthDoc : driver.getWindowHandles()){
            driver.switchTo().window(winHandleAuthDoc);
            System.out.println("Window winHandleAuthDoc name"+driver.getTitle());
        }
        TimeUnit.SECONDS.sleep(3);
        // Close the new window, if that window no more required
        driver.close();
        TimeUnit.SECONDS.sleep(3);
        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleObraPage);

        return isPdfContentValid;

    }

    private void selectFacilityUserType() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("7");
    }

    private void searchAndMultiSelect() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userRoleDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect/div"));
        action.moveToElement(userRoleDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);
        sendKeys(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect/div/div[2]/div[3]/div[1]/input"), "sparrow");
        WebElement selectAllFilteredValues = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect/div/div[2]/div[3]/div[2]/div/label/span[1]"));

        action.moveToElement(selectAllFilteredValues).click().build().perform();
    }
    private void selectQualifications() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userQualificationsDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[6]/div/div/angular2-multiselect/div"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userQualificationsDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);

        WebElement userQualification = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[6]/div/div/angular2-multiselect/div/div[2]/div[3]/div[3]/ul/li[1]/label[2]"));
        TimeUnit.SECONDS.sleep(2);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(userQualification).click().build().perform();
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("/html/body/app-root/div[2]/div/app-footer/footer/div/div/div/div")).click();
        System.out.println("Touched"+driver.findElement(By.xpath("/html/body/app-root/div[2]/div/app-footer/footer/div/div/div/div")).isDisplayed());

    }


    private void selectUserRoleForFacilityUser() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userRoleDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[1]/div[1]/div/angular2-multiselect/div"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userRoleDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);

        //WebElement adminUserRole = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[1]/div[1]/div/angular2-multiselect/div/div[2]/div[3]/div[2]/ul/li[2]/label[2]"));
        WebElement adminUserRole = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[1]/div[1]/div/angular2-multiselect/div/div[2]/div[3]/div[2]/ul/li[1]/label[2]"));
        TimeUnit.SECONDS.sleep(2);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(adminUserRole).click().build().perform();
        TimeUnit.SECONDS.sleep(2);
    }

    /*  public void searchAndClickByText(By by, String textForSearch) {
        //wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElements(by).stream()
                .filter(element -> element.getText().toLowerCase().contains(textForSearch.toLowerCase())).findFirst()
                .orElseThrow(() -> new NoSuchElementException(format(UIELEMENT_ERROR_TEXT, by, textForSearch))).click();

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
/*    private void clickOnLaunchService() {
        click(By.xpath("//*[@id=\"launchServiceBtn\"]"));//acessServiceLink81
        System.out.println("launchService Button Clicked? ="+driver.findElement(By.xpath("//*[@id=\"launchServiceBtn\"]")).isSelected());
    }*/
/*    private void clickOnMDHHS() {
        click(By.xpath("//*[@id=\"acessServiceLink81\"]"));//acessServiceLink81
		*//*
		 * assertThat(driver.getTitle(), is("MiLogin - Launch Service"));
		 *//*
    }*/




/*        WebElement launchServiceBtn=driver.findElement(By.xpath("//*[@id=\"launchServiceBtn\"]/button/div[2]"));
        Actions actions=new Actions(driver);
        actions.moveToElement(launchServiceBtn);
        actions.perform();

        driver.findElement(By.xpath("//*[@id=\"tcCheckBox\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"tcCheckBox\"]")).click();*/

    //if(driver.findElement(By.xpath("//*[@id=\"tcCheckBox\"]")).isDisplayed()){
    //checkTermsAndConditionsBox();
    //TimeUnit.SECONDS.sleep(5);
    //searchAndClickByText(By.xpath("//*[@id=\"tcCheckBox\"]"));
    //searchAndClickByText(By.xpath("//*[@id=\"tcCheckBox\"]"),"I agree to the Terms & Conditions");
    // }


  /*     if(driver.findElement(By.id("launchServiceBtn")).isDisplayed()){
            launchOBRAService();
        }*/

    /*
    public void miInValidLoginUser(String userId, String password) throws InterruptedException {
        inputUserId(userId);
        TimeUnit.SECONDS.sleep(3);
        inputUserPassword(password);
        TimeUnit.SECONDS.sleep(3);
        clickMILoginButton();
        TimeUnit.SECONDS.sleep(3);
    }*/


    public void miLogin(String userId, String password) throws InterruptedException {
        inputUserId(userId);
        TimeUnit.SECONDS.sleep(3);
        inputUserPassword(password);
        TimeUnit.SECONDS.sleep(3);
        clickMILoginButton();
        TimeUnit.SECONDS.sleep(3);

        if(driver.findElement(By.id("acessServiceLink81")).isDisplayed()){
            clickOnTPMDHHS();
        }
        TimeUnit.SECONDS.sleep(5);

        clickOnTermsCheckBox();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("launchService button displayed "+driver.findElement(By.id("launchServiceBtn")).isDisplayed());
        TimeUnit.SECONDS.sleep(5);
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
        TimeUnit.SECONDS.sleep(5);
    }
    
    private void clickOnTermsCheckBox() {
        //click(By.xpath("//*[@id=\"mainContent\"]/section/div/div[2]/div/som-card/div/div/div[2]/p[2]"));
        //click(By.xpath("/html/body/som-footer//footer"));
        WebElement checkBox = driver.findElement(By.name("terms"));
        Actions action = new Actions(driver);
        action.moveToElement(checkBox).click().build().perform();
        System.out.println("Terms and Conditions Checked ? = "+driver.findElement(By.name("terms")).isSelected());
    }
   /* public void miLoginAsFacilityUser(String userId, String password) throws InterruptedException {
>>>>>>> f94d081cc000b7db6486090036f4f0b8a33acd43
        inputUserId(userId);
        TimeUnit.SECONDS.sleep(3);
        inputUserPassword(password);
        TimeUnit.SECONDS.sleep(3);
        clickMILoginButton();
        TimeUnit.SECONDS.sleep(3);

        if(driver.findElement(By.id("acessServiceLink81")).isDisplayed()){
            clickOnTPMDHHS();
        }
        TimeUnit.SECONDS.sleep(5);

        clickOnTermsCheckBox();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("launchService button displayed "+driver.findElement(By.id("launchServiceBtn")).isDisplayed());
        TimeUnit.SECONDS.sleep(5);
        navigateToOBRAWindowAndRegisterAdminUser();
    }*/
/*

    public void miLogin(String userId, String password) throws InterruptedException {
        inputUserId(userId);
        TimeUnit.SECONDS.sleep(3);
        inputUserPassword(password);
        TimeUnit.SECONDS.sleep(3);
        clickMILoginButton();
        TimeUnit.SECONDS.sleep(3);

        if(driver.findElement(By.id("acessServiceLink81")).isDisplayed()){
            clickOnTPMDHHS();
        }
        TimeUnit.SECONDS.sleep(5);

        clickOnTermsCheckBox();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("launchService button displayed "+driver.findElement(By.id("launchServiceBtn")).isDisplayed());
        TimeUnit.SECONDS.sleep(5);
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
        TimeUnit.SECONDS.sleep(5);
    }

    public void navigateToOBRAWindow() throws InterruptedException {
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
        System.out.println("Before Clicking on Register Window is "+driver.getWindowHandle());
        registerFacilityAdmin();

// Close the new window, if that window no more required
        driver.close();

// Switch back to original browser (first window)
        driver.switchTo().window(winHandleMiLogin);

// Continue with original browser (first window)
    }

*/
    /*

    public void facilityUserRegistration() throws InterruptedException {
        //miLoginAsFacilityUser(userId, password);
        TimeUnit.SECONDS.sleep(5);
        clickOnRegister();
        TimeUnit.SECONDS.sleep(5);
        registerFacilityUser();
    }
*/

/*    public void cmhUserRegistration() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        clickOnRegister();
        TimeUnit.SECONDS.sleep(5);
        //clickOnRegister();
        registerCMHUser();
    }*/

   /* public void navigateToOBRAWindowAndRegisterAdminUser() throws InterruptedException {
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
        System.out.println("Before Clicking on Register Window is "+driver.getWindowHandle());
        registerFacilityAdmin();

// Close the new window, if that window no more required
        driver.close();

// Switch back to original browser (first window)
        driver.switchTo().window(winHandleMiLogin);

// Continue with original browser (first window)
    }
*/


/*    private void registerAdminUser() throws InterruptedException {
        // TODO Register User
        sendKeys(By.xpath("//*[@id=\"positionTitle\"]"), "AutomationTestAdmin");
        selectAdminUserType();
        selectUserRole();
        selectFacilityGroup();
        clickOnGenerateAuthDoc();
        checkAcknowledgeButton();
        TimeUnit.SECONDS.sleep(5);
        submitRegisterUserForm();

    }*/

/*    private void checkCMHUserRegTermsAndConditionsBox() {

        WebElement acknowledgeCheckBox = driver.findElement(By.xpath("//*[@id=\"signature\"]"));
        Actions action = new Actions(driver);
        action.moveToElement(acknowledgeCheckBox).click().build().perform();
        System.out.println("acknowledgeCheckBox Checked ? = "+driver.findElement(By.xpath("//*[@id=\"signature\"]")).isSelected());
    }*/


/*    private void submitRegisterUserForm() throws InterruptedException {
        //TimeUnit.SECONDS.sleep(3);
       // click(By.xpath("//*[@id=\"user_save_btn\"]")); /////*[@id="user_save_btn"]

        TimeUnit.SECONDS.sleep(5);
        //click(By.id("add_user_save_btn"));
        WebElement saveButton = driver.findElement(By.id("user_save_btn"));
        Actions action = new Actions(driver);
        action.moveToElement(saveButton).click().build().perform();
        System.out.println("saveButton clicked ? = "+saveButton.isSelected());
        TimeUnit.SECONDS.sleep(5);
    }*/


/*    private void submitCMHUserRegisterUserForm() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        if (driver.findElement(By.id("user_save_btn")).isEnabled()) {
            System.out.println("submit button is Enabled" + driver.findElement(By.id("user_save_btn")).isEnabled());
            click(By.id("user_save_btn")); /////*[@id="user_save_btn"]
            System.out.println("submit button is clicked" + driver.findElement(By.id("user_save_btn")).isSelected());
        }

    }*/




    /*

    private void selectFacilityGroupValue() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(3);
        WebElement userTypeDropDown=driver.findElement(By.xpath("//*[@id=\"facilityGroupId\"]"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userTypeDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(3);

        WebElement adminUser = driver.findElement(By.xpath(".//*[@id=\"facilityGroupId\"]/option[312]"));
        TimeUnit.SECONDS.sleep(3);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(adminUser).click().build().perform();
        TimeUnit.SECONDS.sleep(3);

    }
*/

/*
    private void selectFacilityGroup()  throws InterruptedException{
        TimeUnit.SECONDS.sleep(2);
        Select objSelect = new Select(driver.findElement(By.id("facilityGroupId")));
        TimeUnit.SECONDS.sleep(1);
        objSelect.selectByVisibleText("Sparrow");
        //objSelect.selectByValue("316: 359");
    }
*/
/*    public static void selectMultipelValues(String multipleVals) {
        String multipleSel[] = multipleVals.split(",");

        for (String valueToBeSelected : multipleSel) {
            new Select(driver.findElement(By.id("propId"))).selectByVisibleText(valueToBeSelected);
            driver.findElement(By.id("ddObj")).sendKeys(Keys.CONTROL);
        }
    }*/
  /*  public void angularMultiSelectDropdown(){

        //*[@id="main_content"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect/div/div[2]/div[3]/div[1]/input

        // Click button
        //WebElement facilitiesDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect/div"));

        List<WebElement> facilitiesDropDown = driver.findElements(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect/div"));

        String option = "Sparrow Carson Hospital (Hospital) - 406 East Elm St, Carson City - 48811";

// Iterate the list using for loop

        for (int i = 0; i < facilitiesDropDown.size(); i++) {

            if (facilitiesDropDown.get(i).getText().contains(option)) {

                facilitiesDropDown.get(i).click();

                System.out.println("clicked on Sparrow Carson Hospital (Hospital) - 406 East Elm St, Carson City - 48811");

                break;

            }

        }
       *//* driver.findElement(By.cssSelector("ss-multiselect-dropdown"))
                .findElements(By.tagName("a"))
                .stream()
                .filter(elem -> {
                    String text = elem.getText();
                    return text.contains("Norway") || text.contains("Finland");
                }).forEach(WebElement::click);*//*
    }

    private void multiSelect(){
       // WebElement facilitiesDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect/div"));
        WebElement facilitiesDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect"));
//object of Select class
        Select s=new Select(facilitiesDropDown);
        //get options of dropdown in list
        List<WebElement> t =s.getOptions();
        System.out.println("Options are: ");
        for (WebElement i: t){
            System.out.println(i.getText());
        }
        //return true if multi-select dropdown
        boolean isMultiSelect=s.isMultiple();
        System.out.println("Boolen value for drodown: "+ isMultiSelect);
        //get first selected option in dropdown
        WebElement f = s.getFirstSelectedOption();
        System.out.println("First selected option is: "+ f.getText());
    }
*/
/*
    private void selectFacilities() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userRoleDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect/div"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userRoleDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);

        WebElement adminUserRole = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[2]/div/div/angular2-multiselect/div/div[2]/div[3]/div[3]/ul/li[2]/label[2]"));
        TimeUnit.SECONDS.sleep(2);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(adminUserRole).click().build().perform();
        TimeUnit.SECONDS.sleep(2);
    }
*/

    /*        private void selectAdminUserType() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("6");
    }*/

/*
    private void selectCmhUserType() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        //objSelect.selectByIndex(index);
        //objSelect.selectByIndex(index);
// Or can be used as
        //objSelect.selectByVisibleText(text);
        //objSelect.selectByVisibleText(text);
// Or can be used as
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("6");
        //objSelect.selectByValue(value);
*/
/*        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(3);
        WebElement userTypeDropDown=driver.findElement(By.xpath("//*[@id=\"userType\"]"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userTypeDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(3);

        WebElement adminUser = driver.findElement(By.xpath(".//*[@id=\"userType\"]/option[2]"));
        TimeUnit.SECONDS.sleep(3);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(adminUser).click().build().perform();
        TimeUnit.SECONDS.sleep(3);*//*

    }
*/


/*
    private void selectFacilityUserUserRole() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userRoleDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[1]/div[1]/div/angular2-multiselect/div"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userRoleDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);

        WebElement adminUserRole = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[1]/div[1]/div/angular2-multiselect/div/div[1]/div/div/div/span[1]"));
        TimeUnit.SECONDS.sleep(2);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(adminUserRole).click().build().perform();
        TimeUnit.SECONDS.sleep(2);
    }



    private void clickOnTermsCheckBox() {
        //click(By.xpath("//*[@id=\"mainContent\"]/section/div/div[2]/div/som-card/div/div/div[2]/p[2]"));
        //click(By.xpath("/html/body/som-footer//footer"));
        WebElement checkBox = driver.findElement(By.name("terms"));
        Actions action = new Actions(driver);
        action.moveToElement(checkBox).click().build().perform();
        System.out.println("Terms and Conditions Checked ? = "+driver.findElement(By.name("terms")).isSelected());
    }

*/

/*
    private void launchOBRAService() {
        click(By.id("launchServiceBtn"));//acessServiceLink81
        System.out.println("Launch Service Seleted ?. ="+driver.findElement(By.id("launchServiceBtn")).isSelected());
    }

    private void checkTermsAndConditionsBox() throws InterruptedException {
       // new WebDriverWait(getWebDriver(), 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@formcontrolname='reportingDealPermission' and @ng-reflect-name='reportingDealPermission']"))).click();
       // TimeUnit.SECONDS.sleep(10);

        waitUntilElementClickable(By.xpath("//*[@id=\"tcCheckBox\"]"));
        //click(By.xpath("//*[@id=\"tcCheckBox\"]"));//acessServiceLink81
       // System.out.println("CheckBox Seleted ?. ="+driver.findElement(By.xpath("//*[@id=\"tcCheckBox\"]")).isSelected());
        //assertThat(driver.getTitle(), is("MiLogin - Launch Service"));


    }

    public void searchAndClickByText(By by) {
        //wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElements(by).stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(format(UIELEMENT_ERROR_TEXT, by))).click();

    }    public void miLoginAsFacilityUserAndRegisterCMHUser(String userId, String password) throws InterruptedException {
        inputUserId(userId);
        TimeUnit.SECONDS.sleep(3);
        inputUserPassword(password);
        TimeUnit.SECONDS.sleep(3);
        clickMILoginButton();
        TimeUnit.SECONDS.sleep(3);

        if(driver.findElement(By.id("acessServiceLink81")).isDisplayed()){
            clickOnTPMDHHS();
        }
        TimeUnit.SECONDS.sleep(5);

        clickOnTPTermsCheckBox();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("launchService button displayed "+driver.findElement(By.id("launchServiceBtn")).isDisplayed());
        TimeUnit.SECONDS.sleep(5);
        navigateToOBRAWindowAndRegisterCMHUser();
    }

    public void miLoginAsFacilityUserAndRegisterFacilityUser(String userId, String password) throws InterruptedException {
        inputUserId(userId);
        TimeUnit.SECONDS.sleep(3);
        inputUserPassword(password);
        TimeUnit.SECONDS.sleep(3);
        clickMILoginButton();
        TimeUnit.SECONDS.sleep(3);

        if(driver.findElement(By.id("acessServiceLink81")).isDisplayed()){
            clickOnTPMDHHS();
        }
        TimeUnit.SECONDS.sleep(5);

        clickOnTPTermsCheckBox();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("launchService button displayed "+driver.findElement(By.id("launchServiceBtn")).isDisplayed());
        TimeUnit.SECONDS.sleep(5);
        navigateToOBRAWindowAndRegisterFacilityUser();
    }
    private void navigateToOBRAWindowAndRegisterCMHUser() throws InterruptedException {
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
        System.out.println("Before Clicking on Register Window is "+driver.getWindowHandle());
        registerCMHUser();
// Close the new window, if that window no more required
        driver.close();

// Switch back to original browser (first window)
        driver.switchTo().window(winHandleMiLogin);

// Continue with original browser (first window)
    }

    private void navigateToOBRAWindowAndRegisterFacilityUser() throws InterruptedException {
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
        System.out.println("Before Clicking on Register Window is "+driver.getWindowHandle());
        registerFacilityUser();
// Close the new window, if that window no more required
        driver.close();

// Switch back to original browser (first window)
        driver.switchTo().window(winHandleMiLogin);

// Continue with original browser (first window)
    }



*/

}
