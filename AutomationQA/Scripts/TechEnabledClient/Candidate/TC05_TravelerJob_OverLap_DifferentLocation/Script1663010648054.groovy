
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
Browser.start(GlobalVariable.URL)

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