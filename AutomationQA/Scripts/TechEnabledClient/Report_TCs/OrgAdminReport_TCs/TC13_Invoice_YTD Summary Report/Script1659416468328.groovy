import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.YTDSummaryReportPageSteps
import utils.*

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

'Opening  The ReportPage : YTD Summary'
HeaderPageSteps.selectReportSubMenu('Invoice Report', 'YTD Summary')

DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation('YTD Summary Report')

'Step 4:'

'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
downloadedReportName = 'YTDSummary'

YTDSummaryReportPageSteps.ReportDownloadAndMatch(downloadedReportName)

'Step 5: Clean up data'

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
    Browser.quitDriver()
}