package pages
import core.BaseSteps
import internal.GlobalVariable
import core.ControlFactory
import core.Browser
import utils.PlatformUtil
import configs.Path
import utils.Utilities
import java.awt.AWTException
import java.awt.Robot
import java.awt.event.KeyEvent

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords


public class CandidateProfilePageSteps {
	private static String url ="/NewCandidateProfile.aspx"

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	// =================== ACTION METHODS ZONE ======================


	public static void enterFirstName(firstName) {

		getBaseSteps().clickToControl("First_Name_TextBox", "CandidateProfilePage")
		getBaseSteps().setTextToControl("First_Name_TextBox", firstName, "CandidateProfilePage")

	}
	public static void enterLastName(lastName) {
		CandidateProfilePageSteps.getBaseSteps().clickToControl("Last_Name_TextBox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().setTextToControl("Last_Name_TextBox", lastName, "CandidateProfilePage")

	}

	public static void enterEmailAddress(def email) {

		getBaseSteps().clickToControl("Email_TextBox", "CandidateProfilePage")

		getBaseSteps().setTextToControl("Email_TextBox", email, "CandidateProfilePage")

	}

	public static void enterSSNNo(def ssn) {

		getBaseSteps().clickToControl("SSN_TextBox", "CandidateProfilePage")

		getBaseSteps().setTextToControl("SSN_TextBox", ssn, "CandidateProfilePage")

	}

	public static void enterDOB(def dob) {

		getBaseSteps().clickToControl("Date_of_Birth_Input", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Date_of_Birth_Input", dob, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickToControl("Profile_Summary_TextBox", "CandidateProfilePage")

	}

	//Candidate Profile details

	public static void fillCandidateAndContactDetails(String firstName, lastName, primarySkill, dob, profSummary, stateName, countryName, address, address1, cityName, zipCode) {

		CandidateProfilePageSteps.setCandidateProfileDetails(firstName,lastName,primarySkill,dob)
		CandidateProfilePageSteps.getBaseSteps().clickToControl("Profile_Summary_TextBox","CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().setTextToControl("Profile_Summary_TextBox", profSummary, "CandidateProfilePage")
		CandidateProfilePageSteps.fillAdddressDetails(stateName, countryName, address, address1, cityName, zipCode)



	}

	public static void setCandidateProfileDetails(String  firstName, lastName,primarySkill,dob ) {
		firstName =generateFirstName(firstName)
		println firstName
		getBaseSteps().clickToControl("First_Name_TextBox", "CandidateProfilePage")
		getBaseSteps().setTextToControl("First_Name_TextBox", firstName, "CandidateProfilePage")

		getBaseSteps().clickToControl("Last_Name_TextBox", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Last_Name_TextBox", lastName, "CandidateProfilePage")
		//Generating Random email and passing it
		enterEmailAddress(CandidateProfilePageSteps.generateEmailAdd())
		//Creating dynamic ssNum
		def ssNum=CandidateProfilePageSteps.generateSSNum()
		enterSSNNo(ssNum)

		getBaseSteps().selectCheckboxByContainedTextInComboBox('Primary_Skill', primarySkill, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

		enterDOB(dob)


	}

	public static void editCandidateProfileDetails(String firstName, lastName, primarySkill1) {

		CandidateProfilePageSteps.getBaseSteps().clickToControl("First_Name_TextBox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clearTextInControl("First_Name_TextBox", "CandidateProfilePage")
		String fName1=CandidateProfilePageSteps.generateFirstName(firstName)
		CandidateProfilePageSteps.getBaseSteps().setTextToControl("First_Name_TextBox", fName1, "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clickToControl("Last_Name_TextBox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clearTextInControl("Last_Name_TextBox", "CandidateProfilePage")
		String lName=CandidateProfilePageSteps.generateFirstName(lastName)
		CandidateProfilePageSteps.getBaseSteps().setTextToControl("Last_Name_TextBox", lName, "CandidateProfilePage")

		CandidateProfilePageSteps.getBaseSteps().clickToControl("Email_TextBox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clearTextInControl("Email_TextBox", "CandidateProfilePage")
		String email =CandidateProfilePageSteps.generateEmailAdd()
		CandidateProfilePageSteps.getBaseSteps().setTextToControl("Email_TextBox", email, "CandidateProfilePage")

		getBaseSteps().selectCheckboxByContainedTextInComboBox('Primary_Skill', primarySkill1, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()



	}

	public static void fillAdddressDetails(String stateName, countryName, address, address1, cityName, zipCode) {

		CandidateProfilePageSteps.getBaseSteps().clickToControl("State_Input", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clickLabelwithText("State_ddl", stateName, "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().waitForProgressBarDisappear()
		CandidateProfilePageSteps.getBaseSteps().clickToControl("Country_Input", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clickLabelwithText("Country_Input", countryName, "CandidateProfilePage")

		CandidateProfilePageSteps.getBaseSteps().clickToControl("Address_txtbox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clearTextInControl("Address_txtbox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().setTextToControl("Address_txtbox", address, "CandidateProfilePage")

		CandidateProfilePageSteps.getBaseSteps().clickToControl("Adress1_txtbox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clearTextInControl("Adress1_txtbox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().setTextToControl("Adress1_txtbox", address1, "CandidateProfilePage")

		CandidateProfilePageSteps.getBaseSteps().clickToControl("City_txtbox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clearTextInControl("City_txtbox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().setTextToControl("City_txtbox", cityName, "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clickToControl("Zipcode_txtbox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clearTextInControl("Zipcode_txtbox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().setTextToControl("Zipcode_txtbox", zipCode, "CandidateProfilePage")








	}
	//EMPLOYER details //

	public static void fillExperienceDetails(String employerName,jobTitle,startMonYear,endMonYear ) {

		clickAddnew()
		getBaseSteps().scrollToViewElement("Add_New_Button", "CandidateProfilePage")
		setEmployername("Employer_Input",employerName)
		getBaseSteps().scrollToMiddle()
		setJobTitle("Job_Title_Input",jobTitle)
		//Browser.getDriverContext().get(Browser.getCurrentURL())
		getBaseSteps().scrollToViewElement("Start_MonthYear", "CandidateProfilePage")
		setStartMonYear("Start_MonthYear",startMonYear)
		getBaseSteps().scrollToMiddle()
		setEndMonYear("End_MonthYear",endMonYear)
		getBaseSteps().scrollToViewElement("End_MonthYear", "CandidateProfilePage")
		clickYearOfExperince()


	}
	public static void fillSecondRowExperienceDetails(String employerName,jobTitle,startMonYear,endMonYear) {
		clickAddnew()
		getBaseSteps().scrollToViewElement("Add_New_Button", "CandidateProfilePage")
		getBaseSteps().clickToControl("Employer_Input1", "CandidateProfilePage")
		getBaseSteps().scrollToMiddle()
		getBaseSteps().setTextToControl("Employer_Input1", employerName, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().scrollToViewElement("Job_Title_Input1", "CandidateProfilePage")
		getBaseSteps().clickToControl("Job_Title_Input1", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Job_Title_Input1", jobTitle, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().scrollToViewElement("Start_MonthYear1", "CandidateProfilePage")
		getBaseSteps().clickToControl("Start_MonthYear1","CandidateProfilePage")
		getBaseSteps().setTextToControl("Start_MonthYear1", startMonYear, "CandidateProfilePage")
		//Wait for the Visibility of  Next Control
		getBaseSteps().waitForControlDisplay("End_MonthYear1","CandidateProfilePage")
		getBaseSteps().scrollToViewElement("End_MonthYear1", "CandidateProfilePage")
		getBaseSteps().moveToControl("End_MonthYear1","CandidateProfilePage")
		getBaseSteps().clickToControl("End_MonthYear1","CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clearTextInControl("End_MonthYear1","CandidateProfilePage")
		getBaseSteps().setTextToControl("End_MonthYear1", endMonYear, "CandidateProfilePage")
		getBaseSteps().waitForControlDisplay("Year_of_Exp1", "CandidateProfilePage")
		pressTABKey()
		//getBaseSteps().waitForProgressBarDisappear()
		/*setEmployername("Employer_Input1",employerName)
		 setJobTitle("Job_Title_Input1",jobTitle)
		 setStartMonYear("Start_MonthYear1",startMonYear)
		 setEndMonYear("End_MonthYear1",endMonYear)*/
		getBaseSteps().clickToControl("Year_of_Exp1", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()


	}



	//Education details

	public static void fillEducationDetails(String degreeType,schoolName, grad_monthYear ) {
		getBaseSteps().scrollToViewElement("Add_New_Button1", "CandidateProfilePage")
		CandidateProfilePageSteps.clickAddButtonEdu()
		CandidateProfilePageSteps.getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().scrollToViewElement("Degree_options", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clickToControl("Degree_Type",  "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clickLabelwithText("Degree_options", degreeType, "CandidateProfilePage")
		CandidateProfilePageSteps.enterSchoolName(schoolName)
		getBaseSteps().scrollToViewElement("Graduation_MonYear", "CandidateProfilePage")
		CandidateProfilePageSteps.enterGraduationMonYear(grad_monthYear)
	}

	public static void fillsecondRowEducationalDetails(String degreeType,schoolName, grad_monthYear ) {

		CandidateProfilePageSteps.clickAddButtonEdu()
		CandidateProfilePageSteps.getBaseSteps().clickToControl("Degree_Type1",  "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clickLabelwithText("Degree_Type1_opt", degreeType, "CandidateProfilePage")
		getBaseSteps().clickToControl("School_Name_Input1", "CandidateProfilePage")
		getBaseSteps().setTextToControl("School_Name_Input1", schoolName, "CandidateProfilePage")
		getBaseSteps().clickToControl("Graduation_MonYear1", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Graduation_MonYear1", grad_monthYear, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()




	}

	//
	public static void fillCertificationDetails(String certificationOnDate, certificationUntillDate,pdfFileName) {

		CandidateProfilePageSteps.selectCertificationCheckBox()
		CandidateProfilePageSteps.setCertification_OnDate(certificationOnDate)
		CandidateProfilePageSteps.setCertification_UntilDate(certificationUntillDate)
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,pdfFileName))
		println filePath
		CandidateProfilePageSteps.getBaseSteps().uploadFile("Choose_File", filePath, "CandidateProfilePage")
		CandidateProfilePageSteps.clickSave()
		println "Data Saved"

	}

	public static void selectCredTypesLicenseandFillDetails(String controlName, String credType,String pdfFileName) {
		getBaseSteps().clickToControlByJS(controlName, "CandidateProfilePage")
		//getBaseSteps().clickToControl(controlName, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().scrollToViewElement("Dropdown_list", "CandidateProfilePage")
		getBaseSteps().clickLabelTextWithJS("Dropdown_list", "Check All", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickLabelTextWithJS("Dropdown_list", credType, "CandidateProfilePage")
		getBaseSteps().scrollToViewElement("Dropdown_list", "CandidateProfilePage")
		getBaseSteps().waitForControlDisplay("Get_Details", "CandidateProfilePage")
		getBaseSteps().scrollToViewElement("Get_Details", "CandidateProfilePage")
		getBaseSteps().clickToControlByJS("Get_Details", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().scrollToMiddle()
		getBaseSteps().clickToControlByJS("Unassigned_RadioBtn", "CandidateProfilePage")
		getBaseSteps().waitForControlDisplay("Lic_Nursys_chkBox", "CandidateProfilePage")
		getBaseSteps().clickToControlByJS("Lic_Nursys_chkBox", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickToControl("Cert_On", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Cert_On", "02/22/2020", "CandidateProfilePage")
		getBaseSteps().clickToControl("Cert_Until", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Cert_Until", "02/22/2024", "CandidateProfilePage")
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,pdfFileName))
		//println filePath
		CandidateProfilePageSteps.getBaseSteps().uploadFile("Choose_File1", filePath, "CandidateProfilePage")
		CandidateProfilePageSteps.clickSave()
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void selectCredTypeCertificationBLS_FillDetails(String controlName, String credType1, String pdfFileName) {
		// Check All and then uncheck , then select certification
		//println "Calling Function"
		getBaseSteps().scrollToViewElement(controlName, "CandidateProfilePage")
		getBaseSteps().clickToControlByJS(controlName, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickLabelTextWithJS("Dropdown_list", "Check All", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickLabelTextWithJS("Dropdown_list", "Check All", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickLabelTextWithJS("Dropdown_list", credType1, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickToControlByJS("Unassigned_RadioBtn", "CandidateProfilePage")
		getBaseSteps().waitForControlDisplay("Get_Details", "CandidateProfilePage")
		getBaseSteps().scrollToViewElement("Get_Details", "CandidateProfilePage")
		getBaseSteps().clickToControlByJS("Get_Details", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

		def text=getBaseSteps().getTextFromControl("CredTypeName", "CandidateProfilePage")
		println text.trim()

		getBaseSteps().waitForControlDisplay("Control_checkBox2", "CandidateProfilePage")
		getBaseSteps().clickToControlByJS("Control_checkBox2", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

		getBaseSteps().clickToControl("BLS_Cert_On", "CandidateProfilePage")
		getBaseSteps().setTextToControl("BLS_Cert_On", "02/22/2022", "CandidateProfilePage")
		getBaseSteps().clickToControl("BLS_Cert_until", "CandidateProfilePage")
		getBaseSteps().setTextToControl("BLS_Cert_until", "02/22/2027", "CandidateProfilePage")
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,pdfFileName))
		//println filePath
		CandidateProfilePageSteps.getBaseSteps().uploadFile("BLS_Choose_File", filePath, "CandidateProfilePage")
		//CandidateProfilePageSteps.clickSave()
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void selectCerttificationtype_ProfessionalBackGroundCheck(String controlName, String profcredType,pdfFileName) {

		getBaseSteps().clickToControlByJS(controlName, "CandidateProfilePage")
		//getBaseSteps().clickToControl(controlName, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().scrollToViewElement("Get_Details", "CandidateProfilePage")
		getBaseSteps().clickLabelTextWithJS("Dropdown_list", "Check All", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickLabelTextWithJS("Dropdown_list", "Check All", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickLabelTextWithJS("Dropdown_list", profcredType, "CandidateProfilePage")
		getBaseSteps().waitForControlDisplay("Get_Details", "CandidateProfilePage")
		getBaseSteps().scrollToViewElement("Get_Details", "CandidateProfilePage")
		getBaseSteps().clickToControlByJS("Get_Details", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().clickToControlByJS("Unassigned_RadioBtn", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		def credTypeLabel= getBaseSteps().getTextFromControl("Prof_CredType_Label","CandidateProfilePage")
		println credTypeLabel

		getBaseSteps().clickToControlByJS("Prof_CredType_chkBox1", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

		getBaseSteps().clickToControl("Prof_cert_On", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Prof_cert_On", "02/22/2022", "CandidateProfilePage")
		getBaseSteps().clickToControl("Prof_cert_until", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Prof_cert_until", "02/22/2027", "CandidateProfilePage")
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,pdfFileName))
		//println filePath
		CandidateProfilePageSteps.getBaseSteps().uploadFile("choose_File3", filePath, "CandidateProfilePage")
		//CandidateProfilePageSteps.clickSave()
		getBaseSteps().waitForProgressBarDisappear()

	}

	//Add Document //
	public static void addNewDocuments(String agencyName,docTypeName, pdfFileName) {

		getBaseSteps().scrollToViewElement("Add_New_Doc_Btn", "CandidateProfilePage")
		//Click on Add New Button//
		getBaseSteps().clickToControlByJS("Add_New_Doc_Btn", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		//Select the Doc Type Dropdown
		getBaseSteps().waitForControlDisplay("Doc_Type_Combobox", 40, "CandidateProfilePage")
		getBaseSteps().waitForControlClickable("Doc_Type_Combobox", "CandidateProfilePage")
		getBaseSteps().clickToControlByJS("Doc_Type_Combobox", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectByVisibleText("Doc_Type_Combobox", docTypeName, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,pdfFileName))
		println filePath
		CandidateProfilePageSteps.getBaseSteps().uploadFile("Doc_Upload_Button", filePath, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		clickSave()
		clickMarkComppleted()

		//Assert message : Console :



	}

	public static void addDocsWhileEditing(String docTypeName2,pdfFileName) {

		getBaseSteps().scrollToViewElement("Add_New_Doc_Btn", "CandidateProfilePage")
		//Click on Add New Button//
		getBaseSteps().clickToControlByJS("Add_New_Doc_Btn", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		//Select the Doc Type Dropdown
		getBaseSteps().waitForControlDisplay("Doc_Type_Combobox1", 40, "CandidateProfilePage")
		getBaseSteps().waitForControlClickable("Doc_Type_Combobox1", "CandidateProfilePage")
		getBaseSteps().clickToControlByJS("Doc_Type_Combobox1", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().selectByVisibleText("Doc_Type_Combobox1", docTypeName2, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,pdfFileName))
		CandidateProfilePageSteps.getBaseSteps().uploadFile("Doc_Upload_Button1", filePath, "CandidateProfilePage")
		clickSave()
		clickMarkComppleted()


	}

	public static void fillSingleEntryinEachSection(String firstName,lastName,primarySkill,dob, profSummary,stateName, countryName, address, address1, cityName, zipCode,employerName,jobTitle,startMonYear, endMonYear,degreeType,schoolName,grad_monthYear,certificationOnDate,certificationUntillDate,agencyName, docTypeName,pdfFileName) {

		'Fill Candidate Details and Contact Details'
		CandidateProfilePageSteps.fillCandidateAndContactDetails(firstName, lastName, primarySkill, dob, profSummary, stateName, countryName, address, address1, cityName, zipCode)
		'Fill first Employer Details'
		CandidateProfilePageSteps.fillExperienceDetails(employerName,jobTitle,startMonYear,endMonYear )
		'Fill Educational Details'
		CandidateProfilePageSteps.fillEducationDetails(degreeType,schoolName,grad_monthYear)
		'Fill Certification Details : '
		CandidateProfilePageSteps.fillCertificationDetails(certificationOnDate,certificationUntillDate,pdfFileName )
		//CandidateProfilePageSteps.verifyMainPopUpHasContent(successSaveContent, successMarkedContent)
		'Fill Document Details'
		CandidateProfilePageSteps.addNewDocuments(agencyName, docTypeName, pdfFileName)

	}


	public static boolean searchtheNewlyCreatedCandidate() {

		CandidateProfilePageSteps.getBaseSteps().clickToControlByJS("First_Name_TextBox", "CandidateProfilePage")
		String fName= CandidateProfilePageSteps.getBaseSteps().getValueFromControl("First_Name_TextBox", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clickToControlByJS("Last_Name_TextBox", "CandidateProfilePage")
		String lName= CandidateProfilePageSteps.getBaseSteps().getValueFromControl("Last_Name_TextBox", "CandidateProfilePage")
		//Search with Name //
		def searchString= lName.concat(", ").concat(fName)
		println searchString
		//edit thE RECORD fillSecondRowExperienceDetails
		CandidateProfilePageSteps.getBaseSteps().clickToControlByJS("Candidate_Arrow_btn", "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().waitForControlClickable("Candidate_List", 5, "CandidateProfilePage")
		//CandidateProfilePageSteps.getBaseSteps().clickLabelwithText("Candidate_List", searchString, "CandidateProfilePage")
		CandidateProfilePageSteps.getBaseSteps().clickLabelTextWithJS("Candidate_List", searchString, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		println "Candidate is selected "
		CandidateProfilePageSteps.getBaseSteps().clickToControlByJS("First_Name_TextBox", "CandidateProfilePage")
		String firstName= CandidateProfilePageSteps.getBaseSteps().getValueFromControl("First_Name_TextBox", "CandidateProfilePage")

		if(firstName.equals(fName)) {
			println "Record is SAME"
			return true
		}

		else {

			return false
		}

	}

	//Verify Popup content

	public static void verifyMainPopUpHasContent(String successSaveContent,successMarkedContent ) {
		getBaseSteps().verifyMainPopUpHasContent(successSaveContent)
		clickMarkComppleted()
		getBaseSteps().verifyMainPopUpHasContent(successMarkedContent)
	}


	public static void clickAddnew() {

		getBaseSteps().clickToControlByJS("Add_New_Button", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void setEmployername(String controlName, String employerName) {

		getBaseSteps().clickToControl(controlName, "CandidateProfilePage")

		getBaseSteps().setTextToControl(controlName, employerName, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void setJobTitle(String controlName, String jobTitle) {
		getBaseSteps().clickToControl(controlName, "CandidateProfilePage")
		getBaseSteps().setTextToControl(controlName, jobTitle, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void setStartMonYear(String controlName, String startMonYear) {
		getBaseSteps().waitForControlPresentOnDOM(controlName, 30, "CandidateProfilePage")
		getBaseSteps().clickToControl(controlName, "CandidateProfilePage")
		getBaseSteps().setTextToControl(controlName, startMonYear, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
	}
	public static void setEndMonYear(String controlName,String endMonYear) {
		getBaseSteps().waitForControlPresentOnDOM(controlName, 30, "CandidateProfilePage")
		getBaseSteps().moveToControl(controlName, "CandidateProfilePage")
		getBaseSteps().waitForControlClickable(controlName, 40, "CandidateProfilePage")
		getBaseSteps().clickToControl(controlName, "CandidateProfilePage")
		getBaseSteps().setTextToControl(controlName, endMonYear, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().verifyControlEnabled("Year_of_Exp", "CandidateProfilePage")
		pressTABKey()
		getBaseSteps().clickToControl("Year_of_Exp", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
	}


	public static void clickYearOfExperince() {
		getBaseSteps().clickToControl("Year_of_Exp", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void clickAddButtonEdu() {

		getBaseSteps().clickToControlByJS("Add_New_Button1", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()


	}

	public static void selectDegree(degreeType) {

		getBaseSteps().selectItemByTextInComboBox("Degree_Type", degreeType, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear();
	}

	public static void enterSchoolName(def schoolName) {
		getBaseSteps().clickToControl("School_Name_Input", "CandidateProfilePage")

		getBaseSteps().setTextToControl("School_Name_Input", schoolName, "CandidateProfilePage")

	}

	public static void enterGraduationMonYear(def grad_monthYear) {
		getBaseSteps().clickToControl("Graduation_MonYear", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Graduation_MonYear", grad_monthYear, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void selectCertificationCheckBox() {
		getBaseSteps().scrollToViewElement("Control_checkBox", "CandidateProfilePage")
		getBaseSteps().clickToControlByJS("Control_checkBox", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()

	}

	public static void setCertification_OnDate(def certificationOnDate) {
		getBaseSteps().scrollToViewElement("Certification_on_Date", "CandidateProfilePage")
		getBaseSteps().clickToControl("Certification_on_Date", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Certification_on_Date", certificationOnDate, "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
		getBaseSteps().verifyControlEnabled("Certification_untill_Date", "CandidateProfilePage")
	}

	public static void setCertification_UntilDate(def certificationUntillDate) {
		getBaseSteps().scrollToViewElement("Certification_untill_Date", "CandidateProfilePage")
		getBaseSteps().clickToControl("Certification_untill_Date", "CandidateProfilePage")
		getBaseSteps().setTextToControl("Certification_untill_Date", certificationUntillDate, "CandidateProfilePage")
		getBaseSteps().scrollToMiddle()
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickSave() {

		getBaseSteps().clickToControl("Save_Button", "CandidateProfilePage")
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void clickMarkComppleted() {

		getBaseSteps().clickToControl("Mark_Complete_Button", "CandidateProfilePage")
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
		String userName = "osm.";
		String emailAdd= userName+randomInt+"@mail.com";
		return emailAdd
	}

	public static String generateFirstName(String firstName) {

		Random rn = new Random()
		int randomInt =rn.nextInt(1999)
		String fname= String.format("%s_%s", firstName, randomInt)
		//fname = fname+rn
		return fname
	}


	public static void uploadDocumentFile(String timesheetFileName) {
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,timesheetFileName))
		getBaseSteps().uploadFile("Choose_File",filePath, "CandidateProfilePage")
		//Thread.sleep(5000)
		//getBaseSteps().setTextToControl("Document_Description_TextBox", "Test Upload", "OrderCreationPage")
		//Thread.sleep(3000)
		//getBaseSteps().clickToControl("Upload_Button", "OrderCreationPage")
		//getBaseSteps().clickToControl("Upload_Button", "OrderCreationPage")
		Thread.sleep(7000)
	}

	//pRESS tab KEY
	///utility //

	public static void pressTABKey() {

		Robot robot = new Robot();
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(500);
		//System.out.println("TAB is press");
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(500);


	}


	//utility ///
	public static void takeScreenShot() {

		Browser.takeScreenshot();
		WebUiBuiltInKeywords.takeFullPageScreenshot();

	}



}