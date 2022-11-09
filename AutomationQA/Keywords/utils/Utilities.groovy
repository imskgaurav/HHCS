package utils

import java.awt.Robot
import java.awt.event.KeyEvent
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.math.NumberUtils
import org.openqa.selenium.By
import com.google.common.base.Stopwatch
import com.google.common.base.Strings
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import core.Browser
import core.Logger




public class Utilities {
	
	//Added by sunita
	private static final String downloadPath = System.getProperty("user.dir") + "\\ExportedFiles"
	
	//Added by sunita : To check if exported file exists in the given project path
	public static boolean checkFileExists(String FileName) {
		try {
			
			return new File(downloadPath + "\\" +FileName).exists();
		}catch(Exception e) {
			throw new RuntimeException("Exception while checking file exists")
		}
	}
	
	//Added by sunita : If downloaded file already exists then delete it
	public static void deleteFile(String FileName) {
		File file=new File(downloadPath + "\\" +FileName)
		if(file.exists()) {
			file.delete()
		}
	}

	//Added by Sunita : wait for file to be downloaded
	public static void waitForFileDownloaded(String FileName, int timeoutSeconds) {
		File file=new File(downloadPath + "\\" +FileName)
		/*WebDriver driver = Browser.getDriverContext()
		 FluentWait<WebDriver> wait = new FluentWait<>(driver)
		 .withTimeout(Duration.ofMillis(timeoutSeconds))
		 .pollingEvery(Duration.ofMillis(250))
		 .ignoring(NoSuchElementException.class);
		 wait.until((x)->file.exists())
		 */	
		int pollingTimeOut=timeoutSeconds/1000
		for(int i=1;i<=1000;i++) {
			if(file.exists())
				break
			Thread.sleep(pollingTimeOut)
		}
	}

	//Added by Sunita : to caps the first letter
	public static String getFirstLetterCapitalized(String Data) {
		
				List<String> lstWords=Data.split(' ')
				String updated=''
				for(String word:lstWords) {
					String flData=word.substring(0,1).toUpperCase()
					String remData=word.substring(1).toLowerCase()
					updated=updated+flData+remData+' '
				}
				return updated.trim()
		
			}
	
	public static boolean isStringMatchedWithWildcard(String wildCardString, String fullString) {

		// If we reach at the end of both strings,
		// we are done
		if (wildCardString.length() == 0 && fullString.length() == 0)
			return true;

		// Make sure that the characters after '*'
		// are present in second string.
		// This function assumes that the wildCardString
		// string will not contain two consecutive '*'
		if (wildCardString.length() > 1 && wildCardString.charAt(0) == '*' &&
		fullString.length() == 0)
			return false;

		// If the wildCardString string contains '?',
		// or current characters of both strings match
		if ((wildCardString.length() > 1 && wildCardString.charAt(0) == '?') ||
		(wildCardString.length() != 0 && fullString.length() != 0 &&
		wildCardString.charAt(0) == fullString.charAt(0)))
			return isStringMatchedWithWildcard(wildCardString.substring(1),
					fullString.substring(1));

		// If there is *, then there are two possibilities
		// a) We consider current character of second string
		// b) We ignore current character of second string.
		if (wildCardString.length() > 0 && wildCardString.charAt(0) == '*')
			return isStringMatchedWithWildcard(wildCardString.substring(1), fullString) ||
					isStringMatchedWithWildcard(wildCardString, fullString.substring(1));
		return false;
	}


	public static By mapArgumentToSelector(String selector){
		if(!Strings.isNullOrEmpty(selector) && selector.startsWith("//")) {
			return By.xpath(selector);
		}else if(!Strings.isNullOrEmpty(selector)){
			return By.cssSelector(selector);
		}else{
			return null;
		}
	}

	public static String generateRandomNumber(int quantityNumbers=5){
		if(quantityNumbers<=5){
			String numbers = RandomStringUtils.randomNumeric(quantityNumbers);
			return numbers;
		}else{
			Logger.logERROR("The random number should less than or equal 5");
			return null;
		}
	}

	public static boolean tryToParseStringToInt(String num) {
		try {
			Integer.parseInt(num)
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void waitForAWhile(int seconds, boolean condition=true){
		long timeInMiliSeconds = seconds * 1000;
		Stopwatch sw = new Stopwatch();
		sw.start();
		//		boolean condition = true;
		while(condition){
			if(timeInMiliSeconds< sw.elapsed().toMillis()){
				sw.stop();
				break;
			}
		}

	}
	public static List<String> getRowDataWithTruncatedDecimal(String str) {


		String arr = str.trim().replaceAll("\\s+", " ");


		List<String> li = new ArrayList<String>();
		String[] newArr;
		newArr= arr.split(" ")

		for (String s : newArr) {

			boolean x = NumberUtils.isNumber(s);
			if (x) {

				// truncate the Decimal Value/
				if (s.contains(".")) {

					String[] splitVal = s.split("\\.");


					li.add(splitVal[0]);

				}

				else {

					System.out.println("Only Integer Value:" + s);
					li.add(s);
				}
			}

			else {
				//System.out.println("only String Value");
				//System.out.println(s);
				li.add(s);

			}

		}
		print'The Row Data after Decimal value Truncation :'+li
		return li
	}
	public static String replaceAllSpacesOfString(String str) {

		str= str.replaceAll("\\s", "")
		return str

	}

	public static void takeScreenShot() {

		Browser.takeScreenshot();
		WebUiBuiltInKeywords.takeFullPageScreenshot();

	}

	public static void pressTABKey() {

		Robot robot = new Robot();
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(500);
		//System.out.println("TAB is press");
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(500);


	}


}
