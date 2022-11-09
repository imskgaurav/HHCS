package pages

import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable



public class LoginPageSteps {

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	public static void loginWithOrgUser() {
		getBaseSteps().setTextToControl("Email_TextBox", GlobalVariable.ORG_USER, "LoginPage")
		getBaseSteps().setEncodeTextToControl("Password_TextBox", GlobalVariable.ORG_PW, "LoginPage")
		getBaseSteps().clickToControl("LogIn_Button","LoginPage")
		getBaseSteps().waitForControlDoesNotDisplay("Progress_Image","CommonLocators")
	}

	public static void loginWithAgencyUser() {
		getBaseSteps().setTextToControl("Email_TextBox", GlobalVariable.AGC_USER, "LoginPage")
		getBaseSteps().setEncodeTextToControl("Password_TextBox", GlobalVariable.AGC_PW, "LoginPage")
		getBaseSteps().clickToControl("LogIn_Button","LoginPage")
		getBaseSteps().waitForControlDoesNotDisplay("Progress_Image","CommonLocators");
	}

	public static void loginWithAdminUser() {
		getBaseSteps().setTextToControl("Email_TextBox", GlobalVariable.VMS_USER, "LoginPage")
		getBaseSteps().setEncodeTextToControl("Password_TextBox", GlobalVariable.VMS_PW, "LoginPage")
		getBaseSteps().clickToControl("LogIn_Button","LoginPage")
		getBaseSteps().waitForControlDoesNotDisplay("Progress_Image","CommonLocators");
	}

	//Adding the Code for Login with ProLinkAgency//
	public static void loginWithProLinkAgency() {

		getBaseSteps().setTextToControl("Email_TextBox", GlobalVariable.ProLink_AgencyUSER, "LoginPage")
		getBaseSteps().setEncodeTextToControl("Password_TextBox", GlobalVariable.ProLink_PW, "LoginPage")
		getBaseSteps().clickToControl("LogIn_Button","LoginPage")
		getBaseSteps().waitForControlDoesNotDisplay("Progress_Image","CommonLocators");

	}
	public static void verifyPageIsLoaded() {
		getBaseSteps().verifyControlDisplayed("LogIn_Form", "LoginPage");
	}

	/**@Description - Logs in with candidate login user creds
	 @Author - Hiral Jogi**/
	public static void loginWithCandidateUser() {
		getBaseSteps().setTextToControl("Email_TextBox", GlobalVariable.CAND_USER, "LoginPage")
		getBaseSteps().setEncodeTextToControl("Password_TextBox", GlobalVariable.CAND_PW, "LoginPage")
		getBaseSteps().clickToControl("LogIn_Button","LoginPage")
		getBaseSteps().waitForControlDoesNotDisplay("Progress_Image","CommonLocators");
	}

	public static void loginWithCandidateId(String candLogin,String password) {
		getBaseSteps().setTextToControl("Email_TextBox", candLogin, "LoginPage")
		getBaseSteps().setEncodeTextToControl("Password_TextBox", password, "LoginPage")
		getBaseSteps().clickToControl("LogIn_Button","LoginPage")
		getBaseSteps().waitForControlDoesNotDisplay("Progress_Image","CommonLocators");
		
	}
}
