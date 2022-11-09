
package pages
import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil


public class ManageInvoiceOrgPrintPageSteps {



	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyControlDisplayed("Invoice_Report_Form", "ManageInvoiceOrgPrintPage");
	}
}