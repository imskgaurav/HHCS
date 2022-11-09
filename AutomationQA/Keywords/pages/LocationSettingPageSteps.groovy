package pages

import core.BaseSteps
import core.ControlFactory

public class LocationSettingPageSteps {
	private static url = '/OrgLocationSetting.aspx';


	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}



	public static void selectLocation(String location) {
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectCheckboxByContainedTextInComboBoxHasAList("Location_Combobox","Select all","LocationSettingPage")//uncheck all option
		getBaseSteps().selectCheckboxByContainedTextInComboBoxHasAList("Location_Combobox",location,false,"LocationSettingPage")
		getBaseSteps().clickToControl("By_Location_RadioButton","LocationSettingPage")
		getBaseSteps().waitForProgressBarDisappear()
	}





	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
