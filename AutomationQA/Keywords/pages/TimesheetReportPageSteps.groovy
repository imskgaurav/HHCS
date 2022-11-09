package pages

import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.Utilities

import internal.GlobalVariable

public class TimesheetReportPageSteps {
	private static url = '/TimeSheetReport.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	//=================Action method==========================//
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","TimesheetReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "TimesheetReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate){
		getBaseSteps().clearTextInControl("End_Date_TextBox","TimesheetReportPage")
		getBaseSteps().clickToControl("End_Date_TextBox","TimesheetReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "TimesheetReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static getDetails() {

		getBaseSteps().clickToControl('Get_Details_Button', 'TimesheetReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {

		String fileNameAfterExported = String.format("%s.xlsx",prefixFile)

		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		println fileNameAfterExported
		return fileNameAfterExported
	}

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {


		//String excelContent = ExcelUtil1.getRowContent(rowNum, filePath,sheetIndex)
		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent=Utilities.replaceAllSpacesOfString(excelContent)

		//expectedContent = expectedContent.trim().replaceAll("[\\t\\n\\r]+"," ")


		String msg = String.format("The EXPECTED content is: %s.%nThe ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
	}

	//New Code //
	public static void filterByDate(String startDate, String endDate,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {

		selectStartDate(startDate)
		selectEndDate(endDate)
		getDetails()
		
	}

	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "TimesheetReportPage")
		return rowData
	}
	
	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}

	public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate) {
		selectStartDate(startDate)
		getDetails()
		getBaseSteps().waitForControlDisplay('Table_Data', "TimesheetReportPage")
		String columnHeader=captureGridData('5')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('6')
		List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"TimesheetReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(3, filePath, columnHeader)
		String excelContent = ExcelUtil.getFormattedRowData(4, filePath)
		List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
		String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL FILE CONTENT is: %s",dataRow, excelRow )
		'COMPARING the FIRST Data Row Contents'
		AssertSteps.verifyExpectedResult(compareLists(dataRow, excelRow),msg,msg)
	}



}
