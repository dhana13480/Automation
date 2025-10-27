package gov.michigan.obra.automation.page;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OBRAReviewerDeterminationOnLevelII extends BasePage {
	
    public OBRAReviewerDeterminationOnLevelII(WebDriver driver) {
		
    	
    	super(driver);
	}

    
    public String pendingQueDeterminationFlow() throws InterruptedException, IOException {
    	try {
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"main\"]/li[9]/a"));    	
    	TimeUnit.SECONDS.sleep(2);    	
    	/*click(By.xpath("//*[@id=\"createdOn\"]/a"));
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"createdOn\"]/a"));
    	TimeUnit.SECONDS.sleep(2);*/
    	
    	//find the element which is sent OBRA
    	
    	// Get all <tr> rows inside the tbody
    	List<WebElement> rows = driver.findElements(By.xpath("//*[@id='gridTable']/tbody/tr"));    	
        String lastName ="Vickers";
    	/*for (WebElement row : rows) {
    	    // Now find the 3rd column (relative to this row)
    	    WebElement cell = row.findElement(By.xpath("./td[4]"));
    	    String value = cell.getText().trim();
    	    WebElement cell2 = row.findElement(By.xpath("./td[1]"));
    	    String value2 = cell2.getText().trim();

    	    if (value.equalsIgnoreCase(lastName)) {
    	        System.out.println("Found row with last name: " + value);

    	        WebElement link = cell2.findElement(By.tagName("a"));
    	        link.click();

    	        break; // stop after finding the first match
    	    }
    	}*/
     // Locate the element
        WebElement element = driver.findElement(By.xpath("//*[@id='gridTable']/tbody/tr[1]/td[1]/span"));

        // Get the visible text
        String nameText = element.getText();
        System.out.println("nameText" +nameText);
        // Print or use it
        System.out.println("Cell text: " + nameText);
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[2]/span"));

        // Get the visible text
        String firstnameText = element2.getText();
        System.out.println("nameText" +firstnameText);
     
        
        
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]"));
    	TimeUnit.SECONDS.sleep(3);

    	
    	click(By.xpath("//*[@id=\"main_content\"]/app-pending-evaluation/div/section/div[4]/a"));
    	TimeUnit.SECONDS.sleep(5);
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    	
    	
    	TimeUnit.SECONDS.sleep(5);
    	

    	
    	WebElement dropdown = driver.findElement(By.id("determinationsId"));
    	JavascriptExecutor jsq = (JavascriptExecutor) driver;
    	jsq.executeScript(
    	    "arguments[0].value='2'; arguments[0].dispatchEvent(new Event('change'));", 
    	    dropdown
    	);
    	
    	
    	WebElement dropdown1 = driver.findElement(By.id("diagnosesCategorysId"));
    	JavascriptExecutor js1 = (JavascriptExecutor) driver;
    	js1.executeScript(
    	    "arguments[0].value='3'; arguments[0].dispatchEvent(new Event('change'));", 
    	    dropdown1
    	);
    	TimeUnit.SECONDS.sleep(2);
    	
    	click(By.xpath("//*[@id=\"thirtyMomthRule\"]"));
    	WebElement textArea1 = driver.findElement(By.xpath("//*[@id=\"thirtyMonthRuleNotes\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea1.sendKeys("test");
    	
    	click(By.xpath("//*[@id=\"transferTrauma\"]"));
    	WebElement textArea2 = driver.findElement(By.xpath("//*[@id=\"transferTraumaNotes\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea2.sendKeys("test1");   	
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"digitalSignature\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"updateReEvaluationDate\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	
    	JavascriptExecutor jsUp = (JavascriptExecutor) driver;

    	// Scroll to top
    	jsUp.executeScript("window.scrollTo(0, 0)");
    	TimeUnit.SECONDS.sleep(2);
  
    	
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[2]"));
    	TimeUnit.SECONDS.sleep(2);
    	
    	click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[2]/div[2]/a/h2"));
    	TimeUnit.SECONDS.sleep(2);
    	
    	//click(By.xpath("//*[@id=\"ssn_no\"]"));
    	
    	WebElement textArea3 = driver.findElement(By.xpath("//*[@id=\"eval_lastname\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea3.sendKeys(nameText);
    	TimeUnit.SECONDS.sleep(2);
    	
    	WebElement textArea4 = driver.findElement(By.xpath("//*[@id=\"eval_firstname\"]"));
    	TimeUnit.SECONDS.sleep(2);
    	textArea4.sendKeys(firstnameText);
    	TimeUnit.SECONDS.sleep(2);
    	
    	click(By.xpath("//*[@id=\"main_content\"]/app-evaluation/app-evaluation-search/div/section/form/div[4]/div[5]/div/button[1]"));
    	
    	TimeUnit.SECONDS.sleep(2);
    	
    	WebElement status = driver.findElement(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[7]"));
    	
    	String statusText = status.getText();
    	TimeUnit.SECONDS.sleep(2);
    	
    	click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[4]"));
    	TimeUnit.SECONDS.sleep(2);
    	
    	click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/button[2]"));
    	
    	
    	click(By.xpath("//*[@id=\"main_content\"]/app-evaluation-detail/div/section/div[2]/app-breadcrumbs/div[1]/ul/li[7]/a"));
    	WebElement statusC = driver.findElement(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[7]"));
    	
    	String statusCText = statusC.getText();
    	
    	return statusCText;
    	}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

}
