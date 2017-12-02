package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	private static DB instance;
	public Connection cn;
	
	private DB() {}
	public static DB Instance() {
		if(DB.instance == null) {
			DB.instance = new DB();
		}
		return DB.instance;
	}
	
	public void Connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-G21B0UL\\SQLEXPRESS:50290;databaseName=webraovat;user=sa; password=123";
			cn = DriverManager.getConnection(url);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
