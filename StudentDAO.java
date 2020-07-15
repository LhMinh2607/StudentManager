package content;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class StudentDAO implements DAO<Student, String> {
	SQLconnector sqlc = new SQLconnector();
	
	public List<Student> listStudents;
	
	
	@Override
	/*public List<Student> getAll() {
		List<Student> result = new ArrayList<Student>();
		
		try {
			Connection con = sqlc.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Students");
			//
			while (rs.next()) {
				String ID = rs.getInt("ID");
				String Name = rs.getString("Name");
				String Address = rs.getString("Address");
				String Class = rs.getString("Class");
				byte BirthYear = rs.getByte("BirthYear");
				float GPA = rs.getFloat("GPA");

				
				result.add(new Student(ID, Name, Address, Class, BirthYear, GPA));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlc.closeConnection();
		return result;
	}*/

	//@Override
	public Student get(String k) {
		Student result = null;
		Connection con = sqlc.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Students WHERE ID='" + k + "'");
			//
			if (rs.next()) {
				String ID = rs.getString("ID");
				String Name = rs.getString("Name");
				String Gender = rs.getString("Gender");
				String Address = rs.getString("Address");
				String Class = rs.getString("Class");
				byte BirthYear = rs.getByte("BirthYear");
				float GPA = rs.getFloat("GPA");
				result =new Student(ID, Name, Gender, Address, Class, BirthYear, GPA);			
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlc.closeConnection();
		return result;
	}

	@Override
	public boolean save(Student t) {
		int result = -1;
		Connection con = sqlc.getConnection();
		try {
			/*if (t.getName()!=null || t.getAddress()!=null || t.getAge()<2002 || t.getGpa()>0 || t.getGpa()<=10) 
			{
			}*/
			//if (t.getId() != 0 && !"".equals(t.getId())) 
			//{
	            Statement stm = con.createStatement();
				String sql = "INSERT INTO Students (ID, Name, Gender, Address, Class, BirthYear, GPA) values ('" + t.getId() + "', N'" + t.getName() + "', N'" + t.getGender() + "', N'" + t.getAddress()
				+ "', N'" + t.getClassStudent() + "', '" + t.getAge() + "', '" +t.getGpa()+ "')";
				result = stm.executeUpdate(sql);
				JLabel mess2 = new JLabel("Đã thêm 1 sinh viên");
				mess2.setFont(new Font("Arial", Font.BOLD, 18));
				JOptionPane.showMessageDialog(null, mess2);
	         //}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JLabel mess = new JLabel(e.getMessage());
			mess.setFont(new Font("Arial", Font.BOLD, 24));
			JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
		}
		return result > 0;
	}

	@Override
	public boolean update(Student t) {
		int result = -1;
		Connection con = sqlc.getConnection();
		try {
			Statement stm = con.createStatement();
			String sql = "UPDATE Students SET Name=N'"+t.getName()+"', Gender=N'"+t.getGender()+"', Address=N'"+t.getAddress()+"', Class=N'"+t.getClassStudent()+"', BirthYear="+t.getAge()+", GPA="+t.getGpa()+" WHERE ID=N'" + t.getId() + "'";
			result = stm.executeUpdate(sql);
			
			JLabel mess = new JLabel("Đã cập nhật 1 sinh viên");
			mess.setFont(new Font("Arial", Font.BOLD, 18));
			JOptionPane.showMessageDialog(null, mess);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JLabel mess = new JLabel(e.getMessage());
			mess.setFont(new Font("Arial", Font.BOLD, 24));
			JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
		}
		return result > 0;
	}

	@Override
	public void delete(String val) {
		//int result = -1;
		Connection con = sqlc.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("DELETE FROM STUDENTS WHERE Id=N'" + val +"'");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JLabel mess = new JLabel(e.getMessage());
			mess.setFont(new Font("Arial", Font.BOLD, 24));
			JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	
	
	
	
	
}