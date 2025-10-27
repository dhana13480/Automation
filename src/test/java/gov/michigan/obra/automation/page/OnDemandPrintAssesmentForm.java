package gov.michigan.obra.automation.page;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnDemandPrintAssesmentForm extends BasePage{

	
    public OnDemandPrintAssesmentForm(WebDriver driver) {
		
    	
    	super(driver);
	}

    public void testPrintAssesmentForm() throws InterruptedException, IOException {
       try {
    	   
    	   TimeUnit.SECONDS.sleep(5);
      // 	click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[2]"));  
    	   WebDriverWait waito = new WebDriverWait(driver, Duration.ofSeconds(5));

    	// 1️⃣ Wait until HeaderOverlay disappears
    	   waito.until(ExpectedConditions.invisibilityOfElementLocated(
    	    By.cssSelector(".HeaderOverlay")
    	));

    	// 2️⃣ Then wait until your target element is clickable
    	WebElement navItem = waito.until(ExpectedConditions.elementToBeClickable(
    	    By.xpath("//*[@id='nav-main']/div/div[1]/a/div/p[2]")
    	));

    	// 3️⃣ Now click safely
    	navItem.click();
       	TimeUnit.SECONDS.sleep(5);   
       	WebElement element = driver.findElement(By.xpath("//h2[text()='Evaluations']/parent::a"));
       	element.click();
     	TimeUnit.SECONDS.sleep(5);
     	

    	WebElement textArea1 = driver.findElement(By.xpath("//*[@id=\"eval_lastname\"]"));
    	TimeUnit.SECONDS.sleep(5);
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
    	
    	click(By.xpath("//*[@id=\"main_content\"]/app-evaluation/app-evaluation-search/div/section/form/div[4]/div[5]/div/button[1]"));
    	
    	TimeUnit.SECONDS.sleep(5);
    	
    	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"determinationDate\"]/a"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]"));
    	TimeUnit.SECONDS.sleep(5);
    	
    	// Scroll to bottom
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    	TimeUnit.SECONDS.sleep(3);
    	String originalWindow = driver.getWindowHandle();
    	//3877 form
    	WebElement element1 = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[6]/div/p"));

    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
    	
    	String readOnlyTab = driver.getWindowHandle();
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	 
    	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[6]/div/div/div[1]/div[1]/a"))).click();
    	 Thread.sleep(5000);

    	 TimeUnit.SECONDS.sleep(5);
    	    
    	    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
    	 // Switch to 2nd tab (index 1)
    	    driver.switchTo().window(tabs.get(tabs.size() - 1)); 
    	 	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        	TimeUnit.SECONDS.sleep(3);

    	    // 5️⃣ Wait for the Print button in the regular tab and click
    	    WebElement printButton = wait.until(ExpectedConditions.elementToBeClickable(
    	            By.xpath("//*[@id=\"print\"]")));
    	    printButton.click(); // or JS click if needed:
    	    TimeUnit.SECONDS.sleep(15);	
    	     
        	File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        	System.out.println("filePath : " +screenshot.getAbsolutePath());
        	Files.copy(screenshot.toPath(), Paths.get("PsychosocialForm.png"));
        	TimeUnit.SECONDS.sleep(10);	
        	System.out.println("filePath : " +screenshot.getAbsolutePath());
        	System.out.println("filePath : " +screenshot.getCanonicalPath());
        	
    	    driver.close();
    	    driver.switchTo().window(readOnlyTab);
    	
    	
    	
    	TimeUnit.SECONDS.sleep(5);	
    	//3878 Form 
	  
    	// Click link to open PDF
	   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[6]/div/div/div[1]/div[2]/a"))).click();

    	
	   
	   
    	// Interact with PDF (or just wait)
    	Thread.sleep(5000);
    	   List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
      	 // Switch to 2nd tab (index 1)
    	   driver.switchTo().window(tabs1.get(tabs1.size() - 1));
      	  Thread.sleep(5000);
      	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
      	TimeUnit.SECONDS.sleep(5);	
      	  WebElement printButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"print\"]")));
      	printButton1.click();
    	Thread.sleep(5000);
    	TimeUnit.SECONDS.sleep(10);	
    	File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	Files.copy(screenshot1.toPath(), Paths.get("medical.png"));
    	// Close PDF tab and switch back
    	Thread.sleep(5000);
    	driver.close();
    	driver.switchTo().window(readOnlyTab);
    	TimeUnit.SECONDS.sleep(5);
    	
    	//View All
    	 //driver.switchTo().window(readOnlyTab);
    	List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
     	 // Switch to 2nd tab (index 1)
   	
    	// Click link to open PDF
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[6]/div/div/div[2]/div/button"))).click();
    	TimeUnit.SECONDS.sleep(10);	
    	   driver.switchTo().window(tabs2.get(tabs2.size() - 1));
    	File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	Files.copy(screenshot2.toPath(), Paths.get("AssessmentViewAll.png"));
    	Thread.sleep(5000);
    	//driver.switchTo().window(readOnlyTab);
 	  // List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
   	 // Switch to 2nd tab (index 1)
 	  //driver.switchTo().window(tabs2.get(tabs2.size() - 1));

    	// Interact with PDF (or just wait)
    	Thread.sleep(500);
    	TimeUnit.SECONDS.sleep(5);
    	driver.switchTo().window(readOnlyTab);
    	/*WebElement generateLetter1 = driver.findElement(By.xpath("//a[contains(., 'Generate Letter')]"));
      	generateLetter1.click();*/
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	WebElement dropdownToggle3 = driver.findElement(By.xpath("//div[contains(@class,'btn-group') and contains(@class,'user-detail')]//button[contains(@class,'dropdown-toggle')]"));

     	// Scroll into view first
     	js.executeScript("arguments[0].scrollIntoView(true);", dropdownToggle3);

     	// Force click
     	js.executeScript("arguments[0].click();", dropdownToggle3);

     	TimeUnit.SECONDS.sleep(2);
    	
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
    	
    	/*
    	WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(15));
    	WebElement generateLetter1 = wait0.until(
    	    ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., 'Generate Letter')]"))
    	);
    	generateLetter1.click();
      	TimeUnit.SECONDS.sleep(5);
    	
      	WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
      	wait3.until(ExpectedConditions.alertIsPresent());
      	// Switch to the alert
      	Alert alert1 = driver.switchTo().alert();
      	// Accept the alert (clicks OK)
      	alert1.accept();*/
       	List<String> tabs3 = new ArrayList<>(driver.getWindowHandles());
       	driver.switchTo().window(tabs3.get(tabs3.size() - 1));
    	TimeUnit.SECONDS.sleep(10);	
    	File screenshot3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	Files.copy(screenshot3.toPath(), Paths.get("ComprehensiveLevelIIEvaluation.png"));
    	Thread.sleep(5000);
    	

    	// Close PDF tab and switch back
    	driver.close();
    	//driver.switchTo().window(readOnlyTab);
    	TimeUnit.SECONDS.sleep(5);
    
    	
    	
       	
       }catch (Exception e) {
		e.printStackTrace();
	}
    }

    

}
