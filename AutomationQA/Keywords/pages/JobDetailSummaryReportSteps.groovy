package pages


import core.BaseSteps
import core.ControlFactory
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities
import core.AssertSteps
public class JobDetailSummaryReportSteps {

	private static String url ="/RptJobDetailSummary.aspx"
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================//

	public static void clickGetDetails() {
		getBaseSteps().clickToControl("GetDetails_Button", "JobDetailsSummaryPage")
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
	
	public static  void filterByDefaultSelection() {
		clickGetDetails()
		
	}
	public static String captureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, 'JobDetailsSummaryPage')
		return rowData
	}
	
	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {
		String excelContent=ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent=Utilities.replaceAllSpacesOfString(excelContent)

		String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )

		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}
	public static void reportDownloadAndMatch(String downloadedReportName) {
		try {
		filterByDefaultSelection()
		//JobDetailSummaryReportSteps.getBaseSteps().verifyControlEnabled('Button_Img', 'JobDetailsSummaryPage')
		JobDetailSummaryReportSteps.getBaseSteps().waitForControlDisplay("Table_Data", "JobDetailsSummaryPage")
		String columnHeader = captureGridData('2')
		columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow = captureGridData('3')
		firstRow=Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportToExcelInNewTab('Button_Img',downloadedReportName,"JobDetailsSummaryPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(6, filePath,columnHeader)
		//verifyExcelFileRowContentIsCorrect(7, filePath,columnHeader)
	}
	catch(Exception ex) {
		ex.printStackTrace()
	
	}
}
}