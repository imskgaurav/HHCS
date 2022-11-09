package pages

import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.PlatformUtil
import utils.Utilities

public class EventLogReportPageSteps {

	private static url = '/rptEventLog.aspx';


	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	public static void selectStartDate(String startDate) {
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "EventLogReportPage")
	}

	public static void selectEndDate(String endDate){

		getBaseSteps().setTextToControl("End_Date_TextBox",endDate, "EventLogReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static showDetails() {

		getBaseSteps().clickToControl("Get_Detail_Button", "EventLogReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void verifyTableDataLoaded() {

		getBaseSteps().verifyControlDisplayed('Table_Data', 'EventLogReportPage', "Table Grid is NOT display")
	}

	public static verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {

		String fileNameAfterExported = String.format("%s.xlsx",prefixFile)

		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		println fileNameAfterExported
		return fileNameAfterExported
	}
	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {


		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath,sheetIndex)
		excelContent =Utilities.replaceAllSpacesOfString(excelContent)
		String msg =  String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )


		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
	}


	public static filterByScreenNameAndDate(String startDate, String endDate) {
		getBaseSteps().selectItemByTextInComboBox('Screen_Name_ComboBox', 'Check All', "EventLogReportPage")
		selectStartDate(startDate)
		selectEndDate(endDate)
		showDetails()
	}

	public static String CaptureGridData(String rowNumber) {
		//Storing Row data value in String
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, "EventLogReportPage")
		return rowData
	}

	public static void ReportDownloadAndMatch(def downloadedReportName, String startDate,String endDate,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
      try {
		filterByScreenNameAndDate(startDate, endDate)
		getBaseSteps().waitForControlDisplay('Table_Data', "EventLogReportPage")
		String columnHeader = CaptureGridData('4')
		columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
		String firstRow = CaptureGridData('5')
		firstRow= Utilities.replaceAllSpacesOfString(firstRow)
		/*	getBaseSteps().clickToControl('Button_IMG',"EventLogReportPage")
		 getBaseSteps().waitForControlDisplay('Export_Button', "EventLogReportPage")
		 getBaseSteps().clickToControl('Export_Button', "EventLogReportPage")*/
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_IMG',downloadedReportName, "EventLogReportPage")
		String filePath=PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		verifyExcelFileRowContentIsCorrect(2, filePath,columnHeader)

		verifyExcelFileRowContentIsCorrect(3, filePath,firstRow)
		
      }
	  
	  catch(Exception ex){
		  
		  ex.printStackTrace()
	  }

	  
	}


	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
