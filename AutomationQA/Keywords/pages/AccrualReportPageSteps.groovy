package pages



import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil
import utils.ExcelUtil
import core.AssertSteps
import utils.Utilities


public class AccrualReportPageSteps {

	private static url = '/Rptaccural.aspx'

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory())
	}

	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","AccrualReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "AccrualReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().clickToControl("End_Date_TextBox", "AccrualReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "AccrualReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static getDetails() {

		getBaseSteps().clickToControl("Get_Details", "AccrualReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void clickImgButton() {

		getBaseSteps().clickToControl('Button_Img', "AccrualReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void exportExcel() {

		getBaseSteps().clickToControl('Excel_Button', "AccrualReportPage")
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
		//String excelContent = XLReader.getFullRowText(rowNum, filePath,sheetIndex)
		String excelContent=ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent= excelContent.replaceAll("\\s+", "")

		String msg = String.format("The expected content is: %s.%n The excel file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}
	//New Code//
	public static void filterByDate(String startDate, endDate){
		getBaseSteps().selectCheckboxByContainedTextInComboBox('Select_Candidate_Status', 'Check All', 'AccrualReportPage')
		selectStartDate(startDate)
		selectEndDate(endDate)
		getBaseSteps().clickToControlByJS('Select_Job_Status', 'AccrualReportPage')
		Thread.sleep(200)
		getBaseSteps().clickLabelwithText('Dropown_ChkBox', 'Select all', 'AccrualReportPage')
		getBaseSteps().clickToControlByJS('DateRange_Select', 'AccrualReportPage')
		getBaseSteps().clickLabelwithText('Select_Date_Range1', 'Time sheet date range', 'AccrualReportPage')
		getDetails()


	}

	public static String captureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber,"AccrualReportPage")
		return rowData
	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}
	public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate) {
		try {
			filterByDate(startDate, endDate)
			getBaseSteps().verifyControlEnabled('Button_Img', "AccrualReportPage")
			getBaseSteps().verifyControlNotDisplayed("NoData_Grid", "AccrualReportPage")
			getBaseSteps().waitForControlDisplay('Table_Data', "AccrualReportPage")
			String columnHeader=captureGridData('4')
			columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
			String firstRow=captureGridData('5')
			List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
			String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"AccrualReportPage")
			String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
			verifyExcelFileRowContentIsCorrect(2, filePath, columnHeader)
			//verifyExcelFileRowContentIsCorrect(3, filePath, firstRow)
			String excelContent=ExcelUtil.getFormattedRowData(3, filePath)
			List<String> excelRow=Utilities.getRowDataWithTruncatedDecimal(excelContent)
			String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL FILE CONTENT is: %s",dataRow, excelRow )
			AssertSteps.verifyExpectedResult(compareLists(dataRow, excelRow),msg,msg)
		}
		catch(Exception ex){

			ex.printStackTrace()
		}


	}
	


}
