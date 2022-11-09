package pages

import configs.Path
import configs.VMSStrings
import core.AssertSteps
import core.BaseSteps
import core.ControlFactory
import core.Logger
import utils.DateTimeUtil
import utils.PlatformUtil



public class TimeSheetPageSteps {
	private static url = '/TimeSheet.aspx';

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	// =================== ACTION METHODS ZONE ======================

	public static void enterJobIDAndFindTimeSheet(String jobID, String startTimesheetDateTime ="") {

		int numberOfDays = 7;
		if(startTimesheetDateTime.isEmpty()) {
			startTimesheetDateTime = DateTimeUtil.getDateTime();
		}else {
			startTimesheetDateTime = DateTimeUtil.convertDateStringFormat(startTimesheetDateTime, "MM/dd/yyyy");
		}
		getBaseSteps().setTextToControl("Job_ID_Textbox", jobID, "TimeSheetPage");
		getBaseSteps().waitForControlClickable("Job_Start_Date_Textbox", "TimeSheetPage")
		getBaseSteps().setTextToControl("Job_Start_Date_Textbox", startTimesheetDateTime, "TimeSheetPage");
		getBaseSteps().clickToControl("Get_Details_Button", "TimeSheetPage");
		String tableContent = getBaseSteps().getBodyTableText("Candidate_List_Table", "TimeSheetPage");

		while(tableContent.contains(VMSStrings.NOT_DATA_AVAILABLE_IN_TABLE)) {
			getBaseSteps().setTextToControl("Job_Start_Date_Textbox", DateTimeUtil.nextDateTimeFromAGivenDate(numberOfDays,startTimesheetDateTime), "TimeSheetPage");
			getBaseSteps().clickToControl("Get_Details_Button", "TimeSheetPage");
			tableContent = getBaseSteps().getTextFromControl("Candidate_List_Table", "TimeSheetPage");
			numberOfDays=numberOfDays+7;
		}
	}

	public static void inputTimeSheet(String timeIn, String timeOut, int rows=0, boolean isInputRandomPayRate=false, boolean isCheckToNoMealTaken = false) {

		if(rows==0) {
			rows = Integer.parseInt(getBaseSteps().getRowTableCounts("Timesheet_Table", "TimeSheetPage"));
		}
		int i = 1;
		while(i<=rows) {
			if(isInputRandomPayRate) {
				def payrate = ["Regular", "OnCall", "Charge", "Callback"] as String[]
				def prName = payrate[(int)(Math.random()*payrate.length)]
				getBaseSteps().clickToCellTableCheckBoxControl("Timesheet_Table", i.toString(), "3", "TimeSheetPage") //click on 3rd Payrate dropdown: added by sunita
				getBaseSteps().selectCellItemComboboxTableControl("Timesheet_Table", i.toString(), "3", prName, "TimeSheetPage")//3 is payrate
			}




			getBaseSteps().setTextToCellTableControl("Timesheet_Table", i.toString(), "4", timeIn, "TimeSheetPage")//4 is Time in
			getBaseSteps().setTextToCellTableControl("Timesheet_Table", i.toString(), "5", timeOut, "TimeSheetPage")//5 is time out

			if(isCheckToNoMealTaken) {
				getBaseSteps().clickToCellTableCheckBoxControl("Timesheet_Table", i.toString(), "7", "TimeSheetPage")//7 is no meal taken
			}

			i++;
		}

	}

	public static void inputTimeSheetByRow(String timeIn, String timeOut, int rows, boolean isInputRandomPayRate=false, boolean isCheckToNoMealTaken = false) {


		if(isInputRandomPayRate) {
			def payrate = ["Regular", "OnCall", "Charge", "Callback"] as String[]
			def prName = payrate[(int)(Math.random()*payrate.length)]
			getBaseSteps().selectCellItemComboboxTableControl("Timesheet_Table", rows.toString(), "3", prName, "TimeSheetPage")//3 is payrate
		}

		getBaseSteps().setTextToCellTableControl("Timesheet_Table", rows.toString(), "4", timeIn, "TimeSheetPage")//4 is Time in
		getBaseSteps().setTextToCellTableControl("Timesheet_Table", rows.toString(), "5", timeOut, "TimeSheetPage")//5 is time out

		if(isCheckToNoMealTaken) {
			getBaseSteps().clickToCellTableCheckBoxControl("Timesheet_Table", rows.toString(), "7", "TimeSheetPage")//7 is no meal taken
		}


	}



	public static void uploadTimesheetFile(String timesheetFileName) {
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,timesheetFileName))
		getBaseSteps().uploadFile("Choose_File_Upload",filePath, "TimeSheetPage")
		getBaseSteps().clickToControl("Submit_Upload_Image_Button", "TimeSheetPage")
	}
	
	public static void uploadTimesheetFileUpdated(String timesheetFileName,String content,String errMsg="The pop up does NOT show correct content") {
		String filePath = PlatformUtil.getFileProjectPath(String.format("%s%s",Path.DATAFILE_PATH,timesheetFileName))
		getBaseSteps().uploadFile("Choose_File_Upload",filePath, "TimeSheetPage")
		getBaseSteps().clickToControl("Submit_Upload_Image_Button", "TimeSheetPage")
		//getBaseSteps().verifyMainPopUpHasContent("File uploaded successfully")
		
		String popUpContent=""
		try {
			popUpContent = getBaseSteps().getTextFromControl("Main_Popup", "CommonLocators");
		}catch(Throwable e) {
			Logger.logERROR(String.format("Pop up does NOT display: >>>%s<<<",e.getMessage()))
		}
		'ER:'
		'Candidate timesheet PDF upload successfully.'
		String passMsg = String.format("The pop up content has content as >>>%s<<<",popUpContent)
		AssertSteps.verifyExpectedResult(popUpContent.contains(content.trim()),passMsg,errMsg);
	}



	// =================== ASSERT METHODS ZONE ======================
	public static void verifyPageIsLoaded(){
		getBaseSteps().verifyPageIsLoaded(url);
	}
}
