import core.Browser
import internal.GlobalVariable
import pages.CandidateOrderPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps
import pages.OrderCreationPageSteps
import pages.ReOrderSearchMatchPageSteps as ReOrderSearchMatchPageSteps
import core.AssertSteps as AsserSteps

'Step 1:'

'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL)

'ER:'

'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'

'Enter the Candidate Username and Password and Click on Login button.'
LoginPageSteps.loginWithCandidateId(TestUser, CandPWD)

'ER:'

'System should allow candidate user logged successfully.'
HeaderPageSteps.verifyLoginSuccessful()

'Click on Open Jobs'
HeaderPageSteps.selectCandidateSubMenu('Open Jobs')

CandidateOrderPageSteps.searchForJobWithOrderType(orderTypeName)

CandidateOrderPageSteps.getBaseSteps().selectCheckboxByContainedTextInComboBox('Client_ComboBox', 'Check All', 'CandidateOrderPage')

CandidateOrderPageSteps.getBaseSteps().waitForProgressBarDisappear()

CandidateOrderPageSteps.getBaseSteps().selectCheckboxByContainedTextInComboBox('Client_ComboBox', 'Loving Heart Health System', 
    'CandidateOrderPage')

CandidateOrderPageSteps.getBaseSteps().waitForProgressBarDisappear()

//CandidateOrderPageSteps.getBaseSteps().selectCheckboxByContainedTextInComboBox("Location_ComboBox", "Check All", "CandidateOrderPage")
//CandidateOrderPageSteps.getBaseSteps().waitForProgressBarDisappear()
//CandidateOrderPageSteps.getBaseSteps().selectCheckboxByContainedTextInComboBox("Location_ComboBox", "Defiance Hospital", "CandidateOrderPage")
//CandidateOrderPageSteps.getBaseSteps().waitForProgressBarDisappear()
CandidateOrderPageSteps.getBaseSteps().moveToControl('Search_button', 'CandidateOrderPage')

CandidateOrderPageSteps.getBaseSteps().clickToControlByJS('Search_button', 'CandidateOrderPage')

CandidateOrderPageSteps.getBaseSteps().waitForProgressBarDisappear()

CandidateOrderPageSteps.getBaseSteps().verifyTableDisplayed('Job_Table', 'CandidateOrderPage', 'Job List is Not Showing')

'Verifying the Order Details under  Search Result'
CandidateOrderPageSteps.getBaseSteps().verifyCellTextBaseOnAnotherCellDataTable('Job_Table', 'Job Title', jobTitle1, 'Job Title', 
    jobTitle1, 'CandidateOrderPage')

'Verifying the Order Details under  Search Result'
CandidateOrderPageSteps.getBaseSteps().verifyCellTextBaseOnAnotherCellDataTable('Job_Table', 'Job Title', jobTitle2, 'Job Title', 
    jobTitle2, 'CandidateOrderPage')

'LogOut from Candidate'
HeaderPageSteps.logOut()

'Loin with OrgAdmin to Check STATUS as Offered'

'Navigate to Order search and match from Organiztion menu.'

'Enter the Organization Username and Password and Click on Login button.'
LoginPageSteps.loginWithOrgUser()

HeaderPageSteps.selectOrganizationSubMenu('Re-Order Search & Match')

'ER:'

'System should display Order search and match screen.'
ReOrderSearchMatchPageSteps.verifyPageIsLoaded()

'Step 4:'

'In Order Search, enter Job Id then click Search button'
ReOrderSearchMatchPageSteps.getBaseSteps().setTextToControl('Job_ID_TextBox', jobId, 'ReOrderSearchMatchPage')

ReOrderSearchMatchPageSteps.getBaseSteps().setTextToControl('Start_Date_TextBox', startDate, 'ReOrderSearchMatchPage')

ReOrderSearchMatchPageSteps.getBaseSteps().setTextToControl('End_Date_TextBox', endDate, 'ReOrderSearchMatchPage')

ReOrderSearchMatchPageSteps.getBaseSteps().clickToControl('Search_Button', 'ReOrderSearchMatchPage')

'Verify Order list contains Job title'
ReOrderSearchMatchPageSteps.getBaseSteps().verifyTableBodyContainData('Order_List_Table', jobTitle, 'ReOrderSearchMatchPage')

'Step 5:'
'Select the In-Progress (Offer Accepted) order'

ReOrderSearchMatchPageSteps.getBaseSteps().verifyCellTextBaseOnAnotherCellDataTable('Order_List_Table', 'Job Title', jobTitle, 'Status', 'In-Progress', 'ReOrderSearchMatchPage')

'System displays Accepted candidate details in candidate list grid.'

String name=ReOrderSearchMatchPageSteps.getBaseSteps().getTextFromControl("candidate_Name", 'ReOrderSearchMatchPage')
println name

//ReOrderSearchMatchPageSteps.getBaseSteps().verifyTableBodyContainData('Candidate_List_Table', name, 'ReOrderSearchMatchPage')



///


'Step 1:'
'Log in as Agency'
LoginPageSteps.loginWithAgencyUser()
'ER:'
'System should allow orgnization user logged successfully.'
HeaderPageSteps.verifyLogginSuccessfully()

'Step 2:'
'Click on Re-Order Search & Match under Agency menu.'
HeaderPageSteps.selectAgencySubMenu("Re-Order Search & Match")
'ER:'
'System should display Re-Order Search & Match screen.'
ReOrderSearchMatchPageSteps.verifyPageIsLoaded()

'Step 3:'
'Search Order'
ReOrderSearchMatchPageSteps.searchOrderByJobIDAndDate(jobID, startDate,endDate)
'ER:'
'Order list table is displayed'
ReOrderSearchMatchPageSteps.getBaseSteps().verifyControlDisplayed("Order_List_Table", "ReOrderSearchMatchPage")

'Step 4:'
'Click to In-progress Order ID '
ReOrderSearchMatchPageSteps.getBaseSteps().clickToCellTableTextBaseOnAnotherCellDataTable("Order_List_Table", "1", jobID, "3", "ReOrderSearchMatchPage")// 1 is ID, 3 is Status
ReOrderSearchMatchPageSteps.getBaseSteps().waitForProgressBarDisappear();
'ER:'
'Verify the status of Candidate is Offerred'
String offeredStatusAgency = ReOrderSearchMatchPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Candidate_List_Table", "1", candidateName, "4", "ReOrderSearchMatchPage")// 1 is Name, 4 is Status
AssertSteps.verifyExpectedResult(offeredStatusAgency.equals("Offered"), String.format("The candidate %s status of Angency has %s status ",candidateName,offeredStatusAgency), "The status is not Offered")

'Step 5:'
'Click Action link on Offerred candidate'
ReOrderSearchMatchPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_List_Table", "1", candidateName, "2", "ReOrderSearchMatchPage")// 1 is ID, 2 is Action
ReOrderSearchMatchPageSteps.getBaseSteps().waitForProgressBarDisappear();
'ER:'
'Job Accept/Reject dialog is displayed with correct Job ID and Job Date'
ReOrderSearchMatchPageSteps.getBaseSteps().verifyControlDisplayed("Job_Accept_Reject_Dialog", "ReOrderSearchMatchPage")

'Step 6:'
'Click Save button'
ReOrderSearchMatchPageSteps.getBaseSteps().clickToControl("Job_Accept_Reject_Dialog_Save_Button", "ReOrderSearchMatchPage")
ReOrderSearchMatchPageSteps.getBaseSteps().waitForProgressBarDisappear();
'ER:'
'The candidate status is saved successfully and change to Accepted '
String acceptedStatusAgency = ReOrderSearchMatchPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Candidate_List_Table", "1", candidateName, "4", "ReOrderSearchMatchPage")// 1 is Name, 4 is Status
AssertSteps.verifyExpectedResult(!acceptedStatusAgency.equals(offeredStatusAgency), String.format("The candidate status is changed from %s to %s",offeredStatusAgency,acceptedStatusAgency), "The status is NOT changed")

//TC 58
'Step 7:'
'Enter the Org Username and Password and Click on Login button.'
HeaderPageSteps.logOut()
LoginPageSteps.loginWithOrgUser()
'System should allow orgnization user logged successfully.'
HeaderPageSteps.verifyLogginSuccessfully()

'Step 8:'
'Click on Re-Order Search & Match under Org menu.'
HeaderPageSteps.selectOrganizationSubMenu("Re-Order Search & Match")
'ER:'
'System should display Re-Order Search & Match screen.'
ReOrderSearchMatchPageSteps.verifyPageIsLoaded()

'Step 9:'
'Search Order'
ReOrderSearchMatchPageSteps.searchOrderByJobIDAndDate(jobID, startDate,endDate)
'ER:'
'Order list table is displayed'
ReOrderSearchMatchPageSteps.getBaseSteps().verifyControlDisplayed("Order_List_Table", "ReOrderSearchMatchPage")

'Step 10:'
'Click to an orderbase on job ID '
ReOrderSearchMatchPageSteps.getBaseSteps().clickToCellTableTextBaseOnAnotherCellDataTable("Order_List_Table", "1", jobID, "3", "ReOrderSearchMatchPage")// 1 is ID, 3 is Status
ReOrderSearchMatchPageSteps.getBaseSteps().waitForProgressBarDisappear();
'ER:'
'Verify the status of Candidate is Accepted'
String acceptedStatusOrg = ReOrderSearchMatchPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Candidate_List_Table", "1", candidateName, "4", "ReOrderSearchMatchPage")// 1 is Name, 4 is Status
AssertSteps.verifyExpectedResult(acceptedStatusOrg.equals("Accepted"), String.format("The candidate %s status of Org has %s status ",candidateName,acceptedStatusOrg), "The status is not Accepted")

'Step 11:'
'Click Action link on Accepted candidate'
ReOrderSearchMatchPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_List_Table", "1", candidateName, "2", "ReOrderSearchMatchPage")// 1 is ID, 2 is Action
ReOrderSearchMatchPageSteps.getBaseSteps().waitForProgressBarDisappear();
'ER:'
'Application_Events_Dialog is displayed with correct Job ID and Job Date'
ReOrderSearchMatchPageSteps.getBaseSteps().verifyControlDisplayed("Application_Events_Dialog", "ReOrderSearchMatchPage")
ReOrderSearchMatchPageSteps.getBaseSteps().verifyControlContainedValueDisplayed("Application_Events_Dialog_Job_ID_TextBox", jobID, "ReOrderSearchMatchPage")
ReOrderSearchMatchPageSteps.getBaseSteps().verifyControlContainedValueDisplayed("Application_Events_Dialog_Job_Date_TextBox", String.format("%s - %s",DateTimeUtil.convertDateStringFormat(startDate,"MM/dd/yyyy","M/d/yyyy"),DateTimeUtil.convertDateStringFormat(endDate,"MM/dd/yyyy","M/d/yyyy")), "ReOrderSearchMatchPage")

ReOrderSearchMatchPageSteps.getBaseSteps().selectItemByTextInComboBox("Application_Events_Dialog_Actions_ComboBox", "Onboard", "ReOrderSearchMatchPage")
ReOrderSearchMatchPageSteps.getBaseSteps().waitForProgressBarDisappear()

ReOrderSearchMatchPageSteps.getBaseSteps().verifyControlContainedValueDisplayed("Application_Events_Dialog_Actual_Start_Date_TextBox", startDate, "ReOrderSearchMatchPage")
ReOrderSearchMatchPageSteps.getBaseSteps().verifyControlContainedValueDisplayed("Application_Events_Dialog_Actual_End_Date_TextBox", endDate, "ReOrderSearchMatchPage")

'Step 12:'
'Click Ok button'
ReOrderSearchMatchPageSteps.getBaseSteps().clickToControl("Application_Events_Dialog_Ok_Button", "ReOrderSearchMatchPage")
ReOrderSearchMatchPageSteps.getBaseSteps().waitForProgressBarDisappear()
'Search Order'
Browser.refreshCurrentPage()
ReOrderSearchMatchPageSteps.searchOrderByJobIDAndDate("",startDate,endDate)
ReOrderSearchMatchPageSteps.getBaseSteps().waitForProgressBarDisappear()
'ER:'
'The candidate status is changed to Onboard status'
String onboardStatusOrg = ReOrderSearchMatchPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Candidate_List_Table", "1", candidateName, "4", "ReOrderSearchMatchPage")// 1 is Name, 4 is Status
AssertSteps.verifyExpectedResult(onboardStatusOrg.equals("Onboard"), String.format("The candidate status is changed to %s",onboardStatusOrg), "The status is NOT changed")

String orderStatus = ReOrderSearchMatchPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("Order_List_Table", "4", jobTitle, "3", "ReOrderSearchMatchPage")// 1 is ID, 3 is Status
AssertSteps.verifyExpectedResult(orderStatus.equals("Filled"), String.format("The oder status oforder is changed to %s",orderStatus), "The status is NOT changed")
