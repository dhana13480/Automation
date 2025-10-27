package gov.michigan.obra.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import static gov.michigan.obra.automation.common.constant.ApplicationConstants.AUTHORIZATION_LETTER_PATH;

public class OBRAPage extends BasePage{

    public static final String FIRST_NAME = "a";

    public OBRAPage(WebDriver driver) {
        super(driver);
    }

    public void editExistingAdminUserProfile() throws InterruptedException {
        clickOnOBRAMDHHS();
        clickOnUsersModule();
        inputFirstNameAndAdminUserTypeSearch(FIRST_NAME);
        clickOnUserRow();
        clickOnEditUserDropDown();
        editUserTypeToFacility();
        clearSendKeys(By.xpath("//*[@id=\"add_position\"]"),"Resident Physician");
        editUserRoleForFacilityUser();
        clearSendKeys(By.xpath("//*[@id=\"license\"]"),"123456789");
        clearFacilityAndSelectOne();
        clearQualificationAndSelectOne();
        editStatus();
        saveEditUser();
        //driver.close();
        //String data=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-detail/div/section/div[3]/div[1]/div/dl/dt[7]")).getTagName();//.getAttribute("//*[@id=\"main_content\"]/app-user-detail/div/section/div[3]/div[1]/div/dl/dd[7]/text()")  ;
        //System.out.println("data is ="+data);
        //validateUserUpdate();
        //driver.close();


    }

    private void validateUserUpdate() {


        WebElement table = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-detail/div/section/div[3]/div[1]/div/dl"));
        System.out.println("table is ="+table);
        List<WebElement> rowsList = table.findElements(By.tagName("dt"));

        List<WebElement> columnsList = null;

        for (WebElement row : rowsList) {
            System.out.println();
            columnsList = row.findElements(By.tagName("dd"));

            for (WebElement column : columnsList) {
                System.out.print("Hello"+column.getText() + ",");
            }
            //Verify
            System.out.println("User Type is " + driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-detail/div/section/div[3]/div[1]/div/dl/dt[7]")).getAttribute("value"));
            System.out.println("Position/Title is " + driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-detail/div/section/div[3]/div[1]/div/dl/dt[8]")).getAttribute("value"));
            System.out.println("License is " + driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-user-detail/div/section/div[3]/div[1]/div/dl/dt[11]")).getAttribute("value"));

        }
    }

    private void saveEditUser() throws InterruptedException {
        //click(By.id("add_user_save_btn"));
        WebElement saveButton = driver.findElement(By.id("add_user_save_btn"));
        Actions action = new Actions(driver);
        action.moveToElement(saveButton).click().build().perform();
        System.out.println("saveButton clicked ? = "+saveButton.isSelected());
        TimeUnit.SECONDS.sleep(5);

    }

    public void editExistingFacilityUserProfile() throws InterruptedException {
        clickOnOBRAMDHHS();
        clickOnUsersModule();
        inputFirstNameAndSelectFacilityUserTypeToSearch(FIRST_NAME);
        clickOnUserRow();
        clickOnEditUserDropDown();
        editUserTypeToAdmin();
        clearSendKeys(By.xpath("//*[@id=\"add_position\"]"),"Facility Admin");
        editUserRoleToAdminUser();
        clearSendKeys(By.xpath("//*[@id=\"license\"]"),"123456789");
        selectFacilityGroup();
        selectQualifications();
        uploadAuthDocument(AUTHORIZATION_LETTER_PATH);
        editStatus();
        saveEditUser();
        //driver.close();
    }

    private void uploadAuthDocument(String authorizationLetterPath) throws InterruptedException {
        File uploadFile = new File(authorizationLetterPath);
        TimeUnit.SECONDS.sleep(2);

        WebElement fileInput = driver.findElement(By.xpath("//*[@id=\"authorization\"]"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());
        //driver.findElement(By.id("authorization")).click();
        TimeUnit.SECONDS.sleep(2);

    }

    private void selectQualifications() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userQualificationsDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[8]/div/div/angular2-multiselect/div/div[1]/div"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userQualificationsDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);

        WebElement userQualification = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[8]/div/div/angular2-multiselect/div/div[2]/div[3]/div[3]/ul/li[1]/label[2]"));
        TimeUnit.SECONDS.sleep(2);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(userQualification).click().build().perform();
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("/html/body/app-root/div[2]/div/app-footer/footer/div/div/div/div")).click();
        System.out.println("Touched"+driver.findElement(By.xpath("/html/body/app-root/div[2]/div/app-footer/footer/div/div/div/div")).isDisplayed());

    }

    private void selectFacilityGroup()  throws InterruptedException{
        TimeUnit.SECONDS.sleep(2);
        Select objSelect = new Select(driver.findElement(By.id("facilityGroupId")));
        TimeUnit.SECONDS.sleep(1);
        objSelect.selectByValue("359");
    }

    private void editStatus() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Select objSelect = new Select(driver.findElement(By.id("status")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("A");
        TimeUnit.SECONDS.sleep(3);

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

    private void editUserRoleToAdminUser() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userRoleDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[6]/div[1]/div/angular2-multiselect/div/div[1]/div"));
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userRoleDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);

        //WebElement adminUserRole = driver.findElement(By.xpath(".//*[@id=\"main_content\"]/app-registration/section/form/div[1]/div[1]/div[5]/div[1]/div[1]/div/angular2-multiselect/div/div[2]/div[3]/div[2]/ul/li[2]/label[2]"));
        WebElement adminUserRole = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-edit-user/div/section/form/div[6]/div[1]/div/angular2-multiselect/div/div[2]/div[3]/div[3]/ul/li[1]/label[2]"));
        TimeUnit.SECONDS.sleep(2);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(adminUserRole).click().build().perform();
        TimeUnit.SECONDS.sleep(2);
    }

    public void clearSendKeys(By by, String text) {
        waitUntilElementVisible(by);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }
    private void editUserTypeToFacility() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("7");
    }
    private void editUserTypeToAdmin() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("6");
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
/*
    private void clickOnOBRAMDHHS() {
        click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[1]"));
    }*/

    private void clickOnUsersModule() {
        click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[3]/div[2]/a/h2"));
    }

    private void inputFirstNameAndAdminUserTypeSearch(String firstName) throws InterruptedException {
        sendKeys(By.id("firstName"), firstName);
        selectAdminUserTypeForUserSearch();
        click(By.xpath("//*[@id=\"main_content\"]/app-users/app-user-search/div/section/form/div/div[6]/div/button[1]"));

    }
    private void inputFirstNameAndSelectFacilityUserTypeToSearch(String firstName) throws InterruptedException {
        sendKeys(By.id("firstName"), firstName);
        selectFacilityUserTypeForUserSearch();
        click(By.xpath("//*[@id=\"main_content\"]/app-users/app-user-search/div/section/form/div/div[6]/div/button[1]"));

    }
    private void selectAdminUserTypeForUserSearch() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("6");
    }

    private void selectFacilityUserTypeForUserSearch() throws InterruptedException {
        Select objSelect = new Select(driver.findElement(By.id("userType")));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue("7");
    }

    private void clickOnUserRow() {
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[3]"));
    }
    
    public void reviewerPendingAssesment() throws InterruptedException {
    	try {

    	click(By.xpath("//*[@id=\"main\"]/li[7]/a"));
    	TimeUnit.SECONDS.sleep(3);
    	
    	click(By.xpath("//*[@id=\"receivedDate\"]/a"));
    	TimeUnit.SECONDS.sleep(3);
    	
    	//click(By.xpath("//*[@id=\"receivedDate\"]/a"));
    	//TimeUnit.SECONDS.sleep(3);
    	
    	click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]"));    	
    	TimeUnit.SECONDS.sleep(5);


    	
    	WebElement element = driver.findElement(By.xpath("//*[@id=\"updateReEvaluationDate\"]"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", element); 
    	//WebElement label = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[9]/div/p/span"));
    	//String labelText = label.getText();
    	//if(labelText.equalsIgnoreCase("MDHHS Determination")) {
    		click(By.xpath("//*[@id=\"updateReEvaluationDate\"]"));  
    	//}
    	
    	//scroll up click dropdwon and select mark as pending 
    	js.executeScript("window.scrollTo(0, 0)");
    	TimeUnit.SECONDS.sleep(2);
    	clickOnEvalDropDown();
    	
    	//Psychosocial and Nursing
    	click(By.xpath("//*[@id=\"main_content\"]/app-pending-evaluation/div/section/div[3]/div/div/div[5]/label/span"));  
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"main_content\"]/app-pending-evaluation/div/section/div[3]/div/div/div[6]/label/span")); 
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"save\"]")); 
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"main_content\"]/app-pending-evaluation/div/section/div[3]/div/div/div[5]/label/span/a")); 
    	TimeUnit.SECONDS.sleep(5);
    	
    	
    	//sectionone Presenting Problems    	
    	safeClick(By.cssSelector("#pendData0 > app-common-popover > a > span.font-xl.glyphicon.glyphicon-info-sign")); 
    	//validatePopover();
    	WebElement textArea1 = driver.findElement(By.xpath("//*[@id=\"textField0\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea1.sendKeys("This is my input text!");
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[2]/a")); 
    	TimeUnit.SECONDS.sleep(3);
    	safeClick(By.cssSelector("#pendData10 > app-common-popover > a > span.font-xl.glyphicon.glyphicon-info-sign")); 
    	//validatePopover();
    	WebElement textArea2 = driver.findElement(By.xpath("//*[@id=\"textField10\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea2.sendKeys("This is my input text2!");
    	
    	click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[3]/a")); 
    	TimeUnit.SECONDS.sleep(3);
    	safeClick(By.cssSelector("#pendData19 > app-common-popover > a > span.font-xl.glyphicon.glyphicon-info-sign")); 
    	//validatePopover();
    	WebElement textArea3 = driver.findElement(By.xpath("//*[@id=\"textField19\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea3.sendKeys("This is my input text3!");
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"review\"]")); 
    	TimeUnit.SECONDS.sleep(2);
    	
    	//Nursing
    	click(By.xpath("//*[@id=\"main_content\"]/app-pending-evaluation/div/section/div[3]/div/div/div[6]/label/span/a"));  
    	TimeUnit.SECONDS.sleep(3);
    	safeClick(By.cssSelector("#pendData0 > app-common-popover > a > span.font-xl.glyphicon.glyphicon-info-sign"));
    	//validatePopover();
    	WebElement textArea4 = driver.findElement(By.xpath("//*[@id=\"textField0\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea4.sendKeys("This is my input text4!");	
    	
    	//section2
    	click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[2]/a"));
    	safeClick(By.cssSelector("#pendData12 > app-common-popover > a > span.font-xl.glyphicon.glyphicon-info-sign"));
    	//validatePopover();
    	WebElement textArea5 = driver.findElement(By.xpath("//*[@id=\"textField12\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea5.sendKeys("This is my input text5!");
    	TimeUnit.SECONDS.sleep(2);
    	
    	//section3
    	click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[3]/a"));
    	safeClick(By.cssSelector("#pendData14 > app-common-popover > a > span.font-xl.glyphicon.glyphicon-info-sign"));
    	//validatePopover();
    	WebElement textArea6 = driver.findElement(By.xpath("//*[@id=\"textField14\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea6.sendKeys("This is my input text6!");
    	TimeUnit.SECONDS.sleep(2);
    	
    	//section4
    	click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[4]/a"));
    	safeClick(By.cssSelector("#pendData29 > app-common-popover > a > span.font-xl.glyphicon.glyphicon-info-sign"));
    	//validatePopover();
    	WebElement textArea7 = driver.findElement(By.xpath("//*[@id=\"textField29\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea7.sendKeys("This is my input text7!");
    	TimeUnit.SECONDS.sleep(2);
    	
    	//section5
    	click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[5]/a"));
    	safeClick(By.cssSelector("#pendData32 > app-common-popover > a > span.font-xl.glyphicon.glyphicon-info-sign"));
    	WebElement textArea8 = driver.findElement(By.xpath("//*[@id=\"textField32\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea8.sendKeys("This is my input text8!");
    	TimeUnit.SECONDS.sleep(2);
    	/*
    	//section5
    	click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[6]/a"));
    	safeClick(By.cssSelector("#pendData46 > app-common-popover > a > span.font-xl.glyphicon.glyphicon-info-sign"));
    	//validatePopover();
    	WebElement textArea9 = driver.findElement(By.xpath("//*[@id=\"textField46\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea9.sendKeys("This is my input text2!");*/
    	TimeUnit.SECONDS.sleep(3);
    	
    	//save 
    	click(By.xpath("//*[@id=\"review\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	
    	
    	//sendtoCMH
    	click(By.xpath("//*[@id=\"sendToCMH\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	clickOnEvalDropDownLetter();
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[2]/form/div/div/div/div[2]/div[2]/div[1]/a"));
    	
    	String currentUrl =validatePdfWindowOpens();
    	 Assert.assertNotNull(currentUrl);
    	 //driver.close();
    	}catch (Exception e) {
			e.printStackTrace();
		}

        //validateUserUpdate();
        //driver.close();


    }
    /*
    private void clickOnEvalDropDown() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement evalDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/button[2]"));
        action.moveToElement(evalDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);
        WebElement markPending = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/ul/span/li[5]/a"));

        action.moveToElement(markPending).click().build().perform();
    }
    */
    
    private void clickOnEvalDropDown() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);

        // 1️⃣ Wait for and open the dropdown
        WebElement evalDropDown = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id='main_content']/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/button[2]")
        ));

        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", evalDropDown);
        action.moveToElement(evalDropDown).click().perform();
        Thread.sleep(1000); // give menu time to open

        // 2️⃣ Wait for dropdown options to become visible
        WebElement markPending = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@id='main_content']/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/ul/span/li[5]/a")
        ));

        // 3️⃣ Scroll and click the 'Mark Pending' item
        js.executeScript("arguments[0].scrollIntoView(true);", markPending);
        js.executeScript("arguments[0].click();", markPending);
    }

    
    private void clickOnEvalDropDownLetter() throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement evalDropDown=driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/button[2]"));
        action.moveToElement(evalDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);
        WebElement markPending = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/ul/span/li[4]/a"));

        action.moveToElement(markPending).click().build().perform();
    }
    
    public String validatePdfWindowOpens() throws InterruptedException {
        // Click the link that opens PDF in new window
        WebElement pdfLink = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[2]/form/div/div/div/div[2]/div[2]/div[1]/a"));
        pdfLink.click();
        TimeUnit.SECONDS.sleep(3);
        // Store current window
        String parentWindow = driver.getWindowHandle();

        // Switch to new window
       /* Set<String> allWindows = driver.getWindowHandles();
        TimeUnit.SECONDS.sleep(3);
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }*/
        List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
      	 // Switch to 2nd tab (index 1)
    	  driver.switchTo().window(tabs2.get(tabs2.size() - 1));

       	// Interact with PDF (or just wait)
       	Thread.sleep(500);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
			Files.copy(screenshot.toPath(), Paths.get("FLOW1_pendingLetter.png"));
			Thread.sleep(500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Validate URL contains .pdf
        String currentUrl = driver.getCurrentUrl();
        System.out.println("currentUrl "+currentUrl);
        driver.close();
       return currentUrl;

        
    }
    
    public void safeClick(By locator) {
        try {
            // Step 1: Wait for element to be visible
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            // Step 2: Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // Step 3: Wait for element to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            // Step 4: Try normal click
            try {
                element.click();
            } catch (ElementClickInterceptedException e) {
                // Fallback: JS click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }

        } catch (TimeoutException te) {
            throw new RuntimeException("❌ Timeout: Element not clickable -> " + locator, te);
        }
    }
}
