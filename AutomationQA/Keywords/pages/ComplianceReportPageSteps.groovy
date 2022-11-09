package pages

import core.BaseSteps
import core.AssertSteps
import core.ControlFactory
import internal.GlobalVariable
import utils.PlatformUtil
import utils.ExcelUtil
import utils.Utilities


public class ComplianceReportPageSteps{

	private static url = '/Candidatecreation.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","ComplianceReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "ComplianceReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().clickToControl("End_Date_TextBox", "ComplianceReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "ComplianceReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickGetDetails() {
		getBaseSteps().clickToControl("GetDetails_Button", "ComplianceReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickButtonImg() {

		getBaseSteps().clickToControl("Button_Img", "ComplianceReportPage")
		getBaseSteps().waitForControlDisplay('Export_Excel', "ComplianceReportPage")

	}

	public static void clickExport() {


		getBaseSteps().clickToControl("Export_Excel", "ComplianceReportPage")
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


		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent = Utilities.replaceAllSpacesOfString(excelContent)

		String msg = String.format("The expected content is: %s.%n The excel file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}


	public static void filterByDate(String startDate, String endDate) {

		selectStartDate(startDate)
		selectEndDate(endDate)
		clickGetDetails()
	}

	public static String captureGridData(String rowNumber)
	{
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "ComplianceReportPage")
		return rowData

	}

	public static void verifyTableIsLoaded() {

		getBaseSteps().verifyControlDisplayed('Table_Data', "ComplianceReportPage")


	}

	public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate) {

		filterByDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', "ComplianceReportPage")
		String columnHeader=captureGridData('2')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('3')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName, "ComplianceReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(8, filePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(9, filePath, firstRow)
	}


	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
