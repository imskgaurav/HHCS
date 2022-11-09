import com.kms.katalon.core.annotation.TearDown

import core.Browser
import internal.GlobalVariable
import pages.AgencyDetailsPageSteps
import pages.DashboardPageSteps
import pages.HeaderPageSteps
import pages.InvoiceDetailsPageSteps
import pages.LoginPageSteps
import pages.OrderCreationPageSteps
import pages.OrderSearchPageSteps
import pages.ReOrderSearchMatchPageSteps 


'Step 1:'
'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL00)
'ER:'
'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'
'Enter the Organization Username and Password and Click on Login button.'
LoginPageSteps.loginWithOrgUser()
'ER:'
'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()

//Organization menu
'Create Order'
HeaderPageSteps.selectOrganizationSubMenu("Create Order")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Order Creation")
OrderCreationPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OrderCreationPage")
'Order Search & Match'
HeaderPageSteps.selectOrganizationSubMenu("Order Search & Match")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Order Search")
OrderSearchPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OrderSearchPage")
'Invoice'
HeaderPageSteps.selectOrganizationSubMenu("Invoice")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Invoice Details")
InvoiceDetailsPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "InvoiceDetailsPage")
'Invoice Adjustments'
HeaderPageSteps.selectOrganizationSubMenu("Invoice Adjustments")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Invoice Adjustments")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "InvoiceAdjustmentsPage")
'Group Mail'
HeaderPageSteps.selectOrganizationSubMenu("Group Mail")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Group Mail")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "GroupMailPage")
'Manual Invoice'
HeaderPageSteps.selectOrganizationSubMenu("Manual Invoice")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Manual Invoice")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ManualInvoicePage")
'Re-Order Search & Match'
HeaderPageSteps.selectOrganizationSubMenu("Re-Order Search & Match")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Re-Order Search")
ReOrderSearchMatchPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ReOrderSearchMatchPage")

//Agency
'Agency Details'
HeaderPageSteps.selectAgencySubMenu("Agency Details")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Agency Details")
AgencyDetailsPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "AgencyDetailsPage")
'Candidate Search & Match'
HeaderPageSteps.selectAgencySubMenu("Candidate Search & Match")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidate Search")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CandidateSearchPage")
//'Candidate Details'
//HeaderPageSteps.selectAgencySubMenu("Candidate Details")
//DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidate Details")
//DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CandidateDetailsPage")
'Candidate Unavailability'
HeaderPageSteps.selectAgencySubMenu("Candidate Unavailability")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidate Unavailability")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CandidateUnavailabilityPage")

//Admin
'Manage Users'
HeaderPageSteps.selectAdminSubMenu("Manage Users")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Manage Users")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ManageUsersPage")
'Order Template Management'
HeaderPageSteps.selectAdminSubMenu("Order Template Management")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Order Template Management")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OrderTemplateManagementPage")
'Manage Roles'
HeaderPageSteps.selectAdminSubMenu("Manage Roles")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Manage Roles")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ManageRolesPage")


'Manage Role Access'
HeaderPageSteps.selectAdminSubMenu("Manage Role Access")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Roles Access")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "RolesAccessPage")
'Organizational Hierarchy'
HeaderPageSteps.selectAdminSubMenu("Organizational Hierarchy")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Organizational Hierarchy")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OrganizationalHierarchyPage")
'Organization WorkFlow'
HeaderPageSteps.selectAdminSubMenu("Organization WorkFlow")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Organization Workflow")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OrganizationWorkflowPage")
'Default Pay Rate Details'
HeaderPageSteps.selectAdminSubMenu("Default Pay Rate Details")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Default Pay Rate Details")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "DefaultPayRateDetailsPage")

'Holiday'
HeaderPageSteps.selectAdminSubMenu("Holiday")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Holiday")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "HolidayPage")
'Location Setting'
HeaderPageSteps.selectAdminSubMenu("Location Setting")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Settings")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "LocationSettingPage")
'Do Not Return List'
HeaderPageSteps.selectAdminSubMenu("Do Not Return List")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Do Not Return List")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "DoNotReturnListPage")
'Credential Master'
HeaderPageSteps.selectAdminSubMenu("Credential Master")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Credentials Master")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CredentialMasterPage")
'External Pay Rate Mapping'
HeaderPageSteps.selectAdminSubMenu("External Pay Rate Mapping")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("External Pay Rate")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ExternalPayRatePage")
'Documents'
HeaderPageSteps.selectAdminSubMenu("Documents")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Upload Document")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "UploadDocumentPage")
'Evaluation Master'
HeaderPageSteps.selectAdminSubMenu("Evaluation Master")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Evaluation Master")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "EvaluationMasterPage")
'Shifts'
HeaderPageSteps.selectAdminSubMenu("Shifts")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Shifts Details")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ShiftsDetailsPage")
'Run Interface'
HeaderPageSteps.selectAdminSubMenu("Run Interface")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Run Interface")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "RunInterfacePage")
'Interface Log Summary'
HeaderPageSteps.selectAdminSubMenu("Interface Log Summary")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Interface Log Summary")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "InterfaceLogSummaryPage")
'Pay Rate Override Details'
HeaderPageSteps.selectAdminSubMenu("Pay Rate Override Details")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Pay Rate Override Details")
//DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "PayRateOverrideDetailsPage")
'Skill Import'
//HeaderPageSteps.selectAdminSubMenu("Skill Import")
//DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Skills Import")
//DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "SkillsImportPage")

//Candidate
'TimeSheet'
HeaderPageSteps.selectCandidateSubMenu("TimeSheet")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Timesheet")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "TimeSheetPage")
'Missing TimeSheet Candidate List'
HeaderPageSteps.selectCandidateSubMenu("Missing TimeSheet Candidate List")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidates Missing TimeSheet List")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CandidatesMissingTimeSheetListPage")
'Evaluation'
HeaderPageSteps.selectCandidateSubMenu("Evaluation")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Evaluation Setup")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "EvaluationSetupPage")


//Report
'Invoice Reports'
'Accrual Report'
//HeaderPageSteps.selectReportSubMenu("Accrual Report")
HeaderPageSteps.selectReportSubMenu('Invoice Report', 'Accrual Report')
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Accrual Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "AccrualReportPage")

'Aging Details Report'
HeaderPageSteps.selectReportSubMenu("Invoice Report","Aging Details Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Aging Details Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "AgingDetailsReportPage")

'Invoice Summary Report'
HeaderPageSteps.selectReportSubMenu("Invoice Report","Invoice Summary Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Invoice Summary Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "InvoiceSummaryReportPage")
'Organization Invoice Report'
HeaderPageSteps.selectReportSubMenu("Invoice Report","Organization Invoice Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Invoice Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OrganizationInvoiceReportPage")
'YTD Report'
HeaderPageSteps.selectReportSubMenu("Invoice Report","YTD Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("YTD Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "YTDReportPage")

'YTD Summary'
HeaderPageSteps.selectReportSubMenu("Invoice Report","YTD Summary")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("YTD Summary Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "YTDSummaryReportPage")


//====================================//
'Log Report'
'Activity Log'
//HeaderPageSteps.selectReportSubMenu("Activity Log")
HeaderPageSteps.selectReportSubMenu('Log Reports', 'Activity Log')
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Activity Log Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ActivityLogPage")
'Event Log Report'
HeaderPageSteps.selectReportSubMenu('Log Reports',"Event Log Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Event Log Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "EventLogReportPage")
'Invoice Audit Log Report'
HeaderPageSteps.selectReportSubMenu('Log Reports',"Invoice Audit Log Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Invoice Audit Log Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "InvoiceAuditLogReportPage")
//==============================//
'Labour REPORT'
'Agency & Department Spent Hours Report'
HeaderPageSteps.selectReportSubMenu('Labor Reports', 'Agency & Department Spent Hours Report')
//HeaderPageSteps.selectReportSubMenu("Agency & Department Spent Hours Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Agency & Department Spent Hours Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "AgencyAndDepartmentSpentHoursReportPage")
'Labor Utilization Report'
HeaderPageSteps.selectReportSubMenu('Labor Reports',"Labor Utilization Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Labor Utilization Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "LaborUtilizationReportPage")

'Fill Ratio Report'
HeaderPageSteps.selectReportSubMenu("Labor Reports","Fill Ratio Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Fill ratio report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "FillRatioReportPage")
'Predicted Contract Labor Spent report'
HeaderPageSteps.selectReportSubMenu("Labor Reports","Predicted Contract Labor Spent report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Predicted Contract Labor Spend report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "PredictedContractLaborSpentReportPage")
'Staffing Summary Report'
HeaderPageSteps.selectReportSubMenu("Labor Reports","Staffing Summary Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Staffing Summary Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "StaffingSummaryReportPage")
//====================================================//
"Credential Reports"

'Credential Expiry Report'
HeaderPageSteps.selectReportSubMenu("Credential Reports","Credential Expiry Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Credential Expiry")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "CredentialExpiryReportPage")

'Compliance Report'
HeaderPageSteps.selectReportSubMenu("Credential Reports","Compliance Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Compliance Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "ComplianceReportPage")

'Missing Credentials Report'
HeaderPageSteps.selectReportSubMenu("Credential Reports","Missing Credentials Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Missing Credentials Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "MissingCredentialsReportPage")



//======================================//
'Communication Report'
'General Comments Report'
HeaderPageSteps.selectReportSubMenu("Communication Reports","General Comments Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("General Comments")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "GeneralCommentsReportPage")
'Message History Report'
HeaderPageSteps.selectReportSubMenu("Communication Reports","Message History Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Message History Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "MessageHistoryReportPage")


//'Fill Rate'
//HeaderPageSteps.selectReportSubMenu("Fill Rate")
//DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Candidate Submission & Productivity")
//DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "FillRatePage")
// Is missin when new build was pushed so commenting it out
'First Choice Working Document Report'
HeaderPageSteps.selectReportSubMenu("First Choice Working Document Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("First Choice Working Document Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "FirstChoiceWorkingDocumentReportPage")




'Job Reports'
'Job Details'
HeaderPageSteps.selectReportSubMenu("Job Reports","Job Details")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Job Details")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "JobDetailsPage")
'Job Details Summary'
HeaderPageSteps.selectReportSubMenu("Job Reports","Job Details Summary")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Job Details Summary Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "JobDetailsSummaryPage")
'Job Summary Report'
HeaderPageSteps.selectReportSubMenu("Job Reports","Job Summary Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Job summary report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "JobSummaryReportPage")
'Job Event'
HeaderPageSteps.selectReportSubMenu("Job Reports","Job Event")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Job Event Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "JobEventPage")
'Overall Status Report'
HeaderPageSteps.selectReportSubMenu("Job Reports","Overall Status Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Overall Status Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OverallStatusReportPage")


//=================================//


'Missing Kronos ID’s Report'
HeaderPageSteps.selectReportSubMenu("Organization Reports","Missing Kronos ID’s Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Missing Kronos ID's Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "MissingKronosIDReportPage")

//'Unit Profile'
//HeaderPageSteps.selectReportSubMenu("Unit Profile")
//DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Unit Profile Report")
//DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "UnitProfileReportPage")
'PBJ Report'
HeaderPageSteps.selectReportSubMenu("PBJ Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("PBJ (Payroll based Journal) Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "PJBReportPage")

"TimeSheet Reports"
'TimeSheet Report'
DashboardPageSteps.getBaseSteps().zoomDecrease();
HeaderPageSteps.selectReportSubMenu("Timesheet Reports","TimeSheet Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Timesheet Report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "TimesheetReportPage")
'Overtime Report'
HeaderPageSteps.selectReportSubMenu("Timesheet Reports","Overtime Report")
DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Overtime report")
DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "OvertimeReportPage")
//========================================//


'Overtime Report'
HeaderPageSteps.selectReportSubMenu("Vendor Reports","Vendor Scorecard")
//DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("Vendor Scorecard")
//DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "VendorScorecardReportPage")

//'VMS Coordinator Push Report'
//HeaderPageSteps.selectReportSubMenu("VMS Coordinator Push Report")
//DashboardPageSteps.checkPageLoadAndErrorDisplayForMenuNavigation("VMS Coordinator Push Report")
//DashboardPageSteps.getBaseSteps().verifyControlDisplayed("Content_Div", "VMSCoordinatorPushReportPage")

@TearDown
def closeBrowser(){
	Browser.quitDriver();
}
