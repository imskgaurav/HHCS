import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.OrganizationInvoiceReportPageSteps
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
'Step 3:'
'OPEN the Report Page'
HeaderPageSteps.selectReportSubMenu('Invoice Report', 'Organization Invoice Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Invoice Report')

DashboardPageSteps.getBaseSteps().verifyControlDisplayed('Content_Div', 'OrganizationInvoiceReportPage')

'Step 4:'
String downloadedReportName = 'RptInvoice'
OrganizationInvoiceReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)

'Step 5:'
'Delete file'
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)
@TearDown
def closeBrowser() {
	

	Browser.quitDriver()
}



