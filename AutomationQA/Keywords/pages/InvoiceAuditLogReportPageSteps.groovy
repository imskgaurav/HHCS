package pages

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities

public class InvoiceAuditLogReportPageSteps{

	private static String url ="//rptInvoiceCancelDetails.aspx"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================

	public static void clickGetDetails() {
		getBaseSteps().clickToControl("Show_Details_Button", "InvoiceAuditLogReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}





	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","InvoiceAuditLogReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "InvoiceAuditLogReportPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void selectEndDate(String endDate){

		getBaseSteps().clickToControl("End_Date_TextBox","InvoiceAuditLogReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "InvoiceAuditLogReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickByDetails() {

		getBaseSteps().clickToControl("By_Details_RadioButton", "InvoiceAuditLogReportPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void clickImgButton() {
		getBaseSteps().clickToControl("Button_Img", "InvoiceAuditLogReportPage")

	}

	public static exportExcel() {

		getBaseSteps().clickToControl("Export_Button", "InvoiceAuditLogReportPage")

	}


	public static verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {

		String fileNameAfterExported = String.format("%s.xlsx",prefixFile)

		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		return fileNameAfterExported
	}

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {


		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)
		//expectedContent = expectedContent.trim().replaceAll("[\\t\\n\\r]+"," ")
		String msg = String.format("The EXPECTED content is: %s. The ACTUAL file content is: %s", expectedContent, excelContent )
		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}


	public static  boolean compareLists(final List tableRow,final List excelRow) {
		boolean x
		if(tableRow.equals(excelRow)) {

			x= true
			return x
		}
		else {

			return x

		}
	}

	//New Function for Single Test case Execution //

	public static void filterByDate(String startDate, String endDate) {

		selectStartDate(startDate)
		selectEndDate(endDate)
		clickByDetails()
		clickGetDetails()
	}

	public static String CaptureGridData(String rowNumber)
	{

		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "InvoiceAuditLogReportPage")
		return rowData
	}

	public static void ReportDownloadAndMatch(def downloadedReportName, String startDate, String endDate, FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
         try {
		filterByDate(startDate,  endDate)
		getBaseSteps().verifyControlEnabled('Button_Img', "InvoiceAuditLogReportPage")
		getBaseSteps().waitForControlDisplay('Table_Data', "InvoiceAuditLogReportPage")
		String columnHeader=CaptureGridData('4')
		String firstRow =CaptureGridData('5')
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"InvoiceAuditLogReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(2, filePath, columnHeader)
		String excelContent = ExcelUtil.getFormattedRowData(3, filePath)
		List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
		List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
		String msg = String.format("The EXPECTED CONTENT is: %s.%nThe ACTUAL CONTENT is: %s", dataRow, excelContent )
		'Comparing the First row  Data contents'
		AssertSteps.verifyExpectedResult(compareLists(dataRow, excelRow),msg,msg)
	}
	catch(Exception ex) {
		
		
		ex.printStackTrace()
	}
	}


	public static void filterByDetails(String reportFilter="By Summary") {

		getBaseSteps().clickToControl('By_Summary_RadioButton', "InvoiceAuditLogReportPage")
		clickGetDetails()
		getBaseSteps().verifyControlEnabled('Button_Img', "InvoiceAuditLogReportPage")
		getBaseSteps().waitForControlDisplay('Table_Data', "InvoiceAuditLogReportPage")
	}

	//======================Assert method============================//

	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
