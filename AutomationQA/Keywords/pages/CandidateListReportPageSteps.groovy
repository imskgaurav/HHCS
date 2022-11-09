package pages
import core.BaseSteps
import core.ControlFactory
import core.AssertSteps

import core.Browser
import internal.GlobalVariable
import pages.CandidateListReportPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities


////RptCandidateListReport.aspx
public class CandidateListReportPageSteps {
	private static url = '/RptCandidateListReport.aspx';
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}


	// =================== ACTION METHODS ZONE ======================


	public static void ReportDownloadAndMatch(String downloadedReportName)
	{
		CandidateListReportPageSteps.getBaseSteps().clickToControl("Get_Details_button", "CandidateListReportPage")
		CandidateListReportPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateListReportPageSteps.getBaseSteps().verifyControlDisplayed("Table_Data", "CandidateListReportPage", "No Data is available for selected Criteria")
		String headerRow=CandidateListReportPageSteps.getBaseSteps().getRowTableText("Table_Data", "4",  "CandidateListReportPage")
		println headerRow
		String dataRow=CandidateListReportPageSteps.getBaseSteps().getRowTableText("Table_Data", "5",  "CandidateListReportPage")
		println dataRow
		headerRow= Utilities.replaceAllSpacesOfString(headerRow)
		dataRow=Utilities.replaceAllSpacesOfString(dataRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName, "CandidateListReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		ExcelUtil.verifyExcelFileContentWithNoSpaceIsCorrect(2, filePath, headerRow)
		ExcelUtil.verifyExcelFileContentWithNoSpaceIsCorrect(3, filePath, dataRow)

	}


	//=================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}



}
