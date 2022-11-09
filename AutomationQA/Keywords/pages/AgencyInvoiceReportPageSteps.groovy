package pages

import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities



public class AgencyInvoiceReportPageSteps {

	private static url = '/rptAgencyInvoice.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}



	// =================== ACTION METHODS ZONE ======================
	public static void filterWithDate(String startDate, String endDate){
		getBaseSteps().clickToControl("Start_Date_TextBox","AgencyInvoiceReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "AgencyInvoiceReportPage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().moveToControl("End_Date_TextBox", "AgencyInvoiceReportPage")
		getBaseSteps().clickToControl("End_Date_TextBox", "AgencyInvoiceReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "AgencyInvoiceReportPage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickToControl("Get_Details_Button","AgencyInvoiceReportPage")
		getBaseSteps().waitForProgressBarDisappear()


	}

	public static String captureGridData(String rowNumber,String tableControlName, String pageName)
	{
		getBaseSteps().getRowTableCounts(tableControlName, pageName)
		String rowData = getBaseSteps().getRowTableText(tableControlName, rowNumber, pageName)
		return rowData
	}

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {
		String excelContent=ExcelUtil.getFormattedRowData(rowNum, filePath)

		String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )

		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}

	public static void ReportDownloadAndMatch(String downloadedReportName, startDate, endDate)
	{
		AgencyInvoiceReportPageSteps.filterWithDate(startDate, endDate)
		AgencyInvoiceReportPageSteps.getBaseSteps().waitForControlDisplay('Table_Data', "AgencyInvoiceReportPage")
		String columnHeader=AgencyInvoiceReportPageSteps.captureGridData("2","Table_Data", "AgencyInvoiceReportPage")
		columnHeader =Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow =AgencyInvoiceReportPageSteps.captureGridData("4","Table_Data", "AgencyInvoiceReportPage")
		List<String>dataRow=Utilities.getRowDataWithTruncatedDecimal(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"AgencyInvoiceReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		'COMPARING HEADER row Data'
		//AgencyInvoiceReportPageSteps.verifyExcelFileRowContentIsCorrect(6, filePath,columnHeader)
		ExcelUtil.verifyExcelFileContentWithNoSpaceIsCorrect(6, filePath, columnHeader)
		String excelContent = ExcelUtil.getFormattedRowData(8, filePath)
		List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
		'COMPARING the FIRST Data Row Contents'
		AssertSteps.verifyExpectedResult(AgencyInvoiceReportPageSteps.compareLists(dataRow, excelRow))
		
	}


	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);


	}

}
