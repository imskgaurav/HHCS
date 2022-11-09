package pages

import core.BaseSteps
import internal.GlobalVariable
import core.ControlFactory
import utils.PlatformUtil
import utils.ExcelUtil
import utils.Utilities
public class OrderCheckReportPageSteps {
	private static url = '/RptOrderCheck.aspx'

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================

	public static void ReportDownloadAndMatch(String downloadedReportName, locationName, departmentName, jobID) {

		OrderCheckReportPageSteps.getBaseSteps().selectItemByTextInComboBox("Location_comboBox", locationName, "OrderCheckReportPage")
		OrderCheckReportPageSteps.getBaseSteps().selectItemByTextInComboBox("Department_comboBox", departmentName, "OrderCheckReportPage")

		OrderCheckReportPageSteps.getBaseSteps().selectItemByTextInComboBox("Job_comboBox", jobID, "OrderCheckReportPage")
		OrderCheckReportPageSteps.getBaseSteps().waitForControlDisplay("Get_Details_btn", "OrderCheckReportPage")
		OrderCheckReportPageSteps.getBaseSteps().clickToControl("Get_Details_btn", "OrderCheckReportPage")
		OrderCheckReportPageSteps.getBaseSteps().waitForProgressBarDisappear()
		OrderCheckReportPageSteps.getBaseSteps().verifyControlDisplayed("Table_Data", "OrderCheckReportPage")
		String headerRow = OrderCheckReportPageSteps.getBaseSteps().getRowTableText("Table_Data", "2", "OrderCheckReportPage")
		headerRow=Utilities.replaceAllSpacesOfString(headerRow)
		String dataRow=OrderCheckReportPageSteps.getBaseSteps().getRowTableText("Table_Data", "3", "OrderCheckReportPage")
		dataRow=Utilities.replaceAllSpacesOfString(dataRow)
		String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"OrderCheckReportPage")
		String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		'COMPARING HEADER row Data'
		//AgencyInvoiceReportPageSteps.verifyExcelFileRowContentIsCorrect(6, filePath,columnHeader)
		ExcelUtil.verifyExcelFileContentWithNoSpaceIsCorrect(5, filePath, headerRow)
		ExcelUtil.verifyExcelFileContentWithNoSpaceIsCorrect(6, filePath, dataRow)
	}


}
