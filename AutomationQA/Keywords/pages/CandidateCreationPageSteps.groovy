package pages
import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import core.AssertSteps
import core.BaseSteps
import core.Browser
import core.ControlFactory
import utils.Utilities
public class CandidateCreationPageSteps {
	private static url = '/Candidatecreation.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================


	public static void clickNewCandidateButton(){

		getBaseSteps().clickToControl("New_Candidate_Button", "CandidateCreationPage")
		getBaseSteps().waitForProgressBarDisappear()
	}


	public static void fillCandidatePopUpDetails(String firstName, lastName,primarySkill,dob,successSaveContent) {
		CandidateCreationPageSteps.clickNewCandidateButton()
		'System should display Template Selection dialog'
		CandidateCreationPageSteps.getBaseSteps().verifyControlDisplayed('Candidate_Profile_Template', 'CandidateCreationPage')
		'STEP  4:  fill Candiadate template  DETAILS '
		CandidateCreationPageSteps.fillCandidateProfileTemplate(firstName, lastName)
		//CandidateCreationPageSteps.getBaseSteps().verifyMainPopUpHasContent(successSaveContent)
		//Candidate Form Filling
		CandidateCreationPageSteps.fillCandidateDetails(primarySkill,dob)

	}

	public static void fillProfessonalSummaryAndContactDetails(String profSummarytxt,stateName, city, countryName, address, address1, zipCode) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Prof_Summary_txtBox', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('Prof_Summary_txtBox', profSummarytxt, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForControlDisplay('Address_TextBox', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Country_input', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickLabelwithText("Country_ddl", countryName, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().clickToControl('State_Input', 'CandidateCreationPage')
		//CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS('State_ddl', stateName, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickLabelwithText('State_ddl', stateName, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().moveToControl('City_txtBox', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().clickToControl('City_txtBox', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('City_txtBox', city, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForControlDisplay('Address_TextBox', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Address_TextBox', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('Address_TextBox', address, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForControlDisplay('Address_TextBox1', 'CandidateCreationPage')
		//CandidateCreationPageSteps.getBaseSteps().clearTextInControl('Address_TextBox1', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Address_TextBox1', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('Address_TextBox1', address1, 'CandidateCreationPage')
		//CandidateCreationPageSteps.getBaseSteps().setTextToControl('Address_TextBox1', address1, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().clickToControl('ZipCode_txtBox', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('ZipCode_txtBox', zipCode, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

	}

	public static  void  fillCandidateProfileTemplate(String fName, String lastName) {

		def ssnNum=generateSSNum()
		def firstName=generateFirstName( fName)
		CandidateCreationPageSteps.getBaseSteps().clickToControl("SSNum_Input", "CandidateCreationPage")
		//getBaseSteps().clearTextInControl("SSNum_Input", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("SSNum_Input", ssnNum, "CandidateCreationPage")
		getBaseSteps().clickToControl("First_Name_Input", "CandidateCreationPage")
		//getBaseSteps().clearTextInControl("First_Name_Input", "CandidateCreationPage")
		getBaseSteps().setTextToControl("First_Name_Input", firstName, "CandidateCreationPage")
		getBaseSteps().waitForControlDisplay("Last_Name_Input", "CandidateCreationPage")
		getBaseSteps().clickToControl("Last_Name_Input", "CandidateCreationPage")
		//getBaseSteps().clearTextInControl("Last_Name_Input", "CandidateCreationPage")
		getBaseSteps().setTextToControl("Last_Name_Input", lastName, "CandidateCreationPage")
		String email=generateEmailAdd()
		getBaseSteps().waitForControlDisplay("Email_Input", "CandidateCreationPage")
		getBaseSteps().clickToControl("Email_Input", "CandidateCreationPage")
		//getBaseSteps().clearTextInControl("Email_Input", "CandidateCreationPage")
		getBaseSteps().setTextToControl("Email_Input", email, "CandidateCreationPage")
		getBaseSteps().clickToControl("Confirm_Email_Input", "CandidateCreationPage")
		getBaseSteps().setTextToControl("Confirm_Email_Input", email, "CandidateCreationPage")
		getBaseSteps().clickToControl("OK_Button", "CandidateCreationPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static String generateSSNum()
	{
		String num1 = Utilities.generateRandomNumber(3)
		String num2 = Utilities.generateRandomNumber(2)
		String num3 = Utilities.generateRandomNumber(4)
		String ssnNum = String.format("%s-%s-%s", num1, num2,num3)
		return ssnNum
	}

	public static String generateEmailAdd() {

		Random randomGenerator = new Random()
		int randomInt = randomGenerator.nextInt(5000)
		String userName = "agent";
		String emailAdd= userName+randomInt+"@ymail.com";

		return emailAdd
	}

	public static String generateFirstName(String firstName) {

		Random rn = new Random()
		int randomInt =rn.nextInt(1999)
		String fname= String.format("%s%s", firstName, randomInt)
		//fname = fname+rn
		return fname
	}


	public static void fillOldCandidateProfileandContactDetails(firstName, lastName,primarySkill, dob,profSummarytxt, stateName, city, countryName, addresstxt, address1, zipCode) {

		CandidateCreationPageSteps.clickNewCandidateButton()

		CandidateCreationPageSteps.getBaseSteps().verifyControlDisplayed('Candidate_Profile_Template', 'CandidateCreationPage')
		'STEP  4:  fill Candiadate template  DETAILS '
		CandidateCreationPageSteps.fillCandidateProfileTemplate(firstName, lastName)

		CandidateCreationPageSteps.fillCandidateDetails(primarySkill, dob)
		CandidateCreationPageSteps.fillProfessonalSummaryAndContactDetails(profSummarytxt, stateName, city, countryName, addresstxt, address1, zipCode)

	}

	public static void fillOldCandidateExperienceDetails(String employerName, jobTitle, startMonYear, endMonYear) {
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Experienece_Tab', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.fillEmployerDetails(employerName, jobTitle, startMonYear, endMonYear)

	}

	public static void fillOldCandidateEducationDetails(String degreeType, schoolName, gradMonthYear, fieldOfStudy) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Educational_Tab', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS('Add_New_EduRecord', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		//Educational Record Details
		CandidateCreationPageSteps.fillEducationalDetails(degreeType, schoolName, gradMonthYear, fieldOfStudy)

		CandidateCreationPageSteps.saveEducationDetails()

	}

	public static searchCandidateOnOldProfilePage(String candidateName) {


		CandidateCreationPageSteps.getBaseSteps().clickToControl('Candidate_Search', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().setTextToControl('Candidate_Search', candidateName, 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().clickLabelwithText('Candidate_auto_list', candidateName, 'CandidateCreationPage')

	}

	public static void updateCandidateProfileDetails(String firstName, lastName, primarySkill1, dob1) {

		firstName=generateFirstName(firstName)
		lastName= generateFirstName(lastName)
		getBaseSteps().clickToControl("First_Name_TextBox", "CandidateCreationPage")
		getBaseSteps().clearTextInControl("First_Name_TextBox", "CandidateCreationPage")
		getBaseSteps().setTextToControl("First_Name_TextBox", firstName, "CandidateCreationPage")
		getBaseSteps().clickToControl("Last_Name_TextBox", "CandidateCreationPage")
		getBaseSteps().clearTextInControl("Last_Name_TextBox", "CandidateCreationPage")
		getBaseSteps().setTextToControl("Last_Name_TextBox", lastName, "CandidateCreationPage")
		String email=generateEmailAdd()

		getBaseSteps().clickToControl("Email_txtBox", "CandidateCreationPage")
		getBaseSteps().clearTextInControl("Email_txtBox", "CandidateCreationPage")
		getBaseSteps().setTextToControl("Email_txtBox", email, "CandidateCreationPage")

		getBaseSteps().clickToControl('Primary_Skill_Combobox', 'CandidateCreationPage')

		getBaseSteps().clickLabelwithText('Skills_option', primarySkill1, 'CandidateCreationPage')

		getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().clickToControl('DOB_input', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clearTextInControl('DOB_input', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('DOB_input', dob1, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Save_Button', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

	}

	public static void updateEmployerAndEducationDetails(String employerName, jobTitle, startMonYear, endMonYear,degreeType, schoolName, gradMonthYear, fieldOfStudy) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Experienece_Tab', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.fillEmployerDetails(employerName, jobTitle, startMonYear, endMonYear)
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Educational_Tab', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS('Add_New_EduRecord', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		//Educational Record Details
		CandidateCreationPageSteps.fillEducationalDetails(degreeType, schoolName, gradMonthYear, fieldOfStudy)
		println "Updated the educational Details"

	}


	public static void fillAddressDetails (String profSummarytxt, addresstxt, stateName,countryName) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Prof_Summary_txtBox', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().setTextToControl('Prof_Summary_txtBox', profSummarytxt, 'CandidateCreationPage')

		//Fill Address details
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('Address_TextBox', addresstxt, 'CandidateCreationPage')

		//click on state and select the State name from Dropdownlist//
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Address_TextBox1', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().setTextToControl('Address_TextBox1', addresstxt, 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().clickToControl('State_Input', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().clickLabelwithText('State_Input', stateName, 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().waitForControlDisplay('Country_input', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Country_input', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().clickLabelwithText('Country_input', countryName, 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()


	}

	public static void fillCandidateDetails(def primarySkill, dob) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Primary_Skill_Combobox', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().clickLabelwithText('Skills_option', primarySkill, 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Address_TextBox', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().clickToControl('DOB_input', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clearTextInControl('DOB_input', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('DOB_input', dob, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		//CandidateCreationPageSteps.getBaseSteps().waitForControlClickable('Save_Button', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().moveToControl('Save_Button', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Save_Button', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()


	}

	public static void updateExperienceDetails() {

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Experienece_Tab', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()



	}
	public static void clickAddNewExpRecord() {
		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS("Add_New_Record", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

	}
	public static void addEmployerDetails(String employerName, String jobTitle,String startMonYear, endMonYear) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl("Employer_Input", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Employer_Input", employerName, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Job_Title", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Job_Title", jobTitle, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Start_MonYear_Input", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Start_MonYear_Input", startMonYear, "CandidateCreationPage")

		CandidateCreationPageSteps.getBaseSteps().clickToControl("End_MonYear_Input", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("End_MonYear_Input", endMonYear, "CandidateCreationPage")

		CandidateCreationPageSteps.getBaseSteps().clickToControl("Comments_txtBox", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Comments_txtBox", "Input Text2", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Table_Header", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
	}

	public static void fillEducationalDetails(String degreeType, String schoolName, String gradMonthYear, fieldOfStudy) {
		CandidateCreationPageSteps.getBaseSteps().clickToControl('DegreeType_Input', "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickLabelwithText("DegreeType_options", degreeType, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("School_Name", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("School_Name", schoolName, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Graduation_MonYear", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Graduation_MonYear", gradMonthYear, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForControlDisplay("Field_ofStudy", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Field_ofStudy", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Field_ofStudy", fieldOfStudy, "CandidateCreationPage")
		println "EDUCATIONAL details Completed"
	}

	public static void saveEducationDetails() {

		CandidateCreationPageSteps.getBaseSteps().clickToControl("Edu_Header_Row", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS("Save_Edu_Icon", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		///Credentials Tab //
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Credential_Tab", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
	}


	public static void updateCredentialsDetails(String certified_on,certified_untill, filePath) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Unassigned_Radio_button', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Credential_Type', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS('Credential_Type_chkBox', 'Check All', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS('Credential_Type_chkBox', "License", 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Get_Details_button', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		//Uploading the File//

		CandidateCreationPageSteps.getBaseSteps().clickToControl('License_Nur_chkBox', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl('License_row', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("CertifiedOn_Lic", certified_on, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("CertifiedUntil_Lic", certified_untill, 'CandidateCreationPage')
		CandidateCreationPageSteps.uploadLicenseCredDoc(filePath)
	}

	public static void addDocsforOldCandidateProfile(String filePath, agencyName, docName,successUpdateDoc,profileUpdateContent) {

		CandidateCreationPageSteps.addDocumentDetails(filePath, agencyName, docName)

		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS('Save_btn_DocIcon', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().verifyMainPopUpHasContent(successUpdateDoc)

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Mark_complete_btn', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

		CandidateCreationPageSteps.getBaseSteps().verifyMainPopUpHasContent(profileUpdateContent)


	}

	public static void fillSelectedCredentialType(def certified_on, certified_untill) {
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Credential_Lic_ChkBox1', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Select_Status', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl('CertifiedOn_Lic', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('CertifiedOn_Lic', certified_on, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl('CertifiedUntil_Lic', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('CertifiedUntil_Lic', certified_untill, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()


	}
	public static void uploadLicenseCredDoc(def filePath) {

		CandidateCreationPageSteps.getBaseSteps().moveToControl('select_file', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl('select_file', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.fileUpload(filePath)
		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS('Save_changes_btn', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Unassigned_Radio_button', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

	}


	public static void getprofessionalCredentialsDetails(String credType, credType1) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl("Credential_Type", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS("Credential_Type_chkBox", credType, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS("Credential_Type_chkBox", credType1, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Get_Details_button", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()


	}

	public static void fillProfessionalCredDetails(def filePath) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Credential_Prof_chkBox1', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl('select_Row', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().moveToControl('Select_Prof_File', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().uploadFile("Select_Prof_File", filePath, 'CandidateCreationPage')
		CandidateCreationPageSteps.fileUpload(filePath)
		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS('Save_btn_Icon', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

	}


	public static void getCredentialDetails(String credentialType) {
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Credential_Type", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS("Credential_Type_chkBox", "Check All", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS("Credential_Type_chkBox", credentialType, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Get_Details_button", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

	}


	public static void fillEmployerDetails(String employerName, jobTitle, startMonYear,endMonYear) {

		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS("Add_New_Record", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Employer_Input", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Employer_Input", employerName, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Job_Title", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Job_Title", jobTitle, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Start_MonYear_Input", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Start_MonYear_Input", startMonYear, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForControlDisplay("End_MonYear_Input", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("End_MonYear_Input", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("End_MonYear_Input", endMonYear, "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Comments_txtBox", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().setTextToControl("Comments_txtBox", "Test Input", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Table_Header", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().moveToControl("Save_Change_icon", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()


	}

	public static void saveEmployerDetails() {


		CandidateCreationPageSteps.getBaseSteps().moveToControl("Save_Change_icon", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Table_Header", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Educational_Tab", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		//CandidateCreationPageSteps.getBaseSteps().clickToControlByJS("Save_Change_icon", "CandidateCreationPage")
		//CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()

	}


	public static void addDocumentDetails(def filePath, agencyName,docName) {

		CandidateCreationPageSteps.getBaseSteps().clickToControl('Documents_Tab', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS('Add_New_Doc', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Agency_Name_Input', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS('Ageny_NameList', agencyName, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Document_Type', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickLabelwithText('Document_Type_list', docName, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().moveToControl('Upload_docs', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Upload_docs', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		//Upload the File//
		CandidateCreationPageSteps.fileUpload(filePath)
		CandidateCreationPageSteps.getBaseSteps().clickToControl('File_Desr_Input', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().setTextToControl('File_Desr_Input', "Test", 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Document_Header', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
	}

	public static void saveOldCandidatePageRecords(successUpdateDoc,profileUpdateContent) {
		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS('Save_btn_DocIcon', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().verifyMainPopUpHasContent(successUpdateDoc)
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Mark_complete_btn', 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().verifyMainPopUpHasContent(profileUpdateContent)


	}


	public static String getUpdateCandidateName() {

		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS("Profile_Tab", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForControlDisplay("First_Name_TextBox", 'CandidateCreationPage')
		//		CandidateCreationPageSteps.getBaseSteps().clickToControl("First_Name_TextBox", 'CandidateCreationPage')
		//		String fName= CandidateCreationPageSteps.getBaseSteps().getValueFromControl("First_Name_TextBox", 'CandidateCreationPage')
		//		CandidateCreationPageSteps.getBaseSteps().clickToControl("Last_Name_TextBox", 'CandidateCreationPage')
		//		String lName= CandidateCreationPageSteps.getBaseSteps().getValueFromControl("Last_Name_TextBox", 'CandidateCreationPage')
		//		String fullName= lName.concat(", ").concat(fName)
		//		return fullName

		String candidateName=getCandidateNameFromProfileTab()
		return candidateName

	}

	public static String getCandidateNameFromProfileTab() {

		CandidateCreationPageSteps.getBaseSteps().clickToControl("First_Name_TextBox", 'CandidateCreationPage')
		String fName= CandidateCreationPageSteps.getBaseSteps().getValueFromControl("First_Name_TextBox", 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Last_Name_TextBox", 'CandidateCreationPage')
		String lName= CandidateCreationPageSteps.getBaseSteps().getValueFromControl("Last_Name_TextBox", 'CandidateCreationPage')
		String fullName= lName.concat(", ").concat(fName)
		return fullName
	}

	public static void searchOldCandidateProfile(String candidateName) {

		//Search with Candidate Name//
		CandidateCreationPageSteps.getBaseSteps().clickToControl('Candidate_Search', 'CandidateCreationPage')

		CandidateCreationPageSteps.getBaseSteps().setTextToControl('Candidate_Search', candidateName, 'CandidateCreationPage')

		//CandidateCreationPageSteps.getBaseSteps().clickLabelwithText('Candidate_auto_list', candidateName, 'CandidateCreationPage')
		CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS('Candidate_auto_list', candidateName, 'CandidateCreationPage')
		getBaseSteps().waitForProgressBarDisappear()



	}



	public static void verifyCandidateIsFoundOnSearch() {

		CandidateCreationPageSteps.getBaseSteps().clickToControlByJS("Candidate_Search", "CandidateCreationPage")
		String candidateName= CandidateCreationPageSteps.getBaseSteps().getValueFromControl("Candidate_Search", 'CandidateCreationPage')
		HeaderPageSteps.selectAgencySubMenu('Candidate Profile')
		CandidateCreationPageSteps.verifyPageIsLoaded()
		CandidateCreationPageSteps.searchOldCandidateProfile(candidateName)
		CandidateCreationPageSteps.getBaseSteps().clickToControl("First_Name_TextBox", 'CandidateCreationPage')
		String fName= CandidateCreationPageSteps.getBaseSteps().getValueFromControl("First_Name_TextBox", 'CandidateCreationPage')
		println candidateName
		println fName
		boolean result= candidateName.contains(fName)
		AssertSteps.verifyExpectedResult(result)
	}

	public static void showSelectedCredentialsDetails() {

		CandidateCreationPageSteps.getBaseSteps().clickToControl("Assigned_Radio_button", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Credential_Type", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickLabelTextWithJS("Credential_Type_chkBox", "Check All", "CandidateCreationPage")
		CandidateCreationPageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateCreationPageSteps.getBaseSteps().clickToControl("Get_Details_button", "CandidateCreationPage")


	}
	public static void  fileUpload(String filePath) {

		Robot rb = new Robot()
		rb.delay(1000)
		StringSelection ss = new StringSelection(filePath)
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null)
		rb.keyPress(KeyEvent.VK_CONTROL)
		rb.keyPress(KeyEvent.VK_V)
		rb.delay(1000)
		rb.keyRelease(KeyEvent.VK_CONTROL)
		rb.keyRelease(KeyEvent.VK_V)
		rb.delay(1000)
		rb.keyPress(KeyEvent.VK_ENTER)
		rb.keyRelease(KeyEvent.VK_ENTER)


	}


	public static void takeScreenShot() {

		Browser.takeScreenshot();
		WebUiBuiltInKeywords.takeFullPageScreenshot();

	}

	public static void clickOkButtonToConfirmDialog() {

		getBaseSteps().clickToControl("OK_Button", "CandidateCreationPage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	///NEED to Shift //
	
	

	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
