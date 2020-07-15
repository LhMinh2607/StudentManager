package content;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args)
	{
		//SinhVien s = new SinhVien("","","",0,"","","");
		//s.input();
		//s.output();

		//SQLconnector sql = new SQLconnector();
		//sql.getConnection("jdbc:sqlserver://localhost:1433;", "LhMinh2607", "123456");
        AppGUI window = new AppGUI();
		window.frame.setVisible(true);
		
		/*StudentDAO std = new StudentDAO();
		Student st= new Student(123,"Ly Hoang Long","Blank","CNTT",2000,6.9);
		std.save(st);*/
		//System.out.println(st.getName());
		
	}
}
