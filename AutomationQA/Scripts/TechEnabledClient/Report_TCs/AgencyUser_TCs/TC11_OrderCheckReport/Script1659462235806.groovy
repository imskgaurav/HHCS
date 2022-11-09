import com.kms.katalon.core.annotation.TearDown as TearDown
import core.Browser as Browser
import internal.GlobalVariable as GlobalVariable
import pages.DashboardPageSteps as DashboardPageSteps
import pages.HeaderPageSteps as HeaderPageSteps
import pages.LoginPageSteps as LoginPageSteps
import pages.OrderCheckReportPageSteps as OrderCheckReportPageSteps
import utils.PlatformUtil as PlatformUtil

'Step 1:'

'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)

'ER:'

'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'

'Enter the Organization Username and Password and Click on Login button.'
LoginPageSteps.loginWithAgencyUser()

if (GlobalVariable.AGC_USER == 'aboxberger@yopmail.com') {
    LoginPageSteps.getBaseSteps().clickToControl('OK_Confirm', 'CommonLocators')
}

'ER:'

'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()

'Step 3:'

'Opening the Report Page'
HeaderPageSteps.selectReportSubMenu('Order Check Report')

def downloadedReportName = 'rdlOrderCheck'

//OrderCheckReportPageSteps.ReportDownloadAndMatch(downloadedReportName, locationName, departmentName, jobID)
OrderCheckReportPageSteps.ReportDownloadAndMatch(downloadedReportName, locationName1, departmentName1, jobID1)

//HeaderPageSteps.getBaseSteps().selectItemByTextInComboBox(controlName, itemText, pageName)
'Step 4: Clean up data'

'Delete file '
PlatformUtil.deleteAllFilesWithPrefix(PlatformUtil.getDownloadPath(), downloadedReportName)

@TearDown
def closeBrowser() {
    Browser.quitDriver()
}