package gov.michigan.obra.automation.common;

import gov.michigan.obra.automation.page.*;
import org.openqa.selenium.WebDriver;


public class PageProvider extends BasePage {
    public PageProvider(WebDriver driver) {
        super(driver);
    }

    public UserRegistartion getUserRegistration(){

        return new UserRegistartion(driver);
    }

    public MILogin getMILogin(){

        return new MILogin(driver);
    }

    public OBRAPage getOBRAPage(){
        return new OBRAPage(driver);
    }

    public OBRALogOutPage getOBRALogOutPage(){
        return new OBRALogOutPage(driver);
    }

    public UserApprovalPage getUserApprovalPage(){
        return new UserApprovalPage(driver);
    }

    public MILogOutPage getMILogOutPage(){
        return new MILogOutPage(driver);
    }
    
    public CMHLevel1WorkflowPage getCMHLevel1WorkflowPage(){
        return new CMHLevel1WorkflowPage(driver);
    }

    public Consumer getConsumer(){
        return new Consumer(driver);
    }

    public LevelWorkflow getLevelWorkflowPage(){
        return new LevelWorkflow(driver);
    }
    
    public CompleteAssesmentPage getCompleteAssesmentPage(){
        return new CompleteAssesmentPage(driver);
    }
    
    public CmhObraReviewerFlowPage getCmhObraReviewerFlowPage(){
        return new CmhObraReviewerFlowPage(driver);
    }
    
    public CMHReqSubmiteToOBRAReviewer getCMHReqSubmiteToOBRAReviewerPage(){
        return new CMHReqSubmiteToOBRAReviewer(driver);
    }
    public OBRAReviewerDeterminationOnLevelII getOBRAReviewerDeterminationOnLevelII(){
        return new OBRAReviewerDeterminationOnLevelII(driver);
    }
    public OnDemandPrintForm getOnDemandPrintFormPage(){
        return new OnDemandPrintForm(driver);
    }   
    
    public OnDemandPrintAssesmentForm getOnDemandPrintAssesmentForm(){
        return new OnDemandPrintAssesmentForm(driver);
    }  
    public OnDemandPrintDeterminationLetter getOnDemandPrintDeterminationLetter(){
        return new OnDemandPrintDeterminationLetter(driver);
    }   
    
}

