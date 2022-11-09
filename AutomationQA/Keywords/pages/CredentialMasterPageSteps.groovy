package pages

import core.BaseSteps
import core.ControlFactory



public class CredentialMasterPageSteps {
	private static String url ="/CredentialsMaster.aspx"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================


	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
