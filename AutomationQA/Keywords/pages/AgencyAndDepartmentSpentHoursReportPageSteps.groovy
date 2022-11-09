package pages


import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities

public class AgencyAndDepartmentSpentHoursReportPageSteps {

	private static String errMsg="An error has occurred during report processing. (rsProcessingAborted) ";

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================

	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","AgencyAndDepartmentSpentHoursReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "AgencyAndDepartmentSpentHoursReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().clickToControl("End_Date_TextBox", "AgencyAndDepartmentSpentHoursReportPage")
		//Added because Techenablepage was not accepting the End Date//
		getBaseSteps().clearTextInControl("End_Date_TextBox", "AgencyAndDepartmentSpentHoursReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "AgencyAndDepartmentSpentHoursReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void getDetails() {

		getBaseSteps().clickToControl('Search_Button', 'AgencyAndDepartmentSpentHoursReportPage')
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void checkPageLoadAndErrorDisplayForReport(String pageTitle) {
		getBaseSteps().verifyControlWithTextDisplayed("Page_Title_Label", pageTitle, "AgencyAndDepartmentSpentHoursReportPage")
		getBaseSteps().verifyControlNotDisplayedORDisplayWithoutText("Error_Label",errMsg, "AgencyAndDepartmentSpentHoursReportPage",String.format("The message %s is displayed",errMsg))


	}

	public static String verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {
		String datetimeDownload = DateTimeUtil.getDateTime("MMddyyyy")
		String fileNameAfterExported = String.format("%s_%s.xlsx",prefixFile,datetimeDownload)

		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		return fileNameAfterExported
	}

	//String tableContent = getBaseSteps().getBodyTableText("Candidate_List_Table", "TimeSheetPage");

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {


		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent= Utilities.replaceAllSpacesOfString(excelContent)

		String msg = String.format("The EXPECTED content is: %s.%n The EXCEL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}
	public static void filterByReportTye(String reportType="Department Spent Hours") {
		
		getBaseSteps().clickToControl('Report_Type2', 'AgencyAndDepartmentSpentHoursReportPage')
		getDetails()
		getBaseSteps().verifyControlDisplayed('Table_Data', 'AgencyAndDepartmentSpentHoursReportPage',FailureHandling.CONTINUE_ON_FAILURE)
	}
	
	public static filterByReportTypeAndDate(String reportType="Agency Hours",String startDate,String endDate)
	{
		getBaseSteps().clickToControl('Report_Type1', "AgencyAndDepartmentSpentHoursReportPage")
		selectStartDate(startDate)
		selectEndDate(endDate)
		getDetails()
	}
	public static String CaptureGridData(String rowNumber,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE)
	{
	
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "AgencyAndDepartmentSpentHoursReportPage")
		rowData = Utilities.replaceAllSpacesOfString(rowData)
		println rowData
		return rowData
	}


	public static void ReportDownloadAndMatch(def downloadedReportName, String startDate,String endDate,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE)
	{
		try {
			
	     filterByReportTypeAndDate( startDate, endDate)
		getBaseSteps().verifyControlNotDisplayed("NoData_Grid", "AgencyAndDepartmentSpentHoursReportPage", FailureHandling.STOP_ON_FAILURE)
		getBaseSteps().waitForControlDisplay('Table_Data', "AgencyAndDepartmentSpentHoursReportPage")
		String columnHeader = CaptureGridData('4')
		String firstRow = CaptureGridData('6')
		String fileNameAfterExported = ExcelUtil.VerifyExportExcel('Button_IMG',downloadedReportName,"AgencyAndDepartmentSpentHoursReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(2,filePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(4,filePath, firstRow)
		}
		catch(Exception ex){
			
			ex.printStackTrace()
		}

	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}



	// =================== ASSERT METHODS ZONE ======================
	public static void verifyReportIsLoadedSuccessfully() {
		getBaseSteps().verifyControlDisplayed("Report_div", "AgencyAndDepartmentSpentHoursReportPage")
	}


}

