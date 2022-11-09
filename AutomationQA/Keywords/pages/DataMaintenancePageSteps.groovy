package pages
import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil

public class DataMaintenancePageSteps {
	private static url = '/VMSMaintenance.aspx';
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	// =================== ACTION METHODS ZONE ======================

	// =================== ASSERT METHODS ZONE ======================

	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}

}
