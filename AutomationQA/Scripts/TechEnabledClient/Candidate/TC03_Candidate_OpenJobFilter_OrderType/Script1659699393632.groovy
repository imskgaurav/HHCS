import core.Browser
import internal.GlobalVariable
import pages.CandidateDashboardPageSteps
import pages.CandidateOrderPageSteps as CandidateOrderPageSteps
import pages.HeaderPageSteps
import pages.LoginPageSteps 
import core.AssertSteps
import java.util.List
import java.util.stream.Collectors



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
String rowNum= CandidateOrderPageSteps.getBaseSteps().getRowTableCounts("Table_joblist", "CandidateOrderPage")
int rowCount=Integer.parseInt(rowNum)
for (int i=1;i<=rowCount; i++) {
String rowNumber=String.valueOf(i).toString()
println rowNumber
String cellValue=CandidateOrderPageSteps.getBaseSteps().getCellTableText("Table_joblist", rowNumber, "OrderType", "CandidateOrderPage")
//println cellValue
actualList.add(cellValue)
}
println actualList
List<String> newList = actualList.stream().distinct().collect(Collectors.toList());

// Print the ArrayList with duplicates removed
System.out.println("ArrayList with duplicates removed: "+ newList);
"Fliter for Order Type : Open Per Diem"
ArrayList<String> oderTypeList = new ArrayList<String>()
oderTypeList=CandidateOrderPageSteps.verifyTheOrderTypeFilterFunctionalityOnPage(orderTypeName1)

println oderTypeList

"Filter All Order Type"
CandidateOrderPageSteps.searchForJobWithOrderType(orderTypeAll)

oderTypeList.clear()
oderTypeList=CandidateOrderPageSteps.verifyTheOrderTypeFilterFunctionalityOnPage(orderTypeAll)

println oderTypeList

///Collect the Order Type Value in List
HeaderPageSteps.logOut()