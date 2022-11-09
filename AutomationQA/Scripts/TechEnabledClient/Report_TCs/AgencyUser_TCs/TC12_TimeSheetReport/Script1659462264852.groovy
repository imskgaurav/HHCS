
import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.TimesheetReportPageSteps
import utils.PlatformUtil

'Step 1:'

'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)

'ER:'

'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'

'Enter the Organization Username and Password and Click on Login button.'
LoginPageSteps.loginWithAgencyUser()
if(GlobalVariable.AGC_USER=='aboxberger@yopmail.com') {
LoginPageSteps.getBaseSteps().clickToControl("OK_Confirm", "CommonLocators")
}



'ER:'

'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()

'Step 3:'

'Opening the Report Page'
HeaderPageSteps.selectReportSubMenu('Timesheet Reports', 'TimeSheet Report')

def downloadedReportName="rptTimeSheetSummary"

'Select Status from ComboBox'
TimesheetReportPageSteps.getBaseSteps().clickToControl("Status_Arrow_Button", "TimesheetReportPage")
TimesheetReportPageSteps.getBaseSteps().selectCheckboxByContainedTextInComboBox("Status_Combobox", "Check All", "TimesheetReportPage")
'DownLoading the Excel Report and comparing the Header and Data Row with Table Data'
TimesheetReportPageSteps.ReportDownloadAndMatch(downloadedReportName, startDate, endDate)

'Step 4: Clean up data'
'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(),downloadedReportName )

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}
