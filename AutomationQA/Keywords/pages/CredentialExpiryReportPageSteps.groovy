package pages

import utils.PlatformUtil
import core.BaseSteps
import internal.GlobalVariable
import core.ControlFactory

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.ExcelUtil
import utils.Utilities


public class CredentialExpiryReportPageSteps{
	private static String url ="/RptCredentialExpiry.aspx"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================
	//Select Date//

	public static void selectStartDate(String startDate) {
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "CredentialExpiryReportPage")

	}

	public static void selectEndDate(String endDate){

		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "CredentialExpiryReportPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static showDetails() {

		getBaseSteps().clickToControl("Show_Details", "CredentialExpiryReportPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	//Creating Filter Function //
	public static void FilterByAgencyAndDate(String startDate,String endDate)
	{
		getBaseSteps().selectItemByContainedTextInComboBox('Agency', 'Check All', 'CredentialExpiryReportPage')
		selectStartDate(startDate)
		selectEndDate(endDate)
		showDetails()
	}

	////
	public static String CaptureGridData(String rowNumber,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, 'CredentialExpiryReportPage')
		return rowData
	}

	public static void ReportDownloadAndMatch(def downloadedReportName, String startDate,String endDate,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
		try {
			//getBaseSteps().clickToControl("Agency", "CredentialExpiryReportPage")
			//CredentialExpiryReportPageSteps.getBaseSteps().selectCheckboxByContainedTextInComboBox("CheckAllOption_Agency", "Check All", "CredentialExpiryReportPage")

			selectStartDate(startDate)
			selectEndDate(endDate)

			getBaseSteps().clickToControl("Show_Details", "CredentialExpiryReportPage")
			getBaseSteps().waitForProgressBarDisappear()
			getBaseSteps().waitForControlDisplay('Table_Data', 'CredentialExpiryReportPage')
			String columnHeader = CaptureGridData('2')
			columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
			String firstRow = CaptureGridData('3')
			firstRow= Utilities.replaceAllSpacesOfString(firstRow)
			getBaseSteps().waitForControlDisplay('Export_Button', 'CredentialExpiryReportPage')
			//getBaseSteps().clickToControl('Export_Button', 'CredentialExpiryReportPage')
			String fileNameAfterExported = ExcelUtil.ExportExcel('Button_IMG',downloadedReportName, 'CredentialExpiryReportPage')
			String filePath=PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
			verifyExcelFileRowContentIsCorrect(6, filePath,columnHeader)
			verifyExcelFileRowContentIsCorrect(7, filePath,firstRow)

		}
		catch(Exception ex){

			ex.printStackTrace()
		}

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


		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath,sheetIndex)
		excelContent= Utilities.replaceAllSpacesOfString(excelContent)
		//expectedContent = expectedContent.trim().replaceAll("[\\t\\n\\r]+"," ")
		String msg = String.format("The EXPECTED content is: %s. The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}

	// Tech Enable Client, For Agency User//
	public static void credreportdownloadAndMatchExcelData(String downloadedReportName, startDate, endDate) {

		CredentialExpiryReportPageSteps.getBaseSteps().waitForControlDisplay("Start_Date_TextBox", "CredentialExpiryReportPage")
		CredentialExpiryReportPageSteps.getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "CredentialExpiryReportPage")
		CredentialExpiryReportPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CredentialExpiryReportPageSteps.getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "CredentialExpiryReportPage")
		CredentialExpiryReportPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CredentialExpiryReportPageSteps.showDetails()
		getBaseSteps().verifyControlNotDisplayed("NoData_Grid", "CredentialExpiryReportPage")
		CredentialExpiryReportPageSteps.getBaseSteps().waitForControlDisplay('Table_Data', 'CredentialExpiryReportPage')
		String columnHeader = CredentialExpiryReportPageSteps.CaptureGridData('2')
		columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow = CredentialExpiryReportPageSteps.CaptureGridData('3')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_IMG',downloadedReportName, 'CredentialExpiryReportPage')
		String filePath=PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		CredentialExpiryReportPageSteps.verifyExcelFileRowContentIsCorrect(5, filePath,columnHeader)
		CredentialExpiryReportPageSteps.verifyExcelFileRowContentIsCorrect(6, filePath,firstRow)



	}

}




