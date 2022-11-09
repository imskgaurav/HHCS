package pages



import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps

public class LaborUtilizationReportPageSteps {

	private static String url ="/RptLabourutilization.aspx"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================


	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","LaborUtilizationReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "LaborUtilizationReportPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void selectEndDate(String endDate){
		getBaseSteps().clickToControl("End_Date_TextBox","LaborUtilizationReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "LaborUtilizationReportPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static getDetails() {

		getBaseSteps().clickToControl("Get_Details_Button", "LaborUtilizationReportPage")
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

		String msg = String.format("The EXPECTED content is: %s. The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}

	public static void filterByDate(String startDate, String endDate) {

		selectStartDate(startDate)
		selectEndDate(endDate)
		getDetails()
	}


	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "LaborUtilizationReportPage")
		return rowData
	}

	public static void ReportDownloadAndMatch(def downloadedReportName, String startDate,String endDate, FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE)
	{
		filterByDate(startDate,endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', "LaborUtilizationReportPage")
		String columnHeader =captureGridData('2')
		columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow =captureGridData('3')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"LaborUtilizationReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(12,filePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(13,filePath, firstRow)
	}
}
