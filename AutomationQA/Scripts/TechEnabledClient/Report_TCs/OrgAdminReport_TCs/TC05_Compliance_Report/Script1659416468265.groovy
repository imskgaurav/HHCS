import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.ComplianceReportPageSteps
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import utils.PlatformUtil
import utils.Utilities
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
'Selecting The Report menu'
HeaderPageSteps.selectReportSubMenu("Credential Reports","Compliance Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Compliance Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ComplianceReportPage")
'Step 4:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'

String downloadedReportName = "rdlComplianceReport"
ComplianceReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)


@TearDown
def closeBrowser() {
	

	Browser.quitDriver()
}

