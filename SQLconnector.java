package content;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class SQLconnector {
	private static Connection conn;
    public static Connection getConnection() {
    	try {
    	    String dbURL = "jdbc:sqlserver://localhost;databaseName=StudentManager;user=sa;password=123456";
    	    conn = DriverManager.getConnection(dbURL);
    	    if (conn != null) {
    	      System.out.println("Connected");
    	      DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
    	      System.out.println("Driver name: " + dm.getDriverName());
    	      System.out.println("Driver version: " + dm.getDriverVersion());
    	      System.out.println("Product name: " + dm.getDatabaseProductName());
    	      System.out.println("Product version: " + dm.getDatabaseProductVersion());
    	    }
    	   } catch (SQLException ex) {
    	     System.err.println("Cannot connect database, " + ex);
    	   }
    	return conn;
    }
	
	
	/*public static void main(String[] args)
	{
		try {
    	    String dbURL = "jdbc:sqlserver://localhost;databaseName=StudentManager;user=sa;password=123456";
    	    conn = DriverManager.getConnection(dbURL);
    	    if (conn != null) {
    	      System.out.println("Connected");
    	      DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
    	      System.out.println("Driver name: " + dm.getDriverName());
    	      System.out.println("Driver version: " + dm.getDriverVersion());
    	      System.out.println("Product name: " + dm.getDatabaseProductName());
    	      System.out.println("Product version: " + dm.getDatabaseProductVersion());
    	    }
    	   } catch (SQLException ex) {
    	     System.err.println("Cannot connect database, " + ex);
    	   }
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Students");
			
			while (rs.next()) {
				System.out.println(rs.getInt("ID")+" "+rs.getString("Name")+
				" "+rs.getString("Address")+" "+rs.getString("Class")+
				" "+rs.getByte("BirthYear")+" "+rs.getFloat("GPA"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
