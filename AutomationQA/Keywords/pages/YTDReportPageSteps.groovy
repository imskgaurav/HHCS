package pages



import core.BaseSteps
import core.ControlFactory

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.*

public class YTDReportPageSteps {
	String url ='/RptYTDLocation.aspx'
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	//=================Action method==========================//

	public static void getDetails() {

		getBaseSteps().clickToControl('GetDetails_Button', 'YTDReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void ClickByLocationRadioButton() {

		getBaseSteps().clickToControl('ByLocation_RadioButton', 'YTDReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}


	public static void ClickByMonthRadioButton() {

		getBaseSteps().clickToControl('ByMonth_RadioButton', 'YTDReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}


	public static void  clickButtonImg() {

		getBaseSteps().clickToControl('Button_Img1', "YTDReportPage")
		getBaseSteps().waitForProgressBarDisappear()

		// getBaseSteps().waitForControlDisplay('Export_Excel', "YTDReportPage")
	}
	public static void  exportExcel() {

		getBaseSteps().clickToControl("Export_Excel", "YTDReportPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {

		String fileNameAfterExported = String.format("%s.xlsx",prefixFile)
		println 'chk::'+fileNameAfterExported


		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		return fileNameAfterExported
	}

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {
		
		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent= Utilities.replaceAllSpacesOfString(excelContent)

		String msg = String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}

	public static  boolean compareLists(final List tableRow,final List excelRow) {
		return tableRow.equals(excelRow);
	}


	//Filter Function//
	public static void filterByLocation() {

		ClickByLocationRadioButton()
		getDetails()
	}

	public static void filterReportByMonth() {

		ClickByMonthRadioButton()
		getDetails()

		getBaseSteps().verifyControlEnabled('Button_Img1', "YTDReportPage")
		getBaseSteps().waitForControlDisplay("Table_Data2", "YTDReportPage")


		// ()
	}

	public static String captureGridData(String rowNumber)
	{

		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "YTDReportPage")
		return rowData
	}
	public static void ReportDownloadAndMatch(def downloadedReportName, FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {

		filterByLocation()

		getBaseSteps().waitForControlDisplay("Table_Data", "YTDReportPage")

		String columnHeader=captureGridData('2')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow =captureGridData('4')
		List<String> dataRow = Utilities.getRowDataWithTruncatedDecimal(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img1',downloadedReportName,"YTDReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		'COMPARING HEADER row Data'
		verifyExcelFileRowContentIsCorrect(6, filePath,columnHeader)
		String excelContent = ExcelUtil.getFormattedRowData(8, filePath)
		List<String> excelRow = Utilities.getRowDataWithTruncatedDecimal(excelContent)
		String msg =String.format("The EXPECTED content is: %s.%n The ACTUAL FILE CONTENT is: %s", firstRow, excelContent )
		'COMPARING Last  ROW (Total)contents'
		AssertSteps.verifyExpectedResult(YTDReportPageSteps.compareLists(dataRow, excelRow),msg, msg)
	}
}


