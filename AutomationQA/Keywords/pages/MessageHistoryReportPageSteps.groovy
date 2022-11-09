package pages


import core.BaseSteps
import internal.GlobalVariable
import core.ControlFactory
import utils.DateTimeUtil
import utils.PlatformUtil
import utils.ExcelUtil
import utils.Utilities

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps



public class MessageHistoryReportPageSteps {

	private static url = '/rptMessageHistory.aspx';


	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","MessageHistoryReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "MessageHistoryReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String startDate) {
		def endDate= DateTimeUtil.nextDateTimeFromAGivenDate(10,startDate)
		println endDate
		getBaseSteps().clickToControl("End_Date_TextBox", "MessageHistoryReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "MessageHistoryReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static getDetails() {


		getBaseSteps().clickToControl('Get_Details_Button', 'MessageHistoryReportPage')
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
		//String excelContent = XLReader.getFullRowText(rowNum, filePath,sheetIndex)
		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath, sheetIndex)

		excelContent =Utilities.replaceAllSpacesOfString(excelContent)
		String msg = String.format("The EXPECTED content is: %s..%n The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}

	//New Fucntion for Consolidated Report//

	public static void filterByDate(String startDate, String endDate) {

		selectStartDate(startDate)
		selectEndDate(startDate)
		getDetails()
	}

	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "MessageHistoryReportPage")
		return rowData
	}
	public static void ReportDownloadAndMatch(def downloadedReportName, String startDate,String endDate,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE)
	{
		filterByDate(startDate,endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', "MessageHistoryReportPage")
		String columnHeader =captureGridData('2')
		columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow =captureGridData('3')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		println firstRow
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"MessageHistoryReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(6,filePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(7,filePath, firstRow)
	}
	
	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
