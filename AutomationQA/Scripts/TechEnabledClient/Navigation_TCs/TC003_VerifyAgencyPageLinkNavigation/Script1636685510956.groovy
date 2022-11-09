import com.kms.katalon.core.annotation.TearDown

import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.DashboardPageSteps
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
String wd = Browser.getCurrentWindowHandle()

//Check Quick Link
//Agency Details
linkName = "Agency Details"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation(linkName)
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Invoice Details
linkName = "Invoice Details"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation(linkName)
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//New Candidate Profile
linkName = "New Candidate Profile"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Candidate Profile")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Order Search & Match
linkName = "Order Search & Match"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//TimeSheet
linkName = "TimeSheet"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Timesheet")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)


//Check My Tasks
String number = ""
//Jobs Distributed in last 24hrs
linkName = "Jobs Distributed in last 24hrs"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Order_List_Total_Jobs_Label", number, "OrderSearchPage")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//InProgress Jobs in last 24hrs
linkName = "InProgress Jobs in last 24hrs"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Order_List_Total_Jobs_Label", number, "OrderSearchPage")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Filled Jobs in last 24hrs
linkName = "Filled Jobs in last 24hrs"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Order_List_Total_Jobs_Label", number, "OrderSearchPage")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Pending Timesheet(s) Approval
linkName = "Pending Timesheet(s) Approval"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Timesheet")
//String rowNumber = DashboardPageSteps.getBaseSteps().getRowTableCounts("Candidate_List_Table", "TimeSheetPage")
//AssertSteps.verifyExpectedResult(number.equals(rowNumber),"Theo count number and row records is matched","Theo count number and row records is NOT matched")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Credentials expiring in next 30 days-org
linkName = "Credentials expiring in next 30 days-org"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLinkByJS(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Credential Expiry")
DashboardPageSteps.getBaseSteps().verifyTableDisplayed("Credential_Expiry_Table", "CredentialExpiryReportPage")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Comments updated in Last 7 Days
linkName = "Comments updated in Last 7 Days"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLinkByJS(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("General Comments")
DashboardPageSteps.getBaseSteps().verifyTableDisplayed("General_Comments_Table", "GeneralCommentsPage")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)

//Missing Credentials
linkName = "Missing Credentials"
DashboardPageSteps.clickToLinkByJS(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Missing Credentials Report")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//In Progress Open Per Diem requests
linkName = "In Progress Open Per Diem requests"
DashboardPageSteps.clickToLinkByJS(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Re-Order Search")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Open Per Diem Jobs in last 3 days
linkName = "Open Per Diem Jobs in last 3 days"
DashboardPageSteps.clickToLinkByJS(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Re-Order Search")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Candidates orders ending in next 45 days
linkName = "Candidates orders ending in next 45 days"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLinkByJS(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Order_List_Total_Jobs_Label", number, "OrderSearchPage")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)

/** commenting out since data does not exists for the last 8 weeks
//Order Status
//Days filter added by Sunita
DashboardPageSteps.getBaseSteps().selectItemByContainedTextInComboBox("Days_Filter", "All", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Days_Filter","DashboardPage")

//Inprogress
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Order_Status_Combobox", "In-Progress", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Order_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Order_Status_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Order_Status_Table", "5", "In-Progress", "1", "DashboardPage")//4 is status, 1 is jobID
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order View")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Open
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Order_Status_Combobox", "Open", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Order_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Order_Status_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Order_Status_Table", "5", "Open", "1", "DashboardPage")//4 is status, 1 is jobID
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order View")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)

/** commenting out since data does not exists for the last 8 weeks
//Candidates
/**
//Was Shortlisted, with new code since no candidates exists as Short Listed, clicking on a Accepted candidate
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Candidate_Status_Combobox", "Accepted - Documentation Pending", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Candidate_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Candidate_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_Table", "4", "Accepted - Documentation Pending", "1", "DashboardPage")//4 is status, 1 is name
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation2("Candidate Profile")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)

//Offered
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Candidate_Status_Combobox", "Offered", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Candidate_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Candidate_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_Table", "4", "Offered", "1", "DashboardPage")//4 is status, 1 is name
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation2("Candidate Profile")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)

//Accepted - Documentation Pending
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Candidate_Status_Combobox", "Accepted - Documentation Pending", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Candidate_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Candidate_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_Table", "4", "Accepted - Documentation Pending", "1", "DashboardPage")//4 is status, 1 is name
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation2("Candidate Profile")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)

**/
@TearDown
def closeBrowser(){
	Browser.quitDriver();
}

