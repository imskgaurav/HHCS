package pages

import configs.Path
import configs.VMSStrings
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil
import utils.PlatformUtil



public class OrderTemplateManagementPageSteps {
	private static url = '/OrderTemplateManagement.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	// =================== ACTION METHODS ZONE ======================

	public static void searchTemplate(String templateName) {
		getBaseSteps().setTextToControl("Template_Name_TextBox", templateName, "OrderTemplateManagementPage")
		getBaseSteps().clickToControl("Search_Button", "OrderTemplateManagementPage")
		getBaseSteps().waitForProgressBarDisappear();
		getBaseSteps().clickToControl("Search_Button", "OrderTemplateManagementPage")
		getBaseSteps().waitForProgressBarDisappear();
	}


	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
