package controls

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.google.common.base.Strings

import core.BaseControl



public class Menu extends BaseControl {
	public Menu(){}
	public Menu(String xpath){
		super(By.xpath(xpath));
	}
	public Menu(By by){
		super(by);
	}
	public Menu(WebElement element){
		super(element);
	}

	public void selectSubMenu(String subMenu, String subItemMenu="") {
		click();
		this.by= By.xpath(String.format("//div[@id='ctl00_MenuUserCntrl1_RadMenu1']//a[.='%s']",subMenu.trim()))
		click();
		if(!Strings.isNullOrEmpty(subItemMenu)) {
			this.by= By.xpath(String.format("//div[@id='ctl00_MenuUserCntrl1_RadMenu1']//li/a[.='%s']",subItemMenu.trim()))
			click();
		}
	}

	public void openSubMenuInNewTab(String subMenu) {
		click();
		this.by= By.xpath(String.format("//div[@id='ctl00_MenuUserCntrl1_RadMenu1']//a[.='%s']",subMenu.trim()))
		openLinkInNewTab();
	}
}
