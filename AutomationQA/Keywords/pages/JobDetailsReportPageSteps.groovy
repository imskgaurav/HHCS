package pages


import core.BaseSteps
import core.ControlFactory
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities
import core.AssertSteps

public class JobDetailsReportPageSteps{

	private static String url ="/RptJobDetails"
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================


	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","JobDetailsPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "JobDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void selectEndDate(String endDate){
		getBaseSteps().clickToControl("End_Date_TextBox","JobDetailsPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "JobDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static showDetails() {

		getBaseSteps().clickToControl("ShowDeatils_Button", "JobDetailsPage")
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


		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)

		excelContent= Utilities.replaceAllSpacesOfString(excelContent)

		String msg = String.format("The expected content is: %s.%n The excel file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}
	//New Funtion//

	public static void filterByDate(String startDate,endDate) {

		selectStartDate( startDate)
		selectEndDate(endDate)
		showDetails()


	}
	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "JobDetailsPage")
		return rowData
	}

	public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate) {
		filterByDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data',"JobDetailsPage" )
		String columnHeader=captureGridData('2')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('3')
		firstRow= Utilities.replaceAllSpacesOfString(columnHeader)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"JobDetailsPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(9, filePath, columnHeader)
		//verifyExcelFileRowContentIsCorrect(10, filePath, firstRow)
	}

	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}

