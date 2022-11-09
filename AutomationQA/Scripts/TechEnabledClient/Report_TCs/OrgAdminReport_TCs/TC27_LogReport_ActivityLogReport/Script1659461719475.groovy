import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.ActivityLogReportPageSteps
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import utils.PlatformUtil as PlatformUtil

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
'Click on Report menu and opening Activity Log Report'
HeaderPageSteps.selectReportSubMenu('Log Reports', 'Activity Log')
//HeaderPageSteps.selectReportSubMenu('Activity Log')
'ER:'
'Verify no Error on the page'
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Activity Log Report')
'Step: 4'
def reportName = 'ActivityLog'
'Selecting Filter input  and Verifying the Table Data with Excel downloaded'
ActivityLogReportPageSteps.ReportDownloadAndMatch(reportName, startDate, endDate)

'Step: 5'
'Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), reportName)


@TearDown
def closeBrowser() {
	

	Browser.quitDriver()
}



