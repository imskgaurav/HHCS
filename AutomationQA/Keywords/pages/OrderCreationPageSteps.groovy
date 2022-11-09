package pages

import com.google.common.base.Strings

import configs.Path
import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil
import utils.PlatformUtil
import utils.Utilities
import core.BaseControl

public class OrderCreationPageSteps {
	private static url = '/Ordercreation.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================

	public static void clickNewOrderButton() {
		getBaseSteps().clickToControl("New_Order_Button", "OrderCreationPage");
		getBaseSteps().waitForProgressBarDisappear();
	}

	public static void clickOrderWithTemplateButton() {
		getBaseSteps().clickToControl("Order_With_Template_Button", "OrderCreationPage");
		getBaseSteps().waitForProgressBarDisappear();
	}

	public static void clickSaveButton() {
		getBaseSteps().clickToControl("Save_Button", "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();
	}

	public static void selectOrderTypeFilter(String orderType) {
		getBaseSteps().selectItemByTextInComboBox("Filter_Order_Type_ComboBox", orderType, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();
	}


	public static void selectTemplateRow(String templateName){
		getBaseSteps().clickToCellTableTextBaseOnAnotherCellDataTable("Template_Selection_Dialog_Table", "Template Name", templateName, "Template Name", "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();
		getBaseSteps().clickToControl("Template_Selection_Dialog_OK_Button", "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();
	}

	public static String inputAllMandatoryPointsOfJobDetails(String jobDetail, String region, String location,
			String department, String skill, String orderType, String duration, String startDate = DateTimeUtil.nextDateTime(), String endDate = "", String shiftRequirement, String jobDistribution) {
		String randomNumber = Utilities.generateRandomNumber();
		String jobTitle = String.format("%s%s", jobDetail,randomNumber)

		getBaseSteps().setTextToControl("Job_Detail_TextBox",jobTitle, "OrderCreationPage")
		getBaseSteps().selectItemByTextInComboBox("Region_Combobox", region, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Location_Combobox", location, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Department_Combobox", department, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Skill_Combobox", skill, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Order_Type_Combobox", orderType, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Duration_Combobox", duration, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().setTextToControl("Job_Start_Date_TextBox", startDate, "OrderCreationPage")
		getBaseSteps().clickToControl("Job_Description_TextBox", "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		if(!Strings.isNullOrEmpty(endDate)) {
			getBaseSteps().setTextToControl("Job_End_Date_TextBox", endDate, "OrderCreationPage")
			getBaseSteps().clickToControl("Job_Description_TextBox", "OrderCreationPage")
			getBaseSteps().waitForProgressBarDisappear();
		}

		getBaseSteps().selectItemByTextInComboBox("Shift_Requirement_Combobox", shiftRequirement, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Job_Distribution_Combobox", jobDistribution, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();
		return jobTitle;

	}

	public static void inputAllMandatoryPointsOfPerplacement(String jobDetail, String region, String location,
			String department, String skill, String orderType, String placementFee, String minAnnSalRange, String maxAnnSalRange
			, String shiftRequirement, String jobDistribution) {
		String randomNumber = Utilities.generateRandomNumber();
		String jobTitle = String.format("%s%s", jobDetail,randomNumber)

		getBaseSteps().setTextToControl("Job_Detail_TextBox",jobTitle, "OrderCreationPage")
		getBaseSteps().selectItemByTextInComboBox("Region_Combobox", region, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Location_Combobox", location, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Department_Combobox", department, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Skill_Combobox", skill, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Order_Type_Combobox", orderType, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().setTextToControl("Job_Start_Date_TextBox", DateTimeUtil.nextDateTime(), "OrderCreationPage")
		getBaseSteps().clickToControl("Job_Description_TextBox", "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().setTextToControl("Placement_Fee_TextBox",placementFee, "OrderCreationPage")
		getBaseSteps().setTextToControl("Min_Ann_Sal_Range_TextBox",minAnnSalRange, "OrderCreationPage")
		getBaseSteps().setTextToControl("Max_Ann_Sal_Range_TextBox",maxAnnSalRange, "OrderCreationPage")

		getBaseSteps().selectItemByTextInComboBox("Shift_Requirement_Combobox", shiftRequirement, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Job_Distribution_Combobox", jobDistribution, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

	}
	public static void inputAllMandatoryPointsOfOpenPerDiem(String jobDetail, String region, String location,
			String department, String skill, String orderType, String shiftRequirement, String jobDistribution) {
		String randomNumber = Utilities.generateRandomNumber();
		String jobTitle = String.format("%s%s", jobDetail,randomNumber)

		getBaseSteps().setTextToControl("Job_Detail_TextBox",jobTitle, "OrderCreationPage")
		getBaseSteps().selectItemByTextInComboBox("Region_Combobox", region, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Location_Combobox", location, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Department_Combobox", department, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Skill_Combobox", skill, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Order_Type_Combobox", orderType, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Shift_Requirement_Combobox", shiftRequirement, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Job_Distribution_Combobox", jobDistribution, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

	}
	public static void inputMandatoryPointsOfCreationOrderWithEffectiveDatePayRate(String jobDetail, String region, String location,
			String department, String skill, String orderType, String duration, String shiftRequirement, String jobDistribution) {
		String randomNumber = Utilities.generateRandomNumber();
		String jobTitle = String.format("%s%s", jobDetail,randomNumber)

		getBaseSteps().setTextToControl("Job_Detail_TextBox",jobTitle, "OrderCreationPage")
		getBaseSteps().selectItemByTextInComboBox("Region_Combobox", region, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Location_Combobox", location, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Department_Combobox", department, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Skill_Combobox", skill, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Order_Type_Combobox", orderType, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Duration_Combobox", duration, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Shift_Requirement_Combobox", shiftRequirement, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

		getBaseSteps().selectItemByTextInComboBox("Job_Distribution_Combobox", jobDistribution, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();

	}

	public static String updateJobDetailsAndJobStartDateAndSave(String jobDetail="jd_") {
		String randomNumber = Utilities.generateRandomNumber();
		String jobTitle = String.format("%s%s", jobDetail,randomNumber)
		getBaseSteps().setTextToControl("Job_Detail_TextBox",jobTitle, "OrderCreationPage")
		getBaseSteps().setTextToControl("Job_Start_Date_TextBox", DateTimeUtil.nextDateTime(), "OrderCreationPage")
		getBaseSteps().clickToControl("Job_Description_TextBox", "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear();
		getBaseSteps().clickToControl("Save_Button", "OrderCreationPage")
		return jobTitle
	}
	public static void uploadDocumentFile(String timesheetFileName) {
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,timesheetFileName))
		getBaseSteps().uploadFile("Choose_File_Button",filePath, "OrderCreationPage")
		Thread.sleep(5000)
		getBaseSteps().setTextToControl("Document_Description_TextBox", "Test Upload", "OrderCreationPage")
		//Thread.sleep(3000)
		getBaseSteps().clickToControlByJS("Upload_Button", "OrderCreationPage")
		//getBaseSteps().clickToControl("Upload_Button", "OrderCreationPage")
		//getBaseSteps().clickToControl("Upload_Button", "OrderCreationPage")
		Thread.sleep(7000)
	}


	// start Code added by sunita
	public static void selectRegion(String region) {
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Region_ComboBox","Check All","OrderCreationPage") //uncheck all option
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Region_ComboBox",region,"OrderCreationPage")
		getBaseSteps().clickToControl("Region_Arrow_Icon_Combobox","OrderCreationPage")
		getBaseSteps().clickToControl("Job_Id_TextBox","OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void selectDepartment(String department) {
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Department_ComboBox","Check All","OrderCreationPage")//uncheck all option
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Department_ComboBox",department,"OrderCreationPage")
		getBaseSteps().clickToControl("Job_Id_TextBox","OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectLocation(String location) {
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Location_ComboBox","Check All","OrderCreationPage")//uncheck all option
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Location_ComboBox",location,"OrderCreationPage")
		getBaseSteps().clickToControl("Job_Id_TextBox","OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectYear(String year) {
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectItemByTextInComboBox("Year_ComboBox",year, "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear()
		//Thread.sleep(3000)
		getBaseSteps().clickToControl("Upload_Button", "OrderCreationPage")
		//getBaseSteps().clickToControl("Upload_Button", "OrderCreationPage")
		Thread.sleep(7000)
	}


	//end code added by sunita


	public static void selectCredentialsAndSave(String credential="") {

		if(!Strings.isNullOrEmpty(credential)) {
			getBaseSteps().clickToCellTableCheckboxBaseOnAnotherCellDataTable("Credentials_List_Table", "2", credential,"3", "OrderCreationPage")//2 is Credential Name, 3 is Req. for Submission
			getBaseSteps().waitForProgressBarDisappear()
		}
		getBaseSteps().clickToControl("Save_Changes_Button", "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickSubmitButton() {
		getBaseSteps().clickToControl("Submit_Button", "OrderCreationPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickOKButtonToConfirmDialog() {
		getBaseSteps().clickToControl("OK_Button", "CommonLocators")
		getBaseSteps().waitForProgressBarDisappear()
	}

	// =================== ASSERT METHODS ZONE ======================

	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}

	public static void verifyJobStatusAs(String jobTitle, String expectedStatus) {
		getBaseSteps().waitForTableDataLoaded("Order_List_Table", "OrderSearchPage")
		String actualStatus = getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Order_List_Table",
				"Job Title", jobTitle, "Status", "OrderSearchPage");
		String passMsg = String.format("Job Status is %s",actualStatus)
		AssertSteps.verifyExpectedResult(actualStatus.contains(expectedStatus),passMsg,"Job Status is NOT matched");
	}

	public static void verifyCandidateStatusAs(String candidate, String expectedStatus) {
		getBaseSteps().waitForTableDataLoaded("Candidate_List_Table", "OrderSearchPage")
		String actualStatus = getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Candidate_List_Table",
				"Name", candidate, "Status", "OrderSearchPage")
		String passMsg = String.format("Candidate Status is %s",actualStatus)
		AssertSteps.verifyExpectedResult(actualStatus.contains(expectedStatus),passMsg,"Candidate Status is NOT matched");
	}
	public static void verifyEffectiveDateIsDisplayAccordingly(String effectiveDateOnPayRatePage, String jobStartDate, String effectiveDateOnCreationOrderPage) {
		String formatEffectiveDateOnPayRatePage = DateTimeUtil.convertDateStringFormat(effectiveDateOnPayRatePage,"M/dd/yyyy","MM/dd/yyyy")
		if (DateTimeUtil.isDatetimeBeforeDatetime(formatEffectiveDateOnPayRatePage, jobStartDate) == true) {
			AssertSteps.verifyExpectedResult(effectiveDateOnCreationOrderPage.equals(jobStartDate), String.format("Effective date on table of Creation Order page is %s that matches with Job start date: %s",effectiveDateOnCreationOrderPage,jobStartDate))
		} else {
			AssertSteps.verifyExpectedResult(effectiveDateOnCreationOrderPage.equals(formatEffectiveDateOnPayRatePage), String.format("Effective date on table of Creation Order page is %s that matches with Effective date from Default Pay Rate page %s",effectiveDateOnCreationOrderPage, effectiveDateOnPayRatePage))
		}
	}

	public static void verifyTotalPositionCorrect(String expectedTotal, String actualTotal) {

		AssertSteps.verifyExpectedResult(actualTotal.equals(expectedTotal))
	}
}
