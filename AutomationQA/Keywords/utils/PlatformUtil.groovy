package utils

import com.kms.katalon.core.configuration.RunConfiguration

import configs.FileExtension
import configs.Timeout
import core.HMException
import core.Logger

public class PlatformUtil {

	private static String OS = System.getProperty("os.name").toLowerCase();
	private static final String downloadPath = System.getProperty("user.dir") + "\\ExportedFiles"

	public static String getUserAccount(){
		return System.getProperty("user.name");
	}

	public static String getDownloadPath(){
		try{
			String user = getUserAccount();
			if(isMac()) {
				return "/Users/${user}/Downloads/";
			}
			if(isWindows()) {
				return downloadPath;
				//return "C:\\Users\\${user}\\Downloads\\";
			}
		}catch(HMException e){
			Logger.logERROR(String.format("Cannot get the Download path. Error: %s",e.getMessage()));
		}
	}

	public static String getFolderProjectPath(String folderName){

		if(isMac()) {
			return "${RunConfiguration.getProjectDir()}/".concat(folderName);
		}
		if(isWindows()) {
			return "${RunConfiguration.getProjectDir()}/".concat(folderName).replace('/', '\\')
		}
	}

	public static String getFileProjectPath(String fileName){
		return getFolderProjectPath(fileName);
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	/**
	 * This public method is used to check a file is download successfully or Not
	 * @param downloadPath: this is a directory path where store the download file
	 * @param fileName: this is a name of download file
	 * @return boolean: this returns a download status (true/false)
	 */
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		try{
			File fullFilePath;
			Utilities.waitForAWhile(Timeout.WAIT_TIMEOUT_FEW_SECONDS);
			if(isMac()) {
				fullFilePath = new File(String.format("%s/%s",downloadPath,fileName));
			}else if(isWindows()) {
				fullFilePath = new File(String.format("%s\\%s",downloadPath,fileName));
			}

			if(!fullFilePath.exists()){
				Utilities.waitForAWhile(Timeout.WAIT_TIMEOUT_FEW_SECONDS);
			}
			File directory = new File(downloadPath);
			File[] lstDirectoryFile = directory.listFiles();
			for(File file:lstDirectoryFile){
				if(file.getName().contains(fileName)&& file.getName().matches(FileExtension.FILE_EXTENSION_PATTERN)){
					return true;
				}
			}
			return false;
		}catch(HMException e){
			Logger.logERROR(String.format("File %s is NOT found at %s. Error: %s",fileName,downloadPath, e.getMessage()));
		}
	}

	public static void deleteAllFilesWithPrefix(String folderPath, String prefixFileName){
		try{
			File directory = new File(folderPath);

			for (File f: directory.listFiles()) {
				if (f.getName().startsWith(prefixFileName)) {
					f.delete();
				}
			}
		}catch(HMException e){
			Logger.logERROR(String.format("Cannot delete files in %s: Error: %s",folderPath, e.getMessage()));
		}
	}
	//Adding more Utility //
	public static String getFilePath(String folderPath, String fileName) {
		String filePath="";
		if(isMac()) {
			filePath = String.format("%s%s",folderPath,fileName);
		
		}else if(isWindows()) {
			filePath = String.format("%s\\%s",folderPath,fileName);
			}
		return filePath;
	}


	public static String getFullFileNameInFolderByAPartOfName(String folderPath, String aPartOfFileName) {
		try {
			aPartOfFileName = aPartOfFileName.substring(0, aPartOfFileName.indexOf('.'))
		}catch(StringIndexOutOfBoundsException e) {
			Logger.logERROR(String.format("File does not include extension. Err:%s",e.getMessage()))
		}
		File directory = new File(folderPath);
		File[] lstDirectoryFile = directory.listFiles();
		for(File file:lstDirectoryFile){
			if(file.getName().contains(aPartOfFileName)&& file.getName().matches(FileExtension.FILE_EXTENSION_PATTERN)){
				return file.getName();
			}
		}
		return "";
	}

}

