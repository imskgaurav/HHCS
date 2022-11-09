package pages


import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil
import utils.ExcelUtil
import core.AssertSteps
public class ActivityLogReportPageSteps {

	private static url = 'ActivityLog.aspx'

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory())
	}

	//Select ScreenName //
	public static selectSceernName(String screenName) {
		getBaseSteps().clickToControl("select_screenName","ActivityLogPage")
		getBaseSteps().clickToControl("select_screenName","ActivityLogPage")
		getBaseSteps().selectItemByTextInComboBox("Agency_Name", screenName, "ActivityLogPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","ActivityLogPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "ActivityLogPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().clickToControl("End_Date_TextBox", "ActivityLogPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "ActivityLogPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static getDetails() {

		getBaseSteps().clickToControl("Get_Details", "ActivityLogPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static FilterByScreeNameAndDate(String screenName,String startDate,String endDate)
	{
		selectSceernName (screenName)
		selectStartDate(startDate)
		selectEndDate(endDate)
		getDetails()
	}

	public static String CaptureGridData(String rowNumber)
	{
		getBaseSteps().getRowTableCounts('Table_Data', 'ActivityLogPage')
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, 'ActivityLogPage')
		return rowData
	}

	public static String verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {

		String fileNameAfterExported = String.format("%s.xlsx",prefixFile)

		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		return fileNameAfterExported
	}

	public static void verifyExcelFileContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent){

		//String excelContent = ExcelUtil.getRowContent(4,filePath, sheetIndex).trim().replaceAll("[\\t\\n\\r\\s]+"," ");

		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)

		String msg = String.format("The expected content is: %s.%n The excel file content is: %s", expectedContent, excelContent )

		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}

	public static void ReportDownloadAndMatch(def downloadedReportName, String startDate,String endDate)
	{
		try{
		ActivityLogReportPageSteps.FilterByScreeNameAndDate('Agency Details',startDate,endDate)
		//ActivityLogReportPageSteps.getBaseSteps().verifyControlDisplayed('Table_Data', 'ActivityLogPage')
		getBaseSteps().verifyControlNotDisplayed("NoData_Grid", "ActivityLogPage")
		getBaseSteps().waitForControlDisplay('Table_Data', 'ActivityLogPage')
		String columnHeader = ActivityLogReportPageSteps.CaptureGridData('4')
		String firstRow = ActivityLogReportPageSteps.CaptureGridData('5')
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,'ActivityLogPage')
		String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		ActivityLogReportPageSteps.verifyExcelFileContentIsCorrect(2,excelFilePath, columnHeader)
		ActivityLogReportPageSteps.verifyExcelFileContentIsCorrect(3,excelFilePath, firstRow)
	}
	catch(Exception ex){
			
			ex.printStackTrace()
		}

	}
}

