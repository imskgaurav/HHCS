package pages



import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil


import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.ExcelUtil
import utils.Utilities
import internal.GlobalVariable

public class UnitProfileReportPageSteps {

	private static url = '/RptUnitProfile.aspx';
	//String static tableErrMsge ="TABLE not Loaded"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	//=================Action method==========================//

	public static void getDetails() {

		getBaseSteps().clickToControl('GetDetails_Button', 'UnitProfileReportPage')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void verifyTableDataLoaded() {

		//getBaseSteps().verifyControlDisplayed('Table_Data', 'UnitProfileReportPage', 'Table Grid is NOT display')
		getBaseSteps().verifyControlDisplayed('Table_Data', 'UnitProfileReportPage')

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
		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath, sheetIndex)
		excelContent = Utilities.replaceAllSpacesOfString(excelContent)

		//println 'Without Trim :' +excelContent

		String msg =String.format("The EXPECTED content is: %s. The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)

	}
	public static String captureGridData(String rowNumber)
	{
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "UnitProfileReportPage")
		return rowData
	}
	
	public static void ReportDownloadAndMatch(downloadedReportName, FailureHandling failureHandling=FailureHandling.CONTINUE_ON_FAILURE ) {
		try {
		getDetails()
		getBaseSteps().waitForControlDisplay('Table_Data',"UnitProfileReportPage" )
		String columnHeader=captureGridData('2')
		columnHeader=Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow=captureGridData('3')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"UnitProfileReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(8, filePath, columnHeader)
		verifyExcelFileRowContentIsCorrect(9, filePath, firstRow)
		
	}
	catch(Exception ex) {
		ex.getMessage()
		
	}
	
	}
}
