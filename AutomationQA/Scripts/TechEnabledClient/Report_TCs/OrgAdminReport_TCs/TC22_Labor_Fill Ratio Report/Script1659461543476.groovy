import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.FillRatioReportPageSteps as FillRatioReportPageSteps
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

'Step 3:'

'Selecting the  Report Menu :Labour-> Fill Ratio Report'
HeaderPageSteps.selectReportSubMenu('Labor Reports', 'Fill Ratio Report')

DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation('Fill ratio report')

DashboardPageSteps.getBaseSteps().verifyControlDisplayed('Content_Div', 'FillRatioReportPage')

'Step 4:'

'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
String downloadedReportName = 'rdlFillRatioByAgency'

FillRatioReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)
//FillRatioReportPageSteps.filterReportByLocation()


'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)


@TearDown
def closeBrowser() {
	

Browser.quitDriver()
}








