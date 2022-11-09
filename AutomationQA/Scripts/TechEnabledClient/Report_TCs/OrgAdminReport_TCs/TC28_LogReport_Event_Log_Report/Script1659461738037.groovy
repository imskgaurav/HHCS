import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.EventLogReportPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import utils.PlatformUtil as PlatformUtil
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
'Opening the Event Log Report'
//REPORT menu
//HeaderPageSteps.selectReportSubMenu('Event Log Report')
HeaderPageSteps.selectReportSubMenu('Log Reports', 'Event Log Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation('Event Log Report')

'Step 4:'
'Selecting Filter Input and Verifying The Table Data with Excel downloaded'
def  downLoadReportName ='rdlEventLog'
EventLogReportPageSteps.ReportDownloadAndMatch(downLoadReportName, startDate, endDate)
'Step 5: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downLoadReportName)
@TearDown
def closeBrowser() {
	

	Browser.quitDriver()
}



