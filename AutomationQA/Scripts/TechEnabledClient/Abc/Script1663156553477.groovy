import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://einstein2.us/GIIQAAutomation/Account/Login')


WebUI.setText(findTestObject('Object Repository/Page_Log in/input_Email_ctl00MainContentEmail'), 'test123@yopmail.com')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Log in/input_Password_ctl00MainContentPassword'), 'cvW8qx4B2o3F4VwP/kNsqA==')

WebUI.sendKeys(findTestObject('Object Repository/Page_Log in/input_Password_ctl00MainContentPassword'), Keys.chord(Keys.ENTER))

WebUI.doubleClick(findTestObject('Object Repository/Page_Open Jobs/span_Location_ctl00_MainContent_rdcLocation_Arrow'))

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/span_Location_ctl00_MainContent_rdcLocation_Arrow'))

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/label_Check All'))

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Location_ctl00MainContentrdcLocation'), '')

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/input_Defiance Hospital_rcbCheckBox'))

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Location_ctl00MainContentrdcLocation'), 'Defiance Hospital')

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/input_Department_ctl00MainContentrdcDepartment'))

WebUI.doubleClick(findTestObject('Object Repository/Page_Open Jobs/input_Department_ctl00MainContentrdcDepartment'))

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Department_ctl00MainContentrdcDepartment'), 'All items checked')

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Location_ctl00MainContentrdcLocation'), 'Defiance Hospital')

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Agency_ctl00MainContentradcomAgency'), 'All items checked')

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Client_ctl00MainContentRadCBRegion'), 'All items checked')

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/input_Department_ctl00MainContentrdcDepartment'))

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/span_Department_ctl00_MainContent_rdcDepart_f9d26a'))

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/span_Department_ctl00_MainContent_rdcDepart_f9d26a'))

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/input_Defiance Hospital_rcbCheckBox'))

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Department_ctl00MainContentrdcDepartment'), '')

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/input_Defiance Hospital_rcbCheckBox'))

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Department_ctl00MainContentrdcDepartment'), '')

WebUI.doubleClick(findTestObject('Object Repository/Page_Open Jobs/input_Defiance Hospital_rcbCheckBox'))

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/input_Defiance Hospital_rcbCheckBox'))

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Department_ctl00MainContentrdcDepartment'), 'All items checked')

WebUI.click(findTestObject('Object Repository/Page_Open Jobs/input_Interested_ctl00MainContentbtnSearch'))

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Department_ctl00MainContentrdcDepartment'), 'All items checked')

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Location_ctl00MainContentrdcLocation'), 'Defiance Hospital')

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Agency_ctl00MainContentradcomAgency'), 'All items checked')

WebUI.setText(findTestObject('Object Repository/Page_Open Jobs/input_Client_ctl00MainContentRadCBRegion'), 'All items checked')

