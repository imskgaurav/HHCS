import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.VendorScorecardReportPageSteps
import utils.*


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

'Opening  The ReportPage : Vendor Scorecard'
HeaderPageSteps.selectReportSubMenu('Vendor Reports', 'Vendor Scorecard')

DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Vendor Scorecard')

'Step 4:'

'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
def prefix = 'VendorScorecard'

VendorScorecardReportPageSteps.ReportDownloadAndMatch(prefix, startDate, endDate)

'Step 5: Clean up data'

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix) 

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}