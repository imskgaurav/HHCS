package pages


import core.BaseSteps
import internal.GlobalVariable
import core.ControlFactory
import utils.DateTimeUtil
import utils.PlatformUtil
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.Utilities


public class FillRatePageSteps {


	private static url = '/RptaccuralFillrate.aspx';


	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","FillRatePage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "FillRatePage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().clickToControl("End_Date_TextBox", "FillRatePage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "FillRatePage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static getDetails() {


		getBaseSteps().clickToControl('Get_Details', 'FillRatePage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static String verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {
		String datetimeDownload = DateTimeUtil.getDateTime("MMddyyyy","EST")
		String localTime= DateTimeUtil.getLocalTimeMinusOneHour()
		println localTime
		String endFileName=datetimeDownload.concat(localTime)
		String fileNameAfterExported = String.format("%s_%s.xlsx",prefixFile,endFileName)
		println ''+fileNameAfterExported
		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		return fileNameAfterExported
	}
	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {


		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath, sheetIndex)
		excelContent = Utilities.replaceAllSpacesOfString(excelContent)
		//expectedContent = expectedContent.trim().replaceAll("[\\t\\n\\r]+"," ")
		String msg = String.format("The expected content is: %s.%n The excel file content is: %s", expectedContent, excelContent )
		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}

	//New Function for Combined Execution of Test cases //
	public static String CaptureGridData(String rowNumber)
	{
		//Storing Row data value in String
		getBaseSteps().waitForControlDisplay('Table_Data', "FillRatePage")
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "FillRatePage")
	    //println rowData
		return rowData
	}

	public static void FilterByDate(String startDate,String endDate)
	{
		selectStartDate(startDate)
		selectEndDate(endDate)
		getDetails()
	}


	public static void ReportDownloadAndMatch(String downloadedReportName, String startDate,String endDate,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
        try {
		FilterByDate(startDate, endDate)
		getBaseSteps().verifyControlEnabled('Button_IMG', "FillRatePage",failureHandling=FailureHandling.CONTINUE_ON_FAILURE)
		//getBaseSteps().waitForControlDisplay('Table_Data', "FillRatePage")
		String columnHeader = CaptureGridData('4')
		columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow = CaptureGridData('6')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.VerifyExportExcelwithTimeStamp('Button_IMG',downloadedReportName, "FillRatePage")
		String filePath=PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(2, filePath,columnHeader)
		verifyExcelFileRowContentIsCorrect(4, filePath,firstRow)
		
        }
		
		catch(Exception ex) {
			
			ex.printStackTrace()		
			
		}

	}

}
