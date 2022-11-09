package utils

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.Statement

import com.google.common.base.Strings
import com.kms.katalon.core.model.FailureHandling

import configs.Timeout
import core.AssertSteps
import core.Logger
import internal.GlobalVariable



public class Database {
	private static Connection connection = null;

	private static String BACKUP_STATEMENT = """INSERT INTO [dbo].[RequestAutomationBackupRestore] (RequestDateTime, RequestType, FileSuffix, [StatusDateTime], RequestStatus)
VALUES (GETDATE(), 'backup', '%s', GETDATE(),'created');"""// %s = prefix name

	private static String TRIGGER_STATEMENT = """EXEC sp_defaultdb 'vmsqa', 'Norton_QA_Automation';"""

	private static String GET_RESULT_TRIGGER_STATEMENT ="""SELECT * from [dbo].[RequestAutomationBackupRestore] order by StatusDateTime desc"""

	private static String RESTORE_STATEMENT = """INSERT INTO [dbo].[RequestAutomationBackupRestore] (RequestDateTime, RequestType, FilePathName, [StatusDateTime], RequestStatus)
VALUES (GETDATE(), 'restore', '%s', GETDATE(),'created');""" //%s = file back up name


	public static Connection connectDB(String url, String username, String password){

		password = EncodeUtil.decode(EncodeUtil.getDefault(password))
		if(connection != null && !connection.isClosed()) {
			connection.close()
		}
		connection = DriverManager.getConnection(url, username, password)
		return connection;
	}


	public static ResultSet executeQuery(String queryString) {
		Statement stm = connection.createStatement()
		ResultSet rs = stm.executeQuery(queryString)
		return rs
	}


	public static void closeDatabaseConnection() {
		if(connection != null && !connection.isClosed()) {
			connection.close()
		}
		connection = null
	}



	public static boolean execute(String queryString) {
		Statement stm = connection.createStatement()
		return stm.execute(queryString)
	}

	public static String getExecutedResult(String query) {
		ResultSet rs = executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		String result="";
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) {
					result=result.concat(",  ")
				}
				String columnValue = rs.getString(i);
				result=result.concat(columnValue + " " + rsmd.getColumnName(i));
			}
			result=result.concat("@");
		}
		return result;
	}


	public static String backup(String prefixVersion) {
		try {
			connectDB(GlobalVariable.DB_CONNECTION_URL, GlobalVariable.DB_USER, GlobalVariable.DB_PASS)
			execute(String.format(BACKUP_STATEMENT, prefixVersion))
			execute(TRIGGER_STATEMENT)
		}catch(Throwable e){
			Logger.logINFO(e.getMessage())
		}
		Utilities.waitForAWhile(60)
		connectDB(GlobalVariable.DB_CONNECTION_URL, GlobalVariable.DB_USER, GlobalVariable.DB_PASS)
		Utilities.waitForAWhile(Timeout.WAIT_TIMEOUT_DATABASE_CONNECTION_SECONDS)

		String result = getExecutedResult(GET_RESULT_TRIGGER_STATEMENT)
		result = result.substring(0, result.indexOf("@"))

		if(result.contains("backup RequestType") && result.contains(prefixVersion) && result.contains("completed")) {
			result = result.substring(result.indexOf("Norton"),result.indexOf("bak")+3) // 3 is bak index
		}
		return result;
	}

	public static void restore(String backFileName=GlobalVariable.DB_BACKUP_NAME) {
		String result=""
		try {
			connectDB(GlobalVariable.DB_CONNECTION_URL, GlobalVariable.DB_USER, GlobalVariable.DB_PASS)
			execute(String.format(RESTORE_STATEMENT, backFileName))
			execute(TRIGGER_STATEMENT)
		}catch(Exception e){
			Logger.logINFO(e.getMessage())
		}
		try {
			Utilities.waitForAWhile(60)
			connectDB(GlobalVariable.DB_CONNECTION_URL, GlobalVariable.DB_USER, GlobalVariable.DB_PASS)
			Utilities.waitForAWhile(Timeout.WAIT_TIMEOUT_DATABASE_CONNECTION_SECONDS)
			result = getExecutedResult(GET_RESULT_TRIGGER_STATEMENT)
			result = result.substring(0, result.indexOf("@"))

			if(result.contains("restore RequestType") && result.contains(backFileName) && result.contains("completed")) {
				result = result.substring(result.indexOf("Norton"),result.indexOf("bak")+3) // 3 is bak index
			}
		}catch(Exception e){
			Logger.logINFO(e.getMessage())
		}
		AssertSteps.verifyRestoreData(backFileName.equals(result),"Restore successfully","Restore Failed",FailureHandling.STOP_ON_FAILURE)
	}


}
