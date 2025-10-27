package gov.michigan.obra.automation.page;

import org.openqa.selenium.Alert;
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

public class OnDemandPrintDeterminationLetter extends BasePage{

	
    public OnDemandPrintDeterminationLetter(WebDriver driver) {
		
    	
    	super(driver);
	}

    public void testPrintDeterminationLetter() throws InterruptedException, IOException {
       try {
    	   TimeUnit.SECONDS.sleep(2);          	
          	click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[2]"));
    	
           	WebElement element = driver.findElement(By.xpath("//h2[text()='Evaluations']/parent::a"));
           	element.click();
         	TimeUnit.SECONDS.sleep(3);

        	
        	// Click the dropdown to open it
        	// 1️⃣ Click the dropdown (open it)
        	WebElement dropdown = driver.findElement(By.xpath("//angular2-multiselect[@formcontrolname='determination']//div[@class='c-btn']"));
        	dropdown.click();

        	// 2️⃣ Wait for the options to appear
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']")));
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	/*for (int i = 1; i <= 3; i++) {
        	    WebElement cb = driver.findElement(By.xpath("(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[" + i + "]"));
        	    js.executeScript("arguments[0].scrollIntoView(true);", cb);
        	    js.executeScript("arguments[0].click();", cb);
        	    System.out.println("(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[" + i + "]");
        	    Thread.sleep(300);
        	}*
        	
        	// 3️⃣ Click the 2nd option
        	/*WebElement firstOption = driver.findElement(By.xpath("(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[2]"));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstOption);*/
        	
        	for (int i = 1; i <= 3; i++) { 
        	    WebElement cb = driver.findElement(By.xpath("(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[" + i + "]"));
        	    js.executeScript("arguments[0].scrollIntoView(true);", cb);
        	    js.executeScript("arguments[0].click();", cb);
        	    System.out.println("Clicked checkbox: " + i);

        	    // Stop after the first checkbox
        	    break;
        	}

        	// 4️⃣ (Optional) Close the dropdown
        	//dropdown.click();
        	
        	TimeUnit.SECONDS.sleep(3);
        	//Search 
        	click(By.xpath("//*[@id=\"main_content\"]/app-evaluation/app-evaluation-search/div/section/form/div[4]/div[5]/div/button[1]"));
        	TimeUnit.SECONDS.sleep(2);
        	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
        	TimeUnit.SECONDS.sleep(2);
        	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
        	TimeUnit.SECONDS.sleep(2);
        	click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
        	TimeUnit.SECONDS.sleep(5);
        	
        	//Ham menu
        	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/button[1]"));
        	WebElement dropdownToggle = driver.findElement(By.xpath("//div[contains(@class,'btn-group') and contains(@class,'user-detail')]//button[contains(@class,'dropdown-toggle')]"));

        	// Scroll into view first
        	js.executeScript("arguments[0].scrollIntoView(true);", dropdownToggle);

        	// Force click
        	js.executeScript("arguments[0].click();", dropdownToggle);

        	TimeUnit.SECONDS.sleep(2); // small wait for dropdown to open
        	//genarate letter
        	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/ul/span/li[1]/a"));
          	
          	//WebDriverWait waitd = new WebDriverWait(driver, Duration.ofSeconds(10));
          	WebElement generateLetter = driver.findElement(By.xpath("//a[contains(., 'Generate Letter')]"));
          	generateLetter.click();
          	TimeUnit.SECONDS.sleep(2);
          	
          	 // 1️⃣ Save the original read-only tab
    	    String readOnlyTab = driver.getWindowHandle();
    	    
         // Wait for the alert to be present (optional, but recommended)
          	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
          	wait1.until(ExpectedConditions.alertIsPresent());
          	// Switch to the alert
          	Alert alert = driver.switchTo().alert();
          	// Accept the alert (clicks OK)
          	alert.accept();
          	

      	   List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
        	 // Switch to 2nd tab (index 1)
      	  driver.switchTo().window(tabs1.get(tabs1.size() - 1));      	  
      	
         	// Interact with PDF (or just wait)
         	Thread.sleep(500);
         	TimeUnit.SECONDS.sleep(10);
         	File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
          	Files.copy(screenshot1.toPath(), Paths.get("NursingFacilityNoMental.png"));
         	driver.switchTo().window(readOnlyTab);
         	TimeUnit.SECONDS.sleep(10);
         	
         	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[1]/div[2]/ul/li[2]/a"));
         	WebElement elementLink = driver.findElement(By.xpath("//*[@id='main_content']/app-evaluation-detail/div/section/div[1]/div[2]/ul/li[2]/a"));
         	((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementLink);
         	TimeUnit.SECONDS.sleep(3);
         	
         	WebElement resetButton = driver.findElement(By.xpath("//button[contains(@class,'yellow-btn') and text()='Reset']"));

         // 2️⃣ Scroll it into view (in case a sticky header is covering it)
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resetButton);

         // 3️⃣ Wait a short moment
         Thread.sleep(300); // optional but helps

         // 4️⃣ Click using JavaScript to avoid interception
         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resetButton);
         	
        	//=====================================Flow2 
        	// Click the dropdown to open it
        	// 1️⃣ Click the dropdown (open it)
        	//WebElement dropdown = driver.findElement(By.xpath("//angular2-multiselect[@formcontrolname='determination']//div[@class='c-btn']"));
         	WebElement dropdown1 = driver.findElement(By.xpath("//angular2-multiselect[@formcontrolname='determination']//div[@class='c-btn']"));
        	dropdown1.click();

        	// 2️⃣ Wait for the options to appear
        	WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        	wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']")));

        	// 3️⃣ Click the 2nd option
        	/*WebElement firstOption1 = driver.findElement(By.xpath("(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[2]"));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstOption1);*/
        	//*[@id="add_multiselect_user_role"]/div/div[2]/div[3]/div[3]/ul/li[1]/label[2]
        	// 4️⃣ (Optional) Close the dropdown
        	// Second checkbox
        	// Select 2nd checkbox
        	for (int i = 2; i <= 2; i++) {
        	    WebElement cb2 = driver.findElement(By.xpath(
        	        "(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[" + i + "]"
        	    ));
        	    js.executeScript("arguments[0].scrollIntoView(true);", cb2);
        	    js.executeScript("arguments[0].click();", cb2);
        	    System.out.println("Clicked checkbox 2");
        	}
        	//dropdown1.click();
        	
        	TimeUnit.SECONDS.sleep(3);
        	//Search 
        	click(By.xpath("//*[@id=\"main_content\"]/app-evaluation/app-evaluation-search/div/section/form/div[4]/div[5]/div/button[1]"));
        	TimeUnit.SECONDS.sleep(2);
        	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
        	TimeUnit.SECONDS.sleep(2);
        	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
        	TimeUnit.SECONDS.sleep(2);
        	click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
        	TimeUnit.SECONDS.sleep(5);
        	
        	//Ham menu
        	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/button[1]"));
        	/*WebElement dropdownToggle1 = driver.findElement(By.xpath("//div[contains(@class,'btn-group') and contains(@class,'user-detail')]//button[contains(@class,'dropdown-toggle')]"));
        		dropdownToggle1.click();
          	TimeUnit.SECONDS.sleep(3);*/
          	
          	
          	WebElement dropdownToggle1 = driver.findElement(By.xpath("//div[contains(@class,'btn-group') and contains(@class,'user-detail')]//button[contains(@class,'dropdown-toggle')]"));

          	// Scroll into view first
          	js.executeScript("arguments[0].scrollIntoView(true);", dropdownToggle1);

          	// Force click
          	js.executeScript("arguments[0].click();", dropdownToggle1);

          	TimeUnit.SECONDS.sleep(2); // small wait for dropdown to open
        	//genarate letter
        	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/ul/span/li[1]/a"));
          	
          	//WebDriverWait waitd = new WebDriverWait(driver, Duration.ofSeconds(10));
          	WebElement generateLetter1 = driver.findElement(By.xpath("//a[contains(., 'Generate Letter')]"));
          	generateLetter1.click();
          	TimeUnit.SECONDS.sleep(2);
          	
          	 // 1️⃣ Save the original read-only tab
    	    //String readOnlyTab1 = driver.getWindowHandle();
    	    
         // Wait for the alert to be present (optional, but recommended)
          	WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
          	wait3.until(ExpectedConditions.alertIsPresent());
          	// Switch to the alert
          	Alert alert1 = driver.switchTo().alert();
          	// Accept the alert (clicks OK)
          	alert1.accept();
          	

      	   List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        	 // Switch to 2nd tab (index 1)
      	  driver.switchTo().window(tabs2.get(tabs2.size() - 1));      	  
      	
         	// Interact with PDF (or just wait)
         	Thread.sleep(500);
         	TimeUnit.SECONDS.sleep(10);
         	File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
          	Files.copy(screenshot1.toPath(), Paths.get("NursingFacilityMental.png"));
         	driver.switchTo().window(readOnlyTab);
         	TimeUnit.SECONDS.sleep(10);
         	
         	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[1]/div[2]/ul/li[2]/a"));
         	WebElement elementLink1 = driver.findElement(By.xpath("//*[@id='main_content']/app-evaluation-detail/div/section/div[1]/div[2]/ul/li[2]/a"));
         	((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementLink1);
         	TimeUnit.SECONDS.sleep(3);
         	
         	WebElement resetButton1 = driver.findElement(By.xpath("//button[contains(@class,'yellow-btn') and text()='Reset']"));

         // 2️⃣ Scroll it into view (in case a sticky header is covering it)
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resetButton1);

         // 3️⃣ Wait a short moment
         Thread.sleep(300); // optional but helps

         // 4️⃣ Click using JavaScript to avoid interception
         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resetButton1);	
         	TimeUnit.SECONDS.sleep(3);
        	
        	
        	
         	//=====================================Flow3 
        	
        	
         	
         	WebElement dropdown2 = driver.findElement(By.xpath("//angular2-multiselect[@formcontrolname='determination']//div[@class='c-btn']"));
        	dropdown2.click();

        	// 2️⃣ Wait for the options to appear
        	WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(5));
        	wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']")));

        	// 3️⃣ Click the 2nd option
        	/*WebElement firstOption2 = driver.findElement(By.xpath("(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[1]"));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstOption2);*/
        	
        	// Third checkbox
        	// Select 3rd checkbox  Nursing Facility-Other Mental Health Services
        	for (int i = 3; i <= 3; i++) {
        	    WebElement cb3 = driver.findElement(By.xpath(
        	        "(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[" + i + "]"
        	    ));
        	    js.executeScript("arguments[0].scrollIntoView(true);", cb3);
        	    js.executeScript("arguments[0].click();", cb3);
        	    System.out.println("Clicked checkbox 3");
        	}
        	// 4️⃣ (Optional) Close the dropdown
        	//dropdown2.click();
        	
        	TimeUnit.SECONDS.sleep(3);
        	//Search 
        	click(By.xpath("//*[@id=\"main_content\"]/app-evaluation/app-evaluation-search/div/section/form/div[4]/div[5]/div/button[1]"));
        	TimeUnit.SECONDS.sleep(2);
        	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
        	TimeUnit.SECONDS.sleep(2);
        	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
        	TimeUnit.SECONDS.sleep(2);
        	click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
        	TimeUnit.SECONDS.sleep(5);
        	
        	//Ham menu
        	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/button[1]"));
        	WebElement dropdownToggle2 = driver.findElement(By.xpath("//div[contains(@class,'btn-group') and contains(@class,'user-detail')]//button[contains(@class,'dropdown-toggle')]"));

        	// Scroll into view first
        	js.executeScript("arguments[0].scrollIntoView(true);", dropdownToggle2);

        	// Force click
        	js.executeScript("arguments[0].click();", dropdownToggle2);

        	TimeUnit.SECONDS.sleep(10); // small wait for dropdown to open
        	//genarate letter
        	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/ul/span/li[1]/a"));
          	
          	//WebDriverWait waitd = new WebDriverWait(driver, Duration.ofSeconds(10));
          	WebElement generateLetter2 = driver.findElement(By.xpath("//a[contains(., 'Generate Letter')]"));
          	generateLetter2.click();
          	TimeUnit.SECONDS.sleep(5);
          	
          	 // 1️⃣ Save the original read-only tab
    	    //String readOnlyTab1 = driver.getWindowHandle();
    	    
         // Wait for the alert to be present (optional, but recommended)
          	WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));
          	wait5.until(ExpectedConditions.alertIsPresent());
          	// Switch to the alert
          	Alert alert2 = driver.switchTo().alert();
          	// Accept the alert (clicks OK)
          	alert2.accept();
          	

      	   List<String> tabs3 = new ArrayList<>(driver.getWindowHandles());
        	 // Switch to 2nd tab (index 1)
      	  driver.switchTo().window(tabs3.get(tabs3.size() - 1));      	  
      	
         	// Interact with PDF (or just wait)
         	Thread.sleep(500);
         	TimeUnit.SECONDS.sleep(10);
         	File screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
          	Files.copy(screenshot3.toPath(), Paths.get("NursingFacilityMentalOther.png"));
         	driver.switchTo().window(readOnlyTab);
         	TimeUnit.SECONDS.sleep(5);
         	
         	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[1]/div[2]/ul/li[2]/a"));
         	WebElement elementLink2 = driver.findElement(By.xpath("//*[@id='main_content']/app-evaluation-detail/div/section/div[1]/div[2]/ul/li[2]/a"));
         	((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementLink2);
         	TimeUnit.SECONDS.sleep(3);
         	
         	WebElement resetButton2 = driver.findElement(By.xpath("//button[contains(@class,'yellow-btn') and text()='Reset']"));

         // 2️⃣ Scroll it into view (in case a sticky header is covering it)
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resetButton2);

         // 3️⃣ Wait a short moment
         Thread.sleep(300); // optional but helps

         // 4️⃣ Click using JavaScript to avoid interception
         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resetButton2);	
         
         
       //=====================================Flow4
     	
     	
      	
      	WebElement dropdown4 = driver.findElement(By.xpath("//angular2-multiselect[@formcontrolname='determination']//div[@class='c-btn']"));
     	dropdown4.click();

     	// 2️⃣ Wait for the options to appear
     	WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(5));
     	wait6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']")));

     	// 3️⃣ Click the 2nd option
     	/*WebElement firstOption2 = driver.findElement(By.xpath("(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[1]"));
     	((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstOption2);*/
     	
     	// Third checkbox
     	// Select 3rd checkbox  Nursing Facility-Other Mental Health Services
     	for (int i = 4; i <= 4; i++) {
     	    WebElement cb3 = driver.findElement(By.xpath(
     	        "(//angular2-multiselect[@formcontrolname='determination']//ul[@class='lazyContainer']//input[@type='checkbox'])[" + i + "]"
     	    ));
     	    js.executeScript("arguments[0].scrollIntoView(true);", cb3);
     	    js.executeScript("arguments[0].click();", cb3);
     	    System.out.println("Clicked checkbox 4");
     	}
     	// 4️⃣ (Optional) Close the dropdown
     	//dropdown2.click();
     	
     	TimeUnit.SECONDS.sleep(3);
     	//Search 
     	click(By.xpath("//*[@id=\"main_content\"]/app-evaluation/app-evaluation-search/div/section/form/div[4]/div[5]/div/button[1]"));
     	TimeUnit.SECONDS.sleep(2);
     	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
     	TimeUnit.SECONDS.sleep(2);
     	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
     	TimeUnit.SECONDS.sleep(2);
     	click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
     	TimeUnit.SECONDS.sleep(5);
     	
     	//Ham menu
     	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/button[1]"));
     	WebElement dropdownToggle3 = driver.findElement(By.xpath("//div[contains(@class,'btn-group') and contains(@class,'user-detail')]//button[contains(@class,'dropdown-toggle')]"));

     	// Scroll into view first
     	js.executeScript("arguments[0].scrollIntoView(true);", dropdownToggle3);

     	// Force click
     	js.executeScript("arguments[0].click();", dropdownToggle3);

     	TimeUnit.SECONDS.sleep(2); // small wait for dropdown to open
     	//genarate letter
     	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/ul/span/li[1]/a"));
       	
       	//WebDriverWait waitd = new WebDriverWait(driver, Duration.ofSeconds(10));
       	WebElement generateLetter3 = driver.findElement(By.xpath("//a[contains(., 'Generate Letter')]"));
       	generateLetter3.click();
       	TimeUnit.SECONDS.sleep(2);
       	
       	 // 1️⃣ Save the original read-only tab
 	    //String readOnlyTab1 = driver.getWindowHandle();
 	    
      // Wait for the alert to be present (optional, but recommended)
       	WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));
       	wait7.until(ExpectedConditions.alertIsPresent());
       	// Switch to the alert
       	Alert alert3 = driver.switchTo().alert();
       	// Accept the alert (clicks OK)
       	alert3.accept();
       	

   	   List<String> tabs4 = new ArrayList<>(driver.getWindowHandles());
     	 // Switch to 2nd tab (index 1)
   	  driver.switchTo().window(tabs4.get(tabs4.size() - 1));      	  
   	
      	// Interact with PDF (or just wait)
      	Thread.sleep(500); 
      	TimeUnit.SECONDS.sleep(10);
      	File screenshot4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       	Files.copy(screenshot4.toPath(), Paths.get("NoNursingFacilitySpecialMental.png"));
      	driver.switchTo().window(readOnlyTab);
      	TimeUnit.SECONDS.sleep(6);
      	
      	//click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[1]/div[2]/ul/li[2]/a"));
      	WebElement elementLink3 = driver.findElement(By.xpath("//*[@id='main_content']/app-evaluation-detail/div/section/div[1]/div[2]/ul/li[2]/a"));
      	((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementLink3);
      	TimeUnit.SECONDS.sleep(3);
      	
      	WebElement resetButton3 = driver.findElement(By.xpath("//button[contains(@class,'yellow-btn') and text()='Reset']"));

      // 2️⃣ Scroll it into view (in case a sticky header is covering it)
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resetButton3);

      // 3️⃣ Wait a short moment
      Thread.sleep(300); // optional but helps

      // 4️⃣ Click using JavaScript to avoid interception
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", resetButton3);	
         	
       	driver.close();
       }catch (Exception e) {
		e.printStackTrace();
	}
    }

    

}
