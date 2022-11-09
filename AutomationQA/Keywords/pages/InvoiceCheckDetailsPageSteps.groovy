
package pages

import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable
import utils.DateTimeUtil


public class InvoiceCheckDetailsPageSteps {
	private static url = '/rptInvoiceCheckDetails.aspx';


	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================


	public static void clickGetDetailsButton() {
		getBaseSteps().clickToControl("Get_Details_Button","InvoiceCheckDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}

}
