package pages

import core.BaseSteps
import core.ControlFactory
import utils.ExcelUtil
import utils.PlatformUtil
import com.kms.katalon.core.model.FailureHandling
import core.AssertSteps
import utils.Utilities

public class JobEventPageSteps {

	private static String url ="/rptJobEvent.aspx"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","JobEventPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "JobEventPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void selectEndDate(String endDate){

		getBaseSteps().clickToControl("End_Date_TextBox","JobEventPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "JobEventPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static void  getDetails() {

		getBaseSteps().clickToControl("GetDetails_Button", "JobEventPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void  clickButtonImg() {

		getBaseSteps().clickToControl("Button_Img", "JobEventPage")
		getBaseSteps().waitForProgressBarDisappear()

	}


	public static void  exportExcel() {

		getBaseSteps().clickToControl("Export_Excel", "JobEventPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {

		String fileNameAfterExported = String.format("%s.xlsx",prefixFile)
		println 'chk::'+fileNameAfterExported


		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		return fileNameAfterExported
	}

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {
		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent= Utilities.replaceAllSpacesOfString(excelContent)
		//println 'Without Trim :' +excelContent

		String msg = String.format("The expected content is: %s.%n The excel file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}
	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}

	//Adding New Function//

	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "JobEventPage")
		return rowData
	}



	public static void filterByDate(String startDate,endDate) {

		selectStartDate( startDate)
		selectEndDate(endDate)
		getDetails()


	}


	public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate,FailureHandling  failureHandling=FailureHandling.CONTINUE_ON_FAILURE) {

		try {
			filterByDate(startDate, endDate)
			getBaseSteps().verifyControlNotDisplayed("NoData_Grid", "JobEventPage",failureHandling=FailureHandling.CONTINUE_ON_FAILURE)
			String columnHeader=captureGridData('4')
			columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
			String firstRow=captureGridData('5')
			String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"JobEventPage")
			String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
			List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
			verifyExcelFileRowContentIsCorrect(2, filePath,columnHeader )
			String excelContent = ExcelUtil.getFormattedRowData(3, filePath)
			List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
			AssertSteps.verifyExpectedResult(compareLists(dataRow, excelRow))
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
