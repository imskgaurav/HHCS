package pages

import internal.GlobalVariable
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil
import utils.PlatformUtil
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.Utilities

public class InvoiceSummaryReportPageSteps {
	private static url = '/rptInvoiceCheckDetails.aspx';



	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	// =================== ACTION METHODS ZONE ======================


	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","InvoiceSummaryReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "InvoiceSummaryReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().clickToControl("End_Date_TextBox", "InvoiceSummaryReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "InvoiceSummaryReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickGetDetailsButton() {
		getBaseSteps().clickToControl("Get_Details_Button","InvoiceSummaryReportPage")
		getBaseSteps().waitForProgressBarDisappear()
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
		String excelContent=ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent= Utilities.replaceAllSpacesOfString(excelContent)

		String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )

		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}

	public static void filterWithDateAndByDetails(String startDate, String endDate){
		getBaseSteps().clickToControl('By_Details_RadioButton', 'InvoiceSummaryReportPage')
		selectStartDate(startDate)
		selectEndDate(endDate)
		clickGetDetailsButton()

	}
	
//For AgencyLogin Report --> 

	
	
	public static String captureGridData(String rowNumber)
	{

		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "InvoiceSummaryReportPage")
		return rowData
	}
	public static void ReportDownloadAndMatch(def downloadedReportName, String startDate, String endDate, FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
       try {
		filterWithDateAndByDetails(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', "InvoiceSummaryReportPage")
		String columnHeader=captureGridData('4')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow =captureGridData('5')
		List<String>dataRow=Utilities.getRowDataWithTruncatedDecimal(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"InvoiceSummaryReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		'COMPARING HEADER row Data'
		verifyExcelFileRowContentIsCorrect(2, filePath,columnHeader)
		String excelContent = ExcelUtil.getFormattedRowData(3, filePath)
		List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
		'COMPARING the FIRST Data Row Contents'
		AssertSteps.verifyExpectedResult(InvoiceSummaryReportPageSteps.compareLists(dataRow, excelRow))

	}
	catch(Exception ex) {
		
		ex.printStackTrace()
		
	}
	}

	public static void filterReportBySummary() {

		getBaseSteps().clickToControl('By_Summary_RadioButton', 'InvoiceSummaryReportPage')
		clickGetDetailsButton()
		getBaseSteps().verifyControlEnabled('Button_Img', "InvoiceSummaryReportPage")
		getBaseSteps().waitForControlDisplay("Table_Data2", "InvoiceSummaryReportPage")
	
	}


}
