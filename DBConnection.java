package content;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
	public static final String url = "jdbc:ucanaccess://db//Students.accdb";
	Connection con = null;

	public Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection(url);
				System.out.println("Successful.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		DBConnection db = new DBConnection();
		
		db.getConnection();
	}
	
	
}
