import com.kms.katalon.core.annotation.TearDown

import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.YTDReportPageSteps
import utils.ExcelUtil
import utils.PlatformUtil
import utils.Utilities
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

'ER:'

'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()

'Step 3:'
'YTD Invoice Report'
HeaderPageSteps.selectReportSubMenu('Invoice Report', 'YTD Report')

DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('YTD Report')

DashboardPageSteps.getBaseSteps().verifyControlDisplayed('Content_Div', 'YTDReportPage')

'Step 4:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'

String downloadedReportName = 'RptYTDByLocation'

YTDReportPageSteps.ReportDownloadAndMatch(downloadedReportName)
YTDReportPageSteps.filterReportByMonth()

'Step: Clean up data'

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}


