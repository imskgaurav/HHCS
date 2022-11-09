package pages


import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable
import utils.PlatformUtil
import utils.Utilities
import utils.DateTimeUtil
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps

public class MissingCredentialsReportPageSteps {

	private static url = '//rptOrgMissingCredentials.aspx';
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}


	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","MissingCredentialsReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "MissingCredentialsReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().clickToControl("End_Date_TextBox", "MissingCredentialsReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "MissingCredentialsReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static getDetails() {


		getBaseSteps().clickToControl('Get_Details_Button', 'MissingCredentialsReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {
		String datetimeDownload = DateTimeUtil.getDateTime("MMddyyyy","EST")
		String localTime= DateTimeUtil.getLocalTimeMinusOneHour()
		println localTime
		String endFileName=datetimeDownload.concat(localTime)
		String fileNameAfterExported = String.format("%s_%s.xlsx",prefixFile,endFileName)
		println 'chk::'+fileNameAfterExported


		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		return fileNameAfterExported
	}

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {
		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath,sheetIndex)
		excelContent = Utilities.replaceAllSpacesOfString(excelContent)
		String msg = String.format("The expected content is: %s..%nThe excel file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
	}

	//New Function//
	public static void filterByDate(String startDate, String endDate) {

		selectStartDate(startDate)
		selectEndDate(endDate)
		getDetails()
	}

	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "MissingCredentialsReportPage")
		return rowData
	}
	public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
		filterByDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', "MissingCredentialsReportPage")
		String columnHeader=captureGridData('5')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('6')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.VerifyExportExcelwithTimeStamp('Button_Img',downloadedReportName,"MissingCredentialsReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(3, filePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(4, filePath, firstRow)
	}
}
