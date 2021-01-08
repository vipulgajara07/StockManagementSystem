package stock;

import java.sql.Connection;
import java.sql.DriverManager;

class DatabaseConnect {
	Connection conn;
	
	DatabaseConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagementsystem","root", "");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
