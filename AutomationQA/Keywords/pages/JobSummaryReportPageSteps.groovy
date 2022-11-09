package pages


import core.BaseSteps
import core.ControlFactory
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
public class JobSummaryReportPageSteps {

	private static String url ="/rptJobCandidateDetails.aspx"
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================

	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","JobSummaryReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "JobSummaryReportPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void selectEndDate(String endDate){
		getBaseSteps().clickToControl("End_Date_TextBox","JobSummaryReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "JobSummaryReportPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static getDetails() {

		getBaseSteps().clickToControl("GetDetails_Button", "JobSummaryReportPage")
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
	public static void filterReportByActivitybetweenDaterange(){
		
		getBaseSteps().clickToControl("Activity_Radio_Button", "JobSummaryReportPage")
		getDetails()
		getBaseSteps().verifyControlEnabled("Button_Img","JobSummaryReportPage")
		getBaseSteps().waitForControlDisplay("Table_Data", "JobSummaryReportPage")
		
	}

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {
		//String excelContent = XLReader.getFullRowText(rowNum, filePath,sheetIndex)
		String excelContent = (ExcelUtil.getFormattedRowData(rowNum, filePath).trim())
		excelContent=Utilities.replaceAllSpacesOfString(excelContent)
		String msg = String.format("The expected content is: %s.%nThe excel file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}

	//New Function //

	public static void filterByDate(String startDate, String endDate)	{

		selectStartDate(startDate)
		selectEndDate(endDate)
		getDetails()

	}

	public static String captureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "JobSummaryReportPage")
		return rowData
	}

	public static void ReportDownloadAndMatch(downloadedReportName,String startDate, String endDate, FailureHandling failureHandling=FailureHandling.CONTINUE_ON_FAILURE ) {
		try {
		filterByDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data',"JobSummaryReportPage" )
		String columnHeader=captureGridData('2')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('3')
	    //Export and Verify File Downloaded//
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"JobSummaryReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		println filePath
		verifyExcelFileRowContentIsCorrect(5, filePath,columnHeader)
		List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
		String excelContent = ExcelUtil.getFormattedRowData(6, filePath)
		List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
		String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL FILE CONTENT is: %s",dataRow, excelRow )
		AssertSteps.verifyExpectedResult(compareLists(dataRow, excelRow),msg,msg)

	}
	catch(Exception ex) {
		
		ex.printStackTrace()
		
	}

	}

}
