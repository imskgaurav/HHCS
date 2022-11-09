package pages

import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil
import utils.ExcelUtil
import pages.FirstChoiceWorkingDocumentReportPageSteps
import utils.Utilities
import core.AssertSteps



public class FirstChoiceWorkingDocumentReportPageSteps {
	private static url = 'RptFirstChoiceDocumentReport.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	
	
	
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","FirstChoiceWorkingDocumentReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "FirstChoiceWorkingDocumentReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().clickToControl("End_Date_TextBox", "FirstChoiceWorkingDocumentReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "FirstChoiceWorkingDocumentReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickGetDetails() {
		getBaseSteps().clickToControl("GetDetails_Button", "FirstChoiceWorkingDocumentReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	
	public static String captureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "FirstChoiceWorkingDocumentReportPage")
		return rowData
	}
	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {
		//String excelContent = XLReader.getFullRowText(rowNum, filePath,sheetIndex)
		String excelContent=ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent= excelContent.replaceAll("\\s+", "")

		String msg = String.format("The expected content is: %s.%n The excel file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}
	public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate) {
		
				selectStartDate(startDate)
				selectEndDate(endDate)
				getBaseSteps().clickToControl("GetDetails_Button", "FirstChoiceWorkingDocumentReportPage")
				getBaseSteps().waitForProgressBarDisappear()
				getBaseSteps().waitForControlDisplay('Table_Data', "FirstChoiceWorkingDocumentReportPage")
				String columnHeader=captureGridData('5')
				columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
				String firstRow=captureGridData('6')
				firstRow= Utilities.replaceAllSpacesOfString(firstRow)
				
				String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"FirstChoiceWorkingDocumentReportPage")
				
				String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
				
				verifyExcelFileRowContentIsCorrect(4, filePath, columnHeader)
				verifyExcelFileRowContentIsCorrect(5, filePath, firstRow)
			}
		
}
