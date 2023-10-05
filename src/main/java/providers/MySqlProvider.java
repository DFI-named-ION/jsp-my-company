package providers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlProvider {
	
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_HOST = "jdbc:mysql://localhost:3306/company_db";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "mysql";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DB_DRIVER);
		return DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
	}
}
