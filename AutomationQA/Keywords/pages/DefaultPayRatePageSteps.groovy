package pages
import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import utils.DateTimeUtil

public class DefaultPayRatePageSteps {

	private static url = '/PayrateLookupsDetails.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================

	public static void clickGetDetailsButton() {
		getBaseSteps().clickToControl("Get_Details_Button","DefaultPayRateDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectDepartment(String department) {
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectCheckboxByContainedTextInComboBoxHasAList("Department_Combobox","Select all","DefaultPayRateDetailsPage")//uncheck all option
		getBaseSteps().selectCheckboxByContainedTextInComboBoxHasAList("Department_Combobox",department,false,"DefaultPayRateDetailsPage")
		getBaseSteps().clickToControl("Rate_Time_Textbox","DefaultPayRateDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectLocation(String location) {
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectCheckboxByContainedTextInComboBoxHasAList("Location_Combobox","Select all","DefaultPayRateDetailsPage")//uncheck all option
		getBaseSteps().selectCheckboxByContainedTextInComboBoxHasAList("Location_Combobox",location,false,"DefaultPayRateDetailsPage")
		getBaseSteps().clickToControl("Rate_Time_Textbox","DefaultPayRateDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void selectPayRateDescription(String payRateDescription) {
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectItemByTextInComboBox("Pay_Rate_Description_Combobox",payRateDescription, "DefaultPayRateDetailsPage")
		//getBaseSteps().clickToControl("Rate_Time_Textbox","DefaultPayRateDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static void selectRegion(String region) {
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Region_Combobox","Check All","DefaultPayRateDetailsPage") //uncheck all option
		getBaseSteps().selectCheckboxByContainedTextInComboBox("Region_Combobox",region,"DefaultPayRateDetailsPage")
		getBaseSteps().clickToControl("Region_Arrow_Icon_Combobox","DefaultPayRateDetailsPage")
		getBaseSteps().clickToControl("Rate_Time_Textbox","DefaultPayRateDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static void selectSkill(String skill) {
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectCheckboxByContainedTextInComboBoxHasAList("Skill_Combobox","Select all","DefaultPayRateDetailsPage")//uncheck all option
		getBaseSteps().selectCheckboxByContainedTextInComboBoxHasAList("Skill_Combobox",skill,false,"DefaultPayRateDetailsPage")
		getBaseSteps().clickToControl("Rate_Time_Textbox","DefaultPayRateDetailsPage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	//need new
	public static String getDefaultPayRate(String payRateDesc, String searchItem) {
		HashMap<Integer, String> mapConditionData = new HashMap<Integer, String>();
		// add elements to map
		mapConditionData.put("Pay Rate Desc.", payRateDesc);
		mapConditionData.put("Display in Job", "checked");
		mapConditionData.put("Region Name", searchItem);

		return getBaseSteps().getCellTextBaseOnMultipleCellDataTable("Pay_Rate_Details_Table", mapConditionData, "Rate/Times", "DefaultPayRateDetailsPage")
	}
	public static String getDefaultPayRateBasedOnSpecificCombination(String payRateDesc, String regionName, String locationName,String departmentName,String skill) {
		HashMap<Integer, String> mapConditionData = new HashMap<Integer, String>();
		// add elements to map
		mapConditionData.put("Region Name", regionName);
		mapConditionData.put("Location Name", locationName);
		mapConditionData.put("Department Name", departmentName);
		mapConditionData.put("Skill Name", skill);
		mapConditionData.put("Pay Rate Desc.", payRateDesc);
		mapConditionData.put("Display in Job", "checked");
		return getBaseSteps().getCellTextBaseOnMultipleCellDataTable("Pay_Rate_Details_Table", mapConditionData, "Rate/Times", "DefaultPayRateDetailsPage")
	}

	public static String getEffectiveDateBasedOnSpecificCombination(String payRateDesc, String regionName, String locationName,String departmentName,String skill) {
		HashMap<Integer, String> mapConditionData = new HashMap<Integer, String>();
		// add elements to map
		mapConditionData.put("Region Name", regionName);
		mapConditionData.put("Location Name", locationName);
		mapConditionData.put("Department Name", departmentName);
		mapConditionData.put("Skill Name", skill);
		mapConditionData.put("Pay Rate Desc.", payRateDesc);
		mapConditionData.put("Display in Job", "checked");
		return getBaseSteps().getCellTextBaseOnMultipleCellDataTable("Pay_Rate_Details_Table", mapConditionData, "Effective Date", "DefaultPayRateDetailsPage")
	}

	public static String getEffectiveDateBasedOnRateTimes(String payRateDesc, String regionName, String locationName,String departmentName,String skill, String rateTimes) {
		HashMap<Integer, String> mapConditionData = new HashMap<Integer, String>();
		// add elements to map
		mapConditionData.put("Region Name", regionName);
		mapConditionData.put("Location Name", locationName);
		mapConditionData.put("Department Name", departmentName);
		mapConditionData.put("Skill Name", skill);
		mapConditionData.put("Pay Rate Desc.", payRateDesc);
		mapConditionData.put("Display in Job", "checked");
		mapConditionData.put("Rate/Times", rateTimes);
		return getBaseSteps().getCellTextBaseOnMultipleCellDataTable("Pay_Rate_Details_Table", mapConditionData, "Effective Date", "DefaultPayRateDetailsPage")
	}

	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
