package pages



import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.ExcelUtil
import utils.Utilities

public class YTDSummaryReportPageSteps{

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	//=================Action method==========================//


	public static void showDetails() {

		getBaseSteps().clickToControl('ShowDetails_Button', 'YTDSummaryReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void downLaodExcel() {

		getBaseSteps().clickToControl('DownLoad_Button', 'YTDSummaryReportPage')
		getBaseSteps().waitForProgressBarDisappear()

	}


	public static verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {

		String fileNameAfterExported = String.format("%s.xls",prefixFile)


		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		//getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		//println fileNameAfterExported
		return fileNameAfterExported
	}


	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {
		//String excelContent = XLReader.getFullRowText(rowNum, filePath,sheetIndex)
		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)
		//println 'Without Trim :' +excelContent
		String msg = String.format("The expected content is: %s. The excel file content is: %s", expectedContent, excelContent )

		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}

	//new code
	public static String captureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "YTDSummaryReportPage")
		return rowData
	}
	public static void ReportDownloadAndMatch(downloadedReportName, FailureHandling failureHandling=FailureHandling.CONTINUE_ON_FAILURE ) {
        try {
		showDetails()
		downLaodExcel()
		getBaseSteps().waitForControlDisplay('Table_Data', "YTDSummaryReportPage")
		String columnHeader=captureGridData('4')
		String firstRow=captureGridData('5')
		List<String> headerRow = Utilities.getRowDataWithTruncatedDecimal(columnHeader)
		List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
		String fileNameAfterExported = YTDSummaryReportPageSteps.verifyFileIsDownloadedSuccessfully(downloadedReportName)
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		String headerRowExcel = ExcelUtil.getFormattedRowData(2, filePath)
		String rowDataExcel = ExcelUtil.getFormattedRowData(3, filePath)
		List<String> excelHeader = Utilities.getRowDataWithTruncatedDecimal(headerRowExcel)
		List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(rowDataExcel)
		'Comparing the Header Row'
		AssertSteps.verifyExpectedResult(compareLists(headerRow, excelHeader))
		'Comparing the First Row data'
		AssertSteps.verifyExpectedResult(compareLists(dataRow, excelRow))
	}

	catch(Exception ex) {
		ex.getMessage()
		
	}

}
}
