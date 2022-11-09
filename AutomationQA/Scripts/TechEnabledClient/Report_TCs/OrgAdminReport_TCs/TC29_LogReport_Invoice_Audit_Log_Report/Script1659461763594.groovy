import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.InvoiceAuditLogReportPageSteps
import pages.LoginPageSteps
import utils.PlatformUtil as PlatformUtil
import utils.Utilities as Utilities
import utils.ExcelUtil

import com.kms.katalon.core.annotation.TearDown

import core.AssertSteps

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
'OPEN the Report Page'
'Invoice Audit Log Report'
HeaderPageSteps.selectReportSubMenu('Log Reports', 'Invoice Audit Log Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Invoice Audit Log Report')
DashboardPageSteps.getBaseSteps().verifyControlDisplayed('Content_Div', 'InvoiceAuditLogReportPage')

'Step 4:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
downloadedReportName='rdlInvoiceCancellationItemwise'
InvoiceAuditLogReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)
InvoiceAuditLogReportPageSteps.filterByDetails()


'Step 5: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}

