package pages

import com.google.common.base.Strings

import core.BaseSteps
import core.ControlFactory

public class HeaderPageSteps {

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================

	public static void logOut() {
		getBaseSteps().clickToControl("Setting_Menu_Combobox", "HeaderPage")
		getBaseSteps().clickToControl("LogOut_Link", "HeaderPage")
		getBaseSteps().waitForControlDisplay("LogIn_Form", "LoginPage");
	}

	public static void selectHomeMenu() {
		getBaseSteps().clickToControl("Home_Menu_Button", "HeaderPage")
	}

	public static void selectOrganizationSubMenu(String itemMenu) {
		getBaseSteps().selectSubMenu("Orgarnization_Menu_Button", itemMenu,"HeaderPage");
	}

	public static void selectAgencySubMenu(String itemMenu) {
		getBaseSteps().selectSubMenu("Agency_Menu_Button", itemMenu,"HeaderPage");
	}

	public static void selectAdminSubMenu(String itemMenu) {
		getBaseSteps().selectSubMenu("Admin_Menu_Button", itemMenu,"HeaderPage");
	}

	public static void selectCandidateSubMenu(String itemMenu) {
		getBaseSteps().selectSubMenu("Candidate_Menu_Button", itemMenu,"HeaderPage");
	}

	public static void selectReportSubMenu(String itemMenu,String subItemMenu="") {
		getBaseSteps().selectSubMenu("Report_Menu_Button", itemMenu,subItemMenu,"HeaderPage");

	}



	// =================== ASSERT METHODS ZONE ======================
	public static void verifyLogginSuccessfully() {
		getBaseSteps().verifyControlDisplayed("Setting_Menu_Combobox", "HeaderPage")
	}

	public static void verifyLoginSuccessful() {
		getBaseSteps().verifyControlDisplayed("Candidate_Menu_Button", "HeaderPage")
	}
}
