package supermarket;

import java.sql.DriverManager;
import java.sql.*;
public class MyConnection {
	public static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/shop";
		String user = "root";
		String password = "1234";
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con = DriverManager.getConnection(url, user, password);
			}
		
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return con;
	}
}
