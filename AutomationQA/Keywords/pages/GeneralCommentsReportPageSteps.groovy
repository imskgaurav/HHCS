package pages



import internal.GlobalVariable
import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil
import utils.Utilities
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps

public class GeneralCommentsReportPageSteps{

	private static url = '/RptGeneralNotes.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================

	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","GeneralCommentsReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "GeneralCommentsReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate){
		getBaseSteps().clickToControl("End_Date_TextBox","GeneralCommentsReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "GeneralCommentsReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void showDetails() {

		getBaseSteps().clickToControl('Show_Details_Button', 'GeneralCommentsReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickImgButton() {

		getBaseSteps().clickToControl('Button_Img', 'GeneralCommentsReportPage')
		getBaseSteps().waitForProgressBarDisappear()



	}

	public static void exportExcel() {

		getBaseSteps().clickToControl('Excel_Button', 'GeneralCommentsReportPage')
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

		String excelContent =ExcelUtil.getFormattedRowData(rowNum, filePath, sheetIndex)
		excelContent = Utilities.replaceAllSpacesOfString(excelContent)
		String msg = String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}

	//New Function for Combined Execution of Test cases //
	public static String CaptureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "GeneralCommentsReportPage")
		return rowData
	}

	public static void FilterByDate(String startDate,String endDate)
	{
		selectStartDate(startDate)
		selectEndDate(endDate)
		showDetails()
	}


	public static void ReportDownloadAndMatch(def downloadedReportName, String startDate,String endDate,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
        try {
		FilterByDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', "GeneralCommentsReportPage")
		String columnHeader = CaptureGridData('4')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow = CaptureGridData('5')
		firstRow=Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"GeneralCommentsReportPage")
		String excelFilePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(2,excelFilePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(3,excelFilePath, firstRow)
		
        }
		catch(Exception ex) {
			
			
			ex.printStackTrace()
		}


	}
	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}

