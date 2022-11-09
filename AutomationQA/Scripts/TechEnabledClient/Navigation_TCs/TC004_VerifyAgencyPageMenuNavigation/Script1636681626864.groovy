import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.AgencyDetailsPageSteps
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps 

'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL00)
'ER:'
'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Agency Username and Password and Click on Login button.'
LoginPageSteps.loginWithAgencyUser()
//LoginPageSteps.getBaseSteps().clickToControl("OK_Confirm", "CommonLocators")
'ER:'
'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()

//Agency menu
HeaderPageSteps.selectAgencySubMenu("Agency Details")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Agency Details")
AgencyDetailsPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "AgencyDetailsPage")
HeaderPageSteps.selectAgencySubMenu("New Candidate Profile")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidate Profile")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CandidateProfilePage")
HeaderPageSteps.selectAgencySubMenu("Candidate Search & Match")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidate Search")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CandidateSearchPage")
HeaderPageSteps.selectAgencySubMenu("Order Search & Match")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Order Search")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OrderSearchPage")
//HeaderPageSteps.selectAgencySubMenu("Candidate Details")
//DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidate Details")
//DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CandidateDetailsPage")
HeaderPageSteps.selectAgencySubMenu("Invoice Details")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Invoice Details")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "InvoiceDetailsPage")
HeaderPageSteps.selectAgencySubMenu("Re-Order Search & Match")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Re-Order Search")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ReOrderSearchMatchPage")
HeaderPageSteps.selectAgencySubMenu("Candidate Unavailability")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidate Unavailability")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CandidateUnavailabilityPage")

//Admin
HeaderPageSteps.selectAdminSubMenu("Manage Users")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Manage Users")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ManageUsersPage")

HeaderPageSteps.selectAdminSubMenu("Manage Roles")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Manage Roles")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ManageRolesPage")

HeaderPageSteps.selectAdminSubMenu("Manage Role Access")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Roles Access")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "RolesAccessPage")

HeaderPageSteps.selectAdminSubMenu("Documents")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Upload Document")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "UploadDocumentPage")

HeaderPageSteps.selectAdminSubMenu("Evaluation Master")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Evaluation Master")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "EvaluationMasterPage")


//Candidate
//HeaderPageSteps.selectCandidateSubMenu("Timesheet Template")
//DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Timesheet Template")
//DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "TimesheetTemplatePage")

HeaderPageSteps.selectCandidateSubMenu("TimeSheet")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Timesheet")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "TimeSheetPage")

HeaderPageSteps.selectCandidateSubMenu("Evaluation")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Evaluation")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "EvaluationPage")

//Report
HeaderPageSteps.selectReportSubMenu('Log Reports', 'Activity Log')
//HeaderPageSteps.selectReportSubMenu("Activity Log")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Activity Log Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ActivityLogPage")

//HeaderPageSteps.selectReportSubMenu("Agency Invoice Report")
HeaderPageSteps.selectReportSubMenu('Invoice Report', 'Agency Invoice Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Invoice Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "AgencyInvoiceReportPage")

HeaderPageSteps.selectReportSubMenu("Invoice Report","Invoice Summary Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Invoice Summary Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "InvoiceSummaryReportPage")

HeaderPageSteps.selectReportSubMenu("Candidate List Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidate List Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CandidateListReportPage")

//HeaderPageSteps.selectReportSubMenu("Credential Expiry Report")
HeaderPageSteps.selectReportSubMenu('Credential Reports', 'Credential Expiry Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Credential Expiry")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CredentialExpiryReportPage")

HeaderPageSteps.selectReportSubMenu('Credential Reports',"Missing Credentials Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Missing Credentials Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "MissingCredentialsReportPage")


HeaderPageSteps.selectReportSubMenu('Communication Reports',"General Comments Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("General Comments")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "GeneralCommentsReportPage")

//HeaderPageSteps.selectReportSubMenu("Message History Report")
HeaderPageSteps.selectReportSubMenu('Communication Reports',"Message History Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Message History Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "MessageHistoryReportPage")


HeaderPageSteps.selectReportSubMenu("Order Check Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Order Check Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OrderCheckReportPage")

HeaderPageSteps.selectReportSubMenu('Timesheet Reports',"TimeSheet Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Timesheet Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "TimesheetReportPage")

@TearDown
def closeBrowser(){
	Browser.quitDriver();
}
