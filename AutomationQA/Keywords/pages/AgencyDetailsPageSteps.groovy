package pages

import core.BaseSteps
import core.ControlFactory
public class AgencyDetailsPageSteps {
	private static url = '/ManageAgency.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================


	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
