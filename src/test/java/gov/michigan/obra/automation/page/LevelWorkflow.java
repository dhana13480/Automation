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
import java.util.ArrayList;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.michigan.obra.automation.common.constant.ApplicationConstants;


public class LevelWorkflow extends BasePage{
    public LevelWorkflow(WebDriver driver) {
        super(driver);
    }

    public String level2NSMNotNeededFlow() throws InterruptedException, IOException {
  
    	click(By.xpath("//*[@id=\"main\"]/li[14]"));
    	TimeUnit.SECONDS.sleep(5);	
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
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[1]/div[1]/div[2]/label"));
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"legalInfoCoordVfrd\"]"));
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"generateLetterBtnId\"]"));
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
        verifyPDFContent(driver.getCurrentUrl(),ApplicationConstants.NSNMI_PDF_TEXT,"level_NSNI.pdf");

      		
      	 // switch to the first tab
        driver.switchTo().window(windHandleCurrent);
       
        inputById("letterComments","test");
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
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[4]/td[1]"));
    	
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
        //String windHandleCurrent = driver.getWindowHandle();
        click(By.xpath("//*[@id=\"generateLetterBtnId\"]"));
       
      //Before the action that redirect to the new tab:
        String windHandleCurrent = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(5);
        // code that click in a btn/link in order to open a new tab goes here
        // now to make selenium move to the new tab 
        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        for(int i =0;i<windows.size();i++ ) {
            String aWindow = windows.get(i);
            if(aWindow != windHandleCurrent) {
                driver.switchTo().window(aWindow);
            }
        }
    System.out.println("url  :  "+driver.getCurrentUrl());
   
    TimeUnit.SECONDS.sleep(3);
    verifyPDFContent(driver.getCurrentUrl(),ApplicationConstants.PLNDSC_PDF_TEXT,"level_PLNDSC.pdf");
        
      		
      	 // switch to the first tab
        driver.switchTo().window(windHandleCurrent);
       
        inputById("letterComments","test123");
        TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[4]/div/button[3]"));
        TimeUnit.SECONDS.sleep(5);
        WebElement letterComments=driver.findElement(By.id("letterComments"));
        System.out.println("letterComments  :  "+letterComments.getText());
        return letterComments.getText();
    }
    
    public String level2TransferNotNeeded() throws InterruptedException, IOException {
  	  
    	click(By.xpath("//*[@id=\"main\"]/li[14]"));
    	TimeUnit.SECONDS.sleep(5);
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[10]/td[1]"));
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
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"generateLetterBtnId\"]"));

      //Before the action that redirect to the new tab:
        String windHandleCurrent = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(3);
        // code that click in a btn/link in order to open a new tab goes here
        // now to make selenium move to the new tab 
        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
            for(int i =0;i<windows.size();i++ ) {
                String aWindow = windows.get(i);
                if(aWindow != windHandleCurrent) {
                    driver.switchTo().window(aWindow);
                }
            }
        System.out.println("url  :  "+driver.getCurrentUrl());
       
        TimeUnit.SECONDS.sleep(3);
        

        verifyPDFContent(driver.getCurrentUrl(),ApplicationConstants.TRANSFER_PDF_TEXT,"level_Transfer.pdf");
      		
      	 // switch to the first tab
        driver.switchTo().window(windHandleCurrent);
       
        inputById("letterComments","test123");
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[5]/div/div/div/div[4]/div/button[3]"));
        TimeUnit.SECONDS.sleep(3);
        WebElement letterComments=driver.findElement(By.id("letterComments"));
        System.out.println("letterComments  :  "+letterComments.getText());
        return letterComments.getText();
    }
    
    public void createLevel2() throws InterruptedException, IOException {
    	  
    	click(By.xpath("//*[@id=\"main\"]/li[14]"));
    	TimeUnit.SECONDS.sleep(5);
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

        selectDropDownByVisibleText(By.id("psychoSocialAssessor"),"User CMHDeepak LBSW,DO,LP,ST,LLBSW");
        TimeUnit.SECONDS.sleep(2);
       // selectDropDownByVisibleText(By.id("psychoSocialCounter"),"Agnes Bissett LMSW");
        //TimeUnit.SECONDS.sleep(2);
        click(By.id("psychosocial"));
        
        
        TimeUnit.SECONDS.sleep(5);

        selectDropDownByVisibleText(By.id("sensoriAssessor"),"User CMHDeepak LBSW,DO,LP,ST,LLBSW");
        TimeUnit.SECONDS.sleep(2);
       // selectDropDownByVisibleText(By.id("psychologicalCounter"),"Kandi McKenzie LP");
        //TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"levelIIForm row\"]/div/table/tbody/tr[5]/td[4]/button")); 
        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"main_content\"]/app-create-evaluation/div/section/div[2]/form[2]/div/div[5]/button[4]")); 
        TimeUnit.SECONDS.sleep(2);        
    }  

}
