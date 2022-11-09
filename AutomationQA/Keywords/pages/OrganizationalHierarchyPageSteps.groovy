package pages


import core.BaseSteps
import core.ControlFactory

import internal.GlobalVariable

public class OrganizationalHierarchyPageSteps {

	private static url = '/Organization_Widget.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	// =================== ACTION METHODS ZONE ======================
	public static void selectRegion(String region) {
		getBaseSteps().selectItemByTextInComboBox("Region_Combobox",region,"OrganizationalHierarchyPage")
		getBaseSteps().waitForProgressBarDisappear()

	}
	public static String getFieldOnTableBaseOnLocation(String columnGetText, String location) {
		return getBaseSteps().getCellTextBaseOnAnotherCellDataTable ("Location_Table", "Location Name", location, columnGetText, "OrganizationalHierarchyPage")
	}


	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);

	}
}
