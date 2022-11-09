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
'Enter the Organization Username and Password and Click on Login button.'
LoginPageSteps.loginWithOrgUser()
'ER:'
'Dashboard page is loaded'
DashboardPageSteps.verifyPageIsLoaded()
String wd = Browser.getCurrentWindowHandle()

////Check Quick Link
//Agency & Department Spent Hours Report
String linkName = "Agency & Department Spent Hours Report"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation(linkName)
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
////Browser.switchToWindow(wd)
//Agency Details
linkName = "Agency Details"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation(linkName)
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
////Browser.switchToWindow(wd)
////Create Order
linkName = "Create Order"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Creation")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
////Order Search & Match
linkName = "Order Search & Match"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)

////TimeSheet
//linkName = "TimeSheet"
//DashboardPageSteps.clickToLink(linkName)
//DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation(linkName)
//Browser.closeOpenedWindow()
//Browser.switchToWindow(wd)

//Check My Tasks
/*
 * String number = "" //InComplete Orders linkName = "InComplete Orders" number
 * = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable(
 * "My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
 * DashboardPageSteps.clickToLink(linkName) DashboardPageSteps.
 * checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
 * DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed(
 * "Order_List_Total_Jobs_Label", number, "OrderSearchPage")
 * Browser.closeOpenedWindow() Browser.switchToWindow(wd) //Pending Order(s)
 * Approval linkName = "Pending Order(s) Approval" number =
 * DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable(
 * "My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
 * DashboardPageSteps.clickToLink(linkName) DashboardPageSteps.
 * checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
 * DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed(
 * "Order_List_Total_Jobs_Label", number, "OrderSearchPage")
 * Browser.closeOpenedWindow() Browser.switchToWindow(wd) //Open Orders linkName
 * = "Open Orders" number =
 * DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable(
 * "My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
 * DashboardPageSteps.clickToLink(linkName) DashboardPageSteps.
 * checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
 * DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed(
 * "Order_List_Total_Jobs_Label", number, "OrderSearchPage")
 * Browser.closeOpenedWindow() Browser.switchToWindow(wd) //In-Progress Orders
 * linkName = "In-Progress Orders" number =
 * DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable(
 * "My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
 * DashboardPageSteps.clickToLink(linkName) DashboardPageSteps.
 * checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
 * DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed(
 * "Order_List_Total_Jobs_Label", number, "OrderSearchPage")
 * Browser.closeOpenedWindow() Browser.switchToWindow(wd) //Filled Orders
 * linkName = "Filled Orders" number =
 * DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable(
 * "My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
 * DashboardPageSteps.clickToLink(linkName) DashboardPageSteps.
 * checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
 * DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed(
 * "Order_List_Total_Jobs_Label", number, "OrderSearchPage")
 * Browser.closeOpenedWindow() Browser.switchToWindow(wd)
 * 
 * //Missing Kronos ID //linkName = "Missing Kronos ID"
 * //DashboardPageSteps.clickToLink(linkName) //DashboardPageSteps.
 * checkPageLoadAndErrorDisplayForLinkNavigation("Missing Kronos ID's Report")
 * //Browser.closeOpenedWindow() //Browser.switchToWindow(wd)
 */
////Check My Tasks
String number = ""
linkName = "Pending Timesheets"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Timesheet")
String rowNumber = DashboardPageSteps.getBaseSteps().getRowTableCounts("Candidate_List_Table", "TimeSheetPage")
AssertSteps.verifyExpectedResult(number.equals(rowNumber),"The count number and row records is matched","Theo count number and row records is NOT matched")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Missing Credentials
linkName = "Missing Credentials"
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Missing Credentials Report")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)

//Document expiring in next 30 days-agency
linkName = "Credentials expiring in next 30 days-agency"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Credential Expiry")
//DashboardPageSteps.getBaseSteps().verifyTableDisplayed("Credential_Expiry_Table", "CredentialExpiryReportPage")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Comments in Last 7 Days
linkName = "Comments in Last 7 Days"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLink(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("General Comments")
DashboardPageSteps.getBaseSteps().verifyTableDisplayed("General_Comments_Table", "GeneralCommentsPage")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Documents expiring in next 30 days
//linkName = "Documents expiring in next 30 days"
//number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
//DashboardPageSteps.clickToLink(linkName)
//DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Upload Document")
//String rowNumberUpload = DashboardPageSteps.getBaseSteps().getRowTableCounts("Upload_Document_Table", "UploadDocumentPage")
//AssertSteps.verifyExpectedResult(number.equals(rowNumberUpload),"Theo count number and row records is matched","Theo count number and row records is NOT matched")
//Browser.closeOpenedWindow()
//Browser.switchToWindow(wd)
//Candidates orders ending in next 45 days
linkName = "Candidates orders ending in next 45 days"
number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
DashboardPageSteps.clickToLinkByJS(linkName)
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
//DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Order_List_Total_Jobs_Label", number, "OrderSearchPage")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)

////Closed Orders
//linkName = "Closed Orders"
//number = DashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("My_Task_Table", "1", linkName, "2", "DashboardPage")//1 is Name, 2 is number
//DashboardPageSteps.clickToLinkByJS(linkName)
//DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Search")
//DashboardPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Order_List_Total_Jobs_Label", number, "OrderSearchPage")
//Browser.closeOpenedWindow()
//Browser.switchToWindow(wd)

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
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Creation")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Open
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Order_Status_Combobox", "Open", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Order_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Order_Status_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Order_Status_Table", "5", "Open", "1", "DashboardPage")//4 is status, 1 is jobID
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Order Creation")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
**/

/** commenting out because currently candidate page is broken. uncomment after fix
//Candidates
//Short Listed
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Candidate_Status_Combobox", "Short Listed", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Candidate_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Candidate_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_Table", "4", "Short Listed", "1", "DashboardPage")//4 is status, 1 is name
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Candidate Profile")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Offered
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Candidate_Status_Combobox", "Offered", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Candidate_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Candidate_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_Table", "4", "Offered", "1", "DashboardPage")//4 is status, 1 is name
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Candidate Profile")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Accepted - Documentation Pending
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Candidate_Status_Combobox", "Accepted - Documentation Pending", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Candidate_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Candidate_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_Table", "4", "Accepted - Documentation Pending", "1", "DashboardPage")//4 is status, 1 is name
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Candidate Profile")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
//Offered
DashboardPageSteps.getBaseSteps().selectItemByTextInComboBox("Candidate_Status_Combobox", "Offered", true, true, "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToControl("Candidate_Status_Combobox","DashboardPage")
DashboardPageSteps.getBaseSteps().waitForTableDataLoaded("Candidate_Table", "DashboardPage")
DashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_Table", "4", "Offered", "1", "DashboardPage")//4 is status, 1 is name
DashboardPageSteps.checkPageLoadAndErrorDisplayForLinkNavigation("Candidate Profile")
Browser.closeOpenedWindow()
Browser.switchToWindow(wd)
**/



@TearDown
def closeBrowser(){
	Browser.quitDriver();
}

