package pages
import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable
import utils.PlatformUtil
import utils.Utilities
import utils.ExcelUtil
import core.AssertSteps



public class FillRatioReportPageSteps {
	private static url = '/rptFillRatio.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================
	//FillRatioReportPage.properties
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","FillRatioReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "FillRatioReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().waitForControlClickable("End_Date_TextBox", "FillRatioReportPage")
		getBaseSteps().clickToControl("End_Date_TextBox", "FillRatioReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "FillRatioReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void choosebyDetailsRadioButton() {
		getBaseSteps().clickToControl("By_Agency_RadioButton", "FillRatioReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void filterReportByLocation() {
		getBaseSteps().clickToControl("By_Location_RadioButtton", "FillRatioReportPage")
		getBaseSteps().waitForProgressBarDisappear()
		clickGetDetails()
		//getBaseSteps().verifyControlEnabled("Button_Img","FillRatioReportPage")
		getBaseSteps().waitForControlDisplay("Table_Data", "FillRatioReportPage")


	}

	public static void clickGetDetails() {
		getBaseSteps().clickToControl("GetDetails_Button", "FillRatioReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickButtonImg() {

		getBaseSteps().clickToControl("Button_Img", "FillRatioReportPage")

		getBaseSteps().waitForControlDisplay('Export_Excel', "FillRatioReportPage")

	}

	public static void clickExport() {


		getBaseSteps().clickToControl("Export_Excel", "FillRatioReportPage")
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
		excelContent= Utilities.replaceAllSpacesOfString(excelContent)
		String msg = String.format("The expected content is: %s.%n The excel file content is: %s", expectedContent, excelContent )
		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}

	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "FillRatioReportPage")
		return rowData
	}

	public static void filterByDate(String startDate, String endDate) {

		selectStartDate(startDate)
		selectEndDate(endDate)
		choosebyDetailsRadioButton()
		clickGetDetails()

	}

	public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate) {
		try
		{
			filterByDate(startDate, endDate)
			getBaseSteps().waitForControlDisplay('Table_Data', "FillRatioReportPage")
			String columnHeader=captureGridData('2')
			columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
			String firstRow=captureGridData('3')
			List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
			String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"FillRatioReportPage")
			String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
			'Compare HEADER ROW data'
			verifyExcelFileRowContentIsCorrect(8, filePath, columnHeader)
			String excelContent = ExcelUtil.getFormattedRowData(9, filePath)
			List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
			String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL FILE CONTENT is: %s",dataRow, excelContent )
			AssertSteps.verifyExpectedResult(compareLists(dataRow, excelRow),msg,msg)


		}

		catch(Exception ex) {

			ex.printStackTrace()
		}

	}
}