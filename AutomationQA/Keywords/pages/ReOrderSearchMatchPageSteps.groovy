package pages

import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil
import utils.Utilities



public class ReOrderSearchMatchPageSteps {
	private static url = '/ReOrderSearch.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================
	public static void searchOrderByJobIDAndDate(String jobID, String startDate, String endDate) {
		getBaseSteps().setTextToControl("Job_ID_TextBox", jobID, "ReOrderSearchMatchPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "ReOrderSearchMatchPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "ReOrderSearchMatchPage")
		getBaseSteps().clickToControl("Search_Button", "ReOrderSearchMatchPage")
		getBaseSteps().waitForProgressBarDisappear();
	}

	public static void searchOrderByDate( String startDate, String endDate) {
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "ReOrderSearchMatchPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "ReOrderSearchMatchPage")
		getBaseSteps().clickToControl("Search_Button", "ReOrderSearchMatchPage")
		getBaseSteps().waitForProgressBarDisappear();
	}

	public static void verifyCandidateStatusAs(String candidate, String expectedStatus) {
		getBaseSteps().waitForTableDataLoaded("Candidate_List_Table", "ReOrderSearchMatchPage")
		String actualStatus = getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Candidate_List_Table",
				"Name", candidate, "Status", "ReOrderSearchMatchPage")
		println actualStatus
		String passMsg = String.format("Candidate Status is %s",actualStatus)
		AssertSteps.verifyExpectedResult(actualStatus.contains(expectedStatus),passMsg,"Candidate Status is NOT matched");
	}

	// =================== ASSERT METHODS ZONE ======================

	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
