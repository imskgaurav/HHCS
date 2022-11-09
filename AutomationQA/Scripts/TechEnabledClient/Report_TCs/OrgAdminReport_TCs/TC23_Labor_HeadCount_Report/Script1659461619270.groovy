import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeadCountReportPageSteps
import pages.HeaderPageSteps
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

//REPORT menu
//HeaderPageSteps.selectReportSubMenu('Head Count Report')
HeaderPageSteps.selectReportSubMenu('Labor Reports', 'Head Count Report')

DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Head Count Report')

HeadCountReportPageSteps.getDetails()
HeadCountReportPageSteps.downLoadExcel()

String prefixFile= 'Head Count Report'
String section =''

HeadCountReportPageSteps.verifyFileIsDownloadedSuccessfully(prefixFile, section)

'Step: Clean up data'

'Delete file'
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), prefixFile)

@TearDown
def closeBrowser() {

	Browser.quitDriver()
}