package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

import com.kms.katalon.core.model.FailureHandling

import core.ControlFactory
import core.BaseControl
import core.BaseSteps
import utils.PlatformUtil
import utils.ExcelUtil
import core.AssertSteps
import utils.Utilities
public class OverallStatusReportPageSteps {

	private static url = '/RptOveralStats.aspx';

	private static String errMsg="An error has occurred during report processing. (rsProcessingAborted) ";

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	public static void verifyTableDataLoaded() {

		getBaseSteps().verifyControlDisplayed('Table_Data', 'OverallStatusReportPage',errMsg )
	}

	public static getDetails() {

		getBaseSteps().clickToControl("Get_Details_Button", "OverallStatusReportPage")
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
		String excelContent=ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent= Utilities.replaceAllSpacesOfString(excelContent)

		String msg = String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
	}

	//nEW Function for Integration //
	public static void filterByDefaultSelection(FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
		getBaseSteps().clickToControl('Expand_CheckBox', 'OverallStatusReportPage')
		getDetails()
	}


	public static String captureGridData(String rowNumber,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "OverallStatusReportPage")
		return rowData
	}

	public static void ReportDownloadAndMatch(downloadedReportName, FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
		filterByDefaultSelection()
		getBaseSteps().waitForControlDisplay('Table_Data', "OverallStatusReportPage")
		String columnHeader=captureGridData('2')
		columnHeader = Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('5')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"OverallStatusReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(7, filePath,columnHeader)
		verifyExcelFileRowContentIsCorrect(10, filePath,firstRow)
	}
}
