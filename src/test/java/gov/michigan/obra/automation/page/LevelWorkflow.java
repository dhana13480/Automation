package gov.michigan.obra.automation.page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.michigan.obra.automation.common.constant.ApplicationConstants;


public class LevelWorkflow extends BasePage{
    public LevelWorkflow(WebDriver driver) {
        super(driver);
    }

    public String level2NSMNotNeededFlow(String ssn) throws InterruptedException, IOException {
  
    	click(By.xpath("//*[@id=\"main\"]/li[14]"));
    	TimeUnit.SECONDS.sleep(5);
    	 click(By.xpath("//*[@id=\"sortOrder\"]/span[1]"));
    	 TimeUnit.SECONDS.sleep(2);
    	// click(By.xpath("//*[@id=\"referralDate\"]/a"));
    	 //TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
    	// find the customer table
    	/*WebElement table = driver.findElement(By.xpath("//*[@id=\"gridTable\"]/tbody"));

    	// find the row
    	WebElement arrText = table.findElement(By.xpath("//tr/td[contains(text(), 'ARR')]"));

    	// click on the row
    	arrText.click();*/

        TimeUnit.SECONDS.sleep(3);
        scrollDown();
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"nsmiLtrInd\"]"));
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"legalInfoCoordVfrd\"]"));
        TimeUnit.SECONDS.sleep(3);
        inputById("letterComments","test");
        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"generateLetterBtnId\"]"));
        String windHandleCurrent = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(3);

 
      /*  ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
            for(int i =0;i<windows.size();i++ ) {
                String aWindow = windows.get(i);
                if(aWindow != windHandleCurrent) {
                    driver.switchTo().window(aWindow);
                }
            }
        System.out.println("url  :  "+driver.getCurrentUrl());*/
        
        List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
   	 // Switch to 2nd tab (index 1)
 	  driver.switchTo().window(tabs1.get(tabs1.size() - 1));   
       
        TimeUnit.SECONDS.sleep(3);
        //verifyPDFContent(driver.getCurrentUrl(),ApplicationConstants.NSNMI_PDF_TEXT,"level_NSNI.pdf");
        
		/*File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshot1.toPath(), Paths.get(System.getProperty("user.dir"), "reports", "Screenshots",
				"level_NSNI" + getCurrentDateTimeString() + ".png"));*/
		takeScreenshot("level_NSNI");
      		
      	 // switch to the first tab
        driver.switchTo().window(windHandleCurrent);
       
       
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[4]/div/button[3]"));
        TimeUnit.SECONDS.sleep(5);
        WebElement letterComments=driver.findElement(By.id("letterComments"));
        System.out.println("letterComments  :  "+letterComments.getText());
        return letterComments.getText();
    }
    
    
    public String level2PDischargeNotNeeded() throws InterruptedException, IOException {
    	  
    	click(By.xpath("//*[@id=\"main\"]/li[14]"));
    	TimeUnit.SECONDS.sleep(3);
    	click(By.xpath("//*[@id=\"sortOrder\"]/span[1]"));
   	 TimeUnit.SECONDS.sleep(2);
   	 click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
    	
    	// find the customer table
    	/*WebElement table = driver.findElement(By.xpath("//*[@id=\"gridTable\"]/tbody"));

    	// find the row
    	WebElement hedText = table.findElement(By.xpath("//tr/td[contains(text(), 'HED')]"));

    	// click on the row
    	hedText.click();*/

        TimeUnit.SECONDS.sleep(3);
        scrollDown();
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[1]/div[1]/div[3]/label"));
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"legalInfoCoordVfrd\"]"));
        TimeUnit.SECONDS.sleep(3);
        inputById("letterComments","test123");
        TimeUnit.SECONDS.sleep(5);
        //String windHandleCurrent = driver.getWindowHandle();
        click(By.xpath("//*[@id=\"generateLetterBtnId\"]"));
       
      //Before the action that redirect to the new tab:
        String windHandleCurrent = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(5);
        // code that click in a btn/link in order to open a new tab goes here
        // now to make selenium move to the new tab 
        List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
   	 // Switch to 2nd tab (index 1)
 	  driver.switchTo().window(tabs1.get(tabs1.size() - 1));  
   
    TimeUnit.SECONDS.sleep(3);
   // verifyPDFContent(driver.getCurrentUrl(),ApplicationConstants.PLNDSC_PDF_TEXT,"level_PLNDSC.pdf");
        
    takeScreenshot("level_PLNDSC");	
      	 // switch to the first tab
        driver.switchTo().window(windHandleCurrent);
       

        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[4]/div/button[3]"));
        TimeUnit.SECONDS.sleep(5);
        WebElement letterComments=driver.findElement(By.id("letterComments"));
        System.out.println("letterComments  :  "+letterComments.getText());
        return letterComments.getText();
    }
    
    public String level2TransferNotNeeded() throws InterruptedException, IOException {
  	  
    	click(By.xpath("//*[@id=\"main\"]/li[14]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"sortOrder\"]/span[1]"));
   	 TimeUnit.SECONDS.sleep(2);
   	 click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
    	// find the customer table
    	/*WebElement table = driver.findElement(By.xpath("\".//*[@id='gridTable']//td[contains(.,'HED')]\")).getText()"));
    	System.out.println("table :" +table.getText());
    	// find the row
    	WebElement hedText = table.findElement(By.xpath("//tr/td[contains(text(), 'HED')]"));

    	// click on the row
    	hedText.click();*/

        TimeUnit.SECONDS.sleep(3);
        scrollDown();
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[1]/div[1]/div[3]/label"));
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"legalInfoCoordVfrd\"]"));        
        inputById("letterComments","test123");
        TimeUnit.SECONDS.sleep(3);
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"generateLetterBtnId\"]"));

      //Before the action that redirect to the new tab:
        String windHandleCurrent = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(3);
        // code that click in a btn/link in order to open a new tab goes here
        // now to make selenium move to the new tab 
        List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
   	 // Switch to 2nd tab (index 1)
 	  driver.switchTo().window(tabs1.get(tabs1.size() - 1));  
       
        TimeUnit.SECONDS.sleep(3);
        

      //  verifyPDFContent(driver.getCurrentUrl(),ApplicationConstants.TRANSFER_PDF_TEXT,"level_Transfer.pdf");
        takeScreenshot("level_Transfer");	
 		
      	 // switch to the first tab
        driver.switchTo().window(windHandleCurrent);

        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[4]/div/button[3]"));
        TimeUnit.SECONDS.sleep(3);
        WebElement letterComments=driver.findElement(By.id("letterComments"));
        System.out.println("letterComments  :  "+letterComments.getText());
        return letterComments.getText();
    }
    
    public void createLevel2() throws InterruptedException, IOException {
    	  
    	click(By.xpath("//*[@id=\"main\"]/li[14]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"sortOrder\"]/span[1]"));
   	 TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));

        TimeUnit.SECONDS.sleep(5);
        scrollDown();
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[1]/div[1]/div[1]/label"));
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"legalInfoCoordVfrd\"]"));
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"createLevel2Btn\"]"));
        
        TimeUnit.SECONDS.sleep(5);
        selectDropDownByVisibleText(By.id("evaluationTypeId"),"CIC");
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"main_content\"]/app-create-evaluation/div/section/div[2]/form[1]/div/div[8]/div/div/button[3]"));
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"main_content\"]/app-create-evaluation/div/section/div[2]/form[1]/div/div[8]/button[4]"));
        TimeUnit.SECONDS.sleep(5);

        selectDropDownByVisibleText(By.id("psychoSocialAssessor"),"CMH Professional LLPC,MD,DO,PA,OT,PT,NP,LMSW,LLP,LP,LLMSW,LBSW,LLBSW,MSN,LPC");
        TimeUnit.SECONDS.sleep(2);
       // selectDropDownByVisibleText(By.id("psychoSocialCounter"),"Agnes Bissett LMSW");
        //TimeUnit.SECONDS.sleep(2);
        click(By.id("psychosocial"));
        
        
        TimeUnit.SECONDS.sleep(5);

        selectDropDownByVisibleText(By.id("medicalAssessor"),"CMH Professional LLPC,MD,DO,PA,OT,PT,NP,LMSW,LLP,LP,LLMSW,LBSW,LLBSW,MSN,LPC");
        TimeUnit.SECONDS.sleep(2);
       // selectDropDownByVisibleText(By.id("psychologicalCounter"),"Kandi McKenzie LP");
        //TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"levelIIForm row\"]/div/table/tbody/tr[2]/td[4]/button")); 
        TimeUnit.SECONDS.sleep(2);
        selectDropDownByVisibleText(By.id("psychologicalAssessor"),"CMH Professional LLPC,MD,DO,PA,OT,PT,NP,LMSW,LLP,LP,LLMSW,LBSW,LLBSW,MSN,LPC");
        TimeUnit.SECONDS.sleep(2);
       // selectDropDownByVisibleText(By.id("psychoSocialCounter"),"Agnes Bissett LMSW");
        //TimeUnit.SECONDS.sleep(2);
        click(By.id("//*[@id=\"levelIIForm row\"]/div/table/tbody/tr[4]/td[4]/button"));
        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"main_content\"]/app-create-evaluation/div/section/div[2]/form[2]/div/div[5]/button[4]")); 
        TimeUnit.SECONDS.sleep(2);  
        
        
    }  
    
    
    public String level2Reset() throws InterruptedException, IOException {
    	  
    	click(By.xpath("//*[@id=\"main\"]/li[14]"));
    	TimeUnit.SECONDS.sleep(5);
    	 click(By.xpath("//*[@id=\"sortOrder\"]/span[1]"));
    	 TimeUnit.SECONDS.sleep(2);
    	// click(By.xpath("//*[@id=\"referralDate\"]/a"));
    	 //TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
    	// find the customer table
    	/*WebElement table = driver.findElement(By.xpath("//*[@id=\"gridTable\"]/tbody"));

    	// find the row
    	WebElement arrText = table.findElement(By.xpath("//tr/td[contains(text(), 'ARR')]"));

    	// click on the row
    	arrText.click();*/

        TimeUnit.SECONDS.sleep(3);
        scrollDown();
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"nsmiLtrInd\"]"));
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"legalInfoCoordVfrd\"]"));
        TimeUnit.SECONDS.sleep(3);
        inputById("letterComments","test");
        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"generateLetterBtnId\"]"));
        String windHandleCurrent = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(3);

 
      /*  ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
            for(int i =0;i<windows.size();i++ ) {
                String aWindow = windows.get(i);
                if(aWindow != windHandleCurrent) {
                    driver.switchTo().window(aWindow);
                }
            }
        System.out.println("url  :  "+driver.getCurrentUrl());*/
        
        List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
   	 // Switch to 2nd tab (index 1)
 	  driver.switchTo().window(tabs1.get(tabs1.size() - 1));   
       
        TimeUnit.SECONDS.sleep(3);
        //verifyPDFContent(driver.getCurrentUrl(),ApplicationConstants.NSNMI_PDF_TEXT,"level_NSNI.pdf");
        
		/*File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshot1.toPath(), Paths.get(System.getProperty("user.dir"), "reports", "Screenshots",
				"level_NSNI" + getCurrentDateTimeString() + ".png"));*/
		takeScreenshot("level_Reset");
      		
      	 // switch to the first tab
        driver.switchTo().window(windHandleCurrent);
       
       
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[4]/div/button[3]"));      
        
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[4]/div/button"));      
        TimeUnit.SECONDS.sleep(3);
        Alert alert = driver.switchTo().alert();

        	// Click OK (Accept)
        	alert.accept();
        	TimeUnit.SECONDS.sleep(5);
        	By elementLocator = By.xpath("//*[@id='main_content']/app-levelone-detail/div/section/form/div[2]/div/div/div[1]/div[1]/dl/dd[2]");

        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

        	String text = element.getText();
        	System.out.println("Text: " + text);
        TimeUnit.SECONDS.sleep(5);
      
        return element.getText();
    }

    
    
    
    public String level2Other() throws InterruptedException, IOException {
  	  
    	click(By.xpath("//*[@id=\"main\"]/li[14]"));
    	TimeUnit.SECONDS.sleep(5);
    	 click(By.xpath("//*[@id=\"sortOrder\"]/span[1]"));
    	 TimeUnit.SECONDS.sleep(2);
    	// click(By.xpath("//*[@id=\"referralDate\"]/a"));
    	 //TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
    	// find the customer table
    	/*WebElement table = driver.findElement(By.xpath("//*[@id=\"gridTable\"]/tbody"));

    	// find the row
    	WebElement arrText = table.findElement(By.xpath("//tr/td[contains(text(), 'ARR')]"));

    	// click on the row
    	arrText.click();*/

        TimeUnit.SECONDS.sleep(3);
        scrollDown();
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"levelOtherInd\"]"));
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"legalInfoCoordVfrd\"]"));
        TimeUnit.SECONDS.sleep(3);
        inputById("letterComments","test");
        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"generateLetterBtnId\"]"));
        String windHandleCurrent = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(3);

 
      /*  ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
            for(int i =0;i<windows.size();i++ ) {
                String aWindow = windows.get(i);
                if(aWindow != windHandleCurrent) {
                    driver.switchTo().window(aWindow);
                }
            }
        System.out.println("url  :  "+driver.getCurrentUrl());*/
        
        List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
   	 // Switch to 2nd tab (index 1)
 	  driver.switchTo().window(tabs1.get(tabs1.size() - 1));   
       
        TimeUnit.SECONDS.sleep(3);
        //verifyPDFContent(driver.getCurrentUrl(),ApplicationConstants.NSNMI_PDF_TEXT,"level_NSNI.pdf");
        
		/*File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshot1.toPath(), Paths.get(System.getProperty("user.dir"), "reports", "Screenshots",
				"level_NSNI" + getCurrentDateTimeString() + ".png"));*/
		takeScreenshot("level_Other");
      		
      	 // switch to the first tab
        driver.switchTo().window(windHandleCurrent);
       
       
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[4]/div/button[3]"));      
        
        TimeUnit.SECONDS.sleep(3);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/div[2]/div/button[2]"));
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/div[2]/div/ul/li[4]/a"));
        
        TimeUnit.SECONDS.sleep(3);
        
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/div[1]/form/div/div/div/div[3]/button"));
        
        TimeUnit.SECONDS.sleep(3);
      
        return null;
    }
    
    public void levelSendLetter() throws InterruptedException, IOException {
    	
    	
    	TimeUnit.SECONDS.sleep(3);
    	selectDropDownByVisibleText(By.xpath("//*[@id=\"userFacilities\"]"),
    			"Sparrow Clinton Hospital Swing Bed (Nursing Home) St. Johns (MI)");
    	
    	TimeUnit.SECONDS.sleep(3);
    	 click(By.xpath("//*[@id=\"main\"]/li[22]/a"));
    	 
    	 TimeUnit.SECONDS.sleep(3);
    	 click(By.xpath("//*[@id=\"sortOrder\"]/span[1]"));
    	 TimeUnit.SECONDS.sleep(3);
    	 click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]"));
    	 TimeUnit.SECONDS.sleep(3);
    	 click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/div[2]/div/button[2]"));
    	 TimeUnit.SECONDS.sleep(3);
    	 
    	 click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/div[2]/div/ul/li[7]/a"));
    	 String windHandleCurrent = driver.getWindowHandle();
    	 List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
       	 // Switch to 2nd tab (index 1)
     	  driver.switchTo().window(tabs1.get(tabs1.size() - 1));   
           
            TimeUnit.SECONDS.sleep(5);
    	 
    	 takeScreenshot("sendletter");
    	 TimeUnit.SECONDS.sleep(5);
    	// switch to the first tab
         driver.switchTo().window(windHandleCurrent);
    }
    //			
}
