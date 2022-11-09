package pages

import core.BaseSteps
import core.ControlFactory
import core.AssertSteps



public class OrderSearchPageSteps {
	private static url = '/OrderSearch.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	// =================== ACTION METHODS ZONE ======================

	public static String getJobIdFromJobTitleAndStatus(String jobTitle, String status) {
		getBaseSteps().waitForTableDataLoaded("Order_List_Table", "OrderSearchPage")
		HashMap<Integer, String> mapConditionData = new HashMap<Integer, String>();
		// add elements to map
		mapConditionData.put("Status", status);
		mapConditionData.put("Job Title", jobTitle);

		return getBaseSteps().getCellTextBaseOnMultipleCellDataTable("Order_List_Table", mapConditionData, "ID","OrderSearchPage")
	}

	public static void verifyJobStatusBaseOnJobId(String jobId, String expectedStatus) {
		getBaseSteps().waitForTableDataLoaded("Order_List_Table", "OrderSearchPage")
		String actualStatus = getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Order_List_Table","ID", jobId, "Status", "OrderSearchPage");
		String passMsg = String.format("Job Id -%s has %s status",jobId,actualStatus)
		AssertSteps.verifyExpectedResult(actualStatus.contains(expectedStatus),passMsg,"Job Status is NOT matched");
	}

	public static void verifyCandidateIsDeployed(String candidateName) {
		String attributeTitle = getBaseSteps().getCellLinkTableAttributeBasedOnCellContainsText("Candidate_List_Table", "1", candidateName, "1", "title", "OrderSearchPage")
		String passMsg = String.format("%s is listed in the candidate list grid with Asterik(*) symbol next to his name",candidateName)
		AssertSteps.verifyExpectedResult(attributeTitle.contains("Deployed for Job"),passMsg, "Candidate has NOT Asterik(*)")
	}

	public static void selectSkill(String primarySkill) {

		OrderSearchPageSteps.getBaseSteps().waitForControlClickable("Search_Skill_Type_ComboBox", "OrderSearchPage")
		OrderSearchPageSteps.getBaseSteps().clickToControlByJS("Search_Skill_Type_ComboBox", "OrderSearchPage")
		OrderSearchPageSteps.getBaseSteps().clickToControl("Deselect_All_Button", "OrderSearchPage")
		OrderSearchPageSteps.getBaseSteps().clickLabelwithText("Skills_option", primarySkill, "OrderSearchPage")
		OrderSearchPageSteps.getBaseSteps().clickToControl("Content_Div", "OrderSearchPage")
		OrderSearchPageSteps.getBaseSteps().waitForControlDisplay("Search_Button", "OrderSearchPage")



	}

	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
	public static void verifyVacanciesOfJob(String jobId, String expectedNumber) {
		getBaseSteps().waitForTableDataLoaded("Order_List_Table", "OrderSearchPage")
		String actualStatus = getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Order_List_Table","ID", jobId, "Vacancies", "OrderSearchPage");
		String passMsg = String.format("Job Id - %s has %s Vacancies",jobId,actualStatus)
		AssertSteps.verifyExpectedResult(actualStatus.equals(expectedNumber),passMsg,"Job Status is NOT matched");
	}

}
