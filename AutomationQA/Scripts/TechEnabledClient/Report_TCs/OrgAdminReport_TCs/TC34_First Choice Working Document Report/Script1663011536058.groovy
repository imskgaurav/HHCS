import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.FirstChoiceWorkingDocumentReportPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import utils.PlatformUtil
import utils.Utilities
'Step 1:'

'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)

'ER:'

'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'

'Enter the Organization Username and Password and Click on Login button.'
LoginPageSteps.loginWithOrgUser()

'ER:'

'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()

'Step 3:'

'Opening the Report Page'
HeaderPageSteps.selectReportSubMenu('First Choice Working Document Report')

String downloadedReportName= "rptFirstChoiceWorkingDocumentReport"
FirstChoiceWorkingDocumentReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)

'Delete file'
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(),downloadedReportName )
@TearDown
def closeBrowser() {
Browser.quitDriver()
}
//FirstChoiceWorkingDocumentReportPageSteps.verifyPageIsLoaded()


//FirstChoiceWorkingDocumentReportPageSteps.selectStartDate(startDate)
//FirstChoiceWorkingDocumentReportPageSteps.selectEndDate(endDate)
//FirstChoiceWorkingDocumentReportPageSteps.getBaseSteps().clickToControl("GetDetails_Button", "FirstChoiceWorkingDocumentReportPage")
//FirstChoiceWorkingDocumentReportPageSteps.getBaseSteps().waitForProgressBarDisappear()
//FirstChoiceWorkingDocumentReportPageSteps.getBaseSteps().waitForControlDisplay('Table_Data', "FirstChoiceWorkingDocumentReportPage")
//
//String fileNameAfterExported = ExcelUtil.ExportExcel('Button_Img',downloadedReportName,"FirstChoiceWorkingDocumentReportPage")
//
//String filePath = PlatformUtil.getFilePath(PlatformUtil.getDownloadPath(), fileNameAfterExported)
//
//String columnHeader =FirstChoiceWorkingDocumentReportPageSteps.captureGridData('5')
//columnHeader= Utilities.replaceAllSpacesOfString(columnHeader)






