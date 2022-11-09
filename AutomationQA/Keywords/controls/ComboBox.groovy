package controls
import core.Browser
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import com.google.common.base.Strings
import configs.Timeout
import core.BaseControl
import core.Logger
import utils.Utilities
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords


public class ComboBox extends BaseControl {
	public ComboBox(){}
	public ComboBox(String xpath){
		super(By.xpath(xpath));
	}
	public ComboBox(By by){
		super(by);
	}
	public ComboBox(WebElement element){
		super(element);
	}

	public String getSelectedText(){
		String result=getValue();
		if(Strings.isNullOrEmpty(result)) {
			result = getText();
		}
		return result;
	}

	public List<String> getOptionText(boolean isClickToOpen=true){
		if(isClickToOpen){
			click();
			Utilities.waitForAWhile(Timeout.WAIT_TIMEOUT_FEW_SECONDS)
		}
		List<WebElement> lstOptions = super.findElements(By.xpath("//div[(contains(@class,'rcbSlide') and not(contains(@style,'none;'))) or (@id='divActions')]//ul/li"));//not include filter textbox and check/select all
		List<String> lstOptionText = new ArrayList<String>();
		for(WebElement e in lstOptions){
			if(!Strings.isNullOrEmpty(e.getText())){
				lstOptionText.add(e.getText());
			}
		}
		if(isClickToOpen){click();}
		return lstOptionText;
	}

	public void selectByText(String text,boolean isClickToOpen=true, boolean isDeselectAll=false){
		try {
			String type1 = "(contains(@class,'DropDown') and contains(@style,'visible'))"
			String type2 = "(contains(@class,'dropup') and contains(@class,'open'))"
			String type3 = "(contains(@class,'bs3') and contains(@class,'open'))"
			String type4 = "(contains(@class,'rddlSlide') and contains(@style,'visible'))"
			String type5 = "(contains(@class,'dd_chk_drop'))"

			if(isClickToOpen){
				click();
			}
			if(isDeselectAll) {
				this.by = By.xpath(String.format("//div[%s or %s or %s or %s or %s]//button[contains(@class,'bs-deselect-all')]",type1,type2,type3,type4,type5));
				click();
			}
			this.by = By.xpath(String.format("//div[%s or %s or %s or %s or %s]//div/descendant::*[normalize-space()='%s'][last()]",type1,type2,type3,type4,type5, text.trim()));
			//this.by = By.xpath(String.format("//div[%s or %s or %s or %s]//li[normalize-space()='%s']",type1,type2,type3,type4,text.trim()));
			click();
		}
		catch(Throwable e)
		{
			Browser.takeScreenshot();
			WebUiBuiltInKeywords.takeFullPageScreenshot();
		}

	}

	public void selectByContainedText(String text,boolean isClickToOpen=true, boolean isClickByJS=false){
		String type1 = "(contains(@class,'DropDown') and contains(@style,'visible'))"
		String type2 = "(contains(@class,'dropup') and contains(@class,'open'))"
		String type3 = "(contains(@class,'bs3') and contains(@class,'open'))"
		String type4 = "(contains(@class,'rddlSlide') and contains(@style,'visible'))"
		String type5 = "(contains(@class,'dd_chk_drop'))"

		if(isClickToOpen && isClickByJS==false){
			click();
			this.by = By.xpath(String.format("//div[%s or %s or %s or %s or %s]//div/descendant::*[contains(normalize-space(),'%s')][last()]",type1,type2,type3,type4,type5, text.trim()));
			click();
		}else if(isClickToOpen==false && isClickByJS==true) {
			this.by = By.xpath(String.format("//div[%s or %s or %s or %s or %s]//div/descendant::*[contains(normalize-space(),'%s')][last()]",type1,type2,type3,type4,type5, text.trim()));
			clickByJS();
		}else if(isClickToOpen && isClickByJS==true){
			clickByJS();
			this.by = By.xpath(String.format("//div[%s or %s or %s or %s or %s]//div/descendant::*[contains(normalize-space(),'%s')][last()]",type1,type2,type3,type4,type5, text.trim()));
			clickByJS();
		}else if(isClickToOpen==false && isClickByJS==false){
			this.by = By.xpath(String.format("//div[%s or %s or %s or %s or %s]//div/descendant::*[contains(normalize-space(),'%s')][last()]",type1,type2,type3,type4,type5, text.trim()));
			click();
		}

	}

	public void selectCheckboxByContainedText(String text,boolean isClickToOpen=true){
		if(isClickToOpen){
			click();
		}
		this.by = By.xpath(String.format("//div[(contains(@class,'DropDown') and contains(@style,'visible')) or (contains(@class,'dropup') and contains(@class,'open')) or (contains(@class,'bs3') and contains(@class,'open'))]//*[contains(text(),'%s')]//input",text.trim()));
		click();
	}

	public void selectCheckboxByContainedTextInAList(String text,boolean isClickToOpen=true){
		if(isClickToOpen){
			click();
		}
		this.by = By.xpath(String.format("//div[(contains(@class,'dd_chk_drop') and not(contains(@style,'none')))]//label[contains(text(),'%s')]/preceding-sibling::input[@type='checkbox'][1]",text.trim()));
		click();
	}

	//Added by Sunita, not yet used. Here you can pass id and the text to select from the dropdown
	public void selectCheckboxByContainedTextInAListById(String id, String text,boolean isClickToOpen=true){
		if(isClickToOpen){
			click();
		}
		this.by = By.xpath(String.format("//div[(contains(@id,'%s'))]//label[contains(text(),'%s')]/preceding-sibling::input[@type='checkbox'][1]",id.trim(), text.trim()));
		click();
	}

	public boolean isOptionDefault(String optName){
		try {
			return getSelectedText().contains(optName);
		}catch(Throwable e) {
			Logger.logINFO(String.format("The error is: %s", this.by,e.getMessage()));
			return false;
		}
	}

	public boolean isOptionIncluded(String optName, boolean isClickToOpen=true ){
		try {
			//return getSelectedText().contains(optName);
			List<String> lstOptionText = getOptionText(isClickToOpen);
			for (String s in lstOptionText){
				if(s.contains(optName.trim())){
					return true;
				}
			}
		}catch(Throwable e) {
			Logger.logINFO(String.format("The error is: %s", this.by,e.getMessage()));
			return false;
		}
	}

	public boolean isCheckBoxChecked(String optName,boolean isClickToOpen=true) {
		try {
			if(isClickToOpen){
				click();
			}
			this.by = By.xpath(String.format("//label[contains(text(),'%s')]/preceding-sibling::input[@type='checkbox'][1]",optName.trim()));

			CheckBox checkBox = new CheckBox(this.by)

			return checkBox.isChecked();
		}catch(Throwable e) {
			Logger.logINFO(String.format("The error is: %s", this.by,e.getMessage()));
			return false;
		}
	}

	public boolean isCheckBoxEnabled(String optName,boolean isClickToOpen=true) {
		try {
			if(isClickToOpen){
				click();
			}
			this.by = By.xpath(String.format("//label[contains(text(),'%s')]/preceding-sibling::input[@type='checkbox'][1]",optName.trim()));
			return isEnabled();
		}catch(Throwable e) {
			Logger.logINFO(String.format("The error is: %s", this.by,e.getMessage()));
			return false;
		}
	}
}
