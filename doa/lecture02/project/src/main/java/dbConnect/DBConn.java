package dbConnect;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConn {
	static String url = "jdbc:mysql://localhost:3306/todolist?characterEncoding=UTF-8&serverTimezone=UTC";
	static String user = "connect";
	static String passwd = "1234";
	static String driver = "com.mysql.cj.jdbc.Driver";
	public Connection DBConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		
		try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, passwd);
		} catch(SQLException e) {
			System.out.println("SQL Error");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}