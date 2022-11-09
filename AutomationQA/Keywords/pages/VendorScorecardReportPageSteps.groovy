package pages


import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil
import utils.ExcelUtil
import utils.Utilities
import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps

public class VendorScorecardReportPageSteps {

	private static url = '/RptVendorScorecard.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	//=================Action method==========================//
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","VendorScorecardReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "VendorScorecardReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate){
		getBaseSteps().clickToControl("End_Date_TextBox","VendorScorecardReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "VendorScorecardReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static getDetails() {

		getBaseSteps().clickToControl('Get_Details_Button', 'VendorScorecardReportPage')
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


		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)
		//excelContent=excelContent.replaceAll("[^a-zA-Z0-9_-]", "")
		//println 'Without Trim :' +excelContent
		//expectedContent = expectedContent.trim().replaceAll("[\\t\\n\\r]+"," ")


		String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}
	//New Code //

	public static void  filterByDate(String startDate, String endDate) {

		selectStartDate(startDate)
		selectEndDate(endDate)
		getDetails()
	}
	public static String captureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "VendorScorecardReportPage")
		return rowData
	}


	public static void ReportDownloadAndMatch(downloadedReportName,String startDate, String endDate, FailureHandling failureHandling=FailureHandling.CONTINUE_ON_FAILURE ) {
      try {
		filterByDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data',"VendorScorecardReportPage" )
	
		String columnHeader=captureGridData('5')
		String firstRow=captureGridData('6')
		List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)

		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"VendorScorecardReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		verifyExcelFileRowContentIsCorrect(3, filePath,columnHeader)
		String excelContent = ExcelUtil.getFormattedRowData(4, filePath)
		List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
		String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL FILE CONTENT is: %s",dataRow, excelContent )
		AssertSteps.verifyExpectedResult(compareLists(dataRow, excelRow),msg,msg)

	}
	
	catch(Exception ex) {
		ex.getMessage()
		
	}

	}
}
