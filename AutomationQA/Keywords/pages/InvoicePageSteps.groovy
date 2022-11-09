package pages
import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil

public class InvoicePageSteps {
	private static url = '/ManageInvoiceVMS_WeekWise.aspx';
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	// =================== ACTION METHODS ZONE ======================

	public static void selectStatus(String status) {
		getBaseSteps().selectItemByContainedTextInComboBox("Status_ComboBox", status,"InvoicePage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectInvoiceRecord(String invoiceNumber) {
		getBaseSteps().clickToHeaderTableCheckBoxControl("Invoice_Table","InvoicePage") //uncheck all checkbox
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickToCellTableCheckboxWithoutFirstRowBaseOnAnotherCellDataTable("Invoice_Table", "3", invoiceNumber, "InvoicePage") //select invoice checkbox
		getBaseSteps().waitForProgressBarDisappear()
	}

	// =================== ASSERT METHODS ZONE ======================

	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}

	public static void verifyFieldContainsTextAndReuired(String controlName, String expectedText) {
		getBaseSteps().verifyControlContainedTextDisplayed(controlName, expectedText, "InvoicePage")

		String getClassAttribute = getBaseSteps().getAttributeFromControl(controlName, "class", "InvoicePage")
		AssertSteps.verifyExpectedResult(getClassAttribute.contains("req"), String.format("The %s field is required", controlName))
	}
}
