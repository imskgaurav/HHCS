import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.PredictedContractLaborSpentReportPageSteps
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

//REPORT menu
HeaderPageSteps.selectReportSubMenu('Labor Reports', 'Predicted Contract Labor Spent report')
//HeaderPageSteps.selectReportSubMenu('Predicted Contract Labor Spent report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Predicted Contract Labor Spend report')
String prefix = 'rdlPredictedContractLabourSpent'
PredictedContractLaborSpentReportPageSteps.ReportDownloadAndMatch(prefix)

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefix)

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}

