import core.AssertSteps as AssertSteps
import core.Browser as Browser
import internal.GlobalVariable as GlobalVariable
import pages.HeaderPageSteps as HeaderPageSteps
import pages.LoginPageSteps as LoginPageSteps
import utils.DateTimeUtil as DateTimeUtil
import utils.Database as Database
import utils.PlatformUtil as PlatformUtil
import pages.DashboardPageSteps as DashboardPageSteps
import com.kms.katalon.core.annotation.TearDown as TearDown
import pages.JobDetailsReportPageSteps as JobDetailsReportPageSteps

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

'Selecting the  Report Menu :Job->Job Details'
HeaderPageSteps.selectReportSubMenu('Job Reports', 'Job Details')

DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Job Details')

//DashboardPageSteps.getBaseSteps().verifyControlDisplayed('Content_Div', 'JobDetailsPage')

'Step 4:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
String downloadedReportName = 'rdlJobDetails'

JobDetailsReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)

'Step 5:'

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}


