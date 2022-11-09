import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
import pages.MissingCredentialsReportPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
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
HeaderPageSteps.selectReportSubMenu("Credential Reports", "Documents Expiry Report")


@TearDown
def closeBrowser() {
	Browser.quitDriver()
}


