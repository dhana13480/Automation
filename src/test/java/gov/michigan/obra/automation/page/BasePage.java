package gov.michigan.obra.automation.page;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static gov.michigan.obra.automation.common.constant.Constants.*;
import static java.lang.String.format;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BasePage {

    public static WebDriver driver;
    public final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, EXPLICIT_WAIT_IN_SECONDS);
    }

    public void waitUntilElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitUntilElementClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void click(By by) {
        waitUntilElementClickable(by);
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String text) {
        waitUntilElementVisible(by);
        driver.findElement(by).sendKeys(text);
    }

    public String getText(By by) {
        waitUntilElementVisible(by);
        return driver.findElement(by).getText();
    }

    public WebElement getElement(By by) {
        waitUntilElementVisible(by);
        return driver.findElement(by);
    }

    public void clearSendKeys(By by, String text) {
        waitUntilElementVisible(by);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }


    public void searchAndClickByText(By by, String textForSearch) {
        waitUntilElementClickable(by);
        driver.findElements(by).stream()
                .filter(element -> element.getText().toLowerCase().contains(textForSearch.toLowerCase())).findFirst()
                .orElseThrow(() -> new NoSuchElementException(format(UIELEMENT_ERROR_TEXT, by, textForSearch))).click();
    }

    public void searchAndClickByValue(By by, String textForSearch) {
        waitUntilElementClickable(by);
        driver.findElements(by).stream()
                .filter(element -> element.getText().toLowerCase().contains(textForSearch.toLowerCase())).findFirst()
                .orElseThrow(() -> new NoSuchElementException(format(UIELEMENT_ERROR_TEXT, by, textForSearch))).click();
    }

    public void inputUserId(String userId) {
        sendKeys(By.id("userid"), userId);
    }

    public void inputUserPassword(String password) {
        sendKeys(By.id("password"), password);
    }

    public void clickMILoginButton() {
        click(By.xpath("//*[@id=\"loginbutton\"]/button"));
    }

    public void clickOnTPMDHHS() {
        click(By.xpath("//*[@id=\"acessServiceLink81\"]"));
    }

    public void scrollDown(){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
       // js.executeScript("window.scrollBy(0,450)");
        js.executeScript("window.scrollBy(0,550)");
    }
    public void clickOnOBRAMDHHS() {
        click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[1]"));
    }
    public void clickOnTPTermsCheckBox() {
        scrollDown();
        WebElement checkBox = driver.findElement(By.name("terms"));
        Actions action = new Actions(driver);
        action.moveToElement(checkBox).click().build().perform();
    }

    public void clickOnLaunchService() throws InterruptedException {
        click(By.xpath("//*[@id=\"launchServiceBtn\"]"));//acessServiceLink81
        TimeUnit.SECONDS.sleep(5);
    }

    public void clickOnConsumerModule() {
        click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[2]/div/a"));
    }

    public void selectDropDownByValue(By by,String value) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Select objSelect = new Select(driver.findElement(by));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue(value);
        TimeUnit.SECONDS.sleep(3);
    }

    public void selectDropDownByVisibleText(By by,String value) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Select objSelect = new Select(driver.findElement(by));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByVisibleText(value);
        TimeUnit.SECONDS.sleep(3);
    }

    public void saveButton(By by) throws InterruptedException {
        WebElement saveButton = driver.findElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(saveButton).click().build().perform();
        TimeUnit.SECONDS.sleep(5);

    }

    public void clickDropdownMenuItems(By menu,By dropdownValue) throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userProfileDropDown=driver.findElement(menu);
        action.moveToElement(userProfileDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);
        WebElement editUserLink = driver.findElement(dropdownValue);

        action.moveToElement(editUserLink).click().build().perform();
    }
    public void inputById(String id,String value) {
        sendKeys(By.id(id), value);
    }

    public boolean validatePDFContent(String filePath,String expectedContent){
        boolean pdfValidation=false;
        String pdfContent=extractTextFromPdf(filePath);
        if(pdfContent.contains(expectedContent)){
            pdfValidation=true;
        }
        return pdfValidation;
    }

    private String extractTextFromPdf(String filePath) {
        try(PDDocument document=PDDocument.load(new File(filePath))){
            PDFTextStripper pdfStripper=new PDFTextStripper();
            return pdfStripper.getText(document);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyPDFContent(String url , String content,String fileName) {
    	System.out.println("url :" +url +" fileName : " +fileName);
        String pdfData = (String) ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[0];" + // The callback from ExecuteAsyncScript
                        "var reader;" +
                        "var xhr = new XMLHttpRequest();" +
                        "xhr.onreadystatechange = function() {" +
                        "  if (xhr.readyState == 4) {" +
                            "var reader = new FileReader();" +
                            "reader.readAsDataURL(xhr.response);" +
                            "reader.onloadend = function() {" +
                            "    callback(reader.result);" +
                            "}" +
                        "  }" +
                        "};" +
                        "xhr.open('GET', '" + url + "', true);" +
                        "xhr.responseType = 'blob';" +
                        "xhr.send();");
        System.out.println("pdfData : " + pdfData);
        String base64Data = pdfData.split(",")[1];
        System.out.println("base64Data : " + base64Data);
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
        try (OutputStream stream = new FileOutputStream("c:\\dev\\"+fileName)) {
        	System.out.println("decodedBytes  :  "+decodedBytes);

            stream.write(decodedBytes);     

      		PDDocument document = PDDocument.load(new File("c:\\dev\\"+fileName));
      		PDFTextStripper pdfStripper = new PDFTextStripper();
      		String text = pdfStripper.getText(document);
      		 System.out.println("tile: "+text);
            System.out.println("PDF Content Validated ? "+text.contains(content));
      	return text.contains(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return false;
    }

    public void clickOnRegister() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        WebElement register = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-registration-link/div/section/div[2]/div/div/div[2]/button")); // Working Copy
        ////*[@id="main_content"]/app-registration-link/div/section/div[2]/div/div/div[2]/button
        Actions action = new Actions(driver);
        action.moveToElement(register).click().build().perform();
        TimeUnit.SECONDS.sleep(2);

    }

    public static void checkAcknowledgeButton(By by) {
        WebElement acknowledgeCheckBox = driver.findElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(acknowledgeCheckBox).click().build().perform();
    }

    public void selectUserRole(By dropDown,By userRole) throws InterruptedException {
        Actions action= new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        WebElement userRoleDropDown=driver.findElement(dropDown);
        // action.moveToElement(mainmenu).build().perform();
        action.moveToElement(userRoleDropDown).click().build().perform();

        TimeUnit.SECONDS.sleep(2);

        WebElement adminUserRole = driver.findElement(userRole);
        TimeUnit.SECONDS.sleep(2);
        //action.moveToElement(Sub).build().perform();
        action.moveToElement(adminUserRole).click().build().perform();
        TimeUnit.SECONDS.sleep(2);
    }

    public void selectStatus(By by,String value) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Select objSelect = new Select(driver.findElement(by));
        TimeUnit.SECONDS.sleep(3);
        objSelect.selectByValue(value);
        TimeUnit.SECONDS.sleep(3);

    }

    public void clickOnUserQueue(WebElement userQueue) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        //WebElement userQueue = driver.findElement(By.xpath("//*[@id=\"main\"]/li[20]/a"));

        Actions actions = new Actions(driver);
        actions.moveToElement(userQueue).click().build().perform();

        //click(By.xpath("//*[@id=\"main\"]/li[20]/a"));
    }

    public void selectMultipleCheckBoxes(By by){
        List<WebElement> checkBoxes=driver.findElements(by);
        Optional<WebElement> checkbox=checkBoxes.stream().findFirst();
        System.out.println("checkBoxes are -------"+checkBoxes+"-----and checkbox is -----"+checkbox);
    }

    public void tableRowClick(By tableId,String searchRowInput,boolean found){
        //Locate the table by its ID
        WebElement table= driver.findElement(tableId);
        System.out.println("table is -----"+table);
        //get all rows within the table
        List<WebElement> rows=table.findElements(By.tagName("tr"));
        System.out.println("table with rows data is -----"+rows);

        for (WebElement row : rows){
            //Get all cells / colums in the current row
            //driver.navigate().refresh();
            //Sorting on descending order
            //click(By.id("sortOrder"));
            List<WebElement> cells=row.findElements(By.tagName("td"));
            System.out.println("table with cells data is -----"+cells);

            //Check if the row contains the desired searchRowInput text

            for(WebElement cell: cells){
                System.out.println("cells before matching condition -----"+cell.getText());

                if(cell.getText().contains(searchRowInput)){
                    System.out.println("cells after matching condition cell.getText() is-----"+cell.getText());

                    cells.get(5).click();
                    System.out.println("cells after matching condition cells.get(5) is-----"+cells.get(5));

                    found=true;
                    break;
                }
            }
            if(found){
                break;
            }
            if(!found){
                System.out.println("No Matching row found");
            }
        }

    }
    
   public boolean isElementPresent(By by){
       try{
    	   driver.findElement(by).isDisplayed();
           return true;
       }
       catch(org.openqa.selenium.NoSuchElementException e){
    	   System.out.println("isElementPresent :false" );
           return false;
       }
   }
   
	public  String getCurrentDateTimeString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		return LocalDateTime.now().format(formatter);
	}
	
    public static String takeScreenshot( String fileName) {
        // Define timestamp format for unique filenames
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        
        // Define screenshot folder path
        String screenshotDir = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "Screenshots";
        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Take the screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = screenshotDir + File.separator + fileName + "_" + timestamp + ".png";

        try {
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }

        return screenshotPath;
    }

}
