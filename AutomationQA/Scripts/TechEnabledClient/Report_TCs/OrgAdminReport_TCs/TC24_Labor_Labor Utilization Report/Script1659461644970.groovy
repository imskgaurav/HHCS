import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LaborUtilizationReportPageSteps
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

'Step 3 :'
HeaderPageSteps.selectReportSubMenu('Labor Reports', 'Labor Utilization Report')
//HeaderPageSteps.selectReportSubMenu('Labor Utilization Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation('Labor Utilization Report')

'Step 4 :'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
def downloadedReportName= 'RptLabourutilization'
LaborUtilizationReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)


@TearDown
def closeBrowser() {


	Browser.quitDriver()
}


