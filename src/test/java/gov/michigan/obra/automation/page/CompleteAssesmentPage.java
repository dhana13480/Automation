package gov.michigan.obra.automation.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class CompleteAssesmentPage extends BasePage{
    public CompleteAssesmentPage(WebDriver driver) {
        super(driver);
    }

    public String completeAssesment() throws InterruptedException, IOException {
    	TimeUnit.SECONDS.sleep(5);
    	click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a"));
    	TimeUnit.SECONDS.sleep(5);	
        click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[2]/div[3]/a"));
        TimeUnit.SECONDS.sleep(3);        
        selectDropDownByVisibleText(By.id("sortFilters"),"Submitted Date");
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"sortOrder\"]"));
        TimeUnit.SECONDS.sleep(3);
      //  click(By.xpath("//*[@id=\"sortOrder\"]"));
      //  TimeUnit.SECONDS.sleep(2);
        
        String windHandleCurrent = driver.getWindowHandle();
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]/span/a"));
        TimeUnit.SECONDS.sleep(2);
                			   	
       // Boolean isSensorimotor = isElementPresent(By.xpath("//*[@id=\"main_content\"]/app-sensorimotor-form/div/section/div[1]/div[2]/h1"));
       // Boolean isPsychosocial = isElementPresent(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[1]/div[2]/h2"));
        if(isElementPresent(By.xpath("//*[@id=\"main_content\"]/app-sensorimotor-form/div/section/div[1]/div[2]/h1"))) {
        	
        	inputById("ambulationSkills","test1");
            inputById("fineMotor","test2");
            inputById("grossMotor","test3");
            inputById("positioningNeeds","test4");
            inputById("supportiveDevices","test5");
            inputById("vocationalDevelopment","test6");
            inputById("selfhelpDevelopment","test7");
            inputById("socialDevelopment","test");
            inputById("recommendation","test2");
            inputById("fineMotor","test2");
            TimeUnit.SECONDS.sleep(2);
            click(By.xpath("//*[@id=\"submit\"]"));

            TimeUnit.SECONDS.sleep(3);
            click(By.xpath("//*[@id=\"main_content\"]/app-sensorimotor-form/div/section/form/app-assessment-btn/div/div[2]/div/div/button[2]"));
            TimeUnit.SECONDS.sleep(13);
            ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
            for(int i =0;i<windows.size();i++ ) {
                String aWindow = windows.get(i);
                if(aWindow != windHandleCurrent) {
                    driver.switchTo().window(aWindow);
                }
            }
        }else if(isElementPresent(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[1]/div[2]/h2"))) {
        	TimeUnit.SECONDS.sleep(3);
        	fillPsychologicalAssessment();
        	
        }
        TimeUnit.SECONDS.sleep(3);
        driver.switchTo().window(windHandleCurrent);
        selectDropDownByVisibleText(By.id("sortFilters"),"Submitted Date");
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"sortOrder\"]"));
        TimeUnit.SECONDS.sleep(3);
        click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]/span/a"));
       // Boolean sensorimotor = isElementPresent(By.xpath("//*[@id=\"main_content\"]/app-sensorimotor-form/div/section/div[1]/div[2]/h1"));
       // Boolean ssychosocial = isElementPresent(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[1]/div[2]/h2"));
        if(isElementPresent(By.xpath("//*[@id=\"main_content\"]/app-sensorimotor-form/div/section/div[1]/div[2]/h1"))) {
        	
        	inputById("ambulationSkills","test1");
            inputById("fineMotor","test2");
            inputById("grossMotor","test3");
            inputById("positioningNeeds","test4");
            inputById("supportiveDevices","test5");
            inputById("vocationalDevelopment","test6");
            inputById("selfhelpDevelopment","test7");
            inputById("socialDevelopment","test");
            inputById("recommendation","test2");
            inputById("fineMotor","test2");
            TimeUnit.SECONDS.sleep(2);
            click(By.xpath("//*[@id=\"submit\"]"));

            TimeUnit.SECONDS.sleep(3);
            click(By.xpath("//*[@id=\"main_content\"]/app-sensorimotor-form/div/section/form/app-assessment-btn/div/div[2]/div/div/button[2]"));
            TimeUnit.SECONDS.sleep(13);
            ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
            for(int i =0;i<windows.size();i++ ) {
                String aWindow = windows.get(i);
                if(aWindow != windHandleCurrent) {
                    driver.switchTo().window(aWindow);
                }
            }
        }else if(isElementPresent(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[1]/div[2]/h2"))) {
        	fillPsychologicalAssessment();
        	
        }
        return null;
    }
    
    private void fillPsychologicalAssessment() throws InterruptedException, IOException {

        String windHandleCurrent = driver.getWindowHandle();
        click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[1]/a"));
       // click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]/span/a"));
        TimeUnit.SECONDS.sleep(2);
        inputById("presentingProblems","test1");
        inputById("subjectiveEvaluation","test2");
        click(By.id("legalRepContacted1"));
        
        inputById("objectiveEvalExplain","test3");
        inputById("objectiveEval","test4");
        inputById("presentingProblemHistory","test5");
        inputById("recentMhServices","test6");
        inputById("psychosocialHistory","test7");
        inputById("currentLiving","test");
        click(By.id("returnCurrResidence1"));
        
        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[2]/app-assessment-btn/div/div[1]/div/button[3]/i"));

        TimeUnit.SECONDS.sleep(5);
       // click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[2]/a"));
        WebElement firstbutton= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[2]/a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(firstbutton);
        actions.perform();
        TimeUnit.SECONDS.sleep(3);
        click(By.id("cmhFunded1"));
        inputById("cmhFundedType","test");
        inputById("priorLiving","test3");
        click(By.id("suicidal1"));
        click(By.id("homicidal1"));
        inputById("suicidalHomicidalExplain","test");
        inputById("homicidalExplain","test3");
        inputById("behaviour0","test4");
        inputById("frequency0","test5");
        inputById("intensity0","test6");
        inputById("intervention0","test7");
        inputById("outcome0","test8");
        inputById("substanceAbuse","test9");
        inputById("affectiveDevelopment","test10");
        inputById("cognitiveAbilities","test11");
        inputById("outcome0","test8");
        TimeUnit.SECONDS.sleep(2);
        click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[2]/app-assessment-btn/div/div[1]/div/button[3]"));
        TimeUnit.SECONDS.sleep(3);
       // click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[3]/a"));
        WebElement second= driver.findElement(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[1]/ul/li[3]/a"));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(second);
        actions1.perform();
        TimeUnit.SECONDS.sleep(3);
        inputById("strengthsResources","test");
        
        click(By.id("understandsNo2"));
        click(By.id("simpleDirections2"));
        click(By.id("yesNoResponse2"));
        click(By.id("immediateNeeds2"));
        click(By.id("simpleQuestions2"));
        click(By.id("personalExp2"));
        inputById("receptiveLanguage","test8");
        inputById("expressiveLanguage","test8");
        inputById("nonOralComm","test8");
        inputById("specialComm","test8");
        inputById("recommendations","test8");
        click(By.id("exceedNfCapacity1"));
        inputById("exceedNfExplain","test8");
        inputById("psychiatricExplanation","test10");
        click(By.id("psychiatricHospitalization1"));        
        click(By.id("submit"));
        TimeUnit.SECONDS.sleep(2);
        
        click(By.xpath("//*[@id=\"main_content\"]/app-psychosocial-form/div/section/form/div[2]/div/div[2]/app-assessment-btn/div/div[2]/div/div/span/button"));
        TimeUnit.SECONDS.sleep(5);
        ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
        for(int i =0;i<windows.size();i++ ) {
            String aWindow = windows.get(i);
            if(aWindow != windHandleCurrent) {
                driver.switchTo().window(aWindow);
            }
        }
        
    	
    }
    
   
}
