import core.AssertSteps
import core.Browser
import internal.GlobalVariable
import pages.CandidateDashboardPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps as LoginPageSteps


'Step 1:'

'Enter the Application URL in browser.'
Browser.start(GlobalVariable.URL00)

'ER:'

'System should display VMS Login page.'
LoginPageSteps.verifyPageIsLoaded()

'Step 2:'

'Enter the Candidate Username and Password and Click on Login button.'
LoginPageSteps.loginWithCandidateUser()

'ER:'

'System should allow candidate user logged successfully.'
HeaderPageSteps.verifyLoginSuccessful()

'Step 3:'

'Click on Open Jobs Quick Link'
CandidateDashboardPageSteps.getBaseSteps().clickToControl('OpenJobsLink', 'CandidateDashboardPage')

CandidateDashboardPageSteps.getBaseSteps().waitForProgressBarDisappear()

'ER:'

'Job List master table should be displayed.'
CandidateDashboardPageSteps.getBaseSteps().verifyControlDisplayed('JobListMasterTable', 'CandidateDashboardPage')

'Step 4:'
'Verify JobID created is found in the master table.'
String strJobName = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "Job Title", "CandidateDashboardPage")
String strStartDate = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "Start Date", "CandidateDashboardPage")
String strShiftName = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "Shift Name", "CandidateDashboardPage")
String strOrgName = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "Organization Name", "CandidateDashboardPage")
String strClientName = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "Client Name", "CandidateDashboardPage")
String strLocName = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "Location Name", "CandidateDashboardPage")
String strDeptName = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "Department Name", "CandidateDashboardPage")
String strAgencyName = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "Agency Name", "CandidateDashboardPage")
String strOrderType = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "OrderType", "CandidateDashboardPage")
String strDuration = CandidateDashboardPageSteps.getBaseSteps().getCellTextBaseOnAnotherCellDataTable("JobListMasterTable", "Job ID", jobID, "Duration Type", "CandidateDashboardPage")

//'ER:'
//'Job details for the job created should be displayed correctly on candidate dashboard page.'
String[] actualData = [strJobName, strStartDate, strShiftName, strOrgName, strClientName, strLocName, strDeptName, strAgencyName, strOrderType, strDuration]
String[] expectedData = ["TE_OJ_01", "07/01/2022", "07:00-19:30", "Trinity Health Solutions", "Norton", "LocationN1", "Department 1", "Austin Agency", "Traveler", "(26) Weeks"]
println(actualData)
boolean result = Arrays.equals(actualData, expectedData)
AssertSteps.verifyActualResult(result, "Job details displayed on candidate dashboard page is correct.", "Incorrect job details on candidate dashboard page.")
'Step 5:'

'Click on Interested link for the required jobID.'

CandidateDashboardPageSteps.getBaseSteps().clickToCellTableLinkBaseOnAnotherCellDataTable('JobListMasterTable', 'Job ID', 
    jobID, 'Action', 'CandidateDashboardPage')

'ER:'

'Interested pop up should be displayed.'
CandidateDashboardPageSteps.getBaseSteps().verifyControlDisplayed('btnSave', 'CandidateDashboardPage')

'Step 6:'

'Click on Save button.'
CandidateDashboardPageSteps.getBaseSteps().clickToControl('btnSave', 'CandidateDashboardPage')
'Interested pop up should be closed and the selected jobID action should have be in green color.'
CandidateDashboardPageSteps.getBaseSteps().verifyControlNotDisplayed('btnSave', 'CandidateDashboardPage')

CandidateDashboardPageSteps.getBaseSteps().getXpathFromControl(controlName, pageName)
CandidateDashboardPageSteps.getBaseSteps().getTextFromControlByReplacedTextInXpath(xpath, text)(controlName, attributeName, pageName)
String strColor = CandidateDashboardPageSteps.getBaseSteps().getBackgroundColorFromTableLinkBaseOnAnotherCellDataTable('JobListMasterTable', 
    'Job ID', jobID, 'Action', 'CandidateDashboardPage')

println('Colore ======= ' + strColor)

//WebUI.getCSSValue(findTestObject(null), '')

