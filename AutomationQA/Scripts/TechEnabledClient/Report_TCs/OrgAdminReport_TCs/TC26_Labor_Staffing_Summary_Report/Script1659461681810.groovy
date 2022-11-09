import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.StaffingSummaryReportPageSteps
import utils.PlatformUtil 

'Step 1:'

'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)

'ER:'

'Step 2:'

'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Enter the Organization Username and Password and Click on Login button.'
LoginPageSteps.loginWithOrgUser()

'ER:'
'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()

'Step 3:'
'Opening The ReportPage : Staffing Summary Report'
HeaderPageSteps.selectReportSubMenu('Labor Reports', 'Staffing Summary Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Staffing Summary Report')

'Step 4: '
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
def downloadedReportName = 'rdlMemberActivitySummary'

StaffingSummaryReportPageSteps.reportDownloadAndMatch_TechEnableServiceApp(downloadedReportName, startDate, endDate)

'Step 5: '
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}

