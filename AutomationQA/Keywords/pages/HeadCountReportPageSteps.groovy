package pages
import core.BaseSteps
import core.ControlFactory
import internal.GlobalVariable
import utils.PlatformUtil

public class HeadCountReportPageSteps {

	private static url = '/rptPushoutReport.aspx';


	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}

	public static void getDetails() {


		getBaseSteps().clickToControl('Get_Details_Button', 'HeadCountReport')
		getBaseSteps().waitForProgressBarDisappear()
	}

	public static void downLoadExcel() {
		
		getBaseSteps().clickToControl('DownLoad_Button', 'HeadCountReport')
		getBaseSteps().waitForProgressBarDisappear()
	}
	
	
	public static verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {
		
				String fileNameAfterExported = String.format("%s.xls",prefixFile)
		
				if(section != "") {
					getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
				}
		
				getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
				return fileNameAfterExported
			}
		
}
