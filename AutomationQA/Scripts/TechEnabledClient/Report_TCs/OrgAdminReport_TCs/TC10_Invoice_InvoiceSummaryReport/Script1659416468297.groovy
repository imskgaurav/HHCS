import com.kms.katalon.core.annotation.TearDown as TearDown
import core.Browser as Browser
import internal.GlobalVariable as GlobalVariable
import pages.DashboardPageSteps as DashboardPageSteps
import pages.HeaderPageSteps as HeaderPageSteps
import pages.InvoiceSummaryReportPageSteps as InvoiceSummaryReportPageSteps
import pages.LoginPageSteps
import utils.ExcelUtil
import utils.PlatformUtil as PlatformUtil
import utils.Utilities as Utilities
import core.AssertSteps as AssertSteps


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

//REPORT menu
'Step 3:'
'OPEN the Report Page'
HeaderPageSteps.selectReportSubMenu('Invoice Report', 'Invoice Summary Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Invoice Summary Report')
DashboardPageSteps.getBaseSteps().verifyControlDisplayed('Content_Div', 'InvoiceSummaryReportPage')

'Step 4:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
String downloadedReportName = 'rdlInvoiceSummaryDetails'
InvoiceSummaryReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)
InvoiceSummaryReportPageSteps.filterReportBySummary()
'Step 5: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(),downloadedReportName )

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}
