package pages

import internal.GlobalVariable

import core.AssertSteps
import core.BaseSteps
import core.Browser
import core.ControlFactory
import utils.Utilities
import java.util.List
import java.util.stream.Collectors

public class CandidateOrderPageSteps {
	private static url = '/Candidatecreation.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================
	public static void searchForJobWithOrderType( String orderType) {

		getBaseSteps().clickToControl("OrderType_Arrow", "CandidateOrderPage")
		getBaseSteps().waitForControlDisplay("OrderType_list", "CandidateOrderPage")
		getBaseSteps().clickLabelwithText("OrderType_list",orderType, "CandidateOrderPage")
		getBaseSteps().waitForControlDisplay("Search_button",, "CandidateOrderPage")
		getBaseSteps().clickToControl("Search_button", "CandidateOrderPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static ArrayList<String> findTheJobDetails(String jobID){
		def jobTitle = getBaseSteps().getCellTextBaseOnAnotherCellDataTable('JobListMasterTable', 'Job ID',
				jobID, 'Job Title', 'CandidateDashboardPage')
		def orderType = getBaseSteps().getCellTextBaseOnAnotherCellDataTable('JobListMasterTable', 'Job ID',
				jobID, 'OrderType', 'CandidateDashboardPage')
		def startDate = getBaseSteps().getCellTextBaseOnAnotherCellDataTable('JobListMasterTable', 'Job ID',
				jobID, 'Start Date', 'CandidateDashboardPage')

		def shiftName = getBaseSteps().getCellTextBaseOnAnotherCellDataTable('JobListMasterTable', 'Job ID',
				jobID, 'Shift Name', 'CandidateDashboardPage')

		ArrayList<String> actualList = new ArrayList<String>(Arrays.asList(jobTitle, orderType, startDate, shiftName))

		return actualList



	}

	public static  ArrayList<String> verifyTheOrderTypeFilterFunctionalityOnPage(String orderType){


		searchForJobWithOrderType(orderType)
		ArrayList<String> actualList = new ArrayList<String>()
		String rowNum= CandidateOrderPageSteps.getBaseSteps().getRowTableCounts("Table_joblist", "CandidateOrderPage")
		int rowCount=Integer.parseInt(rowNum)
		for (int i=1;i<=rowCount; i++) {
			String rowNumber=String.valueOf(i).toString()
			String cellValue=CandidateOrderPageSteps.getBaseSteps().getCellTableText("Table_joblist", rowNumber, "OrderType", "CandidateOrderPage")
			actualList.add(cellValue)
		}
		println actualList
		List<String> newList = actualList.stream().distinct().collect(Collectors.toList());
		// Print the ArrayList with duplicates removed
		System.out.println("ArrayList with duplicates removed: "+ newList);
		return newList

	}

}
