import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import core.AssertSteps as AssertSteps
import core.Browser as Browser
import pages.CandidateCreationPageSteps as CandidateCreationPageSteps
import pages.CandidateDashboardPageSteps as CandidateDashboardPageSteps
import pages.HeaderPageSteps as HeaderPageSteps
import pages.LoginPageSteps as LoginPageSteps
import pages.CandidateOrderPageSteps as CandidateOrderPageSteps
import pages.OrderSearchPageSteps
import pages.OrderCreationPageSteps

'Step 1:'

'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL00)

'ER:'

'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'

'Enter the Candidate Username and Password and Click on Login button.'
LoginPageSteps.loginWithCandidateId(GlobalVariable.CAND_USER, GlobalVariable.CAND_PW)

'ER:'

'System should allow candidate user logged successfully.'
HeaderPageSteps.verifyLoginSuccessful()

'Step:'

'Click on Open Jobs Quick Link'
CandidateDashboardPageSteps.getBaseSteps().clickToControlByJS('OpenJobsLink', 'CandidateDashboardPage')
CandidateDashboardPageSteps.getBaseSteps().waitForProgressBarDisappear()
CandidateOrderPageSteps.searchForJobWithOrderType(orderTypeName)
ArrayList<String> actualList = new ArrayList<String>()
actualList=CandidateOrderPageSteps.findTheJobDetails(jobID)
//Creating a ArrayList with Expected Result//
ArrayList<String> expectedList = new ArrayList<String>(Arrays.asList(jobName, orderTypeName, startingDate, shiftTime))
println(actualList.equals(expectedList))
AssertSteps.verifyActualResult(actualList.equals(expectedList), "Job details displayed on candidate Page is correct.", "Incorrect job details on candidate page.")
HeaderPageSteps.logOut()
//Verifying with 2nd Candidate Login //
'Enter the Candidate Username and Password and Click on Login button.'
LoginPageSteps.loginWithCandidateId(GlobalVariable.CAND_USER1, GlobalVariable.CAND_PW1)

'ER:'
'System should allow candidate user logged successfully.'
HeaderPageSteps.verifyLoginSuccessful()

'Step'

'Click on Open Jobs Quick Link'
CandidateDashboardPageSteps.getBaseSteps().clickToControlByJS('OpenJobsLink', 'CandidateDashboardPage')

CandidateDashboardPageSteps.getBaseSteps().waitForProgressBarDisappear()

CandidateOrderPageSteps.searchForJobWithOrderType(orderTypeName)
actualList.clear()
actualList=CandidateOrderPageSteps.findTheJobDetails(jobID)
println(actualList)
AssertSteps.verifyActualResult(actualList.equals(expectedList), "Job details displayed on candidate Page is correct.", "Incorrect job details on candidate page.")
HeaderPageSteps.logOut()

//Login with 3rd Candidate 
'Enter the Candidate Username and Password and Click on Login button.'
LoginPageSteps.loginWithCandidateId(GlobalVariable.CAND_USER2, GlobalVariable.CAND_PW2)

'ER:'

'System should allow candidate user logged successfully.'
HeaderPageSteps.verifyLoginSuccessful()

'Step:'
'Click on Open Jobs Quick Link'
CandidateDashboardPageSteps.getBaseSteps().clickToControlByJS('OpenJobsLink', 'CandidateDashboardPage')
CandidateDashboardPageSteps.getBaseSteps().waitForProgressBarDisappear()

CandidateOrderPageSteps.searchForJobWithOrderType(orderTypeName)
actualList.clear()
actualList=CandidateOrderPageSteps.findTheJobDetails(jobID)
println actualList
AssertSteps.verifyActualResult(actualList.equals(expectedList), "Job details displayed on candidate Page is correct.", "Incorrect job details on candidate page.")
HeaderPageSteps.logOut()
//////Check Candidate is Available with Status Not Applied //
//
//'Enter the Agency  Username and Password and Click on Login button.'
//LoginPageSteps.loginWithAgencyUser()
//'ER:'
//'System should allow Agency user logged successfully and show Logim name as Acency'
//HeaderPageSteps.verifyLogginSuccessfully()
//HeaderPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Login_Name_Label", "Agency", "HeaderPage")
//
//'Step 3:'
//'Click on Order and search match under Agency menu'
//HeaderPageSteps.selectAgencySubMenu("Order Search & Match")
//'ER:'
//'System should display Order and search match screen.'
//OrderSearchPageSteps.verifyPageIsLoaded()
//
//'Step 4:'
//'Verify order list grid.'
//'ER:'
//'System display orders in the order list grid.'
//OrderSearchPageSteps.getBaseSteps().verifyControlDisplayed("Order_List_Table", "OrderSearchPage")
//
//'Step 5:'
//'Select the Open Order from the order list grid.'
//OrderSearchPageSteps.getBaseSteps().setTextToControl("Job_Id_Textbox", jobID, "OrderSearchPage")
//OrderSearchPageSteps.getBaseSteps().clickToControl("Search_Button", "OrderSearchPage")
//OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()
//OrderSearchPageSteps.getBaseSteps().clickToCellTableTextBaseOnAnotherCellDataTable("Order_List_Table","Job Title", jobName, "Status", "OrderSearchPage")
//'ER:'
//'System displays job matching candidates in the candidate list grid and status of candidate is Not Applied'
//OrderSearchPageSteps.getBaseSteps().verifyControlDisplayed("Candidate_List_Table", "OrderSearchPage")
//OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Not Applied")
//
//'Step 6:'
//'Click on action for the candidate.'
//OrderSearchPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_List_Table",
//	"Name", candidateName, "Action", "OrderSearchPage")
//OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()
//'ER:'
//'Popup gets open to Apply for Job and the Bill Rate Details Table displays'
//OrderSearchPageSteps.getBaseSteps().verifyControlDisplayed("Apply_For_Job_Dialog", "OrderSearchPage")
//OrderSearchPageSteps.getBaseSteps().verifyTableDisplayed("Bill_Rate_Details_Table", "OrderSearchPage")
//
//'Step 7:'
//'Enter an Available Date and click on Apply radio button.'
//OrderSearchPageSteps.getBaseSteps().setTextToControl("Available_Start_Date_TextBox", available_start_date, "OrderSearchPage")
//OrderSearchPageSteps.getBaseSteps().clickToControl("Apply_Dialog_Button", "OrderSearchPage")
//'ER:'
////'System displays message as Job Application Saved Successfully.'
////OrderCreationPageSteps.getBaseSteps().verifyMainPopUpHasContent(successAppliedJobApplicationSaveContent)
//
//'Step 8:'
//'Click on Search page so status of the job can reflect'
//OrderSearchPageSteps.getBaseSteps().clickToControl("Search_Button", "OrderSearchPage")
//'ER:'
//'Wait for page tp load'
//OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()
//
//'Step 9:'
//'Verify Job status and candidate status for respective Job.'
//'ER:'
//'System displays the Job status as In-Progress and candidate  status as -Applied.'
//OrderCreationPageSteps.verifyJobStatusAs(jobName,"In-Progress")
//OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Applied")
//HeaderPageSteps.logOut()
////Candidate Apply for Job: The status of Job is : InProgress
//
////
//
//'Step 2:'
//'Enter the Organization Username and Password and Click on Login button.'
//LoginPageSteps.loginWithOrgUser()
//'ER:'
//'System should allow Organization user logged successfully and show Logim name as Organization'
//HeaderPageSteps.verifyLogginSuccessfully()
//HeaderPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Login_Name_Label", "Organization", "HeaderPage")
//
//'Step 3:'
//'Navigate to Order search and match from organiztion menu.'
//HeaderPageSteps.selectOrganizationSubMenu("Order Search & Match")
//'ER:'
//'System should display Order search and match screen.'
//OrderSearchPageSteps.verifyPageIsLoaded()
//
//'Step 4:'
//'Select the In-Progress order'
//OrderSearchPageSteps.getBaseSteps().clickToCellTableTextBaseOnAnotherCellDataTable("Order_List_Table",
//	"Job Title", jobName, "Status", "OrderSearchPage")
//'ER:'
//'Verify Candidate status is Applied in the grid'
//OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Applied")
//
//'Step 5:'
//'Click  on candidate action.'
//OrderSearchPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_List_Table",
//	"Name", candidateName, "Action", "OrderSearchPage")
//'ER:'
//'Applicant event popup gets open'
//OrderSearchPageSteps.getBaseSteps().verifyControlDisplayed("Apply_For_Job_Dialog", "OrderSearchPage")
//
//'Step 6:'
//'Select the status from Applied to Shortlisted under action dropdown and click on Ok.'
//OrderSearchPageSteps.getBaseSteps().selectItemByTextInComboBox("Actions_Dialog_Combobox", "Short Listed", "OrderSearchPage")
//OrderSearchPageSteps.getBaseSteps().clickToControl("Ok_Dialog_Button", "OrderSearchPage")
//'ER:'
//'System displays message as Candidate is Short Listed.'
////OrderSearchPageSteps.getBaseSteps().verifyMainPopUpHasContent(candidateName.toString().concat(GlobalVariable.CLIENT_NAME+" is Short Listed"))
//
//'Step 7:'
//'Verify Job status and candidate status for respective Job.'
//'ER:'
//'System display the Job status as In-Progress and candidate  status as -Shortlisted.'
//OrderCreationPageSteps.verifyJobStatusAs(jobName,"In-Progress")
//OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Short Listed")
//
//'Step 8:'
//'Click  on candidate action to open popup'
//OrderSearchPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_List_Table",
//	"Name", candidateName, "Action", "OrderSearchPage")
//'ER:'
//'Verify the status in dropdownlist in the popup is Short List'
//OrderSearchPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Apply_For_Job_Dialog", "Short Listed",  "OrderSearchPage")
//
//HeaderPageSteps.logOut()
//
////Schedule the Candiadte 
//
'Step 2:'
'Enter the Organization Username and Password and Click on Login button.'
LoginPageSteps.loginWithOrgUser()
'ER:'
'System should allow Organization user logged successfully and show Logim name as Organization'
HeaderPageSteps.verifyLogginSuccessfully()
HeaderPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Login_Name_Label", "Organization", "HeaderPage")

'Step 3:'
'Navigate to Order search and match from Organiztion menu.'
HeaderPageSteps.selectOrganizationSubMenu("Order Search & Match")
'ER:'
'System should display Order search and match screen.'
OrderSearchPageSteps.verifyPageIsLoaded()

'Step 4:'
'In Order Search, enter Job Id then click Search button'
OrderSearchPageSteps.getBaseSteps().setTextToControl("Job_Id_Textbox", jobID, "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().clickToControl("Search_Button", "OrderSearchPage")
'Verify Order list contains Job title'
OrderCreationPageSteps.getBaseSteps().verifyTableBodyContainData("Order_List_Table", jobName, "OrderSearchPage")

'Step 5:'
'Select the In-Progress order'
OrderSearchPageSteps.getBaseSteps().clickToCellTableTextBaseOnAnotherCellDataTable("Order_List_Table",
	"Job Title", jobName, "Status", "OrderSearchPage")

'ER:'
'Verify Candidate status is Short Listed in the grid'
OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Short Listed")

'Step 6:'
'Click  on candidate action.'
OrderSearchPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_List_Table",
	"Name", candidateName, "Action", "OrderSearchPage")
'ER:'
'Applicant event dialog gets open'
OrderSearchPageSteps.getBaseSteps().verifyControlDisplayed("Apply_For_Job_Dialog", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().waitForTableDataLoaded("Bill_Rate_Details_Table", "OrderSearchPage")

'Step 7:'
'Select the status from Shortlisted to Scheduled under action dropdown and click on Ok.'
OrderSearchPageSteps.getBaseSteps().selectItemByTextInComboBox("Actions_Dialog_Combobox", "Scheduled", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().clickToControl("Ok_Dialog_Button", "OrderSearchPage")
'ER:'
'System displays message as Candidate is Scheduled.'
//OrderSearchPageSteps.getBaseSteps().verifyMainPopUpHasContent(candidateName.toString().concat(GlobalVariable.CLIENT_NAME+" is Scheduled"))

'Step 8:'
'Verify Job status and candidate status for respective Job.'
'ER:'
'System display the Job status as In-Progress and candidate  status as Scheduled.'
OrderCreationPageSteps.verifyJobStatusAs(jobName,"In-Progress")
OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Scheduled")

'Step 9:'
'Select the In-Progress order'
OrderSearchPageSteps.getBaseSteps().clickToCellTableTextBaseOnAnotherCellDataTable("Order_List_Table",
	"Job Title", jobName, "Status", "OrderSearchPage")

'Step 10:'
'Click  on candidate action.'
OrderSearchPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_List_Table",
	"Name", candidateName, "Action", "OrderSearchPage")
'ER:'
'Applicant event dialog gets open.'
OrderSearchPageSteps.getBaseSteps().verifyControlDisplayed("Apply_For_Job_Dialog", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().waitForTableDataLoaded("Bill_Rate_Details_Table", "OrderSearchPage")

'Step 11:'
'Select the status from Scheduled to Offered under action dropdown and click on Ok.'
OrderSearchPageSteps.getBaseSteps().selectItemByTextInComboBox("Actions_Dialog_Combobox", "Offered", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().setTextToControl("Offered_Start_Date_TextBox", offered_start_date, "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().clickToControl("Ok_Dialog_Button", "OrderSearchPage")

/////

'Step 12:'
'Click on Search page so status of the job can reflect'
OrderSearchPageSteps.getBaseSteps().clickToControl("Search_Button", "OrderSearchPage")
'ER:'
'Wait for page tp load'
OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()

'Step 13:'
'Verify Job status and candidate status for respective Job.'
'ER:'
'System display the Job status as In-Progress and candidate  status as Offered.'
OrderCreationPageSteps.verifyJobStatusAs(jobName,"In-Progress (Offer Pending)")
OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Offered")

HeaderPageSteps.logOut()

///Job Status is : Offered Acepted and 
'Step 2:'
'Enter the Agency Username and Password and Click on Login button.'
LoginPageSteps.loginWithAgencyUser()
'ER:'
'System should allow Agency user logged successfully and show Logim name as Acency'
HeaderPageSteps.verifyLogginSuccessfully()
HeaderPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Login_Name_Label", "Agency", "HeaderPage")

'Step 3:'
'Click on Order and search match under Agency menu'
HeaderPageSteps.selectAgencySubMenu("Order Search & Match")
'ER:'
'System should display Order and search match screen.'
OrderSearchPageSteps.verifyPageIsLoaded()

'Step 4:'
'In Order Search, enter Job Id then click Search button'
OrderSearchPageSteps.getBaseSteps().setTextToControl("Job_Id_Textbox", jobID, "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().clickToControl("Search_Button", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()
'Verify Order list contains Job title'
OrderSearchPageSteps.getBaseSteps().verifyTableBodyContainData("Order_List_Table", jobName, "OrderSearchPage")

'Step 5:'
'Select the In-Progress (Offer Pending)order.'
OrderSearchPageSteps.getBaseSteps().clickToCellTableTextBaseOnAnotherCellDataTable("Order_List_Table",
	"Job Title", jobName, "Status", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()
'ER:'
'System displays offered candidate details in candidate list grid.'
OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Offered")

'Step 6:'
'Click on candidate action.'
OrderSearchPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable("Candidate_List_Table",
	"Name", candidateName, "Action", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()
'ER:'
'Applicant event dialog gets open.'
OrderSearchPageSteps.getBaseSteps().verifyControlDisplayed("Apply_For_Job_Dialog", "OrderSearchPage")

'Step 7:'
'Select the status from Offered to Accepted under action dropdown and click on Save.'
OrderSearchPageSteps.getBaseSteps().selectItemByTextInComboBox("Actions_Dialog_Combobox", "Accepted", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().clickToControl("Save_Dialog_Button", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()
'ER:'
//'System displays message as Candidate is Accepted.'
//OrderSearchPageSteps.getBaseSteps().verifyMainPopUpHasContent(successAcceptedJobApplicationSaveContent)

'Step 8:'
'Click on Search page so status of the job can reflect'
OrderSearchPageSteps.getBaseSteps().clickToControl("Search_Button", "OrderSearchPage")
'ER:'
'Wait for page tp load'
OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()

'Step 9:'
'Verify Job status and candidate status for respective Job.'
'ER:'
'System display the Job status as In-Progress (M) (Offer Accepted)" and candidate  status as Accepted'
OrderCreationPageSteps.verifyJobStatusAs(jobName,"In-Progress (M) (Offer Accepted)")
OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Accepted")
HeaderPageSteps.logOut()


'Login with Organiztion login with valid credentials.'
LoginPageSteps.loginWithOrgUser()
'ER:'
'User logged succesfully.'
HeaderPageSteps.verifyLogginSuccessfully()

'Step 11:'
'Navigate to order search and match from organiztion menu.'
HeaderPageSteps.selectOrganizationSubMenu("Order Search & Match")
'ER:'
'System should display order search and match screen.'
OrderSearchPageSteps.verifyPageIsLoaded()
HeaderPageSteps.getBaseSteps().verifyControlContainedTextDisplayed("Login_Name_Label", "Organization", "HeaderPage")

'Step 12:'
'In Order Search, enter Job Id then click Search button'
OrderSearchPageSteps.getBaseSteps().setTextToControl("Job_Id_Textbox", jobID, "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().clickToControl("Search_Button", "OrderSearchPage")
OrderSearchPageSteps.getBaseSteps().waitForProgressBarDisappear()
'Verify Order list contains Job title'
OrderSearchPageSteps.getBaseSteps().verifyTableBodyContainData("Order_List_Table", jobName, "OrderSearchPage")

'Step 13:'
'Verify Job status and candidate status for respective Job.'
'ER:'
'System display the Job status as In-Progress (Offer Accepted)" and candidate  status as Accepted'
OrderCreationPageSteps.verifyJobStatusAs(jobName,"In-Progress (Offer Accepted)")
OrderCreationPageSteps.verifyCandidateStatusAs(candidateName,"Accepted")

