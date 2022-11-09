package pages
import utils.Utilities
import java.time.Instant
import core.BaseSteps
import core.ControlFactory
import utils.PlatformUtil
import utils.ExcelUtil
import core.AssertSteps
import java.time.Duration

public class ReportsPageSteps {
	
	
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	
	public static void verifyExportingReport(String expReportFileName,int timeoutMilliSeconds=2000) 
	{
		if(Utilities.checkFileExists(expReportFileName)) {
			Utilities.deleteFile(expReportFileName)
		}
		Instant Start=Instant.now()
		clickExportLink()
		Utilities.waitForFileDownloaded(expReportFileName, timeoutMilliSeconds)
		Instant End=Instant.now()
		Duration timeElapsed=Duration.between(Start, End)
		String AssertMsgPass=expReportFileName+' Report Export validation,exported successfully. Time taken to export: '+timeElapsed.toSeconds()+' seconds'
		String AssertMsgFail=expReportFileName+' Report Export validation,Failed to export. Duration to check export: '+timeElapsed.toSeconds()+' seconds'
		AssertSteps.verifyActualResult(Utilities.checkFileExists(expReportFileName), AssertMsgPass, AssertMsgFail)
	}
	
	public static void clickExportLink() {
		getBaseSteps().clickToControl('Button_Img', "AccrualReportPage")
		getBaseSteps().waitForControlDisplay('Export_Button', 'CommonLocators')
		'Select Export button'
		getBaseSteps().clickToControl('Export_Button', 'CommonLocators')
		Thread.sleep(5000)

	}
	
	public static String expReportName() {
		String expReportFileName
		String pageHeader=getPageHeader()

		switch(pageHeader) {
			case 'Accrual Report':
				expReportFileName='rptAccuralTimesheet'
				break
			case 'USER EVENT LOG':
				expReportFileName='UserEventLog'
				break
			case 'SUMMARY OF HOURS AND SHIFTS':
				expReportFileName='ShiftsReport'
				break
			case 'CME AND PTO/ATO HOURS REPORT':
				expReportFileName='CME Balance Report'
				break
			
			default:
				expReportFileName=Utilities.getFirstLetterCapitalized(pageHeader)
				break
		}
		//return Utilities.getFirstLetterCapitalized(CommonSteps.getPageHeader())
		return expReportFileName
	}
	

	public static String getPageHeader() {
		return getBaseSteps().getTextFromControl('pageHeader', "HeaderPage")
	}
}
