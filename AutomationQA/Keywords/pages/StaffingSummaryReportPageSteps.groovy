package pages

import core.BaseSteps
import internal.GlobalVariable
import core.ControlFactory
import utils.PlatformUtil
import utils.ExcelUtil
import core.AssertSteps
import utils.Utilities


public class StaffingSummaryReportPageSteps {
	private static url = '/RptMedicalCenter.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","StaffingSummaryReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "StaffingSummaryReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().clickToControl("End_Date_TextBox", "StaffingSummaryReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "StaffingSummaryReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static getDetails() {
		getBaseSteps().clickToControl('Show_Details_Button', 'StaffingSummaryReportPage')
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


		//String excelContent = ExcelUtil1.getRowContent(rowNum, filePath,sheetIndex)
		String excelContent = (ExcelUtil.getFormattedRowData(rowNum, filePath, sheetIndex).trim()).replaceAll("[\\t\\n\\r\\s]+"," ");
		excelContent =  Utilities.replaceAllSpacesOfString(excelContent)


		String msg = String.format("The EXPECTED content is: %s. The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}

	//New Function //


	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "StaffingSummaryReportPage")
		return rowData
	}
	public static void filterByDate(String startDate,String endDate) {
		selectStartDate(startDate)

		selectEndDate(endDate)

		getDetails()

	}

	public static void ReportDownloadAndMatch(downloadedReportName, String startDate, String endDate) {

		filterByDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', "StaffingSummaryReportPage")
		String columnHeader=captureGridData('2')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('3')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"StaffingSummaryReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(13, filePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(14, filePath, firstRow)

	}
	//TechEnable Client
	public static void reportDownloadAndMatch_TechEnableServiceApp(downloadedReportName, String startDate, String endDate) {
		filterByDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', "StaffingSummaryReportPage")
		String columnHeader=captureGridData('2')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('3')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"StaffingSummaryReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(14, filePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(15, filePath, firstRow)



	}


}

