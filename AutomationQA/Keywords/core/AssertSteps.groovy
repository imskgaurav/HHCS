package core

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import utils.PlatformUtil


public class AssertSteps {
	
	//Added by sunita : Verify actual result
	public static void verifyActualResult(boolean actualResult,String successMessage="",String failureMessage="",FailureHandling failureHandling=FailureHandling.CONTINUE_ON_FAILURE) {
		if(actualResult) {
			Logger.setPassHandling(String.format("Result: PASSED. %s on Page :  %s",successMessage,Browser.getCurrentURL()))
		}else {
			Browser.takeScreenshot();
			WebUiBuiltInKeywords.takeFullPageScreenshot();
			Logger.setFailureHandling(String.format("Result: Failed. %s on Page :  %s", failureMessage,Browser.getCurrentURL()), failureHandling);
		}
	}

	public static void verifyExpectedResult(boolean actualResult,String passMessage="",String failedMessage="", FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE){

		String passedString="Expected result: PASSED"
		String failedString="Expected result: FAILED"

		if(actualResult){
			Logger.setPassHandling(String.format("%s. %s", passedString,passMessage))
			Logger.logINFO("Expected RESULT is same as ACTUAL")
		}else{
			Browser.takeScreenshot();
			WebUiBuiltInKeywords.takeFullPageScreenshot();
			Logger.setFailureHandling(String.format("%s. %s", failedString,failedMessage), failureHandling);
		}
	}


	public static void verifyRestoreData(boolean actualResult,String passMessage="",String failedMessage="", FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE){

		String passedString="Expected result: PASSED"
		String failedString="Expected result: FAILED"

		if(actualResult){
			Logger.setPassHandling(String.format("%s. %s", passedString,passMessage))
		}else{
			Logger.setFailureHandling(String.format("%s. %s", failedString,failedMessage), failureHandling);
		}
	}


	public static void verifyFileIsDownloaded(boolean actualResult,String passMessage="",String failedMessage="", FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE){
		verifyRestoreData(actualResult,passMessage,failedMessage,failureHandling);
	}
}
