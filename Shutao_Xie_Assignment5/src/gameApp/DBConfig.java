package gameApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	private static final String USERNAME = "shutao";
	private static final String PASSWORD = "123456";
	private static final String CONN_STRING = "jdbc:mysql://localhost/game"; 
	
	public static Connection getConnection() throws SQLException{

		return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
	}
	
	public static void displayException(SQLException exception){
			
		System.err.println("Error Message: " + exception.getMessage());
		System.err.println("Error Code: " + exception.getErrorCode());
		System.err.println("SQL State: " + exception.getSQLState());
			
	}
}
