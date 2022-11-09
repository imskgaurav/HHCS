package pages

import core.BaseControl
import core.BaseSteps
import core.Browser
import core.ControlFactory



public class DashboardPageSteps {
	private static url = '/Dashboard.aspx';
	private static String errMsg = "Something went wrong please try again"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	// =================== ACTION METHODS ZONE ======================

	public static void clickToLink(String linkName) {

		String xpath = String.format(getBaseSteps().getXpathFromControl("Common_Links", "DashboardPage"),linkName.trim());
		BaseControl control = new BaseControl(xpath.trim());
		control.click();
	}
	

	public static void clickToLinkByJS(String linkName) {
		String xpath = String.format(getBaseSteps().getXpathFromControl("Common_Links", "DashboardPage"),linkName.trim());
		BaseControl control = new BaseControl(xpath.trim());
		control.clickByJS();
	}



	public static void checkPageLoadAndErrorDisplayForLinkNavigation(String pageTitle) {
		Browser.switchToNewOpenedWindow()
		getBaseSteps().verifyControlWithTextDisplayed("Page_Title_Label", pageTitle, "DashboardPage")
		getBaseSteps().verifyControlNotDisplayedORDisplayWithoutText("Error_Label",errMsg, "DashboardPage",String.format("The message %s is displayed",errMsg))


	}

	
	public static void checkPageLoadAndErrorDisplayForLinkNavigation2(String pageTitle) {
		Browser.switchToNewOpenedWindow()
		getBaseSteps().verifyControlContainedTextDisplayed("Page_Title_Label", pageTitle, "DashboardPage")
		getBaseSteps().verifyControlNotDisplayedORDisplayWithoutText("Error_Label",errMsg, "DashboardPage",String.format("The message %s is displayed",errMsg))


	}



	public static void checkPageLoadAndErrorDisplayForMenuNavigation(String pageTitle) {
		getBaseSteps().verifyControlWithTextDisplayed("Page_Title_Label", pageTitle, "DashboardPage")
		getBaseSteps().verifyControlNotDisplayedORDisplayWithoutText("Error_Label", errMsg,"DashboardPage",String.format("The message %s is displayed",errMsg))
	}



	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
