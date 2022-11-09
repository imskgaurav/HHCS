package pages


import core.ControlFactory
import core.BaseControl
import core.BaseSteps
import utils.DateTimeUtil
import utils.PlatformUtil
import utils.ExcelUtil
import core.AssertSteps

import internal.GlobalVariable

public class QuickLinksDashBoardReportPageSteps {

	private static url = '/Reports.aspx';

	private static String errMsg="An error has occurred during report processing. (rsProcessingAborted) ";

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================
	//	protected static BaseControl getBaseSteps(){


	//	public static void searchByDate( String startDate, String endDate) {
	//		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "QuickLinksDashBoardReport1")
	//		//baseControl.wai
	//		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "QuickLinksDashBoardReport1")
	//		getBaseSteps().clickToControl("Search_Button", "QuickLinksDashBoardReport1")
	//		getBaseSteps().waitForProgressBarDisappear();
	//	}

	public static void selectStartDate(String startDate) {
		getBaseSteps().setTextToControl("Start_Date_TextBox", startDate, "QuickLinksDashBoardReport1")
		getBaseSteps().clickToControl("End_Date_TextBox", "QuickLinksDashBoardReport1")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectEndDate(String endDate) {
		getBaseSteps().setTextToControl("End_Date_TextBox", endDate, "QuickLinksDashBoardReport1")
		getBaseSteps().clickToControl("Search_Button", "QuickLinksDashBoardReport1")
		//Neeed to Call Sepeartely//
		getBaseSteps().waitForProgressBarDisappear()
	}



	public static void checkPageLoadAndErrorDisplayForReport(String pageTitle) {
		getBaseSteps().verifyControlWithTextDisplayed("Page_Title_Label", pageTitle, "QuickLinksDashBoardReport1")
		getBaseSteps().verifyControlNotDisplayedORDisplayWithoutText("Error_Label",errMsg, "QuickLinksDashBoardReport1",String.format("The message %s is displayed",errMsg))


	}

	public static String verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {
		String datetimeDownload = DateTimeUtil.getDateTime("MMddyyyy")
		String fileNameAfterExported = String.format("%s_%s.xlsx",prefixFile,datetimeDownload)

		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		return fileNameAfterExported
	}


	public static String getCellTextAtFirstRow(int indexColumn) {
		String xpath = getBaseSteps().getXpathFromControl("First_Row", "QuickLinksDashBoardReport1")
		return getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath,indexColumn.toString())
	}


	// =================== ASSERT METHODS ZONE ======================
	public static void verifyReportIsLoadedSuccessfully() {
		getBaseSteps().verifyControlDisplayed("Report_div", "QuickLinksDashBoardReport1")
	}

	public static void verifyExcelFileContentIsCorrect(String filePath, String sheetIndex="0", String expectedContent){

		String excelContent = ExcelUtil.getContentFromFile(filePath, sheetIndex).trim().replaceAll("[\\t\\n\\r\\s]+"," ");
		expectedContent = expectedContent.trim().replaceAll("[\\t\\n\\r]+"," ")
		String msg = String.format("The expected content is: %s. The excel file content is: %s", expectedContent, excelContent )

		//		if(Strings.isNullOrEmpty(expectedContent)) {
		//			AssertSteps.verifyExpectedResult(xlsContent.equals(expectedContent),passMsg,errMsg);
		//		}else {
		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg);
		//}
	}

}
