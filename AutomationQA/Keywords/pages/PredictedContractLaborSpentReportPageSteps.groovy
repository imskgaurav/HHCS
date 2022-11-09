package pages

import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil
import utils.Utilities
import utils.DateTimeUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.ExcelUtil
import pages.PredictedContractLaborSpentReportPageSteps

public class PredictedContractLaborSpentReportPageSteps{
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	//=================Action method==========================//
	public static void selectStartDate(String startDate) {
		getBaseSteps().clickToControl("Start_Date_TextBox","PredictedContractLaborSpentReportPage")
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "PredictedContractLaborSpentReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(){
		def startDate=DateTimeUtil.getCurrentDate()
		def endDate= DateTimeUtil.nextDateTimeFromAGivenDate(15,startDate)
		println endDate
		getBaseSteps().clickToControl("End_Date_TextBox","PredictedContractLaborSpentReportPage")
		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "PredictedContractLaborSpentReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static getDetails() {

		getBaseSteps().clickToControl('GetDetails_Button', 'PredictedContractLaborSpentReportPage')
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
		excelContent= Utilities.replaceAllSpacesOfString(excelContent)
		//println 'WITH Trim :' +excelContent

		String msg = String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}
	//New Function //

	public static void filterByDate(FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
		//selectStartDate(startDate)
		selectEndDate()
		getDetails()

	}

	public static String captureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "PredictedContractLaborSpentReportPage")
		return rowData
	}

	public static void ReportDownloadAndMatch(downloadedReportName,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
		try {
			String msg= ""
			filterByDate()
			getBaseSteps().verifyControlNotDisplayed("NoData_Grid", "PredictedContractLaborSpentReportPage",failureHandling = FailureHandling.STOP_ON_FAILURE)
			getBaseSteps().waitForControlDisplay('Table_Data', 'PredictedContractLaborSpentReportPage')
			String columnHeader=captureGridData('2')
			String firstRow=captureGridData('4')
			columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
			firstRow=Utilities.replaceAllSpacesOfString(firstRow)
			String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,'PredictedContractLaborSpentReportPage')
			String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
			verifyExcelFileRowContentIsCorrect(4, filePath,columnHeader)
			verifyExcelFileRowContentIsCorrect(6, filePath,firstRow)
		}
		catch(Exception ex) {
			ex.printStackTrace()
		}


	}
}
