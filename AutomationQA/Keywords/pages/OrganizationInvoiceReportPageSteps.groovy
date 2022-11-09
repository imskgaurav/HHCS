package pages


import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable
import utils.PlatformUtil
import utils.ExcelUtil
import utils.Utilities

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps


public class OrganizationInvoiceReportPageSteps {


	private static String url ="/rptOrgInvoice.aspx"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	//==========================================Action Method Zone============================//
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","OrganizationInvoiceReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "OrganizationInvoiceReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate){
		getBaseSteps().clickToControl("End_Date_TextBox","OrganizationInvoiceReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "OrganizationInvoiceReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static getDetails() {

		getBaseSteps().clickToControl('Get_Details_Button', 'OrganizationInvoiceReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickImgButton() {
		getBaseSteps().clickToControl('Button_Img', "OrganizationInvoiceReportPage")

	}

	public static void  exportExcel() {

		getBaseSteps().clickToControl("Export_Button", "OrganizationInvoiceReportPage")
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

		String excelContent =ExcelUtil.getFormattedRowData(rowNum, filePath)

		excelContent= Utilities.replaceAllSpacesOfString(excelContent)


		String msg = String.format("The EXPECTED content is: %s. The ACTUAL file content is: %s", expectedContent, excelContent )

		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}

//New Function for Consolidate Report//
	
	public static String CaptureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "OrganizationInvoiceReportPage")
		return rowData
	}

	public static void filterByDate(String startDate, String endDate) {
	selectStartDate(startDate)
	
	selectEndDate(endDate)
	
	getDetails()
}

public static void ReportDownloadAndMatch(def downloadedReportName, String startDate,String endDate, FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE)
{
	filterByDate(startDate, endDate)
	//getBaseSteps().verifyControlDisplayed('Table_Data', "OrganizationInvoiceReportPage")
	getBaseSteps().waitForControlDisplay('Table_Data', "OrganizationInvoiceReportPage")
	String columnHeader =CaptureGridData('2')
	columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
	
	String firstRow = CaptureGridData('4')
	List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
	String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"OrganizationInvoiceReportPage")
	String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
	verifyExcelFileRowContentIsCorrect(4, filePath, columnHeader)
	String excelContent=ExcelUtil.getFormattedRowData(6, filePath)
	List<String> excelRow=Utilities.getRowDataWithTruncatedDecimal(excelContent)
	//COMPARING List value of RowData with Excel Data//
	'COMPARING the FIRST  Data ROW contents'
	AssertSteps.verifyExpectedResult(OrganizationInvoiceReportPageSteps.compareLists(dataRow, excelRow))
	
}
}
