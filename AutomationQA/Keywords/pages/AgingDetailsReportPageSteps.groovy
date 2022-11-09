package pages


import core.BaseSteps
import internal.GlobalVariable
import core.ControlFactory
import utils.PlatformUtil as PlatformUtil
import utils.ExcelUtil

import com.kms.katalon.core.model.FailureHandling

import core.AssertSteps
import utils.Utilities

public class AgingDetailsReportPageSteps {

	private static url = 'RptAgingDetails.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}


	public static void selectRegion(String region) {
		getBaseSteps().clickToControl("Region_Combobox", "AgingDetailsReportPage")
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Region_Combobox","Select all","AgingDetailsReportPage") //uncheck all option
		/*getBaseSteps().clickToControl("Input_SearchText", "AgingDetailsReportPage")
		 getBaseSteps().setTextToControl("Input_SearchText", region, "AgingDetailsReportPage")*/
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Region_Combobox",region,"AgingDetailsReportPage")
		//getBaseSteps().clickToControl("Region_Combobox", "AgingDetailsReportPage")
		//getBaseSteps().selectItemByTextInComboBox("Select_All_Chkbox", "Select all",, "AgingDetailsReportPage")
		//		//getBaseSteps().selectCheckboxByContainedTextInComboBox("Input_SearchText", region, "AgingDetailsReportPage")
		//		getBaseSteps().verifyCheckBoxIsChecked("Input_SearchText", "AgingDetailsReportPage")
		//getBaseSteps().selectCheckboxByContainedTextInComboBoxHasAList("Input_SearchText", region, "AgingDetailsReportPage")
		//getBaseSteps().clickToControl("Region_Arrow_Icon_Combobox","AgingDetailsReportPage")

		//getBaseSteps().clickToControl("Rate_Time_Textbox","AgingDetailsReportPage")
		getBaseSteps().waitForProgressBarDisappear()
		//getBaseSteps().clickToControl("Region_Combobox", "AgingDetailsReportPage")
		//getBaseSteps().selectItemByTextInComboBox("Select_All_Chkbox", "Select all",, "AgingDetailsReportPage")
	}

	public static void verifyTableDataLoaded() {

		getBaseSteps().verifyControlDisplayed('Table_Data', 'AgingDetailsReportPage', "Table Grid is NOT display")
	}
	public static void clickGetDetails() {
		getBaseSteps().clickToControl("GetDetails_button", "AgingDetailsReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickExpand() {
		getBaseSteps().clickToControl("Expansion_+button", "AgingDetailsReportPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static String verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {
		String fileNameAfterExported = String.format("%s.xlsx",prefixFile)

		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		println fileNameAfterExported
		return fileNameAfterExported
	}

	public static void verifyExcelFileRowContentIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent) {


		String excelContent =  ExcelUtil.getFormattedRowData(rowNum, filePath,sheetIndex)
		excelContent=Utilities.replaceAllSpacesOfString(excelContent)


		String msg = String.format("The EXPECTED content is: %s.%n The ACTUAL file content is: %s", expectedContent, excelContent )
		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
	}

	//New Function for Filter //

	public static String CaptureGridData(String rowNumber)
	{
		getBaseSteps().getRowTableCounts('Table_Data', 'AgingDetailsReportPage')
		String rowData = getBaseSteps().getRowTableText('Table_Data', rowNumber, 'AgingDetailsReportPage')
		return rowData
	}
	public static filterByDefaultSelection()
	{
		clickGetDetails()
		clickExpand()

	}


	public static void ReportDownloadAndMatch(def downloadedReportName,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {

		try {
			filterByDefaultSelection()
			verifyTableDataLoaded()
			String columnHeader=CaptureGridData('2')
			columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)
			String firstRow =CaptureGridData('3')
			firstRow=Utilities.replaceAllSpacesOfString(firstRow)
			String fileNameAfterExported = ExcelUtil.ExportExcel('Button_IMG',downloadedReportName,'AgingDetailsReportPage')
			String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
			verifyExcelFileRowContentIsCorrect(2, filePath, columnHeader)
			verifyExcelFileRowContentIsCorrect(3, filePath, firstRow)

		}
		catch(Exception ex) {

			ex.getStackTrace()


		}

	}

}
