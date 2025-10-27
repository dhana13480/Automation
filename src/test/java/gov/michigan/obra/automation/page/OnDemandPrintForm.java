package gov.michigan.obra.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 *  Project Name    : dhhs-obra-test-automation
 *  Developer       : Thumsi, Sreenivasulu
 *  Version         : 1.0.0
 *  Date            : 9/10/24, 11:28 AM
 *
 */

import org.testng.annotations.Test;

import gov.michigan.obra.automation.BaseTest;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OnDemandPrintForm extends BasePage{

	
    public OnDemandPrintForm(WebDriver driver) {
		
    	
    	super(driver);
	}

    public void testPrint7877Form() throws InterruptedException, IOException {
       try {
    	   
    	   TimeUnit.SECONDS.sleep(2);
       //	click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[2]"));  
    	   WebElement element = driver.findElement(By.xpath("//*[@id='nav-main']/div/div[1]/a/div/p[2]"));
    	   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
       	TimeUnit.SECONDS.sleep(2);   
       	
       	WebElement element1 = driver.findElement(By.xpath("//h2[text()='3877-78']/parent::a"));
       	element1.click();
     	TimeUnit.SECONDS.sleep(2);
     	

    	WebElement textArea1 = driver.findElement(By.xpath("//*[@id=\"lname\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea1.sendKeys("a");
    	TimeUnit.SECONDS.sleep(3);
    	/*
    	WebElement dropdown = driver.findElement(By.id("add_multiselect_user_role"));
    	dropdown.click();

    	// 2️⃣ Wait for list and click 2nd option
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement secondOption = wait.until(ExpectedConditions.elementToBeClickable(
    	    By.xpath("(//angular2-multiselect[@id='add_multiselect_user_role']//ul[@class='lazyContainer']/li)[2]//input")
    	));
    	secondOption.click();*/
    	
    	click(By.xpath("//*[@id=\"filter-row\"]/div[3]/div/button[1]"));
    	
    	TimeUnit.SECONDS.sleep(5);
    	
    	click(By.xpath("//*[@id=\"submitDate\"]/a"));
    	TimeUnit.SECONDS.sleep(5);
    	//click(By.xpath("//*[@id=\"submitDate\"]/a"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]/span/a"));
    	TimeUnit.SECONDS.sleep(5);
    	
    	// Scroll to bottom
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    	TimeUnit.SECONDS.sleep(3);
    	String originalWindow = driver.getWindowHandle();
    	//3877 form

    	
    	
    	
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	    // 1️⃣ Save the original read-only tab
    	    String readOnlyTab = driver.getWindowHandle();

    	    // 2️⃣ Click the link in the read-only browser (opens new tab)
    	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[3]/div/div/div/div[1]/a"))).click();


    	    
    	    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
    	 // Switch to 2nd tab (index 1)
    	    driver.switchTo().window(tabs.get(2));
    	    

    	    // 5️⃣ Wait for the Print button in the regular tab and click
    	    WebElement printButton = wait.until(ExpectedConditions.elementToBeClickable(
    	            By.xpath("//*[@id=\"section1\"]/form/div[3]/ul/li[4]/button")));
    	    printButton.click(); // or JS click if needed:
    	    Thread.sleep(500);
    	    TimeUnit.SECONDS.sleep(10);	
        	File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        	Files.copy(screenshot.toPath(), Paths.get("3877.png"));
    	    driver.close();
    	    driver.switchTo().window(readOnlyTab);
    	
    	
    	
    	TimeUnit.SECONDS.sleep(5);	
    	//3878 Form 
	  
    	// Click link to open PDF
	   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[3]/div/div/div/div[2]/a"))).click();

    	
	   
	   
    	// Interact with PDF (or just wait)
    	Thread.sleep(5000);
    	   List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
      	 // Switch to 2nd tab (index 1)
    	   driver.switchTo().window(tabs1.get(tabs1.size() - 1));
      	  Thread.sleep(5000);
      	  WebElement printButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"section1\"]/div[2]/ul/li[2]/button")));
      	printButton1.click();
    	Thread.sleep(5000);
    	File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	Files.copy(screenshot1.toPath(), Paths.get("3878.png"));
    	// Close PDF tab and switch back
    	Thread.sleep(5000);
    	driver.close();
    	driver.switchTo().window(readOnlyTab);
    	TimeUnit.SECONDS.sleep(5);
    	
    	//View All

    	// Click link to open PDF
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[3]/div/div/div/div[3]/button"))).click();

 	   List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
   	 // Switch to 2nd tab (index 1)
 	  driver.switchTo().window(tabs2.get(tabs2.size() - 1));

    	// Interact with PDF (or just wait)
    	Thread.sleep(500);
    	
    	/*WebElement printButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"section1\"]/div[2]/ul/li[2]/button")));
    	printButton2.click();*/
    	/*WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(15));
    	WebElement button = wait0.until(ExpectedConditions.visibilityOfElementLocated(
    	    By.xpath("//*[@id=\"section1\"]/div[2]/ul/li[2]/button")
    	));
    	button.click();*/
    	
    	Thread.sleep(500);
    	TimeUnit.SECONDS.sleep(5);
    	File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	Files.copy(screenshot2.toPath(), Paths.get("ViewAll.png"));
    	Thread.sleep(500);
    	

    	// Close PDF tab and switch back
    	driver.close();
    	driver.switchTo().window(readOnlyTab);
    	TimeUnit.SECONDS.sleep(2);
    	
    	
    	
       	
       }catch (Exception e) {
		e.printStackTrace();
	}
    }

    

}
