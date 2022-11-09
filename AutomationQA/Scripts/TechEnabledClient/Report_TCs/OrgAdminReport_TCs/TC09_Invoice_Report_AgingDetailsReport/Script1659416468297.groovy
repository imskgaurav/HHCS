import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.AgingDetailsReportPageSteps
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

'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()

'Step 3:'

'Aging Details Report'
HeaderPageSteps.selectReportSubMenu('Invoice Report', 'Aging Details Report')

DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Aging Details Report')

DashboardPageSteps.getBaseSteps().verifyControlDisplayed('Content_Div', 'AgingDetailsReportPage')
'Step 4:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
String downloadedReportName = 'RptAging'

AgingDetailsReportPageSteps.ReportDownloadAndMatch(downloadedReportName)

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {

	Browser.quitDriver()
}
