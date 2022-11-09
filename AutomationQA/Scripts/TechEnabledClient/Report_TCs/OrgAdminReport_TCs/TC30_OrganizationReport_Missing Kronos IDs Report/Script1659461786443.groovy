import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.MissingKronosIDReportPageSteps as MissingKronosIDReportPageSteps
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

//REPORT menu
'Step 3:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
HeaderPageSteps.selectReportSubMenu('Organization Reports', 'Missing Kronos ID’s Report')
//HeaderPageSteps.selectReportSubMenu('Missing Kronos ID’s Report')
String downloadedReportName = 'rdlMissingCandidateDetails'
MissingKronosIDReportPageSteps.ReportDownloadAndMatch(downloadedReportName)
MissingKronosIDReportPageSteps.filterReportByExistingClockId()
'Step 4:'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}



