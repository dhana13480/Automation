package gov.michigan.obra.automation.page;

import gov.michigan.obra.automation.page.testdata.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static gov.michigan.obra.automation.common.constant.ApplicationConstants.*;

public class Consumer extends BasePage {
	public Consumer(WebDriver driver) {
		super(driver);
	}

	public String createConsumer(String userId, String password, UserData userData) throws InterruptedException {
		/*
		 * inputUserId(userId); TimeUnit.SECONDS.sleep(3); inputUserPassword(password);
		 * TimeUnit.SECONDS.sleep(3); clickMILoginButton(); TimeUnit.SECONDS.sleep(3);
		 * 
		 * if(driver.findElement(By.id("acessServiceLink81")).isDisplayed()){
		 * clickOnTPMDHHS(); } TimeUnit.SECONDS.sleep(5);
		 * 
		 * clickOnTPTermsCheckBox();
		 * 
		 * TimeUnit.SECONDS.sleep(5);
		 * System.out.println("launchService button displayed "+driver.findElement(By.id
		 * ("launchServiceBtn")).isDisplayed()); TimeUnit.SECONDS.sleep(5); // Store the
		 * current window handle String winHandleMiLogin = driver.getWindowHandle();
		 * 
		 * // Perform the click operation that opens new window
		 * TimeUnit.SECONDS.sleep(5); clickOnLaunchService(); TimeUnit.SECONDS.sleep(5);
		 * 
		 * // Switch to new window opened for(String winHandleObra :
		 * driver.getWindowHandles()){ driver.switchTo().window(winHandleObra); }
		 */
		TimeUnit.SECONDS.sleep(5);
		clickOnOBRAMDHHS();
		TimeUnit.SECONDS.sleep(5);
		clickOnConsumerModule();
		TimeUnit.SECONDS.sleep(5);
		clickOnCreateNewConsumer();
		String SSN = addConsumer(userData);
		createLegalRep(userData);
		return SSN;

	}

	public String createConsumerWithoutLegalRep(String userId, String password, UserData userData)
			throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
		clickOnOBRAMDHHS();
		TimeUnit.SECONDS.sleep(5);
		clickOnConsumerModule();
		TimeUnit.SECONDS.sleep(5);
		clickOnCreateNewConsumer();
		String SSN = addConsumer(userData);
		return SSN;

	}

	public void createConsumer(String userId, String password) throws InterruptedException {
		inputUserId(userId);
		TimeUnit.SECONDS.sleep(3);
		inputUserPassword(password);
		TimeUnit.SECONDS.sleep(3);
		clickMILoginButton();
		TimeUnit.SECONDS.sleep(3);

		if (driver.findElement(By.id("acessServiceLink81")).isDisplayed()) {
			clickOnTPMDHHS();
		}
		TimeUnit.SECONDS.sleep(5);

		clickOnTPTermsCheckBox();

		TimeUnit.SECONDS.sleep(5);
		System.out.println(
				"launchService button displayed " + driver.findElement(By.id("launchServiceBtn")).isDisplayed());
		TimeUnit.SECONDS.sleep(5);
		// Store the current window handle
		String winHandleMiLogin = driver.getWindowHandle();

		// Perform the click operation that opens new window
		TimeUnit.SECONDS.sleep(5);
		clickOnLaunchService();
		TimeUnit.SECONDS.sleep(5);

		// Switch to new window opened
		for (String winHandleObra : driver.getWindowHandles()) {
			driver.switchTo().window(winHandleObra);
		}
	}

	private void createLegalRep(UserData userData) throws InterruptedException {
		click(By.xpath(
				"//*[@id=\"main_content\"]/app-consumer-detail/section/div[3]/div/div[2]/div/div[1]/h2/a/span[1]"));
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"add_companyName\"]"), userData.getDepartment());
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"addressLine1\"]"), userData.getPermAddress());
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"city\"]"), userData.getCity());
		TimeUnit.SECONDS.sleep(2);

		selectDropDownByVisibleText(By.xpath("//*[@id=\"stateId\"]"), "Michigan(MI)");
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"zipCode1\"]"), "48805");
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"add_Phone_no\"]"), userData.getPhoneNumber());
		TimeUnit.SECONDS.sleep(2);

		selectDropDownByVisibleText(By.xpath("//*[@id=\"representativeTypeId\"]"), "Activated DPOA");
		TimeUnit.SECONDS.sleep(2);

		selectDropDownByVisibleText(By.xpath("//*[@id=\"relationshipId\"]"), "Attorney");
		TimeUnit.SECONDS.sleep(3);

		// Provide the full path(s) of the file(s) to upload
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			String projectDir = System.getProperty("user.dir");
			String filePath = projectDir + "/src/test/resources/uploads/sample.pdf";
			WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("upload")));
			fileInput.sendKeys(filePath);
			System.out.println("fileInput: " + fileInput);
			saveButton(By.xpath(
					"/html/body/app-root/div[2]/div/div/app-add-edit-legal-rep/div/section/form/div[9]/button[3]"));
			TimeUnit.SECONDS.sleep(2);

		} catch (Exception e) {
			System.out.println("Connection reset during upload. Retrying...");
			e.printStackTrace();
			Thread.sleep(2000);
			// retry logic here
		}
	}

	private String addConsumer(UserData userData) throws InterruptedException {
		String SSN = userData.getSsn();
		sendKeys(By.xpath("//*[@id=\"ssn\"]"), SSN);
		TimeUnit.SECONDS.sleep(5);
		sendKeys(By.xpath("//*[@id=\"add_firstname\"]"), userData.getFirstName());
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"add_lastname\"]"), userData.getLastName());
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath(
				"//*[@id=\"main_content\"]/app-add-edit-consumer/div/section/form/div[3]/div[1]/div/my-date-picker/div/div/input"),
				"02/29/1980");// "02/29/1980");
		TimeUnit.SECONDS.sleep(2);

		selectDropDownByValue(By.xpath("//*[@id=\"gender\"]"), "M");
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"addressLine1\"]"), userData.getPermAddress());
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"city\"]"), userData.getCity());
		TimeUnit.SECONDS.sleep(2);

		selectDropDownByVisibleText(By.xpath("//*[@id=\"countyId\"]"), "OAKLAND");
		TimeUnit.SECONDS.sleep(2);

		selectDropDownByVisibleText(By.xpath("//*[@id=\"stateId\"]"), "Michigan(MI)");
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"zipCode1\"]"), "48805");
		TimeUnit.SECONDS.sleep(2);

		saveButton(By.xpath("//*[@id=\"main_content\"]/app-add-edit-consumer/div/section/form/div[9]/button[1]"));
		TimeUnit.SECONDS.sleep(2);
		WebElement gender = driver.findElement(By
				.xpath("//*[@id=\"main_content\"]/app-consumer-detail/section/div[3]/div/div[1]/div/div[2]/dl/dd[1]"));

		return SSN;
		// Assertions.assertEquals(gender.getText(),"M");

	}

	/*
	 * public void editConsumer() throws InterruptedException {
	 * TimeUnit.SECONDS.sleep(5); clickOnOBRAMDHHS(); TimeUnit.SECONDS.sleep(5); }
	 */

	public void initiateLevelIForPAS3878(UserData userData, String ssn) {

		try {
			submitForm3877(ssn, By.xpath("//*[@id=\"scr_crt_no_1\"]"), By.xpath("//*[@id=\"scr_crt_no_2\"]"),
					By.xpath("//*[@id=\"scr_crt_yes_3\"]"), By.xpath("//*[@id=\"scr_crt_no_4\"]"),
					By.xpath("//*[@id=\"scr_crt_no_5\"]"), By.xpath("//*[@id=\"scr_crt_no_6\"]"),
					By.xpath("//*[@id=\"scr_crt_yes_7\"]"));

			TimeUnit.SECONDS.sleep(2);
			// clcik on 3878 queue
			click(By.xpath("//*[@id=\"main\"]/li[12]/a"));
			TimeUnit.SECONDS.sleep(2);

			// Click on 3878 status Assigned
			click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr/td[2]/span/a"));
			TimeUnit.SECONDS.sleep(2);

			// Click on section 2
			click(By.xpath("//*[@id=\"main_content\"]/app-form-3878/div/section/div[2]/div[1]/ul/li[2]/a"));
			TimeUnit.SECONDS.sleep(2);

			// click on exemption criteria as coma -radio button
			click(By.xpath("//*[@id=\"exemptionType\"]"));
			TimeUnit.SECONDS.sleep(2);

			// click on check box
			checkAcknowledgeButton(By.id("submit")); //// *[@id="submit"]
			TimeUnit.SECONDS.sleep(2);

			// click on coma as YES
			click(By.xpath("//*[@id=\"comaCriteria_yes\"]"));
			TimeUnit.SECONDS.sleep(2);

			scrollDown();

			// submit button
			saveButton(By.xpath("//*[@id=\"section2\"]/div/form/div[2]/div/div[7]/button[7]"));
			TimeUnit.SECONDS.sleep(2);

			// Before the action that redirect to the new tab:
			String windHandleCurrent = driver.getWindowHandle();
			TimeUnit.SECONDS.sleep(5);
			// code that click in a btn/link in order to open a new tab goes here
			// now to make selenium move to the new tab
			ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
			for (int i = 0; i < windows.size(); i++) {
				String aWindow = windows.get(i);
				if (aWindow != windHandleCurrent) {
					driver.switchTo().window(aWindow);
				}
			}
			System.out.println("url  :  " + driver.getCurrentUrl());

			TimeUnit.SECONDS.sleep(3);
			// verifyPDFContent(driver.getCurrentUrl(),
			// ApplicationConstants.PLNDSC_PDF_TEXT,"level_PLNDSC.pdf");

			// switch to the first tab
			driver.switchTo().window(windHandleCurrent);
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void initiateLevelIForPAS(UserData userData, String ssn) {
		try {
			submitForm3877(ssn, By.xpath("//*[@id=\"scr_crt_no_1\"]"), By.xpath("//*[@id=\"scr_crt_no_2\"]"),
					By.xpath("//*[@id=\"scr_crt_no_3\"]"), By.xpath("//*[@id=\"scr_crt_no_4\"]"),
					By.xpath("//*[@id=\"scr_crt_no_5\"]"), By.xpath("//*[@id=\"scr_crt_no_6\"]"),
					By.xpath("//*[@id=\"scr_crt_no_7\"]"));

			sendKeys(By.xpath("//*[@id=\"ssn\"]"), ssn);
			TimeUnit.SECONDS.sleep(2);

			// search
			click(By.xpath("//*[@id=\"filter-row\"]/div[3]/div/button[1]"));
			TimeUnit.SECONDS.sleep(2);

			// Sorting on descending order
			click(By.id("sortOrder"));
			TimeUnit.SECONDS.sleep(2);

			// Click On Screening type PAS link
			click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]/span/a")); //// *[@id="gridTable"]/tbody/tr/td[1]/span/a
			TimeUnit.SECONDS.sleep(2);
			scrollDown();
			// Facility Assignment
			Actions action = new Actions(driver);
			TimeUnit.SECONDS.sleep(2);
			WebElement facilityDropDown = driver.findElement(By.xpath(
					"//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[4]/div/div/div[2]/div/angular2-multiselect/div/div[1]/div"));
			action.moveToElement(facilityDropDown).click().build().perform();

			TimeUnit.SECONDS.sleep(2);
			sendKeys(By.xpath(
					"//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[4]/div/div/div[2]/div/angular2-multiselect/div/div[2]/div[3]/div[2]/input"),
					"Sparrow Clinton Hospital Swing Bed - 805 S Oakland St, St. Johns, CLINTON - 48879");
			WebElement selectAllFilteredValues = driver.findElement(By.xpath(
					".//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[4]/div/div/div[2]/div/angular2-multiselect/div/div[2]/div[3]/div[3]/div/label/span[1]"));
//*[@id="main_content"]/app-levelone-detail/div/section/form/div[4]/div/div/div[2]/button
			action.moveToElement(selectAllFilteredValues).click().build().perform();
			TimeUnit.SECONDS.sleep(2);

			// Send button
			click(By.xpath(
					"//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[4]/div/div/div[2]/button"));
			WebElement addedFacilityName = driver.findElement(By.xpath(
					"//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[4]/div/div/div[3]/div/div/table/tbody/tr/td[1]"));
			System.out.println("addedFacility Name is -----" + addedFacilityName.getText());
			TimeUnit.SECONDS.sleep(2);

			// Closing alert button
			System.out.println("Is Alret visible ?"
					+ driver.findElement(By.xpath("//*[@id=\"alert_placeholder\"]/div/button")).isDisplayed()
					+ "alret displayed text is ===="
					+ driver.findElement(By.xpath("//*[@id=\"alert_placeholder\"]/div/button")).getText());
			click(By.xpath("//*[@id=\"alert_placeholder\"]/div/button"));

			// Switching to Nursing Facility
			selectDropDownByVisibleText(By.xpath("//*[@id=\"userFacilities\"]"),
					"Sparrow Clinton Hospital Swing Bed (Nursing Home) St. Johns (MI)");
			TimeUnit.SECONDS.sleep(2);

			// Click On RequestsQueue
			click(By.xpath("//*[@id=\"main\"]/li[16]/a"));
			TimeUnit.SECONDS.sleep(2);

			// Sorting on descending order
			click(By.id("sortOrder"));
			TimeUnit.SECONDS.sleep(2);

			// Click On Approve
			click(By.xpath("//*[@id=\"Approve\"]"));
			TimeUnit.SECONDS.sleep(2);

			// Click On Yes on alert
			click(By.xpath("/html/body/modal-container/div/div/div/div/div[3]/div/button[1]"));
			TimeUnit.SECONDS.sleep(2);

			// Closing alert button
			System.out.println("Is Alret visible ?"
					+ driver.findElement(By.xpath("//*[@id=\"alert_placeholder\"]/div/strong")).isDisplayed()
					+ "alret displayed text is ===="
					+ driver.findElement(By.xpath("//*[@id=\"alert_placeholder\"]/div/strong")).getText());
			click(By.xpath("//*[@id=\"alert_placeholder\"]/div/button"));
			TimeUnit.SECONDS.sleep(2);

			// Switching back to Original Facility
			selectDropDownByVisibleText(By.xpath("//*[@id=\"userFacilities\"]"),
					"Sparrow Carson Hospital (Hospital) Carson City (MI)");
			TimeUnit.SECONDS.sleep(2);

			// Click On NF Response Queue
			click(By.xpath("//*[@id=\"main\"]/li[17]/a"));
			TimeUnit.SECONDS.sleep(2);

			click(By.id("sortOrder"));
			TimeUnit.SECONDS.sleep(2);

			List<WebElement> gridTableRows = driver.findElements(By.xpath("//*[@id=\"gridTable\"]/tbody/tr")); //// *[@id="gridTable"]/tbody/tr
			gridTableRows.stream().forEach(x -> x.getText().equals(ssn));
			By expectedRow = null;

			for (int i = 1; i < gridTableRows.size(); i++) {
				WebElement rowMatch = driver
						.findElement(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[" + i + "]/td[2]/span"));
				if (rowMatch.getText().equals(ssn)) {
					System.out.println(
							"SSN Match Found and the SSN is " + rowMatch.getText() + " and the row number is " + i);
					expectedRow = By.xpath("//*[@id=\"gridTable\"]/tbody/tr[" + i + "]/td[1]/span/a");
				} else {
					System.out.println("SSN Match Didnt Found and the row num is " + i + "The Input SSN is " + ssn);
				}
			}
			TimeUnit.SECONDS.sleep(5);

			click(expectedRow);
			TimeUnit.SECONDS.sleep(5);

			// Assign Transfer Request Status
			click(By.xpath(
					"//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[4]/div/div/div[3]/div/div/table/tbody/tr/td[5]/button"));

			// Switching to Nursing Facility
			selectDropDownByVisibleText(By.xpath("//*[@id=\"userFacilities\"]"),
					"Sparrow Clinton Hospital Swing Bed (Nursing Home) St. Johns (MI)");
			TimeUnit.SECONDS.sleep(2);

			// Click on Admission Queue
//			click(By.xpath("//*[@id=\"main\"]/li[18]/a"));
			click(By.xpath("(//li/a[contains(text(),\"Admissions\")])[1]"));

			// Sort by Initiated Date
			click(By.id("sortOrder"));
			TimeUnit.SECONDS.sleep(2);

			// click on PAS
			click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]/span/a"));
			TimeUnit.SECONDS.sleep(2);

			// click on date picker
			click(By.xpath(
					"//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[2]/div/div/div/div[1]/dl/dd[3]/div/p-calendar/span/button/span[1]"));
			TimeUnit.SECONDS.sleep(5);

			// select a date
			click(By.xpath(
					"//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[2]/div/div/div/div[1]/dl/dd[3]/div/p-calendar/span/div/div/div[2]/table/tbody/tr[2]/td[5]/a"));
			TimeUnit.SECONDS.sleep(2);

			// update
			click(By.xpath(
					"//*[@id=\"main_content\"]/app-levelone-detail/div/section/form/div[2]/div/div/div/div[1]/dl/dd[3]/button"));
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void submitForm3877(String ssn, By param1, By param2, By param3, By param4, By param5, By param6,
			By param7) {
		try {
			TimeUnit.SECONDS.sleep(5);
			clickOnOBRAMDHHS();
			TimeUnit.SECONDS.sleep(2);

			// Switching to Original Facility
			selectDropDownByVisibleText(By.xpath("//*[@id=\"userFacilities\"]"),
					"Sparrow Carson Hospital (Hospital) Carson City (MI)");
			TimeUnit.SECONDS.sleep(2);
//			clickOnConsumerModule();
			click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[2]/div/a"));
			TimeUnit.SECONDS.sleep(2);

			// enter SSN
			sendKeys(By.xpath("//*[@id=\"ssn\"]"), ssn);
			TimeUnit.SECONDS.sleep(2);

			// click on search button
			click(By.xpath(
					"//*[@id=\"main_content\"]/app-consumer-module/app-search-consumer/div/form/section/div[2]/div[6]/button[1]"));
			TimeUnit.SECONDS.sleep(2);

			// click on the row
			searchAndClickByText(By.xpath("//*[@id=\"gridTable\"]/tbody/tr/td[1]"), ssn);
			TimeUnit.SECONDS.sleep(2);

			TimeUnit.SECONDS.sleep(2);
			clickDropdownMenuItems(By.xpath(
					"//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[1]/button[2]"),
					By.xpath(
							"//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[1]/ul/li[5]/a"));
			TimeUnit.SECONDS.sleep(2);

			// click on alert button
			/*
			 * WebElement alertButton= driver.findElement(By.xpath(
			 * "//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[3]/form/div/div/div[3]/div/button[1]"
			 * )); click(By.xpath(
			 * "//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[3]/form/div/div/div[3]/div/button[1]"
			 * ));
			 */

			// Create 3877-78 - Level I Screening
			selectDropDownByValue(By.xpath("//*[@id=\"screenTypeId\"]"), "PAS");
			TimeUnit.SECONDS.sleep(2);

			// Click On Next Button
			click(By.xpath("//*[@id=\"main_content\"]/app-create-levelone/div/section/div[3]/ul/li[3]/button"));
			TimeUnit.SECONDS.sleep(2);

			// Leave Section One and click on section II
			click(By.xpath("//*[@id=\"main_content\"]/app-form-3877/div/section/div[2]/div[1]/ul/li[2]/a"));
			TimeUnit.SECONDS.sleep(2);

			// Agency Information - Screening Criteria
			click(param1);
			TimeUnit.SECONDS.sleep(1);

			click(param2);
			TimeUnit.SECONDS.sleep(1);

			click(param3);
			TimeUnit.SECONDS.sleep(1);

			click(param4);
			TimeUnit.SECONDS.sleep(1);

			click(param5);
			TimeUnit.SECONDS.sleep(1);

			click(param6);
			TimeUnit.SECONDS.sleep(1);

			click(param7);
			TimeUnit.SECONDS.sleep(1);

			// NOTEs
			sendKeys(By.xpath("//*[@id=\"notes11\"]"), "Submitting Form 3877 Automation");

			checkAcknowledgeButton(By.xpath("//*[@id=\"submit\"]"));
			TimeUnit.SECONDS.sleep(2);

			scrollDown();
			saveButton(By.xpath("//*[@id=\"section2\"]/form/div[4]/div/div/div[13]/div/ul/li[6]/button"));

			// Before the action that redirect to the new tab:
			String windHandleCurrent = driver.getWindowHandle();
			TimeUnit.SECONDS.sleep(5);
			// code that click in a btn/link in order to open a new tab goes here
			// now to make selenium move to the new tab
			ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
			for (int i = 0; i < windows.size(); i++) {
				String aWindow = windows.get(i);
				if (aWindow != windHandleCurrent) {
					driver.switchTo().window(aWindow);
					System.out.println(driver.getTitle());
				}
			}
			// System.out.println("url : "+driver.getCurrentUrl());

			TimeUnit.SECONDS.sleep(3);
			// verifyPDFContent(driver.getCurrentUrl(),
			// ApplicationConstants.PLNDSC_PDF_TEXT,"level_PLNDSC.pdf");
			File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(screenshot1.toPath(), Paths.get(System.getProperty("user.dir"), "reports", "Screenshots",
					"IntiatedPAS_" + getCurrentDateTimeString() + ".png"));

			// switch to the first tab
			driver.switchTo().window(windHandleCurrent);
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String editConsumer(UserData userData, String ssn) throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
		clickOnOBRAMDHHS();
		TimeUnit.SECONDS.sleep(2);
		// clickOnConsumerModule();
		click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[2]/div/a"));
		TimeUnit.SECONDS.sleep(2);

		System.out.println(ssn);
		sendKeys(By.xpath("//*[@id=\"ssn\"]"), ssn);
		TimeUnit.SECONDS.sleep(2);

		click(By.xpath(
				"//*[@id=\"main_content\"]/app-consumer-module/app-search-consumer/div/form/section/div[2]/div[6]/button[1]"));
		TimeUnit.SECONDS.sleep(2);

		searchAndClickByText(By.xpath("//*[@id=\"gridTable\"]/tbody/tr/td[1]"), ssn);

		TimeUnit.SECONDS.sleep(2);
		clickDropdownMenuItems(By
				.xpath("//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[1]/button[2]"),
				By.xpath(
						"//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[1]/ul/li[1]/a"));

		String newSSN = userData.getSsn();
		clearSendKeys(By.xpath("//*[@id=\"ssn\"]"), newSSN);
		TimeUnit.SECONDS.sleep(2);

		clearSendKeys(By.xpath("//*[@id=\"add_firstname\"]"), userData.getFirstName());
		TimeUnit.SECONDS.sleep(2);

		clearSendKeys(By.xpath("//*[@id=\"add_lastname\"]"), userData.getLastName());
		TimeUnit.SECONDS.sleep(2);
		// scrollDown();

		clearSendKeys(By.xpath("//*[@id=\"zipCode1\"]"), "48805");
		TimeUnit.SECONDS.sleep(2);

		saveButton(By.xpath("//*[@id=\"main_content\"]/app-add-edit-consumer/div/section/form/div[9]/button[1]"));
		TimeUnit.SECONDS.sleep(2);

		click(By.xpath(
				"//*[@id=\"main_content\"]/app-consumer-detail/section/div[3]/div/div[2]/div/div[2]/div/div/div[1]/a"));
		TimeUnit.SECONDS.sleep(2);

		clickDropdownMenuItems(
				By.xpath("//*[@id=\"main_content\"]/app-view-legal-representative/div/section/div[2]/button[2]"),
				By.xpath("//*[@id=\"main_content\"]/app-view-legal-representative/div/section/div[2]/ul/div/li[3]/a"));
		TimeUnit.SECONDS.sleep(2);

		return newSSN;

	}

	public void editConsumerWithoutLegalRep(UserData userData) throws InterruptedException {

		TimeUnit.SECONDS.sleep(2);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[1]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		// click(By.xpath("//*[@id=\"nav-main\"]/div/div[1]/a/div/p[1]"));
		TimeUnit.SECONDS.sleep(2);
		// clickOnConsumerModule();
		click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[2]/div/a"));
		TimeUnit.SECONDS.sleep(2);

		sendKeys(By.xpath("//*[@id=\"ssn\"]"), EDIT_CONSUMER_WITHOUT_LEGALREP_SSN);
		TimeUnit.SECONDS.sleep(2);

		click(By.xpath(
				"//*[@id=\"main_content\"]/app-consumer-module/app-search-consumer/div/form/section/div[2]/div[6]/button[1]"));
		TimeUnit.SECONDS.sleep(2);

		searchAndClickByText(By.xpath("//*[@id=\"gridTable\"]/tbody/tr/td[1]"), EDIT_CONSUMER_WITHOUT_LEGALREP_SSN);

		TimeUnit.SECONDS.sleep(2);
		clickDropdownMenuItems(By
				.xpath("//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[1]/button[2]"),
				By.xpath(
						"//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[1]/ul/li[1]/a"));

		clearSendKeys(By.xpath("//*[@id=\"ssn\"]"), userData.getSsn());
		TimeUnit.SECONDS.sleep(2);

		clearSendKeys(By.xpath("//*[@id=\"add_firstname\"]"), userData.getFirstName());
		TimeUnit.SECONDS.sleep(2);

		clearSendKeys(By.xpath("//*[@id=\"add_lastname\"]"), userData.getLastName());
		TimeUnit.SECONDS.sleep(2);
		scrollDown();

		clearSendKeys(By.xpath("//*[@id=\"zipCode1\"]"), "48805");
		TimeUnit.SECONDS.sleep(2);

		saveButton(By.xpath("//*[@id=\"main_content\"]/app-add-edit-consumer/div/section/form/div[9]/button[1]"));
		TimeUnit.SECONDS.sleep(2);
	}
	
	public void CMHLevel1WorkflowPAS(UserData userData, String ssn) {
		try {
			submitForm3877(ssn, By.xpath("//*[@id=\"scr_crt_no_1\"]"), By.xpath("//*[@id=\"scr_crt_no_2\"]"),
					By.xpath("//*[@id=\"scr_crt_yes_3\"]"), By.xpath("//*[@id=\"scr_crt_no_4\"]"),
					By.xpath("//*[@id=\"scr_crt_no_5\"]"), By.xpath("//*[@id=\"scr_crt_no_6\"]"),
					By.xpath("//*[@id=\"scr_crt_no_7\"]"));

			sendKeys(By.xpath("//*[@id=\"ssn\"]"), ssn);
			TimeUnit.SECONDS.sleep(2);

			// search
			click(By.xpath("//*[@id=\"filter-row\"]/div[3]/div/button[1]"));
			TimeUnit.SECONDS.sleep(2);

			// Sorting on descending order
			click(By.id("sortOrder"));
			TimeUnit.SECONDS.sleep(2);

			// Click On Screening type PAS link
			click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]/span/a")); //// *[@id="gridTable"]/tbody/tr/td[1]/span/a
			TimeUnit.SECONDS.sleep(2);
			scrollDown();
			
			selectDropDownByVisibleText(By.id("agenciesId"), "Montcalm Care Network");
			
			// NOTEs
			sendKeys(By.xpath("//div/*[@id='notes']"), "Sending to CMH");

			// submit button
			saveButton(By.xpath("//*[@id=\"submitLvel1\"]"));
			TimeUnit.SECONDS.sleep(2);

			// Before the action that redirect to the new tab:
			String windHandleCurrent = driver.getWindowHandle();
			TimeUnit.SECONDS.sleep(5);
			// code that click in a btn/link in order to open a new tab goes here
			// now to make selenium move to the new tab
			ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
			for (int i = 0; i < windows.size(); i++) {
				String aWindow = windows.get(i);
				if (aWindow != windHandleCurrent) {
					driver.switchTo().window(aWindow);
				}
			}
			System.out.println("url  :  " + driver.getCurrentUrl());

			TimeUnit.SECONDS.sleep(3);

			// switch to the first tab
			driver.switchTo().window(windHandleCurrent);
			TimeUnit.SECONDS.sleep(3);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void CMHLevel1WorkflowARR(UserData userData, String ssn) {
		try {
			submitForm3877ARR(ssn, By.xpath("//*[@id=\"scr_crt_no_1\"]"), By.xpath("//*[@id=\"scr_crt_no_2\"]"),
					By.xpath("//*[@id=\"scr_crt_yes_3\"]"), By.xpath("//*[@id=\"scr_crt_no_4\"]"),
					By.xpath("//*[@id=\"scr_crt_no_5\"]"), By.xpath("//*[@id=\"scr_crt_no_6\"]"),
					By.xpath("//*[@id=\"scr_crt_no_7\"]"));

			TimeUnit.SECONDS.sleep(2);
			// click on Send to OBRA
			click(By.xpath("//*[@id=\"main\"]/li[20]/a"));
			TimeUnit.SECONDS.sleep(2);

			// Sorting on descending order
			click(By.id("sortOrder"));
			TimeUnit.SECONDS.sleep(2);

			// Click On Screening type ARR link
			click(By.xpath("//*[@id=\"gridTable\"]/tbody/tr[1]/td[1]/span/a"));
			TimeUnit.SECONDS.sleep(2);
			
			scrollDown();
			
			selectDropDownByVisibleText(By.id("agenciesId"), "Clinton-Eaton-Ingham CMH");
			
			// NOTEs
			sendKeys(By.xpath("//div/*[@id='notes']"), "Sending to CMH");

			// submit button
			saveButton(By.xpath("//*[@id=\"submitLvel1\"]"));
			TimeUnit.SECONDS.sleep(2);

			// Before the action that redirect to the new tab:
			String windHandleCurrent = driver.getWindowHandle();
			TimeUnit.SECONDS.sleep(5);
			// code that click in a btn/link in order to open a new tab goes here
			// now to make selenium move to the new tab
			ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
			for (int i = 0; i < windows.size(); i++) {
				String aWindow = windows.get(i);
				if (aWindow != windHandleCurrent) {
					driver.switchTo().window(aWindow);
				}
			}
			System.out.println("url  :  " + driver.getCurrentUrl());

			TimeUnit.SECONDS.sleep(3);
			// verifyPDFContent(driver.getCurrentUrl(),
			// ApplicationConstants.PLNDSC_PDF_TEXT,"level_PLNDSC.pdf");

			// switch to the first tab
			driver.switchTo().window(windHandleCurrent);
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void submitForm3877ARR(String ssn, By param1, By param2, By param3, By param4, By param5, By param6,
			By param7) {
		try {
			TimeUnit.SECONDS.sleep(5);
			clickOnOBRAMDHHS();
			TimeUnit.SECONDS.sleep(2);

			// Switching to Original Facility
//			selectDropDownByVisibleText(By.xpath("//*[@id=\"userFacilities\"]"),
//					"Sparrow Carson Hospital (Hospital) Carson City (MI)");
			selectDropDownByVisibleText(By.xpath("//*[@id=\"userFacilities\"]"),
					"Sparrow Clinton Hospital Swing Bed (Nursing Home) St. Johns (MI)");
			TimeUnit.SECONDS.sleep(2);
			// clickOnConsumerModule();
			click(By.xpath("//*[@id=\"main_content\"]/app-menu-component/app-dashboard/div/section/div[2]/div/a"));
			TimeUnit.SECONDS.sleep(2);

			// enter SSN
			sendKeys(By.xpath("//*[@id=\"ssn\"]"), ssn);
			TimeUnit.SECONDS.sleep(2);

			// click on search button
			click(By.xpath(
					"//*[@id=\"main_content\"]/app-consumer-module/app-search-consumer/div/form/section/div[2]/div[6]/button[1]"));
			TimeUnit.SECONDS.sleep(2);

			// click on the row
			searchAndClickByText(By.xpath("//*[@id=\"gridTable\"]/tbody/tr/td[1]"), ssn);
			TimeUnit.SECONDS.sleep(2);

			TimeUnit.SECONDS.sleep(2);
			clickDropdownMenuItems(By.xpath(
					"//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[1]/button[2]"),
					By.xpath(
							"//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[1]/ul/li[5]/a"));
			
			//*[@id="main_content"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[3]/form/div/div/div[3]/div/button[1]
			TimeUnit.SECONDS.sleep(2);

			// click on alert button
			/*
			 * WebElement alertButton= driver.findElement(By.xpath(
			 * "//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[3]/form/div/div/div[3]/div/button[1]"
			 * )); click(By.xpath(
			 * "//*[@id=\"main_content\"]/app-consumer-detail/section/div[2]/app-breadcrumbs/div[3]/form/div/div/div[3]/div/button[1]"
			 * ));
			 */

			// Create 3877-78 - Level I Screening
			selectDropDownByValue(By.xpath("//*[@id=\"screenTypeId\"]"), "ARR");
			TimeUnit.SECONDS.sleep(2);

			// Click On Next Button
			click(By.xpath("//*[@id=\"main_content\"]/app-create-levelone/div/section/div[3]/ul/li[3]/button"));
			TimeUnit.SECONDS.sleep(2);

			// Leave Section One and click on section II
			click(By.xpath("//*[@id=\"main_content\"]/app-form-3877/div/section/div[2]/div[1]/ul/li[2]/a"));
			TimeUnit.SECONDS.sleep(2);

			// click on date picker
			click(By.xpath(
					"//*[@id=\"section2\"]/form/div[2]/div[2]/div/div/div[2]/my-date-picker/div/div/div/button/span"));
			TimeUnit.SECONDS.sleep(5);

			// click on the date
			click(By.xpath(
					"//*[@id=\"section2\"]/form/div[2]/div[2]/div/div/div[2]/my-date-picker/div/div[2]/table[2]/tbody/tr[3]/td[2]/div/span"));

			// Agency Information - Screening Criteria
			click(param1);
			TimeUnit.SECONDS.sleep(1);

			click(param2);
			TimeUnit.SECONDS.sleep(1);

			click(param3);
			TimeUnit.SECONDS.sleep(1);

			click(param4);
			TimeUnit.SECONDS.sleep(1);

			click(param5);
			TimeUnit.SECONDS.sleep(1);

			click(param6);
			TimeUnit.SECONDS.sleep(1);

			click(param7);
			TimeUnit.SECONDS.sleep(1);

			// NOTEs
			sendKeys(By.xpath("//*[@id=\"notes11\"]"), "Submitting CMH Form 3877 Automation");

			checkAcknowledgeButton(By.xpath("//*[@id=\"submit\"]"));
			TimeUnit.SECONDS.sleep(2);

			scrollDown();
			saveButton(By.xpath("//*[@id=\"section2\"]/form/div[4]/div/div/div[13]/div/ul/li[6]/button"));

			// Before the action that redirect to the new tab:
			String windHandleCurrent = driver.getWindowHandle();
			TimeUnit.SECONDS.sleep(5);
			// code that click in a btn/link in order to open a new tab goes here
			// now to make selenium move to the new tab
			ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
			for (int i = 0; i < windows.size(); i++) {
				String aWindow = windows.get(i);
				if (aWindow != windHandleCurrent) {
					driver.switchTo().window(aWindow);
					System.out.println(driver.getTitle());
				}
			}
			// System.out.println("url : "+driver.getCurrentUrl());

			TimeUnit.SECONDS.sleep(3);
			// verifyPDFContent(driver.getCurrentUrl(),
			// ApplicationConstants.PLNDSC_PDF_TEXT,"level_PLNDSC.pdf");
			File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(screenshot1.toPath(), Paths.get(System.getProperty("user.dir"), "reports", "Screenshots",
					"IntiatedPAS_" + getCurrentDateTimeString() + ".png"));

			// switch to the first tab
			driver.switchTo().window(windHandleCurrent);
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void clickOnCreateNewConsumer() {
		click(By.xpath(
				"//*[@id=\"main_content\"]/app-consumer-module/app-search-consumer/div/form/section/div[4]/div/a/button"));
	}

	private static String getCurrentDateTimeString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		return LocalDateTime.now().format(formatter);
	}

}
