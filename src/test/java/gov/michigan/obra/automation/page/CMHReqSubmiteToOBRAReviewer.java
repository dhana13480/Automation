package gov.michigan.obra.automation.page;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CMHReqSubmiteToOBRAReviewer extends BasePage {
	
    public CMHReqSubmiteToOBRAReviewer(WebDriver driver) {
        super(driver);
    }

    public String pendingQueFlow() throws InterruptedException, IOException {
    	try {
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main\"]/li[9]/a"));
    	
    	TimeUnit.SECONDS.sleep(5);
    	
    	// Locate the table
    	WebElement table = driver.findElement(By.id("gridTable"));

    	// Click the first row
    	WebElement firstRow = table.findElement(By.xpath(".//tbody/tr[1]"));
    	//firstRow.click();
    	
    	// Get fresh cells
    	List<WebElement> cells = firstRow.findElements(By.tagName("td"));
    	firstRow.click();
    	// Get 2nd column text
    	String firstName = cells.get(1).getText();
    	System.out.println("Last Name: " + firstName);
    	TimeUnit.SECONDS.sleep(3);
    	
    	click(By.xpath("//*[@id=\"psychosocial\"]"));
    	TimeUnit.SECONDS.sleep(3);
    	click(By.xpath("//*[@id=\"levelIIForm row\"]/div/table/tbody/tr[2]/td[4]/button"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[2]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[2]/div[3]/a/h2"));
    	TimeUnit.SECONDS.sleep(5);
    	
    	// Get all <tr> rows inside the tbody
    	List<WebElement> rows = driver.findElements(By.xpath("//*[@id='gridTable']/tbody/tr"));
    	  String readOnlyTab = driver.getWindowHandle();
    	for (WebElement row : rows) {
    	    try {
    	        // Column 1: form name (asmtForm)
    	        WebElement formCell = row.findElement(By.xpath("./td[@headers='asmtForm']"));
    	        String formName = formCell.getText().trim();

    	        // Column 8: status
    	        WebElement statusCell = row.findElement(By.xpath("./td[@headers='status']"));
    	        String statusText = statusCell.getAttribute("title"); // or statusCell.getText()

    	        // Check if both match
    	        if (formName.equalsIgnoreCase("Medical") && statusText.equalsIgnoreCase("Assigned (Pending)")) {
    	            // Click the form link (inside column 1)
    	            WebElement link = formCell.findElement(By.xpath(".//a"));
    	            link.click();
    	            System.out.println("Clicked the first matching Medical record successfully.");
    	            break; // stop after first match
    	        }
    	    } catch (NoSuchElementException e) {
    	        // Skip if any cell not found
    	        continue;
    	    }
    	}
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    	WebElement medicalTab = wait.until(ExpectedConditions.elementToBeClickable(
    	    By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[1]/a")
    	));
    	medicalTab.click();
    	
    	TimeUnit.SECONDS.sleep(3);
    	WebElement textArea1 = driver.findElement(By.xpath("//*[@id=\"physician\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	textArea1.sendKeys("This is my input text!");
    	TimeUnit.SECONDS.sleep(5);
    	
    	WebElement date = driver.findElement(By.xpath("//*[@id=\"section1\"]/div[3]/div[1]/div/div/my-date-picker/div/div[1]/input"));
    	LocalDate today = LocalDate.now();
    	String formattedDate = today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    	date.sendKeys(formattedDate);
    	WebElement heightDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
    		    By.xpath("//*[@id='currentHeight']")
    		));

    		// Create Select object
    		Select select = new Select(heightDropdown);

    		// Select the first option (index starts from 0)
    		select.selectByIndex(0);
    		WebElement currentWeight = driver.findElement(By.xpath("//*[@id=\"currentWeight\"]"));
    		currentWeight.sendKeys("6");
        	TimeUnit.SECONDS.sleep(2);
    		WebElement bloodPressure = driver.findElement(By.xpath("//*[@id=\"bloodPressure\"]"));
    		bloodPressure.sendKeys("6");
        	TimeUnit.SECONDS.sleep(2);
    		WebElement pulseRate = driver.findElement(By.xpath("//*[@id=\"pulseRate\"]"));
    		pulseRate.sendKeys("6");
        	TimeUnit.SECONDS.sleep(2);
    		WebElement respirations = driver.findElement(By.xpath("//*[@id=\"respirations\"]"));
    		respirations.sendKeys("6");
        	TimeUnit.SECONDS.sleep(2);
        	WebElement ibwr = driver.findElement(By.xpath("//*[@id=\"ibwr\"]"));
        	ibwr.sendKeys("6");
        	TimeUnit.SECONDS.sleep(2);
        	
        	WebElement medication0 = driver.findElement(By.xpath("//*[@id=\"medication0\"]"));
        	medication0.sendKeys("c");
        	TimeUnit.SECONDS.sleep(2);
        	
        	WebElement doseSchedule0 = driver.findElement(By.xpath("//*[@id=\"doseSchedule0\"]"));
        	doseSchedule0.sendKeys("a");
        	TimeUnit.SECONDS.sleep(2);
        	
        	WebElement reason0 = driver.findElement(By.xpath("//*[@id=\"reason0\"]"));
        	reason0.sendKeys("6");
        	TimeUnit.SECONDS.sleep(2);
        	WebElement date1 = driver.findElement(By.xpath("//*[@id=\"startDate0\"]/div/div/input"));
        	date1.sendKeys(formattedDate);
        	TimeUnit.SECONDS.sleep(3);
        	
        	WebElement diagnosisInput = wait.until(ExpectedConditions.elementToBeClickable(
        	        By.cssSelector("#Diagnosis0 input")
        	));
        	diagnosisInput.clear();
        	diagnosisInput.sendKeys("a");

        	// small wait for suggestions to appear
        	Thread.sleep(500); 

        	diagnosisInput.sendKeys(Keys.ARROW_DOWN); // move to first suggestion
        	diagnosisInput.sendKeys(Keys.ENTER); 
        	TimeUnit.SECONDS.sleep(5);
        	
        	WebElement Onset0 = driver.findElement(By.xpath("//*[@id=\"Onset0\"]"));
        	Onset0.sendKeys("a");
        	TimeUnit.SECONDS.sleep(5);
        	WebElement History0 = driver.findElement(By.xpath("//*[@id=\"History0\"]"));
        	History0.sendKeys("a");
        	TimeUnit.SECONDS.sleep(5);
        	WebElement Prognosis0 = driver.findElement(By.xpath("//*[@id=\"Prognosis0\"]"));
        	Prognosis0.sendKeys("a");
        	TimeUnit.SECONDS.sleep(5);
        	
        	WebElement tardiveRadio = wait.until(ExpectedConditions.elementToBeClickable(
        		    By.id("dependency")  // or use By.cssSelector(".section1_tardiveDyskenesia")
        		));

        		// Scroll into view just in case
        		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tardiveRadio);

        		// Click using JS to avoid interception
        		((JavascriptExecutor) driver).executeScript("arguments[0].click();", tardiveRadio);
        		
        	WebElement date2 = driver.findElement(By.xpath("//*[@id=\"section1\"]/div[7]/div[3]/my-date-picker/div/div[1]/input"));
        	date2.sendKeys(formattedDate);
        	TimeUnit.SECONDS.sleep(3);
        	
        	WebElement medicationHistory = driver.findElement(By.xpath("//*[@id=\"medicationHistory\"]"));
        	medicationHistory.sendKeys("test");
        	TimeUnit.SECONDS.sleep(5);
        	
        	
        	WebElement element = driver.findElement(By.xpath("//*[@id='main_content']/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[2]/a"));
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	js.executeScript("arguments[0].click();", element);
    	//click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[2]/a"));
    	TimeUnit.SECONDS.sleep(5);
    	//click(By.xpath("//*[@id=\"radiation1\"]"));
    	WebElement radiation1 = driver.findElement(By.id("radiation1"));
    	if (!radiation1.isSelected() && radiation1.isEnabled()) {
    	    // Click only if not selected
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radiation1);
    	}
    	
    	WebElement notes = driver.findElement(By.xpath("//*[@id=\"notes\"]"));
    	notes.sendKeys("test");
    	TimeUnit.SECONDS.sleep(3);
    
    	//click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[3]/a"));
    	WebElement element3 = driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[3]/a"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", element3);
    	//click(By.xpath("//*[@id=\"cardiovascular\"]"));
    	
    	WebElement gastrointenstinal1 = wait.until(ExpectedConditions.presenceOfElementLocated(
    		    By.id("gastrointenstinal1")
    		));

    		// Only click if not already selected
    		if (!gastrointenstinal1.isSelected()) {
    		    // Scroll into view (optional but helps)
    		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", gastrointenstinal1);
    		    
    		    // Click using JS to bypass overlay
    		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gastrointenstinal1);
    		}
    	//TimeUnit.SECONDS.sleep(5);
    	WebElement textArea2 = driver.findElement(By.xpath("//*[@id=\"gastrointenstinal\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	textArea2.sendKeys("This is my input text2");
    	TimeUnit.SECONDS.sleep(5);
    	WebElement abnormalLabValues = driver.findElement(By.xpath("//*[@id=\"abnormalLabValues\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	abnormalLabValues.sendKeys("This is my input comments");
    	TimeUnit.SECONDS.sleep(5);
    	
    	
    	//click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[4]/a"));
    	WebElement section4 = wait.until(ExpectedConditions.visibilityOfElementLocated(
    		    By.xpath("//*[@id='main_content']/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[4]/a")
    		));

    		// Scroll into view first
    		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section4);

    		// Use JavaScript click to bypass interception
    		((JavascriptExecutor) driver).executeScript("arguments[0].click();", section4);
    	TimeUnit.SECONDS.sleep(5);
    	WebElement textArea3 = driver.findElement(By.xpath("//*[@id=\"surgeriesDiagnosticProc\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	textArea3.sendKeys("This is my input text3");
    	
    	WebElement equipmentNotes = driver.findElement(By.xpath("//*[@id=\"equipmentNotes\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	equipmentNotes.sendKeys("This is my input text3");
    	TimeUnit.SECONDS.sleep(3);
    	//click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[5]/a"));
    	WebElement section5 = wait.until(ExpectedConditions.visibilityOfElementLocated(
    		    By.xpath("//*[@id='main_content']/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[5]/a")
    		));

    		// Scroll into view
    		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section5);

    		// JS click
    		((JavascriptExecutor) driver).executeScript("arguments[0].click();", section5);
    	WebElement textArea4 = driver.findElement(By.xpath("//*[@id=\"bedMobility\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea4.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement transfer = driver.findElement(By.xpath("//*[@id=\"transfer\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	transfer.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement locomotion = driver.findElement(By.xpath("//*[@id=\"locomotion\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	locomotion.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement dressing = driver.findElement(By.xpath("//*[@id=\"dressing\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	dressing.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement eating = driver.findElement(By.xpath("//*[@id=\"eating\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	eating.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement toiletUse = driver.findElement(By.xpath("//*[@id=\"toiletUse\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	toiletUse.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement personalHygiene = driver.findElement(By.xpath("//*[@id=\"personalHygiene\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	personalHygiene.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement bathing = driver.findElement(By.xpath("//*[@id=\"bathing\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	bathing.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	
    	WebElement administerOwnMeds = driver.findElement(By.xpath("//*[@id=\"administerOwnMeds\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	administerOwnMeds.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement manageFinAffairs = driver.findElement(By.xpath("//*[@id=\"manageFinAffairs\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	manageFinAffairs.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement manageDomesticResp = driver.findElement(By.xpath("//*[@id=\"manageDomesticResp\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	manageDomesticResp.sendKeys("3");
    	
    	TimeUnit.SECONDS.sleep(3);
    	WebElement monitorNutrStatus = driver.findElement(By.xpath("//*[@id=\"monitorNutrStatus\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	monitorNutrStatus.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	WebElement monitorHealthStatus = driver.findElement(By.xpath("//*[@id=\"monitorHealthStatus\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	monitorHealthStatus.sendKeys("3");
    	TimeUnit.SECONDS.sleep(3);
    	
    	//click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[6]/a"));
    	WebElement section6 = wait.until(ExpectedConditions.visibilityOfElementLocated(
    		    By.xpath("//*[@id='main_content']/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[6]/a")
    		));

    		// Scroll into view
    		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section6);

    		// JS click to avoid interception
    		((JavascriptExecutor) driver).executeScript("arguments[0].click();", section6);
    	TimeUnit.SECONDS.sleep(5);
    	
    	WebElement textArea5 = driver.findElement(By.xpath("//*[@id=\"recommendations\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	textArea5.sendKeys("test");
    	TimeUnit.SECONDS.sleep(5);
    	
    	
    	WebElement exceedCapacity1 = wait.until(ExpectedConditions.elementToBeClickable(
    			By.xpath("//*[@id=\"exceedCapacity1\"]")
        	));
    	
    	exceedCapacity1.click(); // only click if not already checked
    		
    		WebElement exceedCapacityExplain = driver.findElement(By.xpath("//*[@id=\"exceedCapacityExplain\"]"));
    		TimeUnit.SECONDS.sleep(5);
    		exceedCapacityExplain.sendKeys("test");
        	TimeUnit.SECONDS.sleep(5);
        	
        	click(By.xpath("//*[@id=\"notSeen\"]"));
        	
    	click(By.xpath("//*[@id=\"submit\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/app-assessment-btn/div/div[2]/div/div/span/button"));
    	TimeUnit.SECONDS.sleep(8);
   	   List<String> tabs1 = new ArrayList<>(driver.getWindowHandles());
     	 // Switch to 2nd tab (index 1)
   	  driver.switchTo().window(tabs1.get(tabs1.size() - 1));
   	File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  	Files.copy(screenshot1.toPath(), Paths.get("medicalPending.png"));
  	TimeUnit.SECONDS.sleep(8);
  	driver.switchTo().window(readOnlyTab);
    	//Psychological
    	
    	for (WebElement row : rows) {
    	    try {
    	        // Column 1: form name (asmtForm)
    	        WebElement formCell = row.findElement(By.xpath("./td[@headers='asmtForm']"));
    	        String formName = formCell.getText().trim();

    	        // Column 8: status
    	        WebElement statusCell = row.findElement(By.xpath("./td[@headers='status']"));
    	        String statusText = statusCell.getAttribute("title"); // or statusCell.getText()

    	        // Check if both match
    	        if (formName.equalsIgnoreCase("Psychosocial") && statusText.equalsIgnoreCase("Assigned (Pending)")) {
    	            // Click the form link (inside column 1)
    	            WebElement link = formCell.findElement(By.xpath(".//a"));
    	            link.click();
    	            System.out.println("Clicked the first matching Psychosocial record successfully.");
    	            break; // stop after first match
    	        }
    	    } catch (NoSuchElementException e) {
    	        // Skip if any cell not found
    	        continue;
    	    }
    	}
    	
    	TimeUnit.SECONDS.sleep(5);
    	WebElement textArea6 = driver.findElement(By.xpath("//*[@id=\"presentingProblems\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	textArea6.sendKeys("test");
    	TimeUnit.SECONDS.sleep(5);
    	
    	click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[2]/a"));
    	
    	
    	
    	WebElement textArea7 = driver.findElement(By.xpath("//*[@id=\"cmhFundedType\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	textArea7.sendKeys("test");
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[3]/a"));
    	TimeUnit.SECONDS.sleep(5);
    	
    	WebElement textArea8 = driver.findElement(By.xpath("//*[@id=\"strengthsResources\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	textArea8.sendKeys("test");	
    	
    	TimeUnit.SECONDS.sleep(5);
    	
    	click(By.xpath("//*[@id=\"submit\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	
    	click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[2]/app-assessment-btn/div/div[2]/div/div/span/button"));
    	TimeUnit.SECONDS.sleep(5);
    	
    	//gOTO penDING QUEUE AGAIN
    	
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main\"]/li[9]/a"));
    	
    	TimeUnit.SECONDS.sleep(5);
    	
    	// Locate the table
    	WebElement table1 = driver.findElement(By.id("gridTable"));

    	// Click the first row
    	WebElement firstRow1 = table1.findElement(By.xpath(".//tbody/tr[1]"));
    	//firstRow.click();
    	
    	// Get fresh cells
    	List<WebElement> cells1 = firstRow1.findElements(By.tagName("td"));
    	firstRow.click();
    	
    	TimeUnit.SECONDS.sleep(3);
    	
    	click(By.xpath("//*[@id=\"levelIIForm row\"]/div/table/tbody/tr[1]/td[1]/strong/a"));
    	
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[3]/a"));
    	
    	click(By.xpath("//*[@id=\"submit\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"approve\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	
    	click(By.xpath("//*[@id=\"levelIIForm row\"]/div/table/tbody/tr[2]/td[1]/strong/a"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main_content\"]/app-medical/div[2]/section/form/div[2]/div/div[1]/ul/li[6]/a"));
    	
    	click(By.xpath("//*[@id=\"submit\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"approve\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main_content\"]/app-create-evaluation/div/section/div[2]/form[2]/div/div[5]/button[2]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"submit\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"next\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"checkthisbox\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"add_user_save_btn\"]"));
    	
    	
    	
    	
    	
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    	
    }
    
}
