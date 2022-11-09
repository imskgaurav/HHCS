package pages

import core.BaseSteps
import internal.GlobalVariable
import core.ControlFactory
import utils.PlatformUtil
import utils.Utilities
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps

public class BenchmarkingRateByStatePageSteps {

	private static url = '/RptBenchmarkingratebystate.aspx';
	//PageLocators/BenchmarkingRateByStatePage.properties
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	public static void clickButtonImg() {

		getBaseSteps().clickToControl('Button_IMG', 'BenchmarkingRateByStatePage')
	}

	public static void clickExportExcel() {

		getBaseSteps().clickToControl('Export_Button', 'BenchmarkingRateByStatePage')
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
		//println 'Without Trim :' +excelContent

		String msg = String.format("The EXPECTED content is: %s. The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}
	public static String captureGridData(String rowNumber)
	{

		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "BenchmarkingRateByStatePage")
		return rowData
	}

	public static void ReportDownloadAndMatch(downloadedReportName,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {

		String columnHeader=captureGridData('2')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_IMG',downloadedReportName,"BenchmarkingRateByStatePage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(4, filePath, columnHeader)

	}

}
