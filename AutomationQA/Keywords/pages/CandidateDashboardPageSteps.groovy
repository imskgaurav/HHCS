package pages

import controls.Table
import core.BaseSteps
import core.ControlFactory


public class CandidateDashboardPageSteps {
	private static url = '/CandidateDashboard.aspx';
	
	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory());
	}
	
	// =================== ACTION METHODS ZONE ======================
	public static String getColumnValues(String columnName, String jobID) {
		String strJobIDDetails = null
		List<String> result = new ArrayList<>()
		String rowCount = getBaseSteps().getRowTableCounts("JobListMasterTable", "CandidateDashboardPage")
		int rows = Integer.parseInt(rowCount)
		//List<String> gridExtIDData = new ArrayList<>()
		//for(int i=rows-1; i>0 ; i--) { // traversing reverse to match the list with excel
			for(int i=1;i<rows;i++) {
			String strColValues = getBaseSteps().getCellTableText("JobListMasterTable", i.toString(), columnName, "CandidateDashboardPage")
			result=result+(strColValues.trim().concat(" "))
			if(strColValues.equals(jobID)) {
				strJobIDDetails = getBaseSteps().getRowTableText("JobListMasterTable", i.toString(), "CandidateDashboardPage")
			}
		}
		return strJobIDDetails
	}
	
	
//	public static void getCSSValue(String tableName,String rowIndex="1", String colNameToGetCSSValue, String pageName) {
//		getBaseSteps().waitForTableDataLoaded(tableName,pageName);
//		Table control = new Table()
//		control.initControl(Table, tableName, pageName);
//
//		control.clickToCellLink(rowIndex, colName);
//	}
//	public static getCSSValueFromTableBaseOnAnotherCellDataTable(String tableName, String conditionColumn, String conditionData, String colNameToGetCSSValue, String pageName){
//		getBaseSteps().waitForTableDataLoaded(tableName,pageName);
//		int rows =Integer.parseInt(getBaseSteps().getRowTableCounts(tableName,pageName));
//
//		for(int i=1;i<=rows;i++){
//			if(getBaseSteps().getCellTableText(tableName, i.toString(),conditionColumn,pageName).contains(conditionData)){
//				//getBaseSteps().clickToCellTableLink(tableName, i.toString(), colNameToClick,pageName);
//				i.getCs
//				getCSSValue(tableName, i.toString(), colNameToGetCSSValue,pageName);
//				break;
//			}
//		}
//	}
//	
//	
//	public String getOptionBackgroundColor(String option) {
//		String bgColorCode,bgColor
//		loadControl();
//		Select ddloption = new Select(this.element);
//		List<WebElement> Options = ddloption.getOptions();
//		for (int i = 0; i < Options.size(); i++) {
//
//			if(Options.get(i).getText().trim().startsWith(option)) {
//
//				//String eStyle=Options.get(i).getAttribute('style')
//				//bgColor=((eStyle.replace('background-color:', '')).replace(';', '')).replace('border: 2px dashed green', '').trim()
//				bgColorCode=Options.get(i).getCssValue('background-color')
//				bgColor=CommonUtilities.getColorNameForHexCode(Color.fromString(bgColorCode).asHex())
//				break
//			}
//		}
//		return bgColor
//	}
//
//	public String getBackgroundColor() {
//		String bgColorCode,bgColor
//		loadControl();
//		Select ddloption = new Select(this.element);
//		List<WebElement> Options = ddloption.getOptions();
//		for (int i = 0; i < Options.size(); i++) {
//
//			if(Options.get(i).getText().trim().startsWith(option)) {
//
//				//String eStyle=Options.get(i).getAttribute('style')
//				//bgColor=((eStyle.replace('background-color:', '')).replace(';', '')).replace('border: 2px dashed green', '').trim()
//				bgColorCode=Options.get(i).getCssValue('background-color')
//				bgColor=CommonUtilities.getColorNameForHexCode(Color.fromString(bgColorCode).asHex())
//				break
//			}
//		}
//		return bgColor
//	}
//	
}
