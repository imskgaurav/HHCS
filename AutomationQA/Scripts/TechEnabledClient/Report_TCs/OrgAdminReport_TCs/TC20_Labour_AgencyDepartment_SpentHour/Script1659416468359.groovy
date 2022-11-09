import com.kms.katalon.core.annotation.TearDown as TearDown
import core.Browser as Browser
import internal.GlobalVariable as GlobalVariable
import pages.AgencyAndDepartmentSpentHoursReportPageSteps as AgencyAndDepartmentSpentHoursReportPageSteps
import pages.DashboardPageSteps as DashboardPageSteps
import pages.HeaderPageSteps as HeaderPageSteps
import pages.LoginPageSteps as LoginPageSteps
import utils.PlatformUtil as PlatformUtil
import utils.Utilities as Utilities

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
HeaderPageSteps.selectReportSubMenu('Labor Reports', 'Agency & Department Spent Hours Report')
//HeaderPageSteps.selectReportSubMenu('Agency & Department Spent Hours Report')

DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Agency & Department Spent Hours Report')

'Step 4:'

'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
def downloadedReportName = 'AgencySpentHoursReport'
AgencyAndDepartmentSpentHoursReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)
'Step : Verifying the Table Data for Report Type :Department Spent Hours'
//AgencyAndDepartmentSpentHoursReportPageSteps.filterByReportTye()

'Step 5: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}

