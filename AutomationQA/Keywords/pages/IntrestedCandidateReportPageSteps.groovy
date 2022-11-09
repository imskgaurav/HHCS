package pages

import core.BaseSteps
import core.ControlFactory
public class IntrestedCandidateReportPageSteps {

	private static url = '/InterestedCandidateReport.aspx';
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================//

	public  static void clickSearch() {

		getBaseSteps().clickToControl("Search_button", "IntrestedCandidateReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	
	
	public static void clickExport() {

	getBaseSteps().clickToControl("Export_button", "IntrestedCandidateReportPage")
	getBaseSteps().waitForProgressBarDisappear()
	}

}
