package gov.michigan.obra.automation.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class CmhObraReviewerFlowPage extends BasePage{
    public CmhObraReviewerFlowPage(WebDriver driver) {
        super(driver);
    }

    public void submitToObraStaff() throws InterruptedException, IOException {
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"main\"]/li[8]/a"));
    	
    	TimeUnit.SECONDS.sleep(2);
    	
    	click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]")); 	
    	
    	
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"levelIIForm row\"]/div/table/tbody/tr[1]/td[1]/strong/a"));
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[3]/a"));
    	TimeUnit.SECONDS.sleep(2);
    	click(By.xpath("//*[@id=\"submit\"]"));

    	TimeUnit.SECONDS.sleep(3);

   	
    	
        click(By.xpath("//*[@id=\"approve\"]"));
        
        
        
        //Sensorimotor Development 
        
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"levelIIForm row\"]/div/table/tbody/tr[5]/td[1]/strong/a"));
	
    	
    	TimeUnit.SECONDS.sleep(5);	

    	click(By.xpath("//*[@id=\"submit\"]"));
    	TimeUnit.SECONDS.sleep(5);
    	
    	
        click(By.xpath("//*[@id=\"approve\"]"));
        TimeUnit.SECONDS.sleep(5);
        
        
    	
    }
    
   
}
