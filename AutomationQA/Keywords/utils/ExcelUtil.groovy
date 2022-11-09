package utils


import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kms.katalon.core.model.FailureHandling

import core.BaseSteps;
import core.ControlFactory;
import core.Browser
import core.AssertSteps


public class ExcelUtil{

	protected static BaseSteps getBaseSteps(){
		return new BaseSteps(new ControlFactory())
	}


	public static String getFullRowContent(int rowNum,String filePath, String sheetIndex="0"){

		FileInputStream fis = new FileInputStream(filePath)
		XSSFWorkbook wb = new XSSFWorkbook(fis)
		XSSFSheet sheet = wb.getSheetAt(0)
		//Iterator<Row> objIterator = sheet.rowIterator()
		//Set the Formula//
		String str= ""

		int currentRow=rowNum
		//println
		DataFormatter df= new DataFormatter()

		for (XSSFRow row: sheet.getRow(currentRow)) {


			for (Cell cell: row) {
				Iterator<Cell> cellIterator = row.cellIterator()
				while(cellIterator.hasNext()) {
					Cell cellValue = cellIterator.next()

					Object obj=df.formatCellValue(cellValue)
					str=(str+obj.toString()).concat(" ")
					println str

				}

			}


		}



		return str
	}
	public static int getRowCountFromFile(String filePath, String sheetIndex="0"){

		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet workSheet = workBook.getSheetAt(Integer.parseInt(sheetIndex));
		return workSheet.getLastRowNum();
	}

	public static String getFormattedRowData(int rowNum,String filePath, String sheetIndex="0") {


		String fileName = filePath.substring(filePath.lastIndexOf(PlatformUtil.isWindows()?"\\":"/")+1)
		String folderPath = filePath.substring(0,filePath.lastIndexOf(PlatformUtil.isWindows()?"\\":"/")+1)
		File file = new File(folderPath)
		String fullFileName = PlatformUtil.getFullFileNameInFolderByAPartOfName(folderPath, fileName)
		filePath = PlatformUtil.getFilePath(folderPath, fullFileName)
		FileInputStream fis = new FileInputStream(filePath)
		Workbook workbook = WorkbookFactory.create(fis);
		//XSSFSheet sheet = wb.getSheetAt(Integer.parseInt(sheetIndex))

		String result = ""
		DataFormatter dataFormatter = new DataFormatter();
		CreationHelper creationHelper = workbook.getCreationHelper();
		FormulaEvaluator formulaEvaluator = creationHelper.createFormulaEvaluator();
		Sheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			for (Cell cell : row) {

				if (row.getRowNum() ==rowNum) {
					CellStyle cellStyle = cell.getCellStyle();
					String dataFormatString = cellStyle.getDataFormatString();
					if (dataFormatString != null && dataFormatString.contains(";"))
						cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(dataFormatString + ";"));
					String cellContent = dataFormatter.formatCellValue(cell, formulaEvaluator);
					//System.out.print(cellContent + " ");
					result=result+(cellContent.trim().concat(" "))
				}
			}


		}
		println 'Total Rows Content:'+ result
		workbook.close()
		return result
	}

	//Verify excel is downloaded successfully
	public static String verifyFileIsDownloadedSuccessfully(String prefixFile, String section = "") {

		String fileNameAfterExported = String.format("%s.xlsx",prefixFile)

		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		return fileNameAfterExported
	}


	//Verify File is Downloaded with MMddyyyy extention

	public static String verifyFileWithExtentionNameIsDownloadedSuccessfully(String prefixFile, String section = "") {
		String datetimeDownload = DateTimeUtil.getDateTime("MMddyyyy")
		String fileNameAfterExported = String.format("%s_%s.xlsx",prefixFile,datetimeDownload)

		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		return fileNameAfterExported
	}
	//		verify File is DownLaoded with dateandTime Extetion
	public static String verifyFileWithDateandTimeExtentionNameIsDownloadedSuccessfully(String prefixFile, String section = "",FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {
		String datetimeDownload = DateTimeUtil.getDateTime("MMddyyyy","EST")
		String localTime= DateTimeUtil.getLocalTimeMinusOneHour()
		String endFileName=datetimeDownload.concat(localTime)
		println endFileName
		String fileNameAfterExported = String.format("%s_%s.xlsx",prefixFile,endFileName)
		println ''+fileNameAfterExported
		if(section != "") {
			getBaseSteps().verifyMainPopUpHasContent("${section} data exported successfully.")
		}

		getBaseSteps().verifyFileIsDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)

		return fileNameAfterExported

	}


	//Export the Excel //
	public static String ExportExcel(String Button_Img, def reportName, String pageName,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE)
	{
		getBaseSteps().clickToControl(Button_Img, pageName)
		getBaseSteps().waitForControlDisplay('Export_Button', 'CommonLocators')
		'Select Export button'
		getBaseSteps().clickToControl('Export_Button', 'CommonLocators')
		Thread.sleep(5000)
		'Verify the attached is downloaded'
		String fileNameAfterExported = verifyFileIsDownloadedSuccessfully(reportName, String section = '')
		PlatformUtil.isFileDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		return fileNameAfterExported
	}

	public static String ExportToExcelInNewTab(String Button_Img, def reportName, String pageName,FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {

		getBaseSteps().clickToControl(Button_Img, pageName)
		getBaseSteps().waitForControlDisplay('Export_Button', 'CommonLocators')
		'Select Export button'
		getBaseSteps().clickToControl('Export_Button', 'CommonLocators')
		Thread.sleep(7000)
		//Browser.switchToNewOpenedWindow()
		getBaseSteps().waitForProgressBarDisappear()
		Browser.switchToDefaultWindow()
		'Verify the attached is downloaded'
		String fileNameAfterExported = verifyFileIsDownloadedSuccessfully(reportName, String section = '')
		PlatformUtil.isFileDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		return fileNameAfterExported

	}



	//Export Excel for Extention File Name //
	public static String VerifyExportExcel(String Button_Img, def reportName, String pageName)
	{
		getBaseSteps().clickToControl(Button_Img, pageName)
		getBaseSteps().waitForControlDisplay('Export_Button', 'CommonLocators')
		'Select Export button'
		getBaseSteps().clickToControl('Export_Button', 'CommonLocators')
		Thread.sleep(6000)
		'Verify the attached is Downloaded'
		String fileNameAfterExported = verifyFileWithExtentionNameIsDownloadedSuccessfully(reportName, String section = '')
		PlatformUtil.isFileDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		return fileNameAfterExported
	}

	public static VerifyExportExcelwithTimeStamp(String Button_Img, def reportName, String pageName,FailureHandling failurehandling=FailureHandling.CONTINUE_ON_FAILURE) {
		getBaseSteps().clickToControl(Button_Img, pageName)
		getBaseSteps().waitForControlDisplay('Export_Button', 'CommonLocators')
		'Select Export button'
		getBaseSteps().clickToControl('Export_Button', 'CommonLocators')
		Thread.sleep(300)
		'Verify the attached is downloaded'
		String fileNameAfterExported = verifyFileWithDateandTimeExtentionNameIsDownloadedSuccessfully(reportName, String section = '')
		PlatformUtil.isFileDownloaded(PlatformUtil.getDownloadPath(), fileNameAfterExported)
		return fileNameAfterExported

	}

	//Agency login Report//
	public static void verifyExcelFileContentWithNoSpaceIsCorrect(int rowNum, String filePath, String sheetIndex="0", String expectedContent){

		//String excelContent = ExcelUtil.getRowContent(4,filePath, sheetIndex).trim().replaceAll("[\\t\\n\\r\\s]+"," ");

		String excelContent = ExcelUtil.getFormattedRowData(rowNum, filePath)
		excelContent = Utilities.replaceAllSpacesOfString(excelContent)

		String msg = String.format("The expected content is: %s.%n The excel file content is: %s", expectedContent, excelContent )

		AssertSteps.verifyExpectedResult(excelContent.contains(expectedContent),msg,msg)
		//}
	}
}
