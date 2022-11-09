import com.kms.katalon.core.annotation.TearDown
import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.OvertimeReportPageSteps
import utils.PlatformUtil
import utils.Utilities
import utils.ExcelUtil
import core.AssertSteps

'Step 1:'

'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)

'ER:'

'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'

'Enter the Organization Username and Password and Click on Login button.'
LoginPageSteps.loginWithOrgUser()

///MissingCredentialsReportPage.properties

'ER:'
'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()

'Step 3:'
'IOpening the OverTime Report Page'
HeaderPageSteps.selectReportSubMenu('Timesheet Reports', 'Overtime Report')
//HeaderPageSteps.selectReportSubMenu('Overtime Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation('Overtime report')

'Step 4:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
def downloadedReportName= 'rdlOverTimeSummaryDetails'

OvertimeReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)
'Step 5: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}
