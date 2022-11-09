
import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.JobDetailSummaryReportSteps
import pages.LoginPageSteps
import utils.PlatformUtil
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
'Invoice Audit Log Report'
HeaderPageSteps.selectReportSubMenu("Job Reports","Job Details Summary")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Job Details Summary Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "JobDetailsSummaryPage")

def prefix= 'rdlJobDetailsSummary'
JobDetailSummaryReportSteps.reportDownloadAndMatch(prefix)

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)


@TearDown
def closeBrowser() {
	

	Browser.quitDriver()
}
