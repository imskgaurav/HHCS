import com.kms.katalon.core.annotation.TearDown as TearDown
import core.Browser as Browser
import internal.GlobalVariable as GlobalVariable
import pages.DashboardPageSteps as DashboardPageSteps
import pages.GeneralCommentsReportPageSteps as GeneralCommentsReportPageSteps
import pages.HeaderPageSteps as HeaderPageSteps
import pages.LoginPageSteps as LoginPageSteps
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

'Opening the Report Page'
HeaderPageSteps.selectReportSubMenu('Communication Reports', 'General Comments Report')

GeneralCommentsReportPageSteps.verifyPageIsLoaded()

def downloadedReportName = 'rdlGeneralNotes'

GeneralCommentsReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)

'Step 4: Clean up data'

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}