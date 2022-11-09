import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps as DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps 
import pages.IntrestedCandidateReportPageSteps
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
HeaderPageSteps.selectReportSubMenu('Interested Candidate Report')

IntrestedCandidateReportPageSteps.clickSearch()

IntrestedCandidateReportPageSteps.clickExport()

@TearDown
def closeBrowser() {
	Browser.quitDriver()
}




