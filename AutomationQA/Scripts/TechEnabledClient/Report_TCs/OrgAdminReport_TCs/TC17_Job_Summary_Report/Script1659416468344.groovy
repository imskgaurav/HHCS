import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.JobSummaryReportPageSteps
import pages.LoginPageSteps
import utils.*
import core.AssertSteps

import com.kms.katalon.core.annotation.TearDown


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
'Selecting the  Report Menu :Job->Job Summary'
HeaderPageSteps.selectReportSubMenu("Job Reports","Job Summary Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Job summary report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "JobSummaryReportPage")

'Step 4:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
String downloadedReportName = "rdlJobCandidateDetails"
JobSummaryReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)
JobSummaryReportPageSteps.filterReportByActivitybetweenDaterange()
'Step 5:'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(),downloadedReportName)
@TearDown
def closeBrowser() {
	

	Browser.quitDriver()
}

