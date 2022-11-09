package pages

import core.BaseSteps
import internal.GlobalVariable
import core.ControlFactory
import utils.PlatformUtil
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
public class MissingKronosIDReportPageSteps {
	private static url = '/rptMissingCandidateDetails.aspx'

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	public static void getDetails() {
		getBaseSteps().clickToControl('GetDetails_Button', 'MissingKronosIDReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickButtonImg() {
		getBaseSteps().clickToControl('Button_Img', 'MissingKronosIDReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void exportExcel() {

		getBaseSteps().clickToControl('Export_Excel', 'MissingKronosIDReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void verifyTableIsLoaded(FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {

		//getBaseSteps().verifyControlEnabled('Button_Img', 'MissingKronosIDReportPage')

		getBaseSteps().waitForControlDisplay('Table_Data', 'MissingKronosIDReportPage')
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
		//excelContent= excelContent.replaceAll("\\s", "")
		//println 'Without Trim :' +excelContent

		String msg = String.format("The expected content is: %s. The excel file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}

	//New  Function //

	public static void filterByDefaultSelection() {

		getDetails()

	}

	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "MissingKronosIDReportPage")
		return rowData
	}
	public static void ReportDownloadAndMatch(def downloadedReportName,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
		getDetails()
		verifyTableIsLoaded()
		String columnHeader =captureGridData('2')
		String firstRow =captureGridData('3')

		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"MissingKronosIDReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(6,filePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(7,filePath, firstRow)
	}

	public static void filterReportByExistingClockId(){

		getBaseSteps().clickToControl("ExistingClockId_RadioButton", "MissingKronosIDReportPage")
		getDetails()
		getBaseSteps().verifyControlEnabled('Button_Img', "MissingKronosIDReportPage")
		//Thread.sleep(2000)
		getBaseSteps().waitForControlDisplay('Table_Data2', "MissingKronosIDReportPage")
	}
}
