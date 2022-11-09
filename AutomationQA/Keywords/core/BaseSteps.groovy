package core


import com.google.common.base.Strings

import configs.Timeout
import controls.CheckBox
import controls.ComboBox
import controls.Menu
import controls.Table
import org.openqa.selenium.WebDriver
import utils.EncodeUtil
import utils.PlatformUtil
import utils.Utilities
import controls.DropDown
import java.awt.Robot
import java.awt.event.KeyEvent

public class BaseSteps {
	private IControlFactory controlFactory;
	public BaseSteps(IControlFactory controlFactory){
		this.controlFactory = controlFactory;
	}

	public IControlFactory getControlFactory(){
		return this.controlFactory;
	}

	// ===================== COMMON ACTIONS STEPS =====================

	//Zoom out so control is visible for interaction
	public void zoomDecrease()
	{
		Robot robot = new Robot();
		for (int i = 0; i < 4; i++)
		{
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}

	public void zoomIncrease()
	{
		Robot robot = new Robot();
		for (int i = 0; i < 4; i++)
		{
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_EQUALS);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_EQUALS);
		}
	}

	public void clearTextInControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.clearText();
	}

	public void clickToControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.click();
	}


	//Added by SUnita
	public void clickToControlByJS(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.clickByJS();
	}
	public void scrollToViewElement(String controlName, String pageName) {

		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.scrolltoView()

	}


	public void scrollToMiddle() {

		BaseControl control= new BaseControl()

		control.scrollToMiddle()


	}



	public String getTextFromControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		return control.getText();
	}
	public String getValueFromControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		return control.getValue();
	}
	public String getAttributeFromControl(String controlName, String attributeName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		return control.getAttribute(attributeName)
	}

	public String getXpathFromControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		return control.xpath;
	}

	public void openLinkInNewTab(String controlName, String pageName) {
		BaseControl control = controlFactory.initControl(BaseControl, controlName, pageName);
		control.openLinkInNewTab();
	}

	public void openSubMenuInNewTab(String controlName, String subMenu, String pageName) {
		Menu control = controlFactory.initControl(Menu, controlName, pageName);
		control.openSubMenuInNewTab(subMenu);
	}

	public void setTextToControl(String controlName, String inputText, String pageName){

		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		if(!Strings.isNullOrEmpty(control.getValue())){
			control.setValueByJS("");
		}
		control.clearText();
		control.sendKeys(inputText);
	}

	public void setEncodeTextToControl(String controlName, String encodeText, String pageName){

		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		if(!Strings.isNullOrEmpty(control.getValue())){
			control.setValueByJS("");
		}
		control.clearText();
		control.sendKeys(EncodeUtil.decode(EncodeUtil.getDefault(encodeText)));
	}

	public void selectSubMenu(String controlName, String subMenu, String subItemMenu="", String pageName) {
		Menu control = controlFactory.initControl(Menu, controlName, pageName);
		control.selectSubMenu(subMenu,subItemMenu);
	}

	public void selectItemByTextInComboBox(String controlName, String itemText, boolean isClickToOpen=true, boolean isDeselectAll =false,String pageName){
		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
		control.selectByText(itemText,isClickToOpen,isDeselectAll);

	}

	public void selectItemByContainedTextInComboBox(String controlName, String itemText,boolean isClickToOpen=true, boolean isClickByJS =false, String pageName){
		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
		control.selectByContainedText(itemText,isClickToOpen,isClickByJS);
	}

	public void selectCheckboxByContainedTextInComboBox(String controlName, String itemText, String pageName){
		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
		control.selectCheckboxByContainedText(itemText);

	}

	public void selectCheckboxByContainedTextInComboBoxHasAList(String controlName, String itemText,boolean isClickToOpen=true, String pageName){
		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
		control.selectCheckboxByContainedTextInAList(itemText,isClickToOpen);

	}

	//new Function//

	public void moveToControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.moveToControl()
	}


	public void clickFrameControl(String frameControl,String controlName,String pageName) {
		WebDriver driver = Browser.getDriverContext();
		driver.switchTo().frame(frameControl)
		BaseControl control=controlFactory.initControl(BaseControl, controlName, pageName)
		control.click()
	}

	public void clickFrameControlUsingIndex(int frameIndex,String controlName,String pageName) {
		WebDriver driver = Browser.getDriverContext();
		driver.switchTo().frame(frameIndex)
		//		BaseControl control=controlFactory.initControl(BaseControl, controlName, pageName)
		//			control.click()

	}

	public void setTextToControlJS(String controlName, String inputText, String pageName){

		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		if(!Strings.isNullOrEmpty(control.getValue())){
			control.setValueByJS("");
		}
		control.clearText();
		//control.sendKeys(inputText);
		control.JSsendKeys(inputText);
	}


	//Added by Sunita, not yet used. Here you can pass id and the text to select from the dropdown


	public void selectCheckboxOptionByText(String controlName, String option, String pageName) {
		CheckBox checkbox = controlFactory.initControl(CheckBox, controlName,pageName);
		checkbox.selectCheckBoxByText(option)
	}

	public void clickLabelwithText(String controlName, String Text,String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.clickwithText(Text)
	}

	public void clickLabelTextWithJS(String controlName, String Text,String pageName) {

		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.clickLabelTextWithJS(Text)


	}

	public void selectByVisibleText(String controlName, String inputText, String pageName){
		DropDown control = controlFactory.initControl(DropDown, controlName,pageName);
		control.selectByVisibleText(inputText);

	}


	public void waitForControlClickable(String controlName, int timeOut = Timeout.WAIT_TIMEOUT_SHORT_SECONDS, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.waitForControlClickable(timeOut);
	}
	public void waitForPageReady(int timeOut) {

		//BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		BaseControl control=new BaseControl();
		control.waitForPageReady(timeOut)

	}
	public void makeElementVisibleInDOM(String controlName, String pageName) {

		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.makeElementVisibleInDOM();

	}

	public List<String> getListTextFromControl(String controlName, String pageName){
		
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		
		return control.getListText()
	}
	//End//

	//Added by Sunita, not yet used. Here you can pass id and the text to select from the dropdown
	public void selectCheckboxByContainedTextInComboBoxHasAListById(String controlName,String Id, String itemText,boolean isClickToOpen=true, String pageName){
		ComboBox control = controlFactory.initControl(ComboBox, controlName, pageName);
		control.selectCheckboxByContainedTextInAListById(Id,itemText,isClickToOpen);

	}

	public void submitToControl(String controlName, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.submit();
	}

	public void waitForControlDoesNotDisplay(String controlName, int timeOut = Timeout.WAIT_TIMEOUT_MEDIUM_SECONDS, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.waitForControlDoesNotDisplay(timeOut);
	}

	public void waitForControlDisplay(String controlName, int timeOut = Timeout.WAIT_TIMEOUT_MEDIUM_SECONDS, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.waitForControlDisplay(timeOut);
	}

	public void waitForControlPresentOnDOM(String controlName, int timeOut = Timeout.WAIT_TIMEOUT_MEDIUM_SECONDS, String pageName) {
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.waitForControlPresentOnDOM(timeOut)

	}

	public void waitForProgressBarDisappear() {
		waitForControlDoesNotDisplay("Progress_Image","CommonLocators");
	}

	public void uploadFile(String controlName, String filePath, String pageName){
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		control.uploadFile(filePath);
	}


	// ===================== COMMON ASSERTION STEPS =====================

	public void verifyPageIsLoaded(String url, String errMsg=String.format("The URL >>>%s<<< is NOT changed",url)){
		String passMsg = String.format("The page is loaded with >>>%s<<<",url)
		Browser.waitForUrlChange(url, Timeout.WAIT_TIMEOUT_SHORT_SECONDS);
		AssertSteps.verifyExpectedResult(Browser.getCurrentURL().toLowerCase().contains(url.toLowerCase()),passMsg,errMsg);
	}

	public void verifyPageHeaderCorrect(String expectedHeader, String errMsg="The page header is NOT correct"){
		String passMsg = String.format("The current page header is >>>%s<<<",expectedHeader)
		AssertSteps.verifyExpectedResult(Browser.getPageHeader().trim().equalsIgnoreCase(expectedHeader),passMsg,errMsg);
	}

	//original
	/*	public void verifyControlDisplayed(String controlName, String pageName, String errMsg="The control does NOT Display"){
	 String passMsg = String.format("The control >>>%s<<< is displayed on page >>>%s<<<",controlName,pageName)
	 BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
	 AssertSteps.verifyExpectedResult(control.isDisplayed(),passMsg,errMsg);
	 }*/

	public void verifyControlDisplayed(String controlName, String pageName, String errMsg="The control does NOT Display")
	{
		try
		{
			String passMsg = String.format("The control >>>%s<<< is displayed on page >>>%s<<<",controlName,pageName)
			BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
			AssertSteps.verifyExpectedResult(control.isDisplayed(),passMsg,errMsg);
		}
		catch(Throwable e)
		{
			Logger.logERROR(String.format("Control does not display: >>>%s<<<",e.getMessage()))
		}
	}

	public void verifyControlNotDisplayed(String controlName, String pageName, String errMsg="The control is Displayed"){
		String passMsg = String.format("The control >>>%s<<< is not displayed on page >>>%s<<<",controlName,pageName)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		AssertSteps.verifyExpectedResult(!control.isDisplayed(),passMsg,errMsg);
	}
	public void verifyControlNotDisplayedORDisplayWithoutText(String controlName,String expectedText, String pageName, String errMsg="The control is Displayed"){
		String passMsg = String.format("The control >>>%s<<< is not displayed on page >>>%s<<<",controlName,pageName)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);

		if(control.isDisplayed()) {
			String controlText = control.getText();
			AssertSteps.verifyExpectedResult(!controlText.equals(expectedText),passMsg,errMsg);

		}else {
			AssertSteps.verifyExpectedResult(!control.isDisplayed(),passMsg,errMsg);
		}
	}

	public void verifyControlDisabled(String controlName, String pageName, String errMsg="The control does NOT Disable"){
		String passMsg = String.format("The control >>>%s<<< is disabled on page >>>%s<<<",controlName,pageName)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		AssertSteps.verifyExpectedResult(!control.isEnabled(),passMsg,errMsg);
	}

	public void verifyControlEnabled(String controlName, String pageName, String errMsg="The control does NOT Enable"){
		String passMsg = String.format("The control >>>%s<<< is enabled on page >>>%s<<<",controlName,pageName)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isEnabled(),passMsg,errMsg);
	}

	public void verifyMainPopUpHasContent(String content,String errMsg="The pop up does NOT show correct content") {
		String popUpContent=""
		try {
			popUpContent = getTextFromControl("Main_Popup", "CommonLocators");
		}catch(Throwable e) {
			Logger.logERROR(String.format("Pop up does NOT display: >>>%s<<<",e.getMessage()))
		}
		String passMsg = String.format("The pop up content has content as >>>%s<<<",popUpContent)
		AssertSteps.verifyExpectedResult(popUpContent.contains(content.trim()),passMsg,errMsg);
		clickToControl("Close_Popup_Button", "CommonLocators")
	}

	public void verifyMainPopUpMatchedContent(String wildcardContent,String errMsg="The pop up does NOT show correct content") {
		String popUpContent=""
		try {
			popUpContent = getTextFromControl("Main_Popup", "CommonLocators");
		}catch(Throwable e) {
			Logger.logERROR(String.format("Pop up does NOT display: >>>%s<<<",e.getMessage()))
		}
		String passMsg = String.format("The pop up content has content as >>>%s<<<",popUpContent)
		AssertSteps.verifyExpectedResult(Utilities.isStringMatchedWithWildcard(wildcardContent, popUpContent),passMsg,errMsg);
		clickToControl("Close_Popup_Button", "CommonLocators")
	}

	public void verifyCheckBoxIsChecked(String controlName, String pageName, String errMsg="The control is NOT checked"){
		String passMsg = String.format("The check box >>>%s<<< is checked",controlName)
		CheckBox control = controlFactory.initControl(CheckBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isChecked(),passMsg,errMsg);
	}

	public void verifyPseudoCheckBoxIsChecked(String controlName, String pageName, String errMsg="The control is NOT checked"){
		String passMsg = String.format("The check box >>>%s<<< is checked",controlName)
		CheckBox control = controlFactory.initControl(CheckBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isCheckedByJS(),passMsg,errMsg);
	}

	public void verifyControlWithTextDisplayed(String controlName, String expectedText, String pageName, String errMsg="Displayed and Text does NOT meet the verification"){
		String passMsg = String.format("The control >>>%s<<< with text >>>%s<<< is displayed",controlName,expectedText)

		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName );
		String controlText = control.getText();
		errMsg = String.format("The control >>>%s<<< has a current >>>%s<<<  text",controlName,controlText)
		AssertSteps.verifyExpectedResult(control.isDisplayed()&&controlText.equals(expectedText),passMsg,errMsg);
	}

	public void verifyControlContainedTextDisplayed(String controlName, String expectedText, String pageName, String errMsg="Displayed and Text does NOT meet the verification"){
		String passMsg = String.format("The control >>>%s<<< contains text >>>%s<<< is displayed",controlName,expectedText)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName );
		String controlText = control.getText();
		AssertSteps.verifyExpectedResult(control.isDisplayed()&&controlText.contains(expectedText),passMsg,errMsg);
	}

	public void verifyControlNotContainsExpectedText(String controlName, String expectedText, String pageName, String errMsg="Still match with text"){
		String passMsg = String.format("The control >>>%s<<< does NOT contain text >>>%s<<<",controlName,expectedText)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName);
		String controlText = control.getText();
		AssertSteps.verifyExpectedResult(!controlText.equals(expectedText),passMsg,errMsg);
	}

	public void verifyControlContainedValueDisplayed(String controlName, String expectedValue, String pageName, String errMsg="Displayed and Text does NOT meet the verification"){
		String passMsg = String.format("The control >>>%s<<< contains text >>>%s<<< is displayed",controlName,expectedValue)
		BaseControl control = controlFactory.initControl(BaseControl, controlName,pageName );
		String controlValue = control.getValue();
		AssertSteps.verifyExpectedResult(control.isDisplayed()&&controlValue.contains(expectedValue),passMsg,errMsg);
	}

	public void verifyOptionIsDefaultInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is not displayed in the combo box"){
		String passMsg = String.format("The combo box >>>%s<<< has default value as >>>%s<<<",controlName,optionName)
		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isOptionDefault(optionName),passMsg,errMsg);
	}

	public void verifyOptionIsContainedInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is not displayed in the combo box"){
		String passMsg = String.format("The combo box >>>%s<<< contains >>>%s<<< option ",controlName,optionName)
		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isOptionIncluded(optionName),passMsg,errMsg);
	}

	public void verifyOptionIsNOTContainedInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is still displayed in the combo box"){
		String passMsg = String.format("The combo box >>>%s<<< does NOT contains >>>%s<<< option ",controlName,optionName)
		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(!control.isOptionIncluded(optionName),passMsg,errMsg);
	}

	public void verifyOptionIsCheckedInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is unchecked in the combo box"){
		String passMsg = String.format("The option >>>%s<<< is checked in >>>%s<<< combobox ",optionName,controlName)
		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isCheckBoxChecked(optionName),passMsg,errMsg);
	}

	public void verifyOptionIsUnCheckedInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is checked in the combo box"){
		String passMsg = String.format("The option >>>%s<<< is unchecked in >>>%s<<< combobox ",optionName,controlName)
		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(!control.isCheckBoxChecked(optionName),passMsg,errMsg);
	}

	public void verifyOptionIsEnabledInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is checked in the combo box"){
		String passMsg = String.format("The option >>>%s<<< is unchecked in >>>%s<<< combobox ",optionName,controlName)
		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(control.isCheckBoxEnabled(optionName),passMsg,errMsg);
	}

	public void verifyOptionIsDisabledInComboBox(String controlName, String optionName, String pageName, String errMsg="The option is checked in the combo box"){
		String passMsg = String.format("The option >>>%s<<< is unchecked in >>>%s<<< combobox ",optionName,controlName)
		ComboBox control = controlFactory.initControl(ComboBox, controlName,pageName);
		AssertSteps.verifyExpectedResult(!control.isCheckBoxEnabled(optionName),passMsg,errMsg);
	}

	public void verifyFileIsDownloaded(String downloadPath, String fileName){
		String passMsg = String.format("The file %s is downloaded at the folder %s",fileName,downloadPath);
		String errMsg = String.format("The file %s is NOT downloaded at the folder %s",fileName, downloadPath)
		AssertSteps.verifyFileIsDownloaded(PlatformUtil.isFileDownloaded(downloadPath, fileName),passMsg,errMsg);
	}


	public String getTextFromControlByReplacedTextInXpath(String xpath, String text) {
		String locator = String.format(xpath,text.trim())
		BaseControl control = new BaseControl(locator)
		return control.getText();
	}




	// ===================== TABLE STEPS =====================
	// ===================== TABLE COMMON ACTION STEPS =====================
	public String getRowTableCounts(String tableName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getRowCounts().toString();
	}

	public String getRowTableText(String tableName, String rowIndex="1", String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getRowText(rowIndex);
	}
	public String getCellLinkTableAttributeBasedOnCellContainsText(String tableName, String conditionColumn, String conditionData, String colName, String attribute, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)) {
				return control.getCellLinkAttribute(i.toString(), colName, attribute);
				break;
			}
		}
	}
	//
	public String getCellTableText(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getCellText(rowIndex, colName);
	}

	public String getCellTableValue(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getCellValue(rowIndex, colName);
	}

	public String getCellFooterTableText(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getCellTextFromFooter(rowIndex, colName)
	}

	public String getCheckBoxCellTableValue(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		if(control.getCheckBoxCellValue(rowIndex, colName)) {
			return "checked";
		}else {
			return "unchecked";
		}
	}

	public String getBodyTableText(String tableName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		return control.getBodyText();
	}

	public void clickToCellTableCheckBoxControl(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToCellCheckBox(rowIndex, colName);
	}

	public void clickToCellTableCheckBoxWithoutColumnNameControl(String tableName, String rowIndex="1", String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToCellCheckBoxWithoutColumnName(rowIndex);
	}

	public void clickToHeaderTableCheckBoxControl(String tableName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToHeaderCheckbox();
	}

	public void clickToCellTableLink(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);

		control.clickToCellLink(rowIndex, colName);
	}

	public void clickToCellTableIcon(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);

		control.clickToCellIcon(rowIndex, colName);
	}


	public void clickToCellTableText(String tableName, String rowIndex="1", String colName, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.clickToCellText(rowIndex, colName);
	}

	public void clickToCellTableLinkBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToClick, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				clickToCellTableLink(tableName, i.toString(), colNameToClick,pageName);
				break;
			}
		}
	}

	public void clickToCellTableLinkBaseOnMultipleCellDataTable(String tableName, HashMap<String, String> dataMap,
			String colNameToClick, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			boolean status = false;
			for (Map.Entry<Integer, String> entry : dataMap.entrySet()) {
				if(entry.getValue().contains("checked")) {
					status = getCheckBoxCellTableValue(tableName, i.toString(), entry.getKey(), pageName).contains(entry.getValue().trim())
				}else {
					status = getCellTableText(tableName, i.toString(),entry.getKey() ,pageName).contains(entry.getValue().trim())
				}
				if(status ==false) {break;}
			}
			if(status) {
				clickToCellTableLink(tableName, i.toString(),colNameToClick,pageName);
				break;
			}
		}

	}


	public void clickToCellTableTextBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToClick, String pageName){
		waitForTableDataLoaded(tableName,pageName);

		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				clickToCellTableText(tableName, i.toString(), colNameToClick,pageName);
				break;
			}
		}
	}

	public void clickToCellTableTextBaseOnMultipleCellDataTable(String tableName, HashMap<String, String> dataMap,
			String colNameToClick, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			boolean status = false;
			for (Map.Entry<Integer, String> entry : dataMap.entrySet()) {
				if(entry.getValue().contains("checked")) {
					status = getCheckBoxCellTableValue(tableName, i.toString(), entry.getKey(), pageName).contains(entry.getValue().trim())
				}else {
					status = getCellTableText(tableName, i.toString(),entry.getKey() ,pageName).contains(entry.getValue().trim())
				}
				if(status ==false) {break;}
			}
			if(status) {
				clickToCellTableText(tableName, i.toString(),colNameToClick,pageName);
				break;
			}
		}

	}

	public void clickToCellTableCheckboxBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToClick ="",String pageName){
		waitForTableDataLoaded(tableName,pageName);

		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				if(Strings.isNullOrEmpty(colNameToClick)) {
					clickToCellTableCheckBoxWithoutColumnNameControl(tableName, i.toString(),pageName);
				}else {
					clickToCellTableCheckBoxControl(tableName, i.toString(), colNameToClick, pageName);
				}
				break;
			}
		}
	}

	public void clickToCellTableCheckboxWithoutFirstRowBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToClick ="",String pageName){
		waitForTableDataLoaded(tableName,pageName);

		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=2;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				if(Strings.isNullOrEmpty(colNameToClick)) {
					clickToCellTableCheckBoxWithoutColumnNameControl(tableName, i.toString(),pageName);
				}else {
					clickToCellTableCheckBoxControl(tableName, i.toString(), colNameToClick, pageName);
				}
				break;
			}
		}
	}

	public String getCellTextBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				return getCellTableText(tableName, i.toString(),colNameToGetText,pageName);
			}
		}
	}

	public String getCellTextBaseOnAnotherCellValueTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableValue(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				return getCellTableText(tableName, i.toString(),colNameToGetText,pageName);
			}
		}
	}
	public String getCellTextOfFooterBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellFooterTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				return getCellFooterTableText(tableName, i.toString(),colNameToGetText,pageName);
			}
		}
	}

	public String getCellTextBaseOnAnotherCellValueAndCellTextTable(String tableName, String conditionColumn1, String conditionData1, String conditionColumn2, String conditionData2, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableValue(tableName, i.toString(),conditionColumn1,pageName).contains(conditionData1) && getCellTableText(tableName, i.toString(),conditionColumn2,pageName).contains(conditionData2)){
				return getCellTableText(tableName, i.toString(),colNameToGetText,pageName);
			}
		}
	}

	public String getCellValueBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				return getCellTableValue(tableName, i.toString(),colNameToGetText,pageName);
			}
		}
	}

	public String getCellTextBaseOnMultipleCellDataTable(String tableName, HashMap<String, String> dataMap,
			String colNameToGetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			boolean status = false;
			for (Map.Entry<Integer, String> entry : dataMap.entrySet()) {
				if(entry.getValue().contains("checked")) {
					status = getCheckBoxCellTableValue(tableName, i.toString(), entry.getKey(), pageName).contains(entry.getValue().trim())
				}else {
					status = getCellTableText(tableName, i.toString(),entry.getKey() ,pageName).contains(entry.getValue().trim())
				}
				if(status ==false) {break;}
			}
			if(status) {
				return getCellTableText(tableName, i.toString(),colNameToGetText,pageName);
			}
		}

	}


	public void setTextToCellTableControl(String tableName, String rowIndex="1", String colName, String text, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.setCellText(text, rowIndex, colName);
	}

	public void setCellTextBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String text, String colNameToSetText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));

		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				setTextToCellTableControl(tableName, i.toString(),colNameToSetText,text,pageName);
				break;
			}
		}
	}

	public void selectCellItemComboboxTableControl(String tableName, String rowIndex="1", String colName, String itemName,boolean isClickToOpen = true, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		Table control = controlFactory.initControl(Table, tableName, pageName);
		control.selectItemInCombobox(itemName, rowIndex, colName,isClickToOpen);
	}

	//	public void selectCellItemComboboxBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData,
	//		String item, String colNameToSelect, String pageName){
	//		waitForTableDataLoaded(tableName,pageName);
	//		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
	//
	//		for(int i=1;i<=rows;i++){
	//			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
	//				setTextToCellTableControl(tableName, i.toString(),colNameToSelect,item,pageName);
	//				break;
	//			}
	//		}
	//	}

	public void waitForTableDataLoaded(String tableName, int timeoutInSeconds = Timeout.WAIT_TIMEOUT_SHORT_SECONDS, String pageName){
		Table control = controlFactory.initControl(Table,tableName,pageName);
		control.waitForDataLoaded(timeoutInSeconds);
	}

	// ===================== TABLE COMMON Table VERIFY STEPS =====================

	public void verifyTableBodyContainData(String controlName, String expText, String pageName, String errMsg="The expected text is not include in the table"){
		String passMsg = String.format("The table >>>%s<<< contains >>>%s<<< data ",controlName,expText)
		String tableBody = getBodyTableText(controlName, pageName).toLowerCase();
		AssertSteps.verifyExpectedResult(tableBody.contains(expText.toLowerCase()), passMsg, errMsg)
	}

	public void verifyCellValueBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetValue, String expectedText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		String result="";
		String msg = String.format("The >>>%s<<< row contains >>>%s<<< value at >>>%s<<< column",conditionData,expectedText,colNameToGetValue)


		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				result=  getCellTableValue(tableName, i.toString(),colNameToGetValue,pageName);
				break;
			}
		}

		AssertSteps.verifyExpectedResult(expectedText.contains(result), msg, msg)
	}

	public void verifyCellTextBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetText, String expectedText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		String result="";
		String msg = String.format("The >>>%s<<< row contains >>>%s<<< text at >>>%s<<< column",conditionData,expectedText,colNameToGetText)


		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
				result=  getCellTableText(tableName, i.toString(),colNameToGetText,pageName);
				break;
			}
		}

		AssertSteps.verifyExpectedResult(expectedText.contains(result), msg, msg)
	}

	public void verifyCellValueBaseOn2CellsDataTable(String tableName, String conditionColumn1, String conditionData1, String conditionColumn2, String conditionData2,String colNameToGetValue, String expectedText, String pageName){
		waitForTableDataLoaded(tableName,pageName);
		int rows =Integer.parseInt(getRowTableCounts(tableName,pageName));
		String result="";
		String msg = String.format("The >>>%s<<< row contains >>>%s<<< value",conditionData1,expectedText)


		for(int i=1;i<=rows;i++){
			if(getCellTableText(tableName, i.toString(),conditionColumn1,pageName).contains(conditionData1) && getCellTableText(tableName, i.toString(),conditionColumn2,pageName).contains(conditionData2)){
				result =  getCellTableValue(tableName, i.toString(),colNameToGetValue,pageName);
				break;
			}
		}

		AssertSteps.verifyExpectedResult(expectedText.contains(result), msg, msg)
	}

	public void verifyTableDisplayed(String tableName, String pageName, String errMsg="The table is not displayed") {
		waitForTableDataLoaded(tableName,pageName);
		verifyControlDisplayed(tableName, pageName)

	}
	public void verifyTableBodyNotContainData(String controlName, String expText, String pageName, String errMsg="The expected text is not include in the table"){
		String passMsg = String.format("The table >>>%s<<< not contains >>>%s<<< data ",controlName,expText)
		String tableBody = getBodyTableText(controlName, pageName);
		AssertSteps.verifyExpectedResult(!tableBody.contains(expText), passMsg, errMsg)
	}

}
