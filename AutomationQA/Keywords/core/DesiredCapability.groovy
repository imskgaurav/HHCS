package core
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions

import com.fasterxml.jackson.databind.ObjectMapper
import com.kms.katalon.core.webui.driver.DriverFactory

import utils.PlatformUtil
public class DesiredCapability {
	
	//private static final String downloadPath = System.getProperty("user.dir") + "\\ExportedFiles"
	
	public static WebDriver setEdgeOptions(){
		System.setProperty('webdriver.edge.driver', DriverFactory.getEdgeChromiumDriverPath());
		EdgeOptions options = new EdgeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.prompt_for_download", false);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		Map<String, Object> edgeOptions = new HashMap<String, Object>()
		edgeOptions.put('prefs', prefs)
		edgeOptions.put("excludeSwitches", Collections.singletonList("enable-automation"));

		options.setCapability('ms:edgeChrominum', true)
		options.setCapability('ms:edgeOptions', edgeOptions)
		WebDriver driver = new EdgeDriver(options);
		return driver;
	}
	/**
	 * This method is used to set Desired Capabilities for Chrome
	 * @return WebDriver: This is a driver with setting options
	 */
	public static WebDriver setChromeOptions(){
		System.setProperty('webdriver.chrome.driver', DriverFactory.getChromeDriverPath())
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.default_directory", PlatformUtil.getDownloadPath());
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("disable-infobars");
		options.addArguments("disable-save-password-bubble");
		options.addArguments("--safebrowsing-disable-download-protection");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		WebDriver driver = new ChromeDriver(options);
		return driver;
	}


	/**
	 * This method is used to set Desired Capabilities for Chrome Headless
	 * @return WebDriver: This is a driver with setting options
	 */
	public static WebDriver setChromeHeadlessOptions(){
		System.setProperty('webdriver.chrome.driver', DriverFactory.getChromeDriverPath())
		ChromeOptions options = new ChromeOptions();


		options.addArguments("--disable-gpu");
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("disable-infobars");
		options.addArguments("disable-save-password-bubble");
		options.addArguments("--safebrowsing-disable-download-protection");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", "false");
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.prompt_for_download", "false");
		prefs.put("download.default_directory", PlatformUtil.getDownloadPath());
		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(options);
		return driver;
	}
}