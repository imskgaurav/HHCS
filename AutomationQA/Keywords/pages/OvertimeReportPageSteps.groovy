package pages



import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable
import utils.PlatformUtil
import utils.Utilities
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps

public class OvertimeReportPageSteps {
	private static String url ="/rptOvertimebyAgency.aspx"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","OvertimeReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "OvertimeReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate){
		getBaseSteps().clickToControl("End_Date_TextBox","OvertimeReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "OvertimeReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static getDetails() {

		getBaseSteps().clickToControl("Get_Details_Button", "OvertimeReportPage")
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


		String excelContent =ExcelUtil.getFormattedRowData(rowNum, filePath, sheetIndex)

		excelContent= Utilities.replaceAllSpacesOfString(excelContent)
		String msg = String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )
		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}

	//
	//New Function
	public static String captureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "OvertimeReportPage")
		return rowData
	}

	//
     public static filterByDate(String startDate, String endDate) {
		 
		 selectStartDate(startDate)
		 selectEndDate(endDate)
		 getDetails()
	 }

    public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
		filterByDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', 'OvertimeReportPage')
		String columnHeader=captureGridData('2')
		columnHeader = Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('3')
		List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"OvertimeReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(7, filePath,columnHeader)
		String excelContent = ExcelUtil.getFormattedRowData(8, filePath)
		List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
		String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL FILE CONTENT is: %s",columnHeader, excelContent )
		'COMPARING the FIRST Data Row Contents'
		AssertSteps.verifyExpectedResult(compareLists(dataRow, excelRow),msg,msg)
	}
}
