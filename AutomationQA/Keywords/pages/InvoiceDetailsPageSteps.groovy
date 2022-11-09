
package pages
import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil


public class InvoiceDetailsPageSteps {
	private static url = '/frmManageInvoice.aspx';


	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================

	public static void clickCancelInvoiceButton() {
		getBaseSteps().clickToControl("Cancel_Invoice_Button","InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickGetDetailsButton() {
		getBaseSteps().clickToControl("Get_Details_Button","InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickSubmitButton() {
		getBaseSteps().clickToControl("Submit_Invoice_Button", "InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickApproveButton() {
		getBaseSteps().clickToControl("Approve_Invoice_Button", "InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickSaveButton() {
		getBaseSteps().clickToControl("Invoice_Payments_Details_Dialog_Save_Payment_Button", "InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickPayInvoiceButton() {
		getBaseSteps().clickToControl("Pay_Invoice_Button", "InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickRollbackInvoiceButton() {
		getBaseSteps().clickToControl("Rollback_Prev_Status_Button","InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static String getInvoiceNumber(String jobTitle) {
		return getBaseSteps().getCellTextBaseOnAnotherCellDataTable ("Invoice_Table", "Job Title", jobTitle, "Inv. No.", "InvoiceDetailsPage")
	}

	public static String getRateFromInvoiceSummaryTable(String type, String date) {
		HashMap<Integer, String> mapConditionData = new HashMap<Integer, String>();
		// add elements to map
		mapConditionData.put("Date", date);
		mapConditionData.put("Type", type);
		return getBaseSteps().getCellTextBaseOnMultipleCellDataTable("Candidate_Timesheet_Summary_Dialog_Inv_Summary_Table", mapConditionData, "9", "InvoiceDetailsPage") //9 is the Rate $ column
	}

	public static String getAmountFromInvoiceSummaryTable(String type, String date) {
		HashMap<Integer, String> mapConditionData = new HashMap<Integer, String>();
		// add elements to map
		mapConditionData.put("Date", date);
		mapConditionData.put("Type", type);
		return getBaseSteps().getCellTextBaseOnMultipleCellDataTable("Candidate_Timesheet_Summary_Dialog_Inv_Summary_Table", mapConditionData, "11", "InvoiceDetailsPage")// 11 is the Amount $ column
	}

	public static String getHourMilesFromInvoiceSummaryTable(String type, String date) {
		HashMap<Integer, String> mapConditionData = new HashMap<Integer, String>();
		// add elements to map
		mapConditionData.put("Date", date);
		mapConditionData.put("Type", type);
		return getBaseSteps().getCellTextBaseOnMultipleCellDataTable("Candidate_Timesheet_Summary_Dialog_Inv_Summary_Table", mapConditionData, "10", "InvoiceDetailsPage")// 10 is the Hours\Miles column
	}

	public static void selectCandidate(String candidate) {
		//getBaseSteps().clickToControl("Candidate_Combobox","InvoiceDetailsPage")
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Candidate_Combobox",candidate,"InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void selectEndDate(String endDate) {
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "InvoiceDetailsPage")
		getBaseSteps().clickToControl("Group_By_DropDownList","InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void selectInvoiceCheckBoxInTable(String candidateName) {
		getBaseSteps().clickToHeaderTableCheckBoxControl("Invoice_Table","InvoiceDetailsPage")
		getBaseSteps().clickToCellTableCheckboxBaseOnAnotherCellDataTable("Invoice_Table", "Candidate Name", candidateName, "InvoiceDetailsPage")
	}

	public static void selectRegion(String region) {
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Region_Combobox",region,"InvoiceDetailsPage")
		getBaseSteps().clickToControl("Status_DropDownList","InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectStatus(String status) {
		getBaseSteps().selectItemByContainedTextInComboBox("Status_DropDownList", status,"InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static void selectStartDate(String startDate) {
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "InvoiceDetailsPage")
		getBaseSteps().clickToControl("End_Date_TextBox", "InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void selectGroupBy(String option) {
		getBaseSteps().selectItemByContainedTextInComboBox("Group_By_DropDownList", option, "InvoiceDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}


	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}

	public static void verifyAmountOnPaymentPopupIsCorrect(String jobTitle, String invoiceNumber) {
		getBaseSteps().clickToControl("Pay_Invoice_Button", "InvoiceDetailsPage")
		getBaseSteps().verifyControlDisplayed("Invoice_Payments_Details_Dialog", "InvoiceDetailsPage")
		String invoicePopup = getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Invoice_Payments_Details_Dialog_Table", "Inv. No.", invoiceNumber, "Invoice Amount", "InvoiceDetailsPage")
		String passMsg = String.format("The invoice number %s is displayed correct in ",invoiceNumber)
		AssertSteps.verifyExpectedResult(invoiceNumber.equals(invoicePopup),passMsg,"Invoice Number n Popup doesn't match with Invoice Number from Invoice Table");
	}

	public static void verifyControlContainsCurrentDay() {
		String currentDay = DateTimeUtil.getDateTime("MM/dd/yyyy","GMT-4")
		Console.println("currentDay is :" +currentDay)
		String paymentDay = getBaseSteps().getValueFromControl("Invoice_Payments_Details_Dialog_Payment_Day_TextBox", "InvoiceDetailsPage")
		Console.println("paymentDay is :" +paymentDay)
		String passMsg = String.format("Payment day is %s",paymentDay)
		AssertSteps.verifyExpectedResult(paymentDay.equals(currentDay),passMsg,"Payment Day doesn't match with Current day");
	}

	public static void verifyInvoiceIdDisplayInTable(String invoiceNumber){
		getBaseSteps().verifyTableBodyContainData("Invoice_Table", invoiceNumber, "InvoiceDetailsPage")
	}
	public static void verifyCalculatedAmountMatchesWithFormula(String subTotal,String orientation, String adjustments, String calculatedAmount ) {
		Double calculatedAmountFormula = Double.parseDouble(subTotal.replace('$','').replace(',', '')) + Double.parseDouble(orientation.replace('$','')) + Double.parseDouble(adjustments.replace('$',''))
		AssertSteps.verifyExpectedResult(calculatedAmountFormula.equals(calculatedAmount.replace('$', '').replace(',', '').toDouble()), String.format("Sum of amount at footer on table is >%s< that matches with Sum of amount formula >%s< ",calculatedAmount.replace('$',''),calculatedAmountFormula))
	}

	//added by sunita since adjustment is missing from footer
	public static void verifyCalculatedAmountMatchesWithFormula(String subTotal,String orientation, String calculatedAmount ) {
		Double calculatedAmountFormula = Double.parseDouble(subTotal.replace('$','').replace(',', '')) + Double.parseDouble(orientation.replace('$',''))
		AssertSteps.verifyExpectedResult(calculatedAmountFormula.equals(calculatedAmount.replace('$', '').replace(',', '').toDouble()), String.format("Sum of amount at footer on table is >%s< that matches with Sum of amount formula >%s< ",calculatedAmount.replace('$',''),calculatedAmountFormula))
	}

	public static void verifyRateOnInvSummaryTableDisplayCorrect (String date, String type, String rateFromOrderCreation, String rateDailyOTFromOrderCreation) {
		String rateOnInvSummarTable = getRateFromInvoiceSummaryTable(type, date)
		if (type.equals("Regular")) {
			AssertSteps.verifyExpectedResult(rateOnInvSummarTable.equals(rateFromOrderCreation), String.format("The Regular rate in invoice table is %s that matches with Regular pay rate %s from Order Creation ",rateOnInvSummarTable, rateFromOrderCreation))
		} else {
			if (type.equals("Regular Daily OT")) {
				Double regularDailyOTExpected = (Double.parseDouble(rateFromOrderCreation))*(Double.parseDouble(rateDailyOTFromOrderCreation))
				AssertSteps.verifyExpectedResult(rateOnInvSummarTable.toDouble().equals(regularDailyOTExpected), String.format("The Regular Daily OT rate in invoice table is %s that matches with Regular pay rate %s multiplying with %s Daily OT from Order Creation = %s",rateOnInvSummarTable, rateFromOrderCreation, rateDailyOTFromOrderCreation,regularDailyOTExpected))
			}
		}
	}
	public static void verifyAmountOnInvSummaryTableDisplayCorrect (String date, String type) {
		String amountOnInvSummaryTable = getAmountFromInvoiceSummaryTable(type,date).replace(',', '')
		String hourMilesOnInvSummaryTable = getHourMilesFromInvoiceSummaryTable(type,date)
		String rateOnInvSummarTable = getRateFromInvoiceSummaryTable(type, date)

		Double expectedAmount = (Double.parseDouble(rateOnInvSummarTable))* (Double.parseDouble(hourMilesOnInvSummaryTable))
		String passMsg = String.format("The amount in table is %s correct by multiplying %s Rate and %s Hour/Miles = %s ",amountOnInvSummaryTable, rateOnInvSummarTable, hourMilesOnInvSummaryTable, expectedAmount)
		AssertSteps.verifyExpectedResult(amountOnInvSummaryTable.toDouble().equals(expectedAmount),passMsg)
	}
}


